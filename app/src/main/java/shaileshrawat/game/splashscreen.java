package shaileshrawat.game;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.util.Property;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;


/**
 * Created by shailesh.rawat on 05-07-2016.
 */
public class splashscreen extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 1000;
    TextView game, t, ricals;
    Animation splashanim;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);
        final int[] drawablearray=new int[]{R.drawable.wenzi0000,R.drawable.wenzi0010,R.drawable.wenzi0019,R.drawable.wenzi0024,R.drawable.wenzi0030};
        splashanim = AnimationUtils.loadAnimation(this, R.anim.splashtexts);
        game= (TextView)findViewById(R.id.textgame);
        t= (TextView)findViewById(R.id.textT);
        ricals= (TextView)findViewById(R.id.textricals);
        game.setAnimation(splashanim);
        ricals.setAnimation(splashanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splashscreen.this, Homepage.class);
                splashscreen.this.startActivity(mainIntent);
                splashscreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);



    }
    @Override
    public void onBackPressed() {
    }

    }

