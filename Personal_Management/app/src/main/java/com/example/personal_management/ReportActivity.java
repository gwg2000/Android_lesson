package com.example.personal_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    Button button_gain;
    EditText edit_name,edit_num,edit_class,edit_temp;
    Boolean gain_position=false;
    String radioselect=null;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        button_gain=findViewById(R.id.gain);
        edit_name=findViewById(R.id.name);
        edit_num=findViewById(R.id.num);
        edit_class=findViewById(R.id.classs);
        edit_temp=findViewById(R.id.temp);

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setCoorType("bd09ll");
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(true);
        option.setWifiCacheTimeOut(5*60*1000);
        option.SetIgnoreCacheException(false);
        option.setNeedNewVersionRgc(true);
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);

//        DBoperate dpoperate=DBoperate.getWordsDB();
//       dpoperate.DeleteAll();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.search:
                Intent intent=new Intent(this,Msg_List.class);
                startActivity(intent);
               break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick_Gain(View view) {
        if(!mLocationClient.isStarted()){
        mLocationClient.start();
        gain_position=true;}
    }

    public void onClick_OK(View view) {

        DBoperate dpoperate=DBoperate.getWordsDB();
        ArrayList<String> list=dpoperate.Findtime();
        String name=edit_name.getText().toString();
        String num=edit_num.getText().toString();
        String classs=edit_class.getText().toString();
        String temp=edit_temp.getText().toString();
        String position=button_gain.getText().toString();

        Calendar calendar = Calendar.getInstance();
        String time=calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DAY_OF_MONTH);
        if(list.contains(time))
        {
            Toast.makeText(ReportActivity.this,"今天已经上报过了，明天再来吧！",Toast.LENGTH_LONG).show();
        }
        else{
            if(name.length()!=0&&num.length()!=0&&classs.length()!=0&&temp.length()!=0&&position.length()!=0&&gain_position!=false){
                dpoperate.Insert(time,name,num,classs,temp,radioselect,position);
                Toast.makeText(ReportActivity.this,"报告成功！",Toast.LENGTH_LONG).show();}
            else
                Toast.makeText(ReportActivity.this,"请仔细查看，您还有没有填的项目哦！",Toast.LENGTH_LONG).show();
        }

    }

    public void onClick_Radio(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.yes:
                if(checked)
                    radioselect="是";
                break;
            case R.id.no:
                if(checked)
                    radioselect="否";
                 break;
        }

    }

    public class MyLocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String addr = location.getAddrStr();
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            button_gain.setText("经度"+longitude+" 纬度"+latitude+"\n"+addr);
        }
    }

}