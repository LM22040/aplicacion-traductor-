package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.DFloNum;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.Quantity;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import java.math.BigDecimal;
import java.math.BigInteger;
import kawa.standard.Scheme;

/* compiled from: numbers.scm */
/* loaded from: classes.dex */
public class numbers extends ModuleBody {
    public static final numbers $instance;
    static final IntNum Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final IntNum Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final SimpleSymbol Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod abs;
    public static final ModuleMethod acos;
    public static final ModuleMethod angle;
    public static final ModuleMethod asin;
    public static final GenericProc atan = null;
    public static final ModuleMethod bitwise$Mnbit$Mncount;
    public static final ModuleMethod bitwise$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnbit$Mnset$Qu;
    public static final ModuleMethod bitwise$Mncopy$Mnbit;
    public static final ModuleMethod bitwise$Mncopy$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnfirst$Mnbit$Mnset;
    public static final ModuleMethod bitwise$Mnif;
    public static final ModuleMethod bitwise$Mnlength;
    public static final ModuleMethod bitwise$Mnreverse$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnrotate$Mnbit$Mnfield;
    public static final ModuleMethod ceiling;
    public static final ModuleMethod complex$Qu;
    public static final ModuleMethod cos;
    public static final ModuleMethod denominator;
    public static final ModuleMethod div$Mnand$Mnmod;
    public static final ModuleMethod div0$Mnand$Mnmod0;
    public static final ModuleMethod duration;
    public static final ModuleMethod exact;
    public static final ModuleMethod exact$Mn$Grinexact;
    public static final ModuleMethod exact$Qu;
    public static final ModuleMethod exp;
    public static final ModuleMethod floor;
    public static final ModuleMethod gcd;
    public static final ModuleMethod imag$Mnpart;
    public static final ModuleMethod inexact;
    public static final ModuleMethod inexact$Mn$Grexact;
    public static final ModuleMethod inexact$Qu;
    public static final ModuleMethod integer$Qu;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    static final ModuleMethod lambda$Fn4;
    public static final ModuleMethod lcm;
    public static final ModuleMethod log;
    public static final ModuleMethod logcount;
    public static final ModuleMethod logop;
    public static final ModuleMethod logtest;
    public static final ModuleMethod magnitude;
    public static final ModuleMethod make$Mnpolar;
    public static final ModuleMethod make$Mnquantity;
    public static final ModuleMethod make$Mnrectangular;
    public static final ModuleMethod max;
    public static final ModuleMethod min;
    public static final ModuleMethod negative$Qu;
    public static final ModuleMethod number$Mn$Grstring;
    public static final ModuleMethod number$Qu;
    public static final ModuleMethod numerator;
    public static final ModuleMethod positive$Qu;
    public static final ModuleMethod quantity$Mn$Grnumber;
    public static final ModuleMethod quantity$Mn$Grunit;
    public static final ModuleMethod quantity$Qu;
    public static final ModuleMethod rational$Qu;
    public static final ModuleMethod rationalize;
    public static final ModuleMethod real$Mnpart;
    public static final ModuleMethod real$Qu;
    public static final ModuleMethod round;
    public static final ModuleMethod sin;
    public static final GenericProc sqrt = null;
    public static final ModuleMethod string$Mn$Grnumber;
    public static final ModuleMethod tan;
    public static final ModuleMethod truncate;
    public static final ModuleMethod zero$Qu;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("duration").readResolve();
        Lit63 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("make-quantity").readResolve();
        Lit62 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("quantity->unit").readResolve();
        Lit61 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("quantity->number").readResolve();
        Lit60 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("string->number").readResolve();
        Lit59 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("number->string").readResolve();
        Lit58 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("bitwise-reverse-bit-field").readResolve();
        Lit57 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("bitwise-rotate-bit-field").readResolve();
        Lit56 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("bitwise-first-bit-set").readResolve();
        Lit55 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("bitwise-length").readResolve();
        Lit54 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("bitwise-bit-count").readResolve();
        Lit53 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("logcount").readResolve();
        Lit52 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("logtest").readResolve();
        Lit51 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("bitwise-if").readResolve();
        Lit50 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("bitwise-bit-field").readResolve();
        Lit49 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("bitwise-copy-bit-field").readResolve();
        Lit48 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("bitwise-copy-bit").readResolve();
        Lit47 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("bitwise-bit-set?").readResolve();
        Lit46 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("logop").readResolve();
        Lit45 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("inexact->exact").readResolve();
        Lit44 = simpleSymbol20;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("exact->inexact").readResolve();
        Lit43 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("exact").readResolve();
        Lit42 = simpleSymbol22;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("inexact").readResolve();
        Lit41 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol("angle").readResolve();
        Lit40 = simpleSymbol24;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("magnitude").readResolve();
        Lit39 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = (SimpleSymbol) new SimpleSymbol("imag-part").readResolve();
        Lit38 = simpleSymbol26;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("real-part").readResolve();
        Lit37 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = (SimpleSymbol) new SimpleSymbol("make-polar").readResolve();
        Lit36 = simpleSymbol28;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("make-rectangular").readResolve();
        Lit35 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = (SimpleSymbol) new SimpleSymbol("acos").readResolve();
        Lit34 = simpleSymbol30;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("asin").readResolve();
        Lit33 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) new SimpleSymbol("tan").readResolve();
        Lit32 = simpleSymbol32;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("cos").readResolve();
        Lit31 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) new SimpleSymbol("sin").readResolve();
        Lit30 = simpleSymbol34;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("log").readResolve();
        Lit29 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("exp").readResolve();
        Lit28 = simpleSymbol36;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("rationalize").readResolve();
        Lit27 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) new SimpleSymbol("round").readResolve();
        Lit26 = simpleSymbol38;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("truncate").readResolve();
        Lit25 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol("ceiling").readResolve();
        Lit24 = simpleSymbol40;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("floor").readResolve();
        Lit23 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = (SimpleSymbol) new SimpleSymbol("denominator").readResolve();
        Lit22 = simpleSymbol42;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("numerator").readResolve();
        Lit21 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = (SimpleSymbol) new SimpleSymbol("lcm").readResolve();
        Lit20 = simpleSymbol44;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("gcd").readResolve();
        Lit19 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = (SimpleSymbol) new SimpleSymbol("div0-and-mod0").readResolve();
        Lit18 = simpleSymbol46;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("div-and-mod").readResolve();
        Lit17 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = (SimpleSymbol) new SimpleSymbol("abs").readResolve();
        Lit16 = simpleSymbol48;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("min").readResolve();
        Lit15 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = (SimpleSymbol) new SimpleSymbol("max").readResolve();
        Lit14 = simpleSymbol50;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("negative?").readResolve();
        Lit13 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = (SimpleSymbol) new SimpleSymbol("positive?").readResolve();
        Lit12 = simpleSymbol52;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("zero?").readResolve();
        Lit11 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = (SimpleSymbol) new SimpleSymbol("inexact?").readResolve();
        Lit10 = simpleSymbol54;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("exact?").readResolve();
        Lit9 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = (SimpleSymbol) new SimpleSymbol("integer?").readResolve();
        Lit8 = simpleSymbol56;
        SimpleSymbol simpleSymbol57 = (SimpleSymbol) new SimpleSymbol("rational?").readResolve();
        Lit7 = simpleSymbol57;
        SimpleSymbol simpleSymbol58 = (SimpleSymbol) new SimpleSymbol("real?").readResolve();
        Lit6 = simpleSymbol58;
        SimpleSymbol simpleSymbol59 = (SimpleSymbol) new SimpleSymbol("complex?").readResolve();
        Lit5 = simpleSymbol59;
        SimpleSymbol simpleSymbol60 = (SimpleSymbol) new SimpleSymbol("quantity?").readResolve();
        Lit4 = simpleSymbol60;
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) new SimpleSymbol("number?").readResolve();
        Lit3 = simpleSymbol61;
        Lit2 = IntNum.make(1);
        Lit1 = (SimpleSymbol) new SimpleSymbol("signum").readResolve();
        Lit0 = IntNum.make(0);
        numbers numbersVar = new numbers();
        $instance = numbersVar;
        number$Qu = new ModuleMethod(numbersVar, 1, simpleSymbol61, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        quantity$Qu = new ModuleMethod(numbersVar, 2, simpleSymbol60, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        complex$Qu = new ModuleMethod(numbersVar, 3, simpleSymbol59, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        real$Qu = new ModuleMethod(numbersVar, 4, simpleSymbol58, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rational$Qu = new ModuleMethod(numbersVar, 5, simpleSymbol57, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        integer$Qu = new ModuleMethod(numbersVar, 6, simpleSymbol56, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact$Qu = new ModuleMethod(numbersVar, 7, simpleSymbol55, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact$Qu = new ModuleMethod(numbersVar, 8, simpleSymbol54, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        zero$Qu = new ModuleMethod(numbersVar, 9, simpleSymbol53, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        positive$Qu = new ModuleMethod(numbersVar, 10, simpleSymbol52, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        negative$Qu = new ModuleMethod(numbersVar, 11, simpleSymbol51, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        max = new ModuleMethod(numbersVar, 12, simpleSymbol50, -4096);
        min = new ModuleMethod(numbersVar, 13, simpleSymbol49, -4096);
        abs = new ModuleMethod(numbersVar, 14, simpleSymbol48, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        div$Mnand$Mnmod = new ModuleMethod(numbersVar, 15, simpleSymbol47, 8194);
        div0$Mnand$Mnmod0 = new ModuleMethod(numbersVar, 16, simpleSymbol46, 8194);
        gcd = new ModuleMethod(numbersVar, 17, simpleSymbol45, -4096);
        lcm = new ModuleMethod(numbersVar, 18, simpleSymbol44, -4096);
        numerator = new ModuleMethod(numbersVar, 19, simpleSymbol43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        denominator = new ModuleMethod(numbersVar, 20, simpleSymbol42, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        floor = new ModuleMethod(numbersVar, 21, simpleSymbol41, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ceiling = new ModuleMethod(numbersVar, 22, simpleSymbol40, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        truncate = new ModuleMethod(numbersVar, 23, simpleSymbol39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        round = new ModuleMethod(numbersVar, 24, simpleSymbol38, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rationalize = new ModuleMethod(numbersVar, 25, simpleSymbol37, 8194);
        exp = new ModuleMethod(numbersVar, 26, simpleSymbol36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        log = new ModuleMethod(numbersVar, 27, simpleSymbol35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sin = new ModuleMethod(numbersVar, 28, simpleSymbol34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cos = new ModuleMethod(numbersVar, 29, simpleSymbol33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tan = new ModuleMethod(numbersVar, 30, simpleSymbol32, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        asin = new ModuleMethod(numbersVar, 31, simpleSymbol31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        acos = new ModuleMethod(numbersVar, 32, simpleSymbol30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod = new ModuleMethod(numbersVar, 33, null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:146");
        lambda$Fn1 = moduleMethod;
        ModuleMethod moduleMethod2 = new ModuleMethod(numbersVar, 34, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:148");
        lambda$Fn2 = moduleMethod2;
        ModuleMethod moduleMethod3 = new ModuleMethod(numbersVar, 35, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:152");
        lambda$Fn3 = moduleMethod3;
        ModuleMethod moduleMethod4 = new ModuleMethod(numbersVar, 36, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:156");
        lambda$Fn4 = moduleMethod4;
        make$Mnrectangular = new ModuleMethod(numbersVar, 37, simpleSymbol29, 8194);
        make$Mnpolar = new ModuleMethod(numbersVar, 38, simpleSymbol28, 8194);
        real$Mnpart = new ModuleMethod(numbersVar, 39, simpleSymbol27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        imag$Mnpart = new ModuleMethod(numbersVar, 40, simpleSymbol26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        magnitude = new ModuleMethod(numbersVar, 41, simpleSymbol25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        angle = new ModuleMethod(numbersVar, 42, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact = new ModuleMethod(numbersVar, 43, simpleSymbol23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact = new ModuleMethod(numbersVar, 44, simpleSymbol22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact$Mn$Grinexact = new ModuleMethod(numbersVar, 45, simpleSymbol21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact$Mn$Grexact = new ModuleMethod(numbersVar, 46, simpleSymbol20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        logop = new ModuleMethod(numbersVar, 47, simpleSymbol19, 12291);
        bitwise$Mnbit$Mnset$Qu = new ModuleMethod(numbersVar, 48, simpleSymbol18, 8194);
        bitwise$Mncopy$Mnbit = new ModuleMethod(numbersVar, 49, simpleSymbol17, 12291);
        bitwise$Mncopy$Mnbit$Mnfield = new ModuleMethod(numbersVar, 50, simpleSymbol16, 16388);
        bitwise$Mnbit$Mnfield = new ModuleMethod(numbersVar, 51, simpleSymbol15, 12291);
        bitwise$Mnif = new ModuleMethod(numbersVar, 52, simpleSymbol14, 12291);
        logtest = new ModuleMethod(numbersVar, 53, simpleSymbol13, 8194);
        logcount = new ModuleMethod(numbersVar, 54, simpleSymbol12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnbit$Mncount = new ModuleMethod(numbersVar, 55, simpleSymbol11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnlength = new ModuleMethod(numbersVar, 56, simpleSymbol10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnfirst$Mnbit$Mnset = new ModuleMethod(numbersVar, 57, simpleSymbol9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnrotate$Mnbit$Mnfield = new ModuleMethod(numbersVar, 58, simpleSymbol8, 16388);
        bitwise$Mnreverse$Mnbit$Mnfield = new ModuleMethod(numbersVar, 59, simpleSymbol7, 12291);
        number$Mn$Grstring = new ModuleMethod(numbersVar, 60, simpleSymbol6, 8193);
        string$Mn$Grnumber = new ModuleMethod(numbersVar, 62, simpleSymbol5, 8193);
        quantity$Mn$Grnumber = new ModuleMethod(numbersVar, 64, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        quantity$Mn$Grunit = new ModuleMethod(numbersVar, 65, simpleSymbol3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnquantity = new ModuleMethod(numbersVar, 66, simpleSymbol2, 8194);
        duration = new ModuleMethod(numbersVar, 67, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        numbersVar.run();
    }

    public numbers() {
        ModuleInfo.register(this);
    }

    public static CharSequence number$To$String(Number number) {
        return number$To$String(number, 10);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        GenericProc genericProc = new GenericProc("atan");
        atan = genericProc;
        genericProc.setProperties(new Object[]{lambda$Fn1, lambda$Fn2});
        GenericProc genericProc2 = new GenericProc("sqrt");
        sqrt = genericProc2;
        genericProc2.setProperties(new Object[]{lambda$Fn3, lambda$Fn4});
    }

    public static boolean isNumber(Object x) {
        return x instanceof Number;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 5:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 25:
            case 33:
            case 37:
            case 38:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 58:
            case 59:
            case 61:
            case 63:
            case 66:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 14:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 19:
                if (RatNum.asRatNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 20:
                if (RatNum.asRatNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 21:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 22:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 23:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 24:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 27:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 29:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 31:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 32:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 34:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 35:
                if (!(obj instanceof Quantity)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 36:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 39:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 40:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 41:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 42:
                if (!(obj instanceof Complex)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 43:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 44:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 45:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 46:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 54:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 55:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 56:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 57:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 60:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 62:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 64:
                if (!(obj instanceof Quantity)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 65:
                if (!(obj instanceof Quantity)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 67:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
    }

    public static boolean isQuantity(Object x) {
        return x instanceof Quantity;
    }

    public static boolean isComplex(Object x) {
        return x instanceof Complex;
    }

    public static boolean isReal(Object x) {
        return RealNum.asRealNumOrNull(x) != null;
    }

    public static boolean isRational(Object x) {
        return RatNum.asRatNumOrNull(x) != null;
    }

    public static boolean isInteger(Object obj) {
        boolean z = obj instanceof IntNum;
        if (!z) {
            boolean z2 = obj instanceof DFloNum;
            if (z2) {
                z2 = true;
                try {
                    if (Math.IEEEremainder(((DFloNum) obj).doubleValue(), 1.0d) != 0.0d) {
                        z2 = false;
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.math.DFloNum.doubleValue()", 1, obj);
                }
            }
            if (z2) {
                return z2;
            }
            boolean z3 = obj instanceof Number;
            if (!z3) {
                return z3;
            }
            boolean z4 = obj instanceof Long;
            if (z4) {
                return z4;
            }
            boolean z5 = obj instanceof Integer;
            if (z5) {
                return z5;
            }
            boolean z6 = obj instanceof Short;
            return z6 ? z6 : obj instanceof BigInteger;
        }
        return z;
    }

    public static boolean isExact(Object x) {
        boolean x2 = x instanceof Number;
        return x2 ? Arithmetic.isExact((Number) x) : x2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isInexact(Object obj) {
        boolean z = obj instanceof Number;
        return z ? ((Arithmetic.isExact((Number) obj) ? 1 : 0) + 1) & 1 : z;
    }

    public static boolean isZero(Number x) {
        if (x instanceof Numeric) {
            return ((Numeric) x).isZero();
        }
        return x instanceof BigInteger ? Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigInteger) x, Lit1)) != Boolean.FALSE : x instanceof BigDecimal ? Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigDecimal) x, Lit1)) != Boolean.FALSE : x.doubleValue() == 0.0d;
    }

    public static boolean isPositive(RealNum x) {
        return x.sign() > 0;
    }

    public static boolean isNegative(RealNum x) {
        return x.isNegative();
    }

    public static Object max(Object... args) {
        int n = args.length;
        Object obj = args[0];
        try {
            RealNum result = LangObjType.coerceRealNum(obj);
            for (int i = 1; i < n; i++) {
                Object obj2 = args[i];
                try {
                    result = result.max(LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.math.RealNum.max(real)", 2, obj2);
                }
            }
            return result;
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "result", -2, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 12:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 13:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 14:
            case 15:
            case 16:
            default:
                return super.matchN(moduleMethod, objArr, callContext);
            case 17:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 18:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
        }
    }

    public static Object min(Object... args) {
        Object obj = args[0];
        try {
            RealNum result = LangObjType.coerceRealNum(obj);
            for (Object obj2 : args) {
                try {
                    result = result.min(LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.math.RealNum.min(real)", 2, obj2);
                }
            }
            return result;
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "result", -2, obj);
        }
    }

    public static Number abs(Number x) {
        if (x instanceof Numeric) {
            return ((Numeric) x).abs();
        }
        if (Scheme.numGEq.apply2(x, Lit0) != Boolean.FALSE) {
            return x;
        }
        return (Number) AddOp.$Mn.apply1(x);
    }

    public static Object divAndMod(RealNum x, RealNum y) {
        Object apply2 = DivideOp.div.apply2(x, y);
        try {
            RealNum q = LangObjType.coerceRealNum(apply2);
            Object apply22 = AddOp.$Mn.apply2(x, MultiplyOp.$St.apply2(q, y));
            try {
                RealNum r = LangObjType.coerceRealNum(apply22);
                return misc.values(q, r);
            } catch (ClassCastException e) {
                throw new WrongType(e, "r", -2, apply22);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "q", -2, apply2);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 15:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 16:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 25:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 33:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 37:
                if (RealNum.asRealNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 38:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 48:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 53:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 60:
                if (!(obj instanceof Number)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 62:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 66:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Object div0AndMod0(RealNum x, RealNum y) {
        Object apply2 = DivideOp.div0.apply2(x, y);
        try {
            RealNum q = LangObjType.coerceRealNum(apply2);
            Object apply22 = AddOp.$Mn.apply2(x, MultiplyOp.$St.apply2(q, y));
            try {
                RealNum r = LangObjType.coerceRealNum(apply22);
                return misc.values(q, r);
            } catch (ClassCastException e) {
                throw new WrongType(e, "r", -2, apply22);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "q", -2, apply2);
        }
    }

    public static IntNum gcd(IntNum... args) {
        int n = args.length;
        if (n == 0) {
            IntNum result = Lit0;
            return result;
        }
        IntNum result2 = args[0];
        for (int i = 1; i < n; i++) {
            result2 = IntNum.gcd(result2, args[i]);
        }
        return result2;
    }

    public static IntNum lcm(IntNum... args) {
        int n = args.length;
        if (n == 0) {
            IntNum result = Lit2;
            return result;
        }
        IntNum result2 = IntNum.abs(args[0]);
        for (int i = 1; i < n; i++) {
            result2 = IntNum.lcm(result2, args[i]);
        }
        return result2;
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 12:
                return max(objArr);
            case 13:
                return min(objArr);
            case 14:
            case 15:
            case 16:
            default:
                return super.applyN(moduleMethod, objArr);
            case 17:
                int length = objArr.length;
                IntNum[] intNumArr = new IntNum[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return gcd(intNumArr);
                    }
                    Object obj = objArr[length];
                    try {
                        intNumArr[length] = LangObjType.coerceIntNum(obj);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "gcd", 0, obj);
                    }
                }
            case 18:
                int length2 = objArr.length;
                IntNum[] intNumArr2 = new IntNum[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return lcm(intNumArr2);
                    }
                    Object obj2 = objArr[length2];
                    try {
                        intNumArr2[length2] = LangObjType.coerceIntNum(obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lcm", 0, obj2);
                    }
                }
        }
    }

    public static IntNum numerator(RatNum x) {
        return x.numerator();
    }

    public static IntNum denominator(RatNum x) {
        return x.denominator();
    }

    public static RealNum floor(RealNum x) {
        return x.toInt(Numeric.FLOOR);
    }

    public static RealNum ceiling(RealNum x) {
        return x.toInt(Numeric.CEILING);
    }

    public static RealNum truncate(RealNum x) {
        return x.toInt(Numeric.TRUNCATE);
    }

    public static RealNum round(RealNum x) {
        return x.toInt(Numeric.ROUND);
    }

    public static RealNum rationalize(RealNum x, RealNum y) {
        return RatNum.rationalize(LangObjType.coerceRealNum(x.sub(y)), LangObjType.coerceRealNum(x.add(y)));
    }

    public static Complex exp(Complex x) {
        return x.exp();
    }

    public static Complex log(Complex x) {
        return x.log();
    }

    public static double sin(double x) {
        return Math.sin(x);
    }

    public static double cos(double x) {
        return Math.cos(x);
    }

    public static double tan(double x) {
        return Math.tan(x);
    }

    public static double asin(double x) {
        return Math.asin(x);
    }

    public static double acos(double x) {
        return Math.acos(x);
    }

    static double lambda1(double y, double x) {
        return Math.atan2(y, x);
    }

    static double lambda2(double x) {
        return Math.atan(x);
    }

    static Quantity lambda3(Quantity num) {
        return Quantity.make(num.number().sqrt(), num.unit().sqrt());
    }

    static double lambda4(double x) {
        return Math.sqrt(x);
    }

    public static Complex makeRectangular(RealNum x, RealNum y) {
        return Complex.make(x, y);
    }

    public static DComplex makePolar(double x, double y) {
        return Complex.polar(x, y);
    }

    public static RealNum realPart(Complex x) {
        return x.re();
    }

    public static RealNum imagPart(Complex x) {
        return x.im();
    }

    public static Number magnitude(Number x) {
        return abs(x);
    }

    public static RealNum angle(Complex x) {
        return x.angle();
    }

    public static Number inexact(Number num) {
        return Arithmetic.toInexact(num);
    }

    public static Number exact(Number num) {
        return Arithmetic.toExact(num);
    }

    public static IntNum logop(int op, IntNum i, IntNum j) {
        return BitOps.bitOp(op, i, j);
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 47:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                if (IntNum.asIntNumOrNull(obj3) == null) {
                    return -786429;
                }
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 49:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 51:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 52:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                if (IntNum.asIntNumOrNull(obj3) == null) {
                    return -786429;
                }
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 59:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
    }

    public static boolean isBitwiseBitSet(IntNum i, int bitno) {
        return BitOps.bitValue(i, bitno);
    }

    public static IntNum bitwiseCopyBit(IntNum i, int bitno, int new$Mnvalue) {
        return BitOps.setBitValue(i, bitno, new$Mnvalue);
    }

    public static IntNum bitwiseCopyBitField(IntNum to, int start, int end, IntNum from) {
        int mask1 = IntNum.shift(-1, start);
        IntNum mask2 = BitOps.not(IntNum.make(IntNum.shift(-1, end)));
        IntNum mask = BitOps.and(IntNum.make(mask1), mask2);
        IntNum mask22 = bitwiseIf(mask, IntNum.shift(from, start), to);
        return mask22;
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 50:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                if (IntNum.asIntNumOrNull(obj4) == null) {
                    return -786428;
                }
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 58:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
    }

    public static IntNum bitwiseBitField(IntNum i, int start, int end) {
        return BitOps.extract(i, start, end);
    }

    public static IntNum bitwiseIf(IntNum e1, IntNum e2, IntNum e3) {
        return BitOps.ior(BitOps.and(e1, e2), BitOps.and(BitOps.not(e1), e3));
    }

    public static boolean logtest(IntNum i, IntNum j) {
        return BitOps.test(i, j);
    }

    public static int logcount(IntNum i) {
        return BitOps.bitCount(IntNum.compare(i, 0L) >= 0 ? i : BitOps.not(i));
    }

    public static int bitwiseBitCount(IntNum i) {
        if (IntNum.compare(i, 0L) >= 0) {
            return BitOps.bitCount(i);
        }
        return (-1) - BitOps.bitCount(BitOps.not(i));
    }

    public static int bitwiseLength(IntNum i) {
        return i.intLength();
    }

    public static int bitwiseFirstBitSet(IntNum i) {
        return BitOps.lowestBitSet(i);
    }

    public static IntNum bitwiseRotateBitField(IntNum n, int start, int end, int count) {
        int width = end - start;
        if (width <= 0) {
            return n;
        }
        int r = count % width;
        int count2 = r < 0 ? r + width : r;
        IntNum field0 = bitwiseBitField(n, start, end);
        IntNum field1 = IntNum.shift(field0, count2);
        IntNum field2 = IntNum.shift(field0, count2 - width);
        IntNum field = BitOps.ior(field1, field2);
        return bitwiseCopyBitField(n, start, end, field);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        switch (moduleMethod.selector) {
            case 50:
                try {
                    try {
                        try {
                            try {
                                return bitwiseCopyBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue(), LangObjType.coerceIntNum(obj4));
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "bitwise-copy-bit-field", 4, obj4);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "bitwise-copy-bit-field", 3, obj3);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "bitwise-copy-bit-field", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "bitwise-copy-bit-field", 1, obj);
                }
            case 58:
                try {
                    try {
                        try {
                            try {
                                return bitwiseRotateBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue());
                            } catch (ClassCastException e5) {
                                throw new WrongType(e5, "bitwise-rotate-bit-field", 4, obj4);
                            }
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "bitwise-rotate-bit-field", 3, obj3);
                        }
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "bitwise-rotate-bit-field", 2, obj2);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "bitwise-rotate-bit-field", 1, obj);
                }
            default:
                return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
    }

    public static IntNum bitwiseReverseBitField(IntNum n, int start, int end) {
        return BitOps.reverseBits(n, start, end);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 47:
                try {
                    try {
                        try {
                            return logop(((Number) obj).intValue(), LangObjType.coerceIntNum(obj2), LangObjType.coerceIntNum(obj3));
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "logop", 3, obj3);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "logop", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "logop", 1, obj);
                }
            case 49:
                try {
                    try {
                        try {
                            return bitwiseCopyBit(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue());
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "bitwise-copy-bit", 3, obj3);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "bitwise-copy-bit", 2, obj2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "bitwise-copy-bit", 1, obj);
                }
            case 51:
                try {
                    try {
                        try {
                            return bitwiseBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue());
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "bitwise-bit-field", 3, obj3);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "bitwise-bit-field", 2, obj2);
                    }
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "bitwise-bit-field", 1, obj);
                }
            case 52:
                try {
                    try {
                        try {
                            return bitwiseIf(LangObjType.coerceIntNum(obj), LangObjType.coerceIntNum(obj2), LangObjType.coerceIntNum(obj3));
                        } catch (ClassCastException e10) {
                            throw new WrongType(e10, "bitwise-if", 3, obj3);
                        }
                    } catch (ClassCastException e11) {
                        throw new WrongType(e11, "bitwise-if", 2, obj2);
                    }
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "bitwise-if", 1, obj);
                }
            case 59:
                try {
                    try {
                        try {
                            return bitwiseReverseBitField(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue(), ((Number) obj3).intValue());
                        } catch (ClassCastException e13) {
                            throw new WrongType(e13, "bitwise-reverse-bit-field", 3, obj3);
                        }
                    } catch (ClassCastException e14) {
                        throw new WrongType(e14, "bitwise-reverse-bit-field", 2, obj2);
                    }
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "bitwise-reverse-bit-field", 1, obj);
                }
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    public static CharSequence number$To$String(Number arg, int radix) {
        return new FString((CharSequence) Arithmetic.toString(arg, radix));
    }

    public static Object string$To$Number(CharSequence str, int radix) {
        Object result = LispReader.parseNumber(str, radix);
        return result instanceof Numeric ? result : Boolean.FALSE;
    }

    public static Complex quantity$To$Number(Quantity q) {
        q.unit();
        double factor = q.doubleValue();
        if (factor == 1.0d) {
            return q.number();
        }
        return Complex.make(q.reValue(), q.imValue());
    }

    public static Quantity makeQuantity(Object val, Object unit) {
        Unit u;
        if (unit instanceof Unit) {
            try {
                u = (Unit) unit;
            } catch (ClassCastException e) {
                throw new WrongType(e, "u", -2, unit);
            }
        } else {
            u = Unit.lookup(unit == null ? null : unit.toString());
        }
        if (u == null) {
            throw new IllegalArgumentException(Format.formatToString(0, "unknown unit: ~s", unit).toString());
        }
        try {
            return Quantity.make((Complex) val, u);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "gnu.math.Quantity.make(gnu.math.Complex,gnu.math.Unit)", 1, val);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 15:
                try {
                    try {
                        return divAndMod(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "div-and-mod", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "div-and-mod", 1, obj);
                }
            case 16:
                try {
                    try {
                        return div0AndMod0(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "div0-and-mod0", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "div0-and-mod0", 1, obj);
                }
            case 25:
                try {
                    try {
                        return rationalize(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "rationalize", 2, obj2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "rationalize", 1, obj);
                }
            case 33:
                try {
                    try {
                        return Double.valueOf(lambda1(((Number) obj).doubleValue(), ((Number) obj2).doubleValue()));
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "lambda", 2, obj2);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "lambda", 1, obj);
                }
            case 37:
                try {
                    try {
                        return makeRectangular(LangObjType.coerceRealNum(obj), LangObjType.coerceRealNum(obj2));
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, "make-rectangular", 2, obj2);
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "make-rectangular", 1, obj);
                }
            case 38:
                try {
                    try {
                        return makePolar(((Number) obj).doubleValue(), ((Number) obj2).doubleValue());
                    } catch (ClassCastException e11) {
                        throw new WrongType(e11, "make-polar", 2, obj2);
                    }
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "make-polar", 1, obj);
                }
            case 48:
                try {
                    try {
                        return isBitwiseBitSet(LangObjType.coerceIntNum(obj), ((Number) obj2).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e13) {
                        throw new WrongType(e13, "bitwise-bit-set?", 2, obj2);
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "bitwise-bit-set?", 1, obj);
                }
            case 53:
                try {
                    try {
                        return logtest(LangObjType.coerceIntNum(obj), LangObjType.coerceIntNum(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e15) {
                        throw new WrongType(e15, "logtest", 2, obj2);
                    }
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "logtest", 1, obj);
                }
            case 60:
                try {
                    try {
                        return number$To$String((Number) obj, ((Number) obj2).intValue());
                    } catch (ClassCastException e17) {
                        throw new WrongType(e17, "number->string", 2, obj2);
                    }
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "number->string", 1, obj);
                }
            case 62:
                try {
                    try {
                        return string$To$Number((CharSequence) obj, ((Number) obj2).intValue());
                    } catch (ClassCastException e19) {
                        throw new WrongType(e19, "string->number", 2, obj2);
                    }
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "string->number", 1, obj);
                }
            case 66:
                return makeQuantity(obj, obj2);
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static Duration duration(Object duration2) {
        return Duration.parseDuration(duration2 == null ? null : duration2.toString());
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isNumber(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                return isQuantity(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return isComplex(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return isReal(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return isRational(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return isInteger(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return isExact(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return isInexact(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                try {
                    return isZero((Number) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "zero?", 1, obj);
                }
            case 10:
                try {
                    return isPositive(LangObjType.coerceRealNum(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "positive?", 1, obj);
                }
            case 11:
                try {
                    return isNegative(LangObjType.coerceRealNum(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "negative?", 1, obj);
                }
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 25:
            case 33:
            case 37:
            case 38:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 58:
            case 59:
            case 61:
            case 63:
            case 66:
            default:
                return super.apply1(moduleMethod, obj);
            case 14:
                try {
                    return abs((Number) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "abs", 1, obj);
                }
            case 19:
                try {
                    return numerator(LangObjType.coerceRatNum(obj));
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "numerator", 1, obj);
                }
            case 20:
                try {
                    return denominator(LangObjType.coerceRatNum(obj));
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "denominator", 1, obj);
                }
            case 21:
                try {
                    return floor(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "floor", 1, obj);
                }
            case 22:
                try {
                    return ceiling(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "ceiling", 1, obj);
                }
            case 23:
                try {
                    return truncate(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "truncate", 1, obj);
                }
            case 24:
                try {
                    return round(LangObjType.coerceRealNum(obj));
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "round", 1, obj);
                }
            case 26:
                try {
                    return exp((Complex) obj);
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "exp", 1, obj);
                }
            case 27:
                try {
                    return log((Complex) obj);
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "log", 1, obj);
                }
            case 28:
                try {
                    return Double.valueOf(sin(((Number) obj).doubleValue()));
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "sin", 1, obj);
                }
            case 29:
                try {
                    return Double.valueOf(cos(((Number) obj).doubleValue()));
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "cos", 1, obj);
                }
            case 30:
                try {
                    return Double.valueOf(tan(((Number) obj).doubleValue()));
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "tan", 1, obj);
                }
            case 31:
                try {
                    return Double.valueOf(asin(((Number) obj).doubleValue()));
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "asin", 1, obj);
                }
            case 32:
                try {
                    return Double.valueOf(acos(((Number) obj).doubleValue()));
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "acos", 1, obj);
                }
            case 34:
                try {
                    return Double.valueOf(lambda2(((Number) obj).doubleValue()));
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "lambda", 1, obj);
                }
            case 35:
                try {
                    return lambda3((Quantity) obj);
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "lambda", 1, obj);
                }
            case 36:
                try {
                    return Double.valueOf(lambda4(((Number) obj).doubleValue()));
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "lambda", 1, obj);
                }
            case 39:
                try {
                    return realPart((Complex) obj);
                } catch (ClassCastException e21) {
                    throw new WrongType(e21, "real-part", 1, obj);
                }
            case 40:
                try {
                    return imagPart((Complex) obj);
                } catch (ClassCastException e22) {
                    throw new WrongType(e22, "imag-part", 1, obj);
                }
            case 41:
                try {
                    return magnitude((Number) obj);
                } catch (ClassCastException e23) {
                    throw new WrongType(e23, "magnitude", 1, obj);
                }
            case 42:
                try {
                    return angle((Complex) obj);
                } catch (ClassCastException e24) {
                    throw new WrongType(e24, "angle", 1, obj);
                }
            case 43:
                try {
                    return inexact((Number) obj);
                } catch (ClassCastException e25) {
                    throw new WrongType(e25, "inexact", 1, obj);
                }
            case 44:
                try {
                    return exact((Number) obj);
                } catch (ClassCastException e26) {
                    throw new WrongType(e26, "exact", 1, obj);
                }
            case 45:
                try {
                    return Arithmetic.toInexact((Number) obj);
                } catch (ClassCastException e27) {
                    throw new WrongType(e27, "exact->inexact", 1, obj);
                }
            case 46:
                try {
                    return Arithmetic.toExact((Number) obj);
                } catch (ClassCastException e28) {
                    throw new WrongType(e28, "inexact->exact", 1, obj);
                }
            case 54:
                try {
                    return Integer.valueOf(logcount(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e29) {
                    throw new WrongType(e29, "logcount", 1, obj);
                }
            case 55:
                try {
                    return Integer.valueOf(bitwiseBitCount(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e30) {
                    throw new WrongType(e30, "bitwise-bit-count", 1, obj);
                }
            case 56:
                try {
                    return Integer.valueOf(bitwiseLength(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e31) {
                    throw new WrongType(e31, "bitwise-length", 1, obj);
                }
            case 57:
                try {
                    return Integer.valueOf(bitwiseFirstBitSet(LangObjType.coerceIntNum(obj)));
                } catch (ClassCastException e32) {
                    throw new WrongType(e32, "bitwise-first-bit-set", 1, obj);
                }
            case 60:
                try {
                    return number$To$String((Number) obj, 10);
                } catch (ClassCastException e33) {
                    throw new WrongType(e33, "number->string", 1, obj);
                }
            case 62:
                try {
                    return string$To$Number((CharSequence) obj, 10);
                } catch (ClassCastException e34) {
                    throw new WrongType(e34, "string->number", 1, obj);
                }
            case 64:
                try {
                    return quantity$To$Number((Quantity) obj);
                } catch (ClassCastException e35) {
                    throw new WrongType(e35, "quantity->number", 1, obj);
                }
            case 65:
                try {
                    return ((Quantity) obj).unit();
                } catch (ClassCastException e36) {
                    throw new WrongType(e36, "quantity->unit", 1, obj);
                }
            case 67:
                return duration(obj);
        }
    }
}
