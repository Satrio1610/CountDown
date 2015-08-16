package com.titaniumstick.countdown;

import android.preference.PreferenceFragment;
import android.os.Bundle;

public class SettingActivity extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // load the preferences from .xml resources
        addPreferencesFromResource(R.xml.preferences);
    }


}
