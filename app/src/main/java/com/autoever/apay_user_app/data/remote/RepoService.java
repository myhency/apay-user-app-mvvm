package com.autoever.apay_user_app.data.remote;

import com.autoever.apay_user_app.data.model.api.ArsCheckRequest;
import com.autoever.apay_user_app.data.model.api.ArsCheckResponse;
import com.autoever.apay_user_app.data.model.api.ArsRequestRequest;
import com.autoever.apay_user_app.data.model.api.ArsRequestResponse;
import com.autoever.apay_user_app.data.model.api.AuthTestResponse;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.data.model.api.BankAccountListRequest;
import com.autoever.apay_user_app.data.model.api.BankAccountListResponse;
import com.autoever.apay_user_app.data.model.api.CardUseDetailResponse;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.data.model.api.ChargeDoRequest;
import com.autoever.apay_user_app.data.model.api.ChargeDoResponse;
import com.autoever.apay_user_app.data.model.api.ChargeReadyRequest;
import com.autoever.apay_user_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_user_app.data.model.api.IdentityCheckRequest;
import com.autoever.apay_user_app.data.model.api.IdentityCheckResponse;
import com.autoever.apay_user_app.data.model.api.LoginRequest;
import com.autoever.apay_user_app.data.model.api.LoginResponse;
import com.autoever.apay_user_app.data.model.api.PaymentDoRequest;
import com.autoever.apay_user_app.data.model.api.PaymentDoResponse;
import com.autoever.apay_user_app.data.model.api.PaymentQrReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentReadyResponse;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyCancelResponse;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyRequest;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_user_app.data.model.api.QrUserDynamicRequest;
import com.autoever.apay_user_app.data.model.api.QrUserDynamicResponse;
import com.autoever.apay_user_app.data.model.api.RefundDoRequest;
import com.autoever.apay_user_app.data.model.api.RefundDoResponse;
import com.autoever.apay_user_app.data.model.api.RefundReadyRequest;
import com.autoever.apay_user_app.data.model.api.RefundReadyResponse;
import com.autoever.apay_user_app.data.model.api.UserRegisterRequest;
import com.autoever.apay_user_app.data.model.api.UserRegisterResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepoService {

    @POST("api/v2/payment/ready")
    Single<PaymentReadyResponse> doPaymentReadyCall(@Body PaymentReadyRequest paymentReadyRequest);

    @PUT("api/v2/payment/do")
    Single<PaymentDoResponse> doPaymentDoCall(@Body PaymentDoRequest paymentDoRequest);

    @POST("api/v2/tokenSystem/charge/ready")
    Single<ChargeReadyResponse> doChargeReadyCall(@Body ChargeReadyRequest chargeReadyRequest);

    @PUT("api/v2/tokenSystem/charge/do")
    Single<ChargeDoResponse> doChargeDoCall(@Body ChargeDoRequest chargeDoRequest);

    @POST("api/v2/user/tokenSystem/1")
    @Headers("No-Authentication: true")
    Single<UserRegisterResponse> doUserRegisterCall(@Body UserRegisterRequest userRegisterRequest);

    @POST("api/v2/user/login")
    @Headers("No-Authentication: true")
    Single<LoginResponse> doLoginCall(@Body LoginRequest loginRequest);

    @GET("api/v2/authTest/hello")
    Single<AuthTestResponse> doAuthTextCall();

    @GET("api/v2/tokenSystem/1/tokenHistories?subscriberId=4")
    Single<CardUseHistoryResponse> doHistoryTestCall();

    @GET("api/v2/user/tokenSystem/{tokenSystemId}/paymentHistories")
    Single<CardUseHistoryResponse> doCardUseHistoryCall(
            @Path("tokenSystemId") int tokenSystemId,
            @Query("userId") int userId,
            @Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize,
            @Query("date") String date,
            @Query("filter") String filter
    );

    @GET("api/v2/paymentHistory/{paymentHistoryId}")
    Single<CardUseDetailResponse> doCardUseDetailCall(
            @Path("paymentHistoryId") int paymentHistoryId,
            @Query("target") String target
    );

    @PUT("api/v2/payment/refund/ready")
    Single<PaymentRefundReadyResponse> doPaymentRefundReadyCall(@Body PaymentRefundReadyRequest paymentRefundReadyRequest);

    @PUT("api/v2/payment/refund/ready/cancel")
    Single<PaymentRefundReadyCancelResponse> doPaymentRefundReadyCancelCall(@Body PaymentRefundReadyRequest paymentRefundReadyRequest);

    @POST("api/v2/payment/ready/storeStatic")
    Single<PaymentReadyResponse> doPaymentQrReadyCall(@Body PaymentQrReadyRequest paymentQrReadyRequest);

    @POST("qr/userDynamic")
    Single<QrUserDynamicResponse> doQrUserDynamicCall(@Body QrUserDynamicRequest qrUserDynamicRequest);

    @GET("api/v2/tokenSystem/{tokenSystemId}/balance")
    Single<BalanceResponse> doGetBalanceCall(
            @Path("tokenSystemId") int tokenSystemId,
            @Query("subscriberId") int subscriberId
    );

    @POST("api/v2/settleBank/list/account")
    Single<BankAccountListResponse> doGetAccountListCall(@Body BankAccountListRequest bankAccountListRequest);

    @POST("api/v2/settleBank/identity/check")
    Single<IdentityCheckResponse> doIdentityCheckCall(@Body IdentityCheckRequest identityCheckRequest);

    @POST("api/v2/settleBank/ars/check")
    Single<ArsCheckResponse> doArsCheckCall(@Body ArsCheckRequest arsCheckRequest);

    @POST("api/v2/settleBank/ars/request")
    Single<ArsRequestResponse> doArsRequestCall(@Body ArsRequestRequest arsRequestRequest);

    @PUT("api/v2/tokenSystem/refund/ready")
    Single<RefundReadyResponse> doRefundReadyCall(@Body RefundReadyRequest refundReadyRequest);

    @PUT("api/v2/tokenSystem/refund/do")
    Single<RefundDoResponse> doRefundDoCall(@Body RefundDoRequest refundDoRequest);
}
