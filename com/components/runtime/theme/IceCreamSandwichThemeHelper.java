package com.google.appinventor.components.runtime.util.theme;

import android.text.Html;
import androidx.appcompat.app.ActionBar;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ImageViewUtil;

/* loaded from: classes.dex */
public class IceCreamSandwichThemeHelper implements ThemeHelper {
    private final AppInventorCompatActivity activity;

    public IceCreamSandwichThemeHelper(AppInventorCompatActivity activity) {
        this.activity = activity;
    }

    @Override // com.google.appinventor.components.runtime.util.theme.ThemeHelper
    public void requestActionBar() {
        this.activity.getWindow().requestFeature(8);
    }

    @Override // com.google.appinventor.components.runtime.util.theme.ThemeHelper
    public boolean setActionBarVisible(boolean visible) {
        ActionBar actionBar = this.activity.getSupportActionBar();
        if (actionBar == null) {
            AppInventorCompatActivity appInventorCompatActivity = this.activity;
            if (appInventorCompatActivity instanceof Form) {
                ((Form) appInventorCompatActivity).dispatchErrorOccurredEvent((Form) appInventorCompatActivity, "ActionBar", ErrorMessages.ERROR_ACTIONBAR_NOT_SUPPORTED, new Object[0]);
            }
            return false;
        }
        if (visible) {
            actionBar.show();
            return true;
        }
        actionBar.hide();
        return true;
    }

    @Override // com.google.appinventor.components.runtime.util.theme.ThemeHelper
    public boolean hasActionBar() {
        return this.activity.getSupportActionBar() != null;
    }

    @Override // com.google.appinventor.components.runtime.util.theme.ThemeHelper
    public void setTitle(String title) {
        ActionBar actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override // com.google.appinventor.components.runtime.util.theme.ThemeHelper
    public void setActionBarAnimation(boolean enabled) {
        ActionBar actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setShowHideAnimationEnabled(enabled);
        }
    }

    @Override // com.google.appinventor.components.runtime.util.theme.ThemeHelper
    public void setTitle(String title, boolean black) {
        ActionBar actionBar = this.activity.getSupportActionBar();
        if (actionBar != null) {
            if (black) {
                actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + title + "</font>"));
                ImageViewUtil.setMenuButtonColor(this.activity, -16777216);
            } else {
                actionBar.setTitle(title);
                ImageViewUtil.setMenuButtonColor(this.activity, -1);
            }
        }
    }
}
