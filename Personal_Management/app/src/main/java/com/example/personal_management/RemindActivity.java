package com.example.personal_management;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RemindActivity extends AppCompatActivity {

    TextView textView;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        textView=findViewById(R.id.inf);
        mp =MediaPlayer.create(this,R.raw.nanshannan);
        mp.start();
        String s=getIntent().getStringExtra("inf");
        String ID=getIntent().getStringExtra("ID");
        DBoperate dBoperate=DBoperate.getWordsDB();
        dBoperate.DeleteRing(ID);
        textView.setText(s);
    }
    public void close(View view) {
        mp.stop();
        finish();
    }
}