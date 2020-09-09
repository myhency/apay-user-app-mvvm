package com.autoever.apay_user_app.ui.user.login.init;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class InitMyPasswordViewModel extends BaseViewModel<InitMyPasswordNavigator> {

    public InitMyPasswordViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
