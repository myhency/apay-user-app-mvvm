package com.autoever.apay_user_app.data;


import com.autoever.apay_user_app.data.local.db.DbHelper;
import com.autoever.apay_user_app.data.local.prefs.PreferencesHelper;
import com.autoever.apay_user_app.data.remote.ApiHelper;
import com.autoever.apay_user_app.data.remote.RepoService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface DataManager extends DbHelper, ApiHelper, PreferencesHelper, RepoService {
    Observable<Boolean> seedDatabaseQuestions();

    Observable<Boolean> getAppAccessPermissions();

    void updateUserInfo(
            String accessToken,
            Integer userId,
            LoggedInMode loggedInMode
    );

//    void updateRepoServiceInterceptor(String accessToken);

    enum LoggedInMode {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
