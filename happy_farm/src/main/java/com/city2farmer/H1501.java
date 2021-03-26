package com.city2farmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.navdrawer.SimpleSideDrawer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.city2farmer.h1502;
import com.city2farmer.userThread;

public class H1501 extends AppCompatActivity {
    private static final String DB_FILE = "happyfarm.db", DB_TABLE15 = "ha1500";
    private static final int DBversion = HappyfarmDB.VERSION;
    private HappyfarmDB dbHf15;
    //private SimpleSideDrawer mNav;
    private Button lmb001, rmb001;
    private Long startTime, spenttime;
    private TextView lt001, lt002, lt003;
    private TextView rt001, rt002, rt003;
    private ImageView imgSwi;
    private ImageButton img01, t001;
    private ImageView img02, img03, img04, img05, img06,img07,img08;
    private View.OnClickListener img01ON;
    private Toast toast;
    private MediaPlayer bgmusic;
//    private Intent intent = new Intent();
    private Button b000;
    private ImageButton b007,b008;
    private CircleImgbutton img;
    private Intent intent = new Intent();
    private int bb;
    private Uri uri;
    private Button b001,b002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1501);

        // 取得目前時間
        startTime = System.currentTimeMillis();
        // -------------------------------------------
//       mNav = new SimpleSideDrawer(this);
//        // 設定開啟左側選單
//        findViewById(R.id.h1501_b001).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mNav.toggleLeftDrawer();
//            }
//        });
//        // 設定開啟右側選單
//        findViewById(R.id.h1501_b002).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mNav.toggleRightDrawer();
//            }
//        });
//
//        mNav.setLeftBehindContentView(R.layout.leftmenu);
//
//        // 設定開啟左側選單 點兩下
//        findViewById(R.id.rl01).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spenttime = System.currentTimeMillis() - startTime;
//
//                if (spenttime < 1000) {
//                    mNav.toggleLeftDrawer();
//                } else {
//                    startTime = System.currentTimeMillis();
//                }
//            }
//        });
//
//// 設定開啟右邊欄 長按
//        mNav.setRightBehindContentView(R.layout.rightmenu);
//        findViewById(R.id.rl01).setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                mNav.toggleRightDrawer();
//                return false;
//            }
//        });

        // -------------------------------------------
        setupViewComponent();
    }

    private void setupViewComponent() {
        //	--設置標題--
        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);
        //bgmusic = MediaPlayer.create(H1501.this, R.raw.bgmusic);
        //bgmusic.start();
        imgSwi = (ImageView) findViewById(R.id.h1501_img01);
        userThread myThread = new userThread(this);
        myThread.start();

