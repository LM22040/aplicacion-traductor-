package gnu.kawa.lispexpr;

import gnu.bytecode.Field;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public abstract class LispLanguage extends Language {
    public static StaticFieldLocation getNamedPartLocation = null;
    public static final String quasiquote_sym = "quasiquote";
    public static final String quote_sym = "quote";
    public static final String unquote_sym = "unquote";
    public static final String unquotesplicing_sym = "unquote-splicing";
    public ReadTable defaultReadTable = createReadTable();
    public static final Symbol lookup_sym = Namespace.EmptyNamespace.getSymbol("$lookup$");
    public static final Symbol bracket_list_sym = Namespace.EmptyNamespace.getSymbol("$bracket-list$");
    public static final Symbol bracket_apply_sym = Namespace.EmptyNamespace.getSymbol("$bracket-apply$");

    public abstract ReadTable createReadTable();

    static {
        StaticFieldLocation staticFieldLocation = new StaticFieldLocation("gnu.kawa.functions.GetNamedPart", "getNamedPart");
        getNamedPartLocation = staticFieldLocation;
        staticFieldLocation.setProcedure();
    }

    @Override // gnu.expr.Language
    public Lexer getLexer(InPort inp, SourceMessages messages) {
        return new LispReader(inp, messages);
    }

    @Override // gnu.expr.Language
    public Compilation getCompilation(Lexer lexer, SourceMessages messages, NameLookup lexical) {
        return new Translator(this, messages, lexical);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x005c, code lost:
    
        if (r1.peek() != 41) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x005e, code lost:
    
        r1.fatal("An unexpected close paren was read.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0063, code lost:
    
        r0.finishModule(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0068, code lost:
    
        if ((r13 & 8) != 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x006a, code lost:
    
        r0.firstForm = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006c, code lost:
    
        r0.setState(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0074, code lost:
    
        return true;
     */
    @Override // gnu.expr.Language
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean parse(gnu.expr.Compilation r12, int r13) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r11 = this;
            r0 = r12
            kawa.lang.Translator r0 = (kawa.lang.Translator) r0
            gnu.text.Lexer r1 = r0.lexer
            gnu.expr.ModuleExp r2 = r0.mainLambda
            gnu.mapping.Values r3 = new gnu.mapping.Values
            r3.<init>()
            r4 = r1
            gnu.kawa.lispexpr.LispReader r4 = (gnu.kawa.lispexpr.LispReader) r4
            gnu.expr.Compilation r5 = gnu.expr.Compilation.setSaveCurrent(r0)
            java.lang.Object r6 = r0.pendingForm     // Catch: java.lang.Throwable -> L86
            if (r6 == 0) goto L1f
            java.lang.Object r6 = r0.pendingForm     // Catch: java.lang.Throwable -> L86
            r0.scanForm(r6, r2)     // Catch: java.lang.Throwable -> L86
            r6 = 0
            r0.pendingForm = r6     // Catch: java.lang.Throwable -> L86
        L1f:
            java.lang.Object r6 = r4.readCommand()     // Catch: java.lang.Throwable -> L86
            java.lang.Object r7 = gnu.lists.Sequence.eofValue     // Catch: java.lang.Throwable -> L86
            r8 = 1
            r9 = 0
            if (r6 != r7) goto L32
            r7 = r13 & 4
            if (r7 == 0) goto L56
        L2e:
            gnu.expr.Compilation.restoreCurrent(r5)
            return r9
        L32:
            r0.scanForm(r6, r2)     // Catch: java.lang.Throwable -> L86
            r7 = r13 & 4
            if (r7 == 0) goto L75
            gnu.text.SourceMessages r7 = r0.getMessages()     // Catch: java.lang.Throwable -> L86
            boolean r7 = r7.seenErrors()     // Catch: java.lang.Throwable -> L86
            if (r7 == 0) goto L56
        L43:
            int r7 = r4.peek()     // Catch: java.lang.Throwable -> L86
            if (r7 < 0) goto L56
            r10 = 13
            if (r7 == r10) goto L56
            r10 = 10
            if (r7 != r10) goto L52
            goto L56
        L52:
            r4.skip()     // Catch: java.lang.Throwable -> L86
            goto L43
        L56:
            int r6 = r1.peek()     // Catch: java.lang.Throwable -> L86
            r7 = 41
            if (r6 != r7) goto L63
            java.lang.String r6 = "An unexpected close paren was read."
            r1.fatal(r6)     // Catch: java.lang.Throwable -> L86
        L63:
            r0.finishModule(r2)     // Catch: java.lang.Throwable -> L86
            r6 = r13 & 8
            if (r6 != 0) goto L6c
            r0.firstForm = r9     // Catch: java.lang.Throwable -> L86
        L6c:
            r6 = 4
            r0.setState(r6)     // Catch: java.lang.Throwable -> L86
            gnu.expr.Compilation.restoreCurrent(r5)
            return r8
        L75:
            r7 = r13 & 8
            if (r7 == 0) goto L85
            int r7 = r0.getState()     // Catch: java.lang.Throwable -> L86
            r9 = 2
            if (r7 < r9) goto L85
        L81:
            gnu.expr.Compilation.restoreCurrent(r5)
            return r8
        L85:
            goto L1f
        L86:
            r6 = move-exception
            gnu.expr.Compilation.restoreCurrent(r5)
            goto L8c
        L8b:
            throw r6
        L8c:
            goto L8b
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispLanguage.parse(gnu.expr.Compilation, int):boolean");
    }

    @Override // gnu.expr.Language
    public void resolve(Compilation comp) {
        Translator tr = (Translator) comp;
        tr.resolveModule(tr.getModule());
    }

    @Override // gnu.expr.Language
    public Declaration declFromField(ModuleExp mod, Object fvalue, Field fld) {
        Declaration fdecl = super.declFromField(mod, fvalue, fld);
        boolean isFinal = (fld.getModifiers() & 16) != 0;
        if (isFinal && (fvalue instanceof Syntax)) {
            fdecl.setSyntax();
        }
        return fdecl;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void defSntxStFld(String name, String cname, String fname) {
        Object property = hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null;
        StaticFieldLocation loc = StaticFieldLocation.define(this.environ, this.environ.getSymbol(name), property, cname, fname);
        loc.setSyntax();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void defSntxStFld(String name, String cname) {
        defSntxStFld(name, cname, Compilation.mangleNameIfNeeded(name));
    }

    public Expression makeBody(Expression[] exps) {
        return new BeginExp(exps);
    }

    public Expression makeApply(Expression func, Expression[] args) {
        return new ApplyExp(func, args);
    }

    public boolean selfEvaluatingSymbol(Object obj) {
        return obj instanceof Keyword;
    }

    public static Symbol langSymbolToSymbol(Object sym) {
        return ((LispLanguage) Language.getDefaultLanguage()).fromLangSymbol(sym);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Symbol fromLangSymbol(Object sym) {
        if (sym instanceof String) {
            return getSymbol((String) sym);
        }
        return (Symbol) sym;
    }

    public Expression checkDefaultBinding(Symbol name, Translator tr) {
        return null;
    }
}
