package shaileshrawat.game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import static shaileshrawat.game.LevelWrapper.hold;

/**
 * Created by shailesh.rawat on 11/10/2016.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                //c.finish();
                Intent levelIntent = new Intent(c, Level.class);
                levelIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(levelIntent);
                c.finish();
                
                break;
            case R.id.btn_no:
                hold=false;
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}