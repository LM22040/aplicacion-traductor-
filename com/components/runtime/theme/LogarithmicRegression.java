package com.google.appinventor.components.runtime.util;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LogarithmicRegression extends OlsTrendLine {
    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected double[] xVector(double x) {
        return new double[]{1.0d, Math.log(x)};
    }

    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected boolean logY() {
        return false;
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
        result.put("a", Double.valueOf(i));
        result.put("b", Double.valueOf(m));
        return result;
    }

    @Override // com.google.appinventor.components.common.TrendlineCalculator
    public float[] computePoints(Map<String, Object> results, float xMin, float xMax, int viewWidth, int steps) {
        float xMin2;
        if (!results.containsKey("b")) {
            return new float[0];
        }
        if (xMax <= 0.0f) {
            return new float[0];
        }
        if (xMin > 0.0f) {
            xMin2 = xMin;
        } else {
            xMin2 = Math.min(1.0E-4f, xMax / (steps + 1));
        }
        double m = ((Double) results.get("b")).doubleValue();
        double b = ((Double) results.get("a")).doubleValue();
        float[] result = new float[steps * 4];
        float lastX = Float.NEGATIVE_INFINITY;
        float lastY = Float.NEGATIVE_INFINITY;
        boolean first = true;
        for (int i = 0; i < steps; i++) {
            if (first) {
                first = false;
                lastX = xMin2 + ((i * (xMax - xMin2)) / steps);
                lastY = (float) ((Math.log(lastX) * m) + b);
            }
            result[i * 4] = lastX;
            result[(i * 4) + 1] = lastY;
            float newX = (((i + 1) * (xMax - xMin2)) / steps) + xMin2;
            float newY = (float) ((Math.log(newX) * m) + b);
            result[(i * 4) + 2] = newX;
            result[(i * 4) + 3] = newY;
            lastX = newX;
            lastY = newY;
        }
        return result;
    }
}
