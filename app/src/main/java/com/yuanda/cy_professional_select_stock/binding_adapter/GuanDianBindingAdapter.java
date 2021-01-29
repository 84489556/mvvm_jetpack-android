package com.yuanda.cy_professional_select_stock.binding_adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.adapter.guandian.CommonViewPagerAdapter;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.ExpertAnalysisnFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.HotFocusFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.NewsFragment;

import java.util.ArrayList;

/**
 * author：created by tangqianzhu
 * mail：pillartang@sina.cn
 * date：2021/1/6 17
 * description:
 */
public class GuanDianBindingAdapter {


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
