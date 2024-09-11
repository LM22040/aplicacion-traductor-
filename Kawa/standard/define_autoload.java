package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Symbol;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import kawa.lang.AutoloadProcedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class define_autoload extends Syntax {
    public static final define_autoload define_autoload = new define_autoload("define-autoload", false);
    public static final define_autoload define_autoloads_from_file = new define_autoload("define-autoloads-from-file", true);
    boolean fromFile;

    public define_autoload(String name, boolean fromFile) {
        super(name);
        this.fromFile = fromFile;
    }

    @Override // kawa.lang.Syntax
    public boolean scanForDefinitions(Pair st, Vector forms, ScopeExp defs, Translator tr) {
        if (!(st.getCdr() instanceof Pair)) {
            return super.scanForDefinitions(st, forms, defs, tr);
        }
        Pair p = (Pair) st.getCdr();
        if (this.fromFile) {
            while (p.getCar() instanceof CharSequence) {
                if (!scanFile(p.getCar().toString(), defs, tr)) {
                    return false;
                }
                Object rest = p.getCdr();
                if (rest == LList.Empty) {
                    return true;
                }
                if (!(rest instanceof Pair)) {
                    break;
                }
                p = (Pair) p.getCdr();
            }
            tr.syntaxError("invalid syntax for define-autoloads-from-file");
            return false;
        }
        Object names = p.getCar();
        if (p.getCdr() instanceof Pair) {
            Object filename = ((Pair) p.getCdr()).getCar();
            return process(names, filename, forms, defs, tr);
        }
        tr.syntaxError("invalid syntax for define-autoload");
        return false;
    }

    public boolean scanFile(String filespec, ScopeExp defs, Translator tr) {
        File file;
        filespec.endsWith(".el");
        File file2 = new File(filespec);
        if (!file2.isAbsolute()) {
            file = new File(new File(tr.getFileName()).getParent(), filespec);
        } else {
            file = file2;
        }
        String filename = file.getPath();
        int dot = filename.lastIndexOf(46);
        if (dot < 0) {
            return true;
        }
        String extension = filename.substring(dot);
        Language language = Language.getInstance(extension);
        if (language == null) {
            tr.syntaxError("unknown extension for " + filename);
            return true;
        }
        String prefix = tr.classPrefix;
        int extlen = extension.length();
        int speclen = filespec.length();
        String cname = filespec.substring(0, speclen - extlen);
        while (cname.startsWith("../")) {
            int i = prefix.lastIndexOf(46, prefix.length() - 2);
            if (i < 0) {
                tr.syntaxError("cannot use relative filename \"" + filespec + "\" with simple prefix \"" + prefix + "\"");
                return false;
            }
            prefix = prefix.substring(0, i + 1);
            cname = cname.substring(3);
        }
        String classname = (prefix + cname).replace('/', '.');
        try {
            InPort port = InPort.openFile(filename);
            Lexer lexer = language.getLexer(port, tr.getMessages());
            try {
                try {
                    findAutoloadComments((LispReader) lexer, classname, defs, tr);
                    return true;
                } catch (Exception e) {
                    ex = e;
                    tr.syntaxError("error reading " + filename + ": " + ex);
                    return true;
                }
            } catch (Exception e2) {
                ex = e2;
            }
        } catch (Exception e3) {
            ex = e3;
        }
    }

    public static void findAutoloadComments(LispReader in, String filename, ScopeExp defs, Translator tr) throws IOException, SyntaxException {
        Compilation compilation = tr;
        boolean lineStart = true;
        int magicLength = ";;;###autoload".length();
        while (true) {
            int ch = in.peek();
            if (ch < 0) {
                return;
            }
            if (ch == 10 || ch == 13) {
                in.read();
                lineStart = true;
                compilation = tr;
            } else {
                if (lineStart && ch == 59) {
                    int i = 0;
                    while (i != magicLength) {
                        boolean lineStart2 = lineStart;
                        ch = in.read();
                        if (ch < 0) {
                            return;
                        }
                        if (ch == 10 || ch == 13) {
                            lineStart = true;
                            compilation = tr;
                            break;
                        } else if (i >= 0) {
                            int i2 = i + 1;
                            if (ch == ";;;###autoload".charAt(i)) {
                                compilation = tr;
                                i = i2;
                                lineStart = lineStart2;
                            } else {
                                i = -1;
                                compilation = tr;
                                lineStart = lineStart2;
                            }
                        } else {
                            compilation = tr;
                            lineStart = lineStart2;
                        }
                    }
                    if (i > 0) {
                        Object form = in.readObject();
                        if (form instanceof Pair) {
                            Pair pair = (Pair) form;
                            Object value = null;
                            String name = null;
                            Object car = pair.getCar();
                            String command = car instanceof String ? car.toString() : car instanceof Symbol ? ((Symbol) car).getName() : null;
                            if (command == "defun") {
                                name = ((Pair) pair.getCdr()).getCar().toString();
                                value = new AutoloadProcedure(name, filename, tr.getLanguage());
                            } else {
                                compilation.error('w', "unsupported ;;;###autoload followed by: " + pair.getCar());
                            }
                            if (value != null) {
                                Declaration decl = defs.getDefine(name, 'w', compilation);
                                Expression ex = new QuoteExp(value);
                                decl.setFlag(16384L);
                                decl.noteValue(ex);
                                decl.setProcedureDecl(true);
                                decl.setType(Compilation.typeProcedure);
                            }
                        }
                        lineStart = false;
                        compilation = tr;
                    }
                }
                lineStart = false;
                in.skip();
                if (ch == 35 && in.peek() == 124) {
                    in.skip();
                    in.readNestedComment('#', '|');
                } else if (!Character.isWhitespace((char) ch)) {
                    Object skipped = in.readObject(ch);
                    if (skipped == Sequence.eofValue) {
                        return;
                    }
                }
                compilation = tr;
            }
        }
    }

    public static boolean process(Object names, Object filename, Vector forms, ScopeExp defs, Translator tr) {
        String fn;
        int len;
        if (names instanceof Pair) {
            Pair p = (Pair) names;
            return process(p.getCar(), filename, forms, defs, tr) && process(p.getCdr(), filename, forms, defs, tr);
        }
        if (names == LList.Empty) {
            return true;
        }
        if (!(names instanceof String) && !(names instanceof Symbol)) {
            return false;
        }
        String name = names.toString();
        Declaration decl = defs.getDefine(name, 'w', tr);
        if ((filename instanceof String) && (len = (fn = (String) filename).length()) > 2 && fn.charAt(0) == '<' && fn.charAt(len - 1) == '>') {
            filename = fn.substring(1, len - 1);
        }
        Object value = new AutoloadProcedure(name, filename.toString(), tr.getLanguage());
        Expression ex = new QuoteExp(value);
        decl.setFlag(16384L);
        decl.noteValue(ex);
        return true;
    }

    @Override // kawa.lang.Syntax
    public Expression rewriteForm(Pair form, Translator tr) {
        return null;
    }
}
