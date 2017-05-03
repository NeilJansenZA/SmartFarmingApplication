package belgiumcampus.smartfarmingapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CropGrowth extends AppCompatActivity {

    TextView vTitle, tvAverageTitle, tvCurrentTitle, tvDailyTitle, tvExpectedTitle, tvWeeklyTitle, tvCropType, tvAverage, tvCurrent, tvDaily, tvExpected, tvWeekly;
    View vAppBar;
    String averageData, currentData, dailyData, expectedData, weeklyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_growth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.cropGrowthToolbar);
        setSupportActionBar(toolbar);

        vAppBar = findViewById(R.id.cropGrowthToolbar);
        vTitle = (TextView) vAppBar.findViewById(R.id.activityTitle);
        tvAverageTitle = (TextView) findViewById(R.id.tvAverageSizeTitle);
        tvCurrentTitle = (TextView) findViewById(R.id.tvCurrentSizeTitle);
        tvDailyTitle = (TextView) findViewById(R.id.tvDailyDifferenceTitle);
        tvExpectedTitle = (TextView) findViewById(R.id.tvExpectedSizeTitle);
        tvWeeklyTitle = (TextView) findViewById(R.id.tvWeeklyDifferenceTitle);
        tvCropType = (TextView) findViewById(R.id.cropType);
        tvAverage = (TextView) findViewById(R.id.tvAverageSize);
        tvCurrent = (TextView) findViewById(R.id.tvCurrentSize);
        tvDaily = (TextView) findViewById(R.id.tvDailyDifference);
        tvExpected = (TextView) findViewById(R.id.tvExpectedSize);
        tvWeekly = (TextView) findViewById(R.id.tvWeeklyDifference);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");

        vTitle.setText("CROP GROWTH");

        vTitle.setTypeface(montserratBold);
        tvCropType.setTypeface(montserratBold);
        tvAverageTitle.setTypeface(montserratBold);
        tvCurrentTitle.setTypeface(robotoRegular);
        tvDailyTitle.setTypeface(robotoRegular);
        tvExpectedTitle.setTypeface(robotoRegular);
        tvWeeklyTitle.setTypeface(robotoRegular);
        tvAverage.setTypeface(robotoRegular);
        tvCurrent.setTypeface(robotoRegular);
        tvDaily.setTypeface(robotoRegular);
        tvExpected.setTypeface(robotoRegular);
        tvWeekly.setTypeface(robotoRegular);
    }
}
