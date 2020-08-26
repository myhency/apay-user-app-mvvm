package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *    "data":{
 *       "chargeId":196,
 *       "bankCode":"CHARGE_READY",
 *       "subscriberId":4,
 *       "tokenSystemId":1,
 *       "amount":50000,
 *       "balance":182436.00,
 *       "chargeStatus":"CHARGE_COMPLETE",
 *       "createdDate":"2020-08-26T02:10:52.796"
 *      }
 * }
 */
public class ChargeDoResponse {

    @Expose
    @SerializedName("data")
    private ChargeDo data;

    public ChargeDo getData() { return data; }

    public static class ChargeDo {

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
        @SerializedName("balance")
        private Long balance;

        @Expose
        @SerializedName("chargeStatus")
        private String chargeStatus;

        @Expose
        @SerializedName("createdDate")
        private Date createdDate;

        public ChargeDo(Long chargeId, String bankCode, Long subscriberId, Long tokenSystemId, Long amount, Long balance, String chargeStatus, Date createdDate) {
            this.chargeId = chargeId;
            this.bankCode = bankCode;
            this.subscriberId = subscriberId;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.balance = balance;
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

        public Long getBalance() {
            return balance;
        }

        public String getChargeStatus() {
            return chargeStatus;
        }

        public Date getCreatedDate() {
            return createdDate;
        }
    }
}
