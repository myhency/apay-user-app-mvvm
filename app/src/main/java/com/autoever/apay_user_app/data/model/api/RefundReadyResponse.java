package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *   "data": {
 *     "chargeId": 212,
 *     "subscriberId": 4,
 *     "tokenSystemId": 1,
 *     "amount": 532436,
 *     "chargeStatus": "REFUND_READY",
 *     "createdDate": "2020-08-26T07:00:15.834"
 *   }
 * }
 */
public class RefundReadyResponse {

    @Expose
    @SerializedName("data")
    private RefundReady data;

    public RefundReady getData() { return data; }

    public static class RefundReady {

        @Expose
        @SerializedName("chargeId")
        private Long chargeId;

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

        public RefundReady(Long chargeId, Long subscriberId, Long tokenSystemId, Long amount, String chargeStatus, Date createdDate) {
            this.chargeId = chargeId;
            this.subscriberId = subscriberId;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.chargeStatus = chargeStatus;
            this.createdDate = createdDate;
        }

        public Long getChargeId() {
            return chargeId;
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
