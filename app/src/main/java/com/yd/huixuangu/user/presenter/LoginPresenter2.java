package com.yd.huixuangu.user.presenter;

import android.util.Log;

import com.yd.huixuangu.base.ApiService;
import com.yd.huixuangu.base.BasePresenter;
import com.yd.huixuangu.base.NodePath;
import com.yd.huixuangu.user.bean.ClassesBean;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.QueryModule;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginPresenter2 extends BasePresenter<ClassesBean> {

    public LoginPresenter2() {
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

        ApiService service = getService();
        service.zbjClasses(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }
    @Override
    public void onSubscribePresenter(@NonNull Disposable d) {

    }

    @Override
    public void onNextPresenter(@NonNull ClassesBean classesBean) {
        Log.d("wgl---",classesBean.getMsg());
    }



    @Override
    public void onErrorPresenter(@NonNull String e) {
        Log.d("wgl",e);
    }

    @Override
    public void onCompletePresenter() {

    }
}
