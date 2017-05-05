package belgiumcampus.smartfarmingapplication;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Pieter on 15/04/2017.
 */

public class ServerAccess {
    String ip = "129.232.130.196";
    String className = "net.sourceforge.jtds.jdbc.Driver";
    String db = "SmartFarmingDB";
    String un = "SmartFarming";
    String password = "SmartFarming321";



    public String executeQuery(String queryName, int columns)
    {

        String z = null;

        try
        {
            Connection DB = CONN();
            if (DB == null)
            {
                z = "Error in connection with SQL server";
            } else
            {
                String query = "Exec " + queryName;
                Statement stmt = DB.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                rs.next();

                z = new String();
                for (int i = 0; i < columns; i++) {
                    String addition = rs.getString(i+1);

                    if (i == 0)
                    {
                        z = addition +";";
                    }
                    else
                    {
                        z +=  addition+ ";" ;
                    }


                }


//
            }
        } catch (Exception ex) {
            z = "SQL Query Failed! " + ex.getMessage();
        }


       return  z;
    }

    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;
        try {

            Class.forName(className);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("error:", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("error:", e.getMessage());
        } catch (Exception e) {
            Log.e("error:", e.getMessage());
        }
        return conn;
    }
}
