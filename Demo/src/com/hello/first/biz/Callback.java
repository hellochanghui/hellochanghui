package com.hello.first.biz;

import java.util.ArrayList;

import com.hello.first.bean.Grade;
import com.hello.first.bean.School;

public interface Callback {

	void onGradesResult(ArrayList<Grade> data, String msg);

	void onSchoolResult(ArrayList<School> data, String msg);

	void onPlayStreamWay(String way, String msg);

	void onPlayStreamUrl(String url, String msg);

	void onForwardUrl(String url, String msg);
}
