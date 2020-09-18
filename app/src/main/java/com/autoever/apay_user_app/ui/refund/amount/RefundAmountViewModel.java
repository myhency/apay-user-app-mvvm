package com.autoever.apay_user_app.ui.refund.amount;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.ui.common.Bank;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class RefundAmountViewModel extends BaseViewModel<RefundAmountNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;
    private final MutableLiveData<String> accountInfoLiveData;

    public RefundAmountViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);

        balanceKRWLiveData = new MutableLiveData<>();
        accountInfoLiveData = new MutableLiveData<>();

        loadUserBalance();
        getBankAccount();
    }

    private void loadUserBalance() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .doGetBalanceCall(1, getDataManager().getCurrentUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(balanceResponse -> {
                    if (balanceResponse != null && balanceResponse.getData() != null) {
                        balanceKRWLiveData.setValue(CommonUtils.formatToKRW(balanceResponse.getData().getBalance().toString()) + " P");
                        Log.d("debug", "getBalance value: " + balanceResponse.getData().getBalance().toString());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void getBankAccount() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetAccountListCall(getDataManager().getCurrentUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(bankAccountListResponse -> {
                    setIsLoading(false);
                    String bankName = Bank.find(bankAccountListResponse
                            .getData()
                            .get(0)
                            .getBankCode())
                            .getBankName();
                    String accountNumber = bankAccountListResponse.getData()
                            .get(0)
                            .getAccountNumber();
                    accountNumber = accountNumber.substring(accountNumber.length() - 4);
                    Log.d("debug", bankName + " " + accountNumber);
                    accountInfoLiveData.setValue(bankName + " " + accountNumber);
                    getNavigator().setBankCode(bankAccountListResponse
                            .getData()
                            .get(0)
                            .getBankCode());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<String> getBalanceKRWLiveData() {
        return balanceKRWLiveData;
    }
}