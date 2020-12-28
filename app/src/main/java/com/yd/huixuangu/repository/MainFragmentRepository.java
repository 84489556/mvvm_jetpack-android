package com.yd.huixuangu.repository;

import android.util.Log;

import com.yd.huixuangu.base.BaseRepository;
import com.yd.huixuangu.bean.GaoguanjingmaishichangtongjiBean;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainFragmentRepository extends BaseRepository {
    Observable<Object> ggjy;
    Observable<Object> zuixinjiaoyi;

    public MainFragmentRepository() {
        ggjy = api.ggjmsctj();
        zuixinjiaoyi = api.zuixinjiaoyi();


    }


    /**
     * concat 有序
     *
     * concat（）最多4个 / concatArray（）可超过4个
     *
     * 中间某个请求error后，不会再收到后续回调
     *
     * 等待当前可观察到的终止后才转移到下一个
     *
     */
    public void request() {
        Observable.concat(ggjy,zuixinjiaoyi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object integer) {
                Log.d("wgl onNext",integer.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("wgl onError",e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }





    /**
     * merge  不等待当前可观察到的终止后才转移到下一个
     *
     * merge（）最多4个 / mergeArray（）可超过4个
     */
    public void request1() {
        Observable.mergeArray(zuixinjiaoyi,ggjy,ggjy,ggjy,ggjy,ggjy,ggjy,ggjy,ggjy,ggjy,zuixinjiaoyi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object integer) {
                Log.d("wgl merge onNext",integer.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("wgl merge onError",e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
