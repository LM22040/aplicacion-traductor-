package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.standard.syntax_case;

/* compiled from: srfi2.scm */
/* loaded from: classes2.dex */
public class srfi2 extends ModuleBody {
    public static final srfi2 $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1;
    static final SyntaxTemplate Lit10;
    static final SyntaxPattern Lit11;
    static final SyntaxTemplate Lit12;
    static final SyntaxPattern Lit13;
    static final SyntaxTemplate Lit14;
    static final SyntaxPattern Lit15;
    static final SyntaxTemplate Lit16;
    static final SyntaxTemplate Lit17;
    static final SyntaxTemplate Lit18;
    static final SyntaxPattern Lit19;
    static final SyntaxTemplate Lit2;
    static final SyntaxTemplate Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxPattern Lit3;
    static final SyntaxTemplate Lit4;
    static final SyntaxPattern Lit5;
    static final SyntaxTemplate Lit6;
    static final SyntaxPattern Lit7;
    static final SyntaxTemplate Lit8;
    static final SyntaxTemplate Lit9;
    public static final Macro and$Mnlet$St;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit22 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
        Lit21 = simpleSymbol2;
        Lit20 = new SyntaxTemplate("\u0001", "\u0018\u0004", new Object[]{Boolean.TRUE}, 0);
        Lit19 = new SyntaxPattern("\f\u0007\f\b\b", new Object[0], 1);
        Lit18 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit17 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{simpleSymbol2}, 0);
        Lit16 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit15 = new SyntaxPattern("\f\u0007\u001c\f\u000f\u0013\b", new Object[0], 3);
        Lit14 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{simpleSymbol2}, 0);
        Lit13 = new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\u0013\b", new Object[0], 3);
        Lit12 = new SyntaxTemplate("\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u0011\u0018\f\t\u000b\b\t\u0003\b\u001a", new Object[]{simpleSymbol, simpleSymbol2}, 0);
        Lit11 = new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\u001b\b", new Object[0], 4);
        Lit10 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit9 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit8 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit7 = new SyntaxPattern("\f\u0007\u001c\f\u000f\b\b", new Object[0], 2);
        Lit6 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit5 = new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\b\b", new Object[0], 2);
        Lit4 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u000b", new Object[]{simpleSymbol}, 0);
        Lit3 = new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\b\b", new Object[0], 3);
        Lit2 = new SyntaxTemplate("\u0001\u0003\u0001\u0000", "\t\u0003\b\u0011\r\u000b\b\b\u0011\u0018\u0004\t\u0013\u001a", new Object[]{(SimpleSymbol) new SimpleSymbol("begin").readResolve()}, 1);
        Lit1 = new SyntaxPattern("\f\u0007,\r\u000f\b\b\b\f\u0017\u001b", new Object[0], 4);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("and-let*").readResolve();
        Lit0 = simpleSymbol3;
        srfi2 srfi2Var = new srfi2();
        $instance = srfi2Var;
        and$Mnlet$St = Macro.make(simpleSymbol3, new ModuleMethod(srfi2Var, 1, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), srfi2Var);
        srfi2Var.run();
    }

    public srfi2() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 1 ? lambda1(obj) : super.apply1(moduleMethod, obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match1(moduleMethod, obj, callContext);
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    static Object lambda1(Object form) {
        Object[] allocVars = SyntaxPattern.allocVars(4, null);
        if (Lit1.match(form, allocVars, 0)) {
            return Lit2.execute(allocVars, TemplateScope.make());
        }
        if (Lit3.match(form, allocVars, 0)) {
            return Lit4.execute(allocVars, TemplateScope.make());
        }
        if (Lit5.match(form, allocVars, 0)) {
            return Lit6.execute(allocVars, TemplateScope.make());
        }
        if (Lit7.match(form, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit8.execute(allocVars, TemplateScope.make()))) {
                return Lit9.execute(allocVars, TemplateScope.make());
            }
            return prim_syntax.syntaxError(Lit10.execute(allocVars, TemplateScope.make()), "expected a variable name" instanceof Object[] ? (Object[]) "expected a variable name" : new Object[]{"expected a variable name"});
        }
        if (Lit11.match(form, allocVars, 0)) {
            return Lit12.execute(allocVars, TemplateScope.make());
        }
        if (Lit13.match(form, allocVars, 0)) {
            return Lit14.execute(allocVars, TemplateScope.make());
        }
        if (!Lit15.match(form, allocVars, 0)) {
            return Lit19.match(form, allocVars, 0) ? Lit20.execute(allocVars, TemplateScope.make()) : syntax_case.error("syntax-case", form);
        }
        if (std_syntax.isIdentifier(Lit16.execute(allocVars, TemplateScope.make()))) {
            return Lit17.execute(allocVars, TemplateScope.make());
        }
        return prim_syntax.syntaxError(Lit18.execute(allocVars, TemplateScope.make()), "expected a variable name" instanceof Object[] ? (Object[]) "expected a variable name" : new Object[]{"expected a variable name"});
    }
}
