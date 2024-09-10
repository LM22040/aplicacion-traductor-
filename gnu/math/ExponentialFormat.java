package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/* loaded from: classes.dex */
public class ExponentialFormat extends Format {
    static final double LOG10 = Math.log(10.0d);
    public int expDigits;
    public boolean exponentShowSign;
    public boolean general;
    public int intDigits;
    public char overflowChar;
    public char padChar;
    public boolean showPlus;
    public int width;
    public int fracDigits = -1;
    public char exponentChar = 'E';

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean addOne(StringBuffer sbuf, int digStart, int digEnd) {
        int j = digEnd;
        while (j != digStart) {
            j--;
            char ch = sbuf.charAt(j);
            if (ch != '9') {
                sbuf.setCharAt(j, (char) (ch + 1));
                return false;
            }
            sbuf.setCharAt(j, '0');
        }
        sbuf.insert(j, '1');
        return true;
    }

    public StringBuffer format(float value, StringBuffer sbuf, FieldPosition fpos) {
        return format(value, this.fracDigits < 0 ? Float.toString(value) : null, sbuf, fpos);
    }

    public StringBuffer format(double value, StringBuffer sbuf, FieldPosition fpos) {
        return format(value, this.fracDigits < 0 ? Double.toString(value) : null, sbuf, fpos);
    }

