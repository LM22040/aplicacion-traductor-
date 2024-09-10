package com.google.appinventor.components.runtime;

import android.util.Log;
import androidx.constraintlayout.solver.LinearSystem;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.DataModel;
import com.google.appinventor.components.runtime.util.CsvUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SimpleObject
/* loaded from: classes.dex */
public abstract class DataCollection<C extends ComponentContainer, M extends DataModel<?>> implements Component, DataSource<Object, List<?>>, DataSourceChangeListener {
    protected final C container;
    protected List<String> dataFileColumns;
    protected M dataModel;
    private DataSource<?, ?> dataSource;
    protected String dataSourceKey;
    private String elements;
    private Object lastDataSourceValue;
    protected List<String> sheetsColumns;
    protected ExecutorService threadRunner;
    protected boolean useSheetHeaders;
    protected List<String> webColumns;
    protected final Set<DataSourceChangeListener> listeners = new HashSet();
    private boolean initialized = false;
    private int tick = 0;

    public abstract void onDataChange();

    public DataCollection(C container) {
        this.container = container;
        DataSourceKey("");
        this.threadRunner = Executors.newSingleThreadExecutor();
        this.dataFileColumns = Arrays.asList("", "");
        this.sheetsColumns = Arrays.asList("", "");
        this.webColumns = Arrays.asList("", "");
    }

    public void setExecutorService(ExecutorService service) {
        this.threadRunner = service;
    }

    public void addDataSourceChangeListener(DataSourceChangeListener listener) {
        this.listeners.add(listener);
        listener.onDataSourceValueChange(this, null, null);
    }

    public void removeDataSourceChangeListener(DataSourceChangeListener listener) {
        this.listeners.remove(listener);
    }

    @Override // com.google.appinventor.components.runtime.DataSource
    public List<?> getDataValue(Object key) {
        return this.dataModel.entries;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void ElementsFromPairs(final String elements) {
        this.elements = elements;
        if (elements == null || elements.equals("") || !this.initialized) {
            return;
        }
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.1
            @Override // java.lang.Runnable
            public void run() {
                DataCollection.this.dataModel.setElements(elements);
                DataCollection.this.onDataChange();
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void SpreadsheetUseHeaders(boolean useHeaders) {
        this.useSheetHeaders = useHeaders;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_DATA_FILE_COLUMN)
    public void DataFileXColumn(String column) {
        this.dataFileColumns.set(0, column);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the column to parse from the attached Web component for the x values. If a column is not specified, default values for the x values will be generated instead.", userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void WebXColumn(String column) {
        this.webColumns.set(0, column);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void SpreadsheetXColumn(String column) {
        this.sheetsColumns.set(0, column);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_DATA_FILE_COLUMN)
    public void DataFileYColumn(String column) {
        this.dataFileColumns.set(1, column);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the column to parse from the attached Web component for the y values. If a column is not specified, default values for the y values will be generated instead.", userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void WebYColumn(String column) {
        this.webColumns.set(1, column);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void SpreadsheetYColumn(String column) {
        this.sheetsColumns.set(1, column);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void DataSourceKey(String key) {
        this.dataSourceKey = key;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_CHART_DATA_SOURCE)
    public <K, V> void Source(DataSource<K, V> dataSource) {
        DataSource<?, ?> dataSource2 = this.dataSource;
        if (dataSource2 != dataSource && (dataSource2 instanceof ObservableDataSource)) {
            ((ObservableDataSource) dataSource2).removeDataObserver(this);
        }
        this.dataSource = dataSource;
        if (this.initialized) {
            if (dataSource instanceof ObservableDataSource) {
                ((ObservableDataSource) dataSource).addDataObserver(this);
                if (this.dataSourceKey == null) {
                    return;
                }
            }
            if (dataSource instanceof DataFile) {
                importFromDataFileAsync((DataFile) dataSource, YailList.makeList((List) this.dataFileColumns));
                return;
            }
            if (dataSource instanceof TinyDB) {
                ImportFromTinyDB((TinyDB) dataSource, this.dataSourceKey);
                return;
            }
            if (dataSource instanceof CloudDB) {
                ImportFromCloudDB((CloudDB) dataSource, this.dataSourceKey);
            } else if (dataSource instanceof Spreadsheet) {
                importFromSpreadsheetAsync((Spreadsheet) dataSource, YailList.makeList((List) this.sheetsColumns), this.useSheetHeaders);
            } else if (dataSource instanceof Web) {
                importFromWebAsync((Web) dataSource, YailList.makeList((List) this.webColumns));
            }
        }
    }

    @SimpleFunction
    public void ImportFromList(final YailList list) {
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.2
            @Override // java.lang.Runnable
            public void run() {
                DataCollection.this.dataModel.importFromList(list);
                DataCollection.this.onDataChange();
            }
        });
    }

    @SimpleFunction(description = "Clears all of the data.")
    public void Clear() {
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.3
            @Override // java.lang.Runnable
            public void run() {
                DataCollection.this.dataModel.clearEntries();
                DataCollection.this.onDataChange();
            }
        });
    }

    @SimpleFunction
    public <K, V> void ChangeDataSource(final DataSource<K, V> source, final String keyValue) {
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.4
            @Override // java.lang.Runnable
            public void run() {
                List<String> columnsList;
                DataSource dataSource = source;
                if ((dataSource instanceof DataFile) || (dataSource instanceof Web)) {
                    YailList keyValues = new YailList();
                    try {
                        keyValues = CsvUtil.fromCsvRow(keyValue);
                    } catch (Exception e) {
                        Log.e(getClass().getName(), e.getMessage());
                    }
                    DataSource dataSource2 = source;
                    if (dataSource2 instanceof DataFile) {
                        columnsList = DataCollection.this.dataFileColumns;
                    } else if (dataSource2 instanceof Spreadsheet) {
                        columnsList = DataCollection.this.sheetsColumns;
                    } else if (dataSource2 instanceof Web) {
                        columnsList = DataCollection.this.webColumns;
                    } else {
                        throw new IllegalArgumentException(source + " is not an expected DataSource");
                    }
                    for (int i = 0; i < columnsList.size(); i++) {
                        String columnValue = "";
                        if (keyValues.size() > i) {
                            columnValue = keyValues.getString(i);
                        }
                        columnsList.set(i, columnValue);
                    }
                } else {
                    DataCollection.this.dataSourceKey = keyValue;
                }
                DataCollection.this.lastDataSourceValue = null;
                DataCollection.this.Source(source);
            }
        });
    }

