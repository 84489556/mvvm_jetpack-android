package com.yd.huixuangu.base;

public interface CallBack<T> {
    T onNext(String s);
    String onError();
}
