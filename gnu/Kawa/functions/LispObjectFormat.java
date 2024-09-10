package gnu.kawa.functions;

import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat.java */
/* loaded from: classes.dex */
class LispObjectFormat extends ReportFormat {
    ReportFormat base;
    int colInc;
    int minPad;
    int minWidth;
    int padChar;
    int where;

    public LispObjectFormat(ReportFormat base, int minWidth, int colInc, int minPad, int padChar, int where) {
        this.base = base;
        this.minWidth = minWidth;
        this.colInc = colInc;
        this.minPad = minPad;
        this.padChar = padChar;
        this.where = where;
    }

    @Override // gnu.text.ReportFormat
    public int format(Object[] args, int start, Writer dst, FieldPosition fpos) throws IOException {
        int start2 = start;
        int minWidth = getParam(this.minWidth, 0, args, start2);
        if (this.minWidth == -1610612736) {
            start2++;
        }
        int colInc = getParam(this.colInc, 1, args, start2);
        if (this.colInc == -1610612736) {
            start2++;
        }
        int minPad = getParam(this.minPad, 0, args, start2);
        if (this.minPad == -1610612736) {
            start2++;
        }
        char padChar = getParam(this.padChar, ' ', args, start2);
        if (this.padChar == -1610612736) {
            start2++;
        }
        return PadFormat.format(this.base, args, start2, dst, padChar, minWidth, colInc, minPad, this.where, fpos);
    }
}
