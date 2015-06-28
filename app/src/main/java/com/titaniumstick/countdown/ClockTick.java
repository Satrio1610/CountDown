package com.titaniumstick.countdown;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ClockTick extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_tick);
        // start countdown
        TextView countDown = (TextView) findViewById(R.id.clock);
        Intent intent = getIntent();
        Long time = intent.getLongExtra(MainActivity.SEND_TIME, 0);

        // set up wifi scanner
        WifiManager mainWifiObj;
        mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        CountClock cod = new CountClock(time,1000,countDown, mainWifiObj);
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
        private WifiManager wf;
        public CountClock(long millisInFuture, long countDownInterval, TextView ntext, WifiManager wifi) {
            super(millisInFuture, countDownInterval);
            text = ntext;
            wf = wifi;

        }

        @Override
        public void onTick(long millisUntilFinished) {
            Long hour = millisUntilFinished/3600/1000;
            Long minute = millisUntilFinished/60/1000%60;
            Long second = millisUntilFinished/1000%60;

            text.setText(hour + ":" + minute + ":" + second);

            if( wf.isWifiEnabled()) {
                wf.setWifiEnabled(false);
            }
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
