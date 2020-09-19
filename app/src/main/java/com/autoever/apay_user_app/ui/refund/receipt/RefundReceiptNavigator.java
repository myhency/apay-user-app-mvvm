package com.autoever.apay_user_app.ui.refund.receipt;

public interface RefundReceiptNavigator {

    void setBankCode(String bankCode);

    void handleNoAccount();

    void handleError(Throwable throwable);
}
