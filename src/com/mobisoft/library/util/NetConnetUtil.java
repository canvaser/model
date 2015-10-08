package com.mobisoft.library.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by canvaser on 2015/9/16.
 */
public class NetConnetUtil {
    public static boolean isNetOk(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
            for (int i = 0; i < networkInfos.length; i++) {
                NetworkInfo.State state = networkInfos[i].getState();
                Log.e("NetStateBroadCast", "" + state.name());
                if (NetworkInfo.State.CONNECTED == state) {
                    return true;
                }
            }
        }
        //没有执行return,则说明当前无网络连接
        Log.e("NetStateBroadCast", "------------> Network is validate");
        return false;
    }

    public static void isNetOk(Context context,String result){
        if(result==null){
            Toast.makeText(context,"请检查网络",Toast.LENGTH_LONG).show();
            return ;
        }
    }

    public static void isNetOk(Context context,String result,String message){
        if(result==null){
            Toast.makeText(context,"请检查网络",Toast.LENGTH_LONG).show();
            return ;
        }
        JSONObject object= JSON.parseObject(result);
        if(object==null){
            Toast.makeText(context,"数据有误",Toast.LENGTH_LONG).show();
            return ;
        }
        String msg=object.getString(message);
        Log.e("msg",msg+"@");
        if(msg!=null){
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
            return ;
        }
    }
}
