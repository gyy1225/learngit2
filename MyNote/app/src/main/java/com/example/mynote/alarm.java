package com.example.mynote;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ASUS on 2017/12/3.
 */

public class alarm extends Activity implements View.OnClickListener {

    public Button saveAlarm;
    public Button deleteAlarm;
    public EditText editAlarm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        saveAlarm = (Button) findViewById(R.id.btn_saveAlarm);
        deleteAlarm = (Button) findViewById(R.id.btn_delAlarm);
        editAlarm = (EditText) findViewById(R.id.et_alarm);
        saveAlarm.setOnClickListener(this);
        deleteAlarm.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        saveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time=editAlarm.getText().toString();


            }
        });
        }
    }

