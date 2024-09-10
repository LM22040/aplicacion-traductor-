package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.mapping.OutPort;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: classes.dex */
public class ClassExp extends LambdaExp {
    public static final int CLASS_SPECIFIED = 65536;
    public static final int HAS_SUBCLASS = 131072;
    public static final int INTERFACE_SPECIFIED = 32768;
    public static final int IS_ABSTRACT = 16384;
    public String classNameSpecifier;
    public LambdaExp clinitMethod;
    boolean explicitInit;
    public LambdaExp initMethod;
    ClassType instanceType;
    boolean partsDeclared;
    boolean simple;
    public int superClassIndex = -1;
    public Expression[] supers;

    public boolean isSimple() {
        return this.simple;
    }

    public void setSimple(boolean value) {
        this.simple = value;
    }

    @Override // gnu.expr.LambdaExp
    public final boolean isAbstract() {
        return getFlag(16384);
    }

    public boolean isMakingClassPair() {
        return this.type != this.instanceType;
    }

    @Override // gnu.expr.LambdaExp, gnu.expr.Expression
    public Type getType() {
        return this.simple ? Compilation.typeClass : Compilation.typeClassType;
    }

    @Override // gnu.expr.LambdaExp
    public ClassType getClassType() {
        return this.type;
    }

    public ClassExp() {
    }

