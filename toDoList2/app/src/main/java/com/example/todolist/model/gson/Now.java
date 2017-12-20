package com.example.todolist.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 2017/12/18.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
