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

public class MusicService extends Service {
    static MediaPlayer player;
    String name;
    static int flag;
    static int totalnum;
    static List<String> musicList=new ArrayList<String>();
    public MusicService() {

    }

    @Override
    public void onCreate() {
        player=new MediaPlayer();
        totalnum=musicList.size();
        //System.out.println("zongshu"+totalnum);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        name=intent.getStringExtra("name");
        position(name);
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

   void position(String name)
   {
       for(int i=0;i<musicList.size();i++)
       {
           if(name.equals(musicList.get(i)))
           {
               flag=i;
               break;
           }
       }
   }
}
