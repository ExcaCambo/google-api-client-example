package com.example.googleapiclientexample;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        initPreferences();
    }

    public void initPreferences() {
        final CheckBoxPreference prefGoogleSignIn = (CheckBoxPreference) findPreference(PrefUtils.USE_GAMES_API);
        if (prefGoogleSignIn != null) {
            prefGoogleSignIn.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    GoogleApiClient googleApiClient = ((BaseActivity) getActivity()).getGoogleApiClient();
                    boolean signedIn = (Boolean) o;
                    if (!signedIn) {
                        if (googleApiClient.isConnected()) {
                            Games.signOut(googleApiClient);
                            googleApiClient.disconnect();
                            PrefUtils.setUseGamesApi(getActivity(), false);
                            ((BaseActivity) getActivity()).buildGoogleApiClient();
                            googleApiClient = ((BaseActivity) getActivity()).getGoogleApiClient();
                            googleApiClient.connect();
                        }
                    } else {
                        if (googleApiClient.isConnected()) {
                            googleApiClient.disconnect();
                            PrefUtils.setUseGamesApi(getActivity(), true);
                            ((BaseActivity)getActivity()).buildGoogleApiClient();
                            googleApiClient = ((BaseActivity) getActivity()).getGoogleApiClient();
                            googleApiClient.connect();
                        }
                    }
                    return true;
                }
            });
        }
    }
}
