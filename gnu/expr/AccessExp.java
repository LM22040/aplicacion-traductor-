package gnu.expr;

import gnu.mapping.Symbol;

/* loaded from: classes.dex */
public abstract class AccessExp extends Expression {
    Declaration binding;
    private Declaration context;
    Object symbol;

    public String string_name() {
        return this.symbol.toString();
    }

    @Override // gnu.mapping.PropertySet, gnu.mapping.Named
    public final String getName() {
        Object obj = this.symbol;
        return obj instanceof Symbol ? ((Symbol) obj).getName() : obj.toString();
    }

    public final String getSimpleName() {
        Object obj = this.symbol;
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof Symbol)) {
            return null;
        }
        Symbol sym = (Symbol) obj;
        if (sym.hasEmptyNamespace()) {
            return sym.getLocalName();
        }
        return null;
    }

    @Override // gnu.mapping.PropertySet, gnu.mapping.Named
    public final Object getSymbol() {
        return this.symbol;
    }

    public final Declaration getBinding() {
        return this.binding;
    }

    public final void setBinding(Declaration decl) {
        this.binding = decl;
    }

    public final Declaration contextDecl() {
        return this.context;
    }

    public final void setContextDecl(Declaration decl) {
        this.context = decl;
    }
}
