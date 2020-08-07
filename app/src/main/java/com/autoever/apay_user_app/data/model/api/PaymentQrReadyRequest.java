package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "amount": 0,
 *   "identifier": "string",
 *   "userId": 0,
 *   "storeStaticQrInfo": {
 *     "qrType": 0,
 *     "hashedStoreId": "string",
 *     "storeName": "string",
 *     "hashedPaymentSystemId": "string",
 *     "paymentDeviceId": "string",
 *     "signature": "string"
 *   }
 * }
 */
public class PaymentQrReadyRequest {

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
    @SerializedName("storeStaticQrInfo")
    private StoreStaticQrInfo storeStaticQrInfo;

    public PaymentQrReadyRequest(Long amount, String identifier, Long userId, StoreStaticQrInfo storeStaticQrInfo) {
        this.amount = amount;
        this.identifier = identifier;
        this.userId = userId;
        this.storeStaticQrInfo = storeStaticQrInfo;
    }

    public static class StoreStaticQrInfo {

        @Expose
        @SerializedName("qrType")
        private Long qrType;

        @Expose
        @SerializedName("hashedStoreId")
        private String hashedStoreId;

        @Expose
        @SerializedName("storeName")
        private String storeName;

        @Expose
        @SerializedName("hashedPaymentSystemId")
        private String hashedPaymentSystemId;

        @Expose
        @SerializedName("paymentDeviceId")
        private String paymentDeviceId;

        @Expose
        @SerializedName("signature")
        private String signature;

        public StoreStaticQrInfo(Long qrType, String hashedStoreId, String storeName, String hashedPaymentSystemId, String paymentDeviceId, String signature) {
            this.qrType = qrType;
            this.hashedStoreId = hashedStoreId;
            this.storeName = storeName;
            this.hashedPaymentSystemId = hashedPaymentSystemId;
            this.paymentDeviceId = paymentDeviceId;
            this.signature = signature;
        }

        public Long getQrType() {
            return qrType;
        }

        public String getHashedStoreId() {
            return hashedStoreId;
        }

        public String getStoreName() {
            return storeName;
        }

        public String getHashedPaymentSystemId() {
            return hashedPaymentSystemId;
        }

        public String getPaymentDeviceId() {
            return paymentDeviceId;
        }

        public String getSignature() {
            return signature;
        }
    }
}
