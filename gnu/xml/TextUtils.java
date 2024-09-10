package gnu.xml;

import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public class TextUtils {
    public static String asString(Object node) {
        if (node == Values.empty || node == null) {
            return "";
        }
        if (node instanceof Values) {
            throw new ClassCastException();
        }
        StringBuffer sbuf = new StringBuffer(100);
        stringValue(node, sbuf);
        return sbuf.toString();
    }

    public static String stringValue(Object node) {
        StringBuffer sbuf = new StringBuffer(100);
        if (node instanceof Values) {
            TreeList tlist = (TreeList) node;
            int index = 0;
            while (true) {
                int kind = tlist.getNextKind(index);
                if (kind == 0) {
                    break;
                }
                if (kind == 32) {
                    stringValue(tlist.getPosNext(index), sbuf);
                } else {
                    tlist.stringValue(tlist.posToDataIndex(index), sbuf);
                }
                index = tlist.nextPos(index);
            }
        } else {
            stringValue(node, sbuf);
        }
        return sbuf.toString();
    }

    public static void stringValue(Object node, StringBuffer sbuf) {
        if (node instanceof KNode) {
            KNode pos = (KNode) node;
            NodeTree tlist = (NodeTree) pos.sequence;
            tlist.stringValue(tlist.posToDataIndex(pos.ipos), sbuf);
            return;
        }
        if (node instanceof BigDecimal) {
            node = XMLPrinter.formatDecimal((BigDecimal) node);
        } else if ((node instanceof Double) || (node instanceof DFloNum)) {
            node = XMLPrinter.formatDouble(((Number) node).doubleValue());
        } else if (node instanceof Float) {
            node = XMLPrinter.formatFloat(((Number) node).floatValue());
        }
        if (node != null && node != Values.empty) {
            sbuf.append(node);
        }
    }

    public static void textValue(Object arg, Consumer out) {
        String str;
        if (arg != null) {
            if ((arg instanceof Values) && ((Values) arg).isEmpty()) {
                return;
            }
            if (arg instanceof String) {
                str = (String) arg;
            } else {
                StringBuffer sbuf = new StringBuffer();
                if (arg instanceof Values) {
                    Object[] vals = ((Values) arg).getValues();
                    for (int i = 0; i < vals.length; i++) {
                        if (i > 0) {
                            sbuf.append(' ');
                        }
                        stringValue(vals[i], sbuf);
                    }
                } else {
                    stringValue(arg, sbuf);
                }
                str = sbuf.toString();
            }
            out.write(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public static String replaceWhitespace(String str, boolean collapse) {
        StringBuilder sbuf = null;
        int len = str.length();
        ?? r2 = collapse;
        int i = 0;
        while (i < len) {
            int i2 = i + 1;
            char ch = str.charAt(i);
            int isSpace = ch == ' ' ? 1 : (ch == '\t' || ch == '\r' || ch == '\n') ? 2 : 0;
            if (sbuf == null && (isSpace == 2 || ((isSpace == 1 && r2 > 0 && collapse) || (isSpace == 1 && i2 == len && collapse)))) {
                sbuf = new StringBuilder();
                int k = r2 > 0 ? i2 - 2 : i2 - 1;
                for (int j = 0; j < k; j++) {
                    sbuf.append(str.charAt(j));
                }
                ch = ' ';
            }
            if (collapse) {
                if (r2 > 0 && isSpace == 0) {
                    if (sbuf != null && sbuf.length() > 0) {
                        sbuf.append(' ');
                    }
                    r2 = 0;
                } else if (isSpace == 2 || (isSpace == 1 && r2 > 0)) {
                    r2 = 2;
                } else if (isSpace > 0) {
                    r2 = 1;
                } else {
                    r2 = 0;
                }
                if (r2 > 0) {
                    i = i2;
                }
            }
            if (sbuf != null) {
                sbuf.append(ch);
            }
            i = i2;
        }
        if (sbuf != null) {
            return sbuf.toString();
        }
        return str;
    }
}
