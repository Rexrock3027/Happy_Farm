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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class H1302_about extends AppCompatActivity implements View.OnClickListener{
    private static final String DB_FILE= "happyfarm.db", DB_TABLE13 = "HA1300";
    private int DBversion= HappyfarmDB.VERSION;
    private HappyfarmDB dbHf13;


    private TextView p05, p06, p07, p12, p13, p14, p15, m05, m06, m07, m12, m13, m14, m15;
    private Uri uri;
    private Intent intent;
    private ImageView aa;
    private ImageButton b008;


//    private static final int REQUEST_CODE_ASK_PERMISSIONS=123;

//    private List<Integer> permissionsList = new ArrayList<Integer>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1302_about);
        setupViewCompoent();
    }



    private void setupViewCompoent() {

        //	--設置標題--
        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);

    p05=(TextView)findViewById(R.id.h1302_AboutUS05Phone);
    p06=(TextView)findViewById(R.id.h1302_AboutUS06Phone);
    p07=(TextView)findViewById(R.id.h1302_AboutUS07Phone);
    p12=(TextView)findViewById(R.id.h1302_AboutUS12Phone);
    p13=(TextView)findViewById(R.id.h1302_AboutUS13Phone);
    p14=(TextView)findViewById(R.id.h1302_AboutUS14Phone);
    p15=(TextView)findViewById(R.id.h1302_AboutUS15Phone);
    //------------
    m05=(TextView)findViewById(R.id.h1302_AboutUS05Mail);
    m06=(TextView)findViewById(R.id.h1302_AboutUS06Mail);
    m07=(TextView)findViewById(R.id.h1302_AboutUS07Mail);
    m12=(TextView)findViewById(R.id.h1302_AboutUS12Mail);
    m13=(TextView)findViewById(R.id.h1302_AboutUS13Mail);
    m14=(TextView)findViewById(R.id.h1302_AboutUS14Mail);
    m15=(TextView)findViewById(R.id.h1302_AboutUS15Mail);

    aa=(ImageView)findViewById(R.id.h1301_b007);
    b008=(ImageButton)findViewById(R.id.h1301_b008);
    //---------------
    p05.setOnClickListener(this);
    p06.setOnClickListener(this);
    p07.setOnClickListener(this);
    p12.setOnClickListener(this);
    p13.setOnClickListener(this);
    p14.setOnClickListener(this);
    p15.setOnClickListener(this);
    //-------------------
    m05.setOnClickListener(this);
    m06.setOnClickListener(this);
    m07.setOnClickListener(this);
    m12.setOnClickListener(this);
    m13.setOnClickListener(this);
    m14.setOnClickListener(this);
    m15.setOnClickListener(this);

    aa.setOnClickListener(this);
    b008.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.h1302_AboutUS05Phone:
                uri = Uri.parse("tel:"+getString(R.string.AboutUS05Phone));
                intent  = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS06Phone:
                uri = Uri.parse("tel:"+getString(R.string.AboutUS06Phone));
                intent  = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS07Phone:
                uri = Uri.parse("tel:"+getString(R.string.AboutUS07Phone));
                intent  = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS12Phone:
                uri = Uri.parse("tel:"+getString(R.string.AboutUS12Phone));
                intent  = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS13Phone:
                uri = Uri.parse("tel:"+getString(R.string.AboutUS13Phone));
                intent  = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS14Phone:
                uri = Uri.parse("tel:"+getString(R.string.AboutUS14Phone));
                intent  = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS15Phone:
                uri = Uri.parse("tel:"+getString(R.string.AboutUS15Phone));
                intent  = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
                //---------------------------------------------------------------
            case R.id.h1302_AboutUS05Mail:
                uri = Uri.parse("mailto:"+getString(R.string.AboutUS05Mail));
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS06Mail:
                uri = Uri.parse("mailto:"+getString(R.string.AboutUS06Mail));
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS07Mail:
                uri = Uri.parse("mailto:"+getString(R.string.AboutUS07Mail));
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS12Mail:
                uri = Uri.parse("mailto:"+getString(R.string.AboutUS12Mail));
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS13Mail:
                uri = Uri.parse("mailto:"+getString(R.string.AboutUS13Mail));
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS14Mail:
                uri = Uri.parse("mailto:"+getString(R.string.AboutUS14Mail));
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.h1302_AboutUS15Mail:
                uri = Uri.parse("mailto:"+getString(R.string.AboutUS15Mail));
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.h1301_b007:
                SharedPreferences A01 =getSharedPreferences("AA", 0);
                A01
                        .edit()
                        .putInt("AA",1)
                        .commit();
                this.finish();
                break;
            case R.id.h1301_b008:
                uri = Uri.parse("https://m.facebook.com/city2farmer");
                Intent it=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
                break;
        }

    }



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
        Toast.makeText(getApplicationContext(), R.string.h0501_toast_back,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHf13 == null) {
            dbHf13 = new HappyfarmDB(this, DB_FILE, null, DBversion);
        }
        //===登入====
        long_in();
    }

    //========登入後換圖片===============================
    public void long_in(){

        if(dbHf13 == null){
            dbHf13 = new HappyfarmDB(H1302_about.this, "happyfarm.db", null, DBversion);
        }
        String  u_pic=dbHf13.Find("r0205");
        boolean login_status = dbHf13.status();
        if(login_status){

        }else{
            CircleImgView cc = (CircleImgView) findViewById(R.id.h1302_icon);
            cc.setImageResource(R.drawable.googleg_color);
        }

        Uri user_uri;
        if(u_pic!=null){
            user_uri=Uri.parse(u_pic);
        }else{
            CircleImgView userImage = (CircleImgView)findViewById(R.id.h1302_icon);
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
                    CircleImgView userImage = (CircleImgView) findViewById(R.id.h1302_icon);
                    userImage.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(user_uri.toString().trim());
        }
        dbHf13.close();
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

