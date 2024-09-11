package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.FileUtils;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.io.File;
import java.io.IOException;
import kawa.standard.readchar;

/* compiled from: files.scm */
/* loaded from: classes.dex */
public class files extends ModuleBody {
    public static final ModuleMethod $Mn$Grpathname;
    public static final ModuleMethod $Pcfile$Mnseparator;
    public static final files $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod URI$Qu;
    public static final ModuleMethod absolute$Mnpath$Qu;
    public static final ModuleMethod copy$Mnfile;
    public static final ModuleMethod create$Mndirectory;
    public static final ModuleMethod delete$Mnfile;
    public static final ModuleMethod directory$Mnfiles;
    public static final ModuleMethod file$Mndirectory$Qu;
    public static final ModuleMethod file$Mnexists$Qu;
    public static final ModuleMethod file$Mnreadable$Qu;
    public static final ModuleMethod file$Mnwritable$Qu;
    public static final ModuleMethod filepath$Qu;
    public static final ModuleMethod make$Mntemporary$Mnfile;
    public static final ModuleMethod path$Mnauthority;
    public static final ModuleMethod path$Mndirectory;
    public static final ModuleMethod path$Mnextension;
    public static final ModuleMethod path$Mnfile;
    public static final ModuleMethod path$Mnfragment;
    public static final ModuleMethod path$Mnhost;
    public static final ModuleMethod path$Mnlast;
    public static final ModuleMethod path$Mnparent;
    public static final ModuleMethod path$Mnport;
    public static final ModuleMethod path$Mnquery;
    public static final ModuleMethod path$Mnscheme;
    public static final ModuleMethod path$Mnuser$Mninfo;
    public static final ModuleMethod path$Qu;
    public static final ModuleMethod rename$Mnfile;
    public static final ModuleMethod resolve$Mnuri;
    public static final ModuleMethod system$Mntmpdir;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("make-temporary-file").readResolve();
        Lit29 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("resolve-uri").readResolve();
        Lit28 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("system-tmpdir").readResolve();
        Lit27 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("%file-separator").readResolve();
        Lit26 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("->pathname").readResolve();
        Lit25 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("directory-files").readResolve();
        Lit24 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("create-directory").readResolve();
        Lit23 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("copy-file").readResolve();
        Lit22 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("rename-file").readResolve();
        Lit21 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("delete-file").readResolve();
        Lit20 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("file-writable?").readResolve();
        Lit19 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("file-readable?").readResolve();
        Lit18 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("file-directory?").readResolve();
        Lit17 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("file-exists?").readResolve();
        Lit16 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("path-fragment").readResolve();
        Lit15 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("path-query").readResolve();
        Lit14 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("path-port").readResolve();
        Lit13 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("path-extension").readResolve();
        Lit12 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("path-last").readResolve();
        Lit11 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("path-parent").readResolve();
        Lit10 = simpleSymbol20;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("path-directory").readResolve();
        Lit9 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("path-file").readResolve();
        Lit8 = simpleSymbol22;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("path-host").readResolve();
        Lit7 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol("path-user-info").readResolve();
        Lit6 = simpleSymbol24;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("path-authority").readResolve();
        Lit5 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = (SimpleSymbol) new SimpleSymbol("path-scheme").readResolve();
        Lit4 = simpleSymbol26;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("absolute-path?").readResolve();
        Lit3 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = (SimpleSymbol) new SimpleSymbol("URI?").readResolve();
        Lit2 = simpleSymbol28;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("filepath?").readResolve();
        Lit1 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = (SimpleSymbol) new SimpleSymbol("path?").readResolve();
        Lit0 = simpleSymbol30;
        files filesVar = new files();
        $instance = filesVar;
        path$Qu = new ModuleMethod(filesVar, 1, simpleSymbol30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        filepath$Qu = new ModuleMethod(filesVar, 2, simpleSymbol29, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        URI$Qu = new ModuleMethod(filesVar, 3, simpleSymbol28, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        absolute$Mnpath$Qu = new ModuleMethod(filesVar, 4, simpleSymbol27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnscheme = new ModuleMethod(filesVar, 5, simpleSymbol26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnauthority = new ModuleMethod(filesVar, 6, simpleSymbol25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnuser$Mninfo = new ModuleMethod(filesVar, 7, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnhost = new ModuleMethod(filesVar, 8, simpleSymbol23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnfile = new ModuleMethod(filesVar, 9, simpleSymbol22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mndirectory = new ModuleMethod(filesVar, 10, simpleSymbol21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnparent = new ModuleMethod(filesVar, 11, simpleSymbol20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnlast = new ModuleMethod(filesVar, 12, simpleSymbol19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnextension = new ModuleMethod(filesVar, 13, simpleSymbol18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnport = new ModuleMethod(filesVar, 14, simpleSymbol17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnquery = new ModuleMethod(filesVar, 15, simpleSymbol16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnfragment = new ModuleMethod(filesVar, 16, simpleSymbol15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnexists$Qu = new ModuleMethod(filesVar, 17, simpleSymbol14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mndirectory$Qu = new ModuleMethod(filesVar, 18, simpleSymbol13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnreadable$Qu = new ModuleMethod(filesVar, 19, simpleSymbol12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnwritable$Qu = new ModuleMethod(filesVar, 20, simpleSymbol11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        delete$Mnfile = new ModuleMethod(filesVar, 21, simpleSymbol10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rename$Mnfile = new ModuleMethod(filesVar, 22, simpleSymbol9, 8194);
        copy$Mnfile = new ModuleMethod(filesVar, 23, simpleSymbol8, 8194);
        create$Mndirectory = new ModuleMethod(filesVar, 24, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        directory$Mnfiles = new ModuleMethod(filesVar, 25, simpleSymbol6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Mn$Grpathname = new ModuleMethod(filesVar, 26, simpleSymbol5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Pcfile$Mnseparator = new ModuleMethod(filesVar, 27, simpleSymbol4, 0);
        system$Mntmpdir = new ModuleMethod(filesVar, 28, simpleSymbol3, 0);
        resolve$Mnuri = new ModuleMethod(filesVar, 29, simpleSymbol2, 8194);
        make$Mntemporary$Mnfile = new ModuleMethod(filesVar, 30, simpleSymbol, 4096);
        filesVar.run();
    }

    public files() {
        ModuleInfo.register(this);
    }

    public static FilePath makeTemporaryFile() {
        return makeTemporaryFile("kawa~d.tmp");
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    public static boolean isPath(Object path) {
        return path instanceof Path;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 5:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 12:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 13:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 14:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 15:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 16:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 17:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 18:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 19:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 20:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 21:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 22:
            case 23:
            case 27:
            case 28:
            case 29:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 24:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 25:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
    }

    public static boolean isFilepath(Object path) {
        return path instanceof FilePath;
    }

    public static boolean URI$Qu(Object path) {
        return path instanceof URIPath;
    }

    public static boolean isAbsolutePath(Path path) {
        return path.isAbsolute();
    }

    public static Object pathScheme(Path p) {
        String s = p.getScheme();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathAuthority(Path p) {
        String s = p.getAuthority();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathUserInfo(Path p) {
        String s = p.getUserInfo();
        return s == null ? Boolean.FALSE : s;
    }

    public static String pathHost(Path p) {
        return p.getHost();
    }

    public static Object pathFile(Path p) {
        String s = p.getPath();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathDirectory(Path p) {
        Path s = p.getDirectory();
        return s == null ? Boolean.FALSE : s.toString();
    }

    public static Object pathParent(Path p) {
        Path s = p.getParent();
        return s == null ? Boolean.FALSE : s.toString();
    }

    public static Object pathLast(Path p) {
        String s = p.getLast();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathExtension(Path p) {
        String s = p.getExtension();
        return s == null ? Boolean.FALSE : s;
    }

    public static int pathPort(Path p) {
        return p.getPort();
    }

    public static Object pathQuery(Path p) {
        String s = p.getQuery();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathFragment(Path p) {
        String s = p.getFragment();
        return s == null ? Boolean.FALSE : s;
    }

    public static boolean isFileExists(Path file) {
        return file.exists();
    }

    public static boolean isFileDirectory(Path file) {
        return file.isDirectory();
    }

    public static boolean isFileReadable(FilePath file) {
        return file.toFile().canRead();
    }

    public static boolean isFileWritable(FilePath file) {
        return file.toFile().canWrite();
    }

    public static void deleteFile(FilePath file) {
        if (!file.delete()) {
            throw new IOException(Format.formatToString(0, "cannot delete ~a", file).toString());
        }
    }

    public static boolean renameFile(FilePath oldname, FilePath newname) {
        return oldname.toFile().renameTo(newname.toFile());
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 22:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (FilePath.coerceToFilePathOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 23:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (Path.coerceToPathOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 29:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (Path.coerceToPathOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static void copyFile(Path from, Path to) {
        InPort in = ports.openInputFile(from);
        OutPort out = ports.openOutputFile(to);
        for (Object ch = readchar.readChar.apply1(in); !ports.isEofObject(ch); ch = readchar.readChar.apply1(in)) {
            ports.writeChar(ch, out);
        }
        ports.closeOutputPort(out);
        ports.closeInputPort(in);
    }

    public static boolean createDirectory(FilePath dirname) {
        return dirname.toFile().mkdir();
    }

    public static Object directoryFiles(FilePath dir) {
        File file = dir.toFile();
        String[] files = new File(file == null ? null : file.toString()).list();
        return files == null ? Boolean.FALSE : LList.makeList(files, 0);
    }

    public static String $PcFileSeparator() {
        return System.getProperty("file.separator");
    }

    @Override // gnu.expr.ModuleBody
    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 27:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 28:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 29:
            default:
                return super.match0(moduleMethod, callContext);
            case 30:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
        }
    }

    public static String systemTmpdir() {
        String name = System.getProperty("java.io.tmpdir");
        if (name != null) {
            return name;
        }
        String sep = $PcFileSeparator();
        return IsEqual.apply(sep, "\\") ? "C:\\temp" : "/tmp";
    }

    public static Path resolveUri(Path uri, Path base) {
        return base.resolve(uri);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 22:
                try {
                    try {
                        return renameFile(FilePath.makeFilePath(obj), FilePath.makeFilePath(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "rename-file", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "rename-file", 1, obj);
                }
            case 23:
                try {
                    try {
                        copyFile(Path.valueOf(obj), Path.valueOf(obj2));
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "copy-file", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "copy-file", 1, obj);
                }
            case 29:
                try {
                    try {
                        return resolveUri(Path.valueOf(obj), Path.valueOf(obj2));
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "resolve-uri", 2, obj2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "resolve-uri", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static FilePath makeTemporaryFile(CharSequence fmt) {
        return FilePath.makeFilePath(FileUtils.createTempFile(fmt.toString()));
    }

    @Override // gnu.expr.ModuleBody
    public Object apply0(ModuleMethod moduleMethod) {
        switch (moduleMethod.selector) {
            case 27:
                return $PcFileSeparator();
            case 28:
                return systemTmpdir();
            case 29:
            default:
                return super.apply0(moduleMethod);
            case 30:
                return makeTemporaryFile();
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isPath(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                return isFilepath(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return URI$Qu(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                try {
                    return isAbsolutePath(Path.valueOf(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "absolute-path?", 1, obj);
                }
            case 5:
                try {
                    return pathScheme(Path.valueOf(obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "path-scheme", 1, obj);
                }
            case 6:
                try {
                    return pathAuthority(Path.valueOf(obj));
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "path-authority", 1, obj);
                }
            case 7:
                try {
                    return pathUserInfo(Path.valueOf(obj));
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "path-user-info", 1, obj);
                }
            case 8:
                try {
                    return pathHost(Path.valueOf(obj));
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "path-host", 1, obj);
                }
            case 9:
                try {
                    return pathFile(Path.valueOf(obj));
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "path-file", 1, obj);
                }
            case 10:
                try {
                    return pathDirectory(Path.valueOf(obj));
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "path-directory", 1, obj);
                }
            case 11:
                try {
                    return pathParent(Path.valueOf(obj));
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "path-parent", 1, obj);
                }
            case 12:
                try {
                    return pathLast(Path.valueOf(obj));
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "path-last", 1, obj);
                }
            case 13:
                try {
                    return pathExtension(Path.valueOf(obj));
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "path-extension", 1, obj);
                }
            case 14:
                try {
                    return Integer.valueOf(pathPort(Path.valueOf(obj)));
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "path-port", 1, obj);
                }
            case 15:
                try {
                    return pathQuery(Path.valueOf(obj));
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "path-query", 1, obj);
                }
            case 16:
                try {
                    return pathFragment(Path.valueOf(obj));
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "path-fragment", 1, obj);
                }
            case 17:
                try {
                    return isFileExists(Path.valueOf(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "file-exists?", 1, obj);
                }
            case 18:
                try {
                    return isFileDirectory(Path.valueOf(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "file-directory?", 1, obj);
                }
            case 19:
                try {
                    return isFileReadable(FilePath.makeFilePath(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "file-readable?", 1, obj);
                }
            case 20:
                try {
                    return isFileWritable(FilePath.makeFilePath(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "file-writable?", 1, obj);
                }
            case 21:
                try {
                    deleteFile(FilePath.makeFilePath(obj));
                    return Values.empty;
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "delete-file", 1, obj);
                }
            case 22:
            case 23:
            case 27:
            case 28:
            case 29:
            default:
                return super.apply1(moduleMethod, obj);
            case 24:
                try {
                    return createDirectory(FilePath.makeFilePath(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "create-directory", 1, obj);
                }
            case 25:
                try {
                    return directoryFiles(FilePath.makeFilePath(obj));
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "directory-files", 1, obj);
                }
            case 26:
                return Path.valueOf(obj);
            case 30:
                try {
                    return makeTemporaryFile((CharSequence) obj);
                } catch (ClassCastException e21) {
                    throw new WrongType(e21, "make-temporary-file", 1, obj);
                }
        }
    }
}
