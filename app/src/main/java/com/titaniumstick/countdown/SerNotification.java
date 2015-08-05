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
    Long nCycle;
    Long wHour;
    Long bHour;
    long tHour = -1;
    String workText = "Sharpen your focus! Flex your muscle! It's time to give your best(again)!";
    String workTitle = " Back to Work!";
    String breakText ="Relax your mind! Do some stretching! Keep in mind there are still some works left to do!";
    String breakTitle="Breaktime!";
    String cycleTitle ="TIMER FINISHED!!";
    String cycleText = "Pat yourself in the back for a job well done!";
    PendingIntent pendingTimerStart;


    @Override
    protected void onHandleIntent(Intent intent) {
        bHour = intent.getLongExtra(MainActivity.SEND_BREAK,1000);
        wHour = intent.getLongExtra(MainActivity.SEND_WORK, 1000);
        nCycle = intent.getLongExtra(MainActivity.SEND_CYCLE, 1000);

        Intent timerNotification = new Intent ( this,ClockTick.class);

        pendingTimerStart = PendingIntent.getActivity(this, 0, timerNotification,PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder serPhaseNotif = new NotificationCompat.Builder(this)
                .setContentIntent(pendingTimerStart)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        final int serPhaseNotificationID = 001;
        final NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Timer timer = new Timer();
        for(long iter = 1; iter < nCycle; iter ++) {

            if ( iter== nCycle -1 ) {
                tHour = tHour + wHour;


                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (ClockTick.isPaused) {
                            serPhaseNotif.setContentTitle(cycleTitle)
                                    .setContentText(cycleText);

                            mNotifyMgr.cancel(serPhaseNotificationID);
                            mNotifyMgr.notify(serPhaseNotificationID, serPhaseNotif.build());
                        }
                    }
                }, tHour);

            } else if (iter%2 != 0) {
                tHour = tHour + wHour;

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(ClockTick.isPaused) {
                            serPhaseNotif.setContentTitle(breakTitle)
                                    .setContentText(breakText);
                            mNotifyMgr.cancel(serPhaseNotificationID);
                            mNotifyMgr.notify(serPhaseNotificationID, serPhaseNotif.build());
                        }
                    }
                }, tHour);

            } else {

                tHour = tHour + bHour;

                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        if(ClockTick.isPaused){
                            serPhaseNotif.setContentTitle(workTitle)
                                    .setContentText(workText);

                            mNotifyMgr.cancel(serPhaseNotificationID);
                            mNotifyMgr.notify(serPhaseNotificationID, serPhaseNotif.build());
                        }

                    }
                },tHour);

            }
        }

    }


}
