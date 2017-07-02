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

        buttonHome = MediaPlayer.create(this, R.raw.smartball_buttonhome);
        buttonBack = MediaPlayer.create(this, R.raw.smartball_randombuttons);
        buttonLevel = MediaPlayer.create(this, R.raw.smartball_buttonlevel);
        rightBall = MediaPlayer.create(this, R.raw.smartball_rightball);
        wrongBall = MediaPlayer.create(this, R.raw.smartball_wrongball);
        gamePlay = MediaPlayer.create(this, R.raw.smartball_gameplay);
        startSiren = MediaPlayer.create(this, R.raw.smartball_gameplaystarts);
        homesound = MediaPlayer.create(this, R.raw.smartball_home);
        startSiren.start();
        startSiren.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                homesound.start();
            }
        });
        gameBtn = (Button) findViewById(R.id.gamestart);
        rulebtn = (Button) findViewById(R.id.rules);
        highScoreBtn = (Button) findViewById(R.id.highscores);
        shareBtn = (Button) findViewById(R.id.sharebtn);
        settingsBtn = (Button) findViewById(R.id.settingsbtn);

        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent game = new Intent(Homepage.this, Level.class);
                startActivity(game);
                finish();
            }
        });
        rulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tutorial = new Intent(getApplicationContext(), tutorial.class);
                startActivity(tutorial);
                finish();
            }
        });
    }
}
