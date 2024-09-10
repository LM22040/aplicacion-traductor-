package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

/* loaded from: classes2.dex */
public class CompileArith implements Inlineable {
    int op;
    Procedure proc;
    public static CompileArith $Pl = new CompileArith(AddOp.$Pl, 1);
    public static CompileArith $Mn = new CompileArith(AddOp.$Mn, 2);

    CompileArith(Object proc, int op) {
        this.proc = (Procedure) proc;
        this.op = op;
    }

    public static CompileArith forMul(Object proc) {
        return new CompileArith(proc, 3);
    }

    public static CompileArith forDiv(Object proc) {
        return new CompileArith(proc, ((DivideOp) proc).op);
    }

    public static CompileArith forBitwise(Object proc) {
        return new CompileArith(proc, ((BitwiseOp) proc).op);
    }

    public static boolean appropriateIntConstant(Expression[] args, int iarg, InlineCalls visitor) {
        Expression exp = visitor.fixIntValue(args[iarg]);
        if (exp != null) {
            args[iarg] = exp;
            return true;
        }
        return false;
    }

    public static boolean appropriateLongConstant(Expression[] args, int iarg, InlineCalls visitor) {
        Expression exp = visitor.fixLongValue(args[iarg]);
        if (exp != null) {
            args[iarg] = exp;
            return true;
        }
        return false;
    }

