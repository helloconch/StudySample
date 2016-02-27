package com.example.abc;

public class JNIInterface {

	static {
		System.loadLibrary("abc");
	}

	public static native String getAPPUrl();
}
