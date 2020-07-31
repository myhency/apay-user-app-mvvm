package com.autoever.apay_user_app.ui.card.use.receipt;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class PaymentRefundReadyReceiptViewModel extends BaseViewModel<PaymentRefundReadyReceiptNavigator> {

    public PaymentRefundReadyReceiptViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
