package com.google.appinventor.components.runtime;

import android.R;
import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "A visible component that indicates the progress of an operation using an animated loop.", iconName = "images/circularProgress.png", version = 1)
@SimpleObject
/* loaded from: classes.dex */
public final class CircularProgress extends AndroidViewComponent {
    private static final String LOG_TAG = "CircularProgress";
    private Context context;
    private int indeterminateColor;
    private LinearLayout.LayoutParams layoutParams;
    private ProgressBar progressBar;

    public CircularProgress(ComponentContainer container) {
        super(container);
        this.indeterminateColor = Component.COLOR_BLUE;
        this.context = container.$context();
        this.layoutParams = new LinearLayout.LayoutParams(-2, -2);
        ProgressBar progressBar = new ProgressBar(this.context, null, R.attr.progressBarStyle);
        this.progressBar = progressBar;
        progressBar.setLayoutParams(this.layoutParams);
        container.$add(this);
        Color(Component.COLOR_BLUE);
        Log.d(LOG_TAG, "Circular Progress created");
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public ProgressBar getView() {
        return this.progressBar;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Change the indeterminate color of the circular progress bar.")
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_BLUE, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void Color(int color) {
        this.indeterminateColor = color;
        Drawable drawable = this.progressBar.getIndeterminateDrawable();
        if (Build.VERSION.SDK_INT >= 29) {
            drawable.setColorFilter(new BlendModeColorFilter(color, BlendMode.SRC_IN));
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        Log.i(LOG_TAG, "Indeterminate Color = " + color);
    }

    @SimpleProperty
    public int Color() {
        return this.indeterminateColor;
    }
}
