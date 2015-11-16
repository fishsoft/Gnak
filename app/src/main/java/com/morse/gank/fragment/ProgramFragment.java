package com.morse.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.morse.gank.R;
import com.morse.gank.adapter.ProgramAdapter;
import com.morse.gank.beans.Bean;
import com.morse.gank.beans.ProgramBean;
import com.morse.gank.utils.JSONUtils;
import com.morse.gank.utils.NetUtils;
import com.morse.gank.utils.UrlUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 详细信息显示类
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
//    private CollectDaoImp mCollectDaoImp;

    public static ProgramFragment newInstance() {
        ProgramFragment fragment = new ProgramFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString("title");
//        mCollectDaoImp=new CollectDaoImp(getActivity(),mTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (NetUtils.isNetWork(getActivity())) {
            mView = inflater.inflate(R.layout.fragment_program, null);
            ButterKnife.bind(this, mView);
            initView();
        } else {
            mView = inflater.inflate(R.layout.no_net, null);
        }
        return mView;
    }

    private void initView() {
        mSwipe.setOnRefreshListener(this);
        mBeans = new ArrayList<Bean>();
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mAdapter = new ProgramAdapter(getActivity(), mBeans, mTitle);
        mRecyler.setLayoutManager(layoutManager);
        mRecyler.setAdapter(mAdapter);
        //pull to refresh data from net
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
                mLastItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(index);
    }

    /**
     * get data from net
     */
    private void initData(int i) {

        if (!NetUtils.isNetWork(getActivity())) {
            getLocalData();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(UrlUtils.PRE_URL + mTitle + UrlUtils.SUF_URL + index, new Response.Listener
                () {
            @Override
            public void onResponse(Object response) {
                ProgramBean programBean = JSONUtils.parseObject(response.toString(), ProgramBean.class);
                if (null != programBean && !programBean.isError()) {
                    ArrayList<Bean> beans = (ArrayList<Bean>) programBean.getBeans();
//                    mCollectDaoImp.insert(beans);
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
                getLocalData();
                refreshFinish();
            }
        });
        //set timeout for http
        request.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 1, 1.0f));
        queue.add(request);
    }

    /**
     * get data from local database
     */
    private void getLocalData() {
//        List<Bean> beans=mCollectDaoImp.query(mTitle);
//        if (null!=beans){
//            mBeans.addAll(beans);
//        }else {
            Snackbar.make(mView, "网络异常", Snackbar.LENGTH_LONG).show();
//        }
        refreshFinish();
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
