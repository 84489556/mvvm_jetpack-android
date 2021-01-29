package com.yuanda.cy_professional_select_stock.base;

import com.yd.httpmudule.RetrofitService;
import com.yuanda.cy_professional_select_stock.net.http.ApiService;

public class BaseRepository {

    protected ApiService api = RetrofitService.createService(ApiService.class);

}
