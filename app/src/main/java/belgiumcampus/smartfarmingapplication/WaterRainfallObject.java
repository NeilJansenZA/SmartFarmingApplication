package belgiumcampus.smartfarmingapplication;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Pieter on 05/05/2017.
 */

public class WaterRainfallObject {

//




    public static String getRecommendation(Context context)
    {

        String receivedData = "No;Data";
        try {
            //procedure name           //columns  //rows //specific column
            receivedData = DataAccess.readData(context, "sp_ShowLatestWaterTableValue", "3", "1", "2");
        }
        catch (Exception e)
        {
            receivedData = "No internet";
        }
        return receivedData;
    }
    ////
    public static  String getCurrentLevel(Context context)
    {
        String receivedData = "No;Data";
        try
        {
            receivedData =  DataAccess.readData(context,"sp_ShowLatestWaterTableValue",  "1"   ,"1","1");
        }
        catch(Exception e)
        {
            receivedData = "No Internet";
        }

        return receivedData.replace(";","").trim();
    }


    public static ArrayList<String> getGraphData(Context context)
    {
        String receivedData = "No;Data";
        ArrayList<String> listOfGraphData = new ArrayList<>();

        try {
            receivedData = DataAccess.readData(context, "sp_ShowLatestRainfallValue", "2", "7", "1,2");

            String [] temp = receivedData.split(";");

            for (int i = 0; i <  14; i = i+2)
            {
                listOfGraphData.add(temp[i] +";" + temp[i+1]);
            }
        }
        catch(Exception e)
        {
            listOfGraphData = null;
        }
            return  listOfGraphData;


    }
///
}