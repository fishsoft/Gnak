package com.morse.gank.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.morse.gank.fragment.ProgramFragment;

import java.util.ArrayList;

/**
 * 作者：Morse on 2015/11/9 10:18
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GankAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> mList;

    public GankAdapter(FragmentManager fm, ArrayList<String> titles) {
        super(fm);
        mList=titles;
    }

    @Override
    public Fragment getItem(int position) {
        ProgramFragment fragment = ProgramFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("title", mList.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
