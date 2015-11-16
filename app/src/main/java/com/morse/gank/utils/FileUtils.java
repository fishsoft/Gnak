package com.morse.gank.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 文件工具类
 * 作者：Morse on 2015/11/16 15:14
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class FileUtils {

    /**
     * 获取缓存目录
     *
     * @param context
     * @return
     */
    public static String getExternalCacheDir(Context context) {
        String path = null;
        if (getSdAvailable()) {
            File file = context.getExternalCacheDir();
            if (file != null) {
                path = file.getAbsolutePath();
            } else {
                path = context.getCacheDir().getAbsolutePath();
            }
        } else {
            path = context.getCacheDir().getAbsolutePath();
        }
        return path;
    }

    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean getSdAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
