package com.autoever.apay_user_app.ui.account.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BankAccountListRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.ui.common.Bank;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountListViewModel extends BaseViewModel<AccountListNavigator> {

    private final MutableLiveData<String> registerDateTimeLiveData;
    private final MutableLiveData<String> bankNameLiveData;
    private final MutableLiveData<String> maskingAccountNumberLiveData;

    public AccountListViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        registerDateTimeLiveData = new MutableLiveData<>();
        bankNameLiveData = new MutableLiveData<>();
        maskingAccountNumberLiveData = new MutableLiveData<>();
    }


    public void doListAccountCall(Long subscriberId) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doGetAccountListCall(new BankAccountListRequest(subscriberId))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(bankAccountListResponse -> {
            setIsLoading(false);
            Date registerDateTime = new SimpleDateFormat("yyyyMMddHHmmss")
                    .parse(bankAccountListResponse
                            .getData()
                            .getAccountList()
                            .get(0)
                            .getRegisterDatetime()
                    );
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            String registerDateTimeString = "등록일시 " + simpleDateFormat.format(registerDateTime);
            String bankName = Bank.find(bankAccountListResponse.getData().getAccountList().get(0).getBankCode()).getBankName();
            String maskingAccountNumber = bankAccountListResponse.getData().getAccountList().get(0).getMaskingAccountNumber();
            registerDateTimeLiveData.setValue(registerDateTimeString);
            bankNameLiveData.setValue(bankName);
            maskingAccountNumberLiveData.setValue(maskingAccountNumber);
        }, throwable -> {
            setIsLoading(false);
        }));

    }

    public LiveData<String> getRegisterDateTimeLiveData() { return registerDateTimeLiveData; }
    public LiveData<String> getBankNameLiveData() { return bankNameLiveData; }
    public LiveData<String> getMaskingAccountNumberLiveData() { return maskingAccountNumberLiveData; }
}
