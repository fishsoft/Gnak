package com.morse.gank.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.morse.gank.beans.Bean;

import java.util.ArrayList;

/**
 * 作者：Morse on 2015/11/16 17:59
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class DbManager {
    private DbHelper helper;
    private SQLiteDatabase db;
    private Context mContext;

    public DbManager(Context context) {
        mContext = context;
        helper = new DbHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * add a data
     *
     * @param beans
     */
    public void insert(ArrayList<Bean> beans, String dataBaseName) {
        db.beginTransaction();  //开始事务
        try {
            for (Bean bean : beans) {
                if (null == queryByUrl(bean.getUrl(), dataBaseName)) {
                    int flag = bolean2int(bean);
                    db.execSQL("INSERT INTO " + dataBaseName + " VALUES(null,?, ?, ?, ?, ?, ?,?, ? ,?)", new Object[]{bean.getWho(),
                            bean.getPublishedAt(), bean.getDesc(), bean.getType(), bean.getUrl(),
                            flag, bean.getObjectId(), bean.getCreatedAt(), bean.getUpdatedAt()});
                }
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * boolean to int data
     *
     * @param bean
     * @return
     */
    private int bolean2int(Bean bean) {
        int flag = 0;
        if (bean.getUsed())
            flag = 1;
        else
            flag = 0;
        return flag;
    }

    /**
     * insert a data
     *
     * @param bean
     */
    public void insert(Bean bean, String dataBaseName) {
        db.beginTransaction();
        try {
            if (null == queryByUrl(bean.getUrl(), dataBaseName)) {
                int flag = bolean2int(bean);
                db.execSQL("INSERT INTO " + dataBaseName + " VALUES(null,?, ?, ?, ?, ?, ?,?, ? ,?)", new Object[]{bean.getWho(),
                        bean.getPublishedAt(), bean.getDesc(), bean.getType(), bean.getUrl(),
                        flag, bean.getObjectId(), bean.getCreatedAt(), bean.getUpdatedAt()});
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * delete
     *
     * @param bean
     */
    public void delete(Bean bean, String dataBaseName) {
        String sql = "delete from " + dataBaseName + " where desc=" + bean.getDesc();
        db.execSQL(sql);
    }

    /**
     * query all
     *
     * @return List<Person>
     */
    public ArrayList<Bean> query(String dataBaseName) {
        ArrayList<Bean> beans = new ArrayList<Bean>();
        Cursor c = db.rawQuery("SELECT * FROM " + dataBaseName, null);
        while (c.moveToNext()) {
            Bean bean = new Bean();
            int2boolean(c, bean);
            bean.setWho(c.getString(c.getColumnIndex("who")));
            bean.setPublishedAt(c.getString(c.getColumnIndex("publishedAt")));
            bean.setDesc(c.getString(c.getColumnIndex("desc")));
            bean.setType(c.getString(c.getColumnIndex("type")));
            bean.setUrl(c.getString(c.getColumnIndex("url")));
            bean.setObjectId(c.getString(c.getColumnIndex("objectId")));
            bean.setCreatedAt(c.getString(c.getColumnIndex("createdAt")));
            bean.setUpdatedAt(c.getString(c.getColumnIndex("updatedAt")));
            beans.add(bean);
        }
        c.close();
        return beans;
    }

    /**
     * query all
     *
     * @return List<Person>
     */
    public Bean queryByUrl(String url, String dataBaseName) {
        Cursor c = db.rawQuery("SELECT * FROM " + dataBaseName + " where url=?", new String[]{url});
        while (c.moveToNext()) {
            if (c.getString(c.getColumnIndex("url")).equals(url)) {
                Bean bean = new Bean();
                int2boolean(c, bean);
                bean.setWho(c.getString(c.getColumnIndex("who")));
                bean.setPublishedAt(c.getString(c.getColumnIndex("publishedAt")));
                bean.setDesc(c.getString(c.getColumnIndex("desc")));
                bean.setType(c.getString(c.getColumnIndex("type")));
                bean.setUrl(c.getString(c.getColumnIndex("url")));
                bean.setObjectId(c.getString(c.getColumnIndex("objectId")));
                bean.setCreatedAt(c.getString(c.getColumnIndex("createdAt")));
                bean.setUpdatedAt(c.getString(c.getColumnIndex("updatedAt")));
                c.close();
                return bean;
            }
        }
        return null;
    }

    /**
     * query by type
     *
     * @return List<Person>
     */
    public ArrayList<Bean> queryByType(String type, String dataBaseName) {
        ArrayList<Bean> beans = new ArrayList<Bean>();
        Cursor c = db.rawQuery("SELECT * FROM " + dataBaseName + " where type= ?", new String[]{type});
        while (c.moveToNext()) {
            Bean bean = new Bean();
            int2boolean(c, bean);
            bean.setWho(c.getString(c.getColumnIndex("who")));
            bean.setPublishedAt(c.getString(c.getColumnIndex("publishedAt")));
            bean.setDesc(c.getString(c.getColumnIndex("desc")));
            bean.setType(c.getString(c.getColumnIndex("type")));
            bean.setUrl(c.getString(c.getColumnIndex("url")));
            bean.setObjectId(c.getString(c.getColumnIndex("objectId")));
            bean.setCreatedAt(c.getString(c.getColumnIndex("createdAt")));
            bean.setUpdatedAt(c.getString(c.getColumnIndex("updatedAt")));
            beans.add(bean);
        }
        c.close();
        return beans;
    }

    /**
     * int to boolean
     *
     * @param c
     * @param bean
     */
    private void int2boolean(Cursor c, Bean bean) {
        if (c.getInt(c.getColumnIndex("used")) == 1)
            bean.setUsed(true);
        else
            bean.setUsed(false);
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
