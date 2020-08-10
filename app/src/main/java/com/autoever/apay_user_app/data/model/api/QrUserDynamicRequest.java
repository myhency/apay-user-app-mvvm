package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QrUserDynamicRequest {

    @Expose
    @SerializedName("paymentSystemId")
    private Long paymentSystemId;

    public QrUserDynamicRequest(Long paymentSystemId) {
        this.paymentSystemId = paymentSystemId;
    }

    public Long getPaymentSystemId() {
        return paymentSystemId;
    }
}

