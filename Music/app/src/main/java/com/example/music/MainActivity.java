package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements MusicItem.OnFragmentInteractionListener{

    //Button Pause,Last,Stop,Next;
    SeekBar seekBar;
    private Handler seekbarhandler;// 处理改变进度条事件
    //int UPDATE = 0x101;
    //private boolean autoChange, manulChange;
    public SeekbarThread seekbarthread=null;
    public class SeekbarThread extends Thread {
        private SeekBar seekbar;
        public void run(){
            Message message=new Message();
            message.what=1;
            seekbarhandler.sendMessage(message);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Pause=findViewById(R.id.begin);
//        Last=findViewById(R.id.last);
//        Stop=findViewById(R.id.stop);
//        Next=findViewById(R.id.next);
//        Pause.setOnClickListener(this);
//        Last.setOnClickListener(this);
//        Stop.setOnClickListener(this);
//        Next.setOnClickListener(this);
//        seekbarthread=new SeekbarThread();
//        seekBar=findViewById(R.id.music_bar);
//        seekbarhandler=new Handler(){
//            public void handleMessage(Message msg){
//                super.handleMessage(msg);
//                if(msg.what==1){
//                    seekBar.setProgress((int)(((double)MusicService.player.getCurrentPosition()/MusicService.player.getDuration())*100));
//                    seekbarhandler.postDelayed(seekbarthread, 1000);
//                }
//            }
//        };
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int process=seekBar.getProgress();
                int musicMax=MusicService.player.getDuration();
                int seekBarMax=seekBar.getMax();
                MusicService.player.seekTo(musicMax * process / seekBarMax);
            }
        });

    }

    @Override
    public void onWordItemClick(String musicname) {

        Intent intent=new Intent(this,MusicService.class);
        intent.putExtra("name",musicname);
        startService(intent);
    }

    @Override
    public void onDeleteDialog(String strId) {

    }

    public void onClick_Pause(View view) {
        if(MusicService.player.isPlaying())
            MusicService.player.pause();
        else
            MusicService.player.start();
    }

    public void onClick_Last(View view) {
        if(MusicService.flag-1>=0){
                    Intent intent=new Intent(this,MusicService.class);
                    intent.putExtra("name",MusicService.musicList.get(MusicService.flag-1));
                    MusicService.flag--;
                    startService(intent);}
    }

    public void onClick_Next(View view) {
        if(MusicService.flag+1!=MusicService.totalnum){
            Intent intent=new Intent(this,MusicService.class);
            intent.putExtra("name",MusicService.musicList.get(MusicService.flag+1));
            MusicService.flag++;
            startService(intent);}
    }

    public void onClick_Stop(View view) {
        MusicService.player.stop();
    }




//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.begin:
//                if(MusicService.player.isPlaying())
//                    MusicService.player.pause();
//                else
//                    MusicService.player.start();
//            case R.id.next:
//                if(MusicService.flag+1!=MusicService.totalnum){
//                    Intent intent=new Intent(this,MusicService.class);
//                intent.putExtra("name",MusicService.musicList.get(MusicService.flag+1));
//                MusicService.flag++;
//                startService(intent);}
//            case R.id.last:
//                if(MusicService.flag-1>=0){
//                    Intent intent=new Intent(this,MusicService.class);
//                    intent.putExtra("name",MusicService.musicList.get(MusicService.flag-1));
//                    MusicService.flag--;
//                    startService(intent);}
//
//        }
//    }
}