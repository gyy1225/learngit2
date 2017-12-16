package com.example.todolist.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todolist.R;
import com.example.todolist.model.note;
import com.example.todolist.view.MainActivity;

import java.util.List;

import static org.litepal.LitePalApplication.getContext;

/**
 * Created by ASUS on 2017/12/15.
 */

public class noteAdapter extends RecyclerView.Adapter<noteAdapter.ViewHolder>{
    private Context mContext;
    private List<note> mNoteList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView content;
        TextView date;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            content=(TextView)view.findViewById(R.id.tv_content2);
            date=(TextView)view.findViewById(R.id.tv_date2);

        }
    }
    public noteAdapter(List<note> noteList){
        mNoteList=noteList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view=LayoutInflater.from(mContext).inflate(R.layout.note_item,
                parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                note note=mNoteList.get(position);
                Intent intent5=new Intent(mContext,editNote.class);
                Bundle bundle = new Bundle();
                /*bundle.putString("info1",note.getContent());
                bundle.putString("info2",note.getDate());
                bundle.putString("info3",note.getGrade());
                bundle.putString("info4",note.getStartTime());
                bundle.putString("info5",note.getEndTime());*/
                bundle.putInt("enter_state", 1);
                intent5.putExtras(bundle);
                mContext.startActivity(intent5);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(noteAdapter.ViewHolder holder, int position) {
        note note=mNoteList.get(position);
        holder.content.setText(note.getContent());
        holder.date.setText(note.getDate());

    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
