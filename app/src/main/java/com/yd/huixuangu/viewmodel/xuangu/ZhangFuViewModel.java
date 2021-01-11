package com.yd.huixuangu.viewmodel.xuangu;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.yd.huixuangu.base.BaseViewModel;
import com.yd.huixuangu.bean.xuangu.BurstBean;
import com.yd.huixuangu.repository.xuangu.YanBaoCeLueRepository;

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
