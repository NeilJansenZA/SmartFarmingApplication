package belgiumcampus.smartfarmingapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

public class SelectionMenu extends AppCompatActivity {


    TextView tvdateDayTownTemp;
    String dateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_menu);
        tvdateDayTownTemp = (TextView)findViewById(R.id.dateDayTownTemp);

        long date = System.currentTimeMillis();
                                            //april 26 2017, wednesday
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy, EEEE");



         dateString = sdf.format(date);

        startRepeatingTask();
        tvdateDayTownTemp.setText(dateString);
    }

    private final static int INTERVAL = 5000; //0.30
    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            readData();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };
    void startRepeatingTask()
    {
        mHandlerTask.run();
    }
    void stopRepeatingTask()
    {
        mHandler.removeCallbacks(mHandlerTask);
    }
    public void readData()
    {
        String receivedData = "No Data";
        try {
                                                                                            //Procedure name,Columns,Rows
            receivedData = new AsyncServerAccess(this.getApplicationContext()).execute("CurrentWeather",  "3"   ,"1").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String [] Data = receivedData.split(";");

        try {
          int currentTemp = Integer.parseInt(Data[2].substring(0,Data[2].indexOf(".")));
            String townName = "TownName";

            String display = String.format("%s \n%s %o°C",dateString ,townName, currentTemp);
            tvdateDayTownTemp.setText(display);
        }catch (Exception e)
        {
            Log.e("error:", e.getMessage());
        }

    }

}
