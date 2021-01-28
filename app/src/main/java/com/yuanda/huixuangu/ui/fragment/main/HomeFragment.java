package com.yuanda.huixuangu.ui.fragment.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.databinding.FragmentHomeBinding;
import com.yuanda.huixuangu.adapter.main.EntranceRecyclerViewAdapter;
import com.yuanda.huixuangu.adapter.main.ImageAdapter;
import com.yuanda.huixuangu.adapter.main.ZhiShuRecyclerViewAdapter;
import com.yuanda.huixuangu.base.BaseFragment;
import com.yuanda.huixuangu.base.DataBindingConfig;
import com.yuanda.huixuangu.bean.Data;
import com.yuanda.huixuangu.net.socket.NodePath;
import com.yuanda.huixuangu.net.socket.SocketModule;
import com.yuanda.huixuangu.net.socket.WebSocketListener;
import com.yuanda.huixuangu.viewmodel.main.HomeFragmentViewModel;
import com.yuanda.huixuangu.viewmodel.main.ItemHomeEntranceViewModel;
import com.yuanda.huixuangu.viewmodel.main.ItemMainZhuShuVM;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements WebSocketListener {
    private HomeFragmentViewModel mViewModel;
    private static HomeFragment homeFragment;
    FragmentHomeBinding homeBinding;
    EntranceRecyclerViewAdapter entranceAdapter;
    ZhiShuRecyclerViewAdapter zhishuAdapter;
    HomeFragmentCallBack homeFragmentCallBack;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        homeFragmentCallBack = new HomeFragmentCallBack();
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_home, BR.home, mViewModel);
        bindingConfig.addBindingParam(BR.callback, homeFragmentCallBack);
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

        setZhiShuData();

        setBannerData();

        setEntranceData();

        initWebSocketSubscribe();

    }

    private void setBannerData() {
        List<Integer> bannerData = new ArrayList<>();
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);
        bannerData.add(R.mipmap.guandian_header);
        homeBinding.mainBanner.setAdapter(new ImageAdapter(bannerData));
    }

    private void setZhiShuData() {

        ArrayList<ItemMainZhuShuVM> zhishuList = new ArrayList<>();
        ItemMainZhuShuVM itemMainZhuShuBean = new ItemMainZhuShuVM();
        itemMainZhuShuBean.getTitle().setValue("上证指数");
        zhishuList.add(itemMainZhuShuBean);

        ItemMainZhuShuVM itemMainZhuShuBean1 = new ItemMainZhuShuVM();
        itemMainZhuShuBean1.getTitle().setValue("深证指数");
        zhishuList.add(itemMainZhuShuBean1);

        ItemMainZhuShuVM itemMainZhuShuBean2 = new ItemMainZhuShuVM();
        itemMainZhuShuBean2.getTitle().setValue("创业板指");
        zhishuList.add(itemMainZhuShuBean2);

        ItemMainZhuShuVM itemMainZhuShuBean3 = new ItemMainZhuShuVM();
        itemMainZhuShuBean3.getTitle().setValue("科创50");
        zhishuList.add(itemMainZhuShuBean3);


        ItemMainZhuShuVM itemMainZhuShuBean4 = new ItemMainZhuShuVM();
        itemMainZhuShuBean4.getTitle().setValue("中小扳指");
        zhishuList.add(itemMainZhuShuBean4);


        ItemMainZhuShuVM itemMainZhuShuBean5 = new ItemMainZhuShuVM();
        itemMainZhuShuBean5.getTitle().setValue("沪深300");
        zhishuList.add(itemMainZhuShuBean5);


        ItemMainZhuShuVM itemMainZhuShuBea6 = new ItemMainZhuShuVM();
        itemMainZhuShuBea6.getTitle().setValue("上证50");
        zhishuList.add(itemMainZhuShuBea6);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        zhishuAdapter = new ZhiShuRecyclerViewAdapter(homeFragmentCallBack);
        zhishuAdapter.setzhishuDataList(zhishuList);
        homeBinding.zhishuRecyclerView.setLayoutManager(linearLayoutManager);
        homeBinding.zhishuRecyclerView.setAdapter(zhishuAdapter);

    }

    private void setEntranceData() {

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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        homeBinding.entranceRecyclerView.setLayoutManager(gridLayoutManager);
        entranceAdapter = new EntranceRecyclerViewAdapter();
        entranceAdapter.setDataList(entranceViewModels);
        homeBinding.entranceRecyclerView.setAdapter(entranceAdapter);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    /**
     * 使其与fragment可以监听到websocket
     */
    private void initWebSocketSubscribe() {
        setSubscribe(this);
    }

    /**
     * 源达云推送数据的时候会走这里。
     * 各种数据格式我知道 ，没文档。只抽了一个SocketModule这样一个父类。
     * 详情还要看rn端每个接口数据格式实现。或许我们可以统一一下
     * <p>
     * 通过data.data里的path 对比，就是关心的某个节点数据，你也可以关心多个节点
     */
    @Override
    public void onReceiveSocket(SocketModule data) {
        Data internalData = data.getData();
        if (internalData == null) return;
        String nodePath = internalData.getNodePath();
        if (NodePath.sczl.equals(nodePath))
            Log.d("wgl-----", data.getData().getNodePath());
    }

    public class HomeFragmentCallBack {

        /**
         * 这里演示订阅节点  也就是RN里的 on方法
         * 顺便也演示一下 mvvm 在recycleview中怎么用的 所以点击事件就放在了 recycleview中，注意adapter的49行
         */
        public void zhiShuOnClick() {
            mViewModel.subscribeNodeMianZhangDieFu();
        }


        /**
         * 这里是RXjava的多请求合并
         * 这里只写了3中操作符 merge zip concat
         */
        public void moreHttpRequest() {

            mViewModel.sendRequest();
        }

    }
}
