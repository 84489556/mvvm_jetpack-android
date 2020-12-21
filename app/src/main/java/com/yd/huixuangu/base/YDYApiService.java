package com.yd.huixuangu.base;


import com.yd.huixuangu.丢弃.user.bean.GaoguanjingmaishichangtongjiBean;
import com.yd.huixuangu.丢弃.user.bean.UserBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface YDYApiService {


    /**
     * 例子：
     * host:main  指定host
     * system/login 接口名称
     */
    @Headers("host:main")
    @POST("system/login")
    Observable<UserBean> systemLogin(@Body String userId, @Body String password);

    /**
     * 高管交易榜最新交易近一年高管市场统计
     */
    @Headers("host:"+HostManage.HostKey.HXG)
    @GET("/ydhxg/GaoGuanJiaoYi/gaoguanjingmaishichangtongji")
    Observable<GaoguanjingmaishichangtongjiBean> gaoguanjingmaishichangtongji();


    @FormUrlEncoded
    @Headers("host:user")
    @POST("system/login")
    Observable<String> changePWD(@FieldMap Map<String, String> map);
}
