package com.wykee.lovetest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wykee.lovetest.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView=findViewById(R.id.slash_logo);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.blink);
        imageView.startAnimation(animation);

        final Thread timer=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }

        };
        timer.start();
    }
}
