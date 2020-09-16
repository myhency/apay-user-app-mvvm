package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {

    @Expose
    @SerializedName("loginId")
    private String loginId;

    @Expose
    @SerializedName("userName")
    private String userName;

    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;

    public ResetPasswordRequest(String loginId, String userName, String phoneNumber) {
        this.loginId = loginId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
