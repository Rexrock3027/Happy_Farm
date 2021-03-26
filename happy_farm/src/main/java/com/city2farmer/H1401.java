package com.city2farmer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

public class H1401 extends AppCompatActivity implements View.OnClickListener {

    private static final String DB_FILE = "happyfarm.db";
    private TextView b001,b002,b003,b004;
    private TextView t0101,t0102,t0103,t0104,t0105,t0106,t0107,t0108;
    private TextView t0201,t0202,t0203,t0204,t0205,t0206,t0207;
    private TextView t0301,t0302,t0303,t0304,t0305,t0306,t0307,t0308,t0309,t0310;
    private TextView t0401,t0402,t0403,t0404,t0405,t0406,t0407;
    private ImageView t017,t018,myname;
    private Intent intent=new Intent();
    private RelativeLayout layout;
    private TextView tname,tnidn,tctt;
    private ImageView img;
    private int bb;
    private HappyfarmDB dbHfarm;
    private int y0101;
    private String y0102,y0103,y0104,y0105,y0106;
    private String sqlctl;
    private int DBversion= HappyfarmDB.VERSION;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1401);
        setupViewCompoent();
        initDB();

        SQLiteDatabase aa = dbHfarm.getWritableDatabase();
        aa.delete("HA1400",null,null);

        //-----------json-----------------------------
        try{

            String Task_opendata
                    = new TransTask().execute("https://city2farmer.com/tcnr14/happyfand_r_api.php").get();

            JSONArray jsonArray = new JSONArray(Task_opendata);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String, Object> item = new HashMap<String, Object>();

                String y001 = jsonObject.getString("y0102");
                String y002 = jsonObject.getString("y0103");
                String y003 = jsonObject.getString("y0104");

                y0101= i;
                y0102=y001;
                y0103=y002;
                y0104=y003;
                dbHfarm.db14(y0101,y0102,y0103,y0104);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
    //------------------------------------------------------------------------------
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
//------------
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
//    ------------------------------------------------------

    private void setupViewCompoent() {
        //	--設置標題--
        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);

        layout=(RelativeLayout)findViewById(R.id.h1401_RL01);

        b001=(TextView)findViewById(R.id.h1401_b001);
        b002=(TextView)findViewById(R.id.h1401_b002);
        b003=(TextView)findViewById(R.id.h1401_b003);
        b004=(TextView)findViewById(R.id.h1401_b004);

        t0101=(TextView)findViewById(R.id.h1401_t0101);
        t0102=(TextView)findViewById(R.id.h1401_t0102);
        t0103=(TextView)findViewById(R.id.h1401_t0103);
        t0104=(TextView)findViewById(R.id.h1401_t0104);
        t0105=(TextView)findViewById(R.id.h1401_t0105);
        t0106=(TextView)findViewById(R.id.h1401_t0106);
        t0107=(TextView)findViewById(R.id.h1401_t0107);
        t0108=(TextView)findViewById(R.id.h1401_t0108);
        t0201=(TextView)findViewById(R.id.h1401_t0201);
        t0202=(TextView)findViewById(R.id.h1401_t0202);
        t0203=(TextView)findViewById(R.id.h1401_t0203);
        t0204=(TextView)findViewById(R.id.h1401_t0204);
        t0205=(TextView)findViewById(R.id.h1401_t0205);
        t0206=(TextView)findViewById(R.id.h1401_t0206);
        t0207=(TextView)findViewById(R.id.h1401_t0207);
        t0301=(TextView)findViewById(R.id.h1401_t0301);
        t0302=(TextView)findViewById(R.id.h1401_t0302);
        t0303=(TextView)findViewById(R.id.h1401_t0303);
        t0304=(TextView)findViewById(R.id.h1401_t0304);
        t0305=(TextView)findViewById(R.id.h1401_t0305);
        t0306=(TextView)findViewById(R.id.h1401_t0306);
        t0307=(TextView)findViewById(R.id.h1401_t0307);
        t0308=(TextView)findViewById(R.id.h1401_t0308);
        t0309=(TextView)findViewById(R.id.h1401_t0309);
        t0310=(TextView)findViewById(R.id.h1401_t0310);
        t0401=(TextView)findViewById(R.id.h1401_t0401);
        t0402=(TextView)findViewById(R.id.h1401_t0402);
        t0403=(TextView)findViewById(R.id.h1401_t0403);
        t0404=(TextView)findViewById(R.id.h1401_t0404);
        t0405=(TextView)findViewById(R.id.h1401_t0405);
        t0406=(TextView)findViewById(R.id.h1401_t0406);
        t0407=(TextView)findViewById(R.id.h1401_t0407);

        img=(ImageView)findViewById(R.id.h1401_t013);
        tname=(TextView)findViewById(R.id.h1401_t014);
        tnidn=(TextView)findViewById(R.id.h1401_t015);
        tctt=(TextView)findViewById(R.id.h1401_t016);

        b001.setOnClickListener(this);
        b002.setOnClickListener(this);
        b003.setOnClickListener(this);
        b004.setOnClickListener(this);

        t0101.setOnClickListener(this);
        t0102.setOnClickListener(this);
        t0103.setOnClickListener(this);
        t0104.setOnClickListener(this);
        t0105.setOnClickListener(this);
        t0106.setOnClickListener(this);
        t0107.setOnClickListener(this);
        t0108.setOnClickListener(this);
        t0201.setOnClickListener(this);
        t0202.setOnClickListener(this);
        t0203.setOnClickListener(this);
        t0204.setOnClickListener(this);
        t0205.setOnClickListener(this);
        t0206.setOnClickListener(this);
        t0207.setOnClickListener(this);
        t0301.setOnClickListener(this);
        t0302.setOnClickListener(this);
        t0303.setOnClickListener(this);
        t0304.setOnClickListener(this);
        t0305.setOnClickListener(this);
        t0306.setOnClickListener(this);
        t0307.setOnClickListener(this);
        t0308.setOnClickListener(this);
        t0309.setOnClickListener(this);
        t0310.setOnClickListener(this);
        t0401.setOnClickListener(this);
        t0402.setOnClickListener(this);
        t0403.setOnClickListener(this);
        t0404.setOnClickListener(this);
        t0405.setOnClickListener(this);
        t0406.setOnClickListener(this);
        t0407.setOnClickListener(this);






        t017=(ImageView)findViewById(R.id.h1401_t017);
        t018=(ImageView)findViewById(R.id.h1401_t018);
        myname =(ImageView)findViewById(R.id.myname14);
        t017.setOnClickListener(b001ON);
        t018.setOnClickListener(b001ON);
        myname.setOnClickListener(b001ON);

        Animation alpha = AnimationUtils.loadAnimation(this, R.anim.h1401_anim_scale_rotate_in);
//                alpha.setInterpolator(new BounceInterpolator());
        layout.setAnimation(alpha);







    }
    private View.OnClickListener b001ON=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.h1401_t017:
                    finish();
                    break;

                case R.id.h1401_t018:
                    uri = Uri.parse("https://m.facebook.com/city2farmer");
                    Intent it=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(it);

                    break;

                case R.id.myname14:
                    intent.putExtra("class_title",getString(R.string.h1301_t003));
                    intent.setClass(H1401.this, H1302_about.class);
                    startActivity(intent);
                    break;
            }

        }
    };



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.h1401_b001:
                visibleandgone();
                b001.setTextColor(Color.rgb(255,0,0));
                t0101.setVisibility(View.VISIBLE);
                t0102.setVisibility(View.VISIBLE);
                t0103.setVisibility(View.VISIBLE);
                t0104.setVisibility(View.VISIBLE);
                t0105.setVisibility(View.VISIBLE);
