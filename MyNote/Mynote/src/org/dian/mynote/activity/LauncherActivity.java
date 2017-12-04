package org.dian.mynote.activity;

import org.dian.mynote.R;
import org.dian.mynote.R.drawable;
import org.dian.mynote.R.id;
import org.dian.mynote.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ImageView;

public class LauncherActivity extends Activity {

	private ImageView myImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		init();
		myImageView = (ImageView)findViewById(R.id.images);
		Resources r = getResources();
		Drawable d = r.getDrawable(R.drawable.images);
		myImageView.setImageDrawable(d);
	}
	private Button mInBtn;
	private Button mOutBtn;
	private void init(){
		
		mInBtn = (Button)
				findViewById(R.id.note_in);
		mInBtn.setOnClickListener(mOnClickListener);
		mOutBtn = (Button)
				findViewById(R.id.note_out);
		mOutBtn.setOnClickListener(mOnClickListener);
	}
	private OnClickListener mOnClickListener = new OnClickListener(){
		
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.note_in:	
			    Intent intent = new Intent();
				intent.setClass(LauncherActivity.this,NotelistActivity.class);
				startActivityForResult(intent,0);
				break;
			default:
				break;
			case R.id.note_out:
				System.exit(0);
			
			// TODO Auto-generated method stub	
		    }
		}
		
	};

}
