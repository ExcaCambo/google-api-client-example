package com.example.googleapiclientexample;

import android.content.Context;
import android.preference.PreferenceManager;

public class PrefUtils {
    static final String USE_GAMES_API = "use_games_api";

    public static boolean useGamesApi(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(USE_GAMES_API, false);
    }

    public static void setUseGamesApi(Context context, boolean useGamesApi) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(USE_GAMES_API, useGamesApi).apply();
    }
}
