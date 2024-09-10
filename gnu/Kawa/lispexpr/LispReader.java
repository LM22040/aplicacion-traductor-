package gnu.kawa.lispexpr;

import gnu.bytecode.Access;
import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;

/* loaded from: classes.dex */
public class LispReader extends Lexer {
    static final int SCM_COMPLEX = 1;
    public static final int SCM_NUMBERS = 1;
    public static final char TOKEN_ESCAPE_CHAR = 65535;
    protected boolean seenEscapes;
    GeneralHashTable<Integer, Object> sharedStructureTable;

    public LispReader(LineBufferedReader port) {
        super(port);
    }

    public LispReader(LineBufferedReader port, SourceMessages messages) {
        super(port, messages);
    }

    public final void readNestedComment(char c1, char c2) throws IOException, SyntaxException {
        int commentNesting = 1;
        int startLine = this.port.getLineNumber();
        int startColumn = this.port.getColumnNumber();
        do {
            int c = read();
            if (c == 124) {
                c = read();
                if (c == c1) {
                    commentNesting--;
                }
            } else if (c == c1 && (c = read()) == c2) {
                commentNesting++;
            }
            if (c < 0) {
                eofError("unexpected end-of-file in " + c1 + c2 + " comment starting here", startLine + 1, startColumn - 1);
                return;
            }
        } while (commentNesting > 0);
    }

    static char getReadCase() {
        try {
            String read_case_string = Environment.getCurrent().get("symbol-read-case", "P").toString();
            char read_case = read_case_string.charAt(0);
            if (read_case != 'P') {
                if (read_case == 'u') {
                    return 'U';
                }
                if (read_case == 'd' || read_case == 'l' || read_case == 'L') {
                    return 'D';
                }
                if (read_case == 'i') {
                    return Access.INNERCLASS_CONTEXT;
                }
                return read_case;
            }
            return read_case;
        } catch (Exception e) {
            return 'P';
        }
    }

    public Object readValues(int ch, ReadTable rtable) throws IOException, SyntaxException {
        return readValues(ch, rtable.lookup(ch), rtable);
    }

