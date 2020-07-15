package com.autoever.apay_user_app.ui.payment;

public interface PaymentNavigator {

    void handleError(Throwable throwable);

    void openCustomScannerActivity();

    void showPriceFragment(String shopCode);

    void showPriceConfirmFragment(String shopCode, int price);
}
