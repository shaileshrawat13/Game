//package shaileshrawat.game;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.widget.ProgressBar;
//
///**
// * Created by shailesh.rawat on 10/17/2016.
// */
//
//public class ScoreBar {
//
//    MyCountDownTimer myCountDownTimer;
//    myCountDownTimer = new MyCountDownTimer(60000, 100);
//    myCountDownTimer.start();
//
//
//    public class MyCountDownTimer extends CountDownTimer {
//        // millis in Future = Time for Timer
//// countDown Interval = Callback time
//        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//
//            int progress = (int) (millisUntilFinished / 100);
//            System.out.println(progress);
//            progressBar.setProgress(progress);
//        }
//
//        @Override
//        public void onFinish() {
//            finish();
//
//        }
//    }
//}
//}
