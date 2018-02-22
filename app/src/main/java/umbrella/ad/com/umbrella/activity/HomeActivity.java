package umbrella.ad.com.umbrella.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import umbrella.ad.com.umbrella.R;
import umbrella.ad.com.umbrella.data.Constants;
import umbrella.ad.com.umbrella.data.WeatherResult;
import umbrella.ad.com.umbrella.task.UmbGetCurrentWeatherTask;

/**
 * Created by ajayasha on 2/21/18.
 */

public class HomeActivity extends AppCompatActivity  implements LocationListener {

    private UmbGetCurrentWeatherTask mGetWeatherTask;
    private Location mLocation;
    private LocationManager mLocationManager;
    private String latitude, longitude;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 10, this);
    }




    private void cancelTask(){
        if(mGetWeatherTask != null){
            mGetWeatherTask.cancel(true);
            mGetWeatherTask = null;

        }

    }


    private void getCurrentWeather(String latitude, String longitude){
        mGetWeatherTask = new UmbGetCurrentWeatherTask(new UmbGetCurrentWeatherTask.OnCompletionListener() {
            @Override
            public void onTaskCompleted(WeatherResult weatherResult) {
                Log.d("AD", "onTaskCompleted()");

            }
        });

        mGetWeatherTask.execute(latitude, longitude);

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        Toast.makeText(this, latitude + "  " + longitude, Toast.LENGTH_LONG).show();

        if(latitude != null && longitude != null){
            cancelTask();
            getCurrentWeather(latitude, longitude);

        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }



}
