package com.yuanda.cy_professional_select_stock.ui.fragment.main;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.adapter.main.LeftAdapter;
import com.yuanda.cy_professional_select_stock.adapter.main.RightAdapter;
import com.yuanda.cy_professional_select_stock.adapter.main.Stock;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.databinding.FragmentScrolllistBinding;

import java.util.ArrayList;
import java.util.List;

public class ScrollListDemoFragment extends BaseFragment {
    FragmentScrolllistBinding binding;
    //左侧固定一列数据适配
    private List<String> leftListData;

    //右侧数据适配
    private List<Stock> stockList;


    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_scrolllist, BR.scrollDemo, null);
    }

    @Override
    protected void initViewModel() {
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        super.initView();
        binding = (FragmentScrolllistBinding) this.mBinding;
        initData();
        // 设置两个水平控件的联动
        binding.titleHorsv.setScrollView(binding.contentHorsv);
        binding.contentHorsv.setScrollView(binding.titleHorsv);


        LeftAdapter leftAdapter = new LeftAdapter(R.layout.item_left_content, leftListData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.leftListview.setLayoutManager(linearLayoutManager);
        binding.leftListview.setAdapter(leftAdapter);

        RightAdapter rightAdapter = new RightAdapter(R.layout.item_right_content, stockList);
        LinearLayoutManager linearLayoutManagerR = new LinearLayoutManager(getContext());
        linearLayoutManagerR.setOrientation(RecyclerView.VERTICAL);
        binding.rightListview.setLayoutManager(linearLayoutManagerR);
        binding.rightListview.setAdapter(rightAdapter);


        binding.refreshLayout.setDragRate(1f);

    }

    private void initData() {
        stockList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            stockList.add(new Stock("风华基金", "222", "333", "444", "555", "666", "dsd", "sdsd"));
        }
        leftListData = new ArrayList<>();
        for (int i = 0; i < stockList.size(); i++) {
            leftListData.add(stockList.get(i).getName());
        }


    }

}
