package com.autoever.apay_user_app.ui.account.register.ars;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class ArsAuthViewModel extends BaseViewModel<ArsAuthNavigator> {

    public ArsAuthViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
