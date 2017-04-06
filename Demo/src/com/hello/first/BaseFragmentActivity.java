package com.hello.first;

import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;

//包含Fragment的Activity
public abstract class BaseFragmentActivity extends AbstractActivity {
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
	}

	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
}