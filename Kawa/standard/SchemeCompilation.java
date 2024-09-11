package kawa.standard;

import gnu.expr.Special;
import kawa.lang.Lambda;
import kawa.repl;

/* loaded from: classes.dex */
public class SchemeCompilation {
    public static final Lambda lambda;
    public static final repl repl;

    static {
        Lambda lambda2 = new Lambda();
        lambda = lambda2;
        repl = new repl(Scheme.instance);
        lambda2.setKeywords(Special.optional, Special.rest, Special.key);
    }
}
