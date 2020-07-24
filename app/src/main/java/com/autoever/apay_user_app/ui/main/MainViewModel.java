package com.autoever.apay_user_app.ui.main;

import android.util.Log;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private static final String TAG = "MainViewModel";

    public MainViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void logout() {
        getDataManager().setUserAsLoggedOut();
        getNavigator().openLoginActivity();
    }

    public void authTest() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doAuthTextCall()
        .doOnSuccess(response -> {
            Log.d("debug", response.getData());

        })
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(response -> {

        }, throwable -> {
            getNavigator().handleError(throwable);
        }));

    }
}
