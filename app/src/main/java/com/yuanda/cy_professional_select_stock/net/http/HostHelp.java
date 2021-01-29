package com.yuanda.cy_professional_select_stock.net.http;

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

    /**
     * 选股跟地址
     * IsRelease ? https://ydhxg-prod-admin2.yd.com.cn/api : http://ydhxg-api.yd.com.cn:9091
     */
    public static String XG_HOST = "";

    static {
        if (BuildConfig.DEBUG) {
            HXG_HOST = "http://ydhxg-api.yd.com.cn:8443";
            YDY_HOST = "https://csyun-slb.yd.com.cn";
            XG_HOST = "http://ydhxg-api.yd.com.cn:9091";
        } else {
            YDY_HOST = "https://yun.ydtg.com.cn";
            HXG_HOST = "https://ydhxg-prod-api.yd.com.cn";
            XG_HOST = "https://ydhxg-prod-admin2.yd.com.cn/api";
        }
    }



}
