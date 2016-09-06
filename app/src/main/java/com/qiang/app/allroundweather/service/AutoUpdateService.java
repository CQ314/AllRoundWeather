package com.qiang.app.allroundweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.qiang.app.allroundweather.model.WeatherDB;
import com.qiang.app.allroundweather.util.HttpCallbackListener;
import com.qiang.app.allroundweather.util.HttpUtil;
import com.qiang.app.allroundweather.util.Utility;

/**
 * Created by 强 on 2016/9/5 0005.
 */
public class AutoUpdateService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 28800000;//8*60*60*1000
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        return  super.onStartCommand(intent, flags, args);
    }

    private void updateWeather() {
        String url = "http://apis.baidu.com/heweather/weather/free?city=";
        String countyName = WeatherDB.getInstance(AutoUpdateService.this).loadWeatherInfos().get(0).getCityName();
        HttpUtil.sendHttpRequest(url + countyName, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Utility.handleWeatherResponse(AutoUpdateService.this, response);
            }
            @Override
            public void onError(Exception e) {
                Log.e("AutoUpdateService", "Request from server error");
            }
        });
    }
}
