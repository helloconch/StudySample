package com.conch.content.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;

public class DictProvider extends ContentProvider {
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final String TABLE_NAME = "dict";
	private static final int WORDS = 1;
	private static final int WORD = 2;
	private static final String DATABASENAME = "myDict.db3";
	private MyDatabaseHelper dbOpenHelper;
	static {
		// 为UriMatcher注册两个Uri
		matcher.addURI(Words.AUTHORITY, "words", WORDS);
		matcher.addURI(Words.AUTHORITY, "word/#", WORD);
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		int num = 0;// 所删除的记录数
		switch (matcher.match(uri)) {
		case WORDS:
			num = db.delete(TABLE_NAME, where, whereArgs);
			break;
		case WORD:
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			// 如果原来的where子句存在，拼接where子句
			if (where != null && !"".equals(where)) {
				whereClause = whereClause + " and " + where;
			}
			num = db.delete(TABLE_NAME, whereClause, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}

	// 返回指定Uri参数对应的数据MIME类型
	@Override
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
		case WORDS:
			return "vnd.android.cursor.dir/com.conch.dict";
		case WORD:
			return "vnd.android.cursor.item/com.conch.dict";
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (matcher.match(uri)) {
		case WORDS:
			// 插入数据，返回插入记录ID
			long rowId = db.insert(TABLE_NAME, Words.Word._ID, values);
			// 如果插入成功返回Uri
			if (rowId > 0) {
				// 在已有的Uri的后面追加ID
				Uri wordUri = ContentUris.withAppendedId(uri, rowId);
				// 通知数据已经改变
				getContext().getContentResolver().notifyChange(wordUri, new DataObserver(new Handler()));
				return wordUri;

			}
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	// 第一次调用该DictProvider时，系统先创建DictProvider对象，并回调该方法
	@Override
	public boolean onCreate() {
		dbOpenHelper = new MyDatabaseHelper(getContext(), DATABASENAME, 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String where, String[] whereArgs, String sortOrder) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (matcher.match(uri)) {
		// 如果Uri参数代表操作全部数据项
		case WORDS:
			return db.query(TABLE_NAME, projection, where, whereArgs, null, null, sortOrder);
			// //如果Uri参数代表操作指定数据项
		case WORD:
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			// 如果原来的where子句存在，拼接where子句
			if (where != null && !"".equals(where)) {
				whereClause = whereClause + " and " + where;
			}
			return db.query(TABLE_NAME, projection, whereClause, whereArgs, null, null, sortOrder);
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int num = 0;// 修改的记录数
		switch (matcher.match(uri)) {
		case WORDS:
			num = db.update(TABLE_NAME, values, where, whereArgs);
			break;
		case WORD:
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			// 如果原来的where子句存在，拼接where子句
			if (where != null && !"".equals(where)) {
				whereClause = whereClause + " and " + where;
			}
			num = db.update(TABLE_NAME, values, whereClause, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}

}
