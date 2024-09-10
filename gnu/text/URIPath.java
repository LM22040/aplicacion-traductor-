package gnu.text;

import gnu.bytecode.Access;
import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/* loaded from: classes.dex */
public class URIPath extends Path implements Comparable<URIPath> {
    final URI uri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public URIPath(URI uri) {
        this.uri = uri;
    }

    public static URIPath coerceToURIPathOrNull(Object path) {
        String str;
        if (path instanceof URIPath) {
            return (URIPath) path;
        }
        if (path instanceof URL) {
            return URLPath.valueOf((URL) path);
        }
        if (path instanceof URI) {
            return valueOf((URI) path);
        }
        if ((path instanceof File) || (path instanceof Path) || (path instanceof FString)) {
            str = path.toString();
        } else if (path instanceof String) {
            str = (String) path;
        } else {
            return null;
        }
        return valueOf(str);
    }

    public static URIPath makeURI(Object arg) {
        URIPath path = coerceToURIPathOrNull(arg);
        if (path == null) {
            throw new WrongType((String) null, -4, arg, "URI");
        }
        return path;
    }

    public static URIPath valueOf(URI uri) {
        return new URIPath(uri);
    }

    public static URIPath valueOf(String uri) {
        try {
            return new URIStringPath(new URI(encodeForUri(uri, Access.INNERCLASS_CONTEXT)), uri);
        } catch (Throwable ex) {
            throw WrappedException.wrapIfNeeded(ex);
        }
    }

    @Override // gnu.text.Path
    public boolean isAbsolute() {
        return this.uri.isAbsolute();
    }

    @Override // gnu.text.Path
    public boolean exists() {
        try {
            URLConnection conn = toURL().openConnection();
            return conn instanceof HttpURLConnection ? ((HttpURLConnection) conn).getResponseCode() == 200 : conn.getLastModified() != 0;
        } catch (Throwable th) {
            return false;
        }
    }

    @Override // gnu.text.Path
    public long getLastModified() {
        return URLPath.getLastModified(toURL());
    }

    @Override // gnu.text.Path
    public long getContentLength() {
        return URLPath.getContentLength(toURL());
    }

    @Override // gnu.text.Path
    public URI toUri() {
        return this.uri;
    }

    @Override // gnu.text.Path
    public String toURIString() {
        return this.uri.toString();
    }

    @Override // gnu.text.Path
    public Path resolve(String rstr) {
        if (Path.uriSchemeSpecified(rstr)) {
            return valueOf(rstr);
        }
        char fileSep = File.separatorChar;
        if (fileSep != '/') {
            if (rstr.length() >= 2 && ((rstr.charAt(1) == ':' && Character.isLetter(rstr.charAt(0))) || (rstr.charAt(0) == fileSep && rstr.charAt(1) == fileSep))) {
                return FilePath.valueOf(new File(rstr));
            }
            rstr = rstr.replace(fileSep, '/');
        }
        try {
            URI resolved = this.uri.resolve(new URI(null, rstr, null));
            return valueOf(resolved);
        } catch (Throwable ex) {
            throw WrappedException.wrapIfNeeded(ex);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(URIPath path) {
        return this.uri.compareTo(path.uri);
    }

    public boolean equals(Object obj) {
        return (obj instanceof URIPath) && this.uri.equals(((URIPath) obj).uri);
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    public String toString() {
        return toURIString();
    }

    @Override // gnu.text.Path
    public URL toURL() {
        return Path.toURL(this.uri.toString());
    }

    @Override // gnu.text.Path
    public InputStream openInputStream() throws IOException {
        return URLPath.openInputStream(toURL());
    }

    @Override // gnu.text.Path
    public OutputStream openOutputStream() throws IOException {
        return URLPath.openOutputStream(toURL());
    }

    @Override // gnu.text.Path
    public String getScheme() {
        return this.uri.getScheme();
    }

    @Override // gnu.text.Path
    public String getHost() {
        return this.uri.getHost();
    }

    @Override // gnu.text.Path
    public String getAuthority() {
        return this.uri.getAuthority();
    }

    @Override // gnu.text.Path
    public String getUserInfo() {
        return this.uri.getUserInfo();
    }

    @Override // gnu.text.Path
    public int getPort() {
        return this.uri.getPort();
    }

    @Override // gnu.text.Path
    public String getPath() {
        return this.uri.getPath();
    }

    @Override // gnu.text.Path
    public String getQuery() {
        return this.uri.getQuery();
    }

    @Override // gnu.text.Path
    public String getFragment() {
        return this.uri.getFragment();
    }

    @Override // gnu.text.Path
    public Path getCanonical() {
        if (isAbsolute()) {
            URI norm = this.uri.normalize();
            if (norm == this.uri) {
                return this;
            }
            return valueOf(norm);
        }
        return getAbsolute().getCanonical();
    }

    public static String encodeForUri(String str, char mode) {
        int i;
        int b;
        StringBuffer sbuf = new StringBuffer();
        int len = str.length();
        for (int ch = 0; ch < len; ch = i) {
            i = ch + 1;
            int ch2 = str.charAt(ch);
            if (ch2 >= 55296 && ch2 < 56320 && i < len) {
                ch2 = ((ch2 - 55296) * 1024) + (str.charAt(i) - 56320) + 65536;
                i++;
            }
            if (mode != 'H' ? !((ch2 < 97 || ch2 > 122) && ((ch2 < 65 || ch2 > 90) && ((ch2 < 48 || ch2 > 57) && ch2 != 45 && ch2 != 95 && ch2 != 46 && ch2 != 126 && (mode != 'I' || (ch2 != 59 && ch2 != 47 && ch2 != 63 && ch2 != 58 && ch2 != 42 && ch2 != 39 && ch2 != 40 && ch2 != 41 && ch2 != 64 && ch2 != 38 && ch2 != 61 && ch2 != 43 && ch2 != 36 && ch2 != 44 && ch2 != 91 && ch2 != 93 && ch2 != 35 && ch2 != 33 && ch2 != 37))))) : !(ch2 < 32 || ch2 > 126)) {
                sbuf.append((char) ch2);
            } else {
                int pos = sbuf.length();
                int nbytes = 0;
                int i2 = 128;
                int i3 = 1;
                if (ch2 >= 128 && ch2 >= 2048 && ch2 < 65536) {
                }
                while (true) {
                    int availbits = nbytes == 0 ? 7 : 6 - nbytes;
                    if (ch2 < (i3 << availbits)) {
                        b = ch2;
                        if (nbytes > 0) {
                            b |= (65408 >> nbytes) & 255;
                        }
                        ch2 = 0;
                    } else {
                        int b2 = ch2 & 63;
                        b = b2 | i2;
                        ch2 >>= 6;
                    }
                    nbytes++;
                    int j = 0;
                    while (j <= i3) {
                        int hex = b & 15;
                        sbuf.insert(pos, (char) (hex <= 9 ? hex + 48 : (hex - 10) + 65));
                        b >>= 4;
                        j++;
                        i3 = 1;
                    }
                    sbuf.insert(pos, '%');
                    if (ch2 == 0) {
                        break;
                    }
                    i2 = 128;
                    i3 = 1;
                }
            }
        }
        return sbuf.toString();
    }
}
