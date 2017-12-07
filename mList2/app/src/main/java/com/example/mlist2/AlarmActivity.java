package com.example.mlist2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class AlarmActivity extends Activity {
    public Button setAlarm;
    public Button deleteAlarm;
    public EditText editAlarm;
    public Button saveAlarm;
    Calendar calendar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        calendar=Calendar.getInstance();
        editAlarm=(EditText) findViewById(R.id.et_alarm);
        setAlarm=(Button)findViewById(R.id.btn_setAlarm);
       deleteAlarm=(Button)findViewById(R.id.btn_delAlarm);
       saveAlarm=(Button)findViewById(R.id.btn_saveAlarm);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                calendar.setTimeInMillis(System.currentTimeMillis());
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // TODO Auto-generated method stub
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10 * 1000),
                                (24 * 60 * 60 * 1000), pendingIntent);
                        String tmps = "设置闹钟时间为" + format(hourOfDay) + ":" +format(minute);
                        editAlarm.setText(tmps);
                    }
                }, hour, minute, true).show();
            }
        });
        saveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AlarmActivity.this,"闹钟设置成功",
                Toast.LENGTH_SHORT).show();
                Intent intent3= new Intent(AlarmActivity.this, MainActivity.class);
                startActivity(intent3);
            }
        });
        deleteAlarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
                editAlarm.setText("闹铃已取消！");
            }
        });
    }
    private String format(int time){
        String str = "" + time;
        if(str.length() == 1){
            str = "0" + str;
        }
        return str;
    }
}
