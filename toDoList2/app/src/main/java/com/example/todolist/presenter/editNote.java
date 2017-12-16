package com.example.todolist.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.todolist.R;
import com.example.todolist.model.note;
import com.example.todolist.view.MainActivity;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASUS on 2017/12/15.
 */

public class editNote extends AppCompatActivity{
    private String content;
    private String date;
    private String grade;
    private String startTime;
    private String endTime;
    private Button save;
    private Button delete;
    private Button alarm;
    private TextView tv_date;
    private EditText et_content;
    private EditText et_grade;
    private EditText et_startTime;
    private EditText et_endTime;
    public int enter_state = 0;//用来区分是新建一个note还是更改原来的note
    public String last_content;//用来获取edittext内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        Toolbar toolbar2=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        initView();
    }

    private void initView() {
        tv_date = (TextView) findViewById(R.id.tv_date);
        et_content = (EditText) findViewById(R.id.et_content);
        et_grade = (EditText) findViewById(R.id.et_grade);
        et_startTime = (EditText) findViewById(R.id.et_startTime);
        et_endTime = (EditText) findViewById(R.id.et_endTime);
        Bundle myBundle = this.getIntent().getExtras();
        enter_state = myBundle.getInt("enter_state");
        /*last_content = myBundle.getString("info1");
        date=myBundle.getString("info2");
        grade=myBundle.getString("info3");
        startTime=myBundle.getString("info4");
        endTime=myBundle.getString("info5");

        note notes = (note) DataSupport.findAll(note.class);
        String content2=notes.getContent();
        String startTime2=notes.getStartTime();
        String endTime2=notes.getEndTime();
        et_content.setText(content2);
        et_startTime.setText(startTime2);
        et_endTime.setText(endTime2);
        if (enter_state == 0) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateString = sdf.format(date);
            tv_date.setText(dateString);
        }
        else {
            String date2=notes.getDate();
            tv_date.setText(date2);
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_save:
                String content = et_content.getText().toString();
                String grade = et_grade.getText().toString();
                String startTime = et_startTime.getText().toString();
                String endTime = et_endTime.getText().toString();
                if (enter_state == 0) {
                    if (!content.equals("")) {
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        String dateString = sdf.format(date);
                        tv_date.setText(dateString);
                        note note = new note();
                        note.setContent(content);
                        note.setDate(dateString);
                        note.setGrade(grade);
                        note.setStartTime(startTime);
                        note.setEndTime(endTime);
                        note.save();
                        Toast.makeText(editNote.this, "保存成功", Toast.LENGTH_SHORT).show();
                        Intent intent4=new Intent(editNote.this, MainActivity.class);
                        startActivity(intent4);
                    } else {
                        Toast.makeText(editNote.this, "请输入你的内容！", Toast.LENGTH_SHORT).show();
                    }
                }
                //查看并修改当前备忘录
                else {
                    note notes = (note) DataSupport.findAll(note.class);
                    String content2=notes.getContent();
                    String date2=notes.getDate();
                    String startTime2=notes.getStartTime();
                    String endTime2=notes.getEndTime();
                    et_content.setText(content2);
                    tv_date.setText(date2);
                    et_startTime.setText(startTime2);
                    et_endTime.setText(endTime2);
                }
                break;
            case (R.id.btn_delete):
                DataSupport.deleteAll(note.class,null,null);
                break;
            case (R.id.btn_alarm):
                Intent intent2=new Intent(editNote.this,AlarmActivity.class);
                startActivity(intent2);

        }

        return true;
    }


}
