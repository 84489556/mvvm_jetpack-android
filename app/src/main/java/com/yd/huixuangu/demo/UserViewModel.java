package com.yd.huixuangu.demo;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private MutableLiveData<String> data;
    public MutableLiveData<String> getData() {
        if (data == null){
            data = new  MutableLiveData<String>();
            data.setValue("0");
        }
        return data;
    }
    public void getRemoteData(){
        DataRepository userRepository = new DataRepository();
        userRepository.getGGJMSCTJ(new CallBack<String>() {
            @Override
            public String onNext(String s) {
                data.postValue(s);
                return null;
            }

            @Override
            public String onError() {
                return null;
            }
        });
    }




}