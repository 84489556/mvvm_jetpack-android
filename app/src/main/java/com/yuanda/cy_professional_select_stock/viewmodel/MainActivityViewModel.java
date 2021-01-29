package com.yuanda.cy_professional_select_stock.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.yuanda.cy_professional_select_stock.base.BaseViewModel;

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

    public MutableLiveData<Boolean> isInputEnabled;//用于控制观点页面滑动冲突的变量

    public MutableLiveData<Boolean> getInputEnabled() {
        if (isInputEnabled == null) {
            isInputEnabled = new MutableLiveData<>();
            isInputEnabled.setValue(true);
        }
        return isInputEnabled;
    }



}
