package com.city2farmer;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class H1201 extends AppCompatActivity {
    private static final String DB_File = "happyfarm.db", DB_TABLE = "HA1200";
    private SQLiteDatabase myHappyfarmDB;
    private HappyfarmDB hf12;
    private int DBversion = HappyfarmDB.VERSION;
    private ImageButton t013,t012;
    private Intent intent = new Intent();
    private Spinner s001, s002;
    private String counties, asdt;
    private TextView tv;
    private ImageView t014;
    private ProgressBar p001;
    private Handler handler = new Handler();
    private LocationManager manager;
    private ArrayList<Map<String, Object>> mLsit;
    private ListView listview;
    private Uri uri;
    private Intent it = new Intent();
    private CardView cardview;
    private String[] spinner1 = null;
    private String TABLE_l0103 = "l0103";
    private int bb;
    private ArrayList<Map<String, Object>> spinner2;
    private Geocoder geocoder;
    private String bestgps;
    private Location currentLocation;
    // 更新位置頻率的條件
    int minTime = 120000; // 毫秒
    float minDistance = 5; // 公尺
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private TextView total;
    private H1201_F_Select mSelect;
    private H1201_F_Map mMap;
    private FragmentManager mFragmentMgr;
    private LinearLayout lay02;
    private TextView bottombg;
    private MenuItem m_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
//        u_checkgps();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1201_main);
        initDB();
        setupViewcomponent();
    }

//    private void u_checkgps() {
//        //  顯示對話方塊檢查有無開啟定位
//        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//
//
//        } else {
//            AlertDialog.Builder openGPS = new AlertDialog.Builder(this);
//            openGPS.setTitle(getString(R.string.h1201_gpstitle))
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setMessage(getString(R.string.h1201_gpsmessage))
//                    .setPositiveButton(getString(R.string.h1201_enable), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                            //设置完成后返回原来的界面
//                            startActivityForResult(intent, 0);
//                        }
//                    })
//                    .setNegativeButton(getString(R.string.h1201_disabled), null).create().show();
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
//                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
//                        PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//    }

    private void setupViewcomponent() {
        //        顯示對話方塊檢查有無開啟網路
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);//先取得此service
        NetworkInfo networInfo = conManager.getActiveNetworkInfo();       //在取得相關資訊

        if (networInfo == null || !networInfo.isAvailable()) {
            new AlertDialog.Builder(H1201.this)
                    .setTitle("提醒")
                    .setMessage("沒有網路可用，請檢查網路狀態")
                    .setCancelable(false)
                    .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        } else {
//            有網路就刪掉重抓一次jsone更新
//                  為了不要一直重複新增，所以先刪除再新增
            myHappyfarmDB = hf12.getWritableDatabase();
            myHappyfarmDB.delete(DB_TABLE, null, null);
        }
//--------------------------------------------------------------------------------------
        //        	--設置標題--
        Intent intent = this.getIntent();
        String mode_title = intent.getStringExtra("class_title");
        this.setTitle(mode_title);

//----------登入&首頁-------------
        t012 = (ImageButton) findViewById(R.id.h1201_i002);
        t013 = (ImageButton) findViewById(R.id.h1201_i001);
        t014 = (ImageView) findViewById(R.id.h1201_i004);
        t012.setOnClickListener(b001ON);
        t013.setOnClickListener(b001ON);
        t014.setOnClickListener(b001ON);

        lay02 = (LinearLayout) findViewById(R.id.h1201_lay02);
        bottombg = (TextView) findViewById(R.id.h1201_bottombg);

        lay02.setVisibility(View.VISIBLE);
        bottombg.setVisibility(View.VISIBLE);
        mSelect = new H1201_F_Select();
        mMap = new H1201_F_Map();
//------------------------fragment設定------------------------
        mFragmentMgr = getFragmentManager();
        if (mSelect.isAdded()) {
            mFragmentMgr.beginTransaction()
                    .show(mSelect)
                    .addToBackStack(null)
                    .commit();
        } else {
            mFragmentMgr.beginTransaction()
                    .add(R.id.h1201_framelayout, mSelect)
                    .show(mSelect)
                    .addToBackStack(null)
                    .commit();
        }
