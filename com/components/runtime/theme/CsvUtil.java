package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.collect.Lists;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class CsvUtil {
    private CsvUtil() {
    }

    public static YailList fromCsvTable(String csvString) throws Exception {
        CsvParser csvParser = new CsvParser(new StringReader(csvString));
        ArrayList<YailList> csvList = new ArrayList<>();
        while (csvParser.hasNext()) {
            csvList.add(YailList.makeList((List) csvParser.next()));
        }
        csvParser.throwAnyProblem();
        return YailList.makeList((List) csvList);
    }

    public static YailList fromCsvRow(String csvString) throws Exception {
        CsvParser csvParser = new CsvParser(new StringReader(csvString));
        if (csvParser.hasNext()) {
            YailList row = YailList.makeList((List) csvParser.next());
            if (csvParser.hasNext()) {
                throw new IllegalArgumentException("CSV text has multiple rows. Expected just one row.");
            }
            csvParser.throwAnyProblem();
            return row;
        }
        throw new IllegalArgumentException("CSV text cannot be parsed as a row.");
    }

    public static String toCsvRow(YailList csvRow) {
        StringBuilder csvStringBuilder = new StringBuilder();
        makeCsvRow(csvRow, csvStringBuilder);
        return csvStringBuilder.toString();
    }

    public static String toCsvTable(YailList csvList) {
        StringBuilder csvStringBuilder = new StringBuilder();
        for (Object rowObj : csvList.toArray()) {
            makeCsvRow((YailList) rowObj, csvStringBuilder);
            csvStringBuilder.append("\r\n");
        }
        return csvStringBuilder.toString();
    }

    private static void makeCsvRow(YailList row, StringBuilder csvStringBuilder) {
        String fieldDelim = "";
        for (Object fieldObj : row.toArray()) {
            String field = fieldObj.toString();
            csvStringBuilder.append(fieldDelim).append("\"").append(field.replaceAll("\"", "\"\"")).append("\"");
            fieldDelim = ",";
        }
    }

    /* loaded from: classes.dex */
    private static class CsvParser implements Iterator<List<String>> {
        private final Reader in;
        private Exception lastException;
        private int limit;
        private int pos;
        private long previouslyRead;
        private final Pattern ESCAPED_QUOTE_PATTERN = Pattern.compile("\"\"");
        private final char[] buf = new char[10240];
        private boolean opened = true;
        private int cellLength = -1;
        private int delimitedCellLength = -1;

        public CsvParser(Reader in) {
            this.in = in;
        }

        public void skip(long charPosition) throws IOException {
            while (charPosition > 0) {
                Reader reader = this.in;
                char[] cArr = this.buf;
                int n = reader.read(cArr, 0, Math.min((int) charPosition, cArr.length));
                if (n >= 0) {
                    this.previouslyRead += n;
                    charPosition -= n;
                } else {
                    return;
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.limit == 0) {
                fill();
            }
            int i = this.pos;
            return (i < this.limit || indexAfterCompactionAndFilling(i) < this.limit) && lookingAtCell();
        }

        @Override // java.util.Iterator
        public List<String> next() {
            ArrayList<String> result = Lists.newArrayList();
            do {
                char[] cArr = this.buf;
                int i = this.pos;
                if (cArr[i] != '\"') {
                    result.add(new String(cArr, i, this.cellLength).trim());
                } else {
                    String cell = new String(cArr, i + 1, this.cellLength - 2);
                    result.add(this.ESCAPED_QUOTE_PATTERN.matcher(cell).replaceAll("\"").trim());
                }
                int i2 = this.delimitedCellLength;
                boolean trailingComma = i2 > 0 && this.buf[(this.pos + i2) - 1] == ',';
                int i3 = this.pos + i2;
                this.pos = i3;
                this.cellLength = -1;
                this.delimitedCellLength = -1;
                int oldLimit = this.limit;
                boolean haveMoreData = i3 < this.limit || indexAfterCompactionAndFilling(i3) < this.limit;
                if (!haveMoreData && this.buf[oldLimit - 1] == ',') {
                    result.add("");
                }
                if (!trailingComma || !haveMoreData) {
                    break;
                }
            } while (lookingAtCell());
            return result;
        }

        public long getCharPosition() {
            return this.previouslyRead + this.pos;
        }

        private int indexAfterCompactionAndFilling(int i) {
            if (this.pos > 0) {
                i = compact(i);
            }
            fill();
            return i;
        }

        private int compact(int i) {
            int oldPos = this.pos;
            this.pos = 0;
            int toMove = this.limit - oldPos;
            if (toMove > 0) {
                char[] cArr = this.buf;
                System.arraycopy(cArr, oldPos, cArr, 0, toMove);
            }
            this.limit -= oldPos;
            this.previouslyRead += oldPos;
            return i - oldPos;
        }

        private void fill() {
            int toFill = this.buf.length - this.limit;
            while (this.opened && toFill > 0) {
                try {
                    int n = this.in.read(this.buf, this.limit, toFill);
                    if (n == -1) {
                        this.opened = false;
                    } else {
                        this.limit += n;
                        toFill -= n;
                    }
                } catch (IOException e) {
                    this.lastException = e;
                    this.opened = false;
                }
            }
        }

        private boolean lookingAtCell() {
            char[] cArr = this.buf;
            int i = this.pos;
            return cArr[i] == '\"' ? findUnescapedEndQuote(i + 1) : findUnquotedCellEnd(i);
        }

        private boolean findUnescapedEndQuote(int i) {
            while (true) {
                if (i >= this.limit) {
                    int indexAfterCompactionAndFilling = indexAfterCompactionAndFilling(i);
                    i = indexAfterCompactionAndFilling;
                    if (indexAfterCompactionAndFilling >= this.limit) {
                        this.lastException = new IllegalArgumentException("Syntax Error. unclosed quoted cell");
                        return false;
                    }
                }
                if (this.buf[i] != '\"' || ((i = checkedIndex(i + 1)) != this.limit && this.buf[i] == '\"')) {
                    i++;
                }
            }
            this.cellLength = i - this.pos;
            return findDelimOrEnd(i);
        }

        private boolean findDelimOrEnd(int i) {
            while (true) {
                if (i >= this.limit) {
                    int indexAfterCompactionAndFilling = indexAfterCompactionAndFilling(i);
                    i = indexAfterCompactionAndFilling;
                    int i2 = this.limit;
                    if (indexAfterCompactionAndFilling >= i2) {
                        this.delimitedCellLength = i2 - this.pos;
                        return true;
                    }
                }
                switch (this.buf[i]) {
                    case '\t':
                    case ' ':
                        i++;
                    case '\n':
                    case ',':
                        this.delimitedCellLength = checkedIndex(i + 1) - this.pos;
                        return true;
                    case '\r':
                        int j = checkedIndex(i + 1);
                        this.delimitedCellLength = (this.buf[j] == '\n' ? checkedIndex(j + 1) : j) - this.pos;
                        return true;
                    default:
                        this.lastException = new IOException("Syntax Error: non-whitespace between closing quote and delimiter or end");
                        return false;
                }
            }
        }

        private int checkedIndex(int i) {
            return i < this.limit ? i : indexAfterCompactionAndFilling(i);
        }

        private boolean findUnquotedCellEnd(int i) {
            while (true) {
                if (i >= this.limit) {
                    int indexAfterCompactionAndFilling = indexAfterCompactionAndFilling(i);
                    i = indexAfterCompactionAndFilling;
                    int i2 = this.limit;
                    if (indexAfterCompactionAndFilling >= i2) {
                        int i3 = i2 - this.pos;
                        this.cellLength = i3;
                        this.delimitedCellLength = i3;
                        return true;
                    }
                }
                switch (this.buf[i]) {
                    case '\n':
                    case ',':
                        int i4 = i - this.pos;
                        this.cellLength = i4;
                        this.delimitedCellLength = i4 + 1;
                        return true;
                    case '\r':
                        this.cellLength = i - this.pos;
                        int j = checkedIndex(i + 1);
                        this.delimitedCellLength = (this.buf[j] == '\n' ? checkedIndex(j + 1) : j) - this.pos;
                        return true;
                    case '\"':
                        this.lastException = new IllegalArgumentException("Syntax Error: quote in unquoted cell");
                        return false;
                    default:
                        i++;
                }
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void throwAnyProblem() throws Exception {
            Exception exc = this.lastException;
            if (exc != null) {
                throw exc;
            }
        }
    }
}
