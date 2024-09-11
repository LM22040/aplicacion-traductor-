package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;
import kawa.standard.syntax_error;
import kawa.standard.try_catch;

/* compiled from: prim_syntax.scm */
/* loaded from: classes.dex */
public class prim_syntax extends ModuleBody {
    public static final prim_syntax $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SyntaxRules Lit10;
    static final SimpleSymbol Lit11;
    static final SyntaxRules Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxPattern Lit14;
    static final SyntaxTemplate Lit15;
    static final SyntaxTemplate Lit16;
    static final SyntaxPattern Lit17;
    static final SyntaxTemplate Lit18;
    static final SyntaxTemplate Lit19;
    static final SimpleSymbol Lit2;
    static final SyntaxTemplate Lit20;
    static final SyntaxPattern Lit21;
    static final SyntaxTemplate Lit22;
    static final SyntaxPattern Lit23;
    static final SyntaxTemplate Lit24;
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit26;
    static final SyntaxTemplate Lit27;
    static final SyntaxTemplate Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxRules Lit3;
    static final SyntaxPattern Lit30;
    static final SyntaxTemplate Lit31;
    static final SyntaxTemplate Lit32;
    static final SyntaxTemplate Lit33;
    static final SyntaxPattern Lit34;
    static final SyntaxPattern Lit35;
    static final SyntaxTemplate Lit36;
    static final SyntaxTemplate Lit37;
    static final SyntaxTemplate Lit38;
    static final SyntaxPattern Lit39;
    static final SimpleSymbol Lit4;
    static final SyntaxTemplate Lit40;
    static final SyntaxTemplate Lit41;
    static final SyntaxTemplate Lit42;
    static final SyntaxPattern Lit43;
    static final SyntaxPattern Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit50;
    static final IntNum Lit51;
    static final IntNum Lit52;
    static final IntNum Lit53;
    static final IntNum Lit54;
    static final IntNum Lit55;
    static final IntNum Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro define;
    public static final Macro define$Mnconstant;
    public static final Macro define$Mnprivate;
    public static final Macro define$Mnsyntax;

