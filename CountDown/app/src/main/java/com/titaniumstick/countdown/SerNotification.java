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
    private Long nCycle;
    private Long wHour;
    private Long bHour;
    private long tHour = -1;
    private String workText = "Sharpen your focus! Flex your muscle! It's time to give your best(again)!";
    private String workTitle = " Back to Work!";
    private String breakText ="Relax your mind! Do some stretching! Keep in mind there are still some works left to do!";
    private String breakTitle="Breaktime!";
    private String cycleTitle ="TIMER FINISHED!!";
    private String cycleText = "Pat yourself in the back for a job well done!";
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
                .setSmallIcon(R.mipmap.launcher2)
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
                        if (ClockTick.checkPause()) {
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
                        if(ClockTick.checkPause()) {
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
                        if(ClockTick.checkPause()){
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
