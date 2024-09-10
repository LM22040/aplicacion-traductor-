package gnu.mapping;

import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class Table2D {
    private static Table2D instance = new Table2D();
    int log2Size;
    int mask;
    int num_bindings;
    Entry[] table;

    public static final Table2D getInstance() {
        return instance;
    }

    public Table2D() {
        this(64);
    }

    public Table2D(int capacity) {
        this.log2Size = 4;
        while (true) {
            int i = this.log2Size;
            if (capacity > (1 << i)) {
                this.log2Size = i + 1;
            } else {
                int capacity2 = 1 << i;
                this.table = new Entry[capacity2];
                this.mask = capacity2 - 1;
                return;
            }
        }
    }

    public Object get(Object key1, Object key2, Object defaultValue) {
        int h1 = System.identityHashCode(key1);
        int h2 = System.identityHashCode(key2);
        Entry entry = lookup(key1, key2, h1, h2, false);
        return (entry == null || entry.value == entry) ? defaultValue : entry.value;
    }

    public boolean isBound(Object key1, Object key2) {
        int h1 = System.identityHashCode(key1);
        int h2 = System.identityHashCode(key2);
        Entry entry = lookup(key1, key2, h1, h2, false);
        return (entry == null || entry.value == entry) ? false : true;
    }

    public Object put(Object key1, Object key2, Object newValue) {
        int h1 = System.identityHashCode(key1);
        int h2 = System.identityHashCode(key2);
        Entry entry = lookup(key1, key2, h1, h2, true);
        Object oldValue = entry.getValue();
        entry.value = newValue;
        return oldValue;
    }

    public Object remove(Object key1, Object key2) {
        int h1 = System.identityHashCode(key1);
        int h2 = System.identityHashCode(key2);
        int hash = h1 ^ h2;
        return remove(key1, key2, hash);
    }

    public Object remove(Object key1, Object key2, int hash) {
        return remove(key1, key2, hash);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004a, code lost:
    
        if (r10 == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004d, code lost:
    
        if (r10 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0050, code lost:
    
        r3 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004f, code lost:
    
        return r12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object remove(java.lang.Object r17, java.lang.Object r18, int r19, int r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r19 ^ r20
            int r2 = r0.mask
            r2 = r2 & r1
            r3 = 0
            gnu.mapping.Entry[] r4 = r0.table
            r4 = r4[r2]
            r5 = r4
        Ld:
            if (r5 == 0) goto L64
            java.lang.Object r6 = r5.key1
            java.lang.Object r7 = r5.key2
            r8 = 0
            boolean r9 = r6 instanceof java.lang.ref.WeakReference
            r10 = 0
            r11 = 1
            if (r9 == 0) goto L27
            r9 = r6
            java.lang.ref.WeakReference r9 = (java.lang.ref.WeakReference) r9
            java.lang.Object r6 = r9.get()
            if (r6 != 0) goto L25
            r9 = 1
            goto L26
        L25:
            r9 = 0
        L26:
            r8 = r9
        L27:
            boolean r9 = r7 instanceof java.lang.ref.WeakReference
            if (r9 == 0) goto L38
            r9 = r7
            java.lang.ref.WeakReference r9 = (java.lang.ref.WeakReference) r9
            java.lang.Object r7 = r9.get()
            if (r7 != 0) goto L36
            r9 = 1
            goto L37
        L36:
            r9 = 0
        L37:
            r8 = r9
        L38:
            gnu.mapping.Entry r9 = r5.chain
            java.lang.Object r12 = r5.value
            r13 = r17
            if (r6 != r13) goto L46
            r14 = r18
            if (r7 != r14) goto L48
            r10 = 1
            goto L48
        L46:
            r14 = r18
        L48:
            if (r8 != 0) goto L52
            if (r10 == 0) goto L4d
            goto L52
        L4d:
            if (r10 == 0) goto L50
            return r12
        L50:
            r3 = r5
            goto L62
        L52:
            if (r3 != 0) goto L59
            gnu.mapping.Entry[] r15 = r0.table
            r15[r2] = r9
            goto L5b
        L59:
            r3.chain = r9
        L5b:
            int r15 = r0.num_bindings
            int r15 = r15 - r11
            r0.num_bindings = r15
            r5.value = r5
        L62:
            r5 = r9
            goto Ld
        L64:
            r13 = r17
            r14 = r18
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.Table2D.remove(java.lang.Object, java.lang.Object, int, int):java.lang.Object");
    }

    void rehash() {
        Entry[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        int newCapacity = oldCapacity * 2;
        Entry[] newTable = new Entry[newCapacity];
        int newMask = newCapacity - 1;
        this.num_bindings = 0;
        int i = oldCapacity;
        while (true) {
            i--;
            if (i >= 0) {
                Entry first = oldTable[i];
                Entry e = first;
                while (e != null) {
                    Object k1 = e.key1;
                    Object k2 = e.key2;
                    boolean dead = false;
                    if (k1 instanceof WeakReference) {
                        k1 = ((WeakReference) k1).get();
                        dead = k1 == null;
                    }
                    if (k2 instanceof WeakReference) {
                        k2 = ((WeakReference) k2).get();
                        dead = k2 == null;
                    }
                    Entry next = e.chain;
                    if (dead) {
                        e.value = e;
                    } else {
                        int hash = System.identityHashCode(k1) ^ System.identityHashCode(k2);
                        int index = hash & newMask;
                        e.chain = newTable[index];
                        newTable[index] = e;
                        this.num_bindings++;
                    }
                    e = next;
                }
            } else {
                this.table = newTable;
                this.log2Size++;
                this.mask = newMask;
                return;
            }
        }
    }

    protected Entry lookup(Object key1, Object key2, int hash1, int hash2, boolean create) {
        int hash = hash1 ^ hash2;
        int index = this.mask & hash;
        Entry prev = null;
        Entry first = this.table[index];
        Entry e = first;
        while (e != null) {
            Object k1 = e.key1;
            Object k2 = e.key2;
            boolean dead = false;
            if (k1 instanceof WeakReference) {
                k1 = ((WeakReference) k1).get();
                dead = k1 == null;
            }
            if (k2 instanceof WeakReference) {
                k2 = ((WeakReference) k2).get();
                boolean z = k2 == null;
                dead = true;
            }
            Entry next = e.chain;
            if (dead) {
                if (prev == null) {
                    this.table[index] = next;
                } else {
                    prev.chain = next;
                }
                this.num_bindings--;
                e.value = e;
            } else {
                if (k1 == key1 && k2 == key2) {
                    return e;
                }
                prev = e;
            }
            e = next;
        }
        if (create) {
            Entry e2 = new Entry();
            Object key12 = wrapReference(key1);
            Object key22 = wrapReference(key2);
            e2.key1 = key12;
            e2.key2 = key22;
            this.num_bindings++;
            e2.chain = first;
            this.table[index] = e2;
            e2.value = e2;
            return e2;
        }
        return null;
    }

    protected Object wrapReference(Object key) {
        return (key == null || (key instanceof Symbol)) ? key : new WeakReference(key);
    }
}
