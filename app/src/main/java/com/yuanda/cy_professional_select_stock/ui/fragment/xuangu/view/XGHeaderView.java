package com.yuanda.cy_professional_select_stock.ui.fragment.xuangu.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.databinding.ViewXuanguHeaderBinding;

import java.util.ArrayList;

public class XGHeaderView extends LinearLayout implements TabLayoutMediator.TabConfigurationStrategy,TabLayout.OnTabSelectedListener, View.OnClickListener {

    private Context mContext;
    private ArrayList<XGTabItem> tabs = new ArrayList<>();
    private ViewXuanguHeaderBinding mViewBinding;
    private ViewPager2 mViewPager;
    private int mLevel = 6;
    private int mTabIndex = 0;
    private XGQuickTabView mXgQuickTabView;

    public XGHeaderView(Context context) {
        super(context);
        initView(context);
    }

    public XGHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    private void initView(Context mContext){
        this.mContext = mContext;
        this.initTabs();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.view_xuangu_header,this,true);
        mViewBinding.xgTabLayout.addOnTabSelectedListener(this);
        mViewBinding.famousUpIv.setOnClickListener(this);
        mViewBinding.headerIv.setOnClickListener(this);
    }
    public void attach(ViewPager2 viewPager) {
        this.mViewPager = viewPager;
        this.mViewPager.setUserInputEnabled(false);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mViewBinding.xgTabLayout, mViewPager, this);
        tabLayoutMediator.attach();
    }
    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        View view = View.inflate(mContext, R.layout.view_xuangu_tab, null);
        view.setTag(position);
        XGTabItem item = tabs.get(position);
        ImageView iconView = view.findViewById(R.id.tab_icon);
        TextView textView = view.findViewById(R.id.tab_text);
        iconView.setImageResource(item.drawableId);
        textView.setText(item.text);
        tab.setCustomView(view);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        if (customView == null) return;
        this.mTabIndex = (int)customView.getTag();
        ((TextView)customView.findViewById(R.id.tab_text)).setTextColor(mContext.getResources().getColor(R.color.color_xg_tabheader_selected));
        ((TextView)customView.findViewById(R.id.tab_text)). setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        if (customView == null) return;
        ((TextView)customView.findViewById(R.id.tab_text)).setTextColor(mContext.getResources().getColor(R.color.color_xg_tabheader));
        ((TextView)customView.findViewById(R.id.tab_text)). setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //todo... 选中之后再次点击即复选时触发
    }

    private void initTabs(){
        tabs.clear();
        tabs.addAll(XGTabItem.getHeaderTabs(mLevel));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.famous_up_iv){
            if(mXgQuickTabView != null && mXgQuickTabView.isShowing()){
                mViewBinding.famousUpIv.setImageResource(R.mipmap.xuangu_famous_up);
                mXgQuickTabView.dismiss();
                return;
            }
            mViewBinding.famousUpIv.setImageResource(R.mipmap.xuangu_famous_up_logo);
            mXgQuickTabView = new XGQuickTabView(mContext,this.mTabIndex,mLevel,this);
            mXgQuickTabView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    mViewBinding.famousUpIv.setImageResource(R.mipmap.xuangu_famous_up);
                }
            });
            mXgQuickTabView.show();
        }
    }
}