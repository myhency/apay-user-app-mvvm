package com.autoever.apay_user_app.ui.card.info;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class CardInfoFragmentViewModel extends BaseViewModel<CardInfoNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;
    private final MutableLiveData<List<CardUseHistoryResponse.CardUseHistory.Content>> cardUseHistoryContentLiveData;
    private List<CardUseHistoryResponse.CardUseHistory.Content> previousContents = new ArrayList<>();

    public CardInfoFragmentViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        cardUseHistoryContentLiveData = new MutableLiveData<>();
        balanceKRWLiveData = new MutableLiveData<>();

        loadUserBalance();
    }

    private void loadUserBalance() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .getUserBalance(new BalanceRequest("1", "4"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(balanceResponse -> {
                    if (balanceResponse != null && balanceResponse.getData() != null) {
                        balanceKRWLiveData.setValue(CommonUtils.formatToKRW(balanceResponse.getData().getBalance().toString()) + " P");
                        Log.d("debug", "getBalance value: " + balanceResponse.getData().getBalance().toString());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void fetchCardUseHistoryContents(int tokenSystemId, int subscriberId, int pageNo, int pageSize) {
        setIsLoading(true);
        if (pageNo == 0) { //처음업데이트 하는 경우, 또는 onRefresh called from SwipeRefreshLayout 경우 item list 를 초기화 해 줌.
            previousContents = new ArrayList<>();
            setIsLoading(false);
        }

        getCompositeDisposable().add(getDataManager()
                .doCardUseHistoryCall(
                        tokenSystemId,
                        subscriberId,
                        pageNo,
                        pageSize,
                        null,
                        null)
                .doOnSuccess(response -> getNavigator().onCompleteUpdatePaymentHistoryList())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(cardUseHistoryResponse -> {
                    setIsLoading(false);
                    if (cardUseHistoryResponse != null &&
                            cardUseHistoryResponse.getData() != null &&
                            cardUseHistoryResponse.getData().getContents().size() > 0) {
                        previousContents.addAll(cardUseHistoryResponse.getData().getContents());
                        cardUseHistoryContentLiveData.setValue(previousContents);
                    }
                }, throwable -> {
                    setIsLoading(false);
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

    public LiveData<List<CardUseHistoryResponse.CardUseHistory.Content>> getCardUseHistoryContentLiveData() {
        return cardUseHistoryContentLiveData;
    }

    public LiveData<String> getBalanceKRWLiveData() {
        return balanceKRWLiveData;
    }
}
