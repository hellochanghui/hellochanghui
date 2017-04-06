package com.hello.first.action;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.hello.first.bean.School;

public interface ISchools {
	
	Type TYPE = new TypeToken<Result<ArrayList<School>>>() {	}.getType();

	void onSchoolResult(ArrayList<School> data, int status);
}
