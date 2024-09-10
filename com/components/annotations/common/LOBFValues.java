package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public enum LOBFValues implements OptionList<String> {
    CorrCoef("correlation coefficient"),
    Slope("slope"),
    Yintercept("Yintercept"),
    Predictions("predictions"),
    AllValues("all values"),
    QuadraticCoefficient("Quadratic Coefficient"),
    LinearCoefficient("slope"),
    ExponentialCoefficient("a"),
    ExponentialBase("b"),
    LogarithmCoefficient("b"),
    LogarithmConstant("a"),
    XIntercepts("Xintercepts"),
    RSquared("r^2");

    private static final Map<String, LOBFValues> lookup = new HashMap();
    private final String lobfValues;

    static {
        for (LOBFValues value : values()) {
            lookup.put(value.toUnderlyingValue(), value);
        }
    }

    LOBFValues(String lobfV) {
        this.lobfValues = lobfV;
    }

    @Override // com.google.appinventor.components.common.OptionList
    public String toUnderlyingValue() {
        return this.lobfValues;
    }

    public static LOBFValues fromUnderlyingValue(String value) {
        return lookup.get(value);
    }
}
