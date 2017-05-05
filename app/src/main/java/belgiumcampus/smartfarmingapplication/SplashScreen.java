package belgiumcampus.smartfarmingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent loadMenu = new Intent(getApplication(), SelectionMenu.class);
                    startActivity(loadMenu);

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause()
    {
        //TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
