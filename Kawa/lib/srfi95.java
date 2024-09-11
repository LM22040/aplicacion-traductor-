package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.standard.Scheme;
import kawa.standard.append;

/* compiled from: srfi95.scm */
/* loaded from: classes.dex */
public class srfi95 extends ModuleBody {
    public static final ModuleMethod $Pcsort$Mnlist;
    public static final ModuleMethod $Pcsort$Mnvector;
    public static final ModuleMethod $Pcvector$Mnsort$Ex;
    public static final srfi95 $instance;
    static final IntNum Lit0;
    static final IntNum Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final IntNum Lit2;
    static final IntNum Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    static final ModuleMethod identity;
    public static final ModuleMethod merge;
    public static final ModuleMethod merge$Ex;
    public static final ModuleMethod sort;
    public static final ModuleMethod sort$Ex;
    public static final ModuleMethod sorted$Qu;

    public static void $PcSortVector(Sequence sequence, Object obj) {
        $PcSortVector(sequence, obj, Boolean.FALSE);
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("sort").readResolve();
        Lit12 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("%sort-vector").readResolve();
        Lit11 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("%vector-sort!").readResolve();
        Lit10 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("sort!").readResolve();
        Lit9 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("%sort-list").readResolve();
        Lit8 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("merge!").readResolve();
        Lit7 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("merge").readResolve();
        Lit6 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("sorted?").readResolve();
        Lit5 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("identity").readResolve();
        Lit4 = simpleSymbol9;
        Lit3 = IntNum.make(0);
        Lit2 = IntNum.make(1);
        Lit1 = IntNum.make(2);
        Lit0 = IntNum.make(-1);
        srfi95 srfi95Var = new srfi95();
        $instance = srfi95Var;
        identity = new ModuleMethod(srfi95Var, 1, simpleSymbol9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sorted$Qu = new ModuleMethod(srfi95Var, 2, simpleSymbol8, 12290);
        merge = new ModuleMethod(srfi95Var, 4, simpleSymbol7, 16387);
        merge$Ex = new ModuleMethod(srfi95Var, 6, simpleSymbol6, 16387);
        $Pcsort$Mnlist = new ModuleMethod(srfi95Var, 8, simpleSymbol5, 12291);
        sort$Ex = new ModuleMethod(srfi95Var, 9, simpleSymbol4, 12290);
        $Pcvector$Mnsort$Ex = new ModuleMethod(srfi95Var, 11, simpleSymbol3, 12291);
        $Pcsort$Mnvector = new ModuleMethod(srfi95Var, 12, simpleSymbol2, 12290);
        sort = new ModuleMethod(srfi95Var, 14, simpleSymbol, 12290);
        srfi95Var.run();
    }

    public srfi95() {
        ModuleInfo.register(this);
    }

    public static Object isSorted(Object obj, Object obj2) {
        return isSorted(obj, obj2, identity);
    }

    public static Object merge(Object obj, Object obj2, Object obj3) {
        return merge(obj, obj2, obj3, identity);
    }

    public static Object merge$Ex(Object obj, Object obj2, Object obj3) {
        return sort$ClMerge$Ex(obj, obj2, obj3, identity);
    }

    public static Object sort(Sequence sequence, Object obj) {
        return sort(sequence, obj, Boolean.FALSE);
    }

    public static Object sort$Ex(Sequence sequence, Object obj) {
        return sort$Ex(sequence, obj, Boolean.FALSE);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    static Object identity(Object x) {
        return x;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 1 ? identity(obj) : super.apply1(moduleMethod, obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match1(moduleMethod, obj, callContext);
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static Object isSorted(Object obj, Object obj2, Object obj3) {
        if (lists.isNull(obj)) {
            return Boolean.TRUE;
        }
        if (obj instanceof Sequence) {
            try {
                Sequence sequence = (Sequence) obj;
                int size = sequence.size() - 1;
                boolean z = size <= 1;
                if (z) {
                    return z ? Boolean.TRUE : Boolean.FALSE;
                }
                Object valueOf = Integer.valueOf(size - 1);
                Object apply2 = Scheme.applyToArgs.apply2(obj3, sequence.get(size));
                while (true) {
                    try {
                        boolean isNegative = numbers.isNegative(LangObjType.coerceRealNum(valueOf));
                        if (isNegative) {
                            return isNegative ? Boolean.TRUE : Boolean.FALSE;
                        }
                        try {
                            Object apply22 = Scheme.applyToArgs.apply2(obj3, sequence.get(((Number) valueOf).intValue()));
                            Object apply3 = Scheme.applyToArgs.apply3(obj2, apply22, apply2);
                            if (apply3 == Boolean.FALSE) {
                                return apply3;
                            }
                            valueOf = AddOp.$Pl.apply2(Lit0, valueOf);
                            apply2 = apply22;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "gnu.lists.Sequence.get(int)", 2, valueOf);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "negative?", 1, valueOf);
                    }
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "arr", -2, obj);
            }
        } else {
            if (lists.isNull(lists.cdr.apply1(obj))) {
                return Boolean.TRUE;
            }
            Object apply23 = Scheme.applyToArgs.apply2(obj3, lists.car.apply1(obj));
            Object apply1 = lists.cdr.apply1(obj);
            while (true) {
                boolean isNull = lists.isNull(apply1);
                if (isNull) {
                    return isNull ? Boolean.TRUE : Boolean.FALSE;
                }
                Object apply24 = Scheme.applyToArgs.apply2(obj3, lists.car.apply1(apply1));
                Object apply32 = Scheme.applyToArgs.apply3(obj2, apply24, apply23);
                try {
                    int i = ((apply32 != Boolean.FALSE ? 1 : 0) + 1) & 1;
                    if (i == 0) {
                        return i != 0 ? Boolean.TRUE : Boolean.FALSE;
                    }
                    apply1 = lists.cdr.apply1(apply1);
                    apply23 = apply24;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "x", -2, apply32);
                }
            }
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 9:
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 12:
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 14:
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 3:
            case 5:
            case 7:
            case 10:
            case 13:
            default:
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            case 4:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 6:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 8:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 9:
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 11:
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 12:
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 14:
                if (!(obj instanceof Sequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
        }
    }

    public static Object merge(Object a, Object b, Object isLess, Object key) {
        frame frameVar = new frame();
        frameVar.less$Qu = isLess;
        frameVar.key = key;
        if (lists.isNull(a)) {
            return b;
        }
        if (lists.isNull(b)) {
            return a;
        }
        return frameVar.lambda1loop(lists.car.apply1(a), Scheme.applyToArgs.apply2(frameVar.key, lists.car.apply1(a)), lists.cdr.apply1(a), lists.car.apply1(b), Scheme.applyToArgs.apply2(frameVar.key, lists.car.apply1(b)), lists.cdr.apply1(b));
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 4:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 5:
            default:
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            case 6:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
        }
    }

    /* compiled from: srfi95.scm */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        Object key;
        Object less$Qu;

        public Object lambda1loop(Object x, Object kx, Object a, Object y, Object ky, Object b) {
            if (Scheme.applyToArgs.apply3(this.less$Qu, ky, kx) != Boolean.FALSE) {
                if (lists.isNull(b)) {
                    return lists.cons(y, lists.cons(x, a));
                }
                return lists.cons(y, lambda1loop(x, kx, a, lists.car.apply1(b), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(b)), lists.cdr.apply1(b)));
            }
            if (lists.isNull(a)) {
                return lists.cons(x, lists.cons(y, b));
            }
            return lists.cons(x, lambda1loop(lists.car.apply1(a), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(a)), lists.cdr.apply1(a), y, ky, b));
        }
    }

