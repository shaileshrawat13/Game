package shaileshrawat.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by SHAILESH RAWAT on 15-05-2017.
 */

public class Homepage extends Activity {
    Button gameBtn, rulebtn, highScoreBtn, shareBtn, settingsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
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
