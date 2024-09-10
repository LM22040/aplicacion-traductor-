package gnu.text;

import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.location.LocationRequestCompat;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/* loaded from: classes.dex */
public class Lexer extends Reader {
    protected boolean interactive;
    SourceMessages messages;
    protected int nesting;
    protected LineBufferedReader port;
    private int saveTokenBufferLength;
    public char[] tokenBuffer;
    public int tokenBufferLength;

    public Lexer(LineBufferedReader port) {
        this.messages = null;
        this.tokenBuffer = new char[100];
        this.tokenBufferLength = 0;
        this.saveTokenBufferLength = -1;
        this.port = port;
    }

    public Lexer(LineBufferedReader port, SourceMessages messages) {
        this.messages = null;
        this.tokenBuffer = new char[100];
        this.tokenBufferLength = 0;
        this.saveTokenBufferLength = -1;
        this.port = port;
        this.messages = messages;
    }

    public char pushNesting(char promptChar) {
        this.nesting++;
        LineBufferedReader port = getPort();
        char save = port.readState;
        port.readState = promptChar;
        return save;
    }

    public void popNesting(char save) {
        LineBufferedReader port = getPort();
        port.readState = save;
        this.nesting--;
    }

    public final LineBufferedReader getPort() {
        return this.port;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.port.close();
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        return this.port.read();
    }

    public int readUnicodeChar() throws IOException {
        int next;
        int c = this.port.read();
        if (c >= 55296 && c < 56319 && (next = this.port.read()) >= 56320 && next <= 57343) {
            return ((c - 55296) << 10) + (c - 56320) + 65536;
        }
        return c;
    }

    @Override // java.io.Reader
    public int read(char[] buf, int offset, int length) throws IOException {
        return this.port.read(buf, offset, length);
    }

    public void unread(int ch) throws IOException {
        if (ch >= 0) {
            this.port.unread();
        }
    }

    public int peek() throws IOException {
        return this.port.peek();
    }

    public void skip() throws IOException {
        this.port.skip();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void unread() throws IOException {
        this.port.unread();
    }

    protected void unread_quick() throws IOException {
        this.port.unread_quick();
    }

    public boolean checkNext(char ch) throws IOException {
        int r = this.port.read();
        if (r == ch) {
            return true;
        }
        if (r >= 0) {
            this.port.unread_quick();
            return false;
        }
        return false;
    }

    protected void skip_quick() throws IOException {
        this.port.skip_quick();
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void setMessages(SourceMessages messages) {
        this.messages = messages;
    }

    public boolean checkErrors(PrintWriter out, int max) {
        SourceMessages sourceMessages = this.messages;
        return sourceMessages != null && sourceMessages.checkErrors(out, max);
    }

    public SourceError getErrors() {
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages == null) {
            return null;
        }
        return sourceMessages.getErrors();
    }

    public boolean seenErrors() {
        SourceMessages sourceMessages = this.messages;
        return sourceMessages != null && sourceMessages.seenErrors();
    }

    public void clearErrors() {
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages != null) {
            sourceMessages.clearErrors();
        }
    }

    public void error(char severity, String filename, int line, int column, String message) {
        if (this.messages == null) {
            this.messages = new SourceMessages();
        }
        this.messages.error(severity, filename, line, column, message);
    }

    public void error(char severity, String message) {
        int line = this.port.getLineNumber();
        int column = this.port.getColumnNumber();
        error(severity, this.port.getName(), line + 1, column >= 0 ? column + 1 : 0, message);
    }

    public void error(String message) {
        error('e', message);
    }

    public void fatal(String message) throws SyntaxException {
        error('f', message);
        throw new SyntaxException(this.messages);
    }

    public void eofError(String msg) throws SyntaxException {
        fatal(msg);
    }

