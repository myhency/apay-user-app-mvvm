package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {
 *   "resultCd": "string", x
 *   "errCd": "string", x
 *   "resultMsg": "string",
 *   "mercntId": "string",
 *   "ordNo": 0,
 *   "authNo": "string",
 *   "trPrice": 0,
 *   "discntPrice": "string", default = 0
 *   "payPrice": 0, default=trPrice 랑 동일
 *   "trDay": "string",
 *   "trTime": "string",
 *   "mercntParam1": "string", OPTIONAL
 *   "mercntParam2": "string", OPTIONAL
 *   "signatureString": "string"
 * }
 */
public class ChargeDoRequest {

    @Expose
    @SerializedName("chargeId")
    private Long chargeId;

    @Expose
    @SerializedName("bankCode")
    private String bankCode;

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    @Expose
    @SerializedName("tokenSystemId")
    private Long tokenSystemId;

    @Expose
    @SerializedName("amount")
    private Long amount;

    public ChargeDoRequest(Long chargeId, String bankCode, Long subscriberId, Long tokenSystemId, Long amount) {
        this.chargeId = chargeId;
        this.bankCode = bankCode;
        this.subscriberId = subscriberId;
        this.tokenSystemId = tokenSystemId;
        this.amount = amount;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public String getBankCode() {
        return bankCode;
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
}
