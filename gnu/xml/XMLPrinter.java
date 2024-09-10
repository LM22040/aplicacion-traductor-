package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.math.DFloNum;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.Path;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public class XMLPrinter extends OutPort implements PositionConsumer, XConsumer {
    private static final int COMMENT = -5;
    private static final int ELEMENT_END = -4;
    private static final int ELEMENT_START = -3;
    static final String HtmlEmptyTags = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/";
    private static final int KEYWORD = -6;
    private static final int PROC_INST = -7;
    private static final int WORD = -2;
    boolean canonicalize;
    public boolean canonicalizeCDATA;
    Object[] elementNameStack;
    int elementNesting;
    public boolean escapeNonAscii;
    public boolean escapeText;
    boolean inAttribute;
    int inComment;
    boolean inDocument;
    boolean inStartTag;
    public boolean indentAttributes;
    boolean isHtml;
    boolean isHtmlOrXhtml;
    NamespaceBinding namespaceBindings;
    NamespaceBinding[] namespaceSaveStack;
    boolean needXMLdecl;
    int prev;
    public int printIndent;
    boolean printXMLdecl;
    char savedHighSurrogate;
    public boolean strict;
    Object style;
    boolean undeclareNamespaces;
    public int useEmptyElementTag;
    public static final ThreadLocation doctypeSystem = new ThreadLocation("doctype-system");
    public static final ThreadLocation doctypePublic = new ThreadLocation("doctype-public");
    public static final ThreadLocation indentLoc = new ThreadLocation("xml-indent");

    public void setPrintXMLdecl(boolean value) {
        this.printXMLdecl = value;
    }

    public XMLPrinter(OutPort out, boolean autoFlush) {
        super(out, autoFlush);
        this.printIndent = -1;
        this.printXMLdecl = false;
        this.inAttribute = false;
        this.inStartTag = false;
        this.needXMLdecl = false;
        this.canonicalize = true;
        this.useEmptyElementTag = 2;
        this.escapeText = true;
        this.escapeNonAscii = true;
        this.isHtml = false;
        this.isHtmlOrXhtml = false;
        this.undeclareNamespaces = false;
        this.namespaceBindings = NamespaceBinding.predefinedXML;
        this.namespaceSaveStack = new NamespaceBinding[20];
        this.elementNameStack = new Object[20];
        this.prev = 32;
    }

    public XMLPrinter(Writer out, boolean autoFlush) {
        super(out, autoFlush);
        this.printIndent = -1;
        this.printXMLdecl = false;
        this.inAttribute = false;
        this.inStartTag = false;
        this.needXMLdecl = false;
        this.canonicalize = true;
        this.useEmptyElementTag = 2;
        this.escapeText = true;
        this.escapeNonAscii = true;
        this.isHtml = false;
        this.isHtmlOrXhtml = false;
        this.undeclareNamespaces = false;
        this.namespaceBindings = NamespaceBinding.predefinedXML;
        this.namespaceSaveStack = new NamespaceBinding[20];
        this.elementNameStack = new Object[20];
        this.prev = 32;
    }

    public XMLPrinter(OutputStream out, boolean autoFlush) {
        super((Writer) new OutputStreamWriter(out), true, autoFlush);
        this.printIndent = -1;
        this.printXMLdecl = false;
        this.inAttribute = false;
        this.inStartTag = false;
        this.needXMLdecl = false;
        this.canonicalize = true;
        this.useEmptyElementTag = 2;
        this.escapeText = true;
        this.escapeNonAscii = true;
        this.isHtml = false;
        this.isHtmlOrXhtml = false;
        this.undeclareNamespaces = false;
        this.namespaceBindings = NamespaceBinding.predefinedXML;
        this.namespaceSaveStack = new NamespaceBinding[20];
        this.elementNameStack = new Object[20];
        this.prev = 32;
    }

    public XMLPrinter(Writer out) {
        super(out);
        this.printIndent = -1;
        this.printXMLdecl = false;
        this.inAttribute = false;
        this.inStartTag = false;
        this.needXMLdecl = false;
        this.canonicalize = true;
        this.useEmptyElementTag = 2;
        this.escapeText = true;
        this.escapeNonAscii = true;
        this.isHtml = false;
        this.isHtmlOrXhtml = false;
        this.undeclareNamespaces = false;
        this.namespaceBindings = NamespaceBinding.predefinedXML;
        this.namespaceSaveStack = new NamespaceBinding[20];
        this.elementNameStack = new Object[20];
        this.prev = 32;
    }

    public XMLPrinter(OutputStream out) {
        super((Writer) new OutputStreamWriter(out), false, false);
        this.printIndent = -1;
        this.printXMLdecl = false;
        this.inAttribute = false;
        this.inStartTag = false;
        this.needXMLdecl = false;
        this.canonicalize = true;
        this.useEmptyElementTag = 2;
        this.escapeText = true;
        this.escapeNonAscii = true;
        this.isHtml = false;
        this.isHtmlOrXhtml = false;
        this.undeclareNamespaces = false;
        this.namespaceBindings = NamespaceBinding.predefinedXML;
        this.namespaceSaveStack = new NamespaceBinding[20];
        this.elementNameStack = new Object[20];
        this.prev = 32;
    }

    public XMLPrinter(OutputStream out, Path path) {
        super(new OutputStreamWriter(out), true, false, path);
        this.printIndent = -1;
        this.printXMLdecl = false;
        this.inAttribute = false;
        this.inStartTag = false;
        this.needXMLdecl = false;
        this.canonicalize = true;
        this.useEmptyElementTag = 2;
        this.escapeText = true;
        this.escapeNonAscii = true;
        this.isHtml = false;
        this.isHtmlOrXhtml = false;
        this.undeclareNamespaces = false;
        this.namespaceBindings = NamespaceBinding.predefinedXML;
        this.namespaceSaveStack = new NamespaceBinding[20];
        this.elementNameStack = new Object[20];
        this.prev = 32;
    }

    public static XMLPrinter make(OutPort out, Object style) {
        XMLPrinter xout = new XMLPrinter(out, true);
        xout.setStyle(style);
        return xout;
    }

    public static String toString(Object value) {
        StringWriter stringWriter = new StringWriter();
        new XMLPrinter(stringWriter).writeObject(value);
        return stringWriter.toString();
    }

    public void setStyle(Object obj) {
        this.style = obj;
        this.useEmptyElementTag = !this.canonicalize ? 1 : 0;
        if ("html".equals(obj)) {
            this.isHtml = true;
            this.isHtmlOrXhtml = true;
            this.useEmptyElementTag = 2;
            if (this.namespaceBindings == NamespaceBinding.predefinedXML) {
                this.namespaceBindings = XmlNamespace.HTML_BINDINGS;
            }
        } else if (this.namespaceBindings == XmlNamespace.HTML_BINDINGS) {
            this.namespaceBindings = NamespaceBinding.predefinedXML;
        }
        if ("xhtml".equals(obj)) {
            this.isHtmlOrXhtml = true;
            this.useEmptyElementTag = 2;
        }
        if ("plain".equals(obj)) {
            this.escapeText = false;
        }
    }

    boolean mustHexEscape(int v) {
        return (v >= 127 && (v <= 159 || this.escapeNonAscii)) || v == 8232 || (v < 32 && (this.inAttribute || !(v == 9 || v == 10)));
    }

    @Override // java.io.PrintWriter, java.io.Writer, gnu.lists.Consumer
    public void write(int v) {
        closeTag();
        if (this.printIndent >= 0 && (v == 13 || v == 10)) {
            if (v != 10 || this.prev != 13) {
                writeBreak(82);
            }
            if (this.inComment > 0) {
                this.inComment = 1;
                return;
            }
            return;
        }
        if (!this.escapeText) {
            this.bout.write(v);
            this.prev = v;
            return;
        }
        int i = this.inComment;
        if (i > 0) {
            if (v == 45) {
                if (i == 1) {
                    this.inComment = 2;
                } else {
                    this.bout.write(32);
                }
            } else {
                this.inComment = 1;
            }
            super.write(v);
            return;
        }
        this.prev = 59;
        if (v == 60 && (!this.isHtml || !this.inAttribute)) {
            this.bout.write("&lt;");
            return;
        }
        if (v == 62) {
            this.bout.write("&gt;");
            return;
        }
        if (v == 38) {
            this.bout.write("&amp;");
            return;
        }
        if (v == 34 && this.inAttribute) {
            this.bout.write("&quot;");
            return;
        }
        if (mustHexEscape(v)) {
            int i2 = v;
            if (v >= 55296) {
                if (v < 56320) {
                    this.savedHighSurrogate = (char) v;
                    return;
                } else if (v < 57344) {
                    i2 = ((this.savedHighSurrogate - 55296) * 1024) + (i2 - 56320) + 65536;
                    this.savedHighSurrogate = (char) 0;
                }
            }
            this.bout.write("&#x" + Integer.toHexString(i2).toUpperCase() + ";");
            return;
        }
        this.bout.write(v);
        this.prev = v;
    }

    private void startWord() {
        closeTag();
        writeWordStart();
    }

    @Override // gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void writeBoolean(boolean v) {
        startWord();
        super.print(v);
        writeWordEnd();
    }

    @Override // gnu.lists.PrintConsumer
    protected void startNumber() {
        startWord();
    }

    @Override // gnu.lists.PrintConsumer
    protected void endNumber() {
        writeWordEnd();
    }

    public void closeTag() {
        if (this.inStartTag && !this.inAttribute) {
            if (this.printIndent >= 0 && this.indentAttributes) {
                endLogicalBlock("");
            }
            this.bout.write(62);
            this.inStartTag = false;
            this.prev = -3;
            return;
        }
        if (this.needXMLdecl) {
            this.bout.write("<?xml version=\"1.0\"?>\n");
            if (this.printIndent >= 0) {
                startLogicalBlock("", "", 2);
            }
            this.needXMLdecl = false;
            this.prev = 62;
        }
    }

    void setIndentMode() {
        Object xmlIndent = indentLoc.get(null);
        String indent = xmlIndent != null ? xmlIndent.toString() : null;
        if (indent == null) {
            this.printIndent = -1;
            return;
        }
        if (indent.equals("pretty")) {
            this.printIndent = 0;
        } else if (indent.equals("always") || indent.equals("yes")) {
            this.printIndent = 1;
        } else {
            this.printIndent = -1;
        }
    }

    @Override // gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void startDocument() {
        if (this.printXMLdecl) {
            this.needXMLdecl = true;
        }
        setIndentMode();
        this.inDocument = true;
        if (this.printIndent >= 0 && !this.needXMLdecl) {
            startLogicalBlock("", "", 2);
        }
    }

    @Override // gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void endDocument() {
        this.inDocument = false;
        if (this.printIndent >= 0) {
            endLogicalBlock("");
        }
        freshLine();
    }

    @Override // gnu.lists.XConsumer
    public void beginEntity(Object base) {
    }

    @Override // gnu.lists.XConsumer
    public void endEntity() {
    }

    protected void writeQName(Object name) {
        if (name instanceof Symbol) {
            Symbol sname = (Symbol) name;
            String prefix = sname.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                this.bout.write(prefix);
                this.bout.write(58);
            }
            this.bout.write(sname.getLocalPart());
            return;
        }
        this.bout.write(name == null ? "{null name}" : (String) name);
    }

    @Override // gnu.mapping.OutPort, gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void startElement(Object type) {
        int j;
        closeTag();
        if (this.elementNesting == 0) {
            if (!this.inDocument) {
                setIndentMode();
            }
            if (this.prev == -7) {
                write(10);
            }
            Object systemIdentifier = doctypeSystem.get(null);
            if (systemIdentifier != null) {
                String systemId = systemIdentifier.toString();
                if (systemId.length() > 0) {
                    Object publicIdentifier = doctypePublic.get(null);
                    this.bout.write("<!DOCTYPE ");
                    this.bout.write(type.toString());
                    String publicId = publicIdentifier != null ? publicIdentifier.toString() : null;
                    if (publicId != null && publicId.length() > 0) {
                        this.bout.write(" PUBLIC \"");
                        this.bout.write(publicId);
                        this.bout.write("\" \"");
                    } else {
                        this.bout.write(" SYSTEM \"");
                    }
                    this.bout.write(systemId);
                    this.bout.write("\">");
                    println();
                }
            }
        }
        int i = this.printIndent;
        if (i >= 0) {
            int i2 = this.prev;
            if (i2 == -3 || i2 == -4 || i2 == -5) {
                writeBreak(i > 0 ? 82 : 78);
            }
            startLogicalBlock("", "", 2);
        }
        this.bout.write(60);
        writeQName(type);
        if (this.printIndent >= 0 && this.indentAttributes) {
            startLogicalBlock("", "", 2);
        }
        Object[] objArr = this.elementNameStack;
        int i3 = this.elementNesting;
        objArr[i3] = type;
        NamespaceBinding[] namespaceBindingArr = this.namespaceSaveStack;
        this.elementNesting = i3 + 1;
        namespaceBindingArr[i3] = this.namespaceBindings;
        if (type instanceof XName) {
            NamespaceBinding elementBindings = ((XName) type).namespaceNodes;
            NamespaceBinding join = NamespaceBinding.commonAncestor(elementBindings, this.namespaceBindings);
            int numBindings = elementBindings == null ? 0 : elementBindings.count(join);
            NamespaceBinding[] sortedBindings = new NamespaceBinding[numBindings];
            int i4 = 0;
            boolean sortNamespaces = this.canonicalize;
            for (NamespaceBinding ns = elementBindings; ns != join; ns = ns.next) {
                int j2 = i4;
                ns.getUri();
                String prefix = ns.getPrefix();
                while (true) {
                    j2--;
                    if (j2 < 0) {
                        break;
                    }
                    NamespaceBinding ns_j = sortedBindings[j2];
                    String prefix_j = ns_j.getPrefix();
                    if (prefix != prefix_j) {
                        if (sortNamespaces) {
                            if (prefix == null || (prefix_j != null && prefix.compareTo(prefix_j) <= 0)) {
                                break;
                            } else {
                                sortedBindings[j2 + 1] = ns_j;
                            }
                        }
                    }
                }
                if (sortNamespaces) {
                    j = j2 + 1;
                } else {
                    j = i4;
                }
                sortedBindings[j] = ns;
                i4++;
            }
            int numBindings2 = i4;
            int i5 = numBindings2;
            while (true) {
                i5--;
                if (i5 < 0) {
                    break;
                }
                NamespaceBinding ns2 = sortedBindings[i5];
                String prefix2 = ns2.prefix;
                String uri = ns2.uri;
                if (uri != this.namespaceBindings.resolve(prefix2) && (uri != null || prefix2 == null || this.undeclareNamespaces)) {
                    this.bout.write(32);
                    if (prefix2 == null) {
                        this.bout.write("xmlns");
                    } else {
                        this.bout.write("xmlns:");
                        this.bout.write(prefix2);
                    }
                    this.bout.write("=\"");
                    this.inAttribute = true;
                    if (uri != null) {
                        write(uri);
                    }
                    this.inAttribute = false;
                    this.bout.write(34);
                }
            }
            if (this.undeclareNamespaces) {
                for (NamespaceBinding ns3 = this.namespaceBindings; ns3 != join; ns3 = ns3.next) {
                    String prefix3 = ns3.prefix;
                    if (ns3.uri != null && elementBindings.resolve(prefix3) == null) {
                        this.bout.write(32);
                        if (prefix3 == null) {
                            this.bout.write("xmlns");
                        } else {
                            this.bout.write("xmlns:");
                            this.bout.write(prefix3);
                        }
                        this.bout.write("=\"\"");
                    }
                }
            }
            this.namespaceBindings = elementBindings;
        }
        int i6 = this.elementNesting;
        NamespaceBinding[] namespaceBindingArr2 = this.namespaceSaveStack;
        if (i6 >= namespaceBindingArr2.length) {
            NamespaceBinding[] nstmp = new NamespaceBinding[i6 * 2];
            System.arraycopy(namespaceBindingArr2, 0, nstmp, 0, i6);
            this.namespaceSaveStack = nstmp;
            int i7 = this.elementNesting;
            Object[] nmtmp = new Object[i7 * 2];
            System.arraycopy(this.elementNameStack, 0, nmtmp, 0, i7);
            this.elementNameStack = nmtmp;
        }
        this.inStartTag = true;
        String typeName = getHtmlTag(type);
        if ("script".equals(typeName) || "style".equals(typeName)) {
            this.escapeText = false;
        }
    }

    public static boolean isHtmlEmptyElementTag(String name) {
        int index = HtmlEmptyTags.indexOf(name);
        return index > 0 && HtmlEmptyTags.charAt(index + (-1)) == '/' && HtmlEmptyTags.charAt(name.length() + index) == '/';
    }

    protected String getHtmlTag(Object type) {
        if (type instanceof Symbol) {
            Symbol sym = (Symbol) type;
            String uri = sym.getNamespaceURI();
            if (uri == "http://www.w3.org/1999/xhtml" || (this.isHtmlOrXhtml && uri == "")) {
                return sym.getLocalPart();
            }
            return null;
        }
        if (this.isHtmlOrXhtml) {
            return type.toString();
        }
        return null;
    }

    @Override // gnu.mapping.OutPort, gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void endElement() {
        if (this.useEmptyElementTag == 0) {
            closeTag();
        }
        Object type = this.elementNameStack[this.elementNesting - 1];
        String typeName = getHtmlTag(type);
        if (this.inStartTag) {
            if (this.printIndent >= 0 && this.indentAttributes) {
                endLogicalBlock("");
            }
            String end = null;
            boolean isEmpty = typeName != null && isHtmlEmptyElementTag(typeName);
            if ((this.useEmptyElementTag == 0 || (typeName != null && !isEmpty)) && (type instanceof Symbol)) {
                Symbol sym = (Symbol) type;
                String prefix = sym.getPrefix();
                String uri = sym.getNamespaceURI();
                String local = sym.getLocalName();
                if (prefix != "") {
                    end = "></" + prefix + ":" + local + ">";
                } else if (uri == "" || uri == null) {
                    end = "></" + local + ">";
                }
            }
            if (end == null) {
                end = (isEmpty && this.isHtml) ? ">" : this.useEmptyElementTag == 2 ? " />" : "/>";
            }
            this.bout.write(end);
            this.inStartTag = false;
        } else {
            if (this.printIndent >= 0) {
                setIndentation(0, false);
                if (this.prev == -4) {
                    writeBreak(this.printIndent > 0 ? 82 : 78);
                }
            }
            this.bout.write("</");
            writeQName(type);
            this.bout.write(">");
        }
        if (this.printIndent >= 0) {
            endLogicalBlock("");
        }
        this.prev = -4;
        if (typeName != null && !this.escapeText && ("script".equals(typeName) || "style".equals(typeName))) {
            this.escapeText = true;
        }
        NamespaceBinding[] namespaceBindingArr = this.namespaceSaveStack;
        int i = this.elementNesting - 1;
        this.elementNesting = i;
        this.namespaceBindings = namespaceBindingArr[i];
        namespaceBindingArr[i] = null;
        this.elementNameStack[i] = null;
    }

    @Override // gnu.mapping.OutPort, gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void startAttribute(Object attrType) {
        if (!this.inStartTag && this.strict) {
            error("attribute not in element", "SENR0001");
        }
        if (this.inAttribute) {
            this.bout.write(34);
        }
        this.inAttribute = true;
        this.bout.write(32);
        if (this.printIndent >= 0) {
            writeBreakFill();
        }
        this.bout.write(attrType.toString());
        this.bout.write("=\"");
        this.prev = 32;
    }

    @Override // gnu.mapping.OutPort, gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void endAttribute() {
        if (this.inAttribute) {
            if (this.prev != -6) {
                this.bout.write(34);
                this.inAttribute = false;
            }
            this.prev = 32;
        }
    }

    @Override // gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void writeDouble(double d) {
        startWord();
        this.bout.write(formatDouble(d));
    }

    @Override // gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void writeFloat(float f) {
        startWord();
        this.bout.write(formatFloat(f));
    }

    public static String formatDouble(double d) {
        if (Double.isNaN(d)) {
            return "NaN";
        }
        boolean neg = d < 0.0d;
        if (Double.isInfinite(d)) {
            return neg ? "-INF" : "INF";
        }
        double dabs = neg ? -d : d;
        String dstr = Double.toString(d);
        if ((dabs >= 1000000.0d || dabs < 1.0E-6d) && dabs != 0.0d) {
            return RealNum.toStringScientific(dstr);
        }
        return formatDecimal(RealNum.toStringDecimal(dstr));
    }

    public static String formatFloat(float f) {
        if (Float.isNaN(f)) {
            return "NaN";
        }
        boolean neg = f < 0.0f;
        if (Float.isInfinite(f)) {
            return neg ? "-INF" : "INF";
        }
        float fabs = neg ? -f : f;
        String fstr = Float.toString(f);
        if ((fabs >= 1000000.0f || fabs < 1.0E-6d) && fabs != 0.0d) {
            return RealNum.toStringScientific(fstr);
        }
        return formatDecimal(RealNum.toStringDecimal(fstr));
    }

    public static String formatDecimal(BigDecimal dec) {
        return formatDecimal(dec.toPlainString());
    }

    static String formatDecimal(String str) {
        char ch;
        int dot = str.indexOf(46);
        if (dot >= 0) {
            int len = str.length();
            int pos = len;
            do {
                pos--;
                ch = str.charAt(pos);
            } while (ch == '0');
            if (ch != '.') {
                pos++;
            }
            return pos == len ? str : str.substring(0, pos);
        }
        return str;
    }

    @Override // gnu.mapping.OutPort, java.io.PrintWriter
    public void print(Object v) {
        if (v instanceof BigDecimal) {
            v = formatDecimal((BigDecimal) v);
        } else if ((v instanceof Double) || (v instanceof DFloNum)) {
            v = formatDouble(((Number) v).doubleValue());
        } else if (v instanceof Float) {
            v = formatFloat(((Float) v).floatValue());
        }
        write(v == null ? "(null)" : v.toString());
    }

    @Override // gnu.lists.PrintConsumer, gnu.lists.Consumer
    public void writeObject(Object v) {
        if (v instanceof SeqPosition) {
            this.bout.clearWordEnd();
            SeqPosition pos = (SeqPosition) v;
            pos.sequence.consumeNext(pos.ipos, this);
            if (pos.sequence instanceof NodeTree) {
                this.prev = 45;
                return;
            }
            return;
        }
        if ((v instanceof Consumable) && !(v instanceof UnescapedData)) {
            ((Consumable) v).consume(this);
            return;
        }
        if (v instanceof Keyword) {
            startAttribute(((Keyword) v).getName());
            this.prev = -6;
            return;
        }
        closeTag();
        if (v instanceof UnescapedData) {
            this.bout.clearWordEnd();
            this.bout.write(((UnescapedData) v).getData());
            this.prev = 45;
        } else {
            if (v instanceof Char) {
                Char.print(((Char) v).intValue(), this);
                return;
            }
            startWord();
            this.prev = 32;
            print(v);
            writeWordEnd();
            this.prev = -2;
        }
    }

    @Override // gnu.lists.PrintConsumer, gnu.lists.Consumer
    public boolean ignoring() {
        return false;
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(String str, int start, int length) {
        int i;
        if (length > 0) {
            closeTag();
            int limit = start + length;
            int count = 0;
            while (start < limit) {
                int start2 = start + 1;
                char c = str.charAt(start);
                if (mustHexEscape(c) || ((i = this.inComment) <= 0 ? c == '<' || c == '>' || c == '&' || (this.inAttribute && (c == '\"' || c < ' ')) : c == '-' || i == 2)) {
                    if (count > 0) {
                        this.bout.write(str, (start2 - 1) - count, count);
                    }
                    write(c);
                    count = 0;
                } else {
                    count++;
                }
                start = start2;
            }
            if (count > 0) {
                this.bout.write(str, limit - count, count);
            }
        }
        this.prev = 45;
    }

    @Override // java.io.PrintWriter, java.io.Writer, gnu.lists.Consumer
    public void write(char[] buf, int off, int len) {
        int i;
        if (len > 0) {
            closeTag();
            int limit = off + len;
            int count = 0;
            while (off < limit) {
                int off2 = off + 1;
                char c = buf[off];
                if (mustHexEscape(c) || ((i = this.inComment) <= 0 ? c == '<' || c == '>' || c == '&' || (this.inAttribute && (c == '\"' || c < ' ')) : c == '-' || i == 2)) {
                    if (count > 0) {
                        this.bout.write(buf, (off2 - 1) - count, count);
                    }
                    write(c);
                    count = 0;
                } else {
                    count++;
                }
                off = off2;
            }
            if (count > 0) {
                this.bout.write(buf, limit - count, count);
            }
        }
        this.prev = 45;
    }

    @Override // gnu.lists.PositionConsumer
    public void writePosition(AbstractSequence seq, int ipos) {
        seq.consumeNext(ipos, this);
    }

    public void writeBaseUri(Object uri) {
    }

    public void beginComment() {
        int i;
        closeTag();
        int i2 = this.printIndent;
        if (i2 >= 0 && ((i = this.prev) == -3 || i == -4 || i == -5)) {
            writeBreak(i2 > 0 ? 82 : 78);
        }
        this.bout.write("<!--");
        this.inComment = 1;
    }

    public void endComment() {
        this.bout.write("-->");
        this.prev = -5;
        this.inComment = 0;
    }

    public void writeComment(String chars) {
        beginComment();
        write(chars);
        endComment();
    }

    @Override // gnu.lists.XConsumer
    public void writeComment(char[] chars, int offset, int length) {
        beginComment();
        write(chars, offset, length);
        endComment();
    }

    @Override // gnu.lists.XConsumer
    public void writeCDATA(char[] chars, int offset, int length) {
        if (this.canonicalizeCDATA) {
            write(chars, offset, length);
            return;
        }
        closeTag();
        this.bout.write("<![CDATA[");
        int limit = offset + length;
        int i = offset;
        while (i < limit - 2) {
            if (chars[i] == ']' && chars[i + 1] == ']' && chars[i + 2] == '>') {
                if (i > offset) {
                    this.bout.write(chars, offset, i - offset);
                }
                print("]]]><![CDATA[]>");
                offset = i + 3;
                length = limit - offset;
                i += 2;
            }
            i++;
        }
        this.bout.write(chars, offset, length);
        this.bout.write("]]>");
        this.prev = 62;
    }

    @Override // gnu.lists.XConsumer
    public void writeProcessingInstruction(String target, char[] content, int offset, int length) {
        if ("xml".equals(target)) {
            this.needXMLdecl = false;
        }
        closeTag();
        this.bout.write("<?");
        print(target);
        print(' ');
        this.bout.write(content, offset, length);
        this.bout.write("?>");
        this.prev = -7;
    }

    @Override // gnu.lists.PositionConsumer
    public void consume(SeqPosition position) {
        position.sequence.consumeNext(position.ipos, this);
    }

    public void error(String msg, String code) {
        throw new RuntimeException("serialization error: " + msg + " [" + code + ']');
    }
}
