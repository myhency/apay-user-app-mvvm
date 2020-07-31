package com.autoever.apay_user_app.ui.card.use;

import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;

public interface CardUseNavigator {

    void handleError(Throwable throwable);

    void openCardUseDetailFragment(Long paymentHistoryId, String paymentStatus);

//    void openPaymentCancelFragment(Long paymentHistoryId);

    void openPaymentRefundReadyReceiptFragment(PaymentRefundReadyResponse paymentRefundReadyResponse);
}
