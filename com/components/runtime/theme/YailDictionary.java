package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.FString;
import gnu.lists.LList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes.dex */
public class YailDictionary extends LinkedHashMap<Object, Object> implements YailObject<YailList> {
    public static final Object ALL = new Object() { // from class: com.google.appinventor.components.runtime.util.YailDictionary.1
        public String toString() {
            return "ALL_ITEMS";
        }
    };
    private static final KeyTransformer IDENTITY = new KeyTransformer() { // from class: com.google.appinventor.components.runtime.util.YailDictionary.2
        @Override // com.google.appinventor.components.runtime.util.YailDictionary.KeyTransformer
        public Object transform(Object key) {
            return key;
        }
    };
    private static final String LOG_TAG = "YailDictionary";
    private final KeyTransformer keyTransformer;

    /* loaded from: classes.dex */
    public interface KeyTransformer {
        Object transform(Object obj);
    }

    public YailDictionary() {
        this.keyTransformer = IDENTITY;
    }

    public YailDictionary(Map<?, ?> prevMap) {
        this(prevMap, IDENTITY);
    }

    public YailDictionary(Map<?, ?> prevMap, KeyTransformer keyTransformer) {
        this.keyTransformer = keyTransformer;
        for (Map.Entry<?, ?> entry : prevMap.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public static YailDictionary makeDictionary() {
        return new YailDictionary();
    }

    public static YailDictionary makeDictionary(Map<Object, Object> prevMap) {
        return new YailDictionary(prevMap);
    }

    public static YailDictionary makeDictionary(Object... keysAndValues) {
        if (keysAndValues.length % 2 == 1) {
            throw new IllegalArgumentException("Expected an even number of key-value entries.");
        }
        YailDictionary dict = new YailDictionary();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            dict.put(keysAndValues[i], keysAndValues[i + 1]);
        }
        return dict;
    }

    public static YailDictionary makeDictionary(List<YailList> pairs) {
        Map<Object, Object> map = new LinkedHashMap<>();
        for (YailList currentYailList : pairs) {
            Object currentKey = currentYailList.getObject(0);
            Object currentValue = currentYailList.getObject(1);
            map.put(currentKey, currentValue);
        }
        return new YailDictionary(map);
    }

    private static Boolean isAlist(YailList yailList) {
        boolean hadPair = false;
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            Object currentPair = it.next();
            if ((currentPair instanceof YailList) && ((YailList) currentPair).size() == 2) {
                hadPair = true;
            }
            return false;
        }
        return Boolean.valueOf(hadPair);
    }

    public static YailDictionary alistToDict(YailList alist) {
        YailDictionary map = new YailDictionary();
        Iterator it = ((LList) alist.getCdr()).iterator();
        while (it.hasNext()) {
            Object o = it.next();
            YailList currentPair = (YailList) o;
            Object currentKey = currentPair.getObject(0);
            Object currentValue = currentPair.getObject(1);
            if ((currentValue instanceof YailList) && isAlist((YailList) currentValue).booleanValue()) {
                map.put(currentKey, alistToDict((YailList) currentValue));
            } else if (currentValue instanceof YailList) {
                map.put(currentKey, checkList((YailList) currentValue));
            } else {
                map.put(currentKey, currentValue);
            }
        }
        return map;
    }

    private static YailList checkList(YailList list) {
        Object[] checked = new Object[list.size()];
        int i = 0;
        Iterator<?> it = list.iterator();
        it.next();
        boolean processed = false;
        while (it.hasNext()) {
            Object o = it.next();
            if (o instanceof YailList) {
                if (isAlist((YailList) o).booleanValue()) {
                    checked[i] = alistToDict((YailList) o);
                    processed = true;
                } else {
                    checked[i] = checkList((YailList) o);
                    if (checked[i] != o) {
                        processed = true;
                    }
                }
            } else {
                checked[i] = o;
            }
            i++;
        }
        if (processed) {
            return YailList.makeList(checked);
        }
        return list;
    }

