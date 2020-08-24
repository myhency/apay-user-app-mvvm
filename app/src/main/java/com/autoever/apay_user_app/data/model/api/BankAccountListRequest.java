package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankAccountListRequest {

    @Expose
    @SerializedName("userId")
    private Long userId;

    public BankAccountListRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
