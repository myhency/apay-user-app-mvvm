package com.autoever.apay_user_app.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private static final String TAG = "MainViewModel";

    public MainViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }


}
