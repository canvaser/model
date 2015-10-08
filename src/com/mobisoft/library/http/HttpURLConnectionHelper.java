package com.mobisoft.library.http;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by canvaser on 2015/9/17.
 */
public class HttpURLConnectionHelper extends AsyncTask<String,String,String> {
    String httpUrl=null;
    String httpArg=null;
    public interface OnResponse{
        public void onResult(String result);
    }
    OnResponse onResponse=null;
    public HttpURLConnectionHelper(String httpUrl,String httpArg,OnResponse onResponse){
        this.httpArg=httpArg;
        this.httpUrl=httpUrl;
        this.onResponse=onResponse;
    }


    @Override
    protected String doInBackground(String... params) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL( httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入 apikey到HTTP header
           // connection.setRequestProperty( "apikey" , App.APIKEY);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader( new InputStreamReader(is, "UTF-8" ));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n" );
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        onResponse.onResult(s);
    }
}
