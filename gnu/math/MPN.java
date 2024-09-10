package gnu.math;

/* loaded from: classes.dex */
class MPN {
    MPN() {
    }

    public static int add_1(int[] dest, int[] x, int size, int y) {
        long carry = y & 4294967295L;
        for (int i = 0; i < size; i++) {
            long carry2 = carry + (x[i] & 4294967295L);
            dest[i] = (int) carry2;
            carry = carry2 >> 32;
        }
        return (int) carry;
    }

    public static int add_n(int[] dest, int[] x, int[] y, int len) {
        long carry = 0;
        for (int i = 0; i < len; i++) {
            long carry2 = carry + (x[i] & 4294967295L) + (4294967295L & y[i]);
            dest[i] = (int) carry2;
            carry = carry2 >>> 32;
        }
        int i2 = (int) carry;
        return i2;
    }

    public static int sub_n(int[] dest, int[] X, int[] Y, int size) {
        int cy = 0;
        for (int i = 0; i < size; i++) {
            int y = Y[i];
            int x = X[i];
            int y2 = y + cy;
            int i2 = 0;
            int cy2 = (y2 ^ Integer.MIN_VALUE) < (cy ^ Integer.MIN_VALUE) ? 1 : 0;
            int y3 = x - y2;
            if ((y3 ^ Integer.MIN_VALUE) > (Integer.MIN_VALUE ^ x)) {
                i2 = 1;
            }
            cy = cy2 + i2;
            dest[i] = y3;
        }
        return cy;
    }

    public static int mul_1(int[] dest, int[] x, int len, int y) {
        long yword = y & 4294967295L;
        long carry = 0;
        for (int j = 0; j < len; j++) {
            long carry2 = carry + ((x[j] & 4294967295L) * yword);
            dest[j] = (int) carry2;
            carry = carry2 >>> 32;
        }
        return (int) carry;
    }

    public static void mul(int[] dest, int[] x, int xlen, int[] y, int ylen) {
        dest[xlen] = mul_1(dest, x, xlen, y[0]);
        for (int i = 1; i < ylen; i++) {
            long yword = y[i] & 4294967295L;
            long carry = 0;
            for (int j = 0; j < xlen; j++) {
                long carry2 = carry + ((x[j] & 4294967295L) * yword) + (dest[i + j] & 4294967295L);
                dest[i + j] = (int) carry2;
                carry = carry2 >>> 32;
            }
            dest[i + xlen] = (int) carry;
        }
    }

    public static long udiv_qrnnd(long N, int D) {
        long a1;
        long q;
        long r;
        long b1;
        long r2;
        long a12 = N >>> 32;
        long a0 = N & 4294967295L;
        if (D < 0) {
            long b12 = D >>> 1;
            long c = N >>> 1;
            if (a12 < b12) {
                a1 = a12;
            } else if ((a12 >> 1) < b12) {
                a1 = a12;
            } else if (a0 < ((-D) & 4294967295L)) {
                r2 = D + D + a0;
                b1 = -2;
            } else {
                r2 = D + a0;
                b1 = -1;
            }
            if (a1 < b12) {
                q = c / b12;
                r = c % b12;
            } else {
                c = (c - (b12 << 32)) ^ (-1);
                long q2 = c / b12;
                long r3 = c % b12;
                q = ((-1) ^ q2) & 4294967295L;
                r = (b12 - 1) - r3;
            }
            long r4 = (r * 2) + (a0 & 1);
            if ((D & 1) == 0) {
                b1 = q;
                r2 = r4;
            } else if (r4 >= q) {
                r2 = r4 - q;
                b1 = q;
            } else if (q - r4 <= (D & 4294967295L)) {
                r2 = (r4 - q) + D;
                b1 = q - 1;
            } else {
                r2 = (r4 - q) + D + D;
                b1 = q - 2;
            }
        } else if (a12 < (((D - a12) - (a0 >>> 31)) & 4294967295L)) {
            b1 = N / D;
            r2 = N % D;
        } else {
            long q3 = D;
            long c2 = N - (q3 << 31);
            long q4 = c2 / D;
            long r5 = c2 % D;
            b1 = q4 - 2147483648L;
            r2 = r5;
        }
        return (r2 << 32) | (4294967295L & b1);
    }

