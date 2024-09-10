package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class QuadraticRegression extends OlsTrendLine {
    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected double[] xVector(double x) {
        return new double[]{1.0d, x, x * x};
    }

    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected boolean logY() {
        return false;
    }

    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine
    protected int size() {
        return 3;
    }

    @Override // com.google.appinventor.components.runtime.util.OlsTrendLine, com.google.appinventor.components.common.TrendlineCalculator
    public Map<String, Object> compute(List<Double> x, List<Double> y) {
        Map<String, Object> result = super.compute(x, y);
        result.put("Yintercept", result.remove("intercept"));
        double a = ((Double) result.get("x^2")).doubleValue();
        double b = ((Double) result.get("slope")).doubleValue();
        double c = ((Double) result.get("Yintercept")).doubleValue();
        double discriminant = (b * b) - ((4.0d * a) * c);
        if (discriminant > 0.0d) {
            List<Double> intercepts = new ArrayList<>();
            double sqrtDiscriminant = Math.sqrt(discriminant);
            double c2 = -b;
            intercepts.add(Double.valueOf((c2 + sqrtDiscriminant) / (a * 2.0d)));
            intercepts.add(Double.valueOf(((-b) - sqrtDiscriminant) / (2.0d * a)));
            result.put("Xintercepts", intercepts);
        } else if (discriminant == 0.0d) {
            result.put("Xintercepts", Double.valueOf((-b) / (2.0d * a)));
        } else {
            result.put("Xintercepts", Double.valueOf(Double.NaN));
        }
        return result;
    }

    @Override // com.google.appinventor.components.common.TrendlineCalculator
    public float[] computePoints(Map<String, Object> results, float xMin, float xMax, int viewWidth, int steps) {
        int i = steps;
        if (!results.containsKey("x^2")) {
            return new float[0];
        }
        double a = ((Double) results.get("x^2")).doubleValue();
        double b = ((Double) results.get("slope")).doubleValue();
        double c = ((Double) results.get("Yintercept")).doubleValue();
        float[] result = new float[i * 4];
        float lastX = Float.NEGATIVE_INFINITY;
        float lastY = Float.NEGATIVE_INFINITY;
        boolean first = true;
        int i2 = 0;
        while (i2 < i) {
            if (first) {
                lastX = xMin + ((i2 * (xMax - xMin)) / i);
                double d = lastX;
                Double.isNaN(d);
                double d2 = lastX;
                Double.isNaN(d2);
                lastY = (float) ((((d * a) + b) * d2) + c);
                first = false;
            }
            result[i2 * 4] = lastX;
            result[(i2 * 4) + 1] = lastY;
            float newX = xMin + (((i2 + 1) * (xMax - xMin)) / i);
            double d3 = newX;
            Double.isNaN(d3);
            double d4 = newX;
            Double.isNaN(d4);
            float newY = (float) ((((d3 * a) + b) * d4) + c);
            result[(i2 * 4) + 2] = newX;
            result[(i2 * 4) + 3] = newY;
            lastX = newX;
            lastY = newY;
            i2++;
            i = steps;
        }
        return result;
    }
}
