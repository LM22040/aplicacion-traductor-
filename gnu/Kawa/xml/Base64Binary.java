package gnu.kawa.xml;

/* loaded from: classes2.dex */
public class Base64Binary extends BinaryObject {
    public static final String ENCODING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    public Base64Binary(byte[] data) {
        this.data = data;
    }

    public static Base64Binary valueOf(String str) {
        return new Base64Binary(str);
    }

    public Base64Binary(String str) {
        int v;
        int len = str.length();
        int blen = 0;
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (!Character.isWhitespace(ch) && ch != '=') {
                blen++;
            }
        }
        int i2 = blen * 3;
        int blen2 = i2 / 4;
        byte[] bytes = new byte[blen2];
        int blen3 = 0;
        int buffered = 0;
        int padding = 0;
        int blen4 = 0;
        for (int i3 = 0; i3 < len; i3++) {
            char ch2 = str.charAt(i3);
            if (ch2 >= 'A' && ch2 <= 'Z') {
                v = ch2 - 'A';
            } else if (ch2 >= 'a' && ch2 <= 'z') {
                v = (ch2 - 'a') + 26;
            } else if (ch2 >= '0' && ch2 <= '9') {
                v = (ch2 - '0') + 52;
            } else if (ch2 == '+') {
                v = 62;
            } else if (ch2 == '/') {
                v = 63;
            } else {
                if (Character.isWhitespace(ch2)) {
                    continue;
                } else if (ch2 == '=') {
                    padding++;
                } else {
                    v = -1;
                }
            }
            if (v < 0 || padding > 0) {
                throw new IllegalArgumentException("illegal character in base64Binary string at position " + i3);
            }
            int value = (blen3 << 6) + v;
            buffered++;
            if (buffered != 4) {
                blen3 = value;
            } else {
                int blen5 = blen4 + 1;
                bytes[blen4] = (byte) (value >> 16);
                int blen6 = blen5 + 1;
                bytes[blen5] = (byte) (value >> 8);
                bytes[blen6] = (byte) value;
                buffered = 0;
                blen4 = blen6 + 1;
                blen3 = value;
            }
        }
        if (buffered + padding <= 0 ? blen4 != bytes.length : buffered + padding != 4 || (blen3 & ((1 << padding) - 1)) != 0 || (blen4 + 3) - padding != bytes.length) {
            throw new IllegalArgumentException();
        }
        switch (padding) {
            case 1:
                int blen7 = blen4 + 1;
                bytes[blen4] = (byte) (blen3 << 10);
                int i4 = blen7 + 1;
                bytes[blen7] = (byte) (blen3 >> 2);
                break;
            case 2:
                int i5 = blen4 + 1;
                bytes[blen4] = (byte) (blen3 >> 4);
                break;
        }
        this.data = bytes;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008b, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.StringBuffer toString(java.lang.StringBuffer r9) {
        /*
            r8 = this;
            byte[] r0 = r8.data
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L5:
            java.lang.String r4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
            if (r3 >= r1) goto L42
            r5 = r0[r3]
            int r6 = r2 << 8
            r7 = r5 & 255(0xff, float:3.57E-43)
            r2 = r6 | r7
            int r3 = r3 + 1
            int r6 = r3 % 3
            if (r6 != 0) goto L41
            int r6 = r2 >> 18
            r6 = r6 & 63
            char r6 = r4.charAt(r6)
            r9.append(r6)
            int r6 = r2 >> 12
            r6 = r6 & 63
            char r6 = r4.charAt(r6)
            r9.append(r6)
            int r6 = r2 >> 6
            r6 = r6 & 63
            char r6 = r4.charAt(r6)
            r9.append(r6)
            r6 = r2 & 63
            char r4 = r4.charAt(r6)
            r9.append(r4)
        L41:
            goto L5
        L42:
            int r3 = r1 % 3
            switch(r3) {
                case 1: goto L6f;
                case 2: goto L48;
                default: goto L47;
            }
        L47:
            goto L8b
        L48:
            int r3 = r2 >> 10
            r3 = r3 & 63
            char r3 = r4.charAt(r3)
            r9.append(r3)
            int r3 = r2 >> 4
            r3 = r3 & 63
            char r3 = r4.charAt(r3)
            r9.append(r3)
            int r3 = r2 << 2
            r3 = r3 & 63
            char r3 = r4.charAt(r3)
            r9.append(r3)
            r3 = 61
            r9.append(r3)
            goto L8b
        L6f:
            int r3 = r2 >> 2
            r3 = r3 & 63
            char r3 = r4.charAt(r3)
            r9.append(r3)
            int r3 = r2 << 4
            r3 = r3 & 63
            char r3 = r4.charAt(r3)
            r9.append(r3)
            java.lang.String r3 = "=="
            r9.append(r3)
        L8b:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.Base64Binary.toString(java.lang.StringBuffer):java.lang.StringBuffer");
    }

    public String toString() {
        return toString(new StringBuffer()).toString();
    }
}
