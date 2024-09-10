package gnu.kawa.util;

import java.util.Map;

/* loaded from: classes.dex */
public class HashNode<K, V> implements Map.Entry<K, V> {
    int hash;
    K key;
    public HashNode<K, V> next;
    V value;

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        K k = this.key;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        return hashCode ^ (v != null ? v.hashCode() : 0);
    }

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V get(V defaultValue) {
        return getValue();
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object o) {
        if (!(o instanceof HashNode)) {
            return false;
        }
        HashNode h2 = (HashNode) o;
        K k = this.key;
        if (k == null) {
            if (h2.key != null) {
                return false;
            }
        } else if (!k.equals(h2.key)) {
            return false;
        }
        V v = this.value;
        if (v == null) {
            if (h2.value != null) {
                return false;
            }
        } else if (!v.equals(h2.value)) {
            return false;
        }
        return true;
    }
}
