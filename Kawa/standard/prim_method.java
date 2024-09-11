package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Expression;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.ListPat;
import kawa.lang.Pattern;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class prim_method extends Syntax {
    public static final prim_method interface_method;
    public static final prim_method op1;
    private static Pattern pattern3;
    private static Pattern pattern4;
    public static final prim_method static_method;
    public static final prim_method virtual_method;
    int op_code;

    static {
        prim_method prim_methodVar = new prim_method(182);
        virtual_method = prim_methodVar;
        prim_methodVar.setName("primitive-virtual-method");
        prim_method prim_methodVar2 = new prim_method(184);
        static_method = prim_methodVar2;
        prim_methodVar2.setName("primitive-static-method");
        prim_method prim_methodVar3 = new prim_method(185);
        interface_method = prim_methodVar3;
        prim_methodVar3.setName("primitive-interface-method");
        prim_method prim_methodVar4 = new prim_method();
        op1 = prim_methodVar4;
        prim_methodVar4.setName("primitive-op1");
        pattern3 = new ListPat(3);
        pattern4 = new ListPat(4);
    }

    int opcode() {
        return this.op_code;
    }

    public prim_method(int opcode) {
        this.op_code = opcode;
    }

    public prim_method() {
    }

    @Override // kawa.lang.Syntax
    public Expression rewrite(Object obj, Translator tr) {
        Type ctype;
        char code;
        ClassType cl;
        PrimProcedure proc;
        Object[] match = new Object[4];
        if (this.op_code != 0 ? !pattern4.match(obj, match, 0) : !pattern3.match(obj, match, 1)) {
            return tr.syntaxError("wrong number of arguments to " + getName() + "(opcode:" + this.op_code + ")");
        }
        if (!(match[3] instanceof LList)) {
            return tr.syntaxError("missing/invalid parameter list in " + getName());
        }
        LList argp = (LList) match[3];
        int narg = argp.size();
        Type[] args = new Type[narg];
        LList argp2 = argp;
        for (int i = 0; i < narg; i++) {
            Pair p = (Pair) argp2;
            args[i] = tr.exp2Type(p);
            argp2 = (LList) p.getCdr();
        }
        Type rtype = tr.exp2Type(new Pair(match[2], null));
        if (this.op_code == 0) {
            int opcode = ((Number) match[1]).intValue();
            proc = new PrimProcedure(opcode, rtype, args);
        } else {
            ClassType cl2 = null;
            Type ctype2 = tr.exp2Type((Pair) obj);
            if (ctype2 == null) {
                ctype = ctype2;
            } else {
                ctype = ctype2.getImplementationType();
            }
            try {
                cl2 = (ClassType) ctype;
                cl2.getReflectClass();
                cl = cl2;
            } catch (Exception e) {
                if (cl2 == null) {
                    code = 'e';
                } else {
                    code = 'w';
                    cl2.setExisting(false);
                }
                tr.error(code, "unknown class: " + match[0]);
                cl = cl2;
            }
            if (match[1] instanceof Pair) {
                Pair p2 = (Pair) match[1];
                if (p2.getCar() == LispLanguage.quote_sym) {
                    match[1] = ((Pair) p2.getCdr()).getCar();
                }
            }
            proc = new PrimProcedure(this.op_code, cl, match[1].toString(), rtype, args);
        }
        return new QuoteExp(proc);
    }
}
