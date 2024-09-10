package com.google.appinventor.components.runtime;

import android.R;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.theme.ClassicThemeHelper;
import com.google.appinventor.components.runtime.util.theme.HoneycombThemeHelper;
import com.google.appinventor.components.runtime.util.theme.IceCreamSandwichThemeHelper;
import com.google.appinventor.components.runtime.util.theme.ThemeHelper;

/* loaded from: classes.dex */
public class AppInventorCompatActivity extends Activity implements AppCompatCallback {
    private static boolean actionBarEnabled;
    private static int primaryColor;
    private AppCompatDelegate appCompatDelegate;
    android.widget.LinearLayout frameWithTitle;
    protected ThemeHelper themeHelper;
    TextView titleBar;
    private static final String LOG_TAG = AppInventorCompatActivity.class.getSimpleName();
    static final int DEFAULT_PRIMARY_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_PRIMARY_COLOR);
    private static boolean classicMode = false;
    private static Theme currentTheme = Theme.PACKAGED;
    private static boolean didSetClassicModeFromYail = false;

    /* loaded from: classes.dex */
    public enum Theme {
        PACKAGED,
        CLASSIC,
        DEVICE_DEFAULT,
        BLACK_TITLE_TEXT,
        DARK
    }

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        boolean z = classicMode || SdkLevel.getLevel() < 11;
        classicMode = z;
        if (z) {
            this.themeHelper = new ClassicThemeHelper();
        } else if (SdkLevel.getLevel() < 14) {
            HoneycombThemeHelper honeycombThemeHelper = new HoneycombThemeHelper(this);
            this.themeHelper = honeycombThemeHelper;
            honeycombThemeHelper.requestActionBar();
            actionBarEnabled = true;
        } else {
            this.themeHelper = new IceCreamSandwichThemeHelper(this);
            if (currentTheme != Theme.PACKAGED) {
                applyTheme();
            }
            AppCompatDelegate create = AppCompatDelegate.create(this, this);
            this.appCompatDelegate = create;
            create.onCreate(icicle);
        }
        super.onCreate(icicle);
        android.widget.LinearLayout linearLayout = new android.widget.LinearLayout(this);
        this.frameWithTitle = linearLayout;
        linearLayout.setOrientation(1);
        setContentView(this.frameWithTitle);
        actionBarEnabled = this.themeHelper.hasActionBar();
        this.titleBar = (TextView) findViewById(R.id.title);
        if (shouldCreateTitleBar()) {
            TextView textView = new TextView(this);
            this.titleBar = textView;
            textView.setBackgroundResource(R.drawable.title_bar);
            this.titleBar.setTextAppearance(this, R.style.TextAppearance.WindowTitle);
            this.titleBar.setGravity(16);
            this.titleBar.setSingleLine();
            this.titleBar.setShadowLayer(2.0f, 0.0f, 0.0f, -1157627904);
            if (!isClassicMode() || SdkLevel.getLevel() < 11) {
                this.frameWithTitle.addView(this.titleBar, new ViewGroup.LayoutParams(-1, -2));
                return;
            }
            return;
        }
        Log.d(LOG_TAG, "Already have a title bar (classic mode): " + this.titleBar);
    }

    public final boolean isAppCompatMode() {
        return this.appCompatDelegate != null;
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.onPostCreate(savedInstanceState);
        }
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.onPostResume();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.onConfigurationChanged(newConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.onStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.onDestroy();
        }
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.setTitle(title);
            return;
        }
        TextView textView = this.titleBar;
        if (textView != null) {
            textView.setText(title);
        }
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeStarted(ActionMode actionMode) {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeFinished(ActionMode actionMode) {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        android.widget.LinearLayout linearLayout = this.frameWithTitle;
        if (view != linearLayout) {
            linearLayout.addView(view, new FrameLayout.LayoutParams(-1, -1));
            view = this.frameWithTitle;
        }
        AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
        if (appCompatDelegate != null) {
            appCompatDelegate.setContentView(view);
        } else {
            super.setContentView(view);
        }
    }

    public ActionBar getSupportActionBar() {
        Window.Callback classicCallback = getWindow().getCallback();
        try {
            AppCompatDelegate appCompatDelegate = this.appCompatDelegate;
            return appCompatDelegate != null ? appCompatDelegate.getSupportActionBar() : null;
        } catch (IllegalStateException e) {
            this.appCompatDelegate = null;
            classicMode = true;
            getWindow().setCallback(classicCallback);
            return null;
        }
    }

    public static boolean isEmulator() {
        return Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.contains("sdk_gphone");
    }

    protected static boolean isActionBarEnabled() {
        return actionBarEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setActionBarEnabled(boolean enabled) {
        actionBarEnabled = enabled;
    }

    public static boolean isClassicMode() {
        return classicMode;
    }

    protected void setClassicMode(boolean classic) {
        if (isRepl() && SdkLevel.getLevel() >= 11) {
            classicMode = classic;
        }
    }

    protected static int getPrimaryColor() {
        return primaryColor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPrimaryColor(int color) {
        ActionBar actionBar = getSupportActionBar();
        int newColor = color == 0 ? DEFAULT_PRIMARY_COLOR : color;
        if (actionBar != null && newColor != primaryColor) {
            primaryColor = newColor;
            actionBar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    public boolean isRepl() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideTitleBar() {
        TextView textView = this.titleBar;
        if (textView != null) {
            if (textView.getParent() != this.frameWithTitle) {
                if (this.titleBar.getParent() != null) {
                    ((View) this.titleBar.getParent()).setVisibility(8);
                    return;
                }
                return;
            }
            this.titleBar.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void maybeShowTitleBar() {
        TextView textView = this.titleBar;
        if (textView != null) {
            textView.setVisibility(0);
            String str = LOG_TAG;
            Log.d(str, "titleBar visible");
            if (this.titleBar.getParent() != null && this.titleBar.getParent() != this.frameWithTitle) {
                Log.d(str, "Setting parent visible");
                ((View) this.titleBar.getParent()).setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void styleTitleBar() {
        ActionBar actionBar = getSupportActionBar();
        String str = LOG_TAG;
        Log.d(str, "actionBarEnabled = " + actionBarEnabled);
        Log.d(str, "!classicMode = " + (!classicMode));
        Log.d(str, "actionBar = " + actionBar);
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getPrimaryColor()));
            if (actionBarEnabled) {
                actionBar.show();
                hideTitleBar();
                return;
            }
            actionBar.hide();
        }
        maybeShowTitleBar();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAppInventorTheme(Theme theme) {
        if (!Form.getActiveForm().isRepl() || theme == currentTheme) {
            return;
        }
        currentTheme = theme;
        applyTheme();
    }

    private void applyTheme() {
        Log.d(LOG_TAG, "applyTheme " + currentTheme);
        setClassicMode(false);
        switch (AnonymousClass1.$SwitchMap$com$google$appinventor$components$runtime$AppInventorCompatActivity$Theme[currentTheme.ordinal()]) {
            case 1:
                setClassicMode(true);
                setTheme(R.style.Theme);
                return;
            case 2:
            case 3:
                setTheme(R.style.Theme.DeviceDefault.Light.NoActionBar);
                return;
            case 4:
                setTheme(R.style.Theme.DeviceDefault.NoActionBar);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.appinventor.components.runtime.AppInventorCompatActivity$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$appinventor$components$runtime$AppInventorCompatActivity$Theme;

        static {
            int[] iArr = new int[Theme.values().length];
            $SwitchMap$com$google$appinventor$components$runtime$AppInventorCompatActivity$Theme = iArr;
            try {
                iArr[Theme.CLASSIC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$AppInventorCompatActivity$Theme[Theme.DEVICE_DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$AppInventorCompatActivity$Theme[Theme.BLACK_TITLE_TEXT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$AppInventorCompatActivity$Theme[Theme.DARK.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private boolean shouldCreateTitleBar() {
        if (isAppCompatMode() && (!this.themeHelper.hasActionBar() || !isActionBarEnabled())) {
            return true;
        }
        if (this.titleBar == null) {
            return isRepl() || classicMode;
        }
        return false;
    }

    public static void setClassicModeFromYail(boolean newClassicMode) {
        if (!didSetClassicModeFromYail) {
            Log.d(LOG_TAG, "Setting classic mode from YAIL: " + newClassicMode);
            classicMode = newClassicMode;
            didSetClassicModeFromYail = true;
        }
    }
}
