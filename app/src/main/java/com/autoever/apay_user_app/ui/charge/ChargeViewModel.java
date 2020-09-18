package com.autoever.apay_user_app.ui.charge;

import android.util.Log;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.ChargeDoRequest;
import com.autoever.apay_user_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_user_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class ChargeViewModel extends BaseViewModel<ChargeNavigator> {

    public ChargeViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doChargeReady(Long amount, String bankCode, String bankInfo) {
        Log.d("debug", "doChargeReady started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doChargeReadyCall(new ChargeReadyRequest(
                getDataManager().getCurrentUserId(),
                1L,
                amount
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(chargeReadyResponse -> {
            setIsLoading(false);
            Log.d("debug", "chargeReadyResponse: " + chargeReadyResponse.getData().getChargeStatus());
            doChargeDo(chargeReadyResponse, bankCode, bankInfo);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }

    public void doChargeDo(ChargeReadyResponse chargeReadyResponse, String bankCode, String bankInfo) {
        Log.d("debug", "doChargeDo started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doChargeDoCall(new ChargeDoRequest(
                chargeReadyResponse.getData().getChargeId(),
                bankCode,
                chargeReadyResponse.getData().getSubscriberId(),
                chargeReadyResponse.getData().getTokenSystemId(),
                chargeReadyResponse.getData().getAmount()
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(chargeDoResponse -> {
            setIsLoading(false);
            getNavigator().openChargeReceiptFragment(chargeDoResponse, bankInfo);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }
}
