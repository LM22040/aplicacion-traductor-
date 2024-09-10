package com.google.appinventor.components.runtime;

import com.github.mikephil.charting.data.Entry;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import gnu.lists.LList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kawa.lang.SyntaxForms;

@DesignerComponent(category = ComponentCategory.DATASCIENCE, description = "A component that contains anomaly detection models", iconName = "images/anomaly.png", nonVisible = SyntaxForms.DEBUGGING, version = 2)
@SimpleObject
/* loaded from: classes.dex */
public final class AnomalyDetection extends DataCollection<ComponentContainer, DataModel<?>> {
    public AnomalyDetection(ComponentContainer container) {
        super(container);
    }

    @SimpleFunction(description = "Z-Score Anomaly Detection: checks each data point's Z-scoreagainst the given threshold if a data point's Z-score is greater than the threshold, the data point is labeled as anomaly and returned in a list of pairs (anomaly index, anomaly value)")
    public List<List<?>> DetectAnomalies(YailList dataList, double threshold) {
        List<List<?>> anomalies = new ArrayList<>();
        LList dataListValues = (LList) dataList.getCdr();
        List<Double> data = castToDouble(dataListValues);
        double sum = 0.0d;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i).doubleValue();
        }
        int i2 = data.size();
        double d = i2;
        Double.isNaN(d);
        double mean = sum / d;
        double variance = 0.0d;
        for (int i3 = 0; i3 < data.size(); i3++) {
            variance += Math.pow(data.get(i3).doubleValue() - mean, 2.0d);
        }
        int i4 = data.size();
        double d2 = i4;
        Double.isNaN(d2);
        double sd = Math.sqrt(variance / d2);
        for (int i5 = 0; i5 < data.size(); i5++) {
            double zScore = Math.abs((data.get(i5).doubleValue() - mean) / sd);
            if (zScore > threshold) {
                anomalies.add(Arrays.asList(Integer.valueOf(i5 + 1), data.get(i5)));
            }
        }
        return anomalies;
    }

    @SimpleFunction
    public List<List<?>> DetectAnomaliesInChartData(ChartData2D chartData, double threshold) {
        List<?> dataValue = chartData.getDataValue((Object) null);
        double sum = 0.0d;
        Iterator<?> it = dataValue.iterator();
        while (it.hasNext()) {
            double y = ((Entry) it.next()).getY();
            Double.isNaN(y);
            sum += y;
        }
        double size = dataValue.size();
        Double.isNaN(size);
        double mean = sum / size;
        double variance = 0.0d;
        Iterator<?> it2 = dataValue.iterator();
        while (it2.hasNext()) {
            double y2 = ((Entry) it2.next()).getY();
            Double.isNaN(y2);
            variance += Math.pow(y2 - mean, 2.0d);
        }
        double size2 = dataValue.size();
        Double.isNaN(size2);
        double sd = Math.sqrt(variance / size2);
        List<List<?>> anomalies = new ArrayList<>();
        Iterator<?> it3 = dataValue.iterator();
        while (it3.hasNext()) {
            Entry entry = (Entry) it3.next();
            double y3 = entry.getY();
            Double.isNaN(y3);
            double zScore = Math.abs((y3 - mean) / sd);
            if (zScore > threshold) {
                anomalies.add(Arrays.asList(Float.valueOf(entry.getX()), Float.valueOf(entry.getY())));
            }
        }
        return anomalies;
    }

    @SimpleFunction(description = "Given a single anomaly and the x and y values of your data. This block will return the x and y value pairs of your data without the anomaly")
    public List<List<?>> CleanData(YailList anomaly, YailList xList, YailList yList) {
        LList xValues = (LList) xList.getCdr();
        List<Double> xData = castToDouble(xValues);
        LList yValues = (LList) yList.getCdr();
        List<Double> yData = castToDouble(yValues);
        if (xData.size() != yData.size()) {
            throw new IllegalStateException("Must have equal X and Y data points");
        }
        if (xData.size() == 0 || yData.size() == 0) {
            throw new IllegalStateException("List must have at least one element");
        }
        int index = (int) getAnomalyIndex(anomaly);
        xData.remove(index - 1);
        yData.remove(index - 1);
        List<List<?>> cleanData = new ArrayList<>();
        if (xData.size() == yData.size()) {
            for (int i = 0; i < xData.size(); i++) {
                cleanData.add(YailList.makeList(Arrays.asList(xData.get(i), yData.get(i))));
            }
        }
        return cleanData;
    }

    public static double getAnomalyIndex(YailList anomaly) {
        if (!anomaly.isEmpty()) {
            LList anomalyValue = (LList) anomaly.getCdr();
            List<Double> anomalyNr = castToDouble(anomalyValue);
            return anomalyNr.get(0).doubleValue();
        }
        throw new IllegalStateException("Must have equal X and Y data points");
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void ElementsFromPairs(String elements) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void SpreadsheetUseHeaders(boolean useHeaders) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void DataFileXColumn(String column) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void WebXColumn(String column) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void SpreadsheetXColumn(String column) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void DataFileYColumn(String column) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void WebYColumn(String column) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void SpreadsheetYColumn(String column) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void DataSourceKey(String key) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public <K, V> void Source(DataSource<K, V> dataSource) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void ImportFromList(YailList list) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void Clear() {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public <K, V> void ChangeDataSource(DataSource<K, V> source, String keyValue) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void RemoveDataSource() {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public YailList GetEntriesWithXValue(String x) {
        return YailList.makeEmptyList();
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public YailList GetEntriesWithYValue(String y) {
        return YailList.makeEmptyList();
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public YailList GetAllEntries() {
        return YailList.makeEmptyList();
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void ImportFromTinyDB(TinyDB tinyDB, String tag) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void ImportFromCloudDB(CloudDB cloudDB, String tag) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void ImportFromDataFile(DataFile dataFile, String xValueColumn, String yValueColumn) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void ImportFromSpreadsheet(Spreadsheet spreadsheet, String xColumn, String yColumn, boolean useHeaders) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void ImportFromWeb(Web web, String xValueColumn, String yValueColumn) {
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void onDataChange() {
    }
}
