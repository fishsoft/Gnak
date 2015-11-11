package com.morse.gank.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.morse.gank.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Morse on 2015/11/9 14:23
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class WebActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView.loadUrl(url);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebSettings settings = mWebView.getSettings();
        //加快渲染，提高渲染优先级
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //把图片渲染放到最后
        settings.setBlockNetworkImage(true);
        //允许加载javascript插件
        settings.setJavaScriptEnabled(true);
        //设置缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
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
        mWebView.onResume();
    }

    /**
     * 解决页面销毁，但是视频还在播放问题
     */
    @Override
    protected void onPause() {
        super.onPause();
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
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
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
        switch (item.getItemId()){
            case android.R.id.home:
                mWebView.goBack();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
