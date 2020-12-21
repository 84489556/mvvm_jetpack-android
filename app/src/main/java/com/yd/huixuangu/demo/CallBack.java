package com.yd.huixuangu.demo;

public interface CallBack<T> {
    T onNext(String s);
    String onError();
}
