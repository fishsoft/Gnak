package com.morse.gank.utils;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者：Morse on 2015/11/9 16:38
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ImageUtils {

    public static void display(SimpleDraweeView draweeView,String url){
        draweeView.setImageURI(Uri.parse(url),draweeView);
        draweeView.setAspectRatio(1.33f);
    }
}
