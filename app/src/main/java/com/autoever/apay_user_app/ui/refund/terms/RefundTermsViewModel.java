package com.autoever.apay_user_app.ui.refund.terms;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class RefundTermsViewModel extends BaseViewModel<RefundTermsNavigator> {

    public RefundTermsViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
