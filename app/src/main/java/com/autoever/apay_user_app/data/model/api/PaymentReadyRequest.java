package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentReadyRequest {

    @Expose
    @SerializedName("userId")
    private String userId;

    @Expose
    @SerializedName("hashedStoreId")
    private String hashedStoreId;

    @Expose
    @SerializedName("tokenSystemId")
    private String tokenSystemId;

    @Expose
    @SerializedName("amount")
    private int amount;

    @Expose
    @SerializedName("identifier")
    private String identifier;

    public PaymentReadyRequest(String userId, String hashedStoreId, String tokenSystemId, int amount, String identifier) {
        this.userId = userId;
        this.hashedStoreId = hashedStoreId;
        this.tokenSystemId = tokenSystemId;
        this.amount = amount;
        this.identifier = identifier;
    }

    public String getUserId() {
        return userId;
    }

    public String getHashedStoreId() {
        return hashedStoreId;
    }

    public String getTokenSystemId() {
        return tokenSystemId;
    }

    public int getAmount() {
        return amount;
    }

    public String getIdentifier() {
        return identifier;
    }
}
