package com.autoever.apay_user_app.ui.payment;

public interface PaymentNavigator {

    void handleError(Throwable throwable);

    void openCustomScannerActivity();

    void showPaymentFragment(String shopCode);
}
