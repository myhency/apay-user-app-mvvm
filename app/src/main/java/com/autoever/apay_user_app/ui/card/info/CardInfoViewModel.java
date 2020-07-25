package com.autoever.apay_user_app.ui.card.info;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import java.util.List;

public class CardInfoViewModel extends BaseViewModel<CardInfoNavigator> {
    private final MutableLiveData<List<CardUseHistoryResponse.CardUseHistory.Content>> cardUseHistoryContentLiveData;

    public CardInfoViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        cardUseHistoryContentLiveData = new MutableLiveData<>();
    }

    public void fetchCardUseHistoryContents(int tokenSystemId, int subscriberId, int pageNo, int pageSize) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doCardUseHistoryCall(
                        tokenSystemId,
                        subscriberId,
                        pageNo,
                        pageSize)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(cardUseHistoryResponse -> {
                    Log.d("debug", cardUseHistoryResponse.getData().getContents().get(0).getIdentifier());
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }

//    public void getHistoryTest() {
//        setIsLoading(true);
//        getCompositeDisposable().add(getDataManager()
//                //TODO. subscriberId 는 어떤걸 쓸지??
//                .doHistoryTestCall()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(response -> {
//                    Log.d("debug", "alsdkjflsakjflaksjflaksdjf:" + response.getData().getPageable().getSort().isSorted());
//                    setIsLoading(false);
//                }, throwable -> {
//                    setIsLoading(false);
//                    getNavigator().handleError(throwable);
//                }));
//    }
}
