package com.autoever.apay_user_app.data.remote;

import com.autoever.apay_user_app.data.model.api.AuthTestResponse;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_user_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_user_app.data.model.api.LoginRequest;
import com.autoever.apay_user_app.data.model.api.LoginResponse;
import com.autoever.apay_user_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyResponse;
import com.autoever.apay_user_app.data.model.api.UserRegisterRequest;
import com.autoever.apay_user_app.data.model.api.UserRegisterResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RepoService {

    @POST("payment/ready")
    Single<PaymentReadyResponse> doPaymentReadyCall(@Body PaymentReadyRequest paymentReadyRequest);

    @PUT("payment/do")
    Single<PaymentDoResponse> doPaymentDoCall(@Body PaymentDoRequest paymentDoRequest);

    @POST("tokenSystem/charge/ready")
    Single<ChargeReadyResponse> doChargeReadyCall(@Body ChargeReadyRequest chargeReadyRequest);

    @POST("user/tokenSystem/1")
    Single<UserRegisterResponse> doUserRegisterCall(@Body UserRegisterRequest userRegisterRequest);

    @POST("user/login")
    @Headers("No-Authentication: true")
    Single<LoginResponse> doLoginCall(@Body LoginRequest loginRequest);

    @GET("authTest/hello")
    Single<AuthTestResponse> doAuthTextCall();

    @GET("tokenSystem/1/tokenHistories?subscriberId=4")
    Single<CardUseHistoryResponse> doHistoryTestCall();
}
