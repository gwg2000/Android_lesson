package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "(1) onCreate()");

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Activity is resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onClickButton(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        final View viewDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog,null,false);
        builder.setTitle("自定义对话框")
                .setView(viewDialog)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText Id = viewDialog.findViewById(R.id.usename);
                        EditText Pwd = viewDialog.findViewById(R.id.pwd);
                        if(Id.getText().toString().equals("abc")&&Pwd.getText().toString().equals("123")){
                        Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();}
                        else {
                            Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
}