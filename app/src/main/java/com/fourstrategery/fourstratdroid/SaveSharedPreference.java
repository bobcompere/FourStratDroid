package com.fourstrategery.fourstratdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by REC on 6/27/2015.
 */
public class SaveSharedPreference {

    static final String PREF_USER = "username";
    static final String AUTH_TOKEN = "authToken";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUser(Context ctx, int user) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER, user);
        editor.commit();
    }

    public static int getUser(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER,-1);
    }

    public static String getAutheticationToken(Context ctx) {
        return getSharedPreferences(ctx).getString(AUTH_TOKEN, "");
    }

    public static void setAutheticationToken(Context ctx, String authToken) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(AUTH_TOKEN, authToken);
        editor.commit();
    }
}
