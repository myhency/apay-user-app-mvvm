package com.autoever.apay_user_app.ui.charge;

import com.autoever.apay_user_app.data.model.api.ChargeDoResponse;

public interface ChargeNavigator {

    void openAmountFragment();

    void handleError(Throwable throwable);

    void openAuthFragment();

    void doChargeReady();

    void openChargeReceiptFragment(ChargeDoResponse chargeDoResponse, String bankInfo);

    void openChargeFailFragment();
}
