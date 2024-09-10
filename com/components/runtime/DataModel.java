package com.google.appinventor.components.runtime;

import com.github.mikephil.charting.data.Entry;
import com.google.appinventor.components.runtime.util.ChartDataSourceUtil;
import com.google.appinventor.components.runtime.util.YailList;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.mapping.Symbol;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class DataModel<E> {
    protected int maximumTimeEntries = HttpRequestContext.HTTP_OK;
    protected List<E> entries = new ArrayList();

    /* loaded from: classes.dex */
    public enum EntryCriterion {
        All,
        XValue,
        YValue
    }

    public abstract void addEntryFromTuple(YailList yailList);

    public abstract void addTimeEntry(YailList yailList);

    public abstract void clearEntries();

    public abstract boolean doesEntryExist(YailList yailList);

    public abstract YailList findEntriesByCriterion(String str, EntryCriterion entryCriterion);

    public abstract int findEntryIndex(Entry entry);

    public abstract List<E> getEntries();

    public abstract Entry getEntryFromTuple(YailList yailList);

    public abstract YailList getTupleFromEntry(Entry entry);

    protected abstract int getTupleSize();

    protected abstract boolean isEntryCriterionSatisfied(Entry entry, EntryCriterion entryCriterion, String str);

    public abstract void removeEntry(int i);

    public abstract void removeEntryFromTuple(YailList yailList);

    public void setElements(String elements) {
        int tupleSize = getTupleSize();
        String[] entries = elements.split(",");
        for (int i = tupleSize - 1; i < entries.length; i += tupleSize) {
            List<String> tupleEntries = new ArrayList<>();
            for (int j = tupleSize - 1; j >= 0; j--) {
                int index = i - j;
                tupleEntries.add(entries[index]);
            }
            addEntryFromTuple(YailList.makeList((List) tupleEntries));
        }
    }

    public void importFromList(List<?> list) {
        for (Object entry : list) {
            YailList tuple = null;
            if (entry instanceof YailList) {
                tuple = (YailList) entry;
            } else if (entry instanceof List) {
                tuple = YailList.makeList((List) entry);
            }
            if (tuple != null) {
                addEntryFromTuple(tuple);
            }
        }
    }

    public void removeValues(List<?> values) {
        for (Object entry : values) {
            YailList tuple = null;
            if (entry instanceof YailList) {
                tuple = (YailList) entry;
            } else if (entry instanceof List) {
                tuple = YailList.makeList((List) entry);
            } else if (entry instanceof Symbol) {
            }
            if (tuple != null) {
                removeEntryFromTuple(tuple);
            }
        }
    }

    public void importFromColumns(YailList columns, boolean hasHeaders) {
        YailList tuples = getTuplesFromColumns(columns, hasHeaders);
        importFromList(tuples);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.google.appinventor.components.runtime.util.YailList] */
    public YailList getTuplesFromColumns(YailList yailList, boolean z) {
        int determineMaximumListSize = ChartDataSourceUtil.determineMaximumListSize(yailList);
        ArrayList arrayList = new ArrayList();
        for (?? r2 = z; r2 < determineMaximumListSize; r2++) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < yailList.size(); i++) {
                Object object = yailList.getObject(i);
                if (!(object instanceof YailList)) {
                    arrayList2.add(getDefaultValue(r2 - 1));
                } else {
                    ?? r6 = (YailList) object;
                    if (r6.size() > r2) {
                        arrayList2.add(r6.getString(r2));
                    } else if (r6.size() == 0) {
                        arrayList2.add(getDefaultValue(r2 - 1));
                    } else {
                        arrayList2.add("");
                    }
                }
            }
            arrayList.add(YailList.makeList((List) arrayList2));
        }
        return YailList.makeList((List) arrayList);
    }

    public YailList getEntriesAsTuples() {
        return findEntriesByCriterion(Component.TYPEFACE_DEFAULT, EntryCriterion.All);
    }

    public void setMaximumTimeEntries(int entries) {
        this.maximumTimeEntries = entries;
    }

    protected void setDefaultStylingProperties() {
    }

    protected String getDefaultValue(int index) {
        return new StringBuilder().append(index).toString();
    }

    protected boolean areEntriesEqual(Entry e1, Entry e2) {
        return e1.equalTo(e2);
    }
}
