package gnu.text;

import gnu.lists.LList;
import gnu.mapping.ThreadLocation;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class PrettyWriter extends Writer {
    private static final int BLOCK_PER_LINE_PREFIX_END = -3;
    private static final int BLOCK_PREFIX_LENGTH = -4;
    private static final int BLOCK_SECTION_COLUMN = -2;
    private static final int BLOCK_SECTION_START_LINE = -6;
    private static final int BLOCK_START_COLUMN = -1;
    private static final int BLOCK_SUFFIX_LENGTH = -5;
    private static final int LOGICAL_BLOCK_LENGTH = 6;
    public static final int NEWLINE_FILL = 70;
    public static final int NEWLINE_LINEAR = 78;
    public static final int NEWLINE_LITERAL = 76;
    public static final int NEWLINE_MANDATORY = 82;
    public static final int NEWLINE_MISER = 77;
    public static final int NEWLINE_SPACE = 83;
    static final int QITEM_BASE_SIZE = 2;
    static final int QITEM_BLOCK_END_SIZE = 2;
    static final int QITEM_BLOCK_END_TYPE = 5;
    static final int QITEM_BLOCK_START_BLOCK_END = 4;
    static final int QITEM_BLOCK_START_PREFIX = 5;
    static final int QITEM_BLOCK_START_SIZE = 7;
    static final int QITEM_BLOCK_START_SUFFIX = 6;
    static final int QITEM_BLOCK_START_TYPE = 4;
    static final int QITEM_INDENTATION_AMOUNT = 3;
    static final char QITEM_INDENTATION_BLOCK = 'B';
    static final char QITEM_INDENTATION_CURRENT = 'C';
    static final int QITEM_INDENTATION_KIND = 2;
    static final int QITEM_INDENTATION_SIZE = 4;
    static final int QITEM_INDENTATION_TYPE = 3;
    static final int QITEM_NEWLINE_KIND = 4;
    static final int QITEM_NEWLINE_SIZE = 5;
    static final int QITEM_NEWLINE_TYPE = 2;
    static final int QITEM_NOP_TYPE = 0;
    static final int QITEM_POSN = 1;
    static final int QITEM_SECTION_START_DEPTH = 2;
    static final int QITEM_SECTION_START_SECTION_END = 3;
    static final int QITEM_SECTION_START_SIZE = 4;
    static final int QITEM_TAB_COLINC = 4;
    static final int QITEM_TAB_COLNUM = 3;
    static final int QITEM_TAB_FLAGS = 2;
    static final int QITEM_TAB_IS_RELATIVE = 2;
    static final int QITEM_TAB_IS_SECTION = 1;
    static final int QITEM_TAB_SIZE = 5;
    static final int QITEM_TAB_TYPE = 6;
    static final int QITEM_TYPE_AND_SIZE = 0;
    static final int QUEUE_INIT_ALLOC_SIZE = 300;
    int blockDepth;
    int[] blocks;
    public char[] buffer;
    public int bufferFillPointer;
    int bufferOffset;
    int bufferStartColumn;
    int currentBlock;
    int lineLength;
    int lineNumber;
    int miserWidth;
    protected Writer out;
    public int pendingBlocksCount;
    char[] prefix;
    int prettyPrintingMode;
    int[] queueInts;
    int queueSize;
    String[] queueStrings;
    int queueTail;
    char[] suffix;
    boolean wordEndSeen;
    public static ThreadLocation lineLengthLoc = new ThreadLocation("line-length");
    public static ThreadLocation miserWidthLoc = new ThreadLocation("miser-width");
    public static ThreadLocation indentLoc = new ThreadLocation("indent");
    public static int initialBufferSize = 126;

    public PrettyWriter(Writer out) {
        this.lineLength = 80;
        this.miserWidth = 40;
        int i = initialBufferSize;
        this.buffer = new char[i];
        this.blocks = new int[60];
        this.blockDepth = 6;
        this.prefix = new char[i];
        this.suffix = new char[i];
        this.queueInts = new int[QUEUE_INIT_ALLOC_SIZE];
        this.queueStrings = new String[QUEUE_INIT_ALLOC_SIZE];
        this.currentBlock = -1;
        this.out = out;
        this.prettyPrintingMode = 1;
    }

    public PrettyWriter(Writer out, int lineLength) {
        this.lineLength = 80;
        this.miserWidth = 40;
        int i = initialBufferSize;
        this.buffer = new char[i];
        this.blocks = new int[60];
        this.blockDepth = 6;
        this.prefix = new char[i];
        this.suffix = new char[i];
        this.queueInts = new int[QUEUE_INIT_ALLOC_SIZE];
        this.queueStrings = new String[QUEUE_INIT_ALLOC_SIZE];
        this.currentBlock = -1;
        this.out = out;
        this.lineLength = lineLength;
        this.prettyPrintingMode = lineLength <= 1 ? 0 : 1;
    }

    public PrettyWriter(Writer writer, boolean z) {
        this.lineLength = 80;
        this.miserWidth = 40;
        int i = initialBufferSize;
        this.buffer = new char[i];
        this.blocks = new int[60];
        this.blockDepth = 6;
        this.prefix = new char[i];
        this.suffix = new char[i];
        this.queueInts = new int[QUEUE_INIT_ALLOC_SIZE];
        this.queueStrings = new String[QUEUE_INIT_ALLOC_SIZE];
        this.currentBlock = -1;
        this.out = writer;
        this.prettyPrintingMode = z ? 1 : 0;
    }

    public void setPrettyPrintingMode(int mode) {
        this.prettyPrintingMode = mode;
    }

    public int getPrettyPrintingMode() {
        return this.prettyPrintingMode;
    }

    public boolean isPrettyPrinting() {
        return this.prettyPrintingMode > 0;
    }

    public void setPrettyPrinting(boolean z) {
        this.prettyPrintingMode = !z ? 1 : 0;
    }

    private int indexPosn(int index) {
        return this.bufferOffset + index;
    }

    private int posnIndex(int posn) {
        return posn - this.bufferOffset;
    }

    private int posnColumn(int posn) {
        return indexColumn(posnIndex(posn));
    }

    private int getQueueType(int index) {
        return this.queueInts[index] & 255;
    }

    private int getQueueSize(int index) {
        return this.queueInts[index] >> 16;
    }

    private int getSectionColumn() {
        return this.blocks[this.blockDepth - 2];
    }

    private int getStartColumn() {
        return this.blocks[this.blockDepth - 1];
    }

    private int getPerLinePrefixEnd() {
        return this.blocks[this.blockDepth - 3];
    }

    private int getPrefixLength() {
        return this.blocks[this.blockDepth - 4];
    }

    private int getSuffixLength() {
        return this.blocks[this.blockDepth - 5];
    }

    private int getSectionStartLine() {
        return this.blocks[this.blockDepth - 6];
    }

    public void writeWordEnd() {
        this.wordEndSeen = true;
    }

    public void writeWordStart() {
        if (this.wordEndSeen) {
            write(32);
        }
        this.wordEndSeen = false;
    }

    public void clearWordEnd() {
        this.wordEndSeen = false;
    }

    @Override // java.io.Writer
    public void write(int ch) {
        this.wordEndSeen = false;
        if (ch == 10 && this.prettyPrintingMode > 0) {
            enqueueNewline(76);
            return;
        }
        ensureSpaceInBuffer(1);
        int fillPointer = this.bufferFillPointer;
        this.buffer[fillPointer] = (char) ch;
        this.bufferFillPointer = fillPointer + 1;
        if (ch == 32 && this.prettyPrintingMode > 1 && this.currentBlock < 0) {
            enqueueNewline(83);
        }
    }

    @Override // java.io.Writer
    public void write(String str) {
        write(str, 0, str.length());
    }

    @Override // java.io.Writer
    public void write(String str, int start, int count) {
        this.wordEndSeen = false;
        while (count > 0) {
            int cnt = count;
            int available = ensureSpaceInBuffer(count);
            if (cnt > available) {
                cnt = available;
            }
            int fillPointer = this.bufferFillPointer;
            count -= cnt;
            while (true) {
                cnt--;
                if (cnt >= 0) {
                    int start2 = start + 1;
                    char ch = str.charAt(start);
                    if (ch == '\n' && this.prettyPrintingMode > 0) {
                        this.bufferFillPointer = fillPointer;
                        enqueueNewline(76);
                        fillPointer = this.bufferFillPointer;
                    } else {
                        int fillPointer2 = fillPointer + 1;
                        this.buffer[fillPointer] = ch;
                        if (ch == ' ' && this.prettyPrintingMode > 1 && this.currentBlock < 0) {
                            this.bufferFillPointer = fillPointer2;
                            enqueueNewline(83);
                            fillPointer = this.bufferFillPointer;
                        } else {
                            fillPointer = fillPointer2;
                        }
                    }
                    start = start2;
                }
            }
            this.bufferFillPointer = fillPointer;
        }
    }

    @Override // java.io.Writer
    public void write(char[] str) {
        write(str, 0, str.length);
    }

    @Override // java.io.Writer
    public void write(char[] str, int start, int count) {
        char c;
        this.wordEndSeen = false;
        int end = start + count;
        while (count > 0) {
            for (int i = start; i < end; i++) {
                if (this.prettyPrintingMode > 0 && ((c = str[i]) == '\n' || (c == ' ' && this.currentBlock < 0))) {
                    write(str, start, i - start);
                    write(c);
                    start = i + 1;
                    count = end - start;
                    break;
                }
            }
            do {
                int available = ensureSpaceInBuffer(count);
                int cnt = available < count ? available : count;
                int fillPointer = this.bufferFillPointer;
                int newFillPtr = fillPointer + cnt;
                int i2 = fillPointer;
                while (i2 < newFillPtr) {
                    this.buffer[i2] = str[start];
                    i2++;
                    start++;
                }
                this.bufferFillPointer = newFillPtr;
                count -= cnt;
            } while (count != 0);
        }
    }

    private void pushLogicalBlock(int column, int perLineEnd, int prefixLength, int suffixLength, int sectionStartLine) {
        int i = this.blockDepth;
        int newLength = i + 6;
        int[] iArr = this.blocks;
        if (newLength >= iArr.length) {
            int[] newBlocks = new int[iArr.length * 2];
            System.arraycopy(iArr, 0, newBlocks, 0, i);
            this.blocks = newBlocks;
        }
        this.blockDepth = newLength;
        int[] iArr2 = this.blocks;
        iArr2[newLength - 1] = column;
        iArr2[newLength - 2] = column;
        iArr2[newLength - 3] = perLineEnd;
        iArr2[newLength - 4] = prefixLength;
        iArr2[newLength - 5] = suffixLength;
        iArr2[newLength - 6] = sectionStartLine;
    }

    void reallyStartLogicalBlock(int column, String prefix, String suffix) {
        int perLineEnd = getPerLinePrefixEnd();
        int prefixLength = getPrefixLength();
        int suffixLength = getSuffixLength();
        pushLogicalBlock(column, perLineEnd, prefixLength, suffixLength, this.lineNumber);
        setIndentation(column);
        if (prefix != null) {
            this.blocks[this.blockDepth - 3] = column;
            int plen = prefix.length();
            prefix.getChars(0, plen, this.suffix, column - plen);
        }
        if (suffix != null) {
            char[] totalSuffix = this.suffix;
            int totalSuffixLen = totalSuffix.length;
            int additional = suffix.length();
            int newSuffixLen = suffixLength + additional;
            if (newSuffixLen > totalSuffixLen) {
                int newTotalSuffixLen = enoughSpace(totalSuffixLen, additional);
                char[] cArr = new char[newTotalSuffixLen];
                this.suffix = cArr;
                System.arraycopy(totalSuffix, totalSuffixLen - suffixLength, cArr, newTotalSuffixLen - suffixLength, suffixLength);
                totalSuffixLen = newTotalSuffixLen;
            }
            suffix.getChars(0, additional, totalSuffix, totalSuffixLen - newSuffixLen);
            this.blocks[this.blockDepth - 5] = newSuffixLen;
        }
    }

    int enqueueTab(int flags, int colnum, int colinc) {
        int addr = enqueue(6, 5);
        int[] iArr = this.queueInts;
        iArr[addr + 2] = flags;
        iArr[addr + 3] = colnum;
        iArr[addr + 4] = colinc;
        return addr;
    }

    private static int enoughSpace(int current, int want) {
        int doubled = current * 2;
        int enough = ((want * 5) >> 2) + current;
        return doubled > enough ? doubled : enough;
    }

    public void setIndentation(int column) {
        char[] prefix = this.prefix;
        int prefixLen = prefix.length;
        int current = getPrefixLength();
        int minimum = getPerLinePrefixEnd();
        if (minimum > column) {
            column = minimum;
        }
        if (column > prefixLen) {
            prefix = new char[enoughSpace(prefixLen, column - prefixLen)];
            System.arraycopy(this.prefix, 0, prefix, 0, current);
            this.prefix = prefix;
        }
        if (column > current) {
            for (int i = current; i < column; i++) {
                prefix[i] = ' ';
            }
        }
        this.blocks[this.blockDepth - 4] = column;
    }

    void reallyEndLogicalBlock() {
        int oldIndent = getPrefixLength();
        this.blockDepth -= 6;
        int newIndent = getPrefixLength();
        if (newIndent > oldIndent) {
            for (int i = oldIndent; i < newIndent; i++) {
                this.prefix[i] = ' ';
            }
        }
    }

    public int enqueue(int kind, int size) {
        int oldLength = this.queueInts.length;
        int endAvail = (oldLength - this.queueTail) - this.queueSize;
        if (endAvail > 0 && size > endAvail) {
            enqueue(0, endAvail);
        }
        if (this.queueSize + size > oldLength) {
            int newLength = enoughSpace(oldLength, size);
            int[] newInts = new int[newLength];
            String[] newStrings = new String[newLength];
            int queueHead = (this.queueTail + this.queueSize) - oldLength;
            if (queueHead > 0) {
                System.arraycopy(this.queueInts, 0, newInts, 0, queueHead);
                System.arraycopy(this.queueStrings, 0, newStrings, 0, queueHead);
            }
            int i = this.queueTail;
            int part1Len = oldLength - i;
            int deltaLength = newLength - oldLength;
            System.arraycopy(this.queueInts, i, newInts, i + deltaLength, part1Len);
            String[] strArr = this.queueStrings;
            int i2 = this.queueTail;
            System.arraycopy(strArr, i2, newStrings, i2 + deltaLength, part1Len);
            this.queueInts = newInts;
            this.queueStrings = newStrings;
            int i3 = this.currentBlock;
            int i4 = this.queueTail;
            if (i3 >= i4) {
                this.currentBlock = i3 + deltaLength;
            }
            this.queueTail = i4 + deltaLength;
        }
        int addr = this.queueTail + this.queueSize;
        int[] iArr = this.queueInts;
        if (addr >= iArr.length) {
            addr -= iArr.length;
        }
        iArr[addr + 0] = (size << 16) | kind;
        if (size > 1) {
            iArr[addr + 1] = indexPosn(this.bufferFillPointer);
        }
        this.queueSize += size;
        return addr;
    }

    public void enqueueNewline(int kind) {
        this.wordEndSeen = false;
        int depth = this.pendingBlocksCount;
        int newline = enqueue(2, 5);
        int[] iArr = this.queueInts;
        iArr[newline + 4] = kind;
        iArr[newline + 2] = this.pendingBlocksCount;
        iArr[newline + 3] = 0;
        int entry = this.queueTail;
        int todo = this.queueSize;
        while (todo > 0) {
            if (entry == this.queueInts.length) {
                entry = 0;
            }
            if (entry == newline) {
                break;
            }
            int type = getQueueType(entry);
            if (type == 2 || type == 4) {
                int[] iArr2 = this.queueInts;
                if (iArr2[entry + 3] == 0 && depth <= iArr2[entry + 2]) {
                    int delta = newline - entry;
                    if (delta < 0) {
                        delta += iArr2.length;
                    }
                    iArr2[entry + 3] = delta;
                }
            }
            int size = getQueueSize(entry);
            todo -= size;
            entry += size;
        }
        maybeOutput(kind == 76 || kind == 82, false);
    }

    public final void writeBreak(int kind) {
        if (this.prettyPrintingMode > 0) {
            enqueueNewline(kind);
        }
    }

    public int enqueueIndent(char kind, int amount) {
        int result = enqueue(3, 4);
        int[] iArr = this.queueInts;
        iArr[result + 2] = kind;
        iArr[result + 3] = amount;
        return result;
    }

    public void addIndentation(int amount, boolean current) {
        if (this.prettyPrintingMode > 0) {
            enqueueIndent(current ? 'C' : QITEM_INDENTATION_BLOCK, amount);
        }
    }

    public void startLogicalBlock(String prefix, boolean perLine, String suffix) {
        int outerBlock;
        if (this.queueSize == 0 && this.bufferFillPointer == 0) {
            Object llen = lineLengthLoc.get(null);
            if (llen == null) {
                this.lineLength = 80;
            } else {
                this.lineLength = Integer.parseInt(llen.toString());
            }
            Object mwidth = miserWidthLoc.get(null);
            if (mwidth == null || mwidth == Boolean.FALSE || mwidth == LList.Empty) {
                this.miserWidth = -1;
            } else {
                this.miserWidth = Integer.parseInt(mwidth.toString());
            }
            indentLoc.get(null);
        }
        if (prefix != null) {
            write(prefix);
        }
        if (this.prettyPrintingMode == 0) {
            return;
        }
        int start = enqueue(4, 7);
        int[] iArr = this.queueInts;
        int i = this.pendingBlocksCount;
        iArr[start + 2] = i;
        String[] strArr = this.queueStrings;
        strArr[start + 5] = perLine ? prefix : null;
        strArr[start + 6] = suffix;
        this.pendingBlocksCount = i + 1;
        int outerBlock2 = this.currentBlock;
        if (outerBlock2 < 0) {
            outerBlock = 0;
        } else {
            outerBlock = outerBlock2 - start;
            if (outerBlock > 0) {
                outerBlock -= iArr.length;
            }
        }
        iArr[start + 4] = outerBlock;
        iArr[start + 3] = 0;
        this.currentBlock = start;
    }

    public void endLogicalBlock() {
        int end = enqueue(5, 2);
        this.pendingBlocksCount--;
        if (this.currentBlock < 0) {
            int[] iArr = this.blocks;
            int suffixLength = iArr[this.blockDepth - 5];
            int suffixPreviousLength = iArr[(r3 - 6) - 5];
            if (suffixLength > suffixPreviousLength) {
                char[] cArr = this.suffix;
                write(cArr, cArr.length - suffixLength, suffixLength - suffixPreviousLength);
            }
            this.currentBlock = -1;
            return;
        }
        int suffixPreviousLength2 = this.currentBlock;
        int[] iArr2 = this.queueInts;
        int outerBlock = iArr2[suffixPreviousLength2 + 4];
        if (outerBlock == 0) {
            this.currentBlock = -1;
        } else {
            int qtailFromStart = this.queueTail - suffixPreviousLength2;
            if (qtailFromStart > 0) {
                qtailFromStart -= iArr2.length;
            }
            if (outerBlock < qtailFromStart) {
                this.currentBlock = -1;
            } else {
                int outerBlock2 = outerBlock + suffixPreviousLength2;
                if (outerBlock2 < 0) {
                    outerBlock2 += iArr2.length;
                }
                this.currentBlock = outerBlock2;
            }
        }
        String suffix = this.queueStrings[suffixPreviousLength2 + 6];
        if (suffix != null) {
            write(suffix);
        }
        int endFromStart = end - suffixPreviousLength2;
        if (endFromStart < 0) {
            endFromStart += this.queueInts.length;
        }
        this.queueInts[suffixPreviousLength2 + 4] = endFromStart;
    }

    public void endLogicalBlock(String suffix) {
        if (this.prettyPrintingMode > 0) {
            endLogicalBlock();
        } else if (suffix != null) {
            write(suffix);
        }
    }

    int computeTabSize(int tab, int sectionStart, int column) {
        int[] iArr = this.queueInts;
        int flags = iArr[tab + 2];
        boolean isSection = (flags & 1) != 0;
        boolean isRelative = (flags & 2) != 0;
        int origin = isSection ? sectionStart : 0;
        int colnum = iArr[tab + 3];
        int colinc = iArr[tab + 4];
        if (isRelative) {
            if (colinc > 1) {
                int newposn = column + colnum;
                int rem = newposn % colinc;
                return rem != 0 ? colnum + rem : colnum;
            }
            return colnum;
        }
        if (column <= colnum + origin) {
            return (column + origin) - column;
        }
        return colinc - ((column - origin) % colinc);
    }

    int indexColumn(int index) {
        int column = this.bufferStartColumn;
        int sectionStart = getSectionColumn();
        int endPosn = indexPosn(index);
        int op = this.queueTail;
        int todo = this.queueSize;
        while (todo > 0) {
            if (op >= this.queueInts.length) {
                op = 0;
            }
            int type = getQueueType(op);
            if (type != 0) {
                int[] iArr = this.queueInts;
                int posn = iArr[op + 1];
                if (posn >= endPosn) {
                    break;
                }
                if (type == 6) {
                    column += computeTabSize(op, sectionStart, posnIndex(posn) + column);
                } else if (type == 2 || type == 4) {
                    sectionStart = posnIndex(iArr[op + 1]) + column;
                }
            }
            int size = getQueueSize(op);
            todo -= size;
            op += size;
        }
        return column + index;
    }

    void expandTabs(int through) {
        PrettyWriter prettyWriter = this;
        int numInsertions = 0;
        int additional = 0;
        int column = prettyWriter.bufferStartColumn;
        int sectionStart = getSectionColumn();
        int op = prettyWriter.queueTail;
        int todo = prettyWriter.queueSize;
        int size = 6;
        int blocksUsed = prettyWriter.pendingBlocksCount * 6;
        while (todo > 0) {
            if (op == prettyWriter.queueInts.length) {
                op = 0;
            }
            if (op == through) {
                break;
            }
            int type = prettyWriter.getQueueType(op);
            if (type == size) {
                int index = prettyWriter.posnIndex(prettyWriter.queueInts[op + 1]);
                int tabsize = prettyWriter.computeTabSize(op, sectionStart, column + index);
                if (tabsize != 0) {
                    int i = (numInsertions * 2) + blocksUsed + 1;
                    int[] iArr = prettyWriter.blocks;
                    if (i >= iArr.length) {
                        int[] newBlocks = new int[iArr.length * 2];
                        System.arraycopy(iArr, 0, newBlocks, 0, iArr.length);
                        prettyWriter.blocks = newBlocks;
                    }
                    int[] iArr2 = prettyWriter.blocks;
                    iArr2[(numInsertions * 2) + blocksUsed] = index;
                    iArr2[(numInsertions * 2) + blocksUsed + 1] = tabsize;
                    numInsertions++;
                    additional += tabsize;
                    column += tabsize;
                }
            } else if (op == 2 || op == 4) {
                sectionStart = prettyWriter.posnIndex(prettyWriter.queueInts[op + 1]) + column;
            }
            int size2 = prettyWriter.getQueueSize(op);
            todo -= size2;
            op += size2;
            size = 6;
        }
        if (numInsertions > 0) {
            int fillPtr = prettyWriter.bufferFillPointer;
            int newFillPtr = fillPtr + additional;
            char[] buffer = prettyWriter.buffer;
            char[] newBuffer = buffer;
            int length = buffer.length;
            int end = fillPtr;
            if (newFillPtr > length) {
                int newLength = enoughSpace(fillPtr, additional);
                newBuffer = new char[newLength];
                prettyWriter.buffer = newBuffer;
            }
            prettyWriter.bufferFillPointer = newFillPtr;
            prettyWriter.bufferOffset -= additional;
            int i2 = numInsertions;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                int numInsertions2 = numInsertions;
                int[] iArr3 = prettyWriter.blocks;
                int srcpos = iArr3[blocksUsed + (i2 * 2)];
                int amount = iArr3[blocksUsed + (i2 * 2) + 1];
                int column2 = column;
                int column3 = srcpos + additional;
                int sectionStart2 = sectionStart;
                int sectionStart3 = end - srcpos;
                System.arraycopy(buffer, srcpos, newBuffer, column3, sectionStart3);
                for (int j = column3 - amount; j < column3; j++) {
                    newBuffer[j] = ' ';
                }
                additional -= amount;
                end = srcpos;
                prettyWriter = this;
                numInsertions = numInsertions2;
                column = column2;
                sectionStart = sectionStart2;
            }
            if (newBuffer != buffer) {
                System.arraycopy(buffer, 0, newBuffer, 0, end);
            }
        }
    }

    int ensureSpaceInBuffer(int want) {
        char[] buffer = this.buffer;
        int length = buffer.length;
        int fillPtr = this.bufferFillPointer;
        int available = length - fillPtr;
        if (available > 0) {
            return available;
        }
        if (this.prettyPrintingMode > 0 && fillPtr > this.lineLength) {
            if (!maybeOutput(false, false)) {
                outputPartialLine();
            }
            return ensureSpaceInBuffer(want);
        }
        int newLength = enoughSpace(length, want);
        char[] newBuffer = new char[newLength];
        this.buffer = newBuffer;
        int i = fillPtr;
        while (true) {
            i--;
            if (i >= 0) {
                newBuffer[i] = buffer[i];
            } else {
                int i2 = newLength - fillPtr;
                return i2;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:42:0x00a2. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0016. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean maybeOutput(boolean r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.PrettyWriter.maybeOutput(boolean, boolean):boolean");
    }

    protected int getMiserWidth() {
        return this.miserWidth;
    }

    boolean isMisering() {
        int mwidth = getMiserWidth();
        return mwidth > 0 && this.lineLength - getStartColumn() <= mwidth;
    }

    int getMaxLines() {
        return -1;
    }

    boolean printReadably() {
        return true;
    }

    int fitsOnLine(int sectionEnd, boolean forceNewlines) {
        int available = this.lineLength;
        if (!printReadably() && getMaxLines() == this.lineNumber) {
            available = (available - 3) - getSuffixLength();
        }
        if (sectionEnd >= 0) {
            return posnColumn(this.queueInts[sectionEnd + 1]) <= available ? 1 : -1;
        }
        if (forceNewlines || indexColumn(this.bufferFillPointer) > available) {
            return -1;
        }
        return 0;
    }

    public void lineAbbreviationHappened() {
    }

    void outputLine(int newline) throws IOException {
        int i;
        int maxLines;
        char[] buffer = this.buffer;
        int[] iArr = this.queueInts;
        int kind = iArr[newline + 4];
        boolean isLiteral = kind == 76;
        int amountToConsume = posnIndex(iArr[newline + 1]);
        if (isLiteral) {
            i = amountToConsume;
        } else {
            int i2 = amountToConsume;
            while (true) {
                i2--;
                if (i2 < 0) {
                    i = 0;
                    break;
                } else if (buffer[i2] != ' ') {
                    int amountToPrint = i2 + 1;
                    i = amountToPrint;
                    break;
                }
            }
        }
        this.out.write(buffer, 0, i);
        int lineNumber = this.lineNumber + 1;
        if (!printReadably() && (maxLines = getMaxLines()) > 0 && lineNumber >= maxLines) {
            this.out.write(" ..");
            int suffixLength = getSuffixLength();
            if (suffixLength != 0) {
                char[] suffix = this.suffix;
                int len = suffix.length;
                this.out.write(suffix, len - suffixLength, suffixLength);
            }
            lineAbbreviationHappened();
        }
        this.lineNumber = lineNumber;
        this.out.write(10);
        this.bufferStartColumn = 0;
        int fillPtr = this.bufferFillPointer;
        int prefixLen = isLiteral ? getPerLinePrefixEnd() : getPrefixLength();
        int shift = amountToConsume - prefixLen;
        int newFillPtr = fillPtr - shift;
        char[] newBuffer = buffer;
        int bufferLength = buffer.length;
        if (newFillPtr > bufferLength) {
            newBuffer = new char[enoughSpace(bufferLength, newFillPtr - bufferLength)];
            this.buffer = newBuffer;
        }
        System.arraycopy(buffer, amountToConsume, newBuffer, prefixLen, fillPtr - amountToConsume);
        System.arraycopy(this.prefix, 0, newBuffer, 0, prefixLen);
        this.bufferFillPointer = newFillPtr;
        this.bufferOffset += shift;
        if (!isLiteral) {
            int[] iArr2 = this.blocks;
            int i3 = this.blockDepth;
            iArr2[i3 - 2] = prefixLen;
            iArr2[i3 - 6] = lineNumber;
        }
    }

    void outputPartialLine() {
        int tail = this.queueTail;
        while (this.queueSize > 0 && getQueueType(tail) == 0) {
            int size = getQueueSize(tail);
            this.queueSize -= size;
            tail += size;
            if (tail == this.queueInts.length) {
                tail = 0;
            }
            this.queueTail = tail;
        }
        int fillPtr = this.bufferFillPointer;
        int count = this.queueSize > 0 ? posnIndex(this.queueInts[tail + 1]) : fillPtr;
        int newFillPtr = fillPtr - count;
        if (count <= 0) {
            throw new Error("outputPartialLine called when nothing can be output.");
        }
        try {
            this.out.write(this.buffer, 0, count);
            this.bufferFillPointer = count;
            this.bufferStartColumn = getColumnNumber();
            char[] cArr = this.buffer;
            System.arraycopy(cArr, count, cArr, 0, newFillPtr);
            this.bufferFillPointer = newFillPtr;
            this.bufferOffset += count;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void forcePrettyOutput() throws IOException {
        maybeOutput(false, true);
        if (this.bufferFillPointer > 0) {
            outputPartialLine();
        }
        expandTabs(-1);
        this.bufferStartColumn = getColumnNumber();
        this.out.write(this.buffer, 0, this.bufferFillPointer);
        this.bufferFillPointer = 0;
        this.queueSize = 0;
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        if (this.out == null) {
            return;
        }
        try {
            forcePrettyOutput();
            this.out.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            forcePrettyOutput();
            this.out.close();
            this.out = null;
        }
        this.buffer = null;
    }

    public void closeThis() throws IOException {
        if (this.out != null) {
            forcePrettyOutput();
            this.out = null;
        }
        this.buffer = null;
    }

    public int getColumnNumber() {
        char ch;
        int i = this.bufferFillPointer;
        do {
            i--;
            if (i < 0) {
                return this.bufferStartColumn + this.bufferFillPointer;
            }
            ch = this.buffer[i];
            if (ch == '\n') {
                break;
            }
        } while (ch != '\r');
        return this.bufferFillPointer - (i + 1);
    }

    public void setColumnNumber(int column) {
        this.bufferStartColumn += column - getColumnNumber();
    }

    public void clearBuffer() {
        this.bufferStartColumn = 0;
        this.bufferFillPointer = 0;
        this.lineNumber = 0;
        this.bufferOffset = 0;
        this.blockDepth = 6;
        this.queueTail = 0;
        this.queueSize = 0;
        this.pendingBlocksCount = 0;
    }
}
