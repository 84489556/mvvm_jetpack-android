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
import com.yd.huixuangu.databinding.FragmentHomeBinding;
import com.yd.huixuangu.viewmodel.main.HomeFragmentViewModel;
import com.yd.huixuangu.viewmodel.main.ItemHomeEntranceViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private HomeFragmentViewModel mViewModel;
    private static HomeFragment homeFragment;
    FragmentHomeBinding homeBinding;

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
        homeBinding = (FragmentHomeBinding) this.mBinding;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        homeBinding.entranceRecyclerView.setLayoutManager(gridLayoutManager);
        homeBinding.entranceRecyclerView.setAdapter(new EntranceRecyclerViewAdapter());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        homeBinding.zhishuRecyclerView.setLayoutManager(linearLayoutManager);
        homeBinding.zhishuRecyclerView.setAdapter(new ZhiShuRecyclerViewAdapter());


        List<Integer> bannerData = new ArrayList<>();
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);

        homeBinding.mainBanner.setAdapter(new ImageAdapter(bannerData));
        setEntranceData();


    }

    private void setEntranceData() {
        EntranceRecyclerViewAdapter adapter = (EntranceRecyclerViewAdapter) homeBinding.entranceRecyclerView.getAdapter();
        if (adapter == null) return;
        ItemHomeEntranceViewModel viewModel = new ItemHomeEntranceViewModel();
        viewModel.getTitle().setValue("哈哈哈");
        ItemHomeEntranceViewModel viewModel1 = new ItemHomeEntranceViewModel();
        viewModel1.getTitle().setValue("呵呵呵");
        ArrayList<ItemHomeEntranceViewModel> entranceViewModels = new ArrayList<>();
        entranceViewModels.add(viewModel);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        entranceViewModels.add(viewModel1);
        adapter.setDataList(entranceViewModels);

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
