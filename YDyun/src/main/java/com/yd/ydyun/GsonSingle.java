package com.yd.ydyun;

import com.google.gson.Gson;

public class GsonSingle {
    private static Gson gson = new Gson();

    public static Gson getInstance() {
        return gson;
    }
}
