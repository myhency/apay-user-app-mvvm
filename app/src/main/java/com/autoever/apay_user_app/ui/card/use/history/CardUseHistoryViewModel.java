package com.autoever.apay_user_app.ui.card.use.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class CardUseHistoryViewModel extends BaseViewModel<CardUseHistoryNavigator> {

    private final MutableLiveData<List<CardUseHistoryResponse.CardUseHistory.Content>> useHistoryContentLiveData;
    private List<CardUseHistoryResponse.CardUseHistory.Content> previousContents = new ArrayList<>();

    public CardUseHistoryViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        useHistoryContentLiveData = new MutableLiveData<>();
    }

    public void fetchUseHistoryContents(int tokenSystemId, int subscriberId, int pageNo, int pageSize, String date, String filter) {
        setIsLoading(true);
        if (pageNo == 0) { //처음업데이트 하는 경우, 또는 onRefresh called from SwipeRefreshLayout 경우 item list 를 초기화 해 줌.
            previousContents = new ArrayList<>();
        }

        getCompositeDisposable().add(getDataManager()
                .doCardUseHistoryCall(
                        tokenSystemId,
                        subscriberId,
                        pageNo,
                        pageSize,
                        date,
                        filter)
                .doOnSuccess(response -> getNavigator().onCompleteUpdatePaymentHistoryList())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(cardUseHistoryResponse -> {
                    setIsLoading(false);
                    if (cardUseHistoryResponse != null &&
                            cardUseHistoryResponse.getData() != null &&
                            cardUseHistoryResponse.getData().getContents().size() > 0) {
                        Log.d("debug", "contents");
                        previousContents.addAll(cardUseHistoryResponse.getData().getContents());
                    } else if (cardUseHistoryResponse.getData().getTotalPages() <= pageNo) {
                        Log.d("debug", "last item");
                    } else {
                        Log.d("debug", "no contents");
                        previousContents = new ArrayList<>();
                    }
                    useHistoryContentLiveData.setValue(previousContents);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<List<CardUseHistoryResponse.CardUseHistory.Content>> getUseHistoryContentLiveData() {
        return useHistoryContentLiveData;
    }
}
