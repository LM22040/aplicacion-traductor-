package gnu.bytecode;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Label {
    int first_fixup;
    Type[] localTypes;
    boolean needsStackMapEntry;
    int position;
    Type[] stackTypes;
    private Object[] typeChangeListeners;

    public final boolean defined() {
        return this.position >= 0;
    }

    public Label() {
        this(-1);
    }

    public Label(CodeAttr code) {
        this(-1);
    }

    public Label(int position) {
        this.position = position;
    }

    Type mergeTypes(Type t1, Type t2) {
        if ((t1 instanceof PrimType) != (t2 instanceof PrimType)) {
            return null;
        }
        return Type.lowestCommonSuperType(t1, t2);
    }

    void setTypes(Type[] locals, int usedLocals, Type[] stack, int usedStack) {
        while (usedLocals > 0) {
            Type last = locals[usedLocals - 1];
            if (last != null) {
                break;
            } else {
                usedLocals--;
            }
        }
        Type[] typeArr = this.stackTypes;
        if (typeArr == null) {
            if (usedStack == 0) {
                this.stackTypes = Type.typeArray0;
            } else {
                Type[] typeArr2 = new Type[usedStack];
                this.stackTypes = typeArr2;
                System.arraycopy(stack, 0, typeArr2, 0, usedStack);
            }
            if (usedLocals == 0) {
                this.localTypes = Type.typeArray0;
                return;
            }
            Type[] typeArr3 = new Type[usedLocals];
            this.localTypes = typeArr3;
            System.arraycopy(locals, 0, typeArr3, 0, usedLocals);
            return;
        }
        int slen = typeArr.length;
        if (usedStack != slen) {
            throw new InternalError("inconsistent stack length");
        }
        for (int i = 0; i < usedStack; i++) {
            Type[] typeArr4 = this.stackTypes;
            typeArr4[i] = mergeTypes(typeArr4[i], stack[i]);
        }
        Type[] typeArr5 = this.localTypes;
        int min = usedLocals < typeArr5.length ? usedLocals : typeArr5.length;
        for (int i2 = 0; i2 < min; i2++) {
            mergeLocalType(i2, locals[i2]);
        }
        int i3 = usedLocals;
        while (true) {
            Type[] typeArr6 = this.localTypes;
            if (i3 < typeArr6.length) {
                typeArr6[i3] = null;
                i3++;
            } else {
                return;
            }
        }
    }

    public void setTypes(CodeAttr code) {
        addTypeChangeListeners(code);
        if (this.stackTypes != null && code.SP != this.stackTypes.length) {
            throw new InternalError();
        }
        setTypes(code.local_types, code.local_types == null ? 0 : code.local_types.length, code.stack_types, code.SP);
    }

    public void setTypes(Label other) {
        Type[] typeArr = other.localTypes;
        int length = typeArr.length;
        Type[] typeArr2 = other.stackTypes;
        setTypes(typeArr, length, typeArr2, typeArr2.length);
    }

    private void mergeLocalType(int varnum, Type newType) {
        Type oldLocal = this.localTypes[varnum];
        Type newLocal = mergeTypes(oldLocal, newType);
        this.localTypes[varnum] = newLocal;
        if (newLocal != oldLocal) {
            notifyTypeChangeListeners(varnum, newLocal);
        }
    }

    private void notifyTypeChangeListeners(int varnum, Type newType) {
        Object listeners;
        Object[] arr = this.typeChangeListeners;
        if (arr == null || arr.length <= varnum || (listeners = arr[varnum]) == null) {
            return;
        }
        if (listeners instanceof Label) {
            ((Label) listeners).mergeLocalType(varnum, newType);
        } else {
            Iterator i$ = ((ArrayList) listeners).iterator();
            while (i$.hasNext()) {
                Label listener = (Label) i$.next();
                listener.mergeLocalType(varnum, newType);
            }
        }
        if (newType == null) {
            arr[varnum] = null;
        }
    }

    void addTypeChangeListener(int varnum, Label listener) {
        ArrayList<Label> list;
        Object[] arr = this.typeChangeListeners;
        if (arr == null) {
            Object[] objArr = new Object[varnum + 10];
            arr = objArr;
            this.typeChangeListeners = objArr;
        } else if (arr.length <= varnum) {
            arr = new Object[varnum + 10];
            Object[] objArr2 = this.typeChangeListeners;
            System.arraycopy(objArr2, 0, arr, 0, objArr2.length);
            this.typeChangeListeners = arr;
        }
        Object set = arr[varnum];
        if (set == null) {
            arr[varnum] = listener;
            return;
        }
        if (set instanceof Label) {
            list = new ArrayList<>();
            list.add((Label) set);
            arr[varnum] = list;
        } else {
            list = (ArrayList) set;
        }
        list.add(listener);
    }

    void addTypeChangeListeners(CodeAttr code) {
        if (code.local_types != null && code.previousLabel != null) {
            int len = code.local_types.length;
            for (int varnum = 0; varnum < len; varnum++) {
                if (code.local_types[varnum] != null && (code.varsSetInCurrentBlock == null || code.varsSetInCurrentBlock.length <= varnum || !code.varsSetInCurrentBlock[varnum])) {
                    code.previousLabel.addTypeChangeListener(varnum, this);
                }
            }
        }
    }

    public void defineRaw(CodeAttr code) {
        if (this.position >= 0) {
            throw new Error("label definition more than once");
        }
        this.position = code.PC;
        int i = code.fixup_count;
        this.first_fixup = i;
        if (i >= 0) {
            code.fixupAdd(1, this);
        }
    }

    public void define(CodeAttr code) {
        if (code.reachableHere()) {
            setTypes(code);
        } else {
            Type[] typeArr = this.localTypes;
            if (typeArr != null) {
                int i = typeArr.length;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    if (this.localTypes[i] != null && (code.locals.used == null || code.locals.used[i] == null)) {
                        this.localTypes[i] = null;
                    }
                }
            }
        }
        code.previousLabel = this;
        code.varsSetInCurrentBlock = null;
        defineRaw(code);
        if (this.localTypes != null) {
            code.setTypes(this);
        }
        code.setReachable(true);
    }
}
