package com.city2farmer;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class H1300 extends AppCompatActivity implements Animation.AnimationListener {

    private RelativeLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1300);
        setupViewCompoent();
    }

    private void setupViewCompoent() {
        layout = (RelativeLayout) findViewById(R.id.layout);
        Animation alpha = AnimationUtils.loadAnimation(this, R.anim.h1300_anim_alpha_out);
//                alpha.setInterpolator(new BounceInterpolator());
        layout.setAnimation(alpha);
        alpha.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startActivity(new Intent(this, H1301.class));
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
