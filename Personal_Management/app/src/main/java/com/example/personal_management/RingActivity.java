package com.example.personal_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.UUID;

public class RingActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    EditText editText;
    String string;
    DBoperate dBoperate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        editText = findViewById(R.id.edit);
        dBoperate = DBoperate.getWordsDB();
    }

    public void onClick_begin(View view) {
        Calendar currentTime = Calendar.getInstance();
        new TimePickerDialog(RingActivity.this, 0,
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
                        alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
                        Intent intent = new Intent(RingActivity.this, MyReceiver.class);
                        intent.setAction("com.gcc.alarm");

                        string = editText.getText().toString();
                        intent.putExtra("inf", string);


                        String time = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE);

//                        DBoperate dBoperate = DBoperate.getWordsDB();

                        int id=dBoperate.gain_id()+1;
                        dBoperate.InsertRing(id+"",time,string);
                        intent.putExtra("IDD",id+"");
                        pendingIntent = PendingIntent.getBroadcast(RingActivity.this,id, intent, 0);

                        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                        Toast.makeText(RingActivity.this, "闹钟已开启！", Toast.LENGTH_SHORT).show();
                    }
                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                .get(Calendar.MINUTE), false).show();
    }

    public static void cancel(int id)
    {

    }


}