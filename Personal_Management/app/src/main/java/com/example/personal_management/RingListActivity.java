package com.example.personal_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RingListActivity extends AppCompatActivity implements RingItemFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_list);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.remind:
                Intent intent=new Intent(this,RingActivity.class);
                startActivity(intent);
                break;
            case R.id.rush:
                RefreshWordItemFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteDialog(final String strId) {
        new AlertDialog.Builder(this).setTitle("取消提醒")
                .setMessage("是否取消该提醒")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {            //既可以使用Sql语句删除，也可以使用使用delete方法删除
                        DBoperate wordsDB=DBoperate.getWordsDB();
                        wordsDB.DeleteRing(strId);
                        //RefreshWordItemFragment();
                        Intent intent = new Intent(RingListActivity.this,MyReceiver.class);
                        //intent.setAction("com.gcc.alarm");
                        PendingIntent sender = PendingIntent.getBroadcast(RingListActivity.this, Integer.valueOf(strId), intent, 0);
                        // And cancel the alarm.
                        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                        am.cancel(sender);
                        RefreshWordItemFragment();}    })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {        }    })
                .create().show();
    }
    public void RefreshWordItemFragment() {
       RingItemFragment wordItemFragment = (RingItemFragment) getSupportFragmentManager()
                .findFragmentById(R.id.ringlist);
        wordItemFragment.refreshWordsList();
    }
}