package com.yd.huixuangu.net.http;


import com.yd.huixuangu.bean.GaoguanjingmaishichangtongjiBean;
import com.yd.huixuangu.bean.xuangu.BurstBean;
import com.yd.huixuangu.net.socket.SocketURLHelp;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 选股
 */
public interface XGApiService {

    //    @FormUrlEncoded

    /**
     * 获取研报策略的介绍
     * CeLueJieShao后面的参数固定为1
     *
     *      标识：1研报策略,
     *      2高成长,
     *      3低估值,
     *      4高分红,
     *      5高盈利,
     *      6现金牛,
     *      7增持回购,
     *      8高运营,
     *      9白马绩优,
     *      10资金揭秘
     *      17.热点策略
     *      18.主题策略
     * @param params
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8","host:"+HostManage.HostKey.YDY})
    @POST(SocketURLHelp.nodeTree)
    Observable<String> getBrifData(@Body RequestBody params);

    /**
     * 获取成长学堂的数据
     * @param params
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8","host:"+HostManage.HostKey.YDY})
    @POST(SocketURLHelp.nodeTree)
    Observable<String> getSchoolData(@Body RequestBody params);


    /**
     * 获取涨幅空间列表数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8","host:"+HostManage.HostKey.XG})
    @POST("/celuexuangu/getzhangfukongjian")
    Observable<BurstBean> getBurstData();
}
