package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *    "data":{
 *       "chargeId":321,
 *       "bankCode":"002",
 *       "subscriberId":4,
 *       "tokenSystemId":1,
 *       "amount":50000,
 *       "balance":0,
 *       "chargeStatus":"REFUND_COMPLETE",
 *       "createdDate":"2020-08-28T07:50:50.676"
 *    }
 * }
 */
public class RefundDoResponse {

    @Expose
    @SerializedName("data")
    private RefundDo data;

    public RefundDo getData() { return data; }

    public static class RefundDo {

        @Expose
        @SerializedName("chargeId")
        private Long chargeId;

        @Expose
        @SerializedName("bankCode")
        private String bankCode;

        @Expose
        @SerializedName("subscriberId")
        private Long subscriberId;

        @Expose
        @SerializedName("tokenSystemId")
        private Long tokenSystemId;

        @Expose
        @SerializedName("amount")
        private Long amount;

        @Expose
        @SerializedName("chargeStatus")
        private String chargeStatus;

        @Expose
        @SerializedName("createdDate")
        private Date createdDate;

        public RefundDo(Long chargeId, String bankCode, Long subscriberId, Long tokenSystemId, Long amount, String chargeStatus, Date createdDate) {
            this.chargeId = chargeId;
            this.bankCode = bankCode;
            this.subscriberId = subscriberId;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.chargeStatus = chargeStatus;
            this.createdDate = createdDate;
        }

        public Long getChargeId() {
            return chargeId;
        }

        public String getBankCode() {
            return bankCode;
        }

        public Long getSubscriberId() {
            return subscriberId;
        }

        public Long getTokenSystemId() {
            return tokenSystemId;
        }

        public Long getAmount() {
            return amount;
        }

        public String getChargeStatus() {
            return chargeStatus;
        }

        public Date getCreatedDate() {
            return createdDate;
        }
    }
}
