package com.morse.gank.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.morse.gank.R;
import com.morse.gank.utils.NetUtils;

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
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (NetUtils.isNetWork(WebActivity.this)) {
            setContentView(R.layout.activity_web);
            ButterKnife.bind(this);
            initView();
        } else
            setContentView(R.layout.no_net);
    }

    private void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView.loadUrl(url);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initWebSetting();
    }

    private void initWebSetting() {
        WebSettings settings = mWebView.getSettings();
        //加快渲染，提高渲染优先级
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //把图片渲染放到最后
        settings.setBlockNetworkImage(true);
        //允许加载javascript插件
        settings.setJavaScriptEnabled(true);
        //设置缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        // 开启DOM storage API 功能
//        mWebView.getSettings().setDomStorageEnabled(true);
//        // 开启database storage API功能
//        mWebView.getSettings().setDatabaseEnabled(true);
//        String cacheDirPath = getFilesDir().getAbsolutePath()
//                + APP_CACHE_DIRNAME;
//        Log.i("cachePath", cacheDirPath);
//        // 设置数据库缓存路径
//        mWebView.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
//        // 设置Application caches缓存目录
//        mWebView.getSettings().setAppCachePath(cacheDirPath);
//        // 开启Application Cache功能
//        mWebView.getSettings().setAppCacheEnabled(true);

        //调整图片适应屏幕
        settings.setUseWideViewPort(true);
        //界面缩小到屏幕大小
        settings.setLoadWithOverviewMode(true);
        //支持手动缩放
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        //支持web插件
        settings.setPluginState(WebSettings.PluginState.ON);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //如果view里面有连接，可以加载连接
                if (!TextUtils.isEmpty(url) && NetUtils.isNetWork(WebActivity.this))
                    view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.getSettings().setBlockNetworkImage(false);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
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
            case android.R.id.home:
                mWebView.goBack();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