//                t0106.setVisibility(View.VISIBLE);
//                t0107.setVisibility(View.VISIBLE);
                t0108.setVisibility(View.VISIBLE);
                break;
            case R.id.h1401_b002:
                visibleandgone();
                b002.setTextColor(Color.rgb(255,0,0));
                t0201.setVisibility(View.VISIBLE);
                t0202.setVisibility(View.VISIBLE);
                t0203.setVisibility(View.VISIBLE);
                t0204.setVisibility(View.VISIBLE);
                t0205.setVisibility(View.VISIBLE);
                t0206.setVisibility(View.VISIBLE);
                t0207.setVisibility(View.VISIBLE);
                break;

            case R.id.h1401_b003:
                visibleandgone();
                b003.setTextColor(Color.rgb(255,0,0));
                t0301.setVisibility(View.VISIBLE);
                t0302.setVisibility(View.VISIBLE);
                t0303.setVisibility(View.VISIBLE);
                t0304.setVisibility(View.VISIBLE);
                t0305.setVisibility(View.VISIBLE);
                t0306.setVisibility(View.VISIBLE);
                t0307.setVisibility(View.VISIBLE);
                t0308.setVisibility(View.VISIBLE);
                t0309.setVisibility(View.VISIBLE);
                t0310.setVisibility(View.VISIBLE);
                break;
            case R.id.h1401_b004:
                visibleandgone();
                b004.setTextColor(Color.rgb(255,0,0));
                t0401.setVisibility(View.VISIBLE);
                t0402.setVisibility(View.VISIBLE);
                t0403.setVisibility(View.VISIBLE);
                t0404.setVisibility(View.VISIBLE);
                t0405.setVisibility(View.VISIBLE);
                t0406.setVisibility(View.VISIBLE);
