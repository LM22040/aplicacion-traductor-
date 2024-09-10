package gnu.expr;

import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.SourceLocator;

/* loaded from: classes.dex */
public class Declaration implements SourceLocator {
    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CAN_WRITE = 8;
    public static final long CLASS_ACCESS_FLAGS = 25820135424L;
    public static final int EARLY_INIT = 536870912;
    public static final long ENUM_ACCESS = 8589934592L;
    public static final int EXPORT_SPECIFIED = 1024;
    public static final int EXTERNAL_ACCESS = 524288;
    public static final long FIELD_ACCESS_FLAGS = 32463912960L;
    public static final int FIELD_OR_METHOD = 1048576;
    public static final long FINAL_ACCESS = 17179869184L;
    static final int INDIRECT_BINDING = 1;
    public static final int IS_ALIAS = 256;
    public static final int IS_CONSTANT = 16384;
    public static final int IS_DYNAMIC = 268435456;
    static final int IS_FLUID = 16;
    public static final int IS_IMPORTED = 131072;
    public static final int IS_NAMESPACE_PREFIX = 2097152;
    static final int IS_SIMPLE = 64;
    public static final int IS_SINGLE_VALUE = 262144;
    public static final int IS_SYNTAX = 32768;
    public static final int IS_UNKNOWN = 65536;
    public static final long METHOD_ACCESS_FLAGS = 17431527424L;
    public static final int MODULE_REFERENCE = 1073741824;
    public static final int NONSTATIC_SPECIFIED = 4096;
    public static final int NOT_DEFINING = 512;
    public static final int PACKAGE_ACCESS = 134217728;
    static final int PRIVATE = 32;
    public static final int PRIVATE_ACCESS = 16777216;
    public static final String PRIVATE_PREFIX = "$Prvt$";
    public static final int PRIVATE_SPECIFIED = 16777216;
    static final int PROCEDURE = 128;
    public static final int PROTECTED_ACCESS = 33554432;
    public static final int PUBLIC_ACCESS = 67108864;
    public static final int STATIC_SPECIFIED = 2048;
    public static final long TRANSIENT_ACCESS = 4294967296L;
    public static final int TYPE_SPECIFIED = 8192;
    static final String UNKNOWN_PREFIX = "loc$";
    public static final long VOLATILE_ACCESS = 2147483648L;
    static int counter;
    public Declaration base;
    public ScopeExp context;
    int evalIndex;
    public Field field;
    String filename;
    public ApplyExp firstCall;
    protected long flags;
    protected int id;
    Method makeLocationMethod;
    Declaration next;
    Declaration nextCapturedVar;
    int position;
    Object symbol;
    protected Type type;
    protected Expression typeExp;
    protected Expression value;
    Variable var;

    public void setCode(int code) {
        if (code >= 0) {
            throw new Error("code must be negative");
        }
        this.id = code;
    }

    public int getCode() {
        return this.id;
    }

    public final Expression getTypeExp() {
        if (this.typeExp == null) {
            setType(Type.objectType);
        }
        return this.typeExp;
    }

    public final Type getType() {
        if (this.type == null) {
            setType(Type.objectType);
        }
        return this.type;
    }

