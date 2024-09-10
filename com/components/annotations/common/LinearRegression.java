package com.google.appinventor.components.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LinearRegression implements TrendlineCalculator {
    @Override // com.google.appinventor.components.common.TrendlineCalculator
    public Map<String, Object> compute(List<Double> x, List<Double> y) {
        if (x.isEmpty() || y.isEmpty()) {
            throw new IllegalStateException("List must have at least one element");
        }
        if (x.size() != y.size()) {
            throw new IllegalStateException("Must have equal X and Y data points");
        }
        int n = x.size();
        double sumx = 0.0d;
        double sumy = 0.0d;
        double sumXY = 0.0d;
        double squareSumX = 0.0d;
        double squareSumY = 0.0d;
        for (int i = 0; i < n; i++) {
            sumx += x.get(i).doubleValue();
            sumXY += x.get(i).doubleValue() * y.get(i).doubleValue();
            sumy += y.get(i).doubleValue();
            squareSumX += x.get(i).doubleValue() * x.get(i).doubleValue();
            squareSumY += y.get(i).doubleValue() * y.get(i).doubleValue();
        }
        double d = n;
        Double.isNaN(d);
        double xmean = sumx / d;
        double squareSumY2 = squareSumY;
        double squareSumY3 = n;
        Double.isNaN(squareSumY3);
        double ymean = sumy / squareSumY3;
        double xxmean = 0.0d;
        double xymean = 0.0d;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < n; i2++) {
            xxmean += (x.get(i2).doubleValue() - xmean) * (x.get(i2).doubleValue() - xmean);
            xymean += (x.get(i2).doubleValue() - xmean) * (y.get(i2).doubleValue() - ymean);
        }
        double slope = xymean / xxmean;
        double intercept = ymean - (slope * xmean);
        for (Double value : x) {
            double prediction = (value.doubleValue() * slope) + intercept;
            arrayList.add(Double.valueOf(prediction));
            ymean = ymean;
        }
        double xmean2 = n;
        Double.isNaN(xmean2);
        double d2 = (xmean2 * sumXY) - (sumx * sumy);
        double sumXY2 = n;
        Double.isNaN(sumXY2);
        double d3 = (sumXY2 * squareSumX) - (sumx * sumx);
        double sumx2 = n;
        Double.isNaN(sumx2);
        double corr = d2 / Math.sqrt(d3 * ((sumx2 * squareSumY2) - (sumy * sumy)));
        Map<String, Object> resultDic = new HashMap<>();
        resultDic.put("slope", Double.valueOf(slope));
        resultDic.put("Yintercept", Double.valueOf(intercept));
        resultDic.put("correlation coefficient", Double.valueOf(corr));
        resultDic.put("predictions", arrayList);
        resultDic.put("Xintercepts", Double.valueOf(slope == 0.0d ? Double.NaN : (-intercept) / slope));
        resultDic.put("r^2", Double.valueOf(corr * corr));
        return resultDic;
    }

    @Override // com.google.appinventor.components.common.TrendlineCalculator
    public float[] computePoints(Map<String, Object> results, float xMin, float xMax, int viewWidth, int steps) {
        if (!results.containsKey("slope")) {
            return new float[0];
        }
        double m = ((Double) results.get("slope")).doubleValue();
        double b = ((Double) results.get("Yintercept")).doubleValue();
        double d = xMin;
        Double.isNaN(d);
        double d2 = xMax;
        Double.isNaN(d2);
        return new float[]{xMin, (float) ((d * m) + b), xMax, (float) ((d2 * m) + b)};
    }
}
