package com.qiang.app.allroundweather.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiang.app.allroundweather.R;
import com.qiang.app.allroundweather.adapter.HourlyWeatherAdapter;
import com.qiang.app.allroundweather.model.HourlyWeather;
import com.qiang.app.allroundweather.model.WeatherDB;
import com.qiang.app.allroundweather.model.WeatherInfo;
import com.qiang.app.allroundweather.util.HttpCallbackListener;
import com.qiang.app.allroundweather.util.HttpUtil;
import com.qiang.app.allroundweather.util.Utility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TabbedWeatherActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_weather);

        queryFromServer(getIntent().getStringExtra("county_name"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        final ArrayList<WeatherInfo> weatherInfoList = WeatherDB.getInstance(this).loadWeatherInfos();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), weatherInfoList);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTitle(weatherInfoList.get(position).getCityName());
            }

            @Override
            public void onPageSelected(int position) {
                setTitle(weatherInfoList.get(position).getCityName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void queryFromServer(final String countyName) {
        try {
            String url = "http://apis.baidu.com/heweather/weather/free?city=";
            //String name = new String(countyName.getBytes("UTF-8"), "iso-8859-1");
            HttpUtil.sendHttpRequest(url + countyName, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    //testView.setText(response );
                    Utility.handleWeatherResponse(TabbedWeatherActivity.this, response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //showWeather();
                            Intent intent = new Intent();
                            intent.putExtra("county_name", countyName);
                            setResult(RESULT_OK, intent);
                        }
                    });
                }
                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(TabbedWeatherActivity.this, "同步失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } catch (Exception e) {
            Log.e("TabbedWeatherActivity", "Request from server error");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }if (id == R.id.action_share) {
            return true;
        }if (id == R.id.action_quit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private TextView testView;
        private TextView countyName;
        private TextView nowTmp;
        private TextView nowFl;
        private TextView updateTime;
        private TextView nowPcpn;
        private TextView nowHum;
        private TextView nowCondTxt;
        private TextView nowWind;
        private TextView nowPres;
        private TextView nowVis;
        private TextView aqiCityQlty;
        private TextView aqiCityPm25;
        private ImageView nowCondCode;

        public List<HourlyWeather> hourlyWeatherList = new ArrayList<>();
        private ListView hourlyForecastListview;

        private ProgressDialog progressDialog;
        private String mCountyName;
        private int listIndex;

        private List<WeatherInfo> weatherInfoList ;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            //weatherInfoList.get(sectionNumber);
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabbed_weather, container, false);
            weatherInfoList = WeatherDB.getInstance(rootView.getContext()).loadWeatherInfos();
            testView = (TextView) rootView.findViewById(R.id.testView);
            countyName= (TextView) rootView.findViewById(R.id.cityName);
            nowTmp= (TextView) rootView.findViewById(R.id.nowTmp);
            nowFl= (TextView) rootView.findViewById(R.id.nowFl);
            updateTime= (TextView) rootView.findViewById(R.id.updateTime);
            nowPcpn= (TextView) rootView.findViewById(R.id.nowPcpn);
            nowHum= (TextView) rootView.findViewById(R.id.nowHum);
            nowCondTxt= (TextView) rootView.findViewById(R.id.nowCondTxt);
            nowWind= (TextView) rootView.findViewById(R.id.nowWind);
            nowPres= (TextView) rootView.findViewById(R.id.nowPres);
            nowVis= (TextView)  rootView.findViewById(R.id.nowVis);
            aqiCityQlty= (TextView) rootView.findViewById(R.id.aqiCityQlty);
            aqiCityPm25= (TextView) rootView.findViewById(R.id.aqiCityPm25);
            nowCondCode = (ImageView) rootView.findViewById(R.id.nowCondCode);
            hourlyForecastListview = (ListView) rootView.findViewById(R.id.hourlyForecastListview);
            HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter(rootView.getContext(), R.layout.list_weather_hourly, hourlyWeatherList);
            hourlyForecastListview.setAdapter(hourlyWeatherAdapter);

//            mCountyName = getIntent().getStringExtra("county_name");
            listIndex = getArguments().getInt(ARG_SECTION_NUMBER);
            WeatherInfo weatherInfo = weatherInfoList.get(listIndex);
            //queryFromServer(weatherInfo.getCityName());

            countyName.setText(weatherInfo.getCityName());
            nowTmp.setText("温度:" + weatherInfo.getNowTmp() + "℃");
            nowFl.setText("体感:" + weatherInfo.getNowFl() + "℃");
            updateTime.setText(weatherInfo.getUpdateTime());
            nowPcpn.setText("降雨量:" + weatherInfo.getNowPcpn() + "mm");
            nowHum.setText("湿度:" + weatherInfo.getNowHum() + "%");
            nowCondTxt.setText(weatherInfo.getNowCondTxt());
            nowWind.setText("风力:" + weatherInfo.getNowWindDir() +  weatherInfo.getNowWindSc() + "级");
            nowPres.setText("气压:" + weatherInfo.getNowPres());
            nowVis.setText("能见度:" + weatherInfo.getNowVis() + "km");
            aqiCityQlty.setText("空气质量:" + weatherInfo.getAqiCityQlty());
            aqiCityPm25.setText("PM2.5:" + weatherInfo.getAqiCityPm25() + "ug/m³");
            hourlyWeatherList = weatherInfo.getHourlyForecast();
            Toast.makeText(rootView.getContext(), "已经是最新数据了", Toast.LENGTH_SHORT).show();
            hourlyWeatherAdapter.notifyDataSetChanged();
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<WeatherInfo> weatherInfoArrayList;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<WeatherInfo> list) {
            super(fm);
            weatherInfoArrayList = list;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return weatherInfoArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return weatherInfoArrayList.get(position).getCityName();
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
        }
    }
}
