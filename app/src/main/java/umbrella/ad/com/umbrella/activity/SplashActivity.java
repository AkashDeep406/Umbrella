package umbrella.ad.com.umbrella.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import umbrella.ad.com.umbrella.R;
import umbrella.ad.com.umbrella.data.AppUtils;

/**
 * Created by ajayasha on 2/21/18.
 */

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {

    private TextView tvsplashtitle,tvsubtitile;
    private Animation fadeIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(2000);
        fadeIn.setAnimationListener(this);

        tvsplashtitle = findViewById(R.id.tvsplashtitle);
        tvsplashtitle.setAnimation(fadeIn);

        tvsubtitile = findViewById(R.id.tvsubtitile);
        tvsubtitile.setAnimation(fadeIn);






    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
