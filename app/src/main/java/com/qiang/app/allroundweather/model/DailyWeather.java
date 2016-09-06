package com.qiang.app.allroundweather.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 强 on 2016/9/4 0004.
 */
public class DailyWeather implements Serializable {
//    "daily_forecast": [ //7天天气预报
//    {
//        "date": "2015-07-02", //预报日期
//        "astro": { //天文数值
//          "sr": "04:50", //日出时间
//          "ss": "19:47" //日落时间
//         },
//        "cond": { //天气状况
//          "code_d": "100", //白天天气状况代码，参考http://www.heweather.com/documents/condition-code
//          "code_n": "100", //夜间天气状况代码
//          "txt_d": "晴", //白天天气状况描述
//          "txt_n": "晴" //夜间天气状况描述
//          },
//        "hum": "14", //相对湿度（%）
//        "pcpn": "0.0", //降水量（mm）
//        "pop": "0", //降水概率
//        "pres": "1003", //气压
//        "tmp": { //温度
//          "max": "34℃", //最高温度
//          "min": "18℃" //最低温度
//          },
//        "vis": "10", //能见度（km）
//        "wind": { //风力风向
//          "deg": "339", //风向（360度）
//          "dir": "东南风", //风向
//          "sc": "3-4", //风力
//          "spd": "15" //风速（kmph）
//          }
//    },
//            ......  //略
//            ],

    private String dailyForecastDate;
    private String dailyForecastPcpn;
    private String dailyForecastPop;
    private String dailyForecastPres;
    private String dailyForecastVis;
    private String dailyForecastAstroSr;
    private String dailyForecastAstroSs;
    private String dailyForecastCondCoded;
    private String dailyForecastCondCoden;
    private String dailyForecastCondTxtd;
    private String dailyForecastCondTxtn;
    private String dailyForecastTmpMax;
    private String dailyForecastTmpMin;
    private String dailyForecastWindDeg;
    private String dailyForecastWindDir;
    private String dailyForecastWindSc ;
    private String dailyForecastWindsSd;

