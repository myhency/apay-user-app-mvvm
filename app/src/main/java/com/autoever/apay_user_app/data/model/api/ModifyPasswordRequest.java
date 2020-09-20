package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModifyPasswordRequest {

    @Expose
    @SerializedName("password")
    private String password;

    public ModifyPasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
