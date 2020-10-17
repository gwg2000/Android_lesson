package com.example.personal_management;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBoperate {
    private static MsgDB mDbHelper=null;

    private static DBoperate instance=new DBoperate();
    public static DBoperate getWordsDB(){
        return DBoperate.instance;
    }
    private DBoperate() {
        if (mDbHelper == null) {
            mDbHelper = new MsgDB(MsgApplication.getContext());
        }
    }

    public ArrayList<Map<String, String>> getAllMsg() {
        ArrayList<Map<String, String>> items = new ArrayList<Map<String, String>>();
        String selectSql = "select * from msg";
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectSql, null);
        while (cursor.moveToNext()) {
                Map<String, String> item = new HashMap<String, String>();
                item.put("Time", cursor.getString(0).toString());
                item.put("Name", cursor.getString(1).toString());
                item.put("Num", cursor.getString(2).toString());
                item.put("Classs", cursor.getString(3).toString());
            item.put("Temp", cursor.getString(4).toString());
            item.put("Radio", cursor.getString(5).toString());
            item.put("Address", cursor.getString(6).toString());

                items.add(item);

        }
        db.close();
        return items;
    }

    public void Insert(String time,String name,String num,String classs,String temp,String radioselect,String address) {

        String insertSql ="insert into msg(time,name,num,classs,temps,radioselect,address) values (?,?,?,?,?,?,?)";
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(insertSql,new String[]{time,name,num,classs,temp,radioselect,address});
        db.close();

    }

    public ArrayList<String> Findtime()
    {
        ArrayList<String> array=new ArrayList<String>();
        String selectSql = "select time from msg";
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectSql, null);
        while(cursor.moveToNext())
        {
            array.add(cursor.getString(0).toString());
        }
        db.close();
        return array;
    }

    public ArrayList<Map<String, String>> FindALLRing()
    {
        ArrayList<Map<String, String>> items = new ArrayList<Map<String, String>>();
        String selectSql = "select * from ring";
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectSql, null);
        while (cursor.moveToNext()) {
            Map<String, String> item = new HashMap<String, String>();
            item.put("ID", cursor.getString(0).toString());
            item.put("TIME", cursor.getString(1).toString());
            item.put("INFO", cursor.getString(2).toString());
            items.add(item);
        }
        db.close();
        return items;
    }

    public void InsertRing(String id,String time,String inf)
    {
        String insertSql ="insert into ring(id,time,info) values (?,?,?)";
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(insertSql,new String[]{id,time,inf});
        db.close();
    }

    public void DeleteRing(String id)
    {
        String deleteSql ="delete from ring where id=?";
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(deleteSql,new String[]{id});
        db.close();
    }

    public int gain_id()
    {
        String selectSql ="select id from ring";
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectSql, null);

        if(cursor.getCount()==0)
            return 0;
        else{
            cursor.moveToLast();
            String id=cursor.getString(0);
            return Integer.parseInt(id);
        }
    }

    public void DeleteAll()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String deletestr="delete  from msg";
        db.execSQL(deletestr);

    }
}
