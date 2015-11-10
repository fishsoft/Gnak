package com.morse.gank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.morse.gank.R;
import com.morse.gank.activity.WebActivity;
import com.morse.gank.beans.Bean;
import com.morse.gank.utils.ImageUtils;

import java.util.ArrayList;

/**
 * 作者：Morse on 2015/11/9 11:08
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<Bean> mBeans;
    private String mTag;

    public ProgramAdapter(Context context, ArrayList<Bean> beans, String tag) {
        mContext = context;
        mBeans = beans;
        mTag = tag;
        mInflater = LayoutInflater.from(context);
        Fresco.initialize(context);
    }

    @Override
    public ProgramHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.program_item, null);
        ProgramHolder holder = new ProgramHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProgramHolder holder, int position) {
        if ("福利".equals(mTag)) {
            if (holder.mProgramTitle.getVisibility() != View.GONE) {
                holder.mProgramTitle.setVisibility(View.GONE);
            }
            if (holder.mImage.getVisibility() != View.VISIBLE) {
                holder.mImage.setVisibility(View.VISIBLE);
            }
            ImageUtils.display(holder.mImage, mBeans.get(position).getUrl());
        } else {
            if (holder.mProgramTitle.getVisibility() != View.VISIBLE) {
                holder.mProgramTitle.setVisibility(View.VISIBLE);
            }
            if (holder.mImage.getVisibility() != View.GONE) {
                holder.mImage.setVisibility(View.GONE);
            }
            holder.mProgramTitle.setText(mBeans.get(position).getDesc());
        }

        holder.mProgramAuthor.setText("作者:" + mBeans.get(position).getWho());
        holder.mPublishAt.setText("发布:" + mBeans.get(position).getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public class ProgramHolder extends RecyclerView.ViewHolder {

        public CardView mListItem;
        public SimpleDraweeView mImage;
        public TextView mProgramTitle;
        public TextView mProgramAuthor;
        public TextView mPublishAt;

        public ProgramHolder(final View itemView) {
            super(itemView);
            mListItem = (CardView) itemView.findViewById(R.id.list_item);
            mImage = (SimpleDraweeView) itemView.findViewById(R.id.image);
            mProgramTitle = (TextView) itemView.findViewById(R.id.program_title);
            mProgramAuthor = (TextView) itemView.findViewById(R.id.program_author);
            mPublishAt = (TextView) itemView.findViewById(R.id.publishedAt);
            mListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, WebActivity.class);
                    intent.putExtra("url",mBeans.get(getPosition()).getUrl());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
