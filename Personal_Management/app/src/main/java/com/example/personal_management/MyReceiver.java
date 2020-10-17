package com.example.personal_management;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction()!=null&&intent.getAction().equals("com.gcc.alarm")) {//自定义的action
            String s=intent.getStringExtra("inf");
            String ID=intent.getStringExtra("IDD");
            intent = new Intent(context, RemindActivity.class);
            intent.putExtra("inf",s);
            intent.putExtra("ID",ID);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
