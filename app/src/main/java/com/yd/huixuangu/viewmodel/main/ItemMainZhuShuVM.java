package com.yd.huixuangu.viewmodel.main;

import androidx.lifecycle.MutableLiveData;

import com.yd.huixuangu.base.BaseViewModel;

public class ItemMainZhuShuVM extends BaseViewModel {
    public MutableLiveData<String> title;

    public MutableLiveData<String> getTitle() {
        if (title == null) {
            title = new MutableLiveData<String>();
            title.setValue("0000");
        }
        return title;
    }



}
