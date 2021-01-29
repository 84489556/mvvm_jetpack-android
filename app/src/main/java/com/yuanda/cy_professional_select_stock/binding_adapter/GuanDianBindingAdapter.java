package com.yuanda.cy_professional_select_stock.binding_adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.adapter.guandian.CommonViewPagerAdapter;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.ExpertAnalysisnFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.HotFocusFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.NewsFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild.CompanyNewsFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild.CompanyResearchFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild.FinancialReportFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild.IndustryResearchFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild.OptionalStockFragment;

import java.util.ArrayList;

/**
 * author：created by tangqianzhu
 * mail：pillartang@sina.cn
 * date：2021/1/6 17
 * description:
 */
public class GuanDianBindingAdapter {

    /**
     * 绑定父类viewpager是否接受用户的滑动事件
     * */
    @BindingAdapter(value = {"setUserInputEnabled"}, requireAll = false)
    public static void setUserInputEnabled(ViewPager2 viewPager2 , MutableLiveData<Boolean> isInputEnabled) {
        viewPager2.setUserInputEnabled(isInputEnabled.getValue());
    }

    /**
     * 绑定父类viewpager的监听
     * */
    @BindingAdapter(value = {"bindViewPagerListener"}, requireAll = false)
    public static void bindViewPagerListener(ViewPager2 viewPager2 ,ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback);
    }
    /**
     * 加载观点一级tab
     * */
    @BindingAdapter(value = {"tabNames"}, requireAll = false)
    public static void initTabAndPage(TabLayout tabLayout, ArrayList<String> tabNames) {
        if(tabNames!= null && tabNames.size()>0){
            ArrayList<Fragment> fragments = new ArrayList<>();
            for (int i = 0;i<tabNames.size();i++){
                switch (i){
                    case 0:
                        fragments.add(ExpertAnalysisnFragment.getInstance());
                        break;
                    case 1:
                        fragments.add(HotFocusFragment.getInstance());
                        break;
                    case 2:
                        fragments.add(NewsFragment.getInstance());
                        break;
                }
            }
            ViewPager2 viewPager = (tabLayout.getRootView()).findViewById(R.id.guandian_viewpager2);
            if (viewPager != null) {
                viewPager.setAdapter(new CommonViewPagerAdapter((FragmentActivity) tabLayout.getContext(),fragments));
                //viewPager.setUserInputEnabled(false);
                //viewPager.setUserInputEnabled(viewPager.getCurrentItem()<2);
                viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                    }
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        viewPager.setUserInputEnabled(viewPager.getCurrentItem()<2);

                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                        super.onPageScrollStateChanged(state);
                    }
                });
                TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNames.get(position));
                        View inflate =  ((FragmentActivity) tabLayout.getContext()).getLayoutInflater().inflate(R.layout.guandian_tab_layout, null, true);
                        TextView textView = inflate.findViewById(R.id.guandiantab__txt);
                        textView.setText(tabNames.get(position));
                        tab.setCustomView(inflate);
                    }
                });
                tabLayoutMediator.attach();
            }
        }
    }
    /**
     * 加载观点资讯二级tab
     * */
    @BindingAdapter(value = {"newsTabNames"}, requireAll = false)
    public static void initNewsTabAndPage(TabLayout tabLayout, ArrayList<String> tabNames) {
        if(tabNames!= null && tabNames.size()>0){
            ArrayList<Fragment> fragments = new ArrayList<>();
            for (int i = 0;i<tabNames.size();i++){
                switch (i){
                    case 0:
                        fragments.add(FinancialReportFragment.getInstance());
                        break;
                    case 1:
                        fragments.add(OptionalStockFragment.getInstance());
                        break;
                    case 2:
                        fragments.add(CompanyNewsFragment.getInstance());
                        break;
                    case 3:
                        fragments.add(CompanyResearchFragment.getInstance());
                        break;
                    case 4:
                        fragments.add(IndustryResearchFragment.getInstance());
                        break;
                }
            }
            ViewPager2 viewPager = (tabLayout.getRootView()).findViewById(R.id.news_viewpager);
            if (viewPager != null) {
                viewPager.setAdapter(new CommonViewPagerAdapter((FragmentActivity) tabLayout.getContext(),fragments));
                TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNames.get(position));
                        View inflate =  ((FragmentActivity) tabLayout.getContext()).getLayoutInflater().inflate(R.layout.guandian_tab_layout, null, true);
                        TextView textView = inflate.findViewById(R.id.guandiantab__txt);
                        textView.setText(tabNames.get(position));
                        tab.setCustomView(inflate);
                    }
                });
                tabLayoutMediator.attach();
            }
        }
    }


    @BindingAdapter(value = {"bindTabListener"}, requireAll = false)
    public static void bindTabListener(TabLayout tabLayout ,TabLayout.OnTabSelectedListener onTabSelectedListener) {
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }
}
