package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.SwitchState;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.Convert;
import gnu.kawa.lispexpr.LispReader;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.Options;
import gnu.text.Path;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kawa.Shell;

/* loaded from: classes.dex */
public class Compilation implements SourceLocator {
    public static final int BODY_PARSED = 4;
    public static final int CALL_WITH_CONSUMER = 2;
    public static final int CALL_WITH_CONTINUATIONS = 4;
    public static final int CALL_WITH_RETURN = 1;
    public static final int CALL_WITH_TAILCALLS = 3;
    public static final int CALL_WITH_UNSPECIFIED = 0;
    public static final int CLASS_WRITTEN = 14;
    public static final int COMPILED = 12;
    public static final int COMPILE_SETUP = 10;
    public static final int ERROR_SEEN = 100;
    public static final int MODULE_NONSTATIC = -1;
    public static final int MODULE_STATIC = 1;
    public static final int MODULE_STATIC_DEFAULT = 0;
    public static final int MODULE_STATIC_RUN = 2;
    public static final int PROLOG_PARSED = 2;
    public static final int PROLOG_PARSING = 1;
    public static final int RESOLVED = 6;
    public static final int WALKED = 8;
    public static Type[] apply0args;
    public static Method apply0method;
    public static Type[] apply1args;
    public static Method apply1method;
    public static Type[] apply2args;
    public static Method apply2method;
    public static Method apply3method;
    public static Method apply4method;
    private static Type[] applyCpsArgs;
    public static Method applyCpsMethod;
    public static Type[] applyNargs;
    public static Method applyNmethod;
    public static Method[] applymethods;
    public static Field argsCallContextField;
    private static Compilation chainUninitialized;
    static Method checkArgCountMethod;
    public static String classPrefixDefault;
    private static final ThreadLocal<Compilation> current;
    public static boolean debugPrintExpr = false;
    public static boolean debugPrintFinalExpr;
    public static int defaultCallConvention;
    public static int defaultClassFileVersion;
    public static boolean emitSourceDebugExtAttr;
    public static final Field falseConstant;
    public static boolean generateMainDefault;
    public static Method getCallContextInstanceMethod;
    public static Method getCurrentEnvironmentMethod;
    public static final Method getLocation1EnvironmentMethod;
    public static final Method getLocation2EnvironmentMethod;
    public static final Method getLocationMethod;
    public static final Method getProcedureBindingMethod;
    public static final Method getSymbolProcedureMethod;
    public static final Method getSymbolValueMethod;
    public static boolean inlineOk;
    public static final Type[] int1Args;
    public static ClassType javaStringType;
    static Method makeListMethod;
    public static int moduleStatic;
    public static Field noArgsField;
    public static final ArrayType objArrayType;
    public static Options options;
    public static Field pcCallContextField;
    public static Field procCallContextField;
    public static ClassType scmBooleanType;
    public static ClassType scmKeywordType;
    public static ClassType scmListType;
    public static ClassType scmSequenceType;
    static final Method setNameMethod;
    public static final Type[] string1Arg;
    public static final Type[] sym1Arg;
    public static final Field trueConstant;
    public static ClassType typeApplet;
    public static ClassType typeCallContext;
    public static ClassType typeClass;
    public static ClassType typeClassType;
    public static final ClassType typeConsumer;
    public static ClassType typeEnvironment;
    public static ClassType typeLanguage;
    public static ClassType typeLocation;
    public static ClassType typeMethodProc;
    public static ClassType typeModuleBody;
    public static ClassType typeModuleMethod;
    public static ClassType typeModuleWithContext;
    public static ClassType typeObject;
    public static ClassType typeObjectType;
    public static ClassType typePair;
    public static ClassType typeProcedure;
    public static ClassType typeProcedure0;
    public static ClassType typeProcedure1;
    public static ClassType typeProcedure2;
    public static ClassType typeProcedure3;
    public static ClassType typeProcedure4;
    public static ClassType[] typeProcedureArray;
    public static ClassType typeProcedureN;
    public static ClassType typeRunnable;
    public static ClassType typeServlet;
    public static ClassType typeString;
    public static ClassType typeSymbol;
    public static ClassType typeType;
    public static ClassType typeValues;
    public static Options.OptionInfo warnAsError;
    public static Options.OptionInfo warnInvokeUnknownMethod;
    public static Options.OptionInfo warnUndefinedVariable;
    public static Options.OptionInfo warnUnknownMember;
    Variable callContextVar;
    Variable callContextVarForInit;
    ClassType[] classes;
    Initializer clinitChain;
    Method clinitMethod;
    public ClassType curClass;
    public LambdaExp curLambda;
    protected ScopeExp current_scope;
    public boolean explicit;
    public Stack<Expression> exprStack;
    Method forNameHelper;
    SwitchState fswitch;
    Field fswitchIndex;
    public boolean immediate;
    private int keyUninitialized;
    int langOptions;
    protected Language language;
    public Lexer lexer;
    public NameLookup lexical;
    LitTable litTable;
    ArrayClassLoader loader;
    int localFieldIndex;
    public ClassType mainClass;
    public ModuleExp mainLambda;
    int maxSelectorValue;
    protected SourceMessages messages;
    public Method method;
    int method_counter;
    public ModuleInfo minfo;
    public ClassType moduleClass;
    Field moduleInstanceMainField;
    Variable moduleInstanceVar;
    private Compilation nextUninitialized;
    int numClasses;
    boolean pedantic;
    public Stack<Object> pendingImports;
    private int state;
    public Variable thisDecl;
    public boolean mustCompile = ModuleExp.alwaysCompile;
    public Options currentOptions = new Options(options);
    public boolean generateMain = generateMainDefault;
    public String classPrefix = classPrefixDefault;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isPedantic() {
        return this.pedantic;
    }

    public void pushPendingImport(ModuleInfo info, ScopeExp defs, int formSize) {
        if (this.pendingImports == null) {
            this.pendingImports = new Stack<>();
        }
        this.pendingImports.push(info);
        this.pendingImports.push(defs);
        Expression posExp = new ReferenceExp((Object) null);
        posExp.setLine(this);
        this.pendingImports.push(posExp);
        this.pendingImports.push(Integer.valueOf(formSize));
    }

    static {
        Options options2 = new Options();
        options = options2;
        warnUndefinedVariable = options2.add("warn-undefined-variable", 1, Boolean.TRUE, "warn if no compiler-visible binding for a variable");
        Options.OptionInfo add = options.add("warn-unknown-member", 1, Boolean.TRUE, "warn if referencing an unknown method or field");
        warnUnknownMember = add;
        warnInvokeUnknownMethod = options.add("warn-invoke-unknown-method", 1, add, "warn if invoke calls an unknown method (subsumed by warn-unknown-member)");
        warnAsError = options.add("warn-as-error", 1, Boolean.FALSE, "Make all warnings into errors");
        defaultClassFileVersion = ClassType.JDK_1_5_VERSION;
        moduleStatic = 0;
        typeObject = Type.objectType;
        scmBooleanType = ClassType.make("java.lang.Boolean");
        ClassType make = ClassType.make("java.lang.String");
        typeString = make;
        javaStringType = make;
        scmKeywordType = ClassType.make("gnu.expr.Keyword");
        scmSequenceType = ClassType.make("gnu.lists.Sequence");
        scmListType = ClassType.make("gnu.lists.LList");
        typePair = ClassType.make("gnu.lists.Pair");
        ArrayType make2 = ArrayType.make(typeObject);
        objArrayType = make2;
        typeRunnable = ClassType.make("java.lang.Runnable");
        typeType = ClassType.make("gnu.bytecode.Type");
        typeObjectType = ClassType.make("gnu.bytecode.ObjectType");
        typeClass = Type.javalangClassType;
        typeClassType = ClassType.make("gnu.bytecode.ClassType");
        typeProcedure = ClassType.make("gnu.mapping.Procedure");
        typeLanguage = ClassType.make("gnu.expr.Language");
        typeEnvironment = ClassType.make("gnu.mapping.Environment");
        typeLocation = ClassType.make("gnu.mapping.Location");
        typeSymbol = ClassType.make("gnu.mapping.Symbol");
        getSymbolValueMethod = typeLanguage.getDeclaredMethod("getSymbolValue", 1);
        getSymbolProcedureMethod = typeLanguage.getDeclaredMethod("getSymbolProcedure", 1);
        getLocationMethod = typeLocation.addMethod("get", Type.typeArray0, Type.objectType, 1);
        getProcedureBindingMethod = typeSymbol.addMethod("getProcedure", Type.typeArray0, typeProcedure, 1);
        trueConstant = scmBooleanType.getDeclaredField("TRUE");
        falseConstant = scmBooleanType.getDeclaredField("FALSE");
        setNameMethod = typeProcedure.getDeclaredMethod("setName", 1);
        int1Args = new Type[]{Type.intType};
        Type[] typeArr = {javaStringType};
        string1Arg = typeArr;
        sym1Arg = typeArr;
        getLocation1EnvironmentMethod = typeEnvironment.getDeclaredMethod("getLocation", 1);
        Type[] args = {typeSymbol, Type.objectType};
        getLocation2EnvironmentMethod = typeEnvironment.addMethod("getLocation", args, typeLocation, 17);
        Type[] makeListArgs = {make2, Type.intType};
        ClassType classType = scmListType;
        makeListMethod = classType.addMethod("makeList", makeListArgs, classType, 9);
        getCurrentEnvironmentMethod = typeEnvironment.addMethod("getCurrent", Type.typeArray0, typeEnvironment, 9);
        Type[] typeArr2 = Type.typeArray0;
        apply0args = typeArr2;
        ClassType classType2 = typeObject;
        apply1args = new Type[]{classType2};
        apply2args = new Type[]{classType2, classType2};
        applyNargs = new Type[]{make2};
        apply0method = typeProcedure.addMethod("apply0", typeArr2, classType2, 17);
        apply1method = typeProcedure.addMethod("apply1", apply1args, typeObject, 1);
        apply2method = typeProcedure.addMethod("apply2", apply2args, typeObject, 1);
        ClassType classType3 = typeObject;
        Type[] apply3args = {classType3, classType3, classType3};
        apply3method = typeProcedure.addMethod("apply3", apply3args, classType3, 1);
        ClassType classType4 = typeObject;
        Type[] apply4args = {classType4, classType4, classType4, classType4};
        apply4method = typeProcedure.addMethod("apply4", apply4args, classType4, 1);
        applyNmethod = typeProcedure.addMethod("applyN", applyNargs, typeObject, 1);
        Type[] args2 = {typeProcedure, Type.intType};
        checkArgCountMethod = typeProcedure.addMethod("checkArgCount", args2, Type.voidType, 9);
        applymethods = new Method[]{apply0method, apply1method, apply2method, apply3method, apply4method, applyNmethod};
        typeProcedure0 = ClassType.make("gnu.mapping.Procedure0");
        typeProcedure1 = ClassType.make("gnu.mapping.Procedure1");
        typeProcedure2 = ClassType.make("gnu.mapping.Procedure2");
        typeProcedure3 = ClassType.make("gnu.mapping.Procedure3");
        typeProcedure4 = ClassType.make("gnu.mapping.Procedure4");
        typeProcedureN = ClassType.make("gnu.mapping.ProcedureN");
        typeModuleBody = ClassType.make("gnu.expr.ModuleBody");
        typeModuleWithContext = ClassType.make("gnu.expr.ModuleWithContext");
        typeApplet = ClassType.make("java.applet.Applet");
        typeServlet = ClassType.make("gnu.kawa.servlet.KawaServlet");
        typeCallContext = ClassType.make("gnu.mapping.CallContext");
        typeConsumer = ClassType.make("gnu.lists.Consumer");
        getCallContextInstanceMethod = typeCallContext.getDeclaredMethod("getInstance", 0);
        ClassType make3 = ClassType.make("gnu.mapping.Values");
        typeValues = make3;
        noArgsField = make3.getDeclaredField("noArgs");
        pcCallContextField = typeCallContext.getDeclaredField("pc");
        typeMethodProc = ClassType.make("gnu.mapping.MethodProc");
        typeModuleMethod = ClassType.make("gnu.expr.ModuleMethod");
        argsCallContextField = typeCallContext.getDeclaredField("values");
        procCallContextField = typeCallContext.getDeclaredField("proc");
        Type[] typeArr3 = {typeCallContext};
        applyCpsArgs = typeArr3;
        applyCpsMethod = typeProcedure.addMethod("apply", typeArr3, Type.voidType, 1);
        typeProcedureArray = new ClassType[]{typeProcedure0, typeProcedure1, typeProcedure2, typeProcedure3, typeProcedure4};
        generateMainDefault = false;
        inlineOk = true;
        classPrefixDefault = "";
        emitSourceDebugExtAttr = true;
        current = new InheritableThreadLocal();
    }

