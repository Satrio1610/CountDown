package com.titaniumstick.countdown;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class ClockTick extends AppCompatActivity {

    long noCycle; // number of cycle the user is going to go through
    long workH;
    long breakH;
    Intent goBack;
    Intent intent;
    TextView countDown;
    TextView phaseShift;
    static boolean isPaused = false;
    static boolean clockEnd = false;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_tick);
        // start countdown
        countDown = (TextView) findViewById(R.id.clock);
        phaseShift = (TextView) findViewById(R.id.phase);

        intent = getIntent();
        noCycle = intent.getLongExtra(MainActivity.SEND_CYCLE, 0);
        workH = intent.getLongExtra(MainActivity.SEND_WORK,0);
        breakH = intent.getLongExtra(MainActivity.SEND_BREAK,0);
        phaseShift.setText("WORK");

        CountClock workClock = new CountClock(workH,1000,"BREAK");
        CountClock breakClock = new CountClock(breakH,1000,"WORK");
        workClock.setPair(breakClock);
        breakClock.setPair(workClock);

        timer = new Timer();

        Runnable intentChange = new Runnable() {
            @Override
            public void run() {
                goBack = new Intent(getApplicationContext(), MainActivity.class);
            }
        };
        Thread intentThread = new Thread(intentChange);
        intentThread.start();

        workClock.start();




    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused= true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;

        if (clockEnd){
            startActivity(goBack);
        }

    }


    public class CountClock extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        String nextPhase;
        CountClock clock2;

        public CountClock(long millisInFuture, long countDownInterval, String p) {
            super(millisInFuture, countDownInterval);
            nextPhase = p;

        }

        public void setPair(CountClock c2) {
            clock2 = c2;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Long hour = millisUntilFinished/3600/1000;
            Long minute = millisUntilFinished/60/1000%60;
            Long second = millisUntilFinished/1000%60;
            countDown.setText(timeDisplay(hour, minute, second));

        }

        @Override
        public void onFinish() {
            synchronized(getApplicationContext()) {
                noCycle--;
                countDown.setText("NEXT SEGMENT");
            }
                if (noCycle == 1) {
                    phaseShift.setText("NONE");
                    if (isPaused) {
                        clockEnd = true;
                    } else {
                        startActivity(goBack);
                    }
                } else {
                    phaseShift.setText(nextPhase);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            clock2.start();
                        }
                    },2000);


                }

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clock_tick, menu);
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

    public String timeDisplay(long hour, long minute, long second) {
        String hourS;
        String minS;
        String secS;
        // hour
        if ( hour < 10 ) {
            hourS = "0" + hour;
        } else {
            hourS = "" + hour;
        }
        // minute
        if ( minute < 10 ) {
            minS = "0" + minute;
        } else {
            minS = "" + minute;
        }
        // second
        if ( second < 10 ) {
            secS = "0" + second;
        } else {
            secS = "" + second;
        }
        String result = hourS + ":" + minS + ":" + secS;
        return result;
    }






}