    /* renamed from: if, reason: not valid java name */
    public static final Macro f4if;
    public static final Macro letrec;
    public static final Macro syntax$Mn$Grexpression;
    public static final Macro syntax$Mnbody$Mn$Grexpression;
    public static final ModuleMethod syntax$Mnerror;
    public static final Macro try$Mncatch;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit58 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("%define-syntax").readResolve();
        Lit57 = simpleSymbol2;
        IntNum make = IntNum.make(0);
        Lit56 = make;
        IntNum make2 = IntNum.make(1);
        Lit55 = make2;
        IntNum make3 = IntNum.make(4);
        Lit54 = make3;
        IntNum make4 = IntNum.make(5);
        Lit53 = make4;
        IntNum make5 = IntNum.make(8);
        Lit52 = make5;
        IntNum make6 = IntNum.make(9);
        Lit51 = make6;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("%define").readResolve();
        Lit50 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit49 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit48 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("kawa.lang.SyntaxForms").readResolve();
        Lit47 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
        Lit46 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("set!").readResolve();
        Lit45 = simpleSymbol8;
        Lit44 = new SyntaxPattern("\u001b", new Object[0], 4);
        Lit43 = new SyntaxPattern("\u001c\f\u001f\b#", new Object[0], 5);
        Lit42 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", ":", new Object[0], 0);
        Lit41 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u001b\b3", new Object[]{simpleSymbol8}, 0);
        Lit40 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", "\t\u001b\t#\t+\u0018\u0004", new Object[]{PairWithPosition.make(Special.undefined, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 471102)}, 0);
        Lit39 = new SyntaxPattern("L\f\u001f\f'\f/\f7\b;", new Object[0], 8);
        Lit38 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "*", new Object[0], 0);
        Lit37 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u001b\b#", new Object[]{simpleSymbol8}, 0);
        Lit36 = new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "\t\u001b\u0018\u0004", new Object[]{PairWithPosition.make(Special.undefined, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 450594)}, 0);
        Lit35 = new SyntaxPattern(",\f\u001f\f'\b+", new Object[0], 6);
        Lit34 = new SyntaxPattern("\b", new Object[0], 3);
        Lit33 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0012", new Object[0], 0);
        Lit32 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("%let").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 512011)}, 0);
        Lit31 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit30 = new SyntaxPattern("\f\u0007\f\u000f\u0013", new Object[0], 3);
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("letrec").readResolve();
        Lit29 = simpleSymbol9;
        Lit28 = new SyntaxTemplate("\u0001\u0001\u0003\u0003\u0002", "(\b\u0015A\b\t\u0013\u0011\u0018\u0004\b\u001b\"", new Object[]{simpleSymbol4}, 1);
        Lit27 = new SyntaxTemplate("\u0001\u0001\u0003\u0003\u0002", "\u000b", new Object[0], 0);
        Lit26 = new SyntaxPattern("\f\u0007\f\u000f-\f\u0017\f\u001f#\u0010\u0018\b", new Object[0], 5);
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("try-catch").readResolve();
        Lit25 = simpleSymbol10;
        Lit24 = new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
        Lit23 = new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
        Lit22 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001\u0000", "#", new Object[0], 0);
        Lit21 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f\f'+", new Object[0], 6);
        Lit20 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u001b", new Object[0], 0);
        Lit19 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit18 = new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u000b", new Object[0], 0);
        Lit17 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4);
        Lit16 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit15 = new SyntaxTemplate("\u0001\u0001\u0001", "\u000b", new Object[0], 0);
        Lit14 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit13 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("syntax-body->expression").readResolve();
        Lit11 = simpleSymbol12;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol12}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol7, Pair.make(simpleSymbol6, Pair.make(Pair.make(simpleSymbol5, Pair.make((SimpleSymbol) new SimpleSymbol("rewriteBody").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 270343)}, 0)}, 1);
        Lit12 = syntaxRules;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("syntax->expression").readResolve();
        Lit9 = simpleSymbol13;
        SyntaxRules syntaxRules2 = new SyntaxRules(new Object[]{simpleSymbol13}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol7, Pair.make(simpleSymbol6, Pair.make(Pair.make(simpleSymbol5, Pair.make((SimpleSymbol) new SimpleSymbol("rewrite").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 249863)}, 0)}, 1);
        Lit10 = syntaxRules2;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("syntax-error").readResolve();
        Lit8 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("define-constant").readResolve();
        Lit6 = simpleSymbol15;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", new Object[]{simpleSymbol7, simpleSymbol4}, 5);
        Object[] objArr = {simpleSymbol3, simpleSymbol7, make6};
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{simpleSymbol7}, 4);
        Object[] objArr2 = new Object[4];
        objArr2[0] = simpleSymbol3;
        objArr2[1] = simpleSymbol7;
        objArr2[2] = make5;
        SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr3 = {simpleSymbol3, IntNum.make(10), Boolean.TRUE};
        SyntaxPattern syntaxPattern4 = new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol4}, 3);
        Object[] objArr4 = {simpleSymbol3, make6};
        SyntaxPattern syntaxPattern5 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr5 = new Object[3];
        objArr5[0] = simpleSymbol3;
        objArr5[1] = make5;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol15, simpleSymbol4, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", objArr, 0), new SyntaxRule(syntaxPattern2, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr2, 0), new SyntaxRule(syntaxPattern3, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", objArr3, 0), new SyntaxRule(syntaxPattern4, "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", objArr4, 0), new SyntaxRule(syntaxPattern5, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr5, 0)}, 5);
        Lit7 = syntaxRules3;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("define-private").readResolve();
        Lit4 = simpleSymbol16;
        Object[] objArr6 = {simpleSymbol16, simpleSymbol4, simpleSymbol7};
        SyntaxPattern syntaxPattern6 = new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{simpleSymbol7}, 4);
        Object[] objArr7 = new Object[4];
        objArr7[0] = simpleSymbol3;
        objArr7[1] = simpleSymbol7;
        objArr7[2] = make3;
        SyntaxPattern syntaxPattern7 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr8 = new Object[3];
        objArr8[0] = simpleSymbol3;
        objArr8[1] = make3;
        SyntaxRules syntaxRules4 = new SyntaxRules(objArr6, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", new Object[]{simpleSymbol7, simpleSymbol4}, 5), "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", new Object[]{simpleSymbol3, simpleSymbol7, make4}, 0), new SyntaxRule(syntaxPattern6, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr7, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", new Object[]{simpleSymbol3, IntNum.make(6), Boolean.TRUE}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol4}, 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", new Object[]{simpleSymbol3, make4}, 0), new SyntaxRule(syntaxPattern7, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr8, 0)}, 5);
        Lit5 = syntaxRules4;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("define").readResolve();
        Lit2 = simpleSymbol17;
        SyntaxPattern syntaxPattern8 = new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", new Object[]{simpleSymbol7, simpleSymbol4}, 5);
        Object[] objArr9 = {simpleSymbol3, simpleSymbol7, make2};
        SyntaxPattern syntaxPattern9 = new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{simpleSymbol7}, 4);
        Object[] objArr10 = new Object[4];
        objArr10[0] = simpleSymbol3;
        objArr10[1] = simpleSymbol7;
        objArr10[2] = make;
        SyntaxPattern syntaxPattern10 = new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr11 = {simpleSymbol3, IntNum.make(2), Boolean.TRUE};
        SyntaxPattern syntaxPattern11 = new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol4}, 3);
        Object[] objArr12 = {simpleSymbol3, make2};
        SyntaxPattern syntaxPattern12 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr13 = new Object[3];
        objArr13[0] = simpleSymbol3;
        objArr13[1] = make;
        SyntaxRules syntaxRules5 = new SyntaxRules(new Object[]{simpleSymbol17, simpleSymbol4, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(syntaxPattern8, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", objArr9, 0), new SyntaxRule(syntaxPattern9, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr10, 0), new SyntaxRule(syntaxPattern10, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", objArr11, 0), new SyntaxRule(syntaxPattern11, "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", objArr12, 0), new SyntaxRule(syntaxPattern12, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr13, 0)}, 5);
        Lit3 = syntaxRules5;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("define-syntax").readResolve();
        Lit0 = simpleSymbol18;
        SyntaxRules syntaxRules6 = new SyntaxRules(new Object[]{simpleSymbol18, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018l\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\u001b#", new Object[]{simpleSymbol7}, 5), "\u0001\u0001\u0001\u0000\u0000", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\b\u0011\u0018\u0014\t\u001a\"", new Object[]{simpleSymbol2, simpleSymbol7, simpleSymbol}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{simpleSymbol7}, 4), "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\b\u001b", new Object[]{simpleSymbol2, simpleSymbol7}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\n\u0012", new Object[]{simpleSymbol2, simpleSymbol}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{simpleSymbol2}, 0)}, 5);
        Lit1 = syntaxRules6;
        prim_syntax prim_syntaxVar = new prim_syntax();
        $instance = prim_syntaxVar;
        define$Mnsyntax = Macro.make(simpleSymbol18, syntaxRules6, prim_syntaxVar);
        define = Macro.make(simpleSymbol17, syntaxRules5, prim_syntaxVar);
        define$Mnprivate = Macro.make(simpleSymbol16, syntaxRules4, prim_syntaxVar);
        define$Mnconstant = Macro.make(simpleSymbol15, syntaxRules3, prim_syntaxVar);
        syntax$Mnerror = new ModuleMethod(prim_syntaxVar, 1, simpleSymbol14, -4095);
        syntax$Mn$Grexpression = Macro.make(simpleSymbol13, syntaxRules2, prim_syntaxVar);
        syntax$Mnbody$Mn$Grexpression = Macro.make(simpleSymbol12, syntaxRules, prim_syntaxVar);
        ModuleMethod moduleMethod = new ModuleMethod(prim_syntaxVar, 2, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:69");
        f4if = Macro.make(simpleSymbol11, moduleMethod, prim_syntaxVar);
        ModuleMethod moduleMethod2 = new ModuleMethod(prim_syntaxVar, 3, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:89");
        try$Mncatch = Macro.make(simpleSymbol10, moduleMethod2, prim_syntaxVar);
        ModuleMethod moduleMethod3 = new ModuleMethod(prim_syntaxVar, 4, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:98");
        letrec = Macro.make(simpleSymbol9, moduleMethod3, prim_syntaxVar);
        prim_syntaxVar.run();
    }

    public prim_syntax() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    public static Expression syntaxError(Object id, Object... msg) {
        return syntax_error.error(id, msg);
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        if (moduleMethod.selector != 1) {
            return super.applyN(moduleMethod, objArr);
        }
        Object obj = objArr[0];
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return syntaxError(obj, objArr2);
            }
            objArr2[length] = objArr[length + 1];
        }
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.matchN(moduleMethod, objArr, callContext);
        }
        callContext.values = objArr;
        callContext.proc = moduleMethod;
        callContext.pc = 5;
        return 0;
    }

    static Object lambda1(Object x) {
        Object[] allocVars = SyntaxPattern.allocVars(6, null);
        if (Lit14.match(x, allocVars, 0)) {
            return new IfExp(SyntaxForms.rewrite(Lit15.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit16.execute(allocVars, TemplateScope.make())), null);
        }
        if (Lit17.match(x, allocVars, 0)) {
            return new IfExp(SyntaxForms.rewrite(Lit18.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit19.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit20.execute(allocVars, TemplateScope.make())));
        }
        if (Lit21.match(x, allocVars, 0)) {
            return syntaxError(Lit22.execute(allocVars, TemplateScope.make()), "too many expressions for 'if'" instanceof Object[] ? (Object[]) "too many expressions for 'if'" : new Object[]{"too many expressions for 'if'"});
        }
        if (Lit23.match(x, allocVars, 0)) {
            return syntaxError(Lit24.execute(allocVars, TemplateScope.make()), "too few expressions for 'if'" instanceof Object[] ? (Object[]) "too few expressions for 'if'" : new Object[]{"too few expressions for 'if'"});
        }
        return syntax_case.error("syntax-case", x);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
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
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    static Object lambda2(Object x) {
        Object[] allocVars = SyntaxPattern.allocVars(5, null);
        if (!Lit26.match(x, allocVars, 0)) {
            return syntax_case.error("syntax-case", x);
        }
        return try_catch.rewrite(Lit27.execute(allocVars, TemplateScope.make()), Lit28.execute(allocVars, TemplateScope.make()));
    }

    static Object lambda3(Object form) {
        Object consX;
        frame frameVar = new frame();
        LList lList = LList.Empty;
        frameVar.out$Mninits = LList.Empty;
        frameVar.out$Mnbindings = lList;
        frameVar.$unnamed$0 = SyntaxPattern.allocVars(3, null);
        if (!Lit30.match(form, frameVar.$unnamed$0, 0)) {
            return syntax_case.error("syntax-case", form);
        }
        frameVar.lambda4processBinding(Lit31.execute(frameVar.$unnamed$0, TemplateScope.make()));
        frameVar.out$Mnbindings = LList.reverseInPlace(frameVar.out$Mnbindings);
        frameVar.out$Mninits = LList.reverseInPlace(frameVar.out$Mninits);
        TemplateScope make = TemplateScope.make();
        consX = LList.consX(new Object[]{frameVar.out$Mnbindings, Quote.append$V(new Object[]{frameVar.out$Mninits, Lit33.execute(frameVar.$unnamed$0, make)})});
        return Quote.append$V(new Object[]{Lit32.execute(frameVar.$unnamed$0, make), consX});
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 2:
                return lambda1(obj);
            case 3:
                return lambda2(obj);
            case 4:
                return lambda3(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    /* compiled from: prim_syntax.scm */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        Object[] $unnamed$0;
        Object out$Mnbindings;
        Object out$Mninits;

        public Object lambda4processBinding(Object b) {
            Object[] allocVars = SyntaxPattern.allocVars(8, this.$unnamed$0);
            if (prim_syntax.Lit34.match(b, allocVars, 0)) {
                return Values.empty;
            }
            if (prim_syntax.Lit35.match(b, allocVars, 0)) {
                this.out$Mnbindings = new Pair(prim_syntax.Lit36.execute(allocVars, TemplateScope.make()), this.out$Mnbindings);
                this.out$Mninits = new Pair(prim_syntax.Lit37.execute(allocVars, TemplateScope.make()), this.out$Mninits);
                return lambda4processBinding(prim_syntax.Lit38.execute(allocVars, TemplateScope.make()));
            }
            if (prim_syntax.Lit39.match(b, allocVars, 0)) {
                this.out$Mnbindings = new Pair(prim_syntax.Lit40.execute(allocVars, TemplateScope.make()), this.out$Mnbindings);
                this.out$Mninits = new Pair(prim_syntax.Lit41.execute(allocVars, TemplateScope.make()), this.out$Mninits);
                return lambda4processBinding(prim_syntax.Lit42.execute(allocVars, TemplateScope.make()));
            }
            if (prim_syntax.Lit43.match(b, allocVars, 0)) {
                return prim_syntax.syntaxError(b, "missing initializion in letrec" instanceof Object[] ? (Object[]) "missing initializion in letrec" : new Object[]{"missing initializion in letrec"});
            }
            if (prim_syntax.Lit44.match(b, allocVars, 0)) {
                return prim_syntax.syntaxError(b, "invalid bindings syntax in letrec" instanceof Object[] ? (Object[]) "invalid bindings syntax in letrec" : new Object[]{"invalid bindings syntax in letrec"});
            }
            return syntax_case.error("syntax-case", b);
        }
    }
}
