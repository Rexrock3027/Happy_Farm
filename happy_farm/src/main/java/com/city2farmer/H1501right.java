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

public class H1501right extends AppCompatActivity {
    private Button b000;
    private TextView rt001,rt002,rt003;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rightmenu);
        setupViewComponent();
    }

    private void setupViewComponent() {
        //	--設置標題--
        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);

        b000 = (Button) findViewById(R.id.b000);
        rt001 = (TextView) findViewById(R.id.rt001);
        rt002 = (TextView) findViewById(R.id.rt002);
        rt003 = (TextView) findViewById(R.id.rt003);

        b000.setOnClickListener(OnClick);
        rt001.setOnClickListener(OnClick);
        rt002.setOnClickListener(OnClick);
        rt003.setOnClickListener(OnClick);
    }

    private Button.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b000:
                    Intent it4 = new Intent();
                    it4.putExtra("class_title", getString(R.string.rt0000));
                    it4.setClass(H1501right.this, h1502.class);

                    startActivity(it4);
                    break;

                case R.id.rt001:
                    // 右側textview1 按下 執行工作

                    break;
                case R.id.rt002:
                    // 右側textview2 按下 執行工作

                    break;
                case R.id.rt003:
                    // 右側textview3 按下 執行工作

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
