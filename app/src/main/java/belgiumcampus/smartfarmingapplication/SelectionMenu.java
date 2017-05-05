package belgiumcampus.smartfarmingapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

public class SelectionMenu extends AppCompatActivity {

    Button weatherB, waterTableB, irrigationB, cropGrowthB, soilMoistureB, aboutB;
    Intent weather, waterTable, irriagation, cropGrowth, soilMousture, about;
    TextView dateDayTownTemp;
    String dataForDateDayTownTemp;

    private final static int INTERVAL = 60 * 60 * 1000; //0.30
    Handler mHandler = new Handler();


    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startRepeatingTask();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_menu);

        weatherB = (Button) findViewById(R.id.weatherButton);
        waterTableB = (Button) findViewById(R.id.waterTableAndRainfallButton);
        irrigationB = (Button) findViewById(R.id.irrigationButton);
        cropGrowthB = (Button) findViewById(R.id.cropGrowthButton);
        soilMoistureB = (Button) findViewById(R.id.soilMoistureButton);
        aboutB = (Button) findViewById(R.id.aboutButton);

        dateDayTownTemp = (TextView) findViewById(R.id.dateDayTownTemp);

        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");
        dateDayTownTemp.setTypeface(montserratBold);
        weatherB.setTypeface(robotoRegular);
        waterTableB.setTypeface(robotoRegular);
        irrigationB.setTypeface(robotoRegular);
        cropGrowthB.setTypeface(robotoRegular);
        soilMoistureB.setTypeface(robotoRegular);
        aboutB.setTypeface(robotoRegular);

        long date = System.currentTimeMillis();
        //april 26 2017, wednesday.
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy, EEEE");



        dataForDateDayTownTemp = sdf.format(date);

        startRepeatingTask();

IrrigationObject.GetHistory(getApplicationContext());


//

       String receivedData = WeatherObject.SelectionMenuDisplay(this.getApplicationContext(),dataForDateDayTownTemp);

        dateDayTownTemp.setText(receivedData);


    }

    void startRepeatingTask()
    {
        mHandlerTask.run();
    }
    void stopRepeatingTask()
    {
        mHandler.removeCallbacks(mHandlerTask);
    }

    public void switchToWeather(View v)
    {
        weather = new Intent(getApplicationContext(), Weather.class);
        startActivity(weather);
    }

    public void switchToIrrigation(View v)
    {

    }

    public void switchToCropGrowth(View v)
    {

    }

    public void switchToSoilMoisture(View v)
    {

    }

    public void switchToAbout(View v)
    {

    }

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
      WeatherObject.SelectionMenuDisplay(getApplicationContext(),dataForDateDayTownTemp);//could cause possible problems ?
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };



}
