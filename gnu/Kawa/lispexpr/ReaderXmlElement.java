package gnu.kawa.lispexpr;

import gnu.expr.Compilation;
import gnu.expr.PrimProcedure;
import gnu.expr.Special;
import gnu.kawa.xml.CommentConstructor;
import gnu.kawa.xml.MakeCDATA;
import gnu.kawa.xml.MakeProcInst;
import gnu.kawa.xml.MakeText;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import gnu.xml.XName;
import java.io.IOException;

/* loaded from: classes.dex */
public class ReaderXmlElement extends ReadTableEntry {
    static final String DEFAULT_ELEMENT_NAMESPACE = "[default-element-namespace]";

    @Override // gnu.kawa.lispexpr.ReadTableEntry
    public Object read(Lexer in, int ch, int count) throws IOException, SyntaxException {
        LispReader reader = (LispReader) in;
        return readXMLConstructor(reader, reader.readUnicodeChar(), false);
    }

    public static Pair quote(Object obj) {
        Symbol q = Namespace.EmptyNamespace.getSymbol(LispLanguage.quote_sym);
        return LList.list2(q, obj);
    }

    public static Object readQNameExpression(LispReader reader, int ch, boolean forElement) throws IOException, SyntaxException {
        reader.getName();
        int line = reader.getLineNumber() + 1;
        int column = reader.getColumnNumber();
        reader.tokenBufferLength = 0;
        if (XName.isNameStart(ch)) {
            int colon = -1;
            while (true) {
                reader.tokenBufferAppend(ch);
                ch = reader.readUnicodeChar();
                if (ch == 58 && colon < 0) {
                    colon = reader.tokenBufferLength;
                } else if (!XName.isNamePart(ch)) {
                    break;
                }
            }
            reader.unread(ch);
            if (colon >= 0 || forElement) {
                int llen = (reader.tokenBufferLength - colon) - 1;
                String local = new String(reader.tokenBuffer, colon + 1, llen).intern();
                String prefix = colon < 0 ? DEFAULT_ELEMENT_NAMESPACE : new String(reader.tokenBuffer, 0, colon).intern();
                Symbol psym = Namespace.EmptyNamespace.getSymbol(prefix);
                return new Pair(ResolveNamespace.resolveQName, PairWithPosition.make(psym, new Pair(local, LList.Empty), reader.getName(), line, column));
            }
            Symbol symbol = Namespace.getDefaultSymbol(reader.tokenBufferString().intern());
            return quote(symbol);
        }
        if (ch == 123 || ch == 40) {
            return readEscapedExpression(reader, ch);
        }
        reader.error("missing element name");
        return null;
    }

    static Object readEscapedExpression(LispReader reader, int ch) throws IOException, SyntaxException {
        if (ch == 40) {
            reader.unread(ch);
            return reader.readObject();
        }
        LineBufferedReader port = reader.getPort();
        char saveReadState = reader.pushNesting('{');
        int startLine = port.getLineNumber();
        int startColumn = port.getColumnNumber();
        try {
            Object valuesMake = new PrimProcedure(Compilation.typeValues.getDeclaredMethod("values", 1));
            Pair list = reader.makePair(valuesMake, startLine, startColumn);
            Pair last = list;
            ReadTable readTable = ReadTable.getCurrent();
            while (true) {
                int line = port.getLineNumber();
                int column = port.getColumnNumber();
                int ch2 = port.read();
                if (ch2 == 125) {
                    break;
                }
                if (ch2 < 0) {
                    reader.eofError("unexpected EOF in list starting here", startLine + 1, startColumn);
                }
                ReadTableEntry entry = readTable.lookup(ch2);
                Object value = reader.readValues(ch2, entry, readTable);
                if (value != Values.empty) {
                    Pair pair = reader.makePair(reader.handlePostfix(value, readTable, line, column), line, column);
                    reader.setCdr(last, pair);
                    last = pair;
                }
            }
            reader.tokenBufferLength = 0;
            return last == list.getCdr() ? last.getCar() : list;
        } finally {
            reader.popNesting(saveReadState);
        }
    }

