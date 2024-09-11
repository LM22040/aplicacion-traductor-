package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.QuoteExp;
import gnu.expr.Symbols;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

/* compiled from: std_syntax.scm */
/* loaded from: classes.dex */
public class std_syntax extends ModuleBody {
    public static final Macro $Prvt$$Pccase;
    public static final Macro $Prvt$$Pccase$Mnmatch;
    public static final Macro $Prvt$$Pcdo$Mninit;
    public static final Macro $Prvt$$Pcdo$Mnlambda1;
    public static final Macro $Prvt$$Pcdo$Mnlambda2;
    public static final Macro $Prvt$$Pcdo$Mnstep;
    public static final Macro $Prvt$$Pclet$Mninit;
    public static final Macro $Prvt$$Pclet$Mnlambda1;
    public static final Macro $Prvt$$Pclet$Mnlambda2;
    public static final Location $Prvt$define;
    public static final Location $Prvt$define$Mnconstant;
    public static final Location $Prvt$if;
    public static final Location $Prvt$letrec;
    public static final std_syntax $instance;
    static final IntNum Lit0;
    static final IntNum Lit1;
    static final SimpleSymbol Lit10;
    static final SyntaxPattern Lit11;
    static final SyntaxPattern Lit12;
    static final SyntaxTemplate Lit13;
    static final SyntaxPattern Lit14;
    static final SyntaxTemplate Lit15;
    static final SimpleSymbol Lit16;
    static final SyntaxPattern Lit17;
    static final SyntaxPattern Lit18;
    static final SyntaxTemplate Lit19;
    static final SimpleSymbol Lit2;
    static final SyntaxPattern Lit20;
    static final SyntaxTemplate Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxRules Lit23;
    static final SimpleSymbol Lit24;
    static final SyntaxRules Lit25;
    static final SimpleSymbol Lit26;
    static final SyntaxRules Lit27;
    static final SimpleSymbol Lit28;
    static final SyntaxRules Lit29;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit30;
    static final SyntaxRules Lit31;
    static final SimpleSymbol Lit32;
    static final SyntaxRules Lit33;
    static final SimpleSymbol Lit34;
    static final SyntaxRules Lit35;
    static final SimpleSymbol Lit36;
    static final SyntaxRules Lit37;
    static final SimpleSymbol Lit38;
    static final SyntaxRules Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SyntaxRules Lit41;
    static final SimpleSymbol Lit42;
    static final SyntaxRules Lit43;
    static final SimpleSymbol Lit44;
    static final SyntaxRules Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SyntaxPattern Lit55;
    static final SimpleSymbol Lit56;
    static final SyntaxTemplate Lit57;
    static final SyntaxTemplate Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit60;
    static final SimpleSymbol Lit61;
    static final SyntaxRules Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit8;
    static final SyntaxRules Lit9;
    public static final Macro and;
    public static final Macro begin$Mnfor$Mnsyntax;

    /* renamed from: case, reason: not valid java name */
    public static final Macro f5case;
    public static final Macro cond;
    public static final ModuleMethod datum$Mn$Grsyntax$Mnobject;
    public static final Macro define$Mnfor$Mnsyntax;
    public static final Macro define$Mnprocedure;
    public static final Macro delay;

