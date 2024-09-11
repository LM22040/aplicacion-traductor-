package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class let_syntax extends Syntax {
    public static final let_syntax let_syntax = new let_syntax(false, "let-syntax");
    public static final let_syntax letrec_syntax = new let_syntax(true, "letrec-syntax");
    boolean recursive;

    public let_syntax(boolean recursive, String name) {
        super(name);
        this.recursive = recursive;
    }

    @Override // kawa.lang.Syntax
    public Expression rewrite(Object obj, Translator tr) {
        Pair pair;
        ScopeExp currentScope;
        if (!(obj instanceof Pair)) {
            return tr.syntaxError("missing let-syntax arguments");
        }
        Pair pair2 = (Pair) obj;
        Object bindings = pair2.getCar();
        Object body = pair2.getCdr();
        int decl_count = Translator.listLength(bindings);
        if (decl_count < 0) {
            return tr.syntaxError("bindings not a proper list");
        }
        Stack renamedAliases = null;
        int renamedAliasesCount = 0;
        Expression[] inits = new Expression[decl_count];
        Declaration[] decls = new Declaration[decl_count];
        Macro[] macros = new Macro[decl_count];
        Pair[] transformers = new Pair[decl_count];
        SyntaxForm[] trSyntax = new SyntaxForm[decl_count];
        LetExp let = new LetExp(inits);
        SyntaxForm listSyntax = null;
        int i = 0;
        while (i < decl_count) {
            while (true) {
                pair = pair2;
                if (!(bindings instanceof SyntaxForm)) {
                    break;
                }
                SyntaxForm listSyntax2 = bindings;
                listSyntax = listSyntax2;
                bindings = listSyntax.getDatum();
                pair2 = pair;
            }
            SyntaxForm bindingSyntax = listSyntax;
            Pair bind_pair = (Pair) bindings;
            SyntaxForm bindingSyntax2 = bindingSyntax;
            Object bind_pair_car = bind_pair.getCar();
            if (bind_pair_car instanceof SyntaxForm) {
                SyntaxForm bindingSyntax3 = (SyntaxForm) bind_pair_car;
                bind_pair_car = bindingSyntax3.getDatum();
                bindingSyntax2 = bindingSyntax3;
            }
            if (!(bind_pair_car instanceof Pair)) {
                return tr.syntaxError(getName() + " binding is not a pair");
            }
            SyntaxForm listSyntax3 = listSyntax;
            Pair binding = (Pair) bind_pair_car;
            Object name = binding.getCar();
            SyntaxForm nameSyntax = bindingSyntax2;
            while (true) {
                Object bind_pair_car2 = bind_pair_car;
                if (!(name instanceof SyntaxForm)) {
                    break;
                }
                SyntaxForm nameSyntax2 = name;
                nameSyntax = nameSyntax2;
                name = nameSyntax.getDatum();
                bind_pair_car = bind_pair_car2;
            }
            if ((name instanceof String) || (name instanceof Symbol)) {
                Object body2 = body;
                Object binding_cdr = binding.getCdr();
                while (binding_cdr instanceof SyntaxForm) {
                    SyntaxForm bindingSyntax4 = binding_cdr;
                    bindingSyntax2 = bindingSyntax4;
                    binding_cdr = bindingSyntax2.getDatum();
                }
                if (!(binding_cdr instanceof Pair)) {
                    return tr.syntaxError(getName() + " has no value for '" + name + "'");
                }
                Pair binding2 = binding_cdr;
                Object cdr = binding2.getCdr();
                Object binding_cdr2 = LList.Empty;
                if (cdr != binding_cdr2) {
                    return tr.syntaxError("let binding for '" + name + "' is improper list");
                }
                Declaration decl = new Declaration(name);
                Macro macro = Macro.make(decl);
                macros[i] = macro;
                transformers[i] = binding2;
                trSyntax[i] = bindingSyntax2;
                let.addDeclaration(decl);
                ScopeExp templateScope = nameSyntax == null ? null : nameSyntax.getScope();
                if (templateScope != null) {
                    Declaration alias = tr.makeRenamedAlias(decl, templateScope);
                    if (renamedAliases == null) {
                        renamedAliases = new Stack();
                    }
                    renamedAliases.push(alias);
                    renamedAliasesCount++;
                }
                if (bindingSyntax2 != null) {
                    currentScope = bindingSyntax2.getScope();
                } else {
                    currentScope = this.recursive ? let : tr.currentScope();
                }
                macro.setCapturedScope(currentScope);
                decls[i] = decl;
                inits[i] = QuoteExp.nullExp;
                bindings = bind_pair.getCdr();
                i++;
                pair2 = pair;
                listSyntax = listSyntax3;
                body = body2;
            } else {
                return tr.syntaxError("variable in " + getName() + " binding is not a symbol");
            }
        }
        Object body3 = body;
        if (this.recursive) {
            push(let, tr, renamedAliases);
        }
        Macro savedMacro = tr.currentMacroDefinition;
        int i2 = 0;
        while (i2 < decl_count) {
            Macro macro2 = macros[i2];
            tr.currentMacroDefinition = macro2;
            Object bindings2 = bindings;
            int decl_count2 = decl_count;
            Expression value = tr.rewrite_car(transformers[i2], trSyntax[i2]);
            inits[i2] = value;
            Declaration decl2 = decls[i2];
            macro2.expander = value;
            Expression[] inits2 = inits;
            decl2.noteValue(new QuoteExp(macro2));
            if (value instanceof LambdaExp) {
                LambdaExp lvalue = (LambdaExp) value;
                lvalue.nameDecl = decl2;
                lvalue.setSymbol(decl2.getSymbol());
            }
            i2++;
            bindings = bindings2;
            decl_count = decl_count2;
            inits = inits2;
        }
        tr.currentMacroDefinition = savedMacro;
        if (!this.recursive) {
            push(let, tr, renamedAliases);
        }
        Expression result = tr.rewrite_body(body3);
        tr.pop(let);
        tr.popRenamedAlias(renamedAliasesCount);
        return result;
    }

    private void push(LetExp let, Translator tr, Stack renamedAliases) {
        tr.push(let);
        if (renamedAliases != null) {
            int i = renamedAliases.size();
            while (true) {
                i--;
                if (i >= 0) {
                    tr.pushRenamedAlias((Declaration) renamedAliases.pop());
                } else {
                    return;
                }
            }
        }
    }
}
