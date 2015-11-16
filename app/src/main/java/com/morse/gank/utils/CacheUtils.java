package com.morse.gank.utils;

import android.content.Context;

import java.io.File;

/**
 * 作者：Morse on 2015/11/16 15:13
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class CacheUtils {

    /**
     * 获取外部缓存目录
     *
     * @param context
     * @return
     */
    private static String getExternalCachePath(Context context) {
        return FileUtils.getExternalCacheDir(context);
    }

    /**
     * 获取收藏数据库目录
     *
     * @param context
     * @return
     */
    public static String getCollectDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "BlogCollect";
    }

    /**
     * 获取IOS数据库目录
     *
     * @param context
     * @return
     */
    public static String getIOSDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "Blogger";
    }

    /**
     * 获取APP数据库目录
     *
     * @param context
     * @return
     */
    public static String getAppDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "BlogList";
    }

    /**
     * 获取Android数据库目录
     *
     * @param context
     * @return
     */
    public static String getAndroidDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "BlogContent";
    }

    /**
     * 获取休息视频数据库目录
     *
     * @param context
     * @return
     */
    public static String getVoideDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "CommentList";
    }

    /**
     * 获取拓展资源数据库目录
     *
     * @param context
     * @return
     */
    public static String getResoureDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "ChannelBlogger";
    }
    /**
     * 获取福利数据库目录
     *
     * @param context
     * @return
     */
    public static String getWelfareDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "ChannelBlogger";
    }
    /**
     * 获取瞎推荐数据库目录
     *
     * @param context
     * @return
     */
    public static String getRecommendDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "ChannelBlogger";
    }
    /**
     * 获取前端数据库目录
     *
     * @param context
     * @return
     */
    public static String getFrontDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "ChannelBlogger";
    }

    /**
     * 获取WebView缓存目录
     *
     * @return
     */
    public static String getWebCachePath(Context context) {
        return getExternalCachePath(context) + File.separator + "App" + File.separator + "Cache";
    }

    /**
     * 获取WebView数据库目录
     *
     * @param context
     * @return
     */
    public static String getAppDatabasePath(Context context) {
        return getExternalCachePath(context) + File.separator + "App" + File.separator + "DataBase";
    }
}
