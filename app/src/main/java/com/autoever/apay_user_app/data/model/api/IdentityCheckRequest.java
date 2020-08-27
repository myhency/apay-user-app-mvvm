package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdentityCheckRequest {

    @Expose
    @SerializedName("settleBankUniqueId")
    private String settleBankUniqueId;

    @Expose
    @SerializedName("authenticationCode")
    private String authenticationCode;

    @Expose
    @SerializedName("tid")
    private String tid;

    public IdentityCheckRequest(String settleBankUniqueId, String authenticationCode, String tid) {
        this.settleBankUniqueId = settleBankUniqueId;
        this.authenticationCode = authenticationCode;
        this.tid = tid;
    }

    public String getSettleBankUniqueId() {
        return settleBankUniqueId;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public String getTid() {
        return tid;
    }
}
