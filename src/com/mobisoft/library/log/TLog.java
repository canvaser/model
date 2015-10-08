package com.mobisoft.library.log;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import com.mobisoft.library.util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TLog {

	public static boolean isDebugModel = true;// 是否输出日志
	public static boolean isSaveDebugInfo = true;// 是否保存调试日志
	public static boolean isSaveCrashInfo = true;// 是否保存报错日志

	public static void v(final String tag, final String msg) {

		if (isDebugModel) {
			Log.v(tag, msg);
		}

	}

	public static void d(final String tag, final String msg) {

		if (isDebugModel) {
			Log.d(tag, msg);
		}

	}

	public static void i(final String tag, final String msg) {
		if (isDebugModel) {
			Log.i(tag, msg);
		}

	}

	public static void w(final String tag, final String msg) {

		if (isDebugModel) {
			Log.w(tag, msg);
		}

	}

	/**
	 * 
	 * 调试日志，便于开发跟踪。
	 * 
	 * @param tag
	 * 
	 * @param msg
	 * 
	 */

	public static void e(final String tag, final String msg) {

		if (isDebugModel) {
			Log.e(tag, msg);
		}

		if (isSaveDebugInfo) {
			new Thread() {
				public void run() {
					write(time() + tag + " [DEBUG] --> " + msg + "\n");
				};

			}.start();

		}

	}

	/**
	 * 
	 * 
	 * try catch 时使用，上线产品可上传反馈。
	 * 
	 * @param tag
	 * 
	 * @param tr
	 * 
	 * 
	 */
	public static void e(final String tag, final Throwable tr) {

		if (isSaveCrashInfo) {
			new Thread() {
				public void run() {
					write(time() + tag + " [CRASH] --> " + getStackTraceString(tr) + "\n");
				};
			}.start();
		}

	}

	/**
	 * 
	 * 
	 * 获取捕捉到的异常的字符串
	 * 
	 * @param tr
	 * 
	 * @return
	 * 
	 * 
	 */
	public static String getStackTraceString(Throwable tr) {

		if (tr == null) {
			return "";
		}

		Throwable t = tr;
		while (t != null) {
			if (t instanceof UnknownHostException) {
				return "";
			}
			t = t.getCause();

		}

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		tr.printStackTrace(pw);

		return sw.toString();

	}

	/**
	 * 
	 * 标识每条日志产生的时间
	 * 
	 * @return
	 * 
	 */

	@SuppressLint("SimpleDateFormat")
	private static String time() {
		return "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())) + "] ";
	}

	/**
	 * 
	 * 错误文件名
	 * 
	 * @return
	 * 
	 */
	@SuppressLint("SimpleDateFormat")
	private static String date() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis()));

	}

	/**
	 * 保存到日志文件
	 * 
	 * @param content
	 */
	public static synchronized void write(String content) {

		try {
			FileWriter writer = new FileWriter(getFilePath(), true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取日志文件路径
	 * 
	 * @return
	 */
	public static String getFilePath() {

		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File logDir = new File(FileUtil.ERROR_LOG_DIRECTORY);
			if (!logDir.exists()) {
				logDir.mkdirs();
			}
			File filePath = new File(logDir + File.separator + date() + FileUtil.ERROR_LOG_SUFFIX);

			return filePath.toString();

		} else {
			return "";

		}

	}

}