//-----------------------------json----------------------
        try {
            // myHappyfarmDB.delete(DB_TABLE,null,null);
            String Task_opendata
                    = new TransTask().execute("https://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=104").get();

            List<Map<String, Object>> jsonList;
            jsonList = new ArrayList<Map<String, Object>>();

            JSONArray jsonArray = new JSONArray(Task_opendata);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String, Object> item = new HashMap<String, Object>();

                String company = jsonObject.getString("公司名稱");
                String counties = jsonObject.getString("縣市");
                String asdt = jsonObject.getString("行政區");
                String address = jsonObject.getString("地址");
                String phone = jsonObject.getString("電話");

                ContentValues newrow = new ContentValues();
                newrow.put("l0102", company);
                newrow.put("l0103", counties);
                newrow.put("l0104", asdt);
                newrow.put("l0105", address);
                newrow.put("l0106", phone);
                myHappyfarmDB.insert(DB_TABLE, null, newrow);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //    --------------------兩個Fragment調用的方法---------------------------------------------
    public void setMap(Bundle bundle) {
//        這裡的bundle是從select傳來的latitude和llongitude
        mMap.setArguments(bundle);
//            select=>map
        mFragmentMgr.beginTransaction()
                .add(R.id.h1201_framelayout, mMap)
                .show(mMap)
                .hide(mSelect)
                .addToBackStack(null)
                .commit();
    }

    public void BackToFragment() {
//            map=>select
//            popBackStack()是fragment的back(返回建)方法
        mFragmentMgr.popBackStack();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
        lay02.setVisibility(View.VISIBLE);
        bottombg.setVisibility(View.VISIBLE);
//            }
//        }, 100);



    }

    //    ------------------------------------------------------------------
    private View.OnClickListener b001ON = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.h1201_i001:
                    finish();
                    break;
                case R.id.h1201_i002:
                    uri = Uri.parse("https://m.facebook.com/city2farmer");
                    Intent it=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(it);
                    break;
                case R.id.h1201_i004:
                    intent.putExtra("class_title", getString(R.string.h1301_t003));
                    intent.setClass(H1201.this, H1302_about.class);
                    startActivity(intent);
                    break;
            }

        }
    };

    //    -----------------------------------------------------------------------
    private void initDB() {
//        假如sql沒開就開
        if (hf12 == null) {
            hf12 = new HappyfarmDB(getApplicationContext(), DB_File, null, DBversion);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(getApplicationContext(), R.string.h0501_toast_back, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        假如sql開啟就把它關掉
        if (hf12 != null) {
            hf12.close();
            hf12 = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        假如sql沒開就開
        if (hf12 == null) {
            hf12 = new HappyfarmDB(this, DB_File, null, DBversion);
        }
        //===登入====
        long_in();
        // 取得最佳的定位提供者
        Criteria criteria = new Criteria();
//        bestgps = manager.getBestProvider(criteria, true);
//
//        try {
//            if (bestgps != null) { // 取得快取的最後位置,如果有的話
//                currentLocation = manager.getLastKnownLocation(bestgps);
//                manager.requestLocationUpdates(bestgps, minTime, minDistance, listener);
//            } else { // 取得快取的最後位置,如果有的話
//                currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                        minTime, minDistance, listener);
//            }
//        } catch (SecurityException e) {
////            Log.e(TAG, "GPS權限失敗..." + e.getMessage());
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (hf12 != null) {
            hf12.close();
            hf12 = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacks(loading);
        if (hf12 != null) {
            hf12.close();
            hf12 = null;
        }
        this.finish();
    }

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    //------------------------menu--------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_return, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//
//                break;
//        }

        return super.onOptionsItemSelected(item);
    }
    // ---- ---------------subclass  json寫法------------------------

    private class TransTask extends AsyncTask<String, Void, String> {
        String ans;

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line = in.readLine();
                while (line != null) {
                    Log.d("HTTP", line);
                    sb.append(line);
                    line = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ans = sb.toString();
            return ans;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("s", "s:" + s);
            parseJson(s);
        }

        private void parseJson(String s) {

        }
    }

    //========登入後換圖片===============================
    public void long_in() {

        if (hf12 == null) {
            hf12 = new HappyfarmDB(H1201.this, "happyfarm.db", null, DBversion);
        }
        String u_pic = hf12.Find("r0205");
        boolean login_status = hf12.status();
        if (login_status) {

        } else {
            CircleImgView cc = (CircleImgView) findViewById(R.id.h1201_icon);
            cc.setImageResource(R.drawable.googleg_color);
        }

        Uri user_uri;
        if (u_pic != null) {
            user_uri = Uri.parse(u_pic);
        } else {
            CircleImgView userImage = (CircleImgView) findViewById(R.id.h1201_icon);
            userImage.setImageResource(R.drawable.googleg_color);
            userImage.invalidate();
            user_uri = null;
        }

        if (user_uri != null) {
            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... params) {
                    String url = params[0];
                    return getBitmapFromURL(url);
                }

                @Override
                protected void onPostExecute(Bitmap result) {
                    CircleImgView userImage = (CircleImgView) findViewById(R.id.h1201_icon);
                    userImage.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(user_uri.toString().trim());
        }
        hf12.close();
    }

    private Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
