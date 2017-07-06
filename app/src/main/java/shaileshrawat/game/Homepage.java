package shaileshrawat.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;


/**
 * Created by SHAILESH RAWAT on 15-05-2017.
 */

public class Homepage extends Mediawrapper {
    Button gameBtn, rulebtn, highScoreBtn, shareBtn, settingsBtn;
    public static MediaPlayer buttonHome, buttonBack, buttonLevel, rightBall, wrongBall, gamePlay, startSiren, scoreCount;
    TextView launchText, rulesText, highscoreText, sharebtnText, settingsText;
    public static boolean mediarunning=false;
    Animation homeButtonAnimations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        final String PACKAGE_NAME = getApplicationContext().getPackageName();
        homeButtonAnimations = AnimationUtils.loadAnimation(this, R.anim.buttonanimations);
        buttonHome = MediaPlayer.create(this, R.raw.homebuttons);
        buttonBack = MediaPlayer.create(this, R.raw.backbutton);
        buttonLevel = MediaPlayer.create(this, R.raw.levelbuttonsound);
        rightBall = MediaPlayer.create(this, R.raw.rightballin);
        wrongBall = MediaPlayer.create(this, R.raw.wrongballin);
        gamePlay = MediaPlayer.create(this, R.raw.game);
        startSiren = MediaPlayer.create(this, R.raw.game);
        startSiren.setVolume(0.6f, 0.6f);
        buttonHome.setVolume(1,1);
        buttonLevel.setVolume(1,1);
        rightBall.setVolume(1,1);
        wrongBall.setVolume(1,1);
        buttonBack.setVolume(1,1);
        scoreCount = MediaPlayer.create(this, R.raw.scorecount);
        if(startSiren.isPlaying()){
            startSiren.reset();
        }else {
            startSiren.start();
            mediarunning=true;
        }
        gameBtn = (Button) findViewById(R.id.gamestart);
        rulebtn = (Button) findViewById(R.id.rules);
        highScoreBtn = (Button) findViewById(R.id.highscores);
        shareBtn = (Button) findViewById(R.id.sharebtn);
        settingsBtn = (Button) findViewById(R.id.settingsbtn);
        launchText = (TextView) findViewById(R.id.launchtext);
        rulesText = (TextView) findViewById(R.id.rulestext);
        highscoreText = (TextView) findViewById(R.id.highscorestext);
        sharebtnText = (TextView) findViewById(R.id.sharetext);
        settingsText = (TextView) findViewById(R.id.settingstext);


        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonHome.start();
                //launchText.setAnimation(homeButtonAnimations);
               // gameBtn.startAnimation(homeButtonAnimations);
                homepageVisibilityShooter();
                gameBtn.setVisibility(View.VISIBLE);
                launchText.setVisibility(View.VISIBLE);
                final Handler newhandler = new Handler();
                newhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            int i=1;
                            @Override
                            public void run() {
                                i++;
                                String setimage = "gamebtn"+i;
                                int imgId = getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + setimage, null, null);
                                gameBtn.setBackground(getResources().getDrawable(imgId));
                            }
                        }, 500);

                        Intent game = new Intent(Homepage.this, Level.class);
                        startActivity(game);
                        finish();
                    }
                }, 2000);

            }
        });
        rulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonHome.start();
                rulesText.startAnimation(homeButtonAnimations);
                rulebtn.startAnimation(homeButtonAnimations);
                homepageVisibilityShooter();
                rulebtn.setVisibility(View.VISIBLE);
                rulesText.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent tutorial = new Intent(getApplicationContext(), tutorial.class);
                        startActivity(tutorial);
                        finish();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(mediarunning){
            startSiren.seekTo(0);
            startSiren.pause();
        }
      finish();
    }
    public void homepageVisibilityShooter(){
        gameBtn.setVisibility(View.INVISIBLE);
        rulebtn.setVisibility(View.INVISIBLE);
        highScoreBtn.setVisibility(View.INVISIBLE);
        shareBtn.setVisibility(View.INVISIBLE);
        settingsBtn.setVisibility(View.INVISIBLE);
        launchText.setVisibility(View.INVISIBLE);
        rulesText.setVisibility(View.INVISIBLE);
        sharebtnText.setVisibility(View.INVISIBLE);
        highscoreText.setVisibility(View.INVISIBLE);
        settingsText.setVisibility(View.INVISIBLE);

    }
}
