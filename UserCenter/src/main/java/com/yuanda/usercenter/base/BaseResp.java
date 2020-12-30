package com.yuanda.usercenter.base;

import androidx.annotation.NonNull;

public class BaseResp {
    private String msg;
    private boolean state;
    private int code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("msg:").append(msg).append(",");
        sb.append("state: ").append(state).append(",");
        sb.append("code: ").append(code);
        return sb.toString();
    }
}
