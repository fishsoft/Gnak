package com.morse.gank.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.morse.gank.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Morse on 2015/11/16 13:53
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ContactUsActivity extends BaseActivity {
    @Bind(R.id.about_toolbar)
    Toolbar mAboutToolbar;
    @Bind(R.id.about_image)
    ImageView mAboutImage;
    @Bind(R.id.about_title)
    TextView mAboutTitle;
    @Bind(R.id.version)
    TextView mVersion;
    @Bind(R.id.update)
    TextView mUpdate;
    @Bind(R.id.developer)
    TextView mDeveloper;
    @Bind(R.id.downtext)
    TextView mDowntext;
    @Bind(R.id.download)
    TextView mDownload;
    @Bind(R.id.app_author)
    TextView mAppAuthor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(mAboutToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("联系我们");
        mAboutTitle.setVisibility(View.INVISIBLE);
        mAboutImage.setImageResource(R.mipmap.ic_launcher);
        mUpdate.setText("邮箱");
        mVersion.setText("2450048085@qq.com");
        mDeveloper.setVisibility(View.GONE);
        mDowntext.setVisibility(View.GONE);
        mDownload.setVisibility(View.GONE);
        mAppAuthor.setVisibility(View.GONE);
    }
}
