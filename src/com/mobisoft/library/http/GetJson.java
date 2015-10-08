package com.mobisoft.library.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.mobisoft.library.Constants;
import com.mobisoft.library.bean.Response;
import com.mobisoft.library.interf.JsonState;
import com.mobisoft.library.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class GetJson extends AsyncTask<Void, Void, Response> {

	private boolean isShow = true;
	private boolean isCancel = false;
	private Activity mActivity = null;
	private String mActionUrl = null;
	private Map<String, Object> request = new HashMap<String, Object>();
	private ProgressDialog pDialog = null;
	private String loadingMessage = null;
	private JsonState jsonState = null;
	private Object[] objs;
	private StringBuffer actionUrl = new StringBuffer();
	private Map<String, Object> params = null;
	private int conntctTimeOut = ApiClientHelper.CONNECT_TIME_OUT;
	private String method = ApiClientHelper.PROTOCOL_GET;
	private String charset = ApiClientHelper.CHARSET_UTF8;

	public GetJson() {
		super();
	}

	/**
	 * 
	 * @param isCancel
	 *            提示语是否可以取消
	 * @param mActivity
	 *            当前活动
	 * @param mActionUrl
	 *            请求的URL
	 * @param pDialog
	 *            提示框，可以自定义传入，可传null，当为null时根据是否显示显示默认的提示框
	 * @param loadingMessage
	 *            提示框信息
	 * @param jsonState
	 *            请求毁掉函数，查看对应接口定义函数
	 * @param objs
	 *            请求毁掉函数的参数，可为null
	 * @param actionUrl
	 *            请求的URL完整参数值，GET时会返回带参数的完整URL
	 * @param params
	 *            请求参数
	 * @param conntctTimeOut
	 *            请求是建立的网络链接请求超时时间
	 * @param method
	 *            请求方法，GET，POST，PUT，DELETE，目前支持POST，GET
	 * @param charset
	 *            请求参数编码字符集
	 */
	public GetJson(boolean isCancel, Activity mActivity, String mActionUrl, ProgressDialog pDialog,
			String loadingMessage, JsonState jsonState, Object[] objs, StringBuffer actionUrl,
			Map<String, Object> params, int conntctTimeOut, String method, String charset) {
		super();
		this.isCancel = isCancel;
		this.mActivity = mActivity;
		this.mActionUrl = mActionUrl;
		this.pDialog = pDialog;
		this.loadingMessage = loadingMessage;
		this.jsonState = jsonState;
		this.objs = objs;
		this.actionUrl = actionUrl;
		this.params = params;
		this.conntctTimeOut = conntctTimeOut;
		this.method = method;
		this.charset = charset;
	}

	/**
	 * 构造函数
	 * 
	 * @param isShow
	 *            是否显示 ProgressDialog，默认显示，可使用其他构造函数
	 * @param isCancel
	 *            ProgressDialog 是否可以取消，默认不可取消，可以使用其他构造函数
	 * @param mActivity
	 *            当前Activity
	 * @param mActionUrl
	 *            请求URL
	 * @param params
	 *            请求参数，map型数据
	 * @param loadingMessage
	 *            ProgressDialog显示的文本信息
	 * @param jsonState
	 *            执行结果是否有操作，有操作需要实现接口
	 */
	public GetJson(boolean isShow, boolean isCancel, Activity mActivity, String mActionUrl, Map<String, Object> params,
			ProgressDialog pDialog, String loadingMessage, JsonState jsonState) {
		super();
		this.isShow = isShow;
		this.isCancel = isCancel;
		this.mActivity = mActivity;
		this.mActionUrl = mActionUrl;
		this.params = params;
		this.pDialog = pDialog;
		this.loadingMessage = loadingMessage;
		this.jsonState = jsonState;
	}

	/**
	 * 构造函数
	 * 
	 * @param isCancel
	 *            ProgressDialog 是否可以取消，默认不可取消，可以使用其他构造函数
	 * @param mActivity
	 *            当前Activity
	 * @param mActionUrl
	 *            请求URL
	 * @param params
	 *            请求参数，map型数据
	 * @param loadingMessage
	 *            ProgressDialog显示的文本信息
	 * @param jsonState
	 *            执行结果是否有操作，有操作需要实现接口
	 */
	public GetJson(boolean isCancel, Activity mActivity, String mActionUrl, Map<String, Object> params,
			String loadingMessage, JsonState jsonState) {
		super();
		this.isCancel = isCancel;
		this.mActivity = mActivity;
		this.mActionUrl = mActionUrl;
		this.params = params;
		this.loadingMessage = loadingMessage;
		this.jsonState = jsonState;
	}

	/**
	 * 构造函数
	 * 
	 * @param mActivity
	 *            当前Activity
	 * @param mActionUrl
	 *            请求URL
	 * @param params
	 *            请求参数，map型数据
	 * @param loadingMessage
	 *            ProgressDialog显示的文本信息
	 */

	public GetJson(Activity mActivity, String mActionUrl, Map<String, Object> params, String loadingMessage,
			JsonState jsonState) {
		super();
		this.mActivity = mActivity;
		this.mActionUrl = mActionUrl;
		this.params = params;
		this.loadingMessage = loadingMessage;
		this.jsonState = jsonState;
	}

	/**
	 * 构造函数
	 * 
	 * @param mActivity
	 *            当前Activity
	 * @param mActionUrl
	 *            请求URL
	 * @param params
	 *            请求参数，map型数据
	 * @param loadingMessage
	 *            ProgressDialog显示的文本信息
	 * @param jsonState
	 *            执行结果是否有操作，有操作需要实现接口
	 */
	public GetJson(Activity mActivity, String mActionUrl, Map<String, Object> params, String loadingMessage) {
		super();
		this.mActivity = mActivity;
		this.mActionUrl = mActionUrl;
		this.params = params;
		this.loadingMessage = loadingMessage;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// 显示对话框，是否是自定义
		if (isShow && pDialog == null) {
			pDialog = new ProgressDialog(mActivity);
			pDialog.setMessage(loadingMessage);
		}
		if (isShow && pDialog != null) {
			pDialog.setCancelable(isCancel);
		}

		if (jsonState != null) {
			jsonState.onPreExecute();
		}

		if (jsonState != null && objs != null) {
			jsonState.onPreExecute(objs);
		}
	}

	@Override
	protected Response doInBackground(Void... args) {

		// "payload":
		// "{\"pwd\":\"123456\",\"account\":\"130521199202030777\",\"cmd\":\"ListLogin\",\"index\":0,\"size\":10}",

		// "account": "130521199202030777",
		request.put("gzip", false);
		request.put("t1", System.currentTimeMillis());
		request.put("ts", String.valueOf(System.currentTimeMillis() % 100));
		request.put("osmodel", Constants.MODEL);
		request.put("ostype", Constants.OS_SYSTEM);
		request.put("osversion", Constants.RELEASE);
		request.put("mudid", "");
		request.put("appversion", "");
		request.put("udid", "");
		request.put("digest", "");
		request.put("account", "");
		request.put("payload", JsonUtil.map2json(this.params));
		if (ApiClientHelper.PROTOCOL_POST.equals(method)) {
			return ApiHttpClient.doPost(mActivity.getBaseContext(), mActionUrl, params, conntctTimeOut, charset);
		} else {
			actionUrl.append(actionUrl);
			actionUrl.append("?req=");
			try {
				actionUrl.append(URLEncoder.encode(JsonUtil.map2json(params), charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return ApiHttpClient.doGet(mActivity.getBaseContext(), mActionUrl, params, conntctTimeOut, charset);
		}

	}

	@Override
	protected void onPostExecute(Response result) {
		super.onPostExecute(result);

		if (isShow && pDialog != null && pDialog.isShowing()) {
			pDialog.dismiss();
		}
		if (jsonState != null) {
			jsonState.onPostExecute(actionUrl.toString(), result);
		}
	}

	public boolean isShow() {
		return isShow;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public Activity getmActivity() {
		return mActivity;
	}

	public String getmActionUrl() {
		return mActionUrl;
	}

	public ProgressDialog getpDialog() {
		return pDialog;
	}

	public String getLoadingMessage() {
		return loadingMessage;
	}

	public JsonState getJsonState() {
		return jsonState;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public void setmActivity(Activity mActivity) {
		this.mActivity = mActivity;
	}

	public void setmActionUrl(String mActionUrl) {
		this.mActionUrl = mActionUrl;
	}

	public void setpDialog(ProgressDialog pDialog) {
		this.pDialog = pDialog;
	}

	public void setLoadingMessage(String loadingMessage) {
		this.loadingMessage = loadingMessage;
	}

	public void setJsonState(JsonState jsonState) {
		this.jsonState = jsonState;
	}

	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public int getConntctTimeOut() {
		return conntctTimeOut;
	}

	public String getMethod() {
		return method;
	}

	public String getCharset() {
		return charset;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setConntctTimeOut(int conntctTimeOut) {
		this.conntctTimeOut = conntctTimeOut;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
