package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.URLPath;
import java.io.File;
import java.net.URL;

/* loaded from: classes.dex */
public class ModuleManager {
    public static final long LAST_MODIFIED_CACHE_TIME = 1000;
    static ModuleManager instance = new ModuleManager();
    private String compilationDirectory = "";
    public long lastModifiedCacheTime = 1000;
    ModuleInfo[] modules;
    int numModules;
    ModuleSet packageInfoChain;

    public void setCompilationDirectory(String path) {
        char sep;
        if (path == null) {
            path = "";
        }
        int plen = path.length();
        if (plen > 0 && path.charAt(plen - 1) != (sep = File.separatorChar)) {
            path = path + sep;
        }
        this.compilationDirectory = path;
    }

    public String getCompilationDirectory() {
        return this.compilationDirectory;
    }

    public static ModuleManager getInstance() {
        return instance;
    }

    public synchronized ModuleInfo getModule(int index) {
        return index >= this.numModules ? null : this.modules[index];
    }

    public synchronized ModuleInfo find(Compilation comp) {
        ModuleInfo info;
        ModuleExp mexp = comp.getModule();
        ClassType ctype = mexp.classFor(comp);
        String fileName = mexp.getFileName();
        Path sourceAbsPath = ModuleInfo.absPath(fileName);
        info = findWithSourcePath(sourceAbsPath, fileName);
        info.setClassName(ctype.getName());
        info.exp = mexp;
        comp.minfo = info;
        info.comp = comp;
        return info;
    }

    private synchronized void add(ModuleInfo info) {
        ModuleInfo[] moduleInfoArr = this.modules;
        if (moduleInfoArr == null) {
            this.modules = new ModuleInfo[10];
        } else {
            int i = this.numModules;
            if (i == moduleInfoArr.length) {
                ModuleInfo[] tmp = new ModuleInfo[i * 2];
                System.arraycopy(moduleInfoArr, 0, tmp, 0, i);
                this.modules = tmp;
            }
        }
        ModuleInfo[] moduleInfoArr2 = this.modules;
        int i2 = this.numModules;
        this.numModules = i2 + 1;
        moduleInfoArr2[i2] = info;
    }

    public synchronized ModuleInfo searchWithClassName(String className) {
        ModuleInfo info;
        int i = this.numModules;
        do {
            i--;
            if (i < 0) {
                return null;
            }
            info = this.modules[i];
        } while (!className.equals(info.getClassName()));
        return info;
    }

    public static synchronized ModuleInfo findWithClass(Class clas) {
        ModuleInfo info;
        synchronized (ModuleManager.class) {
            info = ModuleInfo.mapClassToInfo.get(clas);
            if (info == null) {
                info = new ModuleInfo();
                info.setModuleClass(clas);
            }
        }
        return info;
    }

    public ModuleInfo findWithClassName(String className) {
        ModuleInfo info = searchWithClassName(className);
        if (info != null) {
            return info;
        }
        try {
            return findWithClass(ClassType.getContextClass(className));
        } catch (Throwable ex) {
            throw WrappedException.wrapIfNeeded(ex);
        }
    }

    private synchronized ModuleInfo searchWithAbsSourcePath(String sourcePath) {
        ModuleInfo info;
        int i = this.numModules;
        do {
            i--;
            if (i < 0) {
                return null;
            }
            info = this.modules[i];
        } while (!sourcePath.equals(info.getSourceAbsPathname()));
        return info;
    }

    public synchronized ModuleInfo findWithSourcePath(Path sourceAbsPath, String sourcePath) {
        ModuleInfo info;
        String sourceAbsPathname = sourceAbsPath.toString();
        info = searchWithAbsSourcePath(sourceAbsPathname);
        if (info == null) {
            info = new ModuleInfo();
            info.sourcePath = sourcePath;
            info.sourceAbsPath = sourceAbsPath;
            info.sourceAbsPathname = sourceAbsPathname;
            add(info);
        }
        return info;
    }

    public synchronized ModuleInfo findWithSourcePath(String sourcePath) {
        return findWithSourcePath(ModuleInfo.absPath(sourcePath), sourcePath);
    }

    public synchronized ModuleInfo findWithURL(URL url) {
        Path sourceAbsPath;
        String sourcePath;
        sourceAbsPath = URLPath.valueOf(url);
        sourcePath = url.toExternalForm();
        return findWithSourcePath(sourceAbsPath, sourcePath);
    }

    public synchronized void register(String moduleClass, String moduleSource, String moduleUri) {
        if (searchWithClassName(moduleClass) != null) {
            return;
        }
        Path sourcePath = Path.valueOf(moduleSource);
        String sourceAbsPathname = sourcePath.getCanonical().toString();
        if (searchWithAbsSourcePath(sourceAbsPathname) != null) {
            return;
        }
        ModuleInfo info = new ModuleInfo();
        if (sourcePath.isAbsolute()) {
            info.sourceAbsPath = sourcePath;
            info.sourceAbsPathname = sourceAbsPathname;
        } else {
            try {
                Class setClass = this.packageInfoChain.getClass();
                String setClassName = setClass.getName().replace('.', '/') + ".class";
                URL setClassURL = setClass.getClassLoader().getResource(setClassName);
                Path sourceAbsPath = URLPath.valueOf(setClassURL).resolve(moduleSource);
                info.sourceAbsPath = sourceAbsPath;
                info.sourceAbsPathname = sourceAbsPath.toString();
            } catch (Throwable th) {
                return;
            }
        }
        info.setClassName(moduleClass);
        info.sourcePath = moduleSource;
        info.uri = moduleUri;
        add(info);
    }

    public synchronized void loadPackageInfo(String packageName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String moduleSetClassName = packageName + "." + ModuleSet.MODULES_MAP;
        for (ModuleSet set = this.packageInfoChain; set != null; set = set.next) {
            String setName = set.getClass().getName();
            setName.equals(moduleSetClassName);
        }
        Class setClass = Class.forName(moduleSetClassName);
        ModuleSet instance2 = (ModuleSet) setClass.newInstance();
        instance2.next = this.packageInfoChain;
        this.packageInfoChain = instance2;
        instance2.register(this);
    }

    public synchronized void clear() {
        ModuleSet set = this.packageInfoChain;
        while (set != null) {
            ModuleSet next = set.next;
            set.next = null;
            set = next;
        }
        this.packageInfoChain = null;
        this.modules = null;
        this.numModules = 0;
    }
}
