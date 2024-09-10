package gnu.kawa.functions;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.Access;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Member;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.HasNamedParts;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class CompileNamedPart {
    static final ClassType typeHasNamedParts = ClassType.make("gnu.mapping.HasNamedParts");

    public static Expression validateGetNamedPart(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        Object val;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length != 2 || !(args[1] instanceof QuoteExp) || !(exp instanceof GetNamedExp)) {
            return exp;
        }
        Expression context = args[0];
        Declaration decl = null;
        if (context instanceof ReferenceExp) {
            ReferenceExp rexp = (ReferenceExp) context;
            if ("*".equals(rexp.getName())) {
                return makeGetNamedInstancePartExp(args[1]);
            }
            decl = rexp.getBinding();
        }
        String mname = ((QuoteExp) args[1]).getValue().toString();
        Type type = context.getType();
        if (context == QuoteExp.nullExp) {
        }
        Compilation comp = visitor.getCompilation();
        Language language = comp.getLanguage();
        Type typeval = language.getTypeFor(context, false);
        ClassType caller = comp == null ? null : comp.curClass != null ? comp.curClass : comp.mainClass;
        GetNamedExp nexp = (GetNamedExp) exp;
        if (typeval != null) {
            if (mname.equals(GetNamedPart.CLASSTYPE_FOR)) {
                return new QuoteExp(typeval);
            }
            if (typeval instanceof ObjectType) {
                if (mname.equals("new")) {
                    return nexp.setProcedureKind('N');
                }
                if (mname.equals(GetNamedPart.INSTANCEOF_METHOD_NAME)) {
                    return nexp.setProcedureKind(Access.INNERCLASS_CONTEXT);
                }
                if (mname.equals(GetNamedPart.CAST_METHOD_NAME)) {
                    return nexp.setProcedureKind(Access.CLASS_CONTEXT);
                }
            }
        }
        if (typeval instanceof ObjectType) {
            if (mname.length() > 1 && mname.charAt(0) == '.') {
                return new QuoteExp(new NamedPart(typeval, mname, 'D'));
            }
            if (CompileReflect.checkKnownClass(typeval, comp) < 0) {
                return exp;
            }
            PrimProcedure[] methods = ClassMethods.getMethods((ObjectType) typeval, Compilation.mangleName(mname), (char) 0, caller, language);
            if (methods != null && methods.length > 0) {
                nexp.methods = methods;
                return nexp.setProcedureKind('S');
            }
            ApplyExp aexp = new ApplyExp(SlotGet.staticField, args);
            aexp.setLine(exp);
            return visitor.visitApplyOnly(aexp, required);
        }
        if (!type.isSubtype(Compilation.typeClassType) && !type.isSubtype(Type.javalangClassType)) {
            if (type instanceof ObjectType) {
                ObjectType otype = (ObjectType) type;
                PrimProcedure[] methods2 = ClassMethods.getMethods(otype, Compilation.mangleName(mname), 'V', caller, language);
                if (methods2 != null && methods2.length > 0) {
                    nexp.methods = methods2;
                    return nexp.setProcedureKind(Access.METHOD_CONTEXT);
                }
                ClassType classType = typeHasNamedParts;
                if (type.isSubtype(classType)) {
                    if (decl != null && (val = Declaration.followAliases(decl).getConstantValue()) != null) {
                        HasNamedParts value = (HasNamedParts) val;
                        if (value.isConstant(mname)) {
                            return QuoteExp.getInstance(value.get(mname));
                        }
                    }
                    return new ApplyExp(classType.getDeclaredMethod("get", 1), args[0], QuoteExp.getInstance(mname)).setLine(exp);
                }
                Member part = SlotGet.lookupMember(otype, mname, caller);
                if (part != null || (mname.equals(PropertyTypeConstants.PROPERTY_TYPE_LENGTH) && (type instanceof ArrayType))) {
                    ApplyExp aexp2 = new ApplyExp(SlotGet.field, args);
                    aexp2.setLine(exp);
                    return visitor.visitApplyOnly(aexp2, required);
                }
            }
            if (comp.warnUnknownMember()) {
                comp.error('w', "no known slot '" + mname + "' in " + type.getName());
            }
            return exp;
        }
        return exp;
    }

    public static Expression validateSetNamedPart(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length != 3 || !(args[1] instanceof QuoteExp)) {
            return exp;
        }
        Expression context = args[0];
        String mname = ((QuoteExp) args[1]).getValue().toString();
        Type type = context.getType();
        Compilation comp = visitor.getCompilation();
        Language language = comp.getLanguage();
        Type typeval = language.getTypeFor(context);
        ClassType caller = comp == null ? null : comp.curClass != null ? comp.curClass : comp.mainClass;
        if (typeval instanceof ClassType) {
            exp = new ApplyExp(SlotSet.set$Mnstatic$Mnfield$Ex, args);
        } else if (type instanceof ClassType) {
            Object part = SlotSet.lookupMember((ClassType) type, mname, caller);
            if (part != null) {
                exp = new ApplyExp(SlotSet.set$Mnfield$Ex, args);
            }
        }
        if (exp != exp) {
            exp.setLine(exp);
        }
        exp.setType(Type.voidType);
        return exp;
    }

    public static Expression makeExp(Expression clas, Expression member) {
        String combinedName = combineName(clas, member);
        Environment env = Environment.getCurrent();
        if (combinedName != null) {
            Translator tr = (Translator) Compilation.getCurrent();
            Symbol symbol = Namespace.EmptyNamespace.getSymbol(combinedName);
            Declaration decl = tr.lexical.lookup((Object) symbol, false);
            if (!Declaration.isUnknown(decl)) {
                return new ReferenceExp(decl);
            }
            if (symbol != null && env.isBound(symbol, null)) {
                return new ReferenceExp(combinedName);
            }
        }
        if (clas instanceof ReferenceExp) {
            ReferenceExp rexp = (ReferenceExp) clas;
            if (rexp.isUnknown()) {
                Object rsym = rexp.getSymbol();
                Symbol sym = rsym instanceof Symbol ? (Symbol) rsym : env.getSymbol(rsym.toString());
                if (env.get(sym, (Object) null) == null) {
                    String name = rexp.getName();
                    try {
                        Class cl = ClassType.getContextClass(name);
                        clas = QuoteExp.getInstance(Type.make(cl));
                    } catch (Throwable th) {
                    }
                }
            }
        }
        Expression[] args = {clas, member};
        GetNamedExp exp = new GetNamedExp(args);
        exp.combinedName = combinedName;
        return exp;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0015, code lost:
    
        if (r0 == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String combineName(gnu.expr.Expression r4, gnu.expr.Expression r5) {
        /*
            java.lang.Object r0 = r5.valueIfConstant()
            r1 = r0
            boolean r0 = r0 instanceof gnu.mapping.SimpleSymbol
            if (r0 == 0) goto L3f
            boolean r0 = r4 instanceof gnu.expr.ReferenceExp
            if (r0 == 0) goto L17
            r0 = r4
            gnu.expr.ReferenceExp r0 = (gnu.expr.ReferenceExp) r0
            java.lang.String r0 = r0.getSimpleName()
            r2 = r0
            if (r0 != 0) goto L23
        L17:
            boolean r0 = r4 instanceof gnu.kawa.functions.GetNamedExp
            if (r0 == 0) goto L3f
            r0 = r4
            gnu.kawa.functions.GetNamedExp r0 = (gnu.kawa.functions.GetNamedExp) r0
            java.lang.String r0 = r0.combinedName
            r2 = r0
            if (r0 == 0) goto L3f
        L23:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r0 = r0.append(r2)
            r3 = 58
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.intern()
            return r0
        L3f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.CompileNamedPart.combineName(gnu.expr.Expression, gnu.expr.Expression):java.lang.String");
    }

    public static Expression makeExp(Expression clas, String member) {
        return makeExp(clas, new QuoteExp(member));
    }

    public static Expression makeExp(Type type, String member) {
        return makeExp(new QuoteExp(type), new QuoteExp(member));
    }

    public static Expression validateNamedPart(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        SlotGet slotProc;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        NamedPart namedPart = (NamedPart) proc;
        switch (namedPart.kind) {
            case 'D':
                String fname = namedPart.member.toString().substring(1);
                Expression[] xargs = new Expression[2];
                xargs[1] = QuoteExp.getInstance(fname);
                if (args.length > 0) {
                    xargs[0] = Compilation.makeCoercion(args[0], new QuoteExp(namedPart.container));
                    slotProc = SlotGet.field;
                } else {
                    xargs[0] = QuoteExp.getInstance(namedPart.container);
                    slotProc = SlotGet.staticField;
                }
                ApplyExp aexp = new ApplyExp(slotProc, xargs);
                aexp.setLine(exp);
                return visitor.visitApplyOnly(aexp, required);
            default:
                return exp;
        }
    }

    public static Expression validateNamedPartSetter(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        SlotSet slotProc;
        exp.visitArgs(visitor);
        NamedPart get = (NamedPart) ((NamedPartSetter) proc).getGetter();
        if (get.kind == 'D') {
            Expression[] xargs = new Expression[3];
            xargs[1] = QuoteExp.getInstance(get.member.toString().substring(1));
            xargs[2] = exp.getArgs()[0];
            if (exp.getArgCount() == 1) {
                xargs[0] = QuoteExp.getInstance(get.container);
                slotProc = SlotSet.set$Mnstatic$Mnfield$Ex;
            } else if (exp.getArgCount() == 2) {
                xargs[0] = Compilation.makeCoercion(exp.getArgs()[0], new QuoteExp(get.container));
                slotProc = SlotSet.set$Mnfield$Ex;
            } else {
                return exp;
            }
            ApplyExp aexp = new ApplyExp(slotProc, xargs);
            aexp.setLine(exp);
            return visitor.visitApplyOnly(aexp, required);
        }
        return exp;
    }

    public static Expression makeGetNamedInstancePartExp(Expression member) {
        if (member instanceof QuoteExp) {
            Object val = ((QuoteExp) member).getValue();
            if (val instanceof SimpleSymbol) {
                return QuoteExp.getInstance(new GetNamedInstancePart(val.toString()));
            }
        }
        Expression[] args = {new QuoteExp(ClassType.make("gnu.kawa.functions.GetNamedInstancePart")), member};
        return new ApplyExp(Invoke.make, args);
    }

    public static Expression validateGetNamedInstancePart(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        Procedure property;
        Expression[] xargs;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        GetNamedInstancePart gproc = (GetNamedInstancePart) proc;
        if (gproc.isField) {
            xargs = new Expression[]{args[0], new QuoteExp(gproc.pname)};
            property = SlotGet.field;
        } else {
            int nargs = args.length;
            Expression[] xargs2 = new Expression[nargs + 1];
            xargs2[0] = args[0];
            xargs2[1] = new QuoteExp(gproc.pname);
            System.arraycopy(args, 1, xargs2, 2, nargs - 1);
            property = Invoke.invoke;
            xargs = xargs2;
        }
        return visitor.visitApplyOnly(new ApplyExp(property, xargs), required);
    }

    public static Expression validateSetNamedInstancePart(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        String pname = ((SetNamedInstancePart) proc).pname;
        Expression[] xargs = {args[0], new QuoteExp(pname), args[1]};
        return visitor.visitApplyOnly(new ApplyExp(SlotSet.set$Mnfield$Ex, xargs), required);
    }
}
