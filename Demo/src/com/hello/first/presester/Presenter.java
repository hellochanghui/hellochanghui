package com.hello.first.presester;

import java.util.ArrayList;

import com.hello.first.action.IGrades;
import com.hello.first.action.ISchools;
import com.hello.first.bean.Grade;
import com.hello.first.bean.School;
import com.hello.first.biz.Callback;
import com.hello.first.biz.IMainActivityListener;

public class Presenter extends Base implements ISchools, IGrades, IMainActivityListener {

	private Callback mCallback;

	// private Mode mode;

	public Presenter(Callback callback) {
		mCallback = callback;
	}

	@Override
	public void login(String username, String userpass) {
		// BaseHttpRequest request = new RequestLogin(userpass, userpass);
		// ApiModle.getInstance().getSchools(this, request);
	}

	@Override
	public void getGrades(String orgId) {
		// BaseRequest request = new RequestGetGrades(orgId);
		// ApiModle.getInstance().getGrades(this, request);
	}

	@Override
	public void onSchoolResult(ArrayList<School> data, int status) {
		// mCallback.onSchoolResult(data, onErrorMsg(mode, status));
	}

	@Override
	public void onGradesResult(ArrayList<Grade> data, int status) {
		// mCallback.onGradesResult(data, onErrorMsg(mode, status));
	}
}
