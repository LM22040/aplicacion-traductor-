package kawa.standard;

import gnu.bytecode.Type;
import gnu.expr.BeginExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.ObjectExp;
import gnu.expr.QuoteExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class object extends Syntax {
    public static final Keyword accessKeyword;
    public static final Keyword allocationKeyword;
    public static final Keyword classNameKeyword;
    static final Symbol coloncolon;
    static final Keyword initKeyword;
    static final Keyword init_formKeyword;
    static final Keyword init_keywordKeyword;
    static final Keyword init_valueKeyword;
    static final Keyword initformKeyword;
    public static final Keyword interfaceKeyword;
    public static final object objectSyntax;
    public static final Keyword throwsKeyword;
    static final Keyword typeKeyword;
    Lambda lambda;

    static {
        object objectVar = new object(SchemeCompilation.lambda);
        objectSyntax = objectVar;
        objectVar.setName("object");
        accessKeyword = Keyword.make("access");
        classNameKeyword = Keyword.make("class-name");
        interfaceKeyword = Keyword.make("interface");
        throwsKeyword = Keyword.make("throws");
        typeKeyword = Keyword.make("type");
        allocationKeyword = Keyword.make("allocation");
        initKeyword = Keyword.make("init");
        initformKeyword = Keyword.make("initform");
        init_formKeyword = Keyword.make("init-form");
        init_valueKeyword = Keyword.make("init-value");
        init_keywordKeyword = Keyword.make("init-keyword");
        coloncolon = Namespace.EmptyNamespace.getSymbol("::");
    }

    public object(Lambda lambda) {
        this.lambda = lambda;
    }

    @Override // kawa.lang.Syntax
    public Expression rewriteForm(Pair form, Translator tr) {
        if (!(form.getCdr() instanceof Pair)) {
            return tr.syntaxError("missing superclass specification in object");
        }
        Pair pair = (Pair) form.getCdr();
        ObjectExp oexp = new ObjectExp();
        if (pair.getCar() instanceof FString) {
            if (!(pair.getCdr() instanceof Pair)) {
                return tr.syntaxError("missing superclass specification after object class name");
            }
            pair = (Pair) pair.getCdr();
        }
        Object[] saved = scanClassDef(pair, oexp, tr);
        if (saved != null) {
            rewriteClassDef(saved, tr);
        }
        return oexp;
    }

    public Object[] scanClassDef(Pair pair, ClassExp oexp, Translator tr) {
        ClassExp classExp;
        char c;
        Object pair_car;
        long classAccessFlag;
        Object superlist;
        Object classNamePair;
        Object classNamePair2;
        Object obj;
        Declaration decl;
        long accessFlag;
        Object args;
        Declaration decl2;
        Object components;
        Object savedPos1;
        LambdaExp method_list;
        LambdaExp last_method;
        Object obj2;
        Pair typePair;
        int allocationFlag;
        Object savedPos12;
        Pair pair2;
        Declaration decl3;
        Object savedPos2;
        Object pair_car2;
        int allocationFlag2;
        Pair pair3;
        Pair typePair2;
        int allocationFlag3;
        Object key;
        Pair typePair3;
        int allocationFlag4;
        int allocationFlag5;
        ClassExp classExp2 = oexp;
        tr.mustCompileHere();
        Object savedPos22 = pair.getCar();
        Object components2 = pair.getCdr();
        Vector inits = new Vector(20);
        Object savedPos13 = null;
        LambdaExp method_list2 = null;
        LambdaExp last_method2 = null;
        Object obj3 = components2;
        long classAccessFlag2 = 0;
        while (obj3 != LList.Empty) {
            while (obj3 instanceof SyntaxForm) {
                obj3 = ((SyntaxForm) obj3).getDatum();
            }
            if (!(obj3 instanceof Pair)) {
                tr.error('e', "object member not a list");
                return null;
            }
            Pair pair4 = (Pair) obj3;
            Object pair_car3 = pair4.getCar();
            while (pair_car3 instanceof SyntaxForm) {
                pair_car3 = ((SyntaxForm) pair_car3).getDatum();
            }
            Object obj4 = pair4.getCdr();
            Object savedPos14 = tr.pushPositionOf(pair4);
            if (!(pair_car3 instanceof Keyword)) {
                pair_car = pair_car3;
                classAccessFlag = classAccessFlag2;
                superlist = savedPos22;
                classNamePair = savedPos13;
                classNamePair2 = savedPos14;
                obj = obj4;
            } else {
                obj = obj4;
                while (obj instanceof SyntaxForm) {
                    obj = ((SyntaxForm) obj).getDatum();
                }
                if (!(obj instanceof Pair)) {
                    pair_car = pair_car3;
                    classAccessFlag = classAccessFlag2;
                    superlist = savedPos22;
                    classNamePair = savedPos13;
                    classNamePair2 = savedPos14;
                } else if (pair_car3 == interfaceKeyword) {
                    Object val = ((Pair) obj).getCar();
                    if (val == Boolean.FALSE) {
                        classExp2.setFlag(65536);
                    } else {
                        classExp2.setFlag(32768);
                    }
                    Object obj5 = ((Pair) obj).getCdr();
                    tr.popPositionOf(savedPos14);
                    obj3 = obj5;
                } else {
                    Object val2 = classNameKeyword;
                    if (pair_car3 == val2) {
                        if (savedPos13 != null) {
                            tr.error('e', "duplicate class-name specifiers");
                        }
                        savedPos13 = obj;
                        obj3 = ((Pair) obj).getCdr();
                        tr.popPositionOf(savedPos14);
                    } else {
                        Object obj6 = accessKeyword;
                        if (pair_car3 != obj6) {
                            pair_car = pair_car3;
                            classAccessFlag = classAccessFlag2;
                            superlist = savedPos22;
                            classNamePair = savedPos13;
                            classNamePair2 = savedPos14;
                        } else {
                            Object savedPos23 = tr.pushPositionOf(obj);
                            Object superlist2 = savedPos22;
                            Object classNamePair3 = savedPos13;
                            classAccessFlag2 = addAccessFlags(((Pair) obj).getCar(), classAccessFlag2, Declaration.CLASS_ACCESS_FLAGS, "class", tr);
                            if (classExp2.nameDecl == null) {
                                tr.error('e', "access specifier for anonymous class");
                            }
                            tr.popPositionOf(savedPos23);
                            obj3 = ((Pair) obj).getCdr();
                            tr.popPositionOf(savedPos14);
                            savedPos13 = classNamePair3;
                            savedPos22 = superlist2;
                        }
                    }
                }
            }
            Object pair_car4 = pair_car;
            if (!(pair_car4 instanceof Pair)) {
                tr.error('e', "object member not a list");
                return null;
            }
            Pair initPair = (Pair) pair_car4;
            Object pair_car5 = initPair.getCar();
            Object pair_car6 = pair_car5;
            while (pair_car6 instanceof SyntaxForm) {
                pair_car6 = ((SyntaxForm) pair_car6).getDatum();
            }
            if ((pair_car6 instanceof String) || (pair_car6 instanceof Symbol) || (pair_car6 instanceof Keyword)) {
                Object sname = pair_car6;
                if (sname instanceof Keyword) {
                    decl = null;
                    args = initPair;
                    accessFlag = 0;
                } else {
                    decl = classExp2.addDeclaration(sname);
                    accessFlag = 0;
                    decl.setSimple(false);
                    decl.setFlag(1048576L);
                    Translator.setLine(decl, initPair);
                    args = initPair.getCdr();
                }
                boolean seenInit = false;
                Pair initPair2 = null;
                Object obj7 = args;
                Pair pair5 = null;
                Object args2 = obj7;
                int nKeywords = 0;
                int allocationFlag6 = 0;
                while (true) {
                    Pair pair6 = initPair;
                    if (args2 == LList.Empty) {
                        decl2 = decl;
                        components = components2;
                        savedPos1 = classNamePair2;
                        method_list = method_list2;
                        last_method = last_method2;
                        obj2 = obj;
                        typePair = pair5;
                        allocationFlag = nKeywords;
                        savedPos12 = sname;
                        pair2 = pair6;
                        break;
                    }
                    while (args2 instanceof SyntaxForm) {
                        args2 = ((SyntaxForm) args2).getDatum();
                    }
                    Pair pair7 = (Pair) args2;
                    Pair typePair4 = pair5;
                    Object key2 = pair7.getCar();
                    while (true) {
                        Object args3 = args2;
                        if (!(key2 instanceof SyntaxForm)) {
                            break;
                        }
                        key2 = ((SyntaxForm) key2).getDatum();
                        args2 = args3;
                    }
                    Object savedPos24 = tr.pushPositionOf(pair7);
                    args2 = pair7.getCdr();
                    Symbol symbol = coloncolon;
                    if (key2 != symbol) {
                        decl3 = decl;
                        if (!(key2 instanceof Keyword)) {
                            components = components2;
                            savedPos1 = classNamePair2;
                            last_method = last_method2;
                            obj2 = obj;
                            typePair = typePair4;
                            decl2 = decl3;
                            allocationFlag = nKeywords;
                            savedPos12 = sname;
                            method_list = method_list2;
                            savedPos2 = pair_car6;
                            pair_car2 = savedPos24;
                            if (args2 != LList.Empty && !seenInit) {
                                initPair2 = pair7;
                                seenInit = true;
                                pair5 = typePair;
                                nKeywords = allocationFlag;
                                initPair = pair7;
                            } else if ((args2 instanceof Pair) && allocationFlag6 == 0 && !seenInit && typePair == null) {
                                Pair pair8 = (Pair) args2;
                                if (pair8.getCdr() != LList.Empty) {
                                    pair2 = pair8;
                                    break;
                                }
                                args2 = pair8.getCdr();
                                initPair2 = pair8;
                                seenInit = true;
                                nKeywords = allocationFlag;
                                pair5 = pair7;
                                initPair = pair8;
                            } else {
                                break;
                            }
                            tr.popPositionOf(pair_car2);
                            obj = obj2;
                            decl = decl2;
                            sname = savedPos12;
                            method_list2 = method_list;
                            pair_car6 = savedPos2;
                            components2 = components;
                            classNamePair2 = savedPos1;
                            last_method2 = last_method;
                        }
                    } else {
                        decl3 = decl;
                    }
                    if (!(args2 instanceof Pair)) {
                        components = components2;
                        savedPos1 = classNamePair2;
                        last_method = last_method2;
                        obj2 = obj;
                        typePair = typePair4;
                        decl2 = decl3;
                        allocationFlag = nKeywords;
                        savedPos12 = sname;
                        method_list = method_list2;
                        savedPos2 = pair_car6;
                        pair_car2 = savedPos24;
                        if (args2 != LList.Empty) {
                        }
                        if (args2 instanceof Pair) {
                            break;
                        }
                        break;
                    }
                    int nKeywords2 = allocationFlag6 + 1;
                    Pair pair9 = (Pair) args2;
                    Object value = pair9.getCar();
                    Object args4 = pair9.getCdr();
                    if (key2 == symbol) {
                        components = components2;
                        savedPos1 = classNamePair2;
                        last_method = last_method2;
                        obj2 = obj;
                        decl2 = decl3;
                        allocationFlag2 = nKeywords;
                        pair3 = pair9;
                        savedPos12 = sname;
                        method_list = method_list2;
                        savedPos2 = pair_car6;
                        pair_car2 = savedPos24;
                    } else if (key2 == typeKeyword) {
                        components = components2;
                        savedPos1 = classNamePair2;
                        last_method = last_method2;
                        obj2 = obj;
                        decl2 = decl3;
                        allocationFlag2 = nKeywords;
                        pair3 = pair9;
                        savedPos12 = sname;
                        method_list = method_list2;
                        savedPos2 = pair_car6;
                        pair_car2 = savedPos24;
                    } else {
                        if (key2 == allocationKeyword) {
                            if (nKeywords == 0) {
                                allocationFlag5 = nKeywords;
                            } else {
                                allocationFlag5 = nKeywords;
                                tr.error('e', "duplicate allocation: specification");
                            }
                            if (matches(value, "class", tr) || matches(value, "static", tr)) {
                                nKeywords = 2048;
                                components = components2;
                                savedPos1 = classNamePair2;
                                last_method = last_method2;
                                obj2 = obj;
                                decl2 = decl3;
                                pair3 = pair9;
                                savedPos12 = sname;
                                pair5 = typePair4;
                                method_list = method_list2;
                                savedPos2 = pair_car6;
                                pair_car2 = savedPos24;
                            } else if (matches(value, "instance", tr)) {
                                nKeywords = 4096;
                                components = components2;
                                savedPos1 = classNamePair2;
                                last_method = last_method2;
                                obj2 = obj;
                                decl2 = decl3;
                                pair3 = pair9;
                                savedPos12 = sname;
                                pair5 = typePair4;
                                method_list = method_list2;
                                savedPos2 = pair_car6;
                                pair_car2 = savedPos24;
                            } else {
                                tr.error('e', "unknown allocation kind '" + value + "'");
                                savedPos1 = classNamePair2;
                                last_method = last_method2;
                                obj2 = obj;
                                typePair3 = typePair4;
                                decl2 = decl3;
                                allocationFlag4 = allocationFlag5;
                                pair3 = pair9;
                                savedPos12 = sname;
                                components = components2;
                                method_list = method_list2;
                                savedPos2 = pair_car6;
                                pair_car2 = savedPos24;
                                pair5 = typePair3;
                                nKeywords = allocationFlag4;
                            }
                            args2 = args4;
                            initPair = pair3;
                            allocationFlag6 = nKeywords2;
                        } else {
                            int allocationFlag7 = nKeywords;
                            Keyword keyword = initKeyword;
                            if (key2 == keyword || key2 == initformKeyword || key2 == init_formKeyword) {
                                savedPos1 = classNamePair2;
                                last_method = last_method2;
                                obj2 = obj;
                                typePair2 = typePair4;
                                decl2 = decl3;
                                allocationFlag3 = allocationFlag7;
                                pair3 = pair9;
                                savedPos12 = sname;
                                components = components2;
                                method_list = method_list2;
                                key = key2;
                                savedPos2 = pair_car6;
                                pair_car2 = savedPos24;
                            } else if (key2 == init_valueKeyword) {
                                savedPos1 = classNamePair2;
                                last_method = last_method2;
                                obj2 = obj;
                                typePair2 = typePair4;
                                decl2 = decl3;
                                allocationFlag3 = allocationFlag7;
                                pair3 = pair9;
                                savedPos12 = sname;
                                components = components2;
                                method_list = method_list2;
                                key = key2;
                                savedPos2 = pair_car6;
                                pair_car2 = savedPos24;
                            } else {
                                if (key2 == init_keywordKeyword) {
                                    if (!(value instanceof Keyword)) {
                                        tr.error('e', "invalid 'init-keyword' - not a keyword");
                                        savedPos1 = classNamePair2;
                                        last_method = last_method2;
                                        obj2 = obj;
                                        typePair3 = typePair4;
                                        decl2 = decl3;
                                        allocationFlag4 = allocationFlag7;
                                        pair3 = pair9;
                                        savedPos12 = sname;
                                        components = components2;
                                        method_list = method_list2;
                                        savedPos2 = pair_car6;
                                        pair_car2 = savedPos24;
                                    } else if (((Keyword) value).getName() == sname.toString()) {
                                        savedPos1 = classNamePair2;
                                        last_method = last_method2;
                                        obj2 = obj;
                                        typePair3 = typePair4;
                                        decl2 = decl3;
                                        allocationFlag4 = allocationFlag7;
                                        pair3 = pair9;
                                        savedPos12 = sname;
                                        components = components2;
                                        method_list = method_list2;
                                        savedPos2 = pair_car6;
                                        pair_car2 = savedPos24;
                                    } else {
                                        tr.error('w', "init-keyword option ignored");
                                        savedPos1 = classNamePair2;
                                        last_method = last_method2;
                                        obj2 = obj;
                                        typePair3 = typePair4;
                                        decl2 = decl3;
                                        allocationFlag4 = allocationFlag7;
                                        pair3 = pair9;
                                        savedPos12 = sname;
                                        components = components2;
                                        method_list = method_list2;
                                        savedPos2 = pair_car6;
                                        pair_car2 = savedPos24;
                                    }
                                } else if (key2 != accessKeyword) {
                                    savedPos1 = classNamePair2;
                                    last_method = last_method2;
                                    obj2 = obj;
                                    typePair3 = typePair4;
                                    decl2 = decl3;
                                    allocationFlag4 = allocationFlag7;
                                    pair3 = pair9;
                                    savedPos12 = sname;
                                    components = components2;
                                    method_list = method_list2;
                                    savedPos2 = pair_car6;
                                    pair_car2 = savedPos24;
                                    tr.error('w', "unknown slot keyword '" + key2 + "'");
                                } else {
                                    Object savedPos3 = tr.pushPositionOf(pair9);
                                    savedPos2 = pair_car6;
                                    pair_car2 = savedPos24;
                                    last_method = last_method2;
                                    obj2 = obj;
                                    components = components2;
                                    method_list = method_list2;
                                    decl2 = decl3;
                                    pair3 = pair9;
                                    savedPos1 = classNamePair2;
                                    savedPos12 = sname;
                                    accessFlag = addAccessFlags(value, accessFlag, Declaration.FIELD_ACCESS_FLAGS, "field", tr);
                                    tr.popPositionOf(savedPos3);
                                    pair5 = typePair4;
                                    nKeywords = allocationFlag7;
                                    args2 = args4;
                                    initPair = pair3;
                                    allocationFlag6 = nKeywords2;
                                }
                                pair5 = typePair3;
                                nKeywords = allocationFlag4;
                                args2 = args4;
                                initPair = pair3;
                                allocationFlag6 = nKeywords2;
                            }
                            if (seenInit) {
                                tr.error('e', "duplicate initialization");
                            }
                            seenInit = true;
                            if (key == keyword) {
                                pair5 = typePair2;
                                nKeywords = allocationFlag3;
                            } else {
                                initPair2 = pair3;
                                pair5 = typePair2;
                                nKeywords = allocationFlag3;
                            }
                            args2 = args4;
                            initPair = pair3;
                            allocationFlag6 = nKeywords2;
                        }
                        tr.popPositionOf(pair_car2);
                        obj = obj2;
                        decl = decl2;
                        sname = savedPos12;
                        method_list2 = method_list;
                        pair_car6 = savedPos2;
                        components2 = components;
                        classNamePair2 = savedPos1;
                        last_method2 = last_method;
                    }
                    pair5 = pair3;
                    nKeywords = allocationFlag2;
                    args2 = args4;
                    initPair = pair3;
                    allocationFlag6 = nKeywords2;
                    tr.popPositionOf(pair_car2);
                    obj = obj2;
                    decl = decl2;
                    sname = savedPos12;
                    method_list2 = method_list;
                    pair_car6 = savedPos2;
                    components2 = components;
                    classNamePair2 = savedPos1;
                    last_method2 = last_method;
                }
                args2 = null;
                if (args2 != LList.Empty) {
                    tr.error('e', "invalid argument list for slot '" + savedPos12 + "' args:" + (args2 == null ? "null" : args2.getClass().getName()));
                    return null;
                }
                if (seenInit) {
                    boolean isStatic = allocationFlag == 2048;
                    inits.addElement(decl2 != null ? decl2 : isStatic ? Boolean.TRUE : Boolean.FALSE);
                    inits.addElement(initPair2);
                }
                if (decl2 == null) {
                    if (!seenInit) {
                        tr.error('e', "missing field name");
                        return null;
                    }
                } else {
                    if (typePair != null) {
                        decl2.setType(tr.exp2Type(typePair));
                    }
                    if (allocationFlag != 0) {
                        decl2.setFlag(allocationFlag);
                    }
                    long accessFlag2 = accessFlag;
                    if (accessFlag2 != 0) {
                        decl2.setFlag(accessFlag2);
                    }
                    decl2.setCanRead(true);
                    decl2.setCanWrite(true);
                }
                method_list2 = method_list;
                last_method2 = last_method;
            } else if (pair_car6 instanceof Pair) {
                Pair mpair = (Pair) pair_car6;
                Object mname = mpair.getCar();
                if (!(mname instanceof String) && !(mname instanceof Symbol)) {
                    tr.error('e', "missing method name");
                    return null;
                }
                LambdaExp lexp = new LambdaExp();
                Translator.setLine(classExp2.addMethod(lexp, mname), mpair);
                if (last_method2 == null) {
                    method_list2 = lexp;
                } else {
                    last_method2.nextSibling = lexp;
                }
                last_method2 = lexp;
                components = components2;
                savedPos1 = classNamePair2;
                obj2 = obj;
            } else {
                tr.error('e', "invalid field/method definition");
                components = components2;
                savedPos1 = classNamePair2;
                obj2 = obj;
            }
            tr.popPositionOf(savedPos1);
            obj3 = obj2;
            classExp2 = oexp;
            savedPos13 = classNamePair;
            savedPos22 = superlist;
            classAccessFlag2 = classAccessFlag;
            components2 = components;
        }
        Object superlist3 = savedPos22;
        Object components3 = components2;
        Object classNamePair4 = savedPos13;
        LambdaExp method_list3 = method_list2;
        if (classAccessFlag2 == 0) {
            classExp = oexp;
            c = 1;
        } else {
            classExp = oexp;
            c = 1;
            classExp.nameDecl.setFlag(classAccessFlag2);
        }
        Object[] result = new Object[6];
        result[0] = classExp;
        result[c] = components3;
        result[2] = inits;
        result[3] = method_list3;
        result[4] = superlist3;
        result[5] = classNamePair4;
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:164:0x037c A[Catch: all -> 0x039c, TRY_LEAVE, TryCatch #18 {all -> 0x039c, blocks: (B:126:0x0320, B:129:0x0327, B:131:0x032b, B:133:0x033d, B:136:0x0342, B:138:0x0346, B:140:0x034a, B:142:0x034e, B:153:0x035a, B:157:0x0366, B:162:0x0370, B:164:0x037c), top: B:125:0x0320 }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x039a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02d7 A[Catch: all -> 0x041a, TryCatch #17 {all -> 0x041a, blocks: (B:92:0x02d0, B:96:0x02dd, B:98:0x02e1, B:107:0x02f0, B:210:0x02d7), top: B:91:0x02d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0253 A[Catch: all -> 0x0299, TryCatch #16 {all -> 0x0299, blocks: (B:101:0x02e5, B:238:0x024c, B:240:0x0253, B:241:0x0256, B:266:0x0278), top: B:100:0x02e5 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02e1 A[Catch: all -> 0x041a, LOOP:8: B:98:0x02e1->B:102:0x02ee, LOOP_START, PHI: r2 r19
  0x02e1: PHI (r2v24 'args' java.lang.Object) = (r2v20 'args' java.lang.Object), (r2v36 'args' java.lang.Object) binds: [B:97:0x02df, B:102:0x02ee] A[DONT_GENERATE, DONT_INLINE]
  0x02e1: PHI (r19v3 'memberSyntax' kawa.lang.SyntaxForm) = (r19v2 'memberSyntax' kawa.lang.SyntaxForm), (r19v4 'memberSyntax' kawa.lang.SyntaxForm) binds: [B:97:0x02df, B:102:0x02ee] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #17 {all -> 0x041a, blocks: (B:92:0x02d0, B:96:0x02dd, B:98:0x02e1, B:107:0x02f0, B:210:0x02d7), top: B:91:0x02d0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void rewriteClassDef(java.lang.Object[] r33, kawa.lang.Translator r34) {
        /*
            Method dump skipped, instructions count: 1179
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.object.rewriteClassDef(java.lang.Object[], kawa.lang.Translator):void");
    }

    private static void rewriteInit(Object d, ClassExp oexp, Pair initPair, Translator tr, SyntaxForm initSyntax) {
        Expression initValue;
        boolean isStatic = d instanceof Declaration ? ((Declaration) d).getFlag(2048L) : d == Boolean.TRUE;
        LambdaExp initMethod = isStatic ? oexp.clinitMethod : oexp.initMethod;
        if (initMethod == null) {
            initMethod = new LambdaExp(new BeginExp());
            initMethod.setClassMethod(true);
            initMethod.setReturnType(Type.voidType);
            if (isStatic) {
                initMethod.setName("$clinit$");
                oexp.clinitMethod = initMethod;
            } else {
                initMethod.setName("$finit$");
                oexp.initMethod = initMethod;
                initMethod.add(null, new Declaration(ThisExp.THIS_NAME));
            }
            initMethod.nextSibling = oexp.firstChild;
            oexp.firstChild = initMethod;
        }
        tr.push(initMethod);
        LambdaExp saveLambda = tr.curMethodLambda;
        tr.curMethodLambda = initMethod;
        Expression initValue2 = tr.rewrite_car(initPair, initSyntax);
        if (d instanceof Declaration) {
            Declaration decl = (Declaration) d;
            SetExp sexp = new SetExp(decl, initValue2);
            sexp.setLocation(decl);
            decl.noteValue(null);
            initValue = sexp;
        } else {
            initValue = Compilation.makeCoercion(initValue2, new QuoteExp(Type.voidType));
        }
        ((BeginExp) initMethod.body).add(initValue);
        tr.curMethodLambda = saveLambda;
        tr.pop(initMethod);
    }

    static boolean matches(Object exp, String tag, Translator tr) {
        String value;
        if (exp instanceof Keyword) {
            value = ((Keyword) exp).getName();
        } else if (exp instanceof FString) {
            value = ((FString) exp).toString();
        } else {
            if (exp instanceof Pair) {
                Object qvalue = tr.matchQuoted((Pair) exp);
                if (qvalue instanceof SimpleSymbol) {
                    value = qvalue.toString();
                }
            }
            return false;
        }
        return tag == null || tag.equals(value);
    }

    static long addAccessFlags(Object value, long previous, long allowed, String kind, Translator tr) {
        long flags = matchAccess(value, tr);
        if (flags == 0) {
            tr.error('e', "unknown access specifier " + value);
        } else if ((((-1) ^ allowed) & flags) != 0) {
            tr.error('e', "invalid " + kind + " access specifier " + value);
        } else if ((previous & flags) != 0) {
            tr.error('w', "duplicate " + kind + " access specifiers " + value);
        }
        return previous | flags;
    }

    static long matchAccess(Object value, Translator tr) {
        while (value instanceof SyntaxForm) {
            value = ((SyntaxForm) value).getDatum();
        }
        if (value instanceof Pair) {
            value = tr.matchQuoted((Pair) value);
            if (value instanceof Pair) {
                return matchAccess2((Pair) value, tr);
            }
        }
        return matchAccess1(value, tr);
    }

    private static long matchAccess2(Pair pair, Translator tr) {
        long icar = matchAccess1(pair.getCar(), tr);
        Object cdr = pair.getCdr();
        if (cdr == LList.Empty || icar == 0) {
            return icar;
        }
        if (cdr instanceof Pair) {
            long icdr = matchAccess2((Pair) cdr, tr);
            if (icdr != 0) {
                return icar | icdr;
            }
        }
        return 0L;
    }

    private static long matchAccess1(Object value, Translator tr) {
        if (value instanceof Keyword) {
            value = ((Keyword) value).getName();
        } else if (value instanceof FString) {
            value = ((FString) value).toString();
        } else if (value instanceof SimpleSymbol) {
            value = value.toString();
        }
        if ("private".equals(value)) {
            return 16777216L;
        }
        if ("protected".equals(value)) {
            return 33554432L;
        }
        if ("public".equals(value)) {
            return 67108864L;
        }
        if ("package".equals(value)) {
            return 134217728L;
        }
        if ("volatile".equals(value)) {
            return Declaration.VOLATILE_ACCESS;
        }
        if ("transient".equals(value)) {
            return Declaration.TRANSIENT_ACCESS;
        }
        if ("enum".equals(value)) {
            return Declaration.ENUM_ACCESS;
        }
        if ("final".equals(value)) {
            return Declaration.FINAL_ACCESS;
        }
        return 0L;
    }
}
