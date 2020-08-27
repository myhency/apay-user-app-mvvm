package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefundDoRequest {

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

    public RefundDoRequest(Long chargeId, String bankCode, Long subscriberId, Long tokenSystemId, Long amount) {
        this.chargeId = chargeId;
        this.bankCode = bankCode;
        this.subscriberId = subscriberId;
        this.tokenSystemId = tokenSystemId;
        this.amount = amount;
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
}
