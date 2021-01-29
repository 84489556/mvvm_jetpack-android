package com.yuanda.cy_professional_select_stock.viewmodel.main;

import androidx.lifecycle.MutableLiveData;

import com.yuanda.cy_professional_select_stock.base.BaseViewModel;

public class ItemHomeEntranceViewModel extends BaseViewModel {
    public MutableLiveData<String> title;

    public MutableLiveData<String> getTitle() {
        if (title == null) {
            title = new MutableLiveData<String>();
            title.setValue("0000");
        }
        return title;
    }
}
