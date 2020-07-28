package com.autoever.apay_user_app.ui.card.common;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

public enum PaymentStatus {

    PAY_COMPLETE("결제", "PAY_COMPLETE"),
    PAY_READY("결제대기", "PAY_READY"),
    PAY_READY_CANCEL("결제대기취소", "PAY_READY_CANCEL"),
    PAY_CANCEL("결제취소", "PAY_CANCEL"),
    REFUND_COMPLETE("환전완료", "REFUND_COMPLETE");

    private String displayValue;
    private String status;

    PaymentStatus(String displayValue, String status) {
        this.displayValue = displayValue;
        this.status = status;
    }

    public static PaymentStatus find(String status) {
        Log.d("debug","Error: " + status);
        List<PaymentStatus> values = Arrays.asList(PaymentStatus.values());

        return values.stream()
                .filter(value -> value.getStatus().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
//        for(PaymentStatus value : values) {
//            if(value.getStatus().equals(status)) {
//                return value;
//            } else {
//                throw new IllegalArgumentException("Error: " + status);
//            }
//        }
//
//        throw new IllegalArgumentException("Error: " + status);
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getStatus() {
        return status;
    }
}
