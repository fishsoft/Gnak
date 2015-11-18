package com.morse.gank.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.morse.gank.R;
import com.morse.gank.beans.Bean;
import com.morse.gank.db.DbManager;
import com.morse.gank.inter.HttpRequestListener;
import com.morse.gank.utils.ConfigUtils;
import com.morse.gank.utils.ToastUtils;
import com.morse.gank.views.ProgressWebView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Morse on 2015/11/9 14:23
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class WebActivity extends AppCompatActivity {

    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.webView)
    ProgressWebView mWebView;

    private String mUrl;
    private DbManager mManager;
    private File mFile = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mManager = new DbManager(this);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mWebView.loadUrl(mUrl);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 解决页面销毁，但是视频还在播放问题
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (null != mWebView)
            mWebView.onResume();
    }

    /**
     * 解决页面销毁，但是视频还在播放问题
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (null != mWebView)
            mWebView.onPause();
    }

    /**
     * 返回键监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((null != mWebView && keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (mUrl.equals(mWebView.getUrl()))
                    finish();
                else
                    mWebView.goBack();
            }
            break;
            case R.id.collect_program:
                collect();
                break;
            case R.id.share_program:
                share();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 分享--可分享图片和文字
     */
    private void share() {

        intent = new Intent(Intent.ACTION_SEND);

        if (!TextUtils.isEmpty(mUrl) && !mUrl.endsWith(".jpg")) {
            intent.setType("text/plain"); // 纯文本
            intent.putExtra(Intent.EXTRA_TEXT, getCurrentBean().getDesc() + "\r\n" + mUrl);
            intent.putExtra(Intent.EXTRA_SUBJECT, "干货集中营大放送咯！！！！！！");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent, "干货集中营大放送咯！！！！！！"));
        } else if (mUrl.endsWith(".jpg")) {

            new ImagAsyn(new HttpRequestListener() {
                @Override
                public void onSuccess(Object o) {
                    mFile = (File) o;
                    if (mFile != null && mFile.exists() && mFile.isFile()) {
                        intent.setType("image/jpg");
                        Uri u = Uri.fromFile(mFile);
                        intent.putExtra(Intent.EXTRA_STREAM, u);
                        intent.putExtra(Intent.EXTRA_SUBJECT, "干货集中营大放送咯！！！！！！");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Intent.createChooser(intent, "干货集中营大放送咯！！！！！！"));
                    }
                }

                @Override
                public void onFailure(String s) {

                }
            }).execute();
        }
    }

    /**
     * 收藏
     */
    private void collect() {
        Bean bean = getCurrentBean();
        if (null != bean && null == mManager.queryByUrl(mUrl, ConfigUtils.DATABASENAME_COLLECT)) {
            mManager.insert(bean, ConfigUtils.DATABASENAME_COLLECT);
            ToastUtils.show(WebActivity.this, "收藏成功");
        } else
            ToastUtils.show(WebActivity.this, "文章已经收藏了");
    }

    private File getFileFromBitmap() {
        Bitmap bitmap = null;
        try {
            bitmap = (Bitmap) Picasso.with(this).load(mUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = mUrl.substring(mUrl.lastIndexOf("/"));
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

    private Bean getCurrentBean() {
        return mManager.queryByUrl(mUrl, ConfigUtils.DATABASENAME_GANK);
    }

    class ImagAsyn extends AsyncTask<Void, File, File> {

        private HttpRequestListener mListener;

        public ImagAsyn(HttpRequestListener listener) {
            mListener = listener;
        }

        @Override
        protected File doInBackground(Void... params) {
            return getFileFromBitmap();
        }

        @Override
        protected void onPostExecute(File file) {
            mListener.onSuccess(file);
            super.onPostExecute(file);
        }
    }
}
