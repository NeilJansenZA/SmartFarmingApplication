package belgiumcampus.smartfarmingapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
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

    private final static int INTERVAL = 0; //0.30
    Handler mHandler = new Handler();

    /* Sover is die DateDayTownTemp ge hardcode net om a voorbeeld te wys van hoe dit moet lyk,
    gedink dis net makliker om dit in een TextView te he en dan net die data concatenate soos nodig */
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
        dateDayTownTemp.setText(dataForDateDayTownTemp);
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
            readData();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    public void readData()
    {
        String receivedData = "No Data";
        try
        {
            //Procedure name,Columns,Rows
            receivedData = new AsyncServerAccess(this.getApplicationContext()).execute("CurrentWeather",  "3"   ,"1").get();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        String [] Data = receivedData.split(";");

        try
        {
            int currentTemp = Integer.parseInt(Data[2].substring(0,Data[2].indexOf(".")));
            String townName = "Venda";

            String display = String.format("%s \n%s %s°C",dataForDateDayTownTemp ,townName, currentTemp);
            dateDayTownTemp.setText(display);
        }
        catch (Exception e)
        {
            Log.e("error:", e.getMessage());
        }

    }

}