    StringBuffer format(double value, String dstr, StringBuffer sbuf, FieldPosition fpos) {
        String dstr2;
        int exponent;
        int digits;
        int scale;
        int ee;
        int d;
        int dot;
        int i;
        int log;
        double value2 = value;
        int k = this.intDigits;
        int d2 = this.fracDigits;
        boolean negative = value2 < 0.0d;
        if (negative) {
            value2 = -value2;
        }
        int oldLen = sbuf.length();
        int signLen = 1;
        if (negative) {
            if (d2 >= 0) {
                sbuf.append('-');
            }
        } else if (this.showPlus) {
            sbuf.append('+');
        } else {
            signLen = 0;
        }
        int digStart = sbuf.length();
        boolean nonFinite = Double.isNaN(value2) || Double.isInfinite(value2);
        if (d2 < 0 || nonFinite) {
            if (dstr != null) {
                dstr2 = dstr;
            } else {
                dstr2 = Double.toString(value2);
            }
            int indexE = dstr2.indexOf(69);
            if (indexE >= 0) {
                sbuf.append(dstr2);
                int indexE2 = indexE + digStart;
                boolean negexp = dstr2.charAt(indexE2 + 1) == '-';
                int exponent2 = 0;
                int i2 = indexE2 + (negexp ? 2 : 1);
                while (true) {
                    double value3 = value2;
                    if (i2 >= sbuf.length()) {
                        break;
                    }
                    exponent2 = (exponent2 * 10) + (sbuf.charAt(i2) - '0');
                    i2++;
                    value2 = value3;
                }
                if (negexp) {
                    exponent2 = -exponent2;
                }
                sbuf.setLength(indexE2);
                exponent = exponent2;
            } else {
                exponent = RealNum.toStringScientific(dstr2, sbuf);
            }
            if (negative) {
                digStart++;
            }
            sbuf.deleteCharAt(digStart + 1);
            digits = sbuf.length() - digStart;
            if (digits > 1 && sbuf.charAt((digStart + digits) - 1) == '0') {
                digits--;
                sbuf.setLength(digStart + digits);
            }
            scale = (digits - exponent) - 1;
        } else {
            int digits2 = d2 + (k > 0 ? 1 : k);
            int log2 = (int) ((Math.log(value2) / LOG10) + 1000.0d);
            if (log2 == Integer.MIN_VALUE) {
                log = 0;
            } else {
                log = log2 - 1000;
            }
            scale = (digits2 - log) - 1;
            RealNum.toScaledInt(value2, scale).format(10, sbuf);
            exponent = (digits2 - 1) - scale;
            digits = digits2;
        }
        int exponent3 = exponent - (k - 1);
        int exponentAbs = exponent3 < 0 ? -exponent3 : exponent3;
        int exponentLen = exponentAbs >= 1000 ? 4 : exponentAbs >= 100 ? 3 : exponentAbs >= 10 ? 2 : 1;
        int i3 = this.expDigits;
        if (i3 > exponentLen) {
            exponentLen = this.expDigits;
        }
        boolean showExponent = true;
        boolean negative2 = this.general;
        int ee2 = !negative2 ? 0 : i3 > 0 ? i3 + 2 : 4;
        boolean fracUnspecified = d2 < 0;
        if (negative2 || fracUnspecified) {
            int d3 = digits - scale;
            if (!fracUnspecified) {
                ee = ee2;
                d = d2;
            } else {
                ee = ee2;
                d = d3 < 7 ? d3 : 7;
                if (digits > d) {
                    d = digits;
                }
            }
            int d4 = d - d3;
            if (negative2 && d3 >= 0 && d4 >= 0) {
                digits = d;
                k = d3;
                showExponent = false;
                d2 = d;
            } else if (!fracUnspecified) {
                d2 = d;
            } else {
                int i4 = this.width;
                if (i4 <= 0) {
                    digits = d;
                } else {
                    int avail = ((i4 - signLen) - exponentLen) - 3;
                    digits = avail;
                    if (k < 0) {
                        digits -= k;
                    }
                    if (digits > d) {
                        digits = d;
                    }
                }
                if (digits > 0) {
                    d2 = d;
                } else {
                    digits = 1;
                    d2 = d;
                }
            }
        } else {
            ee = ee2;
        }
        int digEnd = digStart + digits;
        while (sbuf.length() < digEnd) {
            sbuf.append('0');
        }
        char nextDigit = digEnd == sbuf.length() ? '0' : sbuf.charAt(digEnd);
        boolean addOne = nextDigit >= '5';
        if (addOne && addOne(sbuf, digStart, digEnd)) {
            scale++;
        }
        int length = scale - (sbuf.length() - digEnd);
        sbuf.setLength(digEnd);
        int dot2 = digStart;
        if (k < 0) {
            int j = k;
            while (true) {
                j++;
                if (j > 0) {
                    break;
                }
                sbuf.insert(digStart, '0');
                addOne = addOne;
            }
            dot = dot2;
        } else {
            while (digStart + k > digEnd) {
                sbuf.append('0');
                digEnd++;
            }
            dot = dot2 + k;
        }
        if (nonFinite) {
            showExponent = false;
        } else {
            sbuf.insert(dot, '.');
        }
        if (showExponent) {
            sbuf.append(this.exponentChar);
            if (this.exponentShowSign || exponent3 < 0) {
                sbuf.append(exponent3 >= 0 ? '+' : '-');
            }
            int i5 = sbuf.length();
            sbuf.append(exponentAbs);
            int newLen = sbuf.length();
            int j2 = this.expDigits - (newLen - i5);
            if (j2 > 0) {
                int i6 = newLen + j2;
                while (true) {
                    int j3 = j2 - 1;
                    if (j3 < 0) {
                        break;
                    }
                    sbuf.insert(i5, '0');
                    j2 = j3;
                }
            }
        } else {
            exponentLen = 0;
        }
        int newLen2 = sbuf.length();
        int used = newLen2 - oldLen;
        int newLen3 = this.width;
        int i7 = newLen3 - used;
        if (fracUnspecified) {
            int used2 = dot + 1;
            if ((used2 == sbuf.length() || sbuf.charAt(dot + 1) == this.exponentChar) && (this.width <= 0 || i7 > 0)) {
                i7--;
                sbuf.insert(dot + 1, '0');
            }
        }
        if ((i7 >= 0 || this.width <= 0) && (!showExponent || exponentLen <= (i = this.expDigits) || i <= 0 || this.overflowChar == 0)) {
            if (k <= 0 && (i7 > 0 || this.width <= 0)) {
                sbuf.insert(digStart, '0');
                i7--;
            }
            if (!showExponent && this.width > 0) {
                int ee3 = ee;
                while (true) {
                    ee3--;
                    if (ee3 < 0) {
                        break;
                    }
                    sbuf.append(' ');
                    i7--;
                }
            }
            while (true) {
                i7--;
                if (i7 < 0) {
                    break;
                }
                sbuf.insert(oldLen, this.padChar);
            }
        } else if (this.overflowChar != 0) {
            sbuf.setLength(oldLen);
            int i8 = this.width;
            while (true) {
                i8--;
                if (i8 < 0) {
                    break;
                }
                sbuf.append(this.overflowChar);
            }
        }
        return sbuf;
    }

    public StringBuffer format(long num, StringBuffer sbuf, FieldPosition fpos) {
        return format(num, sbuf, fpos);
    }

    @Override // java.text.Format
    public StringBuffer format(Object num, StringBuffer sbuf, FieldPosition fpos) {
        return format(((RealNum) num).doubleValue(), sbuf, fpos);
    }

    public Number parse(String text, ParsePosition status) {
        throw new Error("ExponentialFormat.parse - not implemented");
    }

    @Override // java.text.Format
    public Object parseObject(String text, ParsePosition status) {
        throw new Error("ExponentialFormat.parseObject - not implemented");
    }
}
