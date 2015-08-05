package com.titaniumstick.countdown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

/*
this .java is used to allow user to set up the habit timer which will tell them when to work and when to take a break
No restriction to the phone utility will be imposed as of now. Once started, the app will simply send cue to the users ( via vibratiob,
or vibration + notification if outside app) when to work and when to take a break.

Some limitation will be imposed on the selection of the timing. Addtionally, interaction  button will be available/unavailable depending on the
users'setup. For example. if chosen number of cycle is 1, break time will remain at 0 minutes and are uneditable since editing it will be pointless

Once the users has finished deciding the cycle's characteristic, they should push the go focus button. This will gather the user's input, send it to the next activity
which will display the timer and the cycle status period and carry out further necessary function.
 */
public class MainActivity extends AppCompatActivity implements MinuteDialog.NoticeDialogListener, WorkBreakDialog.NoticeDialogListener, BreakDialog.NoticeDialogListener, ConfirmationDialog.NoticeDialogListener  {
    public final static String SEND_CYCLE = "com.titaniumstick.countdownver2.CYCLE";
    public final static String SEND_WORK = "com.titaniumstick.countdownver2.WORK";
    public final static String SEND_BREAK = "com.titaniumstick.countdownver2.BREAK";

    Button cycleSet;
    Button workSet;
    Button breakSet;
    // result variable
    long cycleNo = 4;
    long workNo = 0;
    long breakNo = 0;

    TextView breakT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set up three button that is going to use to decide on the cycle
        cycleSet = (Button) findViewById(R.id.cycleButton);
        workSet = (Button) findViewById(R.id.workButton);
        breakSet = (Button) findViewById(R.id.breakButton);
        breakT = (TextView) findViewById(R.id.breakText);



    }
    // this method is used to check the user's input, and give suitable response
    // pre condition :
    // post condition: shoot method to collect the data as intent's  extra and shoot the relevant activity
    //                 . if no work condition, a warning will be toasted.
    //                 , if cycle > 1, but break time == 0, check to confirm user's decision
    public void focusGo(View view){
        if( workNo == 0) {
           // title.setText("NO TIME SET!");
            //title.setTypeface(null, Typeface.BOLD);
            //hourSet.setHintTextColor(Color.parseColor("#FF0000"))
            //minuteSet.setTextColor(Color.parseColor("#ff0000"));
            Toast.makeText(this,"You don't have any working duration!", Toast.LENGTH_LONG).show();
        } else {
            // if number of cycle is more than one but the users don't have a break
            if ((cycleNo != 1)&&( breakNo ==0)) {
            // shoot dialog to confirm his set-up

                DialogFragment newFragment = new ConfirmationDialog();
                newFragment.show(getSupportFragmentManager(),"confirm");
            } else {
                // start activity;
                goClockTick();
            }
        }
    }

    // method to create intent to start countdown activity,
    public void goClockTick() {
        Intent clockIntent = new Intent(this, ClockTick.class);
        clockIntent.putExtra(SEND_CYCLE,cycleNo);
        clockIntent.putExtra(SEND_WORK, workNo);
        clockIntent.putExtra(SEND_BREAK, breakNo);

        Runnable run = new Runnable() {

            @Override
            public void run() {
                Intent serNotificationIntent = new Intent(getApplicationContext(), SerNotification.class);
                serNotificationIntent.putExtra(SEND_CYCLE, cycleNo);
                serNotificationIntent.putExtra(SEND_WORK, workNo);
                serNotificationIntent.putExtra(SEND_BREAK, breakNo);
                startService(serNotificationIntent);
            }
        };
        Thread mythread = new Thread(run);
        mythread.start();


        startActivity(clockIntent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(DialogFragment dialog) {

        if ( dialog instanceof MinuteDialog) {
            MinuteDialog cycleD = (MinuteDialog) dialog;
            cycleSet.setText(cycleD.result);
            cycleNo = Long.parseLong(cycleD.result,10);
            cycleNo = cycleNo*2;

            if (cycleNo == 2){
                breakNo = 0;
                breakT.setVisibility(View.INVISIBLE);
                breakSet.setVisibility(View.INVISIBLE);
            } else {
                breakT.setVisibility(View.VISIBLE);
                breakSet.setVisibility(View.VISIBLE);
            }
        }

    }

    public void onWorkClick(DialogFragment dialog) {

        if ( dialog instanceof WorkBreakDialog) {
            WorkBreakDialog workD = (WorkBreakDialog) dialog;
            workSet.setText(workD.newView);
            workNo = Long.parseLong(workD.newResult,10);
            Toast.makeText(this, "workno is " + workNo,Toast.LENGTH_LONG).show();
        }
    }

    public void onBreakClick (DialogFragment dialog) {
        if ( dialog instanceof BreakDialog ) {
            BreakDialog breakD = (BreakDialog) dialog;
            breakSet.setText(breakD.newView);
            breakNo = Long.parseLong(breakD.newResult,10);
        }
    }

    public void onNoticeClick(DialogFragment dialog){
        goClockTick();
    }

    public void clickCycle ( View view) {
        DialogFragment newFragment = new MinuteDialog();
        newFragment.show(getSupportFragmentManager(),"cycle");
    }

    public void clickWork ( View view) {
        DialogFragment newFragment = new WorkBreakDialog();
        newFragment.show(getSupportFragmentManager(),"work");
    }

    public void clickBreak ( View view) {
        DialogFragment newFragment = new BreakDialog();
        newFragment.show(getSupportFragmentManager(),"break");
    }
}



