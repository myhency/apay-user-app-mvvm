package com.autoever.apay_user_app.ui.refund;

import com.autoever.apay_user_app.data.model.api.RefundDoResponse;

public interface RefundNavigator {

    void openRefundTermsFragment();

    void openRefundAmountFragment();

    void openAuthFragment();

    void doRefundReady();

    void openRefundReceiptFragment(RefundDoResponse refundDoResponse);

    void handleError(Throwable throwable);

    void openRefundFailFragment();
}
