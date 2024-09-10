package com.google.appinventor.components.runtime.util;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes.dex */
public class Base58Util {
    public static final char[] ALPHABET;
    private static final char ENCODED_ZERO;
    private static final int[] INDEXES;

    static {
        char[] charArray = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
        ALPHABET = charArray;
        ENCODED_ZERO = charArray[0];
        int[] iArr = new int[128];
        INDEXES = iArr;
        Arrays.fill(iArr, -1);
        int i = 0;
        while (true) {
            char[] cArr = ALPHABET;
            if (i < cArr.length) {
                INDEXES[cArr[i]] = i;
                i++;
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:24:0x0038 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String encode(byte[] r7) {
        /*
            int r0 = r7.length
            if (r0 != 0) goto L6
            java.lang.String r0 = ""
            return r0
        L6:
            r0 = 0
        L7:
            int r1 = r7.length
            if (r0 >= r1) goto L11
            r1 = r7[r0]
            if (r1 != 0) goto L11
            int r0 = r0 + 1
            goto L7
        L11:
            int r1 = r7.length
            byte[] r7 = java.util.Arrays.copyOf(r7, r1)
            int r1 = r7.length
            int r1 = r1 * 2
            char[] r1 = new char[r1]
            int r2 = r1.length
            r3 = r0
        L1d:
            int r4 = r7.length
            if (r3 >= r4) goto L37
            int r2 = r2 + (-1)
            char[] r4 = com.google.appinventor.components.runtime.util.Base58Util.ALPHABET
            r5 = 256(0x100, float:3.59E-43)
            r6 = 58
            byte r5 = divmod(r7, r3, r5, r6)
            char r4 = r4[r5]
            r1[r2] = r4
            r4 = r7[r3]
            if (r4 != 0) goto L1d
            int r3 = r3 + 1
            goto L1d
        L37:
            int r3 = r1.length
            if (r2 >= r3) goto L43
            char r3 = r1[r2]
            char r4 = com.google.appinventor.components.runtime.util.Base58Util.ENCODED_ZERO
            if (r3 != r4) goto L43
            int r2 = r2 + 1
            goto L37
        L43:
            int r0 = r0 + (-1)
            if (r0 < 0) goto L4e
            int r2 = r2 + (-1)
            char r3 = com.google.appinventor.components.runtime.util.Base58Util.ENCODED_ZERO
            r1[r2] = r3
            goto L43
        L4e:
            java.lang.String r3 = new java.lang.String
            int r4 = r1.length
            int r4 = r4 - r2
            r3.<init>(r1, r2, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.Base58Util.encode(byte[]):java.lang.String");
    }

    /* JADX WARN: Incorrect condition in loop: B:38:0x007a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decode(java.lang.String r7) throws java.io.IOException {
        /*
            int r0 = r7.length()
            if (r0 != 0) goto La
            r0 = 0
            byte[] r0 = new byte[r0]
            return r0
        La:
            int r0 = r7.length()
            byte[] r0 = new byte[r0]
            r1 = 0
        L11:
            int r2 = r7.length()
            if (r1 >= r2) goto L50
            char r2 = r7.charAt(r1)
            r3 = 128(0x80, float:1.8E-43)
            if (r2 >= r3) goto L24
            int[] r3 = com.google.appinventor.components.runtime.util.Base58Util.INDEXES
            r3 = r3[r2]
            goto L25
        L24:
            r3 = -1
        L25:
            if (r3 < 0) goto L2d
            byte r4 = (byte) r3
            r0[r1] = r4
            int r1 = r1 + 1
            goto L11
        L2d:
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Illegal character "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r2)
            java.lang.String r6 = " at position "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L50:
            r1 = 0
        L51:
            int r2 = r0.length
            if (r1 >= r2) goto L5b
            r2 = r0[r1]
            if (r2 != 0) goto L5b
            int r1 = r1 + 1
            goto L51
        L5b:
            int r2 = r7.length()
            byte[] r2 = new byte[r2]
            int r3 = r2.length
            r4 = r1
        L63:
            int r5 = r0.length
            if (r4 >= r5) goto L79
            int r3 = r3 + (-1)
            r5 = 58
            r6 = 256(0x100, float:3.59E-43)
            byte r5 = divmod(r0, r4, r5, r6)
            r2[r3] = r5
            r5 = r0[r4]
            if (r5 != 0) goto L63
            int r4 = r4 + 1
            goto L63
        L79:
            int r4 = r2.length
            if (r3 >= r4) goto L83
            r4 = r2[r3]
            if (r4 != 0) goto L83
            int r3 = r3 + 1
            goto L79
        L83:
            int r4 = r3 - r1
            int r5 = r2.length
            byte[] r4 = copyOfRange(r2, r4, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.Base58Util.decode(java.lang.String):byte[]");
    }

    public static BigInteger decodeToBigInteger(String input) throws IOException {
        return new BigInteger(1, decode(input));
    }

    private static byte[] copyOfRange(byte[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0) {
            throw new IllegalArgumentException(from + " > " + to);
        }
        byte[] copy = new byte[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    private static byte divmod(byte[] number, int firstDigit, int base, int divisor) {
        int remainder = 0;
        for (int i = firstDigit; i < number.length; i++) {
            int digit = number[i] & 255;
            int temp = (remainder * base) + digit;
            number[i] = (byte) (temp / divisor);
            remainder = temp % divisor;
        }
        return (byte) remainder;
    }
}
