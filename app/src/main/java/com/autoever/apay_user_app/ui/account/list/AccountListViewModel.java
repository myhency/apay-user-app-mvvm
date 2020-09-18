package com.autoever.apay_user_app.ui.account.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.AccountTerminationRequest;
import com.autoever.apay_user_app.data.model.api.BankAccountListRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.ui.common.Bank;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountListViewModel extends BaseViewModel<AccountListNavigator> {

    public AccountListViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }


    public void doListAccountCall() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doGetAccountListCall(getDataManager().getCurrentUserId())
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(bankAccountListResponse -> {
            setIsLoading(false);
            getNavigator().setAccountInfo(bankAccountListResponse);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));

    }

    public void deleteBankAccountCall(String bankCode) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doAccountTerminationCall(new AccountTerminationRequest(
                bankCode,
                getDataManager().getCurrentUserId()
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(accountTerminationResponse -> {
            setIsLoading(false);
            getNavigator().resetScreen();
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }
}
