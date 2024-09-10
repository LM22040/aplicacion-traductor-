package gnu.lists;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class LListPosition extends ExtPosition {
    Object xpos;

    public LListPosition(LListPosition old) {
        this.sequence = old.sequence;
        this.ipos = old.ipos;
        this.xpos = old.xpos;
    }

    @Override // gnu.lists.SeqPosition
    public SeqPosition copy() {
        return new LListPosition(this);
    }

    public LListPosition(LList seq, int index, boolean isAfter) {
        set(seq, index, isAfter);
    }

    public void set(LList lList, int i, boolean z) {
        int i2;
        this.sequence = lList;
        this.ipos = (i << 1) | (z ? 1 : 0);
        if (z) {
            i2 = i - 2;
        } else {
            i2 = i - 1;
        }
        if (i2 >= 0) {
            Object obj = lList;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    obj = ((Pair) obj).cdr;
                } else {
                    this.xpos = obj;
                    return;
                }
            }
        } else {
            this.xpos = null;
        }
    }

    @Override // gnu.lists.SeqPosition
    public void set(AbstractSequence seq, int index, boolean isAfter) {
        set((LList) seq, index, isAfter);
    }

    @Override // gnu.lists.SeqPosition, java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        Object obj = this.xpos;
        if (obj == null) {
            return (this.ipos >> 1) == 0 ? this.sequence != LList.Empty : ((Pair) this.sequence).cdr != LList.Empty;
        }
        Object next = ((Pair) obj).cdr;
        if ((this.ipos & 1) > 0) {
            next = ((Pair) next).cdr;
        }
        return next != LList.Empty;
    }

    @Override // gnu.lists.SeqPosition, java.util.ListIterator
    public boolean hasPrevious() {
        return (this.ipos >>> 1) != 0;
    }

    public Pair getNextPair() {
        Object next;
        int isAfter = this.ipos & 1;
        if (isAfter > 0) {
            Object next2 = this.xpos;
            if (next2 == null) {
                next = this.sequence;
                if ((this.ipos >> 1) != 0) {
                    next = ((Pair) next).cdr;
                }
            } else {
                next = ((Pair) ((Pair) next2).cdr).cdr;
            }
        } else {
            Object next3 = this.xpos;
            if (next3 == null) {
                next = this.sequence;
            } else {
                next = ((Pair) next3).cdr;
            }
        }
        if (next == LList.Empty) {
            return null;
        }
        return (Pair) next;
    }

    @Override // gnu.lists.SeqPosition
    public Object getNext() {
        Pair pair = getNextPair();
        return pair == null ? LList.eofValue : pair.car;
    }

    @Override // gnu.lists.SeqPosition
    public void setNext(Object value) {
        Pair pair = getNextPair();
        pair.car = value;
    }

    public Pair getPreviousPair() {
        int isAfter = this.ipos & 1;
        Object p = this.xpos;
        if (isAfter > 0) {
            p = p == null ? this.sequence : ((Pair) p).cdr;
        } else if (p == null) {
            return null;
        }
        if (p == LList.Empty) {
            return null;
        }
        return (Pair) p;
    }

    @Override // gnu.lists.SeqPosition
    public Object getPrevious() {
        Pair pair = getPreviousPair();
        return pair == null ? LList.eofValue : pair.car;
    }

    @Override // gnu.lists.SeqPosition
    public void setPrevious(Object value) {
        Pair pair = getPreviousPair();
        pair.car = value;
    }

    @Override // gnu.lists.SeqPosition, java.util.ListIterator
    public int nextIndex() {
        return this.ipos >> 1;
    }

    @Override // gnu.lists.SeqPosition
    public boolean gotoNext() {
        boolean isAfter = (this.ipos & 1) != 0;
        int i = this.ipos;
        Object xp = this.xpos;
        if (xp != null) {
            if (isAfter) {
                xp = ((Pair) xp).cdr;
            }
            if (((Pair) xp).cdr == LList.Empty) {
                return false;
            }
            this.xpos = xp;
            this.ipos = (this.ipos | 1) + 2;
        } else if ((this.ipos >> 1) == 0) {
            if (this.sequence == LList.Empty) {
                return false;
            }
            this.ipos = 3;
        } else {
            Object xp2 = this.sequence;
            if (((Pair) xp2).cdr == LList.Empty) {
                return false;
            }
            this.ipos = 5;
            this.xpos = xp2;
        }
        return true;
    }

    @Override // gnu.lists.SeqPosition
    public boolean gotoPrevious() {
        if ((this.ipos >>> 1) == 0) {
            return false;
        }
        if ((this.ipos & 1) != 0) {
            this.ipos -= 3;
            return true;
        }
        int index = nextIndex();
        set((LList) this.sequence, index - 1, false);
        return true;
    }

    @Override // gnu.lists.SeqPosition
    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("LListPos[");
        sbuf.append("index:");
        sbuf.append(this.ipos);
        if (isAfter()) {
            sbuf.append(" after");
        }
        if (this.position >= 0) {
            sbuf.append(" position:");
            sbuf.append(this.position);
        }
        sbuf.append(']');
        return sbuf.toString();
    }
}
