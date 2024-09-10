package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class HtmlEntities {
    private static final Pattern HTML_ENTITY_PATTERN = Pattern.compile("&(#?[0-9a-zA-Z]+);");
    private static final Map<String, Character> lookup;

    static {
        HashMap hashMap = new HashMap();
        lookup = hashMap;
        hashMap.put("Agrave", (char) 192);
        hashMap.put("agrave", (char) 224);
        hashMap.put("Aacute", (char) 193);
        hashMap.put("aacute", (char) 225);
        hashMap.put("Acirc", (char) 194);
        hashMap.put("acirc", (char) 226);
        hashMap.put("Atilde", (char) 195);
        hashMap.put("atilde", (char) 227);
        hashMap.put("Auml", (char) 196);
        hashMap.put("auml", (char) 228);
        hashMap.put("Aring", (char) 197);
        hashMap.put("aring", (char) 229);
        hashMap.put("AElig", (char) 198);
        hashMap.put("aelig", (char) 230);
        hashMap.put("Ccedil", (char) 199);
        hashMap.put("ccedil", (char) 231);
        hashMap.put("Egrave", (char) 200);
        hashMap.put("egrave", (char) 232);
        hashMap.put("Eacute", (char) 201);
        hashMap.put("eacute", (char) 233);
        hashMap.put("Ecirc", (char) 202);
        hashMap.put("ecirc", (char) 234);
        hashMap.put("Euml", (char) 203);
        hashMap.put("euml", (char) 235);
        hashMap.put("Igrave", (char) 204);
        hashMap.put("igrave", (char) 236);
        hashMap.put("Iacute", (char) 205);
        hashMap.put("iacute", (char) 237);
        hashMap.put("Icirc", (char) 206);
        hashMap.put("icirc", (char) 238);
        hashMap.put("Iuml", (char) 207);
        hashMap.put("iuml", (char) 239);
        hashMap.put("ETH", (char) 208);
        hashMap.put("eth", (char) 240);
        hashMap.put("Ntilde", (char) 209);
        hashMap.put("ntilde", (char) 241);
        hashMap.put("Ograve", (char) 210);
        hashMap.put("ograve", (char) 242);
        hashMap.put("Oacute", (char) 211);
        hashMap.put("oacute", (char) 243);
        hashMap.put("Ocirc", (char) 212);
        hashMap.put("ocirc", (char) 244);
        hashMap.put("Otilde", (char) 213);
        hashMap.put("otilde", (char) 245);
        hashMap.put("Ouml", (char) 214);
        hashMap.put("ouml", (char) 246);
        hashMap.put("Oslash", (char) 216);
        hashMap.put("oslash", (char) 248);
        hashMap.put("Ugrave", (char) 217);
        hashMap.put("ugrave", (char) 249);
        hashMap.put("Uacute", (char) 218);
        hashMap.put("uacute", (char) 250);
        hashMap.put("Ucirc", (char) 219);
        hashMap.put("ucirc", (char) 251);
        hashMap.put("Uuml", (char) 220);
        hashMap.put("uuml", (char) 252);
        hashMap.put("Yacute", (char) 221);
        hashMap.put("yacute", (char) 253);
        hashMap.put("THORN", (char) 222);
        hashMap.put("thorn", (char) 254);
        hashMap.put("szlig", (char) 223);
        hashMap.put("yuml", (char) 255);
        hashMap.put("Yuml", (char) 376);
        hashMap.put("OElig", (char) 338);
        hashMap.put("oelig", (char) 339);
        hashMap.put("Scaron", (char) 352);
        hashMap.put("scaron", (char) 353);
        hashMap.put("Alpha", (char) 913);
        hashMap.put("Beta", (char) 914);
        hashMap.put("Gamma", (char) 915);
        hashMap.put("Delta", (char) 916);
        hashMap.put("Epsilon", (char) 917);
        hashMap.put("Zeta", (char) 918);
        hashMap.put("Eta", (char) 919);
        hashMap.put("Theta", (char) 920);
        hashMap.put("Iota", (char) 921);
        hashMap.put("Kappa", (char) 922);
        hashMap.put("Lambda", (char) 923);
        hashMap.put("Mu", (char) 924);
        hashMap.put("Nu", (char) 925);
        hashMap.put("Xi", (char) 926);
        hashMap.put("Omicron", (char) 927);
        hashMap.put("Pi", (char) 928);
        hashMap.put("Rho", (char) 929);
        hashMap.put("Sigma", (char) 931);
        hashMap.put("Tau", (char) 932);
        hashMap.put("Upsilon", (char) 933);
        hashMap.put("Phi", (char) 934);
        hashMap.put("Chi", (char) 935);
        hashMap.put("Psi", (char) 936);
        hashMap.put("Omega", (char) 937);
        hashMap.put("alpha", (char) 945);
        hashMap.put("beta", (char) 946);
        hashMap.put("gamma", (char) 947);
        hashMap.put("delta", (char) 948);
        hashMap.put("epsilon", (char) 949);
        hashMap.put("zeta", (char) 950);
        hashMap.put("eta", (char) 951);
        hashMap.put("theta", (char) 952);
        hashMap.put("iota", (char) 953);
        hashMap.put("kappa", (char) 954);
        hashMap.put("lambda", (char) 955);
        hashMap.put("mu", (char) 956);
        hashMap.put("nu", (char) 957);
        hashMap.put("xi", (char) 958);
        hashMap.put("omicron", (char) 959);
        hashMap.put("pi", (char) 960);
        hashMap.put("rho", (char) 961);
        hashMap.put("sigmaf", (char) 962);
        hashMap.put("sigma", (char) 963);
        hashMap.put("tau", (char) 964);
        hashMap.put("upsilon", (char) 965);
        hashMap.put("phi", (char) 966);
        hashMap.put("chi", (char) 967);
        hashMap.put("psi", (char) 968);
        hashMap.put("omega", (char) 969);
        hashMap.put("thetasym", (char) 977);
        hashMap.put("upsih", (char) 978);
        hashMap.put("piv", (char) 982);
        hashMap.put("iexcl", (char) 161);
        hashMap.put("cent", (char) 162);
        hashMap.put("pound", (char) 163);
        hashMap.put("curren", (char) 164);
        hashMap.put("yen", (char) 165);
        hashMap.put("brvbar", (char) 166);
        hashMap.put("sect", (char) 167);
        hashMap.put("uml", (char) 168);
        hashMap.put("copy", (char) 169);
        hashMap.put("ordf", (char) 170);
        hashMap.put("laquo", (char) 171);
        hashMap.put("not", (char) 172);
        hashMap.put("shy", (char) 173);
        hashMap.put("reg", (char) 174);
        hashMap.put("macr", (char) 175);
        hashMap.put("deg", (char) 176);
        hashMap.put("plusmn", (char) 177);
        hashMap.put("sup2", (char) 178);
        hashMap.put("sup3", (char) 179);
        hashMap.put("acute", (char) 180);
        hashMap.put("micro", (char) 181);
        hashMap.put("para", (char) 182);
        hashMap.put("middot", (char) 183);
        hashMap.put("cedil", (char) 184);
        hashMap.put("sup1", (char) 185);
        hashMap.put("ordm", (char) 186);
        hashMap.put("raquo", (char) 187);
        hashMap.put("frac14", (char) 188);
        hashMap.put("frac12", (char) 189);
        hashMap.put("frac34", (char) 190);
        hashMap.put("iquest", (char) 191);
        hashMap.put("times", (char) 215);
        hashMap.put("divide", (char) 247);
        hashMap.put("fnof", (char) 402);
        hashMap.put("circ", (char) 710);
        hashMap.put("tilde", (char) 732);
        hashMap.put("lrm", (char) 8206);
        hashMap.put("rlm", (char) 8207);
        hashMap.put("ndash", (char) 8211);
        hashMap.put("endash", (char) 8211);
        hashMap.put("mdash", (char) 8212);
        hashMap.put("emdash", (char) 8212);
        hashMap.put("lsquo", (char) 8216);
        hashMap.put("rsquo", (char) 8217);
        hashMap.put("sbquo", (char) 8218);
        hashMap.put("ldquo", (char) 8220);
        hashMap.put("rdquo", (char) 8221);
        hashMap.put("bdquo", (char) 8222);
        hashMap.put("dagger", (char) 8224);
        hashMap.put("Dagger", (char) 8225);
        hashMap.put("bull", (char) 8226);
        hashMap.put("hellip", (char) 8230);
        hashMap.put("permil", (char) 8240);
        hashMap.put("prime", (char) 8242);
        hashMap.put("Prime", (char) 8243);
        hashMap.put("lsaquo", (char) 8249);
        hashMap.put("rsaquo", (char) 8250);
        hashMap.put("oline", (char) 8254);
        hashMap.put("frasl", (char) 8260);
        hashMap.put("euro", (char) 8364);
        hashMap.put("image", (char) 8465);
        hashMap.put("weierp", (char) 8472);
        hashMap.put("real", (char) 8476);
        hashMap.put("trade", (char) 8482);
        hashMap.put("alefsym", (char) 8501);
        hashMap.put("larr", (char) 8592);
        hashMap.put("uarr", (char) 8593);
        hashMap.put("rarr", (char) 8594);
        hashMap.put("darr", (char) 8595);
        hashMap.put("harr", (char) 8596);
        hashMap.put("crarr", (char) 8629);
        hashMap.put("lArr", (char) 8656);
        hashMap.put("uArr", (char) 8657);
        hashMap.put("rArr", (char) 8658);
        hashMap.put("dArr", (char) 8659);
        hashMap.put("hArr", (char) 8660);
        hashMap.put("forall", (char) 8704);
        hashMap.put("part", (char) 8706);
        hashMap.put("exist", (char) 8707);
        hashMap.put("empty", (char) 8709);
        hashMap.put("nabla", (char) 8711);
        hashMap.put("isin", (char) 8712);
        hashMap.put("notin", (char) 8713);
        hashMap.put("ni", (char) 8715);
        hashMap.put("prod", (char) 8719);
        hashMap.put("sum", (char) 8721);
        hashMap.put("minus", (char) 8722);
        hashMap.put("lowast", (char) 8727);
        hashMap.put("radic", (char) 8730);
        hashMap.put("prop", (char) 8733);
        hashMap.put("infin", (char) 8734);
        hashMap.put("ang", (char) 8736);
        hashMap.put("and", (char) 8743);
        hashMap.put("or", (char) 8744);
        hashMap.put("cap", (char) 8745);
        hashMap.put("cup", (char) 8746);
        hashMap.put("int", (char) 8747);
        hashMap.put("there4", (char) 8756);
        hashMap.put("sim", (char) 8764);
        hashMap.put("cong", (char) 8773);
        hashMap.put("asymp", (char) 8776);
        hashMap.put("ne", (char) 8800);
        hashMap.put("equiv", (char) 8801);
        hashMap.put("le", (char) 8804);
        hashMap.put("ge", (char) 8805);
        hashMap.put("sub", (char) 8834);
        hashMap.put("sup", (char) 8835);
        hashMap.put("nsub", (char) 8836);
        hashMap.put("sube", (char) 8838);
        hashMap.put("supe", (char) 8839);
        hashMap.put("oplus", (char) 8853);
        hashMap.put("otimes", (char) 8855);
        hashMap.put("perp", (char) 8869);
        hashMap.put("sdot", (char) 8901);
        hashMap.put("lceil", (char) 8968);
        hashMap.put("rceil", (char) 8969);
        hashMap.put("lfloor", (char) 8970);
        hashMap.put("rfloor", (char) 8971);
        hashMap.put("lang", (char) 9001);
        hashMap.put("rang", (char) 9002);
        hashMap.put("loz", (char) 9674);
        hashMap.put("spades", (char) 9824);
        hashMap.put("clubs", (char) 9827);
        hashMap.put("hearts", (char) 9829);
        hashMap.put("diams", (char) 9830);
        hashMap.put("gt", '>');
        hashMap.put("GT", '>');
        hashMap.put("lt", '<');
        hashMap.put("LT", '<');
        hashMap.put("quot", '\"');
        hashMap.put("QUOT", '\"');
        hashMap.put("amp", '&');
        hashMap.put("AMP", '&');
        hashMap.put("apos", '\'');
        hashMap.put("nbsp", (char) 160);
        hashMap.put("ensp", (char) 8194);
        hashMap.put("emsp", (char) 8195);
        hashMap.put("thinsp", (char) 8201);
        hashMap.put("zwnj", (char) 8204);
        hashMap.put("zwj", (char) 8205);
    }

    public static Character toCharacter(String entityName) {
        return lookup.get(entityName);
    }

    public static String decodeHtmlText(String htmlText) {
        if (htmlText.length() == 0 || htmlText.indexOf(38) == -1) {
            return htmlText;
        }
        StringBuilder output = new StringBuilder();
        int lastMatchEnd = 0;
        Matcher matcher = HTML_ENTITY_PATTERN.matcher(htmlText);
        while (matcher.find()) {
            String entity = matcher.group(1);
            Character convertedEntity = null;
            if (entity.startsWith("#x")) {
                String hhhh = entity.substring(2);
                try {
                    System.out.println("hex number is " + hhhh);
                    int code = Integer.parseInt(hhhh, 16);
                    convertedEntity = Character.valueOf((char) code);
                } catch (NumberFormatException e) {
                }
            } else if (entity.startsWith("#")) {
                String nnnn = entity.substring(1);
                try {
                    int code2 = Integer.parseInt(nnnn);
                    convertedEntity = Character.valueOf((char) code2);
                } catch (NumberFormatException e2) {
                }
            } else {
                convertedEntity = lookup.get(entity);
            }
            if (convertedEntity != null) {
                output.append(htmlText.substring(lastMatchEnd, matcher.start()));
                output.append(convertedEntity);
                lastMatchEnd = matcher.end();
            }
        }
        if (lastMatchEnd < htmlText.length()) {
            output.append(htmlText.substring(lastMatchEnd));
        }
        return output.toString();
    }
}
