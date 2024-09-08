package androidx.core.util;

import android.os.Build;
import androidx.core.graphics.ColorUtils$Api26Impl$$ExternalSyntheticBackport0;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ObjectsCompat {
    private ObjectsCompat() {
    }

    public static boolean equals(Object a, Object b) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Api19Impl.equals(a, b);
        }
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(Object o) {
        if (o != null) {
            return o.hashCode();
        }
        return 0;
    }

    public static int hash(Object... values) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Api19Impl.hash(values);
        }
        return Arrays.hashCode(values);
    }

    public static String toString(Object o, String nullDefault) {
        return o != null ? o.toString() : nullDefault;
    }

    public static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
        return obj;
    }

    /* loaded from: classes2.dex */
    static class Api19Impl {
        private Api19Impl() {
        }

        static boolean equals(Object a, Object b) {
            return ColorUtils$Api26Impl$$ExternalSyntheticBackport0.m(a, b);
        }

        static int hash(Object... values) {
            return Arrays.hashCode(values);
        }
    }
}
