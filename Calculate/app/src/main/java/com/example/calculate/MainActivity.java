package com.example.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;
import java.util.Stack;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Stack<Double> stack_num=new Stack();
    Stack<Character>stack_op=new Stack();
    String num="";

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
    Button button_sin;
    Button button_cos;
    Button button_tan;
    Button button_sql;
    Button button_delete;
    Button button_right_bracket;
    Button button_left_bracket;
    Button button_change;
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
        button_change=findViewById(R.id.change);
        terminal=findViewById(R.id.terminal);

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        button_sin=findViewById(R.id.sin);
        button_cos=findViewById(R.id.cos);
        button_tan=findViewById(R.id.tan);
        button_delete=findViewById(R.id.delete);
        button_sql=findViewById(R.id.sql);
        button_right_bracket=findViewById(R.id.right_bracket);
        button_left_bracket=findViewById(R.id.left_bracket);
        button_sin.setOnClickListener(this);
        button_cos.setOnClickListener(this);
        button_tan.setOnClickListener(this);
        button_sql.setOnClickListener( this);
        button_delete.setOnClickListener( this);
        button_left_bracket.setOnClickListener( this);
        button_right_bracket.setOnClickListener( this);
        }

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
//        button_sin.setOnClickListener(this);
//        button_cos.setOnClickListener(this);
//        button_tan.setOnClickListener(this);
//        button_sql.setOnClickListener( this);
//        button_delete.setOnClickListener( this);
//        button_left_bracket.setOnClickListener( this);
//        button_right_bracket.setOnClickListener( this);
        button_return_zero.setOnClickListener(this);
        button_change.setOnClickListener(this);

    }

    public void onClick(View v) {
        String str=terminal.getText().toString();
        String num="";
        String ope=null;
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
                if(str.equals("0")) {
                    text = "" + ((Button) v).getText();
                }
                else{
                    text = str + ((Button)v).getText();
                }

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
            case R.id.sin:
                terminal.setText(str+"S");
                pointLock2=true;
                break;
            case R.id.cos:
                terminal.setText(str+"C");
                pointLock2=true;
                break;
            case R.id.tan:
                terminal.setText(str+"T");
                pointLock2=true;
                break;
            case R.id.sql:
                terminal.setText(str+"√");
                pointLock2=true;
                break;
            case R.id.delete:
                if(str.length()>=2)
                terminal.setText(str.substring(0,str.length()-1));
                else
                    terminal.setText("");
                break;
            case R.id.left_bracket:
            case R.id.right_bracket:
                terminal.setText(str+((Button)v).getText());
                pointLock2=true;
                break;
            case R.id.change:
                Intent intent=new Intent (MainActivity.this,Transiton.class);
                startActivity(intent);
                break;
            case R.id.equal:
                stack_op.push('#');
                str+='#';
                //System.out.println(str);
                char c;
                for(int i=0;i<str.length();i++) {
                    //if(stack_op.peek()!=null){
                    c=str.charAt(i);
                    Calculate(c);//}
                }
                Double text1=(Double)stack_num.peek();
                stack_num.pop();
                terminal.setText(text1+"");
                pointLock1=false;
                pointLock2=false;
                operateLock=false;
                break;

     }


    }

    char Comchar(char a, char b)
    {
        if(a=='+')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '<';
            else if(b=='/')return '<';
            else if(b=='(')return '<';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='-')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '<';
            else if(b=='/')return '<';
            else if(b=='(')return '<';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='*')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '>';
            else if(b=='/')return '>';
            else if(b=='(')return '<';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='/')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '>';
            else if(b=='/')return '>';
            else if(b=='(')return '<';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='(')
        {
            if(b=='+')return '<';
            else if(b=='-')return '<';
            else if(b=='*')return '<';
            else if(b=='/')return '<';
            else if(b=='(')return '<';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b==')')return '=';
        }
        else if(a==')')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '>';
            else if(b=='/')return '>';
            else if(b==')')return '>';
            else if(b=='S')return '>';
            else if(b=='C')return '>';
            else if(b=='T')return '>';
            else if(b=='√')return '>';
            else if(b=='#')return '>';
        }
        else if(a=='S')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '>';
            else if(b=='/')return '>';
            else if(b=='(')return '>';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='C')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '>';
            else if(b=='/')return '>';
            else if(b=='(')return '>';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='T')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '>';
            else if(b=='/')return '>';
            else if(b=='(')return '>';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='√')
        {
            if(b=='+')return '>';
            else if(b=='-')return '>';
            else if(b=='*')return '>';
            else if(b=='/')return '>';
            else if(b=='(')return '>';
            else if(b==')')return '>';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if(b=='#')return '>';
        }
        else if(a=='#') {
            if (b == '+') return '<';
            else if (b == '-') return '<';
            else if (b == '*') return '<';
            else if (b == '/') return '<';
            else if (b == '(') return '<';
            else if(b=='S')return '<';
            else if(b=='C')return '<';
            else if(b=='T')return '<';
            else if(b=='√')return '<';
            else if (b == '#') return '=';
        }
        return a;
    }


    boolean In(char c)
    {
        if((c>='0'&&c<='9')||c=='.')
            return true;
        else
            return false;
    }

    double Operate(Double a,char b,Double c)
    {
        if(b=='+')
            return a+c;
        else if(b=='-')
            return a-c;
        else if(b=='*')
            return a*c;
        else if(b=='/')
            return a/c;
        return a;
    }
    double Opereate1(char b,Double a)
    {
        if(b=='S')
            return Math.sin(a*Math.PI/180);
        else if(b=='C')
            return Math.cos(a*Math.PI/180);
        else if(b=='T')
            return Math.tan(a*Math.PI/180);
        else
            return Math.sqrt(a);
    }

    public void Calculate(char c)
    {
        if(In(c)){
            num=num+c;
            //System.out.println(num);
        }
        else{
            if(num!=""){
            stack_num.push(Double.parseDouble(num));
            num="";}

//            char ar=Comchar((char)stack_op.peek(),c);
//            System.out.println("OK");
//            System.out.println(ar);
            switch(Comchar((char)stack_op.peek(),c)){
                case '<':
                    stack_op.push(c);break;
                case '=':
                    stack_op.pop();break;
                case '>':
                    char op=(char)stack_op.peek();
                    stack_op.pop();
                    if(op=='+'||op=='-'||op=='*'||op=='/'){
                    Double a=(Double)stack_num.peek();
                    stack_num.pop();
                    Double b=(Double)stack_num.peek();
                    stack_num.pop();
                    stack_num.push(Operate(b,op,a));
                    Calculate(c);}
                    else{
                        Double a=(Double)stack_num.peek();
                        stack_num.pop();
                        stack_num.push(Opereate1(op,a));
                        Calculate(c);
                    }
                    break;
            }

        }
    }

}