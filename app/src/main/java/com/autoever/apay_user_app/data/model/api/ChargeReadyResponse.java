package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * {
 *   "data": {
 *     "hdInfo": "IA_AUTHPAGE_1.0_1.0",
 *     "apiVer": "1.0",
 *     "processType": "D",
 *     "mercntId": "M2051453",
 *     "productNm": "A-money",
 *     "dutyFreeYn": "N",
 *     "ordNo": 474,
 *     "trDay": "20200720",
 *     "trTime": "134136",
 *     "trPrice": "D9F2128A1B51CF32896596B2DACF0B15",
 *     "signature": "d01c818811bb85cf1af9ba5faabaa8dce213c4ea2e7abe11be280d7fa3a56c20"
 *   }
 * }
 */
public class ChargeReadyResponse {

    @Expose
    @SerializedName("data")
    private ChargeReady data;

    public ChargeReady getData() { return data; }

    public static class ChargeReady {

        @Expose
        @SerializedName("chargeId")
        private Long chargeId;

        @Expose
        @SerializedName("subscriberId")
        private Long subscriberId;

        @Expose
        @SerializedName("tokenSystemId")
        private Long tokenSystemId;

        @Expose
        @SerializedName("amount")
        private Long amount;

        @Expose
        @SerializedName("chargeStatus")
        private String chargeStatus;

        @Expose
        @SerializedName("createdDate")
        private Date createdDate;

        public ChargeReady(Long chargeId, Long subscriberId, Long tokenSystemId, Long amount, String chargeStatus, Date createdDate) {
            this.chargeId = chargeId;
            this.subscriberId = subscriberId;
            this.tokenSystemId = tokenSystemId;
            this.amount = amount;
            this.chargeStatus = chargeStatus;
            this.createdDate = createdDate;
        }

        public Long getChargeId() {
            return chargeId;
        }

        public Long getSubscriberId() {
            return subscriberId;
        }

        public Long getTokenSystemId() {
            return tokenSystemId;
        }

        public Long getAmount() {
            return amount;
        }

        public String getChargeStatus() {
            return chargeStatus;
        }

        public Date getCreatedDate() {
            return createdDate;
        }
    }
}
