package com.autoever.apay_user_app.ui.refund.fail;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class RefundFailViewModel extends BaseViewModel<RefundFailNavigator> {

    public RefundFailViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
