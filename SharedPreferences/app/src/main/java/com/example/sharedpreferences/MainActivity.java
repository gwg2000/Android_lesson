package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences=getSharedPreferences("name-num", MODE_PRIVATE);
        editor=preferences.edit();
    }

    public void onClickInput(View view)  {
        OutputStream out=null;

        try {
            FileOutputStream fileOutputStream=openFileOutput("text",MODE_PRIVATE);
            out=new BufferedOutputStream(fileOutputStream);
            String string="2018011310-高问纲";
            out.write(string.getBytes(StandardCharsets.UTF_8));
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickOut(View view) {
        InputStream in = null;
        try {
            FileInputStream fileInputStream = openFileInput("text");
            in = new BufferedInputStream(fileInputStream);
            int c;
            StringBuilder stringBuilder = new StringBuilder("");
            try {
                while ((c = in.read()) != -1) {
                    stringBuilder.append((char) c);
                }
                Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            } finally {
                if (in != null)
                    in.close();
            }
        }


        catch (Exception e){
            e.printStackTrace();
        }
    }
        }

