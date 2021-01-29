package com.yuanda.cy_professional_select_stock.net.http;

import java.util.HashMap;

public class HostManage {

    private static HashMap<String, String> hostMap = new HashMap<>();


    static {
        hostMap.put(HostKey.HXG, HostHelp.HXG_HOST);
        hostMap.put(HostKey.YDY, HostHelp.YDY_HOST);
        hostMap.put(HostKey.XG, HostHelp.XG_HOST);
    }

    public static HashMap<String, String> getHostMap() {
        return hostMap;
    }

    public static class HostKey {
        static final String HXG = "HXG";
        static final String YDY = "YDY";
        static final String XG = "XG";
    }
}
