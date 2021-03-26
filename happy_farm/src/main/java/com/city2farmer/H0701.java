package com.city2farmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class H0701 extends AppCompatActivity implements View.OnClickListener {

    private ImageView home,myname,fans;
    private Intent intent=new Intent();
    private static final String DB_FILE = "happyfarm.db";
    //private static final String DB_TABLE01 = "nutrition";
    private HappyfarmDB dbHper;
    private static final int DBversion = HappyfarmDB.VERSION;

    private Spinner h0701_s001, h0701_s002, h0701_s003, h0701_s004;
    private TextView obj, calories, minerals_K, Vit_C, Vit_A;
    private final int spring = 0, summer = 6, fall = 13, winner = 23, ID_Max = 29;

    private int bb;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h0701);
        setupViewCompoent();
        //HappyfarmDB dbHper = new HappyfarmDB(getApplicationContext(), DB_FILE, null, DBversion);
        //mFriendDb = Happyfarm07.COLUMN_Content.
        //u_dblist();
    }

    private void setupViewCompoent() {
        Intent intent = this.getIntent(); //	--設置標題--
        String mode_title = intent.getStringExtra("class_title");
        this.setTitle(mode_title);

        fans = (ImageView) findViewById(R.id.h0701_fans);
        home = (ImageView) findViewById(R.id.h0701_home);
        myname = (ImageView) findViewById(R.id.myname07);
        fans.setOnClickListener(b001ON);
        home.setOnClickListener(b001ON);
        myname.setOnClickListener(b001ON);

        obj = (TextView) findViewById(R.id.textView6);
        calories = (TextView) findViewById(R.id.textView8);
        minerals_K = (TextView) findViewById(R.id.textView9);
        Vit_A = (TextView) findViewById(R.id.textView11);
        Vit_C = (TextView) findViewById(R.id.textView12);

        h0701_s001 = (Spinner) findViewById(R.id.h0701_s001);//春天
        h0701_s001.setOnItemSelectedListener(h0701_s001ON); // 宣告Spinner監聽
        h0701_s002 = (Spinner) findViewById(R.id.h0701_s002);//夏天
        h0701_s002.setOnItemSelectedListener(h0701_s002ON);
        h0701_s003 = (Spinner) findViewById(R.id.h0701_s003);//秋天
        h0701_s003.setOnItemSelectedListener(h0701_s003ON);
        h0701_s004 = (Spinner) findViewById(R.id.h0701_s004);//冬天
        h0701_s004.setOnItemSelectedListener(h0701_s004ON);//以上8行可倆倆合併

//        initDB();//注意呼叫SQLite順序很重要
        dbHper = new HappyfarmDB(this, DB_FILE, null, DBversion);
    }

//    private void initDB() {
//            if (dbHper == null)//沒有連線的話，就開啟連線
//                dbHper=new HappyfarmDB(this,DB_FILE,null,DBversion);
//            //recSet = dbHper.getRecSet();
//
//    }

    private AdapterView.OnItemSelectedListener h0701_s001ON = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int index = position + spring; //春季指標目前要+0

            if (spring + 1 <= index & index <= summer) {
                showmsg(index);
                reload_summer();reload_fall();reload_winner();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private AdapterView.OnItemSelectedListener h0701_s002ON = new AdapterView.OnItemSelectedListener() { //夏季下拉選單
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int index = position + summer; //夏季指標目前要+6
            if (summer + 1 <= index & index <= fall) {
                showmsg(index);
                reload_spring();reload_fall();reload_winner();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private AdapterView.OnItemSelectedListener h0701_s003ON = new AdapterView.OnItemSelectedListener() {//秋季下拉選單
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int index = position + fall; //秋季指標目前要+13
            if (fall + 1 <= index & index <= winner) {
                showmsg(index);
                reload_spring();reload_summer();reload_winner();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private AdapterView.OnItemSelectedListener h0701_s004ON = new AdapterView.OnItemSelectedListener() {//冬季下拉選單
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int index = position + winner; //冬季指標目前要+22
            if (winner + 1 <= index & index <= ID_Max) {
                showmsg(index);
                reload_spring();reload_summer();reload_fall();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private void showmsg(int index) {
        String[] ay = dbHper.getOneRec(index);
        obj.setText(ay[1]);
        calories.setText("熱量:" + ay[2] + "kcal");
        minerals_K.setText("鉀:" + ay[3] + "mg");
        Vit_A.setText("Vit.A:" + ay[4] + "IU");
        Vit_C.setText("Vit.C:" + ay[5] + "mg");
//        reload();
    }

    private void reload() {
        reload_spring();
        reload_summer();
        reload_fall();
        reload_winner();
    }

    private void reload_spring() {
        ArrayAdapter<CharSequence> h0701_spring = ArrayAdapter.createFromResource
                (this, R.array.m0501_a001, android.R.layout.simple_spinner_item); //設定 Spinner樣式
        h0701_spring.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //設定Spinner外觀樣式
        h0701_s001.setAdapter(h0701_spring); //指定給Spinner使用
    }

    private void reload_summer() {
        ArrayAdapter<CharSequence> h0701_summer = ArrayAdapter.createFromResource
                (this, R.array.m0501_b001, android.R.layout.simple_spinner_item);
        h0701_summer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        h0701_s002.setAdapter(h0701_summer);
    }

    private void reload_fall() {
        ArrayAdapter<CharSequence> h0701_fall = ArrayAdapter.createFromResource
                (this, R.array.m0501_c001, android.R.layout.simple_spinner_item);
        h0701_fall.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        h0701_s003.setAdapter(h0701_fall);
    }

    private void reload_winner() {
        ArrayAdapter<CharSequence> h0701_winner = ArrayAdapter.createFromResource
                (this, R.array.m0501_d001, android.R.layout.simple_spinner_item);
        h0701_winner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        h0701_s004.setAdapter(h0701_winner);
    }

    private View.OnClickListener b001ON=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.h0701_home:
                    finish();
                    break;

                case R.id.h0701_fans:
                    uri = Uri.parse("https://m.facebook.com/city2farmer");
                    Intent it=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(it);
                    break;

                case R.id.myname07:
                    intent.putExtra("class_title",getString(R.string.h1301_t003));
                    intent.setClass(H0701.this, H1302_about.class);
                    startActivity(intent);
                    break;
            }

        }
    };


    @Override
    public void onClick(View v) {

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_return , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        this.finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), R.string.h0501_toast_back,Toast.LENGTH_LONG).show();

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
        if (dbHper == null) {
            dbHper = new HappyfarmDB(this, DB_FILE, null, DBversion);
        }
        //===登入====
        long_in();
    }

    //========登入後換圖片===============================
    public void long_in(){

        if(dbHper == null){
            dbHper = new HappyfarmDB(H0701.this, "happyfarm.db", null, DBversion);
        }
        String  u_pic=dbHper.Find("r0205");
        boolean login_status = dbHper.status();
        if(login_status){

        }else{
            CircleImgView cc = (CircleImgView) findViewById(R.id.h0701_icon);
            cc.setImageResource(R.drawable.googleg_color);
        }

        Uri user_uri;
        if(u_pic!=null){
            user_uri=Uri.parse(u_pic);
        }else{
            CircleImgView userImage = (CircleImgView)findViewById(R.id.h0701_icon);
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
                    CircleImgView userImage = (CircleImgView) findViewById(R.id.h0701_icon);
                    userImage.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(user_uri.toString().trim());
        }
        dbHper.close();
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