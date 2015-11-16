package com.morse.gank.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络判断类
 * 作者：Morse on 2015/11/11 14:23
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class NetUtils {

    /**
     * 判断网络是否能正常上网
     *
     * @param context 上下文
     * @return boolean
     */
    public static boolean isNetWork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == connectivityManager)
            return false;
        else {
            NetworkInfo infos = connectivityManager.getActiveNetworkInfo();
            try {
                if (infos.getState() == NetworkInfo.State.CONNECTED)
                    return true;
            } catch (NullPointerException e) {
            }
        }
        return false;
    }
}
