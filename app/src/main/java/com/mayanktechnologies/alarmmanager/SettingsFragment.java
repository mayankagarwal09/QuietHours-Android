package com.mayanktechnologies.alarmmanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.seetings_pref);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference p = findPreference(s);
        if (p != null) {
            if (p.getKey().equals("service_status")) {
                SettingsActivity activity = (SettingsActivity) getActivity();
                Boolean value = sharedPreferences.getBoolean(p.getKey(), Constants.SERVICE_STATE);
                if (value == true) {

                    Constants.setAlarm(getActivity().getApplicationContext());
                    //activity.setAlarm();
                    Constants.SERVICE_STATE = true;

                } else {
                    Constants.removeAlarm(getActivity().getApplicationContext());
                    //activity.removeAlarm();
                    Constants.SERVICE_STATE = false;
                }
                Log.v("value", String.valueOf(value));
            } else if (p.getKey().equals("continuous_status")) {
                Boolean value = sharedPreferences.getBoolean(p.getKey(), Constants.CONTINOUS_CLASS_STATE);
                if (value == true) {

                    Constants.CONTINOUS_CLASS_STATE = true;
                    if (Constants.SERVICE_STATE == true) {
                        Constants.removeAlarm(getActivity().getApplicationContext());
                        Constants.setAlarm(getActivity().getApplicationContext());
                    }

                } else {
                    Constants.CONTINOUS_CLASS_STATE = false;
                    if (Constants.SERVICE_STATE == true) {
                        Constants.removeAlarm(getActivity().getApplicationContext());
                        Constants.setAlarm(getActivity().getApplicationContext());
                    }
                }
            } else if (p.getKey().equals("vibrate_status")) {
                Boolean value = sharedPreferences.getBoolean(p.getKey(), Constants.VIBRATE_STATE);
                if (value == true) {

                    Constants.VIBRATE_STATE = true;

                } else {
                    Constants.VIBRATE_STATE = false;

                }
            }
        }
    }

}


