package com.terhands.fitracker.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public final class RepositoryPrefs {

    private static final String REPO_PREFS = "REPO_PREFS";
    private static final String PREF_IS_DB_INITIALIZED = "IS_DB_INITIALIZED";

    public static boolean isInitializationComplete(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(REPO_PREFS, Context.MODE_PRIVATE);
        return prefs.getBoolean(PREF_IS_DB_INITIALIZED, false);
    }

    public static void setInitializationComplete(Context context, boolean isComplete) {
        SharedPreferences prefs = context.getSharedPreferences(REPO_PREFS, Context.MODE_PRIVATE);
        prefs.edit()
             .putBoolean(PREF_IS_DB_INITIALIZED, isComplete)
             .apply();
    }
}
