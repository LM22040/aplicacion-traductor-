package gnu.kawa.reflect;

import androidx.fragment.app.FragmentTransaction;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.lists.ItemPredicate;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: classes.dex */
public class OccurrenceType extends ObjectType implements Externalizable, TypeValue {
    public static final Type emptySequenceType = getInstance(SingletonType.instance, 0, 0);
    static final Method isInstanceMethod;
    public static final ClassType typeOccurrenceType;
    Type base;
    int maxOccurs;
    int minOccurs;

    public Type getBase() {
        return this.base;
    }

    public int minOccurs() {
        return this.minOccurs;
    }

    public int maxOccurs() {
        return this.maxOccurs;
    }

    public OccurrenceType(Type base, int minOccurs, int maxOccurs) {
        this.base = base;
        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
    }

    public static Type getInstance(Type base, int minOccurs, int maxOccurs) {
        if (minOccurs == 1 && maxOccurs == 1) {
            return base;
        }
        if (minOccurs == 0 && maxOccurs < 0 && (base == SingletonType.instance || base == Type.pointer_type)) {
            return Type.pointer_type;
        }
        return new OccurrenceType(base, minOccurs, maxOccurs);
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.reflect.OccurrenceType");
        typeOccurrenceType = make;
        isInstanceMethod = make.getDeclaredMethod("isInstance", 1);
    }

    @Override // gnu.bytecode.ObjectType, gnu.bytecode.Type
    public Type getImplementationType() {
        return Type.pointer_type;
    }

    @Override // gnu.bytecode.ObjectType, gnu.bytecode.Type
    public int compare(Type other) {
        if (other instanceof OccurrenceType) {
            OccurrenceType occOther = (OccurrenceType) other;
            if (this.minOccurs == occOther.minOccurs && this.maxOccurs == occOther.maxOccurs) {
                return this.base.compare(occOther.getBase());
            }
            return -2;
        }
        return -2;
    }

    @Override // gnu.bytecode.ObjectType, gnu.bytecode.Type
    public Object coerceFromObject(Object obj) {
        if (!(obj instanceof Values) && this.minOccurs <= 1 && this.maxOccurs != 0) {
            return this.base.coerceFromObject(obj);
        }
        if (!isInstance(obj)) {
            throw new ClassCastException();
        }
        return obj;
    }

    @Override // gnu.bytecode.ObjectType, gnu.bytecode.Type
    public boolean isInstance(Object obj) {
        if (obj instanceof Values) {
            Values vals = (Values) obj;
            int pos = vals.startPos();
            int n = 0;
            java.lang.reflect.Type type = this.base;
            if (type instanceof ItemPredicate) {
                ItemPredicate pred = (ItemPredicate) type;
                while (true) {
                    boolean matches = pred.isInstancePos(vals, pos);
                    pos = vals.nextPos(pos);
                    if (pos == 0) {
                        if (n < this.minOccurs) {
                            return false;
                        }
                        int i = this.maxOccurs;
                        return i < 0 || n <= i;
                    }
                    if (!matches) {
                        return false;
                    }
                    n++;
                }
            } else {
                while (true) {
                    pos = vals.nextPos(pos);
                    if (pos == 0) {
                        if (n < this.minOccurs) {
                            return false;
                        }
                        int i2 = this.maxOccurs;
                        return i2 < 0 || n <= i2;
                    }
                    Object value = vals.getPosPrevious(pos);
                    if (!this.base.isInstance(value)) {
                        return false;
                    }
                    n++;
                }
            }
        } else {
            if (this.minOccurs > 1 || this.maxOccurs == 0) {
                return false;
            }
            return this.base.isInstance(obj);
        }
    }

    @Override // gnu.expr.TypeValue
    public void emitTestIf(Variable incoming, Declaration decl, Compilation comp) {
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        if (decl != null) {
            code.emitDup();
            decl.compileStore(comp);
        }
        comp.compileConstant(this);
        code.emitSwap();
        code.emitInvokeVirtual(isInstanceMethod);
        code.emitIfIntNotZero();
    }

