package com.reactlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class CacheUtils {
    static SharedPreferences sharedPreferences;
    public static void init(Context context){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("videoData", Context.MODE_PRIVATE);
    }

    public static Map getData(Context context){
        init(context);
        Map<String,Object> res = new HashMap<>();
        res.put("position",sharedPreferences.getInt("position",0));
        res.put("url",""+sharedPreferences.getString("url",""));
        return res;
    }

    //使用 commit() 方法保存会给出反应（保存成功或失败）
    public static boolean setData( Context context,int curPosition,String url){
        init(context);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString("url",url);
        e.putInt("position",curPosition);
        Boolean bool = e.commit();
        return bool;
    }
}
