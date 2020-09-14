package com.example.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_zero;
    Button button_one;
    Button button_two;
    Button button_three;
    Button button_four;
    Button button_five;
    Button button_six;
    Button button_seven;
    Button button_eigth;
    Button button_nine;
    Button button_add;
    Button button_subtract;
    Button button_multiply;
    Button button_divide;
    Button button_equal;
    Button button_return_zero;
    Button button_point;
    TextView terminal;//显示屏

    Boolean pointLock1=false;//防止出现两个连续的小数点
    Boolean pointLock2=false;//防止在运算符后接小数点
    Boolean operateLock=false;//防止出现两个连续的运算符



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_zero=findViewById(R.id.zero);
        button_one=findViewById(R.id.one);
        button_two=findViewById(R.id.two);
        button_three=findViewById(R.id.three);
        button_four=findViewById(R.id.four);
        button_five=findViewById(R.id.five);
        button_six=findViewById(R.id.six);
        button_seven=findViewById(R.id.seven);
        button_eigth=findViewById(R.id.eigth);
        button_nine=findViewById(R.id.nine);
        button_add=findViewById(R.id.add);
        button_subtract=findViewById(R.id.subtract);
        button_multiply=findViewById(R.id.multiply);
        button_divide=findViewById(R.id.divide);
        button_return_zero=findViewById(R.id.return_zero);
        button_equal=findViewById(R.id.equal);
        button_point=findViewById(R.id.point);
        terminal=findViewById(R.id.terminal);

        button_zero.setOnClickListener(this);
        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
        button_three.setOnClickListener( this);
        button_four.setOnClickListener( this);
        button_five.setOnClickListener(this);
        button_six.setOnClickListener(this);
        button_seven.setOnClickListener(this);
        button_eigth.setOnClickListener(this);
        button_nine.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_subtract.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener( this);
        button_point.setOnClickListener(this);
        button_equal.setOnClickListener(this);
        button_return_zero.setOnClickListener(this);
    }

    public void onClick(View v) {
        String str=terminal.getText().toString();
        switch (v.getId()){
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eigth:
            case R.id.nine:
                if(str.equals("error"))
                    terminal.setText("0");
                pointLock2=false;
                operateLock=false;
                String text;
                if(str.equals("0"))
                    text = "" + ((Button)v).getText();
                else
                    text = str + ((Button)v).getText();
                terminal.setText(text);
                break;
            case R.id.point:
                if(!pointLock1&&!pointLock2){
                    terminal.setText(str+((Button)v).getText());
                    pointLock1=true;}
                break;
            case R.id.add:
            case R.id.subtract:
            case R.id.multiply:
            case R.id.divide:
                if(str.charAt(str.length()-1)!='.'&&!operateLock){
                    terminal.setText(str+((Button)v).getText());
                    pointLock1=false;
                    pointLock2=true;
                    operateLock=true;
                }
                break;
            case R.id.return_zero:
                terminal.setText("0");
                pointLock1=false;
                pointLock2=false;
                operateLock=false;
                break;
            case R.id.equal:
                String text1=calculate(str);
                terminal.setText(text1);
                pointLock1=false;
                pointLock2=false;
                operateLock=false;
                break;

     }


    }

    public String calculate(String str)
    {
        return "succes";
    }

}