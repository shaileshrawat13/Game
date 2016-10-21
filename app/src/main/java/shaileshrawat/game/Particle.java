package shaileshrawat.game;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by shailesh.rawat on 9/2/2016.
 */
public class Particle { /* coefficient of restitution */
    private static final float COR = 0.3f;
    private double sensorstrength;

    public boolean visibility=true;
    public float mPosX;
    public float mPosY;
    private float mVelX;
    private float mVelY;

    Particle(){
        sensorstrength = new Random().nextInt(10) + 1 + new Random().nextFloat();
    }

    public void updatePosition(float sx, float sy, float sz, long timestamp) {
        float dt = -System.currentTimeMillis() / LevelWrapper.levelspeed;
        //System.out.println("Difference= " + (System.currentTimeMillis()));
        mVelX += -(sx) * dt;
        //System.out.println(mVelX);
        mVelY += -(sy) * dt;

        mPosX += (mVelX*sensorstrength) * dt;
        //System.out.println(mPosX);
        mPosY += (mVelY*sensorstrength) * dt;
        //System.out.println(mPosY);
    }

    public void resolveCollisionWithBounds(float mHorizontalBound, float mVerticalBound) {
        if (mPosX > mHorizontalBound) {
            mPosX = mHorizontalBound;
            mVelX = -mVelX * COR;
        } else if (mPosX < -mHorizontalBound) {
            mPosX = -mHorizontalBound;
            mVelX = -mVelX * COR;
        }
        if (mPosY > mVerticalBound) {
            mPosY = mVerticalBound;
            mVelY = -mVelY * COR;
        } else if (mPosY < -mVerticalBound) {
            mPosY = -mVerticalBound;
            mVelY = -mVelY * COR;
        }
    }
    public void resetPosition(float rx, float ry){
        mPosX=rx/2;
        mPosY=ry/2;
    }

  }
