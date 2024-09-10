package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class SourceDebugExtAttr extends Attribute {
    int curFileIndex;
    String curFileName;
    int curLineIndex;
    byte[] data;
    private String defaultStratumId;
    int dlength;
    int fileCount;
    int[] fileIDs;
    String[] fileNames;
    int lineCount;
    int[] lines;
    int maxFileID;
    private String outputFileName;

    private int fixLine(int sourceLine, int index) {
        int[] iArr = this.lines;
        int sourceMin = iArr[index];
        int repeat = iArr[index + 2];
        if (sourceLine < sourceMin) {
            if (index > 0) {
                return -1;
            }
            int sourceMax = (sourceMin + repeat) - 1;
            iArr[index] = sourceLine;
            iArr[index + 2] = (sourceMax - sourceLine) + 1;
            iArr[index + 3] = sourceLine;
            sourceMin = sourceLine;
        }
        int sourceMax2 = index + 3;
        int delta = iArr[sourceMax2] - sourceMin;
        if (sourceLine < sourceMin + repeat) {
            return sourceLine + delta;
        }
        if (index != (this.lineCount - 1) * 5 && (index != 0 || sourceLine >= iArr[8])) {
            return -1;
        }
        iArr[index + 2] = (sourceLine - sourceMin) + 1;
        return sourceLine + delta;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int fixLine(int sourceLine) {
        int outputStartLine;
        int outLine;
        int outLine2;
        int i = this.curLineIndex;
        if (i >= 0 && (outLine2 = fixLine(sourceLine, i)) >= 0) {
            return outLine2;
        }
        int i5 = 0;
        int findex = this.curFileIndex;
        for (int i2 = 0; i2 < this.lineCount; i2++) {
            if (i5 != this.curLineIndex && findex == this.lines[i5 + 1] && (outLine = fixLine(sourceLine, i5)) >= 0) {
                this.curLineIndex = i5;
                return outLine;
            }
            i5 += 5;
        }
        int[] iArr = this.lines;
        if (iArr == null) {
            this.lines = new int[20];
        } else if (i5 >= iArr.length) {
            int[] newLines = new int[i5 * 2];
            System.arraycopy(iArr, 0, newLines, 0, i5);
            this.lines = newLines;
        }
        if (i5 == 0) {
            outputStartLine = sourceLine;
        } else {
            int[] iArr2 = this.lines;
            int outputStartLine2 = iArr2[(i5 - 5) + 3] + iArr2[(i5 - 5) + 2];
            if (i5 == 5 && outputStartLine2 < 10000) {
                outputStartLine = 10000;
            } else {
                outputStartLine = outputStartLine2;
            }
            sourceLine = outputStartLine;
        }
        int[] iArr3 = this.lines;
        iArr3[i5] = sourceLine;
        iArr3[i5 + 1] = findex;
        iArr3[i5 + 2] = 1;
        iArr3[i5 + 3] = outputStartLine;
        iArr3[i5 + 4] = 1;
        this.curLineIndex = i5;
        this.lineCount++;
        return sourceLine;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFile(String fname) {
        String fpath;
        String str = this.curFileName;
        if (str != fname) {
            if (fname != null && fname.equals(str)) {
                return;
            }
            this.curFileName = fname;
            String fname2 = SourceFileAttr.fixSourceFile(fname);
            int slash = fname2.lastIndexOf(47);
            if (slash >= 0) {
                fname2 = fname2.substring(slash + 1);
                fpath = fname2 + '\n' + fname2;
            } else {
                fpath = fname2;
            }
            int i = this.curFileIndex;
            if (i >= 0 && fpath.equals(this.fileNames[i])) {
                return;
            }
            int n = this.fileCount;
            for (int i2 = 0; i2 < n; i2++) {
                if (i2 != this.curFileIndex && fpath.equals(this.fileNames[i2])) {
                    this.curFileIndex = i2;
                    this.curLineIndex = -1;
                    return;
                }
            }
            int[] iArr = this.fileIDs;
            if (iArr == null) {
                this.fileIDs = new int[5];
                this.fileNames = new String[5];
            } else if (n >= iArr.length) {
                int[] newIDs = new int[n * 2];
                String[] newNames = new String[n * 2];
                System.arraycopy(iArr, 0, newIDs, 0, n);
                System.arraycopy(this.fileNames, 0, newNames, 0, n);
                this.fileIDs = newIDs;
                this.fileNames = newNames;
            }
            this.fileCount++;
            int id = this.maxFileID + 1;
            this.maxFileID = id;
            int id2 = id << 1;
            if (slash >= 0) {
                id2++;
            }
            this.fileNames[n] = fpath;
            if (this.outputFileName == null) {
                this.outputFileName = fname2;
            }
            this.fileIDs[n] = id2;
            this.curFileIndex = n;
            this.curLineIndex = -1;
        }
    }

    public void addStratum(String name) {
        this.defaultStratumId = name;
    }

    public SourceDebugExtAttr(ClassType cl) {
        super("SourceDebugExtension");
        this.curLineIndex = -1;
        this.curFileIndex = -1;
        addToFrontOf(cl);
    }

    void nonAsteriskString(String str, StringBuffer sbuf) {
        if (str == null || str.length() == 0 || str.charAt(0) == '*') {
            sbuf.append(' ');
        }
        sbuf.append(str);
    }

    @Override // gnu.bytecode.Attribute
    public void assignConstants(ClassType cl) {
        super.assignConstants(cl);
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("SMAP\n");
        nonAsteriskString(this.outputFileName, sbuf);
        sbuf.append('\n');
        String stratum = this.defaultStratumId;
        if (stratum == null) {
            stratum = "Java";
        }
        nonAsteriskString(stratum, sbuf);
        sbuf.append('\n');
        sbuf.append("*S ");
        sbuf.append(stratum);
        sbuf.append('\n');
        sbuf.append("*F\n");
        int i = 0;
        while (true) {
            if (i >= this.fileCount) {
                break;
            }
            int id = this.fileIDs[i];
            boolean with_path = (id & 1) != 0;
            int id2 = id >> 1;
            if (with_path) {
                sbuf.append("+ ");
            }
            sbuf.append(id2);
            sbuf.append(' ');
            sbuf.append(this.fileNames[i]);
            sbuf.append('\n');
            i++;
        }
        int i2 = this.lineCount;
        if (i2 > 0) {
            int prevFileID = 0;
            sbuf.append("*L\n");
            int i3 = 0;
            int i5 = 0;
            do {
                int[] iArr = this.lines;
                int inputStartLine = iArr[i5];
                int lineFileID = this.fileIDs[iArr[i5 + 1]] >> 1;
                int repeatCount = iArr[i5 + 2];
                int outputStartLine = iArr[i5 + 3];
                int outputLineIncrement = iArr[i5 + 4];
                sbuf.append(inputStartLine);
                if (lineFileID != prevFileID) {
                    sbuf.append('#');
                    sbuf.append(lineFileID);
                    prevFileID = lineFileID;
                }
                if (repeatCount != 1) {
                    sbuf.append(',');
                    sbuf.append(repeatCount);
                }
                sbuf.append(':');
                sbuf.append(outputStartLine);
                if (outputLineIncrement != 1) {
                    sbuf.append(',');
                    sbuf.append(outputLineIncrement);
                }
                sbuf.append('\n');
                i5 += 5;
                i3++;
            } while (i3 < this.lineCount);
        }
        sbuf.append("*E\n");
        try {
            byte[] bytes = sbuf.toString().getBytes("UTF-8");
            this.data = bytes;
            this.dlength = bytes.length;
        } catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    @Override // gnu.bytecode.Attribute
    public int getLength() {
        return this.dlength;
    }

    @Override // gnu.bytecode.Attribute
    public void write(DataOutputStream dstr) throws IOException {
        dstr.write(this.data, 0, this.dlength);
    }

    @Override // gnu.bytecode.Attribute
    public void print(ClassTypeWriter dst) {
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.println(this.dlength);
        try {
            dst.print(new String(this.data, 0, this.dlength, "UTF-8"));
        } catch (Exception ex) {
            dst.print("(Caught ");
            dst.print(ex);
            dst.println(')');
        }
        int i = this.dlength;
        if (i > 0) {
            byte[] bArr = this.data;
            if (bArr[i - 1] != 13 && bArr[i - 1] != 10) {
                dst.println();
            }
        }
    }
}
