package com.hello.first;

import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

//纯Activity页面
public abstract class BaseActivity extends AbstractActivity {

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
	}

	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(getClass().getSimpleName());
		MobclickAgent.onPause(this);
	}

	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(getClass().getSimpleName());
		MobclickAgent.onResume(this);
	}
}