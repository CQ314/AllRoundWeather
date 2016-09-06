package com.qiang.app.allroundweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 强 on 2016/8/29 0029.
 */
public class AreaDBOpenHelper extends SQLiteOpenHelper {
    /*build database tables*/
    public static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key autoincrement, "
            +"province_name text, "
            +"province_code text)";

    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement, "
            +"city_name text, "
            +"city_code text, "
            +"province_id integer)";

    public static final String CREATE_COUNTY = "create table County ("
            + "id integer primary key autoincrement, "
            +"county_name text, "
            +"county_code text, "
            +"city_id integer)";

    public AreaDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_PROVINCE);
        database.execSQL(CREATE_CITY);
        database.execSQL(CREATE_COUNTY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){

    }
}
