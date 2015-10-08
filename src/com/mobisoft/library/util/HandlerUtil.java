package com.mobisoft.library.util;

import android.os.Handler;

import com.mobisoft.library.interf.Onfinish;


/**
 * Created by canvaser on 2015/9/16.
 */
public class HandlerUtil {
    private static Handler handler=new Handler();
    public static void postDelay(int duration, final Onfinish onfinish){
        onfinish.onStart();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onfinish.onFinish(null);
            }
        }, duration);
    }
}
