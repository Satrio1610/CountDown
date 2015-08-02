package com.titaniumstick.countdown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ClockTick extends ActionBarActivity {

    int clockCounter; // counter to keep track of the number cycle of work-break the user has gone through
    int noCycle; // number of cycle the user is going to go through

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_tick);
        // start countdown
        TextView countDown = (TextView) findViewById(R.id.clock);
        Intent intent = getIntent();
        Long time = intent.getLongExtra(MainActivity.SEND_BREAK, 0);
        CountClock cod = new CountClock(time,1000,countDown);
        cod.start();





    }

    


    public class CountClock extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        private TextView text;
        public CountClock(long millisInFuture, long countDownInterval, TextView ntext) {
            super(millisInFuture, countDownInterval);
            text = ntext;

        }

        @Override
        public void onTick(long millisUntilFinished) {
            Long hour = millisUntilFinished/3600/1000;
            Long minute = millisUntilFinished/60/1000%60;
            Long second = millisUntilFinished/1000%60;

            text.setText(hour + ":" + minute + ":" + second);

        }

        @Override
        public void onFinish() {
            text.setText("DONE!");

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
}
