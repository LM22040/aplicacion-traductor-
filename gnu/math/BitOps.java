package gnu.math;

/* loaded from: classes.dex */
public class BitOps {
    static final byte[] bit4_count = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    private BitOps() {
    }

    public static boolean bitValue(IntNum x, int bitno) {
        int i = x.ival;
        if (x.words == null) {
            if (bitno >= 32) {
                if (i >= 0) {
                    return false;
                }
            } else if (((i >> bitno) & 1) == 0) {
                return false;
            }
            return true;
        }
        int wordno = bitno >> 5;
        int[] iArr = x.words;
        if (wordno >= i) {
            if (iArr[i - 1] >= 0) {
                return false;
            }
        } else if (((iArr[wordno] >> bitno) & 1) == 0) {
            return false;
        }
        return true;
    }

    static int[] dataBufferFor(IntNum x, int bitno) {
        int[] data;
        int i = x.ival;
        int nwords = (bitno + 1) >> 5;
        if (x.words == null) {
            if (nwords == 0) {
                nwords = 1;
            }
            data = new int[nwords];
            data[0] = i;
            if (i < 0) {
                for (int j = 1; j < nwords; j++) {
                    data[j] = -1;
                }
            }
        } else {
            int nwords2 = (bitno + 1) >> 5;
            data = new int[nwords2 > i ? nwords2 : i];
            int j2 = i;
            while (true) {
                j2--;
                if (j2 < 0) {
                    break;
                }
                data[j2] = x.words[j2];
            }
            int j3 = i - 1;
            if (data[j3] < 0) {
                for (int j4 = i; j4 < nwords2; j4++) {
                    data[j4] = -1;
                }
            }
        }
        return data;
    }

    public static IntNum setBitValue(IntNum x, int bitno, int newValue) {
        int oldValue;
        int newValue2 = newValue & 1;
        int i = x.ival;
        if (x.words == null) {
            int oldValue2 = (i >> (bitno < 31 ? bitno : 31)) & 1;
            if (oldValue2 == newValue2) {
                return x;
            }
            if (bitno < 63) {
                return IntNum.make(i ^ (1 << bitno));
            }
        } else {
            int wordno = bitno >> 5;
            if (wordno < i) {
                oldValue = (x.words[wordno] >> bitno) & 1;
            } else {
                oldValue = x.words[i + (-1)] < 0 ? 1 : 0;
            }
            if (oldValue == newValue2) {
                return x;
            }
        }
        int[] data = dataBufferFor(x, bitno);
        int i2 = bitno >> 5;
        data[i2] = (1 << (bitno & 31)) ^ data[i2];
        return IntNum.make(data, data.length);
    }

    public static boolean test(IntNum x, int y) {
        return x.words == null ? (x.ival & y) != 0 : y < 0 || (x.words[0] & y) != 0;
    }

    public static boolean test(IntNum x, IntNum y) {
        if (y.words == null) {
            return test(x, y.ival);
        }
        if (x.words == null) {
            return test(y, x.ival);
        }
        if (x.ival < y.ival) {
            x = y;
            y = x;
        }
        for (int i = 0; i < y.ival; i++) {
            if ((x.words[i] & y.words[i]) != 0) {
                return true;
            }
        }
        return y.isNegative();
    }

    public static IntNum and(IntNum x, int y) {
        if (x.words == null) {
            return IntNum.make(x.ival & y);
        }
        if (y >= 0) {
            return IntNum.make(x.words[0] & y);
        }
        int len = x.ival;
        int[] words = new int[len];
        words[0] = x.words[0] & y;
        while (true) {
            len--;
            if (len > 0) {
                words[len] = x.words[len];
            } else {
                return IntNum.make(words, x.ival);
            }
        }
    }

