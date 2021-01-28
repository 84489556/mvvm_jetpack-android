package com.yuanda.huixuangu.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.yuanda.huixuangu.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel {

    private MutableLiveData<String> data;
    public MutableLiveData<String> getData() {
        if (data == null){
            data = new  MutableLiveData<String>();
            data.setValue("0");
        }
        return data;
    }
    public void getRemoteData(){
        data.setValue("set");
//        DataRepository userRepository = new DataRepository();
//        userRepository.getGGJMSCTJ(new CallBack<String>() {
//            @Override
//            public String onNext(String s) {
//                data.postValue(s);
//                return null;
//            }
//
//            @Override
//            public String onError() {
//                return null;
//            }
//        });
    }




}
