package com.google.appinventor.components.runtime.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.ScatterChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes.dex */
public class ScatterWithTrendlineRenderer extends ScatterChartRenderer {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = ScatterWithTrendlineRenderer.class.getSimpleName();

    public ScatterWithTrendlineRenderer(ScatterChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    public void drawData(Canvas c) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (IScatterDataSet dataSet : scatterData.getDataSets()) {
            if (dataSet.isVisible() && (dataSet instanceof HasTrendline)) {
                drawTrendline(c, dataSet);
            }
        }
        super.drawData(c);
    }

    protected void drawTrendline(Canvas c, IScatterDataSet dataSet) {
        if (dataSet instanceof HasTrendline) {
            Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
            HasTrendline<?> hasTrendline = (HasTrendline) dataSet;
            if (!hasTrendline.isVisible()) {
                return;
            }
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(hasTrendline.getLineWidth());
            p.setColor(hasTrendline.getColor());
            p.setAlpha((hasTrendline.getColor() >> 24) & 255);
            p.setPathEffect(hasTrendline.getDashPathEffect());
            float[] lineBuffer = hasTrendline.getPoints(this.mChart.getXChartMin(), this.mChart.getXChartMax(), this.mChart.getWidth());
            trans.pointValuesToPixel(lineBuffer);
            c.drawLines(lineBuffer, 0, lineBuffer.length, p);
        }
    }
}
