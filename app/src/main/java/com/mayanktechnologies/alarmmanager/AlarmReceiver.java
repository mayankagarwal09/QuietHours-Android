package com.mayanktechnologies.alarmmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Mayank on 28-08-2017.
 */

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        String action=intent.getAction();
        if(action==Constants.ACTION_RING) {
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            am.setStreamVolume(AudioManager.STREAM_MUSIC,Constants.CURRENT_MEDIA_VOLUME, AudioManager.ADJUST_MUTE);
            Constants.PHONE_RING_STATE=true;
            Log.v("alarmNoti","ring");
        }

        else if(action==Constants.ACTION_SILENT)
        {
            if(Constants.VIBRATE_STATE==true) {
                am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Log.v("alarmNoti","vibrate");
            }else {
                am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Log.v("alarmNoti","silent");
            }
            Constants.CURRENT_MEDIA_VOLUME=am.getStreamVolume(AudioManager.STREAM_MUSIC);
            am.setStreamVolume(AudioManager.STREAM_MUSIC,0,AudioManager.ADJUST_MUTE);
            Constants.PHONE_RING_STATE=false;

        }

    }


}
