package com.conch.content.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	static MyDatabaseHelper dbHelper;
	static Button insert = null;
	static Button search = null;
    static ContentResolver contentResolver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contentResolver=getContentResolver();
		contentResolver.registerContentObserver(Words.Word.DICT_CONTENT_URI, true, new DataObserver(new Handler()));
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			// 创建MyDatabaseHelper对象，指定数据库版本为1，此处使用相对路径即可，
			// 数据库文件自动会保存在程序的数据文件夹的databases目录下。
			dbHelper = new MyDatabaseHelper(getActivity(), "myDict.db3", 1);
			insert = (Button) rootView.findViewById(R.id.insert);
			search = (Button) rootView.findViewById(R.id.search);
			insert.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View source) {
//					// 获取用户输入
//					String word = ((EditText) rootView.findViewById(R.id.word)).getText().toString();
//					String detail = ((EditText) rootView.findViewById(R.id.detail)).getText().toString();
//					// 插入生词记录
//					insertData(dbHelper.getReadableDatabase(), word, detail);
//					// 显示提示信息
//					Toast.makeText(getActivity(), "添加生词成功！", 8000).show();
					//第二种方法采用ContentResover()
					ContentValues values = new ContentValues();
					values.put(Words.Word.WORD, "1111");
					values.put(Words.Word.DETAIL, "2222");
					contentResolver.insert(Words.Word.DICT_CONTENT_URI, values);
				}
			});

			search.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View source) {
					// 获取用户输入
					String key = ((EditText) rootView.findViewById(R.id.key)).getText().toString();
					// 执行查询
					Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from dict where word like ? or detail like ?", new String[] { "%" + key + "%", "%" + key + "%" });

					// 创建一个Bundle对象
					Bundle data = new Bundle();
					data.putSerializable("data", converCursorToList(cursor));
					// 创建一个Intent
					Intent intent = new Intent(getActivity(), ResultActivity.class);
					intent.putExtras(data);
					// 启动Activity
					startActivity(intent);
				}
			});
			return rootView;
		}

		protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
			ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
			// 遍历Cursor结果集
			while (cursor.moveToNext()) {
				// 将结果集中的数据存入ArrayList中
				Map<String, String> map = new HashMap<String, String>();
				// 取出查询记录中第2列、第3列的值
				map.put("word", cursor.getString(1));
				map.put("detail", cursor.getString(2));
				result.add(map);
			}
			return result;
		}

		private void insertData(SQLiteDatabase db, String word, String detail) {
			// 执行插入语句
			db.execSQL("insert into dict values(null , ? , ?)", new String[] { word, detail });
		}
	}

}
