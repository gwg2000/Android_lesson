package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MusicItem.OnFragmentInteractionListener{

    Handler handler;
    SeekBar seekBar;
    int UPDATE = 0x101;
    static String filenames[]=null;
    AssetManager assets = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

              String  filename []= getResources().getAssets().list("");

              int num=filename.length-2;
              filenames=new String[num];
               for(int i=2;i<filename.length;i++)
                 filenames[i-2]=filename[i].substring(0, filename[i].indexOf("."));


        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        seekBar=findViewById(R.id.music_bar);
        handler=new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what==UPDATE)
                {
                    seekBar.setProgress(msg.arg1);
                }
            }

        };

        final Runnable myWorker = new Runnable() {
            int position, mMax, sMax;
            @Override
            public void run() {
                while(true)
                {
                    if(MusicService.player!=null&&MusicService.player.isPlaying())
                    {
                        position = MusicService.player.getCurrentPosition();
                        mMax = MusicService.player.getDuration();
                        sMax = seekBar.getMax();
                        Message m = handler.obtainMessage();
                        m.arg1 = position * sMax / mMax;
                        m.what = UPDATE;
                        handler.sendMessage(m);
                        try {
                            Thread.sleep(1000);// 每间隔1秒发送一次更新消息
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };

        Thread workThread = new Thread(null, myWorker, "WorkThread");
        workThread.start();
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





}