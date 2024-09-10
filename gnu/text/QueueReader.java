package gnu.text;

import gnu.lists.CharSeq;
import java.io.Reader;

/* loaded from: classes.dex */
public class QueueReader extends Reader implements Appendable {
    boolean EOFseen;
    char[] buffer;
    int limit;
    int mark;
    int pos;
    int readAheadLimit;

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public synchronized void mark(int readAheadLimit) {
        this.readAheadLimit = readAheadLimit;
        this.mark = this.pos;
    }

    @Override // java.io.Reader
    public synchronized void reset() {
        if (this.readAheadLimit > 0) {
            this.pos = this.mark;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void resize(int r7) {
        /*
            r6 = this;
            int r0 = r6.limit
            int r1 = r6.pos
            int r2 = r0 - r1
            int r3 = r6.readAheadLimit
            if (r3 <= 0) goto L13
            int r4 = r6.mark
            int r5 = r1 - r4
            if (r5 > r3) goto L13
            int r2 = r0 - r4
            goto L15
        L13:
            r6.mark = r1
        L15:
            char[] r0 = r6.buffer
            int r1 = r0.length
            int r3 = r2 + r7
            if (r1 >= r3) goto L22
            int r1 = r2 * 2
            int r1 = r1 + r7
            char[] r1 = new char[r1]
            goto L23
        L22:
            r1 = r0
        L23:
            int r3 = r6.mark
            r4 = 0
            java.lang.System.arraycopy(r0, r3, r1, r4, r2)
            r6.buffer = r1
            int r0 = r6.pos
            int r3 = r6.mark
            int r0 = r0 - r3
            r6.pos = r0
            r6.mark = r4
            r6.limit = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.QueueReader.resize(int):void");
    }

    @Override // java.lang.Appendable
    public QueueReader append(CharSequence csq) {
        if (csq == null) {
            csq = "null";
        }
        return append(csq, 0, csq.length());
    }

    @Override // java.lang.Appendable
    public synchronized QueueReader append(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }
        int len = end - start;
        reserveSpace(len);
        int sz = this.limit;
        char[] d = this.buffer;
        if (csq instanceof String) {
            ((String) csq).getChars(start, end, d, sz);
        } else if (csq instanceof CharSeq) {
            ((CharSeq) csq).getChars(start, end, d, sz);
        } else {
            int j = sz;
            int i = start;
            while (i < end) {
                d[j] = csq.charAt(i);
                i++;
                j++;
            }
        }
        this.limit = sz + len;
        notifyAll();
        return this;
    }

    public void append(char[] chars) {
        append(chars, 0, chars.length);
    }

    public synchronized void append(char[] chars, int off, int len) {
        reserveSpace(len);
        System.arraycopy(chars, off, this.buffer, this.limit, len);
        this.limit += len;
        notifyAll();
    }

    @Override // java.lang.Appendable
    public synchronized QueueReader append(char ch) {
        reserveSpace(1);
        char[] cArr = this.buffer;
        int i = this.limit;
        this.limit = i + 1;
        cArr[i] = ch;
        notifyAll();
        return this;
    }

    public synchronized void appendEOF() {
        this.EOFseen = true;
    }

    protected void reserveSpace(int len) {
        char[] cArr = this.buffer;
        if (cArr == null) {
            this.buffer = new char[len + 100];
        } else if (cArr.length < this.limit + len) {
            resize(len);
        }
    }

    @Override // java.io.Reader
    public synchronized boolean ready() {
        boolean z;
        if (this.pos >= this.limit) {
            z = this.EOFseen;
        }
        return z;
    }

    public void checkAvailable() {
    }

    @Override // java.io.Reader
    public synchronized int read() {
        while (true) {
            int i = this.pos;
            if (i >= this.limit) {
                if (this.EOFseen) {
                    return -1;
                }
                checkAvailable();
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            } else {
                char[] cArr = this.buffer;
                this.pos = i + 1;
                char ch = cArr[i];
                return ch;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x001c, code lost:
    
        r1 = r1 - r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x001d, code lost:
    
        if (r6 <= r1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x001f, code lost:
    
        r6 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0020, code lost:
    
        java.lang.System.arraycopy(r3.buffer, r0, r4, r5, r6);
        r3.pos += r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x002b, code lost:
    
        return r6;
     */
    @Override // java.io.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int read(char[] r4, int r5, int r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r6 != 0) goto L6
            monitor-exit(r3)
            r0 = 0
            return r0
        L6:
            int r0 = r3.pos     // Catch: java.lang.Throwable -> L2c
            int r1 = r3.limit     // Catch: java.lang.Throwable -> L2c
            if (r0 < r1) goto L1c
            boolean r0 = r3.EOFseen     // Catch: java.lang.Throwable -> L2c
            if (r0 == 0) goto L13
            monitor-exit(r3)
            r0 = -1
            return r0
        L13:
            r3.checkAvailable()     // Catch: java.lang.Throwable -> L2c
            r3.wait()     // Catch: java.lang.InterruptedException -> L1a java.lang.Throwable -> L2c
            goto L6
        L1a:
            r0 = move-exception
            goto L6
        L1c:
            int r1 = r1 - r0
            if (r6 <= r1) goto L20
            r6 = r1
        L20:
            char[] r2 = r3.buffer     // Catch: java.lang.Throwable -> L2c
            java.lang.System.arraycopy(r2, r0, r4, r5, r6)     // Catch: java.lang.Throwable -> L2c
            int r0 = r3.pos     // Catch: java.lang.Throwable -> L2c
            int r0 = r0 + r6
            r3.pos = r0     // Catch: java.lang.Throwable -> L2c
            monitor-exit(r3)
            return r6
        L2c:
            r4 = move-exception
            monitor-exit(r3)
            goto L30
        L2f:
            throw r4
        L30:
            goto L2f
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.QueueReader.read(char[], int, int):int");
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.pos = 0;
        this.limit = 0;
        this.mark = 0;
        this.EOFseen = true;
        this.buffer = null;
    }
}
