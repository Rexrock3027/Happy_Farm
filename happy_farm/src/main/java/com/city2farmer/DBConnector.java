package com.city2farmer;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DBConnector {
    //--------------------------------------------------------
    private static String postUrl;
    private static String myResponse;
    static String result = null;
    private static OkHttpClient client = new OkHttpClient();
    private static int httpstate=0;
    //---------------------------------------------------------
// -------HOSTING-------
    //static String connect_ip = "https://city2farmer.com/tcnr05/happyfarm_connect_db_all.php";
    static String connect_ip = "https://city2farmer.com/android_mysql_connect/happyfarm_connect_db_all.php";


    public static String executeQuery(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl = connect_ip;
        //--------------
        String query_0 = query_string.get(0);
        //body=php裡面包的內容
        //==FormBody(okhttp語法)等同Array
        FormBody body = new FormBody.Builder()
                .add("selefunc_string", "query")
                .add("query_string", query_0)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
        //httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            //httpstate=response.code();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
//================新增SQLi=========================
    public static String executeInsert(ArrayList<String>query_string) {
//        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0); //標題
        String query_1 = query_string.get(1); //編輯日期
        String query_2 = query_string.get(2); //種植日期
        String query_3 = query_string.get(3); //筆記內容
        String query_4 = query_string.get(4); //email
        //body指https://...?後面帶入的參數
        FormBody body = new FormBody.Builder()
                .add("selefunc_string","insert")
                .add("n0102", query_0)
                .add("n0103", query_1)
                .add("n0104", query_2)
                .add("n0105", query_3)
                .add("n0106", query_4)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) { //===執行http命令
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
//=================更新SQLi===================================
    public static String executeUpdate(ArrayList<String> query_string) {
//        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0); //ID
        String query_1 = query_string.get(1); //標題
        String query_2 = query_string.get(2); //編輯日期
        String query_3 = query_string.get(3); //種植日期
        String query_4 = query_string.get(4); //筆記內容

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","update")
                .add("n0101", query_0)
                .add("n0102", query_1)
                .add("n0103", query_2)
                .add("n0104", query_3)
                .add("n0105", query_4)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
//=================按下刪除==========================
    public static String executeDelet(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","delete")
                .add("n0101", query_0)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
        //httpstate = 0;   //設 httpcode初始值
        //===================================================
        try (Response response = client.newCall(request).execute()) {
            //httpstate=response.code();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
