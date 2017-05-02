package belgiumcampus.smartfarmingapplication;

import android.app.Activity;
import android.app.Application;

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




}