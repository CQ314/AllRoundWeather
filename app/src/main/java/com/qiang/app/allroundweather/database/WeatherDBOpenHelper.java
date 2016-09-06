package com.qiang.app.allroundweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 强 on 2016/8/29 0029.
 */
public class WeatherDBOpenHelper extends SQLiteOpenHelper {
    /*build database tables*/
    public static final String CREATE_WEATHER = "create table Weather ("
            + "id integer primary key autoincrement, "
            +"city_name text, "
            +"weather_info text)";


    public WeatherDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_WEATHER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){

    }
}
