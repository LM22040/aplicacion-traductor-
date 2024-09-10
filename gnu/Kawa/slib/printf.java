package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.runtime.Component;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.append;

/* compiled from: printf.scm */
/* loaded from: classes2.dex */
public class printf extends ModuleBody {
    public static final printf $instance;
    static final IntNum Lit0;
    static final IntNum Lit1;
    static final PairWithPosition Lit10;
    static final Char Lit11;
    static final Char Lit12;
    static final Char Lit13;
    static final IntNum Lit14;
    static final IntNum Lit15;
    static final IntNum Lit16;
    static final IntNum Lit17;
    static final Char Lit18;
    static final Char Lit19;
    static final PairWithPosition Lit2;
    static final Char Lit20;
    static final Char Lit21;
    static final Char Lit22;
    static final Char Lit23;
    static final Char Lit24;
    static final Char Lit25;
    static final Char Lit26;
    static final Char Lit27;
    static final Char Lit28;
    static final Char Lit29;
    static final Char Lit3;
    static final Char Lit30;
    static final Char Lit31;
    static final Char Lit32;
    static final PairWithPosition Lit33;
    static final SimpleSymbol Lit34;
    static final Char Lit35;
    static final Char Lit36;
    static final Char Lit37;
    static final Char Lit38;
    static final Char Lit39;
    static final Char Lit4;
    static final Char Lit40;
    static final Char Lit41;
    static final Char Lit42;
    static final Char Lit43;
    static final Char Lit44;
    static final IntNum Lit45;
    static final Char Lit46;
    static final Char Lit47;
    static final IntNum Lit48;
    static final Char Lit49;
    static final Char Lit5;
    static final IntNum Lit50;
    static final Char Lit51;
    static final Char Lit52;
    static final Char Lit53;
    static final Char Lit54;
    static final Char Lit55;
    static final Char Lit56;
    static final Char Lit57;
    static final Char Lit58;
    static final IntNum Lit59;
    static final Char Lit6;
    static final IntNum Lit60;
    static final IntNum Lit61;
    static final FVector Lit62;
    static final PairWithPosition Lit63;
    static final SimpleSymbol Lit64;
    static final Char Lit65;
    static final Char Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final IntNum Lit7;
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final Char Lit8;
    static final Char Lit9;
    public static final ModuleMethod fprintf;
    public static final ModuleMethod printf;
    public static final ModuleMethod sprintf;
    public static final boolean stdio$Clhex$Mnupper$Mncase$Qu = false;
    public static final ModuleMethod stdio$Cliprintf;
    public static final ModuleMethod stdio$Clparse$Mnfloat;
    public static final ModuleMethod stdio$Clround$Mnstring;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("fprintf").readResolve();
        Lit72 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("stdio:iprintf").readResolve();
        Lit71 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("stdio:round-string").readResolve();
        Lit70 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("stdio:parse-float").readResolve();
        Lit69 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("sprintf").readResolve();
        Lit68 = simpleSymbol5;
        Lit67 = (SimpleSymbol) new SimpleSymbol("pad").readResolve();
        Lit66 = Char.make(42);
        Lit65 = Char.make(63);
        Lit64 = (SimpleSymbol) new SimpleSymbol("format-real").readResolve();
        Lit63 = PairWithPosition.make("i", LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1634315);
        Lit62 = FVector.make("y", "z", "a", "f", "p", "n", "u", "m", "", "k", "M", "G", "T", "P", "E", "Z", "Y");
        Lit61 = IntNum.make(3);
        Lit60 = IntNum.make(-10);
        Lit59 = IntNum.make(6);
        Lit58 = Char.make(75);
        Char make = Char.make(107);
        Lit57 = make;
        Lit56 = Char.make(71);
        Char make2 = Char.make(103);
        Lit55 = make2;
        Char make3 = Char.make(69);
        Lit54 = make3;
        Lit53 = Char.make(66);
        Char make4 = Char.make(98);
        Lit52 = make4;
        Lit51 = Char.make(88);
        Lit50 = IntNum.make(16);
        Char make5 = Char.make(120);
        Lit49 = make5;
        Lit48 = IntNum.make(8);
        Lit47 = Char.make(79);
        Char make6 = Char.make(111);
        Lit46 = make6;
        Lit45 = IntNum.make(10);
        Lit44 = Char.make(85);
        Char make7 = Char.make(117);
        Lit43 = make7;
        Lit42 = Char.make(73);
        Char make8 = Char.make(68);
        Lit41 = make8;
        Lit40 = Char.make(65);
        Char make9 = Char.make(97);
        Lit39 = make9;
        Char make10 = Char.make(83);
        Lit38 = make10;
        Char make11 = Char.make(115);
        Lit37 = make11;
        Lit36 = Char.make(67);
        Char make12 = Char.make(99);
        Lit35 = make12;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("printf").readResolve();
        Lit34 = simpleSymbol6;
        Char make13 = Char.make(100);
        Lit12 = make13;
        Char make14 = Char.make(105);
        Lit3 = make14;
        Char make15 = Char.make(102);
        Lit25 = make15;
        Char make16 = Char.make(101);
        Lit13 = make16;
        Lit33 = PairWithPosition.make(make12, PairWithPosition.make(make11, PairWithPosition.make(make9, PairWithPosition.make(make13, PairWithPosition.make(make14, PairWithPosition.make(make7, PairWithPosition.make(make6, PairWithPosition.make(make5, PairWithPosition.make(make4, PairWithPosition.make(make15, PairWithPosition.make(make16, PairWithPosition.make(make2, PairWithPosition.make(make, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781780), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781776), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781772), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1781768), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777704), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777700), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777696), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777692), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777688), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777684), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777680), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777676), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 1777671);
        Lit32 = Char.make(104);
        Char make17 = Char.make(76);
        Lit31 = make17;
        Char make18 = Char.make(108);
        Lit30 = make18;
        Lit29 = Char.make(32);
        Lit28 = Char.make(37);
        Lit27 = Char.make(12);
        Char make19 = Char.make(70);
        Lit26 = make19;
        Lit24 = Char.make(9);
        Lit23 = Char.make(84);
        Lit22 = Char.make(116);
        Lit21 = Char.make(10);
        Lit20 = Char.make(78);
        Lit19 = Char.make(110);
        Lit18 = Char.make(92);
        Lit17 = IntNum.make(-1);
        Lit16 = IntNum.make(9);
        Lit15 = IntNum.make(5);
        Lit14 = IntNum.make(2);
        Lit11 = Char.make(46);
        Lit10 = PairWithPosition.make(make16, PairWithPosition.make(make11, PairWithPosition.make(make15, PairWithPosition.make(make13, PairWithPosition.make(make18, PairWithPosition.make(make3, PairWithPosition.make(make10, PairWithPosition.make(make19, PairWithPosition.make(make8, PairWithPosition.make(make17, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266284), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266280), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266276), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266272), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266268), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266264), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266260), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266256), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266252), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 266247);
        Lit9 = Char.make(48);
        Lit8 = Char.make(35);
        Lit7 = IntNum.make(1);
        Char make20 = Char.make(43);
        Lit6 = make20;
        Char make21 = Char.make(45);
        Lit5 = make21;
        Lit4 = Char.make(64);
        Lit2 = PairWithPosition.make(make20, PairWithPosition.make(make21, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm", 446498);
        Lit1 = IntNum.make(0);
        Lit0 = IntNum.make(-15);
        printf printfVar = new printf();
        $instance = printfVar;
        stdio$Clparse$Mnfloat = new ModuleMethod(printfVar, 22, simpleSymbol4, 8194);
        stdio$Clround$Mnstring = new ModuleMethod(printfVar, 23, simpleSymbol3, 12291);
        stdio$Cliprintf = new ModuleMethod(printfVar, 24, simpleSymbol2, -4094);
        fprintf = new ModuleMethod(printfVar, 25, simpleSymbol, -4094);
        printf = new ModuleMethod(printfVar, 26, simpleSymbol6, -4095);
        sprintf = new ModuleMethod(printfVar, 27, simpleSymbol5, -4094);
        printfVar.run();
    }

    public printf() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        stdio$Clhex$Mnupper$Mncase$Qu = strings.isString$Eq("-F", numbers.number$To$String(Lit0, 16));
    }

