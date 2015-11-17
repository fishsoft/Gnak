package com.morse.gank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.morse.gank.R;
import com.morse.gank.adapter.GankAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    public ArrayList<String> mTitles;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.navigation)
    NavigationView mNavigation;
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private GankAdapter mAdapter;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //设置actionBar
        setSupportActionBar(mToolbar);

        //设置导航开关
        if (mNavigation != null) {
            setupDrawerContent();
            setupActionBar();
        }

        mTitles = new ArrayList<String>();
        mTitles.add("iOS");
        mTitles.add("App");
        mTitles.add("Android");
        mTitles.add("休息视频");
        mTitles.add("拓展资源");
        mTitles.add("福利");
        mTitles.add("瞎推荐");
        mTitles.add("前端");
        mAdapter = new GankAdapter(getSupportFragmentManager(), mTitles);
        mViewpager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    /**
     * 设置actionBar开关
     */
    private void setupActionBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.toogle_open,
                R.string.toggle_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * 设置导航菜单事件
     */
    private void setupDrawerContent() {
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent=new Intent();
                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.collection:
                        intent.setClass(MainActivity.this,CollectActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.about:
                        intent.setClass(MainActivity.this,AboutActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.contant:
                        intent.setClass(MainActivity.this,ContactUsActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 监听物理返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
