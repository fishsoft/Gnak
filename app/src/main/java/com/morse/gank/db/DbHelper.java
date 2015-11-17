package com.morse.gank.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：Morse on 2015/11/16 17:40
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class DbHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "gan.db";
    private static int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS gank" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, who VARCHAR, publishedAt TEXT, desc TEXT, type VARCHAR, url " +
                "TEXT, used INTEGER, objectId TEXT, createdAt TEXT, updatedAt TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS collect" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, who VARCHAR, publishedAt TEXT, desc TEXT, type VARCHAR, url " +
                "TEXT, used INTEGER, objectId TEXT, createdAt TEXT, updatedAt TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE gank ADD COLUMN other STRING");
        db.execSQL("ALTER TABLE collect ADD COLUMN other STRING");
    }
}
