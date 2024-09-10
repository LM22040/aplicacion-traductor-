package com.google.appinventor.components.runtime.util;

import android.os.Build;
import android.os.Environment;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Form;

/* loaded from: classes.dex */
public class RUtil {
    public static boolean needsFilePermission(Form form, String path, FileScope fileScope) {
        if (fileScope != null) {
            switch (AnonymousClass1.$SwitchMap$com$google$appinventor$components$common$FileScope[fileScope.ordinal()]) {
                case 1:
                    return Build.VERSION.SDK_INT < 19;
                case 2:
                    return form.isRepl() && Build.VERSION.SDK_INT < 19;
                case 3:
                    return true;
                default:
                    return false;
            }
        }
        if (path.startsWith("//")) {
            return false;
        }
        if (!path.startsWith("/") && !path.startsWith("file:")) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 8) {
            return FileUtil.isExternalStorageUri(form, path) && !FileUtil.isAppSpecificExternalUri(form, path);
        }
        String fpath = path;
        if (path.startsWith("file:")) {
            fpath = path.substring(5);
        }
        return fpath.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.appinventor.components.runtime.util.RUtil$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$appinventor$components$common$FileScope;

        static {
            int[] iArr = new int[FileScope.values().length];
            $SwitchMap$com$google$appinventor$components$common$FileScope = iArr;
            try {
                iArr[FileScope.App.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$common$FileScope[FileScope.Asset.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$common$FileScope[FileScope.Shared.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }
}
