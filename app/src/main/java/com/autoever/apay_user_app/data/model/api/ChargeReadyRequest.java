package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "subscriberId": 4,
 *   "tokenSystemId": 1,
 *   "trPrice": 10000
 * }
 */
public class ChargeReadyRequest {

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    @Expose
    @SerializedName("tokenSystemId")
    private Long tokenSystemId;

    @Expose
    @SerializedName("amount")
    private Long amount;

    public ChargeReadyRequest(Long subscriberId, Long tokenSystemId, Long amount) {
        this.subscriberId = subscriberId;
        this.tokenSystemId = tokenSystemId;
        this.amount = amount;
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
