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

    public DbManager(Context context) {
        helper = new DbHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * add persons
     *
     * @param beans
     */
    public void insert(ArrayList<Bean> beans) {
        db.beginTransaction();  //开始事务
        try {
            for (Bean bean : beans) {
                int flag = bolean2int(bean);
                db.execSQL("INSERT INTO gank VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{bean.getWho(), bean.getPublishedAt(),
                        bean.getDesc(), bean.getType(), bean.getUrl(), flag, bean.getObjectId(), bean
                        .getCreatedAt(), bean.getUpdatedAt()});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * 将boolean型数据转成int型
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

    public void insert(Bean bean){
        db.beginTransaction();

    }

    /**
     * delete old person
     *
     * @param bean
     */
    public void delete(Bean bean) {
        String sql = "delete from gank where desc=" + bean.getDesc();
        db.execSQL(sql);
    }

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public ArrayList<Bean> query() {
        ArrayList<Bean> beans = new ArrayList<Bean>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Bean bean = new Bean();
            int2boolean(c, bean);
            bean.setWho(c.getString(c.getColumnIndex("who")));
            bean.setPublishedAt(c.getString(c.getColumnIndex("name")));
            bean.setDesc(c.getString(c.getColumnIndex("desc")));
            bean.setType(c.getString(c.getColumnIndex("info")));
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
     * 将int型数据转换成boolean
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
     * query all persons, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
