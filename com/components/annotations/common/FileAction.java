package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum FileAction implements OptionList<String> {
    PickExistingFile("Pick Existing File"),
    PickNewFile("Pick New File"),
    PickDirectory("Pick Directory");

    private static final Map<String, FileAction> LOOKUP = new HashMap();
    private final String value;

    static {
        for (FileAction action : values()) {
            LOOKUP.put(action.value, action);
        }
    }

    FileAction(String value) {
        this.value = value;
    }

    @Override // com.google.appinventor.components.common.OptionList
    public String toUnderlyingValue() {
        return this.value;
    }

    public static FileAction fromUnderlyingValue(String value) {
        return LOOKUP.get(value);
    }
}
