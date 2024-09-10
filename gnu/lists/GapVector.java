package gnu.lists;

/* loaded from: classes.dex */
public class GapVector extends AbstractSequence implements Sequence {
    public SimpleVector base;
    public int gapEnd;
    public int gapStart;

    public GapVector(SimpleVector base) {
        this.base = base;
        this.gapStart = 0;
        this.gapEnd = base.size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GapVector() {
    }

    @Override // gnu.lists.AbstractSequence, gnu.lists.Sequence, java.util.List, java.util.Collection, com.google.appinventor.components.runtime.util.YailObject
    public int size() {
        return this.base.size - (this.gapEnd - this.gapStart);
    }

    @Override // gnu.lists.AbstractSequence
    public boolean hasNext(int ipos) {
        int index = ipos >>> 1;
        int i = this.gapStart;
        if (index >= i) {
            index += this.gapEnd - i;
        }
        return index < this.base.size;
    }

    @Override // gnu.lists.AbstractSequence
    public int getNextKind(int ipos) {
        if (hasNext(ipos)) {
            return this.base.getElementKind();
        }
        return 0;
    }

    @Override // gnu.lists.AbstractSequence
    public Object get(int index) {
        int i = this.gapStart;
        if (index >= i) {
            index += this.gapEnd - i;
        }
        return this.base.get(index);
    }

    @Override // gnu.lists.AbstractSequence, gnu.lists.Sequence, java.util.List
    public Object set(int index, Object value) {
        int i = this.gapStart;
        if (index >= i) {
            index += this.gapEnd - i;
        }
        return this.base.set(index, value);
    }

    @Override // gnu.lists.AbstractSequence, gnu.lists.Sequence
    public void fill(Object value) {
        SimpleVector simpleVector = this.base;
        simpleVector.fill(this.gapEnd, simpleVector.size, value);
        this.base.fill(0, this.gapStart, value);
    }

    @Override // gnu.lists.AbstractSequence
    public void fillPosRange(int fromPos, int toPos, Object value) {
        int from = fromPos == -1 ? this.base.size : fromPos >>> 1;
        int to = toPos == -1 ? this.base.size : toPos >>> 1;
        int limit = this.gapStart;
        if (limit >= to) {
            limit = to;
        }
        for (int i = from; i < limit; i++) {
            this.base.setBuffer(i, value);
        }
        for (int i2 = this.gapEnd; i2 < to; i2++) {
            this.base.setBuffer(i2, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void shiftGap(int newGapStart) {
        int i = this.gapStart;
        int delta = newGapStart - i;
        if (delta > 0) {
            this.base.shift(this.gapEnd, i, delta);
        } else if (delta < 0) {
            this.base.shift(newGapStart, this.gapEnd + delta, -delta);
        }
        this.gapEnd += delta;
        this.gapStart = newGapStart;
    }

    protected final void gapReserve(int size) {
        gapReserve(this.gapStart, size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void gapReserve(int where, int needed) {
        int i = this.gapEnd;
        int i2 = this.gapStart;
        if (needed > i - i2) {
            int oldLength = this.base.size;
            int newLength = oldLength >= 16 ? oldLength * 2 : 16;
            int i3 = this.gapEnd;
            int i4 = this.gapStart;
            int size = oldLength - (i3 - i4);
            int minLength = size + needed;
            if (newLength < minLength) {
                newLength = minLength;
            }
            int newGapEnd = (newLength - size) + where;
            this.base.resizeShift(i4, i3, where, newGapEnd);
            this.gapStart = where;
            this.gapEnd = newGapEnd;
            return;
        }
        if (where != i2) {
            shiftGap(where);
        }
    }

    public int getSegment(int where, int len) {
        int length = size();
        if (where < 0 || where > length) {
            return -1;
        }
        if (len < 0) {
            len = 0;
        } else if (where + len > length) {
            len = length - where;
        }
        int i = where + len;
        int i2 = this.gapStart;
        if (i <= i2) {
            return where;
        }
        if (where >= i2) {
            return (this.gapEnd - i2) + where;
        }
        if (i2 - where > (len >> 1)) {
            shiftGap(where + len);
            return where;
        }
        shiftGap(where);
        return (this.gapEnd - this.gapStart) + where;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.lists.AbstractSequence
    public int addPos(int ipos, Object value) {
        int index = ipos >>> 1;
        int i = this.gapStart;
        if (index >= i) {
            index += this.gapEnd - i;
        }
        add(index, value);
        return ((index + 1) << 1) | 1;
    }

    @Override // gnu.lists.AbstractSequence, java.util.List
    public void add(int index, Object o) {
        gapReserve(index, 1);
        this.base.set(index, o);
        this.gapStart++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.lists.AbstractSequence
    public void removePosRange(int ipos0, int ipos1) {
        int ipos02 = ipos0 >>> 1;
        int ipos12 = ipos1 >>> 1;
        int i = this.gapEnd;
        if (ipos02 > i) {
            shiftGap((ipos02 - i) + this.gapStart);
        } else if (ipos12 < this.gapStart) {
            shiftGap(ipos12);
        }
        int i2 = this.gapStart;
        if (ipos02 < i2) {
            this.base.clearBuffer(ipos02, i2 - ipos02);
            this.gapStart = ipos02;
        }
        int i3 = this.gapEnd;
        if (ipos12 > i3) {
            this.base.clearBuffer(i3, ipos12 - i3);
            this.gapEnd = ipos12;
        }
    }

    @Override // gnu.lists.AbstractSequence
    public int createPos(int i, boolean z) {
        int i2 = this.gapStart;
        if (i > i2) {
            i += this.gapEnd - i2;
        }
        return (i << 1) | (z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.lists.AbstractSequence
    public boolean isAfterPos(int ipos) {
        return (ipos & 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.lists.AbstractSequence
    public int nextIndex(int ipos) {
        int index = ipos == -1 ? this.base.size : ipos >>> 1;
        int i = this.gapStart;
        if (index > i) {
            return index - (this.gapEnd - i);
        }
        return index;
    }

    @Override // gnu.lists.AbstractSequence
    public void consumePosRange(int iposStart, int iposEnd, Consumer out) {
        if (out.ignoring()) {
            return;
        }
        int i = iposStart >>> 1;
        int end = iposEnd >>> 1;
        int lim = this.gapStart;
        if (i < lim) {
            if (end > lim) {
                lim = end;
            }
            consumePosRange(iposStart, lim << 1, out);
        }
        int lim2 = this.gapEnd;
        if (end > lim2) {
            if (i >= lim2) {
                lim2 = i;
            }
            consumePosRange(lim2 << 1, iposEnd, out);
        }
    }
}
