package com.autoever.apay_user_app.ui.charge.receipt;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class ChargeReceiptViewModel extends BaseViewModel<ChargeReceiptNavigator> {

    public ChargeReceiptViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
