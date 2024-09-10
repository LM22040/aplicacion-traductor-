package gnu.kawa.slib;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Convert;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.SourceMessages;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: syntaxutils.scm */
/* loaded from: classes2.dex */
public class syntaxutils extends ModuleBody {
    public static final Macro $Prvt$$Ex;
    public static final Macro $Prvt$typecase$Pc;
    public static final syntaxutils $instance;
    static final Keyword Lit0;
    static final PairWithPosition Lit1;
    static final PairWithPosition Lit10;
    static final PairWithPosition Lit11;
    static final PairWithPosition Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SyntaxRules Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final Keyword Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final PairWithPosition Lit3;
    static final PairWithPosition Lit4;
    static final PairWithPosition Lit5;
    static final PairWithPosition Lit6;
    static final IntNum Lit7;
    static final IntNum Lit8;
    static final PairWithPosition Lit9;
    public static final ModuleMethod expand;

    /* compiled from: syntaxutils.scm */
    /* loaded from: classes2.dex */
    public class frame extends ModuleBody {
        LList pack;
    }

    /* compiled from: syntaxutils.scm */
    /* loaded from: classes2.dex */
    public class frame0 extends ModuleBody {
        LList pack;
    }

    /* compiled from: syntaxutils.scm */
    /* loaded from: classes2.dex */
    public class frame1 extends ModuleBody {
        LList pack;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit26 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("as").readResolve();
        Lit25 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("else").readResolve();
        Lit24 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit23 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("cond").readResolve();
        Lit22 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit21 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("or").readResolve();
        Lit20 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit19 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("eql").readResolve();
        Lit18 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("expand").readResolve();
        Lit17 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("!").readResolve();
        Lit15 = simpleSymbol11;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol11}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b)\u0011\u0018\f\b\u0003\b\u0015\u0013", new Object[]{(SimpleSymbol) new SimpleSymbol("invoke").readResolve(), simpleSymbol8}, 1)}, 3);
        Lit16 = syntaxRules;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("typecase%").readResolve();
        Lit13 = simpleSymbol12;
        SyntaxRules syntaxRules2 = new SyntaxRules(new Object[]{simpleSymbol12, simpleSymbol9, simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u0002\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[]{Boolean.TRUE}, 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004\b\r\u000b", new Object[]{simpleSymbol6}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\f\u0002\f\u000f\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol9}, 4), "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004yY\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\b\u000b\b\u0015\u0013\b\u0011\u0018\u001c\b\u0011\u0018$\t\u0003\b\u001d\u001b", new Object[]{simpleSymbol5, (SimpleSymbol) new SimpleSymbol("eqv?").readResolve(), simpleSymbol8, simpleSymbol3, simpleSymbol12}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\\,\f\u0002\f\u000f\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol7}, 4), "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004\t\u0003)\t\u000b\b\u0015\u0013\b\u001d\u001b", new Object[]{simpleSymbol12}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007l<\f\u0002\r\u000f\b\b\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{simpleSymbol7}, 4), "\u0001\u0003\u0003\u0003", "\u0011\u0018\u0004\u0091\b\u0011\u0018\f\b\u0011\u0018\u0014\u0011\b\u0003\b\u0011\u0018\u001c\b\u0015\u0013\b\u0011\u0018$\t\u0003I\r\t\u000b\b\u0011\u0018\f\b\u0003\b\u0011\u0018,\b\u0011\u0018$\t\u0003\b\u001d\u001b", new Object[]{simpleSymbol4, (SimpleSymbol) new SimpleSymbol("f").readResolve(), simpleSymbol, simpleSymbol6, simpleSymbol12, Boolean.TRUE}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[0], 4), "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004ñ9\u0011\u0018\f\t\u0003\b\u000b\b\u0011\u0018\u0014Q\b\t\u0003\u0011\u0018\u001c\t\u000b\b\u0003\b\u0011\u0018$\b\u0015\u0013\b\u0011\u0018,\b\u0011\u00184\t\u0003\b\u001d\u001b", new Object[]{simpleSymbol5, (SimpleSymbol) new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME).readResolve(), simpleSymbol4, (SimpleSymbol) new SimpleSymbol("::").readResolve(), simpleSymbol6, simpleSymbol3, simpleSymbol12}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\u0011\u0018,\b\u0003", new Object[]{(SimpleSymbol) new SimpleSymbol("error").readResolve(), "typecase% failed", simpleSymbol11, (SimpleSymbol) new SimpleSymbol("getClass").readResolve(), simpleSymbol2, (SimpleSymbol) new SimpleSymbol("<object>").readResolve()}, 0)}, 4);
        Lit14 = syntaxRules2;
        Lit12 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol(":").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 634896);
        Lit11 = PairWithPosition.make(simpleSymbol2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 626704);
        Lit10 = PairWithPosition.make(simpleSymbol8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 552972);
        Lit9 = PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 479236);
        Lit8 = IntNum.make(1);
        Lit7 = IntNum.make(0);
        Lit6 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("if").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 417799);
        Lit5 = PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 409627);
        Lit4 = PairWithPosition.make(simpleSymbol, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 376839);
        Lit3 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("set").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 368647);
        Lit2 = Keyword.make("lang");
        Lit1 = PairWithPosition.make(simpleSymbol6, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 278557);
        Lit0 = Keyword.make("env");
        syntaxutils syntaxutilsVar = new syntaxutils();
        $instance = syntaxutilsVar;
        $Prvt$typecase$Pc = Macro.make(simpleSymbol12, syntaxRules2, syntaxutilsVar);
        $Prvt$$Ex = Macro.make(simpleSymbol11, syntaxRules, syntaxutilsVar);
        expand = new ModuleMethod(syntaxutilsVar, 1, simpleSymbol10, -4095);
        syntaxutilsVar.run();
    }

    public syntaxutils() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    public static Object expand$V(Object sexp, Object[] argsArray) {
        Object consX;
        Keyword keyword = Lit0;
        Object searchForKeyword = Keyword.searchForKeyword(argsArray, 0, keyword);
        if (searchForKeyword == Special.dfault) {
            searchForKeyword = misc.interactionEnvironment();
        }
        Object env = searchForKeyword;
        consX = LList.consX(new Object[]{sexp, LList.Empty});
        return unrewrite(rewriteForm$V(Quote.append$V(new Object[]{Lit1, consX}), new Object[]{keyword, env}));
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
                return expand$V(obj, objArr2);
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

    static Expression rewriteForm$V(Object exp, Object[] argsArray) {
        Object searchForKeyword = Keyword.searchForKeyword(argsArray, 0, Lit2);
        if (searchForKeyword == Special.dfault) {
            searchForKeyword = Language.getDefaultLanguage();
        }
        Object lang = searchForKeyword;
        Object searchForKeyword2 = Keyword.searchForKeyword(argsArray, 0, Lit0);
        if (searchForKeyword2 == Special.dfault) {
            searchForKeyword2 = misc.interactionEnvironment();
        }
        Object env = searchForKeyword2;
        try {
            try {
                NameLookup namelookup = NameLookup.getInstance((Environment) env, (Language) lang);
                SourceMessages messages = new SourceMessages();
                try {
                    Translator translator = new Translator((Language) lang, messages, namelookup);
                    translator.pushNewModule((String) null);
                    Compilation saved$Mncomp = Compilation.setSaveCurrent(translator);
                    try {
                        return translator.rewrite(exp);
                    } finally {
                        Compilation.restoreCurrent(saved$Mncomp);
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "kawa.lang.Translator.<init>(gnu.expr.Language,gnu.text.SourceMessages,gnu.expr.NameLookup)", 1, lang);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 2, lang);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 1, env);
        }
    }

    static Object unrewrite(Expression exp) {
        Object consX;
        Object consX2;
        Object consX3;
        Object consX4;
        Object consX5;
        Object consX6;
        frame frameVar = new frame();
        if (exp instanceof LetExp) {
            try {
                return unrewriteLet((LetExp) exp);
            } catch (ClassCastException e) {
                throw new WrongType(e, "exp", -2, exp);
            }
        }
        if (exp instanceof QuoteExp) {
            try {
                return unrewriteQuote((QuoteExp) exp);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "exp", -2, exp);
            }
        }
        if (exp instanceof SetExp) {
            try {
                SetExp exp2 = (SetExp) exp;
                consX = LList.consX(new Object[]{unrewrite(exp2.getNewValue()), LList.Empty});
                consX2 = LList.consX(new Object[]{exp2.getSymbol(), consX});
                return Quote.append$V(new Object[]{Lit3, consX2});
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "exp", -2, exp);
            }
        }
        if (exp instanceof LambdaExp) {
            try {
                LambdaExp exp3 = (LambdaExp) exp;
                Object[] objArr = new Object[2];
                objArr[0] = Lit4;
                Object[] objArr2 = new Object[2];
                frameVar.pack = LList.Empty;
                for (Declaration decl = exp3.firstDecl(); decl != null; decl = decl.nextDecl()) {
                    Object v = decl.getSymbol();
                    frameVar.pack = lists.cons(v, frameVar.pack);
                }
                objArr2[0] = LList.reverseInPlace(frameVar.pack);
                consX3 = LList.consX(new Object[]{unrewrite(exp3.body), LList.Empty});
                objArr2[1] = consX3;
                consX4 = LList.consX(objArr2);
                objArr[1] = consX4;
                return Quote.append$V(objArr);
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "exp", -2, exp);
            }
        }
        if (exp instanceof ReferenceExp) {
            try {
                return ((ReferenceExp) exp).getSymbol();
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "exp", -2, exp);
            }
        }
        if (exp instanceof ApplyExp) {
            try {
                return unrewriteApply((ApplyExp) exp);
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "exp", -2, exp);
            }
        }
        if (exp instanceof BeginExp) {
            try {
                return Quote.append$V(new Object[]{Lit5, unrewrite$St(((BeginExp) exp).getExpressions())});
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "exp", -2, exp);
            }
        }
        if (!(exp instanceof IfExp)) {
            return exp;
        }
        try {
            IfExp exp4 = (IfExp) exp;
            Object[] objArr3 = new Object[2];
            objArr3[0] = Lit6;
            Object[] objArr4 = new Object[2];
            objArr4[0] = unrewrite(exp4.getTest());
            Object[] objArr5 = new Object[2];
            objArr5[0] = unrewrite(exp4.getThenClause());
            Object[] objArr6 = new Object[2];
            Expression eclause = exp4.getElseClause();
            objArr6[0] = eclause == null ? LList.Empty : LList.list1(unrewrite(eclause));
            objArr6[1] = LList.Empty;
            objArr5[1] = Quote.append$V(objArr6);
            consX5 = LList.consX(objArr5);
            objArr4[1] = consX5;
            consX6 = LList.consX(objArr4);
            objArr3[1] = consX6;
            return Quote.append$V(objArr3);
        } catch (ClassCastException e8) {
            throw new WrongType(e8, "exp", -2, exp);
        }
    }

    static Object unrewrite$St(Expression[] exps) {
        frame0 frame0Var = new frame0();
        frame0Var.pack = LList.Empty;
        Object len = Integer.valueOf(exps.length);
        for (Object i = Lit7; Scheme.numEqu.apply2(i, len) == Boolean.FALSE; i = AddOp.$Pl.apply2(i, Lit8)) {
            Object v = unrewrite(exps[((Number) i).intValue()]);
            frame0Var.pack = lists.cons(v, frame0Var.pack);
        }
        return LList.reverseInPlace(frame0Var.pack);
    }

    static Object unrewriteLet(LetExp exp) {
        Object consX;
        Object consX2;
        frame1 frame1Var = new frame1();
        Object[] objArr = new Object[2];
        objArr[0] = Lit9;
        Object[] objArr2 = new Object[2];
        frame1Var.pack = LList.Empty;
        Declaration decl = exp.firstDecl();
        Object i = Lit7;
        while (decl != null) {
            Object v = LList.list2(decl.getSymbol(), unrewrite(exp.inits[((Number) i).intValue()]));
            frame1Var.pack = lists.cons(v, frame1Var.pack);
            Declaration nextDecl = decl.nextDecl();
            i = AddOp.$Pl.apply2(i, Lit8);
            decl = nextDecl;
        }
        objArr2[0] = LList.reverseInPlace(frame1Var.pack);
        consX = LList.consX(new Object[]{unrewrite(exp.body), LList.Empty});
        objArr2[1] = consX;
        consX2 = LList.consX(objArr2);
        objArr[1] = consX2;
        return Quote.append$V(objArr);
    }

    static Object unrewriteQuote(QuoteExp quoteExp) {
        String name;
        Object consX;
        Object value = quoteExp.getValue();
        if (Numeric.asNumericOrNull(value) != null) {
            try {
                return LangObjType.coerceNumeric(value);
            } catch (ClassCastException e) {
                throw new WrongType(e, "val", -2, value);
            }
        }
        if (value instanceof Boolean) {
            try {
                return value != Boolean.FALSE ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "val", -2, value);
            }
        }
        if (value instanceof Char) {
            try {
                return (Char) value;
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "val", -2, value);
            }
        }
        if (value instanceof Keyword) {
            try {
                return (Keyword) value;
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "val", -2, value);
            }
        }
        if (value instanceof CharSequence) {
            try {
                return (CharSequence) value;
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "val", -2, value);
            }
        }
        if (value == Special.undefined || value == EofClass.eofValue) {
            return value;
        }
        if (value instanceof Type) {
            try {
                name = ((Type) value).getName();
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "val", -2, value);
            }
        } else if (value instanceof Class) {
            try {
                name = ((Class) value).getName();
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "val", -2, value);
            }
        } else {
            consX = LList.consX(new Object[]{value, LList.Empty});
            return Quote.append$V(new Object[]{Lit10, consX});
        }
        return misc.string$To$Symbol(Format.formatToString(0, "<~a>", name));
    }

    static Object unrewriteApply(ApplyExp applyExp) {
        Declaration binding;
        Object consX;
        Expression function = applyExp.getFunction();
        Object unrewrite$St = unrewrite$St(applyExp.getArgs());
        if (function instanceof ReferenceExp) {
            try {
                binding = ((ReferenceExp) function).getBinding();
            } catch (ClassCastException e) {
                throw new WrongType(e, "fun", -2, function);
            }
        } else {
            binding = null;
        }
        Declaration declaration = binding;
        Declaration declarationFromStatic = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
        Object functionValue = applyExp.getFunctionValue();
        int i = ((declaration == null ? 1 : 0) + 1) & 1;
        if (i != 0) {
            int i2 = ((declarationFromStatic == null ? 1 : 0) + 1) & 1;
            if (i2 != 0) {
                if (SlotGet.getSlotValue(false, declaration, "field", "field", "getField", "isField", Scheme.instance) == declarationFromStatic.field) {
                    return unrewrite$St;
                }
            } else if (i2 != 0) {
                return unrewrite$St;
            }
        } else if (i != 0) {
            return unrewrite$St;
        }
        Object append$V = functionValue instanceof Convert ? Quote.append$V(new Object[]{Lit11, unrewrite$St}) : functionValue instanceof GetNamedPart ? Quote.append$V(new Object[]{Lit12, unrewrite$St}) : Boolean.FALSE;
        if (append$V != Boolean.FALSE) {
            return append$V;
        }
        consX = LList.consX(new Object[]{unrewrite(function), unrewrite$St});
        return consX;
    }
}
