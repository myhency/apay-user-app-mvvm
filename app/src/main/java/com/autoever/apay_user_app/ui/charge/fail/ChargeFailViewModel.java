package com.autoever.apay_user_app.ui.charge.fail;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class ChargeFailViewModel extends BaseViewModel<ChargeFailNavigator> {

    public ChargeFailViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
