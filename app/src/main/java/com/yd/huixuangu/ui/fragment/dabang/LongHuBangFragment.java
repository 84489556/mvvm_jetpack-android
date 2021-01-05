package com.yd.huixuangu.ui.fragment.dabang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.dabang.DaBangFragmentViewModel;
import com.yd.huixuangu.viewmodel.dabang.LongHuBangFragmentViewModel;

/**
 * 龙虎榜.
 */
public class LongHuBangFragment extends BaseFragment {

    LongHuBangFragmentViewModel mViewModel;

    public LongHuBangFragment() {
        // Required empty public constructor
    }
    public static LongHuBangFragment newInstance(String param1, String param2) {
        LongHuBangFragment fragment = new LongHuBangFragment();

        return fragment;
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_long_hu_bang, BR.longHuBang, mViewModel);
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(LongHuBangFragmentViewModel.class);
    }
}