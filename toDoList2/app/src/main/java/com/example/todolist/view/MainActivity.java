package com.example.todolist.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.model.note;
import com.example.todolist.presenter.editNote;
import com.example.todolist.presenter.noteAdapter;
import com.example.todolist.presenter.weatherActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class MainActivity extends AppCompatActivity {

    private List<note>noteList=new ArrayList<>();
    private noteAdapter adapter;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navView=(NavigationView)findViewById(R.id.nav_View);

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,editNote.class);
                Bundle bundle = new Bundle();
                bundle.putString("info1", "");
                bundle.putString("info2", "");
                bundle.putString("info3", "");
                bundle.putString("info4", "");
                bundle.putString("info5", "");
                bundle.putInt("enter_state", 0);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
        initNotes();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new noteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        navView.setCheckedItem(R.id.nowWeather);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.moreWeather:
                        Intent intent6=new Intent(MainActivity.this,weatherActivity.class);
                        startActivity(intent6);
                }
                return true;
            }
        });
    }

    private void initNotes() {
        noteList.clear();
        noteList= DataSupport.findAll(note.class);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager=new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(layoutManager);
            switch (item.getItemId()){
                case R.id.byEditTime:
                    Toast.makeText(this,"按编辑时间排序",Toast.LENGTH_SHORT).show();
                    noteList.clear();
                    noteList=DataSupport.order("date desc").find(note.class);
                    adapter=new noteAdapter(noteList);
                    recyclerView.setAdapter(adapter);

                    break;
            case R.id.byGrade:
                Toast.makeText(this,"按优先级排序",Toast.LENGTH_SHORT).show();
                noteList.clear();
                noteList=DataSupport.order("grade desc").find(note.class);
                adapter=new noteAdapter(noteList);
                recyclerView.setAdapter(adapter);
                break;
            case R.id.byStartTime:
                Toast.makeText(this,"按事件开始时间排序",Toast.LENGTH_SHORT).show();
                noteList.clear();
                noteList=DataSupport.order("startTime desc").find(note.class);
                adapter=new noteAdapter(noteList);
                recyclerView.setAdapter(adapter);
                break;
            case R.id.byEndTime:
                Toast.makeText(this,"按事件结束时间排序",Toast.LENGTH_SHORT).show();
                noteList.clear();
                noteList=DataSupport.order("endTime desc").find(note.class);
                adapter=new noteAdapter(noteList);
                recyclerView.setAdapter(adapter);
                break;

                case R.id.weather:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
        }
        return true;
    }

}
