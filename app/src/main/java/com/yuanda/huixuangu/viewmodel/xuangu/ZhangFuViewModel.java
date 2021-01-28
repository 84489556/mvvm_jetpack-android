package com.yuanda.huixuangu.viewmodel.xuangu;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.yuanda.huixuangu.base.BaseViewModel;
import com.yuanda.huixuangu.bean.xuangu.BurstBean;
import com.yuanda.huixuangu.repository.xuangu.YanBaoCeLueRepository;

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
