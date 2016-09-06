package com.qiang.app.allroundweather.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qiang.app.allroundweather.service.AutoUpdateService;

/**
 * Created by 强 on 2016/9/5 0005.
 */
public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
