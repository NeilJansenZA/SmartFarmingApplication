package belgiumcampus.smartfarmingapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Pieter on 01/05/2017.
 */

public class WeatherObject {

    int currentTemp;
    int minTemp;
    int maxTemp;
    int currentHumidity;
    float precipitation;
    int rainChance;

    String observationTime, weatherDescription;

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }


    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public float getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(float precipitation) {
        this.precipitation = precipitation;
    }

    public int getRainChance() {
        return rainChance;
    }

    public void setRainChance(int rainChance) {
        this.rainChance = rainChance;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }


    public WeatherObject(String observationTime, String weatherDescription, String currentTemp, String currentHumidity, String minTemp, String maxTemp, String precipitation, String rainChance ) {

        this.currentTemp = Integer.parseInt(currentTemp.substring(0,currentTemp.indexOf(".")));
        this.minTemp = Integer.parseInt(minTemp.substring(0,minTemp.indexOf(".")));
        this.maxTemp = Integer.parseInt(maxTemp.substring(0,maxTemp.indexOf(".")));
        this.currentHumidity = Integer.parseInt(currentHumidity.substring(0,currentHumidity.indexOf(".")));
        this.precipitation = Float.parseFloat(precipitation.substring(0,precipitation.indexOf(".")));
        this.rainChance = Integer.parseInt(rainChance.substring(0,rainChance.indexOf(".")));
        this.observationTime = observationTime;
        this.weatherDescription = weatherDescription;
    }


    public WeatherObject() {

    }

    public static WeatherObject CreateCurrentWeatherObject(Context context)
    {


        String[] objectData = DataAccess.readData(context,"CurrentWeather",  "8"   ,"1",null).split(";");
          WeatherObject currentWeatherObject = new WeatherObject();
        try {
            currentWeatherObject = new WeatherObject(objectData[0],objectData[1],objectData[2],objectData[3],objectData[4],objectData[5],objectData[6],objectData[7]);
        }catch (Exception e)
        {
            currentWeatherObject = null;
        }

return  currentWeatherObject;
    }

public static ArrayList<String> CreateEightDayForecastList(Context context)
{


    ArrayList<String> eightDayForecastList;
    String shortReceivedForecastData = "No;Data";
    try {

        shortReceivedForecastData = DataAccess.readData(context, "WeatherForecast", "6", "8", "1,2,3,6");
    }catch (Exception e)
    {
shortReceivedForecastData = "error";
    }

    if (!shortReceivedForecastData.equals("error"))
    {
        String[] shortForecastData = shortReceivedForecastData.split(";");
        eightDayForecastList = new ArrayList<String>();
        String concat;
        try {
            for (int i = 0; i < 32; i= i+4) {
                concat = shortForecastData[i].substring(8) +";"+ shortForecastData[i +2].substring(0,shortForecastData[i +2].indexOf(".")) + ";" + shortForecastData[i+1].substring(0,shortForecastData[i +1].indexOf(".")) + ";" + shortForecastData[i+3];
                eightDayForecastList.add( concat);
            }
        }catch (Exception e)
        {
eightDayForecastList= null;
        }
        return  eightDayForecastList;
    }
eightDayForecastList = new ArrayList<String>();
    return eightDayForecastList;
}


    public static ArrayList<String> CreateThirtyDayForecastList(Context context)
    {

        String longReceivedForecastData = "No;Data";
        ArrayList<String> thirtyDayForecastList;

        try {
            longReceivedForecastData =  DataAccess.readData(context,"ThirtyDayForecast",  "3"   ,"30",null);
        }
        catch (Exception e)
        {
longReceivedForecastData = "error";
        }


        if (!longReceivedForecastData.equals("error"))
        {
            String[] longForecastData = longReceivedForecastData.split(";");
            thirtyDayForecastList = new ArrayList<String>();

            String longForecastConcat;
            try {
                for (int i = 0; i < 90; i= i+3) {

                    longForecastConcat = longForecastData[i] +";"+ longForecastData[i+1] +";"+ longForecastData[i+2];
                    thirtyDayForecastList.add(longForecastConcat);

                }
            }catch (Exception e)
            {
                thirtyDayForecastList = null;
            }
            return  thirtyDayForecastList;
        }

thirtyDayForecastList = new ArrayList<>();
        return thirtyDayForecastList;

    }

}
