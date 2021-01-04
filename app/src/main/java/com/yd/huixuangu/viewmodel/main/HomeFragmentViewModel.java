package com.yd.huixuangu.viewmodel.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yd.huixuangu.repository.main.MainFragmentRepository;

public class HomeFragmentViewModel extends ViewModel {
    private MutableLiveData<String> one;
    private MutableLiveData<String> two;
    private MutableLiveData<String> three;

    public MutableLiveData<String> getOne() {
        if (one == null) {
            one = new MutableLiveData<>();
            one.setValue("我是初始数据1");
        }
        return one;
    }

    public MutableLiveData<String> getTwo() {
        if (two == null) {
            two = new MutableLiveData<>();
            two.setValue("我是初始数据2");
        }
        return two;
    }

    public MutableLiveData<String> getThree() {
        if (three == null) {
            three = new MutableLiveData<>();
            three.setValue("我是初始数据3");
        }
        return three;
    }


    public void sendRequest() {
        MainFragmentRepository repository = new MainFragmentRepository();
        repository.request2();


    }
}
