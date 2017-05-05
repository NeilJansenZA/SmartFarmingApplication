package belgiumcampus.smartfarmingapplication;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Pieter on 05/05/2017.
 */

public class IrrigationObject {


    public static String GetRecommendedValue(Context context)
    {
        String receivedData = "No;Data";

        //procedure name           //columns  //rows //specific column
        receivedData =  DataAccess.readData(context,"sp_ShowLatestRainfallValue",  "1"   ,    "1",      "0");
        return receivedData;
    }


//

    public static ArrayList<String> GetHistory(Context context)
    {
        String receivedData = "No;Data";

        ArrayList<String> irrigationHistoryList;

        try {
            receivedData=  DataAccess.readData(context,"ThirtyDayForecast",  "3"   ,"ALL",null);
        }
        catch (Exception e)
        {
            receivedData = "error";
        }


        if (!receivedData.equals("error"))
        {
            String[] Data = receivedData.split(";");
            irrigationHistoryList = new ArrayList<String>();

            String longForecastConcat;
            try {
                for (int i = 0; i < 90; i= i+3) {

                    // longForecastConcat = longForecastData[i] +";"+ longForecastData[i+1] +";"+ longForecastData[i+2];
                    // thirtyDayForecastList.add(longForecastConcat);

                }
            }catch (Exception e)
            {
                irrigationHistoryList = null;
            }
            return  irrigationHistoryList;
        }

        irrigationHistoryList = new ArrayList<>();
        return irrigationHistoryList;


    }

}