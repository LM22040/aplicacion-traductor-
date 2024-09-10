package gnu.kawa.xml;

import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.location.LocationRequestCompat;
import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.kawa.functions.Arithmetic;
import gnu.math.IntNum;
import gnu.math.RealNum;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class XIntegerType extends XDataType {
    public static final XIntegerType byteType;
    public static final XIntegerType intType;
    public static final XIntegerType integerType;
    public static final XIntegerType longType;
    public static final XIntegerType negativeIntegerType;
    public static final XIntegerType nonNegativeIntegerType;
    public static final XIntegerType nonPositiveIntegerType;
    public static final XIntegerType positiveIntegerType;
    public static final XIntegerType shortType;
    static ClassType typeIntNum = ClassType.make("gnu.math.IntNum");
    public static final XIntegerType unsignedByteType;
    public static final XIntegerType unsignedIntType;
    public static final XIntegerType unsignedLongType;
    public static final XIntegerType unsignedShortType;
    boolean isUnsignedType;
    public final IntNum maxValue;
    public final IntNum minValue;

    static {
        XIntegerType xIntegerType = new XIntegerType(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, decimalType, 5, (IntNum) null, (IntNum) null);
        integerType = xIntegerType;
        XIntegerType xIntegerType2 = new XIntegerType("long", (XDataType) xIntegerType, 8, IntNum.make(Long.MIN_VALUE), IntNum.make(LocationRequestCompat.PASSIVE_INTERVAL));
        longType = xIntegerType2;
        XIntegerType xIntegerType3 = new XIntegerType("int", (XDataType) xIntegerType2, 9, IntNum.make(Integer.MIN_VALUE), IntNum.make(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
        intType = xIntegerType3;
        XIntegerType xIntegerType4 = new XIntegerType("short", (XDataType) xIntegerType3, 10, IntNum.make(-32768), IntNum.make(32767));
        shortType = xIntegerType4;
        byteType = new XIntegerType("byte", (XDataType) xIntegerType4, 11, IntNum.make(-128), IntNum.make(127));
        XIntegerType xIntegerType5 = new XIntegerType("nonPositiveInteger", (XDataType) xIntegerType, 6, (IntNum) null, IntNum.zero());
        nonPositiveIntegerType = xIntegerType5;
        negativeIntegerType = new XIntegerType("negativeInteger", (XDataType) xIntegerType5, 7, (IntNum) null, IntNum.minusOne());
        XIntegerType xIntegerType6 = new XIntegerType("nonNegativeInteger", (XDataType) xIntegerType, 12, IntNum.zero(), (IntNum) null);
        nonNegativeIntegerType = xIntegerType6;
        XIntegerType xIntegerType7 = new XIntegerType("unsignedLong", (XDataType) xIntegerType6, 13, IntNum.zero(), IntNum.valueOf("18446744073709551615"));
        unsignedLongType = xIntegerType7;
        XIntegerType xIntegerType8 = new XIntegerType("unsignedInt", (XDataType) xIntegerType7, 14, IntNum.zero(), IntNum.make(4294967295L));
        unsignedIntType = xIntegerType8;
        XIntegerType xIntegerType9 = new XIntegerType("unsignedShort", (XDataType) xIntegerType8, 15, IntNum.zero(), IntNum.make(SupportMenu.USER_MASK));
        unsignedShortType = xIntegerType9;
        unsignedByteType = new XIntegerType("unsignedByte", (XDataType) xIntegerType9, 16, IntNum.zero(), IntNum.make(255));
        positiveIntegerType = new XIntegerType("positiveInteger", (XDataType) xIntegerType6, 17, IntNum.one(), (IntNum) null);
    }

    public boolean isUnsignedType() {
        return this.isUnsignedType;
    }

    public XIntegerType(String name, XDataType base, int typeCode, IntNum min, IntNum max) {
        this((Object) name, base, typeCode, min, max);
        this.isUnsignedType = name.startsWith("unsigned");
    }

    public XIntegerType(Object name, XDataType base, int typeCode, IntNum min, IntNum max) {
        super(name, typeIntNum, typeCode);
        this.minValue = min;
        this.maxValue = max;
        this.baseType = base;
    }

    @Override // gnu.kawa.xml.XDataType, gnu.bytecode.Type
    public boolean isInstance(Object obj) {
        if (!(obj instanceof IntNum)) {
            return false;
        }
        XDataType objType = integerType;
        if (this == objType) {
            return true;
        }
        if (obj instanceof XInteger) {
            objType = ((XInteger) obj).getIntegerType();
        }
        while (objType != null) {
            if (objType == this) {
                return true;
            }
            objType = objType.baseType;
        }
        return false;
    }

    @Override // gnu.kawa.xml.XDataType, gnu.bytecode.Type
    public Object coerceFromObject(Object obj) {
        return valueOf((IntNum) obj);
    }

    public IntNum valueOf(IntNum value) {
        IntNum intNum;
        if (this != integerType) {
            IntNum intNum2 = this.minValue;
            if ((intNum2 != null && IntNum.compare(value, intNum2) < 0) || ((intNum = this.maxValue) != null && IntNum.compare(value, intNum) > 0)) {
                throw new ClassCastException("cannot cast " + value + " to " + this.name);
            }
            return new XInteger(value, this);
        }
        return value;
    }

    @Override // gnu.kawa.xml.XDataType
    public Object cast(Object value) {
        if (value instanceof Boolean) {
            return valueOf(((Boolean) value).booleanValue() ? IntNum.one() : IntNum.zero());
        }
        if (value instanceof IntNum) {
            return valueOf((IntNum) value);
        }
        if (value instanceof BigDecimal) {
            return valueOf(Arithmetic.asIntNum((BigDecimal) value));
        }
        if (value instanceof RealNum) {
            return valueOf(((RealNum) value).toExactInt(3));
        }
        if (value instanceof Number) {
            return valueOf(RealNum.toExactInt(((Number) value).doubleValue(), 3));
        }
        return super.cast(value);
    }

    @Override // gnu.kawa.xml.XDataType
    public Object valueOf(String value) {
        return valueOf(IntNum.valueOf(value.trim(), 10));
    }

    public IntNum valueOf(String value, int radix) {
        return valueOf(IntNum.valueOf(value.trim(), radix));
    }
}
