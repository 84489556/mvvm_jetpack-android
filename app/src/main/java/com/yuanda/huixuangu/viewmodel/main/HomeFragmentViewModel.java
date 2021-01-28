package com.yuanda.huixuangu.viewmodel.main;

import com.yuanda.huixuangu.base.BaseViewModel;
import com.yuanda.huixuangu.repository.main.MainFragmentRepository;

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
