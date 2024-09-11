package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Arrays;
import gnu.lists.Array;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

/* compiled from: arrays.scm */
/* loaded from: classes.dex */
public class arrays extends ModuleBody {
    public static final Class $Lsarray$Gr;
    public static final arrays $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod array;
    public static final ModuleMethod array$Mnend;
    public static final ModuleMethod array$Mnrank;
    public static final ModuleMethod array$Mnstart;
    public static final ModuleMethod array$Qu;
    public static final ModuleMethod make$Mnarray;
    public static final ModuleMethod shape;
    public static final ModuleMethod share$Mnarray;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("share-array").readResolve();
        Lit7 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("array-end").readResolve();
        Lit6 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("array-start").readResolve();
        Lit5 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("array-rank").readResolve();
        Lit4 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("array").readResolve();
        Lit3 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("make-array").readResolve();
        Lit2 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("shape").readResolve();
        Lit1 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("array?").readResolve();
        Lit0 = simpleSymbol8;
        $Lsarray$Gr = Array.class;
        arrays arraysVar = new arrays();
        $instance = arraysVar;
        array$Qu = new ModuleMethod(arraysVar, 1, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        shape = new ModuleMethod(arraysVar, 2, simpleSymbol7, -4096);
        make$Mnarray = new ModuleMethod(arraysVar, 3, simpleSymbol6, 8193);
        array = new ModuleMethod(arraysVar, 5, simpleSymbol5, -4095);
        array$Mnrank = new ModuleMethod(arraysVar, 6, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        array$Mnstart = new ModuleMethod(arraysVar, 7, simpleSymbol3, 8194);
        array$Mnend = new ModuleMethod(arraysVar, 8, simpleSymbol2, 8194);
        share$Mnarray = new ModuleMethod(arraysVar, 9, simpleSymbol, 12291);
        arraysVar.run();
    }

    public arrays() {
        ModuleInfo.register(this);
    }

    public static Array makeArray(Array array2) {
        return makeArray(array2, null);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    public static boolean isArray(Object x) {
        return x instanceof Array;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
                if (!(obj instanceof Array)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                if (!(obj instanceof Array)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static Array shape(Object... args) {
        return Arrays.shape(args);
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 5:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    public static Array makeArray(Array shape2, Object obj) {
        return Arrays.make(shape2, obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 3:
                if (!(obj instanceof Array)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 7:
                if (!(obj instanceof Array)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 8:
                if (!(obj instanceof Array)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Array array(Array shape2, Object... vals) {
        return Arrays.makeSimple(shape2, new FVector(vals));
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 2:
                return shape(objArr);
            case 5:
                Object obj = objArr[0];
                try {
                    Array array2 = (Array) obj;
                    int length = objArr.length - 1;
                    Object[] objArr2 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return array(array2, objArr2);
                        }
                        objArr2[length] = objArr[length + 1];
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "array", 1, obj);
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    public static int arrayRank(Array array2) {
        return array2.rank();
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isArray(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                try {
                    return makeArray((Array) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-array", 1, obj);
                }
            case 6:
                try {
                    return Integer.valueOf(arrayRank((Array) obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "array-rank", 1, obj);
                }
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static int arrayStart(Array array2, int k) {
        return array2.getLowBound(k);
    }

    public static int arrayEnd(Array array2, int k) {
        return array2.getLowBound(k) + array2.getSize(k);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 3:
                try {
                    return makeArray((Array) obj, obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-array", 1, obj);
                }
            case 7:
                try {
                    try {
                        return Integer.valueOf(arrayStart((Array) obj, ((Number) obj2).intValue()));
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "array-start", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "array-start", 1, obj);
                }
            case 8:
                try {
                    try {
                        return Integer.valueOf(arrayEnd((Array) obj, ((Number) obj2).intValue()));
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "array-end", 2, obj2);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "array-end", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static Array shareArray(Array array2, Array shape2, Procedure mapper) {
        return Arrays.shareArray(array2, shape2, mapper);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector != 9) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        }
        try {
            try {
                try {
                    return shareArray((Array) obj, (Array) obj2, (Procedure) obj3);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "share-array", 3, obj3);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "share-array", 2, obj2);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "share-array", 1, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 9) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        if (!(obj instanceof Array)) {
            return -786431;
        }
        callContext.value1 = obj;
        if (!(obj2 instanceof Array)) {
            return -786430;
        }
        callContext.value2 = obj2;
        if (!(obj3 instanceof Procedure)) {
            return -786429;
        }
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }
}
