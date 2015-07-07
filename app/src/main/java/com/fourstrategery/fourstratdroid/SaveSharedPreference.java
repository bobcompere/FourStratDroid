package com.fourstrategery.fourstratdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by REC on 6/27/2015.
 */
public class SaveSharedPreference {

    static final String PREF_USER = "username";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUser(Context ctx, String user) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER, user);
        editor.commit();
    }

    public static String getUser(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER,"");
    }
}
