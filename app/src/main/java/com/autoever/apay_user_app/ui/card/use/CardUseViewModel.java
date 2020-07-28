package com.autoever.apay_user_app.ui.card.use;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class CardUseViewModel extends BaseViewModel<CardUseNavigator> {

    public CardUseViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }
}