    @SimpleFunction(description = "Un-links the currently associated Data Source component from the Chart.")
    public void RemoveDataSource() {
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.5
            @Override // java.lang.Runnable
            public void run() {
                DataCollection.this.Source(null);
                DataCollection.this.dataSourceKey = "";
                DataCollection.this.lastDataSourceValue = null;
                for (int i = 0; i < DataCollection.this.dataFileColumns.size(); i++) {
                    DataCollection.this.dataFileColumns.set(i, "");
                    DataCollection.this.sheetsColumns.set(i, "");
                    DataCollection.this.webColumns.set(i, "");
                }
            }
        });
    }

    @SimpleFunction(description = "Returns a List of entries with x values matching the specified x value. A single entry is represented as a List of values of the entry.")
    public YailList GetEntriesWithXValue(final String x) {
        try {
            return (YailList) this.threadRunner.submit(new Callable<YailList>() { // from class: com.google.appinventor.components.runtime.DataCollection.6
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public YailList call() {
                    return DataCollection.this.dataModel.findEntriesByCriterion(x, DataModel.EntryCriterion.XValue);
                }
            }).get();
        } catch (InterruptedException e) {
            Log.e(getClass().getName(), e.getMessage());
            return new YailList();
        } catch (ExecutionException e2) {
            Log.e(getClass().getName(), e2.getMessage());
            return new YailList();
        }
    }

    @SimpleFunction(description = "Returns a List of entries with y values matching the specified y value. A single entry is represented as a List of values of the entry.")
    public YailList GetEntriesWithYValue(final String y) {
        try {
            return (YailList) this.threadRunner.submit(new Callable<YailList>() { // from class: com.google.appinventor.components.runtime.DataCollection.7
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public YailList call() {
                    return DataCollection.this.dataModel.findEntriesByCriterion(y, DataModel.EntryCriterion.YValue);
                }
            }).get();
        } catch (InterruptedException e) {
            Log.e(getClass().getName(), e.getMessage());
            return new YailList();
        } catch (ExecutionException e2) {
            Log.e(getClass().getName(), e2.getMessage());
            return new YailList();
        }
    }

    @SimpleFunction(description = "Returns all the entries of the Data Series. A single entry is represented as a List of values of the entry.")
    public YailList GetAllEntries() {
        try {
            return (YailList) this.threadRunner.submit(new Callable<YailList>() { // from class: com.google.appinventor.components.runtime.DataCollection.8
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public YailList call() {
                    return DataCollection.this.dataModel.getEntriesAsTuples();
                }
            }).get();
        } catch (InterruptedException e) {
            Log.e(getClass().getName(), e.getMessage());
            return new YailList();
        } catch (ExecutionException e2) {
            Log.e(getClass().getName(), e2.getMessage());
            return new YailList();
        }
    }

    @SimpleFunction
    public void ImportFromTinyDB(TinyDB tinyDB, String tag) {
        final List<?> list = tinyDB.getDataValue(tag);
        updateCurrentDataSourceValue(tinyDB, tag, list);
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.9
            @Override // java.lang.Runnable
            public void run() {
                DataCollection.this.dataModel.importFromList(list);
                DataCollection.this.onDataChange();
            }
        });
    }

    @SimpleFunction
    public void ImportFromCloudDB(final CloudDB cloudDB, final String tag) {
        final Future<YailList> list = cloudDB.getDataValue(tag);
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    YailList listValue = (YailList) list.get();
                    DataCollection.this.updateCurrentDataSourceValue(cloudDB, tag, listValue);
                    DataCollection.this.dataModel.importFromList(listValue);
                    DataCollection.this.onDataChange();
                } catch (InterruptedException e) {
                    Log.e(getClass().getName(), e.getMessage());
                } catch (ExecutionException e2) {
                    Log.e(getClass().getName(), e2.getMessage());
                }
            }
        });
    }

    protected void importFromDataFileAsync(DataFile dataFile, YailList columns) {
        final Future<YailList> dataFileColumns = dataFile.getDataValue(columns);
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.11
            @Override // java.lang.Runnable
            public void run() {
                YailList dataResult = null;
                try {
                    dataResult = (YailList) dataFileColumns.get();
                } catch (InterruptedException e) {
                    Log.e(getClass().getName(), e.getMessage());
                } catch (ExecutionException e2) {
                    Log.e(getClass().getName(), e2.getMessage());
                }
                DataCollection.this.dataModel.importFromColumns(dataResult, true);
                DataCollection.this.onDataChange();
            }
        });
    }

    protected void importFromSpreadsheetAsync(final Spreadsheet sheets, YailList columns, final boolean useHeaders) {
        final Future<YailList> sheetColumns = sheets.getDataValue(columns, useHeaders);
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.12
            @Override // java.lang.Runnable
            public void run() {
                YailList dataColumns = null;
                try {
                    dataColumns = (YailList) sheetColumns.get();
                } catch (InterruptedException e) {
                    Log.e(getClass().getName(), e.getMessage());
                } catch (ExecutionException e2) {
                    Log.e(getClass().getName(), e2.getMessage());
                }
                if (sheets == DataCollection.this.dataSource) {
                    DataCollection dataCollection = DataCollection.this;
                    dataCollection.updateCurrentDataSourceValue(dataCollection.dataSource, null, null);
                }
                DataCollection.this.dataModel.importFromColumns(dataColumns, useHeaders);
                DataCollection.this.onDataChange();
            }
        });
    }

    protected void importFromWebAsync(final Web webComponent, YailList columns) {
        final Future<YailList> webColumns = webComponent.getDataValue(columns);
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.13
            @Override // java.lang.Runnable
            public void run() {
                YailList dataColumns = null;
                try {
                    dataColumns = (YailList) webColumns.get();
                } catch (InterruptedException e) {
                    Log.e(getClass().getName(), e.getMessage());
                } catch (ExecutionException e2) {
                    Log.e(getClass().getName(), e2.getMessage());
                }
                if (webComponent == DataCollection.this.dataSource) {
                    DataCollection dataCollection = DataCollection.this;
                    dataCollection.updateCurrentDataSourceValue(dataCollection.dataSource, null, null);
                }
                DataCollection.this.dataModel.importFromColumns(dataColumns, true);
                DataCollection.this.onDataChange();
            }
        });
    }

    @SimpleFunction
    public void ImportFromDataFile(DataFile dataFile, String xValueColumn, String yValueColumn) {
        YailList columns = YailList.makeList(Arrays.asList(xValueColumn, yValueColumn));
        importFromDataFileAsync(dataFile, columns);
    }

    @SimpleFunction
    public void ImportFromSpreadsheet(Spreadsheet spreadsheet, String xColumn, String yColumn, boolean useHeaders) {
        YailList columns = YailList.makeList(Arrays.asList(xColumn, yColumn));
        importFromSpreadsheetAsync(spreadsheet, columns, useHeaders);
    }

    @SimpleFunction(description = "Imports data from the specified Web component, given the names of the X and Y value columns. Empty columns are filled with default values (1, 2, 3, ... for Entry 1, 2, ...). In order for the data importing to be successful, to load the data, and then this block should be used on that Web component. The usage of the gotValue event in the Web component is unnecessary.")
    public void ImportFromWeb(Web web, String xValueColumn, String yValueColumn) {
        YailList columns = YailList.makeList(Arrays.asList(xValueColumn, yValueColumn));
        importFromWebAsync(web, columns);
    }

    public void Initialize() {
        this.initialized = true;
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            Source(dataSource);
        } else {
            ElementsFromPairs(this.elements);
        }
    }

    @Override // com.google.appinventor.components.runtime.Component
    public HandlesEventDispatching getDispatchDelegate() {
        return this.container.$form();
    }

    @Override // com.google.appinventor.components.runtime.DataSourceChangeListener
    public void onDataSourceValueChange(final DataSource<?, ?> component, final String key, final Object newValue) {
        if (component != this.dataSource || !isKeyValid(key)) {
            return;
        }
        this.threadRunner.execute(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.14
            @Override // java.lang.Runnable
            public void run() {
                if (DataCollection.this.lastDataSourceValue instanceof List) {
                    DataCollection.this.dataModel.removeValues((List) DataCollection.this.lastDataSourceValue);
                }
                DataCollection.this.updateCurrentDataSourceValue(component, key, newValue);
                if (DataCollection.this.lastDataSourceValue instanceof List) {
                    DataCollection.this.dataModel.importFromList((List) DataCollection.this.lastDataSourceValue);
                }
                DataCollection.this.onDataChange();
            }
        });
    }

    @Override // com.google.appinventor.components.runtime.DataSourceChangeListener
    public void onReceiveValue(RealTimeDataSource<?, ?> component, String key, Object value) {
        boolean importData;
        if (component != this.dataSource) {
            return;
        }
        if (component instanceof BluetoothClient) {
            String valueString = (String) value;
            importData = valueString.startsWith(this.dataSourceKey);
            if (importData) {
                value = valueString.substring(this.dataSourceKey.length());
            }
        } else {
            importData = isKeyValid(key);
        }
        if (importData) {
            final Object finalValue = value;
            this.container.$context().runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.DataCollection.15
                @Override // java.lang.Runnable
                public void run() {
                    if (DataCollection.this.container instanceof Chart) {
                        DataCollection dataCollection = DataCollection.this;
                        dataCollection.tick = ((Chart) dataCollection.container).getSyncedTValue(DataCollection.this.tick);
                        YailList tuple = YailList.makeList(Arrays.asList(Integer.valueOf(DataCollection.this.tick), finalValue));
                        DataCollection.this.dataModel.addTimeEntry(tuple);
                        DataCollection.this.onDataChange();
                        DataCollection.this.tick++;
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentDataSourceValue(DataSource<?, ?> source, String key, Object newValue) {
        if (source == this.dataSource && isKeyValid(key)) {
            if (source instanceof Web) {
                YailList columns = ((Web) source).getColumns(YailList.makeList((List) this.webColumns));
                this.lastDataSourceValue = this.dataModel.getTuplesFromColumns(columns, true);
            } else if (source instanceof Spreadsheet) {
                YailList columns2 = ((Spreadsheet) source).getColumns(YailList.makeList((List) this.sheetsColumns), this.useSheetHeaders);
                this.lastDataSourceValue = this.dataModel.getTuplesFromColumns(columns2, this.useSheetHeaders);
            } else {
                this.lastDataSourceValue = newValue;
            }
        }
    }

    private boolean isKeyValid(String key) {
        return key == null || key.equals(this.dataSourceKey);
    }

    public static List<Double> castToDouble(List<?> list) {
        List<Double> listDoubles = new ArrayList<>();
        for (Object o : list) {
            if (o instanceof Number) {
                listDoubles.add(Double.valueOf(((Number) o).doubleValue()));
            } else {
                try {
                    listDoubles.add(Double.valueOf(Double.parseDouble(o.toString())));
                } catch (NumberFormatException e) {
                }
            }
        }
        return listDoubles;
    }
}
