package com.yd.huixuangu.viewmodel.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yd.huixuangu.base.BaseViewModel;
import com.yd.huixuangu.repository.main.MainFragmentRepository;

public class HomeFragmentViewModel extends BaseViewModel {
    private MainFragmentRepository repository;

    /**
     * 这里的是多个接口一起用的情况
     */
    public void sendRequest() {
        if (repository == null) {
            repository = new MainFragmentRepository();
        }

        repository.request2();
    }


    /**
     * 涨跌幅 ，就是首页有一个    ↑280家 -------/------- 209家↓
     */
    public void subscribeNodeMianZhangDieFu() {

        if (repository == null) {
            repository = new MainFragmentRepository();
        }
        repository.subscribeNodeMianZhangDieFu();
    }
}
