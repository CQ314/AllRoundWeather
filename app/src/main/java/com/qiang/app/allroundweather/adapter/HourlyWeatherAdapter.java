package com.qiang.app.allroundweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qiang.app.allroundweather.R;
import com.qiang.app.allroundweather.model.HourlyWeather;
import com.qiang.app.allroundweather.model.WeatherDB;
import com.qiang.app.allroundweather.model.WeatherInfo;

import java.util.List;

/**
 * Created by 强 on 2016/8/31 0031.
 */
public class HourlyWeatherAdapter extends ArrayAdapter<HourlyWeather> {
    private int resourceId;
    private Context context;

    public HourlyWeatherAdapter(Context context, int textViewResourceId, List<HourlyWeather> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        HourlyWeather hourlyWeather = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView hourlyForecastTime = (TextView) view.findViewById(R.id.hourlyForecastTime);
        TextView hourlyForecastTemp = (TextView) view.findViewById(R.id.hourlyForecastTemp);
        TextView hourlyForecastPop = (TextView) view.findViewById(R.id.hourlyForecastPop);
        TextView hourlyForecastWind = (TextView) view.findViewById(R.id.hourlyForecastWind);

        String[] array = hourlyWeather.getHourlyForecastDate().split(" ");//只取时间
        String hourlyTemp = "温度：" + hourlyWeather.getHourlyForecastTmp() + "℃";
        String hourlyPop = "降水概率：" + hourlyWeather.getHourlyForecastPop();
        String hourlyWind = "风力：" + hourlyWeather.getHourlyForecastWindDir() + " " + hourlyWeather.getHourlyForecastWindSc() + "级";

        hourlyForecastTime.setText(array[1]);
        hourlyForecastTemp.setText(hourlyTemp);
        hourlyForecastPop.setText(hourlyPop);
        hourlyForecastWind.setText(hourlyWind);
        return view;
    }

}
