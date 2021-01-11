package com.yd.huixuangu.ui.fragment.xuangu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.adapter.xuangu.XuanGuFragmentStateAdapter;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.databinding.FragmentXuanguCelueBinding;
import com.yd.huixuangu.ui.fragment.xuangu.view.XGTabItem;
import com.yd.huixuangu.viewmodel.xuangu.XuanGuViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 研报策略
 */
public class YanBaoCeLueFragment extends BaseFragment implements TabLayoutMediator.TabConfigurationStrategy,TabLayout.OnTabSelectedListener {
    private XuanGuViewModel mViewModel;
    private List<XGTabItem> tabs = new ArrayList<>();

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_xuangu_celue, BR.viewModel, mViewModel);
        bindingConfig.addBindingParam(BR.clickEvent,new ClickEventHandler());
        return bindingConfig;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTabs();
        initFragments();
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(XuanGuViewModel.class);
    }

    private void initFragments(){
        FragmentXuanguCelueBinding mBinding = (FragmentXuanguCelueBinding) this.mBinding;
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ZhangFuFragment());//涨幅空间
        fragments.add(new JiGouFragment());//明星机构推荐
        fragments.add(new FenXiShiFragment());//明星分析师推荐
        mBinding.ceLueViewPager2.setAdapter(new XuanGuFragmentStateAdapter(getActivity(), fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mBinding.ceLueTabLayout, mBinding.ceLueViewPager2, this);
        tabLayoutMediator.attach();
        mBinding.ceLueViewPager2.setCurrentItem(0, false);
    }

    @Override


    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        View view = View.inflate(getContext(), R.layout.view_xuangu_tab, null);
        view.setTag(position);
        XGTabItem item = tabs.get(position);
        ImageView iconView = view.findViewById(R.id.tab_icon);
        iconView.setVisibility(View.GONE);
        TextView textView = view.findViewById(R.id.tab_text);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextSize(12);
        textView.setText(item.text);
        tab.setCustomView(view);
    }
    private void initTabs(){
        tabs = XGTabItem.getCeLueTabs();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class ClickEventHandler {
        public void onBrifClick(){

        }
        public void onXueTangClick(){

        }
    }
}
