package gnu.xquery.util;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.xml.ChildAxis;
import gnu.kawa.xml.CoerceNodes;
import gnu.kawa.xml.DescendantAxis;
import gnu.kawa.xml.DescendantOrSelfAxis;
import gnu.kawa.xml.NodeSetType;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.SortNodes;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.xquery.lang.XQuery;

/* loaded from: classes2.dex */
public class CompileMisc {
    static final Method castMethod;
    static final Method castableMethod;
    static final ClassType typeTuples = ClassType.make("gnu.xquery.util.OrderedTuples");
    static final ClassType typeXDataType;

    public static Expression validateCompare(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        Expression folded = exp.inlineIfConstant(proc, visitor);
        if (folded != exp) {
            return folded;
        }
        Compare cproc = (Compare) proc;
        if ((cproc.flags & 32) == 0) {
            exp = new ApplyExp(ClassType.make("gnu.xquery.util.Compare").getDeclaredMethod("apply", 4), new QuoteExp(IntNum.make(cproc.flags)), exp.getArg(0), exp.getArg(1), QuoteExp.nullExp);
        }
        if (exp.getTypeRaw() == null) {
            exp.setType(XDataType.booleanType);
        }
        return exp;
    }

    public static Expression validateBooleanValue(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length == 1) {
            Expression arg = args[0];
            Type type = arg.getType();
            if (type == XDataType.booleanType) {
                return arg;
            }
            if (type == null) {
                exp.setType(XDataType.booleanType);
            }
            if (arg instanceof QuoteExp) {
                Object value = ((QuoteExp) arg).getValue();
                try {
                    return BooleanValue.booleanValue(value) ? XQuery.trueExp : XQuery.falseExp;
                } catch (Throwable th) {
                    visitor.getMessages().error('e', "cannot convert to a boolean");
                    return new ErrorExp("cannot convert to a boolean");
                }
            }
        }
        return exp;
    }

    public static Expression validateArithOp(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        return exp;
    }

    public static Expression validateApplyValuesFilter(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        Type seqType;
        Method sizeMethod;
        ValuesFilter vproc = (ValuesFilter) proc;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        Expression exp2 = args[1];
        if (exp2 instanceof LambdaExp) {
            LambdaExp lexp2 = (LambdaExp) exp2;
            if (lexp2.min_args == 3 && lexp2.max_args == 3) {
                exp.setType(args[0].getType());
                Compilation parser = visitor.getCompilation();
                Declaration dotArg = lexp2.firstDecl();
                Declaration posArg = dotArg.nextDecl();
                Declaration lastArg = posArg.nextDecl();
                lexp2.setInlineOnly(true);
                lexp2.returnContinuation = exp;
                lexp2.inlineHome = visitor.getCurrentLambda();
                lexp2.remove(posArg, lastArg);
                lexp2.min_args = 2;
                lexp2.max_args = 2;
                if (!lastArg.getCanRead() && vproc.kind != 'R') {
                    return exp;
                }
                parser.letStart();
                Expression seq = args[0];
                if (vproc.kind == 'P') {
                    seqType = seq.getType();
                    sizeMethod = Compilation.typeValues.getDeclaredMethod("countValues", 1);
                } else {
                    seqType = SortNodes.typeSortedNodes;
                    seq = new ApplyExp(SortNodes.sortNodes, seq);
                    sizeMethod = CoerceNodes.typeNodes.getDeclaredMethod("size", 0);
                }
                Declaration sequence = parser.letVariable("sequence", seqType, seq);
                parser.letEnter();
                Expression pred = lexp2.body;
                Type predType = lexp2.body.getType();
                if (predType != XDataType.booleanType) {
                    pred = new ApplyExp(ValuesFilter.matchesMethod, pred, new ReferenceExp(posArg));
                }
                if (vproc.kind == 'R') {
                    Declaration posIncoming = new Declaration((Object) null, Type.intType);
                    Expression init = new ApplyExp(AddOp.$Mn, new ReferenceExp(lastArg), new ReferenceExp(posIncoming));
                    LetExp let = new LetExp(new Expression[]{new ApplyExp(AddOp.$Pl, init, new QuoteExp(IntNum.one()))});
                    lexp2.replaceFollowing(dotArg, posIncoming);
                    let.add(posArg);
                    let.body = pred;
                    pred = let;
                }
                lexp2.body = new IfExp(pred, new ReferenceExp(dotArg), QuoteExp.voidExp);
                ApplyExp doMap = new ApplyExp(ValuesMap.valuesMapWithPos, lexp2, new ReferenceExp(sequence));
                doMap.setType(dotArg.getType());
                lexp2.returnContinuation = doMap;
                Expression lastInit = new ApplyExp(sizeMethod, new ReferenceExp(sequence));
                LetExp let2 = new LetExp(new Expression[]{lastInit});
                let2.add(lastArg);
                let2.body = gnu.kawa.functions.CompileMisc.validateApplyValuesMap(doMap, visitor, required, ValuesMap.valuesMapWithPos);
                return parser.letDone(let2);
            }
        }
        return exp;
    }

    public static Expression validateApplyRelativeStep(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        Type rtype;
        ApplyExp result;
        ApplyExp result2;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        Expression exp1 = args[0];
        Expression exp2 = args[1];
        Compilation comp = visitor.getCompilation();
        if ((exp2 instanceof LambdaExp) && comp.mustCompile) {
            LambdaExp lexp2 = (LambdaExp) exp2;
            if (lexp2.min_args == 3 && lexp2.max_args == 3) {
                lexp2.setInlineOnly(true);
                lexp2.returnContinuation = exp;
                lexp2.inlineHome = visitor.getCurrentLambda();
                Expression exp22 = lexp2.body;
                Declaration dotArg = lexp2.firstDecl();
                Declaration posArg = dotArg.nextDecl();
                Declaration lastArg = posArg.nextDecl();
                posArg.setNext(lastArg.nextDecl());
                lastArg.setNext(null);
                lexp2.min_args = 2;
                lexp2.max_args = 2;
                Type type1 = exp1.getType();
                if (type1 != null && NodeType.anyNodeTest.compare(type1) == -3) {
                    Language language = visitor.getCompilation().getLanguage();
                    String message = "step input is " + language.formatType(type1) + " - not a node sequence";
                    visitor.getMessages().error('e', message);
                    return new ErrorExp(message);
                }
                Type rtype2 = exp.getTypeRaw();
                if (rtype2 == null || rtype2 == Type.pointer_type) {
                    Type type2 = exp22.getType();
                    Type rtypePrime = OccurrenceType.itemPrimeType(type2);
                    int nodeCompare = NodeType.anyNodeTest.compare(rtypePrime);
                    if (nodeCompare < 0) {
                        rtype = OccurrenceType.getInstance(rtypePrime, 0, -1);
                    } else {
                        rtype = NodeSetType.getInstance(rtypePrime);
                    }
                    exp.setType(rtype);
                }
                if (lastArg.getCanRead()) {
                    ClassType typeNodes = CoerceNodes.typeNodes;
                    comp.letStart();
                    Declaration sequence = comp.letVariable(null, typeNodes, new ApplyExp(CoerceNodes.coerceNodes, exp1));
                    comp.letEnter();
                    Method sizeMethod = typeNodes.getDeclaredMethod("size", 0);
                    Expression lastInit = new ApplyExp(sizeMethod, new ReferenceExp(sequence));
                    LetExp lastLet = new LetExp(new Expression[]{lastInit});
                    lastLet.addDeclaration(lastArg);
                    lastLet.body = new ApplyExp(exp.getFunction(), new ReferenceExp(sequence), lexp2);
                    return comp.letDone(lastLet);
                }
                if (!(exp22 instanceof ApplyExp)) {
                    result = exp;
                } else {
                    ApplyExp aexp2 = (ApplyExp) exp22;
                    Object proc2 = aexp2.getFunction().valueIfConstant();
                    if (proc2 instanceof ValuesFilter) {
                        Expression vexp2 = aexp2.getArgs()[1];
                        if (!(vexp2 instanceof LambdaExp)) {
                            result = exp;
                        } else {
                            LambdaExp lvexp2 = (LambdaExp) vexp2;
                            Declaration dot2 = lvexp2.firstDecl();
                            if (dot2 != null) {
                                Declaration pos2 = dot2.nextDecl();
                                if (pos2 == null || pos2.nextDecl() != null || pos2.getCanRead()) {
                                    result = exp;
                                } else {
                                    result = exp;
                                    if (ClassType.make("java.lang.Number").compare(lvexp2.body.getType()) == -3) {
                                        exp22 = aexp2.getArg(0);
                                        lexp2.body = exp22;
                                        aexp2.setArg(0, exp);
                                        result2 = aexp2;
                                        if (!(exp1 instanceof ApplyExp) && (exp22 instanceof ApplyExp)) {
                                            ApplyExp aexp1 = (ApplyExp) exp1;
                                            ApplyExp aexp22 = (ApplyExp) exp22;
                                            Object p1 = aexp1.getFunction().valueIfConstant();
                                            Object p2 = aexp22.getFunction().valueIfConstant();
                                            if (p1 == RelativeStep.relativeStep && (p2 instanceof ChildAxis) && aexp1.getArgCount() == 2) {
                                                Expression exp12 = aexp1.getArg(1);
                                                if (exp12 instanceof LambdaExp) {
                                                    LambdaExp lexp12 = (LambdaExp) exp12;
                                                    if ((lexp12.body instanceof ApplyExp) && ((ApplyExp) lexp12.body).getFunction().valueIfConstant() == DescendantOrSelfAxis.anyNode) {
                                                        exp.setArg(0, aexp1.getArg(0));
                                                        aexp22.setFunction(new QuoteExp(DescendantAxis.make(((ChildAxis) p2).getNodePredicate())));
                                                    }
                                                }
                                            }
                                        }
                                        return result2;
                                    }
                                }
                            } else {
                                result = exp;
                            }
                        }
                    } else {
                        result = exp;
                    }
                }
                result2 = result;
                if (!(exp1 instanceof ApplyExp)) {
                }
                return result2;
            }
        }
        return exp;
    }

    public static Expression validateApplyOrderedMap(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length > 2) {
            Expression[] rargs = new Expression[args.length - 1];
            System.arraycopy(args, 1, rargs, 0, rargs.length);
            Method makeTupleMethod = typeTuples.getDeclaredMethod("make$V", 2);
            Expression[] xargs = {args[0], new ApplyExp(makeTupleMethod, rargs)};
            return new ApplyExp(proc, xargs);
        }
        return exp;
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.XDataType");
        typeXDataType = make;
        castMethod = make.getDeclaredMethod("cast", 1);
        castableMethod = make.getDeclaredMethod("castable", 1);
    }

    public static void compileOrderedMap(ApplyExp exp, Compilation comp, Target target, Procedure proc) {
        Expression[] args = exp.getArgs();
        if (args.length != 2) {
            ApplyExp.compile(exp, comp, target);
            return;
        }
        CodeAttr code = comp.getCode();
        Scope scope = code.pushScope();
        ClassType classType = typeTuples;
        Variable consumer = scope.addVariable(code, classType, null);
        args[1].compile(comp, Target.pushValue(classType));
        code.emitStore(consumer);
        ConsumerTarget ctarget = new ConsumerTarget(consumer);
        args[0].compile(comp, ctarget);
        Method mm = classType.getDeclaredMethod("run$X", 1);
        code.emitLoad(consumer);
        PrimProcedure.compileInvoke(comp, mm, target, exp.isTailCall(), 182, Type.pointer_type);
        code.popScope();
    }

    public static Expression validateApplyCastAs(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        ApplyExp exp2 = CompileReflect.inlineClassName(exp, 0, visitor);
        Expression[] args = exp2.getArgs();
        if (args.length != 2 || !(args[0] instanceof QuoteExp)) {
            return exp2;
        }
        Object type = ((QuoteExp) args[0]).getValue();
        if (type instanceof XDataType) {
            return new ApplyExp(castMethod, args);
        }
        return exp2;
    }

    public static Expression validateApplyCastableAs(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        ApplyExp exp2 = CompileReflect.inlineClassName(exp, 1, visitor);
        Expression[] args = exp2.getArgs();
        if (args.length != 2 || !(args[1] instanceof QuoteExp)) {
            return exp2;
        }
        Object type = ((QuoteExp) args[1]).getValue();
        if (type instanceof XDataType) {
            return new ApplyExp(castableMethod, args[1], args[0]);
        }
        return exp2;
    }
}
