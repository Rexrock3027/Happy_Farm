package com.city2farmer;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DBConnector06 {
    //--------------------------------------------------------
    private static String postUrl;
    private static String myResponse;
    static String result06 = null;
    private static OkHttpClient client = new OkHttpClient();
    //---------------------------------------------------------
// -------HOSTING-------
    //static String connect_ip = "https://oldpa88.com/android_mysql_connect/android_connect_db.php";
    //----------000webhost---------------------------------------------
    //static String connect_ip = "https://hired-acquisition.000webhostapp.com/android_mysql_connect/android_connect_ha0600.php";

    //---------hostinger-----------------------------------
    static String connect_ip ="https://city2farmer.com/tcnr06/android_connect_ha0600.php";

    public static String executeQuery06(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        FormBody body = new FormBody.Builder()
                .add("selefunc_string","query")
                .add("query_string", query_0)
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
        return result06;
    }
//    老爹的
//   static String connect_ip = "https://oldpa88.com/android_mysql_connect/android_connect_db.php";
    //班長
//  static String connect_ip = "https://mad-muscle.000webhostapp.com/android_mysql_connect/android_connect_db.php";
    //副班長
//    static String connect_ip = "https://monachal-bandage.000webhostapp.com/android_mysql_connect/android_connect_db.php";
    //23皓文
//   static String connect_ip = "https://oldba29.000webhostapp.com/android_mysql_connect/android_connect_db.php";
    //26小石
//   static String connect_ip = "https://volitionary-blocks.000webhostapp.com/android_mysql_connect/android_connect_db.php";
    //08松逸
//   static String connect_ip = "https://iron61700.000webhostapp.com/android_mysql_connect/android_connect_db.php";
    //12其軒
//  static String connect_ip = "https://kartg0203.000webhostapp.com/android_mysql_connect/android_connect_db.php";
}
