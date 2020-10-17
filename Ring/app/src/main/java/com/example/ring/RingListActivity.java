package com.example.ring;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class RingListActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    EditText editText;
    String string;

    ArrayList<String>  ID=new ArrayList<String>();
    ArrayList<String>  Time=new ArrayList<String>();
    ArrayList<String>  Msg=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_list);
        editText=findViewById(R.id.edit);
    }

    public void onClick_begin(View view) {
        Calendar currentTime = Calendar.getInstance();
        new TimePickerDialog(RingListActivity.this, 0,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //设置当前时间
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        // 根据用户选择的时间来设置Calendar对象
                        c.set(Calendar.HOUR, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        // ②设置AlarmManager在Calendar对应的时间启动Activity
                        alarmManager= (AlarmManager) getSystemService(Service.ALARM_SERVICE);
                        Intent intent = new Intent(RingListActivity.this, RingActivity.class);
                        string=editText.getText().toString();
                        intent.putExtra("inf",string);
                        pendingIntent = PendingIntent.getActivity(RingListActivity.this, 0, intent, 0);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                        Toast.makeText(RingListActivity.this, "闹钟已开启！", Toast.LENGTH_SHORT).show();
                    }
                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                .get(Calendar.MINUTE), false).show();
    }

}