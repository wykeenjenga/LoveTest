package com.wykee.lovetest.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.wykee.lovetest.R;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputActivity extends AppCompatActivity {

    TextView user_result,other_result,txtCalc,message_text;
    LinearLayout messageLayout,calcLayout;
    CircleProgress progress_circular;
    private int Min=0,Max;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;


    String getUser,getPatner,getUserName,getPatnerName;

    String[] messageText={
            "You meet thousands of people and none of them really touch you. And then you meet one person and your life is changed forever.",
            "Affection is when you see someone’s strengths; love is when you accept someone’s flaws.",
            "My great hope is to laugh as much as I cry; to get my work done and try to love somebody and the courage to accept the love in return.",
            "Since the invention of the kiss, there have been only five kisses that were rated the most passionate, the most pure. This one left them all behind.",
            "We loved with a love that was more than love.",
            "You know you’re in love when you can’t fall asleep because reality is finally better than your dreams.",
            "You and I, it’s as though we have been taught to kiss in heaven and sent down to earth together, to see if we know what we were taught." ,
            "Marion and I have not climbed Mount Everest or written a great American novel. But we've had the joy of raising two wonderful kids, and watching them and their friends grow up into loving adults." +
                    " And now, we're gonna have the pleasure of watching them pass that love onto their children. And I guess no man or woman could ask for anything more.",
            "You are my heart, my life, my one and only thought.",
            "10. “Being deeply loved by someone gives you strength, while loving someone deeply gives you courage",
            "I think we dream so we don’t have to be apart for so long. If we’re in each other’s dreams, we can be together all the time.",
            "That's how you know you love someone, I guess, when you can't experience anything without wishing the other person were there to see it, too.",

            "We were together even when we were apart.” – Shannon A. Thompson, Death Before Daylight",

            "I will return. I will find you. Love you. Marry you. And live without shame.",

            "The scariest thing about distance is you don’t know if they’ll miss you or forget about you.",

            "There are no goodbyes for us. Wherever you are, you will always be in my heart.",

            "It's enough for me to be sure that you and I exist at this moment.",

            "The pain of parting is nothing to the joy of meeting again.",

            "It feels good to think about you when I’m warm in bed. I feel as if you’re curled up there beside me, fast asleep. And I think how great it would be if it were true.",

            "But I must admit, I miss you quite terribly. The world is too quiet without you nearby."
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        Drawable X = getResources().getDrawable(R.drawable.ic_close_black_24dp);
        getSupportActionBar().setHomeAsUpIndicator(X);



        user_result=findViewById(R.id.user_result);
        other_result=findViewById(R.id.other_result);

        txtCalc=findViewById(R.id.txt_calc);
        messageLayout=findViewById(R.id.result_message);
        calcLayout=findViewById(R.id.calc_layout_result);
        message_text=findViewById(R.id.message_text);
        progress_circular=findViewById(R.id.progress_circular);
        final Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);

        getUser=getIntent().getStringExtra("User");
        getPatner=getIntent().getStringExtra("Patner");

        user_result.setText(getUser);
        other_result.setText(getPatner);

        getUserName=user_result.getText().toString();
        getPatnerName=other_result.getText().toString();

        mAdView=findViewById(R.id.adView);


        //Admob initilization

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest=new AdRequest.Builder().addTestDevice("61408C7DE3960FC3D85A0D40C2E1E604").build();
        mAdView.loadAd(adRequest);*/



        //int random=new Random().nextInt((Max - Min) + 1) + Min;

        messageLayout.setVisibility(View.GONE);
        final Thread timer=new Thread(){
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    calcLayout.startAnimation(animation);
                    String s=getString(R.string.calculating_love);

                    txtCalc.setText(s+getUser+"\tand\t"+getPatner);

                    String userCont="wyYWKkEeJjohHAanNTtGrRSVv";
                    String patnerCont="RrAaCcIiEeGPpHhIiLlOoMmNnJjyY";



                    String pattern = "([a-zA-Z]{2})";

                    Pattern r = Pattern.compile(pattern);

                    Matcher userMatcher = r.matcher(userCont);
                    Matcher patnerMatcher=r.matcher(patnerCont);



                    if (userMatcher.find() && patnerMatcher.find()){

                        Random rand = new Random();
                        int max = rand.nextInt(86 - 65) + 65;

                        int sms_index = rand.nextInt(messageText.length);
                        String sms=messageText[sms_index];

                        for (int i=Min; i<=max; i++){

                            messageLayout.setVisibility(View.VISIBLE);

                            progress_circular.setProgress(i);

                            sleep(40);
                        }

                    }
                    else {

                        Random rand1 = new Random();
                        int maxE = rand1.nextInt(31 - 0) + 0;
                        int sms_index = rand1.nextInt(messageText.length);
                        String sms=messageText[sms_index];

                        for (int i=Min; i<=maxE; i++){

                            messageLayout.setVisibility(View.VISIBLE);

                            progress_circular.setProgress(i);

                            sleep(60);
                        }

                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    calcLayout.clearAnimation();

                }
            }
        };
        timer.start();

        Random rand = new Random();
        int sms_index = rand.nextInt(messageText.length);
        String sms=messageText[sms_index];
        message_text.setText("Hello\t"+getUserName+"\t&\t"+getPatnerName+"\n"+sms);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        return;
    }
}
