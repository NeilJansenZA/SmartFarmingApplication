package belgiumcampus.smartfarmingapplication;

import android.content.Context;

import java.util.concurrent.ExecutionException;

/**
 * Created by Pieter on 03/05/2017.
 */

public class DataAccess {
//




    public static String readData(Context context,String methodName, String Columns, String Rows, String SpecificColumns)
    {
        String receivedData = "No;Data";

        if (SpecificColumns != null)
        {
            try {
                receivedData = new AsyncServerAccess(context).execute(methodName,  Columns   ,Rows, SpecificColumns).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }else
        {
            try {
                receivedData = new AsyncServerAccess(context).execute(methodName,  Columns   ,Rows).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }



        return  receivedData;
    }

}
