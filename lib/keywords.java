package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

/* compiled from: keywords.scm */
/* loaded from: classes.dex */
public class keywords extends ModuleBody {
    public static final keywords $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod keyword$Mn$Grstring;
    public static final ModuleMethod keyword$Qu;
    public static final ModuleMethod string$Mn$Grkeyword;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("string->keyword").readResolve();
        Lit2 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("keyword->string").readResolve();
        Lit1 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("keyword?").readResolve();
        Lit0 = simpleSymbol3;
        keywords keywordsVar = new keywords();
        $instance = keywordsVar;
        keyword$Qu = new ModuleMethod(keywordsVar, 1, simpleSymbol3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        keyword$Mn$Grstring = new ModuleMethod(keywordsVar, 2, simpleSymbol2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mn$Grkeyword = new ModuleMethod(keywordsVar, 3, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        keywordsVar.run();
    }

    public keywords() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    public static boolean isKeyword(Object object) {
        return Keyword.isKeyword(object);
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
                if (!(obj instanceof Keyword)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isKeyword(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return ((Keyword) obj).getName();
                } catch (ClassCastException e) {
                    throw new WrongType(e, "keyword->string", 1, obj);
                }
            case 3:
                return Keyword.make(obj == null ? null : obj.toString());
            default:
                return super.apply1(moduleMethod, obj);
        }
    }
}
