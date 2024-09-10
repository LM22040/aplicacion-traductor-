package gnu.text;

import androidx.core.app.NotificationCompat;
import gnu.lists.Consumer;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

/* loaded from: classes.dex */
public class Char implements Comparable, Externalizable {
    static char[] charNameValues;
    static String[] charNames;
    int value;
    static CharMap hashTable = new CharMap();
    static Char[] ascii = new Char[128];

    public Char() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Char(int ch) {
        this.value = ch;
    }

    public void print(Consumer out) {
        print(this.value, out);
    }

    public static void print(int i, Consumer out) {
        if (i >= 65536) {
            out.write((char) (((i - 65536) >> 10) + 55296));
            out.write((char) ((i & 1023) + 56320));
        } else {
            out.write((char) i);
        }
    }

    public final char charValue() {
        return (char) this.value;
    }

    public final int intValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    static {
        int i = 128;
        while (true) {
            i--;
            if (i >= 0) {
                ascii[i] = new Char(i);
            } else {
                charNameValues = new char[]{' ', '\t', '\n', '\n', '\r', '\f', '\b', 27, 127, 127, 127, 7, 7, 11, 0};
                charNames = new String[]{"space", "tab", "newline", "linefeed", "return", "page", "backspace", "esc", "delete", "del", "rubout", NotificationCompat.CATEGORY_ALARM, "bel", "vtab", "nul"};
                return;
            }
        }
    }

    public static Char make(int ch) {
        Char r1;
        if (ch < 128) {
            return ascii[ch];
        }
        synchronized (hashTable) {
            r1 = hashTable.get(ch);
        }
        return r1;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Char) && ((Char) obj).intValue() == this.value;
    }

    public static int nameToChar(String name) {
        char ch;
        int i = charNames.length;
        do {
            i--;
            if (i < 0) {
                int i2 = charNames.length;
                do {
                    i2--;
                    if (i2 < 0) {
                        int len = name.length();
                        if (len > 1 && name.charAt(0) == 'u') {
                            int value = 0;
                            for (int pos = 1; pos != len; pos++) {
                                int dig = Character.digit(name.charAt(pos), 16);
                                if (dig >= 0) {
                                    value = (value << 4) + dig;
                                }
                            }
                            return value;
                        }
                        if (len == 3 && name.charAt(1) == '-' && ((ch = name.charAt(0)) == 'c' || ch == 'C')) {
                            return name.charAt(2) & 31;
                        }
                        return -1;
                    }
                } while (!charNames[i2].equalsIgnoreCase(name));
                return charNameValues[i2];
            }
        } while (!charNames[i].equals(name));
        return charNameValues[i];
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append('\'');
        int i = this.value;
        if (i >= 32 && i < 127 && i != 39) {
            buf.append((char) i);
        } else {
            buf.append('\\');
            int i2 = this.value;
            if (i2 == 39) {
                buf.append('\'');
            } else if (i2 == 10) {
                buf.append('n');
            } else if (i2 == 13) {
                buf.append('r');
            } else if (i2 == 9) {
                buf.append('t');
            } else if (i2 < 256) {
                String str = Integer.toOctalString(i2);
                int i3 = 3 - str.length();
                while (true) {
                    i3--;
                    if (i3 < 0) {
                        break;
                    }
                    buf.append('0');
                }
                buf.append(str);
            } else {
                buf.append('u');
                String str2 = Integer.toHexString(this.value);
                int i4 = 4 - str2.length();
                while (true) {
                    i4--;
                    if (i4 < 0) {
                        break;
                    }
                    buf.append('0');
                }
                buf.append(str2);
            }
        }
        buf.append('\'');
        return buf.toString();
    }

    public static String toScmReadableString(int ch) {
        StringBuffer sbuf = new StringBuffer(20);
        sbuf.append("#\\");
        int i = 0;
        while (true) {
            char[] cArr = charNameValues;
            if (i < cArr.length) {
                if (((char) ch) != cArr[i]) {
                    i++;
                } else {
                    sbuf.append(charNames[i]);
                    return sbuf.toString();
                }
            } else {
                if (ch < 32 || ch > 127) {
                    sbuf.append('x');
                    sbuf.append(Integer.toString(ch, 16));
                } else {
                    sbuf.append((char) ch);
                }
                return sbuf.toString();
            }
        }
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        int i = this.value;
        if (i > 55296) {
            if (i > 65535) {
                out.writeChar(((i - 65536) >> 10) + 55296);
                this.value = (this.value & 1023) + 56320;
            } else if (i <= 56319) {
                out.writeChar(i);
                this.value = 0;
            }
        }
        out.writeChar(this.value);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        char next;
        char readChar = in.readChar();
        this.value = readChar;
        if (readChar >= 55296 && readChar < 56319 && (next = in.readChar()) >= 56320 && next <= 57343) {
            this.value = ((this.value - 55296) << 10) + (next - 56320) + 65536;
        }
    }

    public Object readResolve() throws ObjectStreamException {
        return make(this.value);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object o) {
        return this.value - ((Char) o).value;
    }
}
