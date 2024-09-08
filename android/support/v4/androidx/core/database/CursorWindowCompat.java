package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;

/* loaded from: classes2.dex */
public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    public static CursorWindow create(String name, long windowSizeBytes) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.createCursorWindow(name, windowSizeBytes);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            return Api15Impl.createCursorWindow(name);
        }
        return new CursorWindow(false);
    }

    /* loaded from: classes2.dex */
    static class Api28Impl {
        private Api28Impl() {
        }

        static CursorWindow createCursorWindow(String name, long windowSizeBytes) {
            return new CursorWindow(name, windowSizeBytes);
        }
    }

    /* loaded from: classes2.dex */
    static class Api15Impl {
        private Api15Impl() {
        }

        static CursorWindow createCursorWindow(String name) {
            return new CursorWindow(name);
        }
    }
}
