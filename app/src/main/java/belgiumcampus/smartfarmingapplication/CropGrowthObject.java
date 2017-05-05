package belgiumcampus.smartfarmingapplication;

import android.content.Context;

/**
 * Created by Pieter on 05/05/2017.
 */

public class CropGrowthObject {

    public static String getAverageSize(Context context)
    {

        String receivedData = "No;Data";
        DataAccess dataAccess = new DataAccess();
        receivedData =  dataAccess.readData(context,"AverageSize_SP",  "1"   ,"1","0");
        return receivedData;
    }

    public static String getDailyDifference(Context context)
    {

        String receivedData = "No;Data";
        DataAccess dataAccess = new DataAccess();
        receivedData =  dataAccess.readData(context,"dailyDiff_SP",  "1"   ,"1","0");
        return receivedData;
    }

    public static String getWeeklyDifference(Context context)
    {

        String receivedData = "No;Data";
        DataAccess dataAccess = new DataAccess();
        receivedData =  dataAccess.readData(context,"weeklyDiff_SP",  "1"   ,"1","0");
        return receivedData;
    }

    public static String getWeeksGrowth(Context context)
    {

        String receivedData = "No;Data";
        DataAccess dataAccess = new DataAccess();
        receivedData =  dataAccess.readData(context,"WeeksGrowth_SP",  "1"   ,"1","0");
        return receivedData;
    }
}
