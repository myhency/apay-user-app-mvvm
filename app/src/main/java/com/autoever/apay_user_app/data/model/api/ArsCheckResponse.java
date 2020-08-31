package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArsCheckResponse {

    @Expose
    @SerializedName("data")
    private ArsCheck data;

    public ArsCheck getData() { return data; }

    public static class ArsCheck {

        @Expose
        @SerializedName("settleBankUniqueId")
        private String settleBankUniqueId;

        public ArsCheck(String settleBankUniqueId) {
            this.settleBankUniqueId = settleBankUniqueId;
        }

        public String getSettleBankUniqueId() {
            return settleBankUniqueId;
        }
    }
}
