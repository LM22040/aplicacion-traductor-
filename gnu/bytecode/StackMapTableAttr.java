package gnu.bytecode;

import com.google.appinventor.components.common.PropertyTypeConstants;
import java.io.DataOutputStream;
import java.io.IOException;
import kawa.Telnet;

/* loaded from: classes.dex */
public class StackMapTableAttr extends MiscAttr {
    public static boolean compressStackMapTable = true;
    int countLocals;
    int countStack;
    int[] encodedLocals;
    int[] encodedStack;
    int numEntries;
    int prevPosition;

    public StackMapTableAttr() {
        super("StackMapTable", null, 0, 0);
        this.prevPosition = -1;
        put2(0);
    }

    public StackMapTableAttr(byte[] data, CodeAttr code) {
        super("StackMapTable", data, 0, data.length);
        this.prevPosition = -1;
        addToFrontOf(code);
        this.numEntries = u2(0);
    }

    public Method getMethod() {
        return ((CodeAttr) this.container).getMethod();
    }

    @Override // gnu.bytecode.MiscAttr, gnu.bytecode.Attribute
    public void write(DataOutputStream dstr) throws IOException {
        put2(0, this.numEntries);
        super.write(dstr);
    }

    void emitVerificationType(int encoding) {
        int tag = encoding & 255;
        put1(tag);
        if (tag >= 7) {
            put2(encoding >> 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int encodeVerificationType(Type type, CodeAttr code) {
        if (type == null) {
            return 0;
        }
        if (type instanceof UninitializedType) {
            UninitializedType utype = (UninitializedType) type;
            Label label = utype.label;
            if (label == null) {
                return 6;
            }
            return (label.position << 8) | 8;
        }
        Type type2 = type.getImplementationType();
        if (type2 instanceof PrimType) {
            switch (type2.signature.charAt(0)) {
                case 'B':
                case 'C':
                case 'I':
                case 'S':
                case 'Z':
                    return 1;
                case 'D':
                    return 3;
                case 'F':
                    return 2;
                case 'J':
                    return 4;
                default:
                    return 0;
            }
        }
        if (type2 == Type.nullType) {
            return 5;
        }
        return (code.getConstants().addClass((ObjectType) type2).index << 8) | 7;
    }

    public void emitStackMapEntry(Label label, CodeAttr code) {
        int offset_delta = (label.position - this.prevPosition) - 1;
        int rawLocalsCount = label.localTypes.length;
        int[] iArr = this.encodedLocals;
        if (rawLocalsCount > iArr.length) {
            int[] tmp = new int[iArr.length + rawLocalsCount];
            System.arraycopy(iArr, 0, tmp, 0, this.countLocals);
            this.encodedLocals = tmp;
        }
        int rawStackCount = label.stackTypes.length;
        int[] iArr2 = this.encodedStack;
        if (rawStackCount > iArr2.length) {
            int[] tmp2 = new int[iArr2.length + rawStackCount];
            System.arraycopy(iArr2, 0, tmp2, 0, this.countStack);
            this.encodedStack = tmp2;
        }
        int unchangedLocals = 0;
        int curLocalsCount = 0;
        int i = 0;
        while (i < rawLocalsCount) {
            int prevType = this.encodedLocals[curLocalsCount];
            int nextType = encodeVerificationType(label.localTypes[i], code);
            if (prevType == nextType && unchangedLocals == curLocalsCount) {
                unchangedLocals = curLocalsCount + 1;
            }
            int curLocalsCount2 = curLocalsCount + 1;
            this.encodedLocals[curLocalsCount] = nextType;
            if (nextType == 3 || nextType == 4) {
                i++;
            }
            i++;
            curLocalsCount = curLocalsCount2;
        }
        while (curLocalsCount > 0 && this.encodedLocals[curLocalsCount - 1] == 0) {
            curLocalsCount--;
        }
        int curStackCount = 0;
        int i2 = 0;
        while (i2 < rawStackCount) {
            int i3 = this.encodedStack[curStackCount];
            Type t = label.stackTypes[i2];
            if (t == Type.voidType) {
                i2++;
                t = label.stackTypes[i2];
            }
            this.encodedStack[curStackCount] = encodeVerificationType(t, code);
            i2++;
            curStackCount++;
        }
        int i4 = this.countLocals;
        int localsDelta = curLocalsCount - i4;
        boolean z = compressStackMapTable;
        if (z && localsDelta == 0 && curLocalsCount == unchangedLocals && curStackCount <= 1) {
            if (curStackCount == 0) {
                if (offset_delta > 63) {
                    put1(Telnet.WILL);
                    put2(offset_delta);
                } else {
                    put1(offset_delta);
                }
            } else {
                if (offset_delta <= 63) {
                    put1(offset_delta + 64);
                } else {
                    put1(247);
                    put2(offset_delta);
                }
                emitVerificationType(this.encodedStack[0]);
            }
        } else if (z && curStackCount == 0 && curLocalsCount < i4 && unchangedLocals == curLocalsCount && localsDelta >= -3) {
            put1(localsDelta + Telnet.WILL);
            put2(offset_delta);
        } else if (!z || curStackCount != 0 || i4 != unchangedLocals || localsDelta > 3) {
            put1(255);
            put2(offset_delta);
            put2(curLocalsCount);
            for (int i5 = 0; i5 < curLocalsCount; i5++) {
                emitVerificationType(this.encodedLocals[i5]);
            }
            put2(curStackCount);
            for (int i6 = 0; i6 < curStackCount; i6++) {
                emitVerificationType(this.encodedStack[i6]);
            }
        } else {
            put1(localsDelta + Telnet.WILL);
            put2(offset_delta);
            for (int i7 = 0; i7 < localsDelta; i7++) {
                emitVerificationType(this.encodedLocals[unchangedLocals + i7]);
            }
        }
        this.countLocals = curLocalsCount;
        this.countStack = curStackCount;
        this.prevPosition = label.position;
        this.numEntries++;
    }

    void printVerificationType(int encoding, ClassTypeWriter dst) {
        int tag = encoding & 255;
        switch (tag) {
            case 0:
                dst.print("top/unavailable");
                return;
            case 1:
                dst.print(PropertyTypeConstants.PROPERTY_TYPE_INTEGER);
                return;
            case 2:
                dst.print(PropertyTypeConstants.PROPERTY_TYPE_FLOAT);
                return;
            case 3:
                dst.print("double");
                return;
            case 4:
                dst.print("long");
                return;
            case 5:
                dst.print("null");
                return;
            case 6:
                dst.print("uninitialized this");
                return;
            case 7:
                int index = encoding >> 8;
                dst.printOptionalIndex(index);
                dst.printConstantTersely(index, 7);
                return;
            case 8:
                dst.print("uninitialized object created at ");
                dst.print(encoding >> 8);
                return;
            default:
                dst.print("<bad verification type tag " + tag + '>');
                return;
        }
    }

    int extractVerificationType(int startOffset, int tag) {
        if (tag == 7 || tag == 8) {
            int value = u2(startOffset + 1);
            return tag | (value << 8);
        }
        return tag;
    }

    static int[] reallocBuffer(int[] buffer, int needed) {
        if (buffer == null) {
            return new int[needed + 10];
        }
        if (needed > buffer.length) {
            int[] tmp = new int[needed + 10];
            System.arraycopy(buffer, 0, tmp, 0, buffer.length);
            return tmp;
        }
        return buffer;
    }

    int extractVerificationTypes(int startOffset, int count, int startIndex, int[] buffer) {
        int tag;
        int offset = startOffset;
        while (true) {
            count--;
            if (count >= 0) {
                if (offset >= this.dataLength) {
                    tag = -1;
                } else {
                    int tag2 = this.data[offset];
                    int encoding = extractVerificationType(offset, tag2);
                    offset += (tag2 == 7 || tag2 == 8) ? 3 : 1;
                    tag = encoding;
                }
                int encoding2 = startIndex + 1;
                buffer[startIndex] = tag;
                startIndex = encoding2;
            } else {
                return offset;
            }
        }
    }

    void printVerificationTypes(int[] encodings, int startIndex, int count, ClassTypeWriter dst) {
        int regno = 0;
        for (int i = 0; i < startIndex + count; i++) {
            int encoding = encodings[i];
            int tag = encoding & 255;
            if (i >= startIndex) {
                dst.print("  ");
                if (regno < 100) {
                    if (regno < 10) {
                        dst.print(' ');
                    }
                    dst.print(' ');
                }
                dst.print(regno);
                dst.print(": ");
                printVerificationType(encoding, dst);
                dst.println();
            }
            regno++;
            if (tag == 3 || tag == 4) {
                regno++;
            }
        }
    }

    @Override // gnu.bytecode.MiscAttr, gnu.bytecode.Attribute
    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", number of entries: ");
        classTypeWriter.println(this.numEntries);
        int i = 2;
        int i2 = -1;
        Method method = getMethod();
        int[] iArr = null;
        int length = (!method.getStaticFlag() ? 1 : 0) + method.arg_types.length;
        int i3 = 0;
        while (i3 < this.numEntries && i < this.dataLength) {
            int i4 = i + 1;
            int u1 = u1(i);
            int i5 = i2 + 1;
            if (u1 > 127) {
                if (i4 + 1 < this.dataLength) {
                    i2 = i5 + u2(i4);
                    i4 += 2;
                } else {
                    return;
                }
            } else {
                i2 = i5 + (u1 & 63);
            }
            classTypeWriter.print("  offset: ");
            classTypeWriter.print(i2);
            if (u1 > 63) {
                if (u1 <= 127 || u1 == 247) {
                    classTypeWriter.println(u1 <= 127 ? " - same_locals_1_stack_item_frame" : " - same_locals_1_stack_item_frame_extended");
                    iArr = reallocBuffer(iArr, 1);
                    i4 = extractVerificationTypes(i4, 1, 0, iArr);
                    printVerificationTypes(iArr, 0, 1, classTypeWriter);
                } else {
                    if (u1 <= 246) {
                        classTypeWriter.print(" - tag reserved for future use - ");
                        classTypeWriter.println(u1);
                        return;
                    }
                    if (u1 <= 250) {
                        int i6 = 251 - u1;
                        classTypeWriter.print(" - chop_frame - undefine ");
                        classTypeWriter.print(i6);
                        classTypeWriter.println(" locals");
                        length -= i6;
                    } else if (u1 == 251) {
                        classTypeWriter.println(" - same_frame_extended");
                    } else if (u1 > 254) {
                        if (i4 + 1 < this.dataLength) {
                            int u2 = u2(i4);
                            classTypeWriter.print(" - full_frame.  Locals count: ");
                            classTypeWriter.println(u2);
                            int[] reallocBuffer = reallocBuffer(iArr, u2);
                            int extractVerificationTypes = extractVerificationTypes(i4 + 2, u2, 0, reallocBuffer);
                            printVerificationTypes(reallocBuffer, 0, u2, classTypeWriter);
                            length = u2;
                            if (extractVerificationTypes + 1 < this.dataLength) {
                                int u22 = u2(extractVerificationTypes);
                                int i7 = extractVerificationTypes + 2;
                                classTypeWriter.print("    (end of locals)");
                                int length2 = Integer.toString(i2).length();
                                while (true) {
                                    length2--;
                                    if (length2 < 0) {
                                        break;
                                    } else {
                                        classTypeWriter.print(' ');
                                    }
                                }
                                classTypeWriter.print("       Stack count: ");
                                classTypeWriter.println(u22);
                                iArr = reallocBuffer(reallocBuffer, u22);
                                int extractVerificationTypes2 = extractVerificationTypes(i7, u22, 0, iArr);
                                printVerificationTypes(iArr, 0, u22, classTypeWriter);
                                i4 = extractVerificationTypes2;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        int i8 = u1 - 251;
                        classTypeWriter.print(" - append_frame - define ");
                        classTypeWriter.print(i8);
                        classTypeWriter.println(" more locals");
                        iArr = reallocBuffer(iArr, length + i8);
                        i4 = extractVerificationTypes(i4, i8, length, iArr);
                        printVerificationTypes(iArr, length, i8, classTypeWriter);
                        length += i8;
                    }
                }
            } else {
                classTypeWriter.println(" - same_frame");
            }
            if (i4 >= 0) {
                i3++;
                i = i4;
            } else {
                classTypeWriter.println("<ERROR - missing data>");
                return;
            }
        }
    }
}
