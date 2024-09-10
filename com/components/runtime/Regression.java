package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.LOBFValues;
import com.google.appinventor.components.common.LinearRegression;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import gnu.lists.LList;
import java.util.List;
import kawa.lang.SyntaxForms;

@DesignerComponent(category = ComponentCategory.DATASCIENCE, description = "A component that contains regression models", iconName = "images/regression.png", nonVisible = SyntaxForms.DEBUGGING, version = 2)
@SimpleObject
/* loaded from: classes.dex */
public final class Regression extends DataCollection<ComponentContainer, DataModel<?>> {
    private static final LinearRegression LINEAR_REGRESSION = new LinearRegression();

    public Regression(ComponentContainer container) {
        super(container);
    }

    public static YailDictionary computeLineOfBestFit(List<Double> x, List<Double> y) {
        return new YailDictionary(LINEAR_REGRESSION.compute(x, y));
    }

    @SimpleFunction(description = "Returns one of the Line of Best Fit values. A value could be\"slope\", \"Yintercept\", \"correlation coefficient\"or \"predictions\". The block returns the complete dictionary with all values if no specific value string is provided")
    public Object CalculateLineOfBestFitValue(YailList xList, YailList yList, @Options(LOBFValues.class) String value) {
        YailDictionary result = computeLineOfBestFit(castToDouble((LList) xList.getCdr()), castToDouble((LList) yList.getCdr()));
        if (result.containsKey(value)) {
            return result.get(value);
        }
        return result;
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
