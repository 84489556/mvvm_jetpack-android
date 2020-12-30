package com.yuanda.usercenter.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yd.httpmudule.RetrofitService;
import com.yuanda.usercenter.api.AccountApi;
import com.yuanda.usercenter.base.BaseResp;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AccountRepository {
    private final String TAG = AccountRepository.class.getSimpleName();
    private final static AccountRepository accountRepository = new AccountRepository();

    private AccountApi service = RetrofitService.createService(AccountApi.class);
    private AccountRepository(){}
    public static AccountRepository getInstance(){
        return accountRepository;
    }

    public MutableLiveData<BaseResp> sendLoginSmsCode(String params) {
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

    /**
     * let paramLogin = {
     *         'username': param.username,
     *         'password': param.password,
     *         'last_time': param.last_time,
     *         'version': version,
     *         'type': typeSystem
     *     };
     * @param phone
     * @param password
     * @return
     */
    public LiveData<BaseResp> loginWithPassword(String phone, String password) {
        final MutableLiveData<BaseResp> mutableLiveData = new MutableLiveData<>();
        String url = "https://cs-hxg-api.zslxt.com/api/hxg/app/v1/login";
        String params = "{\"username\":\"13730129660\",\"password\":\"a123456\",\"last_time\":\"2020-12-30 15:32:36\",\"version\":\"240\",\"type\":0,\"device_id\":\"865f274c98ce936a\",\"device_version\":\"9\",\"system\":\"COR-AL10\",\"channel\":\"official\",\"channelId\":381,\"source\":\"官网\"}";
        RequestBody body = RequestBody.create(params,MediaType.parse("application/json; charset=utf-8"));
        service.loginWithPwd(url,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull BaseResp s) {
                        Log.i(TAG,s.toString());
                        mutableLiveData.setValue(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.d("wgl onComplete", "onComplete");
                    }
                });

        return mutableLiveData;
    }
}
