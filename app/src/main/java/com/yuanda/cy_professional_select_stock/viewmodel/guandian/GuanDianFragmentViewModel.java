package com.yuanda.cy_professional_select_stock.viewmodel.guandian;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

public class GuanDianFragmentViewModel extends ViewModel {
    public final ObservableArrayList<String> tabNames = new ObservableArrayList<String>();
    public final ObservableBoolean isInputEnabled = new ObservableBoolean(); //判断viewPage父类是否能滑动

    {
        tabNames.add("专家分析");
        tabNames.add("热点聚焦");
        tabNames.add("资讯");

        isInputEnabled.set(true);
    }
}