package com.example.todolist.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.todolist.R;

/**
 * Created by ASUS on 2017/12/18.
 */

public class weatherShow extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.nav_header);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getString("weather",null)!=null){
            Intent intent9=new Intent(this,weatherActivity.class);
            startActivity(intent9);
            finish();
        }
        else {
            setContentView(R.layout.citys);
        }

    }
}
