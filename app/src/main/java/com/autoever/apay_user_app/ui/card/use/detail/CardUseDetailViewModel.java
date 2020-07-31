package com.autoever.apay_user_app.ui.card.use.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.CardUseDetailResponse;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;

public class CardUseDetailViewModel extends BaseViewModel<CardUseDetailNavigator> {

    private final MutableLiveData<String> amountLiveData;
    private final MutableLiveData<String> createdDateLiveData;
    private final MutableLiveData<String> storeNameLiveData;
    private final MutableLiveData<String> paymentStatusLiveData;
    private final MutableLiveData<Boolean> refundRequestedLiveData;
    private CardUseDetailResponse mCardUseDetailResponse;


    public CardUseDetailViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        amountLiveData = new MutableLiveData<>();
        createdDateLiveData = new MutableLiveData<>();
        storeNameLiveData = new MutableLiveData<>();
        paymentStatusLiveData = new MutableLiveData<>();
        refundRequestedLiveData = new MutableLiveData<>();
    }

    public void fetchUseHistoryContentsDetail(Long paymentHistoryId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doCardUseDetailCall(paymentHistoryId.intValue(), "user")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(cardUseDetailResponse -> {
                    setIsLoading(false);
                    this.mCardUseDetailResponse = cardUseDetailResponse;
                    amountLiveData.setValue(CommonUtils.formatToKRW(String.valueOf(cardUseDetailResponse.getData().getAmount())) + " P");
                    createdDateLiveData.setValue(simpleDateFormat.format(cardUseDetailResponse.getData().getCreatedDate()));
                    storeNameLiveData.setValue(cardUseDetailResponse.getData().getStoreName());
                    getNavigator().setBottomButton(
                            cardUseDetailResponse.getData().getPaymentStatus()
                            , cardUseDetailResponse.getData().isRefundRequested());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void doPaymentRefundReadyCall() {
        //TODO.
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doPaymentRefundReadyCall(new PaymentRefundReadyRequest(
                        mCardUseDetailResponse.getData().getUserId(),
                        mCardUseDetailResponse.getData().getStoreId(),
                        mCardUseDetailResponse.getData().getTokenSystemId(),
                        mCardUseDetailResponse.getData().getAmount(),
                        mCardUseDetailResponse.getData().getPaymentId(),
                        mCardUseDetailResponse.getData().getIdentifier()
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentRefundReadyResponse -> {
                    setIsLoading(false);
                    getNavigator().openPaymentRefundReadyReceiptFragment(paymentRefundReadyResponse);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<String> getAmountLiveData() {
        return amountLiveData;
    }

    public LiveData<String> getCreatedDateLiveData() {
        return createdDateLiveData;
    }

    public LiveData<String> getStoreNameLiveData() {
        return storeNameLiveData;
    }

    public LiveData<String> getPaymentStatusLiveData() {
        return paymentStatusLiveData;
    }

    public LiveData<Boolean> getRefundRequestedLiveData() {
        return refundRequestedLiveData;
    }


}
