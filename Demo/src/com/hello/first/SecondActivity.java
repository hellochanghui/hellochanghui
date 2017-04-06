package com.hello.first;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_second);

		TextView tv_back = (TextView) findViewById(R.id.tv_back);

		tv_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new FirstEvent("tv_back btn clicked"));
			}
		});
	}
}
