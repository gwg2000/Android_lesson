package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String age=intent.getStringExtra("age");
        Toast.makeText(this,"姓名:"+name+"年龄"+age,Toast.LENGTH_LONG).show();
    }

    public void onClick_return(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("name","zhangsan");
        intent.putExtra("age","200");
        startActivity(intent);
    }
}