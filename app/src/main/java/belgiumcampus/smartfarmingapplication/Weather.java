package belgiumcampus.smartfarmingapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Weather extends AppCompatActivity {

    TextView weatherTitle, weatherData;
    String dataForWeatherDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.weatherToolBar);
        setSupportActionBar(toolbar);

        weatherTitle = (TextView) findViewById(R.id.weatherTitle);
        weatherData = (TextView) findViewById(R.id.weatherTodayDate);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        weatherTitle.setTypeface(montserratBold);
        weatherData.setTypeface(montserratBold);

        WeatherObject currentWeatherObject; //contains current weather

        //this list contains the following values:
        //01;20;30;Sunny
        //Day;Min;Max;Description
        List<String> eightDayForecastList;


        //this list contains the following values:
        //5;1;0.2;
        //Month;day;percipitation
        List<String> thirtyDayForecastList;
        long date = System.currentTimeMillis();
        //april 26 2017
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");
        dataForWeatherDate = sdf.format(date).toUpperCase();
        weatherData.setText("TODAY, " + dataForWeatherDate);

String receivedData = "No;Data";
        try {
             receivedData = new AsyncServerAccess(this.getApplicationContext()).execute("CurrentWeather",  "8"   ,"1").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String[] objectData = receivedData.split(";");


         currentWeatherObject = new WeatherObject(objectData[0],objectData[1],objectData[2],objectData[3],objectData[4],objectData[5],objectData[6],objectData[7]);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String shortReceivedForecastData = "No;Data";
        try {

            shortReceivedForecastData = new AsyncServerAccess(this.getApplicationContext()).execute("WeatherForecast",  "6"   ,"8","1,2,3,6").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String[] shortForecastData = shortReceivedForecastData.split(";");

        eightDayForecastList = new ArrayList<String>();
        String concat;

        for (int i = 0; i < 32; i= i+4) {
        concat = shortForecastData[i].substring(8) +";"+ shortForecastData[i +2].substring(0,shortForecastData[i +2].indexOf(".")) + ";" + shortForecastData[i+1].substring(0,shortForecastData[i +1].indexOf(".")) + ";" + shortForecastData[i+3];
            eightDayForecastList.add( concat);
        }



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String longReceivedForecastData = "No;Data";
        try {
            longReceivedForecastData = new AsyncServerAccess(this.getApplicationContext()).execute("ThirtyDayForecast",  "3"   ,"30").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String[] longForecastData = longReceivedForecastData.split(";");
thirtyDayForecastList = new ArrayList<String>();

String longForecastConcat;

        for (int i = 0; i < 90; i= i+3) {

            longForecastConcat = longForecastData[i] +";"+ longForecastData[i+1] +";"+ longForecastData[i+2];
            thirtyDayForecastList.add(longForecastConcat);

        }



    }

}
