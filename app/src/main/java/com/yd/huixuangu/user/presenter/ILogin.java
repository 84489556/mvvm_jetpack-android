package com.yd.huixuangu.user.presenter;

import com.yd.huixuangu.base.BaseInterface;

public  interface ILogin <T,B>  {
     void success(T msg);
     void failure(String msg);
     void success2(B results);
}
