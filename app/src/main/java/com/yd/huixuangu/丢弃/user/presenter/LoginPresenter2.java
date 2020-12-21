package com.yd.huixuangu.丢弃.user.presenter;

import android.util.Log;

import com.yd.huixuangu.base.ApiService;
import com.yd.huixuangu.base.BasePresenter;
import com.yd.huixuangu.base.NodePath;
import com.yd.huixuangu.丢弃.user.bean.ClassesBean;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.QueryModule;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 这个网络请求是对应RN项目中有个get（）方法请求网络
 */
public class LoginPresenter2 extends BasePresenter<ClassesBean> {

    /**
     * 通过接口通知UI
     */
    private ILogin iLogin;

    public LoginPresenter2() {
        this.iLogin = iLogin;
        next();
    }

    public void next() {
        /**
         * !!!目前报错后台解析格式错误
         *
         *
         * 组拼参数，链式编程。因为只有那几个参数可选、，构建出来对象后传参给RequestBody
         *         private String orderByKey;
         *         private String equalTo;
         *         private String startAt;
         *         private String endAt;
         *         private int limitToFirst;
         *         private String limitToLast;
         *         private String nodePath;
         *         private String api ;
         *         private boolean firstLevel = true;
         */
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
