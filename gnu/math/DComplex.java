package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: classes.dex */
public class DComplex extends Complex implements Externalizable {
    double imag;
    double real;

    public DComplex() {
    }

    public DComplex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    @Override // gnu.math.Quantity
    public RealNum re() {
        return new DFloNum(this.real);
    }

    @Override // gnu.math.Complex, gnu.math.Quantity, java.lang.Number
    public double doubleValue() {
        return this.real;
    }

    @Override // gnu.math.Quantity
    public RealNum im() {
        return new DFloNum(this.imag);
    }

    @Override // gnu.math.Complex, gnu.math.Quantity
    public double doubleImagValue() {
        return this.imag;
    }

    @Override // gnu.math.Complex, gnu.math.Numeric
    public boolean isExact() {
        return false;
    }

    @Override // gnu.math.Complex, gnu.math.Numeric
    public Complex toExact() {
        return new CComplex(DFloNum.toExact(this.real), DFloNum.toExact(this.imag));
    }

    @Override // gnu.math.Complex, gnu.math.Numeric
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Complex)) {
            return false;
        }
        Complex y = (Complex) obj;
        return y.unit() == Unit.Empty && Double.doubleToLongBits(this.real) == Double.doubleToLongBits(y.reValue()) && Double.doubleToLongBits(this.imag) == Double.doubleToLongBits(y.imValue());
    }

    @Override // gnu.math.Numeric
    public String toString() {
        String reString;
        String imString;
        String prefix = "";
        double d = this.real;
        if (d == Double.POSITIVE_INFINITY) {
            prefix = "#i";
            reString = "1/0";
        } else if (d == Double.NEGATIVE_INFINITY) {
            prefix = "#i";
            reString = "-1/0";
        } else if (Double.isNaN(d)) {
            prefix = "#i";
            reString = "0/0";
        } else {
            reString = Double.toString(this.real);
        }
        if (Double.doubleToLongBits(this.imag) == 0) {
            return prefix + reString;
        }
        double d2 = this.imag;
        if (d2 == Double.POSITIVE_INFINITY) {
            prefix = "#i";
            imString = "+1/0i";
        } else if (d2 == Double.NEGATIVE_INFINITY) {
            prefix = "#i";
            imString = "-1/0i";
        } else if (Double.isNaN(d2)) {
            prefix = "#i";
            imString = "+0/0i";
        } else {
            imString = Double.toString(this.imag) + "i";
            if (imString.charAt(0) != '-') {
                imString = "+" + imString;
            }
        }
        return (Double.doubleToLongBits(this.real) == 0 ? prefix : prefix + reString) + imString;
    }

    @Override // gnu.math.Complex, gnu.math.Quantity, gnu.math.Numeric
    public String toString(int radix) {
        if (radix == 10) {
            return toString();
        }
        return "#d" + toString();
    }

    @Override // gnu.math.Complex, gnu.math.Quantity, gnu.math.Numeric
    public final Numeric neg() {
        return new DComplex(-this.real, -this.imag);
    }

    @Override // gnu.math.Complex, gnu.math.Quantity, gnu.math.Numeric
    public Numeric add(Object y, int k) {
        if (y instanceof Complex) {
            Complex yc = (Complex) y;
            if (yc.dimensions() != Dimensions.Empty) {
                throw new ArithmeticException("units mis-match");
            }
            double d = this.real;
            double d2 = k;
            double reValue = yc.reValue();
            Double.isNaN(d2);
            double d3 = d + (d2 * reValue);
            double d4 = this.imag;
            double d5 = k;
            double imValue = yc.imValue();
            Double.isNaN(d5);
            return new DComplex(d3, d4 + (d5 * imValue));
        }
        return ((Numeric) y).addReversed(this, k);
    }

    @Override // gnu.math.Complex, gnu.math.Quantity, gnu.math.Numeric
    public Numeric mul(Object y) {
        if (y instanceof Complex) {
            Complex yc = (Complex) y;
            if (yc.unit() == Unit.Empty) {
                double y_re = yc.reValue();
                double y_im = yc.imValue();
                double d = this.real;
                double d2 = this.imag;
                return new DComplex((d * y_re) - (d2 * y_im), (d * y_im) + (d2 * y_re));
            }
            return Complex.times((Complex) this, yc);
        }
        return ((Numeric) y).mulReversed(this);
    }

    @Override // gnu.math.Complex, gnu.math.Quantity, gnu.math.Numeric
    public Numeric div(Object y) {
        if (y instanceof Complex) {
            Complex yc = (Complex) y;
            return div(this.real, this.imag, yc.doubleValue(), yc.doubleImagValue());
        }
        return ((Numeric) y).divReversed(this);
    }

    public static DComplex power(double x_re, double x_im, double y_re, double y_im) {
        double h = Math.hypot(x_re, x_im);
        double logr = Math.log(h);
        double t = Math.atan2(x_im, x_re);
        double r = Math.exp((logr * y_re) - (y_im * t));
        return Complex.polar(r, (y_im * logr) + (y_re * t));
    }

    public static Complex log(double x_re, double x_im) {
        double h = Math.hypot(x_re, x_im);
        return make(Math.log(h), Math.atan2(x_im, x_re));
    }

    public static DComplex div(double x_re, double x_im, double y_re, double y_im) {
        double d;
        double nr;
        double ni;
        double ar = Math.abs(y_re);
        double ai = Math.abs(y_im);
        if (ar <= ai) {
            double t = y_re / y_im;
            d = y_im * ((t * t) + 1.0d);
            nr = (x_re * t) + x_im;
            ni = (x_im * t) - x_re;
        } else {
            double t2 = y_im / y_re;
            d = y_re * ((t2 * t2) + 1.0d);
            nr = x_re + (x_im * t2);
            ni = x_im - (x_re * t2);
        }
        double ar2 = ni / d;
        return new DComplex(nr / d, ar2);
    }

    public static Complex sqrt(double x_re, double x_im) {
        double ni;
        double nr;
        double r = Math.hypot(x_re, x_im);
        if (r == 0.0d) {
            ni = r;
            nr = r;
        } else if (x_re > 0.0d) {
            double nr2 = Math.sqrt((r + x_re) * 0.5d);
            ni = (x_im / nr2) / 2.0d;
            nr = nr2;
        } else {
            double ni2 = Math.sqrt((r - x_re) * 0.5d);
            if (x_im >= 0.0d) {
                ni = ni2;
            } else {
                ni = -ni2;
            }
            nr = (x_im / ni) / 2.0d;
        }
        return new DComplex(nr, ni);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(this.real);
        out.writeDouble(this.imag);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.real = in.readDouble();
        this.imag = in.readDouble();
    }
}
