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
    public String msg;

    public CustomDialogClass(Activity a, String message) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        msg=message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        this.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        this.getWindow().setDimAmount(0.0f);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        Typeface myTypeface = Typeface.createFromAsset(c.getAssets(), "Fonts/neuropol.ttf");
        message = (TextView) findViewById(R.id.txt_dia);
        message.setText(msg);
        button1 = (Button) findViewById(R.id.btn_yes);
        button1.setTypeface(myTypeface);
        button2 = (Button) findViewById(R.id.btn_no);
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
                    Intent levelIntent = new Intent(c, Level.class);
                    levelIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.getApplicationContext().startActivity(levelIntent);
                    decr = 0;
                    c.finish();
                dismiss();
                break;
            case R.id.btn_no:
                    hold=false;
                    dismiss();
                    break;
            default:
                break;
        }
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}