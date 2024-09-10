package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Format;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;

/* compiled from: genwrite.scm */
/* loaded from: classes2.dex */
public class genwrite extends ModuleBody {
    public static final genwrite $instance;
    static final Char Lit0;
    static final IntNum Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final IntNum Lit15;
    static final IntNum Lit16;
    static final IntNum Lit17;
    static final IntNum Lit18;
    static final IntNum Lit19;
    static final SimpleSymbol Lit2;
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
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod generic$Mnwrite;
    public static final ModuleMethod reverse$Mnstring$Mnappend;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("reverse-string-append").readResolve();
        Lit35 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("generic-write").readResolve();
        Lit34 = simpleSymbol2;
        Lit33 = (SimpleSymbol) new SimpleSymbol(LispLanguage.unquotesplicing_sym).readResolve();
        Lit32 = (SimpleSymbol) new SimpleSymbol(LispLanguage.unquote_sym).readResolve();
        Lit31 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit30 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit29 = (SimpleSymbol) new SimpleSymbol("pp-DO").readResolve();
        Lit28 = (SimpleSymbol) new SimpleSymbol("pp-BEGIN").readResolve();
        Lit27 = (SimpleSymbol) new SimpleSymbol("pp-LET").readResolve();
        Lit26 = (SimpleSymbol) new SimpleSymbol("pp-AND").readResolve();
        Lit25 = (SimpleSymbol) new SimpleSymbol("pp-CASE").readResolve();
        Lit24 = (SimpleSymbol) new SimpleSymbol("pp-COND").readResolve();
        Lit23 = (SimpleSymbol) new SimpleSymbol("pp-IF").readResolve();
        Lit22 = (SimpleSymbol) new SimpleSymbol("pp-LAMBDA").readResolve();
        Lit21 = (SimpleSymbol) new SimpleSymbol("pp-expr-list").readResolve();
        Lit20 = (SimpleSymbol) new SimpleSymbol("pp-expr").readResolve();
        Lit19 = IntNum.make(2);
        Lit18 = IntNum.make(50);
        Lit17 = IntNum.make(1);
        Lit16 = IntNum.make(8);
        Lit15 = IntNum.make(7);
        Lit14 = (SimpleSymbol) new SimpleSymbol("do").readResolve();
        Lit13 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit12 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit11 = (SimpleSymbol) new SimpleSymbol("or").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
        Lit9 = (SimpleSymbol) new SimpleSymbol("case").readResolve();
        Lit8 = (SimpleSymbol) new SimpleSymbol("cond").readResolve();
        Lit7 = (SimpleSymbol) new SimpleSymbol("set!").readResolve();
        Lit6 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit5 = (SimpleSymbol) new SimpleSymbol("define").readResolve();
        Lit4 = (SimpleSymbol) new SimpleSymbol("letrec").readResolve();
        Lit3 = (SimpleSymbol) new SimpleSymbol("let*").readResolve();
        Lit2 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit1 = IntNum.make(0);
        Lit0 = Char.make(10);
        genwrite genwriteVar = new genwrite();
        $instance = genwriteVar;
        generic$Mnwrite = new ModuleMethod(genwriteVar, 12, simpleSymbol2, 16388);
        reverse$Mnstring$Mnappend = new ModuleMethod(genwriteVar, 13, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        genwriteVar.run();
    }

    public genwrite() {
        ModuleInfo.register(this);
    }

