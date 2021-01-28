package com.yuanda.huixuangu.base;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.yd.httpmudule.RetrofitService;
import com.yuanda.huixuangu.net.http.ApiService;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BasePresenter<T> {
    private ApiService service = RetrofitService.createService(ApiService.class);

    public BasePresenter() {
    }


    public abstract void onSubscribePresenter(@NonNull Disposable d);

    public abstract void onNextPresenter(@NonNull T t);

    public abstract void onErrorPresenter(@NonNull String e);

    public abstract void onCompletePresenter();

    private Observer<T> observer = new Observer<T>() {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            onSubscribePresenter(d);
        }


        @Override
        public void onNext(@NonNull T t) {
            if (t != null){
                analysisServerIntent(t);
            }

        }

        @Override
        public void onError(@NonNull Throwable e) {
            onErrorPresenter(e.toString());
        }

        @Override
        public void onComplete() {
            onCompletePresenter();
        }
    };

    /**
     * 在这里处理和后端交互协议
     * 随便找了高管交易榜的接口，没有sateCode
     *
     */
    private void analysisServerIntent(T t) {
//        BaseResponse base = (BaseResponse) o;
//        switch (base.getStatusCode()) {
//            case ServerDictionaries.SUCCESS:
                onNextPresenter(t);
//                break;
//            case ServerDictionaries.NOT_LOGIN:
//                tipDialog();
//                break;
//        }
    }



    public void onDestroy() {

    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setService(ApiService service) {
        this.service = service;
    }

    public ApiService getService() {
        return service;
    }


    private void tipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HuiXuanGuApplication.context);
        builder.setTitle("提示：");
        builder.setMessage("token过期");
        builder.setCancelable(true);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();


    }

    //----------------webSocket-----------------

}
