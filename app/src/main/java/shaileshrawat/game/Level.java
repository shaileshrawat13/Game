package shaileshrawat.game;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shailesh.rawat on 9/14/2016.
 */
public class Level extends Activity implements View.OnClickListener {
    private static final String TAG = ".Level";
    private PowerManager.WakeLock mWakeLock;
    float multiplier=6000000000000f;
    List<Button> buttonList = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int show=View.VISIBLE;
        int hide=View.INVISIBLE;

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (savedInstanceState == null) {
        }
        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
        setContentView(R.layout.level);
        // Ask permissions

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)

        {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        21);

            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)

        {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        22);

            }
        }

        LevelWrapper.levelno=SharedPrefsUtils.getIntegerPreference(getApplicationContext(),"LevelNO",1);
        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);

        TextView label = (TextView) findViewById(R.id.LevelLabel);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Fonts/levelcompletefont.ttf");
        Typeface myTypeface1 = Typeface.createFromAsset(getAssets(), "Fonts/levelfonts.ttf");
        label.setTypeface(myTypeface);
        label.startAnimation(blink);

        Button one= (Button) findViewById(R.id.one);
        one.setTypeface(myTypeface1);
        one.setTag("one");
        one.setOnClickListener(this);

        Button two= (Button) findViewById(R.id.two);
        two.setTypeface(myTypeface1);
        two.setTag("two");
        two.setOnClickListener(this);

        Button three= (Button) findViewById(R.id.three);
        three.setTypeface(myTypeface1);
        three.setTag("three");
        three.setOnClickListener(this);

        Button four= (Button) findViewById(R.id.four);
        four.setTypeface(myTypeface1);
        four.setTag("four");
        four.setOnClickListener(this);

        Button five= (Button) findViewById(R.id.five);
        five.setTypeface(myTypeface1);
        five.setTag("five");
        five.setOnClickListener(this);

        Button six= (Button) findViewById(R.id.six);
        six.setTypeface(myTypeface1);
        six.setTag("six");
        six.setOnClickListener(this);

        Button seven= (Button) findViewById(R.id.seven);
        seven.setTypeface(myTypeface1);
        seven.setTag("seven");
        seven.setOnClickListener(this);

        Button eight= (Button) findViewById(R.id.eight);
        eight.setTypeface(myTypeface1);
        eight.setTag("eight");
        eight.setOnClickListener(this);

        Button nine= (Button) findViewById(R.id.nine);
        nine.setTypeface(myTypeface1);
        nine.setTag("nine");
        nine.setOnClickListener(this);

        Button ten= (Button) findViewById(R.id.ten);
        ten.setTypeface(myTypeface1);
        ten.setTag("ten");
        ten.setOnClickListener(this);
        buttonList.add(one);buttonList.add(two);buttonList.add(three);buttonList.add(four);
        buttonList.add(five);buttonList.add(six);buttonList.add(seven);buttonList.add(eight);
        buttonList.add(nine);buttonList.add(ten);
        // Set hidden all  initially
        for(int i=0; i<buttonList.size();i++ )
        {
            buttonList.get(i).setVisibility(hide);
        }

        for(int i=0; i<LevelWrapper.levelno;i++ )
        {
            buttonList.get(i).setVisibility(show);
            buttonList.get(i).setTypeface(myTypeface);
            buttonList.get(LevelWrapper.levelno-1).setTypeface(myTypeface1);
        }
    }

    public void onClick(View v) {
        String levelNo = v.getTag().toString();
        if (levelNo.equals("one")){
            LevelWrapper.level=1;
            LevelWrapper.levelspeed=9.0f*multiplier;
        }
        if (levelNo.equals("two")){
            LevelWrapper.level=2;
            LevelWrapper.levelspeed=7.5f*multiplier;
         }
        if (levelNo.equals("three")){
            LevelWrapper.level=3;
            LevelWrapper.levelspeed=9.5f*multiplier;
        }
        if (levelNo.equals("four")){
            LevelWrapper.level=4;
            LevelWrapper.levelspeed=9.0f*multiplier;
        }
        if (levelNo.equals("five")){
            LevelWrapper.level=5;
            LevelWrapper.levelspeed=9.60f*multiplier;
        }
        if (levelNo.equals("six")){
            LevelWrapper.level=6;
            LevelWrapper.levelspeed=9.7f*multiplier;
        }
        if (levelNo.equals("seven")){
            LevelWrapper.level=7;
            LevelWrapper.levelspeed=9.8f*multiplier;
        }
        if (levelNo.equals("eight")){
            LevelWrapper.level=8;
            LevelWrapper.levelspeed=10f*multiplier;
        }
        if (levelNo.equals("nine")){
            LevelWrapper.level=9;
            LevelWrapper.levelspeed=10f*multiplier;
        }
        if (levelNo.equals("ten")){
            LevelWrapper.level=10;
            LevelWrapper.levelspeed=90f*multiplier;
        }
        Intent mainIntent = new Intent(getApplicationContext(), Gamehome.class);
        startActivity(mainIntent);
        this.finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mWakeLock.acquire();
        //mSimulationView.startSimulation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mSimulationView.stopSimulation();
        mWakeLock.release();
    }
    @Override
    public void onBackPressed()    {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 20: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    //showInterstitial();
                }
                return;
            }
            case 21: {
                {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.

                    } else {
                        //showInterstitial();
                    }
                    return;
                }
            }
            case 22: {
                {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.

                    } else {
                        // showInterstitial();
                    }
                    return;
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
 }


