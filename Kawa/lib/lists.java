package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.reflect.SlotSet;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.standard.Scheme;

/* compiled from: lists.scm */
/* loaded from: classes.dex */
public class lists extends ModuleBody {
    public static final Location $Prvt$define$Mnprocedure;
    public static final lists $instance;
    static final Keyword Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod assoc;
    public static final ModuleMethod assq;
    public static final ModuleMethod assv;
    public static final GenericProc caaaar = null;
    static final ModuleMethod caaaar$Fn28;
    public static final GenericProc caaadr = null;
    static final ModuleMethod caaadr$Fn30;
    public static final GenericProc caaar = null;
    static final ModuleMethod caaar$Fn12;
    public static final GenericProc caadar = null;
    static final ModuleMethod caadar$Fn32;
    public static final GenericProc caaddr = null;
    static final ModuleMethod caaddr$Fn34;
    public static final GenericProc caadr = null;
    static final ModuleMethod caadr$Fn14;
    public static final GenericProc caar = null;
    static final ModuleMethod caar$Fn4;
    public static final GenericProc cadaar = null;
    static final ModuleMethod cadaar$Fn36;
    public static final GenericProc cadadr = null;
    static final ModuleMethod cadadr$Fn38;
    public static final GenericProc cadar = null;
    static final ModuleMethod cadar$Fn16;
    public static final GenericProc caddar = null;
    static final ModuleMethod caddar$Fn40;
    public static final GenericProc cadddr = null;
    static final ModuleMethod cadddr$Fn42;
    public static final GenericProc caddr = null;
    static final ModuleMethod caddr$Fn18;
    public static final GenericProc cadr = null;
    static final ModuleMethod cadr$Fn6;
    public static final GenericProc car = null;
    static final ModuleMethod car$Fn1;
    public static final GenericProc cdaaar = null;
    static final ModuleMethod cdaaar$Fn44;
    public static final GenericProc cdaadr = null;
    static final ModuleMethod cdaadr$Fn46;
    public static final GenericProc cdaar = null;
    static final ModuleMethod cdaar$Fn20;
    public static final GenericProc cdadar = null;
    static final ModuleMethod cdadar$Fn48;
    public static final GenericProc cdaddr = null;
    static final ModuleMethod cdaddr$Fn50;
    public static final GenericProc cdadr = null;
    static final ModuleMethod cdadr$Fn22;
    public static final GenericProc cdar = null;
    static final ModuleMethod cdar$Fn8;
    public static final GenericProc cddaar = null;
    static final ModuleMethod cddaar$Fn52;
    public static final GenericProc cddadr = null;
    static final ModuleMethod cddadr$Fn54;
    public static final GenericProc cddar = null;
    static final ModuleMethod cddar$Fn24;
    public static final GenericProc cdddar = null;
    static final ModuleMethod cdddar$Fn56;
    public static final GenericProc cddddr = null;
    static final ModuleMethod cddddr$Fn58;
    public static final GenericProc cdddr = null;
    static final ModuleMethod cdddr$Fn26;
    public static final GenericProc cddr = null;
    static final ModuleMethod cddr$Fn10;
    public static final GenericProc cdr = null;
    static final ModuleMethod cdr$Fn2;
    public static final ModuleMethod cons;
    static final ModuleMethod lambda$Fn11;
    static final ModuleMethod lambda$Fn13;
    static final ModuleMethod lambda$Fn15;
    static final ModuleMethod lambda$Fn17;
    static final ModuleMethod lambda$Fn19;
    static final ModuleMethod lambda$Fn21;
    static final ModuleMethod lambda$Fn23;
    static final ModuleMethod lambda$Fn25;
    static final ModuleMethod lambda$Fn27;
    static final ModuleMethod lambda$Fn29;
    static final ModuleMethod lambda$Fn3;
    static final ModuleMethod lambda$Fn31;
    static final ModuleMethod lambda$Fn33;
    static final ModuleMethod lambda$Fn35;
    static final ModuleMethod lambda$Fn37;
    static final ModuleMethod lambda$Fn39;
    static final ModuleMethod lambda$Fn41;
    static final ModuleMethod lambda$Fn43;
    static final ModuleMethod lambda$Fn45;
    static final ModuleMethod lambda$Fn47;
    static final ModuleMethod lambda$Fn49;
    static final ModuleMethod lambda$Fn5;
    static final ModuleMethod lambda$Fn51;
    static final ModuleMethod lambda$Fn53;
    static final ModuleMethod lambda$Fn55;
    static final ModuleMethod lambda$Fn57;
    static final ModuleMethod lambda$Fn7;
    static final ModuleMethod lambda$Fn9;
    public static final ModuleMethod length;
    public static final ModuleMethod list$Mnref;
    public static final ModuleMethod list$Mntail;
    public static final ModuleMethod list$Qu;
    public static final ModuleMethod member;
    public static final ModuleMethod memq;
    public static final ModuleMethod memv;
    public static final ModuleMethod null$Qu;
    public static final ModuleMethod pair$Qu;
    public static final ModuleMethod reverse;
    public static final ModuleMethod reverse$Ex;
    public static final ModuleMethod set$Mncar$Ex;
    public static final ModuleMethod set$Mncdr$Ex;

