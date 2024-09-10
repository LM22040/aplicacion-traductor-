package gnu.text;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/* loaded from: classes.dex */
public class RomanIntegerFormat extends NumberFormat {
    static final String codes = "IVXLCDM";
    private static RomanIntegerFormat newRoman;
    private static RomanIntegerFormat oldRoman;
    public boolean oldStyle;

    public RomanIntegerFormat(boolean oldStyle) {
        this.oldStyle = oldStyle;
    }

    public RomanIntegerFormat() {
    }

    public static RomanIntegerFormat getInstance(boolean oldStyle) {
        if (oldStyle) {
            if (oldRoman == null) {
                oldRoman = new RomanIntegerFormat(true);
            }
            return oldRoman;
        }
        if (newRoman == null) {
            newRoman = new RomanIntegerFormat(false);
        }
        return newRoman;
    }

    public static String format(int num, boolean oldStyle) {
        if (num <= 0 || num >= 4999) {
            return Integer.toString(num);
        }
        StringBuffer sbuf = new StringBuffer(20);
        int unit = 1000;
        for (int power = 3; power >= 0; power--) {
            int digit = num / unit;
            num -= digit * unit;
            if (digit != 0) {
                if (!oldStyle && (digit == 4 || digit == 9)) {
                    sbuf.append(codes.charAt(power * 2));
                    sbuf.append(codes.charAt((power * 2) + ((digit + 1) / 5)));
                } else {
                    int rest = digit;
                    if (rest >= 5) {
                        sbuf.append(codes.charAt((power * 2) + 1));
                        rest -= 5;
                    }
                    while (true) {
                        rest--;
                        if (rest >= 0) {
                            sbuf.append(codes.charAt(power * 2));
                        }
                    }
                }
            }
            unit /= 10;
        }
        return sbuf.toString();
    }

    public static String format(int num) {
        return format(num, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    @Override // java.text.NumberFormat
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.StringBuffer format(long r10, java.lang.StringBuffer r12, java.text.FieldPosition r13) {
        /*
            r9 = this;
            r0 = 0
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 <= 0) goto L1a
            boolean r0 = r9.oldStyle
            if (r0 == 0) goto Ld
            r1 = 4999(0x1387, float:7.005E-42)
            goto Lf
        Ld:
            r1 = 3999(0xf9f, float:5.604E-42)
        Lf:
            long r1 = (long) r1
            int r3 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r3 >= 0) goto L1a
            int r1 = (int) r10
            java.lang.String r0 = format(r1, r0)
            goto L1e
        L1a:
            java.lang.String r0 = java.lang.Long.toString(r10)
        L1e:
            if (r13 == 0) goto L43
            r1 = 1
            int r3 = r0.length()
            r4 = r3
        L27:
            int r4 = r4 + (-1)
            if (r4 <= 0) goto L34
            r5 = 10
            long r5 = r5 * r1
            r7 = 9
            long r1 = r5 + r7
            goto L27
        L34:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>(r3)
            java.text.DecimalFormat r5 = new java.text.DecimalFormat
            java.lang.String r6 = "0"
            r5.<init>(r6)
            r5.format(r1, r4, r13)
        L43:
            r12.append(r0)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.RomanIntegerFormat.format(long, java.lang.StringBuffer, java.text.FieldPosition):java.lang.StringBuffer");
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(double num, StringBuffer sbuf, FieldPosition fpos) {
        long inum = (long) num;
        if (inum == num) {
            return format(inum, sbuf, fpos);
        }
        sbuf.append(Double.toString(num));
        return sbuf;
    }

    @Override // java.text.NumberFormat
    public Number parse(String text, ParsePosition status) {
        throw new Error("RomanIntegerFormat.parseObject - not implemented");
    }
}
