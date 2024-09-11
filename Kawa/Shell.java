package kawa;

import gnu.bytecode.ZipLoader;
import gnu.expr.Compilation;
import gnu.expr.CompiledModule;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;

/* loaded from: classes.dex */
public class Shell {
    private static Class[] boolClasses;
    public static Object[] defaultFormatInfo;
    public static Method defaultFormatMethod;
    public static String defaultFormatName;
    static Object[][] formats;
    private static Class[] httpPrinterClasses;
    private static Object portArg;
    private static Class[] xmlPrinterClasses;
    public static ThreadLocal currentLoadPath = new ThreadLocal();
    private static Class[] noClasses = new Class[0];

    static {
        Class[] clsArr = {Boolean.TYPE};
        boolClasses = clsArr;
        xmlPrinterClasses = new Class[]{OutPort.class, Object.class};
        httpPrinterClasses = new Class[]{OutPort.class};
        portArg = "(port)";
        Object[] objArr = {"scheme", "gnu.kawa.functions.DisplayFormat", "getSchemeFormat", clsArr, Boolean.FALSE};
        Object[] objArr2 = {"readable-scheme", "gnu.kawa.functions.DisplayFormat", "getSchemeFormat", boolClasses, Boolean.TRUE};
        Object[] objArr3 = {"elisp", "gnu.kawa.functions.DisplayFormat", "getEmacsLispFormat", boolClasses, Boolean.FALSE};
        Object[] objArr4 = {"readable-elisp", "gnu.kawa.functions.DisplayFormat", "getEmacsLispFormat", boolClasses, Boolean.TRUE};
        Object[] objArr5 = {"clisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.FALSE};
        Object[] objArr6 = {"readable-clisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.TRUE};
        Object[] objArr7 = {"commonlisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.FALSE};
        Object[] objArr8 = {"readable-commonlisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.TRUE};
        Class[] clsArr2 = xmlPrinterClasses;
        Object obj = portArg;
        formats = new Object[][]{objArr, objArr2, objArr3, objArr4, objArr5, objArr6, objArr7, objArr8, new Object[]{"xml", "gnu.xml.XMLPrinter", "make", clsArr2, obj, null}, new Object[]{"html", "gnu.xml.XMLPrinter", "make", clsArr2, obj, "html"}, new Object[]{"xhtml", "gnu.xml.XMLPrinter", "make", clsArr2, obj, "xhtml"}, new Object[]{"cgi", "gnu.kawa.xml.HttpPrinter", "make", httpPrinterClasses, obj}, new Object[]{"ignore", "gnu.lists.VoidConsumer", "getInstance", noClasses}, new Object[]{null}};
    }

    public static void setDefaultFormat(String name) {
        Object[] info;
        String name2 = name.intern();
        defaultFormatName = name2;
        int i = 0;
        while (true) {
            info = formats[i];
            Object iname = info[0];
            if (iname == null) {
                System.err.println("kawa: unknown output format '" + name2 + "'");
                System.exit(-1);
            } else if (iname == name2) {
                break;
            }
            i++;
        }
        defaultFormatInfo = info;
        try {
            Class formatClass = Class.forName((String) info[1]);
            defaultFormatMethod = formatClass.getMethod((String) info[2], (Class[]) info[3]);
        } catch (Throwable ex) {
            System.err.println("kawa:  caught " + ex + " while looking for format '" + name2 + "'");
            System.exit(-1);
        }
        if (!defaultFormatInfo[1].equals("gnu.lists.VoidConsumer")) {
            ModuleBody.setMainPrintValues(true);
        }
    }

    public static Consumer getOutputConsumer(OutPort out) {
        Object[] info = defaultFormatInfo;
        if (out == null) {
            return VoidConsumer.getInstance();
        }
        if (info == null) {
            return Language.getDefaultLanguage().getOutputConsumer(out);
        }
        try {
            Object[] args = new Object[info.length - 4];
            System.arraycopy(info, 4, args, 0, args.length);
            int i = args.length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                if (args[i] == portArg) {
                    args[i] = out;
                }
            }
            Object format = defaultFormatMethod.invoke(null, args);
            if (format instanceof AbstractFormat) {
                out.objectFormat = (AbstractFormat) format;
                return out;
            }
            return (Consumer) format;
        } catch (Throwable ex) {
            throw new RuntimeException("cannot get output-format '" + defaultFormatName + "' - caught " + ex);
        }
    }

    public static boolean run(Language language, Environment env) {
        OutPort perr;
        InPort inp = InPort.inDefault();
        SourceMessages messages = new SourceMessages();
        if (inp instanceof TtyInPort) {
            Procedure prompter = language.getPrompter();
            if (prompter != null) {
                ((TtyInPort) inp).setPrompter(prompter);
            }
            OutPort perr2 = OutPort.errDefault();
            perr = perr2;
        } else {
            perr = null;
        }
        Throwable ex = run(language, env, inp, OutPort.outDefault(), perr, messages);
        if (ex == null) {
            return true;
        }
        printError(ex, messages, OutPort.errDefault());
        return false;
    }

    public static Throwable run(Language language, Environment env, InPort inp, OutPort pout, OutPort perr, SourceMessages messages) {
        AbstractFormat saveFormat = null;
        if (pout != null) {
            saveFormat = pout.objectFormat;
        }
        Consumer out = getOutputConsumer(pout);
        try {
            return run(language, env, inp, out, perr, null, messages);
        } finally {
            if (pout != null) {
                pout.objectFormat = saveFormat;
            }
        }
    }

    public static boolean run(Language language, Environment env, InPort inp, Consumer out, OutPort perr, URL url) {
        SourceMessages messages = new SourceMessages();
        Throwable ex = run(language, env, inp, out, perr, url, messages);
        if (ex != null) {
            printError(ex, messages, perr);
        }
        return ex == null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b4, code lost:
    
        if (r21 == 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b6, code lost:
    
        r10.consumer = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b8, code lost:
    
        gnu.expr.Language.restoreCurrent(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00bd, code lost:
    
        return null;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00dd A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Throwable run(gnu.expr.Language r18, gnu.mapping.Environment r19, gnu.mapping.InPort r20, gnu.lists.Consumer r21, gnu.mapping.OutPort r22, java.net.URL r23, gnu.text.SourceMessages r24) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.Shell.run(gnu.expr.Language, gnu.mapping.Environment, gnu.mapping.InPort, gnu.lists.Consumer, gnu.mapping.OutPort, java.net.URL, gnu.text.SourceMessages):java.lang.Throwable");
    }

    public static void printError(Throwable ex, SourceMessages messages, OutPort perr) {
        if (ex instanceof WrongArguments) {
            WrongArguments e = (WrongArguments) ex;
            messages.printAll(perr, 20);
            if (e.usage != null) {
                perr.println("usage: " + e.usage);
            }
            e.printStackTrace(perr);
            return;
        }
        if (ex instanceof ClassCastException) {
            messages.printAll(perr, 20);
            perr.println("Invalid parameter, was: " + ex.getMessage());
            ex.printStackTrace(perr);
            return;
        }
        if (ex instanceof SyntaxException) {
            SyntaxException se = (SyntaxException) ex;
            if (se.getMessages() == messages) {
                se.printAll(perr, 20);
                se.clear();
                return;
            }
        }
        messages.printAll(perr, 20);
        ex.printStackTrace(perr);
    }

    public static final CompiledModule checkCompiledZip(InputStream fs, Path path, Environment env, Language language) throws IOException {
        try {
            fs.mark(5);
            boolean isZip = fs.read() == 80 && fs.read() == 75 && fs.read() == 3 && fs.read() == 4;
            fs.reset();
            if (!isZip) {
                return null;
            }
            fs.close();
            Environment orig_env = Environment.getCurrent();
            String name = path.toString();
            try {
                if (env != orig_env) {
                    try {
                        Environment.setCurrent(env);
                    } catch (IOException ex) {
                        throw new WrappedException("load: " + name + " - " + ex.toString(), ex);
                    }
                }
                if (!(path instanceof FilePath)) {
                    throw new RuntimeException("load: " + name + " - not a file path");
                }
                File zfile = ((FilePath) path).toFile();
                if (!zfile.exists()) {
                    throw new RuntimeException("load: " + name + " - not found");
                }
                if (!zfile.canRead()) {
                    throw new RuntimeException("load: " + name + " - not readable");
                }
                ZipLoader loader = new ZipLoader(name);
                Class clas = loader.loadAllClasses();
                return CompiledModule.make(clas, language);
            } finally {
                if (env != orig_env) {
                    Environment.setCurrent(orig_env);
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v9, types: [boolean] */
    public static boolean runFileOrClass(String str, boolean z, int i) {
        Path valueOf;
        InputStream openInputStream;
        Language defaultLanguage = Language.getDefaultLanguage();
        try {
            if (str.equals("-")) {
                valueOf = Path.valueOf("/dev/stdin");
                openInputStream = System.in;
            } else {
                valueOf = Path.valueOf(str);
                openInputStream = valueOf.openInputStream();
            }
            try {
                str = runFile(openInputStream, valueOf, Environment.getCurrent(), z, i);
                return str;
            } catch (Throwable th) {
                th.printStackTrace(System.err);
                return false;
            }
        } catch (Throwable th2) {
            try {
                try {
                    CompiledModule.make(Class.forName(str), defaultLanguage).evalModule(Environment.getCurrent(), OutPort.outDefault());
                    return true;
                } catch (Throwable th3) {
                    th3.printStackTrace();
                    return false;
                }
            } catch (Throwable th4) {
                System.err.println("Cannot read file " + th2.getMessage());
                return false;
            }
        }
    }

    public static final boolean runFile(InputStream fs, Path path, Environment env, boolean lineByLine, int skipLines) throws Throwable {
        CompiledModule cmodule;
        InputStream fs2 = !(fs instanceof BufferedInputStream) ? new BufferedInputStream(fs) : fs;
        Language language = Language.getDefaultLanguage();
        Path savePath = (Path) currentLoadPath.get();
        try {
            currentLoadPath.set(path);
            cmodule = checkCompiledZip(fs2, path, env, language);
        } catch (Throwable th) {
            th = th;
        }
        if (cmodule == null) {
            InPort src = InPort.openFile(fs2, path);
            int skipLines2 = skipLines;
            while (true) {
                int skipLines3 = skipLines2 - 1;
                if (skipLines3 >= 0) {
                    try {
                        src.skipRestOfLine();
                        skipLines2 = skipLines3;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    try {
                        break;
                    } finally {
                        src.close();
                    }
                }
                th = th2;
                currentLoadPath.set(savePath);
                throw th;
            }
            SourceMessages messages = new SourceMessages();
            URL url = path.toURL();
            if (lineByLine) {
                boolean print = ModuleBody.getMainPrintValues();
                Consumer out = print ? getOutputConsumer(OutPort.outDefault()) : new VoidConsumer();
                Throwable ex = run(language, env, src, out, null, url, messages);
                if (ex != null) {
                    throw ex;
                }
            } else {
                cmodule = compileSource(src, env, url, language, messages);
                messages.printAll(OutPort.errDefault(), 20);
                if (cmodule == null) {
                    currentLoadPath.set(savePath);
                    return false;
                }
            }
            src.close();
        }
        if (cmodule != null) {
            cmodule.evalModule(env, OutPort.outDefault());
        }
        currentLoadPath.set(savePath);
        return true;
    }

    static CompiledModule compileSource(InPort port, Environment env, URL url, Language language, SourceMessages messages) throws SyntaxException, IOException {
        ModuleManager manager = ModuleManager.getInstance();
        ModuleInfo minfo = manager.findWithSourcePath(port.getName());
        Compilation comp = language.parse(port, messages, 1, minfo);
        CallContext ctx = CallContext.getInstance();
        ctx.values = Values.noArgs;
        Object inst = ModuleExp.evalModule1(env, comp, url, null);
        if (inst == null || messages.seenErrors()) {
            return null;
        }
        return new CompiledModule(comp.getModule(), inst, language);
    }
}
