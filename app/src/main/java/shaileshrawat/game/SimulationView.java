package shaileshrawat.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Surface;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import static shaileshrawat.game.Homepage.rightBall;
import static shaileshrawat.game.Homepage.wrongBall;
import static shaileshrawat.game.LevelWrapper.hold;
import static shaileshrawat.game.LevelWrapper.started;
import static shaileshrawat.game.LevelWrapper.timer;

import static shaileshrawat.game.LevelWrapper.*;

public class SimulationView extends View implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    public int minball=0;
    int level=LevelWrapper.level;
    public int maxBall= level*2;
    private Bitmap mGrass;
    private Bitmap mHole;
    private Paint paint;
    public static float calculatedScore=0;
    public static int  LEVEL_TIMER=20;
    private int incr=0;
    public static float decr=0;
    private float mXOrigin;
    private float mYOrigin;
    private float mHorizontalBound;
    private float mVerticalBound;
    private Typeface fontText;
    private int t=0;

    String[] color={"ORANGE","PINK","BLUE","GREEN","BROWN", "VIOLET","GRAY","OLIVE","PEACH","RED","TEAL","YELLOW","WHITE","MAGENTA","LIME","SAFFRON","SKY BLUE", "TURQUOISE","MAROON","TAN","BEIGE"};
    static String[] colortext={"#FFA500","#FFC0CB","#0000FF","#00FF00","#9E664C","#9400D3", "#696969","#137244", "#FFDAB9", "#FF0000", "#008080","#FFFF00","#FFFFFF","#AA00BB","#E3FF00","#F4C430","#87CEEB","#40E0D0"
                                ,"#990000","#D2B48C","#F5F5DC"};
    int i=0;
    private HashMap<Particle, String> ballcolormap = new HashMap<Particle, String>();
  //  private HashMap<Integer, Bitmap> hmcolour = new HashMap();
    private ArrayList<Particle> ballList= new ArrayList<Particle>();
    private ArrayList<Bitmap> ballListColour= new ArrayList<Bitmap>();

    public static Activity activity ;

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
        canvas.drawBitmap(mGrass, 0, 0, null);
            if (decr < h - 90) {
                if(levelName=="medium"){
                    if(t>=LEVEL_TIMER/2){
                        t=t-10;
                    }else
                    {
                        t=t+10;
                    }
                    canvas.drawBitmap(mHole, holex+t, holey, null);
                }else {
                    canvas.drawBitmap(mHole, holex, holey, null);
                }
                Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
                canvas.drawBitmap(drawScore(incr), (mXOrigin * 2) - 50, 0, null);
                canvas.drawBitmap(showScore(), (mXOrigin*1.50f), 0, null);
                incr = 0;
                //Ball 1
                for (int k = minball; k < maxBall; k++) {
                    Particle mball10 = ballList.get(k);
                    if (mball10.visibility) {
                        mball10.updatePosition(mSensorX, mSensorY, mSensorZ, mSensorTimeStamp);
                        mball10.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);
                        canvas.drawBitmap(drawCircle(i), (1.25f * mXOrigin),0, null);

                        if (((mXOrigin - BALL_SIZE + mball10.mPosX) >= (holex - 30) && (mXOrigin - BALL_SIZE + mball10.mPosX) <= (holex + 30)) &&
                                (mYOrigin - BALL_SIZE - mball10.mPosY) >= holey - 30 && (mYOrigin - BALL_SIZE - mball10.mPosY) <= (holey + 30)) {

                            if (ballcolormap.get(mball10).equals(colortext[i])) {
                                mball10.visibility = false;
                                incr = -2;
                                rightBall.start();
                                if (k != maxBall + -1) {
                                    i++;
                                    //calculatescore();
                                } else {
                                    calculatescore(k+1);
                                    levelFinishdialog();
                                }
                            } else {
                                mball10.resetPosition(mXOrigin, mYOrigin);
                                incr = 2;
                                wrongBall.start();
                                v.vibrate(100);
                            }
                        } else {
                            canvas.drawBitmap(ballListColour.get(k), mXOrigin - BALL_SIZE + mball10.mPosX, mYOrigin - BALL_SIZE - mball10.mPosY, null);
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
                            System.out.println(timer);
                            newhandler.postDelayed(this, 10);
                            timer=timer+.021f;
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
                            handler.postDelayed(this, 10);
                        }
                        }
                    });
                }
            } else {
                decr = 0;
                timeFinishdialog();
            }
        }

    public static Bitmap drawCircle(int j) {
        Bitmap canvasBitmap = Bitmap.createBitmap( 100, 100, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2.0f);
        paint.setColor(Color.parseColor(colortext[j]));
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawCircle(BALL_SIZE, BALL_SIZE, BALL_SIZE/2, paint);
        return canvasBitmap;
    }

    // Draw scoreBar
    public static Bitmap drawScore(float incr) {
        Bitmap canvasBitmap1 = Bitmap.createBitmap(65 , h, Bitmap.Config.ARGB_8888);
        Paint Mypaint = new Paint();
        decr = timer*(h-90)/(LevelWrapper.level*LEVEL_TIMER);
        Mypaint.setAntiAlias(true);
        int shaderColor0 = Color.GREEN;
        int shaderColor1 = Color.RED;
        Mypaint.setShader(new LinearGradient(
                0, 0,
                0, h-90,
                shaderColor0,
                shaderColor1, Shader.TileMode.CLAMP));
        Canvas canvas = new Canvas(canvasBitmap1);
        canvas.drawRect(50, h, 20, decr, Mypaint);
        return canvasBitmap1;
    }
    // Show Score
    public static Bitmap showScore() {
        Bitmap canvasBitmap = Bitmap.createBitmap(260, 100, Bitmap.Config.ARGB_8888);
        String score1;
        Typeface face=Typeface.createFromAsset(activity.getAssets(), "Fonts/neuropol.ttf");
        score1 = String.format("%.2f",(LevelWrapper.level*LEVEL_TIMER)-timer);
        Paint paint = new Paint();
        Paint mPaint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(face);
        paint.setColor(Color.WHITE);
        paint.setTextSize(65f);
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawText(score1, 0, 100, paint);
        return canvasBitmap;
    }
    public void calculatescore(int totalBallsTaken){

        if(totalBallsTaken == 0){
            calculatedScore = 0;
        }else{
            double ballTotalWeightage = (500.0f / maxBall ) * totalBallsTaken;
            double timeTakenTotalWeightage = 500.0f;
            double timeTaken = timer;//secs;
            double timePercentage = (timeTaken / (LEVEL_TIMER * level)) * 100;

            if(timePercentage > 20){
                timeTakenTotalWeightage = ((100.0f-timePercentage) * timeTakenTotalWeightage)/100;
            }
            calculatedScore = (float) (ballTotalWeightage + timeTakenTotalWeightage);
        }
        /*float perballScore = 1000.0f/(float)maxBall;
        if (timer<=((level*LEVEL_TIMER)/4)){
            calculatedScore+=perballScore;
        }
        else if(timer<=((level*LEVEL_TIMER)/2)){
            calculatedScore+=(perballScore*.75);
        } else if (timer<=(((level*LEVEL_TIMER)/4)*3)){
            calculatedScore+=(perballScore/2);
        }else
        {
            calculatedScore+=(perballScore/4);
        }*/
    }
    private void saveLocalScore(){
        if (calculatedScore>=1000){
            calculatedScore=1000;
        }
        String med="";
        if (levelName=="medium"){
            med="medium";
        }
        if (level==1 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level1", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level1", calculatedScore);
        }
        if (level==2 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level2", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level2", calculatedScore);
        }
        if (level==3 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level3", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level3", calculatedScore);
        }
        if (level==4 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level4", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level4", calculatedScore);
        }
        if (level==5 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level5", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level5", calculatedScore);
        }
        if (level==6 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level6", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level6", calculatedScore);
        }
        if (level==7 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level7", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level7", calculatedScore);
        }
        if (level==8 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level8", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level8", calculatedScore);
        }
        if (level==9 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level9", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level9", calculatedScore);
        }
        if (level==10 && (SharedPrefsUtils.getFloatPreference((Context)activity, med+"Level10", 0) <= calculatedScore)){
            SharedPrefsUtils.setFloatPreference((Context)activity, med+"Level10", calculatedScore);
        }
    }
    private void timeFinishdialog() {
        saveLocalScore();
        Intent finish = new Intent(activity, Finish_Activity.class);
        finish.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish.putExtra("Text", "TIME UP!");
        activity.startActivity(finish);
    }
    private void levelFinishdialog() {
        saveLocalScore();
        hold=true;
        Intent finish = new Intent(activity, Finish_Activity.class);
        finish.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish.putExtra("Text", "LEVEL COMPLETED!");
        activity.startActivity(finish);
    }

}