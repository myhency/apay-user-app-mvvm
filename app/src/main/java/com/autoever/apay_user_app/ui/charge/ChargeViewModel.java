package com.autoever.apay_user_app.ui.charge;

import android.util.Log;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class ChargeViewModel extends BaseViewModel<ChargeNavigator> {

    public ChargeViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doChargeReady(int amount) {
        Log.d("debug", "doChargeReady started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doChargeReadyCall(new ChargeReadyRequest(
                "4",
                "1",
                amount
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(chargeReadyResponse -> {
            setIsLoading(false);
            Log.d("debug", "chargeReadyResponse: " + chargeReadyResponse.getData().getApiVer());
            getNavigator().doChargeDo(
            );
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }

    public void doChargeDo() {

    }
}
