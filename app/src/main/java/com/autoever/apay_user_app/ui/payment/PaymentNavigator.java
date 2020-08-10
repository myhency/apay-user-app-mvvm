package com.autoever.apay_user_app.ui.payment;

import com.autoever.apay_user_app.data.model.api.QrUserDynamicResponse;

import java.util.Date;

public interface PaymentNavigator {

    void handleError(Throwable throwable);

    void openCustomScannerActivity();

    void showPriceFragment(String shopCode);

    void showPriceConfirmFragment(String shopCode, int price);

    void openAuthFragment();

    void showReceiptFragment(String storeName, Date createdDate, int amount, int userBalance);

    void doPaymentReady();

    void doPaymentDo(String userId, String storeId, String tokenSystemId, int amount, String paymentId, String identifier);

    void goNext();

    void getQrUserDynamicData(String parsedQrString);
}

