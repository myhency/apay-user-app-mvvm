package com.autoever.apay_user_app.ui.card.use.receipt;

public interface PaymentRefundReadyReceiptNavigator {

    void setBankCode(String bankCode);

    void handleNoAccount();

    void handleError(Throwable throwable);
}