    public boolean warnUndefinedVariable() {
        return this.currentOptions.getBoolean(warnUndefinedVariable);
    }

    public boolean warnUnknownMember() {
        return this.currentOptions.getBoolean(warnUnknownMember);
    }

    public boolean warnInvokeUnknownMethod() {
        return this.currentOptions.getBoolean(warnInvokeUnknownMethod);
    }

    public boolean warnAsError() {
        return this.currentOptions.getBoolean(warnAsError);
    }

    public final boolean getBooleanOption(String key, boolean defaultValue) {
        return this.currentOptions.getBoolean(key, defaultValue);
    }

    public final boolean getBooleanOption(String key) {
        return this.currentOptions.getBoolean(key);
    }

    public boolean usingCPStyle() {
        return defaultCallConvention == 4;
    }

    public boolean usingTailCalls() {
        return defaultCallConvention >= 3;
    }

    public final CodeAttr getCode() {
        return this.method.getCode();
    }

    public boolean generatingApplet() {
        return (this.langOptions & 16) != 0;
    }

    public boolean generatingServlet() {
        return (this.langOptions & 32) != 0;
    }

    public boolean sharedModuleDefs() {
        return (this.langOptions & 2) != 0;
    }

    public void setSharedModuleDefs(boolean shared) {
        if (shared) {
            this.langOptions |= 2;
        } else {
            this.langOptions &= -3;
        }
    }

    public final ClassType getModuleType() {
        return defaultCallConvention >= 2 ? typeModuleWithContext : typeModuleBody;
    }

    public void compileConstant(Object value) {
        CodeAttr code = getCode();
        if (value == null) {
            code.emitPushNull();
        } else if ((value instanceof String) && !this.immediate) {
            code.emitPushString((String) value);
        } else {
            code.emitGetStatic(compileConstantToField(value));
        }
    }

    public Field compileConstantToField(Object value) {
        Literal literal = this.litTable.findLiteral(value);
        if (literal.field == null) {
            literal.assign(this.litTable);
        }
        return literal.field;
    }

    public boolean inlineOk(Expression proc) {
        if (proc instanceof LambdaExp) {
            LambdaExp lproc = (LambdaExp) proc;
            Declaration nameDecl = lproc.nameDecl;
            if (nameDecl == null || nameDecl.getSymbol() == null || !(nameDecl.context instanceof ModuleExp)) {
                return true;
            }
            if (this.immediate && nameDecl.isPublic() && !lproc.getFlag(2048) && (this.curLambda == null || lproc.topLevel() != this.curLambda.topLevel())) {
                return false;
            }
        }
        return inlineOk;
    }

