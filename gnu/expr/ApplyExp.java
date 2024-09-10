package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.text.SourceMessages;

/* loaded from: classes.dex */
public class ApplyExp extends Expression {
    public static final int INLINE_IF_CONSTANT = 4;
    public static final int MAY_CONTAIN_BACK_JUMP = 8;
    public static final int TAILCALL = 2;
    Expression[] args;
    LambdaExp context;
    Expression func;
    public ApplyExp nextCall;
    protected Type type;

    public final Expression getFunction() {
        return this.func;
    }

    public final Expression[] getArgs() {
        return this.args;
    }

    public final int getArgCount() {
        return this.args.length;
    }

    public void setFunction(Expression func) {
        this.func = func;
    }

    public void setArgs(Expression[] args) {
        this.args = args;
    }

    public Expression getArg(int i) {
        return this.args[i];
    }

    public void setArg(int i, Expression arg) {
        this.args[i] = arg;
    }

    public final boolean isTailCall() {
        return getFlag(2);
    }

    public final void setTailCall(boolean tailCall) {
        setFlag(tailCall, 2);
    }

    public final Object getFunctionValue() {
        Expression expression = this.func;
        if (expression instanceof QuoteExp) {
            return ((QuoteExp) expression).getValue();
        }
        return null;
    }

    public ApplyExp(Expression f, Expression... a) {
        this.func = f;
        this.args = a;
    }

    public ApplyExp(Procedure p, Expression... a) {
        this.func = new QuoteExp(p);
        this.args = a;
    }

