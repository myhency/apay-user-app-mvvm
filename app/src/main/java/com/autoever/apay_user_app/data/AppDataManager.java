package com.autoever.apay_user_app.data;

import android.content.Context;

import com.autoever.apay_user_app.data.local.db.DbHelper;
import com.autoever.apay_user_app.data.local.prefs.PreferencesHelper;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyResponse;
import com.autoever.apay_user_app.data.model.db.User;
import com.autoever.apay_user_app.data.remote.ApiHelper;
import com.autoever.apay_user_app.data.remote.RepoService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";
    private final ApiHelper mApiHelper;
    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final RepoService mRepoService;

    @Inject
    public AppDataManager(ApiHelper mApiHelper, Context mContext, DbHelper mDbHelper, PreferencesHelper mPreferencesHelper, RepoService mRepoService) {
        this.mApiHelper = mApiHelper;
        this.mContext = mContext;
        this.mDbHelper = mDbHelper;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mRepoService = mRepoService;
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
    public Single<BalanceResponse> getUserBalance(BalanceRequest balanceRequest) {
        return mApiHelper.getUserBalance(balanceRequest);
    }

//    @Override
//    public Single<PaymentReadyResponse> doPaymentReady(PaymentReadyRequest paymentReadyRequest) {
//        return mApiHelper.doPaymentReady(paymentReadyRequest);
//    }

//    @Override
//    public Call<PaymentReadyResponse> doPaymentReadyCall(PaymentReadyRequest paymentReadyRequest) {
//        return mApiHelper.doPaymentReadyCall(paymentReadyRequest);
//    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public Single<PaymentReadyResponse> doPaymentReadyCall(PaymentReadyRequest paymentReadyRequest) {
        return mRepoService.doPaymentReadyCall(paymentReadyRequest);
    }

    @Override
    public Single<PaymentDoResponse> doPaymentDoCall(PaymentDoRequest paymentDoRequest) {
        return mRepoService.doPaymentDoCall(paymentDoRequest);
    }
}
