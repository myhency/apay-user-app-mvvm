package com.autoever.apay_user_app.ui.card;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class CardInfoViewModel extends BaseViewModel<CardInfoNavigator> {

    public CardInfoViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);

    }
}
