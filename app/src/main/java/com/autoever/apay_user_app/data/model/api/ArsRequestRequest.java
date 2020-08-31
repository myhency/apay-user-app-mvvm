package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArsRequestRequest {

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
    @SerializedName("identificationNumber")
    private String identificationNumber;

    @Expose
    @SerializedName("authenticationCode")
    private String authenticationCode;

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    public ArsRequestRequest(String phoneNumber, String subscriberName, String withdrawBankCode, String withdrawAccountNumber, String identificationNumber, String authenticationCode, Long subscriberId) {
        this.phoneNumber = phoneNumber;
        this.subscriberName = subscriberName;
        this.withdrawBankCode = withdrawBankCode;
        this.withdrawAccountNumber = withdrawAccountNumber;
        this.identificationNumber = identificationNumber;
        this.authenticationCode = authenticationCode;
        this.subscriberId = subscriberId;
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

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }
}
