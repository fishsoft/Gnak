package com.morse.gank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morse.gank.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于页面
 * 作者：Morse on 2015/11/11 11:44
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class AboutActivity extends BaseActivity {

    @Bind(R.id.about_toolbar)
    Toolbar mAboutToolbar;
    @Bind(R.id.about_image)
    ImageView mAboutImage;
    @Bind(R.id.about_title)
    TextView mAboutTitle;
    @Bind(R.id.version)
    TextView mVersion;
    @Bind(R.id.about_header)
    LinearLayout mAboutHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        setSupportActionBar(mAboutToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mAboutTitle.setText("干货集中营");
    }

    @OnClick(R.id.download)
    void onClick() {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", "https://github.com/fishsoft/Gnak.git");
        startActivity(intent);
        finish();
    }

}
