package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;

/* loaded from: classes.dex */
public class ReferenceExp extends AccessExp {
    public static final int DONT_DEREFERENCE = 2;
    public static final int PREFER_BINDING2 = 8;
    public static final int PROCEDURE_NAME = 4;
    public static final int TYPE_NAME = 16;
    static int counter;
    int id;

    public final boolean getDontDereference() {
        return (this.flags & 2) != 0;
    }

    public final void setDontDereference(boolean setting) {
        setFlag(setting, 2);
    }

    public final boolean isUnknown() {
        return Declaration.isUnknown(this.binding);
    }

    public final boolean isProcedureName() {
        return (this.flags & 4) != 0;
    }

    public final void setProcedureName(boolean setting) {
        setFlag(setting, 4);
    }

    public ReferenceExp(Object symbol) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.symbol = symbol;
    }

    public ReferenceExp(Object symbol, Declaration binding) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.symbol = symbol;
        this.binding = binding;
    }

    public ReferenceExp(Declaration binding) {
        this(binding.getSymbol(), binding);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.Expression
    public boolean mustCompile() {
        return false;
    }

    @Override // gnu.expr.Expression
    public final Object valueIfConstant() {
        Expression dvalue;
        if (this.binding != null && (dvalue = this.binding.getValue()) != null) {
            return dvalue.valueIfConstant();
        }
        return null;
    }

    @Override // gnu.expr.Expression, gnu.mapping.Procedure
    public void apply(CallContext callContext) throws Throwable {
        Object obj;
        Object obj2;
        if (this.binding != null && this.binding.isAlias() && !getDontDereference() && (this.binding.value instanceof ReferenceExp)) {
            ReferenceExp referenceExp = (ReferenceExp) this.binding.value;
            if (referenceExp.getDontDereference() && referenceExp.binding != null) {
                Expression value = referenceExp.binding.getValue();
                if ((value instanceof QuoteExp) || (value instanceof ReferenceExp) || (value instanceof LambdaExp)) {
                    value.apply(callContext);
                    return;
                }
            }
            obj2 = this.binding.value.eval(callContext);
        } else {
            if (this.binding != null && this.binding.field != null && this.binding.field.getDeclaringClass().isExisting() && (!getDontDereference() || this.binding.isIndirectBinding())) {
                try {
                    obj2 = this.binding.field.getReflectField().get(this.binding.field.getStaticFlag() ? null : contextDecl().getValue().eval(callContext));
                } catch (Exception e) {
                    throw new UnboundLocationException("exception evaluating " + this.symbol + " from " + this.binding.field + " - " + e, this);
                }
            } else if (this.binding != null && (((this.binding.value instanceof QuoteExp) || (this.binding.value instanceof LambdaExp)) && this.binding.value != QuoteExp.undefined_exp && (!getDontDereference() || this.binding.isIndirectBinding()))) {
                obj2 = this.binding.value.eval(callContext);
            } else {
                if (this.binding == null || ((this.binding.context instanceof ModuleExp) && !this.binding.isPrivate())) {
                    Environment current = Environment.getCurrent();
                    Symbol symbol = this.symbol instanceof Symbol ? (Symbol) this.symbol : current.getSymbol(this.symbol.toString());
                    if (getFlag(8) && isProcedureName()) {
                        r1 = EnvironmentKey.FUNCTION;
                    }
                    if (getDontDereference()) {
                        obj = current.getLocation(symbol, r1);
                    } else {
                        String str = Location.UNBOUND;
                        obj = current.get(symbol, r1, str);
                        if (obj == str) {
                            throw new UnboundLocationException(symbol, this);
                        }
                    }
                    callContext.writeValue(obj);
                    return;
                }
                obj2 = callContext.evalFrames[ScopeExp.nesting(this.binding.context)][this.binding.evalIndex];
            }
        }
        if (!getDontDereference() && this.binding.isIndirectBinding()) {
            obj2 = ((Location) obj2).get();
        }
        callContext.writeValue(obj2);
    }

    @Override // gnu.expr.Expression
    public void compile(Compilation comp, Target target) {
        if (!(target instanceof ConsumerTarget) || !((ConsumerTarget) target).compileWrite(this, comp)) {
            this.binding.load(this, this.flags, comp, target);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // gnu.expr.Expression
    protected Expression deepCopy(IdentityHashTable mapper) {
        Declaration d = (Declaration) mapper.get(this.binding, this.binding);
        Object s = mapper.get(this.symbol, this.symbol);
        ReferenceExp copy = new ReferenceExp(s, d);
        copy.flags = getFlags();
        return copy;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.Expression
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitReferenceExp(this, d);
    }

    @Override // gnu.expr.Expression
    public Expression validateApply(ApplyExp exp, InlineCalls visitor, Type required, Declaration decl) {
        Expression dval;
        Declaration decl2 = this.binding;
        if (decl2 != null && !decl2.getFlag(65536L)) {
            Declaration decl3 = Declaration.followAliases(decl2);
            if (!decl3.isIndirectBinding() && (dval = decl3.getValue()) != null) {
                return dval.validateApply(exp, visitor, required, decl3);
            }
        } else if (getSymbol() instanceof Symbol) {
            Symbol symbol = (Symbol) getSymbol();
            Object fval = Environment.getCurrent().getFunction(symbol, null);
            if (fval instanceof Procedure) {
                return new QuoteExp(fval).validateApply(exp, visitor, required, null);
            }
        }
        exp.visitArgs(visitor);
        return exp;
    }

    @Override // gnu.expr.Expression
    public void print(OutPort ps) {
        ps.print("(Ref/");
        ps.print(this.id);
        if (this.symbol != null && (this.binding == null || this.symbol.toString() != this.binding.getName())) {
            ps.print('/');
            ps.print(this.symbol);
        }
        if (this.binding != null) {
            ps.print('/');
            ps.print(this.binding);
        }
        ps.print(")");
    }

    @Override // gnu.expr.Expression
    public Type getType() {
        Expression value;
        Declaration decl = this.binding;
        if (decl == null || decl.isFluid()) {
            return Type.pointer_type;
        }
        if (getDontDereference()) {
            return Compilation.typeLocation;
        }
        Declaration decl2 = Declaration.followAliases(decl);
        Type type = decl2.getType();
        if ((type == null || type == Type.pointer_type) && (value = decl2.getValue()) != null && value != QuoteExp.undefined_exp) {
            Expression save = decl2.value;
            decl2.value = null;
            type = value.getType();
            decl2.value = save;
        }
        if (type == Type.toStringType) {
            return Type.javalangStringType;
        }
        return type;
    }

    @Override // gnu.expr.Expression
    public boolean isSingleValue() {
        if (this.binding != null && this.binding.getFlag(262144L)) {
            return true;
        }
        return super.isSingleValue();
    }

    @Override // gnu.expr.Expression
    public boolean side_effects() {
        return this.binding == null || !this.binding.isLexical();
    }

    @Override // gnu.expr.Expression, gnu.mapping.Procedure
    public String toString() {
        return "RefExp/" + this.symbol + '/' + this.id + '/';
    }
}
