package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Notification.Builder;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Builder notification = new Builder(this);
                notification.setContentTitle = "Hello!"
                             .setContentText="by gyy"
                             .setWhen(System.currentTimeMillis())
                             .setSmallIcon(R.mipmap.ic_launcher)
                             .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                             .build();
                manager.notify(1, notification);
                break;
            default:
                break;

        }
    }
}

