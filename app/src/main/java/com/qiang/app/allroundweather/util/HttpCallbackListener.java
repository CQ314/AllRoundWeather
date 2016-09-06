package com.qiang.app.allroundweather.util;

/**
 * Created by 强 on 2016/8/30 0030.
 */
public interface HttpCallbackListener {

    void onFinish (String response);
    void onError (Exception e);
}