    public static Object stdio$ClParseFloat(Object str, Object proc) {
        frame frameVar = new frame();
        frameVar.str = str;
        frameVar.proc = proc;
        Object obj = frameVar.str;
        try {
            frameVar.n = strings.stringLength((CharSequence) obj);
            return frameVar.lambda4real(Lit1, frameVar.lambda$Fn1);
        } catch (ClassCastException e) {
            throw new WrongType(e, "string-length", 1, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        return moduleMethod.selector == 22 ? stdio$ClParseFloat(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        if (moduleMethod.selector != 22) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.proc = moduleMethod;
        callContext.pc = 2;
        return 0;
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        int n;
        Object proc;
        Object str;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 12, null, 16388);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:106");
            this.lambda$Fn1 = moduleMethod;
        }

        public static Boolean lambda1parseError() {
            return Boolean.FALSE;
        }

        public Object lambda2sign(Object i, Object cont) {
            if (Scheme.numLss.apply2(i, Integer.valueOf(this.n)) != Boolean.FALSE) {
                Object obj = this.str;
                try {
                    try {
                        char c = strings.stringRef((CharSequence) obj, ((Number) i).intValue());
                        Object x = Scheme.isEqv.apply2(Char.make(c), printf.Lit5);
                        if (x == Boolean.FALSE ? Scheme.isEqv.apply2(Char.make(c), printf.Lit6) != Boolean.FALSE : x != Boolean.FALSE) {
                            return Scheme.applyToArgs.apply3(cont, AddOp.$Pl.apply2(i, printf.Lit7), Char.make(c));
                        }
                        return Scheme.applyToArgs.apply3(cont, i, printf.Lit6);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-ref", 2, i);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-ref", 1, obj);
                }
            }
            return Values.empty;
        }

        public Object lambda3digits(Object obj, Object obj2) {
            Object substring;
            Object obj3 = obj;
            while (true) {
                Object apply2 = Scheme.numGEq.apply2(obj3, Integer.valueOf(this.n));
                try {
                    boolean booleanValue = ((Boolean) apply2).booleanValue();
                    if (!booleanValue) {
                        Object obj4 = this.str;
                        try {
                            try {
                                boolean isCharNumeric = unicode.isCharNumeric(Char.make(strings.stringRef((CharSequence) obj4, ((Number) obj3).intValue())));
                                if (!isCharNumeric) {
                                    Char r3 = printf.Lit8;
                                    Object obj5 = this.str;
                                    try {
                                        try {
                                            if (!characters.isChar$Eq(r3, Char.make(strings.stringRef((CharSequence) obj5, ((Number) obj3).intValue())))) {
                                                break;
                                            }
                                            obj3 = AddOp.$Pl.apply2(obj3, printf.Lit7);
                                        } catch (ClassCastException e) {
                                            throw new WrongType(e, "string-ref", 2, obj3);
                                        }
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, "string-ref", 1, obj5);
                                    }
                                } else {
                                    if (!isCharNumeric) {
                                        break;
                                    }
                                    obj3 = AddOp.$Pl.apply2(obj3, printf.Lit7);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-ref", 2, obj3);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-ref", 1, obj4);
                        }
                    } else {
                        if (booleanValue) {
                            break;
                        }
                        obj3 = AddOp.$Pl.apply2(obj3, printf.Lit7);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "x", -2, apply2);
                }
            }
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            if (Scheme.numEqu.apply2(obj, obj3) != Boolean.FALSE) {
                substring = Component.TYPEFACE_DEFAULT;
            } else {
                Object obj6 = this.str;
                try {
                    try {
                        try {
                            substring = strings.substring((CharSequence) obj6, ((Number) obj).intValue(), ((Number) obj3).intValue());
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "substring", 3, obj3);
                        }
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "substring", 2, obj);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "substring", 1, obj6);
                }
            }
            return applyToArgs.apply3(obj2, obj3, substring);
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x00e5, code lost:
        
            return kawa.standard.Scheme.applyToArgs.apply2(r0, r1);
         */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0085  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object lambda4real(java.lang.Object r11, java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 242
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame.lambda4real(java.lang.Object, java.lang.Object):java.lang.Object");
        }

        @Override // gnu.expr.ModuleBody
        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            return moduleMethod.selector == 12 ? lambda5(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }

        Object lambda5(Object i, Object sgn, Object digs, Object ex) {
            frame0 frame0Var = new frame0();
            frame0Var.staticLink = this;
            frame0Var.sgn = sgn;
            frame0Var.digs = digs;
            frame0Var.ex = ex;
            if (Scheme.numEqu.apply2(i, Integer.valueOf(this.n)) != Boolean.FALSE) {
                return Scheme.applyToArgs.apply4(this.proc, frame0Var.sgn, frame0Var.digs, frame0Var.ex);
            }
            Object obj = this.str;
            try {
                try {
                    if (lists.memv(Char.make(strings.stringRef((CharSequence) obj, ((Number) i).intValue())), printf.Lit2) != Boolean.FALSE) {
                        return lambda4real(i, frame0Var.lambda$Fn2);
                    }
                    IsEqv isEqv = Scheme.isEqv;
                    Object obj2 = this.str;
                    try {
                        try {
                            if (isEqv.apply2(Char.make(strings.stringRef((CharSequence) obj2, ((Number) i).intValue())), printf.Lit4) != Boolean.FALSE) {
                                Object obj3 = this.str;
                                try {
                                    frame0Var.num = numbers.string$To$Number((CharSequence) obj3, 10);
                                    if (frame0Var.num != Boolean.FALSE) {
                                        Object obj4 = frame0Var.num;
                                        try {
                                            return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.realPart((Complex) obj4), 10), frame0Var.lambda$Fn3);
                                        } catch (ClassCastException e) {
                                            throw new WrongType(e, "real-part", 1, obj4);
                                        }
                                    }
                                    return lambda1parseError();
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "string->number", 1, obj3);
                                }
                            }
                            return Boolean.FALSE;
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "string-ref", 2, i);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-ref", 1, obj2);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "string-ref", 2, i);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-ref", 1, obj);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            if (moduleMethod.selector != 12) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame6 extends ModuleBody {
        Object cont;
        final ModuleMethod lambda$Fn11;
        frame staticLink;

        public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:67");
            this.lambda$Fn11 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 5 ? lambda15(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda15(Object i, Object sgn) {
            frame7 frame7Var = new frame7();
            frame7Var.staticLink = this;
            frame7Var.sgn = sgn;
            return this.staticLink.lambda3digits(i, frame7Var.lambda$Fn12);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 5) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        Object sgn;
        frame6 staticLink;

        public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:69");
            this.lambda$Fn12 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 4 ? lambda16(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda16(Object i, Object digs) {
            Object string$To$Number;
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object obj = this.staticLink.cont;
            Char r2 = printf.Lit5;
            Object obj2 = this.sgn;
            try {
                if (characters.isChar$Eq(r2, (Char) obj2)) {
                    try {
                        string$To$Number = AddOp.$Mn.apply1(numbers.string$To$Number((CharSequence) digs, 10));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string->number", 1, digs);
                    }
                } else {
                    try {
                        string$To$Number = numbers.string$To$Number((CharSequence) digs, 10);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string->number", 1, digs);
                    }
                }
                return applyToArgs.apply3(obj, i, string$To$Number);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "char=?", 2, obj2);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 4) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame2 extends ModuleBody {
        Object cont;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        frame staticLink;

        public frame2() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:81");
            this.lambda$Fn6 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 11, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:78");
            this.lambda$Fn5 = moduleMethod2;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 11 ? lambda9(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda9(Object i) {
            return this.staticLink.lambda2sign(i, this.lambda$Fn6);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 11) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 10 ? lambda10(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda10(Object i, Object sgn) {
            frame3 frame3Var = new frame3();
            frame3Var.staticLink = this;
            frame3Var.sgn = sgn;
            return this.staticLink.lambda3digits(i, frame3Var.lambda$Fn7);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn7;
        Object sgn;
        frame2 staticLink;

        public frame3() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:84");
            this.lambda$Fn7 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 9 ? lambda11(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0047, code lost:
        
            if (kawa.lib.characters.isChar$Eq(r4, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r5, ((java.lang.Number) r8).intValue()))) != false) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0071, code lost:
        
            return kawa.standard.Scheme.applyToArgs.apply2(r1, r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
        
            return kawa.standard.Scheme.applyToArgs.apply2(r1, gnu.kawa.functions.AddOp.$Pl.apply2(r8, gnu.kawa.slib.printf.Lit7));
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x005a, code lost:
        
            if (r3 != false) goto L19;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        java.lang.Object lambda11(java.lang.Object r8, java.lang.Object r9) {
            /*
                r7 = this;
                java.lang.String r0 = "string-ref"
                gnu.kawa.slib.printf$frame4 r1 = new gnu.kawa.slib.printf$frame4
                r1.<init>()
                r1.staticLink = r7
                r1.idigs = r9
                gnu.expr.ModuleMethod r1 = r1.lambda$Fn8
                r2 = r8
                gnu.kawa.functions.NumberCompare r3 = kawa.standard.Scheme.numLss
                gnu.kawa.slib.printf$frame2 r4 = r7.staticLink
                gnu.kawa.slib.printf$frame r4 = r4.staticLink
                int r4 = r4.n
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                java.lang.Object r3 = r3.apply2(r2, r4)
                r4 = r3
                java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch: java.lang.ClassCastException -> L72
                boolean r3 = r4.booleanValue()     // Catch: java.lang.ClassCastException -> L72
                r4 = 0
                if (r3 == 0) goto L5a
                gnu.text.Char r4 = gnu.kawa.slib.printf.Lit11
                gnu.kawa.slib.printf$frame2 r5 = r7.staticLink
                gnu.kawa.slib.printf$frame r5 = r5.staticLink
                java.lang.Object r5 = r5.str
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch: java.lang.ClassCastException -> L52
                r6 = r2
                java.lang.Number r6 = (java.lang.Number) r6     // Catch: java.lang.ClassCastException -> L4a
                int r0 = r6.intValue()     // Catch: java.lang.ClassCastException -> L4a
                char r0 = kawa.lib.strings.stringRef(r5, r0)
                gnu.text.Char r0 = gnu.text.Char.make(r0)
                boolean r0 = kawa.lib.characters.isChar$Eq(r4, r0)
                if (r0 == 0) goto L6b
                goto L5c
            L4a:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                r1 = 2
                r9.<init>(r8, r0, r1, r2)
                throw r9
            L52:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                r1 = 1
                r9.<init>(r8, r0, r1, r5)
                throw r9
            L5a:
                if (r3 == 0) goto L6b
            L5c:
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                gnu.kawa.functions.AddOp r3 = gnu.kawa.functions.AddOp.$Pl
                gnu.math.IntNum r4 = gnu.kawa.slib.printf.Lit7
                java.lang.Object r3 = r3.apply2(r2, r4)
                java.lang.Object r0 = r0.apply2(r1, r3)
                goto L71
            L6b:
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                java.lang.Object r0 = r0.apply2(r1, r2)
            L71:
                return r0
            L72:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                java.lang.String r0 = "x"
                r1 = -2
                r9.<init>(r8, r0, r1, r3)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame3.lambda11(java.lang.Object, java.lang.Object):java.lang.Object");
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 9) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame4 extends ModuleBody {
        Object idigs;
        final ModuleMethod lambda$Fn8;
        final ModuleMethod lambda$Fn9;
        frame3 staticLink;

        public frame4() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 7, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:90");
            this.lambda$Fn9 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 8, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:87");
            this.lambda$Fn8 = moduleMethod2;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 8 ? lambda12(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda12(Object i) {
            return this.staticLink.staticLink.staticLink.lambda3digits(i, this.lambda$Fn9);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 8) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 7 ? lambda13(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda13(Object i, Object fdigs) {
            frame5 frame5Var = new frame5();
            frame5Var.staticLink = this;
            frame5Var.fdigs = fdigs;
            Object cont = frame5Var.lambda$Fn10;
            frame closureEnv = this.staticLink.staticLink.staticLink;
            frame6 frame6Var = new frame6();
            frame6Var.staticLink = closureEnv;
            frame6Var.cont = cont;
            if (Scheme.numGEq.apply2(i, Integer.valueOf(this.staticLink.staticLink.staticLink.n)) != Boolean.FALSE) {
                return Scheme.applyToArgs.apply3(frame6Var.cont, i, printf.Lit1);
            }
            Object obj = this.staticLink.staticLink.staticLink.str;
            try {
                try {
                    if (lists.memv(Char.make(strings.stringRef((CharSequence) obj, ((Number) i).intValue())), printf.Lit10) != Boolean.FALSE) {
                        return this.staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(i, printf.Lit7), frame6Var.lambda$Fn11);
                    }
                    return Scheme.applyToArgs.apply3(frame6Var.cont, i, printf.Lit1);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, i);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 7) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame5 extends ModuleBody {
        Object fdigs;
        final ModuleMethod lambda$Fn10;
        frame4 staticLink;

        public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:92");
            this.lambda$Fn10 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 6 ? lambda14(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda14(Object i, Object ex) {
            FString digs = strings.stringAppend(Component.TYPEFACE_DEFAULT, this.staticLink.idigs, this.fdigs);
            int ndigs = strings.stringLength(digs);
            Object j = printf.Lit7;
            AddOp addOp = AddOp.$Pl;
            Object obj = this.staticLink.idigs;
            try {
                Object ex2 = addOp.apply2(ex, Integer.valueOf(strings.stringLength((CharSequence) obj)));
                while (Scheme.numGEq.apply2(j, Integer.valueOf(ndigs)) == Boolean.FALSE) {
                    try {
                        if (characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef(digs, ((Number) j).intValue())))) {
                            j = AddOp.$Pl.apply2(j, printf.Lit7);
                            ex2 = AddOp.$Mn.apply2(ex2, printf.Lit7);
                        } else {
                            ApplyToArgs applyToArgs = Scheme.applyToArgs;
                            Object[] objArr = new Object[5];
                            objArr[0] = this.staticLink.staticLink.staticLink.cont;
                            objArr[1] = i;
                            objArr[2] = this.staticLink.staticLink.sgn;
                            Object apply2 = AddOp.$Mn.apply2(j, printf.Lit7);
                            try {
                                objArr[3] = strings.substring(digs, ((Number) apply2).intValue(), ndigs);
                                objArr[4] = ex2;
                                return applyToArgs.applyN(objArr);
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 2, apply2);
                            }
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-ref", 2, j);
                    }
                }
                return Scheme.applyToArgs.applyN(new Object[]{this.staticLink.staticLink.staticLink.cont, i, this.staticLink.staticLink.sgn, Component.TYPEFACE_DEFAULT, printf.Lit7});
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "string-length", 1, obj);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 6) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame0 extends ModuleBody {
        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        Object num;
        Object sgn;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 16388);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:111");
            this.lambda$Fn2 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 3, null, 12291);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:123");
            this.lambda$Fn3 = moduleMethod2;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            return moduleMethod.selector == 2 ? lambda6(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
        
            if (kawa.lib.rnrs.unicode.isCharCi$Eq(r4, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r5, ((java.lang.Number) r8).intValue()))) != false) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0076, code lost:
        
            return gnu.kawa.slib.printf.frame.lambda1parseError();
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
        
            return kawa.standard.Scheme.applyToArgs.applyN(new java.lang.Object[]{r7.staticLink.proc, r7.sgn, r7.digs, r7.ex, r9, r10, r11});
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        
            if (r1 != false) goto L19;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        java.lang.Object lambda6(java.lang.Object r8, java.lang.Object r9, java.lang.Object r10, java.lang.Object r11) {
            /*
                r7 = this;
                java.lang.String r0 = "string-ref"
                gnu.kawa.functions.NumberCompare r1 = kawa.standard.Scheme.numEqu
                gnu.kawa.slib.printf$frame r2 = r7.staticLink
                int r2 = r2.n
                r3 = 1
                int r2 = r2 - r3
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                java.lang.Object r1 = r1.apply2(r8, r2)
                r2 = r1
                java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.ClassCastException -> L77
                boolean r1 = r2.booleanValue()     // Catch: java.lang.ClassCastException -> L77
                r2 = 0
                r2 = 2
                if (r1 == 0) goto L49
                gnu.text.Char r4 = gnu.kawa.slib.printf.Lit3
                gnu.kawa.slib.printf$frame r5 = r7.staticLink
                java.lang.Object r5 = r5.str
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch: java.lang.ClassCastException -> L42
                r6 = r8
                java.lang.Number r6 = (java.lang.Number) r6     // Catch: java.lang.ClassCastException -> L3b
                int r0 = r6.intValue()     // Catch: java.lang.ClassCastException -> L3b
                char r0 = kawa.lib.strings.stringRef(r5, r0)
                gnu.text.Char r0 = gnu.text.Char.make(r0)
                boolean r0 = kawa.lib.rnrs.unicode.isCharCi$Eq(r4, r0)
                if (r0 == 0) goto L72
                goto L4b
            L3b:
                r9 = move-exception
                gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
                r10.<init>(r9, r0, r2, r8)
                throw r10
            L42:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                r9.<init>(r8, r0, r3, r5)
                throw r9
            L49:
                if (r1 == 0) goto L72
            L4b:
                gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
                r1 = 7
                java.lang.Object[] r1 = new java.lang.Object[r1]
                gnu.kawa.slib.printf$frame r4 = r7.staticLink
                java.lang.Object r4 = r4.proc
                r5 = 0
                r1[r5] = r4
                java.lang.Object r4 = r7.sgn
                r1[r3] = r4
                java.lang.Object r3 = r7.digs
                r1[r2] = r3
                r2 = 3
                java.lang.Object r3 = r7.ex
                r1[r2] = r3
                r2 = 4
                r1[r2] = r9
                r2 = 5
                r1[r2] = r10
                r2 = 6
                r1[r2] = r11
                java.lang.Object r0 = r0.applyN(r1)
                goto L76
            L72:
                java.lang.Boolean r0 = gnu.kawa.slib.printf.frame.lambda1parseError()
            L76:
                return r0
            L77:
                r8 = move-exception
                gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
                java.lang.String r10 = "x"
                r11 = -2
                r9.<init>(r8, r10, r11, r1)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.frame0.lambda6(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        @Override // gnu.expr.ModuleBody
        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            if (moduleMethod.selector != 2) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 3 ? lambda7(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        Object lambda7(Object sgn, Object digs, Object ex) {
            frame1 frame1Var = new frame1();
            frame1Var.staticLink = this;
            frame1Var.sgn = sgn;
            frame1Var.digs = digs;
            frame1Var.ex = ex;
            Object obj = this.num;
            try {
                return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.imagPart((Complex) obj), 10), frame1Var.lambda$Fn4);
            } catch (ClassCastException e) {
                throw new WrongType(e, "imag-part", 1, obj);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 3) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame1 extends ModuleBody {
        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn4;
        Object sgn;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 12291);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:126");
            this.lambda$Fn4 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 1 ? lambda8(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        Object lambda8(Object im$Mnsgn, Object im$Mndigs, Object im$Mnex) {
            return Scheme.applyToArgs.applyN(new Object[]{this.staticLink.staticLink.proc, this.sgn, this.digs, this.ex, im$Mnsgn, im$Mndigs, im$Mnex});
        }

        @Override // gnu.expr.ModuleBody
        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:152:0x011f, code lost:
    
        if ((((java.lang.Number) r3.lambda17dig(r0)).intValue() & 1) != 0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x013f, code lost:
    
        if (r7 != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00ea, code lost:
    
        if (r8 != false) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object stdio$ClRoundString(java.lang.CharSequence r17, java.lang.Object r18, java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 605
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.stdio$ClRoundString(java.lang.CharSequence, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector != 23) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        }
        try {
            return stdio$ClRoundString((CharSequence) obj, obj2, obj3);
        } catch (ClassCastException e) {
            throw new WrongType(e, "stdio:round-string", 1, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 23) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        if (!(obj instanceof CharSequence)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame8 extends ModuleBody {
        CharSequence str;

        public Object lambda17dig(Object i) {
            try {
                char c = strings.stringRef(this.str, ((Number) i).intValue());
                return unicode.isCharNumeric(Char.make(c)) ? numbers.string$To$Number(strings.$make$string$(Char.make(c)), 10) : printf.Lit1;
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-ref", 2, i);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:0x035e, code lost:
    
        if (r13 == false) goto L551;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x03ae, code lost:
    
        if (kawa.standard.Scheme.numLEq.apply2(r8.width, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6))) == java.lang.Boolean.FALSE) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x03b5, code lost:
    
        if (r8.left$Mnadjust == java.lang.Boolean.FALSE) goto L186;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x03be, code lost:
    
        r12 = gnu.kawa.functions.AddOp.$Mn.apply2(r8.width, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x03d1, code lost:
    
        r6 = gnu.lists.LList.list2(r6, kawa.lib.strings.makeString(((java.lang.Number) r12).intValue(), r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x03da, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x03e0, code lost:
    
        throw new gnu.mapping.WrongType(r0, "make-string", r9, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x03e1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x03e8, code lost:
    
        throw new gnu.mapping.WrongType(r0, "string-length", r9, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x03f0, code lost:
    
        r13 = gnu.kawa.functions.AddOp.$Mn.apply2(r8.width, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x03fc, code lost:
    
        r10 = ((java.lang.Number) r13).intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0407, code lost:
    
        if (r8.leading$Mn0s == java.lang.Boolean.FALSE) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0409, code lost:
    
        r11 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x040c, code lost:
    
        r6 = gnu.lists.LList.list2(kawa.lib.strings.makeString(r10, r11), r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0429, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x042f, code lost:
    
        throw new gnu.mapping.WrongType(r0, "make-string", r9, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0430, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0437, code lost:
    
        throw new gnu.mapping.WrongType(r0, "string-length", r9, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0414, code lost:
    
        r6 = r0.lambda21out$St(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x041a, code lost:
    
        if (r6 == java.lang.Boolean.FALSE) goto L647;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x041c, code lost:
    
        r6 = kawa.lib.lists.cdr.apply1(r8.args);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:?, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0438, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x043f, code lost:
    
        throw new gnu.mapping.WrongType(r0, "string-length", r9, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0378, code lost:
    
        r6 = (java.lang.CharSequence) r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x037a, code lost:
    
        r13 = r8.precision;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0383, code lost:
    
        r6 = kawa.lib.strings.substring(r6, r5, ((java.lang.Number) r13).intValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0388, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x038f, code lost:
    
        throw new gnu.mapping.WrongType(r0, "substring", 3, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0390, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0397, code lost:
    
        throw new gnu.mapping.WrongType(r0, "substring", r9, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0376, code lost:
    
        if (kawa.standard.Scheme.numGEq.apply2(r8.precision, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r6))) == java.lang.Boolean.FALSE) goto L551;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x0498, code lost:
    
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r14)) != false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x04b2, code lost:
    
        if (r8.left$Mnadjust == java.lang.Boolean.FALSE) goto L241;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x04b4, code lost:
    
        r14 = r8.lambda$Fn14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x04df, code lost:
    
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r6)) != false) goto L259;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0525, code lost:
    
        if (r8.left$Mnadjust == java.lang.Boolean.FALSE) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x053b, code lost:
    
        if (kawa.standard.Scheme.numGrt.apply2(r8.width, gnu.kawa.functions.AddOp.$Mn.apply2(r8.precision, r8.pr)) == java.lang.Boolean.FALSE) goto L298;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x053d, code lost:
    
        r6 = kawa.standard.Scheme.applyToArgs;
        r12 = r0.out;
        r5 = gnu.kawa.functions.AddOp.$Mn.apply2(r8.width, gnu.kawa.functions.AddOp.$Mn.apply2(r8.precision, r8.pr));
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x055a, code lost:
    
        r6.apply2(r12, kawa.lib.strings.makeString(((java.lang.Number) r5).intValue(), r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0562, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0569, code lost:
    
        throw new gnu.mapping.WrongType(r0, "make-string", 1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x056b, code lost:
    
        r5 = r8.os;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x056f, code lost:
    
        if (r5 == java.lang.Boolean.FALSE) goto L282;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x0571, code lost:
    
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x0577, code lost:
    
        if (((r5 + 1) & 1) == 0) goto L286;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x057a, code lost:
    
        r5 = kawa.standard.Scheme.numLEq;
        r6 = r8.width;
        r9 = r8.os;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x0590, code lost:
    
        if (r5.apply2(r6, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r9))) == java.lang.Boolean.FALSE) goto L291;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0592, code lost:
    
        kawa.standard.Scheme.applyToArgs.apply2(r0.out, r8.os);
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x059c, code lost:
    
        r5 = kawa.standard.Scheme.applyToArgs;
        r6 = r0.out;
        r9 = gnu.kawa.functions.AddOp.$Mn;
        r12 = r8.width;
        r13 = r8.os;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x05a8, code lost:
    
        r9 = r9.apply2(r12, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r13)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x05c5, code lost:
    
        if (r5.apply2(r6, kawa.lib.strings.makeString(((java.lang.Number) r9).intValue(), r11)) == java.lang.Boolean.FALSE) goto L298;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x05c7, code lost:
    
        kawa.standard.Scheme.applyToArgs.apply2(r0.out, r8.os);
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x05dd, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x05e4, code lost:
    
        throw new gnu.mapping.WrongType(r0, "make-string", 1, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x05e5, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x05ed, code lost:
    
        throw new gnu.mapping.WrongType(r0, "string-length", 1, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x05ee, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x05f6, code lost:
    
        throw new gnu.mapping.WrongType(r0, "string-length", 1, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x0573, code lost:
    
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x05f7, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x0601, code lost:
    
        throw new gnu.mapping.WrongType(r0, "x", -2, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x04fa, code lost:
    
        if (kawa.standard.Scheme.numGrt.apply2(r8.width, r8.pr) == java.lang.Boolean.FALSE) goto L298;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x04fc, code lost:
    
        r6 = kawa.standard.Scheme.applyToArgs;
        r12 = r0.out;
        r13 = gnu.kawa.functions.AddOp.$Mn.apply2(r8.width, r8.pr);
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x0511, code lost:
    
        r6.apply2(r12, kawa.lib.strings.makeString(((java.lang.Number) r13).intValue(), r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x051a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x0520, code lost:
    
        throw new gnu.mapping.WrongType(r0, "make-string", r9, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x04ec, code lost:
    
        if (r6 != java.lang.Boolean.FALSE) goto L259;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x04b7, code lost:
    
        r14 = r8.pr;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x04c1, code lost:
    
        if (kawa.lib.numbers.isNegative(gnu.kawa.lispexpr.LangObjType.coerceRealNum(r14)) == false) goto L246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x04c3, code lost:
    
        r8.pr = r8.width;
        r14 = r8.lambda$Fn15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x04ca, code lost:
    
        r14 = r8.lambda$Fn16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0602, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x060a, code lost:
    
        throw new gnu.mapping.WrongType(r0, "negative?", 1, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x04a7, code lost:
    
        r8.pr = gnu.kawa.slib.printf.Lit1;
        r14 = r8.lambda$Fn13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x04a5, code lost:
    
        if (r14 != java.lang.Boolean.FALSE) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x0875, code lost:
    
        if (kawa.lib.rnrs.unicode.isCharCi$Eq((gnu.text.Char) r6, gnu.kawa.slib.printf.Lit55) != false) goto L451;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x0885, code lost:
    
        r8.precision = gnu.kawa.slib.printf.Lit7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x0883, code lost:
    
        if (r6 != false) goto L451;
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:?, code lost:
    
        return r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0691 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:335:0x06a0  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x06d5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0712 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0781  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x078d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:367:0x07b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x08a8  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x08d4  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x08e6  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x08f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:403:0x08d7  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0892 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0861  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0913  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x090e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:496:0x079c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:520:0x06e4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:532:0x06a5  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x061c  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02f2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object stdio$ClIprintf$V(java.lang.Object r16, java.lang.Object r17, java.lang.Object[] r18) {
        /*
            Method dump skipped, instructions count: 2438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.printf.stdio$ClIprintf$V(java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 24:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 25:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 26:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 27:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame9 extends ModuleBody {
        LList args;
        Object fc;
        int fl;
        Object format$Mnstring;
        Object out;
        Object pos;

        public Object lambda18mustAdvance() {
            this.pos = AddOp.$Pl.apply2(printf.Lit7, this.pos);
            if (Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.fl)) != Boolean.FALSE) {
                return lambda20incomplete();
            }
            Object obj = this.format$Mnstring;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object obj2 = this.pos;
                try {
                    this.fc = Char.make(strings.stringRef(charSequence, ((Number) obj2).intValue()));
                    return Values.empty;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj);
            }
        }

        public boolean lambda19isEndOfFormat() {
            return ((Boolean) Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.fl))).booleanValue();
        }

        public Object lambda20incomplete() {
            return misc.error$V(printf.Lit34, new Object[]{"conversion specification incomplete", this.format$Mnstring});
        }

        public Object lambda21out$St(Object strs) {
            if (strings.isString(strs)) {
                return Scheme.applyToArgs.apply2(this.out, strs);
            }
            Object strs2 = strs;
            while (true) {
                boolean x = lists.isNull(strs2);
                if (x) {
                    return x ? Boolean.TRUE : Boolean.FALSE;
                }
                Object x2 = Scheme.applyToArgs.apply2(this.out, lists.car.apply1(strs2));
                if (x2 == Boolean.FALSE) {
                    return x2;
                }
                strs2 = lists.cdr.apply1(strs2);
            }
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame10 extends ModuleBody {
        Object alternate$Mnform;
        Object args;
        Object blank;
        final ModuleMethod lambda$Fn13;
        final ModuleMethod lambda$Fn14;
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object leading$Mn0s;
        Object left$Mnadjust;
        Object os;
        Procedure pad = new ModuleMethod(this, 15, printf.Lit67, -4095);
        Object pr;
        Object precision;
        Object signed;
        frame9 staticLink;
        Object width;

        public frame10() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 16, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:472");
            this.lambda$Fn13 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 17, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:476");
            this.lambda$Fn14 = moduleMethod2;
            ModuleMethod moduleMethod3 = new ModuleMethod(this, 18, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:484");
            this.lambda$Fn15 = moduleMethod3;
            ModuleMethod moduleMethod4 = new ModuleMethod(this, 19, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:494");
            this.lambda$Fn16 = moduleMethod4;
        }

        public Object lambda22readFormatNumber() {
            if (Scheme.isEqv.apply2(printf.Lit66, this.staticLink.fc) != Boolean.FALSE) {
                this.staticLink.lambda18mustAdvance();
                Object accum = lists.car.apply1(this.args);
                this.args = lists.cdr.apply1(this.args);
                return accum;
            }
            Object c = this.staticLink.fc;
            Object accum2 = printf.Lit1;
            Object c2 = c;
            Object accum3 = accum2;
            while (true) {
                Object obj = this.staticLink.fc;
                try {
                    if (!unicode.isCharNumeric((Char) obj)) {
                        return accum3;
                    }
                    this.staticLink.lambda18mustAdvance();
                    Object obj2 = this.staticLink.fc;
                    accum3 = AddOp.$Pl.apply2(MultiplyOp.$St.apply2(accum3, printf.Lit45), numbers.string$To$Number(strings.$make$string$(c2 instanceof Object[] ? (Object[]) c2 : new Object[]{c2}), 10));
                    c2 = obj2;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "char-numeric?", 1, obj);
                }
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            if (moduleMethod.selector != 15) {
                return super.applyN(moduleMethod, objArr);
            }
            Object obj = objArr[0];
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            while (true) {
                length--;
                if (length < 0) {
                    return lambda23pad$V(obj, objArr2);
                }
                objArr2[length] = objArr[length + 1];
            }
        }

        public Object lambda23pad$V(Object pre, Object[] argsArray) {
            LList strs = LList.makeList(argsArray, 0);
            try {
                Object len = Integer.valueOf(strings.stringLength((CharSequence) pre));
                Object ss = strs;
                while (Scheme.numGEq.apply2(len, this.width) == Boolean.FALSE) {
                    if (lists.isNull(ss)) {
                        if (this.left$Mnadjust != Boolean.FALSE) {
                            Object[] objArr = new Object[2];
                            objArr[0] = strs;
                            Object apply2 = AddOp.$Mn.apply2(this.width, len);
                            try {
                                objArr[1] = LList.list1(strings.makeString(((Number) apply2).intValue(), printf.Lit29));
                                return lists.cons(pre, append.append$V(objArr));
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "make-string", 1, apply2);
                            }
                        }
                        if (this.leading$Mn0s != Boolean.FALSE) {
                            Object apply22 = AddOp.$Mn.apply2(this.width, len);
                            try {
                                return lists.cons(pre, lists.cons(strings.makeString(((Number) apply22).intValue(), printf.Lit9), strs));
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "make-string", 1, apply22);
                            }
                        }
                        Object apply23 = AddOp.$Mn.apply2(this.width, len);
                        try {
                            return lists.cons(strings.makeString(((Number) apply23).intValue(), printf.Lit29), lists.cons(pre, strs));
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "make-string", 1, apply23);
                        }
                    }
                    AddOp addOp = AddOp.$Pl;
                    Object apply1 = lists.car.apply1(ss);
                    try {
                        len = addOp.apply2(len, Integer.valueOf(strings.stringLength((CharSequence) apply1)));
                        ss = lists.cdr.apply1(ss);
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-length", 1, apply1);
                    }
                }
                return lists.cons(pre, strs);
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-length", 1, pre);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 15) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        public Object lambda24integerConvert(Object obj, Object obj2, Object obj3) {
            String str;
            Object obj4 = this.precision;
            try {
                Object obj5 = "";
                if (!numbers.isNegative(LangObjType.coerceRealNum(obj4))) {
                    this.leading$Mn0s = Boolean.FALSE;
                    Object obj6 = this.precision;
                    try {
                        boolean isZero = numbers.isZero((Number) obj6);
                        if (!isZero ? isZero : Scheme.isEqv.apply2(printf.Lit1, obj) != Boolean.FALSE) {
                            obj = "";
                        }
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "zero?", 1, obj6);
                    }
                }
                boolean isSymbol = misc.isSymbol(obj);
                String str2 = Component.TYPEFACE_DEFAULT;
                if (isSymbol) {
                    try {
                        obj = ((Symbol) obj).toString();
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "symbol->string", 1, obj);
                    }
                } else if (numbers.isNumber(obj)) {
                    try {
                        try {
                            obj = numbers.number$To$String((Number) obj, ((Number) obj2).intValue());
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "number->string", 2, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "number->string", 1, obj);
                    }
                } else {
                    try {
                        int i = ((obj != Boolean.FALSE ? 1 : 0) + 1) & 1;
                        if (i == 0 ? lists.isNull(obj) : i != 0) {
                            obj = Component.TYPEFACE_DEFAULT;
                        } else if (!strings.isString(obj)) {
                            obj = Component.TYPEFACE_SANSSERIF;
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "x", -2, obj);
                    }
                }
                if (obj3 != Boolean.FALSE) {
                    obj = Scheme.applyToArgs.apply2(obj3, obj);
                }
                if (IsEqual.apply("", obj)) {
                    str = "";
                } else {
                    try {
                        if (Scheme.isEqv.apply2(printf.Lit5, Char.make(strings.stringRef((CharSequence) obj, 0))) != Boolean.FALSE) {
                            try {
                                try {
                                    obj = strings.substring((CharSequence) obj, 1, strings.stringLength((CharSequence) obj));
                                    str = "-";
                                } catch (ClassCastException e6) {
                                    throw new WrongType(e6, "string-length", 1, obj);
                                }
                            } catch (ClassCastException e7) {
                                throw new WrongType(e7, "substring", 1, obj);
                            }
                        } else if (this.signed != Boolean.FALSE) {
                            str = "+";
                        } else if (this.blank != Boolean.FALSE) {
                            str = " ";
                        } else if (this.alternate$Mnform != Boolean.FALSE) {
                            if (Scheme.isEqv.apply2(obj2, printf.Lit48) == Boolean.FALSE) {
                                if (Scheme.isEqv.apply2(obj2, printf.Lit50) != Boolean.FALSE) {
                                    str2 = "0x";
                                } else {
                                    str2 = "";
                                }
                            }
                            str = str2;
                        } else {
                            str = "";
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "string-ref", 1, obj);
                    }
                }
                Object[] objArr = new Object[2];
                try {
                    if (Scheme.numLss.apply2(Integer.valueOf(strings.stringLength((CharSequence) obj)), this.precision) != Boolean.FALSE) {
                        try {
                            Object apply2 = AddOp.$Mn.apply2(this.precision, Integer.valueOf(strings.stringLength((CharSequence) obj)));
                            try {
                                obj5 = strings.makeString(((Number) apply2).intValue(), printf.Lit9);
                            } catch (ClassCastException e9) {
                                throw new WrongType(e9, "make-string", 1, apply2);
                            }
                        } catch (ClassCastException e10) {
                            throw new WrongType(e10, "string-length", 1, obj);
                        }
                    }
                    objArr[0] = obj5;
                    objArr[1] = obj;
                    return lambda23pad$V(str, objArr);
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "string-length", 1, obj);
                }
            } catch (ClassCastException e12) {
                throw new WrongType(e12, "negative?", 1, obj4);
            }
        }

        Object lambda25(Object s) {
            try {
                this.pr = AddOp.$Pl.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) s)));
                return Scheme.applyToArgs.apply2(this.staticLink.out, s);
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, s);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 16:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 17:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 18:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 19:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        boolean lambda26(Object s) {
            Object obj;
            Special special = Special.undefined;
            try {
                Object sl = AddOp.$Mn.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) s)));
                try {
                    if (numbers.isNegative(LangObjType.coerceRealNum(sl))) {
                        ApplyToArgs applyToArgs = Scheme.applyToArgs;
                        Object obj2 = this.staticLink.out;
                        try {
                            CharSequence charSequence = (CharSequence) s;
                            Object obj3 = this.pr;
                            try {
                                applyToArgs.apply2(obj2, strings.substring(charSequence, 0, ((Number) obj3).intValue()));
                                obj = printf.Lit1;
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 3, obj3);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "substring", 1, s);
                        }
                    } else {
                        Scheme.applyToArgs.apply2(this.staticLink.out, s);
                        obj = sl;
                    }
                    this.pr = obj;
                    try {
                        return numbers.isPositive(LangObjType.coerceRealNum(sl));
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "positive?", 1, sl);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "negative?", 1, sl);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-length", 1, s);
            }
        }

        Boolean lambda27(Object s) {
            try {
                this.pr = AddOp.$Mn.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) s)));
                if (this.os == Boolean.FALSE) {
                    Scheme.applyToArgs.apply2(this.staticLink.out, s);
                } else {
                    Object obj = this.pr;
                    try {
                        if (numbers.isNegative(LangObjType.coerceRealNum(obj))) {
                            Scheme.applyToArgs.apply2(this.staticLink.out, this.os);
                            this.os = Boolean.FALSE;
                            Scheme.applyToArgs.apply2(this.staticLink.out, s);
                        } else {
                            this.os = strings.stringAppend(this.os, s);
                        }
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "negative?", 1, obj);
                    }
                }
                return Boolean.TRUE;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-length", 1, s);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 16:
                    return lambda25(obj);
                case 17:
                    return lambda26(obj) ? Boolean.TRUE : Boolean.FALSE;
                case 18:
                    return lambda27(obj);
                case 19:
                    return lambda28(obj) ? Boolean.TRUE : Boolean.FALSE;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        boolean lambda28(Object s) {
            Special special = Special.undefined;
            try {
                Object sl = AddOp.$Mn.apply2(this.pr, Integer.valueOf(strings.stringLength((CharSequence) s)));
                try {
                    if (numbers.isNegative(LangObjType.coerceRealNum(sl))) {
                        Object[] objArr = new Object[2];
                        objArr[0] = this.os;
                        try {
                            CharSequence charSequence = (CharSequence) s;
                            Object obj = this.pr;
                            try {
                                objArr[1] = strings.substring(charSequence, 0, ((Number) obj).intValue());
                                this.os = strings.stringAppend(objArr);
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 3, obj);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "substring", 1, s);
                        }
                    } else {
                        this.os = strings.stringAppend(this.os, s);
                    }
                    this.pr = sl;
                    try {
                        return numbers.isPositive(LangObjType.coerceRealNum(sl));
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "positive?", 1, sl);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "negative?", 1, sl);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-length", 1, s);
            }
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame11 extends ModuleBody {
        Object fc;
        Procedure format$Mnreal = new ModuleMethod(this, 13, printf.Lit64, -4092);
        final ModuleMethod lambda$Fn17;
        frame10 staticLink;

        public frame11() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 14, null, -4093);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:401");
            this.lambda$Fn17 = moduleMethod;
        }

        public Object lambda29f(Object obj, Object obj2, Object obj3) {
            IntNum intNum;
            try {
                Object stdio$ClRoundString = printf.stdio$ClRoundString((CharSequence) obj, AddOp.$Pl.apply2(obj2, this.staticLink.precision), obj3 != Boolean.FALSE ? obj2 : obj3);
                if (Scheme.numGEq.apply2(obj2, printf.Lit1) != Boolean.FALSE) {
                    try {
                        if (numbers.isZero((Number) obj2)) {
                            intNum = printf.Lit1;
                        } else {
                            try {
                                if (characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef((CharSequence) stdio$ClRoundString, 0)))) {
                                    intNum = printf.Lit7;
                                } else {
                                    intNum = printf.Lit1;
                                }
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "string-ref", 1, stdio$ClRoundString);
                            }
                        }
                        Object max = numbers.max(printf.Lit7, AddOp.$Pl.apply2(printf.Lit7, obj2));
                        try {
                            try {
                                try {
                                    CharSequence substring = strings.substring((CharSequence) stdio$ClRoundString, intNum.intValue(), ((Number) max).intValue());
                                    try {
                                        try {
                                            try {
                                                CharSequence substring2 = strings.substring((CharSequence) stdio$ClRoundString, ((Number) max).intValue(), strings.stringLength((CharSequence) stdio$ClRoundString));
                                                boolean isString$Eq = strings.isString$Eq(substring2, "");
                                                return lists.cons(substring, (!isString$Eq ? isString$Eq : this.staticLink.alternate$Mnform == Boolean.FALSE) ? LList.list2(".", substring2) : LList.Empty);
                                            } catch (ClassCastException e2) {
                                                throw new WrongType(e2, "string-length", 1, stdio$ClRoundString);
                                            }
                                        } catch (ClassCastException e3) {
                                            throw new WrongType(e3, "substring", 2, max);
                                        }
                                    } catch (ClassCastException e4) {
                                        throw new WrongType(e4, "substring", 1, stdio$ClRoundString);
                                    }
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "substring", 3, max);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "substring", 2, intNum);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "substring", 1, stdio$ClRoundString);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "zero?", 1, obj2);
                    }
                }
                Object obj4 = this.staticLink.precision;
                try {
                    if (numbers.isZero((Number) obj4)) {
                        return LList.list1(this.staticLink.alternate$Mnform == Boolean.FALSE ? Component.TYPEFACE_DEFAULT : "0.");
                    }
                    if (obj3 != Boolean.FALSE) {
                        boolean isString$Eq2 = strings.isString$Eq(stdio$ClRoundString, "");
                        obj3 = isString$Eq2 ? LList.list1(Component.TYPEFACE_DEFAULT) : isString$Eq2 ? Boolean.TRUE : Boolean.FALSE;
                    }
                    if (obj3 != Boolean.FALSE) {
                        return obj3;
                    }
                    Object min = numbers.min(this.staticLink.precision, AddOp.$Mn.apply2(printf.Lit17, obj2));
                    try {
                        return LList.list3("0.", strings.makeString(((Number) min).intValue(), printf.Lit9), stdio$ClRoundString);
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, "make-string", 1, min);
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "zero?", 1, obj4);
                }
            } catch (ClassCastException e11) {
                throw new WrongType(e11, "stdio:round-string", 0, obj);
            }
        }

        public Object lambda30formatReal$V(Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
            Object obj5;
            Object stdio$ClRoundString;
            IntNum intNum;
            CharSequence substring;
            Pair list1;
            String str;
            Object obj6;
            Object obj7;
            Object obj8 = obj4;
            LList makeList = LList.makeList(objArr, 0);
            if (!lists.isNull(makeList)) {
                return append.append$V(new Object[]{lambda30formatReal$V(obj, obj2, obj3, obj4, new Object[0]), Scheme.apply.apply3(this.format$Mnreal, Boolean.TRUE, makeList), printf.Lit63});
            }
            try {
                String str2 = " ";
                String str3 = characters.isChar$Eq(printf.Lit5, (Char) obj2) ? "-" : obj != Boolean.FALSE ? "+" : this.staticLink.blank != Boolean.FALSE ? " " : "";
                Object apply2 = Scheme.isEqv.apply2(this.fc, printf.Lit13);
                try {
                    try {
                        try {
                            try {
                                try {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    if (apply2 == Boolean.FALSE ? Scheme.isEqv.apply2(this.fc, printf.Lit54) == Boolean.FALSE : apply2 == Boolean.FALSE) {
                                                        Object apply22 = Scheme.isEqv.apply2(this.fc, printf.Lit25);
                                                        if (apply22 == Boolean.FALSE ? Scheme.isEqv.apply2(this.fc, printf.Lit26) == Boolean.FALSE : apply22 == Boolean.FALSE) {
                                                            Object apply23 = Scheme.isEqv.apply2(this.fc, printf.Lit55);
                                                            if (apply23 == Boolean.FALSE ? Scheme.isEqv.apply2(this.fc, printf.Lit56) == Boolean.FALSE : apply23 == Boolean.FALSE) {
                                                                if (Scheme.isEqv.apply2(this.fc, printf.Lit57) != Boolean.FALSE) {
                                                                    str2 = "";
                                                                } else if (Scheme.isEqv.apply2(this.fc, printf.Lit58) == Boolean.FALSE) {
                                                                    obj7 = Values.empty;
                                                                }
                                                                try {
                                                                    Object apply24 = numbers.isNegative(LangObjType.coerceRealNum(obj4)) ? DivideOp.quotient.apply2(AddOp.$Mn.apply2(obj8, printf.Lit61), printf.Lit61) : DivideOp.quotient.apply2(AddOp.$Mn.apply2(obj8, printf.Lit7), printf.Lit61);
                                                                    Object apply3 = Scheme.numLss.apply3(printf.Lit17, AddOp.$Pl.apply2(apply24, printf.Lit48), Integer.valueOf(vectors.vectorLength(printf.Lit62)));
                                                                    try {
                                                                        boolean booleanValue = ((Boolean) apply3).booleanValue();
                                                                        if (!booleanValue) {
                                                                            apply24 = booleanValue ? Boolean.TRUE : Boolean.FALSE;
                                                                        }
                                                                        if (apply24 != Boolean.FALSE) {
                                                                            Object apply25 = AddOp.$Mn.apply2(obj8, MultiplyOp.$St.apply2(printf.Lit61, apply24));
                                                                            this.staticLink.precision = numbers.max(printf.Lit1, AddOp.$Mn.apply2(this.staticLink.precision, apply25));
                                                                            Object[] objArr2 = new Object[2];
                                                                            objArr2[0] = lambda29f(obj3, apply25, Boolean.FALSE);
                                                                            FVector fVector = printf.Lit62;
                                                                            Object apply26 = AddOp.$Pl.apply2(apply24, printf.Lit48);
                                                                            try {
                                                                                objArr2[1] = LList.list2(str2, vectors.vectorRef(fVector, ((Number) apply26).intValue()));
                                                                                obj7 = append.append$V(objArr2);
                                                                            } catch (ClassCastException e) {
                                                                                throw new WrongType(e, "vector-ref", 2, apply26);
                                                                            }
                                                                        }
                                                                    } catch (ClassCastException e2) {
                                                                        throw new WrongType(e2, "x", -2, apply3);
                                                                    }
                                                                } catch (ClassCastException e3) {
                                                                    throw new WrongType(e3, "negative?", 1, obj8);
                                                                }
                                                            }
                                                            Object obj9 = this.staticLink.alternate$Mnform;
                                                            try {
                                                                int i = ((obj9 != Boolean.FALSE ? 1 : 0) + 1) & 1;
                                                                this.staticLink.alternate$Mnform = Boolean.FALSE;
                                                                if (Scheme.numLEq.apply3(AddOp.$Mn.apply2(printf.Lit7, this.staticLink.precision), obj8, this.staticLink.precision) != Boolean.FALSE) {
                                                                    this.staticLink.precision = AddOp.$Mn.apply2(this.staticLink.precision, obj8);
                                                                    obj7 = lambda29f(obj3, obj8, i != 0 ? Boolean.TRUE : Boolean.FALSE);
                                                                } else {
                                                                    this.staticLink.precision = AddOp.$Mn.apply2(this.staticLink.precision, printf.Lit7);
                                                                    obj5 = i != 0 ? Boolean.TRUE : Boolean.FALSE;
                                                                }
                                                            } catch (ClassCastException e4) {
                                                                throw new WrongType(e4, "strip-0s", -2, obj9);
                                                            }
                                                        } else {
                                                            obj7 = lambda29f(obj3, obj8, Boolean.FALSE);
                                                        }
                                                        return lists.cons(str3, obj7);
                                                    }
                                                    obj5 = Boolean.FALSE;
                                                    LList.chain1(LList.chain1(LList.chain4(list1, str, substring, unicode.isCharUpperCase((Char) obj6) ? "E" : "e", numbers.isNegative(LangObjType.coerceRealNum(obj8)) ? "-" : "+"), Scheme.numLss.apply3(printf.Lit60, obj8, printf.Lit45) != Boolean.FALSE ? Component.TYPEFACE_DEFAULT : ""), numbers.number$To$String(numbers.abs((Number) obj8), 10));
                                                    obj7 = list1;
                                                    return lists.cons(str3, obj7);
                                                } catch (ClassCastException e5) {
                                                    throw new WrongType(e5, "abs", 1, obj8);
                                                }
                                            } catch (ClassCastException e6) {
                                                throw new WrongType(e6, "negative?", 1, obj8);
                                            }
                                        } catch (ClassCastException e7) {
                                            throw new WrongType(e7, "char-upper-case?", 1, obj6);
                                        }
                                        list1 = LList.list1(strings.substring((CharSequence) stdio$ClRoundString, intNum.intValue(), intNum.intValue() + 1));
                                        boolean isString$Eq = strings.isString$Eq(substring, "");
                                        str = (!isString$Eq ? isString$Eq : this.staticLink.alternate$Mnform == Boolean.FALSE) ? "." : "";
                                        obj6 = this.fc;
                                    } catch (ClassCastException e8) {
                                        throw new WrongType(e8, "substring", 2, intNum);
                                    }
                                } catch (ClassCastException e9) {
                                    throw new WrongType(e9, "substring", 1, stdio$ClRoundString);
                                }
                                substring = strings.substring((CharSequence) stdio$ClRoundString, intNum.intValue() + 1, strings.stringLength((CharSequence) stdio$ClRoundString));
                                if (!numbers.isZero(intNum)) {
                                    obj8 = AddOp.$Mn.apply2(obj8, printf.Lit7);
                                }
                            } catch (ClassCastException e10) {
                                throw new WrongType(e10, "string-length", 1, stdio$ClRoundString);
                            }
                        } catch (ClassCastException e11) {
                            throw new WrongType(e11, "substring", 1, stdio$ClRoundString);
                        }
                        intNum = characters.isChar$Eq(printf.Lit9, Char.make(strings.stringRef((CharSequence) stdio$ClRoundString, 0))) ? printf.Lit7 : printf.Lit1;
                    } catch (ClassCastException e12) {
                        throw new WrongType(e12, "string-ref", 1, stdio$ClRoundString);
                    }
                    CharSequence charSequence = (CharSequence) obj3;
                    Object apply27 = AddOp.$Pl.apply2(printf.Lit7, this.staticLink.precision);
                    if (obj5 != Boolean.FALSE) {
                        obj5 = printf.Lit1;
                    }
                    stdio$ClRoundString = printf.stdio$ClRoundString(charSequence, apply27, obj5);
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "stdio:round-string", 0, obj3);
                }
            } catch (ClassCastException e14) {
                throw new WrongType(e14, "char=?", 2, obj2);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 13:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 14:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                default:
                    return super.matchN(moduleMethod, objArr, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            switch (moduleMethod.selector) {
                case 13:
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    Object obj3 = objArr[2];
                    Object obj4 = objArr[3];
                    int length = objArr.length - 4;
                    Object[] objArr2 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return lambda30formatReal$V(obj, obj2, obj3, obj4, objArr2);
                        }
                        objArr2[length] = objArr[length + 4];
                    }
                case 14:
                    Object obj5 = objArr[0];
                    Object obj6 = objArr[1];
                    Object obj7 = objArr[2];
                    int length2 = objArr.length - 3;
                    Object[] objArr3 = new Object[length2];
                    while (true) {
                        length2--;
                        if (length2 < 0) {
                            return lambda31$V(obj5, obj6, obj7, objArr3);
                        }
                        objArr3[length2] = objArr[length2 + 3];
                    }
                default:
                    return super.applyN(moduleMethod, objArr);
            }
        }

        Object lambda31$V(Object sgn, Object digs, Object expon, Object[] argsArray) {
            LList imag = LList.makeList(argsArray, 0);
            return Scheme.apply.apply2(this.staticLink.pad, Scheme.apply.applyN(new Object[]{this.format$Mnreal, this.staticLink.signed, sgn, digs, expon, imag}));
        }
    }

    public static Object fprintf$V(Object port, Object format, Object[] argsArray) {
        frame12 frame12Var = new frame12();
        frame12Var.port = port;
        LList args = LList.makeList(argsArray, 0);
        frame12Var.cnt = Lit1;
        Scheme.apply.apply4(stdio$Cliprintf, frame12Var.lambda$Fn18, format, args);
        return frame12Var.cnt;
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame12 extends ModuleBody {
        Object cnt;
        final ModuleMethod lambda$Fn18;
        Object port;

        public frame12() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 20, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:546");
            this.lambda$Fn18 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 20 ? lambda32(obj) : super.apply1(moduleMethod, obj);
        }

        Boolean lambda32(Object x) {
            if (strings.isString(x)) {
                try {
                    this.cnt = AddOp.$Pl.apply2(Integer.valueOf(strings.stringLength((CharSequence) x)), this.cnt);
                    ports.display(x, this.port);
                    return Boolean.TRUE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-length", 1, x);
                }
            }
            this.cnt = AddOp.$Pl.apply2(printf.Lit7, this.cnt);
            ports.display(x, this.port);
            return Boolean.TRUE;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 20) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object printf$V(Object format, Object[] argsArray) {
        LList args = LList.makeList(argsArray, 0);
        return Scheme.apply.apply4(fprintf, ports.current$Mnoutput$Mnport.apply0(), format, args);
    }

    public static Object sprintf$V(Object str, Object format, Object[] argsArray) {
        Object error$V;
        frame13 frame13Var = new frame13();
        frame13Var.str = str;
        LList args = LList.makeList(argsArray, 0);
        frame13Var.cnt = Lit1;
        if (strings.isString(frame13Var.str)) {
            error$V = frame13Var.str;
        } else if (numbers.isNumber(frame13Var.str)) {
            Object obj = frame13Var.str;
            try {
                error$V = strings.makeString(((Number) obj).intValue());
            } catch (ClassCastException e) {
                throw new WrongType(e, "make-string", 1, obj);
            }
        } else if (frame13Var.str == Boolean.FALSE) {
            error$V = strings.makeString(100);
        } else {
            error$V = misc.error$V(Lit68, new Object[]{"first argument not understood", frame13Var.str});
        }
        frame13Var.s = error$V;
        Object obj2 = frame13Var.s;
        try {
            frame13Var.end = Integer.valueOf(strings.stringLength((CharSequence) obj2));
            Scheme.apply.apply4(stdio$Cliprintf, frame13Var.lambda$Fn19, format, args);
            if (strings.isString(frame13Var.str)) {
                return frame13Var.cnt;
            }
            if (Scheme.isEqv.apply2(frame13Var.end, frame13Var.cnt) != Boolean.FALSE) {
                return frame13Var.s;
            }
            Object obj3 = frame13Var.s;
            try {
                CharSequence charSequence = (CharSequence) obj3;
                Object obj4 = frame13Var.cnt;
                try {
                    return strings.substring(charSequence, 0, ((Number) obj4).intValue());
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 3, obj4);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, obj3);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "string-length", 1, obj2);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 24:
                Object obj = objArr[0];
                Object obj2 = objArr[1];
                int length = objArr.length - 2;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return stdio$ClIprintf$V(obj, obj2, objArr2);
                    }
                    objArr2[length] = objArr[length + 2];
                }
            case 25:
                Object obj3 = objArr[0];
                Object obj4 = objArr[1];
                int length2 = objArr.length - 2;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return fprintf$V(obj3, obj4, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 2];
                }
            case 26:
                Object obj5 = objArr[0];
                int length3 = objArr.length - 1;
                Object[] objArr4 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return printf$V(obj5, objArr4);
                    }
                    objArr4[length3] = objArr[length3 + 1];
                }
            case 27:
                Object obj6 = objArr[0];
                Object obj7 = objArr[1];
                int length4 = objArr.length - 2;
                Object[] objArr5 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return sprintf$V(obj6, obj7, objArr5);
                    }
                    objArr5[length4] = objArr[length4 + 2];
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    /* compiled from: printf.scm */
    /* loaded from: classes2.dex */
    public class frame13 extends ModuleBody {
        Object cnt;
        Object end;
        final ModuleMethod lambda$Fn19;
        Object s;
        Object str;

        public frame13() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 21, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/printf.scm:564");
            this.lambda$Fn19 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 21 ? lambda33(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda33(Object x) {
            char charValue;
            int i = 0;
            if (!strings.isString(x)) {
                if ((this.str != Boolean.FALSE ? Scheme.numGEq.apply2(this.cnt, this.end) : this.str) == Boolean.FALSE) {
                    Object obj = this.str;
                    try {
                        int i2 = ((obj != Boolean.FALSE ? 1 : 0) + 1) & 1;
                        if (i2 == 0 ? i2 != 0 : Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE) {
                            FString stringAppend = strings.stringAppend(this.s, strings.makeString(100));
                            this.s = stringAppend;
                            try {
                                this.end = Integer.valueOf(strings.stringLength(stringAppend));
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "string-length", 1, stringAppend);
                            }
                        }
                        Object obj2 = this.s;
                        try {
                            CharSeq charSeq = (CharSeq) obj2;
                            Object obj3 = this.cnt;
                            try {
                                int intValue = ((Number) obj3).intValue();
                                if (characters.isChar(x)) {
                                    try {
                                        charValue = ((Char) x).charValue();
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, "string-set!", 3, x);
                                    }
                                } else {
                                    charValue = '?';
                                }
                                charSeq.setCharAt(intValue, charValue);
                                this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-set!", 2, obj3);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-set!", 1, obj2);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "x", -2, obj);
                    }
                }
            } else {
                if (this.str == Boolean.FALSE) {
                    try {
                        if (Scheme.numGEq.apply2(AddOp.$Mn.apply2(this.end, this.cnt), Integer.valueOf(strings.stringLength((CharSequence) x))) == Boolean.FALSE) {
                            Object[] objArr = new Object[2];
                            Object obj4 = this.s;
                            try {
                                CharSequence charSequence = (CharSequence) obj4;
                                Object obj5 = this.cnt;
                                try {
                                    objArr[0] = strings.substring(charSequence, 0, ((Number) obj5).intValue());
                                    objArr[1] = x;
                                    FString stringAppend2 = strings.stringAppend(objArr);
                                    this.s = stringAppend2;
                                    try {
                                        Integer valueOf = Integer.valueOf(strings.stringLength(stringAppend2));
                                        this.cnt = valueOf;
                                        this.end = valueOf;
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "string-length", 1, stringAppend2);
                                    }
                                } catch (ClassCastException e7) {
                                    throw new WrongType(e7, "substring", 3, obj5);
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, "substring", 1, obj4);
                            }
                        }
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, "string-length", 1, x);
                    }
                }
                Object[] objArr2 = new Object[2];
                try {
                    objArr2[0] = Integer.valueOf(strings.stringLength((CharSequence) x));
                    objArr2[1] = AddOp.$Mn.apply2(this.end, this.cnt);
                    Object lend = numbers.min(objArr2);
                    for (Object i3 = printf.Lit1; Scheme.numGEq.apply2(i3, lend) == Boolean.FALSE; i3 = AddOp.$Pl.apply2(i3, printf.Lit7)) {
                        Object obj6 = this.s;
                        try {
                            CharSeq charSeq2 = (CharSeq) obj6;
                            Object obj7 = this.cnt;
                            try {
                                try {
                                    try {
                                        charSeq2.setCharAt(((Number) obj7).intValue(), strings.stringRef((CharSequence) x, ((Number) i3).intValue()));
                                        this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
                                    } catch (ClassCastException e10) {
                                        throw new WrongType(e10, "string-ref", 2, i3);
                                    }
                                } catch (ClassCastException e11) {
                                    throw new WrongType(e11, "string-ref", 1, x);
                                }
                            } catch (ClassCastException e12) {
                                throw new WrongType(e12, "string-set!", 2, obj7);
                            }
                        } catch (ClassCastException e13) {
                            throw new WrongType(e13, "string-set!", 1, obj6);
                        }
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "string-length", 1, x);
                }
            }
            if (this.str != Boolean.FALSE) {
                if (Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE) {
                    i = 1;
                }
            } else if (this.str != Boolean.FALSE) {
                i = 1;
            }
            return (i + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 21) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }
}
