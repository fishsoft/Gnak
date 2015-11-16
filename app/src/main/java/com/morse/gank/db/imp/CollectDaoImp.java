package com.morse.gank.db.imp;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.morse.gank.beans.Bean;
import com.morse.gank.db.CollectDao;
import com.morse.gank.utils.CacheUtils;
import com.morse.gank.utils.ToastUtils;

import java.util.List;

/**
 * 作者：Morse on 2015/11/16 15:08
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class CollectDaoImp implements CollectDao {

    private DbUtils mDbUtils;
    private Context mContext;

    public CollectDaoImp(Context context) {
        mContext = context;
        mDbUtils = DbUtils.create(context, CacheUtils.getCollectDbPath(context), "collect");
    }

    /**
     * 插入
     * @param bean
     */
    @Override
    public void insert(Bean bean) {
        try {
            Bean findBean = mDbUtils.findFirst(Selector.from(Bean.class).where("desc", "=", bean.getDesc()));
            if (null == findBean) {
                mDbUtils.save(bean);
            } else {
                mDbUtils.update(findBean, WhereBuilder.b("desc", "=", bean.getDesc()));
            }
        } catch (Exception e) {
            ToastUtils.show(mContext, "收藏失败");
        }
    }

    /**
     * 删除
     * @param bean
     */
    @Override
    public void delete(Bean bean) {
        try {
            mDbUtils.delete(bean);
        } catch (Exception e) {
            ToastUtils.show(mContext, "删除失败");
        }
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Bean> query() {
        try {
            return mDbUtils.findAll(Selector.from(Bean.class));
        } catch (Exception e) {
            ToastUtils.show(mContext, "查询数据失败");
        }
        return null;
    }

    /**
     * 根据类型查询
     * @param type
     * @return
     */
    @Override
    public List<Bean> query(String type) {
        try {
            return mDbUtils.findAll(Selector.from(Bean.class).where(WhereBuilder.b("type", "=", type)));
        } catch (Exception e) {
            ToastUtils.show(mContext, "查询数据失败");
        }
        return null;
    }
}
