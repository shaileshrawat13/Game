package shaileshrawat.game;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;

import static shaileshrawat.game.Homepage.scoreCount;
import static shaileshrawat.game.Level.MEDIUMLEVEL;
import static shaileshrawat.game.LevelWrapper.hold;
import static shaileshrawat.game.LevelWrapper.levelName;
import static shaileshrawat.game.LevelWrapper.levelno;
import static shaileshrawat.game.LevelWrapper.mediumlevelno;
import static shaileshrawat.game.LevelWrapper.timer;
import static shaileshrawat.game.SimulationView.activity;
import static shaileshrawat.game.SimulationView.calculatedScore;
import static shaileshrawat.game.SimulationView.decr;

/**
 * Created by SHAILESH RAWAT on 01-06-2017.
 */

public class ScoreLayout extends Activity{
    Button relaunch, missionSelect, sharebtn1;
    TextView levelText, scoreText, zeroscore;
    ImageView star1, star2, star3;
    Animation fadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        levelText = (TextView) findViewById(R.id.levelCompleteText);
        if (levelName==MEDIUMLEVEL){
            levelText.setText("LEVEL " + mediumlevelno + " COMPLETED");
        }else
        {
            levelText.setText("LEVEL " + levelno + " COMPLETED");
        }

        scoreText = (TextView) findViewById(R.id.displayScoreText);
        animateTextView(0,(int)calculatedScore , scoreText);
        //scoreText.setText(String.valueOf((int)calculatedScore));
        star1 = (ImageView) findViewById(R.id.display1Star);
        star2 = (ImageView) findViewById(R.id.display2Star);
        star3 = (ImageView) findViewById(R.id.display3star);
        zeroscore = (TextView) findViewById(R.id.zeroscore);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        if((Level.displayLevelBG(calculatedScore)==0)) {
            zeroscore.setVisibility(View.VISIBLE);
        }
        else if ((Level.displayLevelBG(calculatedScore)==1)){
                star1.setVisibility(View.VISIBLE);
                star1.startAnimation(fadeIn);
            }else if ((Level.displayLevelBG(calculatedScore)==2)){
                star1.setVisibility(View.VISIBLE);
                star1.startAnimation(fadeIn);
                star2.setVisibility(View.VISIBLE);
                star2.startAnimation(fadeIn);
            }else
            {
                star1.setVisibility(View.VISIBLE);
                star1.startAnimation(fadeIn);
                star2.setVisibility(View.VISIBLE);
                star2.startAnimation(fadeIn);
                star3.setVisibility(View.VISIBLE);
                star3.startAnimation(fadeIn);
                if (LevelWrapper.level == levelno) {
                    levelno++;
                }
                if(levelName==MEDIUMLEVEL){
                    if (LevelWrapper.level == mediumlevelno) {
                        mediumlevelno++;
                    }
                    SharedPrefsUtils.setIntegerPreference(getApplicationContext(), "MediumLevelNO", levelno);
                }else{
                SharedPrefsUtils.setIntegerPreference(getApplicationContext(), "LevelNO", levelno);}

            }

        relaunch = (Button) findViewById(R.id.relaunch);
        missionSelect = (Button) findViewById(R.id.missionSelect);
        sharebtn1 = (Button) findViewById(R.id.sharebtn1);
        relaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decr=0;
                hold=false;
                timer=0;
                calculatedScore=0;
                Intent mainIntent = new Intent(getApplicationContext(), Gamehome.class);
                startActivity(mainIntent);
                finish();
            }
        });
        missionSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decr=0;
                timer=0;
                hold=false;
                calculatedScore=0;
                Intent game = new Intent(getApplicationContext(), Level.class);
                startActivity(game);
            }
        });
        sharebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureandstore(view);
                File dirPath = new File(Environment.getExternalStorageDirectory() + "/smartball.png");
                shareScreenshot(dirPath);
            }
        });

    }

    public void animateTextView(int initialValue, int finalValue, final TextView  textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();
        scoreCount.start();
    }
    public static void captureandstore(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        File dirPath = new File(Environment.getExternalStorageDirectory() + "/smartball.png");
        try {
            FileOutputStream fOut = new FileOutputStream(dirPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 20, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void shareScreenshot(File file){
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Nice..!! I achieved this");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        activity.finish();
        Intent levelIntent = new Intent(getApplicationContext(), Level.class);
        startActivity(levelIntent);
        this.finish();
    }
}
