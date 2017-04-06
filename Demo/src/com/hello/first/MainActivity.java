package com.hello.first;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hello.first.util.ApiModel;

public class MainActivity extends BaseActivity {

	private TextView textView01, textView02, textView03, textView04;

	private Handler.Callback callback = new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == 0x32) {
				if (msg.obj == null) {
					Toast.makeText(context, "msg.obj == null  " + (msg.obj == null), Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
				}

			}
			return true;
		}
	};
	private Handler mHandler = new Handler(callback);

	TextView tv_test, tv_start;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_main);

		EventBus.builder().throwSubscriberException(true).build();

		EventBus.getDefault().register(this);

		textView01 = (TextView) findViewById(R.id.textView01);
		textView01.setOnClickListener(mOnScaleListener);
		textView02 = (TextView) findViewById(R.id.textView02);
		textView02.setOnClickListener(mOnRotateListener);
		textView03 = (TextView) findViewById(R.id.textView03);
		textView03.setOnClickListener(mOnTranslateListener);
		textView04 = (TextView) findViewById(R.id.textView04);
		textView04.setOnClickListener(mOnAlphaListener);

		tv_test = (TextView) findViewById(R.id.tv_test);
		tv_start = (TextView) findViewById(R.id.tv_start);
		tv_test.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				ApiModel.getInstance().fetchRecords();
				ApiModel.getInstance().fetchRecords2();
			}
		});

		tv_start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onEnd(String args) {
		mHandler.sendMessage(mHandler.obtainMessage(0x32, args));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe
	public void onEventMainThread(FirstEvent event) {
		String msg = "onEventMainThread：" + event.getMsg();
		Log.d("harvic", msg);
		tv_test.setText(msg);
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	private View.OnClickListener mOnScaleListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scale);
			ImageView iv_01 = (ImageView) findViewById(R.id.iv_01);
			iv_01.startAnimation(animation);
		}
	};

	private View.OnClickListener mOnRotateListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_rotate);
			ImageView iv_01 = (ImageView) findViewById(R.id.iv_01);
			iv_01.startAnimation(animation);
		}
	};
	private View.OnClickListener mOnTranslateListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate);
			ImageView iv_01 = (ImageView) findViewById(R.id.iv_01);
			iv_01.startAnimation(animation);
		}
	};
	private View.OnClickListener mOnAlphaListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha);
			ImageView iv_01 = (ImageView) findViewById(R.id.iv_01);
			iv_01.startAnimation(animation);
		
		}
	};
	
}