package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FindLoginIdRequest {

    @Expose
    @SerializedName("userName")
    private String userName;

    @Expose
    @SerializedName("phoneNumber")
    private String phoneNumber;

    public FindLoginIdRequest(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
