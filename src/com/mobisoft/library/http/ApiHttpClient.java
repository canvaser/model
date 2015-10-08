package com.mobisoft.library.http;

import android.content.Context;

import com.mobisoft.library.bean.Response;
import com.mobisoft.library.log.TLog;
import com.mobisoft.library.util.JsonUtil;
import com.mobisoft.library.util.StringUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

public class ApiHttpClient {
	private static final String TAG = "ApiHttpClient";

	private static HttpClient customerHttpClient;

	// public static Response doGet(Context context, Map<String, Object> params, String charset) {
	// return doGet(context, params, ApiClientHelper.CONNECT_TIME_OUT, charset);
	// }

	public static Response doGet(Context context, String actionUrl, Map<String, Object> params, int connectTimeOut,
			String charset) {
		Response response = null;
		try {
			HttpGet httpGet = new HttpGet(actionUrl + "?req=" + URLEncoder.encode(JsonUtil.map2json(params), charset));
			// TLog.write(httpGet.getURI().toString());//将URL写入日志，便于查看记录

			// 取得HttpClient对象
			HttpClient httpClient = getHttpClient(context, connectTimeOut);
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			// 请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				String json = EntityUtils.toString(httpEntity);
				if (StringUtil.isEmpty(json)) {
					response = new Response(false, 900, "result is Empty");
				} else {
					response = JsonUtil.json2entity(json, Response.class);
				}

			} else {
				response = new Response(false, httpResponse.getStatusLine().getStatusCode(),
						httpCodeMsg(httpResponse.getStatusLine().getStatusCode()));
			}

			// 确保低级别资源释放
			if (httpEntity != null) {
				httpEntity.consumeContent();
			}

		} catch (OutOfMemoryError e) {

			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(901, e);
		} catch (HttpHostConnectException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(902, e);
		} catch (SocketTimeoutException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(903, e);
		} catch (ConnectTimeoutException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(904, e);
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(905, e);
		} catch (ClientProtocolException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(906, e);
		} catch (IOException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(907, e);
		}
		return response;
	}

	// public static Response doPost(Context context, Map<String, Object> params, String charset) {
	// return doPost(context, params, ApiClientHelper.CONNECT_TIME_OUT, charset);
	// }

	public static Response doPost(Context context, String actionUrl, Map<String, Object> params, int connectTimeOut,
			String charset) {
		Response response = null;

		try {
			HttpPost httpPost = new HttpPost(actionUrl);
			// httpPost.addHeader(name, value);

			HttpClient httpClient = getHttpClient(context, connectTimeOut);

			// 设置post请求参数
			httpPost.setEntity(new StringEntity(JsonUtil.map2json(params), charset));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String json = EntityUtils.toString(httpEntity);
				if (StringUtil.isEmpty(json)) {
					response = new Response(false, 900, "result is Empty");
				} else {
					response = JsonUtil.json2entity(json, Response.class);
				}
			} else {
				response = new Response(false, httpResponse.getStatusLine().getStatusCode(),
						httpCodeMsg(httpResponse.getStatusLine().getStatusCode()));
			}

			// 确保低级别资源释放
			if (httpEntity != null) {
				httpEntity.consumeContent();
			}

		} catch (OutOfMemoryError e) {

			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(901, e);
		} catch (HttpHostConnectException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(902, e);
		} catch (SocketTimeoutException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(903, e);
		} catch (ConnectTimeoutException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(904, e);
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(905, e);
		} catch (ClientProtocolException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(906, e);
		} catch (IOException e) {
			// e.printStackTrace();
			// TLog.e(TAG, e);
			// TLog.write(TLog.getStackTraceString(e));// 将错误写入日志，便于查看记录

			response = exceptionWrite(907, e);
		}
		return response;
	}

	/**
	 * 创建httpClient实例
	 * 
	 * @return
	 * @throws Exception
	 */
	public static synchronized HttpClient getHttpClient(Context context, int timeOut) {
		if (null == customerHttpClient) {
			customerHttpClient = new DefaultHttpClient();
			KeyStore trustStore = null;
			try {
				// 设置支持SSL请求,信任全部证书
				trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
				sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); // 允许所有主机的验证

				// 设置指定证书可以通过方案，需要事先得到证书，与设置所有证书均可以通过方案相似，仅不使用拓展来的sf
				// InputStream ins = context.getAssets().open("app_pay.cer"); // 下载的证书放到项目中的assets目录中
				// CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
				// Certificate cer = cerFactory.generateCertificate(ins);
				// KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
				// keyStore.load(null, null);
				// keyStore.setCertificateEntry("trust", cer);
				// SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);

				// 设置一些基本参数
				HttpParams params = new BasicHttpParams();
				HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
				HttpProtocolParams.setContentCharset(params, ApiClientHelper.CHARSET_UTF8);
				HttpProtocolParams.setUseExpectContinue(params, true);
				HttpProtocolParams.setUserAgent(params,
						"Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
								+ "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");

				// 超时设置
				/* 从连接池中取连接的超时时间 */
				ConnManagerParams.setTimeout(params, timeOut);
				/* 连接超时 */
				HttpConnectionParams.setConnectionTimeout(params, timeOut);
				/* 请求超时 */
				HttpConnectionParams.setSoTimeout(params, timeOut);

				// 设置我们的HttpClient支持HTTP和HTTPS两种模式
				SchemeRegistry schReg = new SchemeRegistry();
				schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
				// 默认方案，无任sf的设置
				// schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

				// 设置所有证书均可以通过方案
				schReg.register(new Scheme("https", sf, 443));

				// 设置指定证书可以通过方案，需要事先得到证书，与设置所有证书均可以通过方案相似，仅不使用拓展来的sf
				// schReg.register(new Scheme("https", socketFactory, 443));

				// 使用线程安全的连接管理来创建HttpClient
				ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
				customerHttpClient = new DefaultHttpClient(conMgr, params);
			} catch (KeyStoreException e) {
				e.printStackTrace();
				TLog.e(TAG, e);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				TLog.e(TAG, e);
			} catch (CertificateException e) {
				e.printStackTrace();
				TLog.e(TAG, e);
			} catch (IOException e) {
				e.printStackTrace();
				TLog.e(TAG, e);
			} catch (KeyManagementException e) {
				e.printStackTrace();
				TLog.e(TAG, e);
			} catch (UnrecoverableKeyException e) {
				e.printStackTrace();
				TLog.e(TAG, e);
			}
		}
		return customerHttpClient;
	}

	/**
	 * 将错误异常写入日志
	 * 
	 * @param errorCode
	 * @param e
	 * @return
	 */
	private static Response exceptionWrite(int errorCode, Throwable e) {
		TLog.e(TAG, TLog.getStackTraceString(e));
		return new Response(false, errorCode, TLog.getStackTraceString(e));
	}

	/**
	 * 
	 * httpCodeMsg: http-code，返回消息. <br/>
	 *
	 * @author mwd
	 * @param httpCode
	 * @return
	 * @since JDK 1.8
	 */
	public static String httpCodeMsg(int httpCode) {
		StringBuffer msg = new StringBuffer();
		switch (httpCode) {
		// 1xx（临时响应）
		case 100:
			msg.append("100-请求者应当继续提出请求。服务器返回此代码表示已收到请求的第一部分，正在等待其余部分。");
			break;
		case 101:
			msg.append("101-请求者已要求服务器切换协议，服务器已确认并准备切换。");
			break;
		// 2xx （成功）
		case 200:
			msg.append("200-服务器已成功处理了请求。通常，这表示服务器提供了请求的网页。");
			msg.append("如果是对您的 robots.txt 文件显示此状态码，则表示 Googlebot 已成功检索到该文件。");
			break;
		case 201:
			msg.append("201-请求成功并且服务器创建了新的资源。");
			break;
		case 202:
			msg.append("202-服务器已接受请求，但尚未处理。");
			break;
		case 203:
			msg.append("203-服务器已成功处理了请求，但返回的信息可能来自另一来源。");
			break;
		case 204:
			msg.append("204-服务器成功处理了请求，但没有返回任何内容。");
			break;
		case 205:
			msg.append("205-服务器成功处理了请求，但没有返回任何内容。与 204 响应不同，此响应要求请求者重置文档视图（例如，清除表单内容以输入新内容）。");
			break;
		case 206:
			msg.append("206-服务器成功处理了部分 GET 请求。");
			break;

		// 3xx （重定向）
		case 300:
			msg.append("300-针对请求，服务器可执行多种操作。服务器可根据请求者 (user agent) 选择一项操作，或提供操作列表供请求者选择。");
			break;
		case 301:
			msg.append("301-请求的网页已永久移动到新位置。服务器返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置。");
			msg.append("您应使用此代码告诉 Googlebot 某个网页或网站已永久移动到新位置。");
			break;
		case 302:
			msg.append("302-服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来响应以后的请求。");
			msg.append("此代码与响应 GET 和 HEAD 请求的 301 代码类似，会自动将请求者转到不同的位置，");
			msg.append("但您不应使用此代码来告诉 Googlebot 某个网页或网站已经移动，因为 Googlebot 会继续抓取原有位置并编制索引。");
			break;
		case 303:
			msg.append("303-请求者应当对不同的位置使用单独的 GET 请求来检索响应时，服务器返回此代码。对于除 HEAD 之外的所有请求，服务器会自动转到其他位置。");
			break;
		case 304:
			msg.append("305-自从上次请求后，请求的网页未修改过。服务器返回此响应时，不会返回网页内容。");
			msg.append("如果网页自请求者上次请求后再也没有更改过，您应将服务器配置为返回此响应（称为 If-Modified-Since HTTP 标头）。");
			msg.append("服务器可以告诉 Googlebot 自从上次抓取后网页没有变更，进而节省带宽和开销。");
			break;
		case 305:
			msg.append("306-请求者只能使用代理访问请求的网页。如果服务器返回此响应，还表示请求者应使用代理。");
			break;
		case 307:
			msg.append("307-服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来响应以后的请求。");
			msg.append("此代码与响应 GET 和 HEAD 请求的 <a href=answer.py?answer=>301</a> 代码类似，会自动将请求者转到不同的位置，");
			msg.append("但您不应使用此代码来告诉 Googlebot 某个页面或网站已经移动，因为 Googlebot 会继续抓取原有位置并编制索引。");
			break;
		// 4xx（请求错误）
		case 400:
			msg.append("400-请求要求身份验证。对于登录后请求的网页，服务器可能返回此响应。");
			break;
		case 401:
			msg.append("401-请求要求身份验证。对于登录后请求的网页，服务器可能返回此响应。");
			break;
		case 403:
			msg.append("403-服务器拒绝请求。如果您在 Googlebot 尝试抓取您网站上的有效网页时看到此状态码（您可以在 Google 网站管理员工具诊断下的网络抓取页面上看到此信息），");
			msg.append("可能是您的服务器或主机拒绝了 Googlebot 访问。");
			break;
		case 404:
			msg.append("404-服务器找不到请求的网页。例如，对于服务器上不存在的网页经常会返回此代码。");
			msg.append("如果您的网站上没有 robots.txt 文件，而您在 Google 网站管理员工具“诊断”标签的 robots.txt 页上看到此状态码，则这是正确的状态码。");
			msg.append("但是，如果您有 robots.txt 文件而又看到此状态码，则说明您的 robots.txt 文件可能命名错误或位于错误的位置（该文件应当位于顶级域，名为 robots.txt）。");
			msg.append("如果对于 Googlebot 抓取的网址看到此状态码（在”诊断”标签的 HTTP 错误页面上），则表示 Googlebot 跟随的可能是另一个页面的无效链接（是旧链接或输入有误的链接）。");
			break;
		case 405:
			msg.append("405-禁用请求中指定的方法。");
			break;
		case 406:
			msg.append("406-无法使用请求的内容特性响应请求的网页。");
			break;
		case 407:
			msg.append("407-此状态码与 <a href=answer.py?answer=35128>401（未授权）</a>类似，");
			msg.append("但指定请求者应当授权使用代理。如果服务器返回此响应，还表示请求者应当使用代理。");
			break;
		case 408:
			msg.append("408-服务器等候请求时发生超时。。");
			break;
		case 409:
			msg.append("409-服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。服务器在响应与前一个请求相冲突的 PUT 请求时可能会返回此代码，以及两个请求的差异列表。");
			break;
		case 410:
			msg.append("410-如果请求的资源已永久删除，服务器就会返回此响应。该代码与 404（未找到）代码类似，但在资源以前存在而现在不存在的情况下，");
			msg.append("有时会用来替代 404 代码。如果资源已永久移动，您应使用 301 指定资源的新位置。");
			break;
		case 411:
			msg.append("411-服务器不接受不含有效内容长度标头字段的请求。");
			break;
		case 412:
			msg.append("412-服务器未满足请求者在请求中设置的其中一个前提条件。");
			break;
		case 413:
			msg.append("413-服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。");
			break;
		case 414:
			msg.append("414-请求的 URI（通常为网址）过长，服务器无法处理。。");
			break;
		case 415:
			msg.append("415-请求的格式不受请求页面的支持。");
			break;
		case 416:
			msg.append("416-如果页面无法提供请求的范围，则服务器会返回此状态码。");
			break;
		case 417:
			msg.append("417-服务器未满足”期望”请求标头字段的要求。");
			break;
		// 5xx（服务器错误）
		case 500:
			msg.append("500-服务器遇到错误，无法完成请求。");
			break;
		case 501:
			msg.append("501-服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。。");
			break;
		case 502:
			msg.append("502-服务器作为网关或代理，从上游服务器收到无效响应。");

			break;
		case 503:
			msg.append("503-服务器目前无法使用（由于超载或停机维护）。通常，这只是暂时状态。");
			break;
		case 504:
			msg.append("504-服务器作为网关或代理，但是没有及时从上游服务器收到请求。");
			break;
		case 505:
			msg.append("505-服务器不支持请求中所用的 HTTP 协议版本。");
			break;
		default:
			msg.append("No-HTTP/HTTPS请求未知错误");

		}
		return msg.toString();
	}
}
