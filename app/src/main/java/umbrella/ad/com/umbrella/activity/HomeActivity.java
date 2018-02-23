package umbrella.ad.com.umbrella.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import umbrella.ad.com.umbrella.R;
import umbrella.ad.com.umbrella.data.AppUtils;
import umbrella.ad.com.umbrella.data.WeatherResult;
import umbrella.ad.com.umbrella.task.UmbGetCurrentWeatherTask;

/**
 * Created by ajayasha on 2/21/18.
 */

public class HomeActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {

    private final String TAG = HomeActivity.this.getClass().getSimpleName();
    private UmbGetCurrentWeatherTask mGetWeatherTask;
    private Location mLocation;
    private LocationManager mLocationManager;
    private String latitude, longitude;
    private TextView tvdescription, tvtemp, tvmainweather, tvmin, tvmax, tvwindspeed, tvsunsettime, tvsunrisetime, tvchangemetric;
    private String weatherDescription, mainForecast, sunrisetime, sunsettime;
    private int temperatureCelcius, minTempCelcius, maxTempCelcius;
    private int temperatureFarenheit, minTempFarenheit, maxTempFarenheit;
    private double windspeed;
    private RelativeLayout rlcontent;
    private ProgressBar pbgettinginfo;
    private WeatherResult weatherResult;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        rlcontent = findViewById(R.id.rlcontent);
        rlcontent.setVisibility(View.GONE);


        tvdescription = findViewById(R.id.tvdescription);
        tvtemp = findViewById(R.id.tvtemp);
        tvmainweather = findViewById(R.id.tvmainweather);
        tvmax = findViewById(R.id.tvmax);
        tvmin = findViewById(R.id.tvmin);
        tvwindspeed = findViewById(R.id.tvwindspeed);
        pbgettinginfo = findViewById(R.id.pbgettinginfo);
        tvsunrisetime = findViewById(R.id.tvsunrisetime);
        tvsunsettime = findViewById(R.id.tvsunsettime);


        tvchangemetric = findViewById(R.id.tvchangemetric);
        tvchangemetric.setOnClickListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        //        used GPS provider not invoking onLocationChanged when reception is poor
        // mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10, this);
        //        mLocation =mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (AppUtils.checkLocationPermission(this)) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, this);
        }

    }

    private void cancelTask() {
        if (mGetWeatherTask != null) {
            mGetWeatherTask.cancel(true);
            mGetWeatherTask = null;

        }

    }


    private void getCurrentWeather(String latitude, String longitude) {
        mGetWeatherTask = new UmbGetCurrentWeatherTask(new UmbGetCurrentWeatherTask.OnCompletionListener() {
            @Override
            public void onTaskCompleted(WeatherResult result) {
                Log.d(TAG, "onTaskCompleted()");
                cancelTask();
                weatherResult = result;
                UpdateUI(weatherResult);
                pbgettinginfo.setVisibility(View.GONE);


            }
        }, this);

        mGetWeatherTask.execute(latitude, longitude);

    }


    /*
      Update/Load UI after weather details
     */
    private void UpdateUI(WeatherResult result) {
        if (result != null) {
            weatherDescription = result.getKeyDescription();
            temperatureCelcius = AppUtils.convertKelvinToCelcius(result.getKeyTemperature());
            minTempCelcius = AppUtils.convertKelvinToCelcius(result.getKeyTempMin());
            maxTempCelcius = AppUtils.convertKelvinToCelcius(result.getKeyTempMax());
            mainForecast = result.getKeyMain();
            windspeed = Double.valueOf(result.getKeySpeed());
            sunrisetime = AppUtils.getTime(result.getKeySunrise());
            sunsettime = AppUtils.getTime(result.getKeySunset());

        }

        updateUIForMetric(AppUtils.KEY_METRIC_CELCIUS);


    }


    public void updateUIForMetric(String metric) {

        tvdescription.setText(getResources().getString(R.string.txt_weatherdescription) + " " + weatherDescription);
        tvmainweather.setText(mainForecast);
        rlcontent.setVisibility(View.VISIBLE);
        tvwindspeed.setText(getResources().getString(R.string.txt_windspeed) + windspeed + "mph");

        tvsunrisetime.setText(getResources().getString(R.string.txt_todaysunrise) + sunrisetime);
        tvsunsettime.setText(getResources().getString(R.string.txt_todaysunset) + sunsettime);

        if (metric.equals(AppUtils.KEY_METRIC_CELCIUS)) {
            tvtemp.setText(String.valueOf(temperatureCelcius) + getResources().getString(R.string.txt_celciussymbol));
            tvmin.setText(getResources().getString(R.string.txt_tempmin) + String.valueOf(minTempCelcius) + getResources().getString(R.string.txt_celciussymbol));
            tvmax.setText(getResources().getString(R.string.txt_tempmax)  + String.valueOf(maxTempCelcius) + getResources().getString(R.string.txt_celciussymbol));

        }


        if (metric.equals(AppUtils.KEY_METRIC_FARENHEIT)) {
            temperatureFarenheit = AppUtils.convertKelvinToFarenheit(weatherResult.getKeyTemperature());
            minTempFarenheit = AppUtils.convertKelvinToFarenheit(weatherResult.getKeyTempMin());
            maxTempFarenheit  = AppUtils.convertKelvinToFarenheit(weatherResult.getKeyTempMax());

            tvtemp.setText(String.valueOf(temperatureFarenheit) + getResources().getString(R.string.txt_fahenheitsymbol));
            tvmin.setText(getResources().getString(R.string.txt_tempmin) + String.valueOf(minTempFarenheit) + getResources().getString(R.string.txt_fahenheitsymbol));
            tvmax.setText(getResources().getString(R.string.txt_tempmax) + String.valueOf(maxTempFarenheit) + getResources().getString(R.string.txt_fahenheitsymbol));


        }

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());

        Log.d(TAG, latitude + "  " + longitude);

        if (latitude != null && longitude != null) {
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


    @Override
    public void onClick(View view) {
        int rid = view.getId();

        switch (rid) {
            case R.id.tvchangemetric:
                String metric = (String) tvchangemetric.getText();
                if (metric.equals(AppUtils.KEY_METRIC_CELCIUS)) {
                    tvchangemetric.setText(AppUtils.KEY_METRIC_FARENHEIT);
                    updateUIForMetric((String)tvchangemetric.getText());
                }
                if (metric.equals(AppUtils.KEY_METRIC_FARENHEIT)) {
                    tvchangemetric.setText(AppUtils.KEY_METRIC_CELCIUS);
                    updateUIForMetric((String)tvchangemetric.getText());

                }
                break;
        }

    }
}
