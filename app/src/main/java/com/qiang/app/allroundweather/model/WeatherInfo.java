package com.qiang.app.allroundweather.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 强 on 2016/9/4 0004.
 */
public class WeatherInfo implements Serializable {
    private String cityName;
    private String updateTime;
    //          "now": { //实况天气
//                "cond": { //天气状况
//                    "code": "100", //天气状况代码
//                     "txt": "晴" //天气状况描述},
//                "fl": "30", //体感温度
//                 "hum": "20%", //相对湿度（%）
//                 "pcpn": "0.0", //降水量（mm）
//                 "pres": "1001", //气压
//                 "tmp": "32", //温度
//                 "vis": "10", //能见度（km）
//                 "wind": { //风力风向
//                    "deg": "10", //风向（360度）
//                    "dir": "北风", //风向
//                    "sc": "3级", //风力
//                    "spd": "15" //风速（kmph）}
//            },
    private String  nowFl;
    private String  nowHum;
    private String  nowPcpn;
    private String  nowPres;
    private String  nowTmp;
    private String  nowVis;
    private String  nowCondCode;
    private String  nowCondTxt;
    private String  nowWindDeg;
    private String  nowWindDir;
    private String  nowWindSc;
    private String  nowWindSpd;
//             "aqi": { //空气质量，仅限国内部分城市，国际城市无此字段
//             "city": {
//                 "aqi": "30", //空气质量指数
//                 "co": "0", //一氧化碳1小时平均值(ug/m³)
//                 "no2": "10", //二氧化氮1小时平均值(ug/m³)
//                 "o3": "94", //臭氧1小时平均值(ug/m³)
//                 "pm10": "10", //PM10 1小时平均值(ug/m³)
//                 "pm25": "7", //PM2.5 1小时平均值(ug/m³)
//                 "qlty": "优", //空气质量类别
//                 "so2": "3" //二氧化硫1小时平均值(ug/m³) }
//         },
    private String  aqiCityAqi;
    private String  aqiCityCo;
    private String  aqiCityNo2;
    private String  aqiCityO3;
    private String  aqiCityPm10;
    private String  aqiCityPm25;
    private String  aqiCityQlty;
    private String  aqiCitySo2;

    private ArrayList<DailyWeather> dailyForecast;
    private ArrayList<HourlyWeather> hourlyForecast;
//    "suggestion": { //生活指数，仅限国内城市，国际城市无此字段
//        "comf": { //舒适度指数
//          "brf": "较不舒适", //简介
//                    "txt": "白天天气多云，同时会感到有些热，不很舒适。" //详细描述//        },
//        "cw": { //洗车指数
//            "brf": "较适宜",
//                    "txt": "较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"//        },
//        "drsg": { //穿衣指数
//            "brf": "炎热",
//                    "txt": "天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"//        },
//        "flu": { //感冒指数
//            "brf": "少发",
//                    "txt": "各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"//        },
//        "sport": { //运动指数
//            "brf": "较适宜",
//                    "txt": "天气较好，户外运动请注意防晒。推荐您进行室内运动。"//        },
//        "trav": { //旅游指数
//            "brf": "较适宜",
//                    "txt": "天气较好，温度较高，天气较热，但有微风相伴，还是比较适宜旅游的，不过外出时要注意防暑防晒哦！"//        },
//        "uv": { //紫外线指数
//            "brf": "中等",
//                    "txt": "属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"//        }
//    }
//}

    private String suggestionComfBrf;
    private String suggestionComfTxt;
    private String suggestionCwBrf;
    private String suggestionCwTxt;
    private String suggestionDrsgBrf;
    private String suggestionDrsgTxt;
    private String suggestionFluBrf;
    private String suggestionFluTxt;
    private String suggestionSportBrf;
    private String suggestionSportTxt;
    private String suggestionTravBrf;
    private String suggestionTravTxt;
    private String suggestionUvBrf;
    private String suggestionUvTxt;


    public WeatherInfo() {
    }

