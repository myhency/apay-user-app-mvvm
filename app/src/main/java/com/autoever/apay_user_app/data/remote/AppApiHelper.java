package com.autoever.apay_user_app.data.remote;

import android.util.Log;

import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<BalanceResponse> getUserBalance(BalanceRequest balanceRequest) {
        Log.d("debug:", "ApiEndPoint.ENDPOINT_USER_BALANCE:" + ApiEndPoint.ENDPOINT_USER_BALANCE);
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_USER_BALANCE)
                .addPathParameter("tokenSystemId", balanceRequest.getTokenSystemId())
                .addQueryParameter("subscriberId", balanceRequest.getSubscriberId())
                .build()
                .getObjectSingle(BalanceResponse.class);
    }

    @Override
    public Single<PaymentReadyResponse> doPaymentReady(PaymentReadyRequest paymentReadyRequest) {
        Log.d("debug:", "ApiEndPoint.ENDPOINT_PAYMENT_READY:" + ApiEndPoint.ENDPOINT_PAYMENT_READY);
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_PAYMENT_READY)
                .addApplicationJsonBody(paymentReadyRequest)
                .build()
                .getObjectSingle(PaymentReadyResponse.class);
    }

    @Override
    public Call<PaymentReadyResponse> doPaymentReadyCall(PaymentReadyRequest paymentReadyRequest) {
        return null;
    }


}
