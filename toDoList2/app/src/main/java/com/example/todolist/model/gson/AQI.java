package com.example.todolist.model.gson;

/**
 * Created by ASUS on 2017/12/18.
 */

public class AQI {
    public AQIcity city;
    public class AQIcity{
        public String aqi;
        public String pm25;
    }
}
