package com.autoever.apay_user_app.ui.refund;

import android.util.Log;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.RefundDoRequest;
import com.autoever.apay_user_app.data.model.api.RefundReadyRequest;
import com.autoever.apay_user_app.data.model.api.RefundReadyResponse;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class RefundViewModel extends BaseViewModel<RefundNavigator> {

    public RefundViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void doRefundReadyCall() {
        Log.d("debug", "doRefundReadyCall started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doRefundReadyCall(new RefundReadyRequest(4000L, 1L))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(refundReadyResponse -> {
            setIsLoading(false);
            Log.d("debug", "amount" + refundReadyResponse.getData().getAmount());
            doRefundDoCall(refundReadyResponse);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }

    private void doRefundDoCall(RefundReadyResponse refundReadyResponse) {
        Log.d("debug", "doRefundDoCall started");
        Log.d("debug", "amount" + refundReadyResponse.getData().getAmount());
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doRefundDoCall(new RefundDoRequest(
                refundReadyResponse.getData().getChargeId(),
                "002",
                refundReadyResponse.getData().getSubscriberId(),
                refundReadyResponse.getData().getTokenSystemId(),
                refundReadyResponse.getData().getAmount()
        ))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(refundDoResponse -> {
            setIsLoading(false);
            getNavigator().openRefundReceiptFragment(refundDoResponse);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }
}
