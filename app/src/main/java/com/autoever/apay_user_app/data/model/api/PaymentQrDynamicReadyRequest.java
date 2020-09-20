package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentQrDynamicReadyRequest {

    @Expose
    @SerializedName("amount")
    private Long amount;

    @Expose
    @SerializedName("identifier")
    private String identifier;

    @Expose
    @SerializedName("userId")
    private Long userId;

    @Expose
    @SerializedName("storeDynamicQrInfo")
    private StoreDynamicQrInfo storeDynamicQrInfo;

    public PaymentQrDynamicReadyRequest(Long amount, String identifier, Long userId, StoreDynamicQrInfo storeDynamicQrInfo) {
        this.amount = amount;
        this.identifier = identifier;
        this.userId = userId;
        this.storeDynamicQrInfo = storeDynamicQrInfo;
    }

    public static class StoreDynamicQrInfo {

        @Expose
        @SerializedName("qrType")
        private Long qrType;

        @Expose
        @SerializedName("hashedStoreId")
        private String hashedStoreId;

        @Expose
        @SerializedName("hashedPaymentSystemId")
        private String hashedPaymentSystemId;

        @Expose
        @SerializedName("token")
        private String token;

        @Expose
        @SerializedName("signature")
        private String signature;

        public StoreDynamicQrInfo(Long qrType, String hashedStoreId, String hashedPaymentSystemId, String token, String signature) {
            this.qrType = qrType;
            this.hashedStoreId = hashedStoreId;
            this.hashedPaymentSystemId = hashedPaymentSystemId;
            this.token = token;
            this.signature = signature;
        }

        public Long getQrType() {
            return qrType;
        }

        public String getHashedStoreId() {
            return hashedStoreId;
        }

        public String getHashedPaymentSystemId() {
            return hashedPaymentSystemId;
        }

        public String getToken() {
            return token;
        }

        public String getSignature() {
            return signature;
        }
    }
}
