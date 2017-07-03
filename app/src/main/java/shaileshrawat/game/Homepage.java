package shaileshrawat.game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by SHAILESH RAWAT on 15-05-2017.
 */

public class Homepage extends Activity {
    Button gameBtn, rulebtn, highScoreBtn, shareBtn, settingsBtn;
    public static MediaPlayer buttonHome, buttonBack, buttonLevel, rightBall, wrongBall, gamePlay, startSiren, homesound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        buttonHome = MediaPlayer.create(this, R.raw.homebuttons);
        buttonBack = MediaPlayer.create(this, R.raw.backbutton);
        buttonLevel = MediaPlayer.create(this, R.raw.levelbuttonsound);
        rightBall = MediaPlayer.create(this, R.raw.rightballin);
        wrongBall = MediaPlayer.create(this, R.raw.wrongballin);
        gamePlay = MediaPlayer.create(this, R.raw.game);
        startSiren = MediaPlayer.create(this, R.raw.game);
        startSiren.start();
        gameBtn = (Button) findViewById(R.id.gamestart);
        rulebtn = (Button) findViewById(R.id.rules);
        highScoreBtn = (Button) findViewById(R.id.highscores);
        shareBtn = (Button) findViewById(R.id.sharebtn);
        settingsBtn = (Button) findViewById(R.id.settingsbtn);

        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonHome.start();
                Intent game = new Intent(Homepage.this, Level.class);
                startActivity(game);
                finish();
            }
        });
        rulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonHome.start();
                Intent tutorial = new Intent(getApplicationContext(), tutorial.class);
                startActivity(tutorial);
                finish();
            }
        });
    }
}
