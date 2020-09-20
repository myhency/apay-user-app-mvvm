package com.autoever.apay_user_app.ui.account.register;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.AccountRegisterRequest;
import com.autoever.apay_user_app.data.model.api.ArsCheckRequest;
import com.autoever.apay_user_app.data.model.api.ArsCheckResponse;
import com.autoever.apay_user_app.data.model.api.ArsRequestRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class AccountRegisterViewModel extends BaseViewModel<AccountRegisterNavigator> {

    private String phoneNumber;
    private String subscriberName;
    private String withdrawBankCode;
    private String withdrawAccountNumber;
    private String identificationNumber;
    private String authenticationCode;

    public AccountRegisterViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doIdentityCheckCall() {
        getNavigator().openBankSelectFragment();
    }

    public void doArsRequestCall(String phoneNumber,
                                 String subscriberName,
                                 String withdrawBankCode,
                                 String withdrawAccountNumber,
                                 String identificationNumber,
                                 String authenticationCode) {
        //로컬 변수 설정
        this.phoneNumber = phoneNumber;
        this.subscriberName = subscriberName;
        this.withdrawBankCode = withdrawBankCode;
        this.withdrawAccountNumber = withdrawAccountNumber;
        this.identificationNumber = identificationNumber;
        this.authenticationCode = authenticationCode;

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doArsRequestCall(new ArsRequestRequest(
                        phoneNumber,
                        subscriberName,
                        withdrawBankCode,
                        withdrawAccountNumber,
                        identificationNumber,
                        authenticationCode,
                        getDataManager().getCurrentUserId()
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(arsRequestResponse -> {
                    setIsLoading(false);
                    getNavigator().openArsAuthFragment(arsRequestResponse);
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void doArsCheckCall(String settleBankUniqueId,
                               String phoneNumber,
                               String subscriberName,
                               String withdrawBankCode,
                               String withdrawAccountNumber,
                               String authenticationCode) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doArsCheckCall(new ArsCheckRequest(
                        settleBankUniqueId,
                        phoneNumber,
                        subscriberName,
                        withdrawBankCode,
                        withdrawAccountNumber,
                        authenticationCode,
                        getDataManager().getCurrentUserId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(arsCheckResponse -> {
                    setIsLoading(false);
                    doAccountRegisterCall(arsCheckResponse);
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    private void doAccountRegisterCall(ArsCheckResponse arsCheckResponse) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doAccountRegisterCall(new AccountRegisterRequest(
                arsCheckResponse.getData().getSettleBankUniqueId(),
                this.withdrawBankCode,
                this.withdrawAccountNumber,
                this.identificationNumber,
                this.phoneNumber,
                "ARS",
                getDataManager().getCurrentUserId()
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(accountRegisterResponse -> {
            setIsLoading(false);
            getNavigator().openDialog();
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));

    }
}