    public void eofError(String message, int startLine, int startColumn) throws SyntaxException {
        error('f', this.port.getName(), startLine, startColumn, message);
        throw new SyntaxException(this.messages);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int readOptionalExponent() throws java.io.IOException {
        /*
            r9 = this;
            int r0 = r9.read()
            r1 = 0
            r2 = 43
            r3 = 45
            if (r0 == r2) goto L11
            if (r0 != r3) goto Le
            goto L11
        Le:
            r2 = r0
            r0 = 0
            goto L15
        L11:
            int r2 = r9.read()
        L15:
            if (r2 < 0) goto L39
            char r4 = (char) r2
            r5 = 10
            int r4 = java.lang.Character.digit(r4, r5)
            r6 = r4
            if (r4 >= 0) goto L22
            goto L39
        L22:
            r4 = 214748363(0xccccccb, float:3.1554432E-31)
        L25:
            int r2 = r9.read()
            char r7 = (char) r2
            int r7 = java.lang.Character.digit(r7, r5)
            if (r7 >= 0) goto L31
            goto L41
        L31:
            if (r6 <= r4) goto L34
            r1 = 1
        L34:
            int r8 = r6 * 10
            int r6 = r8 + r7
            goto L25
        L39:
            if (r0 == 0) goto L40
            java.lang.String r4 = "exponent sign not followed by digit"
            r9.error(r4)
        L40:
            r6 = 1
        L41:
            if (r2 < 0) goto L46
            r9.unread(r2)
        L46:
            if (r0 != r3) goto L49
            int r6 = -r6
        L49:
            if (r1 == 0) goto L54
            if (r0 != r3) goto L50
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L53
        L50:
            r3 = 2147483647(0x7fffffff, float:NaN)
        L53:
            return r3
        L54:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Lexer.readOptionalExponent():int");
    }

    public boolean readDelimited(String delimiter) throws IOException, SyntaxException {
        this.tokenBufferLength = 0;
        int dlen = delimiter.length();
        char last = delimiter.charAt(dlen - 1);
        while (true) {
            int ch = read();
            if (ch < 0) {
                return false;
            }
            if (ch == last) {
                int i = dlen - 1;
                int j = i;
                int dstart = this.tokenBufferLength - i;
                if (dstart < 0) {
                    continue;
                }
                while (j != 0) {
                    j--;
                    if (this.tokenBuffer[dstart + j] != delimiter.charAt(j)) {
                        break;
                    }
                }
                this.tokenBufferLength = dstart;
                return true;
            }
            tokenBufferAppend((char) ch);
        }
    }

    public static long readDigitsInBuffer(LineBufferedReader port, int radix) {
        long ival = 0;
        boolean overflow = false;
        long max_val = LocationRequestCompat.PASSIVE_INTERVAL / radix;
        int i = port.pos;
        if (i >= port.limit) {
            return 0L;
        }
        do {
            char c = port.buffer[i];
            int dval = Character.digit(c, radix);
            if (dval < 0) {
                break;
            }
            if (ival > max_val) {
                overflow = true;
            } else {
                ival = (radix * ival) + dval;
            }
            if (ival < 0) {
                overflow = true;
            }
            i++;
        } while (i < port.limit);
        port.pos = i;
        if (overflow) {
            return -1L;
        }
        return ival;
    }

    public String getName() {
        return this.port.getName();
    }

    public int getLineNumber() {
        return this.port.getLineNumber();
    }

    public int getColumnNumber() {
        return this.port.getColumnNumber();
    }

    public boolean isInteractive() {
        return this.interactive;
    }

    public void setInteractive(boolean v) {
        this.interactive = v;
    }

    public void tokenBufferAppend(int ch) {
        if (ch >= 65536) {
            tokenBufferAppend(((ch - 65536) >> 10) + 55296);
            ch = (ch & 1023) + 56320;
        }
        int len = this.tokenBufferLength;
        char[] buffer = this.tokenBuffer;
        if (len == this.tokenBuffer.length) {
            char[] cArr = new char[len * 2];
            this.tokenBuffer = cArr;
            System.arraycopy(buffer, 0, cArr, 0, len);
            buffer = this.tokenBuffer;
        }
        buffer[len] = (char) ch;
        this.tokenBufferLength = len + 1;
    }

    public String tokenBufferString() {
        return new String(this.tokenBuffer, 0, this.tokenBufferLength);
    }

    public void mark() throws IOException {
        if (this.saveTokenBufferLength >= 0) {
            throw new Error("internal error: recursive call to mark not allowed");
        }
        this.port.mark(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        this.saveTokenBufferLength = this.tokenBufferLength;
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        if (this.saveTokenBufferLength < 0) {
            throw new Error("internal error: reset called without prior mark");
        }
        this.port.reset();
        this.saveTokenBufferLength = -1;
    }
}
