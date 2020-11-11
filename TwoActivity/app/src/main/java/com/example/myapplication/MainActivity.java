package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String age=intent.getStringExtra("age");
        if(name!=null&&age!=null)
        Toast.makeText(this,"姓名:"+name+"年龄"+age,Toast.LENGTH_LONG).show();}


    public void onClick_go(View view) {
        Intent intent=new Intent(this,AnotherActivity.class);
        intent.putExtra("name","gaowengang");
        intent.putExtra("age","20");
        startActivity(intent);
    }

    public void onClick_implicit(View view) {
        Intent intent=new Intent();
        intent.setAction("implict");
        startActivity(intent);
    }
}