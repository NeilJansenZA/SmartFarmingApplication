package belgiumcampus.smartfarmingapplication;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Pieter on 05/05/2017.
 */

public class WaterRainfallObject {






     public static String getRecommendation(Context context)
     {

         String receivedData = "No;Data";
         DataAccess dataAccess = new DataAccess();
                                                     //procedure name           //columns  //rows //specific column
        receivedData =  dataAccess.readData(context,"sp_ShowLatestRainfallValue",  "1"   ,    "1",      "0");
     return receivedData;
     }

    public static  String getCurrentLevel(Context context)
    {
        String receivedData = "No;Data";
        DataAccess dataAccess = new DataAccess();
        receivedData =  dataAccess.readData(context,"sp_ShowLatestRainfallValue",  "1"   ,"1","1");
        return receivedData;
    }


    public ArrayList<String> getGraphData(Context context)
    {


        String receivedData = "No;Data";
        DataAccess dataAccess = new DataAccess();
        receivedData = dataAccess.readData(context,"sp_ShowLatestRainfallValue",  "2"   ,"7","1,2");

        ArrayList<String> listOfGraphData = new ArrayList<>();


        String [] temp = receivedData.split(";");
        for (String item:temp)
        {
        listOfGraphData.add(item);

        }

    return  listOfGraphData;
    }

}