    public static IntNum and(IntNum x, IntNum y) {
        if (y.words == null) {
            return and(x, y.ival);
        }
        if (x.words == null) {
            return and(y, x.ival);
        }
        if (x.ival < y.ival) {
            x = y;
            y = x;
        }
        int len = y.isNegative() ? x.ival : y.ival;
        int[] words = new int[len];
        int i = 0;
        while (i < y.ival) {
            words[i] = x.words[i] & y.words[i];
            i++;
        }
        while (i < len) {
            words[i] = x.words[i];
            i++;
        }
        return IntNum.make(words, len);
    }

    public static IntNum ior(IntNum x, IntNum y) {
        return bitOp(7, x, y);
    }

    public static IntNum xor(IntNum x, IntNum y) {
        return bitOp(6, x, y);
    }

    public static IntNum not(IntNum x) {
        return bitOp(12, x, IntNum.zero());
    }

    public static int swappedOp(int op) {
        return "\u0000\u0001\u0004\u0005\u0002\u0003\u0006\u0007\b\t\f\r\n\u000b\u000e\u000f".charAt(op);
    }

    public static IntNum bitOp(int op, IntNum x, IntNum y) {
        switch (op) {
            case 0:
                return IntNum.zero();
            case 1:
                return and(x, y);
            case 3:
                return x;
            case 5:
                return y;
            case 15:
                return IntNum.minusOne();
            default:
                IntNum result = new IntNum();
                setBitOp(result, op, x, y);
                return result.canonicalize();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void setBitOp(IntNum result, int op, IntNum x, IntNum y) {
        int yi;
        int ylen;
        int xi;
        int xlen;
        int ni;
        if (y.words != null && (x.words == null || x.ival < y.ival)) {
            x = y;
            y = x;
            op = swappedOp(op);
        }
        if (y.words == null) {
            yi = y.ival;
            ylen = 1;
        } else {
            yi = y.words[0];
            ylen = y.ival;
        }
        if (x.words == null) {
            xi = x.ival;
            xlen = 1;
        } else {
            xi = x.words[0];
            xlen = x.ival;
        }
        if (xlen > 1) {
            result.realloc(xlen);
        }
        int[] w = result.words;
        int i = 0;
        int finish = 0;
        switch (op) {
            case 0:
                ni = 0;
                break;
            case 1:
                while (true) {
                    ni = xi & yi;
                    if (i + 1 < ylen) {
                        int i2 = i + 1;
                        w[i] = ni;
                        xi = x.words[i2];
                        yi = y.words[i2];
                        i = i2;
                    } else if (yi < 0) {
                        finish = 1;
                        break;
                    }
                }
                break;
            case 2:
                while (true) {
                    ni = xi & (yi ^ (-1));
                    if (i + 1 < ylen) {
                        int i3 = i + 1;
                        w[i] = ni;
                        xi = x.words[i3];
                        yi = y.words[i3];
                        i = i3;
                    } else if (yi >= 0) {
                        finish = 1;
                        break;
                    }
                }
                break;
            case 3:
                ni = xi;
                finish = 1;
                break;
            case 4:
                while (true) {
                    ni = (xi ^ (-1)) & yi;
                    if (i + 1 < ylen) {
                        int i4 = i + 1;
                        w[i] = ni;
                        xi = x.words[i4];
                        yi = y.words[i4];
                        i = i4;
                    } else if (yi < 0) {
                        finish = 2;
                        break;
                    }
                }
                break;
            case 5:
                while (true) {
                    ni = yi;
                    if (i + 1 >= ylen) {
                        break;
                    } else {
                        int i5 = i + 1;
                        w[i] = ni;
                        int xi2 = x.words[i5];
                        yi = y.words[i5];
                        i = i5;
                    }
                }
            case 6:
                while (true) {
                    int ni2 = xi ^ yi;
                    if (i + 1 >= ylen) {
                        finish = yi >= 0 ? 1 : 2;
                        ni = ni2;
                        break;
                    } else {
                        int i6 = i + 1;
                        w[i] = ni2;
                        xi = x.words[i6];
                        yi = y.words[i6];
                        i = i6;
                    }
                }
            case 7:
                while (true) {
                    ni = xi | yi;
                    if (i + 1 < ylen) {
                        int i7 = i + 1;
                        w[i] = ni;
                        xi = x.words[i7];
                        yi = y.words[i7];
                        i = i7;
                    } else if (yi >= 0) {
                        finish = 1;
                        break;
                    }
                }
                break;
            case 8:
                while (true) {
                    ni = (xi | yi) ^ (-1);
                    if (i + 1 < ylen) {
                        int i8 = i + 1;
                        w[i] = ni;
                        xi = x.words[i8];
                        yi = y.words[i8];
                        i = i8;
                    } else if (yi >= 0) {
                        finish = 2;
                        break;
                    }
                }
                break;
            case 9:
                while (true) {
                    int ni3 = (xi ^ yi) ^ (-1);
                    if (i + 1 >= ylen) {
                        finish = yi < 0 ? 1 : 2;
                        ni = ni3;
                        break;
                    } else {
                        int i9 = i + 1;
                        w[i] = ni3;
                        xi = x.words[i9];
                        yi = y.words[i9];
                        i = i9;
                    }
                }
            case 10:
                while (true) {
                    ni = yi ^ (-1);
                    if (i + 1 >= ylen) {
                        break;
                    } else {
                        int i10 = i + 1;
                        w[i] = ni;
                        int xi3 = x.words[i10];
                        yi = y.words[i10];
                        i = i10;
                    }
                }
            case 11:
                while (true) {
                    ni = xi | (yi ^ (-1));
                    if (i + 1 < ylen) {
                        int i11 = i + 1;
                        w[i] = ni;
                        xi = x.words[i11];
                        yi = y.words[i11];
                        i = i11;
                    } else if (yi < 0) {
                        finish = 1;
                        break;
                    }
                }
                break;
            case 12:
                ni = xi ^ (-1);
                finish = 2;
                break;
            case 13:
                while (true) {
                    ni = (xi ^ (-1)) | yi;
                    if (i + 1 < ylen) {
                        int i12 = i + 1;
                        w[i] = ni;
                        xi = x.words[i12];
                        yi = y.words[i12];
                        i = i12;
                    } else if (yi >= 0) {
                        finish = 2;
                        break;
                    }
                }
                break;
            case 14:
                while (true) {
                    ni = (xi & yi) ^ (-1);
                    if (i + 1 < ylen) {
                        int i13 = i + 1;
                        w[i] = ni;
                        xi = x.words[i13];
                        yi = y.words[i13];
                        i = i13;
                    } else if (yi < 0) {
                        finish = 2;
                        break;
                    }
                }
                break;
            default:
                ni = -1;
                break;
        }
        if (i + 1 == xlen) {
            finish = 0;
        }
        switch (finish) {
            case 0:
                if (i != 0 || w != null) {
                    w[i] = ni;
                    i++;
                    break;
                } else {
                    result.ival = ni;
                    return;
                }
            case 1:
                w[i] = ni;
                while (true) {
                    i++;
                    if (i >= xlen) {
                        break;
                    } else {
                        w[i] = x.words[i];
                    }
                }
            case 2:
                w[i] = ni;
                while (true) {
                    i++;
                    if (i >= xlen) {
                        break;
                    } else {
                        w[i] = x.words[i] ^ (-1);
                    }
                }
        }
        result.ival = i;
    }

    public static IntNum extract(IntNum x, int startBit, int endBit) {
        int x_len;
        long l;
        if (endBit < 32) {
            int word0 = x.words == null ? x.ival : x.words[0];
            return IntNum.make((((-1) ^ ((-1) << endBit)) & word0) >> startBit);
        }
        if (x.words == null) {
            if (x.ival >= 0) {
                return IntNum.make(startBit < 31 ? x.ival >> startBit : 0);
            }
            x_len = 1;
        } else {
            x_len = x.ival;
        }
        boolean neg = x.isNegative();
        if (endBit > x_len * 32) {
            endBit = x_len * 32;
            if (!neg && startBit == 0) {
                return x;
            }
        } else {
            x_len = (endBit + 31) >> 5;
        }
        int length = endBit - startBit;
        if (length < 64) {
            if (x.words == null) {
                l = x.ival >> (startBit < 32 ? startBit : 31);
            } else {
                l = MPN.rshift_long(x.words, x_len, startBit);
            }
            return IntNum.make(((-1) ^ ((-1) << length)) & l);
        }
        int startWord = startBit >> 5;
        int buf_len = ((endBit >> 5) + 1) - startWord;
        int[] buf = new int[buf_len];
        if (x.words == null) {
            buf[0] = startBit >= 32 ? -1 : x.ival >> startBit;
        } else {
            MPN.rshift0(buf, x.words, startWord, x_len - startWord, startBit & 31);
        }
        int x_len2 = length >> 5;
        buf[x_len2] = ((-1) ^ ((-1) << length)) & buf[x_len2];
        return IntNum.make(buf, x_len2 + 1);
    }

    public static int lowestBitSet(int i) {
        if (i == 0) {
            return -1;
        }
        int index = 0;
        while ((i & 255) == 0) {
            i >>>= 8;
            index += 8;
        }
        while ((i & 3) == 0) {
            i >>>= 2;
            index += 2;
        }
        if ((i & 1) == 0) {
            return index + 1;
        }
        return index;
    }

    public static int lowestBitSet(IntNum x) {
        int[] x_words = x.words;
        if (x_words == null) {
            return lowestBitSet(x.ival);
        }
        int x_len = x.ival;
        while (0 < x_len) {
            int b = lowestBitSet(x_words[0]);
            if (b >= 0) {
                return (0 * 32) + b;
            }
        }
        return -1;
    }

    public static int bitCount(int i) {
        int count = 0;
        while (i != 0) {
            count += bit4_count[i & 15];
            i >>>= 4;
        }
        return count;
    }

    public static int bitCount(int[] x, int len) {
        int count = 0;
        while (true) {
            len--;
            if (len >= 0) {
                count += bitCount(x[len]);
            } else {
                return count;
            }
        }
    }

    public static int bitCount(IntNum x) {
        int x_len;
        int i;
        int[] x_words = x.words;
        if (x_words == null) {
            x_len = 1;
            i = bitCount(x.ival);
        } else {
            x_len = x.ival;
            i = bitCount(x_words, x_len);
        }
        return x.isNegative() ? (x_len * 32) - i : i;
    }

    public static IntNum reverseBits(IntNum x, int start, int end) {
        long j;
        int wj;
        int ival = x.ival;
        int[] xwords = x.words;
        long j2 = -1;
        if (xwords != null || end >= 63) {
            int[] data = dataBufferFor(x, end - 1);
            int i = start;
            int j3 = end - 1;
            while (i < j3) {
                int ii = i >> 5;
                int jj = j3 >> 5;
                int wi = data[ii];
                int biti = (wi >> i) & 1;
                if (ii == jj) {
                    int bitj = (wi >> j3) & 1;
                    j = -1;
                    wj = (biti << j3) | ((int) (wi & (((1 << i) | (1 << j3)) ^ (-1)))) | (bitj << i);
                } else {
                    j = j2;
                    int wj2 = data[jj];
                    int bitj2 = (wj2 >> (j3 & 31)) & 1;
                    data[jj] = (wj2 & ((1 << (j3 & 31)) ^ (-1))) | (biti << (j3 & 31));
                    wj = (wi & ((1 << (i & 31)) ^ (-1))) | (bitj2 << (i & 31));
                }
                data[ii] = wj;
                i++;
                j3--;
                j2 = j;
            }
            return IntNum.make(data, data.length);
        }
        long w = ival;
        int i2 = start;
        for (int j4 = end - 1; i2 < j4; j4--) {
            long bitj3 = (w >> j4) & 1;
            w = (w & (((1 << i2) | (1 << j4)) ^ (-1))) | (((w >> i2) & 1) << j4) | (bitj3 << i2);
            i2++;
        }
        return IntNum.make(w);
    }
}
