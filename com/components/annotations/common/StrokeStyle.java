package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum StrokeStyle implements OptionList<Integer> {
    Solid(1),
    Dashed(2),
    Dotted(3);

    private static final Map<Integer, StrokeStyle> lookup = new HashMap();
    private final int value;

    static {
        for (StrokeStyle style : values()) {
            lookup.put(style.toUnderlyingValue(), style);
        }
    }

    StrokeStyle(int value) {
        this.value = value;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.appinventor.components.common.OptionList
    public Integer toUnderlyingValue() {
        return Integer.valueOf(this.value);
    }

    public static StrokeStyle fromUnderlyingValue(Integer style) {
        return lookup.get(style);
    }

    public static StrokeStyle fromUnderlyingValue(String style) {
        return lookup.get(Integer.valueOf(Integer.parseInt(style)));
    }
}
