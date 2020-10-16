package com.example.personal_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

public class Msg_List extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__list);
        listView=findViewById(R.id.msg_list);
        DBoperate dpoperate=DBoperate.getWordsDB();
        ArrayList<Map<String, String>> items =dpoperate.getAllMsg();
        SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.msg_item,
                new String[]{"Time","Name","Num","Classs","Temp","Radio","Address"},
                new int[]{R.id.msg_time,R.id.msg_name,R.id.msg_num,R.id.msg_class,R.id.msg_temp,R.id.msg_radio,R.id.msg_address});
        listView.setAdapter(adapter);
    }
}