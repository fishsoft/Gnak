package com.morse.gank.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.morse.gank.utils.NetUtils;

/**
 * 作者：Morse on 2015/11/13 17:31
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ProgressWebView extends WebView {
    private ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 3, 0, 0));
        addView(progressbar);
        initWebView(context);
        setWebChromeClient(new WebChromeClient());
    }

    private void initWebView(final Context context) {
        WebSettings settings = getSettings();
        //加快渲染，提高渲染优先级
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //把图片渲染放到最后
        settings.setBlockNetworkImage(true);
        //允许加载javascript插件
        settings.setJavaScriptEnabled(true);
        //设置缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
//        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
//        Log.i("cachePath", cacheDirPath);
        // 设置数据库缓存路径
//        settings.setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
//        settings.setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        settings.setAppCacheEnabled(true);

        //调整图片适应屏幕
        settings.setUseWideViewPort(true);
        //界面缩小到屏幕大小
        settings.setLoadWithOverviewMode(true);
        //支持手动缩放
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        //支持web插件
        settings.setPluginState(WebSettings.PluginState.ON);

        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //如果view里面有连接，可以加载连接
                if (!TextUtils.isEmpty(url) && NetUtils.isNetWork(context))
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
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
