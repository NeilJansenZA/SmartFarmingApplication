package belgiumcampus.smartfarmingapplication;

        import android.content.Context;

/**
 * Created by Pieter on 06/05/2017.
 */

public class SoilMoistureObject {

    public static String OverallMoistureReading(Context context)
    {


        String receivedData = "";

        //procedure name           //columns  //rows //specific column
        receivedData =  DataAccess.readData(context,"sp_ShowLatestWaterTableValue",  "1"   ,    "1",      "1");
        return receivedData.replace(";","").trim();


    }


}