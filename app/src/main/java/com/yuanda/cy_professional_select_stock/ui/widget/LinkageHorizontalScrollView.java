package com.yuanda.cy_professional_select_stock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class LinkageHorizontalScrollView extends HorizontalScrollView {

    private View mView;

    public LinkageHorizontalScrollView(Context context) {
        super(context);
    }

    public LinkageHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //这句的意思就是我滚到哪里，设置进来的空间就滚到哪里
        if (mView != null) {
            mView.scrollTo(l, oldt);
        }
    }


    /**
     * 设置联动的view
     *
     * @param view
     */


    public void setScrollView(View view) {
        mView = view;
    }



//    private float downX;    //按下时 的X坐标
//    private float downY;    //按下时 的Y坐标
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //在触发时回去到起始坐标
//        float x = event.getX();
//        float y = event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //将按下时的坐标存储
//                downX = x;
//                downY = y;
//                break;
//            case MotionEvent.ACTION_UP:
//                //获取到距离差
//                float dx = x - downX;
//                float dy = y - downY;
//                //防止是按下也判断
////                Toast.makeText(this, "r", Toast.LENGTH_SHORT).show();
//
//                if (Math.abs(dx) > 5 && Math.abs(dy) > 5) {
//                    //通过距离差判断方向
//                    int orientation = getOrientation(dx, dy);
//                    switch (orientation) {
//                        case 'r':
//                            Log.d("wgl","右");
//                        case 'l':
//                            Log.d("wgl","左");
//
//                            return true;
//                        case 'b':
//                            Log.d("wgl","下");
//                        case 't':
//                            Log.d("wgl","上");
//                            return false;
//                    }
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
//
//
//    private int getOrientation(float dx, float dy) {
//        if (Math.abs(dx) > Math.abs(dy)) {
//            //X轴移动
//            return dx > 0 ? 'r' : 'l';
//        } else {
//            //Y轴移动
//            return dy > 0 ? 'b' : 't';
//        }
//    }

}