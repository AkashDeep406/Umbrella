package umbrella.ad.com.umbrella.data;

import android.support.annotation.VisibleForTesting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by ajayasha on 2/22/18.
 * Parser class for getting Weather Results
 *
 */

public class WeatherResultParser {

    public String value = null;
    public WeatherResult result;


    public WeatherResultParser(){
        result = new WeatherResult();
    }

    public WeatherResult updateAttributesfromJSON(JSONObject jsonobject) throws JSONException {
        Iterator<String> keyIterator = jsonobject.keys();

        while(keyIterator.hasNext()){
            String key = keyIterator.next();

            Object object = jsonobject.get(key);
            if(object instanceof JSONObject){
                Iterator<String> objectkey = ((JSONObject) object).keys();
                result = loadValuesFromResponse(objectkey, (JSONObject) object);

            }

            if(object instanceof JSONArray){
                JSONArray jsonArray = jsonobject.getJSONArray(key);
                for(int i = 0 ; i < jsonArray.length() ; i++){
                    JSONObject arrayObject = jsonArray.getJSONObject(i);
                    Iterator<String> arrayObjectkeys = arrayObject.keys();
                    result = loadValuesFromResponse(arrayObjectkeys, arrayObject);

                }

            }

        }

        return result;

    }



    private WeatherResult loadValuesFromResponse(Iterator<String> objectkeys, JSONObject jsonObject) throws JSONException {

        while(objectkeys.hasNext()){
            String attributeKey = objectkeys.next();
            Object attributeObj = jsonObject.get(attributeKey);

            if(attributeObj instanceof String){
                value = (String) attributeObj;

            }

            if(attributeObj instanceof  Double){
                Double floatValue = (Double) attributeObj;
                value = Double.toString(floatValue);

            }

            if(value != null){
                result.put(attributeKey, value);

            }

        }

        return result;

    }



    @VisibleForTesting
    public  WeatherResult getWeatherResult(){
        return result;


    }



}
