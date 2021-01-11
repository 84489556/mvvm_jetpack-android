package com.yd.huixuangu.ui.fragment.xuangu;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.adapter.xuangu.XuanGuFragmentStateAdapter;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.databinding.FragmentXuanguBinding;
import com.yd.huixuangu.viewmodel.xuangu.XuanGuFragmentViewModel;

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
