package com.yd.ydyun.module;

public class HeartBean {

    String cmd = "13";
    String hbbyte ="-127";


    public static HeartBean heartBean = null;

    public static HeartBean getInstance() {
        if (heartBean == null){
            heartBean = new HeartBean();
        }
        return heartBean;
    }
    @Override
    public String toString() {
        return "HeartBean{" +
                "cmd='" + cmd + '\'' +
                ", hbbyte='" + hbbyte + '\'' +
                '}';
    }
}
