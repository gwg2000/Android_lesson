package com.example.personal_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.List;

public class ReportActivity extends AppCompatActivity {


    Button button_gain;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        button_gain=findViewById(R.id.gain);

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
        mLocationClient.start();
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