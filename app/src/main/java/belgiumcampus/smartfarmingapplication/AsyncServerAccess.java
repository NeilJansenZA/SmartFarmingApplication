package belgiumcampus.smartfarmingapplication;

/**
 * Created by Pieter on 22/04/2017.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Pieter on 21/02/2017.
 */
//

public class AsyncServerAccess extends AsyncTask<String,Void,String>
{
    String ip = "129.232.130.196";
    String className = "net.sourceforge.jtds.jdbc.Driver";
    String db = "SmartFarmingDB";
    String un = "SmartFarming";
    String password = "SmartFarming321";

    public Connection CONN()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;

        try
        {

            Class.forName(className);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        }
        catch (SQLException se)
        {
            Log.e("error:", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error:", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error:", e.getMessage());
        }
        return conn;
    }

    Context contextWorker;

    AsyncServerAccess (Context context)
    {
        contextWorker =  context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String procedureName = params[0];
        int columns = new Integer(params[1]);
        int rows = 0;
        String specificColumns = null;
        if (params.length ==4)
        {
            specificColumns = params[3];
        }
        boolean flagReadAll = false;

        if (params[2].equals("ALL"))
        {
            flagReadAll = true;
        } else
        {
            rows = new Integer(params[2]);
        }
        String resultStringConcatenation = null;

        try
        {
            Connection DB = CONN();
            if (DB == null)
            {
                resultStringConcatenation = "Error in connection with SQL server";
            }
            else
            {
                String query = "Exec " + procedureName;
                Statement stmt = DB.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                rs.next();

                resultStringConcatenation = new String();


                if (flagReadAll && specificColumns != null)
                {

                    do {

                        for (int c = 0; c < columns; c++)
                        {
                            if (specificColumns.contains(String.valueOf(c+1))) {
                                String addition = rs.getString(c + 1);
                                if (resultStringConcatenation == null) {
                                    resultStringConcatenation = addition + ";";
                                } else {
                                    resultStringConcatenation += addition + ";";
                                }
                            }
                        }


                    }while (rs.next());
                    DB.close();
                }
                else if(flagReadAll && specificColumns == null)
                {
//
                    do {



                        for (int c = 0; c < columns; c++)
                        {
                            String addition = rs.getString(c+1);
                            if (resultStringConcatenation == null)
                            {
                                resultStringConcatenation = addition +";";
                            }
                            else
                            {
                                resultStringConcatenation +=  addition+ ";" ;
                            }
                        }



                    }while (rs.next());
                    DB.close();

                }
                else if (!flagReadAll && specificColumns != null)
                {

                    for (int i = 0; i < rows; i++)
                    {
                        for (int c = 0; c < columns; c++)
                        {
                            if (specificColumns.contains(String.valueOf(c+1))) {
                                String addition = rs.getString(c + 1);
                                if (i == 0 && c == 0) {
                                    resultStringConcatenation = addition + ";";
                                } else {
                                    resultStringConcatenation += addition + ";";
                                }
                            }
                        }
                        rs.next();
                    }
                    DB.close();

                }else if (!flagReadAll && specificColumns == null)
                {
                    for (int i = 0; i < rows; i++)
                    {
                        for (int c = 0; c < columns; c++)
                        {
                            String addition = rs.getString(c+1);
                            if (i == 0 && c == 0)
                            {
                                resultStringConcatenation = addition +";";
                            }
                            else
                            {
                                resultStringConcatenation +=  addition+ ";" ;
                            }
                        }
                        rs.next();
                    }
                    DB.close();
                }





            }
        }
        catch (Exception ex)
        {
            resultStringConcatenation = "SQL Query Failed! " + ex.getMessage();
        }


        return  resultStringConcatenation;

    }

    @Override
    protected void onPostExecute(String result)
    {

    }

    @Override
    protected void onPreExecute()
    {

        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}







//