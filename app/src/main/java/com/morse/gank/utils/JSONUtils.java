package com.morse.gank.utils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * 作者：Morse on 2015/11/9 11:42
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class JSONUtils {

    public static <T> T parseObject(String s, Class<T> t) {
        try {
            return new Gson().fromJson(s, t);
        } catch (Exception e) {
            Log.i("morse", "JSONUtils->parseObject:json解析失败");
        }
        return null;
    }

}