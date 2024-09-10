package gnu.xquery.util;

import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.FilterConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;

/* loaded from: classes2.dex */
public class OrderedTuples extends FilterConsumer {
    Procedure body;
    Object[] comps;
    int first;
    int n;
    int[] next;
    Object[] tuples;

    @Override // gnu.lists.FilterConsumer, gnu.lists.Consumer
    public void writeObject(Object v) {
        int i = this.n;
        Object[] objArr = this.tuples;
        if (i >= objArr.length) {
            Object[] tmp = new Object[i * 2];
            System.arraycopy(objArr, 0, tmp, 0, i);
            this.tuples = tmp;
        }
        Object[] objArr2 = this.tuples;
        int i2 = this.n;
        this.n = i2 + 1;
        objArr2[i2] = v;
    }

    OrderedTuples() {
        super(null);
        this.tuples = new Object[10];
    }

    public static OrderedTuples make$V(Procedure body, Object[] comps) {
        OrderedTuples tuples = new OrderedTuples();
        tuples.comps = comps;
        tuples.body = body;
        return tuples;
    }

    public void run$X(CallContext ctx) throws Throwable {
        this.first = listsort(0);
        emit(ctx);
    }

    void emit(CallContext ctx) throws Throwable {
        int p = this.first;
        while (p >= 0) {
            emit(p, ctx);
            p = this.next[p];
        }
    }

    void emit(int index, CallContext ctx) throws Throwable {
        Object[] args = (Object[]) this.tuples[index];
        this.body.checkN(args, ctx);
        ctx.runUntilDone();
    }

    int cmp(int a, int b) throws Throwable {
        int i = 0;
        while (true) {
            Object[] objArr = this.comps;
            if (i >= objArr.length) {
                return 0;
            }
            Procedure comparator = (Procedure) objArr[i];
            String flags = (String) objArr[i + 1];
            NamedCollator collator = (NamedCollator) objArr[i + 2];
            if (collator == null) {
                collator = NamedCollator.codepointCollation;
            }
            Object val1 = comparator.applyN((Object[]) this.tuples[a]);
            Object val2 = comparator.applyN((Object[]) this.tuples[b]);
            Object val12 = KNode.atomicValue(val1);
            Object val22 = KNode.atomicValue(val2);
            if (val12 instanceof UntypedAtomic) {
                val12 = val12.toString();
            }
            if (val22 instanceof UntypedAtomic) {
                val22 = val22.toString();
            }
            boolean empty1 = SequenceUtils.isEmptySequence(val12);
            boolean empty2 = SequenceUtils.isEmptySequence(val22);
            if (!empty1 || !empty2) {
                int c = -1;
                if (empty1 || empty2) {
                    char emptyOrder = flags.charAt(1);
                    if (empty1 != (emptyOrder == 'L')) {
                        c = 1;
                    }
                } else {
                    boolean isNaN1 = (val12 instanceof Number) && Double.isNaN(((Number) val12).doubleValue());
                    boolean isNaN2 = (val22 instanceof Number) && Double.isNaN(((Number) val22).doubleValue());
                    if (!isNaN1 || !isNaN2) {
                        if (isNaN1 || isNaN2) {
                            char emptyOrder2 = flags.charAt(1);
                            if (isNaN1 != (emptyOrder2 == 'L')) {
                                c = 1;
                            }
                        } else {
                            c = ((val12 instanceof Number) && (val22 instanceof Number)) ? NumberCompare.compare(val12, val22, false) : collator.compare(val12.toString(), val22.toString());
                        }
                    }
                }
                if (c != 0) {
                    return flags.charAt(0) == 'A' ? c : -c;
                }
            }
            i += 3;
        }
    }

    int listsort(int list) throws Throwable {
        int e;
        int i = this.n;
        if (i == 0) {
            return -1;
        }
        this.next = new int[i];
        int i2 = 1;
        while (i2 != this.n) {
            this.next[i2 - 1] = i2;
            i2++;
        }
        this.next[i2 - 1] = -1;
        int insize = 1;
        while (true) {
            int p = list;
            list = -1;
            int tail = -1;
            int q = 0;
            while (p >= 0) {
                int nmerges = q + 1;
                int q2 = p;
                int psize = 0;
                for (int psize2 = 0; psize2 < insize; psize2++) {
                    psize++;
                    q2 = this.next[q2];
                    if (q2 < 0) {
                        break;
                    }
                }
                int qsize = insize;
                while (true) {
                    if (psize > 0 || (qsize > 0 && q2 >= 0)) {
                        if (psize == 0) {
                            e = q2;
                            q2 = this.next[q2];
                            qsize--;
                        } else if (qsize == 0 || q2 < 0) {
                            e = p;
                            p = this.next[p];
                            psize--;
                        } else if (cmp(p, q2) <= 0) {
                            e = p;
                            p = this.next[p];
                            psize--;
                        } else {
                            e = q2;
                            q2 = this.next[q2];
                            qsize--;
                        }
                        if (tail >= 0) {
                            this.next[tail] = e;
                        } else {
                            list = e;
                        }
                        tail = e;
                    }
                }
                p = q2;
                q = nmerges;
            }
            this.next[tail] = -1;
            if (q <= 1) {
                return list;
            }
            insize *= 2;
        }
    }
}
