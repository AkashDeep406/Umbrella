package umbrella.ad.com.umbrella.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import umbrella.ad.com.umbrella.R;
import umbrella.ad.com.umbrella.data.Constants;

/**
 * Created by ajayasha on 2/21/18.
 */

public class SplashActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission                                                  .ACCESS_COARSE_LOCATION}, Constants.PERMISSION_LOCATION);

        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        },2000);




    }


    @Override
    protected void onResume() {
        super.onResume();

    }

}
