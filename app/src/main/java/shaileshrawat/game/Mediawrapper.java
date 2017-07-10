package shaileshrawat.game;
import android.app.Activity;

import static shaileshrawat.game.Homepage.startSiren;


/**
 * Created by shailesh.rawat on 24-06-2016.
 */
public abstract class Mediawrapper extends Activity
{
    public static boolean gamesounds = true;
    public static boolean gamemusic = true;
    @Override
    protected void onPause() {
        if(Homepage.mediarunning) {
            startSiren.pause();
        }
        super.onPause();
    }
    protected void onResume(){

        if(Homepage.mediarunning) {

            startSiren.start();
        }
        super.onResume();
    }
}