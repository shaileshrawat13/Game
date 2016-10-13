package shaileshrawat.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by shailesh.rawat on 10/13/2016.
 */

public class tutorial extends Activity implements View.OnClickListener {
    private static final String TAG = ".tutorial";
    private PowerManager.WakeLock mWakeLock;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (savedInstanceState == null) {
        }
        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
        setContentView(R.layout.tutorial);
        //ImageView arrow= (ImageView) findViewById(R.id.arrow);
       Button letsPlay = (Button) findViewById(R.id.button);
        letsPlay.setOnClickListener(this);



    }
    public void onClick(View v) {
        Intent levelIntent = new Intent(getApplicationContext(), Level.class);
        levelIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(levelIntent);
        tutorial.this.finish();
    }
    public void onBackPressed()    {
        this.finish();
    }
}
