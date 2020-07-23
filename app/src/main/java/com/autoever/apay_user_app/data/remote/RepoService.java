package com.autoever.apay_user_app.data.remote;

import com.autoever.apay_user_app.data.model.api.AuthTestResponse;
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
    @Headers("No-Authentication: true")
    Single<PaymentReadyResponse> doPaymentReadyCall(@Body PaymentReadyRequest paymentReadyRequest);

    @PUT("payment/do")
    @Headers("No-Authentication: true")
    Single<PaymentDoResponse> doPaymentDoCall(@Body PaymentDoRequest paymentDoRequest);

    @POST("tokenSystem/charge/ready")
    @Headers("No-Authentication: true")
    Single<ChargeReadyResponse> doChargeReadyCall(@Body ChargeReadyRequest chargeReadyRequest);

    @POST("user/tokenSystem/1")
    @Headers("No-Authentication: true")
    Single<UserRegisterResponse> doUserRegisterCall(@Body UserRegisterRequest userRegisterRequest);

    @POST("user/login")
    @Headers("No-Authentication: true")
    Single<LoginResponse> doLoginCall(@Body LoginRequest loginRequest);

    @GET("authTest/hello")
    Single<AuthTestResponse> doAuthTextCall();
}
