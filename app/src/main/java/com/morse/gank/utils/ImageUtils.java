package com.morse.gank.utils;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 图片处理类
 * 作者：Morse on 2015/11/9 16:38
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ImageUtils {

    /**
     * 图片显示
     * @param draweeView 图片对象
     * @param url 图片下载地址
     */
    public static void display(SimpleDraweeView draweeView,String url){
        draweeView.setImageURI(Uri.parse(url),draweeView);
        //根据宽和高比例显示图片
        draweeView.setAspectRatio(1.33f);
    }
}
