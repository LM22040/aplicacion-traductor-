package gnu.xml;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.LineInputStreamReader;
import gnu.text.Path;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class XMLParser {
    private static final int ATTRIBUTE_SEEN_EQ_STATE = 11;
    private static final int ATTRIBUTE_SEEN_NAME_STATE = 8;
    static final String BAD_ENCODING_SYNTAX = "bad 'encoding' declaration";
    static final String BAD_STANDALONE_SYNTAX = "bad 'standalone' declaration";
    private static final int BEGIN_ELEMENT_STATE = 2;
    private static final int DOCTYPE_NAME_SEEN_STATE = 16;
    private static final int DOCTYPE_SEEN_STATE = 13;
    private static final int END_ELEMENT_STATE = 4;
    private static final int EXPECT_NAME_MODIFIER = 1;
    private static final int EXPECT_RIGHT_STATE = 27;
    private static final int INIT_LEFT_QUEST_STATE = 30;
    private static final int INIT_LEFT_STATE = 34;
    private static final int INIT_STATE = 0;
    private static final int INIT_TEXT_STATE = 31;
    private static final int INVALID_VERSION_DECL = 35;
    private static final int MAYBE_ATTRIBUTE_STATE = 10;
    private static final int PREV_WAS_CR_STATE = 28;
    private static final int SAW_AMP_SHARP_STATE = 26;
    private static final int SAW_AMP_STATE = 25;
    private static final int SAW_ENTITY_REF = 6;
    private static final int SAW_EOF_ERROR = 37;
    private static final int SAW_ERROR = 36;
    private static final int SAW_LEFT_EXCL_MINUS_STATE = 22;
    private static final int SAW_LEFT_EXCL_STATE = 20;
    private static final int SAW_LEFT_QUEST_STATE = 21;
    private static final int SAW_LEFT_SLASH_STATE = 19;
    private static final int SAW_LEFT_STATE = 14;
    private static final int SKIP_SPACES_MODIFIER = 2;
    private static final int TEXT_STATE = 1;

    public static void parse(Object uri, SourceMessages messages, Consumer out) throws IOException {
        parse(Path.openInputStream(uri), uri, messages, out);
    }

    public static LineInputStreamReader XMLStreamReader(InputStream strm) throws IOException {
        LineInputStreamReader in = new LineInputStreamReader(strm);
        int b1 = in.getByte();
        int b2 = b1 < 0 ? -1 : in.getByte();
        int b3 = b2 < 0 ? -1 : in.getByte();
        if (b1 == 239 && b2 == 187 && b3 == 191) {
            in.resetStart(3);
            in.setCharset("UTF-8");
        } else if (b1 == 255 && b2 == 254 && b3 != 0) {
            in.resetStart(2);
            in.setCharset("UTF-16LE");
        } else if (b1 == 254 && b2 == 255 && b3 != 0) {
            in.resetStart(2);
            in.setCharset("UTF-16BE");
        } else {
            int b4 = b3 >= 0 ? in.getByte() : -1;
            if (b1 == 76 && b2 == 111 && b3 == 167 && b4 == 148) {
                throw new RuntimeException("XMLParser: EBCDIC encodings not supported");
            }
            in.resetStart(0);
            if ((b1 == 60 && ((b2 == 63 && b3 == 120 && b4 == 109) || (b2 == 0 && b3 == 63 && b4 == 0))) || (b1 == 0 && b2 == 60 && b3 == 0 && b4 == 63)) {
                char[] buffer = in.buffer;
                if (buffer == null) {
                    char[] cArr = new char[8192];
                    buffer = cArr;
                    in.buffer = cArr;
                }
                int pos = 0;
                int quote = 0;
                while (true) {
                    int b = in.getByte();
                    if (b != 0) {
                        if (b < 0) {
                            break;
                        }
                        int pos2 = pos + 1;
                        buffer[pos] = (char) (b & 255);
                        if (quote == 0) {
                            if (b == 62) {
                                pos = pos2;
                                break;
                            }
                            if (b == 39 || b == 34) {
                                quote = b;
                            }
                        } else if (b == quote) {
                            quote = 0;
                        }
                        pos = pos2;
                    }
                }
                in.pos = 0;
                in.limit = pos;
            } else {
                in.setCharset("UTF-8");
            }
        }
        in.setKeepFullLines(false);
        return in;
    }

    public static void parse(InputStream strm, Object uri, SourceMessages messages, Consumer out) throws IOException {
        LineInputStreamReader in = XMLStreamReader(strm);
        if (uri != null) {
            in.setName(uri);
        }
        parse((LineBufferedReader) in, messages, out);
        in.close();
    }

    public static void parse(LineBufferedReader in, SourceMessages messages, Consumer out) throws IOException {
        XMLFilter filter = new XMLFilter(out);
        filter.setMessages(messages);
        filter.setSourceLocator(in);
        filter.startDocument();
        Object uri = in.getPath();
        if (uri != null) {
            filter.writeDocumentUri(uri);
        }
        parse(in, filter);
        filter.endDocument();
    }

    public static void parse(LineBufferedReader in, SourceMessages messages, XMLFilter filter) throws IOException {
        filter.setMessages(messages);
        filter.setSourceLocator(in);
        filter.startDocument();
        Object uri = in.getPath();
        if (uri != null) {
            filter.writeDocumentUri(uri);
        }
        parse(in, filter);
        filter.endDocument();
        in.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:745:0x07d5, code lost:
    
        if (r13 != 0) goto L752;
     */
    /* JADX WARN: Code restructure failed: missing block: B:747:0x07f3, code lost:
    
        r14 = r22;
        r12 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:751:0x07d9, code lost:
    
        if (r0 != 8) goto L522;
     */
    /* JADX WARN: Code restructure failed: missing block: B:752:0x07db, code lost:
    
        r4 = "missing or invalid attribute name";
     */
    /* JADX WARN: Code restructure failed: missing block: B:753:0x07ed, code lost:
    
        r14 = r4;
        r0 = 36;
        r12 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:756:0x07e0, code lost:
    
        if (r0 == 2) goto L528;
     */
    /* JADX WARN: Code restructure failed: missing block: B:758:0x07e3, code lost:
    
        if (r0 != 4) goto L527;
     */
    /* JADX WARN: Code restructure failed: missing block: B:759:0x07e6, code lost:
    
        r4 = "missing or invalid name";
     */
    /* JADX WARN: Code restructure failed: missing block: B:760:0x07ea, code lost:
    
        r4 = "missing or invalid element name";
     */
    /* JADX WARN: Code restructure failed: missing block: B:792:0x08ab, code lost:
    
        r5 = r2 - r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:793:0x08ad, code lost:
    
        if (r5 <= 0) goto L576;
     */
    /* JADX WARN: Code restructure failed: missing block: B:794:0x08af, code lost:
    
        r25.pos = r2;
        r26.textFromParser(r9, r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:795:0x08b4, code lost:
    
        r4 = r9.length;
        r14 = r2;
        r15 = r4;
        r13 = r5;
        r12 = r23;
        r2 = r0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0026. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:229:0x040f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0206 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x08e0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x0400 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0408 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:411:0x03f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:429:0x02dd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0565 A[LOOP:16: B:459:0x049b->B:476:0x0565, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:477:0x056c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:540:0x05f5 A[LOOP:17: B:532:0x0588->B:540:0x05f5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:541:0x05fd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x08d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:683:0x07fe A[LOOP:18: B:676:0x074d->B:683:0x07fe, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:684:0x0805 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void parse(gnu.text.LineBufferedReader r25, gnu.xml.XMLFilter r26) {
        /*
            Method dump skipped, instructions count: 2438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLParser.parse(gnu.text.LineBufferedReader, gnu.xml.XMLFilter):void");
    }
}
