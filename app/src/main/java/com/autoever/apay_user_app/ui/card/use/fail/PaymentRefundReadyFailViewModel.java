package com.autoever.apay_user_app.ui.card.use.fail;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class PaymentRefundReadyFailViewModel extends BaseViewModel<PaymentRefundReadyFailNavigator> {

    public PaymentRefundReadyFailViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
