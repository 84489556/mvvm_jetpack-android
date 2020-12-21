package com.yd.huixuangu.demo;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    MutableLiveData<String> name;

    public MutableLiveData<String> getName() {
        if (name == null){
            name = new  MutableLiveData<String>();
        }
        return name;
    }

}
