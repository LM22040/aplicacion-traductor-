package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class AbstractWeakHashTable<K, V> extends AbstractHashTable<WEntry<K, V>, K, V> {
    ReferenceQueue<V> rqueue;

    protected abstract K getKeyFromValue(V v);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // gnu.kawa.util.AbstractHashTable
    protected /* bridge */ /* synthetic */ Map.Entry makeEntry(Object obj, int x1, Object obj2) {
        return makeEntry((AbstractWeakHashTable<K, V>) obj, x1, (int) obj2);
    }

    public AbstractWeakHashTable() {
        super(64);
        this.rqueue = new ReferenceQueue<>();
    }

    public AbstractWeakHashTable(int capacity) {
        super(capacity);
        this.rqueue = new ReferenceQueue<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.kawa.util.AbstractHashTable
    public int getEntryHashCode(WEntry<K, V> entry) {
        return entry.hash;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.kawa.util.AbstractHashTable
    public WEntry<K, V> getEntryNext(WEntry<K, V> entry) {
        return entry.next;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.kawa.util.AbstractHashTable
    public void setEntryNext(WEntry<K, V> entry, WEntry<K, V> next) {
        entry.next = next;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.kawa.util.AbstractHashTable
    public WEntry<K, V>[] allocEntries(int n) {
        return new WEntry[n];
    }

    protected V getValueIfMatching(WEntry<K, V> node, Object key) {
        V val = node.getValue();
        if (val != null && matches(getKeyFromValue(val), key)) {
            return val;
        }
        return null;
    }

    @Override // gnu.kawa.util.AbstractHashTable
    public V get(Object obj, V v) {
        cleanup();
        return (V) super.get(obj, v);
    }

    @Override // gnu.kawa.util.AbstractHashTable
    public int hash(Object key) {
        return System.identityHashCode(key);
    }

    protected boolean valuesEqual(V oldValue, V newValue) {
        return oldValue == newValue;
    }

    @Override // gnu.kawa.util.AbstractHashTable
    protected WEntry<K, V> makeEntry(K key, int hash, V value) {
        return new WEntry<>(value, this, hash);
    }

    @Override // gnu.kawa.util.AbstractHashTable, java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        cleanup();
        int hash = hash(key);
        int index = hashToIndex(hash);
        WEntry<K, V> first = ((WEntry[]) this.table)[index];
        WEntry<K, V> node = first;
        WEntry<K, V> prev = null;
        V oldValue = null;
        while (node != null) {
            V curValue = node.getValue();
            if (curValue == value) {
                return curValue;
            }
            WEntry<K, V> next = node.next;
            if (curValue != null && valuesEqual(curValue, value)) {
                if (prev == null) {
                    ((WEntry[]) this.table)[index] = next;
                } else {
                    prev.next = next;
                }
                oldValue = curValue;
            } else {
                prev = node;
            }
            node = next;
        }
        int i = this.num_bindings + 1;
        this.num_bindings = i;
        if (i >= ((WEntry[]) this.table).length) {
            rehash();
            index = hashToIndex(hash);
            first = ((WEntry[]) this.table)[index];
        }
        WEntry<K, V> node2 = makeEntry((AbstractWeakHashTable<K, V>) null, hash, (int) value);
        node2.next = first;
        ((WEntry[]) this.table)[index] = node2;
        return oldValue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cleanup() {
        cleanup(this, this.rqueue);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <Entry extends Map.Entry<K, V>, K, V> void cleanup(AbstractHashTable<Entry, ?, ?> abstractHashTable, ReferenceQueue<?> referenceQueue) {
        while (true) {
            Map.Entry entry = (Map.Entry) referenceQueue.poll();
            if (entry != null) {
                int hashToIndex = abstractHashTable.hashToIndex(abstractHashTable.getEntryHashCode(entry));
                Map.Entry entry2 = null;
                Map.Entry entry3 = abstractHashTable.table[hashToIndex];
                while (true) {
                    if (entry3 != null) {
                        Map.Entry entryNext = abstractHashTable.getEntryNext(entry3);
                        if (entry3 == entry) {
                            if (entry2 == null) {
                                abstractHashTable.table[hashToIndex] = entryNext;
                            } else {
                                abstractHashTable.setEntryNext(entry2, entryNext);
                            }
                        } else {
                            entry2 = entry3;
                            entry3 = entryNext;
                        }
                    }
                }
                abstractHashTable.num_bindings--;
            } else {
                return;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class WEntry<K, V> extends WeakReference<V> implements Map.Entry<K, V> {
        public int hash;
        AbstractWeakHashTable<K, V> htable;
        public WEntry next;

        public WEntry(V value, AbstractWeakHashTable<K, V> htable, int hash) {
            super(value, htable.rqueue);
            this.htable = htable;
            this.hash = hash;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Map.Entry
        public K getKey() {
            Object obj = get();
            if (obj == null) {
                return null;
            }
            return (K) this.htable.getKeyFromValue(obj);
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return (V) get();
        }

        @Override // java.util.Map.Entry
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }
    }
}
