package belgiumcampus.smartfarmingapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

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

        long date = System.currentTimeMillis();
        //april 26 2017
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");
        dataForWeatherDate = sdf.format(date).toUpperCase();
        weatherData.setText("TODAY, " + dataForWeatherDate);
    }

}
