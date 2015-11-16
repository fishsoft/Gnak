package com.morse.gank.db.imp;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.morse.gank.beans.Bean;
import com.morse.gank.db.CollectDao;
import com.morse.gank.utils.CacheUtils;
import com.morse.gank.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作
 * 作者：Morse on 2015/11/16 15:08
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class CollectDaoImp implements CollectDao {

    private static DbUtils mDbUtils;
    private Context mContext;

    public CollectDaoImp(Context context, String type) {
        mContext = context;
        mDbUtils = DbUtils.create(context, CacheUtils.getCollectDbPath(context), type);
    }

    /**
     * 保存单个数据
     * save data to database
     *
     * @param bean
     */
    private static void save(Bean bean) {
        try {
            mDbUtils.save(bean);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * save data to database
     *
     * @param beans
     */
    public void saveAll(ArrayList<Bean> beans) {
        try {
            mDbUtils.saveAll(beans);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入
     *
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
        } catch (DbException e) {
            ToastUtils.show(mContext, "收藏失败");
            e.printStackTrace();
        }
    }

    /**
     * 插入
     *
     * @param beans
     */
    @Override
    public void insert(ArrayList<Bean> beans) {
        try {
            for (Bean bean : beans) {
                Bean findBean = mDbUtils.findFirst(Selector.from(Bean.class).where("desc", "=", bean.getDesc()));
                if (null == bean) {
                    mDbUtils.save(bean);
                } else {
                    mDbUtils.update(findBean, WhereBuilder.b("desc", "=", bean.getDesc()));
                }
            }
        } catch (DbException e) {
            ToastUtils.show(mContext, "收藏失败");
            e.printStackTrace();
        }
    }

    /**
     * 删除
     *
     * @param bean
     */
    @Override
    public void delete(Bean bean) {
        try {
            mDbUtils.delete(bean);
        } catch (DbException e) {
            ToastUtils.show(mContext, "删除失败");
            e.printStackTrace();
        }
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Bean> query() {
        try {
            return mDbUtils.findAll(Selector.from(Bean.class));
        } catch (DbException e) {
            ToastUtils.show(mContext, "查询数据失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询单个数据
     *
     * @return
     */
    private Bean query(Bean bean) {
        try {
            return mDbUtils.findFirst(Selector.from(Bean.class).where(WhereBuilder.b("desc", "=", bean.getDesc())));
        } catch (DbException e) {
            ToastUtils.show(mContext, "查询数据失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据类型查询
     *
     * @param type
     * @return
     */
    @Override
    public List<Bean> query(String type) {
        try {
            return mDbUtils.findAll(Selector.from(Bean.class).where(WhereBuilder.b("type", "=", type)));
        } catch (DbException e) {
            ToastUtils.show(mContext, "查询数据失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * save or update data
     *
     * @param beans
     */
    public void saveOrUpdate(ArrayList<Bean> beans) {
        for (Bean bean : beans) {
            Bean bean1 = query(bean);
            if (null == bean1) {
                save(bean1);
            }
        }
    }
}
