package gnu.text;

import gnu.bytecode.Access;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: classes.dex */
public class LineBufferedReader extends Reader {
    public static final int BUFFER_SIZE = 8192;
    private static final int CONVERT_CR = 1;
    private static final int DONT_KEEP_FULL_LINES = 8;
    private static final int PREV_WAS_CR = 4;
    private static final int USER_BUFFER = 2;
    public char[] buffer;
    private int flags;
    int highestPos;
    protected Reader in;
    public int limit;
    protected int lineNumber;
    private int lineStartPos;
    protected int markPos;
    Path path;
    public int pos;
    public char readState = '\n';
    protected int readAheadLimit = 0;

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    public char getReadState() {
        return this.readState;
    }

    public void setKeepFullLines(boolean keep) {
        if (keep) {
            this.flags &= -9;
        } else {
            this.flags |= 8;
        }
    }

    public final boolean getConvertCR() {
        return (this.flags & 1) != 0;
    }

    public final void setConvertCR(boolean convertCR) {
        if (convertCR) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    public LineBufferedReader(InputStream in) {
        this.in = new InputStreamReader(in);
    }

    public LineBufferedReader(Reader in) {
        this.in = in;
    }

    public void lineStart(boolean revisited) throws IOException {
    }

    public int fill(int len) throws IOException {
        return this.in.read(this.buffer, this.pos, len);
    }

    private void clearMark() {
        this.readAheadLimit = 0;
        int i = this.lineStartPos;
        int i2 = i >= 0 ? i : 0;
        while (true) {
            i2++;
            if (i2 < this.pos) {
                char ch = this.buffer[i2 - 1];
                if (ch == '\n' || (ch == '\r' && (!getConvertCR() || this.buffer[i2] != '\n'))) {
                    this.lineNumber++;
                    this.lineStartPos = i2;
                }
            } else {
                return;
            }
        }
    }

    public void setBuffer(char[] buffer) throws IOException {
        if (buffer == null) {
            char[] cArr = this.buffer;
            if (cArr != null) {
                char[] buffer2 = new char[cArr.length];
                System.arraycopy(cArr, 0, buffer2, 0, cArr.length);
                this.buffer = buffer2;
            }
            this.flags &= -3;
            return;
        }
        if (this.limit - this.pos > buffer.length) {
            throw new IOException("setBuffer - too short");
        }
        this.flags |= 2;
        reserve(buffer, 0);
    }

    private void reserve(char[] buffer, int reserve) throws IOException {
        int saveStart;
        int i;
        int i2;
        int reserve2 = reserve + this.limit;
        if (reserve2 <= buffer.length) {
            saveStart = 0;
        } else {
            saveStart = this.pos;
            int i3 = this.readAheadLimit;
            if (i3 > 0 && (i = this.markPos) < (i2 = this.pos)) {
                if (i2 - i > i3 || ((this.flags & 2) != 0 && reserve2 - i > buffer.length)) {
                    clearMark();
                } else {
                    saveStart = this.markPos;
                }
            }
            int reserve3 = reserve2 - buffer.length;
            if (reserve3 > saveStart || (saveStart > this.lineStartPos && (this.flags & 8) == 0)) {
                int i4 = this.lineStartPos;
                if (reserve3 <= i4 && saveStart > i4) {
                    saveStart = this.lineStartPos;
                } else if ((this.flags & 2) != 0) {
                    saveStart -= (saveStart - reserve3) >> 2;
                } else {
                    if (i4 >= 0) {
                        saveStart = this.lineStartPos;
                    }
                    buffer = new char[buffer.length * 2];
                }
            }
            this.lineStartPos -= saveStart;
            this.limit -= saveStart;
            this.markPos -= saveStart;
            this.pos -= saveStart;
            this.highestPos -= saveStart;
        }
        int i5 = this.limit;
        if (i5 > 0) {
            System.arraycopy(this.buffer, saveStart, buffer, 0, i5);
        }
        this.buffer = buffer;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        char prev;
        int i = this.pos;
        if (i > 0) {
            prev = this.buffer[i - 1];
        } else if ((this.flags & 4) != 0) {
            prev = '\r';
        } else if (this.lineStartPos >= 0) {
            prev = '\n';
        } else {
            prev = 0;
        }
        if (prev == '\r' || prev == '\n') {
            if (this.lineStartPos < i && (this.readAheadLimit == 0 || i <= this.markPos)) {
                this.lineStartPos = i;
                this.lineNumber++;
            }
            boolean revisited = i < this.highestPos;
            if (prev != '\n' || (i > 1 ? this.buffer[i - 2] != '\r' : (this.flags & 4) == 0)) {
                lineStart(revisited);
            }
            if (!revisited) {
                this.highestPos = this.pos + 1;
            }
        }
        int i2 = this.pos;
        int i3 = this.limit;
        if (i2 >= i3) {
            char[] cArr = this.buffer;
            if (cArr == null) {
                this.buffer = new char[8192];
            } else if (i3 == cArr.length) {
                reserve(cArr, 1);
            }
            int i4 = this.pos;
            if (i4 == 0) {
                if (prev == '\r') {
                    this.flags |= 4;
                } else {
                    this.flags &= -5;
                }
            }
            int readCount = fill(this.buffer.length - i4);
            if (readCount <= 0) {
                return -1;
            }
            this.limit += readCount;
        }
        char[] cArr2 = this.buffer;
        int i5 = this.pos;
        int i6 = i5 + 1;
        this.pos = i6;
        char c = cArr2[i5];
        if (c == '\n') {
            if (prev == '\r') {
                int i7 = this.lineStartPos;
                if (i7 == i6 - 1) {
                    this.lineNumber--;
                    this.lineStartPos = i7 - 1;
                }
                if (getConvertCR()) {
                    return read();
                }
            }
        } else if (c == '\r' && getConvertCR()) {
            return 10;
        }
        return c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x005a, code lost:
    
        if (r1 >= r11) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005e, code lost:
    
        return r11 - r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [int] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [int, char] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Override // java.io.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read(char[] r9, int r10, int r11) throws java.io.IOException {
        /*
            r8 = this;
            int r0 = r8.pos
            int r1 = r8.limit
            if (r0 < r1) goto L8
            r0 = 0
            goto L20
        L8:
            if (r0 <= 0) goto L11
            char[] r1 = r8.buffer
            int r0 = r0 + (-1)
            char r0 = r1[r0]
            goto L20
        L11:
            int r0 = r8.flags
            r0 = r0 & 4
            if (r0 != 0) goto L1e
            int r0 = r8.lineStartPos
            if (r0 < 0) goto L1c
            goto L1e
        L1c:
            r0 = 0
            goto L20
        L1e:
            r0 = 10
        L20:
            r1 = r11
        L21:
            if (r1 <= 0) goto L75
            int r2 = r8.pos
            int r3 = r8.limit
            if (r2 >= r3) goto L58
            r4 = 10
            if (r0 == r4) goto L58
            r5 = 13
            if (r0 != r5) goto L32
            goto L58
        L32:
            int r2 = r8.pos
            int r3 = r8.limit
            int r6 = r3 - r2
            if (r1 >= r6) goto L3c
            int r3 = r2 + r1
        L3c:
            if (r2 >= r3) goto L50
            char[] r6 = r8.buffer
            char r0 = r6[r2]
            if (r0 == r4) goto L50
            if (r0 != r5) goto L47
            goto L50
        L47:
            int r6 = r10 + 1
            char r7 = (char) r0
            r9[r10] = r7
            int r2 = r2 + 1
            r10 = r6
            goto L3c
        L50:
            int r4 = r8.pos
            int r4 = r2 - r4
            int r1 = r1 - r4
            r8.pos = r2
            goto L21
        L58:
            if (r2 < r3) goto L5f
            if (r1 >= r11) goto L5f
            int r2 = r11 - r1
            return r2
        L5f:
            int r0 = r8.read()
            if (r0 >= 0) goto L6c
            int r11 = r11 - r1
            if (r11 > 0) goto L6a
            r2 = -1
            goto L6b
        L6a:
            r2 = r11
        L6b:
            return r2
        L6c:
            int r2 = r10 + 1
            char r3 = (char) r0
            r9[r10] = r3
            int r1 = r1 + (-1)
            r10 = r2
            goto L21
        L75:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.read(char[], int, int):int");
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getName() {
        Path path = this.path;
        if (path == null) {
            return null;
        }
        return path.toString();
    }

    public void setName(Object name) {
        setPath(Path.valueOf(name));
    }

    public int getLineNumber() {
        int lineno = this.lineNumber;
        if (this.readAheadLimit == 0) {
            int i = this.pos;
            if (i > 0 && i > this.lineStartPos) {
                char prev = this.buffer[i - 1];
                if (prev == '\n' || prev == '\r') {
                    return lineno + 1;
                }
                return lineno;
            }
            return lineno;
        }
        char[] cArr = this.buffer;
        int i2 = this.lineStartPos;
        if (i2 < 0) {
            i2 = 0;
        }
        return lineno + countLines(cArr, i2, this.pos);
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber += lineNumber - getLineNumber();
    }

    public void incrLineNumber(int lineDelta, int lineStartPos) {
        this.lineNumber += lineDelta;
        this.lineStartPos = lineStartPos;
    }

    public int getColumnNumber() {
        int i;
        char prev;
        int i2 = this.pos;
        if (i2 > 0 && ((prev = this.buffer[i2 - 1]) == '\n' || prev == '\r')) {
            return 0;
        }
        if (this.readAheadLimit <= 0) {
            return i2 - this.lineStartPos;
        }
        int i3 = this.lineStartPos;
        int i4 = i3 >= 0 ? i3 : 0;
        int start = i4;
        while (true) {
            i = this.pos;
            if (i4 >= i) {
                break;
            }
            int i5 = i4 + 1;
            char ch = this.buffer[i4];
            if (ch == '\n' || ch == '\r') {
                start = i5;
            }
            i4 = i5;
        }
        int col = i - start;
        int i6 = this.lineStartPos;
        if (i6 < 0) {
            return col - i6;
        }
        return col;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public synchronized void mark(int readAheadLimit) {
        if (this.readAheadLimit > 0) {
            clearMark();
        }
        this.readAheadLimit = readAheadLimit;
        this.markPos = this.pos;
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        if (this.readAheadLimit <= 0) {
            throw new IOException("mark invalid");
        }
        int i = this.pos;
        if (i > this.highestPos) {
            this.highestPos = i;
        }
        this.pos = this.markPos;
        this.readAheadLimit = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x005d, code lost:
    
        r7.append(r6.buffer, r1, r2 - r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void readLine(java.lang.StringBuffer r7, char r8) throws java.io.IOException {
        /*
            r6 = this;
        L1:
            int r0 = r6.read()
            if (r0 >= 0) goto L8
            return
        L8:
            int r1 = r6.pos
            int r1 = r1 + (-1)
            r6.pos = r1
        Le:
            int r2 = r6.pos
            int r3 = r6.limit
            if (r2 >= r3) goto L5d
            char[] r3 = r6.buffer
            int r4 = r2 + 1
            r6.pos = r4
            char r0 = r3[r2]
            r2 = 13
            r5 = 10
            if (r0 == r2) goto L24
            if (r0 != r5) goto Le
        L24:
            int r4 = r4 + (-1)
            int r4 = r4 - r1
            r7.append(r3, r1, r4)
            r3 = 80
            if (r8 != r3) goto L35
            int r2 = r6.pos
            int r2 = r2 + (-1)
            r6.pos = r2
            return
        L35:
            boolean r3 = r6.getConvertCR()
            r4 = 73
            if (r3 != 0) goto L57
            if (r0 != r5) goto L40
            goto L57
        L40:
            if (r8 == r4) goto L45
            r7.append(r2)
        L45:
            int r0 = r6.read()
            if (r0 != r5) goto L51
            if (r8 == r4) goto L5c
            r7.append(r5)
            goto L5c
        L51:
            if (r0 < 0) goto L5c
            r6.unread_quick()
            goto L5c
        L57:
            if (r8 == r4) goto L5c
            r7.append(r5)
        L5c:
            return
        L5d:
            char[] r3 = r6.buffer
            int r2 = r2 - r1
            r7.append(r3, r1, r2)
            goto L1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.LineBufferedReader.readLine(java.lang.StringBuffer, char):void");
    }

    public String readLine() throws IOException {
        int i;
        char c;
        int ch = read();
        if (ch < 0) {
            return null;
        }
        if (ch == 13 || ch == 10) {
            return "";
        }
        int start = this.pos - 1;
        do {
            int i2 = this.pos;
            if (i2 >= this.limit) {
                break;
            }
            char[] cArr = this.buffer;
            i = i2 + 1;
            this.pos = i;
            c = cArr[i2];
            if (c == '\r') {
                break;
            }
        } while (c != '\n');
        int end = i - 1;
        if (c != '\n' && !getConvertCR()) {
            int i3 = this.pos;
            if (i3 >= this.limit) {
                this.pos = i3 - 1;
                StringBuffer sbuf = new StringBuffer(100);
                sbuf.append(this.buffer, start, this.pos - start);
                readLine(sbuf, Access.INNERCLASS_CONTEXT);
                return sbuf.toString();
            }
            if (this.buffer[i3] == '\n') {
                this.pos = i3 + 1;
            }
        }
        return new String(this.buffer, start, end - start);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [char] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public int skip(int i) throws IOException {
        char c;
        if (i < 0) {
            int i2 = -i;
            while (i2 > 0 && this.pos > 0) {
                unread();
                i2--;
            }
            return i + i2;
        }
        int i3 = i;
        int i4 = this.pos;
        if (i4 >= this.limit) {
            c = 0;
        } else if (i4 > 0) {
            c = this.buffer[i4 - 1];
        } else if ((this.flags & 4) != 0 || this.lineStartPos >= 0) {
            c = 10;
        } else {
            c = 0;
        }
        while (i3 > 0) {
            if (c == 10 || c == 13 || this.pos >= this.limit) {
                c = read();
                if (c < 0) {
                    return i - i3;
                }
                i3--;
            } else {
                int i5 = this.pos;
                int i6 = this.limit;
                if (i3 < i6 - i5) {
                    i6 = i5 + i3;
                }
                while (i5 < i6) {
                    c = this.buffer[i5];
                    if (c == 10 || c == 13) {
                        break;
                    }
                    i5++;
                }
                i3 -= i5 - this.pos;
                this.pos = i5;
            }
        }
        return i;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        return this.pos < this.limit || this.in.ready();
    }

    public final void skip_quick() throws IOException {
        this.pos++;
    }

    public void skip() throws IOException {
        read();
    }

    static int countLines(char[] buffer, int start, int limit) {
        int count = 0;
        char prev = 0;
        for (int i = start; i < limit; i++) {
            char ch = buffer[i];
            if ((ch == '\n' && prev != '\r') || ch == '\r') {
                count++;
            }
            prev = ch;
        }
        return count;
    }

    public void skipRestOfLine() throws IOException {
        int c;
        do {
            c = read();
            if (c < 0) {
                return;
            }
            if (c == 13) {
                int c2 = read();
                if (c2 >= 0 && c2 != 10) {
                    unread();
                    return;
                }
                return;
            }
        } while (c != 10);
    }

    public void unread() throws IOException {
        int i = this.pos;
        if (i == 0) {
            throw new IOException("unread too much");
        }
        int i2 = i - 1;
        this.pos = i2;
        char ch = this.buffer[i2];
        if (ch == '\n' || ch == '\r') {
            if (i2 > 0 && ch == '\n' && getConvertCR()) {
                char[] cArr = this.buffer;
                int i3 = this.pos;
                if (cArr[i3 - 1] == '\r') {
                    this.pos = i3 - 1;
                }
            }
            if (this.pos < this.lineStartPos) {
                this.lineNumber--;
                int i4 = this.pos;
                while (i4 > 0) {
                    i4--;
                    char ch2 = this.buffer[i4];
                    if (ch2 == '\r' || ch2 == '\n') {
                        i4++;
                        break;
                    }
                }
                this.lineStartPos = i4;
            }
        }
    }

    public void unread_quick() {
        this.pos--;
    }

    public int peek() throws IOException {
        char[] cArr;
        char ch;
        int i = this.pos;
        if (i < this.limit && i > 0 && (ch = (cArr = this.buffer)[i - 1]) != '\n' && ch != '\r') {
            char ch2 = cArr[i];
            if (ch2 == '\r' && getConvertCR()) {
                return 10;
            }
            return ch2;
        }
        int c = read();
        if (c >= 0) {
            unread_quick();
        }
        return c;
    }
}
