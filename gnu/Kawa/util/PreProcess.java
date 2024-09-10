package gnu.kawa.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
public class PreProcess {
    static final String JAVA4_FEATURES = "+JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio";
    static final String JAVA5_FEATURES = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName";
    static final String NO_JAVA4_FEATURES = "-JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android";
    static final String NO_JAVA6_FEATURES = "-JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
    static String[] version_features = {"java1", "-JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java2", "+JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4x", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 +use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java6compat5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6 -JAVA7 +JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java6", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 -JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java7", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 +JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer +use:java.dyn -Android", "android", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 -JAXP-QName -use:javax.xml.transform -JAVA6 -JAVA6COMPAT5 +Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer"};
    String filename;
    Hashtable keywords = new Hashtable();
    int lineno;
    byte[] resultBuffer;
    int resultLength;

    void error(String msg) {
        System.err.println(this.filename + ':' + this.lineno + ": " + msg);
        System.exit(-1);
    }

    public void filter(String filename) throws Throwable {
        if (filter(filename, new BufferedInputStream(new FileInputStream(filename)))) {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(this.resultBuffer, 0, this.resultLength);
            out.close();
            System.err.println("Pre-processed " + filename);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e0 A[EDGE_INSN: B:54:0x00e0->B:49:0x00e0 BREAK  A[LOOP:0: B:2:0x0018->B:66:0x0018], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0168 A[EDGE_INSN: B:85:0x0168->B:86:0x0168 BREAK  A[LOOP:1: B:70:0x0145->B:80:0x0161], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean filter(java.lang.String r27, java.io.BufferedInputStream r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 751
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.PreProcess.filter(java.lang.String, java.io.BufferedInputStream):boolean");
    }

    void handleArg(String arg) {
        if (arg.charAt(0) == '%') {
            String arg2 = arg.substring(1);
            int i = 0;
            while (true) {
                if (i >= version_features.length) {
                    System.err.println("Unknown version: " + arg2);
                    System.exit(-1);
                }
                if (arg2.equals(version_features[i])) {
                    break;
                } else {
                    i += 2;
                }
            }
            String features = version_features[i + 1];
            System.err.println("(variant " + arg2 + " maps to: " + features + ")");
            StringTokenizer tokenizer = new StringTokenizer(features);
            while (tokenizer.hasMoreTokens()) {
                handleArg(tokenizer.nextToken());
            }
            return;
        }
        if (arg.charAt(0) == '+') {
            this.keywords.put(arg.substring(1), Boolean.TRUE);
            return;
        }
        if (arg.charAt(0) == '-') {
            int eq = arg.indexOf(61);
            if (eq > 1) {
                String keyword = arg.substring(arg.charAt(1) == '-' ? 2 : 1, eq);
                String value = arg.substring(eq + 1);
                Boolean b = Boolean.FALSE;
                if (value.equalsIgnoreCase("true")) {
                    b = Boolean.TRUE;
                } else if (!value.equalsIgnoreCase("false")) {
                    System.err.println("invalid value " + value + " for " + keyword);
                    System.exit(-1);
                }
                this.keywords.put(keyword, b);
                return;
            }
            this.keywords.put(arg.substring(1), Boolean.FALSE);
            return;
        }
        try {
            filter(arg);
        } catch (Throwable ex) {
            System.err.println("caught " + ex);
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        PreProcess pp = new PreProcess();
        pp.keywords.put("true", Boolean.TRUE);
        pp.keywords.put("false", Boolean.FALSE);
        for (String str : args) {
            pp.handleArg(str);
        }
    }
}
