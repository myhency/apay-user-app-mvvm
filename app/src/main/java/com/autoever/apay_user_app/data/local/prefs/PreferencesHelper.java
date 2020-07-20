package com.autoever.apay_user_app.data.local.prefs;

import com.autoever.apay_user_app.data.DataManager;

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserId();

    void setCurrentUserId(String userId);

    String getAccessToken();

    void setAccessToken(String accessToken);
}
