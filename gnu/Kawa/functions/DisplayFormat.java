package gnu.kawa.functions;

import gnu.bytecode.Access;
import gnu.expr.Keyword;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractFormat;
import gnu.lists.Array;
import gnu.lists.CharSeq;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.SimpleVector;
import gnu.lists.Strings;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.math.RatNum;
import gnu.text.Char;
import gnu.text.Printable;
import gnu.xml.XMLPrinter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class DisplayFormat extends AbstractFormat {
    public static final ThreadLocation outBase;
    public static final ThreadLocation outRadix;
    static Pattern r5rsIdentifierMinusInteriorColons;
    char language;
    boolean readable;

    static {
        ThreadLocation threadLocation = new ThreadLocation("out-base");
        outBase = threadLocation;
        threadLocation.setGlobal(IntNum.ten());
        outRadix = new ThreadLocation("out-radix");
        r5rsIdentifierMinusInteriorColons = Pattern.compile("(([a-zA-Z]|[!$%&*/:<=>?^_~])([a-zA-Z]|[!$%&*/<=>?^_~]|[0-9]|([-+.@]))*[:]?)|([-+]|[.][.][.])");
    }

    public DisplayFormat(boolean readable, char language) {
        this.readable = readable;
        this.language = language;
    }

    public static DisplayFormat getEmacsLispFormat(boolean readable) {
        return new DisplayFormat(readable, 'E');
    }

    public static DisplayFormat getCommonLispFormat(boolean readable) {
        return new DisplayFormat(readable, Access.CLASS_CONTEXT);
    }

    public static DisplayFormat getSchemeFormat(boolean readable) {
        return new DisplayFormat(readable, 'S');
    }

    public boolean getReadableOutput() {
        return this.readable;
    }

    @Override // gnu.lists.AbstractFormat
    public void writeBoolean(boolean v, Consumer out) {
        write(this.language == 'S' ? v ? "#t" : "#f" : v ? "t" : "nil", out);
    }

    @Override // gnu.lists.AbstractFormat
    public void write(int v, Consumer out) {
        if (!getReadableOutput()) {
            Char.print(v, out);
        } else if (this.language == 'E' && v > 32) {
            out.write(63);
            Char.print(v, out);
        } else {
            write(Char.toScmReadableString(v), out);
        }
    }

    public void writeList(LList value, OutPort out) {
        Object list = value;
        out.startLogicalBlock("(", false, ")");
        while (list instanceof Pair) {
            if (list != value) {
                out.writeSpaceFill();
            }
            Pair pair = (Pair) list;
            writeObject(pair.getCar(), (Consumer) out);
            list = pair.getCdr();
        }
        if (list != LList.Empty) {
            out.writeSpaceFill();
            out.write(". ");
            writeObject(LList.checkNonList(list), (Consumer) out);
        }
        out.endLogicalBlock(")");
    }

    @Override // gnu.lists.AbstractFormat
    public void writeObject(Object obj, Consumer out) {
        boolean space = false;
        if ((out instanceof OutPort) && !(obj instanceof UntypedAtomic) && !(obj instanceof Values) && (getReadableOutput() || (!(obj instanceof Char) && !(obj instanceof CharSequence) && !(obj instanceof Character)))) {
            ((OutPort) out).writeWordStart();
            space = true;
        }
        writeObjectRaw(obj, out);
        if (space) {
            ((OutPort) out).writeWordEnd();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeObjectRaw(Object obj, Consumer consumer) {
        String asString;
        String start;
        String end;
        if (obj instanceof Boolean) {
            writeBoolean(((Boolean) obj).booleanValue(), consumer);
            return;
        }
        if (obj instanceof Char) {
            write(((Char) obj).intValue(), consumer);
            return;
        }
        if (obj instanceof Character) {
            write(((Character) obj).charValue(), consumer);
            return;
        }
        if (obj instanceof Symbol) {
            Symbol sym = (Symbol) obj;
            if (sym.getNamespace() == XmlNamespace.HTML) {
                write("html:", consumer);
                write(sym.getLocalPart(), consumer);
                return;
            } else {
                writeSymbol(sym, consumer, this.readable);
                return;
            }
        }
        if ((obj instanceof URI) && getReadableOutput() && (consumer instanceof PrintWriter)) {
            write("#,(URI ", consumer);
            Strings.printQuoted(obj.toString(), (PrintWriter) consumer, 1);
            consumer.write(41);
            return;
        }
        if (obj instanceof CharSequence) {
            CharSequence str = (CharSequence) obj;
            if (getReadableOutput() && (consumer instanceof PrintWriter)) {
                Strings.printQuoted(str, (PrintWriter) consumer, 1);
                return;
            }
            if (obj instanceof String) {
                consumer.write((String) obj);
                return;
            }
            if (obj instanceof CharSeq) {
                CharSeq seq = (CharSeq) obj;
                seq.consume(0, seq.size(), consumer);
                return;
            }
            int len = str.length();
            for (int i = 0; i < len; i++) {
                consumer.write(str.charAt(i));
            }
            return;
        }
        if ((obj instanceof LList) && (consumer instanceof OutPort)) {
            writeList((LList) obj, (OutPort) consumer);
            return;
        }
        if (obj instanceof SimpleVector) {
            SimpleVector vec = (SimpleVector) obj;
            String tag = vec.getTag();
            if (this.language == 'E') {
                start = "[";
                end = "]";
            } else {
                start = tag == null ? "#(" : "#" + tag + "(";
                end = ")";
            }
            if (consumer instanceof OutPort) {
                ((OutPort) consumer).startLogicalBlock(start, false, end);
            } else {
                write(start, consumer);
            }
            int endpos = vec.size() << 1;
            for (int ipos = 0; ipos < endpos; ipos += 2) {
                if (ipos > 0 && (consumer instanceof OutPort)) {
                    ((OutPort) consumer).writeSpaceFill();
                }
                if (!vec.consumeNext(ipos, consumer)) {
                    break;
                }
            }
            if (consumer instanceof OutPort) {
                ((OutPort) consumer).endLogicalBlock(end);
                return;
            } else {
                write(end, consumer);
                return;
            }
        }
        if (obj instanceof Array) {
            write((Array) obj, 0, 0, consumer);
            return;
        }
        if (obj instanceof KNode) {
            if (getReadableOutput()) {
                write("#", consumer);
            }
            Writer wout = consumer instanceof Writer ? (Writer) consumer : new ConsumerWriter(consumer);
            XMLPrinter xout = new XMLPrinter(wout);
            xout.writeObject(obj);
            xout.closeThis();
            return;
        }
        if (obj == Values.empty && getReadableOutput()) {
            write("#!void", consumer);
            return;
        }
        if (obj instanceof Consumable) {
            ((Consumable) obj).consume(consumer);
            return;
        }
        if (obj instanceof Printable) {
            ((Printable) obj).print(consumer);
            return;
        }
        if (obj instanceof RatNum) {
            int b = 10;
            boolean showRadix = false;
            Object base = outBase.get(null);
            Object printRadix = outRadix.get(null);
            if (printRadix != null && (printRadix == Boolean.TRUE || "yes".equals(printRadix.toString()))) {
                showRadix = true;
            }
            if (base instanceof Number) {
                b = ((IntNum) base).intValue();
            } else if (base != null) {
                b = Integer.parseInt(base.toString());
            }
            String asString2 = ((RatNum) obj).toString(b);
            if (showRadix) {
                if (b == 16) {
                    write("#x", consumer);
                } else if (b == 8) {
                    write("#o", consumer);
                } else if (b == 2) {
                    write("#b", consumer);
                } else if (b != 10 || !(obj instanceof IntNum)) {
                    write("#" + base + "r", consumer);
                }
            }
            write(asString2, consumer);
            if (showRadix && b == 10 && (obj instanceof IntNum)) {
                write(".", consumer);
                return;
            }
            return;
        }
        if ((obj instanceof Enum) && getReadableOutput()) {
            write(obj.getClass().getName(), consumer);
            write(":", consumer);
            write(((Enum) obj).name(), consumer);
            return;
        }
        if (obj == null) {
            asString = null;
        } else {
            Class cl = obj.getClass();
            if (cl.isArray()) {
                int len2 = java.lang.reflect.Array.getLength(obj);
                if (consumer instanceof OutPort) {
                    ((OutPort) consumer).startLogicalBlock("[", false, "]");
                } else {
                    write("[", consumer);
                }
                for (int i2 = 0; i2 < len2; i2++) {
                    if (i2 > 0) {
                        write(" ", consumer);
                        if (consumer instanceof OutPort) {
                            ((OutPort) consumer).writeBreakFill();
                        }
                    }
                    writeObject(java.lang.reflect.Array.get(obj, i2), consumer);
                }
                if (consumer instanceof OutPort) {
                    ((OutPort) consumer).endLogicalBlock("]");
                    return;
                } else {
                    write("]", consumer);
                    return;
                }
            }
            asString = obj.toString();
        }
        if (asString == null) {
            write("#!null", consumer);
        } else {
            write(asString, consumer);
        }
    }

    int write(Array array, int index, int level, Consumer out) {
        int step;
        int rank = array.rank();
        int count = 0;
        String start = level > 0 ? "(" : rank == 1 ? "#(" : "#" + rank + "a(";
        if (out instanceof OutPort) {
            ((OutPort) out).startLogicalBlock(start, false, ")");
        } else {
            write(start, out);
        }
        if (rank > 0) {
            int size = array.getSize(level);
            int level2 = level + 1;
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    write(" ", out);
                    if (out instanceof OutPort) {
                        ((OutPort) out).writeBreakFill();
                    }
                }
                if (level2 == rank) {
                    writeObject(array.getRowMajor(index), out);
                    step = 1;
                } else {
                    step = write(array, index, level2, out);
                }
                index += step;
                count += step;
            }
        }
        if (out instanceof OutPort) {
            ((OutPort) out).endLogicalBlock(")");
        } else {
            write(")", out);
        }
        return count;
    }

    void writeSymbol(Symbol sym, Consumer out, boolean readable) {
        String prefix = sym.getPrefix();
        Namespace namespace = sym.getNamespace();
        String uri = namespace == null ? null : namespace.getName();
        boolean hasUri = uri != null && uri.length() > 0;
        boolean hasPrefix = prefix != null && prefix.length() > 0;
        boolean suffixColon = false;
        if (namespace == Keyword.keywordNamespace) {
            char c = this.language;
            if (c == 'C' || c == 'E') {
                out.write(58);
            } else {
                suffixColon = true;
            }
        } else if (hasPrefix || hasUri) {
            if (hasPrefix) {
                writeSymbol(prefix, out, readable);
            }
            if (hasUri && (readable || !hasPrefix)) {
                out.write(123);
                out.write(uri);
                out.write(125);
            }
            out.write(58);
        }
        writeSymbol(sym.getName(), out, readable);
        if (suffixColon) {
            out.write(58);
        }
    }

    void writeSymbol(String sym, Consumer out, boolean readable) {
        if (readable && !r5rsIdentifierMinusInteriorColons.matcher(sym).matches()) {
            int len = sym.length();
            if (len == 0) {
                write("||", out);
                return;
            }
            boolean inVerticalBars = false;
            for (int i = 0; i < len; i++) {
                char ch = sym.charAt(i);
                if (ch == '|') {
                    write(inVerticalBars ? "|\\" : "\\", out);
                    inVerticalBars = false;
                } else if (!inVerticalBars) {
                    out.write(124);
                    inVerticalBars = true;
                }
                out.write(ch);
            }
            if (inVerticalBars) {
                out.write(124);
                return;
            }
            return;
        }
        write(sym, out);
    }
}
