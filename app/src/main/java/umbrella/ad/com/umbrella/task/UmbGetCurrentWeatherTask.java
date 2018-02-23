package umbrella.ad.com.umbrella.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import umbrella.ad.com.umbrella.R;
import umbrella.ad.com.umbrella.data.UmbUtils;
import umbrella.ad.com.umbrella.data.WeatherResult;
import umbrella.ad.com.umbrella.data.WeatherResultParser;

/**
 * Created by ajayasha on 2/21/18.
 */

public class UmbGetCurrentWeatherTask extends AsyncTask<String, Void, WeatherResult> {
    private final String TAG = this.getClass().getSimpleName();
    private OnCompletionListener mListener;
    private String currentLatitude;
    private String currentLongitude;
    private HttpURLConnection httpURLConnection;
    private URL url;
    private ProgressBar progressDialog;
    private Context mContext;
    WeatherResultParser resultParser;



    public UmbGetCurrentWeatherTask(UmbGetCurrentWeatherTask.OnCompletionListener listener, Context context){
        super();
        this.mListener =  listener;
        this.mContext = context;
        resultParser = new WeatherResultParser();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected WeatherResult doInBackground(String... strings) {
        currentLatitude = strings[0];
        currentLongitude = strings[1];

        Log.d(TAG,  "currentLatitude"+currentLatitude +"&"+ "currentLongitude"+"&appid="+currentLongitude);
        WeatherResult result = new WeatherResult();
        try{
            url  = UmbUtils.constructEndPointAPI(currentLatitude, currentLongitude);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(UmbUtils.UMB_CONNECTION_TIMEOUT);

           String response = UmbUtils.getConnectionResult(httpURLConnection);
           JSONObject jsonObject = new JSONObject(response);

           result = resultParser.updateAttributesfromJSON(jsonObject);

        }catch (IOException e){
                e.printStackTrace();

        }catch (JSONException e){
            e.printStackTrace();
        }


        httpURLConnection.disconnect();
        return result;
    }


    @Override
    protected void onPostExecute(WeatherResult weatherResult) {
        super.onPostExecute(weatherResult);
      Log.d(TAG, "" +weatherResult.getKeyTemperature());


        if(mListener != null && !isCancelled()){
            mListener.onTaskCompleted(weatherResult);

        }

    }



    public interface OnCompletionListener{

        public void onTaskCompleted(WeatherResult weatherResult);

    }
}
