package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "settleBankUniqueId": "uniqueId",
 *   "withdrawBankCode": "002",
 *   "withdrawAccountNumber": "001000100001001",
 *   "identificationNumber": "9104192",
 *   "phoneNumber": "01030887369",
 *   "authenticationMethod": "ARS",
 *   "subscriberId": 4
 * }
 */
public class AccountRegisterRequest {

    @Expose
    @SerializedName("settleBankUniqueId")
    private String settleBankUniqueId;

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
    @SerializedName("phoneNumber")
    private String phoneNumber;

    @Expose
    @SerializedName("authenticationMethod")
    private String authenticationMethod;

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    public AccountRegisterRequest(String settleBankUniqueId, String withdrawBankCode, String withdrawAccountNumber, String identificationNumber, String phoneNumber, String authenticationMethod, Long subscriberId) {
        this.settleBankUniqueId = settleBankUniqueId;
        this.withdrawBankCode = withdrawBankCode;
        this.withdrawAccountNumber = withdrawAccountNumber;
        this.identificationNumber = identificationNumber;
        this.phoneNumber = phoneNumber;
        this.authenticationMethod = authenticationMethod;
        this.subscriberId = subscriberId;
    }

    public String getSettleBankUniqueId() {
        return settleBankUniqueId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }
}
