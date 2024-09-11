package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure1;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: classes.dex */
public class SyntaxRules extends Procedure1 implements Printable, Externalizable {
    Object[] literal_identifiers;
    int maxVars;
    SyntaxRule[] rules;

    public SyntaxRules() {
        this.maxVars = 0;
    }

    public SyntaxRules(Object[] literal_identifiers, SyntaxRule[] rules, int maxVars) {
        this.maxVars = 0;
        this.literal_identifiers = literal_identifiers;
        this.rules = rules;
        this.maxVars = maxVars;
    }

    public SyntaxRules(Object[] literal_identifiers, Object srules, Translator tr) {
        int rules_count;
        String save_filename;
        Object[] objArr = literal_identifiers;
        this.maxVars = 0;
        this.literal_identifiers = objArr;
        int rules_count2 = Translator.listLength(srules);
        if (rules_count2 < 0) {
            tr.syntaxError("missing or invalid syntax-rules");
            rules_count = 0;
        } else {
            rules_count = rules_count2;
        }
        this.rules = new SyntaxRule[rules_count];
        int i = 0;
        SyntaxForm rules_syntax = null;
        Object srules2 = srules;
        while (i < rules_count) {
            Object srules3 = srules2;
            SyntaxForm rules_syntax2 = rules_syntax;
            while (srules3 instanceof SyntaxForm) {
                rules_syntax2 = (SyntaxForm) srules3;
                srules3 = rules_syntax2.getDatum();
            }
            Pair rules_pair = (Pair) srules3;
            SyntaxForm rule_syntax = rules_syntax2;
            Object syntax_rule = rules_pair.getCar();
            SyntaxForm rule_syntax2 = rule_syntax;
            while (syntax_rule instanceof SyntaxForm) {
                SyntaxForm rule_syntax3 = syntax_rule;
                rule_syntax2 = rule_syntax3;
                syntax_rule = rule_syntax2.getDatum();
            }
            if (!(syntax_rule instanceof Pair)) {
                tr.syntaxError("missing pattern in " + i + "'th syntax rule");
                return;
            }
            SyntaxForm pattern_syntax = rule_syntax2;
            Pair syntax_rule_pair = (Pair) syntax_rule;
            Object pattern = syntax_rule_pair.getCar();
            String save_filename2 = tr.getFileName();
            int rules_count3 = rules_count;
            int save_line = tr.getLineNumber();
            int save_column = tr.getColumnNumber();
            SyntaxForm template_syntax = rule_syntax2;
            try {
                tr.setLine(syntax_rule_pair);
                Object syntax_rule2 = syntax_rule_pair.getCdr();
                SyntaxForm template_syntax2 = template_syntax;
                while (syntax_rule2 instanceof SyntaxForm) {
                    try {
                        try {
                            template_syntax2 = (SyntaxForm) syntax_rule2;
                            syntax_rule2 = template_syntax2.getDatum();
                        } catch (Throwable th) {
                            th = th;
                            save_filename = save_filename2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        save_filename = save_filename2;
                    }
                }
                if (syntax_rule2 instanceof Pair) {
                    SyntaxForm pattern_syntax2 = pattern_syntax;
                    try {
                        Pair syntax_rule_pair2 = (Pair) syntax_rule2;
                        try {
                            if (syntax_rule_pair2.getCdr() != LList.Empty) {
                                tr.syntaxError("junk after " + i + "'th syntax rule");
                                tr.setLine(save_filename2, save_line, save_column);
                                return;
                            }
                            Object template = syntax_rule_pair2.getCar();
                            PatternScope patternScope = PatternScope.push(tr);
                            tr.push(patternScope);
                            while (pattern instanceof SyntaxForm) {
                                try {
                                    pattern_syntax2 = pattern;
                                    pattern = pattern_syntax2.getDatum();
                                } catch (Throwable th3) {
                                    th = th3;
                                    save_filename = save_filename2;
                                }
                            }
                            StringBuffer programbuf = new StringBuffer();
                            if (pattern instanceof Pair) {
                                objArr[0] = ((Pair) pattern).getCar();
                                Pair p = (Pair) pattern;
                                try {
                                    programbuf.append('\f');
                                    programbuf.append((char) 24);
                                    Object pattern2 = p.getCdr();
                                    try {
                                        save_filename = save_filename2;
                                        try {
                                            SyntaxPattern spattern = new SyntaxPattern(programbuf, pattern2, pattern_syntax2, literal_identifiers, tr);
                                            this.rules[i] = new SyntaxRule(spattern, template, template_syntax2, tr);
                                            PatternScope.pop(tr);
                                            tr.pop();
                                            tr.setLine(save_filename, save_line, save_column);
                                            i++;
                                            srules2 = rules_pair.getCdr();
                                            objArr = literal_identifiers;
                                            rules_syntax = rules_syntax2;
                                            rules_count = rules_count3;
                                        } catch (Throwable th4) {
                                            th = th4;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        save_filename = save_filename2;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    save_filename = save_filename2;
                                }
                            } else {
                                save_filename = save_filename2;
                                try {
                                    tr.syntaxError("pattern does not start with name");
                                    tr.setLine(save_filename, save_line, save_column);
                                    return;
                                } catch (Throwable th7) {
                                    th = th7;
                                }
                            }
                            th = th3;
                            save_filename = save_filename2;
                        } catch (Throwable th8) {
                            th = th8;
                            save_filename = save_filename2;
                        }
                    } catch (Throwable th9) {
                        th = th9;
                        save_filename = save_filename2;
                    }
                } else {
                    try {
                        try {
                            tr.syntaxError("missing template in " + i + "'th syntax rule");
                            tr.setLine(save_filename2, save_line, save_column);
                            return;
                        } catch (Throwable th10) {
                            th = th10;
                            save_filename = save_filename2;
                        }
                    } catch (Throwable th11) {
                        th = th11;
                        save_filename = save_filename2;
                    }
                }
            } catch (Throwable th12) {
                th = th12;
                save_filename = save_filename2;
            }
            tr.setLine(save_filename, save_line, save_column);
            throw th;
        }
        int i2 = this.rules.length;
        while (true) {
            i2--;
            if (i2 < 0) {
                return;
            }
            int size = this.rules[i2].patternNesting.length();
            if (size > this.maxVars) {
                this.maxVars = size;
            }
        }
    }

    @Override // gnu.mapping.Procedure1, gnu.mapping.Procedure
    public Object apply1(Object arg) {
        if (arg instanceof SyntaxForm) {
            SyntaxForm sf = (SyntaxForm) arg;
            Translator tr = (Translator) Compilation.getCurrent();
            ScopeExp save_scope = tr.currentScope();
            tr.setCurrentScope(sf.getScope());
            try {
                return expand(sf, tr);
            } finally {
                tr.setCurrentScope(save_scope);
            }
        }
        return expand(arg, (Translator) Compilation.getCurrent());
    }

    public Object expand(Object obj, Translator tr) {
        Object[] vars = new Object[this.maxVars];
        Macro macro = (Macro) tr.getCurrentSyntax();
        int i = 0;
        while (true) {
            SyntaxRule[] syntaxRuleArr = this.rules;
            if (i < syntaxRuleArr.length) {
                SyntaxRule rule = syntaxRuleArr[i];
                if (rule == null) {
                    return new ErrorExp("error defining " + macro);
                }
                Pattern pattern = rule.pattern;
                boolean matched = pattern.match(obj, vars, 0);
                if (!matched) {
                    i++;
                } else {
                    Object expansion = rule.execute(vars, tr, TemplateScope.make(tr));
                    return expansion;
                }
            } else {
                return tr.syntaxError("no matching syntax-rule for " + this.literal_identifiers[0]);
            }
        }
    }

    @Override // gnu.text.Printable
    public void print(Consumer out) {
        out.write("#<macro ");
        ReportFormat.print(this.literal_identifiers[0], out);
        out.write(62);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.literal_identifiers);
        out.writeObject(this.rules);
        out.writeInt(this.maxVars);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.literal_identifiers = (Object[]) in.readObject();
        this.rules = (SyntaxRule[]) in.readObject();
        this.maxVars = in.readInt();
    }
}
