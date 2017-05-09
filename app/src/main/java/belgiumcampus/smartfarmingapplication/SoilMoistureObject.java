package belgiumcampus.smartfarmingapplication;

import android.content.Context;

/**
 * Created by Pieter on 06/05/2017.
 */

public class SoilMoistureObject {

    public static String MoisturePercentage(Context context)
    {


        String receivedData = "";
        try {
            //procedure name           //columns  //rows //specific column
            receivedData = DataAccess.readData(context, "sp_soilmoisture", "1", "1", "1");
        }catch (Exception e)
        {
            receivedData = "No Internet";
        }
        return receivedData.replace(";","").trim();
    }

    public static String Recommendation(Context context)
    {


        String receivedData = "";
        try {
            //procedure name           //columns  //rows //specific column
            receivedData = DataAccess.readData(context, "sp_soilmoisture", "2", "1", "2");
        }catch (Exception e)
        {
            receivedData = "No Internet";
        }
        return receivedData.replace(";","").trim();


    }
    public static String Date(Context context)
    {


        String receivedData = "";
        try {
            //procedure name           //columns  //rows //specific column
            receivedData = DataAccess.readData(context, "sp_soilmoisture", "3", "1", "3");
        }catch (Exception e)
        {
            receivedData = "No Internet";
        }
        return receivedData.replace(";","").trim();


    }

}