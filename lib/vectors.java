package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

/* compiled from: vectors.scm */
/* loaded from: classes.dex */
public class vectors extends ModuleBody {
    public static final vectors $instance;
    static final Keyword Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    public static final ModuleMethod list$Mn$Grvector;
    public static final ModuleMethod make$Mnvector;
    public static final ModuleMethod vector$Mn$Grlist;
    public static final ModuleMethod vector$Mnfill$Ex;
    public static final ModuleMethod vector$Mnlength;
    public static final GenericProc vector$Mnref = null;
    static final ModuleMethod vector$Mnref$Fn1;
    public static final ModuleMethod vector$Mnset$Ex;
    public static final ModuleMethod vector$Qu;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("vector-fill!").readResolve();
        Lit8 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("list->vector").readResolve();
        Lit7 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("vector->list").readResolve();
        Lit6 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("vector-ref").readResolve();
        Lit5 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("vector-set!").readResolve();
        Lit4 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("vector-length").readResolve();
        Lit3 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("make-vector").readResolve();
        Lit2 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("vector?").readResolve();
        Lit1 = simpleSymbol8;
        Lit0 = Keyword.make("setter");
        vectors vectorsVar = new vectors();
        $instance = vectorsVar;
        vector$Qu = new ModuleMethod(vectorsVar, 1, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnvector = new ModuleMethod(vectorsVar, 2, simpleSymbol7, 8193);
        vector$Mnlength = new ModuleMethod(vectorsVar, 4, simpleSymbol6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        vector$Mnset$Ex = new ModuleMethod(vectorsVar, 5, simpleSymbol5, 12291);
        vector$Mnref$Fn1 = new ModuleMethod(vectorsVar, 6, simpleSymbol4, 8194);
        vector$Mn$Grlist = new ModuleMethod(vectorsVar, 7, simpleSymbol3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grvector = new ModuleMethod(vectorsVar, 8, simpleSymbol2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        vector$Mnfill$Ex = new ModuleMethod(vectorsVar, 9, simpleSymbol, 8194);
        vectorsVar.run();
    }

    public vectors() {
        ModuleInfo.register(this);
    }

    public static FVector makeVector(int i) {
        return makeVector(i, Special.undefined);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        GenericProc genericProc = new GenericProc("vector-ref");
        vector$Mnref = genericProc;
        genericProc.setProperties(new Object[]{Lit0, vector$Mnset$Ex, vector$Mnref$Fn1});
    }

    public static boolean isVector(Object x) {
        return x instanceof FVector;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
            case 5:
            case 6:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 4:
                if (!(obj instanceof FVector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                if (!(obj instanceof FVector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
    }

    public static FVector makeVector(int k, Object fill) {
        return new FVector(k, fill);
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 6:
                if (!(obj instanceof FVector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 9:
                if (!(obj instanceof FVector)) {
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

    public static int vectorLength(FVector x) {
        return x.size();
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector != 5) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        }
        try {
            try {
                ((FVector) obj).set(((Number) obj2).intValue(), obj3);
                return Values.empty;
            } catch (ClassCastException e) {
                throw new WrongType(e, "vector-set!", 2, obj2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "vector-set!", 1, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 5) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        if (!(obj instanceof FVector)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }

    public static Object vectorRef(FVector vector, int k) {
        return vector.get(k);
    }

    public static LList vector$To$List(FVector vec) {
        LList result = LList.Empty;
        int i = vectorLength(vec);
        while (true) {
            i--;
            if (i < 0) {
                return result;
            }
            result = lists.cons(vector$Mnref.apply2(vec, Integer.valueOf(i)), result);
        }
    }

    public static FVector list$To$Vector(LList x) {
        return new FVector(x);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isVector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return makeVector(((Number) obj).intValue());
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-vector", 1, obj);
                }
            case 3:
            case 5:
            case 6:
            default:
                return super.apply1(moduleMethod, obj);
            case 4:
                try {
                    return Integer.valueOf(vectorLength((FVector) obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "vector-length", 1, obj);
                }
            case 7:
                try {
                    return vector$To$List((FVector) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "vector->list", 1, obj);
                }
            case 8:
                try {
                    return list$To$Vector((LList) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "list->vector", 1, obj);
                }
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 2:
                try {
                    return makeVector(((Number) obj).intValue(), obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-vector", 1, obj);
                }
            case 6:
                try {
                    try {
                        return vectorRef((FVector) obj, ((Number) obj2).intValue());
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "vector-ref", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "vector-ref", 1, obj);
                }
            case 9:
                try {
                    ((FVector) obj).setAll(obj2);
                    return Values.empty;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "vector-fill!", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }
}
