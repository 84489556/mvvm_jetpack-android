package com.yd.huixuangu.user.presenter;

import com.yd.huixuangu.base.BasePresenter;
import com.yd.huixuangu.user.bean.GaoguanjingmaishichangtongjiBean;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<GaoguanjingmaishichangtongjiBean> {

    private ILogin iLogin;
    Observer<Object> observer;
    Observable<GaoguanjingmaishichangtongjiBean> gaoguanjingmaishichangtongji;
    public LoginPresenter(ILogin iLogin) {
        super();
        this.iLogin = iLogin;
        start();
    }

    private void start() {

        observer = getObserver();
        gaoguanjingmaishichangtongji= getService().gaoguanjingmaishichangtongji();
        gaoguanjingmaishichangtongji
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }

    @Override
    public void onSubscribePresenter(@NonNull Disposable d) {
    }

    @Override
    public void onNextPresenter(GaoguanjingmaishichangtongjiBean bean) {
        iLogin.success(bean.toString());

    }


    @Override
    public void onErrorPresenter(@NonNull String e) {
        iLogin.failure("onErrorPresenter");
    }

    @Override
    public void onCompletePresenter() {
        iLogin.success("onCompletePresenter");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
