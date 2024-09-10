package gnu.xquery.lang;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ResolveNames;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.GetModuleClass;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.WrongArguments;
import gnu.xml.NamespaceBinding;
import gnu.xquery.util.NamedCollator;

/* loaded from: classes2.dex */
public class XQResolveNames extends ResolveNames {
    public static final int BASE_URI_BUILTIN = -11;
    public static final int CASTABLE_AS_BUILTIN = -34;
    public static final int CAST_AS_BUILTIN = -33;
    public static final int COLLECTION_BUILTIN = -8;
    public static final int COMPARE_BUILTIN = -4;
    public static final int DEEP_EQUAL_BUILTIN = -25;
    public static final int DEFAULT_COLLATION_BUILTIN = -29;
    public static final int DISTINCT_VALUES_BUILTIN = -5;
    public static final int DOC_AVAILABLE_BUILTIN = -10;
    public static final int DOC_BUILTIN = -9;
    public static final int HANDLE_EXTENSION_BUILTIN = -3;
    public static final int IDREF_BUILTIN = -31;
    public static final int ID_BUILTIN = -30;
    public static final int INDEX_OF_BUILTIN = -15;
    public static final int LANG_BUILTIN = -23;
    public static final int LAST_BUILTIN = -1;
    public static final int LOCAL_NAME_BUILTIN = -6;
    public static final int MAX_BUILTIN = -27;
    public static final int MIN_BUILTIN = -26;
    public static final int NAMESPACE_URI_BUILTIN = -7;
    public static final int NAME_BUILTIN = -24;
    public static final int NORMALIZE_SPACE_BUILTIN = -17;
    public static final int NUMBER_BUILTIN = -28;
    public static final int POSITION_BUILTIN = -2;
    public static final int RESOLVE_PREFIX_BUILTIN = -13;
    public static final int RESOLVE_URI_BUILTIN = -12;
    public static final int ROOT_BUILTIN = -32;
    public static final int STATIC_BASE_URI_BUILTIN = -14;
    public static final int STRING_BUILTIN = -16;
    public static final int UNORDERED_BUILTIN = -18;
    public static final int XS_QNAME_BUILTIN = -35;
    public static final int XS_QNAME_IGNORE_DEFAULT_BUILTIN = -36;
    public Namespace[] functionNamespacePath;
    private Declaration moduleDecl;
    public XQParser parser;
    public static final Declaration handleExtensionDecl = makeBuiltin("(extension)", -3);
    public static final Declaration castAsDecl = makeBuiltin("(cast as)", -33);
    public static final Declaration castableAsDecl = makeBuiltin("(castable as)", -34);
    public static final Declaration lastDecl = makeBuiltin("last", -1);
    public static final Declaration xsQNameDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "QName"), -35);
    public static final Declaration xsQNameIgnoreDefaultDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "(QName-ignore-default)"), -36);
    public static final Declaration staticBaseUriDecl = makeBuiltin("static-base-uri", -14);
    public static final Declaration resolvePrefixDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "(resolve-prefix)"), -13);

    public static Declaration makeBuiltin(String name, int code) {
        return makeBuiltin(Symbol.make(XQuery.XQUERY_FUNCTION_NAMESPACE, name, "fn"), code);
    }

    public static Declaration makeBuiltin(Symbol name, int code) {
        Declaration decl = new Declaration(name);
        decl.setProcedureDecl(true);
        decl.setCode(code);
        return decl;
    }

    public XQResolveNames() {
        this(null);
    }

    void pushBuiltin(String name, int code) {
        this.lookup.push(makeBuiltin(name, code));
    }

    public XQResolveNames(Compilation comp) {
        super(comp);
        this.functionNamespacePath = XQuery.defaultFunctionNamespacePath;
        this.lookup.push(lastDecl);
        this.lookup.push(xsQNameDecl);
        this.lookup.push(staticBaseUriDecl);
        pushBuiltin("position", -2);
        pushBuiltin("compare", -4);
        pushBuiltin("distinct-values", -5);
        pushBuiltin("local-name", -6);
        pushBuiltin("name", -24);
        pushBuiltin("namespace-uri", -7);
        pushBuiltin("root", -32);
        pushBuiltin("base-uri", -11);
        pushBuiltin("lang", -23);
        pushBuiltin("resolve-uri", -12);
        pushBuiltin("collection", -8);
        pushBuiltin("doc", -9);
        pushBuiltin("document", -9);
        pushBuiltin("doc-available", -10);
        pushBuiltin("index-of", -15);
        pushBuiltin(PropertyTypeConstants.PROPERTY_TYPE_STRING, -16);
        pushBuiltin("normalize-space", -17);
        pushBuiltin("unordered", -18);
        pushBuiltin("deep-equal", -25);
        pushBuiltin("min", -26);
        pushBuiltin("max", -27);
        pushBuiltin("number", -28);
        pushBuiltin("default-collation", -29);
        pushBuiltin("id", -30);
        pushBuiltin("idref", -31);
    }

    @Override // gnu.expr.ResolveNames
    protected void push(ScopeExp exp) {
        for (Declaration decl = exp.firstDecl(); decl != null; decl = decl.nextDecl()) {
            push(decl);
        }
    }

    void push(Declaration decl) {
        Compilation comp = getCompilation();
        Object name = decl.getSymbol();
        boolean function = decl.isProcedureDecl();
        if (name instanceof String) {
            int line = decl.getLineNumber();
            if (line > 0 && comp != null) {
                String saveFilename = comp.getFileName();
                int saveLine = comp.getLineNumber();
                int saveColumn = comp.getColumnNumber();
                comp.setLocation(decl);
                name = this.parser.namespaceResolve((String) name, function);
                comp.setLine(saveFilename, saveLine, saveColumn);
            } else {
                name = this.parser.namespaceResolve((String) name, function);
            }
            if (name == null) {
                return;
            } else {
                decl.setName(name);
            }
        }
        Declaration old = this.lookup.lookup(name, XQuery.instance.getNamespaceOf(decl));
        if (old != null) {
            if (decl.context == old.context) {
                ScopeExp.duplicateDeclarationError(old, decl, comp);
            } else if (XQParser.warnHidePreviousDeclaration && (!(name instanceof Symbol) || ((Symbol) name).getNamespace() != null)) {
                comp.error('w', decl, "declaration ", " hides previous declaration");
            }
        }
        this.lookup.push(decl);
    }

    Declaration flookup(Symbol sym) {
        Declaration decl;
        Environment env = XQuery.xqEnvironment;
        Location loc = env.lookup(sym, EnvironmentKey.FUNCTION);
        if (loc == null) {
            return null;
        }
        Location loc2 = loc.getBase();
        if ((loc2 instanceof StaticFieldLocation) && (decl = ((StaticFieldLocation) loc2).getDeclaration()) != null) {
            return decl;
        }
        Object val = loc2.get(null);
        if (val == null) {
            return null;
        }
        return procToDecl(sym, val);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.ResolveNames, gnu.expr.ExpVisitor
    public Expression visitReferenceExp(ReferenceExp exp, Void ignored) {
        return visitReferenceExp(exp, (ApplyExp) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00af, code lost:
    
        r7 = r11.parser.namespaceResolve(r5, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b5, code lost:
    
        if (r7 == null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b7, code lost:
    
        r4 = r11.lookup.lookup(r7, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bd, code lost:
    
        if (r4 != null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00bf, code lost:
    
        if (r1 != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c1, code lost:
    
        if (r2 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c3, code lost:
    
        r8 = r7.getNamespaceURI();
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ce, code lost:
    
        if (gnu.xquery.lang.XQuery.SCHEMA_NAMESPACE.equals(r8) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d0, code lost:
    
        r9 = gnu.xquery.lang.XQuery.getStandardType(r7.getName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ef, code lost:
    
        if (r9 == null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00fa, code lost:
    
        return new gnu.expr.QuoteExp(r9).setLine(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00fb, code lost:
    
        if (r8 == null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0102, code lost:
    
        if (r8.length() <= 6) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x010a, code lost:
    
        if (r8.startsWith("class:") == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x010c, code lost:
    
        r6 = gnu.bytecode.ClassType.make(r8.substring(6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x011c, code lost:
    
        return gnu.kawa.functions.CompileNamedPart.makeExp(r6, r7.getName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x011d, code lost:
    
        r4 = flookup(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d9, code lost:
    
        if (r2 == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00db, code lost:
    
        if (r8 != "") goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00e5, code lost:
    
        if (getCompilation().isPedantic() != false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00e7, code lost:
    
        r9 = kawa.standard.Scheme.string2Type(r7.getName());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected gnu.expr.Expression visitReferenceExp(gnu.expr.ReferenceExp r12, gnu.expr.ApplyExp r13) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQResolveNames.visitReferenceExp(gnu.expr.ReferenceExp, gnu.expr.ApplyExp):gnu.expr.Expression");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // gnu.expr.ResolveNames, gnu.expr.ExpVisitor
    public Expression visitSetExp(SetExp exp, Void ignored) {
        Expression result = super.visitSetExp(exp, ignored);
        Declaration decl = exp.getBinding();
        if (decl != null && !getCompilation().immediate) {
            Object name = decl.getSymbol();
            if ((name instanceof Symbol) && XQuery.LOCAL_NAMESPACE.equals(((Symbol) name).getNamespaceURI())) {
                Expression new_value = exp.getNewValue();
                if (!(new_value instanceof ApplyExp) || ((ApplyExp) new_value).getFunction() != XQParser.getExternalFunction) {
                    decl.setFlag(16777216L);
                    decl.setPrivate(true);
                }
            }
        }
        return result;
    }

    private Expression visitStatements(Expression exp) {
        if (exp instanceof BeginExp) {
            BeginExp bbody = (BeginExp) exp;
            Expression[] exps = bbody.getExpressions();
            int nexps = bbody.getExpressionCount();
            for (int i = 0; i < nexps; i++) {
                exps[i] = visitStatements(exps[i]);
            }
            return exp;
        }
        if (exp instanceof SetExp) {
            Declaration decl = this.moduleDecl;
            SetExp sexp = (SetExp) exp;
            Expression exp2 = visitSetExp(sexp, (Void) null);
            if (sexp.isDefining() && sexp.getBinding() == decl) {
                if (!decl.isProcedureDecl()) {
                    push(decl);
                }
                decl = decl.nextDecl();
            }
            this.moduleDecl = decl;
            return exp2;
        }
        return visit(exp, null);
    }

    @Override // gnu.expr.ResolveNames
    public void resolveModule(ModuleExp exp) {
        this.currentLambda = exp;
        for (Declaration decl = exp.firstDecl(); decl != null; decl = decl.nextDecl()) {
            if (decl.isProcedureDecl()) {
                push(decl);
            }
        }
        Declaration decl2 = exp.firstDecl();
        this.moduleDecl = decl2;
        exp.body = visitStatements(exp.body);
        for (Declaration decl3 = exp.firstDecl(); decl3 != null; decl3 = decl3.nextDecl()) {
            if (decl3.getSymbol() != null) {
                this.lookup.removeSubsumed(decl3);
            }
        }
    }

    Expression getCollator(Expression[] args, int argno) {
        if (args != null && args.length > argno) {
            return new ApplyExp(ClassType.make("gnu.xquery.util.NamedCollator").getDeclaredMethod("find", 1), args[argno]);
        }
        NamedCollator coll = this.parser.defaultCollator;
        return coll == null ? QuoteExp.nullExp : new QuoteExp(coll);
    }

    Expression withCollator(Method method, Expression[] args, String name, int minArgs) {
        return withCollator(new QuoteExp(new PrimProcedure(method)), args, name, minArgs);
    }

    Expression withCollator(Expression function, Expression[] args, String name, int minArgs) {
        String err = WrongArguments.checkArgCount(name, minArgs, minArgs + 1, args.length);
        if (err != null) {
            return getCompilation().syntaxError(err);
        }
        Expression[] xargs = new Expression[minArgs + 1];
        System.arraycopy(args, 0, xargs, 0, minArgs);
        xargs[minArgs] = getCollator(args, minArgs);
        return new ApplyExp(function, xargs);
    }

    Expression withContext(Method method, Expression[] args, String name, int minArgs) {
        String err = WrongArguments.checkArgCount(name, minArgs, minArgs + 1, args.length);
        if (err != null) {
            return getCompilation().syntaxError(err);
        }
        if (args.length == minArgs) {
            Expression[] xargs = new Expression[minArgs + 1];
            System.arraycopy(args, 0, xargs, 0, minArgs);
            Declaration dot = this.lookup.lookup((Object) XQParser.DOT_VARNAME, false);
            if (dot == null) {
                String message = "undefined context for " + name;
                this.messages.error('e', message, "XPDY0002");
                return new ErrorExp(message);
            }
            xargs[minArgs] = new ReferenceExp(dot);
            args = xargs;
        }
        return new ApplyExp(method, args);
    }

    private Expression checkArgCount(Expression[] args, Declaration decl, int min, int max) {
        String err = WrongArguments.checkArgCount("fn:" + decl.getName(), min, max, args.length);
        if (err == null) {
            return null;
        }
        return getCompilation().syntaxError(err);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:210:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x041e  */
    @Override // gnu.expr.ExpVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public gnu.expr.Expression visitApplyExp(gnu.expr.ApplyExp r18, java.lang.Void r19) {
        /*
            Method dump skipped, instructions count: 1538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQResolveNames.visitApplyExp(gnu.expr.ApplyExp, java.lang.Void):gnu.expr.Expression");
    }

    public Expression checkPragma(Symbol name, Expression contents) {
        return null;
    }

    Expression getBaseUriExpr() {
        Compilation comp = getCompilation();
        String staticBaseUri = this.parser.getStaticBaseUri();
        if (staticBaseUri != null) {
            return QuoteExp.getInstance(staticBaseUri);
        }
        return GetModuleClass.getModuleClassURI(comp);
    }

    static NamespaceBinding maybeAddNamespace(Symbol qname, boolean isAttribute, NamespaceBinding bindings) {
        if (qname == null) {
            return bindings;
        }
        String prefix = qname.getPrefix();
        String uri = qname.getNamespaceURI();
        if (prefix == "") {
            prefix = null;
        }
        if (uri == "") {
            uri = null;
        }
        if (isAttribute && prefix == null && uri == null) {
            return bindings;
        }
        return NamespaceBinding.maybeAdd(prefix, uri, bindings);
    }

    static Declaration procToDecl(Object symbol, Object val) {
        Declaration decl = new Declaration(symbol);
        decl.setProcedureDecl(true);
        decl.noteValue(new QuoteExp(val));
        decl.setFlag(16384L);
        return decl;
    }
}
