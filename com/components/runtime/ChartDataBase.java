package com.google.appinventor.components.runtime;

import android.view.MotionEvent;
import androidx.constraintlayout.solver.LinearSystem;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.LineType;
import com.google.appinventor.components.common.PointStyle;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
/* loaded from: classes.dex */
public abstract class ChartDataBase extends DataCollection<Chart, ChartDataModel<?, ?, ?, ?, ?>> implements ChartComponent, OnChartGestureListener, OnChartValueSelectedListener {
    private int color;
    private YailList colors;
    private String label;

    /* JADX INFO: Access modifiers changed from: protected */
    public ChartDataBase(Chart chartContainer) {
        super(chartContainer);
        chartContainer.addDataComponent(this);
        initChartData();
        Color(-16777216);
        DataSourceKey("");
        Label("");
    }

    @Override // com.google.appinventor.components.runtime.ChartComponent
    public void initChartData() {
        this.dataModel = ((Chart) this.container).createChartModel();
        ((ChartDataModel) this.dataModel).view.chart.setOnChartGestureListener(this);
        ((ChartDataModel) this.dataModel).view.chart.setOnChartValueSelectedListener(this);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int Color() {
        return this.color;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_BLACK, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void Color(int argb) {
        this.color = argb;
        ((ChartDataModel) this.dataModel).setColor(this.color);
        onDataChange();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public YailList Colors() {
        return this.colors;
    }

    @SimpleProperty
    public void Colors(YailList colors) {
        List<Integer> resultColors = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            String color = colors.getString(i);
            try {
                long colorValue = Long.parseLong(color);
                if (colorValue > 2147483647L) {
                    colorValue -= 4294967296L;
                }
                resultColors.add(Integer.valueOf((int) colorValue));
            } catch (NumberFormatException e) {
                ((Chart) this.container).$form().dispatchErrorOccurredEvent((Component) this.container, "Colors", ErrorMessages.ERROR_INVALID_CHART_DATA_COLOR, color);
            }
        }
        this.colors = YailList.makeList((List) resultColors);
        ((ChartDataModel) this.dataModel).setColors(resultColors);
        onDataChange();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public String Label() {
        return this.label;
    }

    @SimpleProperty
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Label(String text) {
        this.label = text;
        ((ChartDataModel) this.dataModel).setLabel(text);
        onDataChange();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(defaultValue = Component.TYPEFACE_DEFAULT, editorType = PropertyTypeConstants.PROPERTY_TYPE_CHART_POINT_SHAPE)
    public void PointShape(PointStyle shape) {
        if (this.dataModel instanceof ScatterChartDataModel) {
            ((ScatterChartDataModel) this.dataModel).setPointShape(shape);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(defaultValue = Component.TYPEFACE_DEFAULT, editorType = PropertyTypeConstants.PROPERTY_TYPE_CHART_LINE_TYPE)
    public void LineType(LineType type) {
        if (this.dataModel instanceof LineChartBaseDataModel) {
            ((LineChartBaseDataModel) this.dataModel).setLineType(type);
        }
    }

    @Override // com.google.appinventor.components.runtime.DataCollection
    public void onDataChange() {
        ((Chart) this.container).getChartView().refresh((ChartDataModel) this.dataModel);
        for (DataSourceChangeListener listener : this.listeners) {
            listener.onDataSourceValueChange(this, null, null);
        }
    }

    public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {
    }

    public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {
    }

    public void onChartLongPressed(MotionEvent motionEvent) {
    }

    public void onChartDoubleTapped(MotionEvent motionEvent) {
    }

    public void onChartSingleTapped(MotionEvent motionEvent) {
    }

    public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
    }

    public void onChartScale(MotionEvent motionEvent, float v, float v1) {
    }

    public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {
    }

    public void onValueSelected(final Entry entry, Highlight highlight) {
        ((Chart) this.container).$form().runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.ChartDataBase.1
            @Override // java.lang.Runnable
            public void run() {
                PieEntry pieEntry = entry;
                if (pieEntry instanceof PieEntry) {
                    ChartDataBase.this.EntryClick(pieEntry.getLabel(), entry.getValue());
                } else {
                    ChartDataBase.this.EntryClick(Float.valueOf(pieEntry.getX()), entry.getY());
                }
            }
        });
    }

    @SimpleEvent
    public void EntryClick(Object x, double y) {
        EventDispatcher.dispatchEvent(this, "EntryClick", x, Double.valueOf(y));
        ((Chart) this.container).EntryClick(this, x, y);
    }

    public void onNothingSelected() {
    }
}
