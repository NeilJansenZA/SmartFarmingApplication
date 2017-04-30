package belgiumcampus.smartfarmingapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectionMenu extends AppCompatActivity {

    Button weatherB, waterTableB, irrigationB, cropGrowthB, soilMoistureB, aboutB;
    Intent weather, waterTable, irriagation, cropGrowth, soilMousture, about;
    TextView dateDayTownTemp;
    String dataForDateDayTownTemp;

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

        Typeface tfBold = Typeface.createFromAsset(getAssets(), "fonts/montserra_-black.ttf");
        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/montserrat_regular.ttf");
        dateDayTownTemp.setTypeface(tfBold);
        weatherB.setTypeface(tfRegular);
        waterTableB.setTypeface(tfRegular);
        irrigationB.setTypeface(tfRegular);
        cropGrowthB.setTypeface(tfRegular);
        soilMoistureB.setTypeface(tfRegular);
        aboutB.setTypeface(tfRegular);
    }

    public void switchToWeather(View v)
    {
        weather = new Intent(getApplicationContext(), Weather.class);
        startActivity(weather);
    }

    public void switchToIrrigation(View v)
    {

    }

    public void switchToCropGrowth(View v)
    {

    }

    public void switchToSoilMoisture(View v)
    {

    }

    public void switchToAbout(View v)
    {

    }

}
