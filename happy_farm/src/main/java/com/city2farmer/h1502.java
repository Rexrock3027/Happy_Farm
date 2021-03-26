package com.city2farmer;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class h1502 extends AppCompatActivity implements ViewSwitcher.ViewFactory, AdapterView.OnItemClickListener {
    // 圖庫的圖片資源索引
    private Integer[] imgArr = {
            R.drawable.h1521, R.drawable.h1522,
            R.drawable.h1523, R.drawable.h1524,
            R.drawable.h1525, R.drawable.h1526,
            R.drawable.h1527, R.drawable.h1528,
            
    };
    private Integer[] thumbImgArr = {
            R.drawable.h1521w, R.drawable.h1522w,
            R.drawable.h1523w, R.drawable.h1524w,
            R.drawable.h1525w, R.drawable.h1526w,
            R.drawable.h1527w, R.drawable.h1528w,
         
    };
    private GridView gridview;
    private ImageSwitcher imgSwi;
    private TextView t002;
    private String[] listarray;
    private ArrayList<Map<String, Object>> mList;
    private ImageButton back01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1502);
        setupViewCompoent();
    }

    private void setupViewCompoent() {
        t002 = (TextView) findViewById(R.id.h1502_t002);
        listarray = getResources().getStringArray(R.array.h1502_a001);
        mList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < listarray.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("textView", listarray[i]);
            //指定獲取陣列
            mList.add(item);
        }


        // 取得GridView元件
        gridview = (GridView) findViewById(R.id.h1502_g001);
        // 從資源類別R中取得介面元件
        imgSwi = (ImageSwitcher) findViewById(R.id.h1502_im01);
        imgSwi.setFactory((ViewSwitcher.ViewFactory) this); // 必須implements ViewSwitcher.ViewFactory
        //將縮圖填入 GridView
        gridview.setAdapter(new GridAdapter(this, thumbImgArr));

        // GridView元件的事件處理
        gridview.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        Intent intent=this.getIntent();
        String mode_title = intent.getStringExtra("class_title");
        this.setTitle(mode_title);

//        back01 = (ImageButton)findViewById(R.id.h1502_back01);
//        back01.setOnClickListener(OnClick);

    }
//    private Button.OnClickListener OnClick = new Button.OnClickListener(){
//
//        @Override
//        public void onClick(View v) {
//
//            back01.setOnClickListener(new Button.OnClickListener()
//            {
//                public void onClick(View v)
//                {
//                    /* 關閉activity */
//                    h1502.this.finish();
//                }
//            });
//            }
//    };

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        t002.setText( listarray[position]);
        int ss = (int) (Math.random() * 1 + 1);
        imgSwi.setAnimation(null);  //動畫劇本歸零
        imgSwi.clearAnimation();
        imgSwi.destroyDrawingCache();
        imgSwi.setOutAnimation(null);
//                imgSwi.setlnAnimation(null);
        imgSwi.setInAnimation(null);

        switch (ss) {
            case 2:
//                imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_alpha_out));
//                imgSwi.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in));
//                Toast.makeText(getApplicationContext(), getString(R.string.h1502_Alpha), Toast.LENGTH_LONG).show();
                break;
            case 1:
                imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.h1501anim_scale_rotate_out));
                imgSwi.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.h1501anim_scale_rotate_in));
//                Toast.makeText(getApplicationContext(), getString(R.string.h1502_scale_rotate), Toast.LENGTH_LONG).show();
                break;
            case 3:
//                imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_out));
//                imgSwi.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_in));
//                Toast.makeText(getApplicationContext(), getString(R.string.h1502_trans), Toast.LENGTH_LONG).show();
                break;

            case 5:
//                Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_trans_bounce);
//                anim.setInterpolator(new BounceInterpolator());
//                imgSwi.setAnimation(anim);
//                Toast.makeText(getApplicationContext(), getString(R.string.h1502_trans_bounce), Toast.LENGTH_LONG).show();
                break;
        }
//
//        if(ss==1) {imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_alpha_out));
//            imgSwi.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in));
//            Toast.makeText(getApplicationContext(), getString(R.string.h1502_alpha), Toast.LENGTH_LONG).show();
//        }else if(ss==2){
//            imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_scale_rotate_out));
//            imgSwi.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_scale_rotate_in));
//            Toast.makeText(getApplicationContext(), getString(R.string.h1502_scale_rotate), Toast.LENGTH_LONG).show();
//        }else if(ss==3){
//            imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_out));
//            imgSwi.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_in));
//            Toast.makeText(getApplicationContext(), getString(R.string.h1502_trans), Toast.LENGTH_LONG).show();
//        }else if(ss==4){
//            Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_trans_bounce);
//            anim.setInterpolator(new BounceInterpolator());
//            imgSwi.setAnimation(anim);
//            Toast.makeText(getApplicationContext(), getString(R.string.h1502_trans_bounce), Toast.LENGTH_LONG).show();
//        }
//    }


        // stopAnimation(view);
        // 1 alpha 漸變透明度動畫效果
        // 2 translate 畫面轉換位置移動動畫效果
        // 3 scale 漸變尺寸伸縮動畫效果
        // 4 rotate 畫面轉移旋轉動畫效果
        // 5 Bounce 畫面動畫彈跳效果

//        imgSwi.setAnimation(null);  //動畫劇本歸零
//        imgSwi.clearAnimation();
//
////        imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_alpha_out));
////        imgSwi.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_alpha_in));
////
////        Toast.makeText(getApplicationContext(),getString(R.string.h1502_alpha),Toast.LENGTH_LONG).show();
//
////                imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_out));
////        imgSwi.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_in));
////
////        Toast.makeText(getApplicationContext(),getString(R.string.h1502_scale_rotate),Toast.LENGTH_LONG).show();
//
////        imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_trans_out));
////        imgSwi.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_trans_in));
//
//         Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_trans_bounce);
//        anim.setInterpolator(new BounceInterpolator());
//        imgSwi.setAnimation(anim);

        imgSwi.setImageResource(imgArr[position]);  //position 選擇第幾張圖


//        Toast.makeText(getApplicationContext(),getString(R.string.h1502_trans_bounce),Toast.LENGTH_LONG).show();

    }

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
}
