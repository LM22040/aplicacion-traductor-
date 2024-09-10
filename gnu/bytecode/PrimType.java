package gnu.bytecode;

/* loaded from: classes.dex */
public class PrimType extends Type {
    private static final String numberHierarchy = "A:java.lang.Byte;B:java.lang.Short;C:java.lang.Integer;D:java.lang.Long;E:gnu.math.IntNum;E:java.gnu.math.BitInteger;G:gnu.math.RatNum;H:java.lang.Float;I:java.lang.Double;I:gnu.math.DFloNum;J:gnu.math.RealNum;K:gnu.math.Complex;L:gnu.math.Quantity;K:gnu.math.Numeric;N:java.lang.Number;";

    public PrimType(String nam, String sig, int siz, Class reflectClass) {
        super(nam, sig);
        this.size = siz;
        this.reflectClass = reflectClass;
        Type.registerTypeForClass(reflectClass, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PrimType(PrimType type) {
        super(type.this_name, type.signature);
        this.size = type.size;
        this.reflectClass = type.reflectClass;
    }

    @Override // gnu.bytecode.Type
    public Object coerceFromObject(Object obj) {
        if (obj.getClass() == this.reflectClass) {
            return obj;
        }
        char sig1 = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        switch (sig1) {
            case 'B':
                return Byte.valueOf(((Number) obj).byteValue());
            case 'D':
                return Double.valueOf(((Number) obj).doubleValue());
            case 'F':
                return Float.valueOf(((Number) obj).floatValue());
            case 'I':
                return Integer.valueOf(((Number) obj).intValue());
            case 'J':
                return Long.valueOf(((Number) obj).longValue());
            case 'S':
                return Short.valueOf(((Number) obj).shortValue());
            case 'Z':
                return Boolean.valueOf(((Boolean) obj).booleanValue());
            default:
                throw new ClassCastException("don't know how to coerce " + obj.getClass().getName() + " to " + getName());
        }
    }

    public char charValue(Object value) {
        return ((Character) value).charValue();
    }

    public static boolean booleanValue(Object value) {
        return !(value instanceof Boolean) || ((Boolean) value).booleanValue();
    }

    public ClassType boxedType() {
        String cname;
        char sig1 = getSignature().charAt(0);
        switch (sig1) {
            case 'B':
                cname = "java.lang.Byte";
                break;
            case 'C':
                cname = "java.lang.Character";
                break;
            case 'D':
                cname = "java.lang.Double";
                break;
            case 'F':
                cname = "java.lang.Float";
                break;
            case 'I':
                cname = "java.lang.Integer";
                break;
            case 'J':
                cname = "java.lang.Long";
                break;
            case 'S':
                cname = "java.lang.Short";
                break;
            case 'Z':
                cname = "java.lang.Boolean";
                break;
            default:
                cname = null;
                break;
        }
        return ClassType.make(cname);
    }

    @Override // gnu.bytecode.Type
    public void emitCoerceToObject(CodeAttr code) {
        Method method;
        char sig1 = getSignature().charAt(0);
        ClassType clas = boxedType();
        if (sig1 == 'Z') {
            code.emitIfIntNotZero();
            code.emitGetStatic(clas.getDeclaredField("TRUE"));
            code.emitElse();
            code.emitGetStatic(clas.getDeclaredField("FALSE"));
            code.emitFi();
            return;
        }
        Type[] args = {this};
        if (code.getMethod().getDeclaringClass().classfileFormatVersion >= 3211264) {
            method = clas.getDeclaredMethod("valueOf", args);
        } else {
            method = clas.getDeclaredMethod("<init>", args);
            code.emitNew(clas);
            code.emitDupX();
            code.emitSwap();
        }
        code.emitInvoke(method);
    }

    @Override // gnu.bytecode.Type
    public void emitIsInstance(CodeAttr code) {
        char sig1 = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        if (sig1 == 'Z') {
            javalangBooleanType.emitIsInstance(code);
        } else if (sig1 == 'V') {
            code.emitPop(1);
            code.emitPushInt(1);
        } else {
            javalangNumberType.emitIsInstance(code);
        }
    }

    @Override // gnu.bytecode.Type
    public void emitCoerceFromObject(CodeAttr code) {
        char sig1 = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        if (sig1 == 'Z') {
            code.emitCheckcast(javalangBooleanType);
            code.emitInvokeVirtual(booleanValue_method);
            return;
        }
        if (sig1 == 'V') {
            code.emitPop(1);
            return;
        }
        code.emitCheckcast(javalangNumberType);
        if (sig1 == 'I' || sig1 == 'S' || sig1 == 'B') {
            code.emitInvokeVirtual(intValue_method);
            return;
        }
        if (sig1 == 'J') {
            code.emitInvokeVirtual(longValue_method);
            return;
        }
        if (sig1 == 'D') {
            code.emitInvokeVirtual(doubleValue_method);
        } else if (sig1 == 'F') {
            code.emitInvokeVirtual(floatValue_method);
        } else {
            super.emitCoerceFromObject(code);
        }
    }

    public static int compare(PrimType type1, PrimType type2) {
        char sig1 = type1.signature.charAt(0);
        char sig2 = type2.signature.charAt(0);
        if (sig1 == sig2) {
            return 0;
        }
        if (sig1 == 'V') {
            return 1;
        }
        if (sig2 == 'V') {
            return -1;
        }
        if (sig1 == 'Z' || sig2 == 'Z') {
            return -3;
        }
        if (sig1 == 'C') {
            return type2.size > 2 ? -1 : -3;
        }
        if (sig2 == 'C') {
            return type1.size > 2 ? 1 : -3;
        }
        if (sig1 == 'D') {
            return 1;
        }
        if (sig2 == 'D') {
            return -1;
        }
        if (sig1 == 'F') {
            return 1;
        }
        if (sig2 == 'F') {
            return -1;
        }
        if (sig1 == 'J') {
            return 1;
        }
        if (sig2 == 'J') {
            return -1;
        }
        if (sig1 == 'I') {
            return 1;
        }
        if (sig2 == 'I') {
            return -1;
        }
        if (sig1 == 'S') {
            return 1;
        }
        return sig2 == 'S' ? -1 : -3;
    }

    public Type promotedType() {
        switch (this.signature.charAt(0)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                return Type.intType;
            default:
                return getImplementationType();
        }
    }

    private static char findInHierarchy(String cname) {
        int pos = numberHierarchy.indexOf(cname) - 2;
        if (pos < 0) {
            return (char) 0;
        }
        return numberHierarchy.charAt(pos);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x003f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0080  */
    @Override // gnu.bytecode.Type
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int compare(gnu.bytecode.Type r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof gnu.bytecode.PrimType
            if (r0 == 0) goto L1b
            gnu.bytecode.Type r0 = r9.getImplementationType()
            if (r0 == r9) goto L13
            int r0 = r9.compare(r8)
            int r0 = swappedCompareResult(r0)
            return r0
        L13:
            r0 = r9
            gnu.bytecode.PrimType r0 = (gnu.bytecode.PrimType) r0
            int r0 = compare(r8, r0)
            return r0
        L1b:
            boolean r0 = r9 instanceof gnu.bytecode.ClassType
            r1 = -3
            if (r0 != 0) goto L2e
            boolean r0 = r9 instanceof gnu.bytecode.ArrayType
            if (r0 == 0) goto L25
            return r1
        L25:
            int r0 = r9.compare(r8)
            int r0 = swappedCompareResult(r0)
            return r0
        L2e:
            java.lang.String r0 = r8.signature
            r2 = 0
            char r0 = r0.charAt(r2)
            java.lang.String r3 = r9.getName()
            r4 = -1
            if (r3 != 0) goto L3d
            return r4
        L3d:
            r5 = 0
            r6 = 1
            switch(r0) {
                case 66: goto L65;
                case 67: goto L5c;
                case 68: goto L59;
                case 70: goto L56;
                case 73: goto L53;
                case 74: goto L50;
                case 83: goto L4d;
                case 86: goto L4c;
                case 90: goto L43;
                default: goto L42;
            }
        L42:
            goto L67
        L43:
            java.lang.String r7 = "java.lang.Boolean"
            boolean r7 = r3.equals(r7)
            if (r7 == 0) goto L5c
            return r2
        L4c:
            return r6
        L4d:
            r5 = 66
            goto L67
        L50:
            r5 = 68
            goto L67
        L53:
            r5 = 67
            goto L67
        L56:
            r5 = 72
            goto L67
        L59:
            r5 = 73
            goto L67
        L5c:
            java.lang.String r7 = "java.lang.Character"
            boolean r7 = r3.equals(r7)
            if (r7 == 0) goto L67
            return r2
        L65:
            r5 = 65
        L67:
            if (r5 == 0) goto L78
            char r7 = findInHierarchy(r3)
            if (r7 == 0) goto L78
            if (r7 != r5) goto L72
            goto L77
        L72:
            if (r7 >= r5) goto L76
            r2 = 1
            goto L77
        L76:
            r2 = -1
        L77:
            return r2
        L78:
            java.lang.String r2 = "java.lang.Object"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L86
            gnu.bytecode.ClassType r2 = gnu.bytecode.PrimType.toStringType
            if (r9 != r2) goto L85
            goto L86
        L85:
            return r1
        L86:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.PrimType.compare(gnu.bytecode.Type):int");
    }
}
