package kawa.standard;

import gnu.expr.Expression;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class fluid_let extends Syntax {
    public static final fluid_let fluid_let;
    Expression defaultInit;
    boolean star;

    static {
        fluid_let fluid_letVar = new fluid_let();
        fluid_let = fluid_letVar;
        fluid_letVar.setName("fluid-set");
    }

    public fluid_let(boolean star, Expression defaultInit) {
        this.star = star;
        this.defaultInit = defaultInit;
    }

    public fluid_let() {
        this.star = false;
    }

    @Override // kawa.lang.Syntax
    public Expression rewrite(Object obj, Translator tr) {
        if (!(obj instanceof Pair)) {
            return tr.syntaxError("missing let arguments");
        }
        Pair pair = (Pair) obj;
        return rewrite(pair.getCar(), pair.getCdr(), tr);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00e5 A[Catch: all -> 0x0114, TryCatch #0 {all -> 0x0114, blocks: (B:8:0x001c, B:10:0x0024, B:13:0x002a, B:15:0x002e, B:17:0x003a, B:19:0x0042, B:26:0x004a, B:28:0x0054, B:29:0x005c, B:31:0x0064, B:32:0x00d8, B:34:0x00e5, B:35:0x00f0, B:37:0x00fb, B:38:0x0101, B:40:0x0067, B:42:0x006f, B:44:0x007f, B:47:0x0088, B:22:0x00b1, B:52:0x00d6), top: B:7:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fb A[Catch: all -> 0x0114, TryCatch #0 {all -> 0x0114, blocks: (B:8:0x001c, B:10:0x0024, B:13:0x002a, B:15:0x002e, B:17:0x003a, B:19:0x0042, B:26:0x004a, B:28:0x0054, B:29:0x005c, B:31:0x0064, B:32:0x00d8, B:34:0x00e5, B:35:0x00f0, B:37:0x00fb, B:38:0x0101, B:40:0x0067, B:42:0x006f, B:44:0x007f, B:47:0x0088, B:22:0x00b1, B:52:0x00d6), top: B:7:0x001c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public gnu.expr.Expression rewrite(java.lang.Object r13, java.lang.Object r14, kawa.lang.Translator r15) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.fluid_let.rewrite(java.lang.Object, java.lang.Object, kawa.lang.Translator):gnu.expr.Expression");
    }
}
