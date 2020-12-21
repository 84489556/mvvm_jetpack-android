package com.yd.huixuangu.丢弃.user.presenter;

public  interface ILogin <T,B>  {
     void success(T msg);
     void failure(String msg);
     void success2(B results);
}
