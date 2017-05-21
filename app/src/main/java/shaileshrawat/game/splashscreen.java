package shaileshrawat.game;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.util.Property;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;


/**
 * Created by shailesh.rawat on 05-07-2016.
 */
public class splashscreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splashscreen.this,Homepage.class);
                splashscreen.this.startActivity(mainIntent);
                splashscreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        final TextView textView = (TextView) findViewById(R.id.textsss);
        String text = textView.getText().toString();
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Fonts/levelfonts.ttf");
        textView.setTypeface(myTypeface);

        RainbowText span = new RainbowText(this);

        final SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(span, 0, text.length(), 0);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                span, ANIMATED_COLOR_SPAN_FLOAT_PROPERTY, 0, 100);
        objectAnimator.setEvaluator(new FloatEvaluator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(spannableString);
            }
        });
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(DateUtils.MINUTE_IN_MILLIS * 3);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
                }

    private static final Property<RainbowText, Float> ANIMATED_COLOR_SPAN_FLOAT_PROPERTY
            = new Property<RainbowText, Float>(Float.class, "ANIMATED_COLOR_SPAN_FLOAT_PROPERTY") {
        @Override
        public void set(RainbowText span, Float value) {
            span.setTranslateXPercentage(value);
        }
        @Override
        public Float get(RainbowText span) {
            return span.getTranslateXPercentage();
        }
    };
    @Override
    public void onBackPressed() {
    }

    }

