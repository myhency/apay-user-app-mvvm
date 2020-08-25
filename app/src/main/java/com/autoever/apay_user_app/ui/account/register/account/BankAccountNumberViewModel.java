package com.autoever.apay_user_app.ui.account.register.account;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class BankAccountNumberViewModel extends BaseViewModel<BankAccountNumberNavigator> {

    public BankAccountNumberViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
