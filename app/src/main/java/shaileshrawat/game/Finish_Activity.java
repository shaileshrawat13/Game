package shaileshrawat.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by SHAILESH RAWAT on 31-05-2017.
 */

public class Finish_Activity extends Activity {

    TextView finalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_activity);
        finalText = (TextView) findViewById(R.id.finaltext);
        Intent thisIntent = getIntent();
        finalText.setText(thisIntent.getStringExtra("Text").toString());
        Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scaling);
        finalText.setAnimation(scaleAnim);
        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                //TODO: Do something every second
            }

            public void onFinish() {
                finish();
                Intent score = new Intent(getApplicationContext(), ScoreLayout.class);
                score.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                score.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getApplicationContext().startActivity(score);
                finish();
                //YourActivity.finish();  outside the actvitiy
            }
        }.start();
    }
}
