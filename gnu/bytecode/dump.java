package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public class dump extends ClassFileInput {
    ClassTypeWriter writer;

    dump(InputStream str, ClassTypeWriter writer) throws IOException, ClassFormatError {
        super(str);
        this.ctype = new ClassType();
        readFormatVersion();
        readConstants();
        readClassInfo();
        readFields();
        readMethods();
        readAttributes(this.ctype);
        writer.print(this.ctype);
        writer.flush();
    }

    @Override // gnu.bytecode.ClassFileInput
    public ConstantPool readConstants() throws IOException {
        this.ctype.constants = super.readConstants();
        return this.ctype.constants;
    }

    @Override // gnu.bytecode.ClassFileInput
    public Attribute readAttribute(String name, int length, AttrContainer container) throws IOException {
        return super.readAttribute(name, length, container);
    }

    static int readMagic(InputStream in) throws IOException {
        int b;
        int magic = 0;
        for (int j = 0; j < 4 && (b = in.read()) >= 0; j++) {
            magic = (magic << 8) | (b & 255);
        }
        return magic;
    }

    public static void process(InputStream in, String filename, OutputStream out, int flags) throws IOException {
        process(in, filename, new ClassTypeWriter((ClassType) null, out, flags));
    }

    public static void process(InputStream in, String filename, Writer out, int flags) throws IOException {
        process(in, filename, new ClassTypeWriter((ClassType) null, out, flags));
    }

    public static void process(InputStream in, String filename, ClassTypeWriter out) throws IOException {
        InputStream inp = new BufferedInputStream(in);
        inp.mark(5);
        int magic = readMagic(inp);
        if (magic == -889275714) {
            out.print("Reading .class from ");
            out.print(filename);
            out.println('.');
            new dump(inp, out);
            return;
        }
        if (magic == 1347093252) {
            inp.reset();
            out.print("Reading classes from archive ");
            out.print(filename);
            out.println('.');
            ZipInputStream zin = new ZipInputStream(inp);
            while (true) {
                ZipEntry zent = zin.getNextEntry();
                if (zent != null) {
                    String name = zent.getName();
                    if (zent.isDirectory()) {
                        out.print("Archive directory: ");
                        out.print(name);
                        out.println('.');
                    } else {
                        out.println();
                        if (readMagic(zin) == -889275714) {
                            out.print("Reading class member: ");
                            out.print(name);
                            out.println('.');
                            new dump(zin, out);
                        } else {
                            out.print("Skipping non-class member: ");
                            out.print(name);
                            out.println('.');
                        }
                    }
                } else {
                    System.exit(-1);
                    return;
                }
            }
        } else {
            System.err.println("File " + filename + " is not a valid .class file");
            System.exit(-1);
        }
    }

    public static void main(String[] strArr) {
        ClassLoader classLoader;
        InputStream inputStream;
        boolean z;
        URL url;
        String str;
        int indexOf;
        int length = strArr.length;
        ClassTypeWriter classTypeWriter = new ClassTypeWriter((ClassType) null, System.out, 0);
        if (length == 0) {
            usage(System.err);
        }
        for (int i = 0; i < length; i++) {
            String str2 = strArr[i];
            if (str2.equals("-verbose") || str2.equals("--verbose")) {
                classTypeWriter.setFlags(15);
            } else if (uriSchemeSpecified(str2)) {
                try {
                    boolean startsWith = str2.startsWith("jar:");
                    if (!startsWith) {
                        z = startsWith;
                    } else {
                        String substring = str2.substring(4);
                        if (!uriSchemeSpecified(substring) && (indexOf = substring.indexOf(33)) >= 0) {
                            str2 = "jar:" + new File(substring.substring(0, indexOf)).toURI().toURL().toString() + substring.substring(indexOf);
                        }
                        if (substring.indexOf("!/") < 0) {
                            int lastIndexOf = str2.lastIndexOf(33);
                            if (lastIndexOf <= 0) {
                                z = false;
                            } else if (str2.indexOf(47, lastIndexOf) < 0) {
                                int i2 = lastIndexOf + 1;
                                str2 = str2.substring(0, i2) + '/' + str2.substring(i2).replace('.', '/') + ".class";
                                z = startsWith;
                            }
                        }
                        z = startsWith;
                    }
                    try {
                        try {
                            url = new URL(str2);
                        } catch (ZipException e) {
                            System.err.print("Error opening zip archive ");
                            System.err.print(str2);
                            System.err.println(" not found.");
                            e.printStackTrace();
                            if (e.getCause() != null) {
                                e.getCause().printStackTrace();
                            }
                            System.exit(-1);
                            inputStream = null;
                        }
                    } catch (FileNotFoundException e2) {
                        System.err.print("File for URL ");
                        System.err.print(str2);
                        System.err.println(" not found.");
                        System.exit(-1);
                        inputStream = null;
                    }
                    try {
                        inputStream = url.openConnection().getInputStream();
                        process(inputStream, str2, classTypeWriter);
                    } catch (ZipException e3) {
                        if (z) {
                            String file = url.getFile();
                            int lastIndexOf2 = file.lastIndexOf(33);
                            if (lastIndexOf2 <= 0) {
                                str = file;
                            } else {
                                str = file.substring(0, lastIndexOf2);
                            }
                            try {
                                new URL(str).openConnection().getInputStream();
                            } catch (FileNotFoundException e4) {
                                System.err.print("Jar File for URL ");
                                System.err.print(str);
                                System.err.println(" not found.");
                                System.exit(-1);
                            }
                        }
                        throw e3;
                        break;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                    System.err.println("caught ");
                    System.err.print(e5);
                    System.exit(-1);
                }
            } else {
                try {
                    inputStream = new FileInputStream(str2);
                } catch (FileNotFoundException e6) {
                    try {
                        classLoader = ObjectType.getContextClass(str2).getClassLoader();
                    } catch (NoClassDefFoundError e7) {
                        classLoader = ObjectType.getContextClassLoader();
                    } catch (Throwable th) {
                        System.err.print("File ");
                        System.err.print(str2);
                        System.err.println(" not found.");
                        System.exit(-1);
                        classLoader = null;
                    }
                    try {
                        URL resource = classLoader.getResource(str2.replace('.', '/') + ".class");
                        InputStream inputStream2 = resource.openConnection().getInputStream();
                        str2 = resource.toString();
                        inputStream = inputStream2;
                    } catch (Throwable th2) {
                        System.err.print("Can't find .class file for class ");
                        System.err.print(str2);
                        System.err.print(" - ");
                        System.err.println(th2);
                        System.exit(-1);
                        inputStream = null;
                    }
                }
                process(inputStream, str2, classTypeWriter);
            }
        }
    }

    static int uriSchemeLength(String uri) {
        int len = uri.length();
        for (int i = 0; i < len; i++) {
            char ch = uri.charAt(i);
            if (ch == ':') {
                return i;
            }
            if (i != 0) {
                if (!Character.isLetterOrDigit(ch) && ch != '+' && ch != '-' && ch != '.') {
                    return -1;
                }
            } else {
                if (!Character.isLetter(ch)) {
                    return -1;
                }
            }
        }
        return -1;
    }

    static boolean uriSchemeSpecified(String name) {
        int ulen = uriSchemeLength(name);
        if (ulen != 1 || File.separatorChar != '\\') {
            return ulen > 0;
        }
        char drive = name.charAt(0);
        if (drive < 'a' || drive > 'z') {
            return drive < 'A' || drive > 'Z';
        }
        return false;
    }

    public static void usage(PrintStream err) {
        err.println("Prints and dis-assembles the contents of JVM .class files.");
        err.println("Usage: [--verbose] class-or-jar ...");
        err.println("where a class-or-jar can be one of:");
        err.println("- a fully-qualified class name; or");
        err.println("- the name of a .class file, or a URL reference to one; or");
        err.println("- the name of a .jar or .zip archive file, or a URL reference to one.");
        err.println("If a .jar/.zip archive is named, all its.class file members are printed.");
        err.println();
        err.println("You can name a single .class member of an archive with a jar: URL,");
        err.println("which looks like: jar:jar-spec!/p1/p2/cl.class");
        err.println("The jar-spec can be a URL or the name of the .jar file.");
        err.println("You can also use the shorthand syntax: jar:jar-spec!p1.p2.cl");
        System.exit(-1);
    }
}
