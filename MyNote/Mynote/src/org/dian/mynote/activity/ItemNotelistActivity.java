package org.dian.mynote.activity;


import org.dian.mynote.R;
import org.dian.mynote.date.DbHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ItemNotelistActivity extends Activity {
	
	private EditText mEdtTitle ;
	private EditText mEdtContent;
	private Button mBtnSave;
	private Button mBtnDelete;
	
	private String mOrgTitle;		// 原标题
	private String mOrgContent;		// 原内容
	private String mId;				// 在数据库中的唯一id
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itemnote);
		
		mEdtTitle = (EditText) findViewById(R.id.itemnote_title_edit);
		mEdtContent = (EditText) findViewById(R.id.itemnote_content_edit);
		
		mBtnSave = (Button) findViewById(R.id.itemnote_save);
		
		mBtnDelete = (Button) findViewById(R.id.itemnote_delete);
		mBtnSave.setOnClickListener(mOnClickListener);
		mBtnDelete.setOnClickListener(mOnClickListener);
		
		Intent intent = getIntent();
		// 如果是新建笔记，title，content，id均为null
		mOrgTitle = intent.getStringExtra(NotelistActivity.sTITLE);
		// 只有数据库储存方式才传来的值，内容和id
		mOrgContent = intent.getStringExtra(NotelistActivity.sCONTENT);
		mId = intent.getStringExtra(NotelistActivity.sID);	
		mOrgContent = intent.getStringExtra(NotelistActivity.sCONTENT);
		
		// 如果title和content不为为空，将其显示在控件中
				if(!TextUtils.isEmpty(mOrgTitle)){
					mEdtTitle.setText(mOrgTitle);
				}
				if(!TextUtils.isEmpty(mOrgContent)){
					mEdtContent.setText(mOrgContent);
				}
	}
	
private View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.itemnote_save:
				if( saveNote() ){
					Toast.makeText(ItemNotelistActivity.this, 		// 提示保存成功
							getString(R.string.hint_save_succeed), Toast.LENGTH_SHORT).show();
					finish();									// 关闭当前Activity
				} else {
					Toast.makeText(ItemNotelistActivity.this, 		// 提示保存失败
							getString(R.string.hint_save_fail), Toast.LENGTH_SHORT).show();
				}
				break;
				
			case R.id.itemnote_delete:
				if( ! deleteNote() ){
					Toast.makeText(ItemNotelistActivity.this, 		// 提示删除失败
							getString(R.string.hint_delete_fail), Toast.LENGTH_SHORT).show();
				}
				finish();
				break;
			default:
				break;
			}
		}
	};
	
	private boolean saveNote() {
		boolean isSucceed = true;
		String title = mEdtTitle.getText()+"";
		String content = mEdtContent.getText()+"";
		if(TextUtils.isEmpty(title)) {
			Toast.makeText(ItemNotelistActivity.this, 
					getString(R.string.hint_save_empty), Toast.LENGTH_SHORT).show();
			return false;
		}
	DbHelper dbHelper = new DbHelper(ItemNotelistActivity.this);
	if(TextUtils.isEmpty(mId)) {		// 判断是插入，还是编辑笔记
		if( dbHelper.insertNote(title, content) == -1) {
			isSucceed = false;
		}
	}else{
		if( dbHelper.updateNote(mId, title, content) < 1) {
			isSucceed = false;
		}
	}

	return isSucceed;
	}
	
	private boolean deleteNote() {
		boolean isSucceed = true;
			DbHelper dbHelper = new DbHelper(ItemNotelistActivity.this);
			if( ! TextUtils.isEmpty(mId) ){
				isSucceed = dbHelper.deleteNote(mId);
			} 
		return isSucceed;
	}
	
}
