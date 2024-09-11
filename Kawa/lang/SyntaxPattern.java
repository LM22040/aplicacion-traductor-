package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.util.Vector;

/* loaded from: classes.dex */
public class SyntaxPattern extends Pattern implements Externalizable {
    static final int MATCH_ANY = 3;
    static final int MATCH_ANY_CAR = 7;
    static final int MATCH_EQUALS = 2;
    static final int MATCH_IGNORE = 24;
    static final int MATCH_LENGTH = 6;
    static final int MATCH_LREPEAT = 5;
    static final int MATCH_MISC = 0;
    static final int MATCH_NIL = 8;
    static final int MATCH_PAIR = 4;
    static final int MATCH_VECTOR = 16;
    static final int MATCH_WIDE = 1;
    Object[] literals;
    String program;
    int varCount;

    @Override // kawa.lang.Pattern
    public int varCount() {
        return this.varCount;
    }

    @Override // kawa.lang.Pattern
    public boolean match(Object obj, Object[] vars, int start_vars) {
        boolean r = match(obj, vars, start_vars, 0, null);
        return r;
    }

    public SyntaxPattern(String program, Object[] literals, int varCount) {
        this.program = program;
        this.literals = literals;
        this.varCount = varCount;
    }

    public SyntaxPattern(Object pattern, Object[] literal_identifiers, Translator tr) {
        this(new StringBuffer(), pattern, null, literal_identifiers, tr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SyntaxPattern(StringBuffer programbuf, Object pattern, SyntaxForm syntax, Object[] literal_identifiers, Translator tr) {
        Vector literalsbuf = new Vector();
        translate(pattern, programbuf, literal_identifiers, 0, literalsbuf, null, (char) 0, tr);
        this.program = programbuf.toString();
        Object[] objArr = new Object[literalsbuf.size()];
        this.literals = objArr;
        literalsbuf.copyInto(objArr);
        this.varCount = tr.patternScope.pattern_names.size();
    }

    public void disassemble() {
        disassemble(OutPort.errDefault(), (Translator) Compilation.getCurrent(), 0, this.program.length());
    }

    public void disassemble(PrintWriter ps, Translator tr) {
        disassemble(ps, tr, 0, this.program.length());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0042. Please report as an issue. */
    void disassemble(PrintWriter ps, Translator tr, int start, int limit) {
        Vector pattern_names = null;
        if (tr != null && tr.patternScope != null) {
            pattern_names = tr.patternScope.pattern_names;
        }
        int value = 0;
        int i = start;
        while (i < limit) {
            char ch = this.program.charAt(i);
            ps.print(" " + i + ": " + ((int) ch));
            i++;
            int opcode = ch & 7;
            value = (value << 13) | (ch >> 3);
            switch (opcode) {
                case 0:
                    ps.print("[misc ch:" + ((int) ch) + " n:8]");
                    if (ch == '\b') {
                        ps.println(" - NIL");
                    } else if (ch == 16) {
                        ps.println(" - VECTOR");
                    } else {
                        if (ch == 24) {
                            ps.println(" - IGNORE");
                        }
                        ps.println(" - " + opcode + '/' + value);
                    }
                    value = 0;
                    break;
                case 1:
                    ps.println(" - WIDE " + value);
                    break;
                case 2:
                    ps.print(" - EQUALS[" + value + "]");
                    Object[] objArr = this.literals;
                    if (objArr != null && value >= 0 && value < objArr.length) {
                        ps.print(objArr[value]);
                    }
                    ps.println();
                    value = 0;
                    break;
                case 3:
                case 7:
                    ps.print((opcode == 3 ? " - ANY[" : " - ANY_CAR[") + value + "]");
                    if (pattern_names != null && value >= 0 && value < pattern_names.size()) {
                        ps.print(pattern_names.elementAt(value));
                    }
                    ps.println();
                    value = 0;
                    break;
                case 4:
                    ps.println(" - PAIR[" + value + "]");
                    value = 0;
                    break;
                case 5:
                    ps.println(" - LREPEAT[" + value + "]");
                    disassemble(ps, tr, i, i + value);
                    int i2 = i + value;
                    int i3 = i2 + 1;
                    ps.println(" " + i2 + ": - repeat first var:" + (this.program.charAt(i2) >> 3));
                    ps.println(" " + i3 + ": - repeast nested vars:" + (this.program.charAt(i3) >> 3));
                    i = i3 + 1;
                    value = 0;
                    break;
                case 6:
                    ps.println(" - LENGTH " + (value >> 1) + " pairs. " + ((value & 1) == 0 ? "pure list" : "impure list"));
                    value = 0;
                    break;
                default:
                    ps.println(" - " + opcode + '/' + value);
                    value = 0;
                    break;
            }
        }
    }

    void translate(Object pattern, StringBuffer program, Object[] literal_identifiers, int nesting, Vector literals, SyntaxForm syntax, char context, Translator tr) {
        Object savePos;
        int start_pc;
        SyntaxForm car_syntax;
        SyntaxForm syntax2;
        Object next;
        boolean repeat;
        int subvar0;
        Object car;
        char c;
        char context2;
        int i;
        PatternScope patternScope;
        Object next2;
        Object next3;
        Object next4;
        ScopeExp scope1;
        Object literal;
        ScopeExp scope2;
        PatternScope patternScope2 = tr.patternScope;
        Vector patternNames = patternScope2.pattern_names;
        Object pattern2 = pattern;
        SyntaxForm syntax3 = syntax;
        char context3 = context;
        while (true) {
            if (pattern2 instanceof SyntaxForm) {
                syntax3 = (SyntaxForm) pattern2;
                pattern2 = syntax3.getDatum();
            } else {
                if (pattern2 instanceof Pair) {
                    Object savePos2 = tr.pushPositionOf(pattern2);
                    try {
                        start_pc = program.length();
                        program.append((char) 4);
                        Pair pair = (Pair) pattern2;
                        car_syntax = syntax3;
                        Object next5 = pair.getCdr();
                        while (next5 instanceof SyntaxForm) {
                            try {
                                syntax3 = (SyntaxForm) next5;
                                next5 = syntax3.getDatum();
                            } catch (Throwable th) {
                                th = th;
                                savePos = savePos2;
                                tr.popPositionOf(savePos);
                                throw th;
                            }
                        }
                        if ((next5 instanceof Pair) && tr.matches(((Pair) next5).getCar(), "...")) {
                            Object cdr = ((Pair) next5).getCdr();
                            while (true) {
                                next4 = cdr;
                                if (!(next4 instanceof SyntaxForm)) {
                                    break;
                                }
                                syntax3 = (SyntaxForm) next4;
                                cdr = syntax3.getDatum();
                            }
                            syntax2 = syntax3;
                            next = next4;
                            repeat = true;
                        } else {
                            syntax2 = syntax3;
                            next = next5;
                            repeat = false;
                        }
                        try {
                            subvar0 = patternNames.size();
                            char context4 = context3 == 'P' ? (char) 0 : context3;
                            try {
                                car = pair.getCar();
                                int i2 = repeat ? nesting + 1 : nesting;
                                c = context4 == 'V' ? (char) 0 : 'P';
                                context2 = context4;
                                i = i2;
                                patternScope = patternScope2;
                                savePos = savePos2;
                                next2 = next;
                            } catch (Throwable th2) {
                                th = th2;
                                savePos = savePos2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            savePos = savePos2;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        savePos = savePos2;
                    }
                    try {
                        translate(car, program, literal_identifiers, i, literals, car_syntax, c, tr);
                        int subvarN = patternNames.size() - subvar0;
                        int width = (((program.length() - start_pc) - 1) << 3) | (repeat ? 5 : 4);
                        if (width > 65535) {
                            start_pc += insertInt(start_pc, program, (width >> 13) + 1);
                        }
                        program.setCharAt(start_pc, (char) width);
                        int restLength = Translator.listLength(next2);
                        if (restLength == Integer.MIN_VALUE) {
                            tr.syntaxError("cyclic pattern list");
                            tr.popPositionOf(savePos);
                            return;
                        }
                        if (repeat) {
                            addInt(program, subvar0 << 3);
                            addInt(program, subvarN << 3);
                            next3 = next2;
                            if (next3 == LList.Empty) {
                                program.append('\b');
                                tr.popPositionOf(savePos);
                                return;
                            }
                            addInt(program, ((restLength >= 0 ? restLength << 1 : ((-restLength) << 1) - 1) << 3) | 6);
                        } else {
                            next3 = next2;
                        }
                        pattern2 = next3;
                        tr.popPositionOf(savePos);
                        syntax3 = syntax2;
                        patternScope2 = patternScope;
                        context3 = context2;
                    } catch (Throwable th5) {
                        th = th5;
                        tr.popPositionOf(savePos);
                        throw th;
                    }
                } else {
                    PatternScope patternScope3 = patternScope2;
                    if (pattern2 instanceof Symbol) {
                        int j = literal_identifiers.length;
                        do {
                            j--;
                            if (j < 0) {
                                if (patternNames.contains(pattern2)) {
                                    tr.syntaxError("duplicated pattern variable " + pattern2);
                                }
                                int i3 = patternNames.size();
                                patternNames.addElement(pattern2);
                                boolean matchCar = context3 == 'P';
                                int n = (nesting << 1) + (matchCar ? 1 : 0);
                                patternScope3.patternNesting.append((char) n);
                                Declaration decl = patternScope3.addDeclaration(pattern2);
                                decl.setLocation(tr);
                                tr.push(decl);
                                addInt(program, (i3 << 3) | (matchCar ? 7 : 3));
                                return;
                            }
                            ScopeExp current = tr.currentScope();
                            scope1 = syntax3 == null ? current : syntax3.getScope();
                            literal = literal_identifiers[j];
                            if (literal instanceof SyntaxForm) {
                                SyntaxForm syntax22 = (SyntaxForm) literal;
                                literal = syntax22.getDatum();
                                scope2 = syntax22.getScope();
                            } else {
                                scope2 = tr.currentMacroDefinition != null ? tr.currentMacroDefinition.getCapturedScope() : current;
                            }
                        } while (!literalIdentifierEq(pattern2, scope1, literal, scope2));
                        int i4 = SyntaxTemplate.indexOf(literals, pattern2);
                        if (i4 < 0) {
                            i4 = literals.size();
                            literals.addElement(pattern2);
                        }
                        addInt(program, (i4 << 3) | 2);
                        return;
                    }
                    if (pattern2 == LList.Empty) {
                        program.append('\b');
                        return;
                    }
                    if (!(pattern2 instanceof FVector)) {
                        int i5 = SyntaxTemplate.indexOf(literals, pattern2);
                        if (i5 < 0) {
                            i5 = literals.size();
                            literals.addElement(pattern2);
                        }
                        addInt(program, (i5 << 3) | 2);
                        return;
                    }
                    program.append((char) 16);
                    pattern2 = LList.makeList((FVector) pattern2);
                    context3 = 'V';
                    patternScope2 = patternScope3;
                }
            }
        }
    }

    private static void addInt(StringBuffer sbuf, int val) {
        if (val > 65535) {
            addInt(sbuf, (val << 13) + 1);
        }
        sbuf.append((char) val);
    }

    private static int insertInt(int offset, StringBuffer sbuf, int val) {
        if (val > 65535) {
            offset += insertInt(offset, sbuf, (val << 13) + 1);
        }
        sbuf.insert(offset, (char) val);
        return offset + 1;
    }

    boolean match_car(Pair p, Object[] vars, int start_vars, int pc, SyntaxForm syntax) {
        int pc2 = pc + 1;
        char ch = this.program.charAt(pc);
        int value = ch >> 3;
        char ch2 = ch;
        int pc3 = pc2;
        while ((ch2 & 7) == 1) {
            char charAt = this.program.charAt(pc3);
            ch2 = charAt;
            value = (value << 13) | (charAt >> 3);
            pc3++;
        }
        if ((ch2 & 7) != 7) {
            return match(p.getCar(), vars, start_vars, pc, syntax);
        }
        if (syntax != null && !(p.getCar() instanceof SyntaxForm)) {
            p = Translator.makePair(p, SyntaxForms.fromDatum(p.getCar(), syntax), p.getCdr());
        }
        vars[start_vars + value] = p;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:154:0x006d, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0174, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean match(java.lang.Object r22, java.lang.Object[] r23, int r24, int r25, kawa.lang.SyntaxForm r26) {
        /*
            Method dump skipped, instructions count: 578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.SyntaxPattern.match(java.lang.Object, java.lang.Object[], int, int, kawa.lang.SyntaxForm):boolean");
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.program);
        out.writeObject(this.literals);
        out.writeInt(this.varCount);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.literals = (Object[]) in.readObject();
        this.program = (String) in.readObject();
        this.varCount = in.readInt();
    }

    public static Object[] allocVars(int varCount, Object[] outer) {
        Object[] vars = new Object[varCount];
        if (outer != null) {
            System.arraycopy(outer, 0, vars, 0, outer.length);
        }
        return vars;
    }

    public static boolean literalIdentifierEq(Object id1, ScopeExp sc1, Object id2, ScopeExp sc2) {
        if (id1 != id2 && (id1 == null || id2 == null || !id1.equals(id2))) {
            return false;
        }
        if (sc1 == sc2) {
            return true;
        }
        Declaration d1 = null;
        Declaration d2 = null;
        while (sc1 != null && !(sc1 instanceof ModuleExp)) {
            d1 = sc1.lookup(id1);
            if (d1 != null) {
                break;
            }
            sc1 = sc1.outer;
        }
        while (sc2 != null && !(sc2 instanceof ModuleExp)) {
            d2 = sc2.lookup(id2);
            if (d2 != null) {
                break;
            }
            sc2 = sc2.outer;
        }
        return d1 == d2;
    }

    public static Object[] getLiteralsList(Object list, SyntaxForm syntax, Translator tr) {
        Object wrapped;
        Object savePos = tr.pushPositionOf(list);
        int count = Translator.listLength(list);
        if (count < 0) {
            tr.error('e', "missing or malformed literals list");
            count = 0;
        }
        Object[] literals = new Object[count + 1];
        for (int i = 1; i <= count; i++) {
            while (list instanceof SyntaxForm) {
                SyntaxForm syntax2 = (SyntaxForm) list;
                list = syntax2.getDatum();
            }
            Pair pair = (Pair) list;
            tr.pushPositionOf(pair);
            Object literal = pair.getCar();
            if (literal instanceof SyntaxForm) {
                wrapped = literal;
                literal = ((SyntaxForm) literal).getDatum();
            } else {
                wrapped = literal;
            }
            if (!(literal instanceof Symbol)) {
                tr.error('e', "non-symbol '" + literal + "' in literals list");
            }
            literals[i] = wrapped;
            list = pair.getCdr();
        }
        tr.popPositionOf(savePos);
        return literals;
    }

    @Override // gnu.text.Printable
    public void print(Consumer out) {
        out.write("#<syntax-pattern>");
    }
}
