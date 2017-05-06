package belgiumcampus.smartfarmingapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IrrigationHistory extends AppCompatActivity {

    private TextView vTitle, tvIrrigationHistoryDateTitle, tvIrrigationHistoryCropTitle, tvIrrigationHistoryAmountTitle;
    private TextView dateOne, dateTwo, dateThree, dateFour, dateFive, dateSix, dateSeven;
    private TextView cropOne, cropTwo, cropThree, cropFour, cropFive, cropSix, cropSeven;
    private TextView amountOne, amountTwo, amountThree, amountFour, amountFive, amountSix, amountSeven;
    private View vAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation_history);
        loadActivity();
    }

    protected void loadActivity()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.irrigationHistoryToolbar);
        setSupportActionBar(toolbar);

        vAppBar = findViewById(R.id.irrigationHistoryToolbar);
        vTitle = (TextView) vAppBar.findViewById(R.id.activityTitle);
        tvIrrigationHistoryDateTitle = (TextView) findViewById(R.id.tvIrrigationHistoryDateTitle);
        tvIrrigationHistoryCropTitle = (TextView) findViewById(R.id.tvIrrigationHistoryCropTitle);
        tvIrrigationHistoryAmountTitle = (TextView) findViewById(R.id.tvIrrigationHistoryAmountTitle);

        dateOne = (TextView) findViewById(R.id.tvIrrigationDateOne);
        dateTwo = (TextView) findViewById(R.id.tvIrrigationDateTwo);
        dateThree = (TextView) findViewById(R.id.tvIrrigationDateThree);
        dateFour = (TextView) findViewById(R.id.tvIrrigationDateFour);
        dateFive = (TextView) findViewById(R.id.tvIrrigationDateFive);
        dateSix = (TextView) findViewById(R.id.tvIrrigationDateSix);
        dateSeven = (TextView) findViewById(R.id.tvIrrigationDateSeven);

        cropOne = (TextView) findViewById(R.id.tvIrrigationCropOne);
        cropTwo = (TextView) findViewById(R.id.tvIrrigationCropTwo);
        cropThree = (TextView) findViewById(R.id.tvIrrigationCropThree);
        cropFour = (TextView) findViewById(R.id.tvIrrigationCropFour);
        cropFive = (TextView) findViewById(R.id.tvIrrigationCropFive);
        cropSix = (TextView) findViewById(R.id.tvIrrigationCropSix);
        cropSeven = (TextView) findViewById(R.id.tvIrrigationCropSeven);

        amountOne = (TextView) findViewById(R.id.tvIrrigationAmountOne);
        amountTwo = (TextView) findViewById(R.id.tvIrrigationAmountTwo);
        amountThree = (TextView) findViewById(R.id.tvIrrigationAmountThree);
        amountFour = (TextView) findViewById(R.id.tvIrrigationAmountFour);
        amountFive = (TextView) findViewById(R.id.tvIrrigationAmountFive);
        amountSix = (TextView) findViewById(R.id.tvIrrigationAmountSix);
        amountSeven = (TextView) findViewById(R.id.tvIrrigationAmountSeven);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");

        vTitle.setText("IRRIGATION HISTORY");

        vTitle.setTypeface(montserratBold);
        tvIrrigationHistoryDateTitle.setTypeface(montserratBold);
        tvIrrigationHistoryCropTitle.setTypeface(montserratBold);
        tvIrrigationHistoryAmountTitle.setTypeface(montserratBold);

        dateOne.setTypeface(robotoRegular);
        dateTwo.setTypeface(robotoRegular);
        dateThree.setTypeface(robotoRegular);
        dateFour.setTypeface(robotoRegular);
        dateFive.setTypeface(robotoRegular);
        dateSix.setTypeface(robotoRegular);
        dateSeven.setTypeface(robotoRegular);

        cropOne.setTypeface(robotoRegular);
        cropTwo.setTypeface(robotoRegular);
        cropThree.setTypeface(robotoRegular);
        cropFour.setTypeface(robotoRegular);
        cropFive.setTypeface(robotoRegular);
        cropSix.setTypeface(robotoRegular);
        cropSeven.setTypeface(robotoRegular);

        amountOne.setTypeface(robotoRegular);
        amountTwo.setTypeface(robotoRegular);
        amountThree.setTypeface(robotoRegular);
        amountFour.setTypeface(robotoRegular);
        amountFive.setTypeface(robotoRegular);
        amountSix.setTypeface(robotoRegular);
        amountSeven.setTypeface(robotoRegular);
    }
}
