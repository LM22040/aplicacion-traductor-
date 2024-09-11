package kawa.lib.kawa;

import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: regex.scm */
/* loaded from: classes.dex */
public class regex extends ModuleBody {
    public static final regex $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod regex$Mnmatch;
    public static final ModuleMethod regex$Mnmatch$Mnpositions;
    public static final ModuleMethod regex$Mnmatch$Qu;
    public static final ModuleMethod regex$Mnquote;
    public static final ModuleMethod regex$Mnreplace;
    public static final ModuleMethod regex$Mnreplace$St;
    public static final ModuleMethod regex$Mnsplit;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("regex-replace*").readResolve();
        Lit7 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("regex-replace").readResolve();
        Lit6 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("regex-split").readResolve();
        Lit5 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("regex-match-positions").readResolve();
        Lit4 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("regex-match").readResolve();
        Lit3 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("regex-match?").readResolve();
        Lit2 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("regex-quote").readResolve();
        Lit1 = simpleSymbol7;
        Lit0 = (SimpleSymbol) new SimpleSymbol("loop").readResolve();
        regex regexVar = new regex();
        $instance = regexVar;
        regex$Mnquote = new ModuleMethod(regexVar, 2, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        regex$Mnmatch$Qu = new ModuleMethod(regexVar, 3, simpleSymbol6, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnmatch = new ModuleMethod(regexVar, 6, simpleSymbol5, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnmatch$Mnpositions = new ModuleMethod(regexVar, 9, simpleSymbol4, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnsplit = new ModuleMethod(regexVar, 12, simpleSymbol3, 8194);
        regex$Mnreplace = new ModuleMethod(regexVar, 13, simpleSymbol2, 12291);
        regex$Mnreplace$St = new ModuleMethod(regexVar, 14, simpleSymbol, 12291);
        regexVar.run();
    }

    public regex() {
        ModuleInfo.register(this);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence) {
        return isRegexMatch(obj, charSequence, 0);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence, int i) {
        return isRegexMatch(obj, charSequence, i, charSequence.length());
    }

    public static Object regexMatch(Object obj, CharSequence charSequence) {
        return regexMatch(obj, charSequence, 0);
    }

    public static Object regexMatch(Object obj, CharSequence charSequence, int i) {
        return regexMatch(obj, charSequence, i, charSequence.length());
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence) {
        return regexMatchPositions(obj, charSequence, 0);
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence, int i) {
        return regexMatchPositions(obj, charSequence, i, charSequence.length());
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    public static String regexQuote(CharSequence str) {
        return Pattern.quote(str == null ? null : str.toString());
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        if (moduleMethod.selector != 2) {
            return super.apply1(moduleMethod, obj);
        }
        try {
            return regexQuote((CharSequence) obj);
        } catch (ClassCastException e) {
            throw new WrongType(e, "regex-quote", 1, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 2) {
            return super.match1(moduleMethod, obj, callContext);
        }
        if (!(obj instanceof CharSequence)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static boolean isRegexMatch(Object re, CharSequence str, int start, int end) {
        Pattern rex;
        if (re instanceof Pattern) {
            try {
                rex = (Pattern) re;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, re);
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        Matcher matcher = rex.matcher(str);
        matcher.region(start, end);
        return matcher.find();
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 3:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 6:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 9:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 12:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 3:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 6:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 9:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 13:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 14:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 3:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 6:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 9:
                callContext.value1 = obj;
                if (!(obj2 instanceof CharSequence)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
    }

    public static Object regexMatch(Object re, CharSequence str, int start, int end) {
        Pattern rex;
        if (re instanceof Pattern) {
            try {
                rex = (Pattern) re;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, re);
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        Matcher matcher = rex.matcher(str);
        matcher.region(start, end);
        if (!matcher.find()) {
            Object r = Boolean.FALSE;
            return r;
        }
        Object r2 = LList.Empty;
        for (int igroup = matcher.groupCount(); igroup >= 0; igroup--) {
            int start2 = matcher.start(igroup);
            r2 = lists.cons(start2 < 0 ? Boolean.FALSE : str.subSequence(start2, matcher.end(igroup)), r2);
        }
        return r2;
    }

    public static Object regexMatchPositions(Object re, CharSequence str, int start, int end) {
        Pattern rex;
        if (re instanceof Pattern) {
            try {
                rex = (Pattern) re;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, re);
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        Matcher matcher = rex.matcher(str);
        matcher.region(start, end);
        if (!matcher.find()) {
            Object r = Boolean.FALSE;
            return r;
        }
        Object r2 = LList.Empty;
        for (int igroup = matcher.groupCount(); igroup >= 0; igroup--) {
            int start2 = matcher.start(igroup);
            r2 = lists.cons(start2 < 0 ? Boolean.FALSE : lists.cons(Integer.valueOf(start2), Integer.valueOf(matcher.end(igroup))), r2);
        }
        return r2;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        switch (moduleMethod.selector) {
            case 3:
                try {
                    try {
                        try {
                            return isRegexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue(), ((Number) obj4).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "regex-match?", 4, obj4);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "regex-match?", 3, obj3);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "regex-match?", 2, obj2);
                }
            case 6:
                try {
                    try {
                        try {
                            return regexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue(), ((Number) obj4).intValue());
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "regex-match", 4, obj4);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "regex-match", 3, obj3);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "regex-match", 2, obj2);
                }
            case 9:
                try {
                    try {
                        try {
                            return regexMatchPositions(obj, (CharSequence) obj2, ((Number) obj3).intValue(), ((Number) obj4).intValue());
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "regex-match-positions", 4, obj4);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "regex-match-positions", 3, obj3);
                    }
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "regex-match-positions", 2, obj2);
                }
            default:
                return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
    }

    public static LList regexSplit(Object re, CharSequence str) {
        Pattern rex;
        if (re instanceof Pattern) {
            try {
                rex = (Pattern) re;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, re);
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        String[] parts = rex.split(str, -1);
        return LList.makeList(parts, 0);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 3:
                try {
                    return isRegexMatch(obj, (CharSequence) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "regex-match?", 2, obj2);
                }
            case 6:
                try {
                    return regexMatch(obj, (CharSequence) obj2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "regex-match", 2, obj2);
                }
            case 9:
                try {
                    return regexMatchPositions(obj, (CharSequence) obj2);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "regex-match-positions", 2, obj2);
                }
            case 12:
                try {
                    return regexSplit(obj, (CharSequence) obj2);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "regex-split", 2, obj2);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static CharSequence regexReplace(Object re, CharSequence str, Object repl) {
        Pattern rex;
        if (re instanceof Pattern) {
            try {
                rex = (Pattern) re;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, re);
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        Matcher matcher = rex.matcher(str);
        if (!matcher.find()) {
            return str;
        }
        StringBuffer sbuf = new StringBuffer();
        if (!misc.isProcedure(repl)) {
            if (repl != null) {
                r4 = repl.toString();
            }
        } else {
            Object apply2 = Scheme.applyToArgs.apply2(repl, matcher.group());
            r4 = Matcher.quoteReplacement(apply2 != null ? apply2.toString() : null);
        }
        matcher.appendReplacement(sbuf, r4);
        matcher.appendTail(sbuf);
        return sbuf.toString();
    }

    public static CharSequence regexReplace$St(Object re, CharSequence str, Object repl) {
        Pattern rex;
        frame frameVar = new frame();
        frameVar.repl = repl;
        if (re instanceof Pattern) {
            try {
                rex = (Pattern) re;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, re);
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        frameVar.matcher = rex.matcher(str);
        frameVar.sbuf = new StringBuffer();
        if (misc.isProcedure(frameVar.repl)) {
            frameVar.loop = frameVar.loop;
            return frameVar.lambda1loop();
        }
        Matcher matcher = frameVar.matcher;
        Object obj = frameVar.repl;
        return matcher.replaceAll(obj == null ? null : obj.toString());
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 3:
                try {
                    try {
                        return isRegexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "regex-match?", 3, obj3);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "regex-match?", 2, obj2);
                }
            case 6:
                try {
                    try {
                        return regexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue());
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "regex-match", 3, obj3);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "regex-match", 2, obj2);
                }
            case 9:
                try {
                    try {
                        return regexMatchPositions(obj, (CharSequence) obj2, ((Number) obj3).intValue());
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "regex-match-positions", 3, obj3);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "regex-match-positions", 2, obj2);
                }
            case 13:
                try {
                    return regexReplace(obj, (CharSequence) obj2, obj3);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "regex-replace", 2, obj2);
                }
            case 14:
                try {
                    return regexReplace$St(obj, (CharSequence) obj2, obj3);
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "regex-replace*", 2, obj2);
                }
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    /* compiled from: regex.scm */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        Object loop = new ModuleMethod(this, 1, regex.Lit0, 0);
        Matcher matcher;
        Object repl;
        StringBuffer sbuf;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda1loop() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public String lambda1loop() {
            if (this.matcher.find()) {
                Matcher matcher = this.matcher;
                StringBuffer stringBuffer = this.sbuf;
                Object apply2 = Scheme.applyToArgs.apply2(this.repl, this.matcher.group());
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(apply2 == null ? null : apply2.toString()));
            }
            this.matcher.appendTail(this.sbuf);
            return this.sbuf.toString();
        }
    }
}
