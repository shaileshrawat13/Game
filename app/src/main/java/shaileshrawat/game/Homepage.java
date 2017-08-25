package shaileshrawat.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.games.Games;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;


/**
 * Created by SHAILESH RAWAT on 15-05-2017.
 */

public class Homepage extends Mediawrapper {
    public static Button gameBtn, rulebtn, highScoreBtn, shareBtn, settingsBtn;
    public static MediaPlayer buttonHome, buttonBack, buttonLevel, rightBall, wrongBall, gamePlay, startSiren, scoreCount;
    TextView launchText, rulesText, highscoreText, sharebtnText, settingsText;
    public static boolean mediarunning=false;
    Animation homeButtonanimations, rotate;
    boolean abort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        final String PACKAGE_NAME = getApplicationContext().getPackageName();
        buttonHome = MediaPlayer.create(this, R.raw.homebuttons);
        buttonBack = MediaPlayer.create(this, R.raw.backbutton);
        buttonLevel = MediaPlayer.create(this, R.raw.levelbuttonsound);
        rightBall = MediaPlayer.create(this, R.raw.rightballin);
        wrongBall = MediaPlayer.create(this, R.raw.wrongballin);
        startSiren = MediaPlayer.create(this, R.raw.game);
        startSiren.setVolume(0.6f, 0.6f);
        buttonHome.setVolume(1,1);
        buttonLevel.setVolume(1,1);
        rightBall.setVolume(1,1);
        wrongBall.setVolume(1,1);
        buttonBack.setVolume(1,1);
        scoreCount = MediaPlayer.create(this, R.raw.scorecount);
        if(gamemusic) {
            if (startSiren.isPlaying()) {
                startSiren.reset();
            } else {
                startSiren.start();
                mediarunning = true;
            }
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
        homeButtonanimations = AnimationUtils.loadAnimation(this, R.anim.buttonanimations);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);

        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gamesounds) {
                    buttonHome.start();
                }
                homepageVisibilityShooter();
                gameBtn.setVisibility(View.VISIBLE);
                gameBtn.setBackground(getResources().getDrawable(R.drawable.gamebtn4));
                gameBtn.setAnimation(homeButtonanimations);
                homeButtonanimations.setAnimationListener(new Animation.AnimationListener(){

                    @Override
                    public void onAnimationStart(Animation animation) {
                        gameBtn.setClickable(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent tutorial = new Intent(getApplicationContext(), Level.class);
                        startActivity(tutorial);
                        finish();
                    }
                }, 3000);
            }
        });
        rulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gamesounds) {
                    buttonHome.start();
                }
                homepageVisibilityShooter();
                rulebtn.setVisibility(View.VISIBLE);
                rulebtn.setBackground(getResources().getDrawable(R.drawable.rulesbtn4));
                rulebtn.setAnimation(homeButtonanimations);
                homeButtonanimations.setAnimationListener(new Animation.AnimationListener(){

                    @Override
                    public void onAnimationStart(Animation animation) {
                        rulebtn.setClickable(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent tutorial = new Intent(getApplicationContext(), tutorial.class);
                        startActivity(tutorial);
                        finish();
                    }
                }, 3000);

            }
        });

        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gamesounds) {
                    buttonHome.start();
                }
                homepageVisibilityShooter();
                highScoreBtn.setVisibility(View.VISIBLE);
                highScoreBtn.setBackground(getResources().getDrawable(R.drawable.highscorebtn4));
                highScoreBtn.setAnimation(homeButtonanimations);
                homeButtonanimations.setAnimationListener(new Animation.AnimationListener(){

                    @Override
                    public void onAnimationStart(Animation animation) {
                        highScoreBtn.setClickable(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(splashscreen.isSignedIn){
                            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(splashscreen.mGoogleApiClient,
                                    "1001260003726"), REQUEST_LEADERBOARD);
                        }
                        finish();
                    }
                }, 3000);

            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gamesounds){
                    buttonBack.start();
                }
                shareBtn.startAnimation(rotate);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Fantastic!\nDownload Smart Ball in the Hole from Playstore\n" +
                        "https://play.google.com/store/apps/details?id=smartlyinnovation.horoscope2018");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Hello"));
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gamesounds) {
                    buttonBack.start();
                }
                settingsBtn.setBackground(getResources().getDrawable(R.drawable.settingsbtn2));
                settingsBtn.startAnimation(rotate);
                    settingsdialog sdd=new settingsdialog(Homepage.this);
                    sdd.show();
            }
        });
    }

    private int REQUEST_LEADERBOARD = 20;

    @Override
    public void onBackPressed() {
       if (gamemusic) {
           if (mediarunning) {
               startSiren.seekTo(0);
               startSiren.pause();
           }
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
