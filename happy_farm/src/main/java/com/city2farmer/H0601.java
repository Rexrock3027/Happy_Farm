package com.city2farmer;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class H0601 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String DB_FILE = "happyfarm.db", DB_TABLE06 = "HA0600";
    private static final String TAG ="tcnr06==>" ;
    private int DBversion = HappyfarmDB.VERSION;
    private HappyfarmDB dbHper06;
    private ArrayList<String> recSet06;
    private String sqlctl;
    private Spinner sp01, sp02;
    private TextView f001, f001a;
    private EditText f002;
    private CircleImgView img01, img02;
    private LinearLayout pestshow, solshow;
    private String lSeason, lCrop;
    private ImageView home, imgshow,fans;
    private LinearLayout dlgset;
    //private String SE001, CroNam;
    private int index=0;
    private ImageView myname06;
    private Intent intent = new Intent();
    //private String msg = null;
    private int bb;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        enableStrictMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h0601);
        setupViewComponent();
    }

    private void setupViewComponent() {
        Intent intent = this.getIntent();
        String mode_title = intent.getStringExtra("class_title");
        this.setTitle(mode_title);

        sp01 = (Spinner) findViewById(R.id.h0601_sp01);
        sp02 = (Spinner) findViewById(R.id.h0601_sp02);
        f001 = (TextView) findViewById(R.id.h0601_f001);
        f001a = (TextView) findViewById(R.id.h0601_f001a);
        f002 = (EditText) findViewById(R.id.h0601_f002);
        img01 = (CircleImgView) findViewById(R.id.h0601_img01);
        img02 = (CircleImgView) findViewById(R.id.h0601_img02);
        pestshow = (LinearLayout) findViewById(R.id.pestshow);
        solshow = (LinearLayout) findViewById(R.id.solshow);

        fans = (ImageView) findViewById(R.id.h0601_fans);
        home = (ImageView) findViewById(R.id.h0601_home);
        myname06 = (ImageView) findViewById(R.id.myname06);
        home.setOnClickListener(b002ON);
        fans.setOnClickListener(b002ON);
        myname06.setOnClickListener(b002ON);

// -------------------------設定 spinner item 選項------------
//        選擇季節的adapter
        ArrayAdapter<CharSequence> Seasonlist = ArrayAdapter
                .createFromResource(this, R.array.h0601_season,
                        android.R.layout.simple_spinner_item);
        Seasonlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp01.setAdapter(Seasonlist);

//        監聽spinner和button
        sp01.setOnItemSelectedListener(this);
        sp02.setOnItemSelectedListener(spCrop);
        img01.setOnClickListener(img01On);
        img02.setOnClickListener(img02On);
        initDB();
        layoutclose();
    }



    private View.OnClickListener b002ON = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.h0601_home:
                    finish();
                    break;

                case R.id.h0601_fans:
                    uri = Uri.parse("https://m.facebook.com/city2farmer");
                    Intent it=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(it);
                    break;

                case R.id.myname06:
                    intent.putExtra("class_title", getString(R.string.h1301_t003));
                    intent.setClass(H0601.this, H1302_about.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void layoutclose() {
        pestshow.setVisibility(View.INVISIBLE);
        solshow.setVisibility(View.INVISIBLE);
    }

    private void layoutshow() {
        pestshow.setVisibility(View.VISIBLE);
        solshow.setVisibility(View.VISIBLE);
    }

    private void enableStrictMode(H0601 h0601) {
        //-------------抓取遠端資料庫設定執行續------------------------------
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        .penaltyLog()
                        .build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectLeakedSqlLiteObjects()
                        .penaltyLog()
                        .build());
    }

    private void initDB() {
        if (dbHper06 == null)
            dbHper06 = new HappyfarmDB(this, DB_FILE, null, DBversion);
        recSet06 = dbHper06.getRecSet06();
        dbmysql();
        // dbHper06.createTB();

    }

    // 讀取MySQL 資料
    private void dbmysql() {
        sqlctl = "SELECT * FROM ha0600 ORDER BY id ASC";
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(sqlctl);
        try {
            String result06 = DBConnector06.executeQuery06(nameValuePairs);

            //chk_httpstate();
            int a=0;
            /**************************************************************************
             * SQL 結果有多筆資料時使用JSONArray
             * 只有一筆資料時直接建立JSONObject物件 JSONObject
             * jsonData = new JSONObject(result);
             **************************************************************************/
            JSONArray jsonArray = new JSONArray(result06);
            // -------------------------------------------------------
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
//--------------------------------------------------------
                int rowsAffected = dbHper06.clearRec06();                 // 匯入前,刪除所有SQLite資料
//--------------------------------------------------------
                // 處理JASON 傳回來的每筆資料
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    ContentValues newRow = new ContentValues();
                    // --(1) 自動取的欄位 --取出 jsonObject 每個欄位("key","value")-----------------------
                    Iterator itt = jsonData.keys();
                    while (itt.hasNext()) {
                        String key = itt.next().toString();
                        String value = jsonData.getString(key); // 取出欄位的值
                        if (value == null) {
                            continue;
                        } else if ("".equals(value.trim())) {
                            continue;
                        } else {
                            jsonData.put(key, value.trim());
                        }
                        // ------------------------------------------------------------------
                        newRow.put(key, value.toString()); // 動態找出有幾個欄位
                        // -------------------------------------------------------------------
                    }
                    String id=jsonData.getString("ID").toString();
                    String SE001= jsonData.getString("SE001").toString();
                    String CroNam=jsonData.getString("CroNam").toString();
                    String PN001=jsonData.getString("PN001").toString();
                    String PU001=jsonData.getString("PU001").toString();
                    String ST001=jsonData.getString("ST001").toString();
                    String PN002=jsonData.getString("PN002").toString();
                    String PU002=jsonData.getString("PU002").toString();
                    String ST002=jsonData.getString("ST002").toString();
                    // ---(2) 使用固定已知欄位---------------------------
                    newRow.put("id", id);
                    newRow.put("SE001", SE001);
                    newRow.put("CroNam", CroNam);
                    newRow.put("PN001", PN001);
                    newRow.put("PU001", PU001);
                    newRow.put("ST001", ST001);
                    newRow.put("PN002", PN002);
                    newRow.put("PU002", PU002);
                    newRow.put("ST002", ST002);
                    // -------------------加入SQLite---------------------------------------
                    long rowID = dbHper06.insertRec_m06(newRow);
                    //    Toast.makeText(getApplicationContext(), "共匯入 " + Integer.toString(jsonArray.length()) + " 筆資料", Toast.LENGTH_SHORT).show();
                }
                // ---------------------------
            } else {
                //   Toast.makeText(getApplicationContext(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            recSet06 = dbHper06.getRecSet06();  //重新載入SQLite
            //u_setspinner();
            // --------------------------------------------------------
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    //-----------------------------------季節下拉選單的監聽-----------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        lSeason = parent.getSelectedItem().toString();
//                選擇作物的adapter
        switch (lSeason) {
            case "選擇季節":
                ArrayAdapter<String> Croplist = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
                Croplist.add("選擇作物");
                Croplist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp02.setAdapter(Croplist);
                break;
            case "春":
                List<String> cropsp1 = dbHper06.getCroNam(lSeason);
                ArrayAdapter<String> Croplist1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cropsp1);
                Croplist1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp02.setAdapter(Croplist1);
                break;
            case "夏":
                List<String> cropsp2 = dbHper06.getCroNam(lSeason);
                ArrayAdapter<String> Croplist2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cropsp2);
                Croplist2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp02.setAdapter(Croplist2);
                break;
            case "秋":
                List<String> cropsp3 = dbHper06.getCroNam(lSeason);
                ArrayAdapter<String> Croplist3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cropsp3);
                Croplist3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp02.setAdapter(Croplist3);
                break;
            case "冬":
                List<String> cropsp4 = dbHper06.getCroNam(lSeason);
                ArrayAdapter<String> Croplist4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cropsp4);
                Croplist4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp02.setAdapter(Croplist4);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //    -------------------------------------------------作物的spinner-------------------------------------------------
    AdapterView.OnItemSelectedListener spCrop = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            lCrop = parent.getSelectedItem().toString();
            f002.setText("");
            if(lCrop=="選擇作物"){
                f001.setText("");
                f001a.setText("");
                f002.setText("");
                img01.setImageDrawable(getDrawable(R.drawable.h0601_b02thy));
                img02.setImageDrawable(getDrawable(R.drawable.h0601_v01bact));
                layoutclose();
            }else {
                recSet06 = dbHper06.getpest_info(lSeason, lCrop);
//                f001.setText(recSet06.get(1));
//                Picasso.get().load(recSet06.get(2)).into(img01);
//                f002.append(recSet06.get(0) + "\n" + recSet06.get(3));
//                f001a.setText(recSet06.get(4));
//                Picasso.get().load(recSet06.get(5)).into(img02);
                for (int i=0;i<recSet06.size();i++){
                    String[] fld = recSet06.get(i).split("#");
//                    mList.add(fld[0]);
//                    mList.add(fld[1]);
                    f001.setText(fld[0]);
                    Glide.with(img01).load(fld[2]).into(img01);
                    f002.append(fld[4] + "\n" + fld[5]);
                    f001a.setText(fld[1]);
                    Glide.with(img02).load(fld[3]).into(img02);
                }
                //f002.setText("有"+recSet06.size()+"筆資料");
                layoutshow();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    //------------------------------右上角的menu-------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_return, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Intent intent = new Intent();
        switch (item.getItemId()) {
//            case R.id.m_mysql:
//                dbmysql();
//                break;
//
//            case R.id.m_test:
//                intent.setClass(H0601.this, M1416.class);
//                startActivity(intent);
//                break;

            case R.id.action_settings:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //    ---------------------------------------點擊縮圖彈出大圖的方法----------------------------------------
    View.OnClickListener img01On = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Dialog imgdlg = new Dialog(H0601.this);
            imgdlg.setContentView(R.layout.img_dlg);
            imgshow = (ImageView) imgdlg.findViewById(R.id.h0601_imgdlg);
            dlgset = (LinearLayout) findViewById(R.id.h0601_dlg);
            for (int i=0;i<recSet06.size();i++) {
                String[] fld = recSet06.get(i).split("#");
                Glide.with(imgshow).load(fld[2]).into(imgshow);
            }

            imgdlg.setCanceledOnTouchOutside(true);//點擊圖片外時返回
            imgdlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//設定dialog的背景為透明
            imgdlg.show();
        }
    };

    View.OnClickListener img02On = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Dialog imgdlg = new Dialog(H0601.this);
            imgdlg.setContentView(R.layout.img_dlg);
            imgshow = (ImageView) imgdlg.findViewById(R.id.h0601_imgdlg);
            for (int i=0;i<recSet06.size();i++) {
                String[] fld = recSet06.get(i).split("#");
                Glide.with(imgshow).load(fld[3]).into(imgshow);
            }
            imgdlg.setCanceledOnTouchOutside(true);//點擊圖片外時返回
            imgdlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//設定dialog的背景為透明
            imgdlg.show();
        }
    };
    //----------------------------------------------------------------------------------------
