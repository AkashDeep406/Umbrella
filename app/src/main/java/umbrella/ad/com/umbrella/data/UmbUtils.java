package umbrella.ad.com.umbrella.data;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ajayasha on 2/21/18.
 * Utility API related
 *
 */

public abstract class UmbUtils {
    private static final String TAG = "UmbUtils";
    public static final String API_KEY = "d3c3169ed32512dc6a532c4e788012da";
    public static final String BASE_URL = "api.openweathermap.org";
    public static final String URL_PATH_DATA = "data";
    public static final String URL_PATH_VERSION = "2.5";
    public static final String URL_PATH_WEATHER = "weather";
    public static final String UMB_API_CURRENT_WEATHER_CITY = "q";
    public static final String UMB_API_CURRENT_WEATHER_CITY_ID = "id";
    public static final int UMB_CONNECTION_TIMEOUT = 5000;



    /**
     *
     * @param code
     * @return
     * @throws MalformedURLException
     */
    public static URL constructEndPointAPI(String code) throws MalformedURLException{

        Uri.Builder builder =  new Uri.Builder();
        builder.scheme("http")
                .appendPath(BASE_URL)
                .appendPath(URL_PATH_DATA)
                .appendPath(URL_PATH_VERSION)
                .appendPath(URL_PATH_WEATHER)
                .appendQueryParameter(UMB_API_CURRENT_WEATHER_CITY, code)
                .appendQueryParameter("appid", API_KEY);

        String url  =  builder.build().toString();

        return new URL(url);

    }

    /**
     *
     * @param latitude, longitude
     * @return
     * @throws MalformedURLException
     */
    public static URL constructEndPointAPI(String latitude, String longitude) throws MalformedURLException{
        String lat =latitude;
        String lon =longitude;


        Uri.Builder builder =  new Uri.Builder();
        builder.scheme("http")
                .appendPath(BASE_URL)
                .appendPath(URL_PATH_DATA)
                .appendPath(URL_PATH_VERSION)
                .appendPath(URL_PATH_WEATHER)
                .appendQueryParameter("lat", lat)
                .appendQueryParameter("lon", lon)
                .appendQueryParameter("appid", API_KEY);

        String url  =  builder.build().toString();

        return new URL(url);

    }


    public static String getConnectionResult(HttpURLConnection connection) throws IOException, JSONException{
        String response = "";
        if( connection.getResponseCode() == HttpsURLConnection.HTTP_OK){
            response = readInputStream(connection.getInputStream());

        }else{
            response = connection.getErrorStream().toString();
        }

        return response;

    }



    public static String readInputStream(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);

        }
        inputStream.close();


        return stringBuilder.toString();

    }




}
