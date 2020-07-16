package com.autoever.apay_user_app.ui.payment;

import android.util.Log;

import androidx.databinding.Observable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import io.reactivex.internal.operators.observable.ObservableAnySingle;


public class PaymentViewModel extends BaseViewModel<PaymentNavigator> {

    private final MutableLiveData<String> balanceKRWLiveData;

    private ObservableAnySingle<String> paymentId;

    public PaymentViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
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
                    if(balanceResponse != null && balanceResponse.getData() != null) {
                        balanceKRWLiveData.setValue(CommonUtils.formatToKRW(balanceResponse.getData().getBalance().toString())+" P");
                        Log.d("debug", "getBalance value: " + balanceResponse.getData().getBalance().toString());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<String> getBalanceKRWLiveData() { return balanceKRWLiveData; }

    public ObservableAnySingle<String> getPaymentId() {
        return paymentId;
    }

    /**
     * PaymentReadyRequest Body
     * {
     *   "userId": 4,
     *   "hashedStoreId": "string",
     *   "tokenSystemId": 1,
     *   "amount": 100,
     *   "identifier": "string"
     * }
     */
    public void loadPaymentId(int amount, String identifier) {
        Log.d("debug", "loadPaymentId started");
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doPaymentReady(new PaymentReadyRequest(
                        "1",
                        "d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35",
                        "4",
                        amount,
                        identifier))
                .doOnError(throwable -> {
                    Log.d("debug", throwable.toString());
                })
                .doOnSuccess(paymentReadyResponse -> {
                    Log.d("debug", paymentReadyResponse.toString());
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(paymentReadyResponse -> {
//                    if(paymentReadyResponse != null && paymentReadyResponse.getData() != null) {
//                        paymentId.setValue(paymentReadyResponse.getData().getPaymentId());
//                        Log.d("debug", "getPaymentId value: " + paymentReadyResponse.getData().getPaymentId());
//                    }
                    Log.d("debug", "paymentReadyResponse:" + paymentReadyResponse.toString());
                    setIsLoading(false);
                    getNavigator().showReceiptFragment(paymentReadyResponse.getData().getPaymentId());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void callPaymentReadyApi(int amount, String identifier) {

    }

//    public void confirmPassword() {
//        Log.d("debug", "confirmPassword");
//        getNavigator().confirmPassword();
//    }

    public boolean isPasswordValid(String password) {
        return true;
    }
}