//                t0407.setVisibility(View.VISIBLE);
                break;

            case R.id.h1401_t0101:
                randb(0);
                colorindsize();
                t0101.setTextSize(24);
                t0101.setTextColor(Color.rgb(255,0,0));

                break;
            case R.id.h1401_t0102:
                randb(1);
                colorindsize();
                t0102.setTextSize(24);
                t0102.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0103:
                randb(2);
                colorindsize();
                t0103.setTextSize(24);
                t0103.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0104:
                randb(3);
                colorindsize();
                t0104.setTextSize(24);
                t0104.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0105:
                randb(4);
                colorindsize();
                t0105.setTextSize(24);
                t0105.setTextColor(Color.rgb(255,0,0));
                break;
//            case R.id.h1401_t0106:
//                randb(5);
//                colorindsize();
//                t0106.setTextSize(24);
//                t0106.setTextColor(Color.rgb(255,0,0));
//                break;
//            case R.id.h1401_t0107:
//                tname.setText(getString(R.string.h1401_t014)+"     "+getString(R.string.h1401_t0107));
//                colorindsize();
//                t0107.setTextSize(24);
//                t0107.setTextColor(Color.rgb(255,0,0));
//                break;
            case R.id.h1401_t0108:
                randb(6);
                colorindsize();
                t0108.setTextSize(24);
                t0108.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0201:
                randb(7);
                colorindsize();
                t0201.setTextSize(24);
                t0201.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0202:
                randb(8);
                colorindsize();
                t0202.setTextSize(24);
                t0202.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0203:
                randb(9);
                colorindsize();
                t0203.setTextSize(24);
                t0203.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0204:
                randb(10);
                colorindsize();
                t0204.setTextSize(24);
                t0204.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0205:
                randb(11);
                colorindsize();
                t0205.setTextSize(24);
                t0205.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0206:
                randb(12);
                colorindsize();
                t0206.setTextSize(24);
                t0206.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0207:
                randb(13);
                colorindsize();
                t0207.setTextSize(24);
                t0207.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0301:
                randb(14);
                colorindsize();
                t0301.setTextSize(24);
                t0301.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0302:
                randb(15);
                colorindsize();
                t0302.setTextSize(24);
                t0302.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0303:
                randb(16);
                colorindsize();
                t0303.setTextSize(24);
                t0303.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0304:
                randb(17);
                colorindsize();
                t0304.setTextSize(24);
                t0304.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0305:
                randb(18);
                colorindsize();
                t0305.setTextSize(24);
                t0305.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0306:
                randb(19);
                colorindsize();
                t0306.setTextSize(24);
                t0306.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0307:
                randb(20);
                colorindsize();
                t0307.setTextSize(24);
                t0307.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0308:
                randb(21);
                colorindsize();
                t0308.setTextSize(24);
                t0308.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0309:
                randb(22);
                colorindsize();
                t0309.setTextSize(24);
                t0309.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0310:
                randb(23);
                colorindsize();
                t0310.setTextSize(24);
                t0310.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0401:
                randb(24);
                colorindsize();
                t0401.setTextSize(24);
                t0401.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0402:
                randb(25);
                colorindsize();
                t0402.setTextSize(24);
                t0402.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0403:
                randb(28);
                colorindsize();
                t0403.setTextSize(24);
                t0403.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0404:
                randb(26);
                colorindsize();
                t0404.setTextSize(24);
                t0404.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0405:
                randb(27);
                colorindsize();
                t0405.setTextSize(24);
                t0405.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.h1401_t0406:
                randb(5);
                colorindsize();
                t0406.setTextSize(24);
                t0406.setTextColor(Color.rgb(255,0,0));
                break;

        }

    }




    private void visibleandgone() {
        b001.setTextColor(Color.rgb(0,0,0));
        b002.setTextColor(Color.rgb(0,0,0));
        b003.setTextColor(Color.rgb(0,0,0));
        b004.setTextColor(Color.rgb(0,0,0));
        t0101.setVisibility(View.GONE);
        t0102.setVisibility(View.GONE);
        t0103.setVisibility(View.GONE);
        t0104.setVisibility(View.GONE);
        t0105.setVisibility(View.GONE);
        t0106.setVisibility(View.GONE);
        t0107.setVisibility(View.GONE);
        t0108.setVisibility(View.GONE);
        t0201.setVisibility(View.GONE);
        t0202.setVisibility(View.GONE);
        t0203.setVisibility(View.GONE);
        t0204.setVisibility(View.GONE);
        t0205.setVisibility(View.GONE);
        t0206.setVisibility(View.GONE);
        t0207.setVisibility(View.GONE);
        t0301.setVisibility(View.GONE);
        t0302.setVisibility(View.GONE);
        t0303.setVisibility(View.GONE);
        t0304.setVisibility(View.GONE);
        t0305.setVisibility(View.GONE);
        t0306.setVisibility(View.GONE);
        t0307.setVisibility(View.GONE);
        t0308.setVisibility(View.GONE);
        t0309.setVisibility(View.GONE);
        t0310.setVisibility(View.GONE);
        t0401.setVisibility(View.GONE);
        t0402.setVisibility(View.GONE);
        t0403.setVisibility(View.GONE);
        t0404.setVisibility(View.GONE);
        t0405.setVisibility(View.GONE);
        t0406.setVisibility(View.GONE);
        t0407.setVisibility(View.GONE);
    }
    private void colorindsize() {
        t0101.setTextColor(Color.rgb(0,0,0));
        t0102.setTextColor(Color.rgb(0,0,0));
        t0103.setTextColor(Color.rgb(0,0,0));
        t0104.setTextColor(Color.rgb(0,0,0));
        t0105.setTextColor(Color.rgb(0,0,0));
        t0106.setTextColor(Color.rgb(0,0,0));
        t0107.setTextColor(Color.rgb(0,0,0));
        t0108.setTextColor(Color.rgb(0,0,0));
        t0201.setTextColor(Color.rgb(0,0,0));
        t0202.setTextColor(Color.rgb(0,0,0));
        t0203.setTextColor(Color.rgb(0,0,0));
        t0204.setTextColor(Color.rgb(0,0,0));
        t0205.setTextColor(Color.rgb(0,0,0));
        t0206.setTextColor(Color.rgb(0,0,0));
        t0207.setTextColor(Color.rgb(0,0,0));
        t0301.setTextColor(Color.rgb(0,0,0));
        t0302.setTextColor(Color.rgb(0,0,0));
        t0303.setTextColor(Color.rgb(0,0,0));
        t0304.setTextColor(Color.rgb(0,0,0));
        t0305.setTextColor(Color.rgb(0,0,0));
        t0306.setTextColor(Color.rgb(0,0,0));
        t0307.setTextColor(Color.rgb(0,0,0));
        t0308.setTextColor(Color.rgb(0,0,0));
        t0309.setTextColor(Color.rgb(0,0,0));
        t0310.setTextColor(Color.rgb(0,0,0));
        t0401.setTextColor(Color.rgb(0,0,0));
        t0402.setTextColor(Color.rgb(0,0,0));
        t0403.setTextColor(Color.rgb(0,0,0));
        t0404.setTextColor(Color.rgb(0,0,0));
        t0405.setTextColor(Color.rgb(0,0,0));
        t0406.setTextColor(Color.rgb(0,0,0));

        t0101.setTextSize(18);
        t0102.setTextSize(18);
        t0103.setTextSize(18);
        t0104.setTextSize(18);
        t0105.setTextSize(18);
        t0106.setTextSize(18);
        t0107.setTextSize(18);
        t0108.setTextSize(18);
        t0201.setTextSize(18);
        t0202.setTextSize(18);
        t0203.setTextSize(18);
        t0204.setTextSize(18);
        t0205.setTextSize(18);
        t0206.setTextSize(18);
        t0207.setTextSize(18);
        t0301.setTextSize(18);
        t0302.setTextSize(18);
        t0303.setTextSize(18);
        t0304.setTextSize(18);
        t0305.setTextSize(18);
        t0306.setTextSize(18);
        t0307.setTextSize(18);
        t0308.setTextSize(18);
        t0309.setTextSize(18);
        t0310.setTextSize(18);
        t0401.setTextSize(18);
        t0402.setTextSize(18);
        t0403.setTextSize(18);
        t0404.setTextSize(18);
        t0405.setTextSize(18);
        t0406.setTextSize(18);




    }

    //    =============DB相關程式=============================
    private void randb(int i) {
//        for(int j=0;j<40;j++){
//            y0101= j;
//            y0102="測試"+(j+1);
//            y0103="測試圖"+(j+1);
//            y0104="測試內容"+(j+1);
//            dbHfarm.db14(y0101,y0102,y0103,y0104);
//        }
        List<String> redb01 = dbHfarm.redb(i,1);
        tname.setText(getString(R.string.h1401_t014)+"     "+redb01.get(0));

        List<String> redb02 = dbHfarm.redb(i,2);


        Glide.with(img)
                .load(redb02.get(0))
                .into(img);




        List<String> redb03 = dbHfarm.redb(i,3);
        tctt.setText(redb03.get(0));








    }

    private void initDB() {
        if (dbHfarm == null) {
            dbHfarm = new HappyfarmDB(this, DB_FILE, null, DBversion);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHfarm != null) {
            dbHfarm.close();
            dbHfarm = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHfarm == null) {
            dbHfarm = new HappyfarmDB(this, DB_FILE, null, DBversion);
        }
        //===登入====
        long_in();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dbHfarm != null) {
            dbHfarm.close();
            dbHfarm = null;
        }
    }


    //    --------------------------------------menu------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.m_return, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        this.finish();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(getApplicationContext(),R.string.h0501_toast_back,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        SharedPreferences A01 =getSharedPreferences("AA", 0);

        bb =A01.getInt("AA",0);
        if(bb==1){
            bb=0;
            this.finish();
        }
        super.onStart();
    }

    //========登入後換圖片===============================
    public void long_in(){

        if(dbHfarm == null){
            dbHfarm = new HappyfarmDB(H1401.this, "happyfarm.db", null, DBversion);
        }
        String  u_pic=dbHfarm.Find("r0205");
        boolean login_status = dbHfarm.status();
        if(login_status){

        }else{
            CircleImgView cc = (CircleImgView) findViewById(R.id.h1401_icon);
            cc.setImageResource(R.drawable.googleg_color);
        }

        Uri user_uri;
        if(u_pic!=null){
            user_uri=Uri.parse(u_pic);
        }else{
            CircleImgView userImage = (CircleImgView)findViewById(R.id.h1401_icon);
            userImage.setImageResource(R.drawable.googleg_color);
            userImage.invalidate();
            user_uri=null;
        }

        if(user_uri!=null){
            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... params) {
                    String url = params[0];
                    return getBitmapFromURL(url);
                }
                @Override
                protected void onPostExecute(Bitmap result) {
                    CircleImgView userImage = (CircleImgView) findViewById(R.id.h1401_icon);
                    userImage.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(user_uri.toString().trim());
        }
        dbHfarm.close();
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