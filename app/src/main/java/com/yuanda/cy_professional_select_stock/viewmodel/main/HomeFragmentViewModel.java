package com.yuanda.cy_professional_select_stock.viewmodel.main;

import com.yuanda.cy_professional_select_stock.base.BaseViewModel;
import com.yuanda.cy_professional_select_stock.repository.main.MainFragmentRepository;

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