    private static YailList checkListForDicts(YailList list) {
        List<Object> copy = new ArrayList<>();
        Iterator it = ((LList) list.getCdr()).iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o instanceof YailDictionary) {
                copy.add(dictToAlist((YailDictionary) o));
            } else if (o instanceof YailList) {
                copy.add(checkListForDicts((YailList) o));
            } else {
                copy.add(o);
            }
        }
        return YailList.makeList((List) copy);
    }

    public static YailList dictToAlist(YailDictionary dict) {
        List<Object> list = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : dict.entrySet()) {
            list.add(YailList.makeList(new Object[]{entry.getKey(), entry.getValue()}));
        }
        return YailList.makeList((List) list);
    }

    public void setPair(YailList pair) {
        put(pair.getObject(0), pair.getObject(1));
    }

    private Object getFromList(List<?> list, Object obj) {
        int i = !(list instanceof YailList) ? 1 : 0;
        try {
            if (obj instanceof FString) {
                return list.get(Integer.parseInt(obj.toString()) - i);
            }
            if (obj instanceof String) {
                return list.get(Integer.parseInt((String) obj) - i);
            }
            if (obj instanceof Number) {
                return list.get(((Number) obj).intValue() - i);
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            Log.w(LOG_TAG, "Requested too large of an index: " + obj, e);
            throw new YailRuntimeError("Requested too large of an index: " + obj, "IndexOutOfBoundsException");
        } catch (NumberFormatException e2) {
            Log.w(LOG_TAG, "Unable to parse key as integer: " + obj, e2);
            throw new YailRuntimeError("Unable to parse key as integer: " + obj, "NumberParseException");
        }
    }

    public Object getObjectAtKeyPath(List<?> keysOrIndices) {
        Object target = this;
        for (Object currentKey : keysOrIndices) {
            if (target instanceof Map) {
                target = ((Map) target).get(currentKey);
            } else if ((target instanceof YailList) && isAlist((YailList) target).booleanValue()) {
                target = alistToDict((YailList) target).get(currentKey);
            } else if (target instanceof List) {
                target = getFromList((List) target, currentKey);
            } else {
                return null;
            }
        }
        return target;
    }

    private static Collection<Object> allOf(Map<Object, Object> map) {
        return map.values();
    }

    private static Collection<Object> allOf(List<Object> list) {
        if (list instanceof YailList) {
            if (isAlist((YailList) list).booleanValue()) {
                ArrayList<Object> result = new ArrayList<>();
                Iterator it = ((LList) ((YailList) list).getCdr()).iterator();
                while (it.hasNext()) {
                    Object o = it.next();
                    result.add(((YailList) o).getObject(1));
                }
                return result;
            }
            return (Collection) ((YailList) list).getCdr();
        }
        return list;
    }

    private static Collection<Object> allOf(Object object) {
        if (object instanceof Map) {
            return allOf((Map<Object, Object>) object);
        }
        if (object instanceof List) {
            return allOf((List<Object>) object);
        }
        return Collections.emptyList();
    }

    private static Object alistLookup(YailList alist, Object target) {
        Iterator it = ((LList) alist.getCdr()).iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (!(o instanceof YailList)) {
                return null;
            }
            Object key = ((YailList) o).getObject(0);
            if (key.equals(target)) {
                return ((YailList) o).getObject(1);
            }
        }
        return null;
    }

    private static <T> List<Object> walkKeyPath(Object root, List<T> keysOrIndices, List<Object> result) {
        if (keysOrIndices.isEmpty()) {
            if (root != null) {
                result.add(root);
            }
            return result;
        }
        if (root == null) {
            return result;
        }
        Object currentKey = keysOrIndices.get(0);
        List<T> childKeys = keysOrIndices.subList(1, keysOrIndices.size());
        if (currentKey == ALL) {
            for (Object child : allOf(root)) {
                walkKeyPath(child, childKeys, result);
            }
        } else if (root instanceof Map) {
            walkKeyPath(((Map) root).get(currentKey), childKeys, result);
        } else if ((root instanceof YailList) && isAlist((YailList) root).booleanValue()) {
            Object value = alistLookup((YailList) root, currentKey);
            if (value != null) {
                walkKeyPath(value, childKeys, result);
            }
        } else if (root instanceof List) {
            int index = keyToIndex((List) root, currentKey);
            try {
                walkKeyPath(((List) root).get(index), childKeys, result);
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static <T> List<Object> walkKeyPath(YailObject<?> object, List<T> keysOrIndices) {
        return walkKeyPath(object, keysOrIndices, new ArrayList());
    }

    private static int keyToIndex(List<?> list, Object obj) {
        int parseInt;
        int i = !(list instanceof YailList) ? 1 : 0;
        if (obj instanceof Number) {
            parseInt = ((Number) obj).intValue();
        } else {
            try {
                parseInt = Integer.parseInt(obj.toString());
            } catch (NumberFormatException e) {
                throw new DispatchableError(ErrorMessages.ERROR_NUMBER_FORMAT_EXCEPTION, obj.toString());
            }
        }
        int i2 = parseInt - i;
        if (i2 < 0 || i2 >= (list.size() + 1) - i) {
            try {
                throw new DispatchableError(ErrorMessages.ERROR_INDEX_MISSING_IN_LIST, Integer.valueOf(i2 + i), JsonUtil.getJsonRepresentation(list));
            } catch (JSONException e2) {
                Log.e(LOG_TAG, "Unable to serialize object as JSON", e2);
                throw new YailRuntimeError(e2.getMessage(), "JSON Error");
            }
        }
        return i2;
    }

    private Object lookupTargetForKey(Object target, Object key) {
        if (target instanceof YailDictionary) {
            return ((YailDictionary) target).get(key);
        }
        if (target instanceof List) {
            return ((List) target).get(keyToIndex((List) target, key));
        }
        Object[] objArr = new Object[1];
        objArr[0] = target == null ? "null" : target.getClass().getSimpleName();
        throw new DispatchableError(ErrorMessages.ERROR_INVALID_VALUE_IN_PATH, objArr);
    }

    public void setValueForKeyPath(List<?> keys, Object value) {
        Object target = this;
        Iterator<?> it = keys.iterator();
        if (keys.isEmpty()) {
            return;
        }
        while (it.hasNext()) {
            Object key = it.next();
            if (it.hasNext()) {
                target = lookupTargetForKey(target, key);
            } else if (target instanceof YailDictionary) {
                ((YailDictionary) target).put(key, value);
            } else if (target instanceof YailList) {
                LList l = (LList) target;
                l.getIterator(keyToIndex((List) target, key)).set(value);
            } else if (target instanceof List) {
                ((List) target).set(keyToIndex((List) target, key), value);
            } else {
                throw new DispatchableError(ErrorMessages.ERROR_INVALID_VALUE_IN_PATH);
            }
        }
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        if (key instanceof FString) {
            key = key.toString();
        }
        return super.containsKey(this.keyTransformer.transform(key));
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        if (value instanceof FString) {
            return super.containsValue(value.toString());
        }
        return super.containsValue(value);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object get(Object key) {
        if (key instanceof FString) {
            key = key.toString();
        }
        return super.get(this.keyTransformer.transform(key));
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object put(Object key, Object value) {
        if (key instanceof FString) {
            key = key.toString();
        }
        Object key2 = this.keyTransformer.transform(key);
        if (value instanceof FString) {
            value = value.toString();
        }
        return super.put(key2, value);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object remove(Object key) {
        if (key instanceof FString) {
            key = key.toString();
        }
        return super.remove(this.keyTransformer.transform(key));
    }

    @Override // java.util.AbstractMap
    public String toString() {
        try {
            return JsonUtil.getJsonRepresentation(this);
        } catch (JSONException e) {
            throw new YailRuntimeError(e.getMessage(), "JSON Error");
        }
    }

    @Override // com.google.appinventor.components.runtime.util.YailObject
    public Object getObject(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int i = index;
        for (Map.Entry<Object, Object> e : entrySet()) {
            if (i == 0) {
                return Lists.newArrayList(e.getKey(), e.getValue());
            }
            i--;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // com.google.appinventor.components.runtime.util.YailObject, java.lang.Iterable
    public Iterator<YailList> iterator() {
        return new DictIterator(entrySet().iterator());
    }

    /* loaded from: classes.dex */
    private static class DictIterator implements Iterator<YailList> {
        final Iterator<Map.Entry<Object, Object>> it;

        DictIterator(Iterator<Map.Entry<Object, Object>> it) {
            this.it = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.it.hasNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public YailList next() {
            Map.Entry<Object, Object> e = this.it.next();
            return YailList.makeList(new Object[]{e.getKey(), e.getValue()});
        }

        @Override // java.util.Iterator
        public void remove() {
            this.it.remove();
        }
    }
}
