package com.yd.huixuangu.demo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yd.httpmudule.RetrofitService;
import com.yd.huixuangu.base.ApiService;
import com.yd.huixuangu.丢弃.user.bean.GaoguanjingmaishichangtongjiBean;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DataRepository {
    private ApiService service = RetrofitService.createService(ApiService.class);
    final MutableLiveData<String> data = new MutableLiveData<>();
    /**
     * GGJMSCTJ   =  高管净买市场统计
     */
    public MutableLiveData<String> getGGJMSCTJ(CallBack<String> callBack) {
        service.gaoguanjingmaishichangtongji()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GaoguanjingmaishichangtongjiBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull GaoguanjingmaishichangtongjiBean s) {
                        callBack.onNext(s.toString());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("wgl onComplete", "onComplete");
                    }
                });

        return data;
    }

}
