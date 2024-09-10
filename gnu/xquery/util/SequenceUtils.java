package gnu.xquery.util;

import gnu.kawa.xml.NodeType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.xml.NodeTree;

/* loaded from: classes2.dex */
public class SequenceUtils {
    public static final NodeType textOrElement = new NodeType("element-or-text", 3);

    public static boolean isZeroOrOne(Object arg) {
        return !(arg instanceof Values) || ((Values) arg).isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object coerceToZeroOrOne(Object arg, String functionName, int iarg) {
        if (isZeroOrOne(arg)) {
            return arg;
        }
        throw new WrongType(functionName, iarg, arg, "xs:item()?");
    }

    public static Object zeroOrOne(Object arg) {
        return coerceToZeroOrOne(arg, "zero-or-one", 1);
    }

    public static Object oneOrMore(Object arg) {
        if ((arg instanceof Values) && ((Values) arg).isEmpty()) {
            throw new IllegalArgumentException();
        }
        return arg;
    }

    public static Object exactlyOne(Object arg) {
        if (arg instanceof Values) {
            throw new IllegalArgumentException();
        }
        return arg;
    }

    public static boolean isEmptySequence(Object arg) {
        return (arg instanceof Values) && ((Values) arg).isEmpty();
    }

    public static boolean exists(Object arg) {
        return ((arg instanceof Values) && ((Values) arg).isEmpty()) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
    
        if (r14 == r5) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void insertBefore$X(java.lang.Object r17, long r18, java.lang.Object r20, gnu.mapping.CallContext r21) {
        /*
            r0 = r17
            r1 = r20
            r2 = r21
            gnu.lists.Consumer r3 = r2.consumer
            r4 = 0
            r5 = 0
            int r7 = (r18 > r5 ? 1 : (r18 == r5 ? 0 : -1))
            if (r7 > 0) goto L12
            r5 = 1
            goto L14
        L12:
            r5 = r18
        L14:
            boolean r7 = r0 instanceof gnu.mapping.Values
            r8 = 1
            if (r7 == 0) goto L3c
            r7 = r0
            gnu.mapping.Values r7 = (gnu.mapping.Values) r7
            r10 = 0
            r11 = 0
        L20:
            int r13 = r7.nextPos(r10)
            if (r13 != 0) goto L28
            if (r4 == 0) goto L2f
        L28:
            long r14 = r11 + r8
            r11 = r14
            int r16 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r16 != 0) goto L33
        L2f:
            gnu.mapping.Values.writeValues(r1, r3)
            r4 = 1
        L33:
            if (r13 != 0) goto L37
        L36:
            goto L4d
        L37:
            r7.consumePosRange(r10, r13, r3)
            r10 = r13
            goto L20
        L3c:
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 > 0) goto L43
            gnu.mapping.Values.writeValues(r1, r3)
        L43:
            r3.writeObject(r0)
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 <= 0) goto L4d
            gnu.mapping.Values.writeValues(r1, r3)
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.SequenceUtils.insertBefore$X(java.lang.Object, long, java.lang.Object, gnu.mapping.CallContext):void");
    }

    public static void remove$X(Object arg, long position, CallContext ctx) {
        Consumer out = ctx.consumer;
        if (!(arg instanceof Values)) {
            if (position != 1) {
                out.writeObject(arg);
                return;
            }
            return;
        }
        Values values = (Values) arg;
        int ipos = 0;
        long i = 0;
        while (true) {
            int next = values.nextPos(ipos);
            if (next != 0) {
                long j = i + 1;
                i = j;
                if (j != position) {
                    values.consumePosRange(ipos, next, out);
                }
                ipos = next;
            } else {
                return;
            }
        }
    }

    public static void reverse$X(Object arg, CallContext ctx) {
        int n;
        Consumer out = ctx.consumer;
        if (!(arg instanceof Values)) {
            out.writeObject(arg);
            return;
        }
        Values vals = (Values) arg;
        int ipos = 0;
        int[] poses = new int[100];
        int n2 = 0;
        while (true) {
            if (n2 >= poses.length) {
                int[] t = new int[n2 * 2];
                System.arraycopy(poses, 0, t, 0, n2);
                poses = t;
            }
            n = n2 + 1;
            poses[n2] = ipos;
            ipos = vals.nextPos(ipos);
            if (ipos == 0) {
                break;
            } else {
                n2 = n;
            }
        }
        int i = n - 1;
        while (true) {
            i--;
            if (i >= 0) {
                vals.consumePosRange(poses[i], poses[i + 1], out);
            } else {
                return;
            }
        }
    }

    public static void indexOf$X(Object seqParam, Object srchParam, NamedCollator collator, CallContext ctx) {
        Consumer out = ctx.consumer;
        if (seqParam instanceof Values) {
            Values vals = (Values) seqParam;
            int ipos = vals.startPos();
            int i = 1;
            while (true) {
                int nextPos = vals.nextPos(ipos);
                ipos = nextPos;
                if (nextPos != 0) {
                    if (Compare.apply(72, vals.getPosPrevious(ipos), srchParam, collator)) {
                        out.writeInt(i);
                    }
                    i++;
                } else {
                    return;
                }
            }
        } else if (Compare.apply(72, seqParam, srchParam, collator)) {
            out.writeInt(1);
        }
    }

    public static boolean deepEqualChildren(NodeTree seq1, int ipos1, NodeTree seq2, int ipos2, NamedCollator collator) {
        NodeType filter = textOrElement;
        int child1 = seq1.firstChildPos(ipos1, filter);
        int child2 = seq2.firstChildPos(ipos2, filter);
        while (child1 != 0 && child2 != 0) {
            if (!deepEqual(seq1, child1, seq2, child2, collator)) {
                return false;
            }
            child1 = seq1.nextMatching(child1, filter, -1, false);
            child2 = seq2.nextMatching(child2, filter, -1, false);
        }
        return child1 == child2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d0, code lost:
    
        r5 = r22.getAttributeCount(r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d4, code lost:
    
        if (r8 == r5) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d6, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean deepEqual(gnu.xml.NodeTree r20, int r21, gnu.xml.NodeTree r22, int r23, gnu.xquery.util.NamedCollator r24) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.SequenceUtils.deepEqual(gnu.xml.NodeTree, int, gnu.xml.NodeTree, int, gnu.xquery.util.NamedCollator):boolean");
    }

    public static boolean deepEqualItems(Object arg1, Object arg2, NamedCollator collator) {
        if (NumberValue.isNaN(arg1) && NumberValue.isNaN(arg2)) {
            return true;
        }
        return Compare.atomicCompare(8, arg1, arg2, collator);
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x00c2, code lost:
    
        if (r4 != r5) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00c4, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00c6, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean deepEqual(java.lang.Object r17, java.lang.Object r18, gnu.xquery.util.NamedCollator r19) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.SequenceUtils.deepEqual(java.lang.Object, java.lang.Object, gnu.xquery.util.NamedCollator):boolean");
    }
}
