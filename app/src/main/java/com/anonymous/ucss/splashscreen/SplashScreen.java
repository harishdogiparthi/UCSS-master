package com.anonymous.ucss.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.anonymous.ucss.MainActivity;
import com.anonymous.ucss.R;


public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 8000;

    @Override
    protected void onCreate(Bundle savednInstanceState){
        super.onCreate(savednInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.imgLogo);
        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        Animation myAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.myanimation);
        Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        AnimationSet animationSet = new  AnimationSet(false);
        animationSet.addAnimation(blink);
        animationSet.addAnimation(myAnimation);
        animationSet.addAnimation(fade);
        imageView.startAnimation(animationSet);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);

                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
