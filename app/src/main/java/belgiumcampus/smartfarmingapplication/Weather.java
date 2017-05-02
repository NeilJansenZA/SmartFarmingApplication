package belgiumcampus.smartfarmingapplication;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Weather extends AppCompatActivity {

    TextView weatherTitle, weatherData, tvCurrentTemp, tvMinTemp, tvMaxTemp, tvPrecipitation, tvHumidity, tvWeeklyForecast, tv30DayForecast;
    String dataForWeatherDate, dataForCurrentTemp, dataForMinTemp, dataForMaxTemp, dataForPrecipitation, dataForHumidity;
    GraphView forecastGraph;
    WeatherObject currentWeatherObject;
    List<String> eightDayForecastList, thirtyDayForecastList; /* "this list contains the following values: 01;20;30;Sunny Day;Min;Max;Description"
    - "this list contains the following values: 5;1;0.2; Month;day;precipitation"*/
    String receivedData, shortReceivedForecastData, longReceivedForecastData = "No;Data";
    String longForecastConcat;
    int convertFloat, i = 0;
    ImageView imgWeatherIcon;
    String[] splitData;
    String[] day = new String[8];
    String[] min = new String[8];
    String[] max = new String[8];
    String[] desc = new String[8];
    String[] thirtyDay = new String[30];
    String[] thirtyPrecipitation = new String[30];
    TableLayout tblLayout;

    int[][] tableRowFields = new int[][]
            {
                    {R.id.dayOne, R.id.dayTwo, R.id.dayThree, R.id.dayFour, R.id.dayFive, R.id.daySix, R.id.daySeven},
                    {R.id.oneMin, R.id.twoMin, R.id.threeMin, R.id.fourMin, R.id.fiveMin, R.id.sixMin, R.id.sevenMin},
                    {R.id.oneMax, R.id.twoMax, R.id.threeMax, R.id.fourMax, R.id.fiveMax, R.id.sixMax, R.id.sevenMax},
                    {R.id.imgOne, R.id.imgTwo, R.id.imgThree, R.id.imgFour, R.id.imgFive, R.id.imgSix, R.id.imgSeven}
            };
    TextView[][] textViewArray = new TextView[3][7];
    ImageView[][] imageViewArray = new ImageView[1][7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.weatherToolBar);
        setSupportActionBar(toolbar);

        weatherTitle = (TextView) findViewById(R.id.weatherTitle);
        weatherData = (TextView) findViewById(R.id.weatherTodayDate);
        tvCurrentTemp = (TextView) findViewById(R.id.tvCurrentTemp);
        tvMinTemp = (TextView) findViewById(R.id.tvMinTemp);
        tvMaxTemp = (TextView) findViewById(R.id.tvMaxTemp);
        tvPrecipitation = (TextView) findViewById(R.id.tvPrecipitation);
        tvHumidity = (TextView) findViewById(R.id.tvHumidity);
        tvWeeklyForecast = (TextView) findViewById(R.id.displayForecast);
        tv30DayForecast = (TextView) findViewById(R.id.linetext);
        imgWeatherIcon = (ImageView) findViewById(R.id.imgWeatherIcon);
        tblLayout = (TableLayout) findViewById(R.id.tblEightDayForecastTable);
        forecastGraph = (GraphView) findViewById(R.id.forecastGraph);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");

        weatherTitle.setTypeface(montserratBold);
        weatherData.setTypeface(montserratBold);
        tvWeeklyForecast.setTypeface(montserratBold);
        tv30DayForecast.setTypeface(montserratBold);
        tvCurrentTemp.setTypeface(robotoRegular);
        tvMinTemp.setTypeface(robotoRegular);
        tvMaxTemp.setTypeface(robotoRegular);
        tvPrecipitation.setTypeface(robotoRegular);
        tvHumidity.setTypeface(robotoRegular);

        long date = System.currentTimeMillis();
        //april 26 2017
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");
        dataForWeatherDate = sdf.format(date).toUpperCase();
        weatherData.setText("TODAY, " + dataForWeatherDate);

        try
        {
            receivedData = new AsyncServerAccess(this.getApplicationContext()).execute("CurrentWeather",  "8"   ,"1").get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        String[] objectData = receivedData.split(";");
        currentWeatherObject = new WeatherObject(objectData[0],objectData[1],objectData[2],objectData[3],objectData[4],objectData[5],objectData[6],objectData[7]);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try
        {
            shortReceivedForecastData = new AsyncServerAccess(this.getApplicationContext()).execute("WeatherForecast",  "6"   ,"8","1,2,3,6").get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        String[] shortForecastData = shortReceivedForecastData.split(";");

        eightDayForecastList = new ArrayList<String>();
        String concat;

        for (int i = 0; i < 32; i= i+4)
        {
            concat = shortForecastData[i].substring(8) +";"+ shortForecastData[i +2].substring(0,shortForecastData[i +2].indexOf(".")) + ";" + shortForecastData[i+1].substring(0,shortForecastData[i +1].indexOf(".")) + ";" + shortForecastData[i+3];
            eightDayForecastList.add( concat);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try
        {
            longReceivedForecastData = new AsyncServerAccess(this.getApplicationContext()).execute("ThirtyDayForecast",  "3"   ,"30").get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        String[] longForecastData = longReceivedForecastData.split(";");
        thirtyDayForecastList = new ArrayList<String>();

        for (int i = 0; i < 90; i= i+3)
        {
            longForecastConcat = longForecastData[i] +";"+ longForecastData[i+1] +";"+ longForecastData[i+2];
            thirtyDayForecastList.add(longForecastConcat);
        }

        dataForCurrentTemp = String.valueOf(currentWeatherObject.getCurrentTemp());
        convertFloat = (int)(currentWeatherObject.getPrecipitation() * 100);
        dataForMinTemp = String.valueOf(currentWeatherObject.getMinTemp());
        dataForMaxTemp = String.valueOf(currentWeatherObject.getMaxTemp());
        dataForPrecipitation = String.valueOf(convertFloat);
        dataForHumidity = String.valueOf(currentWeatherObject.getCurrentHumidity());

        tvCurrentTemp.setText(dataForCurrentTemp + "°C");
        tvMinTemp.setText("MIN: " + dataForMinTemp + "°C");
        tvMaxTemp.setText("MAX: " + dataForMaxTemp + "°C");
        tvPrecipitation.setText("PRECIPITATION: " + dataForPrecipitation + "%");
        tvHumidity.setText("HUMIDITY: " + dataForHumidity + "%");

        try
        {
            Resources resIcon = getResources();
            String weatherIconName = currentWeatherObject.getWeatherDescription().toLowerCase().trim();
            int resIDIcon = resIcon.getIdentifier(weatherIconName, "drawable", getPackageName());
            imgWeatherIcon.setImageResource(resIDIcon);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            int failID = getResources().getIdentifier("clear", "drawable", getPackageName());
            imgWeatherIcon.setImageResource(failID);
        }

        i = 0;
        for (String reading : eightDayForecastList)
        {
            splitData = reading.split(";");
            day[i] = splitData[0];
            min[i] = splitData[1];
            max[i] = splitData[2];
            desc[i] = splitData[3];
            i++;
        }

        for (int r = 0; r < tableRowFields.length; r++)
        {
            for (int c = 0; c < tableRowFields[0].length; c++)
            {
                if(c == 0)
                {
                    continue;
                }
                else if(r == 3)
                {
                    imageViewArray[0][c] = (ImageView) findViewById(tableRowFields[r][c]);
                    Resources res = getResources();
                    String drawableName = desc[r + 1].toLowerCase().trim();
                    int resID = res.getIdentifier(drawableName, "drawable", getPackageName());
                    imageViewArray[0][c].setImageResource(resID);
                }
                else
                {
                    textViewArray[r][c] = (TextView) findViewById((tableRowFields[r][c]));

                    if(r == 0)
                    {
                        textViewArray[r][c].setText(day[c + 1]);
                    }
                    else if(r == 1)
                    {
                        textViewArray[r][c].setText(min[c + 1]);
                    }
                    else
                    {
                        textViewArray[r][c].setText(max[c + 1]);
                    }
                }
            }
        }

        i = 0;
        for (String reading : thirtyDayForecastList)
        {
            splitData = reading.split(";");
            thirtyDay[i] = splitData[1];
            thirtyPrecipitation[i] = splitData[2];
            i++;
        }

        forecastGraph.getViewport().setYAxisBoundsManual(true);
        //forecastGraph.getViewport().setMaxY(100);
        //forecastGraph.getViewport().setMinY(0);

        LineGraphSeries<DataPoint> forecastSeries = new LineGraphSeries<>(generateData());
        forecastGraph.addSeries(forecastSeries);

        forecastSeries.setColor(Color.BLACK);
        forecastSeries.setDrawDataPoints(true);

        forecastGraph.setBackgroundColor(Color.GREEN);
        forecastGraph.getViewport().setScalable(true);
        forecastGraph.getViewport().setScalable(true);

        forecastGraph.getGridLabelRenderer().setTextSize(40);
        forecastGraph.getGridLabelRenderer().setGridColor(Color.GREEN);
    }

    private DataPoint[] generateData()
    {
        DataPoint[] values = new DataPoint[thirtyDay.length];
        for(i = 0; i < thirtyDay.length; i++)
        {
            double convert = Double.parseDouble(thirtyPrecipitation[i]) * 100;
            int x = Integer.parseInt(thirtyDay[i]);
            int y = (int)convert;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }

        return values;
    }
}
