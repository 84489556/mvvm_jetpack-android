package com.yuanda.huixuangu.repository.main;

import android.util.Log;

import com.yuanda.huixuangu.base.BaseRepository;
import com.yuanda.huixuangu.base.HuiXuanGuApplication;
import com.yuanda.huixuangu.net.socket.CMDConstant;
import com.yuanda.huixuangu.net.socket.NodePath;
import com.yuanda.huixuangu.net.socket.RequestModule;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.websocket.YDYWebSocketManage;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainFragmentRepository extends BaseRepository {
    Observable<Object> ggjy;
    Observable<Object> zuixinjiaoyi;

    public MainFragmentRepository() {
        ggjy = api.ggjmsctj().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        zuixinjiaoyi = api.zuixinjiaoyi().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());


    }


    /**
     * concat 有序
     * <p>
     * concat（）最多4个 / concatArray（）可超过4个
     * <p>
     * 中间某个请求error后，不会再收到后续回调
     * <p>
     * 等待当前可观察到的终止后才转移到下一个
     * <p>
     * onnext会执行多次
     */
    public void request() {
        Observable.concat(ggjy, zuixinjiaoyi)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object integer) {
                        Log.d("wgl onNext", integer.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("wgl onError", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * merge  不等待当前可观察到的终止后才转移到下一个
     * 中间一个请求断了就不会再继续接收其它响应了
     * <p>
     * merge（）最多4个 / mergeArray（）可超过4个
     */
    public void request1() {
        Observable.mergeArray(zuixinjiaoyi, ggjy, ggjy, ggjy, ggjy, ggjy, ggjy, ggjy, ggjy, ggjy, zuixinjiaoyi)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object integer) {
                        Log.d("wgl merge onNext", integer.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("wgl merge onError", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * zip可以把两次请求"结果"合并到一起
     * 其中一个失败，就会走onError
     * <p>
     * BiFunction
     * T - 函数的第一个参数的类型
     * U - 函数的第二个参数的类型
     * R - 函数结果的类型
     */

    public void request2() {
        Observable.zip(ggjy, zuixinjiaoyi, new BiFunction<Object, Object, Object>() {
            @Override
            public Object apply(Object o, Object o2) throws Throwable {
                Log.d("wgl o apply ", o.toString());
                Log.d("wgl o2 apply ", o2.toString());
                return o2.toString().concat("---------" + o.toString());
            }

        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {


            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.d("wgl o onNext ", o.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("wgl o onError ", e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 通过这个可以得到首页 涨了xxx家 ，跌了xxx家那个数据
     * 我也不知道 cmd 是什么的缩写   我查看了下RN的用法 只有开关两个值
     * socketID我也不知道是啥
     * path 就是节点的路径
     */
    public void subscribeNodeMianZhangDieFu() {
        RequestModule build = RequestModule.builder().cmd(CMDConstant.ON)
                .id(HuiXuanGuApplication.socketID)
                .path(NodePath.sczl)
                .build();
        String sczl = GsonSingle.getInstance().toJson(build);
        YDYWebSocketManage.getInstance().subscribe(sczl);
    }
}