    @Override // gnu.expr.TypeValue
    public void emitIsInstance(Variable incoming, Compilation comp, Target target) {
        InstanceOf.emitIsInstance(this, incoming, comp, target);
    }

    @Override // gnu.expr.TypeValue
    public Expression convertValue(Expression value) {
        return null;
    }

    @Override // gnu.expr.TypeValue
    public Procedure getConstructor() {
        return null;
    }

    public static int itemCountRange(Type type) {
        if (type instanceof SingletonType) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        if (type instanceof OccurrenceType) {
            OccurrenceType occ = (OccurrenceType) type;
            int min = occ.minOccurs();
            int max = occ.maxOccurs();
            int bnum = itemCountRange(occ.getBase());
            if ((min == 1 && max == 1) || bnum == 0) {
                return bnum;
            }
            if (max > 1048575) {
                max = -1;
            }
            if (max == 0) {
                return 0;
            }
            int bmin = bnum & 4095;
            int bmax = bnum >> 12;
            if (bnum != 4097) {
                if (min > 4095) {
                    min = 4095;
                }
                min *= bmin;
                if (min > 4095) {
                    min = 4095;
                }
                if (max < 0 || bmax < 0) {
                    max = -1;
                } else {
                    max *= bmax;
                }
                if (max > 1048575) {
                    max = -1;
                }
            }
            return (max << 12) | min;
        }
        if (type instanceof PrimType) {
            if (type.isVoid()) {
                return 0;
            }
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        if (type instanceof ArrayType) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        if (type instanceof ObjectType) {
            int cmp = type.compare(Compilation.typeValues);
            if (cmp == -3) {
                return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
            }
            return -4096;
        }
        return -4096;
    }

    public static char itemCountCode(Type type) {
        int num = itemCountRange(type);
        int min = num & 4095;
        int max = num >> 12;
        if (max == 0) {
            return '0';
        }
        return min == 0 ? max == 1 ? '?' : '*' : (min == 1 && max == 1) ? '1' : '+';
    }

    public static boolean itemCountIsZeroOrOne(Type type) {
        return (itemCountRange(type) >> 13) == 0;
    }

    public static boolean itemCountIsOne(Type type) {
        return itemCountRange(type) == 4097;
    }

    public static Type itemPrimeType(Type type) {
        while (type instanceof OccurrenceType) {
            type = ((OccurrenceType) type).getBase();
        }
        return itemCountIsOne(type) ? type : SingletonType.instance;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.base);
        out.writeInt(this.minOccurs);
        out.writeInt(this.maxOccurs);
    }

    @Override // gnu.bytecode.Type
    public String toString() {
        String b = this.base.toString();
        boolean parens = b == null || b.indexOf(32) >= 0;
        StringBuffer sbuf = new StringBuffer();
        if (parens) {
            sbuf.append('(');
        }
        sbuf.append(b);
        if (parens) {
            sbuf.append(')');
        }
        int i = this.minOccurs;
        if (i != 1 || this.maxOccurs != 1) {
            if (i != 0 || this.maxOccurs != 1) {
                if (i != 1 || this.maxOccurs != -1) {
                    if (i == 0 && this.maxOccurs == -1) {
                        sbuf.append('*');
                    } else {
                        sbuf.append('{');
                        sbuf.append(this.minOccurs);
                        sbuf.append(',');
                        int i2 = this.maxOccurs;
                        if (i2 >= 0) {
                            sbuf.append(i2);
                        } else {
                            sbuf.append('*');
                        }
                        sbuf.append('}');
                    }
                } else {
                    sbuf.append('+');
                }
            } else {
                sbuf.append('?');
            }
        }
        return sbuf.toString();
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.base = (Type) in.readObject();
        this.minOccurs = in.readInt();
        this.maxOccurs = in.readInt();
    }
}
