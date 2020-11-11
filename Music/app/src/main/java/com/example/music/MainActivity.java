package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements MusicItem.OnFragmentInteractionListener{

    Handler handler;
    SeekBar seekBar;
    Button button_order;
    TextView textname;
    int UPDATE = 0x101;
    static String filenames[]=null;
    static int order=0;
    static Boolean first=false;
    static List<String> musicList=new ArrayList<String>();
    static List<String>musicing_list=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
                //读取assets文件夹下的所有文件
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
        button_order=findViewById(R.id.order);
        textname=findViewById(R.id.musicname);
        //更新进度条
        handler=new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what==UPDATE)
                {
                    seekBar.setProgress(msg.arg1);
                    textname.setText("当前播放歌曲："+MusicService.songname);
                }
            }
        };

        //每隔一秒向主线程发送UPDATE信息，更新进度条位置
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

        //发送UPDATE信息线程启动
        Thread workThread = new Thread(null, myWorker, "WorkThread");
        workThread.start();

        //进度条监听器，控制拖动时的操作
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

    //主Activity注册菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.all:
                musicing_list.clear();
                musicing_list.addAll(musicList);
                MusicService.flag=looking(MusicService.songname);
                RefreshWordItemFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //列表项点击事件
    @Override
    public void onWordItemClick(String musicname) {
       play(musicname);
       first=true;
    }

    //列表项长按事件
    @Override
    public void onDeleteDialog(final String musicname) {
        new AlertDialog.Builder(this).setTitle("从当前列表删除")
                .setMessage("确定将该歌曲从当前列表删除吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        musicing_list.remove(looking(musicname));//删除歌曲
                       MusicService.flag=looking(MusicService.songname);
                        RefreshWordItemFragment();
                   }    })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {        }    })
                .create().show();

    }

    //刷新列表
    private void RefreshWordItemFragment() {
        MusicItem wordItemFragment = (MusicItem) getSupportFragmentManager()
                .findFragmentById(R.id.musiclist);
        wordItemFragment.refreshWordsList();
    }

    int looking(String name){
        for(int i=0;i<musicing_list.size();i++)
        {
            if(name.equals(musicing_list.get(i)))
                return i;
        }
        return -1;
    }

    //暂停/播放按钮
    public void onClick_Pause(View view) {

        if(first==false)
        {
            if(!musicing_list.isEmpty()){
            play(musicing_list.get(0));
            first=true;}
        }
        else if(MusicService.player.isPlaying()){
            MusicService.player.pause();
            first=true;}
        else
            MusicService.player.start();
    }

    //上一首按钮
    public void onClick_Last(View view) {
        if(musicing_list.isEmpty())
            return;
        else if(MusicService.flag==-1){
            play(musicing_list.get(0));
        }
       else if(order==1)
        {
            Random rand = new Random();
            int randNum = rand.nextInt(MusicService.totalnum);
            play(musicing_list.get(randNum));
        }
        else{
        if(MusicService.flag-1>=0){
                   play(musicing_list.get(MusicService.flag-1));}}
    }

    //下一首按钮
    public void onClick_Next(View view) {
        if(musicing_list.isEmpty())
            return;
        else if(MusicService.flag==-1){
            play(musicing_list.get(0));
        }
        else if(order==1)
        {
            Random rand = new Random();
            int randNum = rand.nextInt(MusicService.totalnum);
            play(musicing_list.get(randNum));
        }
        else{
        if(MusicService.flag+1!=MusicService.totalnum){
           play(musicing_list.get(MusicService.flag+1));}}
    }


    //启动Service，播放歌曲
    public void play(String name)
    {
        Intent intent=new Intent(this,MusicService.class);
        intent.putExtra("name",name);
        startService(intent);
    }

    //随机或顺序播放按钮
    public void onClick_order(View view) {
        if(order==0){
        button_order.setText("随机");
        order=1;}
        else
        {
            button_order.setText("顺序");
            order=0;
        }
    }
}