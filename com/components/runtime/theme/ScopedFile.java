package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Form;
import java.io.File;
import java.net.URI;

/* loaded from: classes.dex */
public class ScopedFile {
    private final String fileName;
    private final FileScope scope;

    public ScopedFile(FileScope scope, String fileName) {
        if (fileName.startsWith("//")) {
            scope = FileScope.Asset;
            fileName = fileName.substring(2);
        } else if (!fileName.startsWith("/") && scope == FileScope.Legacy) {
            scope = FileScope.Private;
        } else if (fileName.startsWith("/") && scope != FileScope.Legacy) {
            fileName = fileName.substring(1);
        }
        this.scope = scope;
        this.fileName = fileName;
    }

    public FileScope getScope() {
        return this.scope;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int hashCode() {
        return (this.scope.hashCode() * 37) + this.fileName.hashCode();
    }

    public boolean equals(Object obj) {
        String str;
        if (!(obj instanceof ScopedFile)) {
            return false;
        }
        ScopedFile other = (ScopedFile) obj;
        if (this.scope != other.scope) {
            return false;
        }
        String str2 = this.fileName;
        if (str2 == null && other.fileName == null) {
            return true;
        }
        if (str2 == null || (str = other.fileName) == null) {
            return false;
        }
        return str2.equals(str);
    }

    public File resolve(Form form) {
        return new File(URI.create(FileUtil.resolveFileName(form, this.fileName, this.scope)));
    }

    public String toString() {
        return "ScopedFile{scope=" + this.scope + ", fileName='" + this.fileName + "'}";
    }
}
