package belgiumcampus.smartfarmingapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class Irrigation extends AppCompatActivity {

    private TextView vTitle, tvIrrigationTodayDate, tvBananaTitle, tvBananaIrrigationAmount;
    private View vAppBar;
    private Button btnIrrigationHistory;
    private String dataForIrrigationDate;
    private Intent viewHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation);
        loadActivity();
    }

    protected void loadActivity()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.irrigationToolbar);
        setSupportActionBar(toolbar);

        vAppBar = findViewById(R.id.irrigationToolbar);
        vTitle = (TextView) vAppBar.findViewById(R.id.activityTitle);
        tvIrrigationTodayDate = (TextView) findViewById(R.id.irrigationTodayDate);
        tvBananaTitle = (TextView) findViewById(R.id.tvtIrrigationCropBananaTitle);
        tvBananaIrrigationAmount = (TextView) findViewById(R.id.tvBananaIrrigationAmount);
        btnIrrigationHistory = (Button) findViewById(R.id.btnIrrigationHistory);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");

        vTitle.setText("IRRIGATION");

        vTitle.setTypeface(montserratBold);
        tvIrrigationTodayDate.setTypeface(montserratBold);
        tvBananaTitle.setTypeface(montserratBold);
        tvBananaIrrigationAmount.setTypeface(robotoRegular);
        btnIrrigationHistory.setTypeface(montserratBold);

        long date = System.currentTimeMillis();
        //april 26 2017
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");
        dataForIrrigationDate = sdf.format(date).toUpperCase();
        tvIrrigationTodayDate.setText(dataForIrrigationDate + "\r\nTODAY");
    }

    public void viewIrrigationHistory(View v)
    {
        viewHistory = new Intent(getApplicationContext(), IrrigationHistory.class);
        startActivity(viewHistory);
    }
}