    public boolean inlineOk(Procedure proc) {
        if (this.immediate && (proc instanceof ModuleMethod) && (((ModuleMethod) proc).module.getClass().getClassLoader() instanceof ArrayClassLoader)) {
            return false;
        }
        return inlineOk;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x006c A[Catch: ClassCastException -> 0x00c2, TryCatch #0 {ClassCastException -> 0x00c2, blocks: (B:47:0x004e, B:49:0x005a, B:52:0x0061, B:53:0x0068, B:55:0x006c, B:56:0x006f, B:58:0x0073, B:60:0x007b, B:62:0x0083, B:64:0x008b, B:66:0x0093, B:68:0x009b, B:72:0x00a7, B:76:0x00b6, B:79:0x00bd), top: B:46:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00a7 A[Catch: ClassCastException -> 0x00c2, TryCatch #0 {ClassCastException -> 0x00c2, blocks: (B:47:0x004e, B:49:0x005a, B:52:0x0061, B:53:0x0068, B:55:0x006c, B:56:0x006f, B:58:0x0073, B:60:0x007b, B:62:0x0083, B:64:0x008b, B:66:0x0093, B:68:0x009b, B:72:0x00a7, B:76:0x00b6, B:79:0x00bd), top: B:46:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void compileConstant(java.lang.Object r8, gnu.expr.Target r9) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.compileConstant(java.lang.Object, gnu.expr.Target):void");
    }

    private void dumpInitializers(Initializer inits) {
        for (Initializer init = Initializer.reverse(inits); init != null; init = init.next) {
            init.emit(this);
        }
    }

    public ClassType findNamedClass(String name) {
        for (int i = 0; i < this.numClasses; i++) {
            if (name.equals(this.classes[i].getName())) {
                return this.classes[i];
            }
        }
        return null;
    }

    private static void putURLWords(String name, StringBuffer sbuf) {
        int dot = name.indexOf(46);
        if (dot > 0) {
            putURLWords(name.substring(dot + 1), sbuf);
            sbuf.append('.');
            name = name.substring(0, dot);
        }
        sbuf.append(name);
    }

    public static String mangleURI(String name) {
        int dot;
        int extLen;
        boolean hasSlash = name.indexOf(47) >= 0;
        int len = name.length();
        if (len > 6 && name.startsWith("class:")) {
            return name.substring(6);
        }
        if (len > 5 && name.charAt(4) == ':' && name.substring(0, 4).equalsIgnoreCase("http")) {
            name = name.substring(5);
            len -= 5;
            hasSlash = true;
        } else if (len > 4 && name.charAt(3) == ':' && name.substring(0, 3).equalsIgnoreCase("uri")) {
            name = name.substring(4);
            len -= 4;
        }
        int start = 0;
        StringBuffer sbuf = new StringBuffer();
        while (true) {
            int slash = name.indexOf(47, start);
            int end = slash < 0 ? len : slash;
            boolean first = sbuf.length() == 0;
            if (first && hasSlash) {
                String host = name.substring(start, end);
                if (end - start > 4 && host.startsWith("www.")) {
                    host = host.substring(4);
                }
                putURLWords(host, sbuf);
            } else if (start != end) {
                if (!first) {
                    sbuf.append('.');
                }
                if (end == len && (dot = name.lastIndexOf(46, len)) > start + 1 && !first && ((extLen = len - dot) <= 4 || (extLen == 5 && name.endsWith("html")))) {
                    len -= extLen;
                    end = len;
                    name = name.substring(0, len);
                }
                sbuf.append(name.substring(start, end));
            }
            if (slash >= 0) {
                start = slash + 1;
            } else {
                return sbuf.toString();
            }
        }
    }

    public static String mangleName(String name) {
        return mangleName(name, -1);
    }

    public static String mangleNameIfNeeded(String name) {
        if (name == null || isValidJavaName(name)) {
            return name;
        }
        return mangleName(name, 0);
    }

    public static boolean isValidJavaName(String name) {
        int len = name.length();
        if (len == 0 || !Character.isJavaIdentifierStart(name.charAt(0))) {
            return false;
        }
        int i = len;
        do {
            i--;
            if (i <= 0) {
                return true;
            }
        } while (Character.isJavaIdentifierPart(name.charAt(i)));
        return false;
    }

    public static String mangleName(String name, boolean reversible) {
        return mangleName(name, reversible ? 1 : -1);
    }

    public static String mangleName(String str, int i) {
        boolean z = i >= 0;
        int length = str.length();
        if (length == 6 && str.equals("*init*")) {
            return "<init>";
        }
        StringBuffer stringBuffer = new StringBuffer(length);
        int i2 = 0;
        boolean z2 = false;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (z2) {
                charAt = Character.toTitleCase(charAt);
                z2 = false;
            }
            if (Character.isDigit(charAt)) {
                if (i2 == 0) {
                    stringBuffer.append("$N");
                }
                stringBuffer.append(charAt);
            } else if (Character.isLetter(charAt) || charAt == '_') {
                stringBuffer.append(charAt);
            } else if (charAt == '$') {
                stringBuffer.append(i > 1 ? "$$" : "$");
            } else {
                switch (charAt) {
                    case '!':
                        stringBuffer.append("$Ex");
                        break;
                    case '\"':
                        stringBuffer.append("$Dq");
                        break;
                    case '#':
                        stringBuffer.append("$Nm");
                        break;
                    case '%':
                        stringBuffer.append("$Pc");
                        break;
                    case '&':
                        stringBuffer.append("$Am");
                        break;
                    case '\'':
                        stringBuffer.append("$Sq");
                        break;
                    case '(':
                        stringBuffer.append("$LP");
                        break;
                    case ')':
                        stringBuffer.append("$RP");
                        break;
                    case '*':
                        stringBuffer.append("$St");
                        break;
                    case '+':
                        stringBuffer.append("$Pl");
                        break;
                    case ',':
                        stringBuffer.append("$Cm");
                        break;
                    case '-':
                        if (z) {
                            stringBuffer.append("$Mn");
                            break;
                        } else {
                            int i3 = i2 + 1;
                            char charAt2 = i3 < length ? str.charAt(i3) : (char) 0;
                            if (charAt2 == '>') {
                                stringBuffer.append("$To$");
                                i2 = i3;
                                break;
                            } else if (!Character.isLowerCase(charAt2)) {
                                stringBuffer.append("$Mn");
                                break;
                            }
                        }
                        break;
                    case '.':
                        stringBuffer.append("$Dt");
                        break;
                    case '/':
                        stringBuffer.append("$Sl");
                        break;
                    case ':':
                        stringBuffer.append("$Cl");
                        break;
                    case ';':
                        stringBuffer.append("$SC");
                        break;
                    case '<':
                        stringBuffer.append("$Ls");
                        break;
                    case '=':
                        stringBuffer.append("$Eq");
                        break;
                    case '>':
                        stringBuffer.append("$Gr");
                        break;
                    case '?':
                        char charAt3 = stringBuffer.length() > 0 ? stringBuffer.charAt(0) : (char) 0;
                        if (!z && i2 + 1 == length && Character.isLowerCase(charAt3)) {
                            stringBuffer.setCharAt(0, Character.toTitleCase(charAt3));
                            stringBuffer.insert(0, "is");
                            break;
                        } else {
                            stringBuffer.append("$Qu");
                            break;
                        }
                        break;
                    case '@':
                        stringBuffer.append("$At");
                        break;
                    case '[':
                        stringBuffer.append("$LB");
                        break;
                    case ']':
                        stringBuffer.append("$RB");
                        break;
                    case '^':
                        stringBuffer.append("$Up");
                        break;
                    case '{':
                        stringBuffer.append("$LC");
                        break;
                    case '|':
                        stringBuffer.append("$VB");
                        break;
                    case '}':
                        stringBuffer.append("$RC");
                        break;
                    case '~':
                        stringBuffer.append("$Tl");
                        break;
                    default:
                        stringBuffer.append('$');
                        stringBuffer.append(Character.forDigit((charAt >> '\f') & 15, 16));
                        stringBuffer.append(Character.forDigit((charAt >> '\b') & 15, 16));
                        stringBuffer.append(Character.forDigit((charAt >> 4) & 15, 16));
                        stringBuffer.append(Character.forDigit(charAt & 15, 16));
                        break;
                }
                if (!z) {
                    z2 = true;
                }
            }
            i2++;
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.equals(str) ? str : stringBuffer2;
    }

    public static char demangle2(char char1, char char2) {
        switch ((char1 << 16) | char2) {
            case 4259949:
                return '&';
            case 4259956:
                return '@';
            case 4391020:
                return ':';
            case 4391021:
                return ',';
            case 4456561:
                return '\"';
            case 4456564:
                return '.';
            case 4522097:
                return '=';
            case 4522104:
                return '!';
            case 4653170:
                return '>';
            case 4980802:
                return '[';
            case 4980803:
                return '{';
            case 4980816:
                return '(';
            case 4980851:
                return '<';
            case 5046371:
                return '%';
            case 5046382:
                return '-';
            case 5111917:
                return '#';
            case 5242979:
                return '%';
            case 5242988:
                return '+';
            case 5308533:
                return '?';
            case 5374018:
                return ']';
            case 5374019:
                return '}';
            case 5374032:
                return ')';
            case 5439555:
                return ';';
            case 5439596:
                return '/';
            case 5439601:
                return '\\';
            case 5439604:
                return '*';
            case 5505132:
                return '~';
            case 5570672:
                return '^';
            case 5636162:
                return '|';
            default:
                return LispReader.TOKEN_ESCAPE_CHAR;
        }
    }

    public static String demangleName(String name) {
        return demangleName(name, false);
    }

    public static String demangleName(String name, boolean reversible) {
        StringBuffer sbuf = new StringBuffer();
        int len = name.length();
        boolean mangled = false;
        boolean predicate = false;
        boolean downCaseNext = false;
        int i = 0;
        while (i < len) {
            char ch = name.charAt(i);
            if (downCaseNext && !reversible) {
                ch = Character.toLowerCase(ch);
                downCaseNext = false;
            }
            if (!reversible && ch == 'i' && i == 0 && len > 2 && name.charAt(i + 1) == 's') {
                char d = name.charAt(i + 2);
                if (!Character.isLowerCase(d)) {
                    mangled = true;
                    predicate = true;
                    i++;
                    if (Character.isUpperCase(d) || Character.isTitleCase(d)) {
                        sbuf.append(Character.toLowerCase(d));
                        i++;
                    }
                    i++;
                }
            }
            if (ch == '$' && i + 2 < len) {
                char c1 = name.charAt(i + 1);
                char c2 = name.charAt(i + 2);
                char d2 = demangle2(c1, c2);
                if (d2 != 65535) {
                    sbuf.append(d2);
                    i += 2;
                    mangled = true;
                    downCaseNext = true;
                } else if (c1 == 'T' && c2 == 'o' && i + 3 < len && name.charAt(i + 3) == '$') {
                    sbuf.append("->");
                    i += 3;
                    mangled = true;
                    downCaseNext = true;
                }
                i++;
            } else if (!reversible && i > 1 && ((Character.isUpperCase(ch) || Character.isTitleCase(ch)) && Character.isLowerCase(name.charAt(i - 1)))) {
                sbuf.append('-');
                mangled = true;
                ch = Character.toLowerCase(ch);
            }
            sbuf.append(ch);
            i++;
        }
        if (predicate) {
            sbuf.append('?');
        }
        return mangled ? sbuf.toString() : name;
    }

    public String generateClassName(String hint) {
        String hint2 = mangleName(hint, true);
        if (this.mainClass != null) {
            hint2 = this.mainClass.getName() + '$' + hint2;
        } else if (this.classPrefix != null) {
            hint2 = this.classPrefix + hint2;
        }
        if (findNamedClass(hint2) == null) {
            return hint2;
        }
        int i = 0;
        while (true) {
            String new_hint = hint2 + i;
            if (findNamedClass(new_hint) != null) {
                i++;
            } else {
                return new_hint;
            }
        }
    }

    public Compilation(Language language, SourceMessages messages, NameLookup lexical) {
        this.language = language;
        this.messages = messages;
        this.lexical = lexical;
    }

    public void walkModule(ModuleExp mexp) {
        if (debugPrintExpr) {
            OutPort dout = OutPort.errDefault();
            dout.println("[Module:" + mexp.getName());
            mexp.print(dout);
            dout.println(']');
            dout.flush();
        }
        InlineCalls.inlineCalls(mexp, this);
        PushApply.pushApply(mexp);
        ChainLambdas.chainLambdas(mexp, this);
        FindTailCalls.findTailCalls(mexp, this);
    }

    public void outputClass(String directory) throws IOException {
        char dirSep = File.separatorChar;
        for (int iClass = 0; iClass < this.numClasses; iClass++) {
            ClassType clas = this.classes[iClass];
            String out_name = directory + clas.getName().replace('.', dirSep) + ".class";
            String parent = new File(out_name).getParent();
            if (parent != null) {
                new File(parent).mkdirs();
            }
            clas.writeToFile(out_name);
        }
        this.minfo.cleanupAfterCompilation();
    }

    public void cleanupAfterCompilation() {
        for (int iClass = 0; iClass < this.numClasses; iClass++) {
            this.classes[iClass].cleanupAfterCompilation();
        }
        this.classes = null;
        this.minfo.comp = null;
        if (this.minfo.exp != null) {
            this.minfo.exp.body = null;
        }
        this.mainLambda.body = null;
        this.mainLambda = null;
        if (!this.immediate) {
            this.litTable = null;
        }
    }

    public void compileToArchive(ModuleExp mexp, String fname) throws IOException {
        boolean makeJar;
        ZipOutputStream zout;
        if (fname.endsWith(".zip")) {
            makeJar = false;
        } else if (!fname.endsWith(".jar")) {
            fname = fname + ".zip";
            makeJar = false;
        } else {
            makeJar = true;
        }
        process(12);
        File zar_file = new File(fname);
        if (zar_file.exists()) {
            zar_file.delete();
        }
        if (makeJar) {
            zout = new JarOutputStream(new FileOutputStream(zar_file));
        } else {
            zout = new ZipOutputStream(new FileOutputStream(zar_file));
        }
        byte[][] classBytes = new byte[this.numClasses];
        CRC32 zcrc = new CRC32();
        for (int iClass = 0; iClass < this.numClasses; iClass++) {
            ClassType clas = this.classes[iClass];
            classBytes[iClass] = clas.writeToArray();
            ZipEntry zent = new ZipEntry(clas.getName().replace('.', '/') + ".class");
            zent.setSize(classBytes[iClass].length);
            zcrc.reset();
            zcrc.update(classBytes[iClass], 0, classBytes[iClass].length);
            zent.setCrc(zcrc.getValue());
            zout.putNextEntry(zent);
            zout.write(classBytes[iClass]);
        }
        zout.close();
    }

    private void registerClass(ClassType new_class) {
        ClassType[] classTypeArr = this.classes;
        if (classTypeArr == null) {
            this.classes = new ClassType[20];
        } else {
            int i = this.numClasses;
            if (i >= classTypeArr.length) {
                ClassType[] new_classes = new ClassType[classTypeArr.length * 2];
                System.arraycopy(classTypeArr, 0, new_classes, 0, i);
                this.classes = new_classes;
            }
        }
        new_class.addModifiers(new_class.isInterface() ? 1 : 33);
        ClassType classType = this.mainClass;
        if (new_class == classType && this.numClasses > 0) {
            ClassType[] classTypeArr2 = this.classes;
            new_class = classTypeArr2[0];
            classTypeArr2[0] = classType;
        }
        ClassType[] classTypeArr3 = this.classes;
        int i2 = this.numClasses;
        this.numClasses = i2 + 1;
        classTypeArr3[i2] = new_class;
    }

    public void addClass(ClassType new_class) {
        if (this.mainLambda.filename != null) {
            if (emitSourceDebugExtAttr) {
                new_class.setStratum(getLanguage().getName());
            }
            new_class.setSourceFile(this.mainLambda.filename);
        }
        registerClass(new_class);
        new_class.setClassfileVersion(defaultClassFileVersion);
    }

    public boolean makeRunnable() {
        return (generatingServlet() || generatingApplet() || getModule().staticInitRun()) ? false : true;
    }

    public void addMainClass(ModuleExp module) {
        this.mainClass = module.classFor(this);
        ClassType type = this.mainClass;
        ClassType[] interfaces = module.getInterfaces();
        if (interfaces != null) {
            type.setInterfaces(interfaces);
        }
        ClassType sup = module.getSuperType();
        if (sup == null) {
            if (generatingApplet()) {
                sup = typeApplet;
            } else if (generatingServlet()) {
                sup = typeServlet;
            } else {
                sup = getModuleType();
            }
        }
        if (makeRunnable()) {
            type.addInterface(typeRunnable);
        }
        type.setSuper(sup);
        module.type = type;
        addClass(type);
        getConstructor(this.mainClass, module);
    }

    public final Method getConstructor(LambdaExp lexp) {
        return getConstructor(lexp.getHeapFrameType(), lexp);
    }

    public static final Method getConstructor(ClassType clas, LambdaExp lexp) {
        Type[] args;
        Method meth = clas.getDeclaredMethod("<init>", 0);
        if (meth != null) {
            return meth;
        }
        if ((lexp instanceof ClassExp) && lexp.staticLinkField != null) {
            args = new Type[]{lexp.staticLinkField.getType()};
        } else {
            args = apply0args;
        }
        return clas.addMethod("<init>", 1, args, Type.voidType);
    }

    public final void generateConstructor(LambdaExp lexp) {
        generateConstructor(lexp.getHeapFrameType(), lexp);
    }

    public final void generateConstructor(ClassType clas, LambdaExp lexp) {
        ModuleInfo moduleInfo;
        Method save_method = this.method;
        Variable callContextSave = this.callContextVar;
        this.callContextVar = null;
        ClassType save_class = this.curClass;
        this.curClass = clas;
        Method constructor_method = getConstructor(clas, lexp);
        clas.constructor = constructor_method;
        this.method = constructor_method;
        CodeAttr code = constructor_method.startCode();
        if ((lexp instanceof ClassExp) && lexp.staticLinkField != null) {
            code.emitPushThis();
            code.emitLoad(code.getCurrentScope().getVariable(1));
            code.emitPutField(lexp.staticLinkField);
        }
        ClassType superClass = clas.getSuperclass();
        ClassExp.invokeDefaultSuperConstructor(superClass, this, lexp);
        if (this.curClass == this.mainClass && (moduleInfo = this.minfo) != null && moduleInfo.sourcePath != null) {
            code.emitPushThis();
            code.emitInvokeStatic(ClassType.make("gnu.expr.ModuleInfo").getDeclaredMethod("register", 1));
        }
        if (lexp != null && lexp.initChain != null) {
            LambdaExp save = this.curLambda;
            LambdaExp lambdaExp = new LambdaExp();
            this.curLambda = lambdaExp;
            lambdaExp.closureEnv = code.getArg(0);
            this.curLambda.outer = save;
            while (true) {
                Initializer init = lexp.initChain;
                if (init == null) {
                    break;
                }
                lexp.initChain = null;
                dumpInitializers(init);
            }
            this.curLambda = save;
        }
        if (lexp instanceof ClassExp) {
            ClassExp cexp = (ClassExp) lexp;
            callInitMethods(cexp.getCompiledClassType(this), new Vector<>(10));
        }
        code.emitReturn();
        this.method = save_method;
        this.curClass = save_class;
        this.callContextVar = callContextSave;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void callInitMethods(ClassType clas, Vector<ClassType> seen) {
        if (clas == null) {
            return;
        }
        String name = clas.getName();
        if ("java.lang.Object".equals(name)) {
            return;
        }
        int i = seen.size();
        do {
            i--;
            if (i < 0) {
                seen.addElement(clas);
                ClassType[] interfaces = clas.getInterfaces();
                if (interfaces != null) {
                    for (ClassType classType : interfaces) {
                        callInitMethods(classType, seen);
                    }
                }
                int clEnvArgs = 1;
                if (clas.isInterface()) {
                    if (clas instanceof PairClassType) {
                        clas = ((PairClassType) clas).instanceType;
                    } else {
                        try {
                            clas = (ClassType) Type.make(Class.forName(clas.getName() + "$class"));
                        } catch (Throwable th) {
                            return;
                        }
                    }
                } else {
                    clEnvArgs = 0;
                }
                Method meth = clas.getDeclaredMethod("$finit$", clEnvArgs);
                if (meth != null) {
                    CodeAttr code = getCode();
                    code.emitPushThis();
                    code.emitInvoke(meth);
                    return;
                }
                return;
            }
        } while (seen.elementAt(i).getName() != name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void generateMatchMethods(gnu.expr.LambdaExp r31) {
        /*
            Method dump skipped, instructions count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.generateMatchMethods(gnu.expr.LambdaExp):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x007f, code lost:
    
        if (r2.max_args >= (r2.min_args + r0)) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01d4 A[EDGE_INSN: B:73:0x01d4->B:74:0x01d4 BREAK  A[LOOP:2: B:52:0x0140->B:68:0x01c6], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0283 A[LOOP:3: B:80:0x027e->B:82:0x0283, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0287 A[EDGE_INSN: B:83:0x0287->B:84:0x0287 BREAK  A[LOOP:3: B:80:0x027e->B:82:0x0283], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0295 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0262  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void generateApplyMethodsWithContext(gnu.expr.LambdaExp r37) {
        /*
            Method dump skipped, instructions count: 751
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.generateApplyMethodsWithContext(gnu.expr.LambdaExp):void");
    }

    public void generateApplyMethodsWithoutContext(LambdaExp lambdaExp) {
        boolean z;
        int i;
        ClassType classType;
        Method method;
        CodeAttr codeAttr;
        boolean z2;
        SwitchState switchState;
        String str;
        Type[] typeArr;
        SourceLocator sourceLocator;
        Variable variable;
        ClassType classType2;
        Method method2;
        SourceLocator sourceLocator2;
        Method[] methodArr;
        boolean z3;
        String str2;
        int i2;
        Type[] typeArr2;
        LambdaExp lambdaExp2 = lambdaExp;
        int size = lambdaExp2.applyMethods == null ? 0 : lambdaExp2.applyMethods.size();
        if (size == 0) {
            return;
        }
        ClassType classType3 = this.curClass;
        ClassType heapFrameType = lambdaExp.getHeapFrameType();
        this.curClass = heapFrameType;
        ClassType classType4 = typeModuleMethod;
        if (!heapFrameType.getSuperclass().isSubtype(typeModuleBody)) {
            this.curClass = this.moduleClass;
        }
        Method method3 = this.method;
        CodeAttr codeAttr2 = null;
        int i3 = defaultCallConvention >= 2 ? 5 : 0;
        while (i3 < 6) {
            boolean z4 = false;
            SwitchState switchState2 = null;
            String str3 = null;
            Type[] typeArr3 = null;
            int i4 = 0;
            while (i4 < size) {
                LambdaExp lambdaExp3 = (LambdaExp) lambdaExp2.applyMethods.elementAt(i4);
                int i5 = i4;
                Method[] methodArr2 = lambdaExp3.primMethods;
                int length = methodArr2.length;
                boolean z5 = lambdaExp3.max_args < 0 || lambdaExp3.max_args >= lambdaExp3.min_args + length;
                int i6 = size;
                if (i3 < 5) {
                    i = i3 - lambdaExp3.min_args;
                    if (i >= 0 && i < length) {
                        if (i != length - 1 || !z5) {
                            z = false;
                            length = 1;
                            z5 = false;
                        }
                    }
                    z = true;
                    length = 1;
                    z5 = false;
                } else {
                    int i7 = 5 - lambdaExp3.min_args;
                    if (i7 > 0 && length <= i7 && !z5) {
                        z = true;
                    } else {
                        z = false;
                    }
                    i = length - 1;
                }
                if (z) {
                    classType = classType3;
                    classType2 = classType4;
                    method = method3;
                } else {
                    if (z4) {
                        classType = classType3;
                        method = method3;
                        codeAttr = codeAttr2;
                        z2 = z4;
                        switchState = switchState2;
                        str = str3;
                        typeArr = typeArr3;
                    } else {
                        if (i3 < 5) {
                            classType = classType3;
                            str2 = "apply" + i3;
                            typeArr2 = new Type[i3 + 1];
                            for (int i8 = i3; i8 > 0; i8--) {
                                typeArr2[i8] = typeObject;
                            }
                            i2 = 2;
                        } else {
                            classType = classType3;
                            str2 = "applyN";
                            i2 = 2;
                            typeArr2 = new Type[2];
                            typeArr2[1] = objArrayType;
                        }
                        typeArr2[0] = classType4;
                        method = method3;
                        Method addMethod = this.curClass.addMethod(str2, typeArr2, defaultCallConvention >= i2 ? Type.voidType : Type.objectType, 1);
                        this.method = addMethod;
                        CodeAttr startCode = addMethod.startCode();
                        startCode.emitLoad(startCode.getArg(1));
                        startCode.emitGetField(classType4.getField("selector"));
                        codeAttr = startCode;
                        z2 = true;
                        switchState = startCode.startSwitch();
                        str = str2;
                        typeArr = typeArr2;
                    }
                    switchState.addCase(lambdaExp3.getSelectorValue(this), codeAttr);
                    SourceLocator swapSourceLocator = this.messages.swapSourceLocator(lambdaExp3);
                    int lineNumber = lambdaExp3.getLineNumber();
                    if (lineNumber > 0) {
                        codeAttr.putLineNumber(lambdaExp3.getFileName(), lineNumber);
                    }
                    Method method4 = methodArr2[i];
                    Type[] parameterTypes = method4.getParameterTypes();
                    int i9 = lambdaExp3.min_args + i;
                    int i10 = 0;
                    if (i3 <= 4 || length <= 1) {
                        sourceLocator = swapSourceLocator;
                        variable = null;
                    } else {
                        Variable addLocal = codeAttr.addLocal(Type.intType);
                        sourceLocator = swapSourceLocator;
                        codeAttr.emitLoad(codeAttr.getArg(2));
                        codeAttr.emitArrayLength();
                        if (lambdaExp3.min_args != 0) {
                            codeAttr.emitPushInt(lambdaExp3.min_args);
                            codeAttr.emitSub(Type.intType);
                        }
                        codeAttr.emitStore(addLocal);
                        variable = addLocal;
                    }
                    int i11 = !method4.getStaticFlag() ? 1 : 0;
                    int i12 = (z5 ? 1 : 0) + i9 < parameterTypes.length ? 1 : 0;
                    if (i11 + i12 > 0) {
                        codeAttr.emitPushThis();
                        ClassType classType5 = this.curClass;
                        ClassType classType6 = this.moduleClass;
                        if (classType5 == classType6 && this.mainClass != classType6) {
                            codeAttr.emitGetField(this.moduleInstanceMainField);
                        }
                    }
                    Declaration firstDecl = lambdaExp3.firstDecl();
                    if (firstDecl != null && firstDecl.isThisParameter()) {
                        firstDecl = firstDecl.nextDecl();
                    }
                    Declaration declaration = firstDecl;
                    int i13 = 0;
                    while (true) {
                        classType2 = classType4;
                        if (i13 >= i9) {
                            break;
                        }
                        if (variable != null && i13 >= lambdaExp3.min_args) {
                            codeAttr.emitLoad(variable);
                            codeAttr.emitIfIntLEqZero();
                            codeAttr.emitInvoke(methodArr2[i13 - lambdaExp3.min_args]);
                            codeAttr.emitElse();
                            i10++;
                            codeAttr.emitInc(variable, (short) -1);
                        }
                        Variable variable2 = null;
                        Method method5 = method4;
                        if (i3 <= 4) {
                            variable2 = codeAttr.getArg(i13 + 2);
                            codeAttr.emitLoad(variable2);
                            methodArr = methodArr2;
                        } else {
                            methodArr = methodArr2;
                            codeAttr.emitLoad(codeAttr.getArg(2));
                            codeAttr.emitPushInt(i13);
                            codeAttr.emitArrayLoad(Type.objectType);
                        }
                        Type type = declaration.getType();
                        if (type == Type.objectType) {
                            z3 = z2;
                        } else {
                            SourceLocator swapSourceLocator2 = this.messages.swapSourceLocator(declaration);
                            z3 = z2;
                            CheckedTarget.emitCheckedCoerce(this, lambdaExp3, i13 + 1, type, variable2);
                            this.messages.swapSourceLocator(swapSourceLocator2);
                        }
                        declaration = declaration.nextDecl();
                        i13++;
                        classType4 = classType2;
                        method4 = method5;
                        methodArr2 = methodArr;
                        z2 = z3;
                    }
                    Method method6 = method4;
                    boolean z6 = z2;
                    if (z5) {
                        Type type2 = parameterTypes[i12 + i9];
                        if (type2 instanceof ArrayType) {
                            method2 = method6;
                            sourceLocator2 = sourceLocator;
                            varArgsToArray(lambdaExp3, i9, variable, type2, null);
                        } else {
                            method2 = method6;
                            sourceLocator2 = sourceLocator;
                            if ("gnu.lists.LList".equals(type2.getName())) {
                                codeAttr.emitLoad(codeAttr.getArg(2));
                                codeAttr.emitPushInt(i9);
                                codeAttr.emitInvokeStatic(makeListMethod);
                            } else if (type2 == typeCallContext) {
                                codeAttr.emitLoad(codeAttr.getArg(2));
                            } else {
                                throw new RuntimeException("unsupported #!rest type:" + type2);
                            }
                        }
                    } else {
                        method2 = method6;
                        sourceLocator2 = sourceLocator;
                    }
                    codeAttr.emitInvoke(method2);
                    while (true) {
                        i10--;
                        if (i10 < 0) {
                            break;
                        } else {
                            codeAttr.emitFi();
                        }
                    }
                    if (defaultCallConvention < 2) {
                        Target.pushObject.compileFromStack(this, lambdaExp3.getReturnType());
                    }
                    this.messages.swapSourceLocator(sourceLocator2);
                    codeAttr.emitReturn();
                    codeAttr2 = codeAttr;
                    switchState2 = switchState;
                    str3 = str;
                    typeArr3 = typeArr;
                    z4 = z6;
                }
                i4 = i5 + 1;
                lambdaExp2 = lambdaExp;
                size = i6;
                classType3 = classType;
                method3 = method;
                classType4 = classType2;
            }
            int i14 = size;
            ClassType classType7 = classType3;
            ClassType classType8 = classType4;
            Method method7 = method3;
            if (z4) {
                switchState2.addDefault(codeAttr2);
                if (defaultCallConvention >= 2) {
                    codeAttr2.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
                } else {
                    int i15 = (i3 > 4 ? 2 : i3 + 1) + 1;
                    for (int i16 = 0; i16 < i15; i16++) {
                        codeAttr2.emitLoad(codeAttr2.getArg(i16));
                    }
                    codeAttr2.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(str3, typeArr3));
                }
                codeAttr2.emitReturn();
                switchState2.finish(codeAttr2);
            }
            i3++;
            lambdaExp2 = lambdaExp;
            size = i14;
            classType3 = classType7;
            method3 = method7;
            classType4 = classType8;
        }
        this.method = method3;
        this.curClass = classType3;
    }

    private void varArgsToArray(LambdaExp source, int singleArgs, Variable counter, Type lastArgType, Variable ctxVar) {
        Variable counter2;
        CodeAttr code = getCode();
        Type elType = ((ArrayType) lastArgType).getComponentType();
        boolean mustConvert = !"java.lang.Object".equals(elType.getName());
        if (ctxVar == null || mustConvert) {
            if (singleArgs == 0 && !mustConvert) {
                code.emitLoad(code.getArg(2));
            } else {
                code.pushScope();
                if (counter != null) {
                    counter2 = counter;
                } else {
                    Variable counter3 = code.addLocal(Type.intType);
                    if (ctxVar != null) {
                        code.emitLoad(ctxVar);
                        code.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
                    } else {
                        code.emitLoad(code.getArg(2));
                        code.emitArrayLength();
                    }
                    if (singleArgs != 0) {
                        code.emitPushInt(singleArgs);
                        code.emitSub(Type.intType);
                    }
                    code.emitStore(counter3);
                    counter2 = counter3;
                }
                code.emitLoad(counter2);
                code.emitNewArray(elType.getImplementationType());
                Label testLabel = new Label(code);
                Label loopTopLabel = new Label(code);
                loopTopLabel.setTypes(code);
                code.emitGoto(testLabel);
                loopTopLabel.define(code);
                code.emitDup(1);
                code.emitLoad(counter2);
                if (ctxVar != null) {
                    code.emitLoad(ctxVar);
                } else {
                    code.emitLoad(code.getArg(2));
                }
                code.emitLoad(counter2);
                if (singleArgs != 0) {
                    code.emitPushInt(singleArgs);
                    code.emitAdd(Type.intType);
                }
                if (ctxVar != null) {
                    code.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getArgAsObject", 1));
                } else {
                    code.emitArrayLoad(Type.objectType);
                }
                if (mustConvert) {
                    CheckedTarget.emitCheckedCoerce(this, source, source.getName(), 0, elType, null);
                }
                code.emitArrayStore(elType);
                testLabel.define(code);
                code.emitInc(counter2, (short) -1);
                code.emitLoad(counter2);
                code.emitGotoIfIntGeZero(loopTopLabel);
                code.popScope();
            }
        } else {
            code.emitLoad(ctxVar);
            code.emitPushInt(singleArgs);
            code.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsArray", 1));
        }
    }

    private Method startClassInit() {
        Method addMethod = this.curClass.addMethod("<clinit>", apply0args, Type.voidType, 9);
        this.method = addMethod;
        CodeAttr code = addMethod.startCode();
        if (this.generateMain || generatingApplet() || generatingServlet()) {
            ClassType languageType = (ClassType) Type.make(getLanguage().getClass());
            Method registerMethod = languageType.getDeclaredMethod("registerEnvironment", 0);
            if (registerMethod != null) {
                code.emitInvokeStatic(registerMethod);
            }
        }
        return this.method;
    }

    public void process(int wantedState) {
        Compilation saveCompilation = setSaveCurrent(this);
        try {
            try {
                ModuleExp mexp = getModule();
                if (wantedState >= 4 && getState() < 3) {
                    setState(3);
                    this.language.parse(this, 0);
                    this.lexer.close();
                    this.lexer = null;
                    setState(this.messages.seenErrors() ? 100 : 4);
                    if (this.pendingImports != null) {
                        return;
                    }
                }
                if (wantedState >= 6 && getState() < 6) {
                    addMainClass(mexp);
                    this.language.resolve(this);
                    setState(this.messages.seenErrors() ? 100 : 6);
                }
                if (!this.explicit && !this.immediate && this.minfo.checkCurrent(ModuleManager.getInstance(), System.currentTimeMillis())) {
                    this.minfo.cleanupAfterCompilation();
                    setState(14);
                }
                if (wantedState >= 8 && getState() < 8) {
                    walkModule(mexp);
                    setState(this.messages.seenErrors() ? 100 : 8);
                }
                if (wantedState >= 10 && getState() < 10) {
                    this.litTable = new LitTable(this);
                    mexp.setCanRead(true);
                    FindCapturedVars.findCapturedVars(mexp, this);
                    mexp.allocFields(this);
                    mexp.allocChildMethods(this);
                    setState(this.messages.seenErrors() ? 100 : 10);
                }
                if (wantedState >= 12 && getState() < 12) {
                    if (this.immediate) {
                        ClassLoader parentLoader = ObjectType.getContextClassLoader();
                        this.loader = new ArrayClassLoader(parentLoader);
                    }
                    generateBytecode();
                    setState(this.messages.seenErrors() ? 100 : 12);
                }
                if (wantedState >= 14 && getState() < 14) {
                    ModuleManager manager = ModuleManager.getInstance();
                    outputClass(manager.getCompilationDirectory());
                    setState(14);
                }
            } catch (SyntaxException ex) {
                setState(100);
                if (ex.getMessages() != getMessages()) {
                    throw new RuntimeException("confussing syntax error: " + ex);
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
                error('f', "caught " + ex2);
                setState(100);
            }
        } finally {
            restoreCurrent(saveCompilation);
        }
    }

    void generateBytecode() {
        Type[] arg_types;
        int arg_count;
        Method initMethod;
        String mainPrefix;
        String uri;
        ModuleManager manager;
        ClassType typeModuleSet;
        Variable heapFrame;
        char sep;
        ModuleExp module = getModule();
        if (debugPrintFinalExpr) {
            OutPort dout = OutPort.errDefault();
            dout.println("[Compiling final " + module.getName() + " to " + this.mainClass.getName() + ":");
            module.print(dout);
            dout.println(']');
            dout.flush();
        }
        ClassType neededSuper = getModuleType();
        if (this.mainClass.getSuperclass().isSubtype(neededSuper)) {
            this.moduleClass = this.mainClass;
        } else {
            ClassType classType = new ClassType(generateClassName("frame"));
            this.moduleClass = classType;
            classType.setSuper(neededSuper);
            addClass(this.moduleClass);
            generateConstructor(this.moduleClass, null);
        }
        this.curClass = module.type;
        LambdaExp saveLambda = this.curLambda;
        this.curLambda = module;
        if (module.isHandlingTailCalls()) {
            Type[] arg_types2 = {typeCallContext};
            arg_types = arg_types2;
            arg_count = 1;
        } else {
            int arg_count2 = module.min_args;
            if (arg_count2 != module.max_args || module.min_args > 4) {
                Type[] arg_types3 = {new ArrayType(typeObject)};
                arg_types = arg_types3;
                arg_count = 1;
            } else {
                int arg_count3 = module.min_args;
                Type[] arg_types4 = new Type[arg_count3];
                int i = arg_count3;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    } else {
                        arg_types4[i] = typeObject;
                    }
                }
                arg_types = arg_types4;
                arg_count = arg_count3;
            }
        }
        Variable heapFrame2 = module.heapFrame;
        boolean staticModule = module.isStatic();
        Method apply_method = this.curClass.addMethod("run", arg_types, Type.voidType, 17);
        this.method = apply_method;
        apply_method.initCode();
        CodeAttr code = getCode();
        this.thisDecl = this.method.getStaticFlag() ? null : module.declareThis(module.type);
        module.closureEnv = module.thisVariable;
        module.heapFrame = module.isStatic() ? null : module.thisVariable;
        module.allocChildClasses(this);
        if (module.isHandlingTailCalls() || usingCPStyle()) {
            this.callContextVar = new Variable("$ctx", typeCallContext);
            module.getVarScope().addVariableAfter(this.thisDecl, this.callContextVar);
            this.callContextVar.setParameter(true);
        }
        int line = module.getLineNumber();
        if (line > 0) {
            code.putLineNumber(module.getFileName(), line);
        }
        module.allocParameters(this);
        module.enterFunction(this);
        if (usingCPStyle()) {
            loadCallContext();
            code.emitGetField(pcCallContextField);
            SwitchState startSwitch = code.startSwitch();
            this.fswitch = startSwitch;
            startSwitch.addCase(0, code);
        }
        module.compileBody(this);
        module.compileEnd(this);
        Label startLiterals = null;
        Label afterLiterals = null;
        if (this.curClass != this.mainClass) {
            initMethod = null;
        } else {
            Method save_method = this.method;
            Variable callContextSave = this.callContextVar;
            this.callContextVar = null;
            Method initMethod2 = startClassInit();
            this.clinitMethod = initMethod2;
            CodeAttr code2 = getCode();
            startLiterals = new Label(code2);
            afterLiterals = new Label(code2);
            code2.fixupChain(afterLiterals, startLiterals);
            if (staticModule) {
                generateConstructor(module);
                code2.emitNew(this.moduleClass);
                code2.emitDup(this.moduleClass);
                code2.emitInvokeSpecial(this.moduleClass.constructor);
                ClassType classType2 = this.moduleClass;
                Field addField = classType2.addField("$instance", classType2, 25);
                this.moduleInstanceMainField = addField;
                code2.emitPutStatic(addField);
            }
            while (true) {
                Initializer init = this.clinitChain;
                if (init == null) {
                    break;
                }
                this.clinitChain = null;
                dumpInitializers(init);
            }
            if (module.staticInitRun()) {
                code2.emitGetStatic(this.moduleInstanceMainField);
                code2.emitInvoke(typeModuleBody.getDeclaredMethod("run", 0));
            }
            code2.emitReturn();
            if (this.moduleClass != this.mainClass && !staticModule && !this.generateMain && !this.immediate) {
                Method addMethod = this.curClass.addMethod("run", 1, Type.typeArray0, Type.voidType);
                this.method = addMethod;
                code = addMethod.startCode();
                Variable ctxVar = code.addLocal(typeCallContext);
                Variable saveVar = code.addLocal(typeConsumer);
                Variable exceptionVar = code.addLocal(Type.javalangThrowableType);
                code.emitInvokeStatic(getCallContextInstanceMethod);
                code.emitStore(ctxVar);
                Field consumerFld = typeCallContext.getDeclaredField("consumer");
                code.emitLoad(ctxVar);
                code.emitGetField(consumerFld);
                code.emitStore(saveVar);
                code.emitLoad(ctxVar);
                code.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
                code.emitPutField(consumerFld);
                code.emitTryStart(false, Type.voidType);
                code.emitPushThis();
                code.emitLoad(ctxVar);
                code.emitInvokeVirtual(save_method);
                code.emitPushNull();
                code.emitStore(exceptionVar);
                code.emitTryEnd();
                code.emitCatchStart(exceptionVar);
                code.emitCatchEnd();
                code.emitTryCatchEnd();
                code.emitLoad(ctxVar);
                code.emitLoad(exceptionVar);
                code.emitLoad(saveVar);
                code.emitInvokeStatic(typeModuleBody.getDeclaredMethod("runCleanup", 3));
                code.emitReturn();
            } else {
                code = code2;
            }
            this.method = save_method;
            this.callContextVar = callContextSave;
            initMethod = initMethod2;
        }
        module.generateApplyMethods(this);
        this.curLambda = saveLambda;
        module.heapFrame = heapFrame2;
        if (usingCPStyle()) {
            code = getCode();
            this.fswitch.finish(code);
        }
        if (startLiterals != null || this.callContextVar != null) {
            this.method = initMethod;
            CodeAttr code3 = getCode();
            Label endLiterals = new Label(code3);
            code3.fixupChain(startLiterals, endLiterals);
            if (this.callContextVarForInit != null) {
                code3.emitInvokeStatic(getCallContextInstanceMethod);
                code3.emitStore(this.callContextVarForInit);
            }
            try {
                if (this.immediate) {
                    code3.emitPushInt(registerForImmediateLiterals(this));
                    code3.emitInvokeStatic(ClassType.make("gnu.expr.Compilation").getDeclaredMethod("setupLiterals", 1));
                } else {
                    this.litTable.emit();
                }
            } catch (Throwable ex) {
                error('e', "Literals: Internal error:" + ex);
            }
            code3.fixupChain(endLiterals, afterLiterals);
            code = code3;
        }
        if (this.generateMain && this.curClass == this.mainClass) {
            Type[] args = {new ArrayType(javaStringType)};
            Method addMethod2 = this.curClass.addMethod("main", 9, args, Type.voidType);
            this.method = addMethod2;
            CodeAttr code4 = addMethod2.startCode();
            if (Shell.defaultFormatName != null) {
                code4.emitPushString(Shell.defaultFormatName);
                code4.emitInvokeStatic(ClassType.make("kawa.Shell").getDeclaredMethod("setDefaultFormat", 1));
            }
            code4.emitLoad(code4.getArg(0));
            code4.emitInvokeStatic(ClassType.make("gnu.expr.ApplicationMainSupport").getDeclaredMethod("processArgs", 1));
            Field field = this.moduleInstanceMainField;
            if (field != null) {
                code4.emitGetStatic(field);
            } else {
                code4.emitNew(this.curClass);
                code4.emitDup(this.curClass);
                code4.emitInvokeSpecial(this.curClass.constructor);
            }
            code4.emitInvokeVirtual(typeModuleBody.getDeclaredMethod("runAsMain", 0));
            code4.emitReturn();
        }
        ModuleInfo moduleInfo = this.minfo;
        if (moduleInfo == null) {
            return;
        }
        String namespaceUri = moduleInfo.getNamespaceUri();
        String uri2 = namespaceUri;
        if (namespaceUri != null) {
            ModuleManager manager2 = ModuleManager.getInstance();
            String mainPrefix2 = this.mainClass.getName();
            int dot = mainPrefix2.lastIndexOf(46);
            if (dot < 0) {
                mainPrefix = "";
            } else {
                String mainPackage = mainPrefix2.substring(0, dot);
                try {
                    manager2.loadPackageInfo(mainPackage);
                } catch (ClassNotFoundException e) {
                } catch (Throwable ex2) {
                    error('e', "error loading map for " + mainPackage + " - " + ex2);
                }
                mainPrefix = mainPrefix2.substring(0, dot + 1);
            }
            ClassType mapClass = new ClassType(mainPrefix + ModuleSet.MODULES_MAP);
            ClassType typeModuleSet2 = ClassType.make("gnu.expr.ModuleSet");
            mapClass.setSuper(typeModuleSet2);
            registerClass(mapClass);
            this.method = mapClass.addMethod("<init>", 1, apply0args, Type.voidType);
            Method superConstructor = typeModuleSet2.addMethod("<init>", 1, apply0args, Type.voidType);
            CodeAttr code5 = this.method.startCode();
            code5.emitPushThis();
            code5.emitInvokeSpecial(superConstructor);
            code5.emitReturn();
            ClassType typeModuleManager = ClassType.make("gnu.expr.ModuleManager");
            Type[] margs = {typeModuleManager};
            Method addMethod3 = mapClass.addMethod("register", margs, Type.voidType, 1);
            this.method = addMethod3;
            CodeAttr code6 = addMethod3.startCode();
            Method reg = typeModuleManager.getDeclaredMethod("register", 3);
            int i2 = manager2.numModules;
            while (true) {
                int i3 = i2 - 1;
                if (i3 >= 0) {
                    ClassType typeModuleManager2 = typeModuleManager;
                    ModuleInfo mi = manager2.modules[i3];
                    String miClassName = mi.getClassName();
                    if (miClassName == null) {
                        uri = uri2;
                        manager = manager2;
                        typeModuleSet = typeModuleSet2;
                        heapFrame = heapFrame2;
                    } else if (!miClassName.startsWith(mainPrefix)) {
                        uri = uri2;
                        manager = manager2;
                        typeModuleSet = typeModuleSet2;
                        heapFrame = heapFrame2;
                    } else {
                        uri = uri2;
                        String moduleSource = mi.sourcePath;
                        typeModuleSet = typeModuleSet2;
                        Object moduleUri = mi.getNamespaceUri();
                        heapFrame = heapFrame2;
                        code6.emitLoad(code6.getArg(1));
                        compileConstant(miClassName);
                        if (Path.valueOf(moduleSource).isAbsolute()) {
                            manager = manager2;
                        } else {
                            try {
                                sep = File.separatorChar;
                                try {
                                    manager = manager2;
                                } catch (Throwable th) {
                                    ex = th;
                                }
                            } catch (Throwable th2) {
                                ex = th2;
                            }
                            try {
                                String path = Path.toURL(manager2.getCompilationDirectory() + mainPrefix.replace('.', sep)).toString();
                                int plen = path.length();
                                if (plen > 0 && path.charAt(plen - 1) != sep) {
                                    path = path + sep;
                                }
                                moduleSource = Path.relativize(mi.getSourceAbsPathname(), path);
                            } catch (Throwable th3) {
                                ex = th3;
                                throw new WrappedException("exception while fixing up '" + moduleSource + '\'', ex);
                            }
                        }
                        compileConstant(moduleSource);
                        compileConstant(moduleUri);
                        code6.emitInvokeVirtual(reg);
                    }
                    manager2 = manager;
                    typeModuleManager = typeModuleManager2;
                    i2 = i3;
                    uri2 = uri;
                    typeModuleSet2 = typeModuleSet;
                    heapFrame2 = heapFrame;
                } else {
                    code6.emitReturn();
                    return;
                }
            }
        }
    }

    public Field allocLocalField(Type type, String name) {
        if (name == null) {
            StringBuilder append = new StringBuilder().append("tmp_");
            int i = this.localFieldIndex + 1;
            this.localFieldIndex = i;
            name = append.append(i).toString();
        }
        Field field = this.curClass.addField(name, type, 0);
        return field;
    }

    public final void loadCallContext() {
        CodeAttr code = getCode();
        Variable variable = this.callContextVar;
        if (variable != null && !variable.dead()) {
            code.emitLoad(this.callContextVar);
            return;
        }
        if (this.method == this.clinitMethod) {
            Variable variable2 = new Variable("$ctx", typeCallContext);
            this.callContextVar = variable2;
            variable2.reserveLocal(code.getMaxLocals(), code);
            code.emitLoad(this.callContextVar);
            this.callContextVarForInit = this.callContextVar;
            return;
        }
        code.emitInvokeStatic(getCallContextInstanceMethod);
        code.emitDup();
        this.callContextVar = new Variable("$ctx", typeCallContext);
        code.getCurrentScope().addVariable(code, this.callContextVar);
        code.emitStore(this.callContextVar);
    }

    public void freeLocalField(Field field) {
    }

    public Expression parse(Object input) {
        throw new Error("unimeplemented parse");
    }

    public Language getLanguage() {
        return this.language;
    }

    public LambdaExp currentLambda() {
        return this.current_scope.currentLambda();
    }

    public final ModuleExp getModule() {
        return this.mainLambda;
    }

    public void setModule(ModuleExp mexp) {
        this.mainLambda = mexp;
    }

    public boolean isStatic() {
        return this.mainLambda.isStatic();
    }

    public ModuleExp currentModule() {
        return this.current_scope.currentModule();
    }

    public void mustCompileHere() {
        if (!this.mustCompile && !ModuleExp.compilerAvailable) {
            error('w', "this expression claimed that it must be compiled, but compiler is unavailable");
        } else {
            this.mustCompile = true;
        }
    }

    public ScopeExp currentScope() {
        return this.current_scope;
    }

    public void setCurrentScope(ScopeExp scope) {
        int scope_nesting = ScopeExp.nesting(scope);
        int current_nesting = ScopeExp.nesting(this.current_scope);
        while (current_nesting > scope_nesting) {
            pop(this.current_scope);
            current_nesting--;
        }
        ScopeExp sc = scope;
        while (scope_nesting > current_nesting) {
            sc = sc.outer;
            scope_nesting--;
        }
        while (true) {
            ScopeExp scopeExp = this.current_scope;
            if (sc != scopeExp) {
                pop(scopeExp);
                sc = sc.outer;
            } else {
                pushChain(scope, sc);
                return;
            }
        }
    }

    void pushChain(ScopeExp scope, ScopeExp limit) {
        if (scope != limit) {
            pushChain(scope.outer, limit);
            pushScope(scope);
            this.lexical.push(scope);
        }
    }

    public ModuleExp pushNewModule(Lexer lexer) {
        this.lexer = lexer;
        return pushNewModule(lexer.getName());
    }

    public ModuleExp pushNewModule(String filename) {
        ModuleExp module = new ModuleExp();
        if (filename != null) {
            module.setFile(filename);
        }
        if (generatingApplet() || generatingServlet()) {
            module.setFlag(131072);
        }
        if (this.immediate) {
            module.setFlag(1048576);
            new ModuleInfo().setCompilation(this);
        }
        this.mainLambda = module;
        push(module);
        return module;
    }

    public void push(ScopeExp scope) {
        pushScope(scope);
        this.lexical.push(scope);
    }

    public final void pushScope(ScopeExp scope) {
        if (!this.mustCompile && (scope.mustCompile() || (ModuleExp.compilerAvailable && (scope instanceof LambdaExp) && !(scope instanceof ModuleExp)))) {
            mustCompileHere();
        }
        scope.outer = this.current_scope;
        this.current_scope = scope;
    }

    public void pop(ScopeExp scope) {
        this.lexical.pop(scope);
        this.current_scope = scope.outer;
    }

    public final void pop() {
        pop(this.current_scope);
    }

    public void push(Declaration decl) {
        this.lexical.push(decl);
    }

    public Declaration lookup(Object name, int namespace) {
        return this.lexical.lookup(name, namespace);
    }

    public void usedClass(Type type) {
        while (type instanceof ArrayType) {
            type = ((ArrayType) type).getComponentType();
        }
        if (this.immediate && (type instanceof ClassType)) {
            this.loader.addClass((ClassType) type);
        }
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void setMessages(SourceMessages messages) {
        this.messages = messages;
    }

    public void error(char severity, String message, SourceLocator location) {
        String file = location.getFileName();
        int line = location.getLineNumber();
        int column = location.getColumnNumber();
        if (file == null || line <= 0) {
            file = getFileName();
            line = getLineNumber();
            column = getColumnNumber();
        }
        if (severity == 'w' && warnAsError()) {
            severity = 'e';
        }
        this.messages.error(severity, file, line, column, message);
    }

    public void error(char severity, String message) {
        if (severity == 'w' && warnAsError()) {
            severity = 'e';
        }
        this.messages.error(severity, this, message);
    }

    public void error(char severity, Declaration decl, String msg1, String msg2) {
        error(severity, msg1 + decl.getName() + msg2, (String) null, decl);
    }

    public void error(char severity, String message, String code, Declaration decl) {
        String filename;
        int line;
        int column;
        if (severity == 'w' && warnAsError()) {
            severity = 'e';
        }
        String filename2 = getFileName();
        int line2 = getLineNumber();
        int column2 = getColumnNumber();
        int decl_line = decl.getLineNumber();
        if (decl_line <= 0) {
            filename = filename2;
            line = line2;
            column = column2;
        } else {
            String filename3 = decl.getFileName();
            int column3 = decl.getColumnNumber();
            filename = filename3;
            line = decl_line;
            column = column3;
        }
        this.messages.error(severity, filename, line, column, message, code);
    }

    public Expression syntaxError(String message) {
        error('e', message);
        return new ErrorExp(message);
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public final int getLineNumber() {
        return this.messages.getLineNumber();
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public final int getColumnNumber() {
        return this.messages.getColumnNumber();
    }

    @Override // gnu.text.SourceLocator
    public final String getFileName() {
        return this.messages.getFileName();
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public String getPublicId() {
        return this.messages.getPublicId();
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public String getSystemId() {
        return this.messages.getSystemId();
    }

    @Override // gnu.text.SourceLocator
    public boolean isStableSourceLocation() {
        return false;
    }

    public void setFile(String filename) {
        this.messages.setFile(filename);
    }

    public void setLine(int line) {
        this.messages.setLine(line);
    }

    public void setColumn(int column) {
        this.messages.setColumn(column);
    }

    public final void setLine(Expression position) {
        this.messages.setLocation(position);
    }

    public void setLine(Object location) {
        if (location instanceof SourceLocator) {
            this.messages.setLocation((SourceLocator) location);
        }
    }

    public final void setLocation(SourceLocator position) {
        this.messages.setLocation(position);
    }

    public void setLine(String filename, int line, int column) {
        this.messages.setLine(filename, line, column);
    }

    public void letStart() {
        pushScope(new LetExp(null));
    }

    public Declaration letVariable(Object name, Type type, Expression init) {
        LetExp let = (LetExp) this.current_scope;
        Declaration decl = let.addDeclaration(name, type);
        decl.noteValue(init);
        return decl;
    }

    public void letEnter() {
        LetExp let = (LetExp) this.current_scope;
        int ndecls = let.countDecls();
        Expression[] inits = new Expression[ndecls];
        int i = 0;
        Declaration decl = let.firstDecl();
        while (decl != null) {
            inits[i] = decl.getValue();
            decl = decl.nextDecl();
            i++;
        }
        let.inits = inits;
        this.lexical.push(let);
    }

    public LetExp letDone(Expression body) {
        LetExp let = (LetExp) this.current_scope;
        let.body = body;
        pop(let);
        return let;
    }

    private void checkLoop() {
        if (((LambdaExp) this.current_scope).getName() != "%do%loop") {
            throw new Error("internal error - bad loop state");
        }
    }

    public void loopStart() {
        LambdaExp loopLambda = new LambdaExp();
        Expression[] inits = {loopLambda};
        LetExp let = new LetExp(inits);
        Declaration fdecl = let.addDeclaration("%do%loop");
        fdecl.noteValue(loopLambda);
        loopLambda.setName("%do%loop");
        let.outer = this.current_scope;
        loopLambda.outer = let;
        this.current_scope = loopLambda;
    }

    public Declaration loopVariable(Object name, Type type, Expression init) {
        checkLoop();
        LambdaExp loopLambda = (LambdaExp) this.current_scope;
        Declaration decl = loopLambda.addDeclaration(name, type);
        if (this.exprStack == null) {
            this.exprStack = new Stack<>();
        }
        this.exprStack.push(init);
        loopLambda.min_args++;
        return decl;
    }

    public void loopEnter() {
        checkLoop();
        LambdaExp loopLambda = (LambdaExp) this.current_scope;
        int ninits = loopLambda.min_args;
        loopLambda.max_args = ninits;
        Expression[] inits = new Expression[ninits];
        int i = ninits;
        while (true) {
            i--;
            if (i >= 0) {
                inits[i] = this.exprStack.pop();
            } else {
                LetExp let = (LetExp) loopLambda.outer;
                Declaration fdecl = let.firstDecl();
                let.setBody(new ApplyExp((Expression) new ReferenceExp(fdecl), inits));
                this.lexical.push(loopLambda);
                return;
            }
        }
    }

    public void loopCond(Expression cond) {
        checkLoop();
        this.exprStack.push(cond);
    }

    public void loopBody(Expression body) {
        LambdaExp loopLambda = (LambdaExp) this.current_scope;
        loopLambda.body = body;
    }

    public Expression loopRepeat(Expression[] exps) {
        LambdaExp loopLambda = (LambdaExp) this.current_scope;
        ScopeExp let = loopLambda.outer;
        Declaration fdecl = let.firstDecl();
        Expression cond = this.exprStack.pop();
        Expression recurse = new ApplyExp((Expression) new ReferenceExp(fdecl), exps);
        loopLambda.body = new IfExp(cond, new BeginExp(loopLambda.body, recurse), QuoteExp.voidExp);
        this.lexical.pop(loopLambda);
        this.current_scope = let.outer;
        return let;
    }

    public Expression loopRepeat() {
        return loopRepeat(Expression.noExpressions);
    }

    public Expression loopRepeat(Expression exp) {
        Expression[] args = {exp};
        return loopRepeat(args);
    }

    public static ApplyExp makeCoercion(Expression value, Expression type) {
        Expression[] exps = {type, value};
        QuoteExp c = new QuoteExp(Convert.getInstance());
        return new ApplyExp((Expression) c, exps);
    }

    public static Expression makeCoercion(Expression value, Type type) {
        return makeCoercion(value, new QuoteExp(type));
    }

    public void loadClassRef(ObjectType clas) {
        Field field;
        CodeAttr code = getCode();
        if (this.curClass.getClassfileVersion() >= 3211264) {
            code.emitPushClass(clas);
            return;
        }
        if (clas == this.mainClass && this.mainLambda.isStatic() && (field = this.moduleInstanceMainField) != null) {
            code.emitGetStatic(field);
            code.emitInvokeVirtual(Type.objectType.getDeclaredMethod("getClass", 0));
        } else {
            String name = clas instanceof ClassType ? clas.getName() : clas.getInternalName().replace('/', '.');
            code.emitPushString(name);
            code.emitInvokeStatic(getForNameHelper());
        }
    }

    public Method getForNameHelper() {
        if (this.forNameHelper == null) {
            Method save_method = this.method;
            Method addMethod = this.curClass.addMethod("class$", 9, string1Arg, typeClass);
            this.method = addMethod;
            this.forNameHelper = addMethod;
            CodeAttr code = addMethod.startCode();
            code.emitLoad(code.getArg(0));
            code.emitPushInt(0);
            code.emitPushString(this.mainClass.getName());
            code.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 1));
            code.emitInvokeVirtual(typeClass.getDeclaredMethod("getClassLoader", 0));
            code.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 3));
            code.emitReturn();
            this.method = save_method;
        }
        Method save_method2 = this.forNameHelper;
        return save_method2;
    }

    public Object resolve(Object name, boolean function) {
        Symbol symbol;
        Environment env = Environment.getCurrent();
        if (name instanceof String) {
            symbol = env.defaultNamespace().lookup((String) name);
        } else {
            symbol = (Symbol) name;
        }
        if (symbol == null) {
            return null;
        }
        if (function && getLanguage().hasSeparateFunctionNamespace()) {
            return env.getFunction(symbol, null);
        }
        return env.get(symbol, (Object) null);
    }

    public static void setupLiterals(int key) {
        Compilation comp = findForImmediateLiterals(key);
        try {
            Class clas = comp.loader.loadClass(comp.mainClass.getName());
            for (Literal init = comp.litTable.literalsChain; init != null; init = init.next) {
                clas.getDeclaredField(init.field.getName()).set(null, init.value);
            }
            comp.litTable = null;
        } catch (Throwable ex) {
            throw new WrappedException("internal error", ex);
        }
    }

    public static synchronized int registerForImmediateLiterals(Compilation comp) {
        int i;
        synchronized (Compilation.class) {
            i = 0;
            for (Compilation c = chainUninitialized; c != null; c = c.nextUninitialized) {
                int i2 = c.keyUninitialized;
                if (i <= i2) {
                    i = i2 + 1;
                }
            }
            comp.keyUninitialized = i;
            comp.nextUninitialized = chainUninitialized;
            chainUninitialized = comp;
        }
        return i;
    }

    public static synchronized Compilation findForImmediateLiterals(int key) {
        Compilation comp;
        Compilation next;
        synchronized (Compilation.class) {
            Compilation prev = null;
            comp = chainUninitialized;
            while (true) {
                next = comp.nextUninitialized;
                if (comp.keyUninitialized == key) {
                    break;
                }
                prev = comp;
                comp = next;
            }
            if (prev == null) {
                chainUninitialized = next;
            } else {
                prev.nextUninitialized = next;
            }
            comp.nextUninitialized = null;
        }
        return comp;
    }

    public static Compilation getCurrent() {
        return current.get();
    }

    public static void setCurrent(Compilation comp) {
        current.set(comp);
    }

    public static Compilation setSaveCurrent(Compilation comp) {
        ThreadLocal<Compilation> threadLocal = current;
        Compilation save = threadLocal.get();
        threadLocal.set(comp);
        return save;
    }

    public static void restoreCurrent(Compilation saved) {
        current.set(saved);
    }

    public String toString() {
        return "<compilation " + this.mainLambda + ">";
    }
}
