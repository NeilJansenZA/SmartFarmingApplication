package belgiumcampus.smartfarmingapplication;

import android.content.Context;

/**
 * Created by Pieter on 05/05/2017.
 */
//
public class CropGrowthObject {

    public static String getAverageSize(Context context)
    {

        String receivedData = "No;Data";
        try
        {
            receivedData = DataAccess.readData(context, "AverageSize_SP", "1", "1", "1");
        }catch (Exception e)
        {
            receivedData = "No Internet";
        }
        return receivedData.replace(";","").trim();
    }

    public static String getDailyDifference(Context context)
    {

        String receivedData = "No;Data";
        try {
            receivedData = DataAccess.readData(context, "dailyDiff_SP", "1", "1", "1");
        } catch (Exception e)
        {
            receivedData = "No Internet";
        }
        return receivedData.replace(";","").trim();
    }

    public static String getWeeklyDifference(Context context)
    {

        String receivedData = "No;Data";
        try {
            receivedData = DataAccess.readData(context, "weeklyDiff_SP", "1", "1", "1");
        }catch (Exception e)
        {
            receivedData = "No Internet";
        }
        return receivedData.replace(";","").trim();
    }

    public static String getWeeksGrowth(Context context)
    {

        String receivedData = "No;Data";
        try {
            receivedData = DataAccess.readData(context, "WeeksGrowth_SP", "1", "1", "1");
        }catch (Exception e)
        {
            receivedData = "No Internet";
        }
        return receivedData.replace(";","").trim();
    }
}