package com.mobisoft.library;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.widget.Toast;

import java.util.Stack;

public class AppManager {

	private Stack<Activity> activityStack = new Stack<Activity>();

	/**单例模式*/
	private static AppManager instance;

	private AppManager() {
	}

	public static AppManager getAppManager() {
		if (instance == null) {
			return new AppManager();
		}
		return instance;
	}

	/**
	 * 添加当前Activity 到堆栈
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityStack.push(activity);
	}

	/**
	 * 获取当前的Activity （堆栈中的最后一个压入的）
	 * 
	 * @return
	 */
	public Activity getCurrentActivity() {
		return activityStack.lastElement();
	}

	/**
	 * 销毁当前的Activity
	 * 
	 * @param activity
	 */
	public void finishActivity() {
		finishActivity(activityStack.lastElement());
	}

	/**
	 * 销毁指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null && !activityStack.isEmpty()) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 销毁指定的Activity
	 * 
	 * @param clazz
	 */
	public void finishActivity(Class<?> clazz) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(clazz)) {
				finishActivity(activity);
			}
		}
	}

	public void finishAllActivity() {
		for (Activity activity : activityStack) {
			finishActivity(activity);
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	@SuppressWarnings("deprecation")
	public void ExitApp(Context context) {
		finishAllActivity();
		ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		activityMgr.restartPackage(context.getPackageName());
		System.exit(0);
	}


	private long exitTime = 0;

	public void ExitApp(Activity activity) {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(activity, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			activity.finish();
			AppManager.getAppManager().ExitApp(activity);
		}

	}
}
