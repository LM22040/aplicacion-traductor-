package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.kawa.xml.Nodes;
import gnu.kawa.xml.TreeScanner;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

/* loaded from: classes2.dex */
public class RelativeStep extends MethodProc implements Inlineable {
    public static final RelativeStep relativeStep = new RelativeStep();

    RelativeStep() {
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyRelativeStep");
    }

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        return 8194;
    }

    @Override // gnu.mapping.Procedure
    public void apply(CallContext ctx) throws Throwable {
        Nodes values;
        Object arg = ctx.getNextArg();
        Object next = ctx.getNextArg();
        Procedure proc = (Procedure) next;
        Consumer out = ctx.consumer;
        if (arg instanceof Nodes) {
            values = (Nodes) arg;
        } else {
            values = new Nodes();
            Values.writeValues(arg, values);
        }
        int count = values.size();
        int it = 0;
        IntNum countObj = IntNum.make(count);
        RelativeStepFilter filter = new RelativeStepFilter(out);
        for (int pos = 1; pos <= count; pos++) {
            it = values.nextPos(it);
            Object dot = values.getPosPrevious(it);
            proc.check3(dot, IntNum.make(pos), countObj, ctx);
            Values.writeValues(ctx.runUntilValue(), filter);
        }
        filter.finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0119  */
    @Override // gnu.expr.Inlineable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void compile(gnu.expr.ApplyExp r26, gnu.expr.Compilation r27, gnu.expr.Target r28) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.RelativeStep.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target):void");
    }

    @Override // gnu.mapping.Procedure
    public Type getReturnType(Expression[] args) {
        return Type.pointer_type;
    }

    public static TreeScanner extractStep(Expression exp) {
        while (exp instanceof ApplyExp) {
            ApplyExp aexp = (ApplyExp) exp;
            Expression func = aexp.getFunction();
            if (func instanceof QuoteExp) {
                Object value = ((QuoteExp) func).getValue();
                if (value instanceof TreeScanner) {
                    return (TreeScanner) value;
                }
                if (value instanceof ValuesFilter) {
                    exp = aexp.getArgs()[0];
                }
            }
            return null;
        }
        return null;
    }
}
