package com.shgbit.android.demo.utils;

import com.google.gson.Gson;

public class JsonUtil {
    private static final String TAG = "JsonUtil";
    public static <T> T parseJsonToClass (String json, Class<T> classOfT) {
        try {
            return new Gson().fromJson(json, classOfT);
        }catch (Throwable e) {
        }
        return null;
    }

    public static String toJson (Object object) {
        try {
            return new Gson().toJson(object);
        }catch (Throwable e) {

        }
        return "";
    }
}
