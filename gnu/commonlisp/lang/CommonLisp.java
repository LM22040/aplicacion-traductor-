package gnu.commonlisp.lang;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.Access;
import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractFormat;
import gnu.mapping.Environment;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.SimpleEnvironment;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import kawa.lang.Lambda;
import kawa.standard.Scheme;
import kawa.standard.begin;

/* loaded from: classes2.dex */
public class CommonLisp extends Lisp2 {
    static boolean charIsInt = false;
    public static final Environment clispEnvironment;
    static final AbstractFormat displayFormat;
    public static final CommonLisp instance;
    public static final NumberCompare numEqu;
    public static final NumberCompare numGEq;
    public static final NumberCompare numGrt;
    public static final NumberCompare numLEq;
    public static final NumberCompare numLss;
    static final AbstractFormat writeFormat;
    LangPrimType booleanType;

    static {
        SimpleEnvironment make = Environment.make("clisp-environment");
        clispEnvironment = make;
        CommonLisp commonLisp = new CommonLisp();
        instance = commonLisp;
        commonLisp.define("t", TRUE);
        commonLisp.define("nil", FALSE);
        numEqu = NumberCompare.make(commonLisp, "=", 8);
        numGrt = NumberCompare.make(commonLisp, ">", 16);
        numGEq = NumberCompare.make(commonLisp, ">=", 24);
        numLss = NumberCompare.make(commonLisp, "<", 4);
        numLEq = NumberCompare.make(commonLisp, "<=", 12);
        Environment saveEnv = Environment.setSaveCurrent(make);
        try {
            commonLisp.initLisp();
            Environment.restoreCurrent(saveEnv);
            writeFormat = new DisplayFormat(true, Access.CLASS_CONTEXT);
            displayFormat = new DisplayFormat(false, Access.CLASS_CONTEXT);
        } catch (Throwable th) {
            Environment.restoreCurrent(saveEnv);
            throw th;
        }
    }

    public static Object getCharacter(int c) {
        if (charIsInt) {
            return IntNum.make(c);
        }
        return Char.make((char) c);
    }

    public static Numeric asNumber(Object arg) {
        if (arg instanceof Char) {
            return IntNum.make(((Char) arg).intValue());
        }
        return (Numeric) arg;
    }

    public static char asChar(Object x) {
        int i;
        if (x instanceof Char) {
            return ((Char) x).charValue();
        }
        if (x instanceof Numeric) {
            i = ((Numeric) x).intValue();
        } else {
            i = -1;
        }
        if (i < 0 || i > 65535) {
            throw new ClassCastException("not a character value");
        }
        return (char) i;
    }

    @Override // gnu.expr.Language
    public String getName() {
        return "CommonLisp";
    }

    public CommonLisp() {
        this.environ = clispEnvironment;
    }

    void initLisp() {
        LocationEnumeration e = Scheme.builtin().enumerateAllLocations();
        while (e.hasMoreElements()) {
            importLocation(e.nextLocation());
        }
        try {
            loadClass("kawa.lib.prim_syntax");
            loadClass("kawa.lib.std_syntax");
            loadClass("kawa.lib.lists");
            loadClass("kawa.lib.strings");
            loadClass("gnu.commonlisp.lisp.PrimOps");
        } catch (ClassNotFoundException e2) {
        }
        Lambda lambda = new Lambda();
        lambda.setKeywords(asSymbol("&optional"), asSymbol("&rest"), asSymbol("&key"));
        lambda.defaultDefault = nilExpr;
        defun("lambda", lambda);
        defun("defun", new defun(lambda));
        defun("defvar", new defvar(false));
        defun("defconst", new defvar(true));
        defun("defsubst", new defun(lambda));
        defun("function", new function(lambda));
        defun("setq", new setq());
        defun("prog1", new prog1("prog1", 1));
        defun("prog2", prog1.prog2);
        defun("progn", new begin());
        defun("unwind-protect", new UnwindProtect());
        Object not = new Not(this);
        defun("not", not);
        defun("null", not);
        defun("eq", new IsEq(this, "eq"));
        defun("equal", new IsEqual(this, "equal"));
        defun("typep", new InstanceOf(this));
        defun("princ", displayFormat);
        defun("prin1", writeFormat);
        defProcStFld("=", "gnu.commonlisp.lang.CommonLisp", "numEqu");
        defProcStFld("<", "gnu.commonlisp.lang.CommonLisp", "numLss");
        defProcStFld(">", "gnu.commonlisp.lang.CommonLisp", "numGrt");
        defProcStFld("<=", "gnu.commonlisp.lang.CommonLisp", "numLEq");
        defProcStFld(">=", "gnu.commonlisp.lang.CommonLisp", "numGEq");
        defProcStFld("functionp", "gnu.commonlisp.lisp.PrimOps");
    }

    public static CommonLisp getInstance() {
        return instance;
    }

    public static void registerEnvironment() {
        Language.setDefaults(instance);
    }

    @Override // gnu.expr.Language
    public AbstractFormat getFormat(boolean readable) {
        return readable ? writeFormat : displayFormat;
    }

    @Override // gnu.expr.Language
    public Type getTypeFor(String name) {
        if (name == "t") {
            name = "java.lang.Object";
        }
        return Scheme.string2Type(name);
    }

    @Override // gnu.expr.Language
    public Type getTypeFor(Class clas) {
        if (clas.isPrimitive()) {
            String name = clas.getName();
            if (name.equals(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)) {
                if (this.booleanType == null) {
                    this.booleanType = new LangPrimType(Type.booleanType, this);
                }
                return this.booleanType;
            }
            return Scheme.getNamedType(name);
        }
        return Type.make(clas);
    }
}
