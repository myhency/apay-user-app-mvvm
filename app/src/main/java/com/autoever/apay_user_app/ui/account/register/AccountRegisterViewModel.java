package com.autoever.apay_user_app.ui.account.register;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.ArsCheckRequest;
import com.autoever.apay_user_app.data.model.api.ArsRequestRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class AccountRegisterViewModel extends BaseViewModel<AccountRegisterNavigator> {

    public AccountRegisterViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doIdentityCheckCall() {
        getNavigator().openBankSelectFragment();
    }

    public void doArsCheckCall(String settleBankUniqueId,
                               String phoneNumber,
                               String subscriberName,
                               String withdrawBankCode,
                               String withdrawAccountNumber,
                               String authenticationCode,
                               Long subscriberId) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doArsCheckCall(new ArsCheckRequest(
                        settleBankUniqueId,
                        phoneNumber,
                        subscriberName,
                        withdrawBankCode,
                        withdrawAccountNumber,
                        authenticationCode,
                        subscriberId))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(arsCheckResponse -> {
                    setIsLoading(false);
                    getNavigator().openArsAuthFragment(arsCheckResponse);
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void doArsRequestCall(String phoneNumber,
                                 String subscriberName,
                                 String withdrawBankCode,
                                 String withdrawAccountNumber,
                                 String identificationNumber,
                                 String authenticationCode,
                                 Long subscriberId) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doArsRequestCall(new ArsRequestRequest(
                        phoneNumber,
                        subscriberName,
                        withdrawBankCode,
                        withdrawAccountNumber,
                        identificationNumber,
                        authenticationCode,
                        subscriberId
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(arsRequestResponse -> {
                    setIsLoading(false);
                    getNavigator().openDialog();
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
