package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import kawa.standard.object;

/* loaded from: classes.dex */
public class Lambda extends Syntax {
    public static final Keyword nameKeyword = Keyword.make("name");
    public Expression defaultDefault = QuoteExp.falseExp;
    public Object keyKeyword;
    public Object optionalKeyword;
    public Object restKeyword;

    public void setKeywords(Object optional, Object rest, Object key) {
        this.optionalKeyword = optional;
        this.restKeyword = rest;
        this.keyKeyword = key;
    }

    @Override // kawa.lang.Syntax
    public Expression rewriteForm(Pair form, Translator tr) {
        Expression exp = rewrite(form.getCdr(), tr);
        Translator.setLine(exp, form);
        return exp;
    }

    @Override // kawa.lang.Syntax
    public Expression rewrite(Object obj, Translator tr) {
        if (!(obj instanceof Pair)) {
            return tr.syntaxError("missing formals in lambda");
        }
        int old_errors = tr.getMessages().getErrorCount();
        LambdaExp lexp = new LambdaExp();
        Pair pair = (Pair) obj;
        Translator.setLine(lexp, pair);
        rewrite(lexp, pair.getCar(), pair.getCdr(), tr, null);
        if (tr.getMessages().getErrorCount() > old_errors) {
            return new ErrorExp("bad lambda expression");
        }
        return lexp;
    }

    public void rewrite(LambdaExp lexp, Object formals, Object body, Translator tr, TemplateScope templateScopeRest) {
        rewriteFormals(lexp, formals, tr, templateScopeRest);
        if (body instanceof PairWithPosition) {
            lexp.setFile(((PairWithPosition) body).getFileName());
        }
        rewriteBody(lexp, rewriteAttrs(lexp, body, tr), tr);
    }

