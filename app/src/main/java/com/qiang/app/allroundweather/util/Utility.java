package com.qiang.app.allroundweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.qiang.app.allroundweather.activity.NormalWeatherActivity;
import com.qiang.app.allroundweather.model.City;
import com.qiang.app.allroundweather.model.AreaDB;
import com.qiang.app.allroundweather.model.County;
import com.qiang.app.allroundweather.model.HourlyWeather;
import com.qiang.app.allroundweather.model.Province;
import com.qiang.app.allroundweather.model.WeatherDB;
import com.qiang.app.allroundweather.model.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by 强 on 2016/8/30 0030.
 */
public class Utility {
    public synchronized static boolean handleProvincesResponse (AreaDB areaDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    areaDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse (AreaDB areaDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String p : allCities) {
                    String[] array = p.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    areaDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCountiesResponse (AreaDB areaDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String p : allCounties) {
                    String[] array = p.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    areaDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

    public static void handleWeatherResponse(Context context, String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray title = jsonObject.getJSONArray("HeWeather data service 3.0");
            JSONObject first_object = (JSONObject) title.get(0);
            JSONObject basic = (JSONObject) first_object.get("basic");//basic 基本信息
            JSONObject update = (JSONObject) basic.get("update");
            String updateTime = update.getString("loc");//更新时间
            String cityName = basic.getString("city");
            WeatherInfo weatherInfo = new WeatherInfo(jsonObject);
            WeatherDB.getInstance(context).saveWeatherInfo(weatherInfo);

        } catch (Exception e) {
            Log.e("Utility", "handle weather response error");
            e.printStackTrace();
        }
    }
}
