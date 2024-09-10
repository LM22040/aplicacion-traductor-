package gnu.kawa.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class AbstractHashTable<Entry extends Map.Entry<K, V>, K, V> extends AbstractMap<K, V> {
    public static final int DEFAULT_INITIAL_SIZE = 64;
    protected int mask;
    protected int num_bindings;
    protected Entry[] table;

    protected abstract Entry[] allocEntries(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getEntryHashCode(Entry entry);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Entry getEntryNext(Entry entry);

    protected abstract Entry makeEntry(K k, int i, V v);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void setEntryNext(Entry entry, Entry entry2);

    public AbstractHashTable() {
        this(64);
    }

    public AbstractHashTable(int capacity) {
        int log2Size = 4;
        while (capacity > (1 << log2Size)) {
            log2Size++;
        }
        int capacity2 = 1 << log2Size;
        this.table = allocEntries(capacity2);
        this.mask = capacity2 - 1;
    }

    public int hash(Object key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int hashToIndex(int hash) {
        return this.mask & (hash ^ (hash >>> 15));
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected boolean matches(Object key, int hash, Entry node) {
        return getEntryHashCode(node) == hash && matches(node.getKey(), key);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean matches(K key1, Object key2) {
        return key1 == key2 || (key1 != null && key1.equals(key2));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        return get(key, null);
    }

    public Entry getNode(Object obj) {
        int hash = hash(obj);
        Entry entry = this.table[hashToIndex(hash)];
        while (entry != null) {
            if (matches(obj, hash, entry)) {
                return entry;
            }
            entry = getEntryNext(entry);
        }
        return null;
    }

    public V get(Object obj, V v) {
        Entry node = getNode(obj);
        return node == null ? v : (V) node.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [Entry extends java.util.Map$Entry<K, V>[]] */
    /* JADX WARN: Type inference failed for: r12v0, types: [gnu.kawa.util.AbstractHashTable<Entry extends java.util.Map$Entry<K, V>, K, V>, gnu.kawa.util.AbstractHashTable] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.Map$Entry] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.Map$Entry] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.util.Map$Entry] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.util.Map$Entry] */
    public void rehash() {
        ?? r0 = this.table;
        int length = r0.length;
        int i = length * 2;
        Entry[] entryArr = (Entry[]) allocEntries(i);
        this.table = entryArr;
        this.mask = i - 1;
        int i2 = length;
        while (true) {
            i2--;
            if (i2 >= 0) {
                ?? r6 = r0[i2];
                Entry entry = r6;
                if (r6 != null) {
                    Map.Entry entryNext = getEntryNext(r6);
                    entry = r6;
                    if (entryNext != null) {
                        ?? r7 = null;
                        do {
                            Object obj = r6;
                            r6 = getEntryNext(obj);
                            setEntryNext(obj, r7);
                            r7 = obj;
                        } while (r6 != null);
                        entry = r7;
                    }
                }
                Entry entry2 = entry;
                while (entry2 != null) {
                    ?? entryNext2 = getEntryNext(entry2);
                    int hashToIndex = hashToIndex(getEntryHashCode(entry2));
                    setEntryNext(entry2, entryArr[hashToIndex]);
                    entryArr[hashToIndex] = entry2;
                    entry2 = entryNext2;
                }
            } else {
                return;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        return put(key, hash(key), value);
    }

    public V put(K k, int i, V v) {
        int hashToIndex = hashToIndex(i);
        Entry entry = this.table[hashToIndex];
        Entry entry2 = entry;
        while (entry2 != null) {
            if (matches(k, i, entry2)) {
                V v2 = (V) entry2.getValue();
                entry2.setValue(v);
                return v2;
            }
            entry2 = getEntryNext(entry2);
        }
        int i2 = this.num_bindings + 1;
        this.num_bindings = i2;
        if (i2 >= this.table.length) {
            rehash();
            hashToIndex = hashToIndex(i);
            entry = this.table[hashToIndex];
        }
        Entry makeEntry = makeEntry(k, i, v);
        setEntryNext(makeEntry, entry);
        ((Entry[]) this.table)[hashToIndex] = makeEntry;
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        int hash = hash(obj);
        int hashToIndex = hashToIndex(hash);
        Entry entry = null;
        Entry entry2 = this.table[hashToIndex];
        while (entry2 != null) {
            Entry entryNext = getEntryNext(entry2);
            if (matches(obj, hash, entry2)) {
                if (entry == null) {
                    this.table[hashToIndex] = entryNext;
                } else {
                    setEntryNext(entry, entryNext);
                }
                this.num_bindings--;
                return (V) entry2.getValue();
            }
            entry = entry2;
            entry2 = entryNext;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Entry[] t = this.table;
        int i = t.length;
        while (true) {
            i--;
            if (i >= 0) {
                Entry e = t[i];
                while (e != null) {
                    Entry next = getEntryNext(e);
                    setEntryNext(e, null);
                    e = next;
                }
                t[i] = null;
            } else {
                this.num_bindings = 0;
                return;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.num_bindings;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return new AbstractEntrySet(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AbstractEntrySet<Entry extends Map.Entry<K, V>, K, V> extends AbstractSet<Entry> {
        AbstractHashTable<Entry, K, V> htable;

        public AbstractEntrySet(AbstractHashTable<Entry, K, V> htable) {
            this.htable = htable;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.htable.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Entry> iterator() {
            return (Iterator<Entry>) new Iterator<Entry>() { // from class: gnu.kawa.util.AbstractHashTable.AbstractEntrySet.1
                int curIndex = -1;
                Entry currentEntry;
                Entry nextEntry;
                int nextIndex;
                Entry previousEntry;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    if (this.curIndex < 0) {
                        int length = AbstractEntrySet.this.htable.table.length;
                        this.nextIndex = length;
                        this.curIndex = length;
                        advance();
                    }
                    return this.nextEntry != null;
                }

                private void advance() {
                    while (this.nextEntry == null) {
                        int i = this.nextIndex - 1;
                        this.nextIndex = i;
                        if (i >= 0) {
                            this.nextEntry = AbstractEntrySet.this.htable.table[this.nextIndex];
                        } else {
                            return;
                        }
                    }
                }

                @Override // java.util.Iterator
                public Entry next() {
                    Entry entry = this.nextEntry;
                    if (entry == null) {
                        throw new NoSuchElementException();
                    }
                    this.previousEntry = this.currentEntry;
                    this.currentEntry = entry;
                    this.curIndex = this.nextIndex;
                    this.nextEntry = AbstractEntrySet.this.htable.getEntryNext(this.currentEntry);
                    advance();
                    return this.currentEntry;
                }

                @Override // java.util.Iterator
                public void remove() {
                    Entry entry = this.previousEntry;
                    if (entry == this.currentEntry) {
                        throw new IllegalStateException();
                    }
                    if (entry == null) {
                        AbstractEntrySet.this.htable.table[this.curIndex] = this.nextEntry;
                    } else {
                        AbstractEntrySet.this.htable.setEntryNext(this.previousEntry, this.nextEntry);
                    }
                    AbstractHashTable<Entry, K, V> abstractHashTable = AbstractEntrySet.this.htable;
                    abstractHashTable.num_bindings--;
                    this.previousEntry = this.currentEntry;
                }
            };
        }
    }
}
