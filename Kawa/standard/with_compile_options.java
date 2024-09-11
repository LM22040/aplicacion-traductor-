package kawa.standard;

import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class with_compile_options extends Syntax {
    public static final with_compile_options with_compile_options;

    static {
        with_compile_options with_compile_optionsVar = new with_compile_options();
        with_compile_options = with_compile_optionsVar;
        with_compile_optionsVar.setName("with-compile-options");
    }

    @Override // kawa.lang.Syntax
    public void scanForm(Pair form, ScopeExp defs, Translator tr) {
        Stack stack = new Stack();
        Object rest = getOptions(form.getCdr(), stack, this, tr);
        if (rest == LList.Empty) {
            return;
        }
        if (rest == form.getCdr()) {
            tr.scanBody(rest, defs, false);
            return;
        }
        Object rest2 = new Pair(stack, tr.scanBody(rest, defs, true));
        tr.currentOptions.popOptionValues(stack);
        tr.formStack.add(Translator.makePair(form, form.getCar(), rest2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0027, code lost:
    
        if (r0 != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0029, code lost:
    
        r15.error('e', "no option keyword in " + r14.getName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0047, code lost:
    
        return kawa.lang.Translator.wrapSyntax(r12, r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object getOptions(java.lang.Object r12, java.util.Stack r13, kawa.lang.Syntax r14, kawa.lang.Translator r15) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.with_compile_options.getOptions(java.lang.Object, java.util.Stack, kawa.lang.Syntax, kawa.lang.Translator):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039 A[Catch: all -> 0x004e, TryCatch #0 {all -> 0x004e, blocks: (B:7:0x002d, B:9:0x0035, B:10:0x0044, B:15:0x0039), top: B:6:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0035 A[Catch: all -> 0x004e, TryCatch #0 {all -> 0x004e, blocks: (B:7:0x002d, B:9:0x0035, B:10:0x0044, B:15:0x0039), top: B:6:0x002d }] */
    @Override // kawa.lang.Syntax
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public gnu.expr.Expression rewriteForm(gnu.lists.Pair r8, kawa.lang.Translator r9) {
        /*
            r7 = this;
            java.lang.Object r0 = r8.getCdr()
            boolean r1 = r0 instanceof gnu.lists.Pair
            if (r1 == 0) goto L24
            r1 = r0
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1
            r2 = r1
            java.lang.Object r1 = r1.getCar()
            boolean r1 = r1 instanceof java.util.Stack
            if (r1 == 0) goto L24
            java.lang.Object r1 = r2.getCar()
            java.util.Stack r1 = (java.util.Stack) r1
            java.lang.Object r3 = r2.getCdr()
            gnu.text.Options r4 = r9.currentOptions
            r4.pushOptionValues(r1)
            goto L2d
        L24:
            java.util.Stack r1 = new java.util.Stack
            r1.<init>()
            java.lang.Object r3 = getOptions(r0, r1, r7, r9)
        L2d:
            gnu.expr.Expression r2 = r9.rewrite_body(r3)     // Catch: java.lang.Throwable -> L4e
            boolean r4 = r2 instanceof gnu.expr.BeginExp     // Catch: java.lang.Throwable -> L4e
            if (r4 == 0) goto L39
            r4 = r2
            gnu.expr.BeginExp r4 = (gnu.expr.BeginExp) r4     // Catch: java.lang.Throwable -> L4e
            goto L44
        L39:
            gnu.expr.BeginExp r4 = new gnu.expr.BeginExp     // Catch: java.lang.Throwable -> L4e
            r5 = 1
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r5]     // Catch: java.lang.Throwable -> L4e
            r6 = 0
            r5[r6] = r2     // Catch: java.lang.Throwable -> L4e
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L4e
        L44:
            r4.setCompileOptions(r1)     // Catch: java.lang.Throwable -> L4e
            gnu.text.Options r5 = r9.currentOptions
            r5.popOptionValues(r1)
            return r4
        L4e:
            r2 = move-exception
            gnu.text.Options r4 = r9.currentOptions
            r4.popOptionValues(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.with_compile_options.rewriteForm(gnu.lists.Pair, kawa.lang.Translator):gnu.expr.Expression");
    }
}
