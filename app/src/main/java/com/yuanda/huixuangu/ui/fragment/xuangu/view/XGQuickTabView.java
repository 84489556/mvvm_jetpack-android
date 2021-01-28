package com.yuanda.huixuangu.ui.fragment.xuangu.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.RecyclerView;

import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.huixuangu.adapter.xuangu.XGQuickTabAdapter;
import com.yuanda.huixuangu.ui.widget.NoScrollGridLayoutManager;

public class XGQuickTabView extends PopupWindow {

    private View mParentView;
    private Context mContext;
    private int mTabIndex;
    private int mLevel;

    public static interface OnQuickTabClickListener {
        public void onQuickTabClick(int index);
    }
    public XGQuickTabView(Context context, int tabIndex, int level,View parentView){
        super(context);
        this.mContext = context;
        this.mTabIndex = tabIndex;
        this.mLevel = level;
        this.mParentView = parentView;

        initView();
    }
    private void initView(){

        View view = LayoutInflater.from(mContext).inflate(R.layout.view_xuangu_quick_tab, null, false);
        setContentView(view);

        XGQuickTabAdapter adapter = new XGQuickTabAdapter(mContext,XGTabItem.getHeaderTabs(mLevel),mTabIndex);
        RecyclerView recyclerView = view.findViewById(R.id.tabRecyclerView);
        recyclerView.setLayoutManager(new NoScrollGridLayoutManager(mContext, 4));
        recyclerView.setAdapter(adapter);

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(null);

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
    }
    public void show(){
        this.showAsDropDown(mParentView, Gravity.BOTTOM,0,0);
    }

}