    public static int divmod_1(int[] quotient, int[] dividend, int len, int divisor) {
        long r;
        int i = len - 1;
        long r2 = dividend[i];
        if ((r2 & 4294967295L) >= (divisor & 4294967295L)) {
            r = 0;
        } else {
            quotient[i] = 0;
            r = r2 << 32;
            i--;
        }
        while (i >= 0) {
            int n0 = dividend[i];
            r = udiv_qrnnd(((-4294967296L) & r) | (n0 & 4294967295L), divisor);
            quotient[i] = (int) r;
            i--;
        }
        return (int) (r >> 32);
    }

    public static int submul_1(int[] dest, int offset, int[] x, int len, int y) {
        long yl = y & 4294967295L;
        int carry = 0;
        int j = 0;
        while (true) {
            long prod = (x[j] & 4294967295L) * yl;
            int prod_high = (int) (prod >> 32);
            int prod_low = ((int) prod) + carry;
            int carry2 = ((prod_low ^ Integer.MIN_VALUE) < (carry ^ Integer.MIN_VALUE) ? 1 : 0) + prod_high;
            int carry3 = offset + j;
            int x_j = dest[carry3];
            int prod_low2 = x_j - prod_low;
            if ((prod_low2 ^ Integer.MIN_VALUE) > (Integer.MIN_VALUE ^ x_j)) {
                carry2++;
            }
            dest[offset + j] = prod_low2;
            j++;
            if (j < len) {
                carry = carry2;
            } else {
                return carry2;
            }
        }
    }

    public static void divide(int[] zds, int nx, int[] y, int ny) {
        int qhat;
        int j = nx;
        do {
            if (zds[j] == y[ny - 1]) {
                qhat = -1;
            } else {
                int qhat2 = zds[j];
                long w = (qhat2 << 32) + (zds[j - 1] & 4294967295L);
                qhat = (int) udiv_qrnnd(w, y[ny - 1]);
            }
            if (qhat != 0) {
                int borrow = submul_1(zds, j - ny, y, ny, qhat);
                int save = zds[j];
                long num = (save & 4294967295L) - (borrow & 4294967295L);
                while (num != 0) {
                    int qhat3 = qhat - 1;
                    long carry = 0;
                    int i = 0;
                    while (i < ny) {
                        long carry2 = carry + (zds[(j - ny) + i] & 4294967295L) + (y[i] & 4294967295L);
                        zds[(j - ny) + i] = (int) carry2;
                        carry = carry2 >>> 32;
                        i++;
                        borrow = borrow;
                        qhat3 = qhat3;
                        save = save;
                        num = num;
                    }
                    int qhat4 = qhat3;
                    int qhat5 = zds[j];
                    zds[j] = (int) (qhat5 + carry);
                    num = carry - 1;
                    borrow = borrow;
                    qhat = qhat4;
                }
            }
            zds[j] = qhat;
            j--;
        } while (j >= ny);
    }

    public static int chars_per_word(int radix) {
        if (radix < 10) {
            if (radix >= 8) {
                return 10;
            }
            if (radix <= 2) {
                return 32;
            }
            if (radix == 3) {
                return 20;
            }
            if (radix == 4) {
                return 16;
            }
            return 18 - radix;
        }
        if (radix < 12) {
            return 9;
        }
        if (radix <= 16) {
            return 8;
        }
        if (radix <= 23) {
            return 7;
        }
        if (radix <= 40) {
            return 6;
        }
        if (radix <= 256) {
            return 4;
        }
        return 1;
    }

    public static int count_leading_zeros(int i) {
        if (i == 0) {
            return 32;
        }
        int count = 0;
        for (int k = 16; k > 0; k >>= 1) {
            int j = i >>> k;
            if (j == 0) {
                count += k;
            } else {
                i = j;
            }
        }
        return count;
    }

