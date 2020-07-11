package com.autoever.apay_user_app.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.di.PreferenceInfo;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }
}
