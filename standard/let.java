package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class let extends Syntax {
    public static final let let;

    static {
        let letVar = new let();
        let = letVar;
        letVar.setName("let");
    }

    @Override // kawa.lang.Syntax
    public Expression rewrite(Object obj, Translator tr) {
        TemplateScope templateScope;
        Pair binding;
        Stack renamedAliases;
        int renamedAliasesCount;
        Pair init;
        Object obj2 = obj;
        if (!(obj2 instanceof Pair)) {
            return tr.syntaxError("missing let arguments");
        }
        Pair pair = (Pair) obj2;
        Object bindings = pair.getCar();
        Object body = pair.getCdr();
        int decl_count = Translator.listLength(bindings);
        if (decl_count < 0) {
            return tr.syntaxError("bindings not a proper list");
        }
        Expression[] inits = new Expression[decl_count];
        LetExp let2 = new LetExp(inits);
        Stack renamedAliases2 = null;
        int renamedAliasesCount2 = 0;
        SyntaxForm syntaxRest = null;
        int i = 0;
        while (i < decl_count) {
            while (bindings instanceof SyntaxForm) {
                syntaxRest = (SyntaxForm) bindings;
                bindings = syntaxRest.getDatum();
            }
            Pair bind_pair = (Pair) bindings;
            Object bind_pair_car = bind_pair.getCar();
            SyntaxForm syntax = syntaxRest;
            if (bind_pair_car instanceof SyntaxForm) {
                syntax = (SyntaxForm) bind_pair_car;
                bind_pair_car = syntax.getDatum();
            }
            if (!(bind_pair_car instanceof Pair)) {
                return tr.syntaxError("let binding is not a pair:" + bind_pair_car);
            }
            Pair pair2 = pair;
            Pair binding2 = (Pair) bind_pair_car;
            Object name = binding2.getCar();
            if (name instanceof SyntaxForm) {
                SyntaxForm sf = (SyntaxForm) name;
                name = sf.getDatum();
                templateScope = sf.getScope();
            } else {
                templateScope = syntax == null ? null : syntax.getScope();
            }
            Object name2 = tr.namespaceResolve(name);
            int decl_count2 = decl_count;
            if (!(name2 instanceof Symbol)) {
                return tr.syntaxError("variable " + name2 + " in let binding is not a symbol: " + obj2);
            }
            SyntaxForm syntaxRest2 = syntaxRest;
            Declaration decl = let2.addDeclaration(name2);
            SyntaxForm syntax2 = syntax;
            decl.setFlag(262144L);
            if (templateScope != null) {
                Declaration alias = tr.makeRenamedAlias(decl, templateScope);
                if (renamedAliases2 == null) {
                    renamedAliases2 = new Stack();
                }
                renamedAliases2.push(alias);
                renamedAliasesCount2++;
            }
            Object binding_cdr = binding2.getCdr();
            SyntaxForm syntax3 = syntax2;
            while (binding_cdr instanceof SyntaxForm) {
                SyntaxForm syntax4 = binding_cdr;
                syntax3 = syntax4;
                binding_cdr = syntax3.getDatum();
            }
            if (!(binding_cdr instanceof Pair)) {
                return tr.syntaxError("let has no value for '" + name2 + "'");
            }
            Pair binding3 = (Pair) binding_cdr;
            Object binding_cdr2 = binding3.getCdr();
            while (binding_cdr2 instanceof SyntaxForm) {
                SyntaxForm syntax5 = binding_cdr2;
                syntax3 = syntax5;
                binding_cdr2 = syntax3.getDatum();
            }
            if (tr.matches(binding3.getCar(), "::")) {
                if (binding_cdr2 instanceof Pair) {
                    Pair binding4 = (Pair) binding_cdr2;
                    Object cdr = binding4.getCdr();
                    Object binding_cdr3 = LList.Empty;
                    if (cdr != binding_cdr3) {
                        binding_cdr2 = binding4.getCdr();
                        while (binding_cdr2 instanceof SyntaxForm) {
                            syntax3 = (SyntaxForm) binding_cdr2;
                            binding_cdr2 = syntax3.getDatum();
                        }
                        binding = binding4;
                    }
                }
                return tr.syntaxError("missing type after '::' in let");
            }
            binding = binding3;
            if (binding_cdr2 == LList.Empty) {
                renamedAliasesCount = renamedAliasesCount2;
                renamedAliases = renamedAliases2;
                init = binding;
            } else {
                if (!(binding_cdr2 instanceof Pair)) {
                    return tr.syntaxError("let binding for '" + name2 + "' is improper list");
                }
                decl.setType(tr.exp2Type(binding));
                renamedAliases = renamedAliases2;
                renamedAliasesCount = renamedAliasesCount2;
                decl.setFlag(8192L);
                init = (Pair) binding_cdr2;
            }
            inits[i] = tr.rewrite_car(init, syntax3);
            if (init.getCdr() != LList.Empty) {
                return tr.syntaxError("junk after declaration of " + name2);
            }
            decl.noteValue(inits[i]);
            bindings = bind_pair.getCdr();
            i++;
            obj2 = obj;
            renamedAliases2 = renamedAliases;
            pair = pair2;
            decl_count = decl_count2;
            syntaxRest = syntaxRest2;
            renamedAliasesCount2 = renamedAliasesCount;
        }
        int i2 = renamedAliasesCount2;
        while (true) {
            i2--;
            if (i2 >= 0) {
                tr.pushRenamedAlias((Declaration) renamedAliases2.pop());
            } else {
                tr.push(let2);
                let2.body = tr.rewrite_body(body);
                tr.pop(let2);
                tr.popRenamedAlias(renamedAliasesCount2);
                return let2;
            }
        }
    }
}
