package com.yuanda.usercenter.api;

import com.yuanda.usercenter.base.BaseResp;
import com.yuanda.usercenter.common.HostManage;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AccountApi {

    @Headers({"Content-type:application/json;charset=UTF-8","host:"+ HostManage.HostKey.YDY})
    @POST("api/hxg/v2/sms")
    Observable<BaseResp> sendLoginSmsCode(@Body RequestBody params);
}
