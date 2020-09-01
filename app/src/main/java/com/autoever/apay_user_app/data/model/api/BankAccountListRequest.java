package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankAccountListRequest {

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    public BankAccountListRequest(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }
}
