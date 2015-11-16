package com.morse.gank.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.morse.gank.R;
import com.morse.gank.adapter.ProgramAdapter;
import com.morse.gank.beans.Bean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Morse on 2015/11/16 14:16
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class CollectActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.recyler)
    RecyclerView mRecyler;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    private ProgramAdapter mAdapter;
    private ArrayList<Bean> mBeans;
    private LinearLayoutManager layoutManager;
    private int mLastItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        mSwipe.setOnRefreshListener(this);
        mBeans = new ArrayList<Bean>();
        setSupportActionBar(mToolbar);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("收藏");
        bar.setDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyler.setLayoutManager(layoutManager);

        mAdapter = new ProgramAdapter(this, mBeans, "collect");
        mRecyler.setAdapter(mAdapter);

        mRecyler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //查数据库
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onRefresh() {
        //查数据库
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSwipe.setRefreshing(false);
    }
}