    static Object readXMLConstructor(LispReader reader, int next, boolean inElementContent) throws IOException, SyntaxException {
        int startLine = reader.getLineNumber() + 1;
        int startColumn = reader.getColumnNumber() - 2;
        if (next == 33) {
            int next2 = reader.read();
            if (next2 == 45) {
                int peek = reader.peek();
                next2 = peek;
                if (peek == 45) {
                    reader.skip();
                    if (!reader.readDelimited("-->")) {
                        reader.error('f', reader.getName(), startLine, startColumn, "unexpected end-of-file in XML comment starting here - expected \"-->\"");
                    }
                    String str = reader.tokenBufferString();
                    Object exp = LList.list2(CommentConstructor.commentConstructor, str);
                    return exp;
                }
            }
            if (next2 == 91) {
                int read = reader.read();
                next2 = read;
                if (read == 67) {
                    int read2 = reader.read();
                    next2 = read2;
                    if (read2 == 68) {
                        int read3 = reader.read();
                        next2 = read3;
                        if (read3 == 65) {
                            int read4 = reader.read();
                            next2 = read4;
                            if (read4 == 84) {
                                int read5 = reader.read();
                                next2 = read5;
                                if (read5 == 65) {
                                    int read6 = reader.read();
                                    next2 = read6;
                                    if (read6 == 91) {
                                        if (!reader.readDelimited("]]>")) {
                                            reader.error('f', reader.getName(), startLine, startColumn, "unexpected end-of-file in CDATA starting here - expected \"]]>\"");
                                        }
                                        String str2 = reader.tokenBufferString();
                                        Object exp2 = LList.list2(MakeCDATA.makeCDATA, str2);
                                        return exp2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            reader.error('f', reader.getName(), startLine, startColumn, "'<!' must be followed by '--' or '[CDATA['");
            while (next2 >= 0 && next2 != 62 && next2 != 10 && next2 != 13) {
                next2 = reader.read();
            }
            return null;
        }
        if (next == 63) {
            int next3 = reader.readUnicodeChar();
            if (next3 < 0 || !XName.isNameStart(next3)) {
                reader.error("missing target after '<?'");
            }
            do {
                reader.tokenBufferAppend(next3);
                next3 = reader.readUnicodeChar();
            } while (XName.isNamePart(next3));
            String target = reader.tokenBufferString();
            int nspaces = 0;
            while (next3 >= 0 && Character.isWhitespace(next3)) {
                nspaces++;
                next3 = reader.read();
            }
            reader.unread(next3);
            char saveReadState = reader.pushNesting('?');
            try {
                if (!reader.readDelimited("?>")) {
                    reader.error('f', reader.getName(), startLine, startColumn, "unexpected end-of-file looking for \"?>\"");
                }
                if (nspaces == 0 && reader.tokenBufferLength > 0) {
                    reader.error("target must be followed by space or '?>'");
                }
                String content = reader.tokenBufferString();
                Object exp3 = LList.list3(MakeProcInst.makeProcInst, target, content);
                return exp3;
            } finally {
                reader.popNesting(saveReadState);
            }
        }
        Object exp4 = readElementConstructor(reader, next);
        return exp4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:93:0x0134, code lost:
    
        r9 = 62;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object readElementConstructor(gnu.kawa.lispexpr.LispReader r23, int r24) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            Method dump skipped, instructions count: 539
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderXmlElement.readElementConstructor(gnu.kawa.lispexpr.LispReader, int):java.lang.Object");
    }

    public static Pair readContent(LispReader reader, char delimiter, Pair resultTail) throws IOException, SyntaxException {
        reader.tokenBufferLength = 0;
        boolean prevWasEnclosed = false;
        while (true) {
            Object item = null;
            String text = null;
            int line = reader.getLineNumber() + 1;
            int column = reader.getColumnNumber();
            int next = reader.read();
            if (next < 0) {
                reader.error("unexpected end-of-file");
                item = Special.eof;
            } else if (next == delimiter) {
                if (delimiter == '<') {
                    if (reader.tokenBufferLength > 0) {
                        text = reader.tokenBufferString();
                        reader.tokenBufferLength = 0;
                    }
                    int next2 = reader.read();
                    if (next2 == 47) {
                        item = Special.eof;
                    } else {
                        item = readXMLConstructor(reader, next2, true);
                    }
                } else if (reader.checkNext(delimiter)) {
                    reader.tokenBufferAppend(delimiter);
                } else {
                    item = Special.eof;
                }
                prevWasEnclosed = false;
            } else if (next == 38) {
                int next3 = reader.read();
                if (next3 == 35) {
                    readCharRef(reader);
                } else if (next3 == 40 || next3 == 123) {
                    if (reader.tokenBufferLength > 0 || prevWasEnclosed) {
                        text = reader.tokenBufferString();
                    }
                    reader.tokenBufferLength = 0;
                    item = readEscapedExpression(reader, next3);
                } else {
                    item = readEntity(reader, next3);
                    if (prevWasEnclosed && reader.tokenBufferLength == 0) {
                        text = "";
                    }
                }
                prevWasEnclosed = true;
            } else {
                if (delimiter != '<' && (next == 9 || next == 10 || next == 13)) {
                    next = 32;
                }
                if (next == 60) {
                    reader.error('e', "'<' must be quoted in a direct attribute value");
                }
                reader.tokenBufferAppend((char) next);
            }
            if (item != null && reader.tokenBufferLength > 0) {
                text = reader.tokenBufferString();
                reader.tokenBufferLength = 0;
            }
            if (text != null) {
                Pair tnode = Pair.list2(MakeText.makeText, text);
                Pair pair = PairWithPosition.make(tnode, reader.makeNil(), null, -1, -1);
                resultTail.setCdrBackdoor(pair);
                resultTail = pair;
            }
            if (item != Special.eof) {
                if (item != null) {
                    Pair pair2 = PairWithPosition.make(item, reader.makeNil(), null, line, column);
                    resultTail.setCdrBackdoor(pair2);
                    resultTail = pair2;
                }
            } else {
                return resultTail;
            }
        }
    }

    static void readCharRef(LispReader reader) throws IOException, SyntaxException {
        int base;
        int next = reader.read();
        if (next == 120) {
            base = 16;
            next = reader.read();
        } else {
            base = 10;
        }
        int value = 0;
        while (next >= 0) {
            char ch = (char) next;
            int digit = Character.digit(ch, base);
            if (digit < 0 || value >= 134217728) {
                break;
            }
            value = (value * base) + digit;
            next = reader.read();
        }
        if (next != 59) {
            reader.unread(next);
            reader.error("invalid character reference");
        } else if ((value > 0 && value <= 55295) || ((value >= 57344 && value <= 65533) || (value >= 65536 && value <= 1114111))) {
            reader.tokenBufferAppend(value);
        } else {
            reader.error("invalid character value " + value);
        }
    }

    static Object readEntity(LispReader reader, int next) throws IOException, SyntaxException {
        int saveLength = reader.tokenBufferLength;
        while (next >= 0) {
            char ch = (char) next;
            if (!XName.isNamePart(ch)) {
                break;
            }
            reader.tokenBufferAppend(ch);
            next = reader.read();
        }
        if (next != 59) {
            reader.unread(next);
            reader.error("invalid entity reference");
            return "?";
        }
        String ref = new String(reader.tokenBuffer, saveLength, reader.tokenBufferLength - saveLength);
        reader.tokenBufferLength = saveLength;
        namedEntity(reader, ref);
        return null;
    }

    public static void namedEntity(LispReader reader, String name) {
        String name2 = name.intern();
        char ch = '?';
        if (name2 == "lt") {
            ch = '<';
        } else if (name2 == "gt") {
            ch = '>';
        } else if (name2 == "amp") {
            ch = '&';
        } else if (name2 == "quot") {
            ch = '\"';
        } else if (name2 == "apos") {
            ch = '\'';
        } else {
            reader.error("unknown enity reference: '" + name2 + "'");
        }
        reader.tokenBufferAppend(ch);
    }

    static int skipSpace(LispReader reader, int ch) throws IOException, SyntaxException {
        while (ch >= 0 && Character.isWhitespace(ch)) {
            ch = reader.readUnicodeChar();
        }
        return ch;
    }
}
