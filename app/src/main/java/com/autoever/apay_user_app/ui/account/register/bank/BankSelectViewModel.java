package com.autoever.apay_user_app.ui.account.register.bank;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class BankSelectViewModel extends BaseViewModel<BankSelectNavigator> {

    public BankSelectViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
