package com.autoever.apay_user_app.ui.auth;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class AuthViewModel extends BaseViewModel<AuthNavigator> {

    public AuthViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public boolean isPasswordValid(String password) {
        return getDataManager().getEasyPassword().equals(password);
    }
}
