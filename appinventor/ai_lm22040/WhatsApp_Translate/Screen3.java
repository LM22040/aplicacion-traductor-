package appinventor.ai_lm22040.WhatsApp_Translate;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.youngandroid.runtime;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.require;
import kawa.standard.throw_name;

/* compiled from: Screen3.yail */
/* loaded from: classes2.dex */
public class Screen3 extends Form implements Runnable {
    public static Screen3 Screen3;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public Button Button1;
    public Label Label1;
    public Label Label2;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public LList components$Mnto$Mncreate;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public LList events$Mnto$Mnregister;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public final ModuleMethod get$Mnsimple$Mnname;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public final ModuleMethod send$Mnerror;
    static final SimpleSymbol Lit55 = (SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve();
    static final SimpleSymbol Lit54 = (SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve();
    static final SimpleSymbol Lit53 = (SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve();
    static final SimpleSymbol Lit52 = (SimpleSymbol) new SimpleSymbol("send-error").readResolve();
    static final SimpleSymbol Lit51 = (SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve();
    static final SimpleSymbol Lit50 = (SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve();
    static final SimpleSymbol Lit49 = (SimpleSymbol) new SimpleSymbol("add-to-components").readResolve();
    static final SimpleSymbol Lit48 = (SimpleSymbol) new SimpleSymbol("add-to-events").readResolve();
    static final SimpleSymbol Lit47 = (SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve();
    static final SimpleSymbol Lit46 = (SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve();
    static final SimpleSymbol Lit45 = (SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve();
    static final SimpleSymbol Lit44 = (SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve();
    static final SimpleSymbol Lit43 = (SimpleSymbol) new SimpleSymbol("android-log-form").readResolve();
    static final SimpleSymbol Lit42 = (SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve();
    static final FString Lit41 = new FString("com.google.appinventor.components.runtime.Button");
    static final IntNum Lit40 = IntNum.make(-1050);
    static final IntNum Lit39 = IntNum.make(1);
    static final SimpleSymbol Lit38 = (SimpleSymbol) new SimpleSymbol("Shape").readResolve();
    static final IntNum Lit37 = IntNum.make(-1013);
    static final IntNum Lit36 = IntNum.make(20);
    static final IntNum Lit35 = IntNum.make(new int[]{Component.COLOR_GREEN});
    static final SimpleSymbol Lit34 = (SimpleSymbol) new SimpleSymbol("Button1").readResolve();
    static final FString Lit33 = new FString("com.google.appinventor.components.runtime.Button");
    static final FString Lit32 = new FString("com.google.appinventor.components.runtime.Label");
    static final IntNum Lit31 = IntNum.make(-1080);
    static final SimpleSymbol Lit30 = (SimpleSymbol) new SimpleSymbol("Width").readResolve();
    static final IntNum Lit29 = IntNum.make(new int[]{-1});
    static final IntNum Lit28 = IntNum.make(-1038);
    static final SimpleSymbol Lit27 = (SimpleSymbol) new SimpleSymbol("Height").readResolve();
    static final IntNum Lit26 = IntNum.make(22);
    static final SimpleSymbol Lit25 = (SimpleSymbol) new SimpleSymbol("Label2").readResolve();
    static final FString Lit24 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit23 = new FString("com.google.appinventor.components.runtime.Label");
    static final IntNum Lit22 = IntNum.make(new int[]{-1});
    static final SimpleSymbol Lit21 = (SimpleSymbol) new SimpleSymbol("TextColor").readResolve();
    static final SimpleSymbol Lit20 = (SimpleSymbol) new SimpleSymbol("Text").readResolve();
    static final IntNum Lit19 = IntNum.make(28);
    static final SimpleSymbol Lit18 = (SimpleSymbol) new SimpleSymbol("FontSize").readResolve();
    static final SimpleSymbol Lit17 = (SimpleSymbol) new SimpleSymbol("Label1").readResolve();
    static final FString Lit16 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit15 = (SimpleSymbol) new SimpleSymbol("Title").readResolve();
    static final SimpleSymbol Lit14 = (SimpleSymbol) new SimpleSymbol("Sizing").readResolve();
    static final SimpleSymbol Lit13 = (SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve();
    static final SimpleSymbol Lit12 = (SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve();
    static final IntNum Lit11 = IntNum.make(new int[]{-13619152});
    static final SimpleSymbol Lit10 = (SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve();
    static final SimpleSymbol Lit9 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
    static final SimpleSymbol Lit8 = (SimpleSymbol) new SimpleSymbol("AppName").readResolve();
    static final SimpleSymbol Lit7 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
    static final IntNum Lit6 = IntNum.make(3);
    static final SimpleSymbol Lit5 = (SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve();
    static final SimpleSymbol Lit4 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
    static final SimpleSymbol Lit3 = (SimpleSymbol) new SimpleSymbol("ActionBar").readResolve();
    static final SimpleSymbol Lit2 = (SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve();
    static final SimpleSymbol Lit1 = (SimpleSymbol) new SimpleSymbol("getMessage").readResolve();
    static final SimpleSymbol Lit0 = (SimpleSymbol) new SimpleSymbol("Screen3").readResolve();

    public Screen3() {
        ModuleInfo.register(this);
        frame frameVar = new frame();
        frameVar.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frameVar, 1, Lit42, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frameVar, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frameVar, 3, Lit43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frameVar, 4, Lit44, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frameVar, 5, Lit45, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frameVar, 7, Lit46, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frameVar, 8, Lit47, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frameVar, 9, Lit48, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frameVar, 10, Lit49, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frameVar, 11, Lit50, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frameVar, 12, Lit51, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frameVar, 13, Lit52, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frameVar, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frameVar, 15, Lit53, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frameVar, 16, Lit54, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frameVar, 17, Lit55, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frameVar, 18, null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:634");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frameVar, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frameVar, 20, null, 0);
        lambda$Fn3 = new ModuleMethod(frameVar, 21, null, 0);
        lambda$Fn4 = new ModuleMethod(frameVar, 22, null, 0);
        lambda$Fn5 = new ModuleMethod(frameVar, 23, null, 0);
        lambda$Fn6 = new ModuleMethod(frameVar, 24, null, 0);
        lambda$Fn7 = new ModuleMethod(frameVar, 25, null, 0);
        lambda$Fn8 = new ModuleMethod(frameVar, 26, null, 0);
    }

    public Object lookupInFormEnvironment(Symbol symbol) {
        return lookupInFormEnvironment(symbol, Boolean.FALSE);
    }

    @Override // java.lang.Runnable
    public void run() {
        CallContext callContext = CallContext.getInstance();
        Consumer consumer = callContext.consumer;
        callContext.consumer = VoidConsumer.instance;
        try {
            run(callContext);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        ModuleBody.runCleanup(callContext, th, consumer);
    }

    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        Object find = require.find("com.google.youngandroid.runtime");
        try {
            ((Runnable) find).run();
            this.$Stdebug$Mnform$St = Boolean.FALSE;
            SimpleSymbol simpleSymbol = Lit0;
            this.form$Mnenvironment = Environment.make(simpleSymbol.toString());
            FString stringAppend = strings.stringAppend(simpleSymbol.toString(), "-global-vars");
            this.global$Mnvar$Mnenvironment = Environment.make(stringAppend == null ? null : stringAppend.toString());
            Screen3 = null;
            this.form$Mnname$Mnsymbol = simpleSymbol;
            this.events$Mnto$Mnregister = LList.Empty;
            this.components$Mnto$Mncreate = LList.Empty;
            this.global$Mnvars$Mnto$Mncreate = LList.Empty;
            this.form$Mndo$Mnafter$Mncreation = LList.Empty;
            Object find2 = require.find("com.google.youngandroid.runtime");
            try {
                ((Runnable) find2).run();
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    SimpleSymbol simpleSymbol2 = Lit3;
                    Boolean bool = Boolean.TRUE;
                    SimpleSymbol simpleSymbol3 = Lit4;
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, bool, simpleSymbol3);
                    SimpleSymbol simpleSymbol4 = Lit5;
                    IntNum intNum = Lit6;
                    SimpleSymbol simpleSymbol5 = Lit7;
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum, simpleSymbol5);
                    SimpleSymbol simpleSymbol6 = Lit8;
                    SimpleSymbol simpleSymbol7 = Lit9;
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "WhatsApp_Translate", simpleSymbol7);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit10, Lit11, simpleSymbol5);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit12, "unspecified", simpleSymbol7);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit13, Boolean.TRUE, simpleSymbol3);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit14, "Responsive", simpleSymbol7);
                    Values.writeValues(runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit15, "Screen3", simpleSymbol7), $result);
                } else {
                    addToFormDoAfterCreation(new Promise(lambda$Fn2));
                }
                this.Label1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit16, Lit17, lambda$Fn3), $result);
                } else {
                    addToComponents(simpleSymbol, Lit23, Lit17, lambda$Fn4);
                }
                this.Label2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit24, Lit25, lambda$Fn5), $result);
                } else {
                    addToComponents(simpleSymbol, Lit32, Lit25, lambda$Fn6);
                }
                this.Button1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit33, Lit34, lambda$Fn7), $result);
                } else {
                    addToComponents(simpleSymbol, Lit41, Lit34, lambda$Fn8);
                }
                runtime.initRuntime();
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Runnable.run()", 1, find2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "java.lang.Runnable.run()", 1, find);
        }
    }

    static Object lambda3() {
        SimpleSymbol simpleSymbol = Lit0;
        SimpleSymbol simpleSymbol2 = Lit3;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol3 = Lit4;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, bool, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit5;
        IntNum intNum = Lit6;
        SimpleSymbol simpleSymbol5 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum, simpleSymbol5);
        SimpleSymbol simpleSymbol6 = Lit8;
        SimpleSymbol simpleSymbol7 = Lit9;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "WhatsApp_Translate", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit10, Lit11, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit12, "unspecified", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit13, Boolean.TRUE, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit14, "Responsive", simpleSymbol7);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit15, "Screen3", simpleSymbol7);
    }

    static Object lambda4() {
        SimpleSymbol simpleSymbol = Lit17;
        SimpleSymbol simpleSymbol2 = Lit18;
        IntNum intNum = Lit19;
        SimpleSymbol simpleSymbol3 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "WhatsApp Translate+", Lit9);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, Lit22, simpleSymbol3);
    }

    static Object lambda5() {
        SimpleSymbol simpleSymbol = Lit17;
        SimpleSymbol simpleSymbol2 = Lit18;
        IntNum intNum = Lit19;
        SimpleSymbol simpleSymbol3 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "WhatsApp Translate+", Lit9);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, Lit22, simpleSymbol3);
    }

    static Object lambda6() {
        SimpleSymbol simpleSymbol = Lit25;
        SimpleSymbol simpleSymbol2 = Lit18;
        IntNum intNum = Lit26;
        SimpleSymbol simpleSymbol3 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit27, Lit28, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "Configuración\n                            \nTraducir WhatsApp\nHabilitar la traduccion del chat y mostrar\nun botón de traducción en los chats", Lit9);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, Lit29, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit30, Lit31, simpleSymbol3);
    }

    static Object lambda7() {
        SimpleSymbol simpleSymbol = Lit25;
        SimpleSymbol simpleSymbol2 = Lit18;
        IntNum intNum = Lit26;
        SimpleSymbol simpleSymbol3 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit27, Lit28, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "Configuración\n                            \nTraducir WhatsApp\nHabilitar la traduccion del chat y mostrar\nun botón de traducción en los chats", Lit9);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, Lit29, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit30, Lit31, simpleSymbol3);
    }

    static Object lambda8() {
        SimpleSymbol simpleSymbol = Lit34;
        SimpleSymbol simpleSymbol2 = Lit10;
        IntNum intNum = Lit35;
        SimpleSymbol simpleSymbol3 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit18, Lit36, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit27, Lit37, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit38, Lit39, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "Abrir WhatsApp", Lit9);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit30, Lit40, simpleSymbol3);
    }

    static Object lambda9() {
        SimpleSymbol simpleSymbol = Lit34;
        SimpleSymbol simpleSymbol2 = Lit10;
        IntNum intNum = Lit35;
        SimpleSymbol simpleSymbol3 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit18, Lit36, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit27, Lit37, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit38, Lit39, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "Abrir WhatsApp", Lit9);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit30, Lit40, simpleSymbol3);
    }

    /* compiled from: Screen3.yail */
    /* loaded from: classes2.dex */
    public class frame extends ModuleBody {
        Screen3 $main;

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof Screen3)) {
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
                case 4:
                case 6:
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    return super.match1(moduleMethod, obj, callContext);
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 7:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 12:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 13:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 14:
                    if (!(obj instanceof Screen3)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 4:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 8:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 9:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 17:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 10:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                case 15:
                    if (!(obj instanceof Screen3)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    if (!(obj4 instanceof String)) {
                        return -786428;
                    }
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                case 16:
                    if (!(obj instanceof Screen3)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                default:
                    return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 1:
                    return this.$main.getSimpleName(obj);
                case 2:
                    try {
                        this.$main.onCreate((Bundle) obj);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "onCreate", 1, obj);
                    }
                case 3:
                    this.$main.androidLogForm(obj);
                    return Values.empty;
                case 4:
                case 6:
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    return super.apply1(moduleMethod, obj);
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 7:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "is-bound-in-form-environment", 1, obj);
                    }
                case 12:
                    this.$main.addToFormDoAfterCreation(obj);
                    return Values.empty;
                case 13:
                    this.$main.sendError(obj);
                    return Values.empty;
                case 14:
                    this.$main.processException(obj);
                    return Values.empty;
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            switch (moduleMethod.selector) {
                case 10:
                    this.$main.addToComponents(obj, obj2, obj3, obj4);
                    return Values.empty;
                case 15:
                    try {
                        try {
                            try {
                                try {
                                    return this.$main.dispatchEvent((Component) obj, (String) obj2, (String) obj3, (Object[]) obj4) ? Boolean.TRUE : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "dispatchEvent", 4, obj4);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "dispatchEvent", 3, obj3);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "dispatchEvent", 2, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "dispatchEvent", 1, obj);
                    }
                case 16:
                    try {
                        try {
                            try {
                                try {
                                    this.$main.dispatchGenericEvent((Component) obj, (String) obj2, obj3 != Boolean.FALSE, (Object[]) obj4);
                                    return Values.empty;
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "dispatchGenericEvent", 4, obj4);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "dispatchGenericEvent", 3, obj3);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "dispatchGenericEvent", 2, obj2);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "dispatchGenericEvent", 1, obj);
                    }
                default:
                    return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 4:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-form-environment", 1, obj);
                    }
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 8:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-global-var-environment", 1, obj);
                    }
                case 9:
                    this.$main.addToEvents(obj, obj2);
                    return Values.empty;
                case 11:
                    this.$main.addToGlobalVars(obj, obj2);
                    return Values.empty;
                case 17:
                    return this.$main.lookupHandler(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return Screen3.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return Screen3.lambda3();
                case 21:
                    return Screen3.lambda4();
                case 22:
                    return Screen3.lambda5();
                case 23:
                    return Screen3.lambda6();
                case 24:
                    return Screen3.lambda7();
                case 25:
                    return Screen3.lambda8();
                case 26:
                    return Screen3.lambda9();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 18:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 19:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    public String getSimpleName(Object object) {
        return object.getClass().getSimpleName();
    }

    @Override // com.google.appinventor.components.runtime.Form, com.google.appinventor.components.runtime.AppInventorCompatActivity, android.app.Activity
    public void onCreate(Bundle icicle) {
        AppInventorCompatActivity.setClassicModeFromYail(true);
        super.onCreate(icicle);
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.form$Mnenvironment, object));
        this.form$Mnenvironment.put(name, object);
    }

    public Object lookupInFormEnvironment(Symbol name, Object default$Mnvalue) {
        Environment environment = this.form$Mnenvironment;
        int i = 1 & ((environment == null ? 1 : 0) + 1);
        if (i == 0 ? i == 0 : !environment.isBound(name)) {
            return default$Mnvalue;
        }
        return this.form$Mnenvironment.get(name);
    }

    public boolean isBoundInFormEnvironment(Symbol name) {
        return this.form$Mnenvironment.isBound(name);
    }

    public void addToGlobalVarEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.global$Mnvar$Mnenvironment, object));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = lists.cons(lists.cons(component$Mnname, event$Mnname), this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname, Object init$Mnthunk) {
        this.components$Mnto$Mncreate = lists.cons(LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk), this.components$Mnto$Mncreate);
    }

    public void addToGlobalVars(Object var, Object val$Mnthunk) {
        this.global$Mnvars$Mnto$Mncreate = lists.cons(LList.list2(var, val$Mnthunk), this.global$Mnvars$Mnto$Mncreate);
    }

    public void addToFormDoAfterCreation(Object thunk) {
        this.form$Mndo$Mnafter$Mncreation = lists.cons(thunk, this.form$Mndo$Mnafter$Mncreation);
    }

    public void sendError(Object error) {
        RetValManager.sendError(error == null ? null : error.toString());
    }

    public void processException(Object ex) {
        Object apply1 = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(ex, Lit1));
        RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(), ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error", "End Application");
    }

    @Override // com.google.appinventor.components.runtime.Form, com.google.appinventor.components.runtime.HandlesEventDispatching
    public boolean dispatchEvent(Component componentObject, String registeredComponentName, String eventName, Object[] args) {
        Symbol registeredObject = misc.string$To$Symbol(registeredComponentName);
        if (isBoundInFormEnvironment(registeredObject)) {
            if (lookupInFormEnvironment(registeredObject) != componentObject) {
                return false;
            }
            Object handler = lookupHandler(registeredComponentName, eventName);
            try {
                Scheme.apply.apply2(handler, LList.makeList(args, 0));
                return true;
            } catch (PermissionException exception) {
                exception.printStackTrace();
                boolean x = this == componentObject;
                if (!x ? x : IsEqual.apply(eventName, "PermissionNeeded")) {
                    processException(exception);
                    return false;
                }
                PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                return false;
            } catch (StopBlocksExecution exception2) {
                return throw_name.throwName.apply1(exception2) != Boolean.FALSE;
            } catch (Throwable exception3) {
                androidLogForm(exception3.getMessage());
                exception3.printStackTrace();
                processException(exception3);
                return false;
            }
        }
        EventDispatcher.unregisterEventForDelegation(this, registeredComponentName, eventName);
        return false;
    }

    @Override // com.google.appinventor.components.runtime.Form, com.google.appinventor.components.runtime.HandlesEventDispatching
    public void dispatchGenericEvent(Component componentObject, String eventName, boolean notAlreadyHandled, Object[] args) {
        boolean x = false;
        Symbol handler$Mnsymbol = misc.string$To$Symbol(strings.stringAppend("any$", getSimpleName(componentObject), "$", eventName));
        Object handler = lookupInFormEnvironment(handler$Mnsymbol);
        if (handler != Boolean.FALSE) {
            try {
                Scheme.apply.apply2(handler, lists.cons(componentObject, lists.cons(notAlreadyHandled ? Boolean.TRUE : Boolean.FALSE, LList.makeList(args, 0))));
            } catch (PermissionException exception) {
                exception.printStackTrace();
                if (this == componentObject) {
                    x = true;
                }
                if (!x ? x : IsEqual.apply(eventName, "PermissionNeeded")) {
                    processException(exception);
                } else {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                }
            } catch (StopBlocksExecution e) {
            } catch (Throwable exception2) {
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
            }
        }
    }

    public Object lookupHandler(Object componentName, Object eventName) {
        return lookupInFormEnvironment(misc.string$To$Symbol(EventDispatcher.makeFullEventName(componentName == null ? null : componentName.toString(), eventName != null ? eventName.toString() : null)));
    }

    @Override // com.google.appinventor.components.runtime.Form
    public void $define() {
        int i;
        Language.setDefaults(Scheme.getInstance());
        try {
            run();
        } catch (Exception exception) {
            androidLogForm(exception.getMessage());
            processException(exception);
        }
        Screen3 = this;
        addToFormEnvironment(Lit0, this);
        Object obj = this.events$Mnto$Mnregister;
        Object components = obj;
        Object arg0 = obj;
        while (true) {
            i = -2;
            if (arg0 == LList.Empty) {
                try {
                    break;
                } catch (YailRuntimeError exception2) {
                    processException(exception2);
                    return;
                }
            }
            try {
                Pair arg02 = (Pair) arg0;
                Object event$Mninfo = arg02.getCar();
                Object apply1 = lists.car.apply1(event$Mninfo);
                String str = null;
                String obj2 = apply1 == null ? null : apply1.toString();
                Object apply12 = lists.cdr.apply1(event$Mninfo);
                if (apply12 != null) {
                    str = apply12.toString();
                }
                EventDispatcher.registerEventForDelegation(this, obj2, str);
                arg0 = arg02.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        components = lists.reverse(this.components$Mnto$Mncreate);
        addToGlobalVars(Lit2, lambda$Fn1);
        Object arg03 = lists.reverse(this.form$Mndo$Mnafter$Mncreation);
        while (arg03 != LList.Empty) {
            try {
                Pair arg04 = (Pair) arg03;
                misc.force(arg04.getCar());
                arg03 = arg04.getCdr();
                i = -2;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, arg03);
            }
        }
        Object var$Mnval = null;
        Object arg05 = components;
        while (arg05 != LList.Empty) {
            try {
                Pair arg06 = (Pair) arg05;
                Object component$Mninfo = arg06.getCar();
                Object component$Mnname = lists.caddr.apply1(component$Mninfo);
                lists.cadddr.apply1(component$Mninfo);
                Object component$Mntype = lists.cadr.apply1(component$Mninfo);
                Object apply13 = lists.car.apply1(component$Mninfo);
                try {
                    Object component$Mncontainer = lookupInFormEnvironment((Symbol) apply13);
                    Object component$Mnobject = Invoke.make.apply2(component$Mntype, component$Mncontainer);
                    SlotSet.set$Mnfield$Ex.apply3(this, component$Mnname, component$Mnobject);
                    try {
                        addToFormEnvironment((Symbol) component$Mnname, component$Mnobject);
                        arg05 = arg06.getCdr();
                        var$Mnval = component$Mninfo;
                        i = -2;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-form-environment", 0, component$Mnname);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "lookup-in-form-environment", 0, apply13);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "arg0", -2, arg05);
            }
        }
        Object var$Mnval$Mnpairs = lists.reverse(this.global$Mnvars$Mnto$Mncreate);
        Object arg07 = var$Mnval$Mnpairs;
        while (arg07 != LList.Empty) {
            try {
                Pair arg08 = (Pair) arg07;
                var$Mnval = arg08.getCar();
                Object var = lists.car.apply1(var$Mnval);
                Object val$Mnthunk = lists.cadr.apply1(var$Mnval);
                try {
                    addToGlobalVarEnvironment((Symbol) var, Scheme.applyToArgs.apply1(val$Mnthunk));
                    arg07 = arg08.getCdr();
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "add-to-global-var-environment", 0, var);
                }
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "arg0", i, arg07);
            }
        }
        Object arg09 = components;
        while (arg09 != LList.Empty) {
            try {
                Pair arg010 = (Pair) arg09;
                Object component$Mninfo2 = arg010.getCar();
                lists.caddr.apply1(component$Mninfo2);
                var$Mnval = lists.cadddr.apply1(component$Mninfo2);
                if (var$Mnval != Boolean.FALSE) {
                    Scheme.applyToArgs.apply1(var$Mnval);
                }
                arg09 = arg010.getCdr();
            } catch (ClassCastException e8) {
                throw new WrongType(e8, "arg0", i, arg09);
            }
        }
        Object arg011 = components;
        while (arg011 != LList.Empty) {
            try {
                Pair arg012 = (Pair) arg011;
                Object component$Mninfo3 = arg012.getCar();
                Object apply14 = lists.caddr.apply1(component$Mninfo3);
                lists.cadddr.apply1(component$Mninfo3);
                var$Mnval = apply14;
                callInitialize(SlotGet.field.apply2(this, var$Mnval));
                arg011 = arg012.getCdr();
            } catch (ClassCastException e9) {
                throw new WrongType(e9, "arg0", i, arg011);
            }
        }
    }

    public static SimpleSymbol lambda1symbolAppend$V(Object[] argsArray) {
        Object symbols = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object result = LList.Empty;
        Object arg0 = symbols;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object cdr = arg02.getCdr();
                Object car = arg02.getCar();
                try {
                    result = Pair.make(((Symbol) car).toString(), result);
                    arg0 = cdr;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, arg0);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(result));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object lambda2() {
        return null;
    }
}
