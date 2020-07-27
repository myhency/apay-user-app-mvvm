package com.autoever.apay_user_app.ui.card;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class CardInfoViewModel extends BaseViewModel<CardInfoNavigator> {



    public CardInfoViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);

    }


}
