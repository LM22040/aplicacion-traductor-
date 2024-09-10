package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;

/* loaded from: classes.dex */
public class DivideOp extends ArithOp {
    public static final DivideOp $Sl = new DivideOp("/", 4);
    public static final DivideOp div;
    public static final DivideOp div0;
    public static final DivideOp idiv;
    public static final DivideOp mod;
    public static final DivideOp mod0;
    public static final DivideOp modulo;
    public static final DivideOp quotient;
    public static final DivideOp remainder;
    int rounding_mode;

    public int getRoundingMode() {
        return this.rounding_mode;
    }

    static {
        DivideOp divideOp = new DivideOp("idiv", 7);
        idiv = divideOp;
        DivideOp divideOp2 = new DivideOp("quotient", 6);
        quotient = divideOp2;
        DivideOp divideOp3 = new DivideOp("remainder", 8);
        remainder = divideOp3;
        DivideOp divideOp4 = new DivideOp("modulo", 8);
        modulo = divideOp4;
        DivideOp divideOp5 = new DivideOp("div", 6);
        div = divideOp5;
        DivideOp divideOp6 = new DivideOp("mod", 8);
        mod = divideOp6;
        DivideOp divideOp7 = new DivideOp("div0", 6);
        div0 = divideOp7;
        DivideOp divideOp8 = new DivideOp("mod0", 8);
        mod0 = divideOp8;
        divideOp.rounding_mode = 3;
        divideOp2.rounding_mode = 3;
        divideOp3.rounding_mode = 3;
        divideOp4.rounding_mode = 1;
        divideOp5.rounding_mode = 5;
        divideOp6.rounding_mode = 5;
        divideOp7.rounding_mode = 4;
        divideOp8.rounding_mode = 4;
    }

    public DivideOp(String name, int op) {
        super(name, op);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set((PropertySet) this, "*gnu.kawa.functions.CompileArith:forDiv");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01fe, code lost:
    
        r13 = r1;
        r15 = r4;
        r16 = r6;
        r2 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x008f, code lost:
    
        if (r2 == 7) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005b, code lost:
    
        if (r5 == 2) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01a7, code lost:
    
        r15 = r4;
        r16 = r8;
        r8 = r11;
        r17 = 0;
        r11 = r6;
        r7 = r10;
        r13 = r1;
        r2 = r28;
     */
    @Override // gnu.mapping.ProcedureN, gnu.mapping.Procedure
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object applyN(java.lang.Object[] r33) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 912
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.DivideOp.applyN(java.lang.Object[]):java.lang.Object");
    }

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        return this.op == 4 ? -4095 : 8194;
    }
}