    public static Expression validateApplyArithOp(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        int rkind;
        ArithOp aproc = (ArithOp) proc;
        int op = aproc.op;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length > 2) {
            return pairwise(proc, exp.getFunction(), args, visitor);
        }
        Expression folded = exp.inlineIfConstant(proc, visitor);
        if (folded != exp) {
            return folded;
        }
        int rkind2 = 0;
        if (args.length == 2 || args.length == 1) {
            int kind1 = Arithmetic.classifyType(args[0].getType());
            if (args.length == 2 && (op < 9 || op > 12)) {
                int kind2 = Arithmetic.classifyType(args[1].getType());
                rkind = getReturnKind(kind1, kind2, op);
                if (rkind == 4) {
                    if (kind1 == 1 && appropriateIntConstant(args, 1, visitor)) {
                        rkind = 1;
                    } else if (kind2 == 1 && appropriateIntConstant(args, 0, visitor)) {
                        rkind = 1;
                    } else if (kind1 == 2 && appropriateLongConstant(args, 1, visitor)) {
                        rkind = 2;
                    } else if (kind2 == 2 && appropriateLongConstant(args, 0, visitor)) {
                        rkind = 2;
                    }
                }
            } else {
                rkind = kind1;
            }
            rkind2 = adjustReturnKind(rkind, op);
            exp.setType(Arithmetic.kindType(rkind2));
        }
        if (!visitor.getCompilation().mustCompile) {
            return exp;
        }
        switch (op) {
            case 1:
            case 2:
                return validateApplyAdd((AddOp) proc, exp, visitor);
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return validateApplyDiv((DivideOp) proc, exp, visitor);
            case 16:
                if (rkind2 > 0) {
                    return validateApplyNot(exp, rkind2, visitor);
                }
            default:
                return exp;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:140:0x00e1, code lost:
    
        if (r2 == 8) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0195, code lost:
    
        if (r11 == 8) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x019f, code lost:
    
        gnu.expr.ApplyExp.compile(r22, r23, r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01a2, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x019c, code lost:
    
        if (r0 < 13) goto L141;
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ae  */
    @Override // gnu.expr.Inlineable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void compile(gnu.expr.ApplyExp r22, gnu.expr.Compilation r23, gnu.expr.Target r24) {
        /*
            Method dump skipped, instructions count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileArith.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target):void");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:48:0x00f2. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0181  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean compileIntNum(gnu.expr.Expression r21, gnu.expr.Expression r22, int r23, int r24, gnu.expr.Compilation r25) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileArith.compileIntNum(gnu.expr.Expression, gnu.expr.Expression, int, int, gnu.expr.Compilation):boolean");
    }

    public static int getReturnKind(int kind1, int kind2, int op) {
        if (op < 9 || op > 12) {
            return (kind1 <= 0 || (kind1 > kind2 && kind2 > 0)) ? kind1 : kind2;
        }
        return kind1;
    }

    public int getReturnKind(Expression[] args) {
        int len = args.length;
        if (len == 0) {
            return 4;
        }
        ClassType classType = Type.pointer_type;
        int kindr = 0;
        for (int i = 0; i < len; i++) {
            Expression arg = args[i];
            int kind = Arithmetic.classifyType(arg.getType());
            if (i == 0 || kind == 0 || kind > kindr) {
                kindr = kind;
            }
        }
        return kindr;
    }

    public Type getReturnType(Expression[] args) {
        return Arithmetic.kindType(adjustReturnKind(getReturnKind(args), this.op));
    }

    static int adjustReturnKind(int rkind, int op) {
        if (op >= 4 && op <= 7 && rkind > 0) {
            switch (op) {
                case 4:
                    if (rkind <= 4) {
                        return 6;
                    }
                    return rkind;
                case 5:
                    if (rkind <= 10 && rkind != 7) {
                        return 8;
                    }
                    return rkind;
                case 6:
                default:
                    return rkind;
                case 7:
                    if (rkind <= 10) {
                        return 4;
                    }
                    return rkind;
            }
        }
        return rkind;
    }

    public static Expression validateApplyAdd(AddOp proc, ApplyExp exp, InlineCalls visitor) {
        Expression[] args = exp.getArgs();
        if (args.length == 1 && proc.plusOrMinus < 0) {
            Type type0 = args[0].getType();
            if (type0 instanceof PrimType) {
                char sig0 = type0.getSignature().charAt(0);
                Type type = null;
                int opcode = 0;
                if (sig0 != 'V' && sig0 != 'Z' && sig0 != 'C') {
                    if (sig0 == 'D') {
                        opcode = 119;
                        type = LangPrimType.doubleType;
                    } else if (sig0 == 'F') {
                        opcode = 118;
                        type = LangPrimType.floatType;
                    } else if (sig0 == 'J') {
                        opcode = 117;
                        type = LangPrimType.longType;
                    } else {
                        opcode = 116;
                        type = LangPrimType.intType;
                    }
                }
                if (type != null) {
                    PrimProcedure prim = PrimProcedure.makeBuiltinUnary(opcode, type);
                    return new ApplyExp(prim, args);
                }
            }
        }
        return exp;
    }

    public static Expression validateApplyDiv(DivideOp proc, ApplyExp exp, InlineCalls visitor) {
        Expression[] args = exp.getArgs();
        if (args.length == 1) {
            return new ApplyExp(exp.getFunction(), QuoteExp.getInstance(IntNum.one()), args[0]);
        }
        return exp;
    }

    public static Expression validateApplyNot(ApplyExp exp, int kind, InlineCalls visitor) {
        String cname;
        if (exp.getArgCount() == 1) {
            Expression arg = exp.getArg(0);
            if (kind == 1 || kind == 2) {
                Expression[] args = {arg, QuoteExp.getInstance(IntNum.minusOne())};
                return visitor.visitApplyOnly(new ApplyExp(BitwiseOp.xor, args), null);
            }
            if (kind == 4) {
                cname = "gnu.math.BitOps";
            } else if (kind == 3) {
                cname = "java.meth.BigInteger";
            } else {
                cname = null;
            }
            if (cname != null) {
                return new ApplyExp(ClassType.make(cname).getDeclaredMethod("not", 1), exp.getArgs());
            }
        }
        return exp;
    }

    public static Expression validateApplyNumberCompare(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        Expression folded = exp.inlineIfConstant(proc, visitor);
        if (folded != exp) {
            return folded;
        }
        return exp;
    }

    public int primitiveOpcode() {
        switch (this.op) {
            case 1:
                return 96;
            case 2:
                return 100;
            case 3:
                return 104;
            case 4:
            case 5:
            case 6:
            case 7:
                return 108;
            case 8:
                return 112;
            case 9:
            default:
                return -1;
            case 10:
                return 120;
            case 11:
                return 122;
            case 12:
                return 124;
            case 13:
                return 126;
            case 14:
                return 128;
            case 15:
                return 130;
        }
    }

    public static Expression pairwise(Procedure proc, Expression rproc, Expression[] args, InlineCalls visitor) {
        int len = args.length;
        Expression prev = args[0];
        for (int i = 1; i < len; i++) {
            Expression[] args2 = {prev, args[i]};
            ApplyExp next = new ApplyExp(rproc, args2);
            Expression inlined = visitor.maybeInline(next, null, proc);
            prev = inlined != null ? inlined : next;
        }
        return prev;
    }

    public static Expression validateApplyNumberPredicate(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        NumberPredicate nproc = (NumberPredicate) proc;
        int i = nproc.op;
        Expression[] args = exp.getArgs();
        args[0] = visitor.visit(args[0], (Type) LangObjType.integerType);
        exp.setType(Type.booleanType);
        return exp;
    }
}
