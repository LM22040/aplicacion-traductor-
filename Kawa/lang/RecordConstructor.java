package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;

/* loaded from: classes.dex */
public class RecordConstructor extends ProcedureN {
    Field[] fields;
    ClassType type;

    public RecordConstructor(ClassType type, Field[] fields) {
        this.type = type;
        this.fields = fields;
    }

    public RecordConstructor(Class clas, Field[] fields) {
        this((ClassType) Type.make(clas), fields);
    }

    public RecordConstructor(Class clas) {
        init((ClassType) Type.make(clas));
    }

    public RecordConstructor(ClassType type) {
        init(type);
    }

    private void init(ClassType type) {
        this.type = type;
        Field list = type.getFields();
        int count = 0;
        for (Field fld = list; fld != null; fld = fld.getNext()) {
            if ((fld.getModifiers() & 9) == 1) {
                count++;
            }
        }
        this.fields = new Field[count];
        int i = 0;
        for (Field fld2 = list; fld2 != null; fld2 = fld2.getNext()) {
            if ((fld2.getModifiers() & 9) == 1) {
                this.fields[i] = fld2;
                i++;
            }
        }
    }

    public RecordConstructor(Class clas, Object fieldsList) {
        this((ClassType) Type.make(clas), fieldsList);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
    
        r9.fields[r2] = r5;
        r11 = r3.getCdr();
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public RecordConstructor(gnu.bytecode.ClassType r10, java.lang.Object r11) {
        /*
            r9 = this;
            r9.<init>()
            r9.type = r10
            if (r11 != 0) goto Lb
            r9.init(r10)
            goto L67
        Lb:
            r0 = 0
            int r0 = gnu.lists.LList.listLength(r11, r0)
            gnu.bytecode.Field[] r1 = new gnu.bytecode.Field[r0]
            r9.fields = r1
            gnu.bytecode.Field r1 = r10.getFields()
            r2 = 0
        L19:
            if (r2 >= r0) goto L67
            r3 = r11
            gnu.lists.Pair r3 = (gnu.lists.Pair) r3
            java.lang.Object r4 = r3.getCar()
            java.lang.String r4 = r4.toString()
            r5 = r1
        L27:
            if (r5 == 0) goto L40
            java.lang.String r6 = r5.getSourceName()
            if (r6 != r4) goto L3b
            gnu.bytecode.Field[] r6 = r9.fields
            r6[r2] = r5
            java.lang.Object r11 = r3.getCdr()
            int r2 = r2 + 1
            goto L19
        L3b:
            gnu.bytecode.Field r5 = r5.getNext()
            goto L27
        L40:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "no such field "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r4)
            java.lang.String r8 = " in "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = r10.getName()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.RecordConstructor.<init>(gnu.bytecode.ClassType, java.lang.Object):void");
    }

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        int nargs = this.fields.length;
        return (nargs << 12) | nargs;
    }

    @Override // gnu.mapping.PropertySet, gnu.mapping.Named
    public String getName() {
        return this.type.getName() + " constructor";
    }

    @Override // gnu.mapping.ProcedureN, gnu.mapping.Procedure
    public Object applyN(Object[] args) {
        try {
            Object obj = this.type.getReflectClass().newInstance();
            if (args.length != this.fields.length) {
                throw new WrongArguments(this, args.length);
            }
            for (int i = 0; i < args.length; i++) {
                Field fld = this.fields[i];
                try {
                    fld.getReflectField().set(obj, args[i]);
                } catch (Exception ex) {
                    throw new WrappedException("illegal access for field " + fld.getName(), ex);
                }
            }
            return obj;
        } catch (IllegalAccessException ex2) {
            throw new GenericError(ex2.toString());
        } catch (InstantiationException ex3) {
            throw new GenericError(ex3.toString());
        }
    }
}
