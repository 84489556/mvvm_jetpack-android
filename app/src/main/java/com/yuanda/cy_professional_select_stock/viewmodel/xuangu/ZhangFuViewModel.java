package com.yuanda.cy_professional_select_stock.viewmodel.xuangu;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.yuanda.cy_professional_select_stock.base.BaseViewModel;
import com.yuanda.cy_professional_select_stock.bean.xuangu.BurstBean;
import com.yuanda.cy_professional_select_stock.repository.xuangu.YanBaoCeLueRepository;

public class ZhangFuViewModel extends BaseViewModel {
    public MediatorLiveData<BurstBean> burstLiveData = new MediatorLiveData<>();

    public void getBurstData() {
        YanBaoCeLueRepository repository = YanBaoCeLueRepository.getInstance();
        burstLiveData.addSource(repository.getBurstData(), new Observer<BurstBean>() {
            @Override
            public void onChanged(BurstBean burstBean) {
                burstLiveData.setValue(burstBean);
            }
        });
    }

}