    public DailyWeather(JSONObject dailyForecast) {
        try {
            this.dailyForecastDate = dailyForecast.getString("date");
            this.dailyForecastPcpn = dailyForecast.getString("pcpn");
            this.dailyForecastPop = dailyForecast.getString("pop");
            this.dailyForecastPres = dailyForecast.getString("pres");
            this.dailyForecastVis = dailyForecast.getString("vis");
            JSONObject dailyForecastAstro = (JSONObject) dailyForecast.get("astro");
            JSONObject dailyForecastCond = (JSONObject) dailyForecast.get("cond");
            JSONObject dailyForecastTmp = (JSONObject) dailyForecast.get("tmp");
            JSONObject dailyForecastWind = (JSONObject) dailyForecast.get("wind");
            this.dailyForecastAstroSr = dailyForecastAstro.getString("sr");
            this.dailyForecastAstroSs = dailyForecastAstro.getString("ss");
            this.dailyForecastCondCoded = dailyForecastCond.getString("code_d");
            this.dailyForecastCondCoden = dailyForecastCond.getString("code_n");
            this.dailyForecastCondTxtd = dailyForecastCond.getString("txt_d");
            this.dailyForecastCondTxtn = dailyForecastCond.getString("txt_n");
            this.dailyForecastTmpMax = dailyForecastTmp.getString("max");
            this.dailyForecastTmpMin = dailyForecastTmp.getString("min");
            this.dailyForecastWindDeg = dailyForecastWind.getString("deg");
            this.dailyForecastWindDir = dailyForecastWind.getString("dir");
            this.dailyForecastWindSc = dailyForecastWind.getString("sc");
            this.dailyForecastWindsSd = dailyForecastWind.getString("spd");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return dailyForecastDate + dailyForecastPcpn + dailyForecastPop +
                dailyForecastPres + dailyForecastVis + dailyForecastAstroSr +
                dailyForecastAstroSs +dailyForecastCondCoded + dailyForecastCondCoden +
                dailyForecastCondTxtd + dailyForecastCondTxtn + dailyForecastTmpMax +
                dailyForecastTmpMin + dailyForecastWindDeg + dailyForecastWindDir +
                dailyForecastWindSc  + dailyForecastWindsSd;
    }

    public String getDailyForecastAstroSr() {

        return dailyForecastAstroSr;
    }

    public void setDailyForecastAstroSr(String dailyForecastAstroSr) {
        this.dailyForecastAstroSr = dailyForecastAstroSr;
    }

    public String getDailyForecastAstroSs() {
        return dailyForecastAstroSs;
    }

    public void setDailyForecastAstroSs(String dailyForecastAstroSs) {
        this.dailyForecastAstroSs = dailyForecastAstroSs;
    }

    public String getDailyForecastCondCoded() {
        return dailyForecastCondCoded;
    }

    public void setDailyForecastCondCoded(String dailyForecastCondCoded) {
        this.dailyForecastCondCoded = dailyForecastCondCoded;
    }

    public String getDailyForecastCondCoden() {
        return dailyForecastCondCoden;
    }

    public void setDailyForecastCondCoden(String dailyForecastCondCoden) {
        this.dailyForecastCondCoden = dailyForecastCondCoden;
    }

    public String getDailyForecastCondTxtd() {
        return dailyForecastCondTxtd;
    }

    public void setDailyForecastCondTxtd(String dailyForecastCondTxtd) {
        this.dailyForecastCondTxtd = dailyForecastCondTxtd;
    }

    public String getDailyForecastCondTxtn() {
        return dailyForecastCondTxtn;
    }

    public void setDailyForecastCondTxtn(String dailyForecastCondTxtn) {
        this.dailyForecastCondTxtn = dailyForecastCondTxtn;
    }

    public String getDailyForecastDate() {
        return dailyForecastDate;
    }

    public void setDailyForecastDate(String dailyForecastDate) {
        this.dailyForecastDate = dailyForecastDate;
    }

    public String getDailyForecastPcpn() {
        return dailyForecastPcpn;
    }

    public void setDailyForecastPcpn(String dailyForecastPcpn) {
        this.dailyForecastPcpn = dailyForecastPcpn;
    }

    public String getDailyForecastPop() {
        return dailyForecastPop;
    }

    public void setDailyForecastPop(String dailyForecastPop) {
        this.dailyForecastPop = dailyForecastPop;
    }

    public String getDailyForecastPres() {
        return dailyForecastPres;
    }

    public void setDailyForecastPres(String dailyForecastPres) {
        this.dailyForecastPres = dailyForecastPres;
    }

    public String getDailyForecastTmpMax() {
        return dailyForecastTmpMax;
    }

    public void setDailyForecastTmpMax(String dailyForecastTmpMax) {
        this.dailyForecastTmpMax = dailyForecastTmpMax;
    }

    public String getDailyForecastTmpMin() {
        return dailyForecastTmpMin;
    }

    public void setDailyForecastTmpMin(String dailyForecastTmpMin) {
        this.dailyForecastTmpMin = dailyForecastTmpMin;
    }

    public String getDailyForecastVis() {
        return dailyForecastVis;
    }

    public void setDailyForecastVis(String dailyForecastVis) {
        this.dailyForecastVis = dailyForecastVis;
    }

    public String getDailyForecastWindDeg() {
        return dailyForecastWindDeg;
    }

    public void setDailyForecastWindDeg(String dailyForecastWindDeg) {
        this.dailyForecastWindDeg = dailyForecastWindDeg;
    }

    public String getDailyForecastWindDir() {
        return dailyForecastWindDir;
    }

    public void setDailyForecastWindDir(String dailyForecastWindDir) {
        this.dailyForecastWindDir = dailyForecastWindDir;
    }

    public String getDailyForecastWindSc() {
        return dailyForecastWindSc;
    }

    public void setDailyForecastWindSc(String dailyForecastWindSc) {
        this.dailyForecastWindSc = dailyForecastWindSc;
    }

    public String getDailyForecastWindsSd() {
        return dailyForecastWindsSd;
    }

    public void setDailyForecastWindsSd(String dailyForecastWindsSd) {
        this.dailyForecastWindsSd = dailyForecastWindsSd;
    }
}
