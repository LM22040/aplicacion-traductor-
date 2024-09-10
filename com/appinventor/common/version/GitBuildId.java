package com.google.appinventor.common.version;

/* loaded from: classes.dex */
public final class GitBuildId {
    public static final String ACRA_URI = "${acra.uri}";
    public static final String ANT_BUILD_DATE = "August 18 2024";
    public static final String GIT_BUILD_FINGERPRINT = "a32390e0273ad4a8267f47867af12cfd779b6d6b";
    public static final String GIT_BUILD_VERSION = "nb198";

    private GitBuildId() {
    }

    public static String getVersion() {
        if (GIT_BUILD_VERSION != "" && !GIT_BUILD_VERSION.contains(" ")) {
            return GIT_BUILD_VERSION;
        }
        return "none";
    }

    public static String getFingerprint() {
        return GIT_BUILD_FINGERPRINT;
    }

    public static String getDate() {
        return ANT_BUILD_DATE;
    }

    public static String getAcraUri() {
        if (ACRA_URI.equals(ACRA_URI)) {
            return "";
        }
        return ACRA_URI.trim();
    }
}
