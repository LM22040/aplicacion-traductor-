package gnu.expr;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.lists.FString;
import gnu.mapping.Symbol;
import gnu.mapping.Table2D;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.IdentityHashMap;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class LitTable implements ObjectOutput {
    static Table2D staticTable = new Table2D(100);
    Compilation comp;
    Literal literalsChain;
    int literalsCount;
    ClassType mainClass;
    int stackPointer;
    IdentityHashMap literalTable = new IdentityHashMap(100);
    Object[] valueStack = new Object[20];
    Type[] typeStack = new Type[20];

    public LitTable(Compilation comp) {
        this.comp = comp;
        this.mainClass = comp.mainClass;
    }

    public void emit() throws IOException {
        for (Literal init = this.literalsChain; init != null; init = init.next) {
            writeObject(init.value);
        }
        for (Literal init2 = this.literalsChain; init2 != null; init2 = init2.next) {
            emit(init2, true);
        }
        this.literalTable = null;
        this.literalsCount = 0;
    }

    void push(Object value, Type type) {
        int i = this.stackPointer;
        Object[] objArr = this.valueStack;
        if (i >= objArr.length) {
            Object[] newValues = new Object[objArr.length * 2];
            Type[] newTypes = new Type[this.typeStack.length * 2];
            System.arraycopy(objArr, 0, newValues, 0, i);
            System.arraycopy(this.typeStack, 0, newTypes, 0, this.stackPointer);
            this.valueStack = newValues;
            this.typeStack = newTypes;
        }
        Object[] objArr2 = this.valueStack;
        int i2 = this.stackPointer;
        objArr2[i2] = value;
        this.typeStack[i2] = type;
        this.stackPointer = i2 + 1;
    }

    void error(String msg) {
        throw new Error(msg);
    }

    @Override // java.io.ObjectOutput
    public void flush() {
    }

    @Override // java.io.ObjectOutput, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.ObjectOutput, java.io.DataOutput
    public void write(int b) throws IOException {
        error("cannot handle call to write(int) when externalizing literal");
    }

    @Override // java.io.DataOutput
    public void writeBytes(String s) throws IOException {
        error("cannot handle call to writeBytes(String) when externalizing literal");
    }

    @Override // java.io.ObjectOutput, java.io.DataOutput
    public void write(byte[] b) throws IOException {
        error("cannot handle call to write(byte[]) when externalizing literal");
    }

    @Override // java.io.ObjectOutput, java.io.DataOutput
    public void write(byte[] b, int off, int len) throws IOException {
        error("cannot handle call to write(byte[],int,int) when externalizing literal");
    }

    @Override // java.io.DataOutput
    public void writeBoolean(boolean v) {
        push(new Boolean(v), Type.booleanType);
    }

    @Override // java.io.DataOutput
    public void writeChar(int v) {
        push(new Character((char) v), Type.charType);
    }

    @Override // java.io.DataOutput
    public void writeByte(int v) {
        push(new Byte((byte) v), Type.byteType);
    }

    @Override // java.io.DataOutput
    public void writeShort(int v) {
        push(new Short((short) v), Type.shortType);
    }

    @Override // java.io.DataOutput
    public void writeInt(int v) {
        push(new Integer(v), Type.intType);
    }

    @Override // java.io.DataOutput
    public void writeLong(long v) {
        push(new Long(v), Type.longType);
    }

    @Override // java.io.DataOutput
    public void writeFloat(float v) {
        push(new Float(v), Type.floatType);
    }

    @Override // java.io.DataOutput
    public void writeDouble(double v) {
        push(new Double(v), Type.doubleType);
    }

    @Override // java.io.DataOutput
    public void writeUTF(String v) {
        push(v, Type.string_type);
    }

    @Override // java.io.DataOutput
    public void writeChars(String v) {
        push(v, Type.string_type);
    }

    @Override // java.io.ObjectOutput
    public void writeObject(Object obj) throws IOException {
        Literal lit = findLiteral(obj);
        if ((lit.flags & 3) != 0) {
            if (lit.field == null && obj != null && !(obj instanceof String)) {
                lit.assign(this);
            }
            if ((lit.flags & 2) == 0) {
                lit.flags |= 4;
            }
        } else {
            lit.flags |= 1;
            int oldStack = this.stackPointer;
            if ((obj instanceof FString) && ((FString) obj).size() < 65535) {
                push(obj.toString(), Type.string_type);
            } else if (obj instanceof Externalizable) {
                ((Externalizable) obj).writeExternal(this);
            } else if (obj instanceof Object[]) {
                Object[] arr = (Object[]) obj;
                for (Object obj2 : arr) {
                    writeObject(obj2);
                }
            } else if (obj != null && !(obj instanceof String) && !(lit.type instanceof ArrayType)) {
                if (obj instanceof BigInteger) {
                    writeChars(obj.toString());
                } else if (obj instanceof BigDecimal) {
                    BigDecimal dec = (BigDecimal) obj;
                    writeObject(dec.unscaledValue());
                    writeInt(dec.scale());
                } else if (obj instanceof Integer) {
                    push(obj, Type.intType);
                } else if (obj instanceof Short) {
                    push(obj, Type.shortType);
                } else if (obj instanceof Byte) {
                    push(obj, Type.byteType);
                } else if (obj instanceof Long) {
                    push(obj, Type.longType);
                } else if (obj instanceof Double) {
                    push(obj, Type.doubleType);
                } else if (obj instanceof Float) {
                    push(obj, Type.floatType);
                } else if (obj instanceof Character) {
                    push(obj, Type.charType);
                } else if (obj instanceof Class) {
                    push(obj, Type.java_lang_Class_type);
                } else if (obj instanceof Pattern) {
                    Pattern pat = (Pattern) obj;
                    push(pat.pattern(), Type.string_type);
                    push(Integer.valueOf(pat.flags()), Type.intType);
                } else {
                    error(obj.getClass().getName() + " does not implement Externalizable");
                }
            }
            int nargs = this.stackPointer - oldStack;
            if (nargs == 0) {
                lit.argValues = Values.noArgs;
                lit.argTypes = Type.typeArray0;
            } else {
                lit.argValues = new Object[nargs];
                lit.argTypes = new Type[nargs];
                System.arraycopy(this.valueStack, oldStack, lit.argValues, 0, nargs);
                System.arraycopy(this.typeStack, oldStack, lit.argTypes, 0, nargs);
                this.stackPointer = oldStack;
            }
            lit.flags |= 2;
        }
        push(lit, lit.type);
    }

    public Literal findLiteral(Object value) {
        Literal literal;
        if (value == null) {
            return Literal.nullLiteral;
        }
        Literal literal2 = (Literal) this.literalTable.get(value);
        if (literal2 != null) {
            return literal2;
        }
        if (this.comp.immediate) {
            return new Literal(value, this);
        }
        Class valueClass = value.getClass();
        Type valueType = Type.make(valueClass);
        synchronized (staticTable) {
            literal = (Literal) staticTable.get(value, null, null);
            if ((literal == null || literal.value != value) && (valueType instanceof ClassType)) {
                Class fldClass = valueClass;
                ClassType fldType = (ClassType) valueType;
                while (staticTable.get(fldClass, Boolean.TRUE, null) == null) {
                    staticTable.put(fldClass, Boolean.TRUE, fldClass);
                    for (Field fld = fldType.getFields(); fld != null; fld = fld.getNext()) {
                        if ((fld.getModifiers() & 25) == 25) {
                            try {
                                java.lang.reflect.Field rfld = fld.getReflectField();
                                Object litValue = rfld.get(null);
                                if (litValue != null && fldClass.isInstance(litValue)) {
                                    Literal lit = new Literal(litValue, fld, this);
                                    staticTable.put(litValue, null, lit);
                                    if (value == litValue) {
                                        literal = lit;
                                    }
                                }
                            } catch (Throwable ex) {
                                error("caught " + ex + " getting static field " + fld);
                            }
                        }
                    }
                    fldClass = fldClass.getSuperclass();
                    if (fldClass == null) {
                        break;
                    }
                    fldType = (ClassType) Type.make(fldClass);
                }
            }
        }
        if (literal != null) {
            this.literalTable.put(value, literal);
            return literal;
        }
        return new Literal(value, valueType, this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x015d, code lost:
    
        r26 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    gnu.bytecode.Method getMethod(gnu.bytecode.ClassType r30, java.lang.String r31, gnu.expr.Literal r32, boolean r33) {
        /*
            Method dump skipped, instructions count: 603
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.LitTable.getMethod(gnu.bytecode.ClassType, java.lang.String, gnu.expr.Literal, boolean):gnu.bytecode.Method");
    }

    void putArgs(Literal literal, CodeAttr code) {
        Type[] argTypes = literal.argTypes;
        int len = argTypes.length;
        for (int i = 0; i < len; i++) {
            Object value = literal.argValues[i];
            if (value instanceof Literal) {
                emit((Literal) value, false);
            } else {
                this.comp.compileConstant(value, new StackTarget(argTypes[i]));
            }
        }
    }

    private void store(Literal literal, boolean ignore, CodeAttr code) {
        if (literal.field != null) {
            if (!ignore) {
                code.emitDup(literal.type);
            }
            code.emitPutStatic(literal.field);
        }
        literal.flags |= 8;
    }

    void emit(Literal literal, boolean ignore) {
        CodeAttr code = this.comp.getCode();
        if (literal.value == null) {
            if (!ignore) {
                code.emitPushNull();
                return;
            }
            return;
        }
        if (literal.value instanceof String) {
            if (!ignore) {
                code.emitPushString(literal.value.toString());
                return;
            }
            return;
        }
        if ((literal.flags & 8) != 0) {
            if (!ignore) {
                code.emitGetStatic(literal.field);
                return;
            }
            return;
        }
        boolean z = false;
        if (literal.value instanceof Object[]) {
            int len = literal.argValues.length;
            Type elementType = ((ArrayType) literal.type).getComponentType();
            code.emitPushInt(len);
            code.emitNewArray(elementType);
            store(literal, ignore, code);
            for (int i = 0; i < len; i++) {
                Literal el = (Literal) literal.argValues[i];
                if (el.value != null) {
                    code.emitDup(elementType);
                    code.emitPushInt(i);
                    emit(el, false);
                    code.emitArrayStore(elementType);
                }
            }
            return;
        }
        if (literal.type instanceof ArrayType) {
            code.emitPushPrimArray(literal.value, (ArrayType) literal.type);
            store(literal, ignore, code);
            return;
        }
        if (literal.value instanceof Class) {
            Class clas = (Class) literal.value;
            if (clas.isPrimitive()) {
                String cname = clas.getName();
                if (cname.equals("int")) {
                    cname = PropertyTypeConstants.PROPERTY_TYPE_INTEGER;
                }
                code.emitGetStatic(ClassType.make("java.lang." + Character.toUpperCase(cname.charAt(0)) + cname.substring(1)).getDeclaredField("TYPE"));
            } else {
                this.comp.loadClassRef((ObjectType) Type.make(clas));
            }
            store(literal, ignore, code);
            return;
        }
        if ((literal.value instanceof ClassType) && !((ClassType) literal.value).isExisting()) {
            this.comp.loadClassRef((ClassType) literal.value);
            Method meth = Compilation.typeType.getDeclaredMethod("valueOf", 1);
            if (meth == null) {
                meth = Compilation.typeType.getDeclaredMethod("make", 1);
            }
            code.emitInvokeStatic(meth);
            code.emitCheckcast(Compilation.typeClassType);
            store(literal, ignore, code);
            return;
        }
        ClassType type = (ClassType) literal.type;
        boolean useDefaultInit = (literal.flags & 4) != 0;
        Method method = null;
        boolean makeStatic = false;
        if (!useDefaultInit) {
            if (!(literal.value instanceof Symbol)) {
                method = getMethod(type, "valueOf", literal, true);
            }
            if (method == null && !(literal.value instanceof Values)) {
                String mname = "make";
                if (literal.value instanceof Pattern) {
                    mname = "compile";
                }
                method = getMethod(type, mname, literal, true);
            }
            if (method != null) {
                makeStatic = true;
            } else if (literal.argTypes.length > 0) {
                method = getMethod(type, "<init>", literal, false);
            }
            if (method == null) {
                useDefaultInit = true;
            }
        }
        if (useDefaultInit) {
            method = getMethod(type, "set", literal, false);
        }
        if (method == null && literal.argTypes.length > 0) {
            error("no method to construct " + literal.type);
        }
        if (makeStatic) {
            putArgs(literal, code);
            code.emitInvokeStatic(method);
        } else if (useDefaultInit) {
            code.emitNew(type);
            code.emitDup(type);
            Method init0 = type.getDeclaredMethod("<init>", 0);
            code.emitInvokeSpecial(init0);
        } else {
            code.emitNew(type);
            code.emitDup(type);
            putArgs(literal, code);
            code.emitInvokeSpecial(method);
        }
        Method resolveMethod = (makeStatic || (literal.value instanceof Values)) ? null : type.getDeclaredMethod("readResolve", 0);
        if (resolveMethod != null) {
            code.emitInvokeVirtual(resolveMethod);
            type.emitCoerceFromObject(code);
        }
        if (ignore && (!useDefaultInit || method == null)) {
            z = true;
        }
        store(literal, z, code);
        if (useDefaultInit && method != null) {
            if (!ignore) {
                code.emitDup(type);
            }
            putArgs(literal, code);
            code.emitInvokeVirtual(method);
        }
    }
}
