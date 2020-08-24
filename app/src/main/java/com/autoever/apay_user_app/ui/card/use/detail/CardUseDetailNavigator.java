package com.autoever.apay_user_app.ui.card.use.detail;

import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;

public interface CardUseDetailNavigator {

    void handleError(Throwable throwable);

//    void openPaymentCancelFragment(Long paymentHistoryId);

    void openPaymentRefundReadyReceiptFragment(PaymentRefundReadyResponse paymentRefundReadyResponse);

    void setBottomButton(String paymentStatus, boolean refundRequested, boolean canceled);
}
