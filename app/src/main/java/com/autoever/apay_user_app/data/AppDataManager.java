package com.autoever.apay_user_app.data;

import android.content.Context;

import com.autoever.apay_user_app.data.local.db.DbHelper;
import com.autoever.apay_user_app.data.local.prefs.PreferencesHelper;
import com.autoever.apay_user_app.data.model.api.AccountRegisterRequest;
import com.autoever.apay_user_app.data.model.api.AccountRegisterResponse;
import com.autoever.apay_user_app.data.model.api.AccountTerminationRequest;
import com.autoever.apay_user_app.data.model.api.AccountTerminationResponse;
import com.autoever.apay_user_app.data.model.api.ArsCheckRequest;
import com.autoever.apay_user_app.data.model.api.ArsCheckResponse;
import com.autoever.apay_user_app.data.model.api.ArsRequestRequest;
import com.autoever.apay_user_app.data.model.api.ArsRequestResponse;
import com.autoever.apay_user_app.data.model.api.AuthTestResponse;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.api.BankAccountListRequest;
import com.autoever.apay_user_app.data.model.api.BankAccountListResponse;
import com.autoever.apay_user_app.data.model.api.CardUseDetailResponse;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.data.model.api.ChargeDoRequest;
import com.autoever.apay_user_app.data.model.api.ChargeDoResponse;
import com.autoever.apay_user_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_user_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_user_app.data.model.api.FindLoginIdRequest;
import com.autoever.apay_user_app.data.model.api.FindLoginIdResponse;
import com.autoever.apay_user_app.data.model.api.IdentityCheckRequest;
import com.autoever.apay_user_app.data.model.api.IdentityCheckResponse;
import com.autoever.apay_user_app.data.model.api.LoginIdDuplicationCheckResponse;
import com.autoever.apay_user_app.data.model.api.LoginRequest;
import com.autoever.apay_user_app.data.model.api.LoginResponse;
import com.autoever.apay_user_app.data.model.api.ModifyPasswordRequest;
import com.autoever.apay_user_app.data.model.api.ModifyPasswordResponse;
import com.autoever.apay_user_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_user_app.data.model.api.PaymentQrDynamicReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentQrReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyResponse;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyCancelResponse;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_user_app.data.model.api.QrUserDynamicRequest;
import com.autoever.apay_user_app.data.model.api.QrUserDynamicResponse;
import com.autoever.apay_user_app.data.model.api.RefundDoRequest;
import com.autoever.apay_user_app.data.model.api.RefundDoResponse;
import com.autoever.apay_user_app.data.model.api.RefundReadyRequest;
import com.autoever.apay_user_app.data.model.api.RefundReadyResponse;
import com.autoever.apay_user_app.data.model.api.ResetPasswordRequest;
import com.autoever.apay_user_app.data.model.api.ResetPasswordResponse;
import com.autoever.apay_user_app.data.model.api.StoreHashRequest;
import com.autoever.apay_user_app.data.model.api.StoreHashResponse;
import com.autoever.apay_user_app.data.model.api.UserRegisterRequest;
import com.autoever.apay_user_app.data.model.api.UserRegisterResponse;
import com.autoever.apay_user_app.data.model.db.User;
import com.autoever.apay_user_app.data.remote.ApiHelper;
import com.autoever.apay_user_app.data.remote.RepoService;

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

    @Override
    public void setEasyPassword(String easyPassword) {
        mPreferencesHelper.setEasyPassword(easyPassword);
    }

    @Override
    public String getEasyPassword() {
        return mPreferencesHelper.getEasyPassword();
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
    public Single<ChargeDoResponse> doChargeDoCall(ChargeDoRequest chargeDoRequest) {
        return mRepoService.doChargeDoCall(chargeDoRequest);
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

    @Override
    public Single<PaymentRefundReadyCancelResponse> doPaymentRefundReadyCancelCall(PaymentRefundReadyRequest paymentRefundReadyRequest) {
        return mRepoService.doPaymentRefundReadyCancelCall(paymentRefundReadyRequest);
    }

    @Override
    public Single<PaymentReadyResponse> doPaymentQrReadyCall(PaymentQrReadyRequest paymentQrReadyRequest) {
        return mRepoService.doPaymentQrReadyCall(paymentQrReadyRequest);
    }

    @Override
    public Single<PaymentReadyResponse> doPaymentQrDynamicReadyCall(PaymentQrDynamicReadyRequest paymentQrDynamicReadyRequest) {
        return mRepoService.doPaymentQrDynamicReadyCall(paymentQrDynamicReadyRequest);
    }

    @Override
    public Single<QrUserDynamicResponse> doQrUserDynamicCall(QrUserDynamicRequest qrUserDynamicRequest) {
        return mRepoService.doQrUserDynamicCall(qrUserDynamicRequest);
    }

    @Override
    public Single<BalanceResponse> doGetBalanceCall(int tokenSystemId, Long subscriberId) {
        return mRepoService.doGetBalanceCall(tokenSystemId, subscriberId);
    }

    @Override
    public Single<BankAccountListResponse> doGetAccountListCall(Long userId) {
        return mRepoService.doGetAccountListCall(userId);
    }

    @Override
    public Single<IdentityCheckResponse> doIdentityCheckCall(IdentityCheckRequest identityCheckRequest) {
        return mRepoService.doIdentityCheckCall(identityCheckRequest);
    }

    @Override
    public Single<ArsCheckResponse> doArsCheckCall(ArsCheckRequest arsCheckRequest) {
        return mRepoService.doArsCheckCall(arsCheckRequest);
    }

    @Override
    public Single<ArsRequestResponse> doArsRequestCall(ArsRequestRequest arsRequestRequest) {
        return mRepoService.doArsRequestCall(arsRequestRequest);
    }

    @Override
    public Single<AccountRegisterResponse> doAccountRegisterCall(AccountRegisterRequest accountRegisterRequest) {
        return mRepoService.doAccountRegisterCall(accountRegisterRequest);
    }

    @Override
    public Single<RefundReadyResponse> doRefundReadyCall(RefundReadyRequest refundReadyRequest) {
        return mRepoService.doRefundReadyCall(refundReadyRequest);
    }

    @Override
    public Single<RefundDoResponse> doRefundDoCall(RefundDoRequest refundDoRequest) {
        return mRepoService.doRefundDoCall(refundDoRequest);
    }

    @Override
    public Single<LoginIdDuplicationCheckResponse> doLoginIdDuplicationCheckCall(String loginId) {
        return mRepoService.doLoginIdDuplicationCheckCall(loginId);
    }

    @Override
    public Single<FindLoginIdResponse> doFindLoginIdCall(FindLoginIdRequest findLoginIdRequest) {
        return mRepoService.doFindLoginIdCall(findLoginIdRequest);
    }

    @Override
    public Single<ResetPasswordResponse> doResetPasswordCall(ResetPasswordRequest resetPasswordRequest) {
        return mRepoService.doResetPasswordCall(resetPasswordRequest);
    }

    @Override
    public Single<AccountTerminationResponse> doAccountTerminationCall(AccountTerminationRequest accountTerminationRequest) {
        return mRepoService.doAccountTerminationCall(accountTerminationRequest);
    }

    @Override
    public Single<StoreHashResponse> doGetStoreNameCall(StoreHashRequest storeHashRequest) {
        return mRepoService.doGetStoreNameCall(storeHashRequest);
    }

    @Override
    public Single<ModifyPasswordResponse> doModifyPasswordCall(Long userId, ModifyPasswordRequest modifyPasswordRequest) {
        return mRepoService.doModifyPasswordCall(userId, modifyPasswordRequest);
    }
}
