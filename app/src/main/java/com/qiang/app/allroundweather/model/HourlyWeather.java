package com.qiang.app.allroundweather.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 强 on 2016/8/31 0031.
 */
public class HourlyWeather implements Serializable {

//    "hourly_forecast": [ //每三小时天气预报，全能版为每小时预报
//    {
//        "date": "2015-07-02 01:00", //时间
//            "hum": "43", //相对湿度（%）
//            "pop": "0", //降水概率
//            "pres": "1003", //气压
//            "tmp": "25", //温度
//            "wind": { //风力风向
//        "deg": "320", //风向（360度）
//                "dir": "西北风", //风向
//                "sc": "微风", //风力
//                "spd": "12" //风速（kmph）
//    }
//    },
//            ......  //略
//    ],
    private String hourlyForecastDate;
    private String hourlyForecastHum;//相对湿度（%）
    private String hourlyForecastPop;//降水概率
    private String hourlyForecastPres;//气压
    private String hourlyForecastTmp;//温度
    private String hourlyForecastWindDeg;//风向（360度）
    private String hourlyForecastWindDir;//风向
    private String hourlyForecastWindSc ;//风力
    private String hourlyForecastWindsSd;//风速（kmph

    public HourlyWeather(JSONObject hourlyForecast) {
        try {
            this.hourlyForecastDate = hourlyForecast.getString("date");
            this.hourlyForecastHum = hourlyForecast.getString("hum");
            this.hourlyForecastPop = hourlyForecast.getString("pop");
            this.hourlyForecastPres = hourlyForecast.getString("pres");
            this.hourlyForecastTmp = hourlyForecast.getString("tmp");
            JSONObject hourlyForecastWind = (JSONObject) hourlyForecast.get("wind");
            this.hourlyForecastWindDeg = hourlyForecastWind.getString("deg");
            this.hourlyForecastWindDir = hourlyForecastWind.getString("dir");
            this.hourlyForecastWindSc = hourlyForecastWind.getString("sc");
            this.hourlyForecastWindsSd = hourlyForecastWind.getString("spd");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return hourlyForecastDate + hourlyForecastHum + hourlyForecastPop + hourlyForecastPres +
                hourlyForecastTmp + hourlyForecastWindDeg + hourlyForecastWindDir + hourlyForecastWindSc  + hourlyForecastWindsSd;
    }

    public String getHourlyForecastDate() {
        return hourlyForecastDate;
    }

    public void setHourlyForecastDate(String hourlyForecastDate) {
        this.hourlyForecastDate = hourlyForecastDate;
    }

    public String getHourlyForecastHum() {
        return hourlyForecastHum;
    }

    public void setHourlyForecastHum(String hourlyForecastHum) {
        this.hourlyForecastHum = hourlyForecastHum;
    }

    public String getHourlyForecastPop() {
        return hourlyForecastPop;
    }

    public void setHourlyForecastPop(String hourlyForecastPop) {
        this.hourlyForecastPop = hourlyForecastPop;
    }

    public String getHourlyForecastPres() {
        return hourlyForecastPres;
    }

    public void setHourlyForecastPres(String hourlyForecastPres) {
        this.hourlyForecastPres = hourlyForecastPres;
    }

    public String getHourlyForecastTmp() {
        return hourlyForecastTmp;
    }

    public void setHourlyForecastTmp(String hourlyForecastTmp) {
        this.hourlyForecastTmp = hourlyForecastTmp;
    }

    public String getHourlyForecastWindDeg() {
        return hourlyForecastWindDeg;
    }

    public void setHourlyForecastWindDeg(String hourlyForecastWindDeg) {
        this.hourlyForecastWindDeg = hourlyForecastWindDeg;
    }

    public String getHourlyForecastWindDir() {
        return hourlyForecastWindDir;
    }

    public void setHourlyForecastWindDir(String hourlyForecastWindDir) {
        this.hourlyForecastWindDir = hourlyForecastWindDir;
    }

    public String getHourlyForecastWindSc() {
        return hourlyForecastWindSc;
    }

    public void setHourlyForecastWindSc(String hourlyForecastWindSc) {
        this.hourlyForecastWindSc = hourlyForecastWindSc;
    }

    public String getHourlyForecastWindsSd() {
        return hourlyForecastWindsSd;
    }

    public void setHourlyForecastWindsSd(String hourlyForecastWindsSd) {
        this.hourlyForecastWindsSd = hourlyForecastWindsSd;
    }
}
