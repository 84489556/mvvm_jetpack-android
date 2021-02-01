package com.yuanda.cy_professional_select_stock.ui.widget.guandian;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * author：created by tangqianzhu
 * mail：pillartang@sina.cn
 * date：2021/2/1 16
 * description:解决Viewpager中嵌套HorizontalScrollView滑动冲突的CourseHorizontalScrollView
 */
public class CourseHorizontalScrollView extends HorizontalScrollView {

    public CourseHorizontalScrollView(Context context) {
        super(context);
    }

    public CourseHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent p_event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent p_event) {
        if (p_event.getAction() == MotionEvent.ACTION_DOWN && getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(p_event);
    }

}
