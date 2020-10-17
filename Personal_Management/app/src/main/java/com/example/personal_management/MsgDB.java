package com.example.personal_management;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MsgDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "Msgdb";//数据库名字
    private final static int DATABASE_VERSION = 2;//数据库版本

    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS " + "msg";
    private final static String SQL_CREATE_DATABASE = "CREATE TABLE  msg (" +
            "time varchar(30) PRIMARY KEY NOT NULL," +
            "name varchar(30)," +
            "num varchar(20)," +
            "classs varchar(30)," +
            "temps varchar(20)," +
            "radioselect varchar(5)," +
            "address text)";
    private final static String SQL_CREAT_Ring="CREATE TABLE  ring (" +
            "id text," +
            "time varchar(30)," +
            "info text)";


    public  MsgDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DATABASE);
        db.execSQL(SQL_CREAT_Ring);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }
}
