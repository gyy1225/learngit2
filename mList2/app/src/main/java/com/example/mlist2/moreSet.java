package com.example.mlist2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ASUS on 2017/12/4.
 */

public class moreSet extends Activity implements View.OnClickListener{
    public EditText grade;
    public EditText startTime;
    public EditText endTime;
    public Button saveSet;
    public Button delSet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moresettings);
        grade = (EditText) findViewById(R.id.et_grade);
        startTime = (EditText) findViewById(R.id.et_startTime);
        saveSet = (Button) findViewById(R.id.btn_saveSet);
        delSet = (Button) findViewById(R.id.btn_delSet);

        saveSet.setOnClickListener(this);
        delSet.setOnClickListener(this);
        startTime = (EditText) findViewById(R.id.et_endTime);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_saveSet:
                String Grade = grade.getText().toString();
                String StartTime = startTime.getText().toString();
                String EndTime = endTime.getText().toString();
                NoteDateBaseHelper DBHelper = new NoteDateBaseHelper(this);
                SQLiteDatabase db = DBHelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("grade", Grade);
                values.put("startTime", StartTime);
                values.put("endTime", EndTime);
                db.insert("note", null, values);
                finish();
                Toast.makeText(moreSet.this,"设置成功",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delSet:
                Grade = " ";
                StartTime = " ";
                EndTime = " ";
                NoteDateBaseHelper DBHelper2 = new NoteDateBaseHelper(this);
                SQLiteDatabase db2 = DBHelper2.getReadableDatabase();
                values = new ContentValues();
                values.put("grade", Grade);
                values.put("startTime", StartTime);
                values.put("endTime", EndTime);
                db2.insert("note", null, values);
                finish();
                Toast.makeText(moreSet.this,"关闭设置",
                        Toast.LENGTH_SHORT).show();
        }
    }
}