    public static int set_str(int[] dest, byte[] bArr, int str_len, int base) {
        int cy_limb;
        int size = 0;
        if (((base - 1) & base) == 0) {
            int next_bitpos = 0;
            int bits_per_indigit = 0;
            int i = base;
            while (true) {
                int i2 = i >> 1;
                i = i2;
                if (i2 == 0) {
                    break;
                }
                bits_per_indigit++;
            }
            int res_digit = 0;
            int i3 = str_len;
            while (true) {
                i3--;
                if (i3 < 0) {
                    break;
                }
                byte b = bArr[i3];
                res_digit |= b << next_bitpos;
                next_bitpos += bits_per_indigit;
                if (next_bitpos >= 32) {
                    dest[size] = res_digit;
                    next_bitpos -= 32;
                    res_digit = b >> (bits_per_indigit - next_bitpos);
                    size++;
                }
            }
            if (res_digit != 0) {
                int size2 = size + 1;
                dest[size] = res_digit;
                return size2;
            }
            return size;
        }
        int indigits_per_limb = chars_per_word(base);
        int res_digit2 = 0;
        while (res_digit2 < str_len) {
            int chunk = str_len - res_digit2;
            if (chunk > indigits_per_limb) {
                chunk = indigits_per_limb;
            }
            int str_pos = res_digit2 + 1;
            int i4 = bArr[res_digit2];
            int big_base = base;
            while (true) {
                chunk--;
                if (chunk <= 0) {
                    break;
                }
                i4 = (i4 * base) + bArr[str_pos];
                big_base *= base;
                str_pos++;
            }
            if (size == 0) {
                cy_limb = i4;
            } else {
                int cy_limb2 = mul_1(dest, dest, size, big_base);
                cy_limb = cy_limb2 + add_1(dest, dest, size, i4);
            }
            if (cy_limb != 0) {
                dest[size] = cy_limb;
                size++;
            }
            res_digit2 = str_pos;
        }
        return size;
    }

    public static int cmp(int[] x, int[] y, int size) {
        int x_word;
        int y_word;
        do {
            size--;
            if (size >= 0) {
                x_word = x[size];
                y_word = y[size];
            } else {
                return 0;
            }
        } while (x_word == y_word);
        return (x_word ^ Integer.MIN_VALUE) > (Integer.MIN_VALUE ^ y_word) ? 1 : -1;
    }

    public static int cmp(int[] x, int xlen, int[] y, int ylen) {
        if (xlen > ylen) {
            return 1;
        }
        if (xlen < ylen) {
            return -1;
        }
        return cmp(x, y, xlen);
    }

    public static int rshift(int[] dest, int[] x, int x_start, int len, int count) {
        int count_2 = 32 - count;
        int low_word = x[x_start];
        int retval = low_word << count_2;
        int i = 1;
        while (i < len) {
            int high_word = x[x_start + i];
            dest[i - 1] = (low_word >>> count) | (high_word << count_2);
            low_word = high_word;
            i++;
        }
        dest[i - 1] = low_word >>> count;
        return retval;
    }

    public static void rshift0(int[] dest, int[] x, int x_start, int len, int count) {
        if (count > 0) {
            rshift(dest, x, x_start, len, count);
            return;
        }
        for (int i = 0; i < len; i++) {
            dest[i] = x[i + x_start];
        }
    }

    public static long rshift_long(int[] x, int len, int count) {
        int wordno = count >> 5;
        int count2 = count & 31;
        int sign = x[len + (-1)] < 0 ? -1 : 0;
        int w0 = wordno >= len ? sign : x[wordno];
        int wordno2 = wordno + 1;
        int w1 = wordno2 >= len ? sign : x[wordno2];
        if (count2 != 0) {
            int wordno3 = wordno2 + 1;
            int w2 = wordno3 >= len ? sign : x[wordno3];
            w0 = (w0 >>> count2) | (w1 << (32 - count2));
            w1 = (w1 >>> count2) | (w2 << (32 - count2));
        }
        return (w1 << 32) | (w0 & 4294967295L);
    }

