package com.yuanda.huixuangu.viewmodel.guandian;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

public class GuanDianFragmentViewModel extends ViewModel {
    public final ObservableArrayList<String> tabNames = new ObservableArrayList<String>();

    {
        tabNames.add("专家分析");
        tabNames.add("热点聚焦");
        tabNames.add("资讯");
    }
}