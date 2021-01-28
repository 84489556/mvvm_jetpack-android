package com.yuanda.huixuangu.ui.fragment.xuangu;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.databinding.FragmentXuanguBinding;
import com.yuanda.huixuangu.adapter.xuangu.XuanGuFragmentStateAdapter;
import com.yuanda.huixuangu.base.BaseFragment;
import com.yuanda.huixuangu.base.DataBindingConfig;
import com.yuanda.huixuangu.viewmodel.xuangu.XuanGuFragmentViewModel;

import java.util.ArrayList;

public class XuanGuFragment extends BaseFragment {

    XuanGuFragmentViewModel mViewModel;

    private static XuanGuFragment xuanGuFragment;

    public static XuanGuFragment getInstance() {
        if (xuanGuFragment == null) {
            xuanGuFragment = new XuanGuFragment();
        }
        return xuanGuFragment;
    }


    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_xuangu, BR.xuangu, mViewModel);
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(XuanGuFragmentViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragments();
    }
    private void initFragments(){
        FragmentXuanguBinding mBinding = (FragmentXuanguBinding) this.mBinding;
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new YanBaoCeLueFragment());
        fragments.add(new TeSeZhiBiaoFragment());
        fragments.add(new YanBaoCeLueFragment());
        fragments.add(new TeSeZhiBiaoFragment());
        fragments.add(new YanBaoCeLueFragment());
        fragments.add(new TeSeZhiBiaoFragment());
        fragments.add(new YanBaoCeLueFragment());
        mBinding.viewPager2.setAdapter(new XuanGuFragmentStateAdapter(getActivity(), fragments));
        mBinding.xgHeaderView.attach(mBinding.viewPager2);
        mBinding.viewPager2.setCurrentItem(0, false);
    }
}
