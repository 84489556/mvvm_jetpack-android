package com.yuanda.huixuangu.base;

import com.yd.httpmudule.RetrofitService;
import com.yuanda.huixuangu.net.http.ApiService;

public class BaseRepository {

    protected ApiService api = RetrofitService.createService(ApiService.class);

}
