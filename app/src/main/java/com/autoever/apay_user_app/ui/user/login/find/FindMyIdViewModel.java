package com.autoever.apay_user_app.ui.user.login.find;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class FindMyIdViewModel extends BaseViewModel<FindMyIdNavigator> {

    public FindMyIdViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
