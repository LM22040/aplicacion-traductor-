package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import java.lang.reflect.Array;

/* compiled from: LambdaExp.java */
/* loaded from: classes.dex */
class Closure extends MethodProc {
    Object[][] evalFrames;
    LambdaExp lambda;

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        return this.lambda.min_args | (this.lambda.max_args << 12);
    }

    public Closure(LambdaExp lexp, CallContext ctx) {
        this.lambda = lexp;
        Object[][] oldFrames = ctx.evalFrames;
        if (oldFrames != null) {
            int n = oldFrames.length;
            while (n > 0 && oldFrames[n - 1] == null) {
                n--;
            }
            Object[][] objArr = new Object[n];
            this.evalFrames = objArr;
            System.arraycopy(oldFrames, 0, objArr, 0, n);
        }
        setSymbol(this.lambda.getSymbol());
    }

    @Override // gnu.mapping.Procedure
    public int match0(CallContext ctx) {
        return matchN(new Object[0], ctx);
    }

    @Override // gnu.mapping.Procedure
    public int match1(Object arg1, CallContext ctx) {
        return matchN(new Object[]{arg1}, ctx);
    }

    @Override // gnu.mapping.Procedure
    public int match2(Object arg1, Object arg2, CallContext ctx) {
        return matchN(new Object[]{arg1, arg2}, ctx);
    }

    @Override // gnu.mapping.Procedure
    public int match3(Object arg1, Object arg2, Object arg3, CallContext ctx) {
        return matchN(new Object[]{arg1, arg2, arg3}, ctx);
    }

    @Override // gnu.mapping.Procedure
    public int match4(Object arg1, Object arg2, Object arg3, Object arg4, CallContext ctx) {
        return matchN(new Object[]{arg1, arg2, arg3, arg4}, ctx);
    }

    @Override // gnu.mapping.Procedure
    public int matchN(Object[] args, CallContext ctx) {
        int num;
        int nargs;
        int min;
        int i;
        int num2;
        Object value;
        Object value2;
        Object value3;
        int i2 = numArgs();
        int nargs2 = args.length;
        int min2 = i2 & 4095;
        if (nargs2 < min2) {
            return (-983040) | min2;
        }
        int max = i2 >> 12;
        if (nargs2 > max && max >= 0) {
            return (-917504) | max;
        }
        Object[] evalFrame = new Object[this.lambda.frameSize];
        int key_args = this.lambda.keywords == null ? 0 : this.lambda.keywords.length;
        int opt_args = this.lambda.defaultArgs == null ? 0 : this.lambda.defaultArgs.length - key_args;
        int min_args = this.lambda.min_args;
        Declaration decl = this.lambda.firstDecl();
        Declaration decl2 = decl;
        int key_i = 0;
        int key_i2 = 0;
        int opt_i = 0;
        while (decl2 != null) {
            if (opt_i < min_args) {
                int i3 = opt_i + 1;
                value = args[opt_i];
                num = i2;
                nargs = nargs2;
                min = min2;
                num2 = i3;
                i = max;
            } else if (opt_i < min_args + opt_args) {
                if (opt_i < nargs2) {
                    int i4 = opt_i + 1;
                    Object obj = args[opt_i];
                    opt_i = i4;
                    value3 = obj;
                } else {
                    value3 = this.lambda.evalDefaultArg(key_i2, ctx);
                }
                key_i2++;
                num = i2;
                nargs = nargs2;
                min = min2;
                i = max;
                num2 = opt_i;
                value = value3;
            } else if (this.lambda.max_args >= 0 || opt_i != min_args + opt_args) {
                num = i2;
                nargs = nargs2;
                min = min2;
                i = max;
                int key_i3 = key_i + 1;
                Keyword keyword = this.lambda.keywords[key_i];
                int key_offset = min_args + opt_args;
                Object value4 = Keyword.searchForKeyword(args, key_offset, keyword);
                if (value4 == Special.dfault) {
                    value4 = this.lambda.evalDefaultArg(key_i2, ctx);
                }
                key_i2++;
                key_i = key_i3;
                num2 = opt_i;
                value = value4;
            } else if (decl2.type instanceof ArrayType) {
                num = i2;
                int num3 = nargs2 - opt_i;
                nargs = nargs2;
                Type elementType = ((ArrayType) decl2.type).getComponentType();
                if (elementType == Type.objectType) {
                    Object[] rest = new Object[num3];
                    min = min2;
                    System.arraycopy(args, opt_i, rest, 0, num3);
                    value2 = rest;
                    i = max;
                } else {
                    min = min2;
                    Class elementClass = elementType.getReflectClass();
                    i = max;
                    Object value5 = Array.newInstance((Class<?>) elementClass, num3);
                    for (int j = 0; j < num3; j++) {
                        try {
                            Object el = elementType.coerceFromObject(args[opt_i + j]);
                            Array.set(value5, j, el);
                        } catch (ClassCastException e) {
                            return (opt_i + j) | MethodProc.NO_MATCH_BAD_TYPE;
                        }
                    }
                    value2 = value5;
                }
                num2 = opt_i;
                value = value2;
            } else {
                num = i2;
                nargs = nargs2;
                min = min2;
                i = max;
                num2 = opt_i;
                value = LList.makeList(args, opt_i);
            }
            if (decl2.type != null) {
                try {
                    value = decl2.type.coerceFromObject(value);
                } catch (ClassCastException e2) {
                    return num2 | MethodProc.NO_MATCH_BAD_TYPE;
                }
            }
            if (decl2.isIndirectBinding()) {
                Location loc = decl2.makeIndirectLocationFor();
                loc.set(value);
                value = loc;
            }
            evalFrame[decl2.evalIndex] = value;
            decl2 = decl2.nextDecl();
            opt_i = num2;
            max = i;
            i2 = num;
            nargs2 = nargs;
            min2 = min;
        }
        ctx.values = evalFrame;
        ctx.where = 0;
        ctx.next = 0;
        ctx.proc = this;
        return 0;
    }

    @Override // gnu.mapping.Procedure
    public void apply(CallContext ctx) throws Throwable {
        int level = ScopeExp.nesting(this.lambda);
        Object[] evalFrame = ctx.values;
        Object[][] saveFrames = ctx.evalFrames;
        Object[][] objArr = this.evalFrames;
        int numFrames = objArr == null ? 0 : objArr.length;
        if (level >= numFrames) {
            numFrames = level;
        }
        Object[][] newFrames = new Object[numFrames + 10];
        if (objArr != null) {
            System.arraycopy(objArr, 0, newFrames, 0, objArr.length);
        }
        newFrames[level] = evalFrame;
        ctx.evalFrames = newFrames;
        try {
            if (this.lambda.body == null) {
                StringBuffer sbuf = new StringBuffer("procedure ");
                String name = this.lambda.getName();
                if (name == null) {
                    name = "<anonymous>";
                }
                sbuf.append(name);
                int line = this.lambda.getLineNumber();
                if (line > 0) {
                    sbuf.append(" at line ");
                    sbuf.append(line);
                }
                sbuf.append(" was called before it was expanded");
                throw new RuntimeException(sbuf.toString());
            }
            this.lambda.body.apply(ctx);
        } finally {
            ctx.evalFrames = saveFrames;
        }
    }

    @Override // gnu.mapping.PropertySet
    public Object getProperty(Object key, Object defaultValue) {
        Object value = super.getProperty(key, defaultValue);
        if (value == null) {
            return this.lambda.getProperty(key, defaultValue);
        }
        return value;
    }
}
