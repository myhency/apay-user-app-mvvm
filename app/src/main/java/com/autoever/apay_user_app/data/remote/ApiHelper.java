package com.autoever.apay_user_app.data.remote;

import com.autoever.apay_user_app.data.model.api.BalanceRequest;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiHelper {
    Single<BalanceResponse> getUserBalance(BalanceRequest balanceRequest);

    Single<PaymentReadyResponse> doPaymentReady(PaymentReadyRequest paymentReadyRequest);

    @POST("/payment/ready")
    Call<PaymentReadyResponse> doPaymentReadyCall(@Body PaymentReadyRequest paymentReadyRequest);
}
