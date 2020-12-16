package com.yd.huixuangu.base.module;

public class RequestModule {
    private int cmd = 7;
    private String path = "";

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public void setPath(String path) {
        this.path = "yuanda/node".concat(path);
    }
}
