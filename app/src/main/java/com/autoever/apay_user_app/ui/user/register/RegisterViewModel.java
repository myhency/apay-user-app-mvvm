package com.autoever.apay_user_app.ui.user.register;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator> {

    public RegisterViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
