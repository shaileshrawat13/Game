package shaileshrawat.game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static shaileshrawat.game.LevelWrapper.hold;
import static shaileshrawat.game.LevelWrapper.levelno;
import static shaileshrawat.game.LevelWrapper.started;
import static shaileshrawat.game.LevelWrapper.timer;
import static shaileshrawat.game.SimulationView.decr;

/**
 * Created by shailesh.rawat on 11/10/2016.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Button button1, button2;
    public TextView message;
    public String msg, btn1, btn2;

    public CustomDialogClass(Activity a, String message, String btn1msg, String btn2msg) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        msg=message;
        btn1= btn1msg;
        btn2= btn2msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getWindow();
        Typeface myTypeface = Typeface.createFromAsset(c.getAssets(), "Fonts/neuropol.ttf");
        message = (TextView) findViewById(R.id.txt_dia);
        message.setText(msg);
        button1 = (Button) findViewById(R.id.btn_yes);
        button1.setText(btn1);
        button1.setTypeface(myTypeface);
        button2 = (Button) findViewById(R.id.btn_no);
        button2.setText(btn2);
        button2.setTypeface(myTypeface);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        hold=true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                //c.finish();
                if (btn1.equals("Restart") ||btn1.equals("Replay") ){
                    // if this button is clicked, close
                    // current activity
                    decr=0;
                    hold=false;
                    timer=0;
                    c.recreate();

                }else {

                    Intent levelIntent = new Intent(c, Level.class);
                    levelIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.getApplicationContext().startActivity(levelIntent);
                    decr = 0;
                    c.finish();
                }
                dismiss();
                break;
            case R.id.btn_no:
                if (btn2.equals("Exit")){
                    decr=0;
                    timer=0;
                    hold=false;
                    Intent levelIntent = new Intent(c, Level.class);
                    levelIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.startActivity(levelIntent);
                    c.finish();
                }
                else {
                    if (btn2.equals("Next")){
                        Intent levelIntent = new Intent();
                        levelIntent.setClass(getContext(), Level.class);
                        getContext().startActivity(levelIntent);
                        if (LevelWrapper.level == levelno) {
                            levelno++;
                        }
                        SharedPrefsUtils.setIntegerPreference(getContext(), "LevelNO", levelno);
                        c.finish();
                        }
                        else{
                            hold=false;
                        }
                    }

                dismiss();
                break;
                default:
                break;
        }
        dismiss();
        }

}