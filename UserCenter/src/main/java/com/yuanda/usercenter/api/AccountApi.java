package com.yuanda.usercenter.api;

import com.yuanda.usercenter.base.BaseResp;
import com.yuanda.usercenter.common.HostManage;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface AccountApi {

    @Headers({"Content-type:application/json;charset=UTF-8","host:"+ HostManage.HostKey.YDY})
    @POST("api/hxg/v2/sms")
    Observable<BaseResp> sendLoginSmsCode(@Body RequestBody params);
    //https://cs-hxg-api.zslxt.com/api/hxg/app/v1/login

    @POST()
    Observable<BaseResp> loginWithPwd(@Url String url, @Body RequestBody params);
}
