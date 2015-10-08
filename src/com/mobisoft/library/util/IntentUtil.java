package com.mobisoft.library.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.Serializable;

/**
 * Created by canvaser on 2015/9/16.
 */
public class IntentUtil {
    public static void intentToActivity(Context context,Class c,String key,Serializable value){
        Intent intent=new Intent(context,c);
        intent.putExtra(key,value);
        context.startActivity(intent);
    }

    public static void openFile(Context context,File f) {
        Intent intent = new Intent ();
        intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK) ;
        intent.setAction (android.content.Intent.ACTION_VIEW);
        String type = getMIMEType (f);
        intent.setDataAndType(Uri.fromFile(f), type);
        context.startActivity(intent) ;
    }

    //判断文件类型
    private static String getMIMEType (File f) {
        String type = "";
        String fName = f.getName ();
        String end = fName
                .substring (fName.lastIndexOf( ".") + 1, fName.length())
                . toLowerCase();
        if (end.equals( "m4a") || end.equals("mp3") || end.equals("mid" )
                || end.equals ("xmf" ) || end.equals("ogg") || end.equals("wav" )) {
            type = "audio";
        } else if (end.equals ("3gp" ) || end.equals("mp4")) {
            type = "video";
        } else if (end.equals ("jpg" ) || end.equals("gif") || end.equals("png" )
                || end.equals ("jpeg" ) || end.equals("bmp")) {
            type = "image";
        } else if (end.equals ("apk" )) {
            type = "application/vnd.android.package-archive" ;
        } else {
            type = "*";
        }
        if (end.equals( "apk")) {
        } else {
            type += "/*";
        }
        return type;
    }


}
