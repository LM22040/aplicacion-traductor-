package gnu.xquery.util;

import gnu.bytecode.Access;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XIntegerType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.net.URI;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class StringUtils {
    private static String ERROR_VALUE = "<error>";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String coerceToString(Object arg, String functionName, int iarg, String onEmpty) {
        if (arg instanceof KNode) {
            arg = KNode.atomicValue(arg);
        }
        if ((arg == Values.empty || arg == null) && onEmpty != ERROR_VALUE) {
            return onEmpty;
        }
        if ((arg instanceof UntypedAtomic) || (arg instanceof CharSequence) || (arg instanceof URI) || (arg instanceof Path)) {
            return arg.toString();
        }
        throw new WrongType(functionName, iarg, arg, onEmpty == ERROR_VALUE ? "xs:string" : "xs:string?");
    }

    public static Object lowerCase(Object node) {
        return coerceToString(node, "lower-case", 1, "").toLowerCase();
    }

    public static Object upperCase(Object node) {
        return coerceToString(node, "upper-case", 1, "").toUpperCase();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double asDouble(Object value) {
        if (!(value instanceof Number)) {
            value = NumberValue.numberValue(value);
        }
        return ((Number) value).doubleValue();
    }

    public static Object substring(Object str, Object start) {
        double d1 = asDouble(start);
        if (Double.isNaN(d1)) {
            return "";
        }
        int i = (int) (d1 - 0.5d);
        if (i < 0) {
            i = 0;
        }
        String s = coerceToString(str, "substring", 1, "");
        int len = s.length();
        int offset = 0;
        while (true) {
            i--;
            if (i >= 0) {
                if (offset >= len) {
                    return "";
                }
                int offset2 = offset + 1;
                char ch = s.charAt(offset);
                if (ch >= 55296 && ch < 56320 && offset2 < len) {
                    offset = offset2 + 1;
                } else {
                    offset = offset2;
                }
            } else {
                return s.substring(offset);
            }
        }
    }

    public static Object substring(Object str, Object start, Object length) {
        String s = coerceToString(str, "substring", 1, "");
        int len = s.length();
        double d1 = Math.floor(asDouble(start) - 0.5d);
        double d2 = Math.floor(asDouble(length) + 0.5d) + d1;
        if (d1 <= 0.0d) {
            d1 = 0.0d;
        }
        if (d2 > len) {
            d2 = len;
        }
        if (d2 <= d1) {
            return "";
        }
        int i1 = (int) d1;
        int i2 = ((int) d2) - i1;
        int offset = 0;
        while (true) {
            i1--;
            if (i1 >= 0) {
                if (offset >= len) {
                    return "";
                }
                int offset2 = offset + 1;
                char ch = s.charAt(offset);
                if (ch >= 55296 && ch < 56320 && offset2 < len) {
                    offset = offset2 + 1;
                } else {
                    offset = offset2;
                }
            } else {
                int i12 = offset;
                while (true) {
                    i2--;
                    if (i2 >= 0) {
                        if (offset >= len) {
                            return "";
                        }
                        int offset3 = offset + 1;
                        char ch2 = s.charAt(offset);
                        if (ch2 >= 55296 && ch2 < 56320 && offset3 < len) {
                            offset = offset3 + 1;
                        } else {
                            offset = offset3;
                        }
                    } else {
                        int i22 = offset;
                        return s.substring(i12, i22);
                    }
                }
            }
        }
    }

    public static Object stringLength(Object str) {
        String s = coerceToString(str, "string-length", 1, "");
        int slen = s.length();
        int len = 0;
        int i = 0;
        while (i < slen) {
            int i2 = i + 1;
            char ch = s.charAt(i);
            if (ch >= 55296 && ch < 56320 && i2 < slen) {
                i2++;
            }
            len++;
            i = i2;
        }
        return IntNum.make(len);
    }

    public static Object substringBefore(Object str, Object find) {
        int start;
        String s = coerceToString(str, "substring-before", 1, "");
        String f = coerceToString(find, "substring-before", 2, "");
        int flen = f.length();
        return (flen != 0 && (start = s.indexOf(f)) >= 0) ? s.substring(0, start) : "";
    }

    public static Object substringAfter(Object str, Object find) {
        String s = coerceToString(str, "substring-after", 1, "");
        String f = coerceToString(find, "substring-after", 2, "");
        int flen = f.length();
        if (flen == 0) {
            return s;
        }
        int start = s.indexOf(f);
        return start >= 0 ? s.substring(start + flen) : "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00cf, code lost:
    
        r10 = r0;
        r12 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f7, code lost:
    
        r8.append(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00fa, code lost:
    
        if (r12 == 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00fc, code lost:
    
        r8.append(r12);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object translate(java.lang.Object r22, java.lang.Object r23, java.lang.Object r24) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.StringUtils.translate(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object stringPad(Object str, Object padcount) {
        int count = ((Number) NumberValue.numberValue(padcount)).intValue();
        if (count > 0) {
            String sv = coerceToString(str, "string-pad", 1, "");
            int slen = sv.length();
            StringBuffer s = new StringBuffer(count * slen);
            for (int i = 0; i < count; i++) {
                s.append(sv);
            }
            return s.toString();
        }
        if (count == 0) {
            return "";
        }
        throw new IndexOutOfBoundsException("Invalid string-pad count");
    }

    public static Object contains(Object str, Object contain) {
        String s = coerceToString(str, "contains", 1, "");
        String c = coerceToString(contain, "contains", 2, "");
        return s.indexOf(c) < 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object startsWith(Object str, Object with) {
        String s = coerceToString(str, "starts-with", 1, "");
        String w = coerceToString(with, "starts-with", 2, "");
        return s.startsWith(w) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object endsWith(Object str, Object with) {
        String s = coerceToString(str, "ends-with", 1, "");
        String w = coerceToString(with, "ends-with", 2, "");
        return s.endsWith(w) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object stringJoin(Object strseq, Object join) {
        StringBuffer s = new StringBuffer();
        String glue = coerceToString(join, "string-join", 2, ERROR_VALUE);
        int glen = glue.length();
        int index = 0;
        boolean started = false;
        while (true) {
            int next = Values.nextIndex(strseq, index);
            if (next >= 0) {
                Object obj = Values.nextValue(strseq, index);
                if (obj != Values.empty) {
                    if (started && glen > 0) {
                        s.append(glue);
                    }
                    s.append(TextUtils.stringValue(obj));
                    started = true;
                    index = next;
                }
            } else {
                return s.toString();
            }
        }
    }

    public static String concat$V(Object arg1, Object arg2, Object[] args) {
        String str1 = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(arg1, "concat", 1));
        String str2 = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(arg2, "concat", 2));
        StringBuilder result = new StringBuilder(str1);
        result.append(str2);
        int count = args.length;
        for (int i = 0; i < count; i++) {
            Object arg = SequenceUtils.coerceToZeroOrOne(args[i], "concat", i + 2);
            result.append(TextUtils.stringValue(arg));
        }
        return result.toString();
    }

    public static Object compare(Object val1, Object val2, NamedCollator coll) {
        if (val1 == Values.empty || val1 == null || val2 == Values.empty || val2 == null) {
            return Values.empty;
        }
        if (coll == null) {
            coll = NamedCollator.codepointCollation;
        }
        int ret = coll.compare(val1.toString(), val2.toString());
        return ret < 0 ? IntNum.minusOne() : ret > 0 ? IntNum.one() : IntNum.zero();
    }

    public static void stringToCodepoints$X(Object arg, CallContext ctx) {
        String str = coerceToString(arg, "string-to-codepoints", 1, "");
        int len = str.length();
        Consumer out = ctx.consumer;
        int ch = 0;
        while (ch < len) {
            int i = ch + 1;
            int ch2 = str.charAt(ch);
            if (ch2 >= 55296 && ch2 < 56320 && i < len) {
                ch2 = ((ch2 - 55296) * 1024) + (str.charAt(i) - 56320) + 65536;
                i++;
            }
            out.writeInt(ch2);
            ch = i;
        }
    }

    private static void appendCodepoint(Object code, StringBuffer sbuf) {
        IntNum I = (IntNum) XIntegerType.integerType.cast(code);
        int i = I.intValue();
        if (i <= 0 || (i > 55295 && (i < 57344 || ((i > 65533 && i < 65536) || i > 1114111)))) {
            throw new IllegalArgumentException("codepoints-to-string: " + i + " is not a valid XML character [FOCH0001]");
        }
        if (i >= 65536) {
            sbuf.append((char) (((i - 65536) >> 10) + 55296));
            i = (i & 1023) + 56320;
        }
        sbuf.append((char) i);
    }

    public static String codepointsToString(Object arg) {
        if (arg == null) {
            return "";
        }
        StringBuffer sbuf = new StringBuffer();
        if (arg instanceof Values) {
            Values vals = (Values) arg;
            int ipos = vals.startPos();
            while (true) {
                int nextPos = vals.nextPos(ipos);
                ipos = nextPos;
                if (nextPos == 0) {
                    break;
                }
                appendCodepoint(vals.getPosPrevious(ipos), sbuf);
            }
        } else {
            appendCodepoint(arg, sbuf);
        }
        return sbuf.toString();
    }

    public static String encodeForUri(Object arg) {
        String str = coerceToString(arg, "encode-for-uri", 1, "");
        return URIPath.encodeForUri(str, 'U');
    }

    public static String iriToUri(Object arg) {
        String str = coerceToString(arg, "iri-to-uru", 1, "");
        return URIPath.encodeForUri(str, Access.INNERCLASS_CONTEXT);
    }

    public static String escapeHtmlUri(Object arg) {
        String str = coerceToString(arg, "escape-html-uri", 1, "");
        return URIPath.encodeForUri(str, 'H');
    }

    public static String normalizeSpace(Object arg) {
        String str = coerceToString(arg, "normalize-space", 1, "");
        int len = str.length();
        StringBuffer sbuf = null;
        int skipped = 0;
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (Character.isWhitespace(ch)) {
                if (sbuf == null && skipped == 0 && i > 0) {
                    sbuf = new StringBuffer(str.substring(0, i));
                }
                skipped++;
            } else {
                if (skipped > 0) {
                    if (sbuf != null) {
                        sbuf.append(' ');
                    } else if (skipped > 1 || i == 1 || str.charAt(i - 1) != ' ') {
                        sbuf = new StringBuffer();
                    }
                    skipped = 0;
                }
                if (sbuf != null) {
                    sbuf.append(ch);
                }
            }
        }
        return sbuf != null ? sbuf.toString() : skipped > 0 ? "" : str;
    }

    public static Pattern makePattern(String str, String str2) {
        int length = str2.length();
        int i = 0;
        int i2 = 0;
        while (true) {
            length--;
            if (length >= 0) {
                switch (str2.charAt(length)) {
                    case 'i':
                        i2 |= 66;
                        break;
                    case 'm':
                        i2 |= 8;
                        break;
                    case 's':
                        i2 |= 32;
                        break;
                    case 'x':
                        StringBuffer stringBuffer = new StringBuffer();
                        int length2 = str.length();
                        int i3 = 0;
                        int i4 = 0;
                        while (i3 < length2) {
                            int i5 = i3 + 1;
                            char charAt = str.charAt(i3);
                            if (charAt == '\\' && i5 < length2) {
                                stringBuffer.append(charAt);
                                int i6 = i5 + 1;
                                char charAt2 = str.charAt(i5);
                                i5 = i6;
                                charAt = charAt2;
                            } else if (charAt == '[') {
                                i4++;
                            } else if (charAt == ']') {
                                i4--;
                            } else if (i4 == 0 && Character.isWhitespace(charAt)) {
                                i3 = i5;
                            }
                            stringBuffer.append(charAt);
                            i3 = i5;
                        }
                        str = stringBuffer.toString();
                        break;
                    default:
                        throw new IllegalArgumentException("unknown 'replace' flag");
                }
            } else {
                if (str.indexOf("{Is") >= 0) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    int length3 = str.length();
                    while (i < length3) {
                        int i7 = i + 1;
                        char charAt3 = str.charAt(i);
                        if (charAt3 == '\\' && i7 + 4 < length3) {
                            stringBuffer2.append(charAt3);
                            i = i7 + 1;
                            char charAt4 = str.charAt(i7);
                            stringBuffer2.append(charAt4);
                            if (charAt4 == 'p' || charAt4 == 'P') {
                                if (str.charAt(i) == '{' && str.charAt(i + 1) == 'I' && str.charAt(i + 2) == 's') {
                                    stringBuffer2.append('{');
                                    stringBuffer2.append(Access.INNERCLASS_CONTEXT);
                                    stringBuffer2.append('n');
                                    i += 3;
                                }
                            }
                        } else {
                            stringBuffer2.append(charAt3);
                            i = i7;
                        }
                    }
                    str = stringBuffer2.toString();
                }
                return Pattern.compile(str, i2);
            }
        }
    }

    public static boolean matches(Object input, String pattern) {
        return matches(input, pattern, "");
    }

    public static boolean matches(Object arg, String pattern, String flags) {
        String str = coerceToString(arg, "matches", 1, "");
        return makePattern(pattern, flags).matcher(str).find();
    }

    public static String replace(Object input, String pattern, String replacement) {
        return replace(input, pattern, replacement, "");
    }

    public static String replace(Object arg, String pattern, String replacement, String flags) {
        String str = coerceToString(arg, "replace", 1, "");
        int rlen = replacement.length();
        int i = 0;
        while (i < rlen) {
            int i2 = i + 1;
            char rch = replacement.charAt(i);
            if (rch != '\\') {
                i = i2;
            } else {
                if (i2 < rch) {
                    int i3 = i2 + 1;
                    char rch2 = replacement.charAt(i2);
                    if (rch2 == '\\' || rch2 == '$') {
                        i = i3;
                    }
                }
                throw new IllegalArgumentException("invalid replacement string [FORX0004]");
            }
        }
        return makePattern(pattern, flags).matcher(str).replaceAll(replacement);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x001f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void tokenize$X(java.lang.Object r9, java.lang.String r10, java.lang.String r11, gnu.mapping.CallContext r12) {
        /*
            r0 = 1
            java.lang.String r1 = ""
            java.lang.String r2 = "tokenize"
            java.lang.String r0 = coerceToString(r9, r2, r0, r1)
            gnu.lists.Consumer r1 = r12.consumer
            java.util.regex.Pattern r2 = makePattern(r10, r11)
            java.util.regex.Matcher r2 = r2.matcher(r0)
            int r3 = r0.length()
            if (r3 != 0) goto L1a
            return
        L1a:
            r4 = 0
        L1b:
            boolean r5 = r2.find()
            if (r5 != 0) goto L2a
            java.lang.String r6 = r0.substring(r4)
            r1.writeObject(r6)
            return
        L2a:
            int r6 = r2.start()
            java.lang.String r7 = r0.substring(r4, r6)
            r1.writeObject(r7)
            int r4 = r2.end()
            if (r4 == r6) goto L3c
            goto L1b
        L3c:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "pattern matches empty string"
            r7.<init>(r8)
            goto L45
        L44:
            throw r7
        L45:
            goto L44
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.StringUtils.tokenize$X(java.lang.Object, java.lang.String, java.lang.String, gnu.mapping.CallContext):void");
    }

    public static Object codepointEqual(Object arg1, Object arg2) {
        String str1 = coerceToString(arg1, "codepoint-equal", 1, null);
        String str2 = coerceToString(arg2, "codepoint-equal", 2, null);
        if (str1 == null || str2 == null) {
            return Values.empty;
        }
        return str1.equals(str2) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object normalizeUnicode(Object arg) {
        return normalizeUnicode(arg, "NFC");
    }

    public static Object normalizeUnicode(Object arg, String form) {
        String str = coerceToString(arg, "normalize-unicode", 1, "");
        if ("".equals(form.trim().toUpperCase())) {
            return str;
        }
        throw new UnsupportedOperationException("normalize-unicode: unicode string normalization not available");
    }
}
