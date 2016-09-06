package com.qiang.app.allroundweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.qiang.app.allroundweather.database.AreaDBOpenHelper;
import com.qiang.app.allroundweather.database.WeatherDBOpenHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 强 on 2016/8/30 0030.
 */
@SuppressWarnings("serial")
public class WeatherDB implements Serializable {

    /*Data base name */
    public static final String DB_NAME = "weather_database";
    public static final int VERSION = 1;
    private static WeatherDB weatherDB;
    private SQLiteDatabase mSQLiteDatabase;

    private WeatherDB(Context context) {
        WeatherDBOpenHelper dbHelper = new  WeatherDBOpenHelper(context, DB_NAME, null, VERSION);
        mSQLiteDatabase = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    public void saveWeatherInfo(WeatherInfo weatherInfo) {
        if (weatherInfo != null) {
            //WeatherDBOpenHelper dbHelper = new  WeatherDBOpenHelper(context, DB_NAME, null, VERSION);
            //mSQLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("city_name", weatherInfo.getCityName());
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
                objectOutputStream.writeObject(weatherInfo);
                objectOutputStream.flush();
                byte data[] = arrayOutputStream.toByteArray();
                objectOutputStream.close();
                arrayOutputStream.close();
                values.put("weather_info", data);
            } catch (InvalidClassException e) {
                Log.e("weatherSerial","Serial error 1");
                e.printStackTrace();
            }catch (NotSerializableException e) {
                Log.e("weatherSerial","Serial error 2");
                e.printStackTrace();
            }catch (IOException e) {
                Log.e("weatherSerial","Serial error 3");
                e.printStackTrace();
            }catch (Exception e) {
                Log.e("weatherSerial","Serial error 4");
                e.printStackTrace();
            }
            //mSQLiteDatabase.update("Weather", values, "city_name=?", new String[]{weatherInfo.getCityName()});
            mSQLiteDatabase.delete("Weather", "city_name=?", new String[]{weatherInfo.getCityName()});
            mSQLiteDatabase.insert("Weather", null, values);
        }
    }

    public WeatherInfo loadCityWeatherInfo(String cityName) {
        Cursor cursor = mSQLiteDatabase.query("Weather",new String[]{"weather_info"}, "city_name=?", new String[]{cityName}, null, null,null);
        WeatherInfo weatherInfo = new WeatherInfo();
        if (cursor.moveToFirst()) {
            do {
                byte data[] = cursor.getBlob(cursor.getColumnIndex("weather_info"));
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                    weatherInfo = (WeatherInfo) inputStream.readObject();
                    inputStream.close();
                    arrayInputStream.close();
                } catch (InvalidClassException e) {
                    Log.e("weatherSerial","Serial error 1");
                    e.printStackTrace();
                }catch (NotSerializableException e) {
                    Log.e("weatherSerial","Serial error 2");
                    e.printStackTrace();
                }catch (IOException e) {
                    Log.e("weatherSerial","Serial error 3");
                    e.printStackTrace();
                }catch (Exception e) {
                    Log.e("weatherSerial","Serial error 4");
                    e.printStackTrace();
                }
            }while (cursor.moveToNext());
        }
        return weatherInfo;
    }

    public ArrayList<WeatherInfo> loadWeatherInfos() {
        Cursor cursor = mSQLiteDatabase.query("Weather",null, null, null, null, null,null);
        ArrayList<WeatherInfo> weatherInfoList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                byte data[] = cursor.getBlob(cursor.getColumnIndex("weather_info"));
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
                try {
                    WeatherInfo weatherInfo = new WeatherInfo();
                    ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                    weatherInfo = (WeatherInfo) inputStream.readObject();
                    weatherInfoList.add(weatherInfo);
                    inputStream.close();
                    arrayInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }while (cursor.moveToNext());
        }
        return weatherInfoList;
    }
}
