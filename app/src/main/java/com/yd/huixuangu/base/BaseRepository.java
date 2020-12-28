package com.yd.huixuangu.base;

import com.yd.httpmudule.RetrofitService;
import com.yd.huixuangu.net.http.ApiService;

public class BaseRepository {

    protected ApiService api = RetrofitService.createService(ApiService.class);

}
