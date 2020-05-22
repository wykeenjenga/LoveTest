package com.wykee.lovetest.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.spark.submitbutton.SubmitButton;
import com.wykee.lovetest.Activities.OutputActivity;
import com.wykee.lovetest.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.logging.Logger;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Input extends Fragment {

    public Input() {
        // Required empty public constructor
    }

    EditText user,other;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_input, container, false);
        user=view.findViewById(R.id.user_name);
        other=view.findViewById(R.id.other_name);



        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView=view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3092533879997488/9456166237");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                    Toast.makeText(getContext(),"Error,,Port='200'",Toast.LENGTH_LONG).show();
                }
            }
        });

        final Drawable customErrorDrawable = getResources().getDrawable(R.drawable.ic_error_black_24dp);
        customErrorDrawable.setBounds(0, 0,
                customErrorDrawable.getIntrinsicWidth(),
                customErrorDrawable.getIntrinsicHeight());


        SubmitButton submitButton=view.findViewById(R.id.submit);
        final LinearLayout ln=view.findViewById(R.id.calc_layout);
        final Animation anim= AnimationUtils.loadAnimation(getContext(),R.anim.blink);

        ln.setVisibility(View.GONE);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username,patner;
                username=user.getText().toString();
                patner=other.getText().toString();

                if (username.isEmpty() && patner.isEmpty()){
                    user.setError("Error! Enter user name.",customErrorDrawable);
                    other.setError("Error! Enter patner name.",customErrorDrawable);
                }
                else {
                    Thread timer=new Thread(){
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            finally {
                                //inflate another fragment or start another activity
                                Intent i=new Intent(getContext(), OutputActivity.class);
                                i.putExtra("User",username);
                                i.putExtra("Patner",patner);
                                startActivity(i);
                            }
                        }
                    };
                    timer.start();
                    ln.setVisibility(View.VISIBLE);
                    ln.startAnimation(anim);
                }
            }
        });

        return view;
    }
    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {

        }
        return "";
    }

}
