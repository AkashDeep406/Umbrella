package umbrella.ad.com.umbrella;

import android.Manifest;
import android.os.AsyncTask;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import umbrella.ad.com.umbrella.data.WeatherResult;
import umbrella.ad.com.umbrella.data.WeatherResultParser;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WeatherResultParserTest extends InstrumentationTestCase{

    private CountDownLatch countDownLatch = new CountDownLatch();
    private WeatherResult result;
    private WeatherResultParser weatherResultParser = new WeatherResultParser();


    private void getParsedContent() throws InterruptedException {
        final AsyncTask<String, Void, WeatherResult> task = new AsyncTask<String, Void, WeatherResult>() {
            @Override
            protected WeatherResult doInBackground(String... strings) {
                result = weatherResultParser.getWeatherResult();
                return result;
            }


            @Override
            protected void onPostExecute(WeatherResult weatherResult) {
                super.onPostExecute(weatherResult);
                countDownLatch.countDown();
            }

        };


        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    task.execute();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }



        countDownLatch.await(30, TimeUnit.SECONDS);
        assertNotNull(result);



    }




    public void testCurrentTemp() throws Throwable{
        getParsedContent();

        assertEquals(result.getKeyTemperature(),result.getKeyTemperature());

    }


    public void testMinTemp() throws Throwable{
        getParsedContent();

        double min =  Double.valueOf(result.getKeyTempMin());

        assertTrue((-273.15 <= min) && (min <= 273.15));


    }



    public void testMaxTemp() throws Throwable{
        getParsedContent();

        double max = Double.valueOf(result.getKeyTempMin());
        assertTrue((-273.15 <= max) && (max <= 273.15));


    }



    public void testMainWeatherForecast() throws Throwable{
        getParsedContent();

        assertEquals(result.getKeyMain(), "Cloudy");
        assertEquals(result.getKeyMain(), "Clear");
        assertEquals(result.getKeyMain(), "Haze");
        assertEquals(result.getKeyMain(), "Sunny");

    }


    public void testWeatherDescription() throws Throwable{
        getParsedContent();


    }




}