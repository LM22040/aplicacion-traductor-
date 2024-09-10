package gnu.xquery.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.CollationKey;
import java.text.Collator;

/* loaded from: classes2.dex */
public class NamedCollator extends Collator implements Externalizable {
    public static final String UNICODE_CODEPOINT_COLLATION = "http://www.w3.org/2005/xpath-functions/collation/codepoint";
    public static final NamedCollator codepointCollation;
    Collator collator;
    String name;

    public static NamedCollator make(String name) {
        NamedCollator coll = new NamedCollator();
        coll.name = name;
        coll.resolve();
        return coll;
    }

    public String getName() {
        return this.name;
    }

    public static NamedCollator find(String name) {
        return make(name);
    }

    static {
        NamedCollator namedCollator = new NamedCollator();
        codepointCollation = namedCollator;
        namedCollator.name = UNICODE_CODEPOINT_COLLATION;
    }

    public void resolve() {
        String str = this.name;
        if (str != null && !str.equals(UNICODE_CODEPOINT_COLLATION)) {
            throw new RuntimeException("unknown collation: " + this.name);
        }
    }

    public static int codepointCompare(String str1, String str2) {
        int i1;
        int c1 = 0;
        int c2 = 0;
        int len1 = str1.length();
        int len2 = str2.length();
        while (c1 != len1) {
            if (c2 == len2) {
                return 1;
            }
            int i12 = c1 + 1;
            int c12 = str1.charAt(c1);
            if (c12 >= 55296 && c12 < 56320 && i12 < len1) {
                int i13 = i12 + 1;
                i1 = ((c12 - 55296) * 1024) + (str1.charAt(i12) - 56320) + 65536;
                c1 = i13;
            } else {
                i1 = c12;
                c1 = i12;
            }
            int i2 = c2 + 1;
            int c22 = str2.charAt(c2);
            if (c22 >= 55296 && c22 < 56320 && i2 < len2) {
                c22 = ((c22 - 55296) * 1024) + (str2.charAt(i2) - 56320) + 65536;
                i2++;
            }
            if (i1 != c22) {
                return i1 < c22 ? -1 : 1;
            }
            c2 = i2;
        }
        return c2 == len2 ? 0 : -1;
    }

    @Override // java.text.Collator
    public int compare(String str1, String str2) {
        Collator collator = this.collator;
        if (collator != null) {
            return collator.compare(str1, str2);
        }
        return codepointCompare(str1, str2);
    }

    @Override // java.text.Collator
    public CollationKey getCollationKey(String source) {
        return this.collator.getCollationKey(source);
    }

    @Override // java.text.Collator
    public int hashCode() {
        Collator collator = this.collator;
        if (collator != null) {
            return collator.hashCode();
        }
        return 0;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.name);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        resolve();
    }
}
