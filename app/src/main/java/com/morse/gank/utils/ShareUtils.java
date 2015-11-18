package com.morse.gank.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import com.morse.gank.beans.Bean;
import com.morse.gank.inter.HttpRequestListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者：Morse on 2015/11/18 17:50
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ShareUtils {

    /**
     * 分享文本
     * @param context
     * @param bean
     */
    public static void share(Context context,Bean bean){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain"); // 纯文本
        intent.putExtra(Intent.EXTRA_TEXT, bean.getDesc() + "\r\n" + bean.getUrl());
        intent.putExtra(Intent.EXTRA_SUBJECT, "干货集中营大放送咯！！！！！！");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "干货集中营大放送咯！！！！！！"));
    }

    /**
     * 分享图片
     * @param context
     * @param bean
     */
    public void shareImg(final Context context,Bean bean){
        new ImagAsyn(context,bean.getUrl(),new HttpRequestListener() {
            @Override
            public void onSuccess(Object o) {
                File mFile = (File) o;
                if (mFile != null && mFile.exists() && mFile.isFile()) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/jpg");
                    Uri u = Uri.fromFile(mFile);
                    intent.putExtra(Intent.EXTRA_STREAM, u);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "干货集中营大放送咯！！！！！！");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(Intent.createChooser(intent, "干货集中营大放送咯！！！！！！"));
                }
            }

            @Override
            public void onFailure(String s) {
                ToastUtils.show(context,"图片不存在");
            }
        }).execute();
    }

    /**
     * 获取图片文件
     */
    class ImagAsyn extends AsyncTask<Void, File, File> {

        private HttpRequestListener mListener;
        private Context mContext;
        private String mUrl;

        public ImagAsyn(Context context,String url,HttpRequestListener listener) {
            mListener = listener;
            mContext=context;
            mUrl=url;
        }

        @Override
        protected File doInBackground(Void... params) {
            return getFileFromBitmap(mContext,mUrl);
        }

        @Override
        protected void onPostExecute(File file) {
            mListener.onSuccess(file);
            super.onPostExecute(file);
        }
    }

    /**
     * 获取图片文件
     * @return
     */
    private File getFileFromBitmap(Context context,String url) {
        Bitmap bitmap = null;
        try {
            //获取bitmap文件
            bitmap = (Bitmap) Picasso.with(context).load(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //保存文件
        File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = url.substring(url.lastIndexOf("/"));
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
