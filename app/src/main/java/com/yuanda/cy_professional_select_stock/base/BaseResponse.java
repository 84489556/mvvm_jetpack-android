package com.yuanda.cy_professional_select_stock.base;

public class BaseResponse {
    private String msg;
    private boolean state;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
