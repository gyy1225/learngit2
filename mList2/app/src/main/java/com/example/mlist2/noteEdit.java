package com.example.mlist2;

/**
 * Created by ASUS on 2017/11/20.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class noteEdit extends Activity implements OnClickListener {
    private TextView tv_date;
    private EditText et_content;
    private Button btn_ok;
    private Button btn_delete;
    private Button btn_alarm;
    private Button btn_moreSet;
    private EditText et_grade;
    private EditText et_startTime;
    private EditText et_endTime;
    private NoteDateBaseHelper DBHelper;
    public int enter_state = 0;//用来区分是新建一个note还是更改原来的note
    public String last_content;//用来获取edittext内容
    public String grade;
    public String startTime;
    public String endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        InitView();
    }

    private void InitView() {
        tv_date = (TextView) findViewById(R.id.tv_date);
        et_content = (EditText) findViewById(R.id.et_content);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_alarm=(Button)findViewById(R.id.btn_alarm) ;
        btn_moreSet=(Button)findViewById(R.id.btn_moreSet);
        et_grade=(EditText)findViewById(R.id.et_grade);
        et_startTime=(EditText)findViewById(R.id.et_startTime);
        et_endTime=(EditText)findViewById(R.id.et_endTime);
        DBHelper = new NoteDateBaseHelper(this);

        //获取此时时刻时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = sdf.format(date);
        tv_date.setText(dateString);



        //接收内容和id
        Bundle myBundle = this.getIntent().getExtras();
        last_content = myBundle.getString("info");
        enter_state = myBundle.getInt("enter_state");
        /*grade="1";
        startTime=dateString;
        endTime=dateString;*/
            /*grade=myBundle.getString("grade");
        startTime=myBundle.getString("startTime");
        endTime=myBundle.getString("endTime");*/

       /* et_grade.setText(grade);
        et_startTime.setText(startTime);
        et_endTime.setText(endTime);*/
        et_content.setText(last_content);
        btn_alarm.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_moreSet.setOnClickListener(this);
    }

    public String getNote(){
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        // 获取edittext内容
        String content = et_content.getText().toString();
        return content;
    }
    public String getDate(){
        String date=tv_date.toString();
        return date;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                SQLiteDatabase db = DBHelper.getWritableDatabase();
                // 获取edittext内容
                String content = et_content.getText().toString();
                String grade=et_grade.getText().toString();
                String startTime=et_startTime.getText().toString();
                String endTime=et_endTime.getText().toString();

                // 添加一个新的日志
                if (enter_state == 0) {
                    if (!content.equals("")) {
                        //获取此时时刻时间
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        String dateString = sdf.format(date);

                        //向数据库添加信息
                        ContentValues values = new ContentValues();
                        values.put("content", content);
                        values.put("date", dateString);
                        values.put("grade",grade);
                        values.put("startTime",startTime);
                        values.put("endTime",endTime);
                        db.insert("note", null, values);
                        finish();
                    } else {
                        Toast.makeText(noteEdit.this, "请输入你的内容！", Toast.LENGTH_SHORT).show();
                    }
                }
                // 查看并修改一个已有的日志
                else {
                   Cursor cursor=db.query("note",null,null,null,null,null,null);
                   String grade2=cursor.getString(cursor.getColumnIndex("grade"));
                   String startTime2=cursor.getString(cursor.getColumnIndex("startTimr"));
                   String endTime2=cursor.getString(cursor.getColumnIndex("endTime"));
                   et_grade.setText(grade2);
                   et_startTime.setText(startTime2);
                   et_endTime.setText(endTime2);
                    ContentValues values = new ContentValues();
                    values.put("content", content);
                    values.put("grade",grade);
                    values.put("startTime",startTime);
                    values.put("endTime",endTime);
                    db.update("note", values, "content = ?", new String[]{last_content});
                    finish();
                }
                break;
            case R.id.btn_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("删除该日志");
                builder.setMessage("确认删除吗？");
                final String content2 = et_content.getText().toString();
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除该日志
                        SQLiteDatabase db=DBHelper.getWritableDatabase();
                        db.delete("note", "content = ?", new String[]{content2});

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
                finish();
                break;
            case R.id.btn_alarm:
                Intent intent=new Intent(noteEdit.this,AlarmActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_moreSet:
                Intent intent5=new Intent(noteEdit.this,moreSet.class);
                startActivity(intent5);

        }
    }
}
