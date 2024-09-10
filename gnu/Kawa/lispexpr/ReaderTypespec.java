package gnu.kawa.lispexpr;

import gnu.mapping.InPort;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

/* loaded from: classes.dex */
public class ReaderTypespec extends ReadTableEntry {
    @Override // gnu.kawa.lispexpr.ReadTableEntry
    public int getKind() {
        return 6;
    }

    @Override // gnu.kawa.lispexpr.ReadTableEntry
    public Object read(Lexer in, int ch, int count) throws IOException, SyntaxException {
        boolean z;
        int startPos = in.tokenBufferLength;
        LineBufferedReader port = in.getPort();
        ReadTable rtable = ReadTable.getCurrent();
        char saveReadState = 0;
        in.tokenBufferAppend(ch);
        int c = ch;
        if (port instanceof InPort) {
            saveReadState = ((InPort) port).readState;
            ((InPort) port).readState = (char) ch;
        }
        boolean got_open_square = false;
        while (true) {
            int prev = c;
            try {
                if (port.pos < port.limit && prev != 10) {
                    char[] cArr = port.buffer;
                    int i = port.pos;
                    port.pos = i + 1;
                    c = cArr[i];
                } else {
                    c = port.read();
                }
                if (c == 92) {
                    if (in instanceof LispReader) {
                        c = ((LispReader) in).readEscape();
                    } else {
                        c = port.read();
                    }
                } else {
                    if (!got_open_square && c == 91) {
                        z = true;
                    } else if (got_open_square && c == 93) {
                        z = false;
                    } else {
                        if (rtable.lookup(c).getKind() != 2) {
                            break;
                        }
                        in.tokenBufferAppend(c);
                    }
                    got_open_square = z;
                    in.tokenBufferAppend(c);
                }
            } finally {
                in.tokenBufferLength = startPos;
                if (port instanceof InPort) {
                    ((InPort) port).readState = saveReadState;
                }
            }
        }
        in.unread(c);
        return new String(in.tokenBuffer, startPos, in.tokenBufferLength - startPos).intern();
    }
}