    public void rewriteFormals(LambdaExp lexp, Object formals, Translator tr, TemplateScope templateScopeRest) {
        int rest_args;
        TemplateScope templateScopeRest2;
        int rest_args2;
        Pair pair;
        Pair pair2;
        Object name;
        TemplateScope templateScope;
        Object defaultValue;
        Pair typeSpecPair;
        Object defaultValue2;
        Pair p;
        Pair pair3;
        int opt_args;
        if (lexp.getSymbol() == null) {
            String filename = lexp.getFileName();
            int line = lexp.getLineNumber();
            if (filename != null && line > 0) {
                lexp.setSourceLocation(filename, line);
            }
        }
        Object bindings = formals;
        int opt_args2 = -1;
        int rest_args3 = -1;
        int key_args = -1;
        while (true) {
            if (bindings instanceof SyntaxForm) {
                bindings = ((SyntaxForm) bindings).getDatum();
            }
            if (bindings instanceof Pair) {
                Pair pair4 = (Pair) bindings;
                Object pair_car = pair4.getCar();
                if (pair_car instanceof SyntaxForm) {
                    pair_car = ((SyntaxForm) pair_car).getDatum();
                }
                if (pair_car == this.optionalKeyword) {
                    if (opt_args2 >= 0) {
                        tr.syntaxError("multiple " + this.optionalKeyword + " in parameter list");
                        return;
                    } else if (rest_args3 >= 0 || key_args >= 0) {
                        break;
                    } else {
                        opt_args2 = 0;
                    }
                } else if (pair_car == this.restKeyword) {
                    if (rest_args3 >= 0) {
                        tr.syntaxError("multiple " + this.restKeyword + " in parameter list");
                        return;
                    } else {
                        if (key_args >= 0) {
                            tr.syntaxError(this.restKeyword.toString() + " after " + this.keyKeyword);
                            return;
                        }
                        rest_args3 = 0;
                    }
                } else if (pair_car == this.keyKeyword) {
                    if (key_args >= 0) {
                        tr.syntaxError("multiple " + this.keyKeyword + " in parameter list");
                        return;
                    }
                    key_args = 0;
                } else if (tr.matches(pair4.getCar(), "::") && (pair4.getCdr() instanceof Pair)) {
                    pair4 = (Pair) pair4.getCdr();
                } else if (key_args >= 0) {
                    key_args++;
                } else if (rest_args3 >= 0) {
                    rest_args3++;
                } else if (opt_args2 >= 0) {
                    opt_args2++;
                } else {
                    lexp.min_args++;
                }
                pair4.getCdr();
                bindings = pair4.getCdr();
            } else {
                if (bindings instanceof Symbol) {
                    if (opt_args2 >= 0 || key_args >= 0 || rest_args3 >= 0) {
                        tr.syntaxError("dotted rest-arg after " + this.optionalKeyword + ", " + this.restKeyword + ", or " + this.keyKeyword);
                        return;
                    }
                    rest_args3 = 1;
                } else if (bindings != LList.Empty) {
                    tr.syntaxError("misformed formals in lambda");
                    return;
                }
                if (rest_args3 > 1) {
                    tr.syntaxError("multiple " + this.restKeyword + " parameters");
                    return;
                }
                if (opt_args2 < 0) {
                    opt_args2 = 0;
                }
                if (rest_args3 >= 0) {
                    rest_args = rest_args3;
                } else {
                    rest_args = 0;
                }
                if (key_args < 0) {
                    key_args = 0;
                }
                if (rest_args > 0) {
                    lexp.max_args = -1;
                } else {
                    lexp.max_args = lexp.min_args + opt_args2 + (key_args * 2);
                }
                if (opt_args2 + key_args > 0) {
                    lexp.defaultArgs = new Expression[opt_args2 + key_args];
                }
                if (key_args > 0) {
                    lexp.keywords = new Keyword[key_args];
                }
                Object mode = null;
                int key_args2 = 0;
                int opt_args3 = 0;
                Object bindings2 = formals;
                TemplateScope templateScopeRest3 = templateScopeRest;
                while (true) {
                    if (bindings2 instanceof SyntaxForm) {
                        SyntaxForm sf = (SyntaxForm) bindings2;
                        bindings2 = sf.getDatum();
                        templateScopeRest3 = sf.getScope();
                    }
                    TemplateScope templateScope2 = templateScopeRest3;
                    if (bindings2 instanceof Pair) {
                        Pair pair5 = (Pair) bindings2;
                        Object pair_car2 = pair5.getCar();
                        if (pair_car2 instanceof SyntaxForm) {
                            SyntaxForm sf2 = (SyntaxForm) pair_car2;
                            pair_car2 = sf2.getDatum();
                            templateScope2 = sf2.getScope();
                        }
                        if (pair_car2 != this.optionalKeyword && pair_car2 != this.restKeyword) {
                            if (pair_car2 == this.keyKeyword) {
                                templateScopeRest2 = templateScopeRest3;
                                rest_args2 = rest_args;
                                pair = pair5;
                            } else {
                                Object savePos = tr.pushPositionOf(pair5);
                                Object defaultValue3 = this.defaultDefault;
                                Pair typeSpecPair2 = null;
                                if (tr.matches(pair_car2, "::")) {
                                    tr.syntaxError("'::' must follow parameter name");
                                    return;
                                }
                                Object pair_car3 = tr.namespaceResolve(pair_car2);
                                templateScopeRest2 = templateScopeRest3;
                                rest_args2 = rest_args;
                                TemplateScope templateScope3 = templateScope2;
                                if (pair_car3 instanceof Symbol) {
                                    name = pair_car3;
                                    if (pair5.getCdr() instanceof Pair) {
                                        Pair p2 = (Pair) pair5.getCdr();
                                        if (tr.matches(p2.getCar(), "::")) {
                                            if (!(pair5.getCdr() instanceof Pair)) {
                                                tr.syntaxError("'::' not followed by a type specifier (for parameter '" + name + "')");
                                                return;
                                            }
                                            Pair p3 = (Pair) p2.getCdr();
                                            typeSpecPair2 = p3;
                                            pair5 = p3;
                                            templateScope = templateScope3;
                                            defaultValue = defaultValue3;
                                        }
                                    }
                                    templateScope = templateScope3;
                                    defaultValue = defaultValue3;
                                } else if (!(pair_car3 instanceof Pair)) {
                                    name = null;
                                    templateScope = templateScope3;
                                    defaultValue = defaultValue3;
                                } else {
                                    Pair p4 = (Pair) pair_car3;
                                    Object pair_car4 = p4.getCar();
                                    if (pair_car4 instanceof SyntaxForm) {
                                        SyntaxForm sf3 = (SyntaxForm) pair_car4;
                                        pair_car4 = sf3.getDatum();
                                        templateScope3 = sf3.getScope();
                                    }
                                    Object pair_car5 = tr.namespaceResolve(pair_car4);
                                    if ((pair_car5 instanceof Symbol) && (p4.getCdr() instanceof Pair)) {
                                        Object name2 = p4.getCdr();
                                        Pair p5 = (Pair) name2;
                                        if (!tr.matches(p5.getCar(), "::")) {
                                            typeSpecPair = null;
                                        } else {
                                            if (!(p5.getCdr() instanceof Pair)) {
                                                tr.syntaxError("'::' not followed by a type specifier (for parameter '" + pair_car5 + "')");
                                                return;
                                            }
                                            Pair p6 = (Pair) p5.getCdr();
                                            typeSpecPair = p6;
                                            if (p6.getCdr() instanceof Pair) {
                                                p5 = (Pair) p6.getCdr();
                                            } else {
                                                if (p6.getCdr() != LList.Empty) {
                                                    tr.syntaxError("improper list in specifier for parameter '" + pair_car5 + "')");
                                                    return;
                                                }
                                                p5 = null;
                                            }
                                        }
                                        if (p5 != null && mode != null) {
                                            defaultValue2 = p5.getCar();
                                            if (p5.getCdr() instanceof Pair) {
                                                p = (Pair) p5.getCdr();
                                            } else {
                                                if (p5.getCdr() != LList.Empty) {
                                                    tr.syntaxError("improper list in specifier for parameter '" + pair_car5 + "')");
                                                    return;
                                                }
                                                p = null;
                                            }
                                        } else {
                                            defaultValue2 = defaultValue3;
                                            p = p5;
                                        }
                                        if (p == null) {
                                            typeSpecPair2 = typeSpecPair;
                                            name = pair_car5;
                                            defaultValue = defaultValue2;
                                            templateScope = templateScope3;
                                            pair5 = pair5;
                                        } else {
                                            if (typeSpecPair != null) {
                                                tr.syntaxError("duplicate type specifier for parameter '" + pair_car5 + '\'');
                                                return;
                                            }
                                            typeSpecPair2 = p;
                                            if (p.getCdr() != LList.Empty) {
                                                tr.syntaxError("junk at end of specifier for parameter '" + pair_car5 + "' after type " + p.getCar());
                                                return;
                                            }
                                            name = pair_car5;
                                            defaultValue = defaultValue2;
                                            templateScope = templateScope3;
                                            pair5 = pair5;
                                        }
                                    } else {
                                        name = null;
                                        templateScope = templateScope3;
                                        defaultValue = defaultValue3;
                                        pair5 = pair5;
                                        typeSpecPair2 = null;
                                    }
                                }
                                if (name == null) {
                                    tr.syntaxError("parameter is neither name nor (name :: type) nor (name default): " + pair5);
                                    return;
                                }
                                if (mode == this.optionalKeyword || mode == this.keyKeyword) {
                                    pair3 = pair5;
                                    lexp.defaultArgs[opt_args3] = new LangExp(defaultValue);
                                    opt_args3++;
                                } else {
                                    pair3 = pair5;
                                }
                                if (mode != this.keyKeyword) {
                                    opt_args = opt_args3;
                                } else {
                                    int key_args3 = key_args2 + 1;
                                    opt_args = opt_args3;
                                    lexp.keywords[key_args2] = Keyword.make(name instanceof Symbol ? ((Symbol) name).getName() : name.toString());
                                    key_args2 = key_args3;
                                }
                                Declaration decl = new Declaration(name);
                                Translator.setLine(decl, bindings2);
                                if (typeSpecPair2 == null) {
                                    Object name3 = this.restKeyword;
                                    if (mode == name3) {
                                        decl.setType(LangObjType.listType);
                                    }
                                } else {
                                    decl.setTypeExp(new LangExp(typeSpecPair2));
                                    decl.setFlag(8192L);
                                }
                                decl.setFlag(262144L);
                                decl.noteValue(null);
                                addParam(decl, templateScope, lexp, tr);
                                tr.popPositionOf(savePos);
                                opt_args3 = opt_args;
                                pair2 = pair3;
                                bindings2 = pair2.getCdr();
                                templateScopeRest3 = templateScopeRest2;
                                rest_args = rest_args2;
                            }
                        } else {
                            templateScopeRest2 = templateScopeRest3;
                            rest_args2 = rest_args;
                            pair = pair5;
                        }
                        Object mode2 = pair_car2;
                        mode = mode2;
                        pair2 = pair;
                        bindings2 = pair2.getCdr();
                        templateScopeRest3 = templateScopeRest2;
                        rest_args = rest_args2;
                    } else {
                        if (bindings2 instanceof SyntaxForm) {
                            SyntaxForm sf4 = (SyntaxForm) bindings2;
                            bindings2 = sf4.getDatum();
                            templateScopeRest3 = sf4.getScope();
                        }
                        if (bindings2 instanceof Symbol) {
                            Declaration decl2 = new Declaration(bindings2);
                            decl2.setType(LangObjType.listType);
                            decl2.setFlag(262144L);
                            decl2.noteValue(null);
                            addParam(decl2, templateScopeRest3, lexp, tr);
                            return;
                        }
                        return;
                    }
                }
            }
        }
        tr.syntaxError(this.optionalKeyword.toString() + " after " + this.restKeyword + " or " + this.keyKeyword);
    }

