package gnu.kawa.lispexpr;

import gnu.bytecode.Access;
import gnu.bytecode.Type;
import gnu.expr.Keyword;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

/* loaded from: classes.dex */
public class ReaderDispatchMisc extends ReadTableEntry {
    private static ReaderDispatchMisc instance = new ReaderDispatchMisc();
    protected int code;

    public static ReaderDispatchMisc getInstance() {
        return instance;
    }

    public ReaderDispatchMisc() {
        this.code = -1;
    }

    public ReaderDispatchMisc(int code) {
        this.code = code;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [boolean] */
    @Override // gnu.kawa.lispexpr.ReadTableEntry
    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        GeneralHashTable<Integer, Object> generalHashTable;
        Object obj;
        Object readObject;
        int listLength;
        LispReader lispReader = (LispReader) lexer;
        int i3 = this.code;
        if (i3 >= 0) {
            i = i3;
        }
        char c = 0;
        switch (i) {
            case 33:
                return LispReader.readSpecial(lispReader);
            case 35:
                if ((lexer instanceof LispReader) && (generalHashTable = lispReader.sharedStructureTable) != null && (obj = generalHashTable.get(Integer.valueOf(i2), lexer)) != lexer) {
                    return obj;
                }
                lexer.error("an unrecognized #n# back-reference was read");
                return Values.empty;
            case 44:
                if (lispReader.getPort().peek() == 40 && (listLength = LList.listLength((readObject = lispReader.readObject()), false)) > 0) {
                    Pair pair = (Pair) readObject;
                    if (pair.getCar() instanceof Symbol) {
                        String obj2 = pair.getCar().toString();
                        Object readerCtor = ReadTable.getCurrent().getReaderCtor(obj2);
                        if (readerCtor == null) {
                            lexer.error("unknown reader constructor " + obj2);
                        } else if (!(readerCtor instanceof Procedure) && !(readerCtor instanceof Type)) {
                            lexer.error("reader constructor must be procedure or type name");
                        } else {
                            int i4 = listLength - 1;
                            ?? r2 = readerCtor instanceof Type;
                            Object[] objArr = new Object[(r2 == true ? 1 : 0) + i4];
                            Object cdr = pair.getCdr();
                            for (int i5 = 0; i5 < i4; i5++) {
                                Pair pair2 = (Pair) cdr;
                                objArr[(r2 == true ? 1 : 0) + i5] = pair2.getCar();
                                cdr = pair2.getCdr();
                            }
                            try {
                                if (r2 > 0) {
                                    objArr[0] = readerCtor;
                                    return Invoke.make.applyN(objArr);
                                }
                                return ((Procedure) readerCtor).applyN(objArr);
                            } catch (Throwable th) {
                                lexer.error("caught " + th + " applying reader constructor " + obj2);
                            }
                        }
                        return Boolean.FALSE;
                    }
                }
                lexer.error("a non-empty list starting with a symbol must follow #,");
                return Boolean.FALSE;
            case 47:
                return readRegex(lexer, i, i2);
            case 58:
                int i6 = lispReader.tokenBufferLength;
                lispReader.readToken(lispReader.read(), 'P', ReadTable.getCurrent());
                String str = new String(lispReader.tokenBuffer, i6, lispReader.tokenBufferLength - i6);
                lispReader.tokenBufferLength = i6;
                return Keyword.make(str.intern());
            case 59:
                LineBufferedReader port = lispReader.getPort();
                boolean z = port instanceof InPort;
                if (z) {
                    InPort inPort = (InPort) port;
                    c = inPort.readState;
                    inPort.readState = ';';
                }
                try {
                    lispReader.readObject();
                    if (z) {
                        ((InPort) port).readState = c;
                    }
                    return Values.empty;
                } finally {
                    if (z) {
                        ((InPort) port).readState = c;
                    }
                }
            case 61:
                Object readObject2 = lispReader.readObject();
                if (lexer instanceof LispReader) {
                    GeneralHashTable<Integer, Object> generalHashTable2 = lispReader.sharedStructureTable;
                    if (generalHashTable2 == null) {
                        generalHashTable2 = new GeneralHashTable<>();
                        lispReader.sharedStructureTable = generalHashTable2;
                    }
                    generalHashTable2.put(Integer.valueOf(i2), readObject2);
                }
                return readObject2;
            case 66:
                return LispReader.readNumberWithRadix(0, lispReader, 2);
            case 68:
                return LispReader.readNumberWithRadix(0, lispReader, 10);
            case 69:
            case 73:
                lispReader.tokenBufferAppend(35);
                lispReader.tokenBufferAppend(i);
                return LispReader.readNumberWithRadix(2, lispReader, 0);
            case 70:
                if (Character.isDigit((char) lexer.peek())) {
                    return LispReader.readSimpleVector(lispReader, Access.FIELD_CONTEXT);
                }
                return Boolean.FALSE;
            case 79:
                return LispReader.readNumberWithRadix(0, lispReader, 8);
            case 82:
                if (i2 > 36) {
                    lexer.error("the radix " + i2 + " is too big (max is 36)");
                    i2 = 36;
                }
                return LispReader.readNumberWithRadix(0, lispReader, i2);
            case 83:
            case 85:
                return LispReader.readSimpleVector(lispReader, (char) i);
            case 84:
                return Boolean.TRUE;
            case 88:
                return LispReader.readNumberWithRadix(0, lispReader, 16);
            case 92:
                return LispReader.readCharacter(lispReader);
            case 124:
                LineBufferedReader port2 = lispReader.getPort();
                boolean z2 = port2 instanceof InPort;
                if (z2) {
                    InPort inPort2 = (InPort) port2;
                    c = inPort2.readState;
                    inPort2.readState = '|';
                }
                try {
                    lispReader.readNestedComment('#', '|');
                    return Values.empty;
                } finally {
                    if (z2) {
                        ((InPort) port2).readState = c;
                    }
                }
            default:
                lexer.error("An invalid #-construct was read.");
                return Values.empty;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00bb, code lost:
    
        r8.eofError("unexpected EOF in regex literal");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.regex.Pattern readRegex(gnu.text.Lexer r8, int r9, int r10) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            Method dump skipped, instructions count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderDispatchMisc.readRegex(gnu.text.Lexer, int, int):java.util.regex.Pattern");
    }
}