    public ClassExp(boolean simple) {
        this.simple = simple;
        ClassType classType = new ClassType();
        this.type = classType;
        this.instanceType = classType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.LambdaExp, gnu.expr.Expression
    public boolean mustCompile() {
        return true;
    }

    @Override // gnu.expr.LambdaExp, gnu.expr.Expression
    public void compile(Compilation comp, Target target) {
        if (target instanceof IgnoreTarget) {
            return;
        }
        compileMembers(comp);
        compilePushClass(comp, target);
    }

    public void compilePushClass(Compilation compilation, Target target) {
        int i;
        ClassType classType;
        ClassType classType2 = this.type;
        CodeAttr code = compilation.getCode();
        compilation.loadClassRef(classType2);
        boolean needsClosureEnv = getNeedsClosureEnv();
        if (isSimple() && !needsClosureEnv) {
            return;
        }
        if (isMakingClassPair() || needsClosureEnv) {
            ClassType classType3 = this.instanceType;
            if (classType2 == classType3) {
                code.emitDup(classType3);
            } else {
                compilation.loadClassRef(classType3);
            }
            ClassType make = ClassType.make("gnu.expr.PairClassType");
            if (needsClosureEnv) {
                i = 3;
                classType = make;
            } else {
                i = 2;
                classType = make;
            }
        } else {
            i = 1;
            classType = ClassType.make("gnu.bytecode.Type");
        }
        Type[] typeArr = new Type[i];
        if (needsClosureEnv) {
            getOwningLambda().loadHeapFrame(compilation);
            i--;
            typeArr[i] = Type.pointer_type;
        }
        Type make2 = ClassType.make("java.lang.Class");
        while (true) {
            i--;
            if (i < 0) {
                code.emitInvokeStatic(classType.addMethod("make", typeArr, classType, 9));
                target.compileFromStack(compilation, classType);
                return;
            }
            typeArr[i] = make2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.LambdaExp
    public ClassType getCompiledClassType(Compilation comp) {
        return this.type;
    }

    public void setTypes(Compilation compilation) {
        String generateClassName;
        int length;
        int i;
        Expression[] expressionArr = this.supers;
        int i2 = 0;
        int length2 = expressionArr == null ? 0 : expressionArr.length;
        ClassType[] classTypeArr = new ClassType[length2];
        ClassType classType = null;
        int i3 = 0;
        for (int i4 = 0; i4 < length2; i4++) {
            Type typeFor = Language.getDefaultLanguage().getTypeFor(this.supers[i4]);
            if (!(typeFor instanceof ClassType)) {
                compilation.setLine(this.supers[i4]);
                compilation.error('e', "invalid super type");
            } else {
                ClassType classType2 = (ClassType) typeFor;
                try {
                    i = classType2.getModifiers();
                } catch (RuntimeException e) {
                    if (compilation != null) {
                        compilation.error('e', "unknown super-type " + classType2.getName());
                    }
                    i = 0;
                }
                if ((i & 512) == 0) {
                    if (i3 < i4) {
                        compilation.error('e', "duplicate superclass for " + this);
                    }
                    this.superClassIndex = i4;
                    classType = classType2;
                } else {
                    classTypeArr[i3] = classType2;
                    i3++;
                }
            }
        }
        if (classType != null && (this.flags & 32768) != 0) {
            compilation.error('e', "cannot be interface since has superclass");
        }
        if (!this.simple && classType == null && (this.flags & 65536) == 0 && (getFlag(131072) || (this.nameDecl != null && this.nameDecl.isPublic()))) {
            PairClassType pairClassType = new PairClassType();
            this.type = pairClassType;
            pairClassType.setInterface(true);
            pairClassType.instanceType = this.instanceType;
            ClassType[] classTypeArr2 = {this.type};
            this.instanceType.setSuper(Type.pointer_type);
            this.instanceType.setInterfaces(classTypeArr2);
        } else if (getFlag(32768)) {
            this.instanceType.setInterface(true);
        }
        ClassType classType3 = this.type;
        if (classType == null) {
            classType = Type.pointer_type;
        }
        classType3.setSuper(classType);
        if (i3 != length2) {
            ClassType[] classTypeArr3 = new ClassType[i3];
            System.arraycopy(classTypeArr, 0, classTypeArr3, 0, i3);
            classTypeArr = classTypeArr3;
        }
        this.type.setInterfaces(classTypeArr);
        if (this.type.getName() == null) {
            String str = this.classNameSpecifier;
            if (str == null && (str = getName()) != null && (length = str.length()) > 2 && str.charAt(0) == '<') {
                int i5 = length - 1;
                if (str.charAt(i5) == '>') {
                    str = str.substring(1, i5);
                }
            }
            if (str == null) {
                StringBuffer stringBuffer = new StringBuffer(100);
                compilation.getModule().classFor(compilation);
                stringBuffer.append(compilation.mainClass.getName());
                stringBuffer.append('$');
                int length3 = stringBuffer.length();
                while (true) {
                    stringBuffer.append(i2);
                    generateClassName = stringBuffer.toString();
                    if (compilation.findNamedClass(generateClassName) == null) {
                        break;
                    }
                    stringBuffer.setLength(length3);
                    i2++;
                }
            } else if (!isSimple() || (this instanceof ObjectExp)) {
                generateClassName = compilation.generateClassName(str);
            } else {
                StringBuffer stringBuffer2 = new StringBuffer(100);
                int i6 = 0;
                while (true) {
                    int indexOf = str.indexOf(46, i6);
                    if (indexOf < 0) {
                        break;
                    }
                    stringBuffer2.append(Compilation.mangleNameIfNeeded(str.substring(i6, indexOf)));
                    i6 = indexOf + 1;
                    if (i6 < str.length()) {
                        stringBuffer2.append('.');
                    }
                }
                if (i6 == 0) {
                    String name = compilation.mainClass != null ? compilation.mainClass.getName() : null;
                    int lastIndexOf = name == null ? -1 : name.lastIndexOf(46);
                    if (lastIndexOf > 0) {
                        stringBuffer2.append(name.substring(0, lastIndexOf + 1));
                    } else if (compilation.classPrefix != null) {
                        stringBuffer2.append(compilation.classPrefix);
                    }
                } else if (i6 == 1 && i6 < str.length()) {
                    stringBuffer2.setLength(0);
                    stringBuffer2.append(compilation.mainClass.getName());
                    stringBuffer2.append('$');
                }
                if (i6 < str.length()) {
                    stringBuffer2.append(Compilation.mangleNameIfNeeded(str.substring(i6)));
                }
                generateClassName = stringBuffer2.toString();
            }
            this.type.setName(generateClassName);
            compilation.addClass(this.type);
            if (isMakingClassPair()) {
                this.instanceType.setName(this.type.getName() + "$class");
                compilation.addClass(this.instanceType);
            }
        }
    }

    public void declareParts(Compilation comp) {
        if (this.partsDeclared) {
            return;
        }
        this.partsDeclared = true;
        Hashtable<String, Declaration> seenFields = new Hashtable<>();
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            if (decl.getCanRead()) {
                int flags = decl.getAccessFlags((short) 1);
                if (decl.getFlag(2048L)) {
                    flags |= 8;
                }
                if (isMakingClassPair()) {
                    int flags2 = flags | 1024;
                    Type ftype = decl.getType().getImplementationType();
                    this.type.addMethod(slotToMethodName("get", decl.getName()), flags2, Type.typeArray0, ftype);
                    Type[] stypes = {ftype};
                    this.type.addMethod(slotToMethodName("set", decl.getName()), flags2, stypes, Type.voidType);
                } else {
                    String fname = Compilation.mangleNameIfNeeded(decl.getName());
                    decl.field = this.instanceType.addField(fname, decl.getType(), flags);
                    decl.setSimple(false);
                    Declaration old = seenFields.get(fname);
                    if (old != null) {
                        duplicateDeclarationError(old, decl, comp);
                    }
                    seenFields.put(fname, decl);
                }
            }
        }
        for (LambdaExp child = this.firstChild; child != null; child = child.nextSibling) {
            if (child.isAbstract()) {
                setFlag(16384);
            }
            if ("*init*".equals(child.getName())) {
                this.explicitInit = true;
                if (child.isAbstract()) {
                    comp.error('e', "*init* method cannot be abstract", child);
                }
                if (this.type instanceof PairClassType) {
                    comp.error('e', "'*init*' methods only supported for simple classes");
                }
            }
            child.outer = this;
            if ((child != this.initMethod && child != this.clinitMethod && child.nameDecl != null && !child.nameDecl.getFlag(2048L)) || !isMakingClassPair()) {
                child.addMethodFor(this.type, comp, null);
            }
            if (isMakingClassPair()) {
                child.addMethodFor(this.instanceType, comp, this.type);
            }
        }
        if (!this.explicitInit && !this.instanceType.isInterface()) {
            Compilation.getConstructor(this.instanceType, this);
        }
        if (isAbstract()) {
            ClassType classType = this.instanceType;
            classType.setModifiers(classType.getModifiers() | 1024);
        }
        if (this.nameDecl != null) {
            ClassType classType2 = this.instanceType;
            classType2.setModifiers(this.nameDecl.getAccessFlags((short) 1) | (classType2.getModifiers() & (-2)));
        }
    }

    static void getImplMethods(ClassType interfaceType, String mname, Type[] paramTypes, Vector vec) {
        ClassType implType;
        if (interfaceType instanceof PairClassType) {
            implType = ((PairClassType) interfaceType).instanceType;
        } else {
            if (!interfaceType.isInterface()) {
                return;
            }
            try {
                Class reflectClass = interfaceType.getReflectClass();
                if (reflectClass == null) {
                    return;
                }
                String implTypeName = interfaceType.getName() + "$class";
                ClassLoader loader = reflectClass.getClassLoader();
                Class implClass = Class.forName(implTypeName, false, loader);
                implType = (ClassType) Type.make(implClass);
            } catch (Throwable th) {
                return;
            }
        }
        Type[] itypes = new Type[paramTypes.length + 1];
        itypes[0] = interfaceType;
        System.arraycopy(paramTypes, 0, itypes, 1, paramTypes.length);
        Method implMethod = implType.getDeclaredMethod(mname, itypes);
        if (implMethod != null) {
            int count = vec.size();
            if (count == 0 || !vec.elementAt(count - 1).equals(implMethod)) {
                vec.addElement(implMethod);
                return;
            }
            return;
        }
        ClassType[] superInterfaces = interfaceType.getInterfaces();
        for (ClassType classType : superInterfaces) {
            getImplMethods(classType, mname, paramTypes, vec);
        }
    }

    private static void usedSuperClasses(ClassType clas, Compilation comp) {
        comp.usedClass(clas.getSuperclass());
        ClassType[] interfaces = clas.getInterfaces();
        if (interfaces != null) {
            int i = interfaces.length;
            while (true) {
                i--;
                if (i >= 0) {
                    comp.usedClass(interfaces[i]);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x016e A[Catch: all -> 0x0379, TryCatch #0 {all -> 0x0379, blocks: (B:3:0x0008, B:5:0x0017, B:7:0x003a, B:9:0x0041, B:10:0x0047, B:12:0x004b, B:13:0x0057, B:15:0x0062, B:16:0x0065, B:18:0x006b, B:19:0x006e, B:21:0x0079, B:25:0x01d2, B:26:0x0087, B:28:0x00a8, B:30:0x00b5, B:32:0x00d2, B:34:0x00da, B:35:0x00f3, B:36:0x00f5, B:38:0x00f9, B:43:0x0104, B:46:0x010e, B:48:0x0115, B:50:0x0120, B:52:0x012e, B:54:0x0138, B:56:0x014a, B:57:0x0166, B:59:0x016e, B:62:0x0179, B:63:0x018d, B:65:0x0194, B:67:0x01a9, B:69:0x01bf, B:71:0x01af, B:76:0x018a, B:83:0x01b3, B:84:0x00b0, B:86:0x01dd, B:88:0x01e7, B:90:0x01ef, B:91:0x0200, B:96:0x0213, B:98:0x0229, B:102:0x0362, B:103:0x0237, B:105:0x023e, B:107:0x0247, B:109:0x0250, B:115:0x026b, B:117:0x0271, B:119:0x0275, B:120:0x0282, B:122:0x02ab, B:123:0x02bc, B:125:0x02d3, B:126:0x02e6, B:128:0x02d9, B:132:0x027e, B:138:0x02fb, B:140:0x030c, B:143:0x0317, B:146:0x032e, B:148:0x0347, B:150:0x0351, B:155:0x036a, B:160:0x0209, B:161:0x01f5, B:163:0x01f9, B:165:0x001d, B:168:0x0023, B:170:0x0027, B:172:0x0035), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0194 A[Catch: all -> 0x0379, TryCatch #0 {all -> 0x0379, blocks: (B:3:0x0008, B:5:0x0017, B:7:0x003a, B:9:0x0041, B:10:0x0047, B:12:0x004b, B:13:0x0057, B:15:0x0062, B:16:0x0065, B:18:0x006b, B:19:0x006e, B:21:0x0079, B:25:0x01d2, B:26:0x0087, B:28:0x00a8, B:30:0x00b5, B:32:0x00d2, B:34:0x00da, B:35:0x00f3, B:36:0x00f5, B:38:0x00f9, B:43:0x0104, B:46:0x010e, B:48:0x0115, B:50:0x0120, B:52:0x012e, B:54:0x0138, B:56:0x014a, B:57:0x0166, B:59:0x016e, B:62:0x0179, B:63:0x018d, B:65:0x0194, B:67:0x01a9, B:69:0x01bf, B:71:0x01af, B:76:0x018a, B:83:0x01b3, B:84:0x00b0, B:86:0x01dd, B:88:0x01e7, B:90:0x01ef, B:91:0x0200, B:96:0x0213, B:98:0x0229, B:102:0x0362, B:103:0x0237, B:105:0x023e, B:107:0x0247, B:109:0x0250, B:115:0x026b, B:117:0x0271, B:119:0x0275, B:120:0x0282, B:122:0x02ab, B:123:0x02bc, B:125:0x02d3, B:126:0x02e6, B:128:0x02d9, B:132:0x027e, B:138:0x02fb, B:140:0x030c, B:143:0x0317, B:146:0x032e, B:148:0x0347, B:150:0x0351, B:155:0x036a, B:160:0x0209, B:161:0x01f5, B:163:0x01f9, B:165:0x001d, B:168:0x0023, B:170:0x0027, B:172:0x0035), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01a9 A[Catch: all -> 0x0379, TryCatch #0 {all -> 0x0379, blocks: (B:3:0x0008, B:5:0x0017, B:7:0x003a, B:9:0x0041, B:10:0x0047, B:12:0x004b, B:13:0x0057, B:15:0x0062, B:16:0x0065, B:18:0x006b, B:19:0x006e, B:21:0x0079, B:25:0x01d2, B:26:0x0087, B:28:0x00a8, B:30:0x00b5, B:32:0x00d2, B:34:0x00da, B:35:0x00f3, B:36:0x00f5, B:38:0x00f9, B:43:0x0104, B:46:0x010e, B:48:0x0115, B:50:0x0120, B:52:0x012e, B:54:0x0138, B:56:0x014a, B:57:0x0166, B:59:0x016e, B:62:0x0179, B:63:0x018d, B:65:0x0194, B:67:0x01a9, B:69:0x01bf, B:71:0x01af, B:76:0x018a, B:83:0x01b3, B:84:0x00b0, B:86:0x01dd, B:88:0x01e7, B:90:0x01ef, B:91:0x0200, B:96:0x0213, B:98:0x0229, B:102:0x0362, B:103:0x0237, B:105:0x023e, B:107:0x0247, B:109:0x0250, B:115:0x026b, B:117:0x0271, B:119:0x0275, B:120:0x0282, B:122:0x02ab, B:123:0x02bc, B:125:0x02d3, B:126:0x02e6, B:128:0x02d9, B:132:0x027e, B:138:0x02fb, B:140:0x030c, B:143:0x0317, B:146:0x032e, B:148:0x0347, B:150:0x0351, B:155:0x036a, B:160:0x0209, B:161:0x01f5, B:163:0x01f9, B:165:0x001d, B:168:0x0023, B:170:0x0027, B:172:0x0035), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01af A[Catch: all -> 0x0379, TryCatch #0 {all -> 0x0379, blocks: (B:3:0x0008, B:5:0x0017, B:7:0x003a, B:9:0x0041, B:10:0x0047, B:12:0x004b, B:13:0x0057, B:15:0x0062, B:16:0x0065, B:18:0x006b, B:19:0x006e, B:21:0x0079, B:25:0x01d2, B:26:0x0087, B:28:0x00a8, B:30:0x00b5, B:32:0x00d2, B:34:0x00da, B:35:0x00f3, B:36:0x00f5, B:38:0x00f9, B:43:0x0104, B:46:0x010e, B:48:0x0115, B:50:0x0120, B:52:0x012e, B:54:0x0138, B:56:0x014a, B:57:0x0166, B:59:0x016e, B:62:0x0179, B:63:0x018d, B:65:0x0194, B:67:0x01a9, B:69:0x01bf, B:71:0x01af, B:76:0x018a, B:83:0x01b3, B:84:0x00b0, B:86:0x01dd, B:88:0x01e7, B:90:0x01ef, B:91:0x0200, B:96:0x0213, B:98:0x0229, B:102:0x0362, B:103:0x0237, B:105:0x023e, B:107:0x0247, B:109:0x0250, B:115:0x026b, B:117:0x0271, B:119:0x0275, B:120:0x0282, B:122:0x02ab, B:123:0x02bc, B:125:0x02d3, B:126:0x02e6, B:128:0x02d9, B:132:0x027e, B:138:0x02fb, B:140:0x030c, B:143:0x0317, B:146:0x032e, B:148:0x0347, B:150:0x0351, B:155:0x036a, B:160:0x0209, B:161:0x01f5, B:163:0x01f9, B:165:0x001d, B:168:0x0023, B:170:0x0027, B:172:0x0035), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0186  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public gnu.bytecode.ClassType compileMembers(gnu.expr.Compilation r29) {
        /*
            Method dump skipped, instructions count: 897
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ClassExp.compileMembers(gnu.expr.Compilation):gnu.bytecode.ClassType");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.LambdaExp, gnu.expr.ScopeExp, gnu.expr.Expression
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        Compilation comp = visitor.getCompilation();
        if (comp == null) {
            return visitor.visitClassExp(this, d);
        }
        ClassType saveClass = comp.curClass;
        try {
            comp.curClass = this.type;
            return visitor.visitClassExp(this, d);
        } finally {
            comp.curClass = saveClass;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.LambdaExp, gnu.expr.Expression
    public <R, D> void visitChildren(ExpVisitor<R, D> visitor, D d) {
        Declaration firstParam;
        LambdaExp save = visitor.currentLambda;
        visitor.currentLambda = this;
        Expression[] expressionArr = this.supers;
        this.supers = visitor.visitExps(expressionArr, expressionArr.length, d);
        try {
            for (LambdaExp child = this.firstChild; child != null; child = child.nextSibling) {
                if (visitor.exitValue != null) {
                    break;
                }
                if (this.instanceType != null && (firstParam = child.firstDecl()) != null && firstParam.isThisParameter()) {
                    firstParam.setType(this.type);
                }
                visitor.visitLambdaExp(child, d);
            }
        } finally {
            visitor.currentLambda = save;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadSuperStaticLink(Expression superExp, ClassType superClass, Compilation comp) {
        CodeAttr code = comp.getCode();
        superExp.compile(comp, Target.pushValue(Compilation.typeClassType));
        code.emitInvokeStatic(ClassType.make("gnu.expr.PairClassType").getDeclaredMethod("extractStaticLink", 1));
        code.emitCheckcast(superClass.getOuterLinkType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeDefaultSuperConstructor(ClassType superClass, Compilation comp, LambdaExp lexp) {
        CodeAttr code = comp.getCode();
        Method superConstructor = superClass.getDeclaredMethod("<init>", 0);
        if (superConstructor == null) {
            comp.error('e', "super class does not have a default constructor");
            return;
        }
        code.emitPushThis();
        if (superClass.hasOuterLink() && (lexp instanceof ClassExp)) {
            ClassExp clExp = (ClassExp) lexp;
            Expression superExp = clExp.supers[clExp.superClassIndex];
            loadSuperStaticLink(superExp, superClass, comp);
        }
        code.emitInvokeSpecial(superConstructor);
    }

    @Override // gnu.expr.LambdaExp, gnu.expr.Expression
    public void print(OutPort out) {
        out.startLogicalBlock("(" + getExpClassName() + "/", ")", 2);
        Object name = getSymbol();
        if (name != null) {
            out.print(name);
            out.print('/');
        }
        out.print(this.id);
        out.print("/fl:");
        out.print(Integer.toHexString(this.flags));
        if (this.supers.length > 0) {
            out.writeSpaceFill();
            out.startLogicalBlock("supers:", "", 2);
            int i = 0;
            while (true) {
                Expression[] expressionArr = this.supers;
                if (i >= expressionArr.length) {
                    break;
                }
                expressionArr[i].print(out);
                out.writeSpaceFill();
                i++;
            }
            out.endLogicalBlock("");
        }
        out.print('(');
        int i2 = 0;
        if (this.keywords != null) {
            int length = this.keywords.length;
        }
        for (Declaration decl = firstDecl(); decl != null; decl = decl.nextDecl()) {
            if (i2 > 0) {
                out.print(' ');
            }
            decl.printInfo(out);
            i2++;
        }
        out.print(") ");
        for (LambdaExp child = this.firstChild; child != null; child = child.nextSibling) {
            out.writeBreakLinear();
            child.print(out);
        }
        if (this.body != null) {
            out.writeBreakLinear();
            this.body.print(out);
        }
        out.endLogicalBlock(")");
    }

    @Override // gnu.expr.LambdaExp
    public Field compileSetField(Compilation comp) {
        return new ClassInitializer(this, comp).field;
    }

    public static String slotToMethodName(String prefix, String sname) {
        if (!Compilation.isValidJavaName(sname)) {
            sname = Compilation.mangleName(sname, false);
        }
        int slen = sname.length();
        StringBuffer sbuf = new StringBuffer(slen + 3);
        sbuf.append(prefix);
        if (slen > 0) {
            sbuf.append(Character.toTitleCase(sname.charAt(0)));
            sbuf.append(sname.substring(1));
        }
        return sbuf.toString();
    }

    public Declaration addMethod(LambdaExp lexp, Object mname) {
        Declaration mdecl = addDeclaration(mname, Compilation.typeProcedure);
        lexp.outer = this;
        lexp.setClassMethod(true);
        mdecl.noteValue(lexp);
        mdecl.setFlag(1048576L);
        mdecl.setProcedureDecl(true);
        lexp.setSymbol(mname);
        return mdecl;
    }
}
