package com.mobisoft.library.interf;


import com.mobisoft.library.bean.Response;

public interface JsonState {

	void onPreExecute();

	void onPreExecute(Object... objs);

	void onPostExecute(String actionUrl, Response response);
}
