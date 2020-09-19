package com.autoever.apay_user_app.ui.refund.receipt;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.ui.common.Bank;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class RefundReceiptViewModel extends BaseViewModel<RefundReceiptNavigator> {

    private final MutableLiveData<String> accountInfoLiveData;

    public RefundReceiptViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);

        accountInfoLiveData = new MutableLiveData<>();

        getBankAccount();
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

    public LiveData<String> getAccountInfoLiveData() {
        return accountInfoLiveData;
    }
}
