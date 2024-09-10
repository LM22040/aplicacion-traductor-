package gnu.bytecode;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class VarEnumerator implements Enumeration {
    Scope currentScope;
    Variable next;
    Scope topScope;

    public VarEnumerator(Scope scope) {
        this.topScope = scope;
        reset();
    }

    public final void reset() {
        Scope scope = this.topScope;
        this.currentScope = scope;
        if (scope != null) {
            Variable firstVar = scope.firstVar();
            this.next = firstVar;
            if (firstVar == null) {
                fixup();
            }
        }
    }

    private void fixup() {
        while (this.next == null) {
            if (this.currentScope.firstChild != null) {
                this.currentScope = this.currentScope.firstChild;
            } else {
                while (this.currentScope.nextSibling == null) {
                    Scope scope = this.currentScope;
                    if (scope == this.topScope) {
                        return;
                    } else {
                        this.currentScope = scope.parent;
                    }
                }
                this.currentScope = this.currentScope.nextSibling;
            }
            this.next = this.currentScope.firstVar();
        }
    }

    public final Variable nextVar() {
        Variable result = this.next;
        if (result != null) {
            Variable nextVar = result.nextVar();
            this.next = nextVar;
            if (nextVar == null) {
                fixup();
            }
        }
        return result;
    }

    @Override // java.util.Enumeration
    public final boolean hasMoreElements() {
        return this.next != null;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        Variable result = nextVar();
        if (result == null) {
            throw new NoSuchElementException("VarEnumerator");
        }
        return result;
    }
}
