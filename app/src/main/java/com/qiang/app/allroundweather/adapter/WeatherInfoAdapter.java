package com.qiang.app.allroundweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiang.app.allroundweather.R;
import com.qiang.app.allroundweather.model.WeatherDB;
import com.qiang.app.allroundweather.model.WeatherInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 强 on 2016/9/4 0004.
 */
public class WeatherInfoAdapter extends ArrayAdapter<WeatherInfo> {
    private int resourceId;
    private Context context;

    public WeatherInfoAdapter(Context context, int textViewResourceId, List<WeatherInfo> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherInfo weather = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView cityListCountyName = (TextView) view.findViewById(R.id.city_list_county_name);
        TextView cityListNowTemp = (TextView) view.findViewById(R.id.city_list_now_temp);
        TextView cityListNowTime = (TextView) view.findViewById(R.id.city_list_now_time);
        ImageView cityListNowCondCode = (ImageView) view.findViewById(R.id.city_list_now_condCode);

        WeatherInfo weatherInfo = WeatherDB.getInstance(view.getContext()).loadWeatherInfos().get(position);

        cityListCountyName.setText(weatherInfo.getCityName());
        cityListNowTemp.setText(weatherInfo.getNowTmp() + " ℃");
        cityListNowTime.setText(weatherInfo.getUpdateTime());

        return view;
    }

}
