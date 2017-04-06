package com.hello.first.util;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import android.util.Log;

public class ApiModel implements HttpClient.HttpCallback {
	private static ApiModel instance;
	private Callback callback;

	public static ApiModel getInstance() {
		if (instance == null) {
			instance = new ApiModel();
			HttpClient.getInstance().setOnStateChangedListener(instance);
		}
		return instance;
	}

	public void fetchRecords() {
		Request localRequest = new Request.Builder().url("http://58.132.23.13:88/ve/webservices/app_tpk.shtml?method=getBuildData&user_id=20121128101024269739607672137452&louCeng_id=5DC293C9F45E4D33831BE3166E2BDD8F").build();
		HttpClient.getInstance().getString(localRequest, 20);
	}

	public void fetchRecords2() {
		Request localRequest = new Request.Builder().url("http://192.168.2.109:88/ve/webservices/app_tpk.shtml?method=getReviewToMyList&user_id=E1BD385F4E44499FB81DA55281B2AFCB&page=0&pagesize=17").build();
		HttpClient.getInstance().getString(localRequest, 20);
		HttpClient.getInstance().cancel(localRequest);
	}

	public void onFailure(Call paramCall, IOException paramIOException) {
		if ((paramIOException instanceof SocketTimeoutException))
			Log.i("result", "onFailure 连接远程服务器超时\n   code = " + paramIOException + "    " + paramIOException);

		if (paramIOException instanceof SocketException) {
			Log.i("result", "onFailure 无法建立远程连接\n code = " + paramIOException + "    " + paramIOException);
		} else {
			Log.i("result", "onFailure code = " + paramIOException + "    " + paramIOException);
		}
	}

	public void onNetworkStart(int paramInt) {
		this.callback.onNetworkStart();
	}

	public void onResponse(Call paramCall, Response paramResponse) throws IOException {

		String str = paramResponse.body().string();
		int i = paramResponse.code();
		paramResponse.body().close();
		Log.i("result", "onResponse  code = " + i + "    " + str);
		this.callback.onEnd(str);
	}

	public void setCallback(Callback paramCallback) {
		this.callback = paramCallback;
	}

	public static interface Callback {
		void onEnd(String args);

		void onNetworkStart();
	}
}