package com.mayanktechnologies.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Log.v("bootcomplete","called");
            SharedPreferences preferences=context.getSharedPreferences(Constants.SHARED_PREF,Context.MODE_PRIVATE);
            boolean state=preferences.getBoolean(Constants.SERVICE_STATE_ID,false);
            Log.v("bootcomplete",String.valueOf(state));
            if (state==true) {
                Log.v("bootcomplete","alarmset");
                Constants.SharedPreferenceLoad(context);
                Constants.requestCodesMap.clear();
                Constants.setAlarm(context);
                Constants.saveSharedPreference(context);
            }
        }
    }
}
