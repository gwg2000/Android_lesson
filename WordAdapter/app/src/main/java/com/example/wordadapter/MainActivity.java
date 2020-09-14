package com.example.wordadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final static String Word="word";
    private final static String Meaning="meaning";
    private final static String Sample="sample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String word[]={"Apple","Orange","Banana","Lemon"};
        String meaning[]={"hhhh","qqqqqq","ddddd","gggggg"};
        String sample[]={"zzzzz","cccccc","gggggg","iiiii"};
        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        for(int i=0;i<word.length;i++)
        {
            Map<String,Object> item=new HashMap<String, Object>();
            item.put(Word,word[i]);
            item.put(Meaning,meaning[i]);
            item.put(Sample,sample[i]);
            items.add(item);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,items,R.layout.item,
                new String[]{Word,Meaning,Sample},
                new int[]{R.id.word,R.id.meaning,R.id.sample});
        ListView list =findViewById(R.id.listview);
        list.setAdapter(adapter);
    }
}