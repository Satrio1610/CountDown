package com.titaniumstick.countdown;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class SerNotification extends IntentService {

    public SerNotification() {
        super("SerNotification");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Long time = intent.getLongExtra(MainActivity.SEND_BREAK,1000);

        Intent notificationStart = new Intent ( this,ClockTick.class);

        PendingIntent pendingStart = PendingIntent.getActivity(this,0,notificationStart,PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder serBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Finished!")
                .setContentText("Wifi and Data is returned to the original state")
                .setAutoCancel(true);

        final int serNotificationID = 001;
        final NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        serBuilder.setContentIntent(pendingStart);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mNotifyMgr.notify(serNotificationID,serBuilder.build());
            }
        },time);

    }


}
