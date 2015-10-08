package com.mobisoft.library;

import android.content.Context;


public class AppConfig {

    /**屏幕宽度*/
    public static int SCREEN_WIDTH=0;

    /**屏幕高度*/
    public static int SCREEN_HEIGHT=0;

    /**屏幕密度*/
    public static float DENSITY=0;

    /**每英寸像素点*/
    public static int DENSITYDPI=0;

    /**字体缩放比例*/
    public static float SCALEDDENSITY=0;

    /**X轴方向每英寸像素点*/
    public static float XDPI=0;

    /**Y轴方向每英寸像素点*/
    public static float YDPI=0;


    public AppConfig(Context context){
        SCREEN_WIDTH =context.getResources().getDisplayMetrics(). widthPixels;
        SCREEN_HEIGHT =context.getResources().getDisplayMetrics(). heightPixels;
        DENSITY=context.getResources().getDisplayMetrics(). density;
        DENSITYDPI =context.getResources().getDisplayMetrics(). densityDpi;
        SCALEDDENSITY =context.getResources().getDisplayMetrics().scaledDensity ;
        XDPI=context.getResources().getDisplayMetrics(). xdpi;
        YDPI=context.getResources().getDisplayMetrics(). ydpi;
    }
}
