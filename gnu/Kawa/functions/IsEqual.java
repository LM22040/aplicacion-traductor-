package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure2;

/* loaded from: classes.dex */
public class IsEqual extends Procedure2 {
    Language language;

    public IsEqual(Language language, String name) {
        this.language = language;
        setName(name);
    }

    public static boolean numberEquals(Number num1, Number num2) {
        boolean exact1 = Arithmetic.isExact(num1);
        boolean exact2 = Arithmetic.isExact(num2);
        if (exact1 && exact2) {
            return NumberCompare.$Eq(num1, num2);
        }
        return exact1 == exact2 && num1.equals(num2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c0, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean apply(java.lang.Object r10, java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.IsEqual.apply(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // gnu.mapping.Procedure2, gnu.mapping.Procedure
    public Object apply2(Object arg1, Object arg2) {
        return this.language.booleanObject(apply(arg1, arg2));
    }
}
