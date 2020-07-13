package com.autoever.apay_user_app.ui.payment.scanner;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.ui.payment.PaymentNavigator;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class CustomScannerViewModel extends BaseViewModel<CustomScannerNavigator> {

    private final MutableLiveData<BalanceResponse.Balance> balanceLiveData;

    public CustomScannerViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        balanceLiveData = new MutableLiveData<>();
        loadUserBalance();
    }

    private void loadUserBalance() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .getUserBalance("1", "4")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(balanceResponse -> {
                    if(balanceResponse != null && balanceResponse.getData() != null) {
                        balanceLiveData.setValue(balanceResponse.getData());
                        Log.d("debug", "value: " + balanceResponse.getData().getBalance().toString());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<BalanceResponse.Balance> getBalanceLiveData() {
        return balanceLiveData;
    }
}
