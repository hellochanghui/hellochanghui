package com.hello.first;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.hello.first.util.ApiModel;
import com.hello.first.util.ApiModel.Callback;

public abstract class AbstractActivity extends Activity implements Callback {
	public Context context;

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		getWindow().setBackgroundDrawable(null);
		context = this;
		ApiModel.getInstance().setCallback(this);
	}

	@Override
	public void onEnd(String args) {
		Log.i("result", "BaseActivyt onEnd...");
	}

	@Override
	public void onNetworkStart() {
		Log.i("result", "BaseActivyt onNetworkStart...");
	}

	protected boolean onResultIntercept(String msg) {
		if (!TextUtils.isEmpty(msg)) {
			// AppToastUtil.showShort(msg);
			return true;
		}
		return false;
	}
}