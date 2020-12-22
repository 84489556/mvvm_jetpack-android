package com.yd.huixuangu.net.http;

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


    /**
     * 源达云节点
     *ip: IsRelease ? 'yun.ydtg.com.cn': "csyun-slb.yd.com.cn",
     */
    public static String YDY_HOST = "";

    static {
        if (BuildConfig.DEBUG) {
            HXG_HOST = "http://ydhxg-api.yd.com.cn:8443";
            YDY_HOST = "https://yun.ydtg.com.cn";
        } else {
            YDY_HOST = "https://yun.ydtg.com.cn";
            HXG_HOST = "https://ydhxg-prod-api.yd.com.cn";
        }
    }



}
