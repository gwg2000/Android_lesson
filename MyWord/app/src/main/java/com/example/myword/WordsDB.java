package com.example.myword;

import android.database.Cursor;

import com.example.myword.dummy.Words;

import java.util.ArrayList;
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
    public ArrayList<Map<String, String>> getAllWords() {    }
    //将游标转化为单词列表
    private ArrayList<Map<String, String>> ConvertCursor2WordList(Cursor cursor) { }

    public void Insert(String strWord, String strMeaning, String strSample) {}
    public void Delete(String strId) {}
    public void Update(String strId, String strWord, String strMeaning, String strSample) { }
    public ArrayList<Map<String, String>> Search(String strWordSearch) {}



}
