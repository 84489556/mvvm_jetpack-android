package com.yd.huixuangu.base.module;

public class SocketModule {

        private int code;
        private String command;
        private Data data;
        private String msg;
        private String token;
        public void setCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        public void setCommand(String command) {
            this.command = command;
        }
        public String getCommand() {
            return command;
        }

        public void setData(Data data) {
            this.data = data;
        }
        public Data getData() {
            return data;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }

        public void setToken(String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }

    @Override
    public String toString() {
        return "SocketModule{" +
                "code=" + code +
                ", command='" + command + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
