package com.city2farmer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class H1501left extends AppCompatActivity {

    private TextView lt001,lt002,lt003;
    private Button lmb001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leftmenu);
        setupViewComponent();
    }

    private void setupViewComponent() {
        //	--設置標題--
        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);

        lt001 = (TextView) findViewById(R.id.lt001);
        lt002 = (TextView) findViewById(R.id.lt002);
        lt003 = (TextView) findViewById(R.id.lt003);
        //lmb001 = (Button) findViewById(R.id.lmb001);

        lt001.setOnClickListener(OnClick);
        lt002.setOnClickListener(OnClick);
        lt003.setOnClickListener(OnClick);
        //lmb001.setOnClickListener(OnClick); // 左側邊欄按鈕監聽
    }

    private Button.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.lt001:
                    // 左側textview1 按下 執行工作
                    Uri uri1 = Uri.parse("https://www.ttdares.gov.tw/ws.php?id=7556");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(it);

                    break;
                case R.id.lt002:
                    // 左側textview2 按下 執行工作
                    Uri uri2 = Uri.parse("https://www.ttdares.gov.tw/ws.php?id=2836");
                    Intent it2 = new Intent(Intent.ACTION_VIEW, uri2);
                    startActivity(it2);

                    break;
                case R.id.lt003:
                    // 左側textview3 按下 執行工作
                    Uri uri3 = Uri.parse("https://www.ttdares.gov.tw/ws.php?id=1582");
                    Intent it3 = new Intent(Intent.ACTION_VIEW, uri3);
                    startActivity(it3);

                    break;
            }
        }
    };

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


}
