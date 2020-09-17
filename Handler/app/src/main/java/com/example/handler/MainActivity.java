package com.example.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.num);
        textView=findViewById(R.id.second);
        button=findViewById(R.id.button);

        final Handler handler = new Handler(){
            public void handleMessage(Message msg) {
                textView.setText((String)msg.obj);
            }
        };
        final Runnable myWorker = new Runnable() {
            public void run() {
                if(editText.getText().toString()!=null){
                int a=Integer.parseInt(editText.getText().toString());
                Message msg = new Message();
               if(isPrimeNumber(a))
                   msg.obj="是";
               else
                   msg.obj="否";
               handler.sendMessage(msg);}
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker, "WorkThread");
                workThread.start();
            }
        });


    }

    public Boolean isPrimeNumber(int a)
    {
        for(int i=2;i<a;i++)
        {
            if(a%i==0)
                return false;
            break;
        }
        return true;
    }
}