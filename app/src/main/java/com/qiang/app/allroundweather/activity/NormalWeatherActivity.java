package com.qiang.app.allroundweather.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiang.app.allroundweather.R;
import com.qiang.app.allroundweather.adapter.HourlyWeatherAdapter;
import com.qiang.app.allroundweather.model.HourlyWeather;
import com.qiang.app.allroundweather.model.WeatherDB;
import com.qiang.app.allroundweather.model.WeatherInfo;
import com.qiang.app.allroundweather.util.HttpCallbackListener;
import com.qiang.app.allroundweather.util.HttpUtil;
import com.qiang.app.allroundweather.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class NormalWeatherActivity extends AppCompatActivity {

    private  TextView testView;
    //    private TextView ;

    private TextView countyName;
    private TextView nowTmp;
    private TextView nowFl;
    private TextView updateTime;
    private TextView nowPcpn;
    private TextView nowHum;
    private TextView nowCondTxt;
    private TextView nowWind;
    private TextView nowPres;
    private TextView nowVis;
    private TextView aqiCityQlty;
    private TextView aqiCityPm25;

    private ImageView nowCondCode;

    public List<HourlyWeather> hourlyWeatherList = new ArrayList<>();
    private ListView hourlyForecastListview;

    private ProgressDialog progressDialog;
    private String mCountyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String countyName = getSharedPreferences(getIntent().getStringExtra("county_name"), MODE_PRIVATE).getString("countyName", "");
//                Toast.makeText(NormalWeatherActivity.this, countyName, Toast.LENGTH_SHORT);
//                if (!TextUtils.isEmpty(countyName)) {
//                    queryFromServer(countyName);
//                }else {
//                    Toast.makeText(NormalWeatherActivity.this, "county is null", Toast.LENGTH_SHORT);
//                }
            }
        });
        testView = (TextView) findViewById(R.id.testView);
        countyName= (TextView) findViewById(R.id.cityName);
        nowTmp= (TextView) findViewById(R.id.nowTmp);
        nowFl= (TextView) findViewById(R.id.nowFl);
        updateTime= (TextView) findViewById(R.id.updateTime);
        nowPcpn= (TextView) findViewById(R.id.nowPcpn);
        nowHum= (TextView) findViewById(R.id.nowHum);
        nowCondTxt= (TextView) findViewById(R.id.nowCondTxt);
        nowWind= (TextView) findViewById(R.id.nowWind);
        nowPres= (TextView) findViewById(R.id.nowPres);
        nowVis= (TextView)  findViewById(R.id.nowVis);
        aqiCityQlty= (TextView) findViewById(R.id.aqiCityQlty);
        aqiCityPm25= (TextView) findViewById(R.id.aqiCityPm25);
        nowCondCode = (ImageView) findViewById(R.id.nowCondCode);
        hourlyForecastListview = (ListView) findViewById(R.id.hourlyForecastListview);
        HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter(NormalWeatherActivity.this, R.layout.list_weather_hourly, hourlyWeatherList);
        hourlyForecastListview.setAdapter(hourlyWeatherAdapter);

        mCountyName = getIntent().getStringExtra("county_name");

        queryFromServer(mCountyName);

        hourlyWeatherAdapter.notifyDataSetChanged();
    }

    private void queryFromServer(final String countyName) {
        try {
            String url = "http://apis.baidu.com/heweather/weather/free?city=";
            //String name = new String(countyName.getBytes("UTF-8"), "iso-8859-1");
            HttpUtil.sendHttpRequest(url + countyName, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    //testView.setText(response );
                    Utility.handleWeatherResponse(NormalWeatherActivity.this, response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeather();
                            Intent intent = new Intent();
                            intent.putExtra("county_name", countyName);
                            setResult(RESULT_OK, intent);
                        }
                    });
                }
                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(NormalWeatherActivity.this, "同步失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } catch (Exception e) {
            Log.e("NormalWeatherActivity", "Request from server error");
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showWeather() {
        WeatherInfo weatherInfo = WeatherDB.getInstance(NormalWeatherActivity.this).loadCityWeatherInfo(mCountyName);
        countyName.setText(weatherInfo.getCityName());
        nowTmp.setText("温度:" + weatherInfo.getNowTmp() + "℃");
        nowFl.setText("体感:" + weatherInfo.getNowFl() + "℃");
        updateTime.setText(weatherInfo.getUpdateTime());
        nowPcpn.setText("降雨量:" + weatherInfo.getNowPcpn() + "mm");
        nowHum.setText("湿度:" + weatherInfo.getNowHum() + "%");
        nowCondTxt.setText(weatherInfo.getNowCondTxt());
        nowWind.setText("风力:" + weatherInfo.getNowWindDir() +  weatherInfo.getNowWindSc() + "级");
        nowPres.setText("气压:" + weatherInfo.getNowPres());
        nowVis.setText("能见度:" + weatherInfo.getNowVis() + "km");
        aqiCityQlty.setText("空气质量:" + weatherInfo.getAqiCityQlty());
        aqiCityPm25.setText("PM2.5:" + weatherInfo.getAqiCityPm25() + "ug/m³");
        hourlyWeatherList = weatherInfo.getHourlyForecast();
        Toast.makeText(NormalWeatherActivity.this, "已经是最新数据了", Toast.LENGTH_SHORT).show();
    }

}
