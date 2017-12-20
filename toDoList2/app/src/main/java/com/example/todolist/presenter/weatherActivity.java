package com.example.todolist.presenter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.model.gson.Forecast;
import com.example.todolist.model.gson.Weather;
import com.example.todolist.presenter.util.HttpUtil;
import com.example.todolist.presenter.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2017/12/16.
 */

public class weatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private TextView aqiText;
    private LinearLayout forecastLayout;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
//初始化
        weatherLayout=(ScrollView)findViewById(R.id.weather_layout);
        titleCity=(TextView)findViewById(R.id.title_city);
        titleUpdateTime=(TextView)findViewById(R.id.title_update_time);
        degreeText=(TextView)findViewById(R.id.degree_text);
        weatherInfoText=(TextView)findViewById(R.id.weather_info_text);
        aqiText=(TextView)findViewById(R.id.aqi_text);
        forecastLayout=(LinearLayout)findViewById(R.id.forecast_layout);
        pm25Text=(TextView)findViewById(R.id.pm25_text);
        comfortText=(TextView)findViewById(R.id.comfort_text);
        carWashText=(TextView)findViewById(R.id.carwash_text);
        sportText=(TextView)findViewById(R.id.sport_text);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString=prefs.getString("weather",null);
        if(weatherString!=null){
            Weather weather= Utility.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        }else {
            String weatherId=getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requertWeather(weatherId);
        }
    }

//根据天气id请求天气信息
    public void requertWeather(final String weatherId){
        String weatherUrl="http://guolin.tech/api/weather?cityid=" +
                weatherId + "&key=e6179e28c4624be0804114fa8d9841a6";
                HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(weatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();;
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String respondeText=response.body().string();
                        final Weather weather=Utility.handleWeatherResponse(respondeText);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(weather!=null&&"ok".equals(weather.status)){
                                    SharedPreferences.Editor editor=PreferenceManager.
                                            getDefaultSharedPreferences(weatherActivity.this).edit();
                                    editor.putString("weather",respondeText);
                                    editor.apply();
                                    showWeatherInfo(weather);
                                }else {
                                    Toast.makeText(weatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();;
                                }
                            }
                        });
                    }
                });
    }
    private void showWeatherInfo(Weather weather){
        String cityName=weather.basic.cityName;
        String updateTime=weather.basic.update.updateTime.split(" ")[1];
        String degree=weather.now.temperature + "℃";
        String weatherInfo=weather.now.more.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast forecast:weather.forecastList){
            View view= LayoutInflater.from(this).inflate(R.layout.forecast_item,
                    forecastLayout,false);
            TextView dateText=(TextView)findViewById(R.id.date_text);
            TextView infoText=(TextView)findViewById(R.id.info_text);
            TextView maxText=(TextView)findViewById(R.id.max_text);
            TextView minText=(TextView)findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperature.max);
            minText.setText(forecast.temperature.min);
            forecastLayout.addView(view);
        }
        if(weather.aqi!=null){
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }
        String comfort="舒适度"+ weather.suggestion.comfort.info;
        String carWash="洗车指数"+ weather.suggestion.carWash.info;
        String sport="运动建议"+weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
    }


}
