package org.dian.mynote.activity;


import org.dian.mynote.R;
import org.dian.mynote.date.DbHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;



public class NotelistActivity extends Activity {
	
	static final public String sMODE = "mode";
	static final public String sTITLE = "title";
	static final public String sCONTENT = "content";
	static final public String sID = "_id";

	private Button mBtnAdd;
	private ListView mListView;
	
	private SimpleCursorAdapter mAdapter_DB = null;		// 数据库和listview的桥梁
	private Cursor mCursor = null;						// 操作数据库的游标

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notelist);
		
		
		mBtnAdd = (Button) this.findViewById(R.id.notelist_addbtn);
		mListView = (ListView) this.findViewById(R.id.notelist_listview);
		// 设置监听器
		mBtnAdd.setOnClickListener(mOnClickListener);
		mListView.setOnItemClickListener(mOnItemClickListener);
	}

	private View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.notelist_addbtn) {
				// 点击添加，跳转到编辑界面
				Intent intent = new Intent();
				intent.setClass(NotelistActivity.this, ItemNotelistActivity.class);
				startActivity(intent);
			}
		}
	};
	
	private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			if(parent.getId() == R.id.notelist_listview) {
				Intent intent = new Intent();
				intent.setClass(NotelistActivity.this, ItemNotelistActivity.class);
	
					mCursor.moveToPosition(position);                        //将cursor指向position
					/*传递记事的id，标题，内容,这些内容从数据库中相应的字段中取得*/
					intent.putExtra(DbHelper.NOTE_ID, mCursor.getString(mCursor.getColumnIndexOrThrow(DbHelper.NOTE_ID)));
					intent.putExtra(DbHelper.NOTE_TITLE, mCursor.getString(mCursor.getColumnIndexOrThrow(DbHelper.NOTE_TITLE)));
					intent.putExtra(DbHelper.NOTE_CONTENT, mCursor.getString(mCursor.getColumnIndexOrThrow(DbHelper.NOTE_CONTENT)));
					startActivity(intent);
					
			}
		}
	};
	@Override
	protected void onResume() {
		super.onResume();
	
			DbHelper db = new DbHelper(this);			//获得数据库对象
			mCursor = db.selectNotes();					//取得数据库中的记事
			if(mCursor.moveToFirst()){
				startManagingCursor(mCursor);			//让Activity来管理cursor
				String[] from = {DbHelper.NOTE_TITLE};	//数据库中的列名
				int[] to = {R.id.item_tv};				//数据库中列的内容绑定的视图
				// SimpleCursorAdapter将数据库中的值绑定到listview
				// 参数分别为上下文，listview的布局，游标，数据库中列的值，值所要绑定的视图
				mAdapter_DB = new SimpleCursorAdapter(NotelistActivity.this, 
					R.layout.listitem, mCursor, from, to);
				mListView.setAdapter(mAdapter_DB);      // listview 绑定适配器
			}
			
		}
}
	


