package gnu.math;

/* loaded from: classes.dex */
public abstract class Quantity extends Numeric {
    public abstract Complex number();

    public Unit unit() {
        return Unit.Empty;
    }

    public Dimensions dimensions() {
        return unit().dimensions();
    }

    public RealNum re() {
        return number().re();
    }

    public RealNum im() {
        return number().im();
    }

    public final double reValue() {
        return doubleValue();
    }

    public final double imValue() {
        return doubleImagValue();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return unit().doubleValue() * re().doubleValue();
    }

    public double doubleImagValue() {
        return unit().doubleValue() * im().doubleValue();
    }

    public static Quantity make(Complex x, Unit u) {
        if (u == Unit.Empty) {
            return x;
        }
        if (x instanceof DFloNum) {
            return new DQuantity(x.doubleValue(), u);
        }
        return new CQuantity(x, u);
    }

    public static Quantity make(RealNum re, RealNum im, Unit unit) {
        if (unit == Unit.Empty) {
            return Complex.make(re, im);
        }
        if (im.isZero() && (!re.isExact() || !im.isExact())) {
            return new DQuantity(re.doubleValue(), unit);
        }
        return new CQuantity(re, im, unit);
    }

    public static Quantity make(double re, double im, Unit unit) {
        if (unit == Unit.Empty) {
            return Complex.make(re, im);
        }
        if (im == 0.0d) {
            return new DQuantity(re, unit);
        }
        return new CQuantity(new DFloNum(re), new DFloNum(im), unit);
    }

    @Override // gnu.math.Numeric
    public Numeric neg() {
        return make((Complex) number().neg(), unit());
    }

    @Override // gnu.math.Numeric
    public Numeric abs() {
        return make((Complex) number().abs(), unit());
    }

    public static int compare(Quantity x, Quantity y) {
        if (x.unit() == y.unit()) {
            return Complex.compare(x.number(), y.number());
        }
        if (x.dimensions() != y.dimensions() || x.imValue() != y.imValue()) {
            return -3;
        }
        return DFloNum.compare(x.reValue(), y.reValue());
    }

    @Override // gnu.math.Numeric
    public int compare(Object obj) {
        if (!(obj instanceof Quantity)) {
            return ((Numeric) obj).compareReversed(this);
        }
        return compare(this, (Quantity) obj);
    }

    @Override // gnu.math.Numeric
    public int compareReversed(Numeric x) {
        if (x instanceof Quantity) {
            return compare((Quantity) x, this);
        }
        throw new IllegalArgumentException();
    }

    public static Quantity add(Quantity x, Quantity y, int k) {
        if (x.unit() == y.unit()) {
            return make(Complex.add(x.number(), y.number(), k), x.unit());
        }
        if (x.dimensions() != y.dimensions()) {
            throw new ArithmeticException("units mis-match");
        }
        double x_factor = x.unit().doubleValue();
        double reValue = x.reValue();
        double d = k;
        double reValue2 = y.reValue();
        Double.isNaN(d);
        double re = (reValue + (d * reValue2)) / x_factor;
        double imValue = x.imValue();
        double d2 = k;
        double imValue2 = y.imValue();
        Double.isNaN(d2);
        double im = (imValue + (d2 * imValue2)) / x_factor;
        return make(re, im, x.unit());
    }

    @Override // gnu.math.Numeric
    public Numeric add(Object y, int k) {
        if (y instanceof Quantity) {
            return add(this, (Quantity) y, k);
        }
        return ((Numeric) y).addReversed(this, k);
    }

    @Override // gnu.math.Numeric
    public Numeric addReversed(Numeric x, int k) {
        if (x instanceof Quantity) {
            return add((Quantity) x, this, k);
        }
        throw new IllegalArgumentException();
    }

    public static Quantity times(Quantity x, Quantity y) {
        Unit unit = Unit.times(x.unit(), y.unit());
        Numeric num = x.number().mul(y.number());
        return make((Complex) num, unit);
    }

    @Override // gnu.math.Numeric
    public Numeric mul(Object y) {
        if (y instanceof Quantity) {
            return times(this, (Quantity) y);
        }
        return ((Numeric) y).mulReversed(this);
    }

    @Override // gnu.math.Numeric
    public Numeric mulReversed(Numeric x) {
        if (x instanceof Quantity) {
            return times((Quantity) x, this);
        }
        throw new IllegalArgumentException();
    }

    public static Quantity divide(Quantity x, Quantity y) {
        Unit unit = Unit.divide(x.unit(), y.unit());
        Numeric num = x.number().div(y.number());
        return make((Complex) num, unit);
    }

    @Override // gnu.math.Numeric
    public Numeric div(Object y) {
        if (y instanceof Quantity) {
            return divide(this, (Quantity) y);
        }
        return ((Numeric) y).divReversed(this);
    }

    @Override // gnu.math.Numeric
    public Numeric divReversed(Numeric x) {
        if (x instanceof Quantity) {
            return divide((Quantity) x, this);
        }
        throw new IllegalArgumentException();
    }

    @Override // gnu.math.Numeric
    public String toString(int radix) {
        String str = number().toString(radix);
        if (unit() == Unit.Empty) {
            return str;
        }
        return str + unit().toString();
    }
}
