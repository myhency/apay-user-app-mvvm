package com.autoever.apay_user_app.ui.account.list;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class AccountListViewModel extends BaseViewModel<AccountListNavigator> {

    public AccountListViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }


}
