package com.google.appinventor.components.runtime.collect;

import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class Sets {
    public static <K> HashSet<K> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(E... elements) {
        int capacity = ((elements.length * 4) / 3) + 1;
        HashSet<E> set = new HashSet<>(capacity);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> SortedSet<E> newSortedSet(E... elements) {
        SortedSet<E> set = new TreeSet<>();
        Collections.addAll(set, elements);
        return set;
    }
}
