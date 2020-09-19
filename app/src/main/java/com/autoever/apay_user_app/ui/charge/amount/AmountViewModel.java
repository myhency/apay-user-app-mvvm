package com.autoever.apay_user_app.ui.charge.amount;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.ui.common.Bank;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class AmountViewModel extends BaseViewModel<AmountNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;
    private final MutableLiveData<String> accountInfoLiveData;

    public AmountViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        balanceKRWLiveData = new MutableLiveData<>();
        accountInfoLiveData = new MutableLiveData<>();

        loadUserBalance();
        getBankAccount();
    }

    public void loadUserBalance() {
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
            if(bankAccountListResponse.getData().size() > 0) {
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
            } else {
                getNavigator().handleNoAccount();
            }
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }

    public LiveData<String> getBalanceKRWLiveData() {
        return balanceKRWLiveData;
    }

    public LiveData<String> getAccountInfoLiveData() {
        return accountInfoLiveData;
    }
}
