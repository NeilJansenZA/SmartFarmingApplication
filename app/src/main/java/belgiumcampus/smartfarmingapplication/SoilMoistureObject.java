package belgiumcampus.smartfarmingapplication;

import android.content.Context;

/**
 * Created by Pieter on 06/05/2017.
 */

public class SoilMoistureObject {

    public static String MoisturePercentage(Context context)
    {


        String receivedData = "";

        //procedure name           //columns  //rows //specific column
        receivedData =  DataAccess.readData(context,"sp_soilmoisture",  "1"   ,    "1",      "1");
        return receivedData.replace(";","").trim();


    }

    public static String Recommendation(Context context)
    {


        String receivedData = "";

        //procedure name           //columns  //rows //specific column
        receivedData =  DataAccess.readData(context,"sp_soilmoisture",  "2"   ,    "1",      "2");
        return receivedData.replace(";","").trim();


    }
    public static String Date(Context context)
    {


        String receivedData = "";

        //procedure name           //columns  //rows //specific column
        receivedData =  DataAccess.readData(context,"sp_soilmoisture",  "3"   ,    "1",      "3");
        return receivedData.replace(";","").trim();


    }

}
