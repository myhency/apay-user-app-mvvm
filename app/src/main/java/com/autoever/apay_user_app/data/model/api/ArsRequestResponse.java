package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *    "authenticationCode":"123456",
 *    "identificationNumber":"9911121",
 *    "phoneNumber":"01093577050",
 *    "subscriberId":4,
 *    "subscriberName":"홍길동",
 *    "withdrawAccountNumber":"011234567890",
 *    "withdrawBankCode":"020"
 * }
 */
public class ArsRequestResponse {

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
