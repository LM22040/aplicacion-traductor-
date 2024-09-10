package gnu.xquery.util;

import gnu.lists.Consumer;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

/* loaded from: classes2.dex */
public class SubList extends MethodProc {
    public static final SubList subList = new SubList();

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        return 12290;
    }

    public static void subList(Object seq, double start, double end, Consumer out) {
        if (!(seq instanceof Values)) {
            if (start <= 1.0d && end >= 2.0d) {
                out.writeObject(seq);
                return;
            }
            return;
        }
        Values vals = (Values) seq;
        int n = 0;
        int i = 0;
        do {
            n++;
            if (n < start) {
                i = vals.nextDataIndex(i);
            } else {
                int startPosition = i;
                int endPosition = i;
                while (true) {
                    int n2 = n + 1;
                    if (n >= end || (i = vals.nextDataIndex(i)) < 0) {
                        break;
                    }
                    endPosition = i;
                    n = n2;
                }
                vals.consumeIRange(startPosition, endPosition, out);
                return;
            }
        } while (i >= 0);
    }

    @Override // gnu.mapping.Procedure
    public void apply(CallContext ctx) {
        Consumer consumer = ctx.consumer;
        Object seq = ctx.getNextArg();
        double d1 = Math.round(StringUtils.asDouble(ctx.getNextArg()));
        Object eof = Sequence.eofValue;
        Object arg2 = ctx.getNextArg(eof);
        ctx.lastArg();
        double d2 = arg2 != eof ? Math.round(StringUtils.asDouble(arg2)) : Double.POSITIVE_INFINITY;
        Double.isNaN(d1);
        subList(seq, d1, d1 + d2, consumer);
    }
}
