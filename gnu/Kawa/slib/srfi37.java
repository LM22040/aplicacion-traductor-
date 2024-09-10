package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

/* compiled from: srfi37.scm */
/* loaded from: classes2.dex */
public class srfi37 extends ModuleBody {
    public static final srfi37 $instance;
    static final IntNum Lit0;
    static final Char Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final Char Lit2;
    static final IntNum Lit3;
    static final IntNum Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod args$Mnfold;
    public static final ModuleMethod option;
    public static final ModuleMethod option$Mnnames;
    public static final ModuleMethod option$Mnoptional$Mnarg$Qu;
    public static final ModuleMethod option$Mnprocessor;
    public static final ModuleMethod option$Mnrequired$Mnarg$Qu;
    static final Class option$Mntype;
    public static final ModuleMethod option$Qu;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("args-fold").readResolve();
        Lit11 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("option-processor").readResolve();
        Lit10 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("option-optional-arg?").readResolve();
        Lit9 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("option-required-arg?").readResolve();
        Lit8 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("option-names").readResolve();
        Lit7 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("option").readResolve();
        Lit6 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("option?").readResolve();
        Lit5 = simpleSymbol7;
        Lit4 = IntNum.make(0);
        Lit3 = IntNum.make(3);
        Lit2 = Char.make(61);
        Lit1 = Char.make(45);
        Lit0 = IntNum.make(1);
        srfi37 srfi37Var = new srfi37();
        $instance = srfi37Var;
        option$Mntype = option$Mntype.class;
        option$Qu = new ModuleMethod(srfi37Var, 25, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option = new ModuleMethod(srfi37Var, 26, simpleSymbol6, 16388);
        option$Mnnames = new ModuleMethod(srfi37Var, 27, simpleSymbol5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnrequired$Mnarg$Qu = new ModuleMethod(srfi37Var, 28, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnoptional$Mnarg$Qu = new ModuleMethod(srfi37Var, 29, simpleSymbol3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        option$Mnprocessor = new ModuleMethod(srfi37Var, 30, simpleSymbol2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        args$Mnfold = new ModuleMethod(srfi37Var, 31, simpleSymbol, -4092);
        srfi37Var.run();
    }

    public srfi37() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    public static option$Mntype option(Object names, Object required$Mnarg$Qu, Object optional$Mnarg$Qu, Object processor) {
        option$Mntype tmp = new option$Mntype();
        tmp.names = names;
        tmp.required$Mnarg$Qu = required$Mnarg$Qu;
        tmp.optional$Mnarg$Qu = optional$Mnarg$Qu;
        tmp.processor = processor;
        return tmp;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        return moduleMethod.selector == 26 ? option(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 26) {
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

    public static boolean isOption(Object obj) {
        return obj instanceof option$Mntype;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 25:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 27:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 29:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                if (!(obj instanceof option$Mntype)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
    }

    public static Object optionNames(option$Mntype obj) {
        return obj.names;
    }

    public static Object isOptionRequiredArg(option$Mntype obj) {
        return obj.required$Mnarg$Qu;
    }

    public static Object isOptionOptionalArg(option$Mntype obj) {
        return obj.optional$Mnarg$Qu;
    }

    public static Object optionProcessor(option$Mntype obj) {
        return obj.processor;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 25:
                return isOption(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 26:
            default:
                return super.apply1(moduleMethod, obj);
            case 27:
                try {
                    return optionNames((option$Mntype) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "option-names", 1, obj);
                }
            case 28:
                try {
                    return isOptionRequiredArg((option$Mntype) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "option-required-arg?", 1, obj);
                }
            case 29:
                try {
                    return isOptionOptionalArg((option$Mntype) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "option-optional-arg?", 1, obj);
                }
            case 30:
                try {
                    return optionProcessor((option$Mntype) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "option-processor", 1, obj);
                }
        }
    }

    public static Object argsFold$V(Object args, Object options, Object unrecognizedOptionProc, Object operandProc, Object[] argsArray) {
        frame frameVar = new frame();
        frameVar.options = options;
        frameVar.unrecognized$Mnoption$Mnproc = unrecognizedOptionProc;
        frameVar.operand$Mnproc = operandProc;
        LList seeds = LList.makeList(argsArray, 0);
        return frameVar.lambda5scanArgs(args, seeds);
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        if (moduleMethod.selector != 31) {
            return super.applyN(moduleMethod, objArr);
        }
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        Object obj3 = objArr[2];
        Object obj4 = objArr[3];
        int length = objArr.length - 4;
        Object[] objArr2 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return argsFold$V(obj, obj2, obj3, obj4, objArr2);
            }
            objArr2[length] = objArr[length + 4];
        }
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        if (moduleMethod.selector != 31) {
            return super.matchN(moduleMethod, objArr, callContext);
        }
        callContext.values = objArr;
        callContext.proc = moduleMethod;
        callContext.pc = 5;
        return 0;
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame extends ModuleBody {
        Object operand$Mnproc;
        Object options;
        Object unrecognized$Mnoption$Mnproc;

        public static Object lambda1find(Object l, Object $Qu) {
            return lists.isNull(l) ? Boolean.FALSE : Scheme.applyToArgs.apply2($Qu, lists.car.apply1(l)) != Boolean.FALSE ? lists.car.apply1(l) : lambda1find(lists.cdr.apply1(l), $Qu);
        }

        public Object lambda2findOption(Object name) {
            frame0 frame0Var = new frame0();
            frame0Var.staticLink = this;
            frame0Var.name = name;
            return lambda1find(this.options, frame0Var.lambda$Fn1);
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x00a4, code lost:
        
            if (r4 != java.lang.Boolean.FALSE) goto L40;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00cf, code lost:
        
            r4 = r2.option;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00d3, code lost:
        
            r4 = gnu.kawa.slib.srfi37.isOptionRequiredArg((gnu.kawa.slib.option$Mntype) r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00d9, code lost:
        
            if (r4 == java.lang.Boolean.FALSE) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00e1, code lost:
        
            if (kawa.lib.lists.isPair(r2.args) == false) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00f9, code lost:
        
            return kawa.standard.call_with_values.callWithValues(r2.lambda$Fn7, r2.lambda$Fn8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:?, code lost:
        
            return kawa.standard.call_with_values.callWithValues(r2.lambda$Fn5, r2.lambda$Fn6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00e6, code lost:
        
            if (r4 == java.lang.Boolean.FALSE) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00fa, code lost:
        
            r7 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0100, code lost:
        
            throw new gnu.mapping.WrongType(r7, "option-required-arg?", 0, r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
        
            return kawa.standard.call_with_values.callWithValues(r2.lambda$Fn3, r2.lambda$Fn4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00b1, code lost:
        
            if (gnu.kawa.slib.srfi37.isOptionOptionalArg((gnu.kawa.slib.option$Mntype) r4) != java.lang.Boolean.FALSE) goto L40;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00c4, code lost:
        
            if (r4 != false) goto L40;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object lambda3scanShortOptions(java.lang.Object r4, java.lang.Object r5, java.lang.Object r6, java.lang.Object r7) {
            /*
                Method dump skipped, instructions count: 298
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi37.frame.lambda3scanShortOptions(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public Object lambda4scanOperands(Object operands, Object seeds) {
            frame2 frame2Var = new frame2();
            frame2Var.staticLink = this;
            frame2Var.operands = operands;
            frame2Var.seeds = seeds;
            if (lists.isNull(frame2Var.operands)) {
                return Scheme.apply.apply2(misc.values, frame2Var.seeds);
            }
            return call_with_values.callWithValues(frame2Var.lambda$Fn9, frame2Var.lambda$Fn10);
        }

        /* JADX WARN: Code restructure failed: missing block: B:101:0x0218, code lost:
        
            r9 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:103:0x021e, code lost:
        
            throw new gnu.mapping.WrongType(r9, "string-ref", 1, r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:104:0x021f, code lost:
        
            if (r9 == false) goto L157;
         */
        /* JADX WARN: Code restructure failed: missing block: B:105:0x0200, code lost:
        
            r9 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:107:0x0253, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:109:0x0259, code lost:
        
            throw new gnu.mapping.WrongType(r1, "string-length", 1, r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:110:0x0182, code lost:
        
            r9 = r1.arg;
         */
        /* JADX WARN: Code restructure failed: missing block: B:112:0x0184, code lost:
        
            r9 = (java.lang.CharSequence) r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:113:0x0186, code lost:
        
            r4 = r1.arg;
         */
        /* JADX WARN: Code restructure failed: missing block: B:116:0x018a, code lost:
        
            r1.name = kawa.lib.strings.substring(r9, 2, kawa.lib.strings.stringLength((java.lang.CharSequence) r4));
            r9 = lambda2findOption(r1.name);
         */
        /* JADX WARN: Code restructure failed: missing block: B:117:0x019c, code lost:
        
            if (r9 == java.lang.Boolean.FALSE) goto L108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:118:0x019f, code lost:
        
            r9 = gnu.kawa.slib.srfi37.option(gnu.lists.LList.list1(r1.name), java.lang.Boolean.FALSE, java.lang.Boolean.FALSE, r8.unrecognized$Mnoption$Mnproc);
         */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x01af, code lost:
        
            r1.option = r9;
            r9 = r1.option;
         */
        /* JADX WARN: Code restructure failed: missing block: B:122:0x01b5, code lost:
        
            r9 = gnu.kawa.slib.srfi37.isOptionRequiredArg((gnu.kawa.slib.option$Mntype) r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:123:0x01bb, code lost:
        
            if (r9 == java.lang.Boolean.FALSE) goto L116;
         */
        /* JADX WARN: Code restructure failed: missing block: B:125:0x01c3, code lost:
        
            if (kawa.lib.lists.isPair(r1.args) == false) goto L119;
         */
        /* JADX WARN: Code restructure failed: missing block: B:127:?, code lost:
        
            return kawa.standard.call_with_values.callWithValues(r1.lambda$Fn21, r1.lambda$Fn22);
         */
        /* JADX WARN: Code restructure failed: missing block: B:129:?, code lost:
        
            return kawa.standard.call_with_values.callWithValues(r1.lambda$Fn19, r1.lambda$Fn20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:131:0x01c8, code lost:
        
            if (r9 == java.lang.Boolean.FALSE) goto L119;
         */
        /* JADX WARN: Code restructure failed: missing block: B:133:0x01dd, code lost:
        
            r10 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:135:0x01e5, code lost:
        
            throw new gnu.mapping.WrongType(r10, "option-required-arg?", 0, r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:137:0x01e6, code lost:
        
            r9 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:139:0x01ec, code lost:
        
            throw new gnu.mapping.WrongType(r9, "string-length", 1, r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x01ed, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:143:0x01f3, code lost:
        
            throw new gnu.mapping.WrongType(r0, "substring", 1, r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:148:0x0176, code lost:
        
            if (r9 != false) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:153:0x0180, code lost:
        
            if (r9 != false) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x016c, code lost:
        
            if (kawa.lib.characters.isChar$Eq(r9, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r6, 1))) != false) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x01f4, code lost:
        
            r9 = r1.arg;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x01fc, code lost:
        
            if (kawa.lib.strings.stringLength((java.lang.CharSequence) r9) <= 1) goto L135;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x01fe, code lost:
        
            r9 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0201, code lost:
        
            if (r9 == false) goto L145;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0203, code lost:
        
            r9 = gnu.kawa.slib.srfi37.Lit1;
            r2 = r1.arg;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0215, code lost:
        
            if (kawa.lib.characters.isChar$Eq(r9, gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r2, 0))) == false) goto L157;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x0252, code lost:
        
            return kawa.standard.call_with_values.callWithValues(r1.lambda$Fn23, r1.lambda$Fn24);
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0221, code lost:
        
            r9 = r1.arg;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x0223, code lost:
        
            r9 = (java.lang.CharSequence) r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0225, code lost:
        
            r2 = r1.arg;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:?, code lost:
        
            return lambda3scanShortOptions(gnu.kawa.slib.srfi37.Lit4, kawa.lib.strings.substring(r9, 1, kawa.lib.strings.stringLength((java.lang.CharSequence) r2)), r1.args, r1.seeds);
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x023c, code lost:
        
            r9 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x0242, code lost:
        
            throw new gnu.mapping.WrongType(r9, "string-length", 1, r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x0243, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:99:0x0249, code lost:
        
            throw new gnu.mapping.WrongType(r0, "substring", 1, r9);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object lambda5scanArgs(java.lang.Object r9, java.lang.Object r10) {
            /*
                Method dump skipped, instructions count: 618
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi37.frame.lambda5scanArgs(java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame0 extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        final ModuleMethod lambda$Fn2;
        Object name;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:75");
            this.lambda$Fn2 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 2, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:72");
            this.lambda$Fn1 = moduleMethod2;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 1:
                    return lambda7(obj) ? Boolean.TRUE : Boolean.FALSE;
                case 2:
                    return lambda6(obj);
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        Object lambda6(Object option) {
            try {
                return frame.lambda1find(srfi37.optionNames((option$Mntype) option), this.lambda$Fn2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-names", 0, option);
            }
        }

        boolean lambda7(Object test$Mnname) {
            return IsEqual.apply(this.name, test$Mnname);
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
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame1 extends ModuleBody {
        Object args;
        Object index;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
        final ModuleMethod lambda$Fn4 = new ModuleMethod(this, 4, null, -4096);
        final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
        final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 6, null, -4096);
        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
        final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 8, null, -4096);
        char name;
        Object option;
        Object seeds;
        Object shorts;
        frame staticLink;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 3:
                    return lambda8();
                case 4:
                case 6:
                default:
                    return super.apply0(moduleMethod);
                case 5:
                    return lambda10();
                case 7:
                    return lambda12();
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            switch (moduleMethod.selector) {
                case 4:
                    return lambda9$V(objArr);
                case 5:
                case 7:
                default:
                    return super.applyN(moduleMethod, objArr);
                case 6:
                    return lambda11$V(objArr);
                case 8:
                    return lambda13$V(objArr);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 3:
                case 5:
                case 7:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 4:
                case 6:
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 4:
                case 6:
                case 8:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 5:
                case 7:
                default:
                    return super.matchN(moduleMethod, objArr, callContext);
            }
        }

        Object lambda9$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.lambda5scanArgs(this.args, x);
        }

        Object lambda8() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = Char.make(this.name);
                Object obj2 = this.shorts;
                try {
                    CharSequence charSequence = (CharSequence) obj2;
                    Object apply2 = AddOp.$Pl.apply2(this.index, srfi37.Lit0);
                    try {
                        int intValue = ((Number) apply2).intValue();
                        Object obj3 = this.shorts;
                        try {
                            objArr[3] = strings.substring(charSequence, intValue, strings.stringLength((CharSequence) obj3));
                            objArr[4] = this.seeds;
                            return apply.applyN(objArr);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-length", 1, obj3);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "substring", 2, apply2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "substring", 1, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "option-processor", 0, obj);
            }
        }

        Object lambda11$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), x);
        }

        Object lambda10() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = Char.make(this.name);
                objArr[3] = lists.car.apply1(this.args);
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }

        Object lambda13$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.lambda3scanShortOptions(AddOp.$Pl.apply2(this.index, srfi37.Lit0), this.shorts, this.args, x);
        }

        Object lambda12() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = Char.make(this.name);
                objArr[3] = Boolean.FALSE;
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame2 extends ModuleBody {
        Object operands;
        Object seeds;
        frame staticLink;
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
        final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 10, null, -4096);

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 9 ? lambda14() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            return moduleMethod.selector == 10 ? lambda15$V(objArr) : super.applyN(moduleMethod, objArr);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 9) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        Object lambda14() {
            return Scheme.apply.apply3(this.staticLink.operand$Mnproc, lists.car.apply1(this.operands), this.seeds);
        }

        Object lambda15$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.lambda4scanOperands(lists.cdr.apply1(this.operands), x);
        }
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame3 extends ModuleBody {
        Object arg;
        Object args;
        final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 17, null, 0);
        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 18, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 19, null, 0);
        final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 20, null, -4096);
        final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 21, null, 0);
        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, -4096);
        final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 23, null, 0);
        final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 24, null, -4096);
        CharSequence name;
        Object option;
        Object seeds;
        frame staticLink;
        Object temp;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 17:
                    return lambda16();
                case 18:
                case 20:
                case 22:
                default:
                    return super.apply0(moduleMethod);
                case 19:
                    return lambda24();
                case 21:
                    return lambda26();
                case 23:
                    return lambda28();
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 18 ? lambda17(obj) : super.apply1(moduleMethod, obj);
        }

        @Override // gnu.expr.ModuleBody
        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            switch (moduleMethod.selector) {
                case 20:
                    return lambda25$V(objArr);
                case 21:
                case 23:
                default:
                    return super.applyN(moduleMethod, objArr);
                case 22:
                    return lambda27$V(objArr);
                case 24:
                    return lambda29$V(objArr);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 17:
                case 19:
                case 21:
                case 23:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 18:
                case 20:
                case 22:
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 18) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 20:
                case 22:
                case 24:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 21:
                case 23:
                default:
                    return super.matchN(moduleMethod, objArr, callContext);
            }
        }

        Object lambda17(Object x) {
            frame4 frame4Var = new frame4();
            frame4Var.staticLink = this;
            frame4Var.x = x;
            return call_with_values.callWithValues(frame4Var.lambda$Fn13, frame4Var.lambda$Fn14);
        }

        CharSequence lambda16() {
            Object obj = this.arg;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object obj2 = this.temp;
                try {
                    return strings.substring(charSequence, 2, ((Number) obj2).intValue());
                } catch (ClassCastException e) {
                    throw new WrongType(e, "substring", 3, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "substring", 1, obj);
            }
        }

        Object lambda25$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), x);
        }

        Object lambda24() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = this.name;
                objArr[3] = lists.car.apply1(this.args);
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }

        Object lambda27$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.lambda5scanArgs(this.args, x);
        }

        Object lambda26() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.option;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.option;
                objArr[2] = this.name;
                objArr[3] = Boolean.FALSE;
                objArr[4] = this.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }

        Object lambda28() {
            return Scheme.apply.apply3(this.staticLink.operand$Mnproc, this.arg, this.seeds);
        }

        Object lambda29$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.lambda5scanArgs(this.args, x);
        }
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame4 extends ModuleBody {
        final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, null, 0);
        final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        frame3 staticLink;
        Object x;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 15 ? lambda18() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 16 ? lambda19(obj) : super.apply1(moduleMethod, obj);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 15) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 16) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        Object lambda19(Object x) {
            frame5 frame5Var = new frame5();
            frame5Var.staticLink = this;
            frame5Var.x = x;
            return call_with_values.callWithValues(frame5Var.lambda$Fn15, frame5Var.lambda$Fn16);
        }

        CharSequence lambda18() {
            Object obj = this.staticLink.arg;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object apply2 = AddOp.$Pl.apply2(this.staticLink.temp, srfi37.Lit0);
                try {
                    int intValue = ((Number) apply2).intValue();
                    Object obj2 = this.staticLink.arg;
                    try {
                        return strings.substring(charSequence, intValue, strings.stringLength((CharSequence) obj2));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-length", 1, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 2, apply2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, obj);
            }
        }
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame5 extends ModuleBody {
        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        frame4 staticLink;
        Object x;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 13 ? lambda20() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 14 ? lambda21(obj) : super.apply1(moduleMethod, obj);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 13) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 14) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        Object lambda21(Object x) {
            frame6 frame6Var = new frame6();
            frame6Var.staticLink = this;
            frame6Var.x = x;
            return call_with_values.callWithValues(frame6Var.lambda$Fn17, frame6Var.lambda$Fn18);
        }

        Object lambda20() {
            Object x = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
            return x != Boolean.FALSE ? x : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
        }
    }

    /* compiled from: srfi37.scm */
    /* loaded from: classes2.dex */
    public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        frame5 staticLink;
        Object x;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 11 ? lambda22() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            return moduleMethod.selector == 12 ? lambda23$V(objArr) : super.applyN(moduleMethod, objArr);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 11) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 12) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        Object lambda23$V(Object[] argsArray) {
            LList x = LList.makeList(argsArray, 0);
            return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, x);
        }

        Object lambda22() {
            Apply apply = Scheme.apply;
            Object[] objArr = new Object[5];
            Object obj = this.x;
            try {
                objArr[0] = srfi37.optionProcessor((option$Mntype) obj);
                objArr[1] = this.x;
                objArr[2] = this.staticLink.staticLink.x;
                objArr[3] = this.staticLink.x;
                objArr[4] = this.staticLink.staticLink.staticLink.seeds;
                return apply.applyN(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "option-processor", 0, obj);
            }
        }
    }
}
