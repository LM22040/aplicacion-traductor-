package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SourceMessages implements SourceLocator {
    int current_column;
    String current_filename;
    int current_line;
    SourceError firstError;
    SourceError lastError;
    SourceLocator locator;
    public boolean sortMessages;
    public static boolean debugStackTraceOnWarning = false;
    public static boolean debugStackTraceOnError = false;
    private int errorCount = 0;
    SourceError lastPrevFilename = null;

    public SourceError getErrors() {
        return this.firstError;
    }

    public boolean seenErrors() {
        return this.errorCount > 0;
    }

    public boolean seenErrorsOrWarnings() {
        return this.firstError != null;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public void clearErrors() {
        this.errorCount = 0;
    }

    public void clear() {
        this.lastError = null;
        this.firstError = null;
        this.errorCount = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x008b, code lost:
    
        r5.next = r4.firstError;
        r4.firstError = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void error(gnu.text.SourceError r5) {
        /*
            r4 = this;
            char r0 = r5.severity
            r1 = 119(0x77, float:1.67E-43)
            r2 = 102(0x66, float:1.43E-43)
            if (r0 != r2) goto Ld
            r0 = 1000(0x3e8, float:1.401E-42)
            r4.errorCount = r0
            goto L17
        Ld:
            char r0 = r5.severity
            if (r0 == r1) goto L17
            int r0 = r4.errorCount
            int r0 = r0 + 1
            r4.errorCount = r0
        L17:
            boolean r0 = gnu.text.SourceMessages.debugStackTraceOnError
            if (r0 == 0) goto L25
            char r0 = r5.severity
            r3 = 101(0x65, float:1.42E-43)
            if (r0 == r3) goto L2d
            char r0 = r5.severity
            if (r0 == r2) goto L2d
        L25:
            boolean r0 = gnu.text.SourceMessages.debugStackTraceOnWarning
            if (r0 == 0) goto L34
            char r0 = r5.severity
            if (r0 != r1) goto L34
        L2d:
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            r5.fakeException = r0
        L34:
            gnu.text.SourceError r0 = r4.lastError
            if (r0 == 0) goto L4c
            java.lang.String r0 = r0.filename
            if (r0 == 0) goto L4c
            gnu.text.SourceError r0 = r4.lastError
            java.lang.String r0 = r0.filename
            java.lang.String r1 = r5.filename
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L4c
            gnu.text.SourceError r0 = r4.lastError
            r4.lastPrevFilename = r0
        L4c:
            gnu.text.SourceError r0 = r4.lastPrevFilename
            boolean r1 = r4.sortMessages
            if (r1 == 0) goto L87
            char r1 = r5.severity
            if (r1 != r2) goto L57
            goto L87
        L57:
            if (r0 != 0) goto L5c
            gnu.text.SourceError r1 = r4.firstError
            goto L5e
        L5c:
            gnu.text.SourceError r1 = r0.next
        L5e:
            if (r1 != 0) goto L61
            goto L89
        L61:
            int r2 = r5.line
            if (r2 == 0) goto L85
            int r2 = r1.line
            if (r2 == 0) goto L85
            int r2 = r5.line
            int r3 = r1.line
            if (r2 >= r3) goto L70
            goto L89
        L70:
            int r2 = r5.line
            int r3 = r1.line
            if (r2 != r3) goto L85
            int r2 = r5.column
            if (r2 == 0) goto L85
            int r2 = r1.column
            if (r2 == 0) goto L85
            int r2 = r5.column
            int r3 = r1.column
            if (r2 >= r3) goto L85
            goto L89
        L85:
            r0 = r1
            goto L57
        L87:
            gnu.text.SourceError r0 = r4.lastError
        L89:
            if (r0 != 0) goto L92
            gnu.text.SourceError r1 = r4.firstError
            r5.next = r1
            r4.firstError = r5
            goto L98
        L92:
            gnu.text.SourceError r1 = r0.next
            r5.next = r1
            r0.next = r5
        L98:
            gnu.text.SourceError r1 = r4.lastError
            if (r0 != r1) goto L9e
            r4.lastError = r5
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.SourceMessages.error(gnu.text.SourceError):void");
    }

    public void error(char severity, String filename, int line, int column, String message) {
        error(new SourceError(severity, filename, line, column, message));
    }

    public void error(char severity, SourceLocator location, String message) {
        error(new SourceError(severity, location, message));
    }

    public void error(char severity, String filename, int line, int column, String message, String code) {
        SourceError err = new SourceError(severity, filename, line, column, message);
        err.code = code;
        error(err);
    }

    public void error(char severity, SourceLocator location, String message, String code) {
        SourceError err = new SourceError(severity, location, message);
        err.code = code;
        error(err);
    }

    public void error(char severity, String message) {
        error(new SourceError(severity, this.current_filename, this.current_line, this.current_column, message));
    }

    public void error(char severity, String message, Throwable exception) {
        SourceError err = new SourceError(severity, this.current_filename, this.current_line, this.current_column, message);
        err.fakeException = exception;
        error(err);
    }

    public void error(char severity, String message, String code) {
        SourceError err = new SourceError(severity, this.current_filename, this.current_line, this.current_column, message);
        err.code = code;
        error(err);
    }

    public void printAll(PrintStream out, int max) {
        for (SourceError err = this.firstError; err != null; err = err.next) {
            max--;
            if (max >= 0) {
                err.println(out);
            } else {
                return;
            }
        }
    }

    public void printAll(PrintWriter out, int max) {
        for (SourceError err = this.firstError; err != null; err = err.next) {
            max--;
            if (max >= 0) {
                err.println(out);
            } else {
                return;
            }
        }
    }

    public String toString(int max) {
        if (this.firstError == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (SourceError err = this.firstError; err != null; err = err.next) {
            max--;
            if (max < 0) {
                break;
            }
            buffer.append(err);
            buffer.append('\n');
        }
        return buffer.toString();
    }

    public boolean checkErrors(PrintWriter out, int max) {
        if (this.firstError == null) {
            return false;
        }
        printAll(out, max);
        this.lastError = null;
        this.firstError = null;
        int saveCount = this.errorCount;
        this.errorCount = 0;
        return saveCount > 0;
    }

    public boolean checkErrors(PrintStream out, int max) {
        if (this.firstError == null) {
            return false;
        }
        printAll(out, max);
        this.lastError = null;
        this.firstError = null;
        int saveCount = this.errorCount;
        this.errorCount = 0;
        return saveCount > 0;
    }

    public final void setSourceLocator(SourceLocator locator) {
        this.locator = locator == this ? null : locator;
    }

    public final SourceLocator swapSourceLocator(SourceLocator locator) {
        SourceLocator save = this.locator;
        this.locator = locator;
        return save;
    }

    public final void setLocation(SourceLocator locator) {
        this.locator = null;
        this.current_line = locator.getLineNumber();
        this.current_column = locator.getColumnNumber();
        this.current_filename = locator.getFileName();
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public String getPublicId() {
        SourceLocator sourceLocator = this.locator;
        if (sourceLocator == null) {
            return null;
        }
        return sourceLocator.getPublicId();
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public String getSystemId() {
        SourceLocator sourceLocator = this.locator;
        return sourceLocator == null ? this.current_filename : sourceLocator.getSystemId();
    }

    @Override // gnu.text.SourceLocator
    public boolean isStableSourceLocation() {
        return false;
    }

    @Override // gnu.text.SourceLocator
    public final String getFileName() {
        return this.current_filename;
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public final int getLineNumber() {
        SourceLocator sourceLocator = this.locator;
        return sourceLocator == null ? this.current_line : sourceLocator.getLineNumber();
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public final int getColumnNumber() {
        SourceLocator sourceLocator = this.locator;
        return sourceLocator == null ? this.current_column : sourceLocator.getColumnNumber();
    }

    public void setFile(String filename) {
        this.current_filename = filename;
    }

    public void setLine(int line) {
        this.current_line = line;
    }

    public void setColumn(int column) {
        this.current_column = column;
    }

    public void setLine(String filename, int line, int column) {
        this.current_filename = filename;
        this.current_line = line;
        this.current_column = column;
    }
}
