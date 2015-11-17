package com.morse.gank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.morse.gank.R;
import com.morse.gank.beans.Bean;
import com.morse.gank.db.DbManager;
import com.morse.gank.utils.ConfigUtils;
import com.morse.gank.utils.ToastUtils;
import com.morse.gank.views.ProgressWebView;

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
     * 分享
     */
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getCurrentBean().getDesc() + "：" + "\n" + mUrl);
        startActivity(Intent.createChooser(intent, "干货集中营大放送咯！！！！！！"));
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

    private Bean getCurrentBean(){
        return mManager.queryByUrl(mUrl, ConfigUtils.DATABASENAME_GANK);
    }
}
