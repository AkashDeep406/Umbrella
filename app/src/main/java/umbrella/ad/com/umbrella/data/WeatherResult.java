package umbrella.ad.com.umbrella.data;


import java.util.Hashtable;


/**
 * Created by ajayasha on 2/21/18.
 *
 */

public class WeatherResult extends Hashtable<String, String> {
    public static final String KEY_WEATHER = "weather";
    public static final String KEY_CLOUDS = "clouds";
    public static final String KEY_SYS = "sys";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LON = "lon";
    public static final String KEY_ID = "id";
    public static final String KEY_MAIN = "main";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_ICON = "icon";
    public static final String KEY_BASE = "base";
    public static final String KEY_TEMPERATURE = "temp";
    public static final String KEY_PRESSURE = "pressure";
    public static final String KEY_HUMIDITY = "humidity";
    public static final String KEY_TEMP_MIN = "temp_min";
    public static final String KEY_TEMP_MAX = "temp_max";
    public static final String KEY_SEA_LEVEL = "sea_level";
    public static final String KEY_GROUND_LEVEL = "grnd_level";
    public static final String KEY_SPEED = "speed";
    public static final String KEY_DEG = "deg";
    public static final String KEY_ALL = "all";
    public static final String KEY_DT = "dt";
    public static final String KEY_MESSAGE = "country";
    public static final String KEY_SUNRISE = "sunrise";
    public static final String KEY_SUNSET = "sunset";
    public static final String KEY_NAME = "name";
    public static final String KEY_COD = "cod";
    String value = null;



    public  String getKeyLat() {
        return get(KEY_LAT);
    }

    public String getKeyLon() {
        return get(KEY_LON);
    }

    public String getKeyId() {
        return get(KEY_ID);
    }

    public String getKeyMain() {
        return get(KEY_MAIN);
    }

    public String getKeyDescription() {
        return get(KEY_DESCRIPTION);
    }

    public String getKeyIcon() {
        return get(KEY_ICON);
    }

    public String getKeyBase() {
        return get(KEY_BASE);
    }

    public String getKeyTemperature() {
        return get(KEY_TEMPERATURE);
    }

    public String getKeyPressure() {
        return get(KEY_PRESSURE);
    }

    public String getKeyHumidity() {
        return get(KEY_HUMIDITY);
    }

    public String getKeyTempMin() {
        return get(KEY_TEMP_MIN);
    }

    public String getKeyTempMax() {
        return get(KEY_TEMP_MAX);
    }

    public String getKeySeaLevel() {
        return get(KEY_SEA_LEVEL);
    }

    public String getKeyGroundLevel() {
        return get(KEY_GROUND_LEVEL);
    }

    public String getKeySpeed() {
        return get(KEY_SPEED);
    }

    public String getKeyDeg() {
        return get(KEY_DEG);
    }

    public String getKeyAll() {
        return get(KEY_ALL);
    }

    public String getKeyDt() {
        return get(KEY_DT);
    }

    public String getKeyMessage() {
        return get(KEY_MESSAGE);
    }

    public String getKeySunrise() {
        return get(KEY_SUNRISE);
    }

    public String getKeySunset(){ return get(KEY_SUNSET);}

    public String getKeyName() {
        return get(KEY_NAME);
    }

    public String getKeyCod() {
        return get(KEY_COD);
    }
}
