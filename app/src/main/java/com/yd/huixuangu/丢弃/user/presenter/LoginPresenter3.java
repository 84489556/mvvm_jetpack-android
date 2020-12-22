package com.yd.huixuangu.丢弃.user.presenter;

import android.util.Log;

import com.yd.huixuangu.base.BasePresenter;
import com.yd.huixuangu.net.socket.NodePath;
import com.yd.huixuangu.丢弃.user.bean.ClassesBean;
import com.yd.huixuangu.丢弃.user.bean.GaoguanjingmaishichangtongjiBean;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.QueryModule;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 这个网络请求是对应RN项目中有个get（）方法请求网络
 */
public class LoginPresenter3 extends BasePresenter<ClassesBean> {

    /**
     * 通过接口通知UI
     */
    private ILogin iLogin;

    public LoginPresenter3() {
        this.iLogin = iLogin;
        next();
    }

    public void next() {
        QueryModule queryModule = new QueryModule.Builder()
                .api("/get")
                .orderByKey("$key")
                .nodePath(NodePath.classes)
                .build();
        String param = GsonSingle.getInstance().toJson(queryModule);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), param);


        Observable<GaoguanjingmaishichangtongjiBean> ggjy = getService().gaoguanjingmaishichangtongji();
        Observable<String> zbjClasses = getService().zbjClasses(body);
        ggjy.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<GaoguanjingmaishichangtongjiBean>() {
                    @Override
                    public void accept(GaoguanjingmaishichangtongjiBean bean) throws Throwable {
                        //第一个网络请求结果
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<GaoguanjingmaishichangtongjiBean, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(GaoguanjingmaishichangtongjiBean bean) throws Throwable {
                        //开始请求第二个网络
                        return zbjClasses;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        //第二个网络请求结果
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        //发生请求错误
                    }
                });

    }

    @Override
    public void onSubscribePresenter(@NonNull Disposable d) {
    }

    @Override
    public void onNextPresenter(@NonNull ClassesBean classesBean) {
        iLogin.success2(classesBean);
    }


    @Override
    public void onErrorPresenter(@NonNull String e) {
        Log.d("wgl", e);
    }

    @Override
    public void onCompletePresenter() {

    }
}
