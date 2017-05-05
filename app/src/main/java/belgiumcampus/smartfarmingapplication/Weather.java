package belgiumcampus.smartfarmingapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Weather extends AppCompatActivity implements
        OnChartGestureListener,
        OnChartValueSelectedListener {

    TextView weatherTitle, weatherData, tvCurrentTemp, tvMinTemp, tvMaxTemp, tvPrecipitation, tvHumidity, tvWeeklyForecast, tv30DayForecast, dayDate, dayMin, dayMax, dayWth;
    String dataForWeatherDate, dataForCurrentTemp, dataForMinTemp, dataForMaxTemp, dataForPrecipitation, dataForHumidity;
    LineChart forecastGraph;
    WeatherObject currentWeatherObject;
    View vAppBar;
    ArrayList<String> eightDayForecastList, thirtyDayForecastList; /* "this list contains the following values: 01;20;30;Sunny Day;Min;Max;Description"
    - "this list contains the following values: 5;1;0.2; Month;day;precipitation"*/
    int convertFloat, i, highest = 0;
    ImageView imgWeatherIcon;
    String[] splitData;
    String[] day = new String[8];
    String[] min = new String[8];
    String[] max = new String[8];
    String[] desc = new String[8];
    String[] thirtyDay = new String[30];
    String[] thirtyPrecipitation = new String[30];
    TableLayout tblLayout;
    Boolean connection = true;

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
        loadActivity();
    }

    protected void loadActivity() {
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.weatherToolBar);
        setSupportActionBar(toolbar);

        vAppBar = findViewById(R.id.weatherToolBar);
        weatherTitle = (TextView) vAppBar.findViewById(R.id.activityTitle);
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
        forecastGraph = (LineChart) findViewById(R.id.forecastGraph);
        dayDate = (TextView) findViewById(R.id.dayDate);
        dayMin = (TextView) findViewById(R.id.showMin);
        dayMax = (TextView) findViewById(R.id.showMax);
        dayWth = (TextView) findViewById(R.id.showDescription);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");
        forecastGraph.setOnChartGestureListener(this);
        forecastGraph.setOnChartValueSelectedListener(this);

        weatherTitle.setText("WEATHER");

        weatherTitle.setTypeface(montserratBold);
        weatherData.setTypeface(montserratBold);
        tvWeeklyForecast.setTypeface(montserratBold);
        tv30DayForecast.setTypeface(montserratBold);
        tvCurrentTemp.setTypeface(robotoRegular);
        tvMinTemp.setTypeface(robotoRegular);
        tvMaxTemp.setTypeface(robotoRegular);
        tvPrecipitation.setTypeface(robotoRegular);
        tvHumidity.setTypeface(robotoRegular);
        dayDate.setTypeface(robotoRegular);
        dayMin.setTypeface(robotoRegular);
        dayMax.setTypeface(robotoRegular);
        dayWth.setTypeface(robotoRegular);

        long date = System.currentTimeMillis();
        //april 26 2017
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");
        dataForWeatherDate = sdf.format(date).toUpperCase();
        weatherData.setText("TODAY, " + dataForWeatherDate);

        currentWeatherObject = WeatherObject.CreateCurrentWeatherObject(this.getApplicationContext());

        if (currentWeatherObject != null) {
            connection = true;
            eightDayForecastList = WeatherObject.CreateEightDayForecastList(this.getApplicationContext());
            thirtyDayForecastList = WeatherObject.CreateThirtyDayForecastList(this.getApplicationContext());

            dataForCurrentTemp = String.valueOf(currentWeatherObject.getCurrentTemp());
            convertFloat = (int) (currentWeatherObject.getPrecipitation() * 100);
            dataForMinTemp = String.valueOf(currentWeatherObject.getMinTemp());
            dataForMaxTemp = String.valueOf(currentWeatherObject.getMaxTemp());
            dataForPrecipitation = String.valueOf(convertFloat);
            dataForHumidity = String.valueOf(currentWeatherObject.getCurrentHumidity());

            tvCurrentTemp.setText(dataForCurrentTemp + "°C");
            tvMinTemp.setText("MIN: " + dataForMinTemp + "°C");
            tvMaxTemp.setText("MAX: " + dataForMaxTemp + "°C");
            tvPrecipitation.setText("PRECIPITATION: " + dataForPrecipitation + "%");
            tvHumidity.setText("HUMIDITY: " + dataForHumidity + "%");

            try {
                Resources resIcon = getResources();
                String weatherIconName = currentWeatherObject.getWeatherDescription().toLowerCase().trim();
                weatherIconName = weatherIconName.replaceAll(" ", "");
                int resIDIcon = resIcon.getIdentifier(weatherIconName, "drawable", getPackageName());
                imgWeatherIcon.setImageResource(resIDIcon);
            } catch (Exception e) {
                e.printStackTrace();
                int failID = getResources().getIdentifier("clear", "drawable", getPackageName());
                imgWeatherIcon.setImageResource(failID);
            }

            i = 0;
            for (String reading : eightDayForecastList) {
                splitData = reading.split(";");
                day[i] = splitData[0];
                min[i] = splitData[1];
                max[i] = splitData[2];
                desc[i] = splitData[3];
                i++;
            }

            for (int r = 0; r < tableRowFields.length; r++) {
                for (int c = 0; c < tableRowFields[0].length; c++) {
                    if (c == 0) {
                        continue;
                    } else if (r == 3) {
                        imageViewArray[0][c] = (ImageView) findViewById(tableRowFields[r][c]);
                        Resources res = getResources();
                        String drawableName = desc[1].toLowerCase().trim();
                        drawableName = drawableName.replaceAll(" ", "");
                        int resID = res.getIdentifier(drawableName, "drawable", getPackageName());
                        imageViewArray[0][c].setImageResource(resID);
                    } else {
                        textViewArray[r][c] = (TextView) findViewById((tableRowFields[r][c]));

                        if (r == 0) {
                            textViewArray[r][c].setText(day[c]);
                            textViewArray[r][c].setTypeface(robotoRegular);
                        } else if (r == 1) {
                            textViewArray[r][c].setText(min[c]);
                            textViewArray[r][c].setTypeface(robotoRegular);
                        } else {
                            textViewArray[r][c].setText(max[c]);
                            textViewArray[r][c].setTypeface(robotoRegular);
                        }
                    }
                }
            }

            i = 0;
            for (String reading : thirtyDayForecastList) {
                splitData = reading.split(";");
                thirtyDay[i] = splitData[1];
                thirtyPrecipitation[i] = splitData[2];
                i++;
            }

            setData();
            Legend l = forecastGraph.getLegend();
            l.setForm(Legend.LegendForm.LINE);

            forecastGraph.setTouchEnabled(true);
            forecastGraph.setDragEnabled(true);
            forecastGraph.setScaleEnabled(true);


            YAxis leftAxis = forecastGraph.getAxisLeft();
            leftAxis.setAxisMaxValue(highest);
            leftAxis.setAxisMinValue(0f);
            //leftAxis.setYOffset(20f);
            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            leftAxis.setDrawZeroLine(false);

            // limit lines are drawn behind data (and not on top)
            leftAxis.setDrawLimitLinesBehindData(true);

            forecastGraph.getAxisRight().setEnabled(false);

            forecastGraph.invalidate();
        } else {
            confirmConnection(Weather.this);
        }
    }

    private void setData()
    {
        ArrayList<String> xVals = setXAxisValues();
        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet setOne;

        setOne = new LineDataSet(yVals, "");
        setOne.setColor(Color.WHITE);
        setOne.setCircleColor(Color.BLACK);
        setOne.setLineWidth(1f);
        setOne.setCircleRadius(3f);
        setOne.setDrawCircleHole(false);
        setOne.setValueTextSize(9f);
        setOne.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setOne);

        LineData data = new LineData(xVals, dataSets);
        forecastGraph.setData(data);
        forecastGraph.getLegend().setEnabled(false);
    }

    private ArrayList<String> setXAxisValues ()
    {
        ArrayList<String> xVals = new ArrayList<String>();

        for(String day : thirtyDay)
        {
            xVals.add(day);
        }

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues()
    {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        int pos = 0;
        double convert;
        highest = 0;

       for(String data : thirtyPrecipitation)
        {
            convert = Double.parseDouble(data);
            convert = Math.ceil(convert);

            yVals.add(new Entry((int) convert, pos));
            if((int) convert > highest)
            {
                highest = (int) convert;
            }
            pos++;
        }

        return yVals;
    }

    private void confirmConnection(Activity context) {
        final AlertDialog alert = new AlertDialog.Builder(
                new ContextThemeWrapper(context, R.style.AppTheme))
                .create();
        alert.setTitle("Connection Error");
        alert.setMessage("No internet connection");
        alert.setCancelable(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        connection = false;
                        alert.dismiss();
                    }
                });

        alert.show();
    }

    @Override
    public void onChartGestureStart(MotionEvent me,
                                    ChartTouchListener.ChartGesture
                                            lastPerformedGesture) {

        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me,
                                  ChartTouchListener.ChartGesture
                                          lastPerformedGesture) {

        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            forecastGraph.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2,
                             float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + forecastGraph.getLowestVisibleXIndex()
                + ", high: " + forecastGraph.getHighestVisibleXIndex());

        Log.i("MIN MAX", "xmin: " + forecastGraph.getXChartMin()
                + ", xmax: " + forecastGraph.getXChartMax()
                + ", ymin: " + forecastGraph.getYChartMin()
                + ", ymax: " + forecastGraph.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
