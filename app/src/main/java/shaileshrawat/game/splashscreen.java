package shaileshrawat.game;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.Property;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;


/**
 * Created by shailesh.rawat on 05-07-2016.
 */
public class splashscreen extends Mediawrapper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{


    private final int SPLASH_DISPLAY_LENGTH = 3000;
    TextView game, t, ricals;
    Animation splashanim;

    public static GoogleApiClient mGoogleApiClient;

    public static boolean isSignedIn = false;

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);
        MediaPlayer splashSound = MediaPlayer.create(this, R.raw.splashsound);
        splashSound.start();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        splashanim = AnimationUtils.loadAnimation(this, R.anim.splashtexts);
        game = (TextView) findViewById(R.id.textgame);
        t = (TextView) findViewById(R.id.textT);
        ricals = (TextView) findViewById(R.id.textricals);
        game.setAnimation(splashanim);
        ricals.setAnimation(splashanim);
        gamesounds = SharedPrefsUtils.getBooleanPreference(this, "soundflag", true);
        gamemusic = SharedPrefsUtils.getBooleanPreference(this, "musicflag", true);
        startHomeActivity();
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                *//* Create an Intent that will start the Menu-Activity. *//*
                Intent mainIntent = new Intent(splashscreen.this, Homepage.class);
                splashscreen.this.startActivity(mainIntent);
                splashscreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);*/

    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            isSignedIn = true;
        }
        startHomeActivity();
    }

    @Override
    public void onConnectionSuspended(int i) {
        isSignedIn = false;
        startHomeActivity();
    }

    private void startHomeActivity(){
        Intent mainIntent = new Intent(splashscreen.this, Homepage.class);
        splashscreen.this.startActivity(mainIntent);
        splashscreen.this.finish();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("shi", connectionResult.toString());
        Toast.makeText(this, "Internet to On kar le" , Toast.LENGTH_LONG).show();

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 50);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mGoogleApiClient.connect();
            }
        } else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 50){
            Log.d("shaiasd", ""+resultCode);
            mGoogleApiClient.connect();
        }
    }
}