    public WeatherInfo(String cityName) {
        this.cityName = cityName;
    }

    public WeatherInfo(JSONObject jsonObject) {
        try {
            JSONArray title = jsonObject.getJSONArray("HeWeather data service 3.0");
            JSONObject first_object = (JSONObject) title.get(0);

            JSONObject basic = (JSONObject) first_object.get("basic");//basic 基本信息
            JSONObject update = (JSONObject) basic.get("update");
            this.updateTime = update.getString("loc");//更新时间
            this.cityName = basic.getString("city");//城市名 同county name

            JSONObject now = (JSONObject) first_object.get("now");
            this.nowFl =  now.getString("fl");
            this.nowHum = now.getString("hum");
            this.nowPcpn = now.getString("pcpn");
            this.nowPres = now.getString("pres");
            this.nowTmp = now.getString("tmp");
            this.nowVis = now.getString("vis");
            JSONObject nowCond = (JSONObject) now.get("cond");
            this.nowCondCode =  nowCond.getString("code");
            this.nowCondTxt =  nowCond.getString("txt");
            JSONObject nowWind = (JSONObject) now.get("wind");
            this.nowWindDeg =  nowWind.getString("deg");
            this.nowWindDir =  nowWind.getString("dir");
            this.nowWindSc =  nowWind.getString("sc");
            this.nowWindSpd =  nowWind.getString("spd");

            JSONObject aqi = (JSONObject) first_object.get("aqi");
            JSONObject aqiCity = (JSONObject) aqi.get("city");
            this.aqiCityAqi =  aqiCity.getString("aqi");
            this.aqiCityCo =  aqiCity.getString("co");
            this.aqiCityNo2 =  aqiCity.getString("no2");
            this.aqiCityO3 =  aqiCity.getString("o3");
            this.aqiCityPm10 =  aqiCity.getString("pm10");
            this.aqiCityPm25 =  aqiCity.getString("pm25");
            this.aqiCityQlty =  aqiCity.getString("qlty");
            this.aqiCitySo2 =  aqiCity.getString("so2");

            JSONObject suggestion = (JSONObject) first_object.get("suggestion");
            JSONObject suggestionComf = (JSONObject) first_object.get("comf");
            JSONObject suggestionCw = (JSONObject) first_object.get("cw");
            JSONObject suggestionDrsg = (JSONObject) first_object.get("drsg");
            JSONObject suggestionFlu = (JSONObject) first_object.get("flu");
            JSONObject suggestionSport = (JSONObject) first_object.get("sport");
            JSONObject suggestionTrav = (JSONObject) first_object.get("trav");
            JSONObject suggestionUv = (JSONObject) first_object.get("uv");
            this.suggestionComfBrf =  suggestionComf.getString("brf");
            this.suggestionComfTxt =  suggestionComf.getString("txt");
            this.suggestionCwBrf =  suggestionCw.getString("brf");
            this.suggestionCwTxt =  suggestionCw.getString("txt");
            this.suggestionDrsgBrf =  suggestionDrsg.getString("brf");
            this.suggestionDrsgTxt =  suggestionDrsg.getString("txt");
            this.suggestionFluBrf =  suggestionFlu.getString("brf");
            this.suggestionFluTxt =  suggestionFlu.getString("txt");
            this.suggestionSportBrf =  suggestionSport.getString("brf");
            this.suggestionSportTxt =  suggestionSport.getString("txt");
            this.suggestionTravBrf =  suggestionTrav.getString("brf");
            this.suggestionTravTxt =  suggestionTrav.getString("txt");
            this.suggestionUvBrf =  suggestionUv.getString("brf");
            this.suggestionUvTxt =  suggestionUv.getString("txt");
            JSONArray jsonArray = (JSONArray) first_object.get("daily_forecast");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject dailyForecast = jsonArray.getJSONObject(i);
                this.dailyForecast.add(new DailyWeather(dailyForecast));
            }
            jsonArray = (JSONArray) first_object.get("hourly_forecast");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject hourlyForecast = jsonArray.getJSONObject(i);
                this.hourlyForecast.add(new HourlyWeather(hourlyForecast));
            }
        }catch (Exception e){
            Log.e("WeatherInfo", "handle weather response error");
            e.printStackTrace();
        }
    }

    public String toString() {
        return      cityName + updateTime +
                nowFl + nowHum + nowPcpn + nowPres + nowTmp + nowVis + nowCondCode + nowCondTxt + nowWindDeg + nowWindDir + nowWindSc + nowWindSpd +
                aqiCityAqi + aqiCityCo + aqiCityNo2 + aqiCityO3 + aqiCityPm10 + aqiCityPm25 + aqiCityQlty + aqiCitySo2 +
                suggestionComfBrf + suggestionComfTxt + suggestionCwBrf + suggestionCwTxt + suggestionDrsgBrf + suggestionDrsgTxt + suggestionFluBrf + suggestionFluTxt + suggestionSportBrf + suggestionSportTxt + suggestionTravBrf + suggestionTravTxt + suggestionUvBrf + suggestionUvTxt +
        dailyForecast.toString() + hourlyForecast.toString();
    }

    public String getAqiCityAqi() {
        return aqiCityAqi;
    }

    public void setAqiCityAqi(String aqiCityAqi) {
        this.aqiCityAqi = aqiCityAqi;
    }

    public String getAqiCityCo() {
        return aqiCityCo;
    }

    public void setAqiCityCo(String aqiCityCo) {
        this.aqiCityCo = aqiCityCo;
    }

    public String getAqiCityNo2() {
        return aqiCityNo2;
    }

    public void setAqiCityNo2(String aqiCityNo2) {
        this.aqiCityNo2 = aqiCityNo2;
    }

    public String getAqiCityO3() {
        return aqiCityO3;
    }

    public void setAqiCityO3(String aqiCityO3) {
        this.aqiCityO3 = aqiCityO3;
    }

    public String getAqiCityPm10() {
        return aqiCityPm10;
    }

    public void setAqiCityPm10(String aqiCityPm10) {
        this.aqiCityPm10 = aqiCityPm10;
    }

    public String getAqiCityPm25() {
        return aqiCityPm25;
    }

    public void setAqiCityPm25(String aqiCityPm25) {
        this.aqiCityPm25 = aqiCityPm25;
    }

    public String getAqiCityQlty() {
        return aqiCityQlty;
    }

    public void setAqiCityQlty(String aqiCityQlty) {
        this.aqiCityQlty = aqiCityQlty;
    }

    public String getAqiCitySo2() {
        return aqiCitySo2;
    }

    public void setAqiCitySo2(String aqiCitySo2) {
        this.aqiCitySo2 = aqiCitySo2;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public ArrayList<DailyWeather> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(ArrayList<DailyWeather> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public ArrayList<HourlyWeather> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(ArrayList<HourlyWeather> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public String getNowCondCode() {
        return nowCondCode;
    }

    public void setNowCondCode(String nowCondCode) {
        this.nowCondCode = nowCondCode;
    }

    public String getNowCondTxt() {
        return nowCondTxt;
    }

    public void setNowCondTxt(String nowCondTxt) {
        this.nowCondTxt = nowCondTxt;
    }

    public String getNowFl() {
        return nowFl;
    }

    public void setNowFl(String nowFl) {
        this.nowFl = nowFl;
    }

    public String getNowHum() {
        return nowHum;
    }

    public void setNowHum(String nowHum) {
        this.nowHum = nowHum;
    }

    public String getNowPcpn() {
        return nowPcpn;
    }

    public void setNowPcpn(String nowPcpn) {
        this.nowPcpn = nowPcpn;
    }

    public String getNowPres() {
        return nowPres;
    }

    public void setNowPres(String nowPres) {
        this.nowPres = nowPres;
    }

    public String getNowTmp() {
        return nowTmp;
    }

    public void setNowTmp(String nowTmp) {
        this.nowTmp = nowTmp;
    }

    public String getNowVis() {
        return nowVis;
    }

    public void setNowVis(String nowVis) {
        this.nowVis = nowVis;
    }

    public String getNowWindDeg() {
        return nowWindDeg;
    }

    public void setNowWindDeg(String nowWindDeg) {
        this.nowWindDeg = nowWindDeg;
    }

    public String getNowWindDir() {
        return nowWindDir;
    }

    public void setNowWindDir(String nowWindDir) {
        this.nowWindDir = nowWindDir;
    }

    public String getNowWindSc() {
        return nowWindSc;
    }

    public void setNowWindSc(String nowWindSc) {
        this.nowWindSc = nowWindSc;
    }

    public String getNowWindSpd() {
        return nowWindSpd;
    }

    public void setNowWindSpd(String nowWindSpd) {
        this.nowWindSpd = nowWindSpd;
    }

    public String getSuggestionComfBrf() {
        return suggestionComfBrf;
    }

    public void setSuggestionComfBrf(String suggestionComfBrf) {
        this.suggestionComfBrf = suggestionComfBrf;
    }

    public String getSuggestionComfTxt() {
        return suggestionComfTxt;
    }

    public void setSuggestionComfTxt(String suggestionComfTxt) {
        this.suggestionComfTxt = suggestionComfTxt;
    }

    public String getSuggestionCwBrf() {
        return suggestionCwBrf;
    }

    public void setSuggestionCwBrf(String suggestionCwBrf) {
        this.suggestionCwBrf = suggestionCwBrf;
    }

    public String getSuggestionCwTxt() {
        return suggestionCwTxt;
    }

    public void setSuggestionCwTxt(String suggestionCwTxt) {
        this.suggestionCwTxt = suggestionCwTxt;
    }

    public String getSuggestionDrsgBrf() {
        return suggestionDrsgBrf;
    }

    public void setSuggestionDrsgBrf(String suggestionDrsgBrf) {
        this.suggestionDrsgBrf = suggestionDrsgBrf;
    }

    public String getSuggestionDrsgTxt() {
        return suggestionDrsgTxt;
    }

    public void setSuggestionDrsgTxt(String suggestionDrsgTxt) {
        this.suggestionDrsgTxt = suggestionDrsgTxt;
    }

    public String getSuggestionFluBrf() {
        return suggestionFluBrf;
    }

    public void setSuggestionFluBrf(String suggestionFluBrf) {
        this.suggestionFluBrf = suggestionFluBrf;
    }

    public String getSuggestionFluTxt() {
        return suggestionFluTxt;
    }

    public void setSuggestionFluTxt(String suggestionFluTxt) {
        this.suggestionFluTxt = suggestionFluTxt;
    }

    public String getSuggestionSportBrf() {
        return suggestionSportBrf;
    }

    public void setSuggestionSportBrf(String suggestionSportBrf) {
        this.suggestionSportBrf = suggestionSportBrf;
    }

    public String getSuggestionSportTxt() {
        return suggestionSportTxt;
    }

    public void setSuggestionSportTxt(String suggestionSportTxt) {
        this.suggestionSportTxt = suggestionSportTxt;
    }

    public String getSuggestionTravBrf() {
        return suggestionTravBrf;
    }

    public void setSuggestionTravBrf(String suggestionTravBrf) {
        this.suggestionTravBrf = suggestionTravBrf;
    }

    public String getSuggestionTravTxt() {
        return suggestionTravTxt;
    }

    public void setSuggestionTravTxt(String suggestionTravTxt) {
        this.suggestionTravTxt = suggestionTravTxt;
    }

    public String getSuggestionUvBrf() {
        return suggestionUvBrf;
    }

    public void setSuggestionUvBrf(String suggestionUvBrf) {
        this.suggestionUvBrf = suggestionUvBrf;
    }

    public String getSuggestionUvTxt() {
        return suggestionUvTxt;
    }

    public void setSuggestionUvTxt(String suggestionUvTxt) {
        this.suggestionUvTxt = suggestionUvTxt;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
