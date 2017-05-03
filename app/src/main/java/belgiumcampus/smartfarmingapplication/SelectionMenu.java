package belgiumcampus.smartfarmingapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
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
    Boolean connection = true;

    private final static int INTERVAL = 60 * 60 * 1000; //0.30
    Handler mHandler = new Handler();



    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
    @Override
      protected void onResume()
    {
        super.onResume();
        startRepeatingTask();
    }

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

        String receivedData = "No Data";
        try
        {
            //Procedure name,Columns,Rows
            receivedData = new AsyncServerAccess(this.getApplicationContext()).execute("CurrentWeather",  "3"   ,"1").get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        try
        {
            String [] Data = receivedData.split(";");

            int currentTemp = Integer.parseInt(Data[2].substring(0,Data[2].indexOf(".")));
            String townName = "TownName";


            dateDayTownTemp.setText(String.format("%s \n%s %s°C",dataForDateDayTownTemp ,townName, String.valueOf(currentTemp)));

        }
        catch (Exception e)
        {
            dateDayTownTemp.setText("No internet connection");
            confirmConnection(SelectionMenu.this);
        }
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
        if(connection)
        {
            weather = new Intent(getApplicationContext(), Weather.class);
            startActivity(weather);
        }
        else
        {
            confirmConnection(SelectionMenu.this);
        }
    }

    public void switchToIrrigation(View v)
    {
        if(connection)
        {

        }
        else
        {
            confirmConnection(SelectionMenu.this);
        }
    }

    public void switchToCropGrowth(View v)
    {
        if(connection)
        {
            cropGrowth = new Intent(getApplicationContext(), CropGrowth.class);
            startActivity(cropGrowth);
        }
        else
        {
            confirmConnection(SelectionMenu.this);
        }
    }

    public void switchToSoilMoisture(View v)
    {
        if(connection)
        {

        }
        else
        {
            confirmConnection(SelectionMenu.this);
        }
    }

    public void switchToAbout(View v)
    {
        if(connection)
        {

        }
        else
        {
            confirmConnection(SelectionMenu.this);
        }

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

    private void confirmConnection(Activity context)
    {
        final AlertDialog alert = new AlertDialog.Builder(
                new ContextThemeWrapper(context, R.style.AppTheme))
                .create();
        alert.setTitle("Connection Error");
        alert.setMessage("No internet connection");
        alert.setCancelable(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        connection = false;
                        alert.dismiss();
                    }
                });

        alert.show();
    }
}


