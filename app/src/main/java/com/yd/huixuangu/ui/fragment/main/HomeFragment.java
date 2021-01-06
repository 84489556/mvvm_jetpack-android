package com.yd.huixuangu.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.VideoTestActivity;
import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.adapter.HomeRecyclerViewAdapter;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.databinding.FragmentHomeBinding;
import com.yd.huixuangu.databinding.FragmentMainBinding;
import com.yd.huixuangu.viewmodel.main.HomeFragmentViewModel;

public class HomeFragment extends BaseFragment {
    private HomeFragmentViewModel mViewModel;
    private static HomeFragment homeFragment;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_home, BR.home, mViewModel);
        bindingConfig.addBindingParam(BR.callback, new HomeFragmentCallBack());
        return bindingConfig;
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(HomeFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initView() {
        FragmentHomeBinding mBinding = (FragmentHomeBinding) this.mBinding;
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(gridLayoutManager);
        mBinding.recyclerView.setAdapter(new HomeRecyclerViewAdapter(getContext()));
    }

    public class HomeFragmentCallBack {

        public void openSecondFragment() {

            Bundle bundle = new Bundle();
            bundle.putString("key", "来自mainfragment的数据");
            nav().navigate(R.id.action_homeFragment_to_secondFragment, bundle);
        }


        public void moreHttpRequest() {

            mViewModel.sendRequest();
        }

        public void toVideo() {
            assert getActivity() == null;
            Intent intent = new Intent(getContext(), VideoTestActivity.class);
            getActivity().startActivity(intent);
        }


    }
}