    public final void setType(Type type) {
        this.type = type;
        Variable variable = this.var;
        if (variable != null) {
            variable.setType(type);
        }
        this.typeExp = QuoteExp.getInstance(type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setTypeExp(Expression expression) {
        Type t;
        this.typeExp = expression;
        if (expression instanceof TypeValue) {
            t = ((TypeValue) expression).getImplementationType();
        } else {
            t = Language.getDefaultLanguage().getTypeFor(expression, false);
        }
        if (t == null) {
            t = Type.pointer_type;
        }
        this.type = t;
        Variable variable = this.var;
        if (variable != null) {
            variable.setType(t);
        }
    }

    public final String getName() {
        Object obj = this.symbol;
        if (obj == null) {
            return null;
        }
        return obj instanceof Symbol ? ((Symbol) obj).getName() : obj.toString();
    }

    public final void setName(Object symbol) {
        this.symbol = symbol;
    }

    public final Object getSymbol() {
        return this.symbol;
    }

    public final void setSymbol(Object symbol) {
        this.symbol = symbol;
    }

    public final Declaration nextDecl() {
        return this.next;
    }

    public final void setNext(Declaration next) {
        this.next = next;
    }

    public Variable getVariable() {
        return this.var;
    }

    public final boolean isSimple() {
        return (this.flags & 64) != 0;
    }

    public final void setSimple(boolean b) {
        setFlag(b, 64L);
        Variable variable = this.var;
        if (variable == null || variable.isParameter()) {
            return;
        }
        this.var.setSimple(b);
    }

    public final void setSyntax() {
        setSimple(false);
        setFlag(536920064L);
    }

    public final ScopeExp getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadOwningObject(Declaration owner, Compilation comp) {
        if (owner == null) {
            owner = this.base;
        }
        if (owner != null) {
            owner.load(null, 0, comp, Target.pushObject);
        } else {
            getContext().currentLambda().loadHeapFrame(comp);
        }
    }

    public void load(AccessExp access, int flags, Compilation comp, Target target) {
        Object val;
        String filename;
        int line;
        int fragment_cookie;
        Method meth;
        ClassType ltype;
        ReferenceExp rexp;
        Declaration orig;
        if (target instanceof IgnoreTarget) {
            return;
        }
        Declaration owner = access == null ? null : access.contextDecl();
        if (isAlias()) {
            Expression expression = this.value;
            if ((expression instanceof ReferenceExp) && (orig = (rexp = (ReferenceExp) expression).binding) != null && (((flags & 2) == 0 || orig.isIndirectBinding()) && (owner == null || !orig.needsContext()))) {
                orig.load(rexp, flags, comp, target);
                return;
            }
        }
        if (isFluid() && (this.context instanceof FluidLetExp)) {
            this.base.load(access, flags, comp, target);
            return;
        }
        CodeAttr code = comp.getCode();
        Type rtype = getType();
        if (!isIndirectBinding() && (flags & 2) != 0) {
            if (this.field == null) {
                throw new Error("internal error: cannot take location of " + this);
            }
            boolean immediate = comp.immediate;
            if (this.field.getStaticFlag()) {
                ltype = ClassType.make("gnu.kawa.reflect.StaticFieldLocation");
                meth = ltype.getDeclaredMethod("make", immediate ? 1 : 2);
            } else {
                ClassType ltype2 = ClassType.make("gnu.kawa.reflect.FieldLocation");
                Method meth2 = ltype2.getDeclaredMethod("make", immediate ? 2 : 3);
                loadOwningObject(owner, comp);
                meth = meth2;
                ltype = ltype2;
            }
            if (immediate) {
                comp.compileConstant(this);
            } else {
                comp.compileConstant(this.field.getDeclaringClass().getName());
                comp.compileConstant(this.field.getName());
            }
            code.emitInvokeStatic(meth);
            rtype = ltype;
        } else {
            Field field = this.field;
            if (field != null) {
                comp.usedClass(field.getDeclaringClass());
                comp.usedClass(this.field.getType());
                if (!this.field.getStaticFlag()) {
                    loadOwningObject(owner, comp);
                    code.emitGetField(this.field);
                } else {
                    code.emitGetStatic(this.field);
                }
            } else if (isIndirectBinding() && comp.immediate && getVariable() == null) {
                Environment env = Environment.getCurrent();
                Object obj = this.symbol;
                Symbol sym = obj instanceof Symbol ? (Symbol) obj : env.getSymbol(obj.toString());
                Object property = null;
                if (isProcedureDecl() && comp.getLanguage().hasSeparateFunctionNamespace()) {
                    property = EnvironmentKey.FUNCTION;
                }
                Location loc = env.getLocation(sym, property);
                comp.compileConstant(loc, Target.pushValue(Compilation.typeLocation));
            } else {
                if (comp.immediate && (val = getConstantValue()) != null) {
                    comp.compileConstant(val, target);
                    return;
                }
                if (this.value != QuoteExp.undefined_exp && ignorable()) {
                    Expression expression2 = this.value;
                    if (!(expression2 instanceof LambdaExp) || !(((LambdaExp) expression2).outer instanceof ModuleExp)) {
                        this.value.compile(comp, target);
                        return;
                    }
                }
                Variable var = getVariable();
                if ((this.context instanceof ClassExp) && var == null && !getFlag(128L)) {
                    ClassExp cl = (ClassExp) this.context;
                    if (cl.isMakingClassPair()) {
                        String getName = ClassExp.slotToMethodName("get", getName());
                        Method getter = cl.type.getDeclaredMethod(getName, 0);
                        cl.loadHeapFrame(comp);
                        code.emitInvoke(getter);
                    }
                }
                if (var == null) {
                    var = allocateVariable(code);
                }
                code.emitLoad(var);
            }
            if (isIndirectBinding() && (flags & 2) == 0) {
                if (access != null && (filename = access.getFileName()) != null && (line = access.getLineNumber()) > 0) {
                    ClassType typeUnboundLocationException = ClassType.make("gnu.mapping.UnboundLocationException");
                    boolean isInTry = code.isInTry();
                    int column = access.getColumnNumber();
                    Label startTry = new Label(code);
                    startTry.define(code);
                    code.emitInvokeVirtual(Compilation.getLocationMethod);
                    Label endTry = new Label(code);
                    endTry.define(code);
                    Label endLabel = new Label(code);
                    endLabel.setTypes(code);
                    if (isInTry) {
                        code.emitGoto(endLabel);
                    } else {
                        code.setUnreachable();
                    }
                    if (isInTry) {
                        fragment_cookie = 0;
                    } else {
                        int fragment_cookie2 = code.beginFragment(endLabel);
                        fragment_cookie = fragment_cookie2;
                    }
                    code.addHandler(startTry, endTry, typeUnboundLocationException);
                    code.emitDup(typeUnboundLocationException);
                    code.emitPushString(filename);
                    code.emitPushInt(line);
                    code.emitPushInt(column);
                    code.emitInvokeVirtual(typeUnboundLocationException.getDeclaredMethod("setLine", 3));
                    code.emitThrow();
                    if (isInTry) {
                        endLabel.define(code);
                    } else {
                        code.endFragment(fragment_cookie);
                    }
                } else {
                    code.emitInvokeVirtual(Compilation.getLocationMethod);
                }
                rtype = Type.pointer_type;
            }
        }
        target.compileFromStack(comp, rtype);
    }

    public void compileStore(Compilation comp) {
        CodeAttr code = comp.getCode();
        if (isSimple()) {
            code.emitStore(getVariable());
        } else {
            if (!this.field.getStaticFlag()) {
                loadOwningObject(null, comp);
                code.emitSwap();
                code.emitPutField(this.field);
                return;
            }
            code.emitPutStatic(this.field);
        }
    }

    public final Expression getValue() {
        if (this.value == QuoteExp.undefined_exp) {
            Field field = this.field;
            if (field != null && (field.getModifiers() & 24) == 24 && !isIndirectBinding()) {
                try {
                    this.value = new QuoteExp(this.field.getReflectField().get(null));
                } catch (Throwable th) {
                }
            }
        } else if ((this.value instanceof QuoteExp) && getFlag(8192L) && this.value.getType() != this.type) {
            try {
                Object val = ((QuoteExp) this.value).getValue();
                Type t = getType();
                this.value = new QuoteExp(t.coerceFromObject(val), t);
            } catch (Throwable th2) {
            }
        }
        return this.value;
    }

    public final void setValue(Expression value) {
        this.value = value;
    }

    public final Object getConstantValue() {
        Object v = getValue();
        if (!(v instanceof QuoteExp) || v == QuoteExp.undefined_exp) {
            return null;
        }
        return ((QuoteExp) v).getValue();
    }

    public final boolean hasConstantValue() {
        Object v = getValue();
        return (v instanceof QuoteExp) && v != QuoteExp.undefined_exp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldEarlyInit() {
        return getFlag(536870912L) || isCompiletimeConstant();
    }

    public boolean isCompiletimeConstant() {
        return getFlag(16384L) && hasConstantValue();
    }

    public final boolean needsExternalAccess() {
        long j = this.flags;
        return (j & 524320) == 524320 || (j & 2097184) == 2097184;
    }

    public final boolean needsContext() {
        Field field;
        return (this.base != null || (field = this.field) == null || field.getStaticFlag()) ? false : true;
    }

    public final boolean getFlag(long flag) {
        return (this.flags & flag) != 0;
    }

    public final void setFlag(boolean setting, long flag) {
        if (!setting) {
            this.flags &= (-1) ^ flag;
        } else {
            this.flags |= flag;
        }
    }

    public final void setFlag(long flag) {
        this.flags |= flag;
    }

    public final boolean isPublic() {
        return (this.context instanceof ModuleExp) && (this.flags & 32) == 0;
    }

    public final boolean isPrivate() {
        return (this.flags & 32) != 0;
    }

    public final void setPrivate(boolean isPrivate) {
        setFlag(isPrivate, 32L);
    }

    public short getAccessFlags(short defaultFlags) {
        short flags;
        if (getFlag(251658240L)) {
            flags = 0;
            if (getFlag(16777216L)) {
                flags = (short) (0 | 2);
            }
            if (getFlag(33554432L)) {
                flags = (short) (flags | 4);
            }
            if (getFlag(67108864L)) {
                flags = (short) (flags | 1);
            }
        } else {
            flags = defaultFlags;
        }
        if (getFlag(VOLATILE_ACCESS)) {
            flags = (short) (flags | 64);
        }
        if (getFlag(TRANSIENT_ACCESS)) {
            flags = (short) (flags | 128);
        }
        if (getFlag(ENUM_ACCESS)) {
            flags = (short) (flags | Access.ENUM);
        }
        if (getFlag(FINAL_ACCESS)) {
            return (short) (flags | 16);
        }
        return flags;
    }

    public final boolean isAlias() {
        return (this.flags & 256) != 0;
    }

    public final void setAlias(boolean flag) {
        setFlag(flag, 256L);
    }

    public final boolean isFluid() {
        return (this.flags & 16) != 0;
    }

    public final void setFluid(boolean fluid) {
        setFlag(fluid, 16L);
    }

    public final boolean isProcedureDecl() {
        return (this.flags & 128) != 0;
    }

    public final void setProcedureDecl(boolean val) {
        setFlag(val, 128L);
    }

    public final boolean isNamespaceDecl() {
        return (this.flags & 2097152) != 0;
    }

    public final boolean isIndirectBinding() {
        return (this.flags & 1) != 0;
    }

    public final void setIndirectBinding(boolean indirectBinding) {
        setFlag(indirectBinding, 1L);
    }

    public void maybeIndirectBinding(Compilation comp) {
        if ((isLexical() && !(this.context instanceof ModuleExp)) || this.context == comp.mainLambda) {
            setIndirectBinding(true);
        }
    }

    public final boolean getCanRead() {
        return (this.flags & 2) != 0;
    }

    public final void setCanRead(boolean read) {
        setFlag(read, 2L);
    }

    public final void setCanRead() {
        setFlag(true, 2L);
        Declaration declaration = this.base;
        if (declaration != null) {
            declaration.setCanRead();
        }
    }

    public final boolean getCanCall() {
        return (this.flags & 4) != 0;
    }

    public final void setCanCall(boolean called) {
        setFlag(called, 4L);
    }

    public final void setCanCall() {
        setFlag(true, 4L);
        Declaration declaration = this.base;
        if (declaration != null) {
            declaration.setCanRead();
        }
    }

    public final boolean getCanWrite() {
        return (this.flags & 8) != 0;
    }

    public final void setCanWrite(boolean written) {
        if (!written) {
            this.flags &= -9;
        } else {
            this.flags |= 8;
        }
    }

    public final void setCanWrite() {
        this.flags |= 8;
        Declaration declaration = this.base;
        if (declaration != null) {
            declaration.setCanRead();
        }
    }

    public final boolean isThisParameter() {
        return this.symbol == ThisExp.THIS_NAME;
    }

    public boolean ignorable() {
        if (getCanRead() || isPublic()) {
            return false;
        }
        if (getCanWrite() && getFlag(65536L)) {
            return false;
        }
        if (!getCanCall()) {
            return true;
        }
        Expression value = getValue();
        if (value == null || !(value instanceof LambdaExp)) {
            return false;
        }
        LambdaExp lexp = (LambdaExp) value;
        return !lexp.isHandlingTailCalls() || lexp.getInlineOnly();
    }

    public boolean needsInit() {
        return !ignorable() && (this.value != QuoteExp.nullExp || this.base == null);
    }

    public boolean isStatic() {
        Field field = this.field;
        if (field != null) {
            return field.getStaticFlag();
        }
        if (getFlag(2048L) || isCompiletimeConstant()) {
            return true;
        }
        if (getFlag(4096L)) {
            return false;
        }
        LambdaExp lambda = this.context.currentLambda();
        return (lambda instanceof ModuleExp) && ((ModuleExp) lambda).isStatic();
    }

    public final boolean isLexical() {
        return (this.flags & 268501008) == 0;
    }

    public static final boolean isUnknown(Declaration decl) {
        return decl == null || decl.getFlag(65536L);
    }

    public void noteValue(Expression value) {
        if (this.value == QuoteExp.undefined_exp) {
            if (value instanceof LambdaExp) {
                ((LambdaExp) value).nameDecl = this;
            }
            this.value = value;
        } else {
            Expression expression = this.value;
            if (expression != value) {
                if (expression instanceof LambdaExp) {
                    ((LambdaExp) expression).nameDecl = null;
                }
                this.value = null;
            }
        }
    }

    protected Declaration() {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64L;
        this.makeLocationMethod = null;
    }

    public Declaration(Variable var) {
        this(var.getName(), var.getType());
        this.var = var;
    }

    public Declaration(Object name) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64L;
        this.makeLocationMethod = null;
        setName(name);
    }

    public Declaration(Object name, Type type) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.value = QuoteExp.undefined_exp;
        this.flags = 64L;
        this.makeLocationMethod = null;
        setName(name);
        setType(type);
    }

    public Declaration(Object name, Field field) {
        this(name, field.getType());
        this.field = field;
        setSimple(false);
    }

    public void pushIndirectBinding(Compilation comp) {
        CodeAttr code = comp.getCode();
        code.emitPushString(getName());
        if (this.makeLocationMethod == null) {
            Type[] args = {Type.pointer_type, Type.string_type};
            this.makeLocationMethod = Compilation.typeLocation.addMethod("make", args, Compilation.typeLocation, 9);
        }
        code.emitInvokeStatic(this.makeLocationMethod);
    }

    public final Variable allocateVariable(CodeAttr code) {
        if (!isSimple() || this.var == null) {
            String vname = null;
            if (this.symbol != null) {
                vname = Compilation.mangleNameIfNeeded(getName());
            }
            if (isAlias() && (getValue() instanceof ReferenceExp)) {
                Declaration base = followAliases(this);
                this.var = base == null ? null : base.var;
            } else {
                Type type = isIndirectBinding() ? Compilation.typeLocation : getType().getImplementationType();
                this.var = this.context.getVarScope().addVariable(code, type, vname);
            }
        }
        return this.var;
    }

    public final void setLocation(SourceLocator location) {
        this.filename = location.getFileName();
        setLine(location.getLineNumber(), location.getColumnNumber());
    }

    public final void setFile(String filename) {
        this.filename = filename;
    }

    public final void setLine(int lineno, int colno) {
        if (lineno < 0) {
            lineno = 0;
        }
        if (colno < 0) {
            colno = 0;
        }
        this.position = (lineno << 12) + colno;
    }

    public final void setLine(int lineno) {
        setLine(lineno, 0);
    }

    @Override // gnu.text.SourceLocator
    public final String getFileName() {
        return this.filename;
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public String getPublicId() {
        return null;
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public String getSystemId() {
        return this.filename;
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public final int getLineNumber() {
        int line = this.position >> 12;
        if (line == 0) {
            return -1;
        }
        return line;
    }

    @Override // gnu.text.SourceLocator, org.xml.sax.Locator
    public final int getColumnNumber() {
        int column = this.position & 4095;
        if (column == 0) {
            return -1;
        }
        return column;
    }

    @Override // gnu.text.SourceLocator
    public boolean isStableSourceLocation() {
        return true;
    }

    public void printInfo(OutPort out) {
        StringBuffer sbuf = new StringBuffer();
        printInfo(sbuf);
        out.print(sbuf.toString());
    }

    public void printInfo(StringBuffer sbuf) {
        sbuf.append(this.symbol);
        sbuf.append('/');
        sbuf.append(this.id);
        sbuf.append("/fl:");
        sbuf.append(Long.toHexString(this.flags));
        if (ignorable()) {
            sbuf.append("(ignorable)");
        }
        Expression tx = this.typeExp;
        Type t = getType();
        if (tx != null && !(tx instanceof QuoteExp)) {
            sbuf.append("::");
            sbuf.append(tx);
        } else if (this.type != null && t != Type.pointer_type) {
            sbuf.append("::");
            sbuf.append(t.getName());
        }
        if (this.base != null) {
            sbuf.append("(base:#");
            sbuf.append(this.base.id);
            sbuf.append(')');
        }
    }

    public String toString() {
        return "Declaration[" + this.symbol + '/' + this.id + ']';
    }

    public static Declaration followAliases(Declaration decl) {
        while (decl != null && decl.isAlias()) {
            Expression declValue = decl.getValue();
            if (!(declValue instanceof ReferenceExp)) {
                break;
            }
            ReferenceExp rexp = (ReferenceExp) declValue;
            Declaration orig = rexp.binding;
            if (orig == null) {
                break;
            }
            decl = orig;
        }
        return decl;
    }

    public void makeField(Compilation comp, Expression value) {
        setSimple(false);
        makeField(comp.mainClass, comp, value);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0075, code lost:
    
        if (((gnu.expr.ModuleExp) r4).staticInitRun() != false) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void makeField(gnu.bytecode.ClassType r13, gnu.expr.Compilation r14, gnu.expr.Expression r15) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Declaration.makeField(gnu.bytecode.ClassType, gnu.expr.Compilation, gnu.expr.Expression):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Location makeIndirectLocationFor() {
        Object obj = this.symbol;
        Symbol sym = obj instanceof Symbol ? (Symbol) obj : Namespace.EmptyNamespace.getSymbol(this.symbol.toString().intern());
        return Location.make(sym);
    }

    public static Declaration getDeclarationFromStatic(String cname, String fname) {
        ClassType clas = ClassType.make(cname);
        Field fld = clas.getDeclaredField(fname);
        Declaration decl = new Declaration(fname, fld);
        decl.setFlag(18432L);
        return decl;
    }

    public static Declaration getDeclarationValueFromStatic(String className, String fieldName, String name) {
        try {
            Class cls = Class.forName(className);
            java.lang.reflect.Field fld = cls.getDeclaredField(fieldName);
            Object value = fld.get(null);
            Declaration decl = new Declaration(name, ClassType.make(className).getDeclaredField(fieldName));
            decl.noteValue(new QuoteExp(value));
            decl.setFlag(18432L);
            return decl;
        } catch (Exception ex) {
            throw new WrappedException(ex);
        }
    }

    public static Declaration getDeclaration(Named proc) {
        return getDeclaration(proc, proc.getName());
    }

    public static Declaration getDeclaration(Object proc, String name) {
        Class procClass;
        Field procField = null;
        if (name != null && (procClass = PrimProcedure.getProcedureClass(proc)) != null) {
            ClassType procType = (ClassType) Type.make(procClass);
            String fname = Compilation.mangleNameIfNeeded(name);
            procField = procType.getDeclaredField(fname);
        }
        if (procField != null) {
            int fflags = procField.getModifiers();
            if ((fflags & 8) != 0) {
                Declaration decl = new Declaration(name, procField);
                decl.noteValue(new QuoteExp(proc));
                if ((fflags & 16) != 0) {
                    decl.setFlag(16384L);
                }
                return decl;
            }
            return null;
        }
        return null;
    }
}
