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
    static int flag;
    static int totalnum;
    static  String songname;
//    static List<String> musicList=new ArrayList<String>();
//    static List<String>musicing_list=new ArrayList<String>();
    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    public MusicService() {

    }

    @Override
    public void onCreate() {
        player=new MediaPlayer();
        totalnum=MainActivity.musicList.size();
        //System.out.println("zongshu"+totalnum);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer arg0) {
                try {
                    player.reset();
                    AssetManager assetManager = getAssets();
                    AssetFileDescriptor assetFileDescriptor=null;
                    if(MainActivity.order==1){
                        Random rand = new Random();
                        int randNum = rand.nextInt(totalnum);
                         assetFileDescriptor =
                                assetManager.openFd(MainActivity.musicing_list.get(randNum)+".mp3");
                        flag=randNum;
                    }
                    else{
                     assetFileDescriptor =
                            assetManager.openFd(MainActivity.musicing_list.get(flag+1)+".mp3");
                        flag++;}
                    player.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    player.prepare();
                    player.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
        super.onCreate();
    }

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
