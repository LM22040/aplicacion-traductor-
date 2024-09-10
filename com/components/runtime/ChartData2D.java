package com.google.appinventor.components.runtime;

import android.util.Log;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import gnu.lists.LList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

@DesignerComponent(category = ComponentCategory.CHARTS, description = "A component that holds (x, y)-coordinate based data", iconName = "images/web.png", version = 1)
@SimpleObject
/* loaded from: classes.dex */
public final class ChartData2D extends ChartDataBase {
    public ChartData2D(Chart chartContainer) {
        super(chartContainer);
    }

    @SimpleFunction
    public void AddEntry(final String x, final String y) {
        synchronized (this) {
            final AtomicBoolean isDone = new AtomicBoolean(false);
            this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.ChartData2D.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        YailList pair = YailList.makeList(Arrays.asList(x, y));
                        ((ChartDataModel) ChartData2D.this.dataModel).addEntryFromTuple(pair);
                        ChartData2D.this.onDataChange();
                        synchronized (ChartData2D.this) {
                            isDone.set(true);
                            ChartData2D.this.notifyAll();
                        }
                    } catch (Throwable th) {
                        synchronized (ChartData2D.this) {
                            isDone.set(true);
                            ChartData2D.this.notifyAll();
                            throw th;
                        }
                    }
                }
            });
            try {
                if (!isDone.get()) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    @SimpleFunction
    public void RemoveEntry(final String x, final String y) {
        synchronized (this) {
            final AtomicBoolean isDone = new AtomicBoolean(false);
            this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.ChartData2D.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        YailList pair = YailList.makeList(Arrays.asList(x, y));
                        float xValue = Float.parseFloat(x);
                        float yValue = Float.parseFloat(y);
                        Entry currEntry = new Entry(xValue, yValue);
                        int index = ((ChartDataModel) ChartData2D.this.dataModel).findEntryIndex(currEntry);
                        ((ChartDataModel) ChartData2D.this.dataModel).removeEntryFromTuple(pair);
                        ChartData2D.this.resetHighlightAtIndex(index);
                        ChartData2D.this.onDataChange();
                        synchronized (ChartData2D.this) {
                            isDone.set(true);
                            ChartData2D.this.notifyAll();
                        }
                    } catch (Throwable th) {
                        synchronized (ChartData2D.this) {
                            isDone.set(true);
                            ChartData2D.this.notifyAll();
                            throw th;
                        }
                    }
                }
            });
            try {
                if (!isDone.get()) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    @SimpleFunction(description = "Checks whether an (x, y) entry exists in the Coordinate Data.Returns true if the Entry exists, and false otherwise.")
    public boolean DoesEntryExist(final String x, final String y) {
        try {
            return ((Boolean) this.threadRunner.submit(new Callable<Boolean>() { // from class: com.google.appinventor.components.runtime.ChartData2D.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Boolean call() {
                    YailList pair = YailList.makeList(Arrays.asList(x, y));
                    return Boolean.valueOf(((ChartDataModel) ChartData2D.this.dataModel).doesEntryExist(pair));
                }
            }).get()).booleanValue();
        } catch (InterruptedException e) {
            Log.e(getClass().getName(), e.getMessage());
            return false;
        } catch (ExecutionException e2) {
            Log.e(getClass().getName(), e2.getMessage());
            return false;
        }
    }

    @SimpleFunction(description = "Draws the corresponding line of best fit on the graph")
    @Deprecated
    public void DrawLineOfBestFit(YailList xList, YailList yList) {
        List<?> predictions = (List) Regression.computeLineOfBestFit(castToDouble((LList) xList.getCdr()), castToDouble((LList) yList.getCdr())).get("predictions");
        List<List<?>> predictionPairs = new ArrayList<>();
        List<?> xValues = (List) xList.getCdr();
        for (int i = 0; i < xValues.size(); i++) {
            predictionPairs.add(Arrays.asList(xValues.get(i), predictions.get(i)));
        }
        YailList predictionPairsList = YailList.makeList((List) predictionPairs);
        ((ChartDataModel) this.dataModel).importFromList(predictionPairsList);
        if (((ChartDataModel) this.dataModel).getDataset() instanceof LineDataSet) {
            ((ChartDataModel) this.dataModel).getDataset().setDrawCircles(false);
            ((ChartDataModel) this.dataModel).getDataset().setDrawValues(false);
        }
        onDataChange();
    }

    /* loaded from: classes.dex */
    private static class AnomalyManager {
        Set<Integer> indexes;
        Set<Double> xValues;

        private AnomalyManager() {
            this.indexes = new HashSet();
            this.xValues = new HashSet();
        }

        public String toString() {
            return "{indexes: " + this.indexes + ", xValues: " + this.xValues + "}";
        }
    }

    @SimpleFunction(description = "Highlights data points of choice on the Chart in the color of choice. This block expects a list of data points, each data point is an index, value pair")
    public void HighlightDataPoints(YailList dataPoints, int color) {
        List<?> dataPointsList = (LList) dataPoints.getCdr();
        if (!dataPoints.isEmpty()) {
            List<?> entries = ((ChartDataModel) this.dataModel).getEntries();
            java.util.Map<Double, AnomalyManager> anomalyMap = new HashMap<>();
            int i = 0;
            for (Entry entry : ((ChartDataModel) this.dataModel).getEntries()) {
                if (!anomalyMap.containsKey(Double.valueOf(entry.getY()))) {
                    anomalyMap.put(Double.valueOf(entry.getY()), new AnomalyManager());
                }
                anomalyMap.get(Double.valueOf(entry.getY())).xValues.add(Double.valueOf(entry.getX()));
                anomalyMap.get(Double.valueOf(entry.getY())).indexes.add(Integer.valueOf(i));
                i++;
            }
            int[] highlights = new int[entries.size()];
            Arrays.fill(highlights, ((ChartDataModel) this.dataModel).getDataset().getColor());
            for (Object dataPoint : dataPointsList) {
                if (dataPoint instanceof YailList) {
                    Number y = (Number) ((YailList) dataPoint).getObject(1);
                    AnomalyManager anomalyManager = anomalyMap.get(Double.valueOf(y.doubleValue()));
                    if (anomalyManager != null) {
                        Number x = (Number) ((YailList) dataPoint).getObject(0);
                        if (anomalyManager.xValues.contains(Double.valueOf(x.doubleValue())) || anomalyManager.indexes.contains(Integer.valueOf(x.intValue() - 1))) {
                            for (Integer index : anomalyManager.indexes) {
                                highlights[index.intValue()] = color;
                            }
                        }
                    }
                }
            }
            ((ChartDataModel) this.dataModel).getDataset().setCircleColors(highlights);
            onDataChange();
            return;
        }
        throw new IllegalStateException("Anomalies list is Empty. Nothing to highlight!");
    }

    public List<Double> getYValues() {
        List<Double> yValues = new ArrayList<>();
        for (Entry entry : ((ChartDataModel) this.dataModel).getEntries()) {
            yValues.add(Double.valueOf(entry.getY()));
        }
        return yValues;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetHighlightAtIndex(int index) {
        List<Integer> defaultColors = ((ChartDataModel) this.dataModel).getDataset().getCircleColors();
        defaultColors.remove(index);
    }
}
