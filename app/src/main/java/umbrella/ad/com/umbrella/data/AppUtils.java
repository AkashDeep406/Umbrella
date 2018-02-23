package umbrella.ad.com.umbrella.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ajayasha on 2/21/18.
 * App Utilities and Constants
 */

//        "coord":{
//        "lon":139.01,
//        "lat":35.02
//        },
//        "weather":[
//        {
//        "id":800,
//        "main":"Clear",
//        "description":"clear sky",
//        "icon":"01n"
//        }
//        ],
//        "base":"stations",
//        "main":{
//        "temp":285.514,
//        "pressure":1013.75,
//        "humidity":100,
//        "temp_min":285.514,
//        "temp_max":285.514,
//        "sea_level":1023.22,
//        "grnd_level":1013.75
//        },
//        "wind":{
//        "speed":5.52,
//        "deg":311
//        },
//        "clouds":{
//        "all":0
//        },
//        "dt":1485792967,
//        "sys":{
//        "message":0.0025,
//        "country":"JP",
//        "sunrise":1485726240,
//        "sunset":1485763863
//        },
//        "id":1907296,
//        "name":"Tawarano",
//        "cod":200
//        }

public class AppUtils {

    public static final int PERMISSION_LOCATION = 111;
    public static final float KELVIN_THRESHOLD = 273.15f;
    public static final String KEY_METRIC_CELCIUS = "°C";
    public static final String KEY_METRIC_FARENHEIT = "°F";



   public static int convertKelvinToCelcius(String temp){
       Double celcius = Double.valueOf(temp) -  KELVIN_THRESHOLD;

       return (int)Math.round(celcius);

   }



   public static int convertKelvinToFarenheit(String temp){
       Double kelvin = Double.valueOf(temp);

       double farenheit = (((kelvin - 273) * 9/5) + 32);

       return (int) Math.round(farenheit);

   }


   /**
   * https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
   * */
   public static String getTime(String time){
       DateFormat format = new SimpleDateFormat("hh:mm a");
       String timeformat = format.format(new Date()).toString();

       return timeformat;
   }

    /**
     *
     * check permission
     * @param context
     * @return bool
     */

    public static boolean checkLocationPermission(Context context){
        Activity activity = (Activity) context;
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);

        }else{
            return true;
        }

        return false;

    }


}
