package com.morse.gank.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.morse.gank.R;
import com.morse.gank.adapter.ProgramAdapter;
import com.morse.gank.beans.Bean;
import com.morse.gank.beans.ProgramBean;
import com.morse.gank.utils.HttpUtils;
import com.morse.gank.utils.JSONUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Morse on 2015/11/9 10:24
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ProgramFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.recyler)
    RecyclerView mRecyler;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    private View mView;
    private String mTitle;
    private int index = 1;
    private ArrayList<Bean> mBeans;
    private ProgramAdapter mAdapter;
    private int mLastItem;

    public static ProgramFragment newInstance() {
        ProgramFragment fragment = new ProgramFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_program, null);
        ButterKnife.bind(this, mView);
        mSwipe.setOnRefreshListener(this);
        mBeans = new ArrayList<Bean>();
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mAdapter = new ProgramAdapter(getActivity(), mBeans, mTitle);
        mRecyler.setLayoutManager(layoutManager);
        mRecyler.setAdapter(mAdapter);
        mRecyler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastItem + 1 == mAdapter.getItemCount()) {
                    initData(index++);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastItem=layoutManager.findLastVisibleItemPosition();
            }
        });
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(index);
    }

    private void initData(int i) {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(HttpUtils.PRE_URL + mTitle + HttpUtils.SUF_URL + index, new Response.Listener
                () {
            @Override
            public void onResponse(Object response) {
                ProgramBean programBean = JSONUtils.parseObject(response.toString(), ProgramBean.class);
                if (null != programBean && !programBean.isError()) {
                    ArrayList<Bean> beans = (ArrayList<Bean>) programBean.getBeans();
                    mBeans.addAll(beans);
                    if (null != mAdapter) {
                        mAdapter.notifyDataSetChanged();
                    }
                }
                refreshFinish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    private void refreshFinish() {
        if (null != mSwipe) {
            mSwipe.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        initData(1);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
