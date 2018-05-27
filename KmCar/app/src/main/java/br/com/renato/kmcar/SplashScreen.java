package br.com.renato.kmcar;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.widget.ImageView;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        Thread time = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);

                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        time.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