    /* renamed from: do, reason: not valid java name */
    public static final Macro f6do;
    public static final ModuleMethod free$Mnidentifier$Eq$Qu;
    public static final ModuleMethod generate$Mntemporaries;
    public static final ModuleMethod identifier$Qu;
    public static final Macro let;
    public static final Macro let$St;
    public static final Macro or;
    public static final ModuleMethod syntax$Mncolumn;
    public static final ModuleMethod syntax$Mnline;
    public static final ModuleMethod syntax$Mnobject$Mn$Grdatum;
    public static final ModuleMethod syntax$Mnsource;
    public static final Macro with$Mnsyntax;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("temp").readResolve();
        Lit77 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("=>").readResolve();
        Lit76 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("else").readResolve();
        Lit75 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("eqv?").readResolve();
        Lit74 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("x").readResolve();
        Lit73 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit72 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("letrec").readResolve();
        Lit71 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("%let").readResolve();
        Lit70 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("%syntax-error").readResolve();
        Lit69 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit68 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("make").readResolve();
        Lit67 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit66 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("<gnu.expr.GenericProc>").readResolve();
        Lit65 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit64 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("syntax-case").readResolve();
        Lit63 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("with-syntax").readResolve();
        Lit61 = simpleSymbol16;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\b\f\u0007\r\u000f\b\b\b", new Object[0], 2);
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit56 = simpleSymbol17;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol16}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\r\u000b", new Object[]{simpleSymbol17}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\t\u0010\b\t\u0003\b\u0011\u0018\f\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol15, simpleSymbol17}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L-\f\u0007\f\u000f\b\u0000\u0010\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4), "\u0003\u0003\u0001\u0003", "\u0011\u0018\u00041\u0011\u0018\f\b\r\u000b\t\u0010\b\u0019\b\u0005\u0003\b\u0011\u0018\u0014\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol15, (SimpleSymbol) new SimpleSymbol("list").readResolve(), simpleSymbol17}, 1)}, 4);
        Lit62 = syntaxRules;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("define-for-syntax").readResolve();
        Lit59 = simpleSymbol18;
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\u0003", new Object[0], 1);
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("begin-for-syntax").readResolve();
        Lit54 = simpleSymbol19;
        SyntaxRules syntaxRules2 = new SyntaxRules(new Object[]{simpleSymbol18}, new SyntaxRule[]{new SyntaxRule(syntaxPattern2, "\u0000", "\u0011\u0018\u0004\b\u0011\u0018\f\u0002", new Object[]{simpleSymbol19, (SimpleSymbol) new SimpleSymbol("define").readResolve()}, 0)}, 1);
        Lit60 = syntaxRules2;
        Lit58 = new SyntaxTemplate("\u0001\u0000", "\u0018\u0004", new Object[]{Values.empty}, 0);
        Lit57 = new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
        Lit55 = new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("syntax-column").readResolve();
        Lit53 = simpleSymbol20;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("syntax-line").readResolve();
        Lit52 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("syntax-source").readResolve();
        Lit51 = simpleSymbol22;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("free-identifier=?").readResolve();
        Lit50 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol("identifier?").readResolve();
        Lit49 = simpleSymbol24;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("generate-temporaries").readResolve();
        Lit48 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = (SimpleSymbol) new SimpleSymbol("datum->syntax-object").readResolve();
        Lit47 = simpleSymbol26;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("syntax-object->datum").readResolve();
        Lit46 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = (SimpleSymbol) new SimpleSymbol("define-procedure").readResolve();
        Lit44 = simpleSymbol28;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol28, simpleSymbol14, simpleSymbol13}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u0004Á\u0011\u0018\f\t\u0003\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\u0011\u0018\u001c\b\u0011\u0018,\b\u0003\b\u0011\u00184\t\u0003\u0011\u0018<\b\u0011\u0018D\b\r\u000b", new Object[]{simpleSymbol17, (SimpleSymbol) new SimpleSymbol("define-constant").readResolve(), simpleSymbol14, simpleSymbol13, simpleSymbol11, simpleSymbol12, (SimpleSymbol) new SimpleSymbol("invoke").readResolve(), PairWithPosition.make(simpleSymbol12, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("setProperties").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 1024020), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 1024020), (SimpleSymbol) new SimpleSymbol("java.lang.Object[]").readResolve()}, 1)}, 2);
        Lit45 = syntaxRules3;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("delay").readResolve();
        Lit42 = simpleSymbol29;
        SyntaxRules syntaxRules4 = new SyntaxRules(new Object[]{simpleSymbol29}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\t\u0010\b\u0003", new Object[]{simpleSymbol11, (SimpleSymbol) new SimpleSymbol("<kawa.lang.Promise>").readResolve(), simpleSymbol10}, 0)}, 1);
        Lit43 = syntaxRules4;
        SimpleSymbol simpleSymbol30 = (SimpleSymbol) new SimpleSymbol("do").readResolve();
        Lit40 = simpleSymbol30;
        Object[] objArr = {simpleSymbol30, simpleSymbol14};
        SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\u001c\f\u000f\u0013\r\u001f\u0018\b\b", new Object[0], 4);
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("%do-lambda1").readResolve();
        Lit36 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) new SimpleSymbol("%do-step").readResolve();
        Lit32 = simpleSymbol32;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("%do-init").readResolve();
        Lit34 = simpleSymbol33;
        SyntaxRules syntaxRules5 = new SyntaxRules(objArr, new SyntaxRule[]{new SyntaxRule(syntaxPattern3, "\u0003\u0001\u0000\u0003", "\u0011\u0018\u0004Ɖ\b\u0011\u0018\f\b\u0011\u0018\u0014\u0019\b\u0005\u0003\t\u0010\b\u0011\u0018\u001c)\u0011\u0018$\b\u000b\u0081\u0011\u0018,\u0011\u001d\u001b\b\u0011\u0018\f\b\u0005\u0011\u00184\u0003\b\u0011\u0018,\u0011\u0018<\u0012\b\u0011\u0018\f\b\u0005\u0011\u0018D\b\u0003", new Object[]{simpleSymbol7, (SimpleSymbol) new SimpleSymbol("%do%loop").readResolve(), simpleSymbol31, simpleSymbol6, (SimpleSymbol) new SimpleSymbol("not").readResolve(), simpleSymbol17, simpleSymbol32, Values.empty, simpleSymbol33}, 1)}, 4);
        Lit41 = syntaxRules5;
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) new SimpleSymbol("%do-lambda2").readResolve();
        Lit38 = simpleSymbol34;
        SyntaxRules syntaxRules6 = new SyntaxRules(new Object[]{simpleSymbol34}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\n\u0019\t\u0003\u0013\b\u001b", new Object[]{simpleSymbol34}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{simpleSymbol10}, 0)}, 4);
        Lit39 = syntaxRules6;
        SyntaxRules syntaxRules7 = new SyntaxRules(new Object[]{simpleSymbol31, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018l\\\f\u0007\f\u0002\f\u000f\f\u0017\f\u001f\b#\f/\f7\b", new Object[]{simpleSymbol14}, 7), "\u0001\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\"I9\t\u0003\u0011\u0018\f\b\u000b+\b3", new Object[]{simpleSymbol31, simpleSymbol14}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\\L\f\u0007\f\u0002\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[]{simpleSymbol14}, 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001aI9\t\u0003\u0011\u0018\f\b\u000b#\b+", new Object[]{simpleSymbol31, simpleSymbol14}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018L<\f\u0007\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[0], 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001a\u0019\t\u0003#\b+", new Object[]{simpleSymbol31}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\f\u001f\f'\b", new Object[0], 5), "\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u0012\u0019\t\u0003\u001b\b#", new Object[]{simpleSymbol31}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u0010\b\u000b", new Object[]{simpleSymbol34}, 0)}, 7);
        Lit37 = syntaxRules7;
        SyntaxRules syntaxRules8 = new SyntaxRules(new Object[]{simpleSymbol33, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\\\f\u0007\f\u0002\f\u000f\f\u0017\f\u001f\b\b", new Object[]{simpleSymbol14}, 4), "\u0001\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u0002\f\u000f\f\u0017\b\b", new Object[]{simpleSymbol14}, 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\b", new Object[0], 3), "\u0001\u0001\u0001", "\u000b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018,\f\u0007\f\u000f\b\b", new Object[0], 2), "\u0001\u0001", "\u000b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\b", new Object[0], 1), "\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("do binding with no value", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 794643), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 794628)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\f\u0017\f\u001f\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("do binding must have syntax: (var [:: type] init [step])", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 806917), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 802820)}, 0)}, 4);
        Lit35 = syntaxRules8;
        SyntaxRules syntaxRules9 = new SyntaxRules(new Object[]{simpleSymbol32, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\f\u001f\b", new Object[]{simpleSymbol14}, 4), "\u0001\u0001\u0001\u0001", "\u001b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{simpleSymbol14}, 3), "\u0001\u0001\u0001", "\u0003", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0003", new Object[0], 0)}, 4);
        Lit33 = syntaxRules9;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("let*").readResolve();
        Lit30 = simpleSymbol35;
        SyntaxRules syntaxRules10 = new SyntaxRules(new Object[]{simpleSymbol35}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\u0003", new Object[0], 1), "\u0000", "\u0011\u0018\u0004\t\u0010\u0002", new Object[]{simpleSymbol8}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\u000b", new Object[0], 2), "\u0001\u0000", "\u0011\u0018\u0004\u0011\b\u0003\n", new Object[]{simpleSymbol8}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3), "\u0001\u0000\u0000", "\u0011\u0018\u0004\u0011\b\u0003\b\u0011\u0018\f\t\n\u0012", new Object[]{simpleSymbol8, simpleSymbol35}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\u000b", new Object[0], 2), "\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("invalid bindings list in let*", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 679943), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 675846)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u0003", new Object[0], 1), "\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("missing bindings list in let*", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 692231), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 688134)}, 0)}, 3);
        Lit31 = syntaxRules10;
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit28 = simpleSymbol36;
        SyntaxPattern syntaxPattern4 = new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\u000b", new Object[0], 2);
        Object[] objArr2 = {simpleSymbol8};
        SyntaxPattern syntaxPattern5 = new SyntaxPattern("\f\u0018\f\u0007,\r\u000f\b\b\b\u0013", new Object[0], 3);
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("%let-lambda1").readResolve();
        Lit22 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) new SimpleSymbol("%let-init").readResolve();
        Lit26 = simpleSymbol38;
        SyntaxRules syntaxRules11 = new SyntaxRules(new Object[]{simpleSymbol36}, new SyntaxRule[]{new SyntaxRule(syntaxPattern4, "\u0003\u0000", "\u0011\u0018\u0004\u0019\b\u0005\u0003\n", objArr2, 1), new SyntaxRule(syntaxPattern5, "\u0001\u0003\u0000", "©\u0011\u0018\u0004y\b\t\u0003\b\u0011\u0018\f\u0019\b\r\u000b\t\u0010\b\u0012\b\u0003\b\r\u0011\u0018\u0014\b\u000b", new Object[]{simpleSymbol7, simpleSymbol37, simpleSymbol38}, 1)}, 3);
        Lit29 = syntaxRules11;
        SyntaxRules syntaxRules12 = new SyntaxRules(new Object[]{simpleSymbol38, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\f\u0007\f\u000f\b\b", new Object[0], 2), "\u0001\u0001", "\u000b", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u0002\f\u000f\f\u0017\b\b", new Object[]{simpleSymbol14}, 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0013", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\b", new Object[0], 1), "\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("let binding with no value", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 552979), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 552964)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\f\u0017\f\u001f\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("let binding must have syntax: (var [type] init)", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 565253), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 561156)}, 0)}, 4);
        Lit27 = syntaxRules12;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("%let-lambda2").readResolve();
        Lit24 = simpleSymbol39;
        SyntaxRules syntaxRules13 = new SyntaxRules(new Object[]{simpleSymbol39}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\n\u0019\t\u0003\u0013\b\u001b", new Object[]{simpleSymbol39}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u000b", new Object[]{simpleSymbol10}, 0)}, 4);
        Lit25 = syntaxRules13;
        SyntaxRules syntaxRules14 = new SyntaxRules(new Object[]{simpleSymbol37, simpleSymbol14}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018L<\f\u0007\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[0], 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001a1!\t\u0003\b\u000b#\b+", new Object[]{simpleSymbol37}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\\L\f\u0007\f\u0002\f\u000f\f\u0017\b\u001b\f'\f/\b", new Object[]{simpleSymbol14}, 6), "\u0001\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u001a1!\t\u0003\b\u000b#\b+", new Object[]{simpleSymbol37}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<,\f\u0007\f\u000f\b\u0013\f\u001f\f'\b", new Object[0], 5), "\u0001\u0001\u0000\u0001\u0001", "\u0011\u0018\u0004\t\u0012\u0019\t\u0003\u001b\b#", new Object[]{simpleSymbol37}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\b\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u0010\b\u000b", new Object[]{simpleSymbol39}, 0)}, 6);
        Lit23 = syntaxRules14;
        Lit21 = new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f\u0011\u0018\f\b\t\u0003\b\u0015\u0013", new Object[]{simpleSymbol8, simpleSymbol5, simpleSymbol6}, 1);
        Lit20 = new SyntaxPattern("\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
        Lit19 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit18 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
        Lit17 = new SyntaxPattern("\f\u0007\b", new Object[0], 1);
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol("or").readResolve();
        Lit16 = simpleSymbol40;
        Lit15 = new SyntaxTemplate("\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u000b\b\u0011\u0018\u0014\u0011\u0018\f)\t\u0003\b\u0015\u0013\u0018\u001c", new Object[]{simpleSymbol8, simpleSymbol5, simpleSymbol6, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 385052)}, 1);
        Lit14 = new SyntaxPattern("\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
        Lit13 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit12 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
        Lit11 = new SyntaxPattern("\f\u0007\b", new Object[0], 1);
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
        Lit10 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = (SimpleSymbol) new SimpleSymbol("%case-match").readResolve();
        Lit8 = simpleSymbol42;
        SyntaxRules syntaxRules15 = new SyntaxRules(new Object[]{simpleSymbol42}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\b\u000b", new Object[]{simpleSymbol4, simpleSymbol12}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004Y\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\b\u000b\b\u0011\u0018\u001c\t\u0003\b\u0015\u0013", new Object[]{simpleSymbol40, simpleSymbol4, simpleSymbol12, simpleSymbol42}, 1)}, 3);
        Lit9 = syntaxRules15;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("%case").readResolve();
        Lit6 = simpleSymbol43;
        SyntaxRules syntaxRules16 = new SyntaxRules(new Object[]{simpleSymbol43, simpleSymbol3}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u0002\r\u000f\b\b\b\b", new Object[]{simpleSymbol3}, 2), "\u0001\u0003", "\u0011\u0018\u0004\b\r\u000b", new Object[]{simpleSymbol17}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u0002\r\u000f\b\b\b\u0013", new Object[]{simpleSymbol3}, 3), "\u0001\u0003\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("junk following else (in case)", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 241674), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 237577)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\r\u000f\b\b\b\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004A\u0011\u0018\f\t\u0003\b\r\u000b\b\u0011\u0018\u0014\b\u0015\u0013", new Object[]{simpleSymbol6, simpleSymbol42, simpleSymbol17}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\r\u000f\b\b\b\r\u0017\u0010\b\b\f\u001f\r' \b\b", new Object[0], 5), "\u0001\u0003\u0003\u0001\u0003", "\u0011\u0018\u0004A\u0011\u0018\f\t\u0003\b\r\u000b1\u0011\u0018\u0014\b\u0015\u0013\b\u0011\u0018\u001c\t\u0003\t\u001b\b%#", new Object[]{simpleSymbol6, simpleSymbol42, simpleSymbol17, simpleSymbol43}, 1)}, 5);
        Lit7 = syntaxRules16;
        SimpleSymbol simpleSymbol44 = (SimpleSymbol) new SimpleSymbol("case").readResolve();
        Lit4 = simpleSymbol44;
        SyntaxRules syntaxRules17 = new SyntaxRules(new Object[]{simpleSymbol44}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\u0011\u0018\f\b\r\u000b", new Object[]{simpleSymbol8, (SimpleSymbol) new SimpleSymbol("tmp").readResolve(), simpleSymbol43}, 1)}, 2);
        Lit5 = syntaxRules17;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("cond").readResolve();
        Lit2 = simpleSymbol45;
        SyntaxRules syntaxRules18 = new SyntaxRules(new Object[]{simpleSymbol45, simpleSymbol3, simpleSymbol2}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0002\f\u0007\r\u000f\b\b\b\b", new Object[]{simpleSymbol3}, 2), "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\r\u000b", new Object[]{simpleSymbol17}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0002\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[]{simpleSymbol3}, 3), "\u0001\u0003\u0003", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol9, PairWithPosition.make("else clause must be last clause of cond", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 86035), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 86020)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u0002\f\u000f\b\b", new Object[]{simpleSymbol2}, 2), "\u0001\u0001", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\u0011\u0018\f\b\t\u000b\u0018\u001c", new Object[]{simpleSymbol8, simpleSymbol, simpleSymbol6, PairWithPosition.make(simpleSymbol, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 102423)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u0002\f\u000f\b\f\u0017\r\u001f\u0018\b\b", new Object[]{simpleSymbol2}, 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\u0011\u0018\f!\t\u000b\u0018\u001c\b\u0011\u0018$\t\u0013\b\u001d\u001b", new Object[]{simpleSymbol8, simpleSymbol, simpleSymbol6, PairWithPosition.make(simpleSymbol, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 122898), simpleSymbol45}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\b", new Object[0], 1), "\u0001", "\u0003", new Object[0], 0), new SyntaxRule(new SyntaxPattern("\f\u0018\u001c\f\u0007\b\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\u000b\b\u0015\u0013", new Object[]{simpleSymbol40, simpleSymbol45}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\u000b\b\u0015\u0013", new Object[]{simpleSymbol6, simpleSymbol17}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\r\u0017\u0010\b\b\f\u001f\r' \b\b", new Object[0], 5), "\u0001\u0001\u0003\u0001\u0003", "\u0011\u0018\u0004\t\u0003A\u0011\u0018\f\t\u000b\b\u0015\u0013\b\u0011\u0018\u0014\t\u001b\b%#", new Object[]{simpleSymbol6, simpleSymbol17, simpleSymbol45}, 1)}, 5);
        Lit3 = syntaxRules18;
        Lit1 = IntNum.make(1);
        Lit0 = IntNum.make(0);
        std_syntax std_syntaxVar = new std_syntax();
        $instance = std_syntaxVar;
        $Prvt$define = StaticFieldLocation.make("kawa.lib.prim_syntax", "define");
        $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
        $Prvt$if = StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
        $Prvt$letrec = StaticFieldLocation.make("kawa.lib.prim_syntax", "letrec");
        cond = Macro.make(simpleSymbol45, syntaxRules18, std_syntaxVar);
        f5case = Macro.make(simpleSymbol44, syntaxRules17, std_syntaxVar);
        $Prvt$$Pccase = Macro.make(simpleSymbol43, syntaxRules16, std_syntaxVar);
        $Prvt$$Pccase$Mnmatch = Macro.make(simpleSymbol42, syntaxRules15, std_syntaxVar);
        and = Macro.make(simpleSymbol41, new ModuleMethod(std_syntaxVar, 1, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), std_syntaxVar);
        or = Macro.make(simpleSymbol40, new ModuleMethod(std_syntaxVar, 2, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN), std_syntaxVar);
        $Prvt$$Pclet$Mnlambda1 = Macro.make(simpleSymbol37, syntaxRules14, std_syntaxVar);
        $Prvt$$Pclet$Mnlambda2 = Macro.make(simpleSymbol39, syntaxRules13, std_syntaxVar);
        $Prvt$$Pclet$Mninit = Macro.make(simpleSymbol38, syntaxRules12, std_syntaxVar);
        let = Macro.make(simpleSymbol36, syntaxRules11, std_syntaxVar);
        let$St = Macro.make(simpleSymbol35, syntaxRules10, std_syntaxVar);
        $Prvt$$Pcdo$Mnstep = Macro.make(simpleSymbol32, syntaxRules9, std_syntaxVar);
        $Prvt$$Pcdo$Mninit = Macro.make(simpleSymbol33, syntaxRules8, std_syntaxVar);
        $Prvt$$Pcdo$Mnlambda1 = Macro.make(simpleSymbol31, syntaxRules7, std_syntaxVar);
        $Prvt$$Pcdo$Mnlambda2 = Macro.make(simpleSymbol34, syntaxRules6, std_syntaxVar);
        f6do = Macro.make(simpleSymbol30, syntaxRules5, std_syntaxVar);
        delay = Macro.make(simpleSymbol29, syntaxRules4, std_syntaxVar);
        define$Mnprocedure = Macro.make(simpleSymbol28, syntaxRules3, std_syntaxVar);
        syntax$Mnobject$Mn$Grdatum = new ModuleMethod(std_syntaxVar, 3, simpleSymbol27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        datum$Mn$Grsyntax$Mnobject = new ModuleMethod(std_syntaxVar, 4, simpleSymbol26, 8194);
        generate$Mntemporaries = new ModuleMethod(std_syntaxVar, 5, simpleSymbol25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        identifier$Qu = new ModuleMethod(std_syntaxVar, 6, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        free$Mnidentifier$Eq$Qu = new ModuleMethod(std_syntaxVar, 7, simpleSymbol23, 8194);
        syntax$Mnsource = new ModuleMethod(std_syntaxVar, 8, simpleSymbol22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        syntax$Mnline = new ModuleMethod(std_syntaxVar, 9, simpleSymbol21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        syntax$Mncolumn = new ModuleMethod(std_syntaxVar, 10, simpleSymbol20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod = new ModuleMethod(std_syntaxVar, 11, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm:298");
        begin$Mnfor$Mnsyntax = Macro.make(simpleSymbol19, moduleMethod, std_syntaxVar);
        define$Mnfor$Mnsyntax = Macro.make(simpleSymbol18, syntaxRules2, std_syntaxVar);
        with$Mnsyntax = Macro.make(simpleSymbol16, syntaxRules, std_syntaxVar);
        std_syntaxVar.run();
    }

    public std_syntax() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    static Object lambda1(Object f) {
        Object[] allocVars = SyntaxPattern.allocVars(3, null);
        if (Lit11.match(f, allocVars, 0)) {
            return new QuoteExp(Language.getDefaultLanguage().booleanObject(true));
        }
        if (Lit12.match(f, allocVars, 0)) {
            return Lit13.execute(allocVars, TemplateScope.make());
        }
        if (!Lit14.match(f, allocVars, 0)) {
            return syntax_case.error("syntax-case", f);
        }
        return Lit15.execute(allocVars, TemplateScope.make());
    }

    static Object lambda2(Object f) {
        Object[] allocVars = SyntaxPattern.allocVars(3, null);
        if (Lit17.match(f, allocVars, 0)) {
            return new QuoteExp(Language.getDefaultLanguage().booleanObject(false));
        }
        if (Lit18.match(f, allocVars, 0)) {
            return Lit19.execute(allocVars, TemplateScope.make());
        }
        if (!Lit20.match(f, allocVars, 0)) {
            return syntax_case.error("syntax-case", f);
        }
        return Lit21.execute(allocVars, TemplateScope.make());
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
            case 2:
            case 11:
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
            case 7:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 5:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
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
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 7:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Object generateTemporaries(Object list) {
        Object n = Integer.valueOf(Translator.listLength(list));
        Object lst = LList.Empty;
        while (Scheme.numEqu.apply2(n, Lit0) == Boolean.FALSE) {
            n = AddOp.$Mn.apply2(n, Lit1);
            lst = new Pair(SyntaxForms.makeWithTemplate(list, Symbols.gentemp()), lst);
        }
        return lst;
    }

    public static boolean isIdentifier(Object form) {
        boolean x = form instanceof Symbol;
        if (x) {
            return x;
        }
        boolean x2 = form instanceof SyntaxForm;
        if (!x2) {
            return x2;
        }
        try {
            return SyntaxForms.isIdentifier((SyntaxForm) form);
        } catch (ClassCastException e) {
            throw new WrongType(e, "kawa.lang.SyntaxForms.isIdentifier(kawa.lang.SyntaxForm)", 1, form);
        }
    }

    public static boolean isFreeIdentifier$Eq(Object id1, Object id2) {
        try {
            try {
                return SyntaxForms.freeIdentifierEquals((SyntaxForm) id1, (SyntaxForm) id2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 2, id2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 1, id1);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 4:
                return SyntaxForms.makeWithTemplate(obj, obj2);
            case 7:
                return isFreeIdentifier$Eq(obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static Object syntaxSource(Object form) {
        String str;
        if (form instanceof SyntaxForm) {
            return syntaxSource(((SyntaxForm) form).getDatum());
        }
        return (!(form instanceof PairWithPosition) || (str = ((PairWithPosition) form).getFileName()) == null) ? Boolean.FALSE : str;
    }

    public static Object syntaxLine(Object form) {
        if (form instanceof SyntaxForm) {
            return syntaxLine(((SyntaxForm) form).getDatum());
        }
        return form instanceof PairWithPosition ? Integer.valueOf(((PairWithPosition) form).getLineNumber()) : Boolean.FALSE;
    }

    public static Object syntaxColumn(Object form) {
        if (form instanceof SyntaxForm) {
            return syntaxLine(((SyntaxForm) form).getDatum());
        }
        return form instanceof PairWithPosition ? Integer.valueOf(((PairWithPosition) form).getColumnNumber() + 0) : Boolean.FALSE;
    }

    static Object lambda3(Object form) {
        Object[] allocVars = SyntaxPattern.allocVars(2, null);
        if (Lit55.match(form, allocVars, 0)) {
            if (Eval.eval.apply1(Quote.quote(new Pair(Lit56, Lit57.execute(allocVars, TemplateScope.make())))) != Boolean.FALSE) {
                return Lit58.execute(allocVars, TemplateScope.make());
            }
        }
        return syntax_case.error("syntax-case", form);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return lambda1(obj);
            case 2:
                return lambda2(obj);
            case 3:
                return Quote.quote(obj);
            case 4:
            case 7:
            default:
                return super.apply1(moduleMethod, obj);
            case 5:
                return generateTemporaries(obj);
            case 6:
                return isIdentifier(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return syntaxSource(obj);
            case 9:
                return syntaxLine(obj);
            case 10:
                return syntaxColumn(obj);
            case 11:
                return lambda3(obj);
        }
    }
}