//        lt001 = (TextView) findViewById(R.id.lt001);
//        lt002 = (TextView) findViewById(R.id.lt002);
//        lt003 = (TextView) findViewById(R.id.lt003);
//        lmb001 = (Button) findViewById(R.id.lmb001);
//
//        b000 = (Button) findViewById(R.id.b000);
//        rt001 = (TextView) findViewById(R.id.rt001);
//        rt002 = (TextView) findViewById(R.id.rt002);
//        rt003 = (TextView) findViewById(R.id.rt003);
//        rmb001 = (Button) findViewById(R.id.rmb001);
//        // 設定 button 按鍵的事件
//        lt001.setOnClickListener(OnClick);
//        lt002.setOnClickListener(OnClick);
//        lt003.setOnClickListener(OnClick);
//        lmb001.setOnClickListener(OnClick); // 左側邊欄按鈕監聽
//        b000.setOnClickListener(OnClick);
//        rt001.setOnClickListener(OnClick);
//        rt002.setOnClickListener(OnClick);
//        rt003.setOnClickListener(OnClick);
//        rmb001.setOnClickListener(OnClick); // 右側邊欄按鈕監聽

        img01= (ImageButton)findViewById(R.id.h1501_imgbutton);
        img02=(ImageView)findViewById(R.id.h1501_img02);
        img03=(ImageView)findViewById(R.id.h1501_img03);
        img04=(ImageView)findViewById(R.id.h1501_img04);
        img05= (ImageView)findViewById(R.id.h1501_img05);
        img06=(ImageView)findViewById(R.id.h1501_img06);
        img07=(ImageView)findViewById(R.id.h1501_img07);
        img08=(ImageView)findViewById(R.id.h1501_img08);

        img01.setOnClickListener(OnClick02);
        img02.setOnClickListener(OnClick02);
        img03.setOnClickListener(OnClick02);
        img04.setOnClickListener(OnClick02);
        img05.setOnClickListener(OnClick02);
        img06.setOnClickListener(OnClick02);
        img07.setOnClickListener(OnClick02);
        img08.setOnClickListener(OnClick02);

        img = (CircleImgbutton) findViewById(R.id.h1501_icon);
        b007 = (ImageButton) findViewById(R.id.h1501_b007);
        b008 = (ImageButton) findViewById(R.id.h1501_b008);
        t001 = (ImageButton) findViewById(R.id.h1501_t0011);
        b001 = (Button)findViewById(R.id.h1501_b001);
        b002 = (Button)findViewById(R.id.h1501_b002);

        b007.setOnClickListener(b001ON);
        b008.setOnClickListener(b001ON);
        t001.setOnClickListener(b001ON);
        //img.setOnClickListener(b001ON);
        b001.setOnClickListener(b001ON);
        b002.setOnClickListener(b001ON);

    }

    private View.OnClickListener b001ON = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.h1501_b007:
                    finish();
                        break;

                case R.id.h1501_b008:
                    uri = Uri.parse("https://m.facebook.com/city2farmer");
                    Intent it=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(it);
                    break;

                case R.id.h1501_t0011:
                    intent.putExtra("class_title", getString(R.string.h1301_t003));
                    intent.setClass(H1501.this, H1302_about.class);
                    startActivity(intent);
                    break;

                case R.id.h1501_b001:
                    intent.putExtra("class_title", getString(R.string.rt0000));
                    intent.setClass(H1501.this, H1501left.class);
                    startActivity(intent);
                    break;

                case R.id.h1501_b002:
                    intent.putExtra("class_title", getString(R.string.h1501_b002));
                    intent.setClass(H1501.this, H1501right.class);
                    startActivity(intent);
                    break;
            }
        }
    };


    Handler myHandler =new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 0:
                    imgSwi.setImageResource(R.drawable.h1511);
                    break;
                case 1:
                    imgSwi.setImageResource(R.drawable.h1512);
                    break;
                case 2:
                    imgSwi.setImageResource(R.drawable.h1513);
                    break;
                case 3:
                    imgSwi.setImageResource(R.drawable.h1514);
                    break;
            }

        }
    };
    private View.OnClickListener OnClick02=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.h1501_imgbutton:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v001),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,30,1470);
                    toast.show();
                    break;

                case R.id.h1501_img02:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v002),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,280,1470);
                    toast.show();
                    break;

                case R.id.h1501_img03:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v003),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,530,1470);
                    toast.show();
                    break;
                case R.id.h1501_img04:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v004),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,780,1470);
                    toast.show();
                    break;


                case R.id.h1501_img05:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v005),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,30,1780);
                    toast.show();
                    break;

                case R.id.h1501_img06:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v006),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,280,1780);
                    toast.show();
                    break;

                case R.id.h1501_img07:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v007),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,530,1780);
                    toast.show();
                    break;
                case R.id.h1501_img08:
                    toast=Toast.makeText(getApplicationContext(),getString(R.string.h1501_v008),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP |Gravity.START,780,1780);
                    toast.show();
                    break;

            }
        }
    };
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(getApplicationContext(), "請按右上返回鍵", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_return,menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHf15 == null) {
            dbHf15 = new HappyfarmDB(this, DB_FILE, null, DBversion);
        }
        //===登入====
        long_in();
    }

    //========登入後換圖片===============================
    public void long_in(){

        if(dbHf15 == null){
            dbHf15 = new HappyfarmDB(H1501.this, "happyfarm.db", null, DBversion);
        }
        String  u_pic=dbHf15.Find("r0205");
        boolean login_status = dbHf15.status();
        if(login_status){

        }else{
            CircleImgbutton cc = (CircleImgbutton) findViewById(R.id.h1501_icon);
            cc.setImageResource(R.drawable.googleg_color);
        }

        Uri user_uri;
        if(u_pic!=null){
            user_uri=Uri.parse(u_pic);
        }else{
            CircleImgbutton userImage = (CircleImgbutton) findViewById(R.id.h1501_icon);
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
                    CircleImgbutton userImage = (CircleImgbutton) findViewById(R.id.h1501_icon);
                    userImage.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(user_uri.toString().trim());
        }
        dbHf15.close();
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
