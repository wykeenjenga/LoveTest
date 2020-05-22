package com.wykee.lovetest.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.wykee.lovetest.Fragments.Input;
import com.wykee.lovetest.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity{
    Class fragmentclass;
    Fragment fragment;
    public ActionBarDrawerToggle drawerToggle;
    String version;

    DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setTitle("Love Test");
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        NavigationView navView=(NavigationView) findViewById(R.id.nav_drawer);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View header = navView.getHeaderView(0);

        TextView app_version=header.findViewById(R.id.app_version);
        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            version= pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        app_version.setText(version);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.test_love:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.feedback:
                        Intent email=new Intent(Intent.ACTION_SEND);
                        String[] recipients={"wycliffnjenga19@gmail.com"};
                        email.putExtra(Intent.EXTRA_EMAIL, recipients);
                        email.setType("text/html");
                        email.setPackage("com.google.android.gm");
                        startActivity(Intent.createChooser(email, "Contact Developer"));
                        email.putExtra(Intent.EXTRA_CC,"droidsoption5@gmail.com");
                        break;
                    case R.id.invite:
                        //send App link to other
                        Toast.makeText(getApplicationContext(),"sending link",Toast.LENGTH_LONG).show();
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        //link of the app is supposed to be declared hear
                        String shareBody = "https://play.google.com/store/apps/details?id=com.wykee.lovetest";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    case R.id.about:
                        Intent i=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        i.addCategory(Intent.CATEGORY_DEFAULT);
                        i.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(i);
                        break;
                    case R.id.other_apps:
                        //show our apps

                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        fragmentclass= Input.class;
        try {
            fragment=(Fragment)fragmentclass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (fragment!=null){
            getSupportFragmentManager().beginTransaction().add(R.id.root_layout,fragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    boolean doubleBackToExitPressedOnce = false;
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce){
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce=true;
        Toast.makeText(this, "Please press 'back' again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
