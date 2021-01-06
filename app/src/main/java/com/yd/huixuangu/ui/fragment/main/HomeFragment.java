package com.yd.huixuangu.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.VideoTestActivity;
import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.adapter.main.EntranceRecyclerViewAdapter;
import com.yd.huixuangu.adapter.main.ImageAdapter;
import com.yd.huixuangu.adapter.main.ZhiShuRecyclerViewAdapter;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.bean.main.ItemMainBannerBean;
import com.yd.huixuangu.databinding.FragmentHomeBinding;
import com.yd.huixuangu.viewmodel.main.HomeFragmentViewModel;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

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


    List<Integer> bannerData;

    @Override
    public void initView() {
        FragmentHomeBinding mBinding = (FragmentHomeBinding) this.mBinding;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        mBinding.entranceRecyclerView.setLayoutManager(gridLayoutManager);
        mBinding.entranceRecyclerView.setAdapter(new EntranceRecyclerViewAdapter(getContext()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mBinding.zhishuRecyclerView.setLayoutManager(linearLayoutManager);
        mBinding.zhishuRecyclerView.setAdapter(new ZhiShuRecyclerViewAdapter());


        bannerData = new ArrayList<>();
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);

        mBinding.mainBanner.setAdapter(new ImageAdapter(bannerData));


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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
