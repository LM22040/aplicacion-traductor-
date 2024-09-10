package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.InPort;
import gnu.mapping.Procedure1;
import gnu.text.CompoundFormat;
import gnu.text.LineBufferedReader;
import gnu.text.LiteralFormat;
import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;

/* loaded from: classes.dex */
public class ParseFormat extends Procedure1 {
    public static final int PARAM_FROM_LIST = -1610612736;
    public static final int PARAM_UNSPECIFIED = -1073741824;
    public static final int SEEN_HASH = 16;
    public static final int SEEN_MINUS = 1;
    public static final int SEEN_PLUS = 2;
    public static final int SEEN_SPACE = 4;
    public static final int SEEN_ZERO = 8;
    public static final ParseFormat parseFormat = new ParseFormat(false);
    boolean emacsStyle;

    public ParseFormat(boolean emacsStyle) {
        this.emacsStyle = true;
        this.emacsStyle = emacsStyle;
    }

    public ReportFormat parseFormat(LineBufferedReader fmt) throws ParseException, IOException {
        return parseFormat(fmt, this.emacsStyle ? '?' : '~');
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:28:0x0088. Please report as an issue. */
    public static ReportFormat parseFormat(LineBufferedReader lineBufferedReader, char c) throws ParseException, IOException {
        int i;
        java.text.Format objectFormat;
        int i2;
        int i3;
        int i4;
        int i5;
        StringBuffer stringBuffer = new StringBuffer(100);
        Vector vector = new Vector();
        while (true) {
            int read = lineBufferedReader.read();
            if (read >= 0) {
                if (read != c) {
                    stringBuffer.append((char) read);
                } else {
                    read = lineBufferedReader.read();
                    if (read == c) {
                        stringBuffer.append((char) read);
                    }
                }
            }
            int length = stringBuffer.length();
            int i6 = 0;
            if (length > 0) {
                char[] cArr = new char[length];
                stringBuffer.getChars(0, length, cArr, 0);
                stringBuffer.setLength(0);
                vector.addElement(new LiteralFormat(cArr));
            }
            if (read >= 0) {
                if (read == 36) {
                    if (Character.digit((char) lineBufferedReader.read(), 10) < 0) {
                        throw new ParseException("missing number (position) after '%$'", -1);
                    }
                    do {
                        read = lineBufferedReader.read();
                    } while (Character.digit((char) read, 10) >= 0);
                }
                int i7 = 0;
                while (true) {
                    char c2 = (char) read;
                    switch (c2) {
                        case ' ':
                            i5 = i7 | 4;
                            i7 = i5;
                            read = lineBufferedReader.read();
                        case '#':
                            i5 = i7 | 16;
                            i7 = i5;
                            read = lineBufferedReader.read();
                        case '+':
                            i5 = i7 | 2;
                            i7 = i5;
                            read = lineBufferedReader.read();
                        case '-':
                            i5 = i7 | 1;
                            i7 = i5;
                            read = lineBufferedReader.read();
                        case '0':
                            i5 = i7 | 8;
                            i7 = i5;
                            read = lineBufferedReader.read();
                    }
                    int digit = Character.digit(c2, 10);
                    if (digit >= 0) {
                        while (true) {
                            read = lineBufferedReader.read();
                            int digit2 = Character.digit((char) read, 10);
                            if (digit2 >= 0) {
                                digit = (digit * 10) + digit2;
                            }
                        }
                    } else if (read != 42) {
                        digit = -1073741824;
                    } else {
                        digit = -1610612736;
                    }
                    if (read != 46) {
                        i = -1073741824;
                    } else if (read == 42) {
                        i = -1610612736;
                    } else {
                        int i8 = 0;
                        while (true) {
                            read = lineBufferedReader.read();
                            int digit3 = Character.digit((char) read, 10);
                            if (digit3 < 0) {
                                i = i8;
                            } else {
                                i8 = (i8 * 10) + digit3;
                            }
                        }
                    }
                    switch (read) {
                        case 83:
                        case 115:
                            objectFormat = new ObjectFormat(read == 83, i);
                            break;
                        case 88:
                        case 100:
                        case 105:
                        case 111:
                        case 120:
                            if (read == 100 || read == 105) {
                                i2 = 0;
                                i3 = 10;
                            } else if (read == 111) {
                                i2 = 0;
                                i3 = 8;
                            } else {
                                i3 = 16;
                                i2 = read == 88 ? 32 : 0;
                            }
                            int i9 = (i7 & 9) == 8 ? 48 : 32;
                            if ((i7 & 16) != 0) {
                                i2 |= 8;
                            }
                            if ((i7 & 2) != 0) {
                                i2 |= 2;
                            }
                            if ((i7 & 1) != 0) {
                                i2 |= 16;
                            }
                            if ((i7 & 4) == 0) {
                                i4 = i2;
                            } else {
                                i4 = i2 | 4;
                            }
                            if (i != -1073741824) {
                                i7 &= -9;
                                objectFormat = IntegerFormat.getInstance(i3, i, 48, -1073741824, -1073741824, i4 | 64);
                                break;
                            } else {
                                objectFormat = IntegerFormat.getInstance(i3, digit, i9, -1073741824, -1073741824, i4);
                                break;
                            }
                            break;
                        case 101:
                        case 102:
                        case 103:
                            objectFormat = new ObjectFormat(false);
                            break;
                        default:
                            throw new ParseException("unknown format character '" + read + "'", -1);
                    }
                    if (digit > 0) {
                        char c3 = (i7 & 8) != 0 ? '0' : ' ';
                        if ((i7 & 1) != 0) {
                            i6 = 100;
                        } else if (c3 == '0') {
                            i6 = -1;
                        }
                        objectFormat = new PadFormat(objectFormat, digit, c3, i6);
                    }
                    vector.addElement(objectFormat);
                }
            } else {
                int size = vector.size();
                if (size == 1) {
                    Object elementAt = vector.elementAt(0);
                    if (elementAt instanceof ReportFormat) {
                        return (ReportFormat) elementAt;
                    }
                }
                java.text.Format[] formatArr = new java.text.Format[size];
                vector.copyInto(formatArr);
                return new CompoundFormat(formatArr);
            }
        }
    }

    @Override // gnu.mapping.Procedure1, gnu.mapping.Procedure
    public Object apply1(Object arg) {
        return asFormat(arg, this.emacsStyle ? '?' : '~');
    }

    public static ReportFormat asFormat(Object arg, char style) {
        InPort iport;
        try {
            if (arg instanceof ReportFormat) {
                return (ReportFormat) arg;
            }
            if (style == '~') {
                return new LispFormat(arg.toString());
            }
            if (arg instanceof FString) {
                FString str = (FString) arg;
                iport = new CharArrayInPort(str.data, str.size);
            } else {
                iport = new CharArrayInPort(arg.toString());
            }
            try {
                return parseFormat(iport, style);
            } finally {
                iport.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error parsing format (" + ex + ")");
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("End while parsing format");
        } catch (ParseException ex2) {
            throw new RuntimeException("Invalid format (" + ex2 + ")");
        }
    }
}
