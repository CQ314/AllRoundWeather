package com.qiang.app.allroundweather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.qiang.app.allroundweather.R;
import com.qiang.app.allroundweather.adapter.WeatherInfoAdapter;
import com.qiang.app.allroundweather.model.City;
import com.qiang.app.allroundweather.model.County;
import com.qiang.app.allroundweather.model.WeatherDB;
import com.qiang.app.allroundweather.model.WeatherInfo;
import com.qiang.app.allroundweather.service.AutoUpdateService;

import java.util.ArrayList;

public class CityListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int REQUEST_CITY = 1;
    public static final int REQUEST_WEATHER = 2;

    private ListView weatherInfoListView;

    public static ArrayList<WeatherInfo> weatherInfoList= new ArrayList<WeatherInfo>();
    private WeatherInfoAdapter weatherInfoAdapter;// = new WeatherInfoAdapter(CityListActivity.this,R.layout.list_city_weather,weatherInfoList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weatherInfoListView = (ListView)findViewById(R.id.city_listView);
        weatherInfoAdapter = new WeatherInfoAdapter(CityListActivity.this,R.layout.list_city_weather,weatherInfoList);
        weatherInfoListView.setAdapter(weatherInfoAdapter);
        weatherInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CityListActivity.this, TabbedWeatherActivity.class);
                intent.putExtra("county_name", weatherInfoList.get(position).getCityName());
                //Toast.makeText(CityListActivity.this,countyName ,Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, REQUEST_WEATHER);
            }
        });
        weatherInfoList.clear();
        weatherInfoList.addAll(WeatherDB.getInstance(CityListActivity.this).loadWeatherInfos());
        weatherInfoAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityListActivity.this, ChooseAreaActivity.class);
                intent.putExtra("ChooseArea", true);
                startActivityForResult(intent, REQUEST_CITY);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.city_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_quit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(CityListActivity.this, ChooseAreaActivity.class);
            intent.putExtra("ChooseArea", true);
            startActivityForResult(intent, REQUEST_CITY);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
        switch (requestcode) {
            case REQUEST_CITY:
                if (resultCode == RESULT_OK) {
                    String countyName = data.getStringExtra("county_name");
                    //Log.d("CityListActivity", countyName);
                    WeatherInfo weatherInfo = new WeatherInfo(countyName);
                    WeatherDB.getInstance(CityListActivity.this).saveWeatherInfo(weatherInfo);
                    weatherInfoList.add(weatherInfo);
                    //weatherInfoList.clear();
                    //weatherInfoList.addAll(WeatherDB.getInstance(CityListActivity.this).loadWeatherInfos());
                    weatherInfoAdapter.notifyDataSetChanged();

                    Intent intent = new Intent(CityListActivity.this, TabbedWeatherActivity.class);
                    intent.putExtra("county_name", countyName);
                    Toast.makeText(CityListActivity.this,countyName ,Toast.LENGTH_SHORT).show();
                    startActivityForResult(intent, REQUEST_WEATHER);
                }else {
                    Toast.makeText(CityListActivity.this,"没有更新" ,Toast.LENGTH_SHORT).show();
                }break;
            case REQUEST_WEATHER:
                if (resultCode == RESULT_OK) {
                    weatherInfoList.clear();
                    weatherInfoList.addAll(WeatherDB.getInstance(CityListActivity.this).loadWeatherInfos());
                    weatherInfoAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(this, AutoUpdateService.class);
                    startService(intent);
                }else {
                    Toast.makeText(CityListActivity.this,"没有更新" ,Toast.LENGTH_SHORT).show();
                }break;
            default:{

            }
        }
    }
}