    /* compiled from: srfi95.scm */
    /* loaded from: classes.dex */
    public class frame1 extends ModuleBody {
        Object key;
        Object less$Qu;

        public Object lambda3loop(Object r, Object a, Object kcara, Object b, Object kcarb) {
            if (Scheme.applyToArgs.apply3(this.less$Qu, kcarb, kcara) != Boolean.FALSE) {
                try {
                    ((Pair) r).setCdr(b);
                    if (lists.isNull(lists.cdr.apply1(b))) {
                        try {
                            ((Pair) b).setCdr(a);
                            return Values.empty;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "set-cdr!", 1, b);
                        }
                    }
                    return lambda3loop(b, a, kcara, lists.cdr.apply1(b), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(b)));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "set-cdr!", 1, r);
                }
            }
            try {
                ((Pair) r).setCdr(a);
                if (lists.isNull(lists.cdr.apply1(a))) {
                    try {
                        ((Pair) a).setCdr(b);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "set-cdr!", 1, a);
                    }
                }
                return lambda3loop(a, lists.cdr.apply1(a), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(a)), b, kcarb);
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "set-cdr!", 1, r);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object sort$ClMerge$Ex(Object a, Object b, Object isLess, Object key) {
        frame1 frame1Var = new frame1();
        frame1Var.less$Qu = isLess;
        frame1Var.key = key;
        if (!lists.isNull(a)) {
            if (!lists.isNull(b)) {
                Object kcara = Scheme.applyToArgs.apply2(frame1Var.key, lists.car.apply1(a));
                Object kcarb = Scheme.applyToArgs.apply2(frame1Var.key, lists.car.apply1(b));
                if (Scheme.applyToArgs.apply3(frame1Var.less$Qu, kcarb, kcara) != Boolean.FALSE) {
                    if (lists.isNull(lists.cdr.apply1(b))) {
                        try {
                            ((Pair) b).setCdr(a);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "set-cdr!", 1, b);
                        }
                    } else {
                        frame1Var.lambda3loop(b, a, kcara, lists.cdr.apply1(b), Scheme.applyToArgs.apply2(frame1Var.key, lists.cadr.apply1(b)));
                    }
                } else if (lists.isNull(lists.cdr.apply1(a))) {
                    try {
                        ((Pair) a).setCdr(b);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "set-cdr!", 1, a);
                    }
                } else {
                    frame1Var.lambda3loop(a, lists.cdr.apply1(a), Scheme.applyToArgs.apply2(frame1Var.key, lists.cadr.apply1(a)), b, kcarb);
                }
            }
            return a;
        }
        return b;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        switch (moduleMethod.selector) {
            case 4:
                return merge(obj, obj2, obj3, obj4);
            case 5:
            default:
                return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            case 6:
                return sort$ClMerge$Ex(obj, obj2, obj3, obj4);
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:23:0x0048 */
    /* JADX WARN: Incorrect condition in loop: B:8:0x002c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object $PcSortList(java.lang.Object r8, java.lang.Object r9, java.lang.Object r10) {
        /*
            kawa.lib.srfi95$frame0 r0 = new kawa.lib.srfi95$frame0
            r0.<init>()
            r0.seq = r8
            r0.less$Qu = r9
            gnu.expr.Special r1 = gnu.expr.Special.undefined
            r0.keyer = r1
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            if (r10 == r1) goto L16
            gnu.expr.GenericProc r1 = kawa.lib.lists.car
            goto L18
        L16:
            gnu.expr.ModuleMethod r1 = kawa.lib.srfi95.identity
        L18:
            r0.keyer = r1
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            java.lang.String r2 = "length"
            r3 = 1
            if (r10 == r1) goto L98
            java.lang.Object r1 = r0.seq
            r4 = 0
        L25:
            boolean r5 = kawa.lib.lists.isNull(r1)
            r4 = r5
            java.lang.String r5 = "set-car!"
            if (r4 == 0) goto L6e
            java.lang.Object r1 = r0.seq
            gnu.lists.LList r1 = (gnu.lists.LList) r1     // Catch: java.lang.ClassCastException -> L67
            int r1 = kawa.lib.lists.length(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r1 = r0.lambda2step(r1)
            r0.seq = r1
            java.lang.Object r1 = r0.seq
        L43:
            boolean r2 = kawa.lib.lists.isNull(r1)
            r4 = r2
            if (r4 == 0) goto L4d
            java.lang.Object r0 = r0.seq
            goto La8
        L4d:
            r2 = r1
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2     // Catch: java.lang.ClassCastException -> L60
            gnu.expr.GenericProc r6 = kawa.lib.lists.cdar
            java.lang.Object r6 = r6.apply1(r1)
            kawa.lib.lists.setCar$Ex(r2, r6)
            gnu.expr.GenericProc r2 = kawa.lib.lists.cdr
            java.lang.Object r1 = r2.apply1(r1)
            goto L43
        L60:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r9.<init>(r8, r5, r3, r1)
            throw r9
        L67:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r9.<init>(r8, r2, r3, r1)
            throw r9
        L6e:
            r6 = r1
            gnu.lists.Pair r6 = (gnu.lists.Pair) r6     // Catch: java.lang.ClassCastException -> L91
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            gnu.expr.GenericProc r7 = kawa.lib.lists.car
            java.lang.Object r7 = r7.apply1(r1)
            java.lang.Object r5 = r5.apply2(r10, r7)
            gnu.expr.GenericProc r7 = kawa.lib.lists.car
            java.lang.Object r7 = r7.apply1(r1)
            gnu.lists.Pair r5 = kawa.lib.lists.cons(r5, r7)
            kawa.lib.lists.setCar$Ex(r6, r5)
            gnu.expr.GenericProc r5 = kawa.lib.lists.cdr
            java.lang.Object r1 = r5.apply1(r1)
            goto L25
        L91:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r9.<init>(r8, r5, r3, r1)
            throw r9
        L98:
            java.lang.Object r1 = r0.seq
            gnu.lists.LList r1 = (gnu.lists.LList) r1     // Catch: java.lang.ClassCastException -> La9
            int r1 = kawa.lib.lists.length(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r0 = r0.lambda2step(r1)
        La8:
            return r0
        La9:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r9.<init>(r8, r2, r3, r1)
            goto Lb1
        Lb0:
            throw r9
        Lb1:
            goto Lb0
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lib.srfi95.$PcSortList(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* compiled from: srfi95.scm */
    /* loaded from: classes.dex */
    public class frame0 extends ModuleBody {
        Object keyer;
        Object less$Qu;
        Object seq;

        public Object lambda2step(Object n) {
            if (Scheme.numGrt.apply2(n, srfi95.Lit1) != Boolean.FALSE) {
                Object j = DivideOp.quotient.apply2(n, srfi95.Lit1);
                Object a = lambda2step(j);
                Object k = AddOp.$Mn.apply2(n, j);
                Object b = lambda2step(k);
                return srfi95.sort$ClMerge$Ex(a, b, this.less$Qu, this.keyer);
            }
            if (Scheme.numEqu.apply2(n, srfi95.Lit1) != Boolean.FALSE) {
                Object x = lists.car.apply1(this.seq);
                Object y = lists.cadr.apply1(this.seq);
                Object p = this.seq;
                this.seq = lists.cddr.apply1(this.seq);
                if (Scheme.applyToArgs.apply3(this.less$Qu, Scheme.applyToArgs.apply2(this.keyer, y), Scheme.applyToArgs.apply2(this.keyer, x)) != Boolean.FALSE) {
                    try {
                        ((Pair) p).setCar(y);
                        Object apply1 = lists.cdr.apply1(p);
                        try {
                            ((Pair) apply1).setCar(x);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "set-car!", 1, apply1);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "set-car!", 1, p);
                    }
                }
                Object apply12 = lists.cdr.apply1(p);
                try {
                    ((Pair) apply12).setCdr(LList.Empty);
                    return p;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "set-cdr!", 1, apply12);
                }
            }
            if (Scheme.numEqu.apply2(n, srfi95.Lit2) != Boolean.FALSE) {
                Object p2 = this.seq;
                this.seq = lists.cdr.apply1(this.seq);
                try {
                    ((Pair) p2).setCdr(LList.Empty);
                    return p2;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "set-cdr!", 1, p2);
                }
            }
            return LList.Empty;
        }
    }

    static Object rank$Mn1Array$To$List(Sequence seq) {
        Object lst = LList.Empty;
        for (int idx = seq.size() - 1; idx >= 0; idx--) {
            lst = lists.cons(seq.get(idx), lst);
        }
        return lst;
    }

    public static Object sort$Ex(Sequence seq, Object less$Qu, Object key) {
        if (lists.isList(seq)) {
            Object ret = $PcSortList(seq, less$Qu, key);
            if (ret != seq) {
                Object crt = ret;
                while (lists.cdr.apply1(crt) != seq) {
                    crt = lists.cdr.apply1(crt);
                }
                try {
                    ((Pair) crt).setCdr(ret);
                    Object scar = lists.car.apply1(seq);
                    Object scdr = lists.cdr.apply1(seq);
                    try {
                        ((Pair) seq).setCar(lists.car.apply1(ret));
                        try {
                            ((Pair) seq).setCdr(lists.cdr.apply1(ret));
                            try {
                                ((Pair) ret).setCar(scar);
                                try {
                                    ((Pair) ret).setCdr(scdr);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "set-cdr!", 1, ret);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "set-car!", 1, ret);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "set-cdr!", 1, seq);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "set-car!", 1, seq);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "set-cdr!", 1, crt);
                }
            }
            return seq;
        }
        return $PcVectorSort$Ex(seq, less$Qu, key);
    }

    public static Object $PcVectorSort$Ex(Sequence seq, Object less$Qu, Object key) {
        Object sorted = $PcSortList(rank$Mn1Array$To$List(seq), less$Qu, key);
        Object i = Lit3;
        while (!lists.isNull(sorted)) {
            seq.set(((Number) i).intValue(), lists.car.apply1(sorted));
            Object apply1 = lists.cdr.apply1(sorted);
            i = AddOp.$Pl.apply2(i, Lit2);
            sorted = apply1;
        }
        return seq;
    }

    public static void $PcSortVector(Sequence seq, Object less$Qu, Object key) {
        int dim = seq.size();
        FVector newra = vectors.makeVector(dim);
        Object sorted = $PcSortList(rank$Mn1Array$To$List(seq), less$Qu, key);
        Object i = Lit3;
        while (!lists.isNull(sorted)) {
            try {
                newra.set(((Number) i).intValue(), lists.car.apply1(sorted));
                Object apply1 = lists.cdr.apply1(sorted);
                i = AddOp.$Pl.apply2(i, Lit2);
                sorted = apply1;
            } catch (ClassCastException e) {
                throw new WrongType(e, "vector-set!", 1, i);
            }
        }
    }

    public static Object sort(Sequence seq, Object less$Qu, Object key) {
        if (lists.isList(seq)) {
            return $PcSortList(append.append$V(new Object[]{seq, LList.Empty}), less$Qu, key);
        }
        $PcSortVector(seq, less$Qu, key);
        return Values.empty;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 2:
                return isSorted(obj, obj2);
            case 9:
                try {
                    return sort$Ex((Sequence) obj, obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "sort!", 1, obj);
                }
            case 12:
                try {
                    $PcSortVector((Sequence) obj, obj2);
                    return Values.empty;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "%sort-vector", 1, obj);
                }
            case 14:
                try {
                    return sort((Sequence) obj, obj2);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "sort", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 2:
                return isSorted(obj, obj2, obj3);
            case 3:
            case 5:
            case 7:
            case 10:
            case 13:
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
            case 4:
                return merge(obj, obj2, obj3);
            case 6:
                return merge$Ex(obj, obj2, obj3);
            case 8:
                return $PcSortList(obj, obj2, obj3);
            case 9:
                try {
                    return sort$Ex((Sequence) obj, obj2, obj3);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "sort!", 1, obj);
                }
            case 11:
                try {
                    return $PcVectorSort$Ex((Sequence) obj, obj2, obj3);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "%vector-sort!", 1, obj);
                }
            case 12:
                try {
                    $PcSortVector((Sequence) obj, obj2, obj3);
                    return Values.empty;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "%sort-vector", 1, obj);
                }
            case 14:
                try {
                    return sort((Sequence) obj, obj2, obj3);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "sort", 1, obj);
                }
        }
    }
}
