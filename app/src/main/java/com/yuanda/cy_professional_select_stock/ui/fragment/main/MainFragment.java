package com.yuanda.cy_professional_select_stock.ui.fragment.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.databinding.FragmentMainBinding;
import com.yuanda.cy_professional_select_stock.adapter.main.MainFragmentStateAdapter;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.ui.activity.MainActivity;
import com.yuanda.cy_professional_select_stock.ui.fragment.dabang.DaBangFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.guandian.GanDianFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.kanshi.KanShiFragment;
import com.yuanda.cy_professional_select_stock.ui.fragment.xuangu.XuanGuFragment;
import com.yuanda.cy_professional_select_stock.utils.BarUtils;
import com.yuanda.cy_professional_select_stock.viewmodel.main.MainFragmentViewModel;

import java.util.ArrayList;

public class MainFragment extends BaseFragment implements TabLayoutMediator.TabConfigurationStrategy {
    private MainFragmentViewModel mViewModel;
    private ArrayList<String> tabStrList;

    private static MainFragment mainFragment;

    public static MainFragment getInstance() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }


    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_main, BR.mainFragmentModel, mViewModel);
        bindingConfig.addBindingParam(BR.callback, new MainFragmentCallBack());
        return bindingConfig;
    }

    @Override
    protected void initViewModel() {

        mViewModel = getFragmentScopeViewModel(MainFragmentViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initView() {
        tabStrList = new ArrayList<>();
        FragmentMainBinding mBinding = (FragmentMainBinding) this.mBinding;
        ArrayList<Fragment> fragments = new ArrayList<>();

        tabStrList.add("看势");
        tabStrList.add("观点");
        tabStrList.add("首页");
        tabStrList.add("打榜");
        tabStrList.add("选股");

        fragments.add(KanShiFragment.getInstance());
        fragments.add(GanDianFragment.getInstance());
        fragments.add(HomeFragment.getInstance());
        fragments.add(DaBangFragment.getInstance());
        fragments.add(XuanGuFragment.getInstance());


        if (getActivity() == null) return;
        mBinding.mainViewPager2.setAdapter(new MainFragmentStateAdapter(getActivity(), fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mBinding.mainTabLayout, mBinding.mainViewPager2, this);
        tabLayoutMediator.attach();
        mBinding.mainViewPager2.setUserInputEnabled(false);
        mBinding.mainViewPager2.setCurrentItem(2, false);
        mBinding.mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择时触发
                View customView = tab.getCustomView();
                if (customView == null) return;
                mBinding.mainViewPager2.setCurrentItem((int)customView.getTag(), false);
                ImageView iconView = customView.findViewById(R.id.main_tab_icon);
                TextView textView = customView.findViewById(R.id.main_tab_text);
                int tag = (int) customView.getTag();
                if (tag == 0) iconView.setImageResource(R.mipmap.kanshi_select);
                if (tag == 1)  iconView.setImageResource(R.mipmap.guandian_select);
                if (tag == 2) iconView.setImageResource(R.mipmap.main_select);
                if (tag == 3) iconView.setImageResource(R.mipmap.dabang_select);
                if (tag == 4) iconView.setImageResource(R.mipmap.xuangu_select);
                if(tag==1){
                    BarUtils.setStatusBarLightMode((MainActivity)getActivity(),true );//透明状态栏
                }else {
                    BarUtils.setStatusBarLightMode((MainActivity)getActivity(),false );//透明状态栏
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView == null) return;
                ImageView iconView = customView.findViewById(R.id.main_tab_icon);
                int tag = (int) customView.getTag();
                if (tag == 0) iconView.setImageResource(R.mipmap.kanshi_unselect);
                if (tag == 1) iconView.setImageResource(R.mipmap.guandian_unselect);
                if (tag == 2) iconView.setImageResource(R.mipmap.main_unselect);
                if (tag == 3) iconView.setImageResource(R.mipmap.dabang_unselect);
                if (tag == 4) iconView.setImageResource(R.mipmap.xuangu_unselect);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //选中之后再次点击即复选时触发
            }
        });


    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        View inflate = getLayoutInflater().inflate(R.layout.view_main_tab, null, true);
        inflate.setTag(position);
        initTab(inflate, position);
        tab.setCustomView(inflate);
    }

    private void initTab(View v, int p) {

        ImageView iconView = v.findViewById(R.id.main_tab_icon);
        TextView textView = v.findViewById(R.id.main_tab_text);
        textView.setText(tabStrList.get(p));

        switch (p) {
            case 0:
                iconView.setImageResource(R.mipmap.kanshi_unselect);
                break;
            case 1:
                iconView.setImageResource(R.mipmap.guandian_unselect);
                break;
            case 2:
                iconView.setImageResource(R.mipmap.main_select);
                break;
            case 3:
                iconView.setImageResource(R.mipmap.dabang_unselect);
                break;
            case 4:
                iconView.setImageResource(R.mipmap.xuangu_unselect);
                break;
        }

    }

    public class MainFragmentCallBack {


    }
}
