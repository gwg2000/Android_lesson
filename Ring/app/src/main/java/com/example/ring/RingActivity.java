package com.example.ring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RingActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        textView=findViewById(R.id.inf);
        String s=getIntent().getStringExtra("inf");
        textView.setText(s);
    }
    public void close(View view) {
        finish();
    }
}