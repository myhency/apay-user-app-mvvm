package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountTerminationRequest {

    @Expose
    @SerializedName("withdrawBankCode")
    private String withdrawBankCode;

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    public AccountTerminationRequest(String withdrawBankCode, Long subscriberId) {
        this.withdrawBankCode = withdrawBankCode;
        this.subscriberId = subscriberId;
    }

    public String getWithdrawBankCode() {
        return withdrawBankCode;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }
}
