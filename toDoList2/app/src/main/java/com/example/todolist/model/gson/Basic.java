package com.example.todolist.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 2017/12/18.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
