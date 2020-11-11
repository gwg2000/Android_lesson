package com.example.music;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicService extends Service {
    static MediaPlayer player;
    String name;
    static int flag;//播放列表musicing_list的游标
    static int totalnum;//播放列表musicing_list的歌曲总数
    static  String songname;//当前正在播放的歌曲名

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    public MusicService() {}

    @Override
    public void onCreate() {
        player=new MediaPlayer();
        totalnum=MainActivity.musicList.size();

        //监听player播放完成之后要的动作
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer arg0) {
                if (!MainActivity.musicing_list.isEmpty()) {
                    try {
                        player.reset();
                        AssetManager assetManager = getAssets();
                        AssetFileDescriptor assetFileDescriptor = null;
                        if(flag==-1){
                            assetFileDescriptor =
                                    assetManager.openFd(MainActivity.musicing_list.get(0) + ".mp3");
                            flag=0;
                        }
                        else if (MainActivity.order == 1) {
                            Random rand = new Random();
                            int randNum = rand.nextInt(totalnum);
                            assetFileDescriptor =
                                    assetManager.openFd(MainActivity.musicing_list.get(randNum) + ".mp3");
                            flag = randNum;
                        }
                        else {
                            if((flag++)!=totalnum)
                            { flag++;
                            assetFileDescriptor =
                                    assetManager.openFd(MainActivity.musicing_list.get(flag) + ".mp3");}

                        }
                        player.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        player.prepare();
                        player.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
        super.onCreate();
    }

    //每次服务启动，播放歌曲
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        name=intent.getStringExtra("name");
        position(name);
        songname=name;
        try {
            player.reset();
            AssetManager assetManager = getAssets();
            AssetFileDescriptor assetFileDescriptor =
                    assetManager.openFd(name+".mp3");
            player.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //更新flag为播放歌曲在当前列表musicing_list中的位置
   static void position(String name)
   {
       for(int i=0;i<MainActivity.musicing_list.size();i++)
       {
           if(name.equals(MainActivity.musicing_list.get(i)))
           {
               flag=i;
               break;
           }
       }
   }
}
