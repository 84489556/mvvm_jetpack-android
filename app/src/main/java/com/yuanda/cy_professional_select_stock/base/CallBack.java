package com.yuanda.cy_professional_select_stock.base;

public interface CallBack<T> {
    T onNext(String s);
    String onError();
}
