package com.autoever.apay_user_app.ui.payment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;


public class PaymentViewModel extends BaseViewModel<PaymentNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;

    private final MutableLiveData<String> paymentIdLiveData;


    public PaymentViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        balanceKRWLiveData = new MutableLiveData<>();
        paymentIdLiveData = new MutableLiveData<>();
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

    /**
     * PaymentReadyRequest Body
     * {
     * "userId": 4,
     * "hashedStoreId": "string",
     * "tokenSystemId": 1,
     * "amount": 100,
     * "identifier": "string"
     * }
     */
    public void loadPaymentId(int amount, String identifier) {
        Log.d("debug", "loadPaymentId started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doPaymentReadyCall(new PaymentReadyRequest(
                        "4",
                        "2",
                        "1",
                        amount,
                        identifier))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentReadyResponse -> {
                    setIsLoading(false);
                    getNavigator().doPaymentDo(
                            paymentReadyResponse.getData().getUserId(),
                            paymentReadyResponse.getData().getStoreId(),
                            paymentReadyResponse.getData().getTokenSystemId(),
                            paymentReadyResponse.getData().getAmount(),
                            paymentReadyResponse.getData().getPaymentId(),
                            paymentReadyResponse.getData().getIdentifier());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    /**
     * PaymentDoRequest Body
     * {
     *   "userId": 0,
     *   "storeId": 0,
     *   "tokenSystemId": 0,
     *   "amount": 0,
     *   "paymentId": 0,
     *   "identifier": "string"
     * }
     */
    public void doPayment(String userId, String storeId, String tokenSystemId, int amount, String paymentId, String identifier) {
        Log.d("debug", "doPayment started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doPaymentDoCall(new PaymentDoRequest(
                        userId,
                        storeId,
                        tokenSystemId,
                        amount,
                        paymentId,
                        identifier))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentDoResponse -> {
                    setIsLoading(false);
                    Log.d("debug", paymentDoResponse.toString());
                    getNavigator().showReceiptFragment(
                            paymentDoResponse.getData().getStoreName(),
                            paymentDoResponse.getData().getCreatedDate(),
                            paymentDoResponse.getData().getAmount(),
                            paymentDoResponse.getData().getUserBalance()
                    );
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public LiveData<String> getBalanceKRWLiveData() {
        return balanceKRWLiveData;
    }

    public LiveData<String> getPaymentIdLiveData() {
        return paymentIdLiveData;
    }

}
