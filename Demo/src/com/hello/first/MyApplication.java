package com.hello.first;

import android.app.Application;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		int a = Constants.MSG_RESULT;
		System.out.println("a = " + a);
		Log.i("result", "app");
		CrashReport.initCrashReport(getApplicationContext(), "0e534dcaf1", BuildConfig.DEBUG);
		MobclickAgent.openActivityDurationTrack(false);
	}
}