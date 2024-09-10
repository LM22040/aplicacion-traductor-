package gnu.lists;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

/* loaded from: classes.dex */
public class Pair extends LList implements Externalizable {
    protected Object car;
    protected Object cdr;

    public Pair(Object carval, Object cdrval) {
        this.car = carval;
        this.cdr = cdrval;
    }

    public Pair() {
    }

    public Object getCar() {
        return this.car;
    }

    public Object getCdr() {
        return this.cdr;
    }

    public void setCar(Object car) {
        this.car = car;
    }

    public void setCdr(Object cdr) {
        this.cdr = cdr;
    }

    public void setCdrBackdoor(Object cdr) {
        this.cdr = cdr;
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence, gnu.lists.Sequence, java.util.List, java.util.Collection, com.google.appinventor.components.runtime.util.YailObject
    public int size() {
        int n = listLength(this, true);
        if (n >= 0) {
            return n;
        }
        if (n == -1) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        throw new RuntimeException("not a true list");
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence, gnu.lists.Sequence, java.util.List, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    public int length() {
        int n = 0;
        Object slow = this;
        Object fast = this;
        while (fast != Empty) {
            if (!(fast instanceof Pair)) {
                if (!(fast instanceof Sequence)) {
                    return -2;
                }
                int j = ((Sequence) fast).size();
                return j >= 0 ? n + j : j;
            }
            Pair fast_pair = (Pair) fast;
            if (fast_pair.cdr == Empty) {
                return n + 1;
            }
            if (fast == slow && n > 0) {
                return -1;
            }
            Object obj = fast_pair.cdr;
            if (!(obj instanceof Pair)) {
                n++;
                fast = fast_pair.cdr;
            } else {
                if (!(slow instanceof Pair)) {
                    return -2;
                }
                slow = ((Pair) slow).cdr;
                fast = ((Pair) obj).cdr;
                n += 2;
            }
        }
        return n;
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence
    public boolean hasNext(int ipos) {
        if (ipos <= 0) {
            return ipos == 0;
        }
        return PositionManager.getPositionObject(ipos).hasNext();
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence
    public int nextPos(int ipos) {
        if (ipos <= 0) {
            if (ipos < 0) {
                return 0;
            }
            return PositionManager.manager.register(new LListPosition(this, 1, true));
        }
        LListPosition it = (LListPosition) PositionManager.getPositionObject(ipos);
        if (it.gotoNext()) {
            return ipos;
        }
        return 0;
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence
    public Object getPosNext(int ipos) {
        if (ipos <= 0) {
            return ipos == 0 ? this.car : eofValue;
        }
        return PositionManager.getPositionObject(ipos).getNext();
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence
    public Object getPosPrevious(int ipos) {
        if (ipos <= 0) {
            return ipos == 0 ? eofValue : lastPair().car;
        }
        return PositionManager.getPositionObject(ipos).getPrevious();
    }

    public final Pair lastPair() {
        Pair pair = this;
        while (true) {
            Object next = pair.cdr;
            if (next instanceof Pair) {
                pair = (Pair) next;
            } else {
                return pair;
            }
        }
    }

    @Override // gnu.lists.AbstractSequence, java.util.List, java.util.Collection
    public int hashCode() {
        int hash = 1;
        Object list = this;
        while (list instanceof Pair) {
            Pair pair = (Pair) list;
            Object obj = pair.car;
            hash = (hash * 31) + (obj == null ? 0 : obj.hashCode());
            list = pair.cdr;
        }
        if (list != LList.Empty && list != null) {
            return hash ^ list.hashCode();
        }
        return hash;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0018, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0039, code lost:
    
        return r2.equals(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x003a, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean equals(gnu.lists.Pair r5, gnu.lists.Pair r6) {
        /*
            r0 = 1
            if (r5 != r6) goto L4
            return r0
        L4:
            r1 = 0
            if (r5 == 0) goto L3b
            if (r6 != 0) goto La
            goto L3b
        La:
            java.lang.Object r2 = r5.car
            java.lang.Object r3 = r6.car
            if (r2 == r3) goto L19
            if (r2 == 0) goto L18
            boolean r4 = r2.equals(r3)
            if (r4 != 0) goto L19
        L18:
            return r1
        L19:
            java.lang.Object r2 = r5.cdr
            java.lang.Object r3 = r6.cdr
            if (r2 != r3) goto L20
            return r0
        L20:
            if (r2 == 0) goto L3a
            if (r3 != 0) goto L25
            goto L3a
        L25:
            boolean r4 = r2 instanceof gnu.lists.Pair
            if (r4 == 0) goto L35
            boolean r4 = r3 instanceof gnu.lists.Pair
            if (r4 != 0) goto L2e
            goto L35
        L2e:
            r5 = r2
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            r6 = r3
            gnu.lists.Pair r6 = (gnu.lists.Pair) r6
            goto La
        L35:
            boolean r0 = r2.equals(r3)
            return r0
        L3a:
            return r1
        L3b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.Pair.equals(gnu.lists.Pair, gnu.lists.Pair):boolean");
    }

    public static int compareTo(Pair pair1, Pair pair2) {
        Object x1;
        Object x2;
        if (pair1 == pair2) {
            return 0;
        }
        if (pair1 == null) {
            return -1;
        }
        if (pair2 == null) {
            return 1;
        }
        while (true) {
            int d = ((Comparable) pair1.car).compareTo((Comparable) pair2.car);
            if (d != 0) {
                return d;
            }
            x1 = pair1.cdr;
            x2 = pair2.cdr;
            if (x1 == x2) {
                return 0;
            }
            if (x1 == null) {
                return -1;
            }
            if (x2 == null) {
                return 1;
            }
            if (!(x1 instanceof Pair) || !(x2 instanceof Pair)) {
                break;
            }
            pair1 = (Pair) x1;
            pair2 = (Pair) x2;
        }
        return ((Comparable) x1).compareTo((Comparable) x2);
    }

    @Override // gnu.lists.LList, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == Empty) {
            return 1;
        }
        return compareTo(this, (Pair) obj);
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence
    public Object get(int index) {
        Pair pair = this;
        int i = index;
        while (true) {
            if (i <= 0) {
                break;
            }
            i--;
            Object obj = pair.cdr;
            if (obj instanceof Pair) {
                pair = (Pair) obj;
            } else if (obj instanceof Sequence) {
                return ((Sequence) obj).get(i);
            }
        }
        if (i == 0) {
            return pair.car;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // gnu.lists.LList, gnu.lists.AbstractSequence, java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Pair)) {
            return equals(this, (Pair) obj);
        }
        return false;
    }

    public static Pair make(Object car, Object cdr) {
        return new Pair(car, cdr);
    }

    @Override // gnu.lists.AbstractSequence, java.util.List, java.util.Collection
    public Object[] toArray() {
        int len = size();
        Object[] arr = new Object[len];
        int i = 0;
        Sequence rest = this;
        while (i < len && (rest instanceof Pair)) {
            Pair pair = (Pair) rest;
            arr[i] = pair.car;
            rest = (Sequence) pair.cdr;
            i++;
        }
        int prefix = i;
        while (i < len) {
            arr[i] = rest.get(i - prefix);
            i++;
        }
        return arr;
    }

    @Override // gnu.lists.AbstractSequence, java.util.List, java.util.Collection
    public Object[] toArray(Object[] arr) {
        int alen = arr.length;
        int len = length();
        if (len > alen) {
            arr = new Object[len];
            alen = len;
        }
        int i = 0;
        Sequence rest = this;
        while (i < len && (rest instanceof Pair)) {
            Pair pair = (Pair) rest;
            arr[i] = pair.car;
            rest = (Sequence) pair.cdr;
            i++;
        }
        int prefix = i;
        while (i < len) {
            arr[i] = rest.get(i - prefix);
            i++;
        }
        if (len < alen) {
            arr[len] = null;
        }
        return arr;
    }

    @Override // gnu.lists.LList, java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.car);
        out.writeObject(this.cdr);
    }

    @Override // gnu.lists.LList, java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.car = in.readObject();
        this.cdr = in.readObject();
    }

    @Override // gnu.lists.LList
    public Object readResolve() throws ObjectStreamException {
        return this;
    }
}
