package com.yd.huixuangu.base;

import java.util.HashMap;

public class HostManage {

    private static  HostManage instance = null;
    private static   HashMap<String ,String> hostMap = new HashMap<>();
    private static   HashMap<String ,String> ydyMap = new HashMap<>();



    static {
        hostMap.put(HostKey.HXG, HostHelp.HXG_HOST);
    }

    public static HashMap<String, String> getHostMap() {
        return hostMap;
    }

    public  static class HostKey{
         static final String HXG = "HXG";
    }
}
