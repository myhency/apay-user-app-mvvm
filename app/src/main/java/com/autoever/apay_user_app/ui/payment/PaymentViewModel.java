package com.autoever.apay_user_app.ui.payment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_user_app.data.model.api.PaymentQrReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.QrUserDynamicRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;
import com.google.gson.JsonObject;


public class PaymentViewModel extends BaseViewModel<PaymentNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;

    private final MutableLiveData<String> paymentIdLiveData;

    private final MutableLiveData<String> storeNameLiveData;

    private final MutableLiveData<String> createdDateLiveData;

    private final MutableLiveData<String> amountLiveData;

    private final MutableLiveData<String> qrUserDynamicLiveData;

    private final MutableLiveData<String> balanceLeftKWRLiveData;


    public PaymentViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
        balanceKRWLiveData = new MutableLiveData<>();
        paymentIdLiveData = new MutableLiveData<>();
        storeNameLiveData = new MutableLiveData<>();
        createdDateLiveData = new MutableLiveData<>();
        amountLiveData = new MutableLiveData<>();
        balanceLeftKWRLiveData = new MutableLiveData<>();
        qrUserDynamicLiveData = new MutableLiveData<>();
        loadUserBalance();
    }

    private void loadUserBalance() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                //TODO. subscriberId 는 어떤걸 쓸지??
                .doGetBalanceCall(1, 4)
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
    public void loadPaymentId(int amount, String identifier, JsonObject staticQrData) {
        Log.d("debug", "loadPaymentId started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doPaymentQrReadyCall(new PaymentQrReadyRequest(
                        Long.valueOf(amount),
                        identifier,
                        4L,
                        new PaymentQrReadyRequest.StoreStaticQrInfo(
                                staticQrData.get("qrType").getAsLong(),
                                staticQrData.get("storeId").getAsString(),
                                staticQrData.get("storeName").getAsString(),
                                staticQrData.get("paymentSystemId").getAsString(),
                                staticQrData.get("paymentDeviceId").getAsString(),
                                staticQrData.get("signature").getAsString()
                        )
                ))
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
                    getNavigator().handleError(throwable);
                }));
    }

    /**
     * PaymentDoRequest Body
     * {
     * "userId": 0,
     * "storeId": 0,
     * "tokenSystemId": 0,
     * "amount": 0,
     * "paymentId": 0,
     * "identifier": "string"
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
                    storeNameLiveData.setValue(paymentDoResponse.getData().getStoreName());
                    amountLiveData.setValue(CommonUtils.formatToKRW(String.valueOf(paymentDoResponse.getData().getAmount())) + " P");
                    balanceLeftKWRLiveData.setValue(CommonUtils.formatToKRW(String.valueOf(paymentDoResponse.getData().getUserBalance())) + " P");
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

    public void loadQrUserDynamic() {
        Log.d("debug", "loadQrUserDynamic started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doQrUserDynamicCall(new QrUserDynamicRequest(
                        1L
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(qrUserDynamicResponse -> {
                    setIsLoading(false);
                    Log.d("debug", qrUserDynamicResponse.toString());
                    getNavigator().getQrUserDynamicData(qrUserDynamicResponse.parseToQrString());
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

    public LiveData<String> getStoreNameLiveData() {
        return storeNameLiveData;
    }

    public LiveData<String> getCreatedDateLiveData() {
        return createdDateLiveData;
    }

    public LiveData<String> getAmountLiveData() {
        return amountLiveData;
    }

    public LiveData<String> getBalanceLeftKWRLiveData() {
        return balanceLeftKWRLiveData;
    }

}
