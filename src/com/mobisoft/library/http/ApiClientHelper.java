package com.mobisoft.library.http;

import org.apache.http.protocol.HTTP;

public class ApiClientHelper {

	/** 协议请求方式-DELETE */
	public static final String PROTOCOL_DELETE = "DELETE";
	/** 协议请求方式-GET */
	public static final String PROTOCOL_GET = "GET";
	/** 协议请求方式-POST */
	public static final String PROTOCOL_POST = "POST";
	/** 协议请求方式-PUT */
	public static final String PROTOCOL_PUT = "PUT";

	/** 请求时编码格式UTF-8 */
	public static final String CHARSET_UTF8 = HTTP.UTF_8;
	/** 请求时编码格式UTF-16 */
	public static final String CHARSET_UTF16 = HTTP.UTF_16;

	/** NET请求协议-HTTP */
	public static final String HTTP_PROTOCOL = "http://";
	/** NET请求协议-HTTPS */
	public static final String HTTPS_PROTOCOL = "https://";

	/** 请求的连接超时时间，毫秒 */
	public static final int CONNECT_TIME_OUT = 20000;
	/** 请求的读取超时时间，毫秒 */
	public static final int READER_TIME_OUT = 30000;

}
