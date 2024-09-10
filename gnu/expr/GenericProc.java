package gnu.expr;

import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;

/* loaded from: classes.dex */
public class GenericProc extends MethodProc {
    int count;
    int maxArgs;
    protected MethodProc[] methods;
    int minArgs;

    public GenericProc(String name) {
        setName(name);
    }

    public GenericProc() {
    }

    public int getMethodCount() {
        return this.count;
    }

    public MethodProc getMethod(int i) {
        if (i >= this.count) {
            return null;
        }
        return this.methods[i];
    }

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        return this.minArgs | (this.maxArgs << 12);
    }

    protected synchronized void add(MethodProc[] procs) {
        int n = procs.length;
        if (this.methods == null) {
            this.methods = new MethodProc[n];
        }
        for (MethodProc methodProc : procs) {
            add(methodProc);
        }
    }

    public synchronized void addAtEnd(MethodProc method) {
        int oldCount = this.count;
        MethodProc[] methodProcArr = this.methods;
        if (methodProcArr == null) {
            this.methods = new MethodProc[8];
        } else if (oldCount >= methodProcArr.length) {
            MethodProc[] copy = new MethodProc[methodProcArr.length * 2];
            System.arraycopy(methodProcArr, 0, copy, 0, oldCount);
            this.methods = copy;
        }
        this.methods[oldCount] = method;
        int n = method.minArgs();
        if (n < this.minArgs || this.count == 0) {
            this.minArgs = n;
        }
        int n2 = method.maxArgs();
        if (n2 == -1 || n2 > this.maxArgs) {
            this.maxArgs = n2;
        }
        this.count = oldCount + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
    
        r3 = r6.methods;
        java.lang.System.arraycopy(r3, r1, r3, r1 + 1, r0 - r1);
        r6.methods[r1] = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void add(gnu.mapping.MethodProc r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            int r0 = r6.count     // Catch: java.lang.Throwable -> L26
            r6.addAtEnd(r7)     // Catch: java.lang.Throwable -> L26
            r1 = 0
        L7:
            if (r1 >= r0) goto L24
            gnu.mapping.MethodProc[] r2 = r6.methods     // Catch: java.lang.Throwable -> L26
            r2 = r2[r1]     // Catch: java.lang.Throwable -> L26
            gnu.mapping.MethodProc r2 = gnu.mapping.MethodProc.mostSpecific(r7, r2)     // Catch: java.lang.Throwable -> L26
            if (r2 != r7) goto L21
            gnu.mapping.MethodProc[] r3 = r6.methods     // Catch: java.lang.Throwable -> L26
            int r4 = r1 + 1
            int r5 = r0 - r1
            java.lang.System.arraycopy(r3, r1, r3, r4, r5)     // Catch: java.lang.Throwable -> L26
            gnu.mapping.MethodProc[] r3 = r6.methods     // Catch: java.lang.Throwable -> L26
            r3[r1] = r7     // Catch: java.lang.Throwable -> L26
            goto L24
        L21:
            int r1 = r1 + 1
            goto L7
        L24:
            monitor-exit(r6)
            return
        L26:
            r7 = move-exception
            monitor-exit(r6)
            goto L2a
        L29:
            throw r7
        L2a:
            goto L29
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.GenericProc.add(gnu.mapping.MethodProc):void");
    }

    @Override // gnu.mapping.MethodProc, gnu.mapping.ProcedureN, gnu.mapping.Procedure
    public Object applyN(Object[] args) throws Throwable {
        if (this.count == 1) {
            return this.methods[0].applyN(args);
        }
        checkArgCount(this, args.length);
        CallContext ctx = CallContext.getInstance();
        for (int i = 0; i < this.count; i++) {
            MethodProc method = this.methods[i];
            int m = method.matchN(args, ctx);
            if (m == 0) {
                return ctx.runUntilValue();
            }
        }
        throw new WrongType((Procedure) this, -1, (ClassCastException) null);
    }

    @Override // gnu.mapping.MethodProc
    public int isApplicable(Type[] args) {
        int best = -1;
        int i = this.count;
        while (true) {
            i--;
            if (i >= 0) {
                MethodProc method = this.methods[i];
                int result = method.isApplicable(args);
                if (result == 1) {
                    return 1;
                }
                if (result == 0) {
                    best = 0;
                }
            } else {
                return best;
            }
        }
    }

    @Override // gnu.mapping.Procedure
    public int match0(CallContext ctx) {
        if (this.count == 1) {
            return this.methods[0].match0(ctx);
        }
        for (int i = 0; i < this.count; i++) {
            MethodProc method = this.methods[i];
            int code = method.match0(ctx);
            if (code == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    @Override // gnu.mapping.Procedure
    public int match1(Object arg1, CallContext ctx) {
        if (this.count == 1) {
            return this.methods[0].match1(arg1, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            MethodProc method = this.methods[i];
            int code = method.match1(arg1, ctx);
            if (code == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    @Override // gnu.mapping.Procedure
    public int match2(Object arg1, Object arg2, CallContext ctx) {
        if (this.count == 1) {
            return this.methods[0].match2(arg1, arg2, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            MethodProc method = this.methods[i];
            int code = method.match2(arg1, arg2, ctx);
            if (code == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    @Override // gnu.mapping.Procedure
    public int match3(Object arg1, Object arg2, Object arg3, CallContext ctx) {
        if (this.count == 1) {
            return this.methods[0].match3(arg1, arg2, arg3, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            MethodProc method = this.methods[i];
            int code = method.match3(arg1, arg2, arg3, ctx);
            if (code == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    @Override // gnu.mapping.Procedure
    public int match4(Object arg1, Object arg2, Object arg3, Object arg4, CallContext ctx) {
        if (this.count == 1) {
            return this.methods[0].match4(arg1, arg2, arg3, arg4, ctx);
        }
        for (int i = 0; i < this.count; i++) {
            MethodProc method = this.methods[i];
            int code = method.match4(arg1, arg2, arg3, arg4, ctx);
            if (code == 0) {
                return 0;
            }
        }
        ctx.proc = null;
        return -1;
    }

    @Override // gnu.mapping.Procedure
    public int matchN(Object[] args, CallContext ctx) {
        Type atype;
        if (this.count == 1) {
            return this.methods[0].matchN(args, ctx);
        }
        int alen = args.length;
        Type[] atypes = new Type[alen];
        Language language = Language.getDefaultLanguage();
        for (int j = 0; j < alen; j++) {
            Object arg = args[j];
            if (arg == null) {
                atype = Type.nullType;
            } else {
                Class aclass = arg.getClass();
                if (language != null) {
                    atype = language.getTypeFor(aclass);
                } else {
                    Type atype2 = Type.make(aclass);
                    atype = atype2;
                }
            }
            atypes[j] = atype;
        }
        int j2 = this.count;
        int[] codes = new int[j2];
        int defCount = 0;
        int maybeCount = 0;
        int bestIndex = -1;
        for (int i = 0; i < this.count; i++) {
            int code = this.methods[i].isApplicable(atypes);
            if (defCount == 0 && code >= 0) {
                bestIndex = i;
            }
            if (code > 0) {
                defCount++;
            } else if (code == 0) {
                maybeCount++;
            }
            codes[i] = code;
        }
        if (defCount == 1 || (defCount == 0 && maybeCount == 1)) {
            return this.methods[bestIndex].matchN(args, ctx);
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            int code2 = codes[i2];
            if (code2 >= 0 && (code2 != 0 || defCount <= 0)) {
                MethodProc method = this.methods[i2];
                if (method.matchN(args, ctx) == 0) {
                    return 0;
                }
            }
        }
        ctx.proc = null;
        return -1;
    }

    public void setProperty(Keyword key, Object value) {
        String name = key.getName();
        if (name == "name") {
            setName(value.toString());
        } else if (name == "method") {
            add((MethodProc) value);
        } else {
            super.setProperty(key.asSymbol(), value);
        }
    }

    public final void setProperties(Object[] args) {
        int alen = args.length;
        int i = 0;
        while (i < alen) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                i++;
                setProperty((Keyword) arg, args[i]);
            } else {
                add((MethodProc) arg);
            }
            i++;
        }
    }

    public static GenericProc make(Object[] args) {
        GenericProc result = new GenericProc();
        result.setProperties(args);
        return result;
    }

    public static GenericProc makeWithoutSorting(Object... args) {
        GenericProc result = new GenericProc();
        int alen = args.length;
        int i = 0;
        while (i < alen) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                i++;
                result.setProperty((Keyword) arg, args[i]);
            } else {
                result.addAtEnd((MethodProc) arg);
            }
            i++;
        }
        return result;
    }
}
