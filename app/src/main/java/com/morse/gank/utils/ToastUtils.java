package com.morse.gank.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：Morse on 2015/11/16 15:28
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ToastUtils {

    public static void show(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
}
