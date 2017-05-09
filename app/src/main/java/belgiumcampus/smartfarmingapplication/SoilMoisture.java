package belgiumcampus.smartfarmingapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SoilMoisture extends AppCompatActivity {

    private TextView vTitle, tvOverallMoistureReading, tvMoistureReading, tvSensorInformationTitle, tvSensorReadingTitle, tvSensorConnection, tvSensorIdealReadingTitle, tvSoilMoistureRecommendation, tvSensorReading, tvIdealReading;
    private View vAppBar;
    private ImageView imgConnection;
    private String recommendationData, idealReadingData, overallReadingData, sensorReadingData;
    private double  moisturePercentage, overallReadingDouble;
    private int moistureInt, overallInt;
    private static final int idealReading = 60; //Depending on ideal Reading required
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_moisture);
        loadActivity();
    }

    protected void loadActivity()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.soilMoistureToolbar);
        setSupportActionBar(toolbar);

        vAppBar = findViewById(R.id.soilMoistureToolbar);
        vTitle = (TextView) vAppBar.findViewById(R.id.activityTitle);
        tvOverallMoistureReading =(TextView) findViewById(R.id.tvOverallMoistureReadingLabel);
        tvMoistureReading =(TextView) findViewById(R.id.tvOverallReading);
        tvSensorInformationTitle =(TextView) findViewById(R.id.tvSensorTitle);
        tvSensorReadingTitle =(TextView) findViewById(R.id.tvSensorReadingTitle);
        tvSensorConnection =(TextView) findViewById(R.id.tvSensorConnectionTitle);
        tvSensorIdealReadingTitle =(TextView) findViewById(R.id.tvSensorIdealReadingTitle);
        tvSoilMoistureRecommendation =(TextView) findViewById(R.id.tvSoilMoistureRecommendation);
        tvSensorReading =(TextView) findViewById(R.id.tvSoilMoistureReading);
        tvIdealReading =(TextView) findViewById(R.id.tvIdealReading);
        imgConnection =(ImageView) findViewById(R.id.imgConnection);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");

        vTitle.setText("SOIL MOISTURE");

        vTitle.setTypeface(montserratBold);
        tvOverallMoistureReading.setTypeface(montserratBold);
        tvMoistureReading.setTypeface(robotoRegular);
        tvSensorInformationTitle.setTypeface(montserratBold);
        tvSensorReadingTitle.setTypeface(robotoRegular);
        tvSensorConnection.setTypeface(robotoRegular);
        tvSensorIdealReadingTitle.setTypeface(robotoRegular);
        tvSoilMoistureRecommendation.setTypeface(robotoRegular);
        tvSensorReading.setTypeface(robotoRegular);
        tvIdealReading.setTypeface(robotoRegular);

        recommendationData = SoilMoistureObject.Recommendation(getApplicationContext());
        sensorReadingData = SoilMoistureObject.MoisturePercentage(getApplicationContext());

        try
        {
            moisturePercentage = Double.parseDouble(sensorReadingData);
            overallReadingDouble = Math.ceil((moisturePercentage + idealReading) / 2);
            moistureInt = (int) Math.ceil(moisturePercentage);
            overallInt = (int) overallReadingDouble;
            sensorReadingData = String.valueOf(moistureInt + "%");
            idealReadingData = String.valueOf(idealReading + "%");
            overallReadingData = String.valueOf(overallInt + "%");
        }
        catch (Exception e)
        {
            overallReadingData = String.valueOf(0 + "%");
        }
        finally
        {
            if(recommendationData.equals("0"))
            {
                recommendationData = "IRRIGATION\r\nNOT NEEDED";
            }
            else if(recommendationData.equals("1"))
            {
                recommendationData = "IRRIGATION\r\nNEEDED";
            }
            else
            {
                recommendationData = "NO INTERNET\r\nCONNECTION";
            }
            tvMoistureReading.setText(overallReadingData);
            tvSensorReading.setText(sensorReadingData);
            tvIdealReading.setText(idealReadingData);
            tvSoilMoistureRecommendation.setText(recommendationData);
        }
    }

    public void Restart(View v)
    {
        onRestart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent soilMoisture = new Intent(SoilMoisture.this, SoilMoisture.class);
        startActivity(soilMoisture);
        finish();
    }
}
