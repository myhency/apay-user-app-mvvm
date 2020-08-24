package com.autoever.apay_user_app.ui.account.register.terms;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class AccountRegisterTermsViewModel extends BaseViewModel<AccountRegisterTermsNavigator> {

    public AccountRegisterTermsViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
