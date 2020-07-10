package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class BalanceResponse {

    @Expose
    @SerializedName("balance")
    private Integer balance;

    public Integer getBalance() {
        return balance;
    }
}
