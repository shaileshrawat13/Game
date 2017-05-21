package shaileshrawat.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by shailesh.rawat on 9/14/2016.
 */
public class LevelWrapper {
    static int level;
    static float levelspeed;
    static int levelno=1;
    static boolean hold=false;
    static boolean started = false;
    static int timer=0;
    public static int BALL_SIZE;
    public static int HOLE_SIZE;
    public static Display mDisplay;
    public static int w,h;

    private Context context;

    public LevelWrapper(Context context){
        this.context = context;

        Point size = new Point();
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();
        mDisplay.getSize(size);
        //System.out.println(screenHeight + " " + screenWidth);
        BALL_SIZE=size.y/30;
        HOLE_SIZE=BALL_SIZE*2;
        w=size.x;
        h=size.y;
    }

}
