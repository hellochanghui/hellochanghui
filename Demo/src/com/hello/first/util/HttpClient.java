package com.hello.first.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class HttpClient {
	private static final HttpClient INSTANCE = new HttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final int TIME_OUT = 30;
	private static OkHttpClient sOkHttpClient = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).build();
	private HttpCallback callback;

	private void deliveryResult(int paramInt, Request paramRequest, HashMap<String, String> paramHashMap) {
		if (paramHashMap != null)
			new Request.Builder();
		this.callback.onNetworkStart(paramInt);
		sOkHttpClient.newCall(paramRequest).enqueue(this.callback);
	}

	public static HttpClient getInstance() {
		return INSTANCE;
	}

//	private Request getRequest(Request.Builder paramBuilder, String paramString, HashMap<String, String> paramHashMap) {
//		return paramBuilder.url(paramString).post(mapToParam(paramHashMap)).build();
//	}

	private RequestBody mapToParam(HashMap<String, String> paramHashMap) {
		FormBody.Builder localBuilder = new FormBody.Builder();
		Iterator<Entry<String, String>> localIterator = paramHashMap.entrySet().iterator();
		while (localIterator.hasNext()) {
			Map.Entry<String, String> localEntry = localIterator.next();
			localBuilder.add((String) localEntry.getKey(), (String) localEntry.getValue());
		}
		return localBuilder.build();
	}

	void ResetOption() {
	}

	void cancel(Request paramRequest) {
		sOkHttpClient.newCall(paramRequest).cancel();
	}

	Response getResponse(String paramString) throws IOException {
		return getResponse(new Request.Builder().url(paramString).build());
	}

	Response getResponse(Request paramRequest) throws IOException {
		return sOkHttpClient.newCall(paramRequest).execute();
	}

	String getString(String paramString) throws IOException {
		Response localResponse = getResponse(paramString);
		String str = localResponse.body().string();
		localResponse.body().close();
		return str;
	}

	void getString(String paramString, int paramInt) {
		getString(new Request.Builder().url(paramString).build(), paramInt, null, 30);
	}

	void getString(Request paramRequest, int paramInt) {
		getString(paramRequest, paramInt, null, 30);
	}

	void getString(Request paramRequest, int paramInt, HashMap<String, String> paramHashMap) {
		getString(paramRequest, paramInt, paramHashMap, 30);
	}

	void getString(Request paramRequest, int paramInt1, HashMap<String, String> paramHashMap, int paramInt2) {
		deliveryResult(paramInt1, paramRequest, paramHashMap);
	}

	void post(Request paramRequest, int paramInt1, HashMap<String, String> paramHashMap, int paramInt2) {
	}

	String postJson(String paramString1, String paramString2) throws IOException {
		RequestBody localRequestBody = RequestBody.create(JSON, paramString2);
		Request localRequest = new Request.Builder().url(paramString1).post(localRequestBody).build();
		Response localResponse = sOkHttpClient.newCall(localRequest).execute();
		if (localResponse.isSuccessful())
			return localResponse.body().string();
		throw new IOException("Unexpected code " + localResponse);
	}

	String postKeyValue(String paramString, HashMap<String, String> paramHashMap) throws IOException {
		RequestBody localRequestBody = mapToParam(paramHashMap);
		Request localRequest = new Request.Builder().url(paramString).post(localRequestBody).build();
		Response localResponse = sOkHttpClient.newCall(localRequest).execute();
		if (localResponse.isSuccessful())
			return localResponse.body().string();
		return null;
	}

	public void setOnStateChangedListener(HttpCallback paramHttpCallback) {
		this.callback = paramHttpCallback;
	}

	static abstract interface HttpCallback extends Callback {
		public abstract void onNetworkStart(int paramInt);
	}
}