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
    private String subscriberId;

    @Expose
    @SerializedName("tokenSystemId")
    private String tokenSystemId;

    @Expose
    @SerializedName("amount")
    private int amount;

    public ChargeReadyRequest(String subscriberId, String tokenSystemId, int amount) {
        this.subscriberId = subscriberId;
        this.tokenSystemId = tokenSystemId;
        this.amount = amount;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getTokenSystemId() {
        return tokenSystemId;
    }

    public int getAmount() {
        return amount;
    }
}
