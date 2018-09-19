package com.android.launcher3.custompage;

import android.content.Context;

public class ScreenUtils {

    private int navigationBarHeight = -1;
    private int statusBarHeight = -1;
    private Context mContext;

    public ScreenUtils(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    public int getNavigationBarHeight() {
        if (navigationBarHeight == -1) {
            navigationBarHeight = mContext.getResources().getDimensionPixelSize(
                    mContext.getResources().getIdentifier("navigation_bar_height",
                            "dimen", "android")
            );
        }
        return navigationBarHeight;
    }

    public int getStatusBarHeight() {
        if (statusBarHeight == -1) {
            statusBarHeight = mContext.getResources().getDimensionPixelSize(
                    mContext.getResources().getIdentifier("status_bar_height",
                            "dimen", "android")
            );
        }
        return statusBarHeight;
    }

}