    public Object readValues(int ch, ReadTableEntry entry, ReadTable rtable) throws IOException, SyntaxException {
        int startPos = this.tokenBufferLength;
        this.seenEscapes = false;
        int kind = entry.getKind();
        switch (kind) {
            case 0:
                String err = "invalid character #\\" + ((char) ch);
                if (this.interactive) {
                    fatal(err);
                } else {
                    error(err);
                }
                return Values.empty;
            case 1:
                return Values.empty;
            case 2:
            case 3:
            case 4:
            default:
                return readAndHandleToken(ch, startPos, rtable);
            case 5:
            case 6:
                return entry.read(this, ch, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object readAndHandleToken(int ch, int startPos, ReadTable rtable) throws IOException, SyntaxException {
        Object value;
        readToken(ch, getReadCase(), rtable);
        int endPos = this.tokenBufferLength;
        if (!this.seenEscapes && (value = parseNumber(this.tokenBuffer, startPos, endPos - startPos, (char) 0, 0, 1)) != null && !(value instanceof String)) {
            return value;
        }
        char readCase = getReadCase();
        char c = TOKEN_ESCAPE_CHAR;
        if (readCase == 'I') {
            int upperCount = 0;
            int lowerCount = 0;
            int i = startPos;
            while (i < endPos) {
                char ci = this.tokenBuffer[i];
                if (ci == 65535) {
                    i++;
                } else if (Character.isLowerCase(ci)) {
                    lowerCount++;
                } else if (Character.isUpperCase(ci)) {
                    upperCount++;
                }
                i++;
            }
            if (lowerCount == 0) {
                readCase = 'D';
            } else if (upperCount == 0) {
                readCase = 'U';
            } else {
                readCase = 'P';
            }
        }
        int upperCount2 = startPos + 2;
        boolean handleUri = endPos >= upperCount2 && this.tokenBuffer[endPos + (-1)] == '}' && this.tokenBuffer[endPos + (-2)] != 65535 && peek() == 58;
        int packageMarker = -1;
        int lbrace = -1;
        int rbrace = -1;
        int braceNesting = 0;
        int j = startPos;
        int i2 = startPos;
        while (i2 < endPos) {
            char ci2 = this.tokenBuffer[i2];
            if (ci2 == c) {
                i2++;
                if (i2 < endPos) {
                    this.tokenBuffer[j] = this.tokenBuffer[i2];
                    j++;
                }
            } else {
                if (handleUri) {
                    if (ci2 == '{') {
                        if (lbrace < 0) {
                            lbrace = j;
                        } else if (braceNesting == 0) {
                        }
                        braceNesting++;
                    } else if (ci2 == '}' && braceNesting - 1 >= 0 && braceNesting == 0 && rbrace < 0) {
                        rbrace = j;
                    }
                }
                if (ci2 == ':') {
                    packageMarker = packageMarker >= 0 ? -1 : j;
                } else if (readCase == 'U') {
                    ci2 = Character.toUpperCase(ci2);
                } else if (readCase == 'D') {
                    ci2 = Character.toLowerCase(ci2);
                }
                this.tokenBuffer[j] = ci2;
                j++;
            }
            i2++;
            c = TOKEN_ESCAPE_CHAR;
        }
        int endPos2 = j;
        int len = endPos2 - startPos;
        if (lbrace >= 0 && rbrace > lbrace) {
            String prefix = lbrace > 0 ? new String(this.tokenBuffer, startPos, lbrace - startPos) : null;
            int lbrace2 = lbrace + 1;
            String uri = new String(this.tokenBuffer, lbrace2, rbrace - lbrace2);
            read();
            int ch2 = read();
            Object rightOperand = readValues(ch2, rtable.lookup(ch2), rtable);
            if (!(rightOperand instanceof SimpleSymbol)) {
                error("expected identifier in symbol after '{URI}:'");
            }
            return Symbol.valueOf(rightOperand.toString(), uri, prefix);
        }
        if (rtable.initialColonIsKeyword && packageMarker == startPos && len > 1) {
            int startPos2 = 1 + startPos;
            String str = new String(this.tokenBuffer, startPos2, endPos2 - startPos2);
            return Keyword.make(str.intern());
        }
        if (rtable.finalColonIsKeyword && packageMarker == endPos2 - 1 && (len > 1 || this.seenEscapes)) {
            String str2 = new String(this.tokenBuffer, startPos, len - 1);
            return Keyword.make(str2.intern());
        }
        return rtable.makeSymbol(new String(this.tokenBuffer, startPos, len));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x002f, code lost:
    
        unread(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:?, code lost:
    
        return;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:59:0x008c. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void readToken(int r8, char r9, gnu.kawa.lispexpr.ReadTable r10) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            if (r8 >= 0) goto Lb
            if (r0 == 0) goto L47
            java.lang.String r2 = "unexpected EOF between escapes"
            r7.eofError(r2)
        Lb:
            gnu.kawa.lispexpr.ReadTableEntry r2 = r10.lookup(r8)
            int r3 = r2.getKind()
            r4 = 65535(0xffff, float:9.1834E-41)
            if (r3 != 0) goto L33
            if (r0 == 0) goto L22
            r7.tokenBufferAppend(r4)
            r7.tokenBufferAppend(r8)
            goto Laa
        L22:
            r4 = 125(0x7d, float:1.75E-43)
            if (r8 != r4) goto L2f
            int r1 = r1 + (-1)
            if (r1 < 0) goto L2f
            r7.tokenBufferAppend(r8)
            goto Laa
        L2f:
            r7.unread(r8)
            goto L47
        L33:
            char r5 = r10.postfixLookupOperator
            if (r8 != r5) goto L4f
            if (r0 != 0) goto L4f
            gnu.text.LineBufferedReader r5 = r7.port
            int r5 = r5.peek()
            char r6 = r10.postfixLookupOperator
            if (r5 != r6) goto L48
            r7.unread(r8)
        L47:
            return
        L48:
            boolean r6 = r7.validPostfixLookupStart(r5, r10)
            if (r6 == 0) goto L4f
            r3 = 5
        L4f:
            r5 = 3
            r6 = 1
            if (r3 != r5) goto L77
            int r8 = r7.read()
            if (r8 >= 0) goto L5e
            java.lang.String r5 = "unexpected EOF after single escape"
            r7.eofError(r5)
        L5e:
            boolean r5 = r10.hexEscapeAfterBackslash
            if (r5 == 0) goto L6e
            r5 = 120(0x78, float:1.68E-43)
            if (r8 == r5) goto L6a
            r5 = 88
            if (r8 != r5) goto L6e
        L6a:
            int r8 = r7.readHexEscape()
        L6e:
            r7.tokenBufferAppend(r4)
            r7.tokenBufferAppend(r8)
            r7.seenEscapes = r6
            goto Laa
        L77:
            r5 = 4
            if (r3 != r5) goto L83
            if (r0 != 0) goto L7e
            r4 = 1
            goto L7f
        L7e:
            r4 = 0
        L7f:
            r0 = r4
            r7.seenEscapes = r6
            goto Laa
        L83:
            if (r0 == 0) goto L8c
            r7.tokenBufferAppend(r4)
            r7.tokenBufferAppend(r8)
            goto Laa
        L8c:
            switch(r3) {
                case 1: goto La6;
                case 2: goto L98;
                case 3: goto L8f;
                case 4: goto L94;
                case 5: goto L90;
                case 6: goto La2;
                default: goto L8f;
            }
        L8f:
            goto Laa
        L90:
            r7.unread(r8)
            return
        L94:
            r0 = 1
            r7.seenEscapes = r6
            goto Laa
        L98:
            r4 = 123(0x7b, float:1.72E-43)
            if (r8 != r4) goto La2
            gnu.kawa.lispexpr.ReadTableEntry r4 = gnu.kawa.lispexpr.ReadTableEntry.brace
            if (r2 != r4) goto La2
            int r1 = r1 + 1
        La2:
            r7.tokenBufferAppend(r8)
            goto Laa
        La6:
            r7.unread(r8)
            return
        Laa:
            int r8 = r7.read()
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readToken(int, char, gnu.kawa.lispexpr.ReadTable):void");
    }

    public Object readObject() throws IOException, SyntaxException {
        int line;
        int column;
        Object value;
        char saveReadState = ((InPort) this.port).readState;
        int startPos = this.tokenBufferLength;
        ((InPort) this.port).readState = ' ';
        try {
            ReadTable rtable = ReadTable.getCurrent();
            do {
                line = this.port.getLineNumber();
                column = this.port.getColumnNumber();
                int ch = this.port.read();
                if (ch < 0) {
                    return Sequence.eofValue;
                }
                value = readValues(ch, rtable);
            } while (value == Values.empty);
            return handlePostfix(value, rtable, line, column);
        } finally {
            this.tokenBufferLength = startPos;
            ((InPort) this.port).readState = saveReadState;
        }
    }

    protected boolean validPostfixLookupStart(int ch, ReadTable rtable) throws IOException {
        int kind;
        if (ch < 0 || ch == 58 || ch == rtable.postfixLookupOperator) {
            return false;
        }
        return ch == 44 || (kind = rtable.lookup(ch).getKind()) == 2 || kind == 6 || kind == 4 || kind == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object handlePostfix(Object value, ReadTable rtable, int line, int column) throws IOException, SyntaxException {
        if (value == QuoteExp.voidExp) {
            value = Values.empty;
        }
        while (true) {
            int ch = this.port.peek();
            if (ch < 0 || ch != rtable.postfixLookupOperator) {
                break;
            }
            this.port.read();
            int ch2 = this.port.peek();
            if (!validPostfixLookupStart(ch2, rtable)) {
                unread();
                break;
            }
            int ch3 = this.port.read();
            Object rightOperand = readValues(ch3, rtable.lookup(ch3), rtable);
            value = PairWithPosition.make(LispLanguage.lookup_sym, LList.list2(value, LList.list2(rtable.makeSymbol(LispLanguage.quasiquote_sym), rightOperand)), this.port.getName(), line + 1, column + 1);
        }
        return value;
    }

    private boolean isPotentialNumber(char[] buffer, int start, int end) {
        int sawDigits = 0;
        for (int i = start; i < end; i++) {
            char ch = buffer[i];
            if (Character.isDigit(ch)) {
                sawDigits++;
            } else if (ch == '-' || ch == '+') {
                if (i + 1 == end) {
                    return false;
                }
            } else {
                if (ch == '#') {
                    return true;
                }
                if (Character.isLetter(ch) || ch == '/' || ch == '_' || ch == '^') {
                    if (i == start) {
                        return false;
                    }
                } else if (ch != '.') {
                    return false;
                }
            }
        }
        return sawDigits > 0;
    }

    public static Object parseNumber(CharSequence str, int radix) {
        char[] buf;
        if (str instanceof FString) {
            buf = ((FString) str).data;
        } else {
            buf = str.toString().toCharArray();
        }
        int len = str.length();
        return parseNumber(buf, 0, len, (char) 0, radix, 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0236, code lost:
    
        if (r1 >= 0) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0238, code lost:
    
        if (r17 == false) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x023a, code lost:
    
        r2 = r4 + 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x023c, code lost:
    
        if (r2 >= r8) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0244, code lost:
    
        if (r26[r4 + 3] != '.') goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x024a, code lost:
    
        if (r26[r2] != '0') goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x024c, code lost:
    
        r2 = r26[r4];
        r10 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0252, code lost:
    
        if (r2 != 'i') goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0258, code lost:
    
        if (r26[r4 + 1] != 'n') goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0260, code lost:
    
        if (r26[r4 + 2] != 'f') goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0262, code lost:
    
        r10 = 'i';
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0277, code lost:
    
        if (r10 != 0) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0279, code lost:
    
        return "no digits";
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x027a, code lost:
    
        r9 = r4 + 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0283, code lost:
    
        if (r13 == 'i') goto L204;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0287, code lost:
    
        if (r13 == 'I') goto L204;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0289, code lost:
    
        r15 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x028e, code lost:
    
        r18 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0296, code lost:
    
        if (r10 == 0) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x029b, code lost:
    
        if (r10 != 'i') goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x029d, code lost:
    
        r0 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x02a4, code lost:
    
        if (r21 == false) goto L214;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02a6, code lost:
    
        r0 = -r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x02a7, code lost:
    
        r2 = new gnu.math.DFloNum(r0);
        r0 = 0;
        r10 = 'e';
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x036a, code lost:
    
        if (r13 == r10) goto L272;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x036e, code lost:
    
        if (r13 != 'E') goto L271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0371, code lost:
    
        r6 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0378, code lost:
    
        if (r9 >= r8) goto L329;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x037a, code lost:
    
        r1 = r9 + 1;
        r0 = r26[r9];
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0380, code lost:
    
        if (r0 != '@') goto L291;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0382, code lost:
    
        r0 = parseNumber(r26, r1, r8 - r1, r13, 10, r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0391, code lost:
    
        if ((r0 instanceof java.lang.String) == false) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0393, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0396, code lost:
    
        if ((r0 instanceof gnu.math.RealNum) != false) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0398, code lost:
    
        return "invalid complex polar constant";
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x039b, code lost:
    
        r0 = (gnu.math.RealNum) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x03a1, code lost:
    
        if (r6.isZero() == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x03a7, code lost:
    
        if (r0.isExact() != false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x03b0, code lost:
    
        return new gnu.math.DFloNum(0.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x03b5, code lost:
    
        return gnu.math.Complex.polar(r6, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x03b8, code lost:
    
        if (r0 == '-') goto L316;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x03bc, code lost:
    
        if (r0 != '+') goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x03bf, code lost:
    
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x03c4, code lost:
    
        if (java.lang.Character.isLetter(r0) != false) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x03c9, code lost:
    
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x03cb, code lost:
    
        if (r1 != r8) goto L315;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x03ee, code lost:
    
        r0 = r26[r1];
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x03cf, code lost:
    
        if (r11 != 1) goto L313;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x03d1, code lost:
    
        r0 = r26[r1 - 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x03d7, code lost:
    
        if (r0 == 'i') goto L308;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x03db, code lost:
    
        if (r0 != 'I') goto L376;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:?, code lost:
    
        return "excess junk after number";
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x03dd, code lost:
    
        if (r1 >= r8) goto L311;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x03df, code lost:
    
        return "junk after imaginary suffix 'i'";
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x03ea, code lost:
    
        return gnu.math.Complex.make(gnu.math.IntNum.zero(), r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x03eb, code lost:
    
        return "excess junk after number";
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x03c6, code lost:
    
        r1 = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x03fa, code lost:
    
        r1 = r1 - 1;
        r0 = parseNumber(r26, r1, r8 - r1, r13, 10, r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x040b, code lost:
    
        if ((r0 instanceof java.lang.String) == false) goto L319;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x040d, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0410, code lost:
    
        if ((r0 instanceof gnu.math.Complex) != false) goto L323;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x042b, code lost:
    
        return "invalid numeric constant (" + r0 + ")";
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x042c, code lost:
    
        r0 = (gnu.math.Complex) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0436, code lost:
    
        if (r0.re().isZero() != false) goto L327;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0438, code lost:
    
        return "invalid numeric constant";
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0443, code lost:
    
        return gnu.math.Complex.make(r6, r0.im());
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0446, code lost:
    
        if ((r6 instanceof gnu.math.DFloNum) == false) goto L342;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0448, code lost:
    
        if (r0 <= 0) goto L342;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x044a, code lost:
    
        if (r0 == r10) goto L342;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x044c, code lost:
    
        r1 = r6.doubleValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0450, code lost:
    
        switch(r0) {
            case 100: goto L340;
            case 102: goto L338;
            case 108: goto L336;
            case 115: goto L338;
            default: goto L342;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0458, code lost:
    
        return java.math.BigDecimal.valueOf(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x045e, code lost:
    
        return java.lang.Float.valueOf((float) r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0463, code lost:
    
        return java.lang.Double.valueOf(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0464, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0373, code lost:
    
        r6 = r2.toExact();
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x02a0, code lost:
    
        r0 = Double.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x02b0, code lost:
    
        if (r3 >= 0) goto L249;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x02b2, code lost:
    
        if (r0 < 0) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x02b4, code lost:
    
        r10 = 'e';
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x031b, code lost:
    
        if (r1 <= r0) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x031d, code lost:
    
        if (r0 < 0) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x031f, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0320, code lost:
    
        if (r12 == null) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0322, code lost:
    
        return "floating-point number after fraction symbol '/'";
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0325, code lost:
    
        r0 = new java.lang.String(r26, r1, r9 - r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x032c, code lost:
    
        if (r3 < 0) goto L262;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x032e, code lost:
    
        r2 = java.lang.Character.toLowerCase(r26[r3]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0334, code lost:
    
        if (r2 == r10) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x0336, code lost:
    
        r3 = r3 - r1;
        r0 = r0.substring(0, r3) + r10 + r0.substring(r3 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x035c, code lost:
    
        r0 = gnu.lists.Convert.parseDouble(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x0362, code lost:
    
        if (r21 == false) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x0364, code lost:
    
        r0 = -r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x0365, code lost:
    
        r3 = new gnu.math.DFloNum(r0);
        r0 = r2;
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x035a, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x02b8, code lost:
    
        r10 = 'e';
        r0 = valueOf(r26, r1, r9 - r1, r11, r21, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x02c5, code lost:
    
        if (r12 != null) goto L221;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x02c7, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x02f6, code lost:
    
        if (r15 == false) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x02fc, code lost:
    
        if (r2.isExact() == false) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0300, code lost:
    
        if (r16 == false) goto L246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x0306, code lost:
    
        if (r2.isZero() == false) goto L246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x0308, code lost:
    
        r1 = -0.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x030f, code lost:
    
        r2 = new gnu.math.DFloNum(r1);
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x030b, code lost:
    
        r1 = r2.doubleValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x0316, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x02cd, code lost:
    
        if (r0.isZero() == false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x02cf, code lost:
    
        r1 = r12.isZero();
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x02d3, code lost:
    
        if (r15 == false) goto L232;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x02d7, code lost:
    
        if (r1 == false) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x02d9, code lost:
    
        r1 = Double.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x02e2, code lost:
    
        r0 = new gnu.math.DFloNum(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x02ef, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x02dc, code lost:
    
        if (r16 == false) goto L230;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x02de, code lost:
    
        r18 = Double.NEGATIVE_INFINITY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x02e0, code lost:
    
        r1 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x02e6, code lost:
    
        if (r1 == false) goto L235;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x02e8, code lost:
    
        return "0/0 is undefined";
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x02eb, code lost:
    
        r0 = gnu.math.RatNum.make(r12, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x02f1, code lost:
    
        r2 = gnu.math.RatNum.make(r12, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x0319, code lost:
    
        r10 = 'e';
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x028d, code lost:
    
        r15 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0265, code lost:
    
        if (r2 != 'n') goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x026d, code lost:
    
        if (r26[r4 + 1] != 'a') goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x0273, code lost:
    
        if (r26[r4 + 2] != 'n') goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x0276, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x027e, code lost:
    
        r9 = r4;
        r10 = 0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:264:0x0157. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object parseNumber(char[] r26, int r27, int r28, char r29, int r30, int r31) {
        /*
            Method dump skipped, instructions count: 1244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.parseNumber(char[], int, int, char, int, int):java.lang.Object");
    }

    private static IntNum valueOf(char[] buffer, int digits_start, int number_of_digits, int radix, boolean negative, long lvalue) {
        if (number_of_digits + radix <= 28) {
            return IntNum.make(negative ? -lvalue : lvalue);
        }
        return IntNum.valueOf(buffer, digits_start, number_of_digits, radix, negative);
    }

    public int readEscape() throws IOException, SyntaxException {
        int c = read();
        if (c < 0) {
            eofError("unexpected EOF in character literal");
            return -1;
        }
        return readEscape(c);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int readEscape(int r7) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readEscape(int):int");
    }

    public int readHexEscape() throws IOException, SyntaxException {
        int d;
        int c = 0;
        while (true) {
            d = read();
            int v = Character.digit((char) d, 16);
            if (v < 0) {
                break;
            }
            c = (c << 4) + v;
        }
        if (d != 59 && d >= 0) {
            unread(d);
        }
        return c;
    }

    public final Object readObject(int c) throws IOException, SyntaxException {
        unread(c);
        return readObject();
    }

    public Object readCommand() throws IOException, SyntaxException {
        return readObject();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object makeNil() {
        return LList.Empty;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Pair makePair(Object car, int line, int column) {
        return makePair(car, LList.Empty, line, column);
    }

    protected Pair makePair(Object car, Object cdr, int line, int column) {
        String pname = this.port.getName();
        if (pname != null && line >= 0) {
            return PairWithPosition.make(car, cdr, pname, line + 1, column + 1);
        }
        return Pair.make(car, cdr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCdr(Object pair, Object cdr) {
        ((Pair) pair).setCdrBackdoor(cdr);
    }

    public static Object readNumberWithRadix(int previous, LispReader reader, int radix) throws IOException, SyntaxException {
        int startPos = reader.tokenBufferLength - previous;
        reader.readToken(reader.read(), 'P', ReadTable.getCurrent());
        int endPos = reader.tokenBufferLength;
        if (startPos == endPos) {
            reader.error("missing numeric token");
            return IntNum.zero();
        }
        Object result = parseNumber(reader.tokenBuffer, startPos, endPos - startPos, (char) 0, radix, 0);
        if (result instanceof String) {
            reader.error((String) result);
            return IntNum.zero();
        }
        if (result == null) {
            reader.error("invalid numeric constant");
            return IntNum.zero();
        }
        return result;
    }

    public static Object readCharacter(LispReader reader) throws IOException, SyntaxException {
        int i;
        int ch = reader.read();
        if (ch < 0) {
            reader.eofError("unexpected EOF in character literal");
        }
        int startPos = reader.tokenBufferLength;
        reader.tokenBufferAppend(ch);
        reader.readToken(reader.read(), 'D', ReadTable.getCurrent());
        char[] tokenBuffer = reader.tokenBuffer;
        int length = reader.tokenBufferLength - startPos;
        if (length == 1) {
            return Char.make(tokenBuffer[startPos]);
        }
        String name = new String(tokenBuffer, startPos, length);
        int ch2 = Char.nameToChar(name);
        if (ch2 >= 0) {
            return Char.make(ch2);
        }
        char c = tokenBuffer[startPos];
        if (c == 'x' || c == 'X') {
            int value = 0;
            while (i != length) {
                int v = Character.digit(tokenBuffer[startPos + i], 16);
                i = (v >= 0 && (value = (value * 16) + v) <= 1114111) ? i + 1 : 1;
            }
            return Char.make(value);
        }
        int ch3 = Character.digit((int) c, 8);
        if (ch3 >= 0) {
            int value2 = ch3;
            for (int i2 = 1; i2 != length; i2++) {
                int ch4 = Character.digit(tokenBuffer[startPos + i2], 8);
                if (ch4 >= 0) {
                    value2 = (value2 * 8) + ch4;
                }
            }
            return Char.make(value2);
        }
        reader.error("unknown character name: " + name);
        return Char.make(63);
    }

    public static Object readSpecial(LispReader reader) throws IOException, SyntaxException {
        int ch = reader.read();
        if (ch < 0) {
            reader.eofError("unexpected EOF in #! special form");
        }
        if (ch == 47 && reader.getLineNumber() == 0 && reader.getColumnNumber() == 3) {
            ReaderIgnoreRestOfLine.getInstance().read(reader, 35, 1);
            return Values.empty;
        }
        int startPos = reader.tokenBufferLength;
        reader.tokenBufferAppend(ch);
        reader.readToken(reader.read(), 'D', ReadTable.getCurrent());
        int length = reader.tokenBufferLength - startPos;
        String name = new String(reader.tokenBuffer, startPos, length);
        if (name.equals("optional")) {
            return Special.optional;
        }
        if (name.equals("rest")) {
            return Special.rest;
        }
        if (name.equals("key")) {
            return Special.key;
        }
        if (name.equals("eof")) {
            return Special.eof;
        }
        if (name.equals("void")) {
            return QuoteExp.voidExp;
        }
        if (name.equals("default")) {
            return Special.dfault;
        }
        if (name.equals("undefined")) {
            return Special.undefined;
        }
        if (name.equals("abstract")) {
            return Special.abstractSpecial;
        }
        if (name.equals("null")) {
            return null;
        }
        reader.error("unknown named constant #!" + name);
        return null;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x004e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009a A[FALL_THROUGH, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static gnu.lists.SimpleVector readSimpleVector(gnu.kawa.lispexpr.LispReader r6, char r7) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = 0
        L1:
            int r1 = r6.read()
            if (r1 >= 0) goto Lc
            java.lang.String r2 = "unexpected EOF reading uniform vector"
            r6.eofError(r2)
        Lc:
            char r2 = (char) r1
            r3 = 10
            int r2 = java.lang.Character.digit(r2, r3)
            if (r2 >= 0) goto L9b
        L16:
            r2 = 8
            r3 = 32
            r4 = 0
            if (r0 == r2) goto L27
            r2 = 16
            if (r0 == r2) goto L27
            if (r0 == r3) goto L27
            r2 = 64
            if (r0 != r2) goto L31
        L27:
            r2 = 70
            if (r7 != r2) goto L2d
            if (r0 < r3) goto L31
        L2d:
            r2 = 40
            if (r1 == r2) goto L37
        L31:
            java.lang.String r2 = "invalid uniform vector syntax"
            r6.error(r2)
            return r4
        L37:
            r3 = -1
            r5 = 41
            java.lang.Object r2 = gnu.kawa.lispexpr.ReaderParens.readList(r6, r2, r3, r5)
            r3 = 0
            int r3 = gnu.lists.LList.listLength(r2, r3)
            if (r3 >= 0) goto L4b
            java.lang.String r5 = "invalid elements in uniform vector syntax"
            r6.error(r5)
            return r4
        L4b:
            r5 = r2
            gnu.lists.Sequence r5 = (gnu.lists.Sequence) r5
            switch(r7) {
                case 70: goto L52;
                case 83: goto L62;
                case 85: goto L7e;
                default: goto L51;
            }
        L51:
            goto L9a
        L52:
            switch(r0) {
                case 32: goto L5c;
                case 64: goto L56;
                default: goto L55;
            }
        L55:
            goto L62
        L56:
            gnu.lists.F64Vector r4 = new gnu.lists.F64Vector
            r4.<init>(r5)
            return r4
        L5c:
            gnu.lists.F32Vector r4 = new gnu.lists.F32Vector
            r4.<init>(r5)
            return r4
        L62:
            switch(r0) {
                case 8: goto L78;
                case 16: goto L72;
                case 32: goto L6c;
                case 64: goto L66;
                default: goto L65;
            }
        L65:
            goto L7e
        L66:
            gnu.lists.S64Vector r4 = new gnu.lists.S64Vector
            r4.<init>(r5)
            return r4
        L6c:
            gnu.lists.S32Vector r4 = new gnu.lists.S32Vector
            r4.<init>(r5)
            return r4
        L72:
            gnu.lists.S16Vector r4 = new gnu.lists.S16Vector
            r4.<init>(r5)
            return r4
        L78:
            gnu.lists.S8Vector r4 = new gnu.lists.S8Vector
            r4.<init>(r5)
            return r4
        L7e:
            switch(r0) {
                case 8: goto L94;
                case 16: goto L8e;
                case 32: goto L88;
                case 64: goto L82;
                default: goto L81;
            }
        L81:
            goto L9a
        L82:
            gnu.lists.U64Vector r4 = new gnu.lists.U64Vector
            r4.<init>(r5)
            return r4
        L88:
            gnu.lists.U32Vector r4 = new gnu.lists.U32Vector
            r4.<init>(r5)
            return r4
        L8e:
            gnu.lists.U16Vector r4 = new gnu.lists.U16Vector
            r4.<init>(r5)
            return r4
        L94:
            gnu.lists.U8Vector r4 = new gnu.lists.U8Vector
            r4.<init>(r5)
            return r4
        L9a:
            return r4
        L9b:
            int r3 = r0 * 10
            int r0 = r3 + r2
            goto L1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readSimpleVector(gnu.kawa.lispexpr.LispReader, char):gnu.lists.SimpleVector");
    }
}
