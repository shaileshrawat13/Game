package shaileshrawat.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static shaileshrawat.game.LevelWrapper.curtime;
import static shaileshrawat.game.LevelWrapper.hold;
import static shaileshrawat.game.LevelWrapper.levelno;
import static shaileshrawat.game.LevelWrapper.started;
import static shaileshrawat.game.LevelWrapper.timer;
import static shaileshrawat.game.R.id.time;

/**
 * Created by shailesh.rawat on 9/2/2016.
 */
public class SimulationView extends View implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Display mDisplay;

    private Bitmap mGrass, mGreen;
    private Bitmap mHole;
    private Paint paint;

    private static int BALL_SIZE = 60;
    private static final int HOLE_SIZE = 150;

    public static int w;
    public static int h;

    private int incr=0;
    public static float decr=0;

    private float mXOrigin;
    private float mYOrigin;
    private float mHorizontalBound;
    private float mVerticalBound;
    int level=LevelWrapper.level;
    String[] color={"ORANGE","PINK","BLUE","GREEN","BROWN", "VIOLET","GRAY","OLIVE","PEACH","RED","TEAL","YELLOW","WHITE","MAGENTA","LIME","SAFFRON","SKY BLUE", "TURQUOISE","MAROON","TAN","BEIGE"};
    static String[] colortext={"#FFA500","#FFC0CB","#0000FF","#00FF00","#9E664C","#9400D3", "#696969","#137244", "#FFDAB9", "#FF0000", "#008080","#FFFF00","#FFFFFF","#AA00BB","#E3FF00","#F4C430","#87CEEB","#40E0D0"
                                ,"#990000","#D2B48C","#F5F5DC"};
    int i=0;
    private HashMap<Particle, String> ballcolormap = new HashMap();
    private HashMap<Integer, Bitmap> hmcolour = new HashMap();
    private List<Particle> ballList= new ArrayList();
    private List<Bitmap> ballListColour= new ArrayList();
    int minball=0;
    int maxBall= level*2;
    private static Activity activity ;

    public SimulationView(Activity activity) {
        super((Context)activity);
       this.activity = activity;

        for(int k = minball; k <maxBall; k++) {

            Particle mBall11 = new Particle();
            Bitmap dynballBitmap = drawCircle(k);
            ballList.add(mBall11);
            ballListColour.add(dynballBitmap);
            ballcolormap.put(mBall11,colortext[k]);
        }

        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(90f);

        // Draw Holes
        Bitmap hole = BitmapFactory.decodeResource(getResources(), R.drawable.blackhole2);
        mHole = Bitmap.createScaledBitmap(hole, HOLE_SIZE, HOLE_SIZE, true);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        // Draw Grass

        mGrass = BitmapFactory.decodeResource(getResources(), R.drawable.wood, opts);

        Point size = new Point();


        WindowManager mWindowManager = (WindowManager) ((Context)activity).getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();
        mDisplay.getSize(size);
        w = size.x;
        h = size.y;
        //System.out.println(screenHeight + " " + screenWidth);
        BALL_SIZE=w*h/34560;
        mSensorManager = (SensorManager) ((Context)activity).getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    protected void onSizeChanged(int w1, int h1, int oldw, int oldh) {
        mXOrigin = w1 * 0.5f;
        mYOrigin = h1 * 0.5f;

        mHorizontalBound = ((w1) * 0.5f)-BALL_SIZE/2;

        mVerticalBound = ((h1) * 0.5f)-BALL_SIZE/2;
    }

    private float mSensorX;
    private float mSensorY;
    private float mSensorZ;
    private long mSensorTimeStamp;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;

        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }
        mSensorZ = event.values[2];
        mSensorTimeStamp = event.timestamp;
        //System.out.println("Event "+ event.timestamp);
        //mSensorTimeStamp =35828590473855;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void startSimulation() {

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public void stopSimulation() {
        mSensorManager.unregisterListener(this);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float holex = mXOrigin / 2;
        float holey = mYOrigin / 3;
        //System.out.println("HOLEX" + holex + " " + holey);
        //System.out.println((holex-30) + " " + (holey-5));

        canvas.drawBitmap(mGrass, 0, 0, null);
        canvas.drawBitmap(mHole, holex, holey, null);

            if (decr < h - 90) {

                paint.setColor(Color.parseColor(colortext[i]));
                canvas.drawText(color[i], mXOrigin, (2 * mYOrigin), paint);
                canvas.drawBitmap(drawScore(incr), (mXOrigin * 2) - 50, 0, null);
                canvas.drawBitmap(showScore(), w - 150, h - 100, null);
                incr = 0;

                //Ball 1
                for (int k = minball; k < maxBall; k++) {
                    Particle mball10 = ((Particle) ballList.get(k));
                    if (mball10.visibility) {
                        mball10.updatePosition(mSensorX, mSensorY, mSensorZ, mSensorTimeStamp);
                        //System.out.println(mSensorX + " " +  mSensorY+ " " + mSensorZ+ " " + mSensorTimeStamp);
                        //System.out.println("Sensor" + mSensorTimeStamp);
                        mball10.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);
                        canvas.drawBitmap(drawCircle(i), (1.42f * mXOrigin), (1.91f * mYOrigin), null);

                        if (((mXOrigin - BALL_SIZE + mball10.mPosX) >= (holex - 30) && (mXOrigin - BALL_SIZE + mball10.mPosX) <= (holex + 30)) &&
                                (mYOrigin - BALL_SIZE - mball10.mPosY) >= holey - 30 && (mYOrigin - BALL_SIZE - mball10.mPosY) <= (holey + 30)) {

                            if (ballcolormap.get(mball10).equals(colortext[i])) {
                                mball10.visibility = false;
                                incr = -2;
                                if (k != maxBall + -1) {
                                    i++;
                                } else {
                                    Intent levelIntent = new Intent();
                                    levelIntent.setClass(getContext(), Level.class);
                                    getContext().startActivity(levelIntent);
                                    if (LevelWrapper.level == levelno) {
                                        levelno++;
                                    }
                                    SharedPrefsUtils.setIntegerPreference(getContext(), "LevelNO", levelno);
                                    activity.finish();


                                }


                            } else {
                                mball10.resetPosition(mXOrigin, mYOrigin);
                                incr = 2;
                            }

                        } else {
                            canvas.drawBitmap(ballListColour.get(k), mXOrigin - BALL_SIZE + mball10.mPosX, mYOrigin - BALL_SIZE - mball10.mPosY, null);
                            //System.out.println((mXOrigin -BALL_SIZE + mball10.mPosX) + " " + (mYOrigin - BALL_SIZE - mball10.mPosY));
                        }
                    }
                }

                final Handler newhandler = new Handler();

                if(!started){
                    started = true;
                    newhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(!hold){
                                timer++;
                                newhandler.postDelayed(this, 1000);
                            }else{
                                started = false;
                            }

                        }
                    });
                }

                if(!hold){
                    invalidate();

                }else{
                   final  Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(!hold){
                                invalidate();
                            }else{
                                handler.postDelayed(this, 100);
                            }
                        }
                    });
                }



            } else {
                decr = 0;
                showPopup("Duh! Duh! Time Up to finish this level." +
                        "\nClick 'Restart' to play again");
            /*Intent levelIntent = new Intent();
            levelIntent.setClass(getContext(), AndroidPopupWindowActivity.class);
            getContext().startActivity(levelIntent);
            activity.finish();*/
            }
        }



    public static Bitmap drawCircle(int j) {
        Bitmap canvasBitmap = Bitmap.createBitmap( 100, 100, Bitmap.Config.ARGB_8888);
        //BitmapShader shader = new BitmapShader(canvasBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(colortext[j]));
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawCircle(50, 50, BALL_SIZE/2, paint);
        return canvasBitmap;
    }

    // Draw scoreBar

    public static Bitmap drawScore(float incr) {
        Bitmap canvasBitmap1 = Bitmap.createBitmap(65 , h, Bitmap.Config.ARGB_8888);

        Paint Mypaint = new Paint();
        /*float score=0;
        long elapsedtime = System.currentTimeMillis();
        score=score+((elapsedtime-curtime)/1000)+incr;*/

        decr = timer*(h-90)/(LevelWrapper.level*10);
        /*System.out.println("sec=" + score);
        System.out.println(decr);*/
        Mypaint.setAntiAlias(true);
        int shaderColor0 = Color.GREEN;
        int shaderColor1 = Color.RED;

        Mypaint.setShader(new LinearGradient(
                0, 0,
                0, h-90,
                shaderColor0,
                shaderColor1, Shader.TileMode.CLAMP));

        //paint.setColor(Color.parseColor(colortext[j]));

        Canvas canvas = new Canvas(canvasBitmap1);
        canvas.drawRect(50, h - 90, 20, decr, Mypaint);
        return canvasBitmap1;
    }

    public static Bitmap showScore() {
        Bitmap canvasBitmap = Bitmap.createBitmap( 200, 100, Bitmap.Config.ARGB_8888);
        String score1;

        long elapsedtime = System.currentTimeMillis();
        //score1 = String.valueOf((elapsedtime-curtime)/1000);
        score1 = String.valueOf(timer);
        //System.out.println(score1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTextSize(90f);
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawText(score1, 0, 80, paint);
        return canvasBitmap;
    }
    private void showPopup(String testString) {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle("");
        // set dialog message
        alertDialogBuilder
                .setMessage("Duh..!! Your time is up")
                .setCancelable(false)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        LevelWrapper.curtime = System.currentTimeMillis();
                        decr=0;
                        timer=0;
                        activity.recreate();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        decr=0;
                        Intent levelIntent = new Intent(activity, Level.class);
                        levelIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(levelIntent);
                        activity.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}