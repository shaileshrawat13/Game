package shaileshrawat.game;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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

import static shaileshrawat.game.Homepage.buttonLevel;
import static shaileshrawat.game.LevelWrapper.hold;
import static shaileshrawat.game.LevelWrapper.levelName;
import static shaileshrawat.game.LevelWrapper.levelno;
import static shaileshrawat.game.LevelWrapper.mediumlevelno;
import static shaileshrawat.game.LevelWrapper.started;
import static shaileshrawat.game.LevelWrapper.timer;
import static shaileshrawat.game.SimulationView.calculatedScore;

/**
 * Created by shailesh.rawat on 9/14/2016.
 */
public class Level extends Activity implements View.OnClickListener {
    private static final String TAG = ".Level";
    public static int star;
    private PowerManager.WakeLock mWakeLock;
    float multiplier=1000000000000f;
    Button mediumlevel, easylevel;
    TextView levelname;

    List<Button> buttonList = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hold = true;
        timer = 0;
        super.onCreate(savedInstanceState);
        new LevelWrapper((Context) this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (savedInstanceState == null) {
        }
        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
        setContentView(R.layout.level);
        // How to play button

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
        mediumlevel = (Button) findViewById(R.id.mediumlevel);
        easylevel = (Button) findViewById(R.id.easylevel);
        levelname = (TextView) findViewById(R.id.levelName);
        LevelWrapper.levelno = SharedPrefsUtils.getIntegerPreference(getApplicationContext(), "LevelNO", 1);
        LevelWrapper.mediumlevelno = SharedPrefsUtils.getIntegerPreference(getApplicationContext(), "MediumLevelNO", 1);
        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        TextView label = (TextView) findViewById(R.id.LevelLabel);
        label.startAnimation(blink);

        Button one = (Button) findViewById(R.id.one);
        one.setTag("one");
        one.setOnClickListener(this);

        Button two = (Button) findViewById(R.id.two);
        two.setTag("two");
        two.setOnClickListener(this);

        Button three = (Button) findViewById(R.id.three);
        three.setTag("three");
        three.setOnClickListener(this);

        Button four = (Button) findViewById(R.id.four);
        four.setTag("four");
        four.setOnClickListener(this);

        Button five = (Button) findViewById(R.id.five);
        five.setTag("five");
        five.setOnClickListener(this);

        Button six = (Button) findViewById(R.id.six);
        six.setTag("six");
        six.setOnClickListener(this);

        Button seven = (Button) findViewById(R.id.seven);
        seven.setTag("seven");
        seven.setOnClickListener(this);

        Button eight = (Button) findViewById(R.id.eight);
        eight.setTag("eight");
        eight.setOnClickListener(this);

        Button nine = (Button) findViewById(R.id.nine);
        nine.setTag("nine");
        nine.setOnClickListener(this);

        Button ten = (Button) findViewById(R.id.ten);
        ten.setTag("ten");
        ten.setOnClickListener(this);
        buttonList.add(one);
        buttonList.add(two);
        buttonList.add(three);
        buttonList.add(four);
        buttonList.add(five);
        buttonList.add(six);
        buttonList.add(seven);
        buttonList.add(eight);
        buttonList.add(nine);
        buttonList.add(ten);
        if(levelName=="easy"){
            displayLevels(levelno);
            levelname.setText("EASY");
        }else{
            displayLevels(mediumlevelno);
            levelname.setText("MEDIUM");
        }


        easylevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelName="easy";
                levelname.setText("EASY");
                displayLevels(levelno);
            }
        });
        mediumlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelName="medium";
                levelname.setText("MEDIUM");
                displayLevels(mediumlevelno);
            }
        });
    }

    public void onClick(View v) {
        String levelNo = v.getTag().toString();
        buttonLevel.start();
        if (levelNo.equals("one")) {
                LevelWrapper.level = 1;
                LevelWrapper.levelspeed = 9.0f * multiplier;
            }
            if (levelNo.equals("two")) {
                LevelWrapper.level = 2;
                LevelWrapper.levelspeed = 7.5f * multiplier;
            }
            if (levelNo.equals("three")) {
                LevelWrapper.level = 3;
                LevelWrapper.levelspeed = 9.5f * multiplier;
            }
            if (levelNo.equals("four")) {
                LevelWrapper.level = 4;
                LevelWrapper.levelspeed = 9.0f * multiplier;
            }
            if (levelNo.equals("five")) {
                LevelWrapper.level = 5;
                LevelWrapper.levelspeed = 9.60f * multiplier;
            }
            if (levelNo.equals("six")) {
                LevelWrapper.level = 6;
                LevelWrapper.levelspeed = 9.7f * multiplier;
            }
            if (levelNo.equals("seven")) {
                LevelWrapper.level = 7;
                LevelWrapper.levelspeed = 9.8f * multiplier;
            }
            if (levelNo.equals("eight")) {
                LevelWrapper.level = 8;
                LevelWrapper.levelspeed = 10f * multiplier;
            }
            if (levelNo.equals("nine")) {
                LevelWrapper.level = 9;
                LevelWrapper.levelspeed = 10f * multiplier;
            }
            if (levelNo.equals("ten")) {
                LevelWrapper.level = 10;
                LevelWrapper.levelspeed = 10.5f * multiplier;
            }
            hold=false;
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
        Intent backHome = new Intent(getApplicationContext(), Homepage.class);
        startActivity(backHome);
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

    public String getimage(int level){
        String filename="";
        int star;
        String med="";
        if(levelName=="medium"){
            med="medium";
        }
        switch (level) {
            case 1:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level1", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 2:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level2", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 3:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level3", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 4:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level4", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 5:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level5", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 6:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level6", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 7:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level7", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 8:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level8", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 9:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level9", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 10:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level10", 0)));
                filename = "level"+level+"btn"+star;
                break;
            case 11:
                star=displayLevelBG((SharedPrefsUtils.getFloatPreference(getApplicationContext(), med+"Level10", 0)));
                filename = "level"+level+"btn"+star;
        }
        return filename;
    }
    public static int displayLevelBG(float score){

            star = 0;
            if (score >= 0 && score < 350) {
                star = 0;
            }
            if (score >= 350 && score < 500) {
                star = 1;
            }
            if (score >= 500 && score < 750) {
                star = 2;
            }
            if (score >= 750) {
                star = 3;
            }
            return star;
        }

  public void displayLevels(int level) {

      // Set hidden all  initially
      for (int i = 0; i < buttonList.size(); i++) {
          buttonList.get(i).setVisibility(View.GONE);
      }

      for (int i = 0; i < level; i++) {
          buttonList.get(i).setVisibility(View.VISIBLE);
          buttonList.get(i).setEnabled(true);
          String setimage = getimage(i + 1);
          String PACKAGE_NAME = getApplicationContext().getPackageName();
          int imgId = getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + setimage, null, null);
          buttonList.get(i).setBackgroundDrawable(getResources().getDrawable(imgId));
          if (i < 9) {
              buttonList.get(i + 1).setVisibility(View.VISIBLE);
              buttonList.get(i + 1).setEnabled(false);
              buttonList.get(i + 1).setBackgroundDrawable(getResources().getDrawable(R.drawable.lockedlevel));
          }
      }
  }
 }


