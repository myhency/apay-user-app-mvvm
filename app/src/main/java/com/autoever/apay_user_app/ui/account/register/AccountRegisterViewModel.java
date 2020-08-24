package com.autoever.apay_user_app.ui.account.register;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class AccountRegisterViewModel extends BaseViewModel<AccountRegisterNavigator> {

    public AccountRegisterViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
