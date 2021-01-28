package com.yuanda.huixuangu.viewmodel.main;

import androidx.lifecycle.MutableLiveData;

import com.yuanda.huixuangu.base.BaseViewModel;

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
