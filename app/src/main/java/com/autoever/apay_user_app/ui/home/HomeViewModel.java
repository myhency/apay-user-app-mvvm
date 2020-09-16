package com.autoever.apay_user_app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        balanceKRWLiveData = new MutableLiveData<>();
        loadUserBalance();
    }

    public void loadUserBalance() {
        setIsLoading(true);

        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .doGetBalanceCall(1, getDataManager().getCurrentUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(balanceResponse -> {
                    if (balanceResponse != null && balanceResponse.getData() != null) {
                        balanceKRWLiveData.setValue(CommonUtils.formatToKRW(balanceResponse.getData().getBalance().toString()));
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<String> getBalanceKRWLiveData() {
        return balanceKRWLiveData;
    }
}