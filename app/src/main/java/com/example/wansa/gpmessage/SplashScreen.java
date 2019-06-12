package com.example.wansa.gpmessage;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private ImageView imgview;
    private static int GECİS_SURESİ=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        imgview=(ImageView)findViewById(R.id.splashscreen1);

        Animation animation=AnimationUtils.loadAnimation(this,R.anim.animation);
        imgview.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent secondpage =new Intent(SplashScreen.this,WelcomeActivity.class);
                startActivity(secondpage);
                finish();
            }
        },GECİS_SURESİ);
    }
}
