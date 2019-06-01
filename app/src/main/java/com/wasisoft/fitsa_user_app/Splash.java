package com.wasisoft.fitsa_user_app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent = new Intent(Splash.this, UserTypeActivity.class);
                 startActivity(intent);
                 //finish the activity
                 finish();

             }
         },2000);

    }
}
