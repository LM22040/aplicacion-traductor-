package kawa.standard;

import gnu.expr.Expression;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class module_static extends Syntax {
    public static final module_static module_static;

    static {
        module_static module_staticVar = new module_static();
        module_static = module_staticVar;
        module_staticVar.setName("module-static");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0136  */
    @Override // kawa.lang.Syntax
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanForDefinitions(gnu.lists.Pair r16, java.util.Vector r17, gnu.expr.ScopeExp r18, kawa.lang.Translator r19) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.module_static.scanForDefinitions(gnu.lists.Pair, java.util.Vector, gnu.expr.ScopeExp, kawa.lang.Translator):boolean");
    }

    @Override // kawa.lang.Syntax
    public Expression rewriteForm(Pair form, Translator tr) {
        return null;
    }
}