    public static Object genericWrite(Object obj, Object isDisplay, Object width, Object output) {
        frame closureEnv = new frame();
        closureEnv.display$Qu = isDisplay;
        closureEnv.width = width;
        closureEnv.output = output;
        if (closureEnv.width != Boolean.FALSE) {
            CharSequence makeString = strings.makeString(1, Lit0);
            Object col = Lit1;
            frame0 frame0Var = new frame0();
            frame0Var.staticLink = closureEnv;
            Procedure procedure = frame0Var.pp$Mnexpr;
            Procedure procedure2 = frame0Var.pp$Mnexpr$Mnlist;
            Procedure procedure3 = frame0Var.pp$MnLAMBDA;
            Procedure procedure4 = frame0Var.pp$MnIF;
            Procedure procedure5 = frame0Var.pp$MnCOND;
            Procedure procedure6 = frame0Var.pp$MnCASE;
            Procedure procedure7 = frame0Var.pp$MnAND;
            Procedure procedure8 = frame0Var.pp$MnLET;
            Procedure procedure9 = frame0Var.pp$MnBEGIN;
            frame0Var.pp$MnDO = frame0Var.pp$MnDO;
            frame0Var.pp$MnBEGIN = procedure9;
            frame0Var.pp$MnLET = procedure8;
            frame0Var.pp$MnAND = procedure7;
            frame0Var.pp$MnCASE = procedure6;
            frame0Var.pp$MnCOND = procedure5;
            frame0Var.pp$MnIF = procedure4;
            frame0Var.pp$MnLAMBDA = procedure3;
            frame0Var.pp$Mnexpr$Mnlist = procedure2;
            frame0Var.pp$Mnexpr = procedure;
            return closureEnv.lambda4out(makeString, frame0Var.lambda7pr(obj, col, col, frame0Var.pp$Mnexpr));
        }
        return closureEnv.lambda5wr(obj, Lit1);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        return moduleMethod.selector == 12 ? genericWrite(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
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

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    /* compiled from: genwrite.scm */
    /* loaded from: classes2.dex */
    public class frame extends ModuleBody {
        Object display$Qu;
        Object output;
        Object width;

        public static Object lambda1isReadMacro(Object obj) {
            Object apply2;
            Object apply22;
            Object apply1 = lists.car.apply1(obj);
            Object apply12 = lists.cdr.apply1(obj);
            Object apply23 = Scheme.isEqv.apply2(apply1, genwrite.Lit30);
            if (apply23 == Boolean.FALSE ? (apply2 = Scheme.isEqv.apply2(apply1, genwrite.Lit31)) == Boolean.FALSE ? (apply22 = Scheme.isEqv.apply2(apply1, genwrite.Lit32)) == Boolean.FALSE ? Scheme.isEqv.apply2(apply1, genwrite.Lit33) == Boolean.FALSE : apply22 == Boolean.FALSE : apply2 == Boolean.FALSE : apply23 == Boolean.FALSE) {
                return Boolean.FALSE;
            }
            boolean isPair = lists.isPair(apply12);
            return isPair ? lists.isNull(lists.cdr.apply1(apply12)) ? Boolean.TRUE : Boolean.FALSE : isPair ? Boolean.TRUE : Boolean.FALSE;
        }

        public static Object lambda2readMacroBody(Object l) {
            return lists.cadr.apply1(l);
        }

        public static Object lambda3readMacroPrefix(Object l) {
            Object head = lists.car.apply1(l);
            lists.cdr.apply1(l);
            return Scheme.isEqv.apply2(head, genwrite.Lit30) != Boolean.FALSE ? "'" : Scheme.isEqv.apply2(head, genwrite.Lit31) != Boolean.FALSE ? "`" : Scheme.isEqv.apply2(head, genwrite.Lit32) != Boolean.FALSE ? "," : Scheme.isEqv.apply2(head, genwrite.Lit33) != Boolean.FALSE ? ",@" : Values.empty;
        }

        public Object lambda4out(Object str, Object col) {
            if (col == Boolean.FALSE) {
                return col;
            }
            Object x = Scheme.applyToArgs.apply2(this.output, str);
            if (x == Boolean.FALSE) {
                return x;
            }
            try {
                return AddOp.$Pl.apply2(col, Integer.valueOf(strings.stringLength((CharSequence) str)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, str);
            }
        }

        public Object lambda5wr(Object obj, Object col) {
            Object l;
            Object expr;
            Object obj2;
            if (lists.isPair(obj)) {
                if (lambda1isReadMacro(obj) != Boolean.FALSE) {
                    return lambda5wr(lambda2readMacroBody(obj), lambda4out(lambda3readMacroPrefix(obj), col));
                }
                l = obj;
                expr = col;
            } else if (lists.isNull(obj)) {
                l = obj;
                expr = col;
            } else if (vectors.isVector(obj)) {
                try {
                    l = vectors.vector$To$List((FVector) obj);
                    expr = lambda4out("#", col);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "vector->list", 1, obj);
                }
            } else {
                Object[] objArr = new Object[2];
                objArr[0] = this.display$Qu != Boolean.FALSE ? "~a" : "~s";
                objArr[1] = obj;
                return lambda4out(Format.formatToString(0, objArr), col);
            }
            if (lists.isPair(l)) {
                Object l2 = lists.cdr.apply1(l);
                Object col2 = expr != Boolean.FALSE ? lambda5wr(lists.car.apply1(l), lambda4out("(", expr)) : expr;
                while (true) {
                    if (col2 == Boolean.FALSE) {
                        obj2 = col2;
                        break;
                    }
                    if (lists.isPair(l2)) {
                        Object l3 = lists.cdr.apply1(l2);
                        col2 = lambda5wr(lists.car.apply1(l2), lambda4out(" ", col2));
                        l2 = l3;
                    } else if (lists.isNull(l2)) {
                        obj2 = lambda4out(")", col2);
                    } else {
                        obj2 = lambda4out(")", lambda5wr(l2, lambda4out(" . ", col2)));
                    }
                }
                return obj2;
            }
            return lambda4out("()", expr);
        }
    }

    /* compiled from: genwrite.scm */
    /* loaded from: classes2.dex */
    public class frame0 extends ModuleBody {
        frame staticLink;
        Procedure pp$Mnexpr = new ModuleMethod(this, 2, genwrite.Lit20, 12291);
        Procedure pp$Mnexpr$Mnlist = new ModuleMethod(this, 3, genwrite.Lit21, 12291);
        Procedure pp$MnLAMBDA = new ModuleMethod(this, 4, genwrite.Lit22, 12291);
        Procedure pp$MnIF = new ModuleMethod(this, 5, genwrite.Lit23, 12291);
        Procedure pp$MnCOND = new ModuleMethod(this, 6, genwrite.Lit24, 12291);
        Procedure pp$MnCASE = new ModuleMethod(this, 7, genwrite.Lit25, 12291);
        Procedure pp$MnAND = new ModuleMethod(this, 8, genwrite.Lit26, 12291);
        Procedure pp$MnLET = new ModuleMethod(this, 9, genwrite.Lit27, 12291);
        Procedure pp$MnBEGIN = new ModuleMethod(this, 10, genwrite.Lit28, 12291);
        Procedure pp$MnDO = new ModuleMethod(this, 11, genwrite.Lit29, 12291);

        public Object lambda6indent(Object to, Object col) {
            Object n;
            Object x;
            if (col == Boolean.FALSE) {
                return col;
            }
            if (Scheme.numLss.apply2(to, col) != Boolean.FALSE) {
                Object x2 = this.staticLink.lambda4out(strings.makeString(1, genwrite.Lit0), col);
                if (x2 == Boolean.FALSE) {
                    return x2;
                }
                x = genwrite.Lit1;
                n = to;
            } else {
                n = AddOp.$Mn.apply2(to, col);
                x = col;
            }
            while (true) {
                if (Scheme.numGrt.apply2(n, genwrite.Lit1) == Boolean.FALSE) {
                    break;
                }
                if (Scheme.numGrt.apply2(n, genwrite.Lit15) != Boolean.FALSE) {
                    n = AddOp.$Mn.apply2(n, genwrite.Lit16);
                    x = this.staticLink.lambda4out("        ", x);
                } else {
                    try {
                        x = this.staticLink.lambda4out(strings.substring("        ", 0, ((Number) n).intValue()), x);
                        break;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, n);
                    }
                }
            }
            return x;
        }

        public Object lambda7pr(Object obj, Object col, Object extra, Object pp$Mnpair) {
            frame1 frame1Var = new frame1();
            frame1Var.staticLink = this;
            boolean x = lists.isPair(obj);
            if (!x ? vectors.isVector(obj) : x) {
                LList lList = LList.Empty;
                frame1Var.left = numbers.min(AddOp.$Pl.apply2(AddOp.$Mn.apply2(AddOp.$Mn.apply2(this.staticLink.width, col), extra), genwrite.Lit17), genwrite.Lit18);
                frame1Var.result = lList;
                genwrite.genericWrite(obj, this.staticLink.display$Qu, Boolean.FALSE, frame1Var.lambda$Fn1);
                if (Scheme.numGrt.apply2(frame1Var.left, genwrite.Lit1) != Boolean.FALSE) {
                    return this.staticLink.lambda4out(genwrite.reverseStringAppend(frame1Var.result), col);
                }
                if (lists.isPair(obj)) {
                    return Scheme.applyToArgs.apply4(pp$Mnpair, obj, col, extra);
                }
                try {
                    return lambda10ppList(vectors.vector$To$List((FVector) obj), this.staticLink.lambda4out("#", col), extra, this.pp$Mnexpr);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "vector->list", 1, obj);
                }
            }
            return this.staticLink.lambda5wr(obj, col);
        }

        public Object lambda8ppExpr(Object expr, Object col, Object extra) {
            Object x;
            Object x2;
            Object x3;
            Object head;
            if (frame.lambda1isReadMacro(expr) != Boolean.FALSE) {
                return lambda7pr(frame.lambda2readMacroBody(expr), this.staticLink.lambda4out(frame.lambda3readMacroPrefix(expr), col), extra, this.pp$Mnexpr);
            }
            Object head2 = lists.car.apply1(expr);
            if (!misc.isSymbol(head2)) {
                return lambda10ppList(expr, col, extra, this.pp$Mnexpr);
            }
            Object x4 = Scheme.isEqv.apply2(head2, genwrite.Lit2);
            if (x4 == Boolean.FALSE ? (x = Scheme.isEqv.apply2(head2, genwrite.Lit3)) == Boolean.FALSE ? (x2 = Scheme.isEqv.apply2(head2, genwrite.Lit4)) == Boolean.FALSE ? Scheme.isEqv.apply2(head2, genwrite.Lit5) == Boolean.FALSE : x2 == Boolean.FALSE : x == Boolean.FALSE : x4 == Boolean.FALSE) {
                Object x5 = Scheme.isEqv.apply2(head2, genwrite.Lit6);
                if (x5 == Boolean.FALSE ? Scheme.isEqv.apply2(head2, genwrite.Lit7) != Boolean.FALSE : x5 != Boolean.FALSE) {
                    x3 = this.pp$MnIF;
                } else if (Scheme.isEqv.apply2(head2, genwrite.Lit8) != Boolean.FALSE) {
                    x3 = this.pp$MnCOND;
                } else if (Scheme.isEqv.apply2(head2, genwrite.Lit9) != Boolean.FALSE) {
                    x3 = this.pp$MnCASE;
                } else {
                    Object x6 = Scheme.isEqv.apply2(head2, genwrite.Lit10);
                    x3 = (x6 == Boolean.FALSE ? Scheme.isEqv.apply2(head2, genwrite.Lit11) == Boolean.FALSE : x6 == Boolean.FALSE) ? Scheme.isEqv.apply2(head2, genwrite.Lit12) != Boolean.FALSE ? this.pp$MnLET : Scheme.isEqv.apply2(head2, genwrite.Lit13) != Boolean.FALSE ? this.pp$MnBEGIN : Scheme.isEqv.apply2(head2, genwrite.Lit14) != Boolean.FALSE ? this.pp$MnDO : Boolean.FALSE : this.pp$MnAND;
                }
            } else {
                x3 = this.pp$MnLAMBDA;
            }
            Object proc = x3;
            if (proc != Boolean.FALSE) {
                head = Scheme.applyToArgs.apply4(proc, expr, col, extra);
            } else {
                try {
                    head = strings.stringLength(((Symbol) head2).toString()) > 5 ? lambda12ppGeneral(expr, col, extra, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr) : lambda9ppCall(expr, col, extra, this.pp$Mnexpr);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, head2);
                }
            }
            return head;
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
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 4:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 5:
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
                case 7:
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
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 10:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                case 11:
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

        public Object lambda9ppCall(Object expr, Object col, Object extra, Object pp$Mnitem) {
            Object col$St = this.staticLink.lambda5wr(lists.car.apply1(expr), this.staticLink.lambda4out("(", col));
            if (col == Boolean.FALSE) {
                return col;
            }
            return lambda11ppDown(lists.cdr.apply1(expr), col$St, AddOp.$Pl.apply2(col$St, genwrite.Lit17), extra, pp$Mnitem);
        }

        public Object lambda10ppList(Object l, Object col, Object extra, Object pp$Mnitem) {
            Object col2 = this.staticLink.lambda4out("(", col);
            return lambda11ppDown(l, col2, col2, extra, pp$Mnitem);
        }

        public Object lambda11ppDown(Object l, Object col1, Object col2, Object extra, Object pp$Mnitem) {
            Object lambda4out;
            Object l2 = l;
            Object col = col1;
            while (col != Boolean.FALSE) {
                if (!lists.isPair(l2)) {
                    if (lists.isNull(l2)) {
                        lambda4out = this.staticLink.lambda4out(")", col);
                    } else {
                        frame frameVar = this.staticLink;
                        lambda4out = frameVar.lambda4out(")", lambda7pr(l2, lambda6indent(col2, frameVar.lambda4out(".", lambda6indent(col2, col))), AddOp.$Pl.apply2(extra, genwrite.Lit17), pp$Mnitem));
                    }
                    Object col3 = lambda4out;
                    return col3;
                }
                Object rest = lists.cdr.apply1(l2);
                col = lambda7pr(lists.car.apply1(l2), lambda6indent(col2, col), lists.isNull(rest) ? AddOp.$Pl.apply2(extra, genwrite.Lit17) : genwrite.Lit1, pp$Mnitem);
                l2 = rest;
            }
            return col;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0160  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x016d  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00d1  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00db  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object lambda12ppGeneral(java.lang.Object r41, java.lang.Object r42, java.lang.Object r43, java.lang.Object r44, java.lang.Object r45, java.lang.Object r46, java.lang.Object r47) {
            /*
                Method dump skipped, instructions count: 464
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.genwrite.frame0.lambda12ppGeneral(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }

        public Object lambda13ppExprList(Object l, Object col, Object extra) {
            return lambda10ppList(l, col, extra, this.pp$Mnexpr);
        }

        public Object lambda14pp$MnLAMBDA(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda15pp$MnIF(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda16pp$MnCOND(Object expr, Object col, Object extra) {
            return lambda9ppCall(expr, col, extra, this.pp$Mnexpr$Mnlist);
        }

        public Object lambda17pp$MnCASE(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr$Mnlist);
        }

        public Object lambda18pp$MnAND(Object expr, Object col, Object extra) {
            return lambda9ppCall(expr, col, extra, this.pp$Mnexpr);
        }

        public Object lambda19pp$MnLET(Object expr, Object col, Object extra) {
            Object rest = lists.cdr.apply1(expr);
            boolean x = lists.isPair(rest);
            if (x) {
                x = misc.isSymbol(lists.car.apply1(rest));
            }
            return lambda12ppGeneral(expr, col, extra, x ? Boolean.TRUE : Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
        }

        public Object lambda20pp$MnBEGIN(Object expr, Object col, Object extra) {
            return lambda12ppGeneral(expr, col, extra, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr);
        }

        @Override // gnu.expr.ModuleBody
        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            switch (moduleMethod.selector) {
                case 2:
                    return lambda8ppExpr(obj, obj2, obj3);
                case 3:
                    return lambda13ppExprList(obj, obj2, obj3);
                case 4:
                    return lambda14pp$MnLAMBDA(obj, obj2, obj3);
                case 5:
                    return lambda15pp$MnIF(obj, obj2, obj3);
                case 6:
                    return lambda16pp$MnCOND(obj, obj2, obj3);
                case 7:
                    return lambda17pp$MnCASE(obj, obj2, obj3);
                case 8:
                    return lambda18pp$MnAND(obj, obj2, obj3);
                case 9:
                    return lambda19pp$MnLET(obj, obj2, obj3);
                case 10:
                    return lambda20pp$MnBEGIN(obj, obj2, obj3);
                case 11:
                    return lambda21pp$MnDO(obj, obj2, obj3);
                default:
                    return super.apply3(moduleMethod, obj, obj2, obj3);
            }
        }

        public Object lambda21pp$MnDO(Object expr, Object col, Object extra) {
            Boolean bool = Boolean.FALSE;
            Procedure procedure = this.pp$Mnexpr$Mnlist;
            return lambda12ppGeneral(expr, col, extra, bool, procedure, procedure, this.pp$Mnexpr);
        }
    }

    /* compiled from: genwrite.scm */
    /* loaded from: classes2.dex */
    public class frame1 extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object left;
        Object result;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/genwrite.scm:72");
            this.lambda$Fn1 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 1 ? lambda22(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        boolean lambda22(Object str) {
            this.result = lists.cons(str, this.result);
            try {
                this.left = AddOp.$Mn.apply2(this.left, Integer.valueOf(strings.stringLength((CharSequence) str)));
                return ((Boolean) Scheme.numGrt.apply2(this.left, genwrite.Lit1)).booleanValue();
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, str);
            }
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
    }

    public static Object reverseStringAppend(Object l) {
        return lambda23revStringAppend(l, Lit1);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 13 ? reverseStringAppend(obj) : super.apply1(moduleMethod, obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 13) {
            return super.match1(moduleMethod, obj, callContext);
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static Object lambda23revStringAppend(Object l, Object i) {
        if (lists.isPair(l)) {
            Object str = lists.car.apply1(l);
            try {
                int len = strings.stringLength((CharSequence) str);
                Object result = lambda23revStringAppend(lists.cdr.apply1(l), AddOp.$Pl.apply2(i, Integer.valueOf(len)));
                Object j = Lit1;
                try {
                    Object k = AddOp.$Mn.apply2(AddOp.$Mn.apply2(Integer.valueOf(strings.stringLength((CharSequence) result)), i), Integer.valueOf(len));
                    while (Scheme.numLss.apply2(j, Integer.valueOf(len)) != Boolean.FALSE) {
                        try {
                            try {
                                try {
                                    try {
                                        ((CharSeq) result).setCharAt(((Number) k).intValue(), strings.stringRef((CharSequence) str, ((Number) j).intValue()));
                                        AddOp addOp = AddOp.$Pl;
                                        IntNum intNum = Lit17;
                                        j = addOp.apply2(j, intNum);
                                        k = AddOp.$Pl.apply2(k, intNum);
                                    } catch (ClassCastException e) {
                                        throw new WrongType(e, "string-ref", 2, j);
                                    }
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "string-ref", 1, str);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-set!", 2, k);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-set!", 1, result);
                        }
                    }
                    return result;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "string-length", 1, result);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-length", 1, str);
            }
        }
        try {
            return strings.makeString(((Number) i).intValue());
        } catch (ClassCastException e7) {
            throw new WrongType(e7, "make-string", 1, i);
        }
    }
}
