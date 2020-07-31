package com.autoever.apay_user_app.data;

import android.content.Context;

import com.autoever.apay_user_app.data.local.db.DbHelper;
import com.autoever.apay_user_app.data.local.prefs.PreferencesHelper;
import com.autoever.apay_user_app.data.model.api.AuthTestResponse;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.api.CardUseDetailResponse;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_user_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_user_app.data.model.api.LoginRequest;
import com.autoever.apay_user_app.data.model.api.LoginResponse;
import com.autoever.apay_user_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyResponse;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_user_app.data.model.api.UserRegisterRequest;
import com.autoever.apay_user_app.data.model.api.UserRegisterResponse;
import com.autoever.apay_user_app.data.model.db.User;
import com.autoever.apay_user_app.data.remote.ApiHelper;
import com.autoever.apay_user_app.data.remote.RepoService;
import com.autoever.apay_user_app.data.remote.RepoServiceInterceptor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Header;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";
    private final ApiHelper mApiHelper;
    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final RepoService mRepoService;

    @Inject
    public AppDataManager(ApiHelper mApiHelper, Context mContext, DbHelper mDbHelper,
                          PreferencesHelper mPreferencesHelper, RepoService mRepoService) {
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
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT
        );
    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);

//        updateRepoServiceInterceptor(accessToken);
    }

//    @Override
//    public void updateRepoServiceInterceptor(String accessToken) {
//        mRepoService.getRepoServiceInterceptor().setSessionToken(accessToken);
//    }

    @Override
    public Observable<Boolean> insertUser(User user) {
        return null;
    }

    @Override
    public Single<BalanceResponse> getUserBalance(BalanceRequest balanceRequest) {
        return mApiHelper.getUserBalance(balanceRequest);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
//        mRepoService.getRepoServiceInterceptor().setSessionToken(accessToken);
    }

//    @Override
//    public RepoServiceInterceptor getRepoServiceInterceptor() {
//        return mRepoService.getRepoServiceInterceptor();
//    }

    @Override
    public Single<PaymentReadyResponse> doPaymentReadyCall(PaymentReadyRequest paymentReadyRequest) {
        return mRepoService.doPaymentReadyCall(paymentReadyRequest);
    }

    @Override
    public Single<PaymentDoResponse> doPaymentDoCall(PaymentDoRequest paymentDoRequest) {
        return mRepoService.doPaymentDoCall(paymentDoRequest);
    }

    @Override
    public Single<ChargeReadyResponse> doChargeReadyCall(ChargeReadyRequest chargeReadyRequest) {
        return mRepoService.doChargeReadyCall(chargeReadyRequest);
    }

    @Override
    public Single<UserRegisterResponse> doUserRegisterCall(UserRegisterRequest userRegisterRequest) {
        return mRepoService.doUserRegisterCall(userRegisterRequest);
    }

    @Override
    public Single<LoginResponse> doLoginCall(LoginRequest loginRequest) {
        return mRepoService.doLoginCall(loginRequest);
    }

    @Override
    public Single<AuthTestResponse> doAuthTextCall() {
        return mRepoService.doAuthTextCall();
    }

    @Override
    public Single<CardUseHistoryResponse> doHistoryTestCall() {
        return mRepoService.doHistoryTestCall();
    }

    @Override
    public Single<CardUseHistoryResponse> doCardUseHistoryCall(int tokenSystemId, int subscriberId, int pageNo, int pageSize, String date, String filter) {
        return mRepoService.doCardUseHistoryCall(tokenSystemId, subscriberId, pageNo, pageSize, date, filter);
    }

    @Override
    public Single<CardUseDetailResponse> doCardUseDetailCall(int paymentHistoryId, String target) {
        return mRepoService.doCardUseDetailCall(paymentHistoryId, target);
    }

    @Override
    public Single<PaymentRefundReadyResponse> doPaymentRefundReadyCall(PaymentRefundReadyRequest paymentRefundReadyRequest) {
        return mRepoService.doPaymentRefundReadyCall(paymentRefundReadyRequest);
    }
}