    public static int lshift(int[] dest, int d_offset, int[] x, int len, int count) {
        int count_2 = 32 - count;
        int i = len - 1;
        int high_word = x[i];
        int retval = high_word >>> count_2;
        int d_offset2 = d_offset + 1;
        while (true) {
            i--;
            if (i >= 0) {
                int low_word = x[i];
                dest[d_offset2 + i] = (high_word << count) | (low_word >>> count_2);
                high_word = low_word;
            } else {
                dest[d_offset2 + i] = high_word << count;
                return retval;
            }
        }
    }

    static int findLowestBit(int word) {
        int i = 0;
        while ((word & 15) == 0) {
            word >>= 4;
            i += 4;
        }
        if ((word & 3) == 0) {
            word >>= 2;
            i += 2;
        }
        if ((word & 1) == 0) {
            return i + 1;
        }
        return i;
    }

    static int findLowestBit(int[] words) {
        int i = 0;
        while (words[i] == 0) {
            i++;
        }
        return (i * 32) + findLowestBit(words[i]);
    }

    public static int gcd(int[] x, int[] y, int len) {
        int word;
        int[] odd_arg;
        int[] other_arg;
        int i = 0;
        while (true) {
            word = x[i] | y[i];
            if (word != 0) {
                break;
            }
            i++;
        }
        int initShiftWords = i;
        int initShiftBits = findLowestBit(word);
        int len2 = len - initShiftWords;
        rshift0(x, x, initShiftWords, len2, initShiftBits);
        rshift0(y, y, initShiftWords, len2, initShiftBits);
        if ((x[0] & 1) != 0) {
            odd_arg = x;
            other_arg = y;
        } else {
            odd_arg = y;
            other_arg = x;
        }
        while (true) {
            int i2 = 0;
            while (other_arg[i2] == 0) {
                i2++;
            }
            if (i2 > 0) {
                int j = 0;
                while (j < len2 - i2) {
                    other_arg[j] = other_arg[j + i2];
                    j++;
                }
                while (j < len2) {
                    other_arg[j] = 0;
                    j++;
                }
            }
            int j2 = other_arg[0];
            int i3 = findLowestBit(j2);
            if (i3 > 0) {
                rshift(other_arg, other_arg, 0, len2, i3);
            }
            int i4 = cmp(odd_arg, other_arg, len2);
            if (i4 == 0) {
                break;
            }
            if (i4 > 0) {
                sub_n(odd_arg, odd_arg, other_arg, len2);
                int[] tmp = odd_arg;
                odd_arg = other_arg;
                other_arg = tmp;
            } else {
                sub_n(other_arg, other_arg, odd_arg, len2);
            }
            while (odd_arg[len2 - 1] == 0 && other_arg[len2 - 1] == 0) {
                len2--;
            }
        }
        if (initShiftWords + initShiftBits > 0) {
            if (initShiftBits > 0) {
                int sh_out = lshift(x, initShiftWords, x, len2, initShiftBits);
                if (sh_out != 0) {
                    x[len2 + initShiftWords] = sh_out;
                    len2++;
                }
            } else {
                int i5 = len2;
                while (true) {
                    i5--;
                    if (i5 < 0) {
                        break;
                    }
                    x[i5 + initShiftWords] = x[i5];
                }
            }
            int i6 = initShiftWords;
            while (true) {
                i6--;
                if (i6 >= 0) {
                    x[i6] = 0;
                } else {
                    return len2 + initShiftWords;
                }
            }
        } else {
            return len2;
        }
    }

    public static int intLength(int i) {
        return 32 - count_leading_zeros(i < 0 ? i ^ (-1) : i);
    }

    public static int intLength(int[] words, int len) {
        int len2 = len - 1;
        return intLength(words[len2]) + (len2 * 32);
    }
}
