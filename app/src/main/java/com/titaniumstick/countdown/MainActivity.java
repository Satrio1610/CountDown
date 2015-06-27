package com.titaniumstick.countdown;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public final static String SEND_TIME = "com.titaniumstick.countdownver2.TIME";
    TextView title;
    EditText hourSet;
    EditText minuteSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void focusGo(View view){
        title = (TextView) findViewById(R.id.title);
        hourSet = (EditText) findViewById(R.id.hour);
        minuteSet = (EditText) findViewById(R.id.minute);
        String hInput = hourSet.getText().toString();
        String mInput = minuteSet.getText().toString();

        if( ( hInput.equals("") && mInput.equals(""))
                || ((Integer.valueOf(hInput)== 0) && (Integer.valueOf(mInput)==0))) {
            //check if there is no time set or if time set is 00:00
            title.setText("NO TIME SET!");
            title.setTypeface(null, Typeface.BOLD);
            hourSet.setHintTextColor(Color.parseColor("#FF0000"));
            hourSet.setText("");
            minuteSet.setHintTextColor(Color.parseColor("#ff0000"));
            minuteSet.setText("");
        } else {
            Intent intent = new Intent(this,ClockTick.class);
            // processing number of hour written
            Long nHour = Long.valueOf(hInput)*3600;
            Long nMin = Long.valueOf(mInput)*60;
            Long total = (nMin + nHour)*1000;
            intent.putExtra(SEND_TIME,total);
            startActivity(intent);
        }
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
}

