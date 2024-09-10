package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import java.util.Set;
import java.util.Vector;

/* loaded from: classes.dex */
public class LambdaExp extends ScopeExp {
    public static final int ATTEMPT_INLINE = 4096;
    static final int CANNOT_INLINE = 32;
    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CLASS_METHOD = 64;
    static final int DEFAULT_CAPTURES_ARG = 512;
    static final int IMPORTS_LEX_VARS = 8;
    static final int INLINE_ONLY = 8192;
    static final int METHODS_COMPILED = 128;
    static final int NEEDS_STATIC_LINK = 16;
    protected static final int NEXT_AVAIL_FLAG = 16384;
    public static final int NO_FIELD = 256;
    public static final int OVERLOADABLE_FIELD = 2048;
    public static final int SEQUENCE_RESULT = 1024;
    static Method searchForKeywordMethod3;
    static Method searchForKeywordMethod4;
    static final ApplyExp unknownContinuation = new ApplyExp((Expression) null, (Expression[]) null);
    Vector applyMethods;
    Variable argsArray;
    public Expression body;
    Declaration capturedVars;
    Variable closureEnv;
    public Field closureEnvField;
    public Expression[] defaultArgs;
    private Declaration firstArgsArrayArg;
    public LambdaExp firstChild;
    Variable heapFrame;
    Initializer initChain;
    public LambdaExp inlineHome;
    public Keyword[] keywords;
    public int max_args;
    public int min_args;
    public Declaration nameDecl;
    public LambdaExp nextSibling;
    Method[] primBodyMethods;
    Method[] primMethods;
    Object[] properties;
    public Expression returnContinuation;
    public Type returnType;
    int selectorValue;
    public Field staticLinkField;
    Set<LambdaExp> tailCallers;
    Procedure thisValue;
    Variable thisVariable;
    Expression[] throwsSpecification;
    ClassType type = Compilation.typeProcedure;

    public void capture(Declaration decl) {
        if (decl.isSimple()) {
            if (this.capturedVars == null && !decl.isStatic() && !(this instanceof ModuleExp) && !(this instanceof ClassExp)) {
                this.heapFrame = new Variable("heapFrame");
            }
            decl.setSimple(false);
            if (!decl.isPublic()) {
                decl.nextCapturedVar = this.capturedVars;
                this.capturedVars = decl;
            }
        }
    }

    static {
    }

    public void setExceptions(Expression[] exceptions) {
        this.throwsSpecification = exceptions;
    }

    public final boolean getInlineOnly() {
        return (this.flags & 8192) != 0;
    }

    public final void setInlineOnly(boolean inlineOnly) {
        setFlag(inlineOnly, 8192);
    }

    public final boolean getNeedsClosureEnv() {
        return (this.flags & 24) != 0;
    }

    public final boolean getNeedsStaticLink() {
        return (this.flags & 16) != 0;
    }

    public final void setNeedsStaticLink(boolean needsStaticLink) {
        if (!needsStaticLink) {
            this.flags &= -17;
        } else {
            this.flags |= 16;
        }
    }

    public final boolean getImportsLexVars() {
        return (this.flags & 8) != 0;
    }

    public final void setImportsLexVars(boolean importsLexVars) {
        if (!importsLexVars) {
            this.flags &= -9;
        } else {
            this.flags |= 8;
        }
    }

    public final void setImportsLexVars() {
        int old = this.flags;
        this.flags |= 8;
        if ((old & 8) == 0 && this.nameDecl != null) {
            setCallersNeedStaticLink();
        }
    }

    public final void setNeedsStaticLink() {
        int old = this.flags;
        this.flags |= 16;
        if ((old & 16) == 0 && this.nameDecl != null) {
            setCallersNeedStaticLink();
        }
    }

    void setCallersNeedStaticLink() {
        LambdaExp outer = outerLambda();
        for (ApplyExp app = this.nameDecl.firstCall; app != null; app = app.nextCall) {
            for (LambdaExp caller = app.context; caller != outer && !(caller instanceof ModuleExp); caller = caller.outerLambda()) {
                caller.setNeedsStaticLink();
            }
        }
    }

    public final boolean getCanRead() {
        return (this.flags & 2) != 0;
    }

    public final void setCanRead(boolean read) {
        if (!read) {
            this.flags &= -3;
        } else {
            this.flags |= 2;
        }
    }

    public final boolean getCanCall() {
        return (this.flags & 4) != 0;
    }

    public final void setCanCall(boolean called) {
        if (!called) {
            this.flags &= -5;
        } else {
            this.flags |= 4;
        }
    }

    public final boolean isClassMethod() {
        return (this.flags & 64) != 0;
    }

