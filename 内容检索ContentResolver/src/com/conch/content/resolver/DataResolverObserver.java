package com.conch.content.resolver;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

public class DataResolverObserver extends ContentObserver {

	public DataResolverObserver(Handler handler) {
		super(handler);
	}

	@Override
	public void onChange(boolean selfChange, Uri uri) {
		super.onChange(selfChange, uri);
		System.out.println("DataObserver----selfChange:" + selfChange + "----uri:" + uri);
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
		System.out.println("DataObserver---selfChange:" + selfChange);
		System.out.println();
	}

}