//    private DialogInterface.OnClickListener aldBtListener = new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            switch (which) {
//                case BUTTON_POSITIVE:
//                    int rowsAffected = dbHper06.clearRec06();
//                    msg = "資料表已清空! 共刪除" + rowsAffected + "筆";
//                    Toast.makeText(H0601.this, msg, Toast.LENGTH_SHORT).show();
//                    break;
//
//                case BUTTON_NEGATIVE:
//                    msg = "取消刪除";
//                    Toast.makeText(H0601.this, msg, Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };

    //---------------------------------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        if (dbHper06 != null) {
            dbHper06.close();
            dbHper06 = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHper06 == null) {
            dbHper06 = new HappyfarmDB(this, DB_FILE, null, DBversion);
        }
        //===登入====
        long_in();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dbHper06 != null) {
            dbHper06.close();
            dbHper06 = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHper06 != null) {
            dbHper06.close();
            dbHper06 = null;
        }
        this.finish();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(getApplicationContext(), R.string.h0501_toast_back, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        SharedPreferences A01 = getSharedPreferences("AA", 0);

        bb = A01.getInt("AA", 0);
        if (bb == 1) {
            bb = 0;
            this.finish();
        }
        super.onStart();
    }

    //========登入後換圖片===============================
    public void long_in(){

        if(dbHper06 == null){
            dbHper06 = new HappyfarmDB(H0601.this, "happyfarm.db", null, DBversion);
        }
        String  u_pic=dbHper06.Find("r0205");
        boolean login_status = dbHper06.status();
        if(login_status){

        }else{
            CircleImgView cc = (CircleImgView) findViewById(R.id.h0601_icon);
            cc.setImageResource(R.drawable.googleg_color);
        }

        Uri user_uri;
        if(u_pic!=null){
            user_uri=Uri.parse(u_pic);
        }else{
            CircleImgView userImage = (CircleImgView)findViewById(R.id.h0601_icon);
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
                    CircleImgView userImage = (CircleImgView) findViewById(R.id.h0601_icon);
                    userImage.setImageBitmap(result);
                    super.onPostExecute(result);
                }
            }.execute(user_uri.toString().trim());
        }
        dbHper06.close();
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