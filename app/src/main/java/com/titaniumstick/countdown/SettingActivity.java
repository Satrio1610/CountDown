
package com.titaniumstick.countdown;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.os.Bundle;

public class SettingActivity extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final static String SET_NAME = "name_setting";
    private SharedPreferences sPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // load the preferences from .xml resources
        addPreferencesFromResource(R.xml.preferences);
        setSummaryToCurValue(findPreference(SET_NAME),SET_NAME);
        sPrefs = getPreferenceScreen().getSharedPreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        sPrefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(SET_NAME)) {
            Preference namePref = findPreference(key);
            // Set summary to be the user-description for the selected value
            namePref.setSummary(sharedPreferences.getString(key, ""));
        }

    }

    public void setSummaryToCurValue(Preference prefs, String key){
        prefs.setSummary(getPreferenceScreen().getSharedPreferences().getString(key,""));
    }
}
