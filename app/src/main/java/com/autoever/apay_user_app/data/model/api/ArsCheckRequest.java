package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArsCheckRequest {

    @Expose
    @SerializedName("settleBankUniqueId")
    private String settleBankUniqueId;

    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;

    @Expose
    @SerializedName("subscriberName")
    private String subscriberName;

    @Expose
    @SerializedName("withdrawBankCode")
    private String withdrawBankCode;

    @Expose
    @SerializedName("withdrawAccountNumber")
    private String withdrawAccountNumber;

    @Expose
    @SerializedName("authenticationCode")
    private String authenticationCode;

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    public ArsCheckRequest(String settleBankUniqueId, String phoneNumber, String subscriberName, String withdrawBankCode, String withdrawAccountNumber, String authenticationCode, Long subscriberId) {
        this.settleBankUniqueId = settleBankUniqueId;
        this.phoneNumber = phoneNumber;
        this.subscriberName = subscriberName;
        this.withdrawBankCode = withdrawBankCode;
        this.withdrawAccountNumber = withdrawAccountNumber;
        this.authenticationCode = authenticationCode;
        this.subscriberId = subscriberId;
    }

    public String getSettleBankUniqueId() {
        return settleBankUniqueId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public String getWithdrawBankCode() {
        return withdrawBankCode;
    }

    public String getWithdrawAccountNumber() {
        return withdrawAccountNumber;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }
}
