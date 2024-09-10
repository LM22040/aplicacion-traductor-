package gnu.kawa.lispexpr;

import gnu.mapping.InPort;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

/* loaded from: classes.dex */
public class ReaderString extends ReadTableEntry {
    @Override // gnu.kawa.lispexpr.ReadTableEntry
    public Object read(Lexer in, int ch, int count) throws IOException, SyntaxException {
        return readString(in, ch, count);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v15, types: [int] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5, types: [char] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r8v0, types: [gnu.text.Lexer] */
    public static String readString(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        char c;
        char c2;
        int i3 = lexer.tokenBufferLength;
        LineBufferedReader port = lexer.getPort();
        boolean z = port instanceof InPort;
        if (!z) {
            c = 0;
            c2 = i;
        } else {
            InPort inPort = (InPort) port;
            c = inPort.readState;
            inPort.readState = (char) i;
            c2 = i;
        }
        while (true) {
            int i4 = 13;
            if (c2 == 13) {
                try {
                    c2 = port.read();
                    if (c2 == 10) {
                    }
                } finally {
                    lexer.tokenBufferLength = i3;
                    if (z) {
                        ((InPort) port).readState = c;
                    }
                }
            } else if (port.pos < port.limit && c2 != 10) {
                char[] cArr = port.buffer;
                int i5 = port.pos;
                port.pos = i5 + 1;
                c2 = cArr[i5];
            } else {
                c2 = port.read();
            }
            if (c2 != i) {
                switch (c2) {
                    case 13:
                        if (port.getConvertCR()) {
                            i4 = 10;
                            c2 = c2;
                        } else {
                            c2 = 32;
                        }
                        lexer.tokenBufferAppend(i4);
                        continue;
                    case 92:
                        if (lexer instanceof LispReader) {
                            c2 = ((LispReader) lexer).readEscape();
                        } else {
                            c2 = port.read();
                        }
                        if (c2 != -2) {
                            break;
                        } else {
                            c2 = 10;
                            break;
                        }
                }
                if (c2 < 0) {
                    lexer.eofError("unexpected EOF in string literal");
                }
                lexer.tokenBufferAppend(c2);
            } else {
                return new String(lexer.tokenBuffer, i3, lexer.tokenBufferLength - i3).intern();
            }
        }
    }
}
