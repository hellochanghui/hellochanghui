package com.hello.first.action;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.hello.first.bean.Grade;

public interface IGrades {
	
	Type TYPE = new TypeToken<Result<ArrayList<Grade>>>() {	}.getType();

	void onGradesResult(ArrayList<Grade> data, int status);
}
