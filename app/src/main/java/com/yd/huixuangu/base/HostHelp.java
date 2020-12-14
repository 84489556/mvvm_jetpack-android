package com.yd.huixuangu.base;

import com.yd.httpmudule.BuildConfig;

/**
 * 所有的host
 */
public class HostHelp {


    /**
     * 慧选股 接口根地址
     * let hxgURLRelease = 'https://ydhxg-prod-api.yd.com.cn';
     * let hxgURLDebug = 'http://ydhxg-api.yd.com.cn:8443';
     * export const urlHXG = IsRelease ? hxgURLRelease : hxgURLDebug;
     */
    public static String HXG_HOST = "";
    //test
    public static  String MAIN_HOST = "http://www.xxxx.com";
    static {
        if (BuildConfig.DEBUG) {
            HXG_HOST= "http://ydhxg-api.yd.com.cn:8443";
             MAIN_HOST = "http://www.xxxxDebug.com";
        } else {
            HXG_HOST = "https://ydhxg-prod-api.yd.com.cn";
            MAIN_HOST = "http://www.xxxxRelesase.com";
        }
    }




    static String getHxgHost() {
        return HXG_HOST;
    }


    public static String getMainHost() {
        return MAIN_HOST;
    }



}
