package belgiumcampus.smartfarmingapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class WaterRainfall extends AppCompatActivity implements
        OnChartGestureListener,
        OnChartValueSelectedListener {

    private TextView vTitle, tvCurrentWaterLevelTitle, tvHigh, tvMedium, tvLow, tvWaterRainfallRecommendationTitle, tvRecommendation, tvRainFallMeasurementTitle;
    private ImageView imgWaterLevel;
    private View vAppBar;
    private LineChart mChart;
    private WaterRainfallObject currentWaterRainfallObject;
    private String currentWaterLevel, recommendation;
    private ArrayList<String> graphDataList;
    private Boolean connection = true;
    private String[] weekRainfall = new String[7];
    private String[] weekDays = new String[7];
    private String[] splitData;
    private int i, highest = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_rainfall);

        loadActivity();
    }

    protected void loadActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.waterRainfallToolbar);
        setSupportActionBar(toolbar);

        vAppBar = findViewById(R.id.waterRainfallToolbar);
        vTitle = (TextView) vAppBar.findViewById(R.id.activityTitle);
        tvCurrentWaterLevelTitle = (TextView) findViewById(R.id.tvCurrentWaterLevelLabel);
        tvHigh = (TextView) findViewById(R.id.tvHigh);
        tvMedium = (TextView) findViewById(R.id.tvMedium);
        tvLow = (TextView) findViewById(R.id.tvLow);
        tvWaterRainfallRecommendationTitle = (TextView) findViewById(R.id.tvWaterRainfallRecommendationTitle);
        tvRainFallMeasurementTitle = (TextView) findViewById(R.id.tvRainfallMeasurementTitle);
        tvRecommendation = (TextView) findViewById(R.id.tvRecommendation);
        imgWaterLevel = (ImageView) findViewById(R.id.imgWaterLevel);
        mChart = (LineChart) findViewById(R.id.rainfallGraph);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");

        vTitle.setText("WATER TABLE & RAINFALL");

        vTitle.setTypeface(montserratBold);
        tvCurrentWaterLevelTitle.setTypeface(montserratBold);
        tvHigh.setTypeface(robotoRegular);
        tvMedium.setTypeface(robotoRegular);
        tvLow.setTypeface(robotoRegular);
        tvWaterRainfallRecommendationTitle.setTypeface(montserratBold);
        tvRainFallMeasurementTitle.setTypeface(montserratBold);
        tvRecommendation.setTypeface(robotoRegular);

        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);


        connection = true;
        currentWaterLevel = String.valueOf(currentWaterRainfallObject.getCurrentLevel(this.getApplicationContext()));
        recommendation = String.valueOf(currentWaterRainfallObject.getRecommendation(this.getApplicationContext()));
        graphDataList = currentWaterRainfallObject.getGraphData(this.getApplicationContext());

        tvRecommendation.setText(recommendation);

        try {
            Resources resIcon = getResources();
            String waterLevelName = currentWaterLevel.toLowerCase();
            waterLevelName = waterLevelName.replaceAll(" ", "");
            int resIDIcon = resIcon.getIdentifier(waterLevelName, "drawable", getPackageName());
            imgWaterLevel.setImageResource(resIDIcon);
        } catch (Exception e) {
            e.printStackTrace();
            int failID = getResources().getIdentifier("low", "drawable", getPackageName());
            imgWaterLevel.setImageResource(failID);
        }

        if (graphDataList != null) {
            i = 0;
            for (String reading : graphDataList) {
                splitData = reading.split(";");
                weekRainfall[i] = splitData[0];
                weekDays[i] = splitData[1];
                i++;
            }
        }

        setData();
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        mChart.setDescription("");

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMaxValue(highest);
        leftAxis.setAxisMinValue(0f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        mChart.invalidate();
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
        mChart.setData(data);
        mChart.getLegend().setEnabled(false);
    }

    private ArrayList<String> setXAxisValues()
    {
        ArrayList<String> xVals = new ArrayList<String>();
        for(String xData : weekDays)
        {
            xVals.add(xData);
        }

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues()
    {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        int pos = 0;
        highest = 0;

        for(String yData : weekRainfall)
        {
            int rainData;
            rainData = Integer.parseInt(yData);
            yVals.add(new Entry(rainData, pos));

            if(rainData > highest)
            {
                highest = rainData;
            }
            pos++;
        }

        return yVals;
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
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart.highlightValues(null);
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
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex()
                + ", high: " + mChart.getHighestVisibleXIndex());

        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin()
                + ", xmax: " + mChart.getXChartMax()
                + ", ymin: " + mChart.getYChartMin()
                + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
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

    public void Restart(View v)
    {
        onRestart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent waterTable = new Intent(WaterRainfall.this, WaterRainfall.class);
        startActivity(waterTable);
        finish();
    }

}