    public ApplyExp(Method m, Expression... a) {
        this((Expression) new QuoteExp(new PrimProcedure(m)), a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.Expression
    public boolean mustCompile() {
        return false;
    }

    @Override // gnu.expr.Expression, gnu.mapping.Procedure
    public void apply(CallContext ctx) throws Throwable {
        Object proc = this.func.eval(ctx);
        int n = this.args.length;
        Object[] vals = new Object[n];
        for (int i = 0; i < n; i++) {
            vals[i] = this.args[i].eval(ctx);
        }
        ((Procedure) proc).checkN(vals, ctx);
    }

    public static void compileToArray(Expression[] args, Compilation comp) {
        CodeAttr code = comp.getCode();
        if (args.length == 0) {
            code.emitGetStatic(Compilation.noArgsField);
            return;
        }
        code.emitPushInt(args.length);
        code.emitNewArray(Type.pointer_type);
        for (int i = 0; i < args.length; i++) {
            Expression arg = args[i];
            if (comp.usingCPStyle() && !(arg instanceof QuoteExp) && !(arg instanceof ReferenceExp)) {
                arg.compileWithPosition(comp, Target.pushObject);
                code.emitSwap();
                code.emitDup(1, 1);
                code.emitSwap();
                code.emitPushInt(i);
                code.emitSwap();
            } else {
                code.emitDup(Compilation.objArrayType);
                code.emitPushInt(i);
                arg.compileWithPosition(comp, Target.pushObject);
            }
            code.emitArrayStore(Type.pointer_type);
        }
    }

    @Override // gnu.expr.Expression
    public void compile(Compilation comp, Target target) {
        compile(this, comp, target, true);
    }

    public static void compile(ApplyExp exp, Compilation comp, Target target) {
        compile(exp, comp, target, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void compile(gnu.expr.ApplyExp r19, gnu.expr.Compilation r20, gnu.expr.Target r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 789
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ApplyExp.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target, boolean):void");
    }

    @Override // gnu.expr.Expression
    public Expression deepCopy(IdentityHashTable mapper) {
        Expression f = deepCopy(this.func, mapper);
        Expression[] a = deepCopy(this.args, mapper);
        if (f == null && this.func != null) {
            return null;
        }
        if (a == null && this.args != null) {
            return null;
        }
        ApplyExp copy = new ApplyExp(f, a);
        copy.flags = getFlags();
        return copy;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.Expression
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitApplyExp(this, d);
    }

    public void visitArgs(InlineCalls visitor) {
        Expression[] expressionArr = this.args;
        this.args = visitor.visitExps(expressionArr, expressionArr.length, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.Expression
    public <R, D> void visitChildren(ExpVisitor<R, D> visitor, D d) {
        this.func = visitor.visitAndUpdate(this.func, d);
        if (visitor.exitValue == null) {
            Expression[] expressionArr = this.args;
            this.args = visitor.visitExps(expressionArr, expressionArr.length, d);
        }
    }

    @Override // gnu.expr.Expression
    public void print(OutPort out) {
        out.startLogicalBlock("(Apply", ")", 2);
        if (isTailCall()) {
            out.print(" [tailcall]");
        }
        Type type = this.type;
        if (type != null && type != Type.pointer_type) {
            out.print(" => ");
            out.print(this.type);
        }
        out.writeSpaceFill();
        printLineColumn(out);
        this.func.print(out);
        for (int i = 0; i < this.args.length; i++) {
            out.writeSpaceLinear();
            this.args[i].print(out);
        }
        out.endLogicalBlock(")");
    }

    private static void pushArgs(LambdaExp lexp, Expression[] args, int[] incValues, Compilation comp) {
        Declaration param = lexp.firstDecl();
        int args_length = args.length;
        for (int i = 0; i < args_length; i++) {
            Expression arg = args[i];
            if (param.ignorable()) {
                arg.compile(comp, Target.Ignore);
            } else {
                if (incValues != null) {
                    int canUseInc = SetExp.canUseInc(arg, param);
                    incValues[i] = canUseInc;
                    if (canUseInc != 65536) {
                    }
                }
                arg.compileWithPosition(comp, StackTarget.getInstance(param.getType()));
            }
            param = param.nextDecl();
        }
    }

    private static void popParams(CodeAttr code, LambdaExp lexp, int[] incValues, boolean toArray) {
        Variable vars = lexp.getVarScope().firstVar();
        Declaration decls = lexp.firstDecl();
        if (vars != null && vars.getName() == "this") {
            vars = vars.nextVar();
        }
        if (vars != null && vars.getName() == "$ctx") {
            vars = vars.nextVar();
        }
        if (vars != null && vars.getName() == "argsArray") {
            if (toArray) {
                popParams(code, 0, 1, null, decls, vars);
                return;
            }
            vars = vars.nextVar();
        }
        popParams(code, 0, lexp.min_args, incValues, decls, vars);
    }

    private static void popParams(CodeAttr code, int paramNo, int count, int[] incValues, Declaration decl, Variable vars) {
        if (count > 0) {
            popParams(code, paramNo + 1, count - 1, incValues, decl.nextDecl(), decl.getVariable() == null ? vars : vars.nextVar());
            if (!decl.ignorable()) {
                if (incValues != null && incValues[paramNo] != 65536) {
                    code.emitInc(vars, (short) incValues[paramNo]);
                } else {
                    code.emitStore(vars);
                }
            }
        }
    }

    public final Type getTypeRaw() {
        return this.type;
    }

    public final void setType(Type type) {
        this.type = type;
    }

    @Override // gnu.expr.Expression
    public boolean side_effects() {
        Object value = derefFunc(this.func).valueIfConstant();
        if (!(value instanceof Procedure) || !((Procedure) value).isSideEffectFree()) {
            return true;
        }
        Expression[] a = this.args;
        for (Expression expression : a) {
            if (expression.side_effects()) {
                return true;
            }
        }
        return false;
    }

    static Expression derefFunc(Expression afunc) {
        Declaration func_decl;
        if ((afunc instanceof ReferenceExp) && (func_decl = Declaration.followAliases(((ReferenceExp) afunc).binding)) != null && !func_decl.getFlag(65536L)) {
            return func_decl.getValue();
        }
        return afunc;
    }

    @Override // gnu.expr.Expression
    public final Type getType() {
        Type type = this.type;
        if (type != null) {
            return type;
        }
        Expression afunc = derefFunc(this.func);
        this.type = Type.objectType;
        if (afunc instanceof QuoteExp) {
            Object value = ((QuoteExp) afunc).getValue();
            if (value instanceof Procedure) {
                this.type = ((Procedure) value).getReturnType(this.args);
            }
        } else if (afunc instanceof LambdaExp) {
            this.type = ((LambdaExp) afunc).getReturnType();
        }
        return this.type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Inlineable asInlineable(Procedure procedure) {
        if (procedure instanceof Inlineable) {
            return (Inlineable) procedure;
        }
        return (Inlineable) Procedure.compilerKey.get(procedure);
    }

    static boolean inlineCompile(Procedure proc, ApplyExp exp, Compilation comp, Target target) throws Throwable {
        Inlineable compiler = asInlineable(proc);
        if (compiler == null) {
            return false;
        }
        compiler.compile(exp, comp, target);
        return true;
    }

    public final Expression inlineIfConstant(Procedure proc, InlineCalls visitor) {
        return inlineIfConstant(proc, visitor.getMessages());
    }

    public final Expression inlineIfConstant(Procedure proc, SourceMessages messages) {
        Declaration decl;
        int len = this.args.length;
        Object[] vals = new Object[len];
        int i = len;
        while (true) {
            i--;
            if (i >= 0) {
                Expression arg = this.args[i];
                if ((arg instanceof ReferenceExp) && (decl = ((ReferenceExp) arg).getBinding()) != null && (arg = decl.getValue()) == QuoteExp.undefined_exp) {
                    return this;
                }
                if (!(arg instanceof QuoteExp)) {
                    return this;
                }
                vals[i] = ((QuoteExp) arg).getValue();
            } else {
                try {
                    return new QuoteExp(proc.applyN(vals), this.type);
                } catch (Throwable ex) {
                    if (messages != null) {
                        messages.error('w', "call to " + proc + " throws " + ex);
                    }
                    return this;
                }
            }
        }
    }

    @Override // gnu.expr.Expression, gnu.mapping.Procedure
    public String toString() {
        if (this == LambdaExp.unknownContinuation) {
            return "ApplyExp[unknownContinuation]";
        }
        StringBuilder append = new StringBuilder().append("ApplyExp/");
        Expression[] expressionArr = this.args;
        return append.append(expressionArr == null ? 0 : expressionArr.length).append('[').append(this.func).append(']').toString();
    }
}
