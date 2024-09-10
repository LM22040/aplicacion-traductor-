package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat.java */
/* loaded from: classes.dex */
class LispEscapeFormat extends ReportFormat {
    public static final int ESCAPE_ALL = 242;
    public static final int ESCAPE_NORMAL = 241;
    public static final LispEscapeFormat alwaysTerminate = new LispEscapeFormat(0, -1073741824);
    boolean escapeAll;
    int param1;
    int param2;
    int param3;

    public LispEscapeFormat(int param1, int param2) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = -1073741824;
    }

    public LispEscapeFormat(int param1, int param2, int param3) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    static Numeric getParam(int param, Object[] args, int start) {
        if (param == -1342177280) {
            return IntNum.make(args.length - start);
        }
        if (param == -1610612736) {
            Object arg = args[start];
            if (arg instanceof Numeric) {
                return (Numeric) arg;
            }
            if (arg instanceof Number) {
                if ((arg instanceof Float) || (arg instanceof Double)) {
                    return new DFloNum(((Number) arg).doubleValue());
                }
                return IntNum.make(((Number) arg).longValue());
            }
            if (arg instanceof Char) {
                return new IntNum(((Char) arg).intValue());
            }
            if (arg instanceof Character) {
                return new IntNum(((Character) arg).charValue());
            }
            return new DFloNum(Double.NaN);
        }
        return IntNum.make(param);
    }

    @Override // gnu.text.ReportFormat
    public int format(Object[] args, int start, Writer dst, FieldPosition fpos) throws IOException {
        boolean do_terminate;
        int i = this.param1;
        if (i == -1073741824) {
            do_terminate = start == args.length;
        } else if (this.param2 == -1073741824 && i == 0) {
            do_terminate = true;
        } else {
            Numeric param = getParam(i, args, start);
            if (this.param1 == -1610612736) {
                start++;
            }
            int i2 = this.param2;
            if (i2 == -1073741824) {
                do_terminate = param.isZero();
            } else {
                Numeric param2 = getParam(i2, args, start);
                if (this.param2 == -1610612736) {
                    start++;
                }
                int i3 = this.param3;
                if (i3 == -1073741824) {
                    do_terminate = param.equals(param2);
                } else {
                    Numeric param3 = getParam(i3, args, start);
                    if (this.param3 == -1610612736) {
                        start++;
                    }
                    do_terminate = param2.geq(param) && param3.geq(param2);
                }
            }
        }
        return result(do_terminate ? this.escapeAll ? ESCAPE_ALL : ESCAPE_NORMAL : 0, start);
    }
}