    public final void setClassMethod(boolean isMethod) {
        if (!isMethod) {
            this.flags &= -65;
        } else {
            this.flags |= 64;
        }
    }

    public final boolean isModuleBody() {
        return this instanceof ModuleExp;
    }

    public final boolean isClassGenerated() {
        return isModuleBody() || (this instanceof ClassExp);
    }

    public boolean isAbstract() {
        return this.body == QuoteExp.abstractExp;
    }

    public int getCallConvention() {
        if (isModuleBody()) {
            if (Compilation.defaultCallConvention >= 2) {
                return Compilation.defaultCallConvention;
            }
            return 2;
        }
        if (isClassMethod() || Compilation.defaultCallConvention == 0) {
            return 1;
        }
        return Compilation.defaultCallConvention;
    }

    public final boolean isHandlingTailCalls() {
        return isModuleBody() || (Compilation.defaultCallConvention >= 3 && !isClassMethod());
    }

    public final boolean variable_args() {
        return this.max_args < 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClassType getCompiledClassType(Compilation comp) {
        if (this.type == Compilation.typeProcedure) {
            throw new Error("internal error: getCompiledClassType");
        }
        return this.type;
    }

    @Override // gnu.expr.Expression
    public Type getType() {
        return this.type;
    }

    public ClassType getClassType() {
        return this.type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }

    public int incomingArgs() {
        int i = this.min_args;
        int i2 = this.max_args;
        if (i != i2 || i2 > 4 || i2 <= 0) {
            return 1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSelectorValue(Compilation comp) {
        int s = this.selectorValue;
        if (s == 0) {
            int s2 = comp.maxSelectorValue;
            comp.maxSelectorValue = this.primMethods.length + s2;
            int s3 = s2 + 1;
            this.selectorValue = s3;
            return s3;
        }
        return s;
    }

    public final Method getMethod(int argCount) {
        int i;
        int index;
        Method[] methodArr = this.primMethods;
        if (methodArr == null || (((i = this.max_args) >= 0 && argCount > i) || (index = argCount - this.min_args) < 0)) {
            return null;
        }
        int length = methodArr.length;
        return methodArr[index < length ? index : length - 1];
    }

    public final Method getMainMethod() {
        Method[] methods = this.primBodyMethods;
        if (methods == null) {
            return null;
        }
        return methods[methods.length - 1];
    }

    public final Type restArgType() {
        int i = this.min_args;
        int i2 = this.max_args;
        if (i == i2) {
            return null;
        }
        if (this.primMethods == null) {
            throw new Error("internal error - restArgType");
        }
        Method[] methods = this.primMethods;
        if (i2 >= 0 && methods.length > i2 - i) {
            return null;
        }
        Method method = methods[methods.length - 1];
        Type[] types = method.getParameterTypes();
        int ilast = types.length - 1;
        if (method.getName().endsWith("$X")) {
            ilast--;
        }
        return types[ilast];
    }

    public LambdaExp outerLambda() {
        if (this.outer == null) {
            return null;
        }
        return this.outer.currentLambda();
    }

    public LambdaExp outerLambdaNotInline() {
        ScopeExp exp = this;
        while (true) {
            ScopeExp scopeExp = exp.outer;
            exp = scopeExp;
            if (scopeExp != null) {
                if (exp instanceof LambdaExp) {
                    LambdaExp result = (LambdaExp) exp;
                    if (!result.getInlineOnly()) {
                        return result;
                    }
                }
            } else {
                return null;
            }
        }
    }

    boolean inlinedIn(LambdaExp outer) {
        for (LambdaExp exp = this; exp.getInlineOnly(); exp = exp.getCaller()) {
            if (exp == outer) {
                return true;
            }
        }
        return false;
    }

    public LambdaExp getCaller() {
        return this.inlineHome;
    }

    public Variable declareThis(ClassType clas) {
        if (this.thisVariable == null) {
            this.thisVariable = new Variable("this");
            getVarScope().addVariableAfter(null, this.thisVariable);
            this.thisVariable.setParameter(true);
        }
        if (this.thisVariable.getType() == null) {
            this.thisVariable.setType(clas);
        }
        if (this.decls != null && this.decls.isThisParameter()) {
            this.decls.var = this.thisVariable;
        }
        return this.thisVariable;
    }

    public Variable declareClosureEnv() {
        Variable prev;
        if (this.closureEnv == null && getNeedsClosureEnv()) {
            LambdaExp parent = outerLambda();
            if (parent instanceof ClassExp) {
                parent = parent.outerLambda();
            }
            Variable parentFrame = parent.heapFrame;
            if (parentFrame == null) {
                parentFrame = parent.closureEnv;
            }
            if (isClassMethod() && !"*init*".equals(getName())) {
                this.closureEnv = declareThis(this.type);
            } else if (parent.heapFrame == null && !parent.getNeedsStaticLink() && !(parent instanceof ModuleExp)) {
                this.closureEnv = null;
            } else if (!isClassGenerated() && !getInlineOnly()) {
                Method primMethod = getMainMethod();
                boolean isInit = "*init*".equals(getName());
                if (!primMethod.getStaticFlag() && !isInit) {
                    this.closureEnv = declareThis(primMethod.getDeclaringClass());
                } else {
                    Type envType = primMethod.getParameterTypes()[0];
                    this.closureEnv = new Variable("closureEnv", envType);
                    if (isInit) {
                        prev = declareThis(primMethod.getDeclaringClass());
                    } else {
                        prev = null;
                    }
                    getVarScope().addVariableAfter(prev, this.closureEnv);
                    this.closureEnv.setParameter(true);
                }
            } else if (inlinedIn(parent)) {
                this.closureEnv = parentFrame;
            } else {
                this.closureEnv = new Variable("closureEnv", parentFrame.getType());
                getVarScope().addVariable(this.closureEnv);
            }
        }
        return this.closureEnv;
    }

    public LambdaExp() {
    }

    public LambdaExp(int args) {
        this.min_args = args;
        this.max_args = args;
    }

    public LambdaExp(Expression body) {
        this.body = body;
    }

    public void loadHeapFrame(Compilation comp) {
        ClassType curType;
        LambdaExp curLambda = comp.curLambda;
        while (curLambda != this && curLambda.getInlineOnly()) {
            curLambda = curLambda.getCaller();
        }
        CodeAttr code = comp.getCode();
        Variable variable = curLambda.heapFrame;
        if (variable != null && this == curLambda) {
            code.emitLoad(variable);
            return;
        }
        Variable variable2 = curLambda.closureEnv;
        if (variable2 != null) {
            code.emitLoad(variable2);
            curType = (ClassType) curLambda.closureEnv.getType();
        } else {
            code.emitPushThis();
            curType = comp.curClass;
        }
        while (curLambda != this) {
            Field link = curLambda.staticLinkField;
            if (link != null && link.getDeclaringClass() == curType) {
                code.emitGetField(link);
                curType = (ClassType) link.getType();
            }
            curLambda = curLambda.outerLambda();
        }
    }

    Declaration getArg(int i) {
        for (Declaration var = firstDecl(); var != null; var = var.nextDecl()) {
            if (i == 0) {
                return var;
            }
            i--;
        }
        throw new Error("internal error - getArg");
    }

    public void compileEnd(Compilation comp) {
        CodeAttr code = comp.getCode();
        if (!getInlineOnly()) {
            if (comp.method.reachableHere() && (Compilation.defaultCallConvention < 3 || isModuleBody() || isClassMethod() || isHandlingTailCalls())) {
                code.emitReturn();
            }
            popScope(code);
            code.popScope();
        }
        for (LambdaExp child = this.firstChild; child != null; child = child.nextSibling) {
            if (!child.getCanRead() && !child.getInlineOnly()) {
                child.compileAsMethod(comp);
            }
        }
        if (this.heapFrame != null) {
            comp.generateConstructor(this);
        }
    }

    public void generateApplyMethods(Compilation comp) {
        comp.generateMatchMethods(this);
        if (Compilation.defaultCallConvention >= 2) {
            comp.generateApplyMethodsWithContext(this);
        } else {
            comp.generateApplyMethodsWithoutContext(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Field allocFieldFor(Compilation comp) {
        Declaration declaration = this.nameDecl;
        if (declaration != null && declaration.field != null) {
            return this.nameDecl.field;
        }
        boolean needsClosure = getNeedsClosureEnv();
        ClassType frameType = needsClosure ? getOwningLambda().getHeapFrameType() : comp.mainClass;
        String name = getName();
        String fname = name == null ? "lambda" : Compilation.mangleNameIfNeeded(name);
        int fflags = 16;
        Declaration declaration2 = this.nameDecl;
        if (declaration2 != null && (declaration2.context instanceof ModuleExp)) {
            boolean external_access = this.nameDecl.needsExternalAccess();
            if (external_access) {
                fname = Declaration.PRIVATE_PREFIX + fname;
            }
            if (this.nameDecl.getFlag(2048L)) {
                fflags = 16 | 8;
                if (!((ModuleExp) this.nameDecl.context).isStatic()) {
                    fflags &= -17;
                }
            }
            if (!this.nameDecl.isPrivate() || external_access || comp.immediate) {
                fflags |= 1;
            }
            if ((this.flags & 2048) != 0) {
                String fname0 = fname;
                int i = this.min_args;
                int suffix = i == this.max_args ? i : 1;
                while (true) {
                    int suffix2 = suffix + 1;
                    fname = fname0 + '$' + suffix;
                    if (frameType.getDeclaredField(fname) == null) {
                        break;
                    }
                    suffix = suffix2;
                }
            }
        } else {
            StringBuilder append = new StringBuilder().append(fname).append("$Fn");
            int i2 = comp.localFieldIndex + 1;
            comp.localFieldIndex = i2;
            fname = append.append(i2).toString();
            if (!needsClosure) {
                fflags = 16 | 8;
            }
        }
        Type rtype = Compilation.typeModuleMethod;
        Field field = frameType.addField(fname, rtype, fflags);
        Declaration declaration3 = this.nameDecl;
        if (declaration3 != null) {
            declaration3.field = field;
        }
        return field;
    }

    final void addApplyMethod(Compilation comp, Field field) {
        LambdaExp owner = this;
        if (field != null && field.getStaticFlag()) {
            owner = comp.getModule();
        } else {
            do {
                owner = owner.outerLambda();
                if (owner instanceof ModuleExp) {
                    break;
                }
            } while (owner.heapFrame == null);
            ClassType frameType = owner.getHeapFrameType();
            if (!frameType.getSuperclass().isSubtype(Compilation.typeModuleBody)) {
                owner = comp.getModule();
            }
        }
        if (owner.applyMethods == null) {
            owner.applyMethods = new Vector();
        }
        owner.applyMethods.addElement(this);
    }

    public Field compileSetField(Compilation comp) {
        if (this.primMethods == null) {
            allocMethod(outerLambda(), comp);
        }
        Field field = allocFieldFor(comp);
        if (comp.usingCPStyle()) {
            compile(comp, Type.objectType);
        } else {
            compileAsMethod(comp);
            addApplyMethod(comp, field);
        }
        return new ProcInitializer(this, comp, field).field;
    }

    @Override // gnu.expr.Expression
    public void compile(Compilation comp, Target target) {
        if (target instanceof IgnoreTarget) {
            return;
        }
        CodeAttr code = comp.getCode();
        LambdaExp outer = outerLambda();
        Type rtype = Compilation.typeModuleMethod;
        if ((this.flags & 256) != 0 || (comp.immediate && (outer instanceof ModuleExp))) {
            if (this.primMethods == null) {
                allocMethod(outerLambda(), comp);
            }
            compileAsMethod(comp);
            addApplyMethod(comp, null);
            ProcInitializer.emitLoadModuleMethod(this, comp);
        } else {
            Field field = compileSetField(comp);
            if (field.getStaticFlag()) {
                code.emitGetStatic(field);
            } else {
                LambdaExp parent = comp.curLambda;
                Variable frame = parent.heapFrame;
                if (frame == null) {
                    frame = parent.closureEnv;
                }
                code.emitLoad(frame);
                code.emitGetField(field);
            }
        }
        target.compileFromStack(comp, rtype);
    }

    public ClassType getHeapFrameType() {
        if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
            return (ClassType) getType();
        }
        return (ClassType) this.heapFrame.getType();
    }

    public LambdaExp getOwningLambda() {
        for (ScopeExp exp = this.outer; exp != null; exp = exp.outer) {
            if ((exp instanceof ModuleExp) || (((exp instanceof ClassExp) && getNeedsClosureEnv()) || ((exp instanceof LambdaExp) && ((LambdaExp) exp).heapFrame != null))) {
                return (LambdaExp) exp;
            }
        }
        return null;
    }

    void addMethodFor(Compilation comp, ObjectType closureEnvType) {
        ClassType ctype;
        ScopeExp sc = this;
        while (sc != null && !(sc instanceof ClassExp)) {
            sc = sc.outer;
        }
        if (sc != null) {
            ctype = ((ClassExp) sc).instanceType;
        } else {
            ctype = getOwningLambda().getHeapFrameType();
        }
        addMethodFor(ctype, comp, closureEnvType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x022b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0238 A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r17v11, types: [short] */
    /* JADX WARN: Type inference failed for: r8v31, types: [gnu.expr.Declaration] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addMethodFor(gnu.bytecode.ClassType r39, gnu.expr.Compilation r40, gnu.bytecode.ObjectType r41) {
        /*
            Method dump skipped, instructions count: 1181
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.LambdaExp.addMethodFor(gnu.bytecode.ClassType, gnu.expr.Compilation, gnu.bytecode.ObjectType):void");
    }

    public void allocChildClasses(Compilation comp) {
        Declaration declaration;
        Method main = getMainMethod();
        if (main != null && !main.getStaticFlag()) {
            declareThis(main.getDeclaringClass());
        }
        Declaration decl = firstDecl();
        while (true) {
            if (decl == this.firstArgsArrayArg && this.argsArray != null) {
                getVarScope().addVariable(this.argsArray);
            }
            if (!getInlineOnly() && getCallConvention() >= 2 && ((declaration = this.firstArgsArrayArg) != null ? !(this.argsArray == null ? decl != declaration.nextDecl() : decl != declaration) : decl == null)) {
                getVarScope().addVariable(null, Compilation.typeCallContext, "$ctx").setParameter(true);
            }
            if (decl != null) {
                if (decl.var == null && (!getInlineOnly() || !decl.ignorable())) {
                    if (decl.isSimple() && !decl.isIndirectBinding()) {
                        decl.allocateVariable(null);
                    } else {
                        String vname = Compilation.mangleName(decl.getName()).intern();
                        Type vtype = decl.getType().getImplementationType();
                        Variable var = getVarScope().addVariable(null, vtype, vname);
                        decl.var = var;
                        var.setParameter(true);
                    }
                }
                decl = decl.nextDecl();
            } else {
                declareClosureEnv();
                allocFrame(comp);
                allocChildMethods(comp);
                return;
            }
        }
    }

    void allocMethod(LambdaExp outer, Compilation comp) {
        ObjectType closureEnvType;
        Variable variable;
        if (!getNeedsClosureEnv()) {
            closureEnvType = null;
        } else if ((outer instanceof ClassExp) || (outer instanceof ModuleExp)) {
            closureEnvType = outer.getCompiledClassType(comp);
        } else {
            LambdaExp owner = outer;
            while (true) {
                variable = owner.heapFrame;
                if (variable != null) {
                    break;
                } else {
                    owner = owner.outerLambda();
                }
            }
            closureEnvType = (ClassType) variable.getType();
        }
        addMethodFor(comp, closureEnvType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void allocChildMethods(Compilation comp) {
        ClassType parentFrameType;
        for (LambdaExp child = this.firstChild; child != null; child = child.nextSibling) {
            if (!child.isClassGenerated() && !child.getInlineOnly() && child.nameDecl != null) {
                child.allocMethod(this, comp);
            }
            if (child instanceof ClassExp) {
                ClassExp cl = (ClassExp) child;
                if (cl.getNeedsClosureEnv()) {
                    if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
                        parentFrameType = (ClassType) getType();
                    } else {
                        Variable parentFrame = this.heapFrame;
                        if (parentFrame == null) {
                            parentFrame = this.closureEnv;
                        }
                        parentFrameType = (ClassType) parentFrame.getType();
                    }
                    Field outerLink = cl.instanceType.setOuterLink(parentFrameType);
                    cl.staticLinkField = outerLink;
                    cl.closureEnvField = outerLink;
                }
            }
        }
    }

    public void allocFrame(Compilation comp) {
        ClassType frameType;
        if (this.heapFrame != null) {
            if ((this instanceof ModuleExp) || (this instanceof ClassExp)) {
                frameType = getCompiledClassType(comp);
            } else {
                frameType = new ClassType(comp.generateClassName("frame"));
                frameType.setSuper(comp.getModuleType());
                comp.addClass(frameType);
            }
            this.heapFrame.setType(frameType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void allocParameters(Compilation comp) {
        CodeAttr code = comp.getCode();
        code.locals.enterScope(getVarScope());
        int line = getLineNumber();
        if (line > 0) {
            code.putLineNumber(getFileName(), line);
        }
        Variable variable = this.heapFrame;
        if (variable != null) {
            variable.allocateLocal(code);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x029d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void enterFunction(gnu.expr.Compilation r26) {
        /*
            Method dump skipped, instructions count: 721
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.LambdaExp.enterFunction(gnu.expr.Compilation):void");
    }

    void compileAsMethod(Compilation comp) {
        Method save_method;
        LambdaExp save_lambda;
        Method method;
        int numStubs;
        Expression arg;
        if ((this.flags & 128) == 0 && !isAbstract()) {
            this.flags |= 128;
            if (this.primMethods == null) {
                return;
            }
            Method save_method2 = comp.method;
            LambdaExp save_lambda2 = comp.curLambda;
            comp.curLambda = this;
            int i = 0;
            Method method2 = this.primMethods[0];
            boolean isStatic = method2.getStaticFlag();
            int numStubs2 = this.primMethods.length - 1;
            Type restArgType = restArgType();
            long[] saveDeclFlags = null;
            if (numStubs2 > 0) {
                saveDeclFlags = new long[this.min_args + numStubs2];
                Declaration decl = firstDecl();
                for (int k = 0; k < this.min_args + numStubs2; k++) {
                    saveDeclFlags[k] = decl.flags;
                    decl = decl.nextDecl();
                }
            }
            boolean ctxArg = getCallConvention() >= 2;
            int i2 = 0;
            while (i2 <= numStubs2) {
                comp.method = this.primMethods[i2];
                if (i2 < numStubs2) {
                    CodeAttr code = comp.method.startCode();
                    int toCall = i2 + 1;
                    while (toCall < numStubs2 && (this.defaultArgs[toCall] instanceof QuoteExp)) {
                        toCall++;
                    }
                    boolean varArgs = toCall == numStubs2 && restArgType != null;
                    Variable callContextSave = comp.callContextVar;
                    method = method2;
                    Variable var = code.getArg(i);
                    if (!isStatic) {
                        code.emitPushThis();
                        if (getNeedsClosureEnv()) {
                            this.closureEnv = var;
                        }
                        var = code.getArg(1);
                    }
                    Declaration decl2 = firstDecl();
                    Declaration decl3 = decl2;
                    save_lambda = save_lambda2;
                    int j = 0;
                    while (true) {
                        save_method = save_method2;
                        if (j >= this.min_args + i2) {
                            break;
                        }
                        decl3.flags |= 64;
                        decl3.var = var;
                        code.emitLoad(var);
                        var = var.nextVar();
                        j++;
                        decl3 = decl3.nextDecl();
                        numStubs2 = numStubs2;
                        save_method2 = save_method;
                        callContextSave = callContextSave;
                    }
                    numStubs = numStubs2;
                    Variable callContextSave2 = callContextSave;
                    comp.callContextVar = ctxArg ? var : null;
                    int j2 = i2;
                    while (j2 < toCall) {
                        Target paramTarget = StackTarget.getInstance(decl3.getType());
                        this.defaultArgs[j2].compile(comp, paramTarget);
                        j2++;
                        decl3 = decl3.nextDecl();
                    }
                    if (varArgs) {
                        String lastTypeName = restArgType.getName();
                        if ("gnu.lists.LList".equals(lastTypeName)) {
                            arg = new QuoteExp(LList.Empty);
                        } else if ("java.lang.Object[]".equals(lastTypeName)) {
                            arg = new QuoteExp(Values.noArgs);
                        } else {
                            throw new Error("unimplemented #!rest type " + lastTypeName);
                        }
                        arg.compile(comp, restArgType);
                    }
                    if (ctxArg) {
                        code.emitLoad(var);
                    }
                    if (isStatic) {
                        code.emitInvokeStatic(this.primMethods[toCall]);
                    } else {
                        code.emitInvokeVirtual(this.primMethods[toCall]);
                    }
                    code.emitReturn();
                    this.closureEnv = null;
                    comp.callContextVar = callContextSave2;
                } else {
                    save_method = save_method2;
                    save_lambda = save_lambda2;
                    method = method2;
                    numStubs = numStubs2;
                    if (saveDeclFlags != null) {
                        Declaration decl4 = firstDecl();
                        for (int k2 = 0; k2 < this.min_args + numStubs; k2++) {
                            decl4.flags = saveDeclFlags[k2];
                            decl4.var = null;
                            decl4 = decl4.nextDecl();
                        }
                    }
                    comp.method.initCode();
                    allocChildClasses(comp);
                    allocParameters(comp);
                    enterFunction(comp);
                    compileBody(comp);
                    compileEnd(comp);
                    generateApplyMethods(comp);
                }
                i2++;
                numStubs2 = numStubs;
                method2 = method;
                save_lambda2 = save_lambda;
                save_method2 = save_method;
                i = 0;
            }
            comp.method = save_method2;
            comp.curLambda = save_lambda2;
        }
    }

    public void compileBody(Compilation comp) {
        Target target;
        Variable callContextSave = comp.callContextVar;
        comp.callContextVar = null;
        if (getCallConvention() >= 2) {
            Variable var = getVarScope().lookup("$ctx");
            if (var != null && var.getType() == Compilation.typeCallContext) {
                comp.callContextVar = var;
            }
            target = ConsumerTarget.makeContextTarget(comp);
        } else {
            target = Target.pushValue(getReturnType());
        }
        Expression expression = this.body;
        expression.compileWithPosition(comp, target, expression.getLineNumber() > 0 ? this.body : this);
        comp.callContextVar = callContextSave;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.ScopeExp, gnu.expr.Expression
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        LambdaExp saveLambda;
        Compilation comp = visitor.getCompilation();
        if (comp == null) {
            saveLambda = null;
        } else {
            saveLambda = comp.curLambda;
            comp.curLambda = this;
        }
        try {
            return visitor.visitLambdaExp(this, d);
        } finally {
            if (comp != null) {
                comp.curLambda = saveLambda;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.Expression
    public <R, D> void visitChildren(ExpVisitor<R, D> visitor, D d) {
        visitChildrenOnly(visitor, d);
        visitProperties(visitor, d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <R, D> void visitChildrenOnly(ExpVisitor<R, D> visitor, D d) {
        Expression expression;
        LambdaExp save = visitor.currentLambda;
        visitor.currentLambda = this;
        try {
            this.throwsSpecification = visitor.visitExps(this.throwsSpecification, d);
            visitor.visitDefaultArgs(this, d);
            if (visitor.exitValue == null && (expression = this.body) != null) {
                this.body = visitor.update(expression, visitor.visit(expression, d));
            }
        } finally {
            visitor.currentLambda = save;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <R, D> void visitProperties(ExpVisitor<R, D> visitor, D d) {
        Object[] objArr = this.properties;
        if (objArr != null) {
            int len = objArr.length;
            for (int i = 1; i < len; i += 2) {
                Object[] objArr2 = this.properties;
                Object val = objArr2[i];
                if (val instanceof Expression) {
                    objArr2[i] = visitor.visitAndUpdate((Expression) val, d);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.Expression
    public boolean mustCompile() {
        Keyword[] keywordArr = this.keywords;
        if (keywordArr != null && keywordArr.length > 0) {
            return true;
        }
        Expression[] expressionArr = this.defaultArgs;
        if (expressionArr != null) {
            int i = expressionArr.length;
            while (true) {
                i--;
                if (i >= 0) {
                    Expression def = this.defaultArgs[i];
                    if (def != null && !(def instanceof QuoteExp)) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override // gnu.expr.Expression, gnu.mapping.Procedure
    public void apply(CallContext ctx) throws Throwable {
        setIndexes();
        ctx.writeValue(new Closure(this, ctx));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object evalDefaultArg(int index, CallContext ctx) {
        try {
            return this.defaultArgs[index].eval(ctx);
        } catch (Throwable ex) {
            throw new WrappedException("error evaluating default argument", ex);
        }
    }

    @Override // gnu.expr.Expression
    public Expression validateApply(ApplyExp exp, InlineCalls visitor, Type required, Declaration decl) {
        Method method;
        Expression[] margs;
        Expression inlined;
        LambdaExp lambdaExp = this;
        Expression[] args = exp.getArgs();
        if ((lambdaExp.flags & 4096) != 0 && (inlined = InlineCalls.inlineCall(lambdaExp, args, true)) != null) {
            return visitor.visit(inlined, required);
        }
        exp.visitArgs(visitor);
        int args_length = exp.args.length;
        String msg = WrongArguments.checkArgCount(getName(), lambdaExp.min_args, lambdaExp.max_args, args_length);
        if (msg != null) {
            return visitor.noteError(msg);
        }
        int conv = getCallConvention();
        Compilation comp = visitor.getCompilation();
        if (comp.inlineOk((Expression) lambdaExp) && isClassMethod()) {
            if ((conv <= 2 || conv == 3) && (method = lambdaExp.getMethod(args_length)) != null) {
                boolean isStatic = lambdaExp.nameDecl.isStatic();
                if (!isStatic && (lambdaExp.outer instanceof ClassExp)) {
                    ClassExp cl = (ClassExp) lambdaExp.outer;
                    cl.isMakingClassPair();
                }
                PrimProcedure mproc = new PrimProcedure(method, lambdaExp);
                if (isStatic) {
                    margs = exp.args;
                } else {
                    LambdaExp curLambda = visitor.getCurrentLambda();
                    while (curLambda != null) {
                        if (curLambda.outer == lambdaExp.outer) {
                            Declaration d = curLambda.firstDecl();
                            if (d != null && d.isThisParameter()) {
                                int nargs = exp.getArgCount();
                                margs = new Expression[nargs + 1];
                                System.arraycopy(exp.getArgs(), 0, margs, 1, nargs);
                                margs[0] = new ThisExp(d);
                            }
                            return visitor.noteError("calling non-static method " + getName() + " from static method " + curLambda.getName());
                        }
                        curLambda = curLambda.outerLambda();
                        lambdaExp = this;
                        args_length = args_length;
                    }
                    return visitor.noteError("internal error: missing " + lambdaExp);
                }
                ApplyExp nexp = new ApplyExp(mproc, margs);
                return nexp.setLine(exp);
            }
        }
        return exp;
    }

    @Override // gnu.expr.Expression
    public void print(OutPort out) {
        Special mode;
        out.startLogicalBlock("(Lambda/", ")", 2);
        Object sym = getSymbol();
        if (sym != null) {
            out.print(sym);
            out.print('/');
        }
        out.print(this.id);
        out.print('/');
        out.print("fl:");
        out.print(Integer.toHexString(this.flags));
        out.writeSpaceFill();
        printLineColumn(out);
        out.startLogicalBlock("(", false, ")");
        Special prevMode = null;
        int i = 0;
        int opt_i = 0;
        Keyword[] keywordArr = this.keywords;
        int key_args = keywordArr == null ? 0 : keywordArr.length;
        Expression[] expressionArr = this.defaultArgs;
        int opt_args = expressionArr != null ? expressionArr.length - key_args : 0;
        Declaration decl = firstDecl();
        if (decl != null && decl.isThisParameter()) {
            i = -1;
        }
        while (decl != null) {
            int i2 = this.min_args;
            if (i < i2) {
                mode = null;
            } else if (i < i2 + opt_args) {
                mode = Special.optional;
            } else if (this.max_args < 0 && i == i2 + opt_args) {
                mode = Special.rest;
            } else {
                mode = Special.key;
            }
            if (decl != firstDecl()) {
                out.writeSpaceFill();
            }
            if (mode != prevMode) {
                out.print(mode);
                out.writeSpaceFill();
            }
            Expression defaultArg = null;
            if (mode == Special.optional || mode == Special.key) {
                defaultArg = this.defaultArgs[opt_i];
                opt_i++;
            }
            if (defaultArg != null) {
                out.print('(');
            }
            decl.printInfo(out);
            if (defaultArg != null && defaultArg != QuoteExp.falseExp) {
                out.print(' ');
                defaultArg.print(out);
                out.print(')');
            }
            i++;
            prevMode = mode;
            decl = decl.nextDecl();
        }
        out.endLogicalBlock(")");
        out.writeSpaceLinear();
        Expression expression = this.body;
        if (expression == null) {
            out.print("<null body>");
        } else {
            expression.print(out);
        }
        out.endLogicalBlock(")");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getExpClassName() {
        String cname = getClass().getName();
        int index = cname.lastIndexOf(46);
        if (index >= 0) {
            return cname.substring(index + 1);
        }
        return cname;
    }

    @Override // gnu.expr.Expression
    public boolean side_effects() {
        return false;
    }

    @Override // gnu.expr.ScopeExp, gnu.expr.Expression, gnu.mapping.Procedure
    public String toString() {
        Expression expression;
        String str = getExpClassName() + ':' + getSymbol() + '/' + this.id + '/';
        int l = getLineNumber();
        if (l <= 0 && (expression = this.body) != null) {
            l = expression.getLineNumber();
        }
        if (l > 0) {
            return str + "l:" + l;
        }
        return str;
    }

    @Override // gnu.mapping.PropertySet
    public Object getProperty(Object key, Object defaultValue) {
        Object[] objArr;
        Object[] objArr2 = this.properties;
        if (objArr2 != null) {
            int i = objArr2.length;
            do {
                i -= 2;
                if (i >= 0) {
                    objArr = this.properties;
                }
            } while (objArr[i] != key);
            return objArr[i + 1];
        }
        return defaultValue;
    }

    @Override // gnu.mapping.PropertySet
    public synchronized void setProperty(Object key, Object value) {
        this.properties = PropertySet.setProperty(this.properties, key, value);
    }

    public final Type getReturnType() {
        if (this.returnType == null) {
            this.returnType = Type.objectType;
            if (this.body != null && !isAbstract()) {
                this.returnType = this.body.getType();
            }
        }
        return this.returnType;
    }

    public final void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public final void setCoercedReturnType(Type returnType) {
        this.returnType = returnType;
        if (returnType != null && returnType != Type.objectType && returnType != Type.voidType && this.body != QuoteExp.abstractExp) {
            Expression value = this.body;
            Expression makeCoercion = Compilation.makeCoercion(value, returnType);
            this.body = makeCoercion;
            makeCoercion.setLine(value);
        }
    }

    public final void setCoercedReturnValue(Expression type, Language language) {
        if (!isAbstract()) {
            Expression value = this.body;
            ApplyExp makeCoercion = Compilation.makeCoercion(value, type);
            this.body = makeCoercion;
            makeCoercion.setLine(value);
        }
        Type rtype = language.getTypeFor(type);
        if (rtype != null) {
            setReturnType(rtype);
        }
    }
}
