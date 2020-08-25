package com.autoever.apay_user_app.ui.account.register.auth;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class CellPhoneAuthViewModel extends BaseViewModel<CellPhoneAuthNavigator> {

    public CellPhoneAuthViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
