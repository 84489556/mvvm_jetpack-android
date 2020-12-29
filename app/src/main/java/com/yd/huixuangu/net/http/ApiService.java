package com.yd.huixuangu.net.http;


import com.yd.huixuangu.net.socket.SocketURLHelp;
import com.yd.huixuangu.bean.GaoguanjingmaishichangtongjiBean;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface  ApiService  {




    /**
     * 高管交易榜最新交易近一年高管市场统计
     */
    @Headers("host:"+ HostManage.HostKey.HXG)
    @GET("/ydhxg/GaoGuanJiaoYi/gaoguanjingmaishichangtongji")
    Observable<GaoguanjingmaishichangtongjiBean> gaoguanjingmaishichangtongji();




    /**
     * 高管交易榜最新交易近一年高管市场统计
     */
    @Headers("host:"+ HostManage.HostKey.HXG)
    @GET("/ydhxg/GaoGuanJiaoYi/gaoguanjingmaishichangtongji")
    Observable<Object> ggjmsctj();
    /**
     * 最新交易
     */
    @Headers("host:"+ HostManage.HostKey.HXG)
    @GET("/ydhxg/GaoGuanJiaoYi/zuixinjiaoyi?pageNum=1&pageSize=2&indus=&desc=true")
    Observable<Object> zuixinjiaoyi();


    //    @FormUrlEncoded
    @Headers({"Content-type:application/json;charset=UTF-8","host:"+HostManage.HostKey.YDY})
    @POST(SocketURLHelp.nodeTree)
    Observable<String> zbjClasses(@Body RequestBody params);


    @Headers({"Content-type:application/json;charset=UTF-8","host:"+HostManage.HostKey.YDY})
    @POST(SocketURLHelp.nodeTree)
    Observable<String> hotStock(@Body RequestBody params);
}
