package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum BestFitModel implements OptionList<String> {
    Linear("Linear"),
    Quadratic("Quadratic"),
    Exponential("Exponential"),
    Logarithmic("Logarithmic");

    private static final Map<String, BestFitModel> lookup = new HashMap();
    private final String value;

    static {
        for (BestFitModel model : values()) {
            lookup.put(model.toUnderlyingValue(), model);
        }
    }

    BestFitModel(String value) {
        this.value = value;
    }

    @Override // com.google.appinventor.components.common.OptionList
    public String toUnderlyingValue() {
        return this.value;
    }

    public static BestFitModel fromUnderlyingValue(String model) {
        return lookup.get(model);
    }
}
