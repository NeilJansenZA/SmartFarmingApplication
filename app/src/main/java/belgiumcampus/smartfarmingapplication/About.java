package belgiumcampus.smartfarmingapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {

    private TextView vTitle, tvMission, tvAbout;
    private View vAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        loadActivity();
    }

    protected void loadActivity()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.aboutToolbar);
        setSupportActionBar(toolbar);

        vAppBar = findViewById(R.id.aboutToolbar);
        vTitle = (TextView) vAppBar.findViewById(R.id.activityTitle);
        tvMission = (TextView) findViewById(R.id.tvOurMission);
        tvAbout = (TextView) findViewById(R.id.tvAboutInformation);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");

        vTitle.setText("ABOUT US");

        vTitle.setTypeface(montserratBold);
        tvMission.setTypeface(montserratBold);
        tvAbout.setTypeface(robotoRegular);
    }
}
