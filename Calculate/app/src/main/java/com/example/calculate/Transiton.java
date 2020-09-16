package com.example.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Transiton extends AppCompatActivity implements View.OnClickListener {
    Button button_return1;
    Button button_begin;
    Spinner spinner_1;
    Spinner spinner_2;
    TextView textView_target;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transiton);
        button_return1=findViewById(R.id.return1);
        button_begin=findViewById(R.id.begin);
        spinner_1=findViewById(R.id.spinner1);
        spinner_2=findViewById(R.id.spinner2);
        textView_target=findViewById(R.id.target);
        editText=findViewById(R.id.edit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_2.setAdapter(adapter);

        button_begin.setOnClickListener(this);
        button_return1.setOnClickListener(this);

    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.return1:
                Intent intent=new Intent(Transiton.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.begin:
                String s1=spinner_1.getSelectedItem().toString();
                String s2=spinner_2.getSelectedItem().toString();
                String s3=editText.getText().toString();
                if(s3==null)
                    Toast.makeText(this,"请填入要转换的数字",Toast.LENGTH_LONG).show();
                else{
                    String  s= transform(s1,s2,s3);
                    textView_target.setText(s);
                }
                break;
        }
    }

    String transform(String s1,String s2,String s3)
    {
        if(s1.equals("16进制"))
        {
            if(s2.equals("16进制"))return s3;
            else if(s2.equals("8进制"))return Integer.toString(Integer.parseInt(s3,16),8);
            else if(s2.equals("10进制"))return Integer.parseInt(s3,16)+"";
            else return Integer.toString(Integer.parseInt(s3,16),2);
        }
        else if(s1.equals("10进制"))
        {
            if(s2.equals("16进制"))return Integer.toString(Integer.parseInt(s3),16);
            else if(s2.equals("8进制"))return Integer.toString(Integer.parseInt(s3),8);
            else if(s2.equals("10进制"))return s3;
            else return Integer.toString(Integer.parseInt(s3),2);
        }
        else if(s1.equals("8进制"))
        {
            if(s2.equals("16进制"))return Integer.toString(Integer.parseInt(s3,8),16);
            else if(s2.equals("8进制"))return s3;
            else if(s2.equals("10进制"))return Integer.parseInt(s3,8)+"";
            else return Integer.toString(Integer.parseInt(s3,8),2);
        }
        else if(s1.equals("2进制"))
        {
            if(s2.equals("16进制"))return Integer.toString(Integer.parseInt(s3,2),16);
            else if(s2.equals("8进制"))return Integer.toString(Integer.parseInt(s3,2),8);
            else if(s2.equals("10进制"))return Integer.parseInt(s3,2)+"";
            else return s3;
        }
        return "error";
    }


}