    public lists() {
        ModuleInfo.register(this);
    }

    public static Object assoc(Object obj, Object obj2) {
        return assoc(obj, obj2, Scheme.isEqual);
    }

    public static Object member(Object obj, Object obj2) {
        return member(obj, obj2, Scheme.isEqual);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        GenericProc genericProc = new GenericProc("car");
        car = genericProc;
        Keyword keyword = Lit0;
        genericProc.setProperties(new Object[]{keyword, set$Mncar$Ex, car$Fn1});
        GenericProc genericProc2 = new GenericProc("cdr");
        cdr = genericProc2;
        genericProc2.setProperties(new Object[]{keyword, set$Mncdr$Ex, cdr$Fn2});
        GenericProc genericProc3 = new GenericProc("caar");
        caar = genericProc3;
        genericProc3.setProperties(new Object[]{keyword, lambda$Fn3, caar$Fn4});
        GenericProc genericProc4 = new GenericProc("cadr");
        cadr = genericProc4;
        genericProc4.setProperties(new Object[]{keyword, lambda$Fn5, cadr$Fn6});
        GenericProc genericProc5 = new GenericProc("cdar");
        cdar = genericProc5;
        genericProc5.setProperties(new Object[]{keyword, lambda$Fn7, cdar$Fn8});
        GenericProc genericProc6 = new GenericProc("cddr");
        cddr = genericProc6;
        genericProc6.setProperties(new Object[]{keyword, lambda$Fn9, cddr$Fn10});
        GenericProc genericProc7 = new GenericProc("caaar");
        caaar = genericProc7;
        genericProc7.setProperties(new Object[]{keyword, lambda$Fn11, caaar$Fn12});
        GenericProc genericProc8 = new GenericProc("caadr");
        caadr = genericProc8;
        genericProc8.setProperties(new Object[]{keyword, lambda$Fn13, caadr$Fn14});
        GenericProc genericProc9 = new GenericProc("cadar");
        cadar = genericProc9;
        genericProc9.setProperties(new Object[]{keyword, lambda$Fn15, cadar$Fn16});
        GenericProc genericProc10 = new GenericProc("caddr");
        caddr = genericProc10;
        genericProc10.setProperties(new Object[]{keyword, lambda$Fn17, caddr$Fn18});
        GenericProc genericProc11 = new GenericProc("cdaar");
        cdaar = genericProc11;
        genericProc11.setProperties(new Object[]{keyword, lambda$Fn19, cdaar$Fn20});
        GenericProc genericProc12 = new GenericProc("cdadr");
        cdadr = genericProc12;
        genericProc12.setProperties(new Object[]{keyword, lambda$Fn21, cdadr$Fn22});
        GenericProc genericProc13 = new GenericProc("cddar");
        cddar = genericProc13;
        genericProc13.setProperties(new Object[]{keyword, lambda$Fn23, cddar$Fn24});
        GenericProc genericProc14 = new GenericProc("cdddr");
        cdddr = genericProc14;
        genericProc14.setProperties(new Object[]{keyword, lambda$Fn25, cdddr$Fn26});
        GenericProc genericProc15 = new GenericProc("caaaar");
        caaaar = genericProc15;
        genericProc15.setProperties(new Object[]{keyword, lambda$Fn27, caaaar$Fn28});
        GenericProc genericProc16 = new GenericProc("caaadr");
        caaadr = genericProc16;
        genericProc16.setProperties(new Object[]{keyword, lambda$Fn29, caaadr$Fn30});
        GenericProc genericProc17 = new GenericProc("caadar");
        caadar = genericProc17;
        genericProc17.setProperties(new Object[]{keyword, lambda$Fn31, caadar$Fn32});
        GenericProc genericProc18 = new GenericProc("caaddr");
        caaddr = genericProc18;
        genericProc18.setProperties(new Object[]{keyword, lambda$Fn33, caaddr$Fn34});
        GenericProc genericProc19 = new GenericProc("cadaar");
        cadaar = genericProc19;
        genericProc19.setProperties(new Object[]{keyword, lambda$Fn35, cadaar$Fn36});
        GenericProc genericProc20 = new GenericProc("cadadr");
        cadadr = genericProc20;
        genericProc20.setProperties(new Object[]{keyword, lambda$Fn37, cadadr$Fn38});
        GenericProc genericProc21 = new GenericProc("caddar");
        caddar = genericProc21;
        genericProc21.setProperties(new Object[]{keyword, lambda$Fn39, caddar$Fn40});
        GenericProc genericProc22 = new GenericProc("cadddr");
        cadddr = genericProc22;
        genericProc22.setProperties(new Object[]{keyword, lambda$Fn41, cadddr$Fn42});
        GenericProc genericProc23 = new GenericProc("cdaaar");
        cdaaar = genericProc23;
        genericProc23.setProperties(new Object[]{keyword, lambda$Fn43, cdaaar$Fn44});
        GenericProc genericProc24 = new GenericProc("cdaadr");
        cdaadr = genericProc24;
        genericProc24.setProperties(new Object[]{keyword, lambda$Fn45, cdaadr$Fn46});
        GenericProc genericProc25 = new GenericProc("cdadar");
        cdadar = genericProc25;
        genericProc25.setProperties(new Object[]{keyword, lambda$Fn47, cdadar$Fn48});
        GenericProc genericProc26 = new GenericProc("cdaddr");
        cdaddr = genericProc26;
        genericProc26.setProperties(new Object[]{keyword, lambda$Fn49, cdaddr$Fn50});
        GenericProc genericProc27 = new GenericProc("cddaar");
        cddaar = genericProc27;
        genericProc27.setProperties(new Object[]{keyword, lambda$Fn51, cddaar$Fn52});
        GenericProc genericProc28 = new GenericProc("cddadr");
        cddadr = genericProc28;
        genericProc28.setProperties(new Object[]{keyword, lambda$Fn53, cddadr$Fn54});
        GenericProc genericProc29 = new GenericProc("cdddar");
        cdddar = genericProc29;
        genericProc29.setProperties(new Object[]{keyword, lambda$Fn55, cdddar$Fn56});
        GenericProc genericProc30 = new GenericProc("cddddr");
        cddddr = genericProc30;
        genericProc30.setProperties(new Object[]{keyword, lambda$Fn57, cddddr$Fn58});
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("assoc").readResolve();
        Lit19 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("assv").readResolve();
        Lit18 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("assq").readResolve();
        Lit17 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("member").readResolve();
        Lit16 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("memv").readResolve();
        Lit15 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("memq").readResolve();
        Lit14 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("reverse!").readResolve();
        Lit13 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("list?").readResolve();
        Lit12 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("list-ref").readResolve();
        Lit11 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("list-tail").readResolve();
        Lit10 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("reverse").readResolve();
        Lit9 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_LENGTH).readResolve();
        Lit8 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("set-cdr!").readResolve();
        Lit7 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("set-car!").readResolve();
        Lit6 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("null?").readResolve();
        Lit5 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("cons").readResolve();
        Lit4 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("pair?").readResolve();
        Lit3 = simpleSymbol17;
        Lit2 = (SimpleSymbol) new SimpleSymbol("cdr").readResolve();
        Lit1 = (SimpleSymbol) new SimpleSymbol("car").readResolve();
        Lit0 = Keyword.make("setter");
        lists listsVar = new lists();
        $instance = listsVar;
        $Prvt$define$Mnprocedure = StaticFieldLocation.make("kawa.lib.std_syntax", "define$Mnprocedure");
        pair$Qu = new ModuleMethod(listsVar, 1, simpleSymbol17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cons = new ModuleMethod(listsVar, 2, simpleSymbol16, 8194);
        null$Qu = new ModuleMethod(listsVar, 3, simpleSymbol15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        set$Mncar$Ex = new ModuleMethod(listsVar, 4, simpleSymbol14, 8194);
        set$Mncdr$Ex = new ModuleMethod(listsVar, 5, simpleSymbol13, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(listsVar, 6, "car", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/lists.scm:31");
        car$Fn1 = moduleMethod;
        ModuleMethod moduleMethod2 = new ModuleMethod(listsVar, 7, "cdr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/lists.scm:36");
        cdr$Fn2 = moduleMethod2;
        lambda$Fn3 = new ModuleMethod(listsVar, 8, null, 8194);
        caar$Fn4 = new ModuleMethod(listsVar, 9, "caar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn5 = new ModuleMethod(listsVar, 10, null, 8194);
        cadr$Fn6 = new ModuleMethod(listsVar, 11, "cadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn7 = new ModuleMethod(listsVar, 12, null, 8194);
        cdar$Fn8 = new ModuleMethod(listsVar, 13, "cdar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn9 = new ModuleMethod(listsVar, 14, null, 8194);
        cddr$Fn10 = new ModuleMethod(listsVar, 15, "cddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn11 = new ModuleMethod(listsVar, 16, null, 8194);
        caaar$Fn12 = new ModuleMethod(listsVar, 17, "caaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn13 = new ModuleMethod(listsVar, 18, null, 8194);
        caadr$Fn14 = new ModuleMethod(listsVar, 19, "caadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn15 = new ModuleMethod(listsVar, 20, null, 8194);
        cadar$Fn16 = new ModuleMethod(listsVar, 21, "cadar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn17 = new ModuleMethod(listsVar, 22, null, 8194);
        caddr$Fn18 = new ModuleMethod(listsVar, 23, "caddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn19 = new ModuleMethod(listsVar, 24, null, 8194);
        cdaar$Fn20 = new ModuleMethod(listsVar, 25, "cdaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn21 = new ModuleMethod(listsVar, 26, null, 8194);
        cdadr$Fn22 = new ModuleMethod(listsVar, 27, "cdadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn23 = new ModuleMethod(listsVar, 28, null, 8194);
        cddar$Fn24 = new ModuleMethod(listsVar, 29, "cddar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn25 = new ModuleMethod(listsVar, 30, null, 8194);
        cdddr$Fn26 = new ModuleMethod(listsVar, 31, "cdddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn27 = new ModuleMethod(listsVar, 32, null, 8194);
        caaaar$Fn28 = new ModuleMethod(listsVar, 33, "caaaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn29 = new ModuleMethod(listsVar, 34, null, 8194);
        caaadr$Fn30 = new ModuleMethod(listsVar, 35, "caaadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn31 = new ModuleMethod(listsVar, 36, null, 8194);
        caadar$Fn32 = new ModuleMethod(listsVar, 37, "caadar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn33 = new ModuleMethod(listsVar, 38, null, 8194);
        caaddr$Fn34 = new ModuleMethod(listsVar, 39, "caaddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn35 = new ModuleMethod(listsVar, 40, null, 8194);
        cadaar$Fn36 = new ModuleMethod(listsVar, 41, "cadaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn37 = new ModuleMethod(listsVar, 42, null, 8194);
        cadadr$Fn38 = new ModuleMethod(listsVar, 43, "cadadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn39 = new ModuleMethod(listsVar, 44, null, 8194);
        caddar$Fn40 = new ModuleMethod(listsVar, 45, "caddar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn41 = new ModuleMethod(listsVar, 46, null, 8194);
        cadddr$Fn42 = new ModuleMethod(listsVar, 47, "cadddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn43 = new ModuleMethod(listsVar, 48, null, 8194);
        cdaaar$Fn44 = new ModuleMethod(listsVar, 49, "cdaaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn45 = new ModuleMethod(listsVar, 50, null, 8194);
        cdaadr$Fn46 = new ModuleMethod(listsVar, 51, "cdaadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn47 = new ModuleMethod(listsVar, 52, null, 8194);
        cdadar$Fn48 = new ModuleMethod(listsVar, 53, "cdadar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn49 = new ModuleMethod(listsVar, 54, null, 8194);
        cdaddr$Fn50 = new ModuleMethod(listsVar, 55, "cdaddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn51 = new ModuleMethod(listsVar, 56, null, 8194);
        cddaar$Fn52 = new ModuleMethod(listsVar, 57, "cddaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn53 = new ModuleMethod(listsVar, 58, null, 8194);
        cddadr$Fn54 = new ModuleMethod(listsVar, 59, "cddadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn55 = new ModuleMethod(listsVar, 60, null, 8194);
        cdddar$Fn56 = new ModuleMethod(listsVar, 61, "cdddar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn57 = new ModuleMethod(listsVar, 62, null, 8194);
        cddddr$Fn58 = new ModuleMethod(listsVar, 63, "cddddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        length = new ModuleMethod(listsVar, 64, simpleSymbol12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        reverse = new ModuleMethod(listsVar, 65, simpleSymbol11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mntail = new ModuleMethod(listsVar, 66, simpleSymbol10, 8194);
        list$Mnref = new ModuleMethod(listsVar, 67, simpleSymbol9, 8194);
        list$Qu = new ModuleMethod(listsVar, 68, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        reverse$Ex = new ModuleMethod(listsVar, 69, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        memq = new ModuleMethod(listsVar, 70, simpleSymbol6, 8194);
        memv = new ModuleMethod(listsVar, 71, simpleSymbol5, 8194);
        member = new ModuleMethod(listsVar, 72, simpleSymbol4, 12290);
        assq = new ModuleMethod(listsVar, 74, simpleSymbol3, 8194);
        assv = new ModuleMethod(listsVar, 75, simpleSymbol2, 8194);
        assoc = new ModuleMethod(listsVar, 76, simpleSymbol, 12290);
        listsVar.run();
    }

    public static boolean isPair(Object x) {
        return x instanceof Pair;
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
            case 4:
            case 5:
            case 8:
            case 10:
            case 12:
            case 14:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 28:
            case 30:
            case 32:
            case 34:
            case 36:
            case 38:
            case 40:
            case 42:
            case 44:
            case 46:
            case 48:
            case 50:
            case 52:
            case 54:
            case 56:
            case 58:
            case 60:
            case 62:
            case 66:
            case 67:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                if (!(obj instanceof Pair)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                if (!(obj instanceof Pair)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
            case 11:
            case 13:
            case 15:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 27:
            case 29:
            case 31:
            case 33:
            case 35:
            case 37:
            case 39:
            case 41:
            case 43:
            case 45:
            case 47:
            case 49:
            case 51:
            case 53:
            case 55:
            case 57:
            case 59:
            case 61:
            case 63:
                break;
            case 64:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                break;
            case 65:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 68:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 69:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static Pair cons(Object car2, Object cdr2) {
        return new Pair(car2, cdr2);
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
            case 3:
            case 6:
            case 7:
            case 9:
            case 11:
            case 13:
            case 15:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 27:
            case 29:
            case 31:
            case 33:
            case 35:
            case 37:
            case 39:
            case 41:
            case 43:
            case 45:
            case 47:
            case 49:
            case 51:
            case 53:
            case 55:
            case 57:
            case 59:
            case 61:
            case 63:
            case 64:
            case 65:
            case 68:
            case 69:
            case 73:
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
            case 4:
                if (!(obj instanceof Pair)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 5:
                if (!(obj instanceof Pair)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 8:
            case 10:
            case 12:
            case 14:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 28:
            case 30:
            case 32:
            case 34:
            case 36:
            case 38:
            case 40:
            case 42:
            case 44:
            case 46:
            case 48:
            case 50:
            case 52:
            case 54:
            case 56:
            case 58:
            case 60:
            case 62:
            case 66:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 67:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 70:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 71:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 72:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 74:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 75:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 76:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
        }
    }

    public static boolean isNull(Object x) {
        return x == LList.Empty;
    }

    static Object car(Pair x) {
        return x.getCar();
    }

    static Object cdr(Pair x) {
        return x.getCdr();
    }

    static Object caar(Object arg) {
        return ((Pair) ((Pair) arg).getCar()).getCar();
    }

    static void lambda1(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCar(), Lit1, value);
    }

    static Object cadr(Object arg) {
        return ((Pair) ((Pair) arg).getCdr()).getCar();
    }

    static void lambda2(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCdr(), Lit1, value);
    }

    static Object cdar(Object arg) {
        return ((Pair) ((Pair) arg).getCar()).getCdr();
    }

    static void lambda3(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCar(), Lit2, value);
    }

    static Object cddr(Object arg) {
        return ((Pair) ((Pair) arg).getCdr()).getCdr();
    }

    static void lambda4(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCdr(), Lit2, value);
    }

    static Object caaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar();
    }

    static void lambda5(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCar(), Lit1, value);
    }

    static Object caadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar();
    }

    static void lambda6(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCar(), Lit1, value);
    }

    static Object cadar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar();
    }

    static void lambda7(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCdr(), Lit1, value);
    }

    static Object caddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar();
    }

    static void lambda8(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCdr(), Lit1, value);
    }

    static Object cdaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr();
    }

    static void lambda9(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCar(), Lit2, value);
    }

    static Object cdadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr();
    }

    static void lambda10(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCar(), Lit2, value);
    }

    static Object cddar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr();
    }

    static void lambda11(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCdr(), Lit2, value);
    }

    static Object cdddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr();
    }

    static void lambda12(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCdr(), Lit2, value);
    }

    static Object caaaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar()).getCar();
    }

    static void lambda13(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar(), Lit1, value);
    }

    static Object caaadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar()).getCar();
    }

    static void lambda14(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar(), Lit1, value);
    }

    static Object caadar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar()).getCar();
    }

    static void lambda15(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar(), Lit1, value);
    }

    static Object caaddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar()).getCar();
    }

    static void lambda16(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar(), Lit1, value);
    }

    static Object cadaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr()).getCar();
    }

    static void lambda17(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr(), Lit1, value);
    }

    static Object cadadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr()).getCar();
    }

