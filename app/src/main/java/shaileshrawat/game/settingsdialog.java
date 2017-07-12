package shaileshrawat.game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import static shaileshrawat.game.Homepage.startSiren;
import static shaileshrawat.game.LevelWrapper.hold;
import static shaileshrawat.game.Mediawrapper.gamemusic;
import static shaileshrawat.game.Mediawrapper.gamesounds;
import static shaileshrawat.game.SimulationView.decr;

/**
 * Created by shailesh.rawat on 7/12/2017.
 */

public class settingsdialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Button music, sound, more;


    public settingsdialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings);
        this.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        this.getWindow().setDimAmount(0.0f);
        music = (Button) findViewById(R.id.musicbtn);
        if(gamemusic){
            music.setBackground(c.getResources().getDrawable(R.drawable.musicon));
        }else
        {
            music.setBackground(c.getResources().getDrawable(R.drawable.musicoff));
        }
        sound = (Button) findViewById(R.id.soundbtn);
        if(gamesounds){
            sound.setBackground(c.getResources().getDrawable(R.drawable.soundson));
        }else
        {
            sound.setBackground(c.getResources().getDrawable(R.drawable.soundsoff));
        }
        more = (Button) findViewById(R.id.moredevbtn);
        more.setOnClickListener(this);
        music.setOnClickListener(this);
        sound.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.musicbtn:
                if(gamemusic) {
                    gamemusic = false;
                    SharedPrefsUtils.setBooleanPreference(c, "musicflag", false);
                    music.setBackground(v.getResources().getDrawable(R.drawable.musicoff));
                    if(startSiren.isPlaying()){
                        startSiren.pause();
                    }
                }else{
                    gamemusic = true;
                    SharedPrefsUtils.setBooleanPreference(c, "musicflag", true);
                    music.setBackground(v.getResources().getDrawable(R.drawable.musicon));
                    if(!startSiren.isPlaying()){
                        startSiren.start();
                    }
                }
                break;
            case R.id.soundbtn:
                if(gamesounds) {
                    gamesounds = false;
                    SharedPrefsUtils.setBooleanPreference(c, "soundflag", false);
                    sound.setBackground(v.getResources().getDrawable(R.drawable.soundsoff));
                }else{
                    gamesounds = true;
                    SharedPrefsUtils.setBooleanPreference(c, "soundflag", true);
                    sound.setBackground(v.getResources().getDrawable(R.drawable.soundson));
                }
                break;
            case R.id.moredevbtn:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Smartly+Innovated"));
                c.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
