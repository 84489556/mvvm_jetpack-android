package com.yd.ydyun.module;

public class WebSocketBaseResponse {
    private String command;
    private Data data;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        private int code;
        private int hbbyte;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setHbbyte(int hbbyte) {
            this.hbbyte = hbbyte;
        }

        public int getHbbyte() {
            return hbbyte;
        }

    }
}
