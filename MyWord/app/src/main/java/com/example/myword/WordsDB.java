package com.example.myword;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myword.dummy.Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordsDB {
    private static WordsDBHelper mDbHelper;
    private static WordsDB instance=new WordsDB();
    public static WordsDB getWordsDB(){
        return WordsDB.instance;
    }
    private WordsDB() {
        if (mDbHelper == null) {
            mDbHelper = new WordsDBHelper(WordsApplication.getContext());
        }
    }
    public void close() {
        if (mDbHelper != null)
            mDbHelper.close();
    }

    //获得单个单词的全部信息
    public Words.WordDescription getSingleWord(String id) {   }
    //得到全部单词列表
    public ArrayList<Map<String, String>> getAllWords() {
        ArrayList<Map<String,String>> items=new ArrayList<Map<String,String>>();
        String selectSql = "select "+Words.Word._ID+" , "+Words.Word.COLUMN_NAME_WORD+" from "+Words.Word.TABLE_NAME;
        SQLiteDatabase db= SQLiteDatabase.openOrCreateDatabase("wordsdb",null);
        Cursor cursor=db.rawQuery(selectSql,null);
        while(cursor.moveToNext())
        {
            Map<String,String> item=new HashMap<String,String>();
            item.put(Words.Word._ID,cursor.getString(1));
            item.put(Words.Word.COLUMN_NAME_WORD,cursor.getString(2));
            items.add(item);
        }
        db.close();
        return items;
    }
    //将游标转化为单词列表
    private ArrayList<Map<String, String>> ConvertCursor2WordList(Cursor cursor) { }

    public void Insert(String strWord, String strMeaning, String strSample) {

    }
    public void Delete(String strId) {
        String deleteSql = "delete from "+Words.Word.TABLE_NAME+" where "+Words.Word._ID+"="+strId;
        SQLiteDatabase db= SQLiteDatabase.openOrCreateDatabase("wordsdb",null);
        db.execSQL(deleteSql);
        db.close();
    }
    public void Update(String strId, String strWord, String strMeaning, String strSample) {

    }
    public ArrayList<Map<String, String>> Search(String strWordSearch) {}



}
