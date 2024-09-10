package com.google.appinventor.components.annotations;

/* loaded from: classes.dex */
public enum PropertyCategory {
    BEHAVIOR("Behavior"),
    APPEARANCE("Appearance"),
    DEPRECATED("Deprecated"),
    UNSET("Unspecified"),
    APPLICATION("Application"),
    ADVANCED("Advanced"),
    GENERAL("General"),
    THEMING("Theming"),
    PUBLISHING("Publishing");

    private final String name;

    PropertyCategory(String categoryName) {
        this.name = categoryName;
    }

    public String getName() {
        return this.name;
    }
}
