package kawa;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.swingviews.SwingContent;
import gnu.lists.CharBuffer;
import gnu.mapping.Environment;
import gnu.mapping.Future;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.QueueReader;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/* loaded from: classes.dex */
public class ReplDocument extends DefaultStyledDocument implements DocumentListener, FocusListener {
    static Style blueStyle;
    public static Style defaultStyle;
    public static Style inputStyle;
    static Style promptStyle;
    public static Style redStyle;
    public static StyleContext styles;
    Object closeListeners;
    SwingContent content;
    public int endMark;
    Environment environment;
    final ReplPaneOutPort err_stream;
    final GuiInPort in_p;
    final QueueReader in_r;
    Language language;
    int length;
    final ReplPaneOutPort out_stream;
    public int outputMark;
    JTextPane pane;
    int paneCount;
    Future thread;

    /* loaded from: classes.dex */
    public interface DocumentCloseListener {
        void closed(ReplDocument replDocument);
    }

    static {
        StyleContext styleContext = new StyleContext();
        styles = styleContext;
        defaultStyle = styleContext.addStyle("default", (Style) null);
        inputStyle = styles.addStyle("input", (Style) null);
        redStyle = styles.addStyle("red", (Style) null);
        blueStyle = styles.addStyle("blue", (Style) null);
        promptStyle = styles.addStyle("prompt", (Style) null);
        StyleConstants.setForeground(redStyle, Color.red);
        StyleConstants.setForeground(blueStyle, Color.blue);
        StyleConstants.setForeground(promptStyle, Color.green);
        StyleConstants.setBold(inputStyle, true);
    }

    public ReplDocument(Language language, Environment penvironment, boolean shared) {
        this(new SwingContent(), language, penvironment, shared);
    }