    static void lambda18(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr(), Lit1, value);
    }

    static Object caddar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr()).getCar();
    }

    static void lambda19(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr(), Lit1, value);
    }

    static Object cadddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr()).getCar();
    }

    static void lambda20(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr(), Lit1, value);
    }

    static Object cdaaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar()).getCdr();
    }

    static void lambda21(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar(), Lit2, value);
    }

    static Object cdaadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar()).getCdr();
    }

    static void lambda22(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar(), Lit2, value);
    }

    static Object cdadar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar()).getCdr();
    }

    static void lambda23(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar(), Lit2, value);
    }

    static Object cdaddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar()).getCdr();
    }

    static void lambda24(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar(), Lit2, value);
    }

    static Object cddaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr()).getCdr();
    }

    static void lambda25(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr(), Lit2, value);
    }

    static Object cddadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr()).getCdr();
    }

    static void lambda26(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr(), Lit2, value);
    }

    static Object cdddar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr()).getCdr();
    }

    static void lambda27(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr(), Lit2, value);
    }

    static Object cddddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr()).getCdr();
    }

    static void lambda28(Object arg, Object value) {
        SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr(), Lit2, value);
    }

    public static int length(LList list) {
        return LList.length(list);
    }

    public static LList reverse(LList list) {
        Object result = LList.Empty;
        Object arg = list;
        while (!isNull(arg)) {
            try {
                Pair pair = (Pair) arg;
                arg = cdr.apply1(pair);
                result = cons(car.apply1(pair), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "pair", -2, arg);
            }
        }
        return (LList) result;
    }

    public static Object listTail(Object list, int count) {
        return LList.listTail(list, count);
    }

    public static Object listRef(Object list, int index) {
        return car.apply1(listTail(list, index));
    }

    public static boolean isList(Object obj) {
        return LList.listLength(obj, false) >= 0;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isPair(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
            case 4:
            case 5:
            case 8:
            case 10:
            case 12:
            case 14:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 28:
            case 30:
            case 32:
            case 34:
            case 36:
            case 38:
            case 40:
            case 42:
            case 44:
            case 46:
            case 48:
            case 50:
            case 52:
            case 54:
            case 56:
            case 58:
            case 60:
            case 62:
            case 66:
            case 67:
            default:
                return super.apply1(moduleMethod, obj);
            case 3:
                return isNull(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                try {
                    return car((Pair) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "car", 1, obj);
                }
            case 7:
                try {
                    return cdr((Pair) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "cdr", 1, obj);
                }
            case 9:
                return caar(obj);
            case 11:
                return cadr(obj);
            case 13:
                return cdar(obj);
            case 15:
                return cddr(obj);
            case 17:
                return caaar(obj);
            case 19:
                return caadr(obj);
            case 21:
                return cadar(obj);
            case 23:
                return caddr(obj);
            case 25:
                return cdaar(obj);
            case 27:
                return cdadr(obj);
            case 29:
                return cddar(obj);
            case 31:
                return cdddr(obj);
            case 33:
                return caaaar(obj);
            case 35:
                return caaadr(obj);
            case 37:
                return caadar(obj);
            case 39:
                return caaddr(obj);
            case 41:
                return cadaar(obj);
            case 43:
                return cadadr(obj);
            case 45:
                return caddar(obj);
            case 47:
                return cadddr(obj);
            case 49:
                return cdaaar(obj);
            case 51:
                return cdaadr(obj);
            case 53:
                return cdadar(obj);
            case 55:
                return cdaddr(obj);
            case 57:
                return cddaar(obj);
            case 59:
                return cddadr(obj);
            case 61:
                return cdddar(obj);
            case 63:
                return cddddr(obj);
            case 64:
                try {
                    return Integer.valueOf(length((LList) obj));
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, obj);
                }
            case 65:
                try {
                    return reverse((LList) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "reverse", 1, obj);
                }
            case 68:
                return isList(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 69:
                try {
                    return LList.reverseInPlace((LList) obj);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "reverse!", 1, obj);
                }
        }
    }

    public static Object memq(Object x, Object list) {
        Object lst = list;
        while (true) {
            boolean x2 = lst instanceof Pair;
            if (!x2) {
                Object lst2 = x2 ? Boolean.TRUE : Boolean.FALSE;
                return lst2;
            }
            try {
                Pair p = (Pair) lst;
                if (x == p.getCar()) {
                    return lst;
                }
                lst = p.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "p", -2, lst);
            }
        }
    }

    public static Object memv(Object x, Object list) {
        Object lst = list;
        while (true) {
            boolean x2 = lst instanceof Pair;
            if (!x2) {
                Object lst2 = x2 ? Boolean.TRUE : Boolean.FALSE;
                return lst2;
            }
            try {
                Pair p = (Pair) lst;
                if (Scheme.isEqv.apply2(x, p.getCar()) != Boolean.FALSE) {
                    return lst;
                }
                lst = p.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "p", -2, lst);
            }
        }
    }

    public static Object member(Object x, Object list, Procedure test) {
        Object lst = list;
        while (true) {
            boolean x2 = lst instanceof Pair;
            if (!x2) {
                Object lst2 = x2 ? Boolean.TRUE : Boolean.FALSE;
                return lst2;
            }
            try {
                Pair p = (Pair) lst;
                if (test.apply2(x, p.getCar()) != Boolean.FALSE) {
                    return lst;
                }
                lst = p.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "p", -2, lst);
            }
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 72:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                if (!(obj3 instanceof Procedure)) {
                    return -786429;
                }
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 76:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                if (!(obj3 instanceof Procedure)) {
                    return -786429;
                }
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
    }

    public static Object assq(Object x, Object list) {
        for (Object list2 = list; list2 != LList.Empty; list2 = cdr.apply1(list2)) {
            Object apply1 = car.apply1(list2);
            try {
                Pair pair = (Pair) apply1;
                if (pair.getCar() == x) {
                    return pair;
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "pair", -2, apply1);
            }
        }
        return Boolean.FALSE;
    }

    public static Object assv(Object x, Object list) {
        for (Object list2 = list; list2 != LList.Empty; list2 = cdr.apply1(list2)) {
            Object apply1 = car.apply1(list2);
            try {
                Pair pair = (Pair) apply1;
                if (Scheme.isEqv.apply2(pair.getCar(), x) != Boolean.FALSE) {
                    return pair;
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "pair", -2, apply1);
            }
        }
        return Boolean.FALSE;
    }

    public static Object assoc(Object x, Object list, Procedure test) {
        for (Object list2 = list; list2 != LList.Empty; list2 = cdr.apply1(list2)) {
            Object apply1 = car.apply1(list2);
            try {
                Pair pair = (Pair) apply1;
                if (test.apply2(pair.getCar(), x) != Boolean.FALSE) {
                    return pair;
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "pair", -2, apply1);
            }
        }
        return Boolean.FALSE;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 2:
                return cons(obj, obj2);
            case 3:
            case 6:
            case 7:
            case 9:
            case 11:
            case 13:
            case 15:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 27:
            case 29:
            case 31:
            case 33:
            case 35:
            case 37:
            case 39:
            case 41:
            case 43:
            case 45:
            case 47:
            case 49:
            case 51:
            case 53:
            case 55:
            case 57:
            case 59:
            case 61:
            case 63:
            case 64:
            case 65:
            case 68:
            case 69:
            case 73:
            default:
                return super.apply2(moduleMethod, obj, obj2);
            case 4:
                try {
                    ((Pair) obj).setCar(obj2);
                    return Values.empty;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-car!", 1, obj);
                }
            case 5:
                try {
                    ((Pair) obj).setCdr(obj2);
                    break;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "set-cdr!", 1, obj);
                }
            case 8:
                lambda1(obj, obj2);
                break;
            case 10:
                lambda2(obj, obj2);
                break;
            case 12:
                lambda3(obj, obj2);
                break;
            case 14:
                lambda4(obj, obj2);
                break;
            case 16:
                lambda5(obj, obj2);
                break;
            case 18:
                lambda6(obj, obj2);
                break;
            case 20:
                lambda7(obj, obj2);
                break;
            case 22:
                lambda8(obj, obj2);
                break;
            case 24:
                lambda9(obj, obj2);
                break;
            case 26:
                lambda10(obj, obj2);
                break;
            case 28:
                lambda11(obj, obj2);
                break;
            case 30:
                lambda12(obj, obj2);
                break;
            case 32:
                lambda13(obj, obj2);
                break;
            case 34:
                lambda14(obj, obj2);
                break;
            case 36:
                lambda15(obj, obj2);
                break;
            case 38:
                lambda16(obj, obj2);
                break;
            case 40:
                lambda17(obj, obj2);
                break;
            case 42:
                lambda18(obj, obj2);
                break;
            case 44:
                lambda19(obj, obj2);
                break;
            case 46:
                lambda20(obj, obj2);
                break;
            case 48:
                lambda21(obj, obj2);
                break;
            case 50:
                lambda22(obj, obj2);
                break;
            case 52:
                lambda23(obj, obj2);
                break;
            case 54:
                lambda24(obj, obj2);
                break;
            case 56:
                lambda25(obj, obj2);
                break;
            case 58:
                lambda26(obj, obj2);
                break;
            case 60:
                lambda27(obj, obj2);
                break;
            case 62:
                lambda28(obj, obj2);
                break;
            case 66:
                try {
                    return listTail(obj, ((Number) obj2).intValue());
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "list-tail", 2, obj2);
                }
            case 67:
                try {
                    return listRef(obj, ((Number) obj2).intValue());
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "list-ref", 2, obj2);
                }
            case 70:
                return memq(obj, obj2);
            case 71:
                return memv(obj, obj2);
            case 72:
                return member(obj, obj2);
            case 74:
                return assq(obj, obj2);
            case 75:
                return assv(obj, obj2);
            case 76:
                return assoc(obj, obj2);
        }
        return Values.empty;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 72:
                try {
                    return member(obj, obj2, (Procedure) obj3);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "member", 3, obj3);
                }
            case 76:
                try {
                    return assoc(obj, obj2, (Procedure) obj3);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "assoc", 3, obj3);
                }
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }
}
