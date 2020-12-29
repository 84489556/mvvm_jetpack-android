package com.yuanda.usercenter.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.yd.httpmudule.RetrofitService;
import com.yuanda.usercenter.api.AccountApi;
import com.yuanda.usercenter.base.BaseResp;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AccountRepository {
    private final static AccountRepository accountRepository = new AccountRepository();

    private AccountApi service = RetrofitService.createService(AccountApi.class);
    private AccountRepository(){}
    public static AccountRepository getInstance(){
        return accountRepository;
    }

    public MutableLiveData<BaseResp> sendLoginSmsCode(String params){
//        service.sendLoginSmsCode(params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BaseResp>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(@NonNull BaseResp s) {
////                        callBack.onNext(s.toString());
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
////                        callBack.onError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("wgl onComplete", "onComplete");
//                    }
//                });

        return new MutableLiveData<BaseResp>();
    }
}
