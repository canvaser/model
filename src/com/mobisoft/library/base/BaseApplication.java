package com.mobisoft.library.base;

import android.app.Application;

import com.mobisoft.library.AppConfig;
import com.mobisoft.library.log.CrashHandler;
import com.mobisoft.library.util.FileUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;

public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		new AppConfig(this);
		CrashHandler.getInstance().init(getApplicationContext());// 注册crash监控
		deleteCrash();
	}

	// 删除在当天之前的所有crash文件，使用线程不论成功失败，均要进入APP
	private void deleteCrash() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				File mCrashDirectory = new File(FileUtil.CRASH_DIRECTORY);
				File[] files = mCrashDirectory.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File dir, String filename) {
						return filename.startsWith(FileUtil.CRASH_MOBISOFT)
								&& filename.endsWith(FileUtil.ERROR_LOG_SUFFIX);

					}
				});
				long currentTime = Calendar.getInstance().getTimeInMillis();
				for (File file : files) {
					if ((currentTime - file.lastModified()) / 86400000 > 0) {
						file.delete();
					}
				}

			}
		}).start();
	}

}
