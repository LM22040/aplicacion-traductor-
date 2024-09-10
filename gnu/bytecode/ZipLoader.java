package gnu.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class ZipLoader extends ClassLoader {
    private Vector<Object> loadedClasses;
    int size;
    ZipFile zar;
    private String zipname;

    public ZipLoader(String name) throws IOException {
        this.zipname = name;
        ZipFile zipFile = new ZipFile(name);
        this.zar = zipFile;
        this.size = 0;
        Enumeration e = zipFile.entries();
        while (e.hasMoreElements()) {
            ZipEntry ent = e.nextElement();
            if (!ent.isDirectory()) {
                this.size++;
            }
        }
        this.loadedClasses = new Vector<>(this.size);
    }

    @Override // java.lang.ClassLoader
    public Class loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> cls;
        int indexOf = this.loadedClasses.indexOf(str);
        boolean z2 = true;
        if (indexOf >= 0) {
            cls = (Class) this.loadedClasses.elementAt(indexOf + 1);
        } else if (this.zar == null && this.loadedClasses.size() == this.size * 2) {
            cls = Class.forName(str);
        } else {
            String str2 = str.replace('.', '/') + ".class";
            if (this.zar != null) {
                z2 = false;
            } else {
                try {
                    this.zar = new ZipFile(this.zipname);
                } catch (IOException e) {
                    throw new ClassNotFoundException("IOException while loading " + str2 + " from ziparchive \"" + str + "\": " + e.toString());
                }
            }
            ZipEntry entry = this.zar.getEntry(str2);
            if (entry == null) {
                if (z2) {
                    try {
                        close();
                    } catch (IOException e2) {
                        throw new RuntimeException("failed to close \"" + this.zipname + "\"");
                    }
                }
                cls = Class.forName(str);
            } else {
                try {
                    int size = (int) entry.getSize();
                    byte[] bArr = new byte[size];
                    new DataInputStream(this.zar.getInputStream(entry)).readFully(bArr);
                    Class<?> defineClass = defineClass(str, bArr, 0, size);
                    this.loadedClasses.addElement(str);
                    this.loadedClasses.addElement(defineClass);
                    if (this.size * 2 == this.loadedClasses.size()) {
                        close();
                    }
                    cls = defineClass;
                } catch (IOException e3) {
                    throw new ClassNotFoundException("IOException while loading " + str2 + " from ziparchive \"" + str + "\": " + e3.toString());
                }
            }
        }
        if (z) {
            resolveClass(cls);
        }
        return cls;
    }

    public Class loadAllClasses() throws IOException {
        Enumeration e = this.zar.entries();
        Class mainClass = null;
        while (e.hasMoreElements()) {
            ZipEntry member = e.nextElement();
            String name = member.getName().replace('/', '.');
            String name2 = name.substring(0, name.length() - "/class".length());
            int member_size = (int) member.getSize();
            InputStream strm = this.zar.getInputStream(member);
            byte[] bytes = new byte[member_size];
            new DataInputStream(strm).readFully(bytes);
            Class clas = defineClass(name2, bytes, 0, member_size);
            if (mainClass == null) {
                mainClass = clas;
            }
            this.loadedClasses.addElement(name2);
            this.loadedClasses.addElement(clas);
        }
        close();
        return mainClass;
    }

    public void close() throws IOException {
        ZipFile zipFile = this.zar;
        if (zipFile != null) {
            zipFile.close();
        }
        this.zar = null;
    }
}
