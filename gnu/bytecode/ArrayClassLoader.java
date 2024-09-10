package gnu.bytecode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class ArrayClassLoader extends ClassLoader {
    Hashtable cmap;
    URL context;
    Hashtable map;

    public ArrayClassLoader() {
        this.map = new Hashtable(100);
        this.cmap = new Hashtable(100);
    }

    public ArrayClassLoader(ClassLoader parent) {
        super(parent);
        this.map = new Hashtable(100);
        this.cmap = new Hashtable(100);
    }

    public URL getResourceContext() {
        return this.context;
    }

    public void setResourceContext(URL context) {
        this.context = context;
    }

    public ArrayClassLoader(byte[][] classBytes) {
        this.map = new Hashtable(100);
        this.cmap = new Hashtable(100);
        int i = classBytes.length;
        while (true) {
            i--;
            if (i >= 0) {
                addClass("lambda" + i, classBytes[i]);
            } else {
                return;
            }
        }
    }

    public ArrayClassLoader(String[] classNames, byte[][] classBytes) {
        this.map = new Hashtable(100);
        this.cmap = new Hashtable(100);
        int i = classBytes.length;
        while (true) {
            i--;
            if (i >= 0) {
                addClass(classNames[i], classBytes[i]);
            } else {
                return;
            }
        }
    }

    public void addClass(Class clas) {
        this.cmap.put(clas.getName(), clas);
    }

    public void addClass(String name, byte[] bytes) {
        this.map.put(name, bytes);
    }

    public void addClass(ClassType ctype) {
        this.map.put(ctype.getName(), ctype);
    }

    @Override // java.lang.ClassLoader
    public InputStream getResourceAsStream(String name) {
        InputStream in = super.getResourceAsStream(name);
        if (in == null && name.endsWith(".class")) {
            String cname = name.substring(0, name.length() - 6).replace('/', '.');
            Object r = this.map.get(cname);
            if (r instanceof byte[]) {
                return new ByteArrayInputStream((byte[]) r);
            }
        }
        return in;
    }

    @Override // java.lang.ClassLoader
    protected URL findResource(String name) {
        if (this.context != null) {
            try {
                URL url = new URL(this.context, name);
                url.openConnection().connect();
                return url;
            } catch (Throwable th) {
            }
        }
        return super.findResource(name);
    }

    @Override // java.lang.ClassLoader
    public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clas = loadClass(name);
        if (resolve) {
            resolveClass(clas);
        }
        return clas;
    }

    @Override // java.lang.ClassLoader
    public Class loadClass(String str) throws ClassNotFoundException {
        Class<?> cls;
        Object obj = this.cmap.get(str);
        if (obj != null) {
            return (Class) obj;
        }
        Object obj2 = this.map.get(str);
        if (obj2 instanceof ClassType) {
            ClassType classType = (ClassType) obj2;
            if (classType.isExisting()) {
                obj2 = classType.reflectClass;
            } else {
                obj2 = classType.writeToArray();
            }
        }
        if (obj2 instanceof byte[]) {
            synchronized (this) {
                Object obj3 = this.map.get(str);
                if (obj3 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj3;
                    cls = defineClass(str, bArr, 0, bArr.length);
                    this.cmap.put(str, cls);
                } else {
                    cls = (Class) obj3;
                }
            }
            return cls;
        }
        if (obj2 == null) {
            return getParent().loadClass(str);
        }
        return (Class) obj2;
    }

    public static Package getContextPackage(String cname) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try {
                if (loader instanceof ArrayClassLoader) {
                    return ((ArrayClassLoader) loader).getPackage(cname);
                }
            } catch (SecurityException e) {
            }
        } catch (SecurityException e2) {
        }
        return Package.getPackage(cname);
    }
}
