package com.morse.gank.utils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Json解析类
 * 作者：Morse on 2015/11/9 11:42
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class JSONUtils {

    /**
     * 将json字符串转换成JavaBean对象
     * @param s 字符串
     * @param t JavaBean类
     * @param <T> 被转化的对象
     * @return 对象
     */
    public static <T> T parseObject(String s, Class<T> t) {
        try {
            return new Gson().fromJson(s, t);
        } catch (Exception e) {
            Log.i("morse", "JSONUtils->parseObject:json解析失败");
        }
        return null;
    }

}