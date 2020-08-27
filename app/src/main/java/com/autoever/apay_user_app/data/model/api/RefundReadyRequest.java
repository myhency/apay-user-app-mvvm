package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class RefundReadyRequest {

    @Expose
    @SerializedName("subscriberId")
    private Long subscriberId;

    @Expose
    @SerializedName("tokenSystemId")
    private Long tokenSystemId;

    public RefundReadyRequest(Long subscriberId, Long tokenSystemId) {
        this.subscriberId = subscriberId;
        this.tokenSystemId = tokenSystemId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public Long getTokenSystemId() {
        return tokenSystemId;
    }
}
