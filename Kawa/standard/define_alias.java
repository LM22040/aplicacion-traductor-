package kawa.standard;

import gnu.expr.Expression;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class define_alias extends Syntax {
    public static final define_alias define_alias;

    static {
        define_alias define_aliasVar = new define_alias();
        define_alias = define_aliasVar;
        define_aliasVar.setName("define-alias");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008d, code lost:
    
        if ((r15 instanceof gnu.expr.ModuleExp) == false) goto L37;
     */
    @Override // kawa.lang.Syntax
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanForDefinitions(gnu.lists.Pair r18, java.util.Vector r19, gnu.expr.ScopeExp r20, kawa.lang.Translator r21) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.define_alias.scanForDefinitions(gnu.lists.Pair, java.util.Vector, gnu.expr.ScopeExp, kawa.lang.Translator):boolean");
    }

    @Override // kawa.lang.Syntax
    public Expression rewrite(Object obj, Translator tr) {
        return tr.syntaxError("define-alias is only allowed in a <body>");
    }
}
