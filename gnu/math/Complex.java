package gnu.math;

/* loaded from: classes.dex */
public abstract class Complex extends Quantity {
    private static CComplex imMinusOne;
    private static CComplex imOne;

    @Override // gnu.math.Quantity
    public Complex number() {
        return this;
    }

    @Override // gnu.math.Numeric
    public boolean isExact() {
        return re().isExact() && im().isExact();
    }

    @Override // gnu.math.Numeric
    public Complex toExact() {
        RealNum re = re();
        RealNum im = im();
        RatNum xre = re.toExact();
        RatNum xim = im.toExact();
        if (xre == re && xim == im) {
            return this;
        }
        return new CComplex(xre, xim);
    }

    @Override // gnu.math.Numeric
    public Complex toInexact() {
        if (isExact()) {
            return this;
        }
        return new DComplex(re().doubleValue(), im().doubleValue());
    }

    public static CComplex imOne() {
        if (imOne == null) {
            imOne = new CComplex(IntNum.zero(), IntNum.one());
        }
        return imOne;
    }

    public static CComplex imMinusOne() {
        if (imMinusOne == null) {
            imMinusOne = new CComplex(IntNum.zero(), IntNum.minusOne());
        }
        return imMinusOne;
    }

    @Override // gnu.math.Quantity, java.lang.Number
    public double doubleValue() {
        return re().doubleValue();
    }

    @Override // gnu.math.Quantity
    public double doubleImagValue() {
        return im().doubleValue();
    }

    public final double doubleRealValue() {
        return doubleValue();
    }

    @Override // gnu.math.Numeric, java.lang.Number
    public long longValue() {
        return re().longValue();
    }

    public static Complex make(RealNum re, RealNum im) {
        if (im.isZero()) {
            return re;
        }
        if (!re.isExact() || !im.isExact()) {
            return new DComplex(re.doubleValue(), im.doubleValue());
        }
        return new CComplex(re, im);
    }

    public static Complex make(double re, double im) {
        if (im == 0.0d) {
            return new DFloNum(re);
        }
        return new DComplex(re, im);
    }

    public static DComplex polar(double r, double t) {
        return new DComplex(Math.cos(t) * r, Math.sin(t) * r);
    }

    public static DComplex polar(RealNum r, RealNum t) {
        return polar(r.doubleValue(), t.doubleValue());
    }

    public static Complex power(Complex x, Complex y) {
        if (y instanceof IntNum) {
            return (Complex) x.power((IntNum) y);
        }
        double x_re = x.doubleRealValue();
        double x_im = x.doubleImagValue();
        double y_re = y.doubleRealValue();
        double y_im = y.doubleImagValue();
        if (x_im == 0.0d && y_im == 0.0d && (x_re >= 0.0d || Double.isInfinite(x_re) || Double.isNaN(x_re))) {
            return new DFloNum(Math.pow(x_re, y_re));
        }
        return DComplex.power(x_re, x_im, y_re, y_im);
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric abs() {
        return new DFloNum(Math.hypot(doubleRealValue(), doubleImagValue()));
    }

    public RealNum angle() {
        return new DFloNum(Math.atan2(doubleImagValue(), doubleRealValue()));
    }

    public static boolean equals(Complex x, Complex y) {
        return x.re().equals(y.re()) && x.im().equals(x.im());
    }

    @Override // gnu.math.Numeric
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Complex)) {
            return false;
        }
        return equals(this, (Complex) obj);
    }

    public static int compare(Complex x, Complex y) {
        int code = x.im().compare(y.im());
        if (code != 0) {
            return code;
        }
        return x.re().compare(y.re());
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public int compare(Object obj) {
        if (!(obj instanceof Complex)) {
            return ((Numeric) obj).compareReversed(this);
        }
        return compare(this, (Complex) obj);
    }

    @Override // gnu.math.Numeric
    public boolean isZero() {
        return re().isZero() && im().isZero();
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public String toString(int radix) {
        if (im().isZero()) {
            return re().toString(radix);
        }
        String imString = im().toString(radix) + "i";
        if (imString.charAt(0) != '-') {
            imString = "+" + imString;
        }
        if (re().isZero()) {
            return imString;
        }
        return re().toString(radix) + imString;
    }

    public static Complex neg(Complex x) {
        return make(x.re().rneg(), x.im().rneg());
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric neg() {
        return neg(this);
    }

    public static Complex add(Complex x, Complex y, int k) {
        return make(RealNum.add(x.re(), y.re(), k), RealNum.add(x.im(), y.im(), k));
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric add(Object y, int k) {
        if (y instanceof Complex) {
            return add(this, (Complex) y, k);
        }
        return ((Numeric) y).addReversed(this, k);
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric addReversed(Numeric x, int k) {
        if (x instanceof Complex) {
            return add((Complex) x, this, k);
        }
        throw new IllegalArgumentException();
    }

    public static Complex times(Complex x, Complex y) {
        RealNum x_re = x.re();
        RealNum x_im = x.im();
        RealNum y_re = y.re();
        RealNum y_im = y.im();
        return make(RealNum.add(RealNum.times(x_re, y_re), RealNum.times(x_im, y_im), -1), RealNum.add(RealNum.times(x_re, y_im), RealNum.times(x_im, y_re), 1));
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric mul(Object y) {
        if (y instanceof Complex) {
            return times(this, (Complex) y);
        }
        return ((Numeric) y).mulReversed(this);
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric mulReversed(Numeric x) {
        if (x instanceof Complex) {
            return times((Complex) x, this);
        }
        throw new IllegalArgumentException();
    }

    public static Complex divide(Complex x, Complex y) {
        if (!x.isExact() || !y.isExact()) {
            return DComplex.div(x.doubleRealValue(), x.doubleImagValue(), y.doubleRealValue(), y.doubleImagValue());
        }
        RealNum x_re = x.re();
        RealNum x_im = x.im();
        RealNum y_re = y.re();
        RealNum y_im = y.im();
        RealNum q = RealNum.add(RealNum.times(y_re, y_re), RealNum.times(y_im, y_im), 1);
        RealNum n = RealNum.add(RealNum.times(x_re, y_re), RealNum.times(x_im, y_im), 1);
        RealNum d = RealNum.add(RealNum.times(x_im, y_re), RealNum.times(x_re, y_im), -1);
        return make(RealNum.divide(n, q), RealNum.divide(d, q));
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric div(Object y) {
        if (y instanceof Complex) {
            return divide(this, (Complex) y);
        }
        return ((Numeric) y).divReversed(this);
    }

    @Override // gnu.math.Quantity, gnu.math.Numeric
    public Numeric divReversed(Numeric x) {
        if (x instanceof Complex) {
            return divide((Complex) x, this);
        }
        throw new IllegalArgumentException();
    }

    public Complex exp() {
        return polar(Math.exp(doubleRealValue()), doubleImagValue());
    }

    public Complex log() {
        return DComplex.log(doubleRealValue(), doubleImagValue());
    }

    public Complex sqrt() {
        return DComplex.sqrt(doubleRealValue(), doubleImagValue());
    }
}
