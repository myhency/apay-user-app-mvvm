package com.autoever.apay_user_app.ui.account.register.fail;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class RegisterAccountFailViewModel extends BaseViewModel<RegisterAccountFailNavigator> {

    public RegisterAccountFailViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
