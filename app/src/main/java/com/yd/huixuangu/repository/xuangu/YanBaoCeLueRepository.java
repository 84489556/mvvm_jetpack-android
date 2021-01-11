package com.yd.huixuangu.repository.xuangu;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.yd.httpmudule.RetrofitService;
import com.yd.huixuangu.bean.GaoguanjingmaishichangtongjiBean;
import com.yd.huixuangu.bean.xuangu.BurstBean;
import com.yd.huixuangu.net.http.ApiService;
import com.yd.huixuangu.net.http.XGApiService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class YanBaoCeLueRepository {
    private static final String TAG = YanBaoCeLueRepository.class.getSimpleName();

    private XGApiService service = RetrofitService.createService(XGApiService.class);
    private static YanBaoCeLueRepository instance;
    private YanBaoCeLueRepository(){}
    public static synchronized YanBaoCeLueRepository getInstance(){
        if(instance == null){
            instance = new YanBaoCeLueRepository();
        }
        return instance;
    }
    public MutableLiveData<String> getBrifData(){

        return null;
    }
    public MutableLiveData<String> getSchoolData(){

        return null;
    }
    //获取涨幅空间列表数据
    public MutableLiveData<BurstBean> getBurstData() {
        final MutableLiveData<BurstBean> data = new MutableLiveData<>();
        service.getBurstData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BurstBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull BurstBean s) {
                        Log.i(TAG,s.toString());
                        data.setValue(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.d("wgl onComplete", "onComplete");
                    }
                });
        return data;
    }
}
