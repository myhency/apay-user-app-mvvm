package com.autoever.apay_user_app.ui.splash;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void startSeeding() {
        getCompositeDisposable().add(getDataManager()
                .getUserBalance("1", "4")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    decideNextActivity();
                }, throwable -> {
                    decideNextActivity();
                })
        );
    }

    private void decideNextActivity() {
        //TODO. 로그인 화면을 구성해야 함.
//        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
//            getNavigator().openLoginActivity();
//        } else {
//            getNavigator().openMainActivity();
//        }

        getNavigator().openMainActivity();
    }
}
