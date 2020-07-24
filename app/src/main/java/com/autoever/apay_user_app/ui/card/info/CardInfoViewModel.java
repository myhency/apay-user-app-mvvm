package com.autoever.apay_user_app.ui.card.info;

import android.util.Log;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class CardInfoViewModel extends BaseViewModel<CardInfoNavigator> {

    public CardInfoViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void getHistoryTest() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .doHistoryTestCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    Log.d("debug","alsdkjflsakjflaksjflaksdjf:" + response.getData().getPageable().getSort().isSorted());
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }
}
