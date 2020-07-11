package com.autoever.apay_user_app.data;

import android.content.Context;

import com.autoever.apay_user_app.data.local.db.DbHelper;
import com.autoever.apay_user_app.data.local.prefs.PreferencesHelper;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.db.User;
import com.autoever.apay_user_app.data.remote.ApiHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";
    private final ApiHelper mApiHelper;
    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(ApiHelper mApiHelper, Context mContext, DbHelper mDbHelper, PreferencesHelper mPreferencesHelper) {
        this.mApiHelper = mApiHelper;
        this.mContext = mContext;
        this.mDbHelper = mDbHelper;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {
        return null;
    }

    @Override
    public Observable<Boolean> getAppAccessPermissions() {
        return null;
    }

    @Override
    public Observable<Boolean> insertUser(User user) {
        return null;
    }

    @Override
    public Single<BalanceResponse> getUserBalance() {
        return mApiHelper.getUserBalance();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }


//    @Override
//    public Observable<String> getUserBalance() {
//        return null;
//    }
}
