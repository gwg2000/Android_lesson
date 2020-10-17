package com.example.personal_management;

import android.app.Application;
import android.content.Context;

public class MsgApplication extends Application {
    public  static int num=0;
    private static Context context;
    public static Context getContext(){
        return MsgApplication.context;
    }
    public void onCreate() {
        super.onCreate();
        MsgApplication.context=getApplicationContext();
    }
}