    private ReplDocument(SwingContent content, Language language, Environment penvironment, final boolean shared) {
        super(content, styles);
        this.outputMark = 0;
        this.endMark = -1;
        this.length = 0;
        this.content = content;
        ModuleBody.exitIncrement();
        addDocumentListener(this);
        this.language = language;
        QueueReader queueReader = new QueueReader() { // from class: kawa.ReplDocument.1
            @Override // gnu.text.QueueReader
            public void checkAvailable() {
                ReplDocument.this.checkingPendingInput();
            }
        };
        this.in_r = queueReader;
        ReplPaneOutPort replPaneOutPort = new ReplPaneOutPort(this, "/dev/stdout", defaultStyle);
        this.out_stream = replPaneOutPort;
        ReplPaneOutPort replPaneOutPort2 = new ReplPaneOutPort(this, "/dev/stderr", redStyle);
        this.err_stream = replPaneOutPort2;
        GuiInPort guiInPort = new GuiInPort(queueReader, Path.valueOf("/dev/stdin"), replPaneOutPort, this);
        this.in_p = guiInPort;
        Future make = Future.make(new repl(language) { // from class: kawa.ReplDocument.2
            @Override // kawa.repl, gnu.mapping.Procedure0or1, gnu.mapping.Procedure
            public Object apply0() {
                Environment env = Environment.getCurrent();
                if (shared) {
                    env.setIndirectDefines();
                }
                ReplDocument.this.environment = env;
                Shell.run(this.language, env);
                SwingUtilities.invokeLater(new Runnable() { // from class: kawa.ReplDocument.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ReplDocument.this.fireDocumentClosed();
                    }
                });
                return Values.empty;
            }
        }, penvironment, guiInPort, replPaneOutPort, replPaneOutPort2);
        this.thread = make;
        make.start();
    }

    public synchronized void deleteOldText() {
        try {
            String str = getText(0, this.outputMark);
            int i = this.outputMark;
            int lineBefore = i <= 0 ? 0 : str.lastIndexOf(10, i - 1) + 1;
            remove(0, lineBefore);
        } catch (BadLocationException ex) {
            throw new Error((Throwable) ex);
        }
    }

    public void insertString(int pos, String str, AttributeSet style) {
        try {
            super.insertString(pos, str, style);
        } catch (BadLocationException ex) {
            throw new Error((Throwable) ex);
        }
    }

    public void write(final String str, final AttributeSet style) {
        SwingUtilities.invokeLater(new Runnable() { // from class: kawa.ReplDocument.3
            @Override // java.lang.Runnable
            public void run() {
                boolean moveCaret = ReplDocument.this.pane != null && ReplDocument.this.pane.getCaretPosition() == ReplDocument.this.outputMark;
                ReplDocument replDocument = ReplDocument.this;
                replDocument.insertString(replDocument.outputMark, str, style);
                int len = str.length();
                ReplDocument.this.outputMark += len;
                if (moveCaret) {
                    ReplDocument.this.pane.setCaretPosition(ReplDocument.this.outputMark);
                }
            }
        });
    }

    public void checkingPendingInput() {
        SwingUtilities.invokeLater(new Runnable() { // from class: kawa.ReplDocument.4
            @Override // java.lang.Runnable
            public void run() {
                int inputStart = ReplDocument.this.outputMark;
                if (inputStart <= ReplDocument.this.endMark) {
                    CharBuffer b = ReplDocument.this.content.buffer;
                    int lineAfter = b.indexOf(10, inputStart);
                    if (lineAfter == ReplDocument.this.endMark) {
                        ReplDocument.this.endMark = -1;
                    }
                    if (inputStart == ReplDocument.this.outputMark) {
                        ReplDocument.this.outputMark = lineAfter + 1;
                    }
                    if (ReplDocument.this.in_r != null) {
                        synchronized (ReplDocument.this.in_r) {
                            ReplDocument.this.in_r.append((CharSequence) b, inputStart, lineAfter + 1);
                            ReplDocument.this.in_r.notifyAll();
                        }
                    }
                }
            }
        });
    }

    public void focusGained(FocusEvent e) {
        Object source = e.getSource();
        if (source instanceof ReplPane) {
            this.pane = (ReplPane) source;
        } else {
            this.pane = null;
        }
        this.pane = source instanceof ReplPane ? (ReplPane) source : null;
    }

    public void focusLost(FocusEvent e) {
        this.pane = null;
    }

    public void changedUpdate(DocumentEvent e) {
        textValueChanged(e);
    }

    public void insertUpdate(DocumentEvent e) {
        textValueChanged(e);
    }

    public void removeUpdate(DocumentEvent e) {
        textValueChanged(e);
    }

    public synchronized void textValueChanged(DocumentEvent e) {
        int pos = e.getOffset();
        int length = getLength();
        int i = this.length;
        int delta = length - i;
        this.length = i + delta;
        int i2 = this.outputMark;
        if (pos < i2) {
            this.outputMark = i2 + delta;
        } else if (pos - delta < i2) {
            this.outputMark = pos;
        }
        int i3 = this.endMark;
        if (i3 >= 0) {
            if (pos < i3) {
                this.endMark = i3 + delta;
            } else if (pos - delta < i3) {
                this.endMark = pos;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() {
        this.in_r.appendEOF();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
        }
        this.thread.stop();
        fireDocumentClosed();
        ModuleBody.exitDecrement();
    }

    public void addDocumentCloseListener(DocumentCloseListener listener) {
        ArrayList vec;
        Object obj = this.closeListeners;
        if (obj == null) {
            this.closeListeners = listener;
            return;
        }
        if (obj instanceof ArrayList) {
            vec = (ArrayList) obj;
        } else {
            vec = new ArrayList(10);
            vec.add(this.closeListeners);
            this.closeListeners = vec;
        }
        vec.add(listener);
    }

    public void removeDocumentCloseListener(DocumentCloseListener listener) {
        Object obj = this.closeListeners;
        if (obj instanceof DocumentCloseListener) {
            if (obj == listener) {
                this.closeListeners = null;
            }
        } else if (obj != null) {
            ArrayList vec = (ArrayList) obj;
            int i = vec.size();
            while (true) {
                i--;
                if (i < 0) {
                    break;
                } else if (vec.get(i) == listener) {
                    vec.remove(i);
                }
            }
            int i2 = vec.size();
            if (i2 == 0) {
                this.closeListeners = null;
            }
        }
    }

    void fireDocumentClosed() {
        Object obj = this.closeListeners;
        if (obj instanceof DocumentCloseListener) {
            ((DocumentCloseListener) obj).closed(this);
            return;
        }
        if (obj != null) {
            ArrayList vec = (ArrayList) obj;
            int i = vec.size();
            while (true) {
                i--;
                if (i >= 0) {
                    ((DocumentCloseListener) vec.get(i)).closed(this);
                } else {
                    return;
                }
            }
        }
    }
}