    private static void addParam(Declaration decl, ScopeExp templateScope, LambdaExp lexp, Translator tr) {
        if (templateScope != null) {
            decl = tr.makeRenamedAlias(decl, templateScope);
        }
        lexp.addDeclaration(decl);
        if (templateScope != null) {
            decl.context = templateScope;
        }
    }

    public Object rewriteAttrs(LambdaExp lexp, Object body, Translator tr) {
        int accessFlag;
        int allocationFlag;
        int allocationFlag2;
        int accessFlag2;
        int allocationFlag3;
        Object attrValue;
        int accessFlag3;
        int accessFlag4;
        SyntaxForm syntax0 = null;
        int allocationFlag4 = 0;
        int accessFlag5 = 0;
        String allocationFlagName = null;
        String allocationFlagName2 = null;
        Object body2 = body;
        while (true) {
            if (body2 instanceof SyntaxForm) {
                syntax0 = (SyntaxForm) body2;
                body2 = syntax0.getDatum();
            } else {
                if (!(body2 instanceof Pair)) {
                    break;
                }
                Pair pair1 = (Pair) body2;
                Object attrName = Translator.stripSyntax(pair1.getCar());
                if (tr.matches(attrName, "::")) {
                    attrName = null;
                } else if (!(attrName instanceof Keyword)) {
                    break;
                }
                SyntaxForm syntax1 = syntax0;
                Object pair1_cdr = pair1.getCdr();
                while (pair1_cdr instanceof SyntaxForm) {
                    SyntaxForm syntax12 = pair1_cdr;
                    syntax1 = syntax12;
                    pair1_cdr = syntax1.getDatum();
                }
                if (!(pair1_cdr instanceof Pair)) {
                    break;
                }
                Pair pair2 = pair1_cdr;
                if (attrName == null) {
                    if (!lexp.isClassMethod() || !"*init*".equals(lexp.getName())) {
                        lexp.body = new LangExp(new Object[]{pair2, syntax1});
                        accessFlag = accessFlag5;
                        allocationFlag = allocationFlag4;
                    } else {
                        tr.error('e', "explicit return type for '*init*' method");
                        accessFlag = accessFlag5;
                        allocationFlag = allocationFlag4;
                    }
                } else {
                    if (attrName == object.accessKeyword) {
                        Expression attrExpr = tr.rewrite_car(pair2, syntax1);
                        if (attrExpr instanceof QuoteExp) {
                            Object attrValue2 = ((QuoteExp) attrExpr).getValue();
                            if (attrValue2 instanceof SimpleSymbol) {
                                attrValue = attrValue2;
                            } else {
                                attrValue = attrValue2;
                                if (!(attrValue instanceof CharSequence)) {
                                    accessFlag2 = accessFlag5;
                                    allocationFlag3 = allocationFlag4;
                                }
                            }
                            Object body3 = lexp.nameDecl;
                            if (body3 == null) {
                                tr.error('e', "access: not allowed for anonymous function");
                                accessFlag2 = accessFlag5;
                                allocationFlag3 = allocationFlag4;
                                accessFlag5 = accessFlag2;
                                allocationFlag4 = allocationFlag3;
                            } else {
                                String value = attrValue.toString();
                                int accessFlag6 = accessFlag5;
                                if ("private".equals(value)) {
                                    accessFlag3 = 16777216;
                                    allocationFlag3 = allocationFlag4;
                                } else if ("protected".equals(value)) {
                                    accessFlag3 = Declaration.PROTECTED_ACCESS;
                                    allocationFlag3 = allocationFlag4;
                                } else if ("public".equals(value)) {
                                    accessFlag3 = Declaration.PUBLIC_ACCESS;
                                    allocationFlag3 = allocationFlag4;
                                } else if ("package".equals(value)) {
                                    accessFlag3 = Declaration.PACKAGE_ACCESS;
                                    allocationFlag3 = allocationFlag4;
                                } else {
                                    allocationFlag3 = allocationFlag4;
                                    tr.error('e', "unknown access specifier");
                                    accessFlag3 = accessFlag6;
                                }
                                if (allocationFlagName2 == null || value == null) {
                                    accessFlag4 = accessFlag3;
                                } else {
                                    accessFlag4 = accessFlag3;
                                    tr.error('e', "duplicate access specifiers - " + allocationFlagName2 + " and " + value);
                                }
                                allocationFlagName2 = value;
                                accessFlag5 = accessFlag4;
                                allocationFlag4 = allocationFlag3;
                            }
                        } else {
                            accessFlag2 = accessFlag5;
                            allocationFlag3 = allocationFlag4;
                        }
                        tr.error('e', "access: value not a constant symbol or string");
                        accessFlag5 = accessFlag2;
                        allocationFlag4 = allocationFlag3;
                    } else {
                        accessFlag = accessFlag5;
                        allocationFlag = allocationFlag4;
                        Object body4 = object.allocationKeyword;
                        if (attrName == body4) {
                            Expression attrExpr2 = tr.rewrite_car(pair2, syntax1);
                            if (attrExpr2 instanceof QuoteExp) {
                                Object attrValue3 = ((QuoteExp) attrExpr2).getValue();
                                if ((attrValue3 instanceof SimpleSymbol) || (attrValue3 instanceof CharSequence)) {
                                    if (lexp.nameDecl == null) {
                                        tr.error('e', "allocation: not allowed for anonymous function");
                                        allocationFlag4 = allocationFlag;
                                        accessFlag5 = accessFlag;
                                    } else {
                                        String value2 = attrValue3.toString();
                                        if ("class".equals(value2) || "static".equals(value2)) {
                                            allocationFlag2 = 2048;
                                        } else if ("instance".equals(value2)) {
                                            allocationFlag2 = 4096;
                                        } else {
                                            tr.error('e', "unknown allocation specifier");
                                            allocationFlag2 = allocationFlag;
                                        }
                                        if (allocationFlagName != null && value2 != null) {
                                            tr.error('e', "duplicate allocation specifiers - " + allocationFlagName + " and " + value2);
                                        }
                                        allocationFlagName = value2;
                                        allocationFlag4 = allocationFlag2;
                                        accessFlag5 = accessFlag;
                                    }
                                }
                            }
                            tr.error('e', "allocation: value not a constant symbol or string");
                            allocationFlag4 = allocationFlag;
                            accessFlag5 = accessFlag;
                        } else if (attrName == object.throwsKeyword) {
                            Object attrValue4 = pair2.getCar();
                            int count = Translator.listLength(attrValue4);
                            if (count < 0) {
                                tr.error('e', "throws: not followed by a list");
                            } else {
                                Expression[] exps = new Expression[count];
                                SyntaxForm syntax2 = syntax1;
                                for (int i = 0; i < count; i++) {
                                    while (attrValue4 instanceof SyntaxForm) {
                                        syntax2 = (SyntaxForm) attrValue4;
                                        attrValue4 = syntax2.getDatum();
                                    }
                                    Pair pair3 = (Pair) attrValue4;
                                    exps[i] = tr.rewrite_car(pair3, syntax2);
                                    Translator.setLine(exps[i], pair3);
                                    attrValue4 = pair3.getCdr();
                                }
                                lexp.setExceptions(exps);
                            }
                        } else if (attrName == nameKeyword) {
                            Expression attrExpr3 = tr.rewrite_car(pair2, syntax1);
                            if (attrExpr3 instanceof QuoteExp) {
                                lexp.setName(((QuoteExp) attrExpr3).getValue().toString());
                            }
                        } else {
                            tr.error('w', "unknown procedure property " + attrName);
                        }
                    }
                    body2 = pair2.getCdr();
                }
                accessFlag5 = accessFlag;
                allocationFlag4 = allocationFlag;
                body2 = pair2.getCdr();
            }
        }
        int accessFlag7 = accessFlag5 | allocationFlag4;
        if (accessFlag7 != 0) {
            lexp.nameDecl.setFlag(accessFlag7);
        }
        if (syntax0 != null) {
            return SyntaxForms.fromDatumIfNeeded(body2, syntax0);
        }
        return body2;
    }

    public Object skipAttrs(LambdaExp lexp, Object body, Translator tr) {
        while (body instanceof Pair) {
            Pair pair = (Pair) body;
            if (!(pair.getCdr() instanceof Pair)) {
                break;
            }
            Object attrName = pair.getCar();
            if (!tr.matches(attrName, "::") && !(attrName instanceof Keyword)) {
                break;
            }
            body = ((Pair) pair.getCdr()).getCdr();
        }
        return body;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void rewriteBody(gnu.expr.LambdaExp r20, java.lang.Object r21, kawa.lang.Translator r22) {
        /*
            Method dump skipped, instructions count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Lambda.rewriteBody(gnu.expr.LambdaExp, java.lang.Object, kawa.lang.Translator):void");
    }

    @Override // kawa.lang.Syntax, gnu.text.Printable
    public void print(Consumer out) {
        out.write("#<builtin lambda>");
    }
}
