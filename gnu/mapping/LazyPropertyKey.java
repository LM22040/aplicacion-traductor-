package gnu.mapping;

/* loaded from: classes.dex */
public class LazyPropertyKey<T> extends PropertyKey<T> {
    public LazyPropertyKey(String name) {
        super(name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // gnu.mapping.PropertyKey
    public T get(PropertySet propertySet, T t) {
        T t2 = (T) propertySet.getProperty(this, t);
        if (t2 instanceof String) {
            String str = (String) t2;
            int i = str.charAt(0) == '*' ? 1 : 0;
            int indexOf = str.indexOf(58);
            if (indexOf <= i || indexOf >= str.length() - 1) {
                throw new RuntimeException("lazy property " + this + " must have the form \"ClassName:fieldName\" or \"ClassName:staticMethodName\"");
            }
            String substring = str.substring(i, indexOf);
            String substring2 = str.substring(indexOf + 1);
            try {
                Class<?> cls = Class.forName(substring, true, propertySet.getClass().getClassLoader());
                T t3 = i == 0 ? (T) cls.getField(substring2).get(null) : (T) cls.getDeclaredMethod(substring2, Object.class).invoke(null, propertySet);
                propertySet.setProperty(this, t3);
                return t3;
            } catch (Throwable th) {
                throw new RuntimeException("lazy property " + this + " has specifier \"" + str + "\" but there is no such " + (i == 0 ? "field" : "method"), th);
            }
        }
        return t2;
    }

    public void set(PropertySet container, String specifier) {
        container.setProperty(this, specifier);
    }
}
