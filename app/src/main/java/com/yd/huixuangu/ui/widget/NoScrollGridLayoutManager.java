package com.yd.huixuangu.ui.widget;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * 避免和ScrollView等滑动冲突
 */
public final class NoScrollGridLayoutManager extends GridLayoutManager {

    public NoScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
