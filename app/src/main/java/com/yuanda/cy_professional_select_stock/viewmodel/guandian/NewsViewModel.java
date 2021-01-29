package com.yuanda.cy_professional_select_stock.viewmodel.guandian;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel {
    public final ObservableArrayList<String> tabNames = new ObservableArrayList<String>();

    {
        tabNames.add("财经报道");
        tabNames.add("自选股");
        tabNames.add("公司新闻");
        tabNames.add("公司研究");
        tabNames.add("行业研究");
    }
}
