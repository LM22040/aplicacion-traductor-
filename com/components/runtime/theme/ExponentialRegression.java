package com.google.appinventor.components.runtime.util;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ExponentialRegression extends OlsTrendLine {
    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected double[] xVector(double x) {
        return new double[]{1.0d, x};
    }

    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected boolean logY() {
        return true;
    }

    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected int size() {
        return 2;
    }

    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine, com.google.appinventor.components.common.TrendlineCalculator
    public Map<String, Object> compute(List<Double> x, List<Double> y) {
        Map<String, Object> result = super.compute(x, y);
        result.remove("x^2");
        double m = ((Double) result.remove("slope")).doubleValue();
        double i = ((Double) result.remove("intercept")).doubleValue();
        result.put("a", Double.valueOf(Math.exp(i)));
        result.put("b", Double.valueOf(Math.exp(m)));
        return result;
    }

    @Override // com.google.appinventor.components.common.TrendlineCalculator
    public float[] computePoints(Map<String, Object> results, float xMin, float xMax, int viewWidth, int steps) {
        if (!results.containsKey("a")) {
            return new float[0];
        }
        double a = ((Double) results.get("a")).doubleValue();
        double b = ((Double) results.get("b")).doubleValue();
        float[] result = new float[steps * 4];
        float lastX = Float.NEGATIVE_INFINITY;
        float lastY = Float.NEGATIVE_INFINITY;
        boolean first = true;
        for (int i = 0; i < steps; i++) {
            if (first) {
                first = false;
                lastX = xMin + ((i * (xMax - xMin)) / steps);
                lastY = (float) (Math.pow(b, lastX) * a);
            }
            result[i * 4] = lastX;
            result[(i * 4) + 1] = lastY;
            float newX = xMin + (((i + 1) * (xMax - xMin)) / steps);
            float newY = (float) (Math.pow(b, newX) * a);
            result[(i * 4) + 2] = newX;
            result[(i * 4) + 3] = newY;
            lastX = newX;
            lastY = newY;
        }
        return result;
    }
